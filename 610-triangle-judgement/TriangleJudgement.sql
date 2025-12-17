/*
 * LeetCode 610: Triangle Judgement
 * 
 * Problem:
 * Report for every three line segments whether they can form a triangle.
 * 
 * Table: Triangle
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | x           | int  |
 * | y           | int  |
 * | z           | int  |
 * +-------------+------+
 * (x, y, z) is the primary key.
 * Each row contains the lengths of three line segments.
 * 
 * Triangle Inequality Theorem:
 * Three line segments can form a triangle if and only if the sum of any
 * two sides is greater than the third side:
 *   - x + y > z
 *   - x + z > y
 *   - y + z > x
 * 
 * Example:
 * Input:
 * +----+----+----+
 * | x  | y  | z  |
 * +----+----+----+
 * | 13 | 15 | 30 |
 * | 10 | 20 | 15 |
 * +----+----+----+
 * 
 * Output:
 * +----+----+----+----------+
 * | x  | y  | z  | triangle |
 * +----+----+----+----------+
 * | 13 | 15 | 30 | No       |
 * | 10 | 20 | 15 | Yes      |
 * +----+----+----+----------+
 * 
 * Explanation:
 * - (13, 15, 30): 13 + 15 = 28, but 28 < 30 → cannot form triangle ❌
 * - (10, 20, 15): 10+20=30 > 15 ✓, 10+15=25 > 20 ✓, 20+15=35 > 10 ✓ → triangle ✅
 * 
 * Approach:
 * - Use CASE expression to check all three conditions
 * - If ALL conditions are true → 'Yes', otherwise → 'No'
 */

# Write your MySQL query statement below

SELECT *,
CASE
    WHEN x + y > z AND x + z > y AND y + z > x THEN 'Yes'
    ELSE 'No'
END AS triangle
FROM Triangle;

