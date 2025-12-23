/*
 * LeetCode 1873: Calculate Special Bonus
 *
 * Problem:
 * Write a solution to calculate the bonus of each employee. The bonus of an
 * employee is 100% of their salary if:
 * - The ID of the employee is an odd number AND
 * - The name of the employee does not start with the character 'M'.
 *
 * Otherwise, the bonus of an employee is 0.
 *
 * Return the result table ordered by employee_id.
 *
 * Table: Employees
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | employee_id | int     |
 * | name        | varchar |
 * | salary      | int     |
 * +-------------+---------+
 * employee_id is the primary key for this table.
 * Each row of this table indicates the employee ID, employee name, and salary.
 *
 * Approach: CASE Expression with Conditions
 *
 * Key Insight:
 * - Bonus = salary if BOTH conditions are true:
 *   1. employee_id is odd (employee_id % 2 = 1)
 *   2. name does NOT start with 'M' (name NOT LIKE 'M%')
 * - Otherwise, bonus = 0
 *
 * Algorithm:
 * 1. Use CASE expression to check both conditions
 * 2. WHEN employee_id % 2 = 1 AND name NOT LIKE 'M%' THEN salary
 * 3. ELSE 0
 * 4. ORDER BY employee_id
 *
 * Why Modulo Operator (% 2 = 1)?
 * - employee_id % 2 returns the remainder when divided by 2
 * - If remainder is 1, the number is odd
 * - If remainder is 0, the number is even
 *
 * Why NOT LIKE 'M%'?
 * - LIKE 'M%' matches strings starting with 'M' followed by any characters
 * - NOT LIKE 'M%' matches strings that do NOT start with 'M'
 * - Alternative: LEFT(name, 1) != 'M' or SUBSTRING(name, 1, 1) != 'M'
 *
 * Example:
 *
 *   Employees:
 *   +-------------+---------+--------+
 *   | employee_id | name    | salary |
 *   +-------------+---------+--------+
 *   | 2           | Meir    | 3000   |
 *   | 3           | Michael | 3800   |
 *   | 7           | Addilyn | 7400   |
 *   | 8           | Juan    | 6100   |
 *   | 9           | Kannon  | 7700   |
 *   +-------------+---------+--------+
 *
 *   Analysis:
 *   - Employee 2: ID is even (2 % 2 = 0) → bonus = 0
 *   - Employee 3: ID is odd (3 % 2 = 1) BUT name starts with 'M' → bonus = 0
 *   - Employee 7: ID is odd (7 % 2 = 1) AND name doesn't start with 'M' → bonus = 7400
 *   - Employee 8: ID is even (8 % 2 = 0) → bonus = 0
 *   - Employee 9: ID is odd (9 % 2 = 1) AND name doesn't start with 'M' → bonus = 7700
 *
 *   Result:
 *   +-------------+-------+
 *   | employee_id | bonus |
 *   +-------------+-------+
 *   | 2           | 0     |
 *   | 3           | 0     |
 *   | 7           | 7400  |
 *   | 8           | 0     |
 *   | 9           | 7700  |
 *   +-------------+-------+
 *
 * Condition Breakdown:
 *   employee_id % 2 = 1  →  Checks if ID is odd
 *   name NOT LIKE 'M%'   →  Checks if name doesn't start with 'M'
 *   AND                  →  Both must be true
 *
 * Truth Table:
 *   ID Odd? | Name starts with M? | Bonus
 *   --------|---------------------|-------
 *   Yes     | No                  | salary
 *   Yes     | Yes                 | 0
 *   No      | No                  | 0
 *   No      | Yes                 | 0
 *
 * Important Notes:
 * - Both conditions must be true (AND operator)
 * - Modulo operator (%) checks for odd numbers
 * - LIKE 'M%' pattern matches names starting with 'M'
 * - CASE expression provides clear conditional logic
 * - ORDER BY ensures result is sorted by employee_id
 */

# Write your MySQL query statement below

SELECT 
    employee_id,
    CASE 
        WHEN employee_id % 2 = 1 AND name NOT LIKE 'M%' THEN salary
        ELSE 0
    END AS bonus
FROM Employees
ORDER BY employee_id;

