/*
 * LeetCode 1251: Average Selling Price
 * 
 * Problem:
 * Write a solution to find the average selling price for each product.
 * average_price should be rounded to 2 decimal places.
 * If a product has no sales, its average price is assumed to be 0.
 * 
 * Table: Prices
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | product_id    | int     |
 * | start_date    | date    |
 * | end_date      | date    |
 * | price         | int     |
 * +---------------+---------+
 * (product_id, start_date) is primary key.
 * Each row indicates the price of product_id during [start_date, end_date].
 * 
 * Table: UnitsSold
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | product_id    | int     |
 * | purchase_date | date    |
 * | units         | int     |
 * +---------------+---------+
 * No primary key. Each row indicates units sold on purchase_date.
 * 
 * Approach: LEFT JOIN with Date Range Matching
 * 
 * Key Insight:
 * - Price varies by date range, so we must match purchase_date to the correct price period
 * - Use BETWEEN to join sales to the applicable price window
 * - LEFT JOIN ensures products with no sales still appear (with 0 average)
 * - Weighted average: total_revenue / total_units
 * 
 * Formula:
 *   average_price = SUM(units × price) / SUM(units)
 * 
 * Algorithm:
 * 1. Get all distinct product_ids from Prices
 * 2. LEFT JOIN with UnitsSold on product_id
 * 3. LEFT JOIN with Prices matching product_id AND date range
 * 4. Calculate weighted average, use COALESCE for products with no sales
 * 5. Round to 2 decimal places
 * 
 * Example:
 * 
 *   Prices:
 *   +------------+------------+------------+-------+
 *   | product_id | start_date | end_date   | price |
 *   +------------+------------+------------+-------+
 *   | 1          | 2019-02-17 | 2019-02-28 | 5     |
 *   | 1          | 2019-03-01 | 2019-03-22 | 20    |
 *   | 2          | 2019-02-01 | 2019-02-20 | 15    |
 *   | 2          | 2019-02-21 | 2019-03-31 | 30    |
 *   +------------+------------+------------+-------+
 * 
 *   UnitsSold:
 *   +------------+---------------+-------+
 *   | product_id | purchase_date | units |
 *   +------------+---------------+-------+
 *   | 1          | 2019-02-25    | 100   |  → price = 5
 *   | 1          | 2019-03-01    | 15    |  → price = 20
 *   | 2          | 2019-02-10    | 200   |  → price = 15
 *   | 2          | 2019-03-22    | 30    |  → price = 30
 *   +------------+---------------+-------+
 * 
 *   Calculation:
 *   Product 1: (100×5 + 15×20) / (100+15) = (500+300)/115 = 800/115 = 6.96
 *   Product 2: (200×15 + 30×30) / (200+30) = (3000+900)/230 = 3900/230 = 16.96
 * 
 *   Result:
 *   +------------+---------------+
 *   | product_id | average_price |
 *   +------------+---------------+
 *   | 1          | 6.96          |
 *   | 2          | 16.96         |
 *   +------------+---------------+
 * 
 * Why LEFT JOIN from Prices?
 *   - Products in Prices may have no sales in UnitsSold
 *   - LEFT JOIN keeps these products, COALESCE gives them 0 average
 * 
 * Why BETWEEN for date matching?
 *   - purchase_date must fall within [start_date, end_date]
 *   - This finds the correct price that was active on the purchase date
 */

SELECT
    p.product_id,
    ROUND(
        COALESCE(SUM(u.units * pr.price) * 1.0 / SUM(u.units), 0), 
        2
    ) AS average_price
FROM
    (SELECT DISTINCT product_id FROM Prices) p
LEFT JOIN UnitsSold u
    ON p.product_id = u.product_id
LEFT JOIN Prices pr
    ON u.product_id = pr.product_id
    AND u.purchase_date BETWEEN pr.start_date AND pr.end_date
GROUP BY
    p.product_id;

