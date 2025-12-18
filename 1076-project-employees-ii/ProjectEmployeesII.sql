/*
 * LeetCode 1076: Project Employees II
 * 
 * Problem:
 * Write a solution to report all the projects that have the most employees.
 * Return the result table in any order.
 * 
 * Table: Project
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | project_id  | int     |
 * | employee_id | int     |
 * +-------------+---------+
 * (project_id, employee_id) is the primary key.
 * 
 * Table: Employee
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | employee_id      | int     |
 * | name             | varchar |
 * | experience_years | int     |
 * +------------------+---------+
 * 
 * Approach: GROUP BY with Subquery for Maximum Count
 * 
 * Key Insight:
 * - Count employees per project
 * - Find the maximum count across all projects
 * - Return all projects matching that maximum (handles ties)
 * 
 * Common Mistake:
 * - Using a fixed threshold like COUNT(*) > 2 is WRONG
 * - We need the MAXIMUM count, which could be any number
 * 
 * Algorithm:
 * 1. Group by project_id, count employees
 * 2. Subquery: Find the max employee count among all projects
 * 3. HAVING: Filter to projects matching that max count
 * 
 * Example:
 * 
 *   Project table:
 *   +-------------+-------------+
 *   | project_id  | employee_id |
 *   +-------------+-------------+
 *   | 1           | 1           |
 *   | 1           | 2           |
 *   | 1           | 3           |
 *   | 2           | 1           |
 *   | 2           | 4           |
 *   | 3           | 5           |
 *   | 3           | 6           |
 *   | 3           | 7           |
 *   +-------------+-------------+
 * 
 *   Step 1 - Count employees per project:
 *   +-------------+----------------+
 *   | project_id  | employee_count |
 *   +-------------+----------------+
 *   | 1           | 3              |  ← Max (tie)
 *   | 2           | 2              |
 *   | 3           | 3              |  ← Max (tie)
 *   +-------------+----------------+
 * 
 *   Step 2 - Subquery finds max count: 3
 * 
 *   Step 3 - HAVING filters: projects 1 and 3
 * 
 *   Result:
 *   +-------------+
 *   | project_id  |
 *   +-------------+
 *   | 1           |
 *   | 3           |
 *   +-------------+
 * 
 * Why NOT "COUNT(*) > 2":
 *   - If all projects had 2 employees, the answer should include ALL of them
 *   - A fixed threshold doesn't adapt to the actual data
 */

SELECT project_id
FROM Project
GROUP BY project_id
HAVING COUNT(employee_id) = (
    SELECT COUNT(employee_id)
    FROM Project
    GROUP BY project_id
    ORDER BY COUNT(employee_id) DESC
    LIMIT 1
);

