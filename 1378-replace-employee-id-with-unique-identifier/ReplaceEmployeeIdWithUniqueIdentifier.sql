/*
 * LeetCode 1378: Replace Employee ID With The Unique Identifier
 * 
 * Problem:
 * Show the unique ID of each user. If a user does not have a unique ID, replace
 * it with null. Return the result table in any order.
 * 
 * Table: Employees
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | name          | varchar |
 * +---------------+---------+
 * id is the primary key for this table.
 * Each row of this table contains the id and the name of an employee in a company.
 * 
 * Table: EmployeeUNI
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | unique_id     | int     |
 * +---------------+---------+
 * (id, unique_id) is the primary key for this table.
 * Each row of this table contains the id and the corresponding unique ID of an
 * employee in the company.
 * 
 * Example:
 * Input:
 * Employees table:
 * +----+----------+
 * | id | name     |
 * +----+----------+
 * | 1  | Alice    |
 * | 7  | Bob      |
 * | 11 | Meir     |
 * | 90 | Winston  |
 * | 3  | Jonathan |
 * +----+----------+
 * 
 * EmployeeUNI table:
 * +----+-----------+
 * | id | unique_id |
 * +----+-----------+
 * | 3  | 1         |
 * | 11 | 2         |
 * | 90 | 3         |
 * +----+-----------+
 * 
 * Output:
 * +-----------+----------+
 * | unique_id | name     |
 * +-----------+----------+
 * | null      | Alice    |
 * | null      | Bob      |
 * | 2         | Meir     |
 * | 3         | Winston  |
 * | 1         | Jonathan |
 * +-----------+----------+
 * 
 * Explanation:
 * - Alice and Bob don't have a unique ID, so we show null instead.
 * - The unique ID of Meir is 2.
 * - The unique ID of Winston is 3.
 * - The unique ID of Jonathan is 1.
 * 
 * Approach: LEFT JOIN
 * 
 * Key Insight:
 * - We need ALL employees from the Employees table
 * - Some employees may not have a unique_id in EmployeeUNI
 * - Use LEFT JOIN to include all employees, with NULL for missing unique_id
 * - LEFT JOIN preserves all rows from the left table (Employees)
 * 
 * Algorithm:
 * 1. Start with Employees table (left table)
 * 2. LEFT JOIN with EmployeeUNI on id
 * 3. Select unique_id and name
 * 4. Employees without unique_id will have NULL in unique_id column
 * 
 * Why LEFT JOIN?
 * 
 *   - INNER JOIN would exclude employees without unique_id
 *   - RIGHT JOIN would exclude employees not in EmployeeUNI (but all employees
 *     should be shown)
 *   - LEFT JOIN ensures all employees are included, with NULL for missing unique_id
 * 
 * Visual Example:
 * 
 *   Employees:          EmployeeUNI:        Result:
 *   +----+-------+      +----+--------+    +-----------+-------+
 *   | id | name  |      | id | unique |    | unique_id | name  |
 *   +----+-------+      +----+--------+    +-----------+-------+
 *   | 1  | Alice |      | 3  | 1      |    | null      | Alice |
 *   | 7  | Bob   |      | 11 | 2      |    | null      | Bob   |
 *   | 11 | Meir  |      | 90 | 3      |    | 2         | Meir  |
 *   | 90 | Winston|     +----+--------+    | 3         | Winston|
 *   | 3  | Jonathan|                       | 1         | Jonathan|
 *   +----+-------+                         +-----------+-------+
 * 
 *   LEFT JOIN matches:
 *   - id=3  → unique_id=1
 *   - id=11 → unique_id=2
 *   - id=90 → unique_id=3
 *   - id=1  → no match → unique_id=NULL
 *   - id=7  → no match → unique_id=NULL
 * 
 * Edge Cases:
 * 
 *   - All employees have unique_id: All unique_id values will be non-null
 *   - No employees have unique_id: All unique_id values will be null
 *   - Some employees have unique_id: Mixed null and non-null values
 *   - Empty Employees table: Returns empty result
 *   - Empty EmployeeUNI table: All unique_id values will be null
 */

# Write your MySQL query statement below

SELECT eu.unique_id, e.name
FROM Employees e
LEFT JOIN EmployeeUNI eu
ON e.id = eu.id;

