/*
 * LeetCode 1571: Warehouse Manager
 * 
 * Problem:
 * Write a solution to report the total volume occupied by each warehouse.
 * 
 * The total volume is the sum of (Width * Length * Height * units) for all products
 * stored in that warehouse.
 * 
 * Return the result table in any order.
 * 
 * Table: Warehouse
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | name             | varchar |
 * | product_id       | int     |
 * | units             | int     |
 * +------------------+---------+
 * (name, product_id) is the primary key for this table.
 * Each row contains information about the units of a product stored in a warehouse.
 * 
 * Table: Products
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | product_id       | int     |
 * | product_name     | varchar |
 * | Width            | int     |
 * | Length           | int     |
 * | Height           | int     |
 * +------------------+---------+
 * product_id is the primary key for this table.
 * Each row contains information about the dimensions of a product.
 * 
 * Approach: JOIN + GROUP BY + SUM with Volume Calculation
 * 
 * Key Insight:
 * - Join Warehouse with Products to get product dimensions
 * - Calculate volume per product: Width * Length * Height * units
 * - Group by warehouse name to aggregate volumes
 * - Sum all volumes per warehouse
 * 
 * Algorithm:
 * 1. JOIN Warehouse with Products on product_id
 * 2. Calculate volume for each row: Width * Length * Height * units
 * 3. GROUP BY warehouse name
 * 4. SUM all volumes per warehouse
 * 
 * Formula:
 *   volume_per_product = Width × Length × Height × units
 *   total_volume = SUM(volume_per_product) for each warehouse
 * 
 * Time Complexity: O(n), where n is the number of rows in Warehouse table.
 *   - JOIN: O(n) to match products
 *   - GROUP BY: O(n) to aggregate
 *   - SUM: O(n) to calculate totals
 * 
 * Space Complexity: O(k), where k is the number of warehouses.
 *   - Result set contains one row per warehouse
 * 
 * Example:
 * 
 *   Warehouse:
 *   +------------+------------+-------+
 *   | name       | product_id | units |
 *   +------------+------------+-------+
 *   | LCHouse1   | 1          | 1     |
 *   | LCHouse1   | 2          | 10    |
 *   | LCHouse1   | 3          | 5     |
 *   | LCHouse2   | 1          | 2     |
 *   | LCHouse2   | 2          | 2     |
 *   | LCHouse3   | 4          | 1     |
 *   +------------+------------+-------+
 * 
 *   Products:
 *   +------------+--------------+-------+--------+--------+
 *   | product_id | product_name | Width | Length | Height |
 *   +------------+--------------+-------+--------+--------+
 *   | 1          | LC-TV        | 5     | 50     | 40     |
 *   | 2          | LC-KeyChain  | 5     | 5      | 5      |
 *   | 3          | LC-Phone     | 2     | 10     | 10     |
 *   | 4          | LC-T-Shirt   | 4     | 10     | 20     |
 *   +------------+--------------+-------+--------+--------+
 * 
 *   Step 1 - JOIN Warehouse with Products:
 *   +------------+------------+-------+-------+--------+--------+
 *   | name       | product_id | units | Width | Length | Height |
 *   +------------+------------+-------+-------+--------+--------+
 *   | LCHouse1   | 1          | 1     | 5     | 50     | 40     |
 *   | LCHouse1   | 2          | 10    | 5     | 5      | 5      |
 *   | LCHouse1   | 3          | 5     | 2     | 10     | 10     |
 *   | LCHouse2   | 1          | 2     | 5     | 50     | 40     |
 *   | LCHouse2   | 2          | 2     | 5     | 5      | 5      |
 *   | LCHouse3   | 4          | 1     | 4     | 10     | 20     |
 *   +------------+------------+-------+-------+--------+--------+
 * 
 *   Step 2 - Calculate volume per row:
 *   - LCHouse1, product 1: 5 × 50 × 40 × 1 = 10,000
 *   - LCHouse1, product 2: 5 × 5 × 5 × 10 = 1,250
 *   - LCHouse1, product 3: 2 × 10 × 10 × 5 = 1,000
 *   - LCHouse2, product 1: 5 × 50 × 40 × 2 = 20,000
 *   - LCHouse2, product 2: 5 × 5 × 5 × 2 = 250
 *   - LCHouse3, product 4: 4 × 10 × 20 × 1 = 800
 * 
 *   Step 3 - GROUP BY and SUM:
 *   - LCHouse1: 10,000 + 1,250 + 1,000 = 12,250
 *   - LCHouse2: 20,000 + 250 = 20,250
 *   - LCHouse3: 800
 * 
 *   Result:
 *   +------------+--------+
 *   | warehouse_name | volume |
 *   +------------+--------+
 *   | LCHouse1   | 12250  |
 *   | LCHouse2   | 20250  |
 *   | LCHouse3   | 800    |
 *   +------------+--------+
 * 
 * Why JOIN?
 * 
 *   - Warehouse table has product_id and units, but not dimensions
 *   - Products table has dimensions (Width, Length, Height)
 *   - JOIN combines both to calculate volume
 *   - INNER JOIN ensures only valid product_ids are included
 * 
 * Why Width * Length * Height * units?
 * 
 *   - Volume of one product = Width × Length × Height
 *   - If warehouse has 'units' of that product, total volume = volume × units
 *   - Example: Product with dimensions 5×5×5, 10 units = 5×5×5×10 = 1,250
 * 
 * Why GROUP BY warehouse name?
 * 
 *   - Need to aggregate volumes per warehouse
 *   - Multiple products can be in the same warehouse
 *   - GROUP BY groups all products in the same warehouse together
 *   - SUM calculates total volume for each warehouse
 * 
 * Volume Calculation Breakdown:
 * 
 *   For each warehouse-product combination:
 *   - Get product dimensions from Products table
 *   - Get number of units from Warehouse table
 *   - Calculate: Width × Length × Height × units
 *   - This gives the volume occupied by that product in that warehouse
 * 
 *   For each warehouse:
 *   - Sum all product volumes
 *   - This gives the total volume occupied by all products in that warehouse
 * 
 * Edge Cases:
 * 
 *   - Warehouse with no products: Excluded (no JOIN match)
 *   - Warehouse with single product: Volume = that product's volume
 *   - Warehouse with multiple products: Sum of all product volumes
 *   - Product with 0 units: Contributes 0 to volume
 *   - Product with large dimensions: Large contribution to volume
 * 
 * NULL Handling:
 * 
 *   - If Width, Length, Height, or units is NULL, multiplication returns NULL
 *   - SUM ignores NULL values
 *   - If all values are NULL, SUM returns NULL (not 0)
 *   - Consider using COALESCE or IFNULL if NULL handling is needed
 * 
 * Performance Considerations:
 * 
 *   - JOIN on product_id should use index
 *   - GROUP BY requires sorting or hashing
 *   - Volume calculation is simple arithmetic (fast)
 *   - Consider indexing on Warehouse.product_id and Products.product_id
 * 
 * Alternative: Using Subquery
 * 
 *   SELECT
 *       w.name AS warehouse_name,
 *       (SELECT SUM(p.Width * p.Length * p.Height * w2.units)
 *        FROM Warehouse w2
 *        JOIN Products p ON w2.product_id = p.product_id
 *        WHERE w2.name = w.name) AS volume
 *   FROM Warehouse w
 *   GROUP BY w.name;
 * 
 *   - Uses correlated subquery
 *   - Less efficient than JOIN approach
 *   - More complex but produces same result
 */

# Write your MySQL query statement below

SELECT
    w.name AS warehouse_name,
    SUM(p.Width * p.Length * p.Height * w.units) AS volume
FROM Warehouse w
JOIN Products p
    ON w.product_id = p.product_id
GROUP BY w.name;

