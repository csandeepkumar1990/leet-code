/*
 * LeetCode 1511: Customer Order Frequency
 * 
 * Problem:
 * Write a solution to report the customer_id and customer_name of customers
 * who have spent at least $100 in BOTH June AND July 2020.
 * 
 * Table: Customers
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | customer_id  | int     |  (Primary Key)
 * | name         | varchar |
 * | country      | varchar |
 * +--------------+---------+
 * 
 * Table: Product
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | product_id   | int     |  (Primary Key)
 * | description  | varchar |
 * | price        | int     |
 * +--------------+---------+
 * 
 * Table: Orders
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | order_id     | int     |  (Primary Key)
 * | customer_id  | int     |
 * | product_id   | int     |
 * | order_date   | date    |
 * | quantity     | int     |
 * +--------------+---------+
 * 
 * Approach: CTE with Monthly Aggregation + HAVING with Conditional SUM
 * 
 * Key Insight:
 * - Must meet threshold in BOTH months (AND condition)
 * - Calculate total_spent = price × quantity for each order
 * - Group by customer and month first, then check both months
 * 
 * Algorithm:
 * 1. CTE: Calculate monthly spending per customer (June & July 2020)
 * 2. JOIN with Customers to get names
 * 3. GROUP BY customer
 * 4. HAVING: Check spending >= 100 for BOTH months using conditional SUM
 * 
 * Example:
 * 
 *   Orders (June-July 2020):
 *   +----------+-------------+------------+------------+----------+
 *   | order_id | customer_id | product_id | order_date | quantity |
 *   +----------+-------------+------------+------------+----------+
 *   | 1        | 1           | 10         | 2020-06-10 | 1        | → $100
 *   | 2        | 1           | 20         | 2020-07-01 | 1        | → $50
 *   | 3        | 1           | 30         | 2020-07-08 | 2        | → $60
 *   | 4        | 2           | 10         | 2020-06-15 | 2        | → $200
 *   | 5        | 2           | 40         | 2020-07-01 | 10       | → $100
 *   | 6        | 3           | 20         | 2020-06-24 | 2        | → $100
 *   | 7        | 3           | 30         | 2020-06-25 | 2        | → $60
 *   +----------+-------------+------------+------------+----------+
 * 
 *   Monthly Spending:
 *   +-------------+-------+-------------+
 *   | customer_id | month | total_spent |
 *   +-------------+-------+-------------+
 *   | 1           | 6     | 100         | ✓ >= 100
 *   | 1           | 7     | 110         | ✓ >= 100  → Customer 1 qualifies!
 *   | 2           | 6     | 200         | ✓ >= 100
 *   | 2           | 7     | 100         | ✓ >= 100  → Customer 2 qualifies!
 *   | 3           | 6     | 160         | ✓ >= 100
 *   | 3           | 7     | 0 (no data) | ✗ < 100   → Customer 3 fails
 *   +-------------+-------+-------------+
 * 
 *   Result: Customers 1 and 2
 * 
 * HAVING Clause Breakdown:
 * 
 *   SUM(CASE WHEN m.month = 6 THEN m.total_spent ELSE 0 END) >= 100
 *   └─────────────────────────────────────────────────────────────┘
 *   Sums only June spending, ignores other months
 * 
 *   For Customer 1:
 *   - June:  CASE month=6 → 100, ELSE → 0  → SUM = 100 ✓
 *   - July:  CASE month=7 → 110, ELSE → 0  → SUM = 110 ✓
 *   Both >= 100 → Include!
 * 
 * Why CTE?
 *   - First aggregate Orders by (customer, month) to get monthly totals
 *   - Then check if BOTH months meet the threshold
 *   - Cleaner than doing everything in one query
 * 
 * Date Filter:
 *   WHERE order_date >= '2020-06-01' AND order_date < '2020-08-01'
 *   - Includes: June 1, 2020 to July 31, 2020
 *   - Using < '2020-08-01' is cleaner than <= '2020-07-31'
 */

WITH monthly_spent AS (
    SELECT
        o.customer_id,
        MONTH(o.order_date) AS month,
        SUM(p.price * o.quantity) AS total_spent
    FROM Orders o
    JOIN Product p
        ON o.product_id = p.product_id
    WHERE o.order_date >= '2020-06-01'
      AND o.order_date < '2020-08-01'
    GROUP BY o.customer_id, MONTH(o.order_date)
)
SELECT c.customer_id, c.name
FROM Customers c
JOIN monthly_spent m
    ON c.customer_id = m.customer_id
GROUP BY c.customer_id, c.name
HAVING SUM(CASE WHEN m.month = 6 THEN m.total_spent ELSE 0 END) >= 100
   AND SUM(CASE WHEN m.month = 7 THEN m.total_spent ELSE 0 END) >= 100;

