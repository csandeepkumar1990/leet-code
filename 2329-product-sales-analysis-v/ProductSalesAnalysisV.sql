/*
 * LeetCode 2329: Product Sales Analysis V
 * 
 * Problem:
 * Write a solution to report the spending of each user.
 * Return the result table ordered by spending in descending order.
 * In case of a tie, order by user_id in ascending order.
 * 
 * Table: Sales
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | sale_id     | int     |
 * | product_id  | int     |
 * | user_id     | int     |
 * | quantity    | int     |
 * +-------------+---------+
 * sale_id is the primary key for this table.
 * Each row contains information about a sale.
 * 
 * Table: Product
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | price       | int     |
 * +-------------+---------+
 * product_id is the primary key for this table.
 * Each row contains information about the price of a product.
 * 
 * Approach: JOIN with Aggregation
 * 
 * Key Insight:
 * - Join Sales and Product tables to get price information
 * - Calculate spending per sale: quantity × price
 * - Sum spending per user using GROUP BY
 * - Order by spending DESC, then user_id ASC
 * 
 * Formula:
 *   spending = SUM(quantity × price) per user_id
 * 
 * Algorithm:
 * 1. JOIN Sales and Product on product_id
 * 2. Calculate spending per sale: quantity × price
 * 3. GROUP BY user_id to aggregate spending per user
 * 4. SUM(quantity × price) to get total spending
 * 5. ORDER BY spending DESC, user_id ASC
 * 
 * Example:
 * 
 *   Sales:
 *   +---------+------------+---------+----------+
 *   | sale_id | product_id | user_id | quantity |
 *   +---------+------------+---------+----------+
 *   | 1       | 1          | 101     | 10       |
 *   | 2       | 2          | 101     | 2        |
 *   | 3       | 3          | 102     | 3        |
 *   | 4       | 1          | 102     | 2        |
 *   | 5       | 2          | 103     | 3        |
 *   | 6       | 1          | 101     | 5        |
 *   +---------+------------+---------+----------+
 * 
 *   Product:
 *   +------------+-------+
 *   | product_id | price |
 *   +------------+-------+
 *   | 1          | 10    |
 *   | 2          | 25    |
 *   | 3          | 15    |
 *   +------------+-------+
 * 
 *   Step 1 - JOIN on product_id:
 *   +---------+---------+----------+------------+-------+
 *   | sale_id | user_id | quantity | product_id | price |
 *   +---------+---------+----------+------------+-------+
 *   | 1       | 101     | 10       | 1          | 10    |
 *   | 2       | 101     | 2        | 2          | 25    |
 *   | 3       | 102     | 3        | 3          | 15    |
 *   | 4       | 102     | 2        | 1          | 10    |
 *   | 5       | 103     | 3        | 2          | 25    |
 *   | 6       | 101     | 5        | 1          | 10    |
 *   +---------+---------+----------+------------+-------+
 * 
 *   Step 2 - Calculate spending per sale:
 *   User 101: (10 × 10) + (2 × 25) + (5 × 10) = 100 + 50 + 50 = 200
 *   User 102: (3 × 15) + (2 × 10) = 45 + 20 = 65
 *   User 103: (3 × 25) = 75
 * 
 *   Step 3 - Group and sum:
 *   User 101: SUM(100, 50, 50) = 200
 *   User 102: SUM(45, 20) = 65
 *   User 103: SUM(75) = 75
 * 
 *   Step 4 - Order by spending DESC, user_id ASC:
 *   +---------+----------+
 *   | user_id | spending |
 *   +---------+----------+
 *   | 101     | 200      |
 *   | 103     | 75       |
 *   | 102     | 65       |
 *   +---------+----------+
 * 
 * Why JOIN?
 *   - Sales table has quantity but not price
 *   - Product table has price but not quantity
 *   - JOIN combines both to calculate total spending
 *   - INNER JOIN ensures only valid product_ids are included
 * 
 * Why SUM(quantity * price)?
 *   - Each sale: spending = quantity × price
 *   - Multiple sales per user: sum all individual spending
 *   - Total spending = sum of (quantity × price) for all sales
 * 
 * Why GROUP BY user_id?
 *   - Aggregates spending calculations per user
 *   - Without GROUP BY, we'd get one row per sale
 *   - With GROUP BY, we get one row per user with total spending
 * 
 * ORDER BY Clause:
 *   - spending DESC: Highest spending first
 *   - user_id ASC: In case of tie, lower user_id first
 *   - Multiple ORDER BY columns: primary sort by spending, secondary by user_id
 * 
 * Alternative JOIN Syntax:
 *   - ON s.product_id = p.product_id (explicit)
 *   - USING (product_id) (simpler, when column names match)
 *   - Both produce the same result
 * 
 * Edge Cases:
 *   - User with no sales: Not included (INNER JOIN excludes them)
 *   - Product with no sales: Not included (INNER JOIN excludes them)
 *   - Multiple sales of same product by same user: Correctly summed
 *   - Zero quantity: Contributes 0 to spending (correct)
 *   - Zero price: Contributes 0 to spending (correct)
 */

# Write your MySQL query statement below

SELECT
    s.user_id,
    SUM(s.quantity * p.price) AS spending
FROM Sales s
JOIN Product p
    ON s.product_id = p.product_id
GROUP BY s.user_id
ORDER BY spending DESC, s.user_id ASC;

