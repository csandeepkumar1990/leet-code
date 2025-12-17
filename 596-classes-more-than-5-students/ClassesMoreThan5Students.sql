/*
 * LeetCode 596: Classes More Than 5 Students
 * 
 * Problem:
 * Find all classes that have at least 5 students.
 * 
 * Table: Courses
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | student     | varchar |
 * | class       | varchar |
 * +-------------+---------+
 * (student, class) is the primary key.
 * Each row indicates the name of a student and the class they are enrolled in.
 * 
 * Example:
 * Input:
 * +---------+----------+
 * | student | class    |
 * +---------+----------+
 * | A       | Math     |
 * | B       | English  |
 * | C       | Math     |
 * | D       | Biology  |
 * | E       | Math     |
 * | F       | Computer |
 * | G       | Math     |
 * | H       | Math     |
 * | I       | Math     |
 * +---------+----------+
 * 
 * Output:
 * +---------+
 * | class   |
 * +---------+
 * | Math    |
 * +---------+
 * 
 * Explanation:
 * - Math: 6 students (A, C, E, G, H, I) → 6 >= 5 ✅
 * - English: 1 student (B) → 1 < 5 ❌
 * - Biology: 1 student (D) → 1 < 5 ❌
 * - Computer: 1 student (F) → 1 < 5 ❌
 * 
 * Approach:
 * - GROUP BY class to aggregate students per class
 * - HAVING COUNT(student) >= 5 filters classes with at least 5 students
 */

# Write your MySQL query statement below

SELECT class
FROM Courses
GROUP BY class
HAVING COUNT(student) >= 5;

