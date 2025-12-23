/*
 * LeetCode 1789: Primary Department for Each Employee
 *
 * Problem:
 * Employees can belong to multiple departments. When an employee belongs to
 * multiple departments, one of them is their primary department. Note that when
 * an employee belongs to only one department, their primary column is 'N'.
 *
 * Write a solution to report all the employees with their primary department.
 * For employees who belong to one department, report their only department.
 *
 * Return the result table in any order.
 *
 * Table: Employee
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | employee_id   | int     |
 * | department_id | int     |
 * | primary_flag  | varchar |
 * +---------------+---------+
 * (employee_id, department_id) is the primary key for this table.
 * employee_id is the id of the employee.
 * department_id is the id of the department to which the employee belongs.
 * primary_flag is an ENUM of type ('Y', 'N'). If the flag is 'Y', the department
 * is the primary department for the employee. If the flag is 'N', the department
 * is not the primary.
 *
 * Approach: Filter with OR and Subquery
 *
 * Key Insight:
 * - An employee's primary department is either:
 *   1. The department where primary_flag = 'Y' (explicitly marked as primary)
 *   2. The only department if the employee belongs to just one department
 *      (in this case, primary_flag = 'N' but it's still the primary)
 *
 * Algorithm:
 * 1. Select employees where primary_flag = 'Y' (explicit primary departments)
 * 2. OR select employees who belong to only one department (implicit primary)
 * 3. Use a subquery to find employees with COUNT(*) = 1
 *
 * Why This Works:
 * - Employees with primary_flag = 'Y' are explicitly marked as primary
 * - Employees with only one department have that department as their primary
 *   (even if primary_flag = 'N')
 * - The OR condition covers both cases
 *
 * Alternative Approach:
 * - Could use UNION instead of OR with subquery
 * - Both approaches are valid and produce the same result
 *
 * Example:
 *
 *   Employee:
 *   +-------------+---------------+--------------+
 *   | employee_id | department_id | primary_flag |
 *   +-------------+---------------+--------------+
 *   | 1           | 1             | N            |
 *   | 2           | 1             | Y             |
 *   | 2           | 2             | N             |
 *   | 3           | 3             | N             |
 *   | 4           | 2             | N             |
 *   | 4           | 3             | Y             |
 *   | 4           | 4             | N             |
 *   +-------------+---------------+--------------+
 *
 *   Analysis:
 *   - Employee 1: Only one department (1), primary_flag = 'N'
 *     → Include: belongs to only one department
 *   - Employee 2: Two departments (1, 2), department 1 has primary_flag = 'Y'
 *     → Include: primary_flag = 'Y' for department 1
 *   - Employee 3: Only one department (3), primary_flag = 'N'
 *     → Include: belongs to only one department
 *   - Employee 4: Three departments (2, 3, 4), department 3 has primary_flag = 'Y'
 *     → Include: primary_flag = 'Y' for department 3
 *
 *   Result:
 *   +-------------+---------------+
 *   | employee_id | department_id |
 *   +-------------+---------------+
 *   | 1           | 1             |
 *   | 2           | 1             |
 *   | 3           | 3             |
 *   | 4           | 3             |
 *   +-------------+---------------+
 *
 * Subquery Explanation:
 *   SELECT employee_id
 *   FROM Employee
 *   GROUP BY employee_id
 *   HAVING COUNT(*) = 1
 *
 *   This finds employees who appear exactly once in the Employee table,
 *   meaning they belong to only one department.
 *
 * Important Notes:
 * - The OR condition ensures we capture both explicit and implicit primary departments
 * - The subquery efficiently identifies single-department employees
 * - No need for UNION or DISTINCT since each employee appears exactly once in result
 */

# Write your MySQL query statement below

SELECT employee_id, department_id
FROM Employee
WHERE primary_flag = 'Y'
   OR employee_id IN (
       SELECT employee_id
       FROM Employee
       GROUP BY employee_id
       HAVING COUNT(*) = 1
   );

