/*
 * LeetCode 1777: Product's Price for Each Store
 *
 * Problem:
 * Write a solution to find the price of each product in each store.
 *
 * Return the result table in any order.
 *
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | store       | enum    |
 * | price       | int     |
 * +-------------+---------+
 * (product_id, store) is the primary key for this table.
 * store is an ENUM of type ('store1', 'store2', 'store3') where each
 * represents the store this product is available in.
 * price is the price of the product in this store.
 *
 * Approach: Pivot Using CASE with MAX Aggregate
 *
 * Key Insight:
 * - Need to transform rows (one per store) into columns (one column per store)
 * - This is a pivot operation: rows → columns
 * - Use CASE statements to conditionally extract prices for each store
 * - Use MAX aggregate function to handle the GROUP BY requirement
 *
 * Algorithm:
 * 1. GROUP BY product_id to aggregate all stores for each product
 * 2. For each store column, use CASE to extract price when store matches
 * 3. Use MAX to aggregate the CASE result (handles NULLs and single values)
 * 4. Result: one row per product with columns for each store's price
 *
 * Why Use MAX() Here?
 *
 *   The MAX() function is used for a specific reason in pivot queries:
 *
 *   1. **SQL Requirement**: When using GROUP BY, all non-grouped columns in
 *      SELECT must be aggregate functions. Since we're grouping by product_id,
 *      we need an aggregate function for the CASE expression.
 *
 *   2. **Handling NULLs**: 
 *      - CASE WHEN store = 'store1' THEN price END returns:
 *        * The price value when store matches 'store1'
 *        * NULL when store doesn't match 'store1'
 *      - MAX() ignores NULL values and returns the non-NULL value
 *      - Since each product has at most one price per store, MAX returns
 *        that single price value
 *
 *   3. **Single Value Extraction**:
 *      - For each product_id and store combination, there's exactly one price
 *      - MAX(price) = price when there's only one value
 *      - MAX(NULL, price) = price (MAX ignores NULL)
 *
 *   4. **Alternative Functions**:
 *      - MIN() would work the same (since there's only one value)
 *      - SUM() would work but is semantically incorrect
 *      - MAX() is conventional for this pivot pattern
 *
 * Example:
 *
 *   Products (input - rows):
 *   +------------+--------+-------+
 *   | product_id | store  | price |
 *   +------------+--------+-------+
 *   | 0          | store1 | 95    |
 *   | 0          | store3 | 105   |
 *   | 0          | store2 | 100   |
 *   | 1          | store1 | 70    |
 *   | 1          | store3 | 80    |
 *   +------------+--------+-------+
 *
 *   Step-by-step for product_id = 0:
 *
 *   Row 1: store='store1', price=95
 *     - CASE WHEN store='store1' THEN 95 END = 95
 *     - CASE WHEN store='store2' THEN price END = NULL
 *     - CASE WHEN store='store3' THEN price END = NULL
 *
 *   Row 2: store='store3', price=105
 *     - CASE WHEN store='store1' THEN price END = NULL
 *     - CASE WHEN store='store2' THEN price END = NULL
 *     - CASE WHEN store='store3' THEN 105 END = 105
 *
 *   Row 3: store='store2', price=100
 *     - CASE WHEN store='store1' THEN price END = NULL
 *     - CASE WHEN store='store2' THEN 100 END = 100
 *     - CASE WHEN store='store3' THEN price END = NULL
 *
 *   After GROUP BY product_id and MAX():
 *     - MAX(95, NULL, NULL) = 95 → store1
 *     - MAX(NULL, NULL, 100) = 100 → store2
 *     - MAX(NULL, 105, NULL) = 105 → store3
 *
 *   Result (output - columns):
 *   +------------+--------+--------+--------+
 *   | product_id | store1 | store2 | store3 |
 *   +------------+--------+--------+--------+
 *   | 0          | 95     | 100    | 105    |
 *   | 1          | 70     | NULL   | 80     |
 *   +------------+--------+--------+--------+
 *
 * Visual Transformation:
 *
 *   BEFORE (Normalized - Rows):
 *   product_id | store  | price
 *   -----------|--------|-------
 *   0          | store1 | 95
 *   0          | store2 | 100
 *   0          | store3 | 105
 *
 *   AFTER (Pivoted - Columns):
 *   product_id | store1 | store2 | store3
 *   -----------|--------|--------|--------
 *   0          | 95     | 100    | 105
 *
 * Important Notes:
 * - MAX() is used because SQL requires aggregate functions with GROUP BY
 * - MAX() effectively extracts the single non-NULL value from each CASE
 * - If a product doesn't exist in a store, that column will be NULL
 * - This is a standard SQL pivot pattern using conditional aggregation
 */

# Write your MySQL query statement below

SELECT
    product_id,
    MAX(CASE WHEN store = 'store1' THEN price END) AS store1,
    MAX(CASE WHEN store = 'store2' THEN price END) AS store2,
    MAX(CASE WHEN store = 'store3' THEN price END) AS store3
FROM Products
GROUP BY product_id;

