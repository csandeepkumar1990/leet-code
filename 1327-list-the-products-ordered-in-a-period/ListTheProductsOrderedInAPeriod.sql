/*
 * LeetCode 1327: List the Products Ordered in a Period
 * 
 * Problem:
 * Write a solution to get the names of products that have at least 100 units
 * ordered in February 2020 and their total amount ordered.
 * 
 * Table: Products
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | product_id    | int     |  (Primary Key)
 * | product_name  | varchar |
 * | product_category| varchar|
 * +---------------+---------+
 * 
 * Table: Orders
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | product_id    | int     |  (Foreign Key)
 * | order_date    | date    |
 * | unit          | int     |
 * +---------------+---------+
 * No primary key. May have duplicates.
 * 
 * Approach: JOIN + Filter + GROUP BY + HAVING
 * 
 * Key Insight:
 * - Filter orders to February 2020 only
 * - Sum units per product
 * - Use HAVING to filter products with >= 100 units
 * 
 * Algorithm:
 * 1. JOIN Orders with Products on product_id
 * 2. WHERE: Filter to February 2020
 * 3. GROUP BY: Aggregate by product
 * 4. HAVING: Keep only products with SUM(unit) >= 100
 * 
 * Example:
 * 
 *   Products:
 *   +------------+--------------+------------------+
 *   | product_id | product_name | product_category |
 *   +------------+--------------+------------------+
 *   | 1          | Leetcode Sol | Book             |
 *   | 2          | Jewels Sto   | Fashion          |
 *   | 3          | HP Laptop    | Electronics      |
 *   +------------+--------------+------------------+
 * 
 *   Orders:
 *   +------------+------------+------+
 *   | product_id | order_date | unit |
 *   +------------+------------+------+
 *   | 1          | 2020-02-05 | 60   |  ← Feb 2020
 *   | 1          | 2020-02-10 | 70   |  ← Feb 2020
 *   | 2          | 2020-01-18 | 30   |  ← Jan (excluded)
 *   | 2          | 2020-02-11 | 80   |  ← Feb 2020
 *   | 3          | 2020-02-17 | 2    |  ← Feb 2020
 *   | 3          | 2020-02-24 | 3    |  ← Feb 2020
 *   +------------+------------+------+
 * 
 *   Step 1 - Filter February 2020:
 *   +------------+------+
 *   | product_id | unit |
 *   +------------+------+
 *   | 1          | 60   |
 *   | 1          | 70   |
 *   | 2          | 80   |
 *   | 3          | 2    |
 *   | 3          | 3    |
 *   +------------+------+
 * 
 *   Step 2 - GROUP BY & SUM:
 *   +------------+--------------+------------+
 *   | product_id | product_name | total_unit |
 *   +------------+--------------+------------+
 *   | 1          | Leetcode Sol | 130        |  ← >= 100 ✓
 *   | 2          | Jewels Sto   | 80         |  ← < 100 ✗
 *   | 3          | HP Laptop    | 5          |  ← < 100 ✗
 *   +------------+--------------+------------+
 * 
 *   Step 3 - HAVING SUM >= 100:
 *   +--------------+------+
 *   | product_name | unit |
 *   +--------------+------+
 *   | Leetcode Sol | 130  |
 *   +--------------+------+
 * 
 * Date Filter Options:
 * 
 *   Option 1 (used here):
 *   WHERE order_date >= '2020-02-01' AND order_date < '2020-03-01'
 * 
 *   Option 2:
 *   WHERE YEAR(order_date) = 2020 AND MONTH(order_date) = 2
 * 
 *   Option 3:
 *   WHERE order_date BETWEEN '2020-02-01' AND '2020-02-29'
 * 
 *   Option 1 is preferred: handles leap years, uses indexes better
 * 
 * WHERE vs HAVING:
 * 
 *   WHERE:  Filters BEFORE grouping (individual rows)
 *   HAVING: Filters AFTER grouping (aggregated results)
 * 
 *   - WHERE order_date: Filter rows to Feb 2020
 *   - HAVING SUM(unit): Filter groups to >= 100 total
 * 
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 * 
 * | Concept  | Purpose                                    |
 * |----------|--------------------------------------------|
 * | JOIN     | Get product names from Products table      |
 * | WHERE    | Filter to February 2020 orders only        |
 * | GROUP BY | Aggregate units per product                |
 * | HAVING   | Keep products with >= 100 total units      |
 * | SUM()    | Calculate total units ordered              |
 */

SELECT
    p.product_name,
    SUM(o.unit) AS unit
FROM Orders o
JOIN Products p
    ON o.product_id = p.product_id
WHERE o.order_date >= '2020-02-01'
  AND o.order_date < '2020-03-01'
GROUP BY p.product_id, p.product_name
HAVING SUM(o.unit) >= 100;

