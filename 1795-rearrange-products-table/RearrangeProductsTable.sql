/*
 * LeetCode 1795: Rearrange Products Table
 *
 * Problem:
 * Write a solution to rearrange the Products table so that each row has
 * (product_id, store, price). If a product is not available in a store, do not
 * include a row with that product_id and store combination in the result table.
 *
 * Return the result table in any order.
 *
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | store1      | int     |
 * | store2      | int     |
 * | store3      | int     |
 * +-------------+---------+
 * product_id is the primary key for this table.
 * Each row in this table indicates the product's price in 3 different stores:
 * store1, store2, and store3.
 * If the product is not available in a store, the price will be null in that
 * store's column.
 *
 * Approach: Unpivot Using UNION ALL
 *
 * Key Insight:
 * - Need to transform columns (store1, store2, store3) into rows
 * - This is an unpivot operation: columns → rows (opposite of pivot)
 * - Use UNION ALL to combine three separate SELECT statements
 * - Each SELECT handles one store column
 * - Filter out NULL values to exclude unavailable products
 *
 * Algorithm:
 * 1. For each store column (store1, store2, store3):
 *    a. SELECT product_id, store name, and price
 *    b. WHERE price IS NOT NULL (exclude unavailable products)
 * 2. UNION ALL to combine all three result sets
 * 3. Result: one row per product-store combination
 *
 * Why UNION ALL (not UNION)?
 * - UNION removes duplicates, UNION ALL keeps all rows
 * - Since each SELECT targets different stores, there are no duplicates
 * - UNION ALL is more efficient (no duplicate checking)
 *
 * Visual Transformation:
 *
 *   BEFORE (Wide Format - Columns):
 *   +------------+--------+--------+--------+
 *   | product_id | store1 | store2 | store3 |
 *   +------------+--------+--------+--------+
 *   | 0          | 95     | 100    | 105    |
 *   | 1          | 70     | NULL   | 80     |
 *   +------------+--------+--------+--------+
 *
 *   AFTER (Long Format - Rows):
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store1 | 95    |
 *   | 0          | store2 | 100    |
 *   | 0          | store3 | 105    |
 *   | 1          | store1 | 70     |
 *   | 1          | store3 | 80     |
 *   +------------+--------+-------+
 *
 * Step-by-Step Breakdown:
 *
 *   Query 1: SELECT product_id, 'store1' AS store, store1 AS price
 *            FROM Products WHERE store1 IS NOT NULL
 *   Result:
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store1 | 95    |
 *   | 1          | store1 | 70    |
 *   +------------+--------+-------+
 *
 *   Query 2: SELECT product_id, 'store2' AS store, store2 AS price
 *            FROM Products WHERE store2 IS NOT NULL
 *   Result:
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store2 | 100   |
 *   +------------+--------+-------+
 *
 *   Query 3: SELECT product_id, 'store3' AS store, store3 AS price
 *            FROM Products WHERE store3 IS NOT NULL
 *   Result:
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store3 | 105   |
 *   | 1          | store3 | 80    |
 *   +------------+--------+-------+
 *
 *   UNION ALL combines all three:
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store1 | 95    |
 *   | 1          | store1 | 70    |
 *   | 0          | store2 | 100   |
 *   | 0          | store3 | 105   |
 *   | 1          | store3 | 80    |
 *   +------------+--------+-------+
 *
 * Important Notes:
 * - This is the reverse operation of problem 1777 (pivot → unpivot)
 * - WHERE price IS NOT NULL ensures we only include available products
 * - UNION ALL is preferred over UNION for better performance
 * - Each SELECT statement has the same column structure (required for UNION)
 * - The store name is hardcoded as a string literal in each SELECT
 */

# Write your MySQL query statement below

SELECT product_id, 'store1' AS store, store1 AS price
FROM Products
WHERE store1 IS NOT NULL

UNION ALL

SELECT product_id, 'store2' AS store, store2 AS price
FROM Products
WHERE store2 IS NOT NULL

UNION ALL

SELECT product_id, 'store3' AS store, store3 AS price
FROM Products
WHERE store3 IS NOT NULL;

