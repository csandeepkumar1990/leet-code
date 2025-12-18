/*
 * LeetCode 1084: Sales Analysis III
 * 
 * Problem:
 * Write a solution to report the products that were ONLY sold in the first
 * quarter of 2019 (between 2019-01-01 and 2019-03-31 inclusive).
 * 
 * Table: Product
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | product_id   | int     |  (Primary Key)
 * | product_name | varchar |
 * | unit_price   | int     |
 * +--------------+---------+
 * 
 * Table: Sales
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | seller_id   | int     |
 * | product_id  | int     |  (Foreign Key)
 * | buyer_id    | int     |
 * | sale_date   | date    |
 * | quantity    | int     |
 * | price       | int     |
 * +-------------+---------+
 * 
 * Key Insight:
 * - "ONLY sold in Q1 2019" means ALL sales must be within that period
 * - If ANY sale is outside Q1 2019, exclude that product
 * - Use MIN/MAX to check the range of all sale dates for each product
 * 
 * Approach: GROUP BY + HAVING with MIN/MAX
 * 
 * Logic:
 * - MIN(sale_date) >= '2019-01-01' → First sale is on or after Jan 1
 * - MAX(sale_date) <= '2019-03-31' → Last sale is on or before Mar 31
 * - If both conditions true → ALL sales are within Q1 2019
 * 
 * Example:
 * 
 *   Sales:
 *   +------------+---------+------------+
 *   | product_id | sale_id | sale_date  |
 *   +------------+---------+------------+
 *   | 1          | 1       | 2019-01-21 |  ✓ in Q1
 *   | 1          | 2       | 2019-02-17 |  ✓ in Q1
 *   | 2          | 3       | 2019-02-02 |  ✓ in Q1
 *   | 2          | 4       | 2019-05-13 |  ✗ outside Q1!
 *   | 3          | 5       | 2019-03-05 |  ✓ in Q1
 *   +------------+---------+------------+
 * 
 *   Analysis per product:
 *   
 *   Product 1:
 *     MIN(sale_date) = 2019-01-21 >= 2019-01-01 ✓
 *     MAX(sale_date) = 2019-02-17 <= 2019-03-31 ✓
 *     → Include ✓
 *   
 *   Product 2:
 *     MIN(sale_date) = 2019-02-02 >= 2019-01-01 ✓
 *     MAX(sale_date) = 2019-05-13 <= 2019-03-31 ✗
 *     → Exclude (sold outside Q1)
 *   
 *   Product 3:
 *     MIN(sale_date) = 2019-03-05 >= 2019-01-01 ✓
 *     MAX(sale_date) = 2019-03-05 <= 2019-03-31 ✓
 *     → Include ✓
 * 
 *   Result: Products 1 and 3
 * 
 * Why MIN/MAX Works:
 * 
 *   Product's sale dates: [Jan 21, Feb 17, May 13]
 *                          ↑              ↑
 *                         MIN            MAX
 * 
 *   If MIN is in range AND MAX is in range,
 *   then ALL dates between them are in range too!
 * 
 *   Timeline:
 *   ──────|═══════════════════════|──────────────
 *      Jan 1                   Mar 31
 *         └── Q1 2019 ──────────┘
 * 
 *   Product 1: [Jan 21 ──── Feb 17]  ← fits inside Q1 ✓
 *   Product 2: [Feb 02 ────────────── May 13]  ← extends past Q1 ✗
 */

SELECT p.product_id, p.product_name
FROM Product p
JOIN Sales s ON p.product_id = s.product_id
GROUP BY p.product_id, p.product_name
HAVING 
    MIN(s.sale_date) >= '2019-01-01'
    AND MAX(s.sale_date) <= '2019-03-31';

