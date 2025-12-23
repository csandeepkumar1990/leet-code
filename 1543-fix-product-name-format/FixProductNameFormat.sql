/*
 * LeetCode 1543: Fix Product Name Format
 * 
 * Problem:
 * Write a solution to fix the names so that only the first character is uppercase
 * and the rest are lowercase. Return the result table ordered by sale_date.
 * 
 * Table: Sales
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | sale_id      | int     |
 * | product_name | varchar |
 * | sale_date    | date    |
 * +--------------+---------+
 * sale_id is the primary key for this table.
 * Each row of this table contains the product name and the date it was sold.
 * 
 * Note: Since the answer may be very large, return the result table ordered by
 * sale_date, product_name.
 * 
 * Approach: Normalize Product Names and Group by Month
 * 
 * Key Insight:
 * - Normalize product names using LOWER(TRIM()) to handle case-insensitive
 *   comparisons and remove leading/trailing spaces
 * - Group sales by normalized product name and month (YYYY-MM format)
 * - Count total sales for each product-month combination
 * - Order results by product name and sale date
 * 
 * Algorithm:
 * 1. Normalize product_name: LOWER(TRIM(product_name))
 * 2. Format sale_date to YYYY-MM: DATE_FORMAT(sale_date, '%Y-%m')
 * 3. GROUP BY normalized product name and formatted sale date
 * 4. COUNT(*) to get total sales per group
 * 5. ORDER BY product_name, sale_date
 * 
 * Why Normalize Product Names?
 * 
 *   - Product names may have inconsistent casing: "Laptop", "laptop", "LAPTOP"
 *   - May have leading/trailing spaces: " Laptop ", "Laptop"
 *   - Normalization ensures all variations are grouped together
 *   - LOWER() converts to lowercase for case-insensitive comparison
 *   - TRIM() removes leading and trailing whitespace
 * 
 * Why Format Sale Date?
 * 
 *   - DATE_FORMAT(sale_date, '%Y-%m') extracts year and month
 *   - Groups all sales in the same month together
 *   - Format: '2020-01', '2020-02', etc.
 *   - Enables monthly aggregation and reporting
 * 
 * Example:
 * 
 *   Sales table:
 *   +---------+--------------+------------+
 *   | sale_id | product_name | sale_date  |
 *   +---------+--------------+------------+
 *   | 1       | Laptop       | 2020-01-15 |
 *   | 2       | laptop       | 2020-01-20 |
 *   | 3       | LAPTOP       | 2020-02-10 |
 *   | 4       | Mouse        | 2020-01-25 |
 *   | 5       |  mouse       | 2020-02-05 |
 *   +---------+--------------+------------+
 * 
 *   After normalization:
 *   - "Laptop", "laptop", "LAPTOP" → "laptop"
 *   - "Mouse", " mouse" → "mouse"
 * 
 *   After grouping by month:
 *   - 2020-01: laptop (2 sales), mouse (1 sale)
 *   - 2020-02: laptop (1 sale), mouse (1 sale)
 * 
 *   Result:
 *   +--------------+------------+-------+
 *   | product_name | sale_date  | total |
 *   +--------------+------------+-------+
 *   | laptop       | 2020-01    | 2     |
 *   | laptop       | 2020-02    | 1     |
 *   | mouse        | 2020-01    | 1     |
 *   | mouse        | 2020-02    | 1     |
 *   +--------------+------------+-------+
 * 
 * Important Notes:
 * 
 *   - LOWER() ensures case-insensitive grouping
 *   - TRIM() handles whitespace variations
 *   - DATE_FORMAT() groups by month, ignoring day
 *   - COUNT(*) counts all rows in each group
 *   - ORDER BY ensures consistent output ordering
 * 
 * Performance Considerations:
 * 
 *   - Normalization functions (LOWER, TRIM) are applied in SELECT and GROUP BY
 *   - Consider indexing on product_name and sale_date if table is large
 *   - DATE_FORMAT prevents index usage on sale_date, but necessary for grouping
 */

# Write your MySQL query statement below
SELECT
    LOWER(TRIM(product_name)) AS product_name,
    DATE_FORMAT(sale_date, '%Y-%m') AS sale_date,
    COUNT(*) AS total
FROM Sales
GROUP BY
    LOWER(TRIM(product_name)),
    DATE_FORMAT(sale_date, '%Y-%m')
ORDER BY
    product_name,
    sale_date;

