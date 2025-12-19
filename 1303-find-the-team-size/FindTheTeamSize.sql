/*
 * LeetCode 1303: Find the Team Size
 * 
 * Problem:
 * Write a solution to find the team size of each employee.
 * Return the result in any order.
 * 
 * Table: Employee
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | employee_id   | int     |  (Primary Key)
 * | team_id       | int     |
 * +---------------+---------+
 * 
 * Approach: Subquery with JOIN
 * 
 * Key Insight:
 * - First calculate team sizes (GROUP BY team_id)
 * - Then join back to get team size for each employee
 * - Each employee gets their team's total count
 * 
 * Algorithm:
 * 1. Subquery: COUNT employees per team_id
 * 2. JOIN: Match each employee with their team's size
 * 3. SELECT: Return employee_id and team_size
 * 
 * Example:
 * 
 *   Employee:
 *   +-------------+---------+
 *   | employee_id | team_id |
 *   +-------------+---------+
 *   | 1           | 8       |
 *   | 2           | 8       |
 *   | 3           | 8       |
 *   | 4           | 7       |
 *   | 5           | 9       |
 *   | 6           | 9       |
 *   +-------------+---------+
 * 
 *   Step 1 - Subquery (team sizes):
 *   +---------+-----------+
 *   | team_id | team_size |
 *   +---------+-----------+
 *   | 8       | 3         |
 *   | 7       | 1         |
 *   | 9       | 2         |
 *   +---------+-----------+
 * 
 *   Step 2 - JOIN with original table:
 *   +-------------+-----------+
 *   | employee_id | team_size |
 *   +-------------+-----------+
 *   | 1           | 3         |  ← team 8
 *   | 2           | 3         |  ← team 8
 *   | 3           | 3         |  ← team 8
 *   | 4           | 1         |  ← team 7
 *   | 5           | 2         |  ← team 9
 *   | 6           | 2         |  ← team 9
 *   +-------------+-----------+
 * 
 * Visual:
 * 
 *   Team 8: [Emp 1, Emp 2, Emp 3] → size = 3
 *   Team 7: [Emp 4]               → size = 1
 *   Team 9: [Emp 5, Emp 6]        → size = 2
 * 
 *   Each employee gets their team's size:
 *   Emp 1 → Team 8 → 3
 *   Emp 2 → Team 8 → 3
 *   Emp 3 → Team 8 → 3
 *   Emp 4 → Team 7 → 1
 *   Emp 5 → Team 9 → 2
 *   Emp 6 → Team 9 → 2
 * 
 * Alternative Approach 1: Window Function (MySQL 8.0+)
 * 
 *   SELECT
 *       employee_id,
 *       COUNT(*) OVER (PARTITION BY team_id) AS team_size
 *   FROM Employee;
 * 
 *   - PARTITION BY team_id: Groups by team
 *   - COUNT(*) OVER: Counts within each partition
 *   - No JOIN needed!
 * 
 * Alternative Approach 2: Correlated Subquery
 * 
 *   SELECT
 *       employee_id,
 *       (SELECT COUNT(*) FROM Employee e2 WHERE e2.team_id = e1.team_id) AS team_size
 *   FROM Employee e1;
 * 
 *   - Less efficient (runs subquery for each row)
 *   - But works in all SQL versions
 * 
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 * 
 * | Concept          | Purpose                                |
 * |------------------|----------------------------------------|
 * | Subquery         | Calculate team sizes first             |
 * | JOIN             | Map sizes back to each employee        |
 * | GROUP BY         | Aggregate employees by team            |
 * | COUNT(*)         | Count members in each team             |
 * | Window Function  | Alternative: COUNT(*) OVER (PARTITION) |
 */

SELECT
    e.employee_id,
    t.team_size
FROM Employee e
JOIN (
    SELECT
        team_id,
        COUNT(*) AS team_size
    FROM Employee
    GROUP BY team_id
) t
ON e.team_id = t.team_id;

