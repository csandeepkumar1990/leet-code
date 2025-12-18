/*
 * LeetCode 1082: Sales Analysis I
 * 
 * Problem:
 * Write a solution to report the best seller(s) who sold the highest total
 * price of products. If there is a tie, report all of them.
 * 
 * Table: Sales
 * +-------------+-------+
 * | Column Name | Type  |
 * +-------------+-------+
 * | sale_id     | int   |
 * | product_id  | int   |
 * | year        | int   |
 * | quantity    | int   |
 * | price       | int   |  (price per sale, not per unit)
 * | seller_id   | int   |
 * +-------------+-------+
 * 
 * Approach: GROUP BY with Subquery for Maximum
 * 
 * Key Insight:
 * - Group sales by seller_id and sum their prices
 * - Find the maximum total across all sellers
 * - Return all sellers whose total equals the maximum (handles ties)
 * 
 * Algorithm:
 * 1. Outer query: Group by seller_id, sum prices
 * 2. HAVING clause: Filter to those matching the max total
 * 3. Subquery: Find the highest SUM(price) among all sellers
 * 
 * Why HAVING instead of WHERE?
 * - WHERE filters rows BEFORE aggregation
 * - HAVING filters groups AFTER aggregation
 * - We need to compare SUM(price), so we must use HAVING
 * 
 * Example:
 * 
 *   Sales table:
 *   +---------+------------+------+----------+-------+-----------+
 *   | sale_id | product_id | year | quantity | price | seller_id |
 *   +---------+------------+------+----------+-------+-----------+
 *   | 1       | 100        | 2019 | 10       | 1000  | 1         |
 *   | 2       | 100        | 2020 | 5        | 500   | 1         |
 *   | 3       | 200        | 2019 | 8        | 800   | 2         |
 *   | 4       | 200        | 2020 | 10       | 1000  | 2         |
 *   | 5       | 300        | 2019 | 15       | 1500  | 3         |
 *   +---------+------------+------+----------+-------+-----------+
 * 
 *   Step 1 - Group and Sum:
 *   +------------+-------------+
 *   | seller_id  | total_price |
 *   +------------+-------------+
 *   | 1          | 1500        |
 *   | 2          | 1800        |  ← Max
 *   | 3          | 1500        |
 *   +------------+-------------+
 * 
 *   Step 2 - Subquery finds max: 1800
 * 
 *   Step 3 - HAVING filters: seller_id = 2
 * 
 *   Result:
 *   +-----------+
 *   | seller_id |
 *   +-----------+
 *   | 2         |
 *   +-----------+
 * 
 * Handling Ties:
 *   If sellers 1 and 2 both had total 1800, both would be returned
 *   because HAVING SUM(price) = 1800 matches multiple groups.
 */

SELECT seller_id
FROM Sales
GROUP BY seller_id
HAVING SUM(price) = (
    SELECT SUM(price)
    FROM Sales
    GROUP BY seller_id
    ORDER BY SUM(price) DESC
    LIMIT 1 -- Gets the single highest total price
);

