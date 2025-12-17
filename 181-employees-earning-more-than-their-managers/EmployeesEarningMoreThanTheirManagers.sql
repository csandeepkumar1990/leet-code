/*
 * LeetCode 181: Employees Earning More Than Their Managers
 * 
 * Problem:
 * Given an Employee table with columns: id, name, salary, managerId
 * Find the employees who earn more than their managers.
 * 
 * Table: Employee
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | name        | varchar |
 * | salary      | int     |
 * | managerId   | int     |
 * +-------------+---------+
 * id is the primary key.
 * Each row indicates the ID, name, salary, and manager's ID of an employee.
 * 
 * Example:
 * Input:
 * +----+-------+--------+-----------+
 * | id | name  | salary | managerId |
 * +----+-------+--------+-----------+
 * | 1  | Joe   | 70000  | 3         |
 * | 2  | Henry | 80000  | 4         |
 * | 3  | Sam   | 60000  | NULL      |
 * | 4  | Max   | 90000  | NULL      |
 * +----+-------+--------+-----------+
 * 
 * Output:
 * +----------+
 * | Employee |
 * +----------+
 * | Joe      |
 * +----------+
 * 
 * Explanation:
 * - Joe (id=1) earns 70000 and his manager is Sam (id=3) who earns 60000
 *   → 70000 > 60000, so Joe is included
 * - Henry (id=2) earns 80000 and his manager is Max (id=4) who earns 90000
 *   → 80000 < 90000, so Henry is NOT included
 * 
 * Approach:
 * - Self-join the Employee table: alias 'e' for employee, alias 'm' for manager
 * - Join condition: e.managerId = m.id (links employee to their manager)
 * - Filter: e.salary > m.salary (employee earns more than manager)
 */

# Write your MySQL query statement below

SELECT e.name AS Employee
FROM Employee e
JOIN Employee m
  ON e.managerId = m.id
WHERE e.salary > m.salary;

