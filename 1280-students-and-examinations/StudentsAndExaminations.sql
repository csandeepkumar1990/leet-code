/*
 * LeetCode 1280: Students and Examinations
 * 
 * Problem:
 * Write a solution to find the number of times each student attended each exam.
 * 
 * Return the result table ordered by student_id and subject_name.
 * 
 * Note: The result should show all students and all subjects, even if a student
 * didn't attend an exam for a particular subject.
 * 
 * Table: Students
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | student_id    | int     |
 * | student_name  | varchar |
 * +---------------+---------+
 * student_id is the primary key for this table.
 * Each row of this table contains the ID and the name of one student.
 * 
 * Table: Subjects
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | subject_name | varchar |
 * +--------------+---------+
 * subject_name is the primary key for this table.
 * Each row of this table contains the name of one subject.
 * 
 * Table: Examinations
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | student_id   | int     |
 * | subject_name | varchar |
 * +--------------+---------+
 * There is no primary key for this table. It may contain duplicates.
 * Each row indicates that a student with ID student_id attended the exam of subject_name.
 * 
 * Approach: CROSS JOIN + LEFT JOIN + GROUP BY
 * 
 * Key Insight:
 * - Need all combinations of students and subjects (Cartesian product)
 * - Use CROSS JOIN to generate all student-subject pairs
 * - LEFT JOIN with Examinations to count exams per pair
 * - Use COUNT() to count exams, which returns 0 for pairs with no exams
 * 
 * Algorithm:
 * 1. CROSS JOIN Students with Subjects to create all student-subject combinations
 * 2. LEFT JOIN with Examinations on both student_id and subject_name
 * 3. GROUP BY student_id, student_name, subject_name
 * 4. COUNT(e.subject_name) to count exams (returns 0 if no match)
 * 5. ORDER BY student_id, subject_name
 * 
 * Time Complexity: O(s * t + e), where:
 *   - s = number of students
 *   - t = number of subjects
 *   - e = number of examination records
 *   CROSS JOIN creates s × t rows, then LEFT JOIN adds examination data.
 * 
 * Space Complexity: O(s * t), for the result set containing all combinations.
 * 
 * Example:
 * 
 *   Students:
 *   +------------+--------------+
 *   | student_id | student_name |
 *   +------------+--------------+
 *   | 1          | Alice        |
 *   | 2          | Bob          |
 *   | 13         | John         |
 *   | 6          | Alex         |
 *   +------------+--------------+
 * 
 *   Subjects:
 *   +--------------+
 *   | subject_name |
 *   +--------------+
 *   | Math         |
 *   | Physics      |
 *   | Programming  |
 *   +--------------+
 * 
 *   Examinations:
 *   +------------+--------------+
 *   | student_id | subject_name |
 *   +------------+--------------+
 *   | 1          | Math         |
 *   | 1          | Physics      |
 *   | 1          | Programming  |
 *   | 2          | Programming  |
 *   | 1          | Physics      |
 *   | 1          | Math         |
 *   | 13         | Math         |
 *   | 13         | Programming  |
 *   | 13         | Physics      |
 *   | 2          | Math         |
 *   | 1          | Math         |
 *   +------------+--------------+
 * 
 *   Step 1 - CROSS JOIN (Students × Subjects):
 *   Creates all 4 students × 3 subjects = 12 combinations:
 *   (1, Alice, Math), (1, Alice, Physics), (1, Alice, Programming),
 *   (2, Bob, Math), (2, Bob, Physics), (2, Bob, Programming),
 *   (13, John, Math), (13, John, Physics), (13, John, Programming),
 *   (6, Alex, Math), (6, Alex, Physics), (6, Alex, Programming)
 * 
 *   Step 2 - LEFT JOIN with Examinations:
 *   Matches examinations where both student_id and subject_name match:
 *   - (1, Alice, Math): 3 matches (count = 3)
 *   - (1, Alice, Physics): 2 matches (count = 2)
 *   - (1, Alice, Programming): 1 match (count = 1)
 *   - (2, Bob, Math): 1 match (count = 1)
 *   - (2, Bob, Physics): 0 matches (count = 0)
 *   - (2, Bob, Programming): 1 match (count = 1)
 *   - (13, John, Math): 1 match (count = 1)
 *   - (13, John, Physics): 1 match (count = 1)
 *   - (13, John, Programming): 1 match (count = 1)
 *   - (6, Alex, Math): 0 matches (count = 0)
 *   - (6, Alex, Physics): 0 matches (count = 0)
 *   - (6, Alex, Programming): 0 matches (count = 0)
 * 
 *   Step 3 - GROUP BY and COUNT:
 *   Groups by (student_id, student_name, subject_name) and counts exams
 * 
 *   Result:
 *   +------------+--------------+--------------+------------------+
 *   | student_id | student_name | subject_name | attended_exams   |
 *   +------------+--------------+--------------+------------------+
 *   | 1          | Alice        | Math         | 3                |
 *   | 1          | Alice        | Physics      | 2                |
 *   | 1          | Alice        | Programming  | 1                |
 *   | 2          | Bob          | Math         | 1                |
 *   | 2          | Bob          | Physics      | 0                |
 *   | 2          | Bob          | Programming  | 1                |
 *   | 6          | Alex         | Math         | 0                |
 *   | 6          | Alex         | Physics      | 0                |
 *   | 6          | Alex         | Programming  | 0                |
 *   | 13         | John         | Math         | 1                |
 *   | 13         | John         | Physics      | 1                |
 *   | 13         | John         | Programming  | 1                |
 *   +------------+--------------+--------------+------------------+
 * 
 * Why CROSS JOIN?
 * 
 *   - Need ALL combinations of students and subjects
 *   - CROSS JOIN creates Cartesian product: every student × every subject
 *   - Ensures we have a row for each student-subject pair, even if no exam exists
 *   - Example: 4 students × 3 subjects = 12 combinations
 * 
 * Why LEFT JOIN (not INNER JOIN)?
 * 
 *   - LEFT JOIN keeps all rows from CROSS JOIN (all student-subject pairs)
 *   - INNER JOIN would only keep pairs that have exams
 *   - We need pairs with 0 exams too (student didn't attend that exam)
 *   - LEFT JOIN ensures students who didn't attend exams still appear
 * 
 * Why JOIN on Both student_id AND subject_name?
 * 
 *   - Need to match both conditions for accurate counting
 *   - student_id alone: would match wrong subjects
 *   - subject_name alone: would match wrong students
 *   - Both together: ensures correct student-subject pair
 * 
 * Why COUNT(e.subject_name) instead of COUNT(*)?
 * 
 *   - COUNT(*) counts all rows, including NULLs from LEFT JOIN
 *   - COUNT(e.subject_name) counts only non-NULL values
 *   - When no exam exists, e.subject_name is NULL, so COUNT returns 0
 *   - This gives us the correct count of exams (0 for no exams)
 * 
 * COUNT(*) vs COUNT(column):
 * 
 *   COUNT(*):
 *   - Counts all rows in the group
 *   - Includes NULL values
 *   - For LEFT JOIN: always returns at least 1 (the CROSS JOIN row)
 *   - Would give wrong result (always >= 1)
 * 
 *   COUNT(e.subject_name):
 *   - Counts non-NULL values only
 *   - For LEFT JOIN: returns 0 if no match, actual count if matches exist
 *   - Gives correct result (0 for no exams, actual count otherwise)
 * 
 * Why GROUP BY All Three Columns?
 * 
 *   - GROUP BY student_id, student_name, subject_name
 *   - Groups each unique student-subject combination
 *   - Allows counting exams per combination
 *   - student_name is included for SELECT clause (functional dependency)
 * 
 * Query Execution Order:
 * 
 *   1. FROM Students s
 *   2. CROSS JOIN Subjects sub (creates all combinations)
 *   3. LEFT JOIN Examinations e (adds exam data)
 *   4. GROUP BY student_id, student_name, subject_name
 *   5. SELECT with COUNT(e.subject_name)
 *   6. ORDER BY student_id, subject_name
 * 
 * Alternative Approach: Using Subquery
 * 
 *   SELECT
 *       s.student_id,
 *       s.student_name,
 *       sub.subject_name,
 *       (SELECT COUNT(*)
 *        FROM Examinations e
 *        WHERE e.student_id = s.student_id
 *        AND e.subject_name = sub.subject_name) AS attended_exams
 *   FROM Students s
 *   CROSS JOIN Subjects sub
 *   ORDER BY s.student_id, sub.subject_name;
 * 
 *   - Uses correlated subquery instead of LEFT JOIN
 *   - More readable but potentially less efficient
 *   - Both approaches produce same result
 * 
 * Edge Cases:
 * 
 *   - Student with no exams: All subjects show 0 exams
 *   - Subject with no students: All students show 0 exams for that subject
 *   - Student attended same exam multiple times: COUNT reflects actual count
 *   - All students attended all exams: All counts >= 1
 *   - Single student, single subject: One row with count
 * 
 * Performance Considerations:
 * 
 *   - CROSS JOIN can create large result sets (s × t rows)
 *   - For large datasets, consider if all combinations are really needed
 *   - LEFT JOIN is efficient with proper indexes
 *   - GROUP BY requires sorting or hashing
 */

# Write your MySQL query statement below

SELECT
    s.student_id,
    s.student_name,
    sub.subject_name,
    COUNT(e.subject_name) AS attended_exams
FROM Students s
CROSS JOIN Subjects sub
LEFT JOIN Examinations e
    ON s.student_id = e.student_id
    AND sub.subject_name = e.subject_name
GROUP BY
    s.student_id,
    s.student_name,
    sub.subject_name
ORDER BY
    s.student_id,
    sub.subject_name;

