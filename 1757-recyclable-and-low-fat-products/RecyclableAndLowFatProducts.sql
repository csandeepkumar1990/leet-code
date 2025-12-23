/*
 * LeetCode 1757: Recyclable and Low Fat Products
 *
 * Problem:
 * Write a solution to find the ids of products that are both low fat and recyclable.
 *
 * Return the result table in any order.
 *
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | low_fats    | enum    |
 * | recyclable  | enum    |
 * +-------------+---------+
 * product_id is the primary key for this table.
 * low_fats is an ENUM of type ('Y', 'N') where 'Y' means this product is low fat
 * and 'N' means it is not.
 * recyclable is an ENUM of type ('Y', 'N') where 'Y' means this product is
 * recyclable and 'N' means it is not.
 *
 * Approach: Simple WHERE Filter
 *
 * Key Insight:
 * - Need to filter products where both low_fats = 'Y' AND recyclable = 'Y'
 * - This is a straightforward filtering operation
 *
 * Algorithm:
 * 1. SELECT product_id from Products table
 * 2. WHERE low_fats = 'Y' AND recyclable = 'Y'
 *
 * Example:
 *
 *   Products:
 *   +------------+----------+------------+
 *   | product_id | low_fats | recyclable |
 *   +------------+----------+------------+
 *   | 0          | Y        | N          |
 *   | 1          | Y        | Y          |
 *   | 2          | N        | Y          |
 *   | 3          | Y        | Y          |
 *   | 4          | N        | N          |
 *   +------------+----------+------------+
 *
 *   Filtering for low_fats = 'Y' AND recyclable = 'Y':
 *   - Product 0: Y and N → ✗
 *   - Product 1: Y and Y → ✓
 *   - Product 2: N and Y → ✗
 *   - Product 3: Y and Y → ✓
 *   - Product 4: N and N → ✗
 *
 *   Result:
 *   +------------+
 *   | product_id |
 *   +------------+
 *   | 1          |
 *   | 3          |
 *   +------------+
 *
 * Important Notes:
 * - Both conditions must be true (AND operator)
 * - ENUM values are case-sensitive, so 'Y' must match exactly
 * - Simple filtering query, no joins or aggregations needed
 */

# Write your MySQL query statement below

SELECT product_id
FROM Products
WHERE low_fats = 'Y' AND recyclable = 'Y';

