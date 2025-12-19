/*
 * LeetCode 1350: Students With Invalid Departments
 * 
 * Problem:
 * Find the id and name of all students who are enrolled in departments
 * that no longer exist. Return the result in any order.
 * 
 * Table: Departments
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |  (Primary Key)
 * | name          | varchar |
 * +---------------+---------+
 * 
 * Table: Students
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |  (Primary Key)
 * | name          | varchar |
 * | department_id | int     |
 * +---------------+---------+
 * 
 * Approach: LEFT JOIN + NULL Check
 * 
 * Key Insight:
 * - LEFT JOIN keeps all students, even if department doesn't exist
 * - If department doesn't exist, d.id will be NULL
 * - Filter for NULL to find "orphan" students
 * 
 * Algorithm:
 * 1. LEFT JOIN Students with Departments on department_id = id
 * 2. WHERE d.id IS NULL (department not found)
 * 3. SELECT student id and name
 * 
 * How LEFT JOIN Works Here:
 * 
 *   Students                    Departments
 *   +----+-------+--------+     +----+------+
 *   | id | name  | dept_id|     | id | name |
 *   +----+-------+--------+     +----+------+
 *   | 1  | Alice | 1      |     | 1  | CS   |
 *   | 2  | Bob   | 7      |     | 2  | Math |
 *   | 3  | Carol | 2      |     +----+------+
 *   +----+-------+--------+
 * 
 *   After LEFT JOIN:
 *   +----+-------+--------+------+
 *   | id | name  | dept_id| d.id |
 *   +----+-------+--------+------+
 *   | 1  | Alice | 1      | 1    |  ← dept exists
 *   | 2  | Bob   | 7      | NULL |  ← dept 7 NOT FOUND!
 *   | 3  | Carol | 2      | 2    |  ← dept exists
 *   +----+-------+--------+------+
 * 
 *   WHERE d.id IS NULL:
 *   +----+------+
 *   | id | name |
 *   +----+------+
 *   | 2  | Bob  |  ← Invalid department!
 *   +----+------+
 * 
 * Example:
 * 
 *   Departments:
 *   +----+-----------+
 *   | id | name      |
 *   +----+-----------+
 *   | 1  | Electrical|
 *   | 7  | Computer  |
 *   | 13 | Business  |
 *   +----+-----------+
 * 
 *   Students:
 *   +----+--------+---------------+
 *   | id | name   | department_id |
 *   +----+--------+---------------+
 *   | 23 | Alice  | 1             |  ← dept 1 exists ✓
 *   | 1  | Bob    | 7             |  ← dept 7 exists ✓
 *   | 5  | Janet  | 99            |  ← dept 99 NOT FOUND ✗
 *   | 2  | Steve  | 6             |  ← dept 6 NOT FOUND ✗
 *   +----+--------+---------------+
 * 
 *   Result:
 *   +----+-------+
 *   | id | name  |
 *   +----+-------+
 *   | 5  | Janet |
 *   | 2  | Steve |
 *   +----+-------+
 * 
 * Alternative Approach: NOT EXISTS
 * 
 *   SELECT id, name
 *   FROM Students s
 *   WHERE NOT EXISTS (
 *       SELECT 1 FROM Departments d
 *       WHERE d.id = s.department_id
 *   );
 * 
 * Alternative Approach: NOT IN
 * 
 *   SELECT id, name
 *   FROM Students
 *   WHERE department_id NOT IN (SELECT id FROM Departments);
 * 
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 * 
 * | Concept        | Purpose                                 |
 * |----------------|-----------------------------------------|
 * | LEFT JOIN      | Keep all students, match if dept exists |
 * | IS NULL        | Find rows where no match was found      |
 * | "Orphan" rows  | Students referencing non-existent dept  |
 * 
 * LEFT JOIN vs INNER JOIN:
 * - INNER JOIN: Only students WITH valid departments
 * - LEFT JOIN: ALL students (NULL if dept missing)
 */

SELECT s.id, s.name
FROM Students s
LEFT JOIN Departments d
    ON s.department_id = d.id
WHERE d.id IS NULL;

