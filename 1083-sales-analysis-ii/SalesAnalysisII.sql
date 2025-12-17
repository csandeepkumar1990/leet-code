/*
 * LeetCode 1083: Sales Analysis II
 * 
 * Problem:
 * Find all buyers who bought S8 but did not buy iPhone.
 * 
 * Table: Product
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | product_name| varchar |
 * | unit_price  | int     |
 * +-------------+---------+
 * product_id is the primary key.
 * 
 * Table: Sales
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | seller_id   | int     |
 * | product_id  | int     |
 * | buyer_id    | int     |
 * | sale_date   | date    |
 * | quantity    | int     |
 * | price       | int     |
 * +-------------+---------+
 * This table can have duplicate rows.
 * product_id is a foreign key to the Product table.
 * 
 * Example:
 * Input:
 * Product table:
 * +------------+--------------+------------+
 * | product_id | product_name | unit_price |
 * +------------+--------------+------------+
 * | 1          | S8           | 1000       |
 * | 2          | G4           | 800        |
 * | 3          | iPhone       | 1400       |
 * +------------+--------------+------------+
 * 
 * Sales table:
 * +-----------+------------+----------+------------+----------+-------+
 * | seller_id | product_id | buyer_id | sale_date  | quantity | price |
 * +-----------+------------+----------+------------+----------+-------+
 * | 1         | 1          | 1        | 2019-01-21 | 2        | 2000  |
 * | 1         | 2          | 2        | 2019-02-17 | 1        | 800   |
 * | 2         | 1          | 3        | 2019-06-02 | 1        | 800   |
 * | 3         | 3          | 3        | 2019-05-13 | 2        | 2800  |
 * +-----------+------------+----------+------------+----------+-------+
 * 
 * Output:
 * +-------------+
 * | buyer_id    |
 * +-------------+
 * | 1           |
 * +-------------+
 * 
 * Explanation:
 * - Buyer 1: bought S8 ✓, never bought iPhone ✓ → included ✅
 * - Buyer 2: bought G4 only, never bought S8 → excluded ❌
 * - Buyer 3: bought S8 ✓, but also bought iPhone → excluded ❌
 * 
 * Approach:
 * - Main query: Find all buyers who bought 'S8' (JOIN Sales with Product)
 * - Subquery: Find all buyers who bought 'iPhone'
 * - NOT IN: Exclude buyers from main query who appear in subquery
 * - DISTINCT: Ensure each buyer appears only once
 */

# Write your MySQL query statement below

SELECT DISTINCT s1.buyer_id
FROM Sales s1
JOIN Product p1
  ON s1.product_id = p1.product_id
WHERE p1.product_name = 'S8'
AND s1.buyer_id NOT IN (
    SELECT s2.buyer_id
    FROM Sales s2
    JOIN Product p2
      ON s2.product_id = p2.product_id
    WHERE p2.product_name = 'iPhone'
)
ORDER BY s1.buyer_id;

