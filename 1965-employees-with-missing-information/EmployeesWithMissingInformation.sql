/*
 * LeetCode 1965: Employees With Missing Information
 *
 * Problem:
 * Write an SQL query to report the IDs of all the employees with missing information.
 * The information of an employee is missing if:
 * - The employee's name is missing, or
 * - The employee's salary is missing.
 *
 * Return the result table ordered by employee_id in ascending order.
 *
 * Table: Employees
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | employee_id | int     |
 * | name        | varchar |
 * +-------------+---------+
 * employee_id is the primary key for this table.
 * Each row of this table indicates the name of an employee.
 *
 * Table: Salaries
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | employee_id | int     |
 * | salary      | int     |
 * +-------------+---------+
 * employee_id is the primary key for this table.
 * Each row of this table indicates the salary of an employee.
 *
 * Approach: UNION with NOT IN Subqueries
 *
 * Key Insight:
 * - An employee has missing information if they exist in one table but not the other
 * - Need to find employees in Employees table without a corresponding entry in Salaries
 * - Need to find employees in Salaries table without a corresponding entry in Employees
 * - UNION combines both sets of missing employee IDs
 * - ORDER BY ensures ascending order of employee_id
 *
 * Algorithm:
 * 1. Find employee_id from Employees that are NOT IN Salaries
 * 2. Find employee_id from Salaries that are NOT IN Employees
 * 3. UNION the two result sets (automatically removes duplicates)
 * 4. ORDER BY employee_id
 *
 * How NOT IN Works:
 *
 *   Employees                    Salaries
 *   +-------------+------+      +-------------+--------+
 *   | employee_id | name |      | employee_id | salary |
 *   +-------------+------+      +-------------+--------+
 *   | 2           | Crew |      | 5           | 76071  |
 *   | 4           | Haven|      | 1           | 22517  |
 *   | 5           | Kristian|   | 4           | 63539  |
 *   +-------------+------+      +-------------+--------+
 *
 *   First Query: Employees NOT IN Salaries
 *   - employee_id 2: NOT in Salaries → Missing salary! ✓
 *   - employee_id 4: IN Salaries → Has salary ✗
 *   - employee_id 5: IN Salaries → Has salary ✗
 *   Result: {2}
 *
 *   Second Query: Salaries NOT IN Employees
 *   - employee_id 5: IN Employees → Has name ✗
 *   - employee_id 1: NOT in Employees → Missing name! ✓
 *   - employee_id 4: IN Employees → Has name ✗
 *   Result: {1}
 *
 *   UNION: {2} ∪ {1} = {1, 2}
 *   ORDER BY employee_id: {1, 2}
 *
 * Example:
 *
 *   Employees:
 *   +-------------+---------+
 *   | employee_id | name    |
 *   +-------------+---------+
 *   | 2           | Crew     |
 *   | 4           | Haven    |
 *   | 5           | Kristian |
 *   +-------------+---------+
 *
 *   Salaries:
 *   +-------------+--------+
 *   | employee_id | salary |
 *   +-------------+--------+
 *   | 5           | 76071  |
 *   | 1           | 22517  |
 *   | 4           | 63539  |
 *   +-------------+--------+
 *
 *   Processing:
 *   - Employee 1: Has salary but NO name → Missing information! ✓
 *   - Employee 2: Has name but NO salary → Missing information! ✓
 *   - Employee 4: Has both name and salary → Complete ✓
 *   - Employee 5: Has both name and salary → Complete ✓
 *
 *   Result (ordered by employee_id):
 *   +-------------+
 *   | employee_id |
 *   +-------------+
 *   | 1           |  ← Missing name
 *   | 2           |  ← Missing salary
 *   +-------------+
 *
 * Alternative Approach: Using LEFT JOIN
 *
 *   SELECT e.employee_id
 *   FROM Employees e
 *   LEFT JOIN Salaries s ON e.employee_id = s.employee_id
 *   WHERE s.employee_id IS NULL
 *
 *   UNION
 *
 *   SELECT s.employee_id
 *   FROM Salaries s
 *   LEFT JOIN Employees e ON s.employee_id = e.employee_id
 *   WHERE e.employee_id IS NULL
 *
 *   ORDER BY employee_id;
 *
 * Why UNION?
 * - UNION automatically removes duplicate employee_ids
 * - If an employee_id appears in both result sets, it only appears once
 * - UNION ALL would keep duplicates (not needed here)
 *
 * Important Notes:
 * - NOT IN with NULL values: If Salaries.employee_id contains NULL,
 *   NOT IN will return no rows (NULL comparison issue)
 * - In practice, since employee_id is PRIMARY KEY, it cannot be NULL
 * - ORDER BY applies to the final UNION result
 * - Both subqueries return the same column (employee_id), so UNION is valid
 */

# Write your MySQL query statement below
SELECT employee_id
FROM Employees
WHERE employee_id NOT IN (SELECT employee_id FROM Salaries)

UNION

SELECT employee_id
FROM Salaries
WHERE employee_id NOT IN (SELECT employee_id FROM Employees)

ORDER BY employee_id;

