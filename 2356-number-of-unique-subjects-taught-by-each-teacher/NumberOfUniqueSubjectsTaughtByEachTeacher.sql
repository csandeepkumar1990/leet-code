/*
 * LeetCode 2356: Number of Unique Subjects Taught by Each Teacher
 * 
 * Problem:
 * Write a solution to calculate the number of unique subjects each teacher teaches in the university.
 * Return the result table in any order.
 * 
 * Table: Teacher
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | teacher_id  | int  |
 * | subject_id  | int  |
 * | dept_id     | int  |
 * +-------------+------+
 * (teacher_id, subject_id, dept_id) is the primary key for this table.
 * Each row indicates that the teacher with teacher_id teaches the subject subject_id
 * in the department dept_id.
 * 
 * Approach: GROUP BY with COUNT(DISTINCT)
 * 
 * Key Insight:
 * - A teacher may teach the same subject in multiple departments
 * - Need to count unique subjects per teacher (not total rows)
 * - Use COUNT(DISTINCT subject_id) to count unique subjects only
 * - GROUP BY teacher_id to aggregate per teacher
 * 
 * Formula:
 *   For each teacher:
 *   cnt = COUNT(DISTINCT subject_id)
 * 
 * Algorithm:
 * 1. GROUP BY teacher_id to aggregate subjects per teacher
 * 2. Use COUNT(DISTINCT subject_id) to count unique subjects
 * 3. DISTINCT ensures each subject is counted only once per teacher
 * 
 * Example:
 * 
 *   Teacher:
 *   +------------+------------+---------+
 *   | teacher_id | subject_id | dept_id |
 *   +------------+------------+---------+
 *   | 1          | 2          | 3       |
 *   | 1          | 2          | 4       |  ← Same subject, different dept
 *   | 1          | 3          | 3       |
 *   | 2          | 1          | 1       |
 *   | 2          | 2          | 1       |
 *   | 2          | 3          | 1       |
 *   | 2          | 4          | 1       |
 *   +------------+------------+---------+
 * 
 *   Step 1 - Group by teacher_id:
 *   Teacher 1: [subject_id: 2, 2, 3]
 *   Teacher 2: [subject_id: 1, 2, 3, 4]
 * 
 *   Step 2 - Count DISTINCT subject_id:
 *   Teacher 1: COUNT(DISTINCT [2, 2, 3]) = COUNT([2, 3]) = 2
 *              - Subject 2 appears twice but counted once
 *              - Subject 3 counted once
 *              - Total: 2 unique subjects
 * 
 *   Teacher 2: COUNT(DISTINCT [1, 2, 3, 4]) = COUNT([1, 2, 3, 4]) = 4
 *              - All subjects are unique
 *              - Total: 4 unique subjects
 * 
 *   Result:
 *   +------------+-----+
 *   | teacher_id | cnt |
 *   +------------+-----+
 *   | 1          | 2   |
 *   | 2          | 4   |
 *   +------------+-----+
 * 
 * Why COUNT(DISTINCT subject_id)?
 *   - A teacher may teach the same subject in multiple departments
 *   - Without DISTINCT: COUNT(subject_id) would count duplicates
 *   - With DISTINCT: Each unique subject is counted only once
 *   - Example: Teacher 1 teaches subject 2 in dept 3 and dept 4
 *             → Should count as 1 subject, not 2
 * 
 * Why not COUNT(*)?
 *   - COUNT(*) counts all rows (including duplicates)
 *   - Teacher 1 has 3 rows but only 2 unique subjects
 *   - COUNT(*) would return 3 (wrong)
 *   - COUNT(DISTINCT subject_id) returns 2 (correct)
 * 
 * Why GROUP BY teacher_id?
 *   - Aggregates subjects per teacher
 *   - Without GROUP BY: Would get one row with total count
 *   - With GROUP BY: One row per teacher with their subject count
 * 
 * DISTINCT in COUNT:
 *   - COUNT(DISTINCT column) counts unique values only
 *   - Removes duplicates before counting
 *   - Essential when same subject appears multiple times
 * 
 * Edge Cases:
 *   - Teacher with no subjects: Not in result (no rows to group)
 *   - Teacher with one subject in multiple depts: Counts as 1
 *   - Teacher with all unique subjects: COUNT(DISTINCT) = COUNT(*)
 *   - Multiple teachers: Each gets their own count
 * 
 * Alternative Approaches:
 *   - Using subquery: SELECT teacher_id, (SELECT COUNT(DISTINCT subject_id) ...)
 *   - Using CTE with DISTINCT first, then COUNT
 *   - Both work but GROUP BY is more efficient
 */

# Write your MySQL query statement below

SELECT teacher_id,
       COUNT(DISTINCT subject_id) AS cnt
FROM Teacher
GROUP BY teacher_id;

