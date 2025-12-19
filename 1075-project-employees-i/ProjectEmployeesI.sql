/*
 * LeetCode 1075: Project Employees I
 * 
 * Problem:
 * Write a solution to report the average experience years of all employees
 * for each project, rounded to 2 decimal places.
 * 
 * Table: Project
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | project_id  | int     |
 * | employee_id | int     |
 * +-------------+---------+
 * (project_id, employee_id) is the primary key.
 * employee_id is a foreign key to Employee table.
 * 
 * Table: Employee
 * +------------------+---------+
 * | Column Name      | Type    |
 * +------------------+---------+
 * | employee_id      | int     |  (Primary Key)
 * | name             | varchar |
 * | experience_years | int     |
 * +------------------+---------+
 * 
 * Approach: JOIN + GROUP BY + AVG
 * 
 * Key Insight:
 * - Join Project with Employee to get experience years
 * - Group by project_id to calculate average per project
 * - Use ROUND(..., 2) to format to 2 decimal places
 * 
 * Algorithm:
 * 1. JOIN Project and Employee on employee_id
 * 2. GROUP BY project_id
 * 3. Calculate AVG(experience_years) for each group
 * 4. ROUND to 2 decimal places
 * 
 * Example:
 * 
 *   Project:
 *   +-------------+-------------+
 *   | project_id  | employee_id |
 *   +-------------+-------------+
 *   | 1           | 1           |
 *   | 1           | 2           |
 *   | 1           | 3           |
 *   | 2           | 1           |
 *   | 2           | 4           |
 *   +-------------+-------------+
 * 
 *   Employee:
 *   +-------------+--------+------------------+
 *   | employee_id | name   | experience_years |
 *   +-------------+--------+------------------+
 *   | 1           | Khaled | 3                |
 *   | 2           | Ali    | 2                |
 *   | 3           | John   | 1                |
 *   | 4           | Doe    | 2                |
 *   +-------------+--------+------------------+
 * 
 *   After JOIN:
 *   +------------+-------------+------------------+
 *   | project_id | employee_id | experience_years |
 *   +------------+-------------+------------------+
 *   | 1          | 1           | 3                |
 *   | 1          | 2           | 2                |
 *   | 1          | 3           | 1                |
 *   | 2          | 1           | 3                |
 *   | 2          | 4           | 2                |
 *   +------------+-------------+------------------+
 * 
 *   GROUP BY project_id:
 * 
 *   Project 1: employees (1, 2, 3) → years (3, 2, 1)
 *              AVG = (3 + 2 + 1) / 3 = 6 / 3 = 2.00
 * 
 *   Project 2: employees (1, 4) → years (3, 2)
 *              AVG = (3 + 2) / 2 = 5 / 2 = 2.50
 * 
 *   Result:
 *   +-------------+---------------+
 *   | project_id  | average_years |
 *   +-------------+---------------+
 *   | 1           | 2.00          |
 *   | 2           | 2.50          |
 *   +-------------+---------------+
 * 
 * Visual:
 * 
 *   Project 1 ──┬── Employee 1 (3 yrs)
 *               ├── Employee 2 (2 yrs)  → AVG = 2.00
 *               └── Employee 3 (1 yr)
 * 
 *   Project 2 ──┬── Employee 1 (3 yrs)
 *               └── Employee 4 (2 yrs)  → AVG = 2.50
 */

SELECT 
    p.project_id,
    ROUND(AVG(e.experience_years), 2) AS average_years
FROM Project p
JOIN Employee e
ON p.employee_id = e.employee_id
GROUP BY p.project_id
ORDER BY p.project_id;

