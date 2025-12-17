/*
 * LeetCode 577: Employee Bonus
 * 
 * Problem:
 * Find the name and bonus of employees whose bonus is less than 1000,
 * or employees who have no bonus record.
 * 
 * Table: Employee
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | empId       | int     |
 * | name        | varchar |
 * | supervisor  | int     |
 * | salary      | int     |
 * +-------------+---------+
 * empId is the primary key.
 * 
 * Table: Bonus
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | empId       | int     |
 * | bonus       | int     |
 * +-------------+---------+
 * empId is the primary key and foreign key to Employee.empId.
 * 
 * Example:
 * Input:
 * Employee table:
 * +-------+--------+------------+--------+
 * | empId | name   | supervisor | salary |
 * +-------+--------+------------+--------+
 * | 1     | John   | 3          | 1000   |
 * | 2     | Dan    | 3          | 2000   |
 * | 3     | Brad   | null       | 4000   |
 * | 4     | Thomas | 3          | 4000   |
 * +-------+--------+------------+--------+
 * 
 * Bonus table:
 * +-------+-------+
 * | empId | bonus |
 * +-------+-------+
 * | 2     | 500   |
 * | 4     | 2000  |
 * +-------+-------+
 * 
 * Output:
 * +------+-------+
 * | name | bonus |
 * +------+-------+
 * | John | null  |
 * | Dan  | 500   |
 * | Brad | null  |
 * +------+-------+
 * 
 * Explanation:
 * - John (empId=1): no bonus record → bonus IS NULL ✅
 * - Dan (empId=2): bonus = 500 → 500 < 1000 ✅
 * - Brad (empId=3): no bonus record → bonus IS NULL ✅
 * - Thomas (empId=4): bonus = 2000 → 2000 >= 1000 ❌
 * 
 * Approach:
 * - LEFT JOIN: Include all employees, even those without a bonus record
 * - WHERE clause: Filter for bonus < 1000 OR bonus IS NULL
 *   (NULL check needed because employees without bonus have NULL after LEFT JOIN)
 */

# Write your MySQL query statement below

SELECT e.name, b.bonus
FROM Employee e
LEFT JOIN Bonus b
  ON e.empId = b.empId
WHERE b.bonus < 1000 OR b.bonus IS NULL;

