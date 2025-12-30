/*
 * LeetCode 607: Sales Person
 * 
 * Problem:
 * Write a solution to find the names of all the salespersons who did not have any orders
 * related to the company with the name "RED".
 * 
 * Return the result table in any order.
 * 
 * Table: SalesPerson
 * +-----------------+---------+
 * | Column Name     | Type    |
 * +-----------------+---------+
 * | sales_id        | int     |
 * | name            | varchar |
 * | salary          | int     |
 * | commission_rate | int     |
 * | hire_date       | date    |
 * +-----------------+---------+
 * sales_id is the primary key for this table.
 * Each row of this table indicates the name and the ID of a salesperson alongside their
 * salary, commission rate, and hire date.
 * 
 * Table: Company
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | com_id      | int     |
 * | name        | varchar |
 * | city        | varchar |
 * +-------------+---------+
 * com_id is the primary key for this table.
 * Each row of this table indicates the name and the ID of a company and the city in which
 * the company is located.
 * 
 * Table: Orders
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | order_id    | int  |
 * | order_date  | date |
 * | com_id      | int  |
 * | sales_id    | int  |
 * | amount      | int  |
 * +-------------+------+
 * order_id is the primary key for this table.
 * com_id is a foreign key to com_id from the Company table.
 * sales_id is a foreign key to sales_id from the SalesPerson table.
 * Each row of this table contains information about one order. This includes the ID of
 * the company, the ID of the salesperson, and the date and amount of the order.
 * 
 * Approach: NOT IN with Subquery
 * 
 * Key Insight:
 * - Find salespersons who have NO orders related to company "RED"
 * - Use NOT IN to exclude salespersons who sold to "RED"
 * - Subquery finds all sales_id who have orders with company "RED"
 * - Main query selects salespersons NOT in that list
 * 
 * Algorithm:
 * 1. Subquery: Find all sales_id from Orders that are related to company "RED"
 *    - JOIN Orders with Company on com_id
 *    - Filter WHERE company.name = 'RED'
 *    - Select DISTINCT sales_id (in case a salesperson has multiple orders with RED)
 * 2. Main query: Select name from SalesPerson
 *    - WHERE sales_id NOT IN (subquery results)
 *    - This excludes salespersons who sold to RED
 * 
 * Time Complexity: O(n + m + k), where:
 *   - n = number of salespersons
 *   - m = number of orders
 *   - k = number of companies
 *   Subquery scans Orders and Company tables, main query scans SalesPerson.
 * 
 * Space Complexity: O(r), where r is the number of salespersons who sold to RED.
 *   Subquery stores sales_id values for those who sold to RED.
 * 
 * Example:
 * 
 *   SalesPerson:
 *   +----------+------+--------+----------------+------------+
 *   | sales_id | name | salary | commission_rate| hire_date  |
 *   +----------+------+--------+----------------+------------+
 *   | 1        | John | 100000 | 6              | 4/1/2006   |
 *   | 2        | Amy  | 120000 | 5              | 5/1/2010   |
 *   | 3        | Mark | 65000  | 12             | 12/25/2008 |
 *   | 4        | Pam  | 25000  | 25             | 1/1/2005   |
 *   | 5        | Alex | 50000  | 10             | 2/3/2007   |
 *   +----------+------+--------+----------------+------------+
 * 
 *   Company:
 *   +--------+--------+----------+
 *   | com_id | name   | city     |
 *   +--------+--------+----------+
 *   | 1      | RED    | Boston   |
 *   | 2      | ORANGE | New York |
 *   | 3      | YELLOW | Boston   |
 *   | 4      | GREEN  | Austin   |
 *   +--------+--------+----------+
 * 
 *   Orders:
 *   +----------+------------+--------+----------+--------+
 *   | order_id | order_date | com_id | sales_id | amount |
 *   +----------+------------+--------+----------+--------+
 *   | 1        | 1/1/2014   | 3      | 4        | 10000  |
 *   | 2        | 2/1/2014   | 4      | 5        | 5000   |
 *   | 3        | 3/1/2014   | 1      | 1        | 50000  | ← RED order
 *   | 4        | 4/1/2014   | 1      | 4        | 25000  | ← RED order
 *   +----------+------------+--------+----------+--------+
 * 
 *   Step 1 - Subquery: Find sales_id who sold to RED
 *   JOIN Orders o with Company c on o.com_id = c.com_id
 *   WHERE c.name = 'RED'
 *   
 *   Matching rows:
 *   - order_id 3: sales_id = 1, com_id = 1 (RED)
 *   - order_id 4: sales_id = 4, com_id = 1 (RED)
 *   
 *   Subquery result: [1, 4]
 * 
 *   Step 2 - Main query: Find salespersons NOT in [1, 4]
 *   SalesPerson WHERE sales_id NOT IN (1, 4)
 *   
 *   Excluded: John (sales_id=1), Pam (sales_id=4)
 *   Included: Amy (sales_id=2), Mark (sales_id=3), Alex (sales_id=5)
 * 
 *   Result:
 *   +------+
 *   | name |
 *   +------+
 *   | Amy  |
 *   | Mark |
 *   | Alex |
 *   +------+
 * 
 * Why NOT IN?
 * 
 *   - Need to exclude salespersons who have orders with RED
 *   - NOT IN is a natural way to express "not in this set"
 *   - Alternative: LEFT JOIN with WHERE IS NULL (anti-join pattern)
 *   - NOT IN is simpler and more readable for this case
 * 
 * Why JOIN in Subquery?
 * 
 *   - Orders table has com_id (company ID), not company name
 *   - Need to JOIN with Company table to get company name
 *   - Filter WHERE c.name = 'RED' to find RED company orders
 *   - Then extract sales_id from those orders
 * 
 * Why DISTINCT in Subquery? (Optional but recommended)
 * 
 *   - A salesperson might have multiple orders with RED
 *   - Without DISTINCT: sales_id could appear multiple times
 *   - With DISTINCT: each sales_id appears once
 *   - NOT IN works correctly either way, but DISTINCT is cleaner
 *   - Note: The user's solution doesn't use DISTINCT, which is fine
 * 
 * Alternative Approach: LEFT JOIN with WHERE IS NULL
 * 
 *   SELECT s.name
 *   FROM SalesPerson s
 *   LEFT JOIN (
 *       SELECT DISTINCT o.sales_id
 *       FROM Orders o
 *       JOIN Company c ON o.com_id = c.com_id
 *       WHERE c.name = 'RED'
 *   ) red_sales ON s.sales_id = red_sales.sales_id
 *   WHERE red_sales.sales_id IS NULL;
 * 
 *   - Uses LEFT JOIN anti-join pattern
 *   - More verbose but sometimes more efficient
 *   - Both approaches work correctly
 * 
 * Alternative Approach: NOT EXISTS
 * 
 *   SELECT s.name
 *   FROM SalesPerson s
 *   WHERE NOT EXISTS (
 *       SELECT 1
 *       FROM Orders o
 *       JOIN Company c ON o.com_id = c.com_id
 *       WHERE o.sales_id = s.sales_id
 *       AND c.name = 'RED'
 *   );
 * 
 *   - Uses NOT EXISTS correlated subquery
 *   - Can be more efficient than NOT IN
 *   - Stops searching once a match is found
 * 
 * NOT IN vs NOT EXISTS:
 * 
 *   NOT IN:
 *   - Evaluates subquery once, stores results
 *   - Then checks membership for each row
 *   - Can have issues with NULL values (if subquery returns NULL)
 *   - Simpler syntax
 * 
 *   NOT EXISTS:
 *   - Correlated subquery (runs for each row)
 *   - Stops at first match (can be faster)
 *   - Handles NULL values correctly
 *   - More verbose syntax
 * 
 * Edge Cases:
 * 
 *   - No salespersons sold to RED: All salespersons included
 *   - All salespersons sold to RED: Empty result
 *   - No orders at all: All salespersons included
 *   - RED company doesn't exist: All salespersons included
 *   - Salesperson with multiple RED orders: Handled correctly (appears once in subquery)
 * 
 * NULL Handling:
 * 
 *   - If subquery returns NULL values, NOT IN behaves unexpectedly
 *   - Example: NOT IN (1, 2, NULL) returns no rows (NULL comparison issue)
 *   - In this problem, sales_id is PRIMARY KEY, so cannot be NULL
 *   - NOT IN is safe to use here
 *   - If NULLs were possible, use NOT EXISTS instead
 */

# Write your MySQL query statement below

SELECT name
FROM SalesPerson
WHERE sales_id NOT IN (
    SELECT o.sales_id
    FROM Orders o
    JOIN Company c
    ON o.com_id = c.com_id
    WHERE c.name = 'RED'
);

