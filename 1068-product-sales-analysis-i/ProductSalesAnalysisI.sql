/*
 * LeetCode 1068: Product Sales Analysis I
 * 
 * Problem:
 * Write a solution to report the product_name, year, and price for each
 * sale_id in the Sales table. Return the result in any order.
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
 * Approach: Simple JOIN
 * 
 * Key Insight:
 * - Sales table has product_id but not product_name
 * - Product table has the product_name we need
 * - JOIN on product_id to combine the information
 * 
 * Algorithm:
 * 1. JOIN Sales with Product on product_id
 * 2. SELECT product_name, year, price
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
 *   +---------+------------+------+----------+-------+
 * 
 *   Product:
 *   +------------+--------------+
 *   | product_id | product_name |
 *   +------------+--------------+
 *   | 100        | Nokia        |
 *   | 200        | Apple        |
 *   | 300        | Samsung      |
 *   +------------+--------------+
 * 
 *   After JOIN (matching product_id):
 * 
 *   sale_id=1: product_id=100 → Nokia
 *   sale_id=2: product_id=100 → Nokia
 *   sale_id=7: product_id=200 → Apple
 * 
 *   Result:
 *   +--------------+------+-------+
 *   | product_name | year | price |
 *   +--------------+------+-------+
 *   | Nokia        | 2008 | 5000  |
 *   | Nokia        | 2009 | 5000  |
 *   | Apple        | 2011 | 9000  |
 *   +--------------+------+-------+
 * 
 * Visual:
 * 
 *   Sales                      Product
 *   ┌─────────────────┐        ┌─────────────────┐
 *   │ sale_id: 1      │        │ product_id: 100 │
 *   │ product_id: 100 │───────→│ name: Nokia     │
 *   │ year: 2008      │        └─────────────────┘
 *   │ price: 5000     │
 *   └─────────────────┘
 *           ↓
 *   Result: (Nokia, 2008, 5000)
 * 
 * Note: This is a basic JOIN problem - no aggregation, no filtering.
 *       Just combining data from two tables.
 */

SELECT 
    p.product_name, 
    s.year, 
    s.price
FROM Sales s
JOIN Product p
ON s.product_id = p.product_id;

