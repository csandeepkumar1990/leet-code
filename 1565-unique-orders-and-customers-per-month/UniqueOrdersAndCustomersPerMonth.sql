/*
 * LeetCode 1565: Unique Orders and Customers Per Month
 * 
 * Problem:
 * Write a solution to find for each month, the number of orders and the number
 * of customers with invoices > $20.
 * 
 * Return the result table sorted in any order.
 * 
 * Table: Orders
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | order_id      | int     |
 * | order_date    | date    |
 * | customer_id   | int     |
 * | invoice       | int     |
 * +---------------+---------+
 * order_id is the primary key for this table.
 * This table contains information about the orders made by customer_id.
 * 
 * Example:
 * Input:
 * Orders table:
 * +----------+------------+-------------+------------+
 * | order_id | order_date | customer_id | invoice    |
 * +----------+------------+-------------+------------+
 * | 1        | 2020-09-15 | 1           | 30         |
 * | 2        | 2020-09-17 | 2           | 90         |
 * | 3        | 2020-10-06 | 3           | 20         |
 * | 4        | 2020-10-20 | 3           | 21         |
 * | 5        | 2020-11-10 | 1           | 10         |
 * | 6        | 2020-11-21 | 2           | 15         |
 * | 7        | 2020-12-01 | 4           | 55         |
 * | 8        | 2020-12-03 | 4           | 77         |
 * | 9        | 2021-01-07 | 3           | 31         |
 * | 10       | 2021-01-15 | 2           | 28         |
 * +----------+------------+-------------+------------+
 * 
 * Output:
 * +---------+-------------+----------------+
 * | month   | order_count | customer_count |
 * +---------+-------------+----------------+
 * | 2020-09 | 2           | 2              |
 * | 2020-10 | 1           | 1              |
 * | 2020-12 | 2           | 1              |
 * | 2021-01 | 1           | 1              |
 * +---------+-------------+----------------+
 * 
 * Explanation:
 * In September 2020 we have two orders from 2 different customers with
 * invoices > $20.
 * In October 2020 we have two orders but only one order is from a customer
 * with invoice > $20 so we show only one order.
 * In November 2020 we have two orders from 2 different customers but invoices
 * <= $20, so we don't include that month.
 * In December 2020 we have two orders from 1 customer with invoices > $20 and
 * we show both orders.
 * In January 2021 we have two orders from 2 different customers with invoices
 * > $20.
 * 
 * Approach: DATE_FORMAT with GROUP BY and Aggregation
 * 
 * Key Insight:
 * - Filter orders where invoice > 20
 * - Extract year-month from order_date using DATE_FORMAT
 * - Count distinct orders per month
 * - Count distinct customers per month
 * - Group by month to aggregate
 * 
 * Algorithm:
 * 1. Filter Orders WHERE invoice > 20
 * 2. Extract month using DATE_FORMAT(order_date, '%Y-%m')
 * 3. GROUP BY month
 * 4. COUNT(DISTINCT order_id) for order_count
 * 5. COUNT(DISTINCT customer_id) for customer_count
 * 6. ORDER BY month
 * 
 * Why DATE_FORMAT?
 * 
 *   - Extracts year and month from date: '2020-09-15' → '2020-09'
 *   - Format '%Y-%m' gives YYYY-MM format
 *   - Groups orders by month for aggregation
 *   - Alternative: YEAR(order_date), MONTH(order_date) but DATE_FORMAT is cleaner
 * 
 * Why COUNT(DISTINCT order_id)?
 * 
 *   - We want unique orders per month
 *   - DISTINCT ensures we don't count duplicate order_ids
 *   - Even though order_id is primary key, using DISTINCT is explicit and safe
 * 
 * Why COUNT(DISTINCT customer_id)?
 * 
 *   - Same customer can place multiple orders in a month
 *   - We want unique customers, not total orders
 *   - DISTINCT ensures each customer is counted only once per month
 * 
 * Why WHERE invoice > 20?
 * 
 *   - Problem requires only orders with invoice > $20
 *   - Filter before grouping to reduce data processed
 *   - More efficient than filtering after aggregation
 * 
 * Visual Example:
 * 
 *   Orders (filtered invoice > 20):
 *   +----------+------------+-------------+---------+
 *   | order_id | order_date | customer_id | invoice |
 *   +----------+------------+-------------+---------+
 *   | 1        | 2020-09-15 | 1           | 30      |
 *   | 2        | 2020-09-17 | 2           | 90      |
 *   | 4        | 2020-10-20 | 3           | 21      |
 *   | 7        | 2020-12-01 | 4           | 55      |
 *   | 8        | 2020-12-03 | 4           | 77      |
 *   | 9        | 2021-01-07 | 3           | 31      |
 *   +----------+------------+-------------+---------+
 * 
 *   After DATE_FORMAT and GROUP BY:
 *   +---------+-------------+----------------+
 *   | month   | order_count | customer_count |
 *   +---------+-------------+----------------+
 *   | 2020-09 | 2           | 2              | (orders 1,2 from customers 1,2)
 *   | 2020-10 | 1           | 1              | (order 4 from customer 3)
 *   | 2020-12 | 2           | 1              | (orders 7,8 from customer 4)
 *   | 2021-01 | 1           | 1              | (order 9 from customer 3)
 *   +---------+-------------+----------------+
 * 
 * Edge Cases:
 * 
 *   - No orders with invoice > 20: Returns empty result
 *   - All orders in same month: Single row with aggregated counts
 *   - Orders spanning multiple years: Grouped by year-month
 *   - Same customer multiple orders in month: Counted once in customer_count
 *   - Empty Orders table: Returns empty result
 * 
 * Important Notes:
 * 
 *   - WHERE clause filters before GROUP BY (more efficient)
 *   - COUNT(DISTINCT) handles duplicates correctly
 *   - DATE_FORMAT creates string, so ORDER BY works alphabetically (correct for dates)
 *   - Can use ORDER BY month or ORDER BY DATE_FORMAT(order_date, '%Y-%m')
 */

# Write your MySQL query statement below

SELECT
    DATE_FORMAT(order_date, '%Y-%m') AS month,
    COUNT(DISTINCT order_id) AS order_count,
    COUNT(DISTINCT customer_id) AS customer_count
FROM Orders
WHERE invoice > 20
GROUP BY month
ORDER BY month;

