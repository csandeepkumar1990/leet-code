/*
 * LeetCode 1069: Product Sales Analysis II
 * 
 * Problem:
 * Write a solution to report the total quantity sold for every product.
 * Return the result in any order.
 * 
 * Table: Sales
 * +-------------+-------+
 * | Column Name | Type  |
 * +-------------+-------+
 * | sale_id     | int   |
 * | product_id  | int   |
 * | year        | int   |
 * | quantity    | int   |
 * | price       | int   |
 * +-------------+-------+
 * (sale_id, year) is the primary key.
 * product_id is a foreign key to Product table.
 * 
 * Table: Product
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | product_id   | int     |  (Primary Key)
 * | product_name | varchar |
 * +--------------+---------+
 * 
 * Approach: Simple GROUP BY with SUM
 * 
 * Key Insight:
 * - Group all sales by product_id
 * - Sum up quantities for each product
 * - No need to join with Product table (only need product_id)
 * 
 * Algorithm:
 * 1. GROUP BY product_id
 * 2. SUM(quantity) for each group
 * 
 * Time Complexity: O(n) where n = number of sales records
 * 
 * Example:
 * 
 *   Sales:
 *   +---------+------------+------+----------+-------+
 *   | sale_id | product_id | year | quantity | price |
 *   +---------+------------+------+----------+-------+
 *   | 1       | 100        | 2008 | 10       | 5000  |
 *   | 2       | 100        | 2009 | 12       | 5000  |
 *   | 7       | 200        | 2011 | 15       | 9000  |
 *   | 3       | 100        | 2010 | 8        | 5500  |
 *   | 4       | 200        | 2012 | 20       | 9500  |
 *   +---------+------------+------+----------+-------+
 * 
 *   GROUP BY product_id:
 * 
 *   Product 100:
 *     Sales: (10, 12, 8)
 *     SUM = 10 + 12 + 8 = 30
 * 
 *   Product 200:
 *     Sales: (15, 20)
 *     SUM = 15 + 20 = 35
 * 
 *   Result:
 *   +------------+----------------+
 *   | product_id | total_quantity |
 *   +------------+----------------+
 *   | 100        | 30             |
 *   | 200        | 35             |
 *   +------------+----------------+
 * 
 * Visual:
 * 
 *   Product 100 ──┬── 2008: qty 10
 *                 ├── 2009: qty 12  → Total: 30
 *                 └── 2010: qty 8
 * 
 *   Product 200 ──┬── 2011: qty 15
 *                 └── 2012: qty 20  → Total: 35
 * 
 * Note: This is one of the simplest SQL aggregation problems.
 *       Just GROUP BY and SUM - no joins, no filters needed!
 */

SELECT 
    product_id, 
    SUM(quantity) AS total_quantity
FROM Sales
GROUP BY product_id;

