/*
 * LeetCode 1978: Employees Whose Manager Left the Company
 * 
 * Problem:
 * Write a solution to find the IDs of the employees whose salary is strictly less than
 * $30000 and whose manager left the company. When a manager leaves the company, their
 * information is deleted from the Employees table, but the reports still have their
 * manager_id set to the manager that left.
 * 
 * Return the result table ordered by employee_id in ascending order.
 * 
 * Table: Employees
 * +-------------+----------+
 * | Column Name | Type     |
 * +-------------+----------+
 * | employee_id | int      |
 * | name        | varchar  |
 * | manager_id  | int      |
 * | salary      | int      |
 * +-------------+----------+
 * employee_id is the primary key for this table.
 * This table contains information about the employees, their salary, and the ID of
 * their manager. Some employees do not have a manager (manager_id is null).
 * 
 * Approach: Self-Join with LEFT JOIN Anti-Pattern
 * 
 * Key Insight:
 * - Find employees with salary < 30000
 * - Find employees whose manager_id is not NULL (they have a manager)
 * - Find employees whose manager is not in the Employees table (manager left)
 * - Use LEFT JOIN to check if manager exists
 * - WHERE m.employee_id IS NULL means manager not found
 * 
 * Algorithm:
 * 1. Self-join Employees table (e for employees, m for managers)
 * 2. LEFT JOIN on e.manager_id = m.employee_id
 * 3. Filter:
 *    - e.salary < 30000
 *    - e.manager_id IS NOT NULL (employee has a manager)
 *    - m.employee_id IS NULL (manager not found in table)
 * 4. ORDER BY e.employee_id
 * 
 * Time Complexity: O(n), where n is the number of employees.
 *   - LEFT JOIN: O(n) to match managers
 *   - WHERE filter: O(n) to filter rows
 *   - ORDER BY: O(n log n) for sorting
 * 
 * Space Complexity: O(k), where k is the number of employees with missing managers.
 *   - Result set contains employees whose managers left
 * 
 * Example:
 * 
 *   Employees:
 *   +-------------+-----------+------------+--------+
 *   | employee_id | name      | manager_id | salary |
 *   +-------------+-----------+------------+--------+
 *   | 3           | Mila      | 9          | 60301  |
 *   | 12          | Antonella | NULL       | 31000  |
 *   | 13          | Emery     | NULL       | 67084  |
 *   | 1           | Kalel      | 11         | 21241  |
 *   | 9           | Mikaela   | NULL       | 50937  |
 *   | 11          | Joziah    | 6          | 28485  |
 *   +-------------+-----------+------------+--------+
 * 
 *   Note: Manager 6 and 11 are referenced but not in the table (they left)
 * 
 *   Step 1 - LEFT JOIN Employees e with Employees m:
 *   +-------------+-----------+------------+--------+-------------+
 *   | e.employee_id | e.name | e.manager_id | e.salary | m.employee_id |
 *   +-------------+-----------+------------+--------+-------------+
 *   | 3           | Mila      | 9          | 60301  | 9           |  ← Manager 9 exists
 *   | 12          | Antonella | NULL       | 31000  | NULL        |  ← No manager
 *   | 13          | Emery     | NULL       | 67084  | NULL        |  ← No manager
 *   | 1           | Kalel      | 11         | 21241  | NULL        |  ← Manager 11 NOT FOUND!
 *   | 9           | Mikaela   | NULL       | 50937  | NULL        |  ← No manager
 *   | 11          | Joziah    | 6          | 28485  | NULL        |  ← Manager 6 NOT FOUND!
 *   +-------------+-----------+------------+--------+-------------+
 * 
 *   Step 2 - Apply WHERE filters:
 *   - e.salary < 30000:
 *     - Employee 3: 60301 >= 30000 ✗
 *     - Employee 12: 31000 >= 30000 ✗
 *     - Employee 13: 67084 >= 30000 ✗
 *     - Employee 1: 21241 < 30000 ✓
 *     - Employee 9: 50937 >= 30000 ✗
 *     - Employee 11: 28485 < 30000 ✓
 *     Remaining: [1, 11]
 * 
 *   - e.manager_id IS NOT NULL:
 *     - Employee 1: manager_id = 11 (not NULL) ✓
 *     - Employee 11: manager_id = 6 (not NULL) ✓
 *     Remaining: [1, 11]
 * 
 *   - m.employee_id IS NULL:
 *     - Employee 1: m.employee_id = NULL (manager 11 not found) ✓
 *     - Employee 11: m.employee_id = NULL (manager 6 not found) ✓
 *     Remaining: [1, 11]
 * 
 *   Result:
 *   +-------------+
 *   | employee_id |
 *   +-------------+
 *   | 1           |
 *   | 11          |
 *   +-------------+
 * 
 * Why Self-Join?
 * 
 *   - Need to check if manager exists in the same table
 *   - Self-join creates two instances: e (employees) and m (managers)
 *   - LEFT JOIN matches employees with their managers
 *   - If manager doesn't exist, m.employee_id will be NULL
 * 
 * Why LEFT JOIN (not INNER JOIN)?
 * 
 *   - LEFT JOIN keeps all employees (e), even if manager not found
 *   - INNER JOIN would only keep employees whose managers exist
 *   - We need employees whose managers DON'T exist
 *   - LEFT JOIN allows us to identify missing managers with NULL
 * 
 * Why e.manager_id IS NOT NULL?
 * 
 *   - Employees with NULL manager_id don't have a manager
 *   - They can't have a "manager that left" (they never had one)
 *   - Filter out employees without managers
 *   - Only check employees who had a manager
 * 
 * Why m.employee_id IS NULL?
 * 
 *   - LEFT JOIN returns NULL when no match is found
 *   - If manager exists: m.employee_id has a value
 *   - If manager doesn't exist: m.employee_id is NULL
 *   - m.employee_id IS NULL means manager left the company
 * 
 * Anti-Join Pattern:
 * 
 *   This is a classic "anti-join" pattern:
 *   - LEFT JOIN to include all rows from left table
 *   - WHERE right_table.key IS NULL to find rows with no match
 *   - Used to find "orphan" records (employees with missing managers)
 * 
 * Visual Example:
 * 
 *   Employees (e):          Employees (m - managers):
 *   +----+------+----+      +----+------+----+
 *   | id | mgr  | sal|      | id | mgr  | sal|
 *   +----+------+----+      +----+------+----+
 *   | 1  | 11   | 20K|      | 9  | NULL | 50K|  ← Manager 9 exists
 *   | 3  | 9    | 60K|      |    |      |    |  ← Manager 11 NOT FOUND
 *   | 11 | 6    | 28K|      |    |      |    |  ← Manager 6 NOT FOUND
 *   +----+------+----+      +----+------+----+
 * 
 *   After LEFT JOIN:
 *   - Employee 1 (mgr=11): No match → m.id = NULL → Manager left ✓
 *   - Employee 3 (mgr=9): Matches manager 9 → m.id = 9 → Manager exists ✗
 *   - Employee 11 (mgr=6): No match → m.id = NULL → Manager left ✓
 * 
 *   After filters (salary < 30K, manager_id NOT NULL, m.id IS NULL):
 *   - Employee 1: salary=20K < 30K ✓, manager_id=11 NOT NULL ✓, m.id=NULL ✓ → Included
 *   - Employee 11: salary=28K < 30K ✓, manager_id=6 NOT NULL ✓, m.id=NULL ✓ → Included
 * 
 * Edge Cases:
 * 
 *   - Employee with NULL manager_id: Excluded (no manager to leave)
 *   - Employee with salary >= 30000: Excluded (salary too high)
 *   - Employee with existing manager: Excluded (manager still in company)
 *   - All managers exist: Returns empty result
 *   - All managers left: Returns all employees with salary < 30000 and manager_id NOT NULL
 * 
 * Alternative Approach: NOT EXISTS
 * 
 *   SELECT employee_id
 *   FROM Employees e
 *   WHERE salary < 30000
 *     AND manager_id IS NOT NULL
 *     AND NOT EXISTS (
 *         SELECT 1
 *         FROM Employees m
 *         WHERE m.employee_id = e.manager_id
 *     )
 *   ORDER BY employee_id;
 * 
 *   - Uses NOT EXISTS subquery instead of LEFT JOIN
 *   - Both approaches work, LEFT JOIN is more common
 *   - NOT EXISTS can be more efficient in some databases
 */

# Write your MySQL query statement below

SELECT e.employee_id
FROM Employees e
LEFT JOIN Employees m
    ON e.manager_id = m.employee_id
WHERE e.salary < 30000
  AND e.manager_id IS NOT NULL
  AND m.employee_id IS NULL
ORDER BY e.employee_id;

