/*
 * LeetCode 3051: Find Candidates for Data Scientist Position
 * 
 * Problem:
 * Write a solution to find the IDs of candidates who possess all of the
 * required skills for the Data Scientist position: Python, Tableau, and PostgreSQL.
 * Return the result table ordered by candidate_id in ascending order.
 * 
 * Table: Candidates
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | candidate_id| int     |
 * | skill       | varchar |
 * +-------------+---------+
 * (candidate_id, skill) is the primary key for this table.
 * Each row indicates that a candidate has a skill.
 * 
 * Approach: GROUP BY with HAVING and COUNT(DISTINCT)
 * 
 * Key Insight:
 * - Need to find candidates who have ALL three skills: Python, Tableau, PostgreSQL
 * - Filter rows to only include these three skills
 * - Group by candidate_id to aggregate skills per candidate
 * - Count distinct skills - must equal 3 to have all required skills
 * 
 * Formula:
 *   Filter: skill IN ('Python', 'Tableau', 'PostgreSQL')
 *   Group by: candidate_id
 *   Check: COUNT(DISTINCT skill) = 3
 * 
 * Algorithm:
 * 1. Filter rows with WHERE to keep only the three required skills
 * 2. GROUP BY candidate_id to aggregate skills per candidate
 * 3. Use HAVING COUNT(DISTINCT skill) = 3 to ensure all three skills are present
 * 4. ORDER BY candidate_id for ascending order
 * 
 * Example:
 * 
 *   Candidates:
 *   +--------------+------------+
 *   | candidate_id | skill      |
 *   +--------------+------------+
 *   | 123          | Python     |
 *   | 123          | Tableau    |
 *   | 123          | PostgreSQL |
 *   | 234          | Python     |
 *   | 234          | Tableau    |
 *   | 345          | Python     |
 *   | 345          | PostgreSQL |
 *   | 345          | Spark      |  ← not required
 *   | 456          | Java       |  ← not required
 *   | 456          | Python     |
 *   | 456          | Tableau    |
 *   +--------------+------------+
 * 
 *   Step 1 - Filter with WHERE:
 *   Keep only rows where skill IN ('Python', 'Tableau', 'PostgreSQL')
 *   Candidate 123: Python, Tableau, PostgreSQL  → 3 rows
 *   Candidate 234: Python, Tableau              → 2 rows
 *   Candidate 345: Python, PostgreSQL          → 2 rows
 *   Candidate 456: Python, Tableau             → 2 rows
 * 
 *   Step 2 - Group by candidate_id:
 *   Candidate 123: [Python, Tableau, PostgreSQL] → COUNT(DISTINCT skill) = 3 ✓
 *   Candidate 234: [Python, Tableau]             → COUNT(DISTINCT skill) = 2 ✗
 *   Candidate 345: [Python, PostgreSQL]          → COUNT(DISTINCT skill) = 2 ✗
 *   Candidate 456: [Python, Tableau]             → COUNT(DISTINCT skill) = 2 ✗
 * 
 *   Result:
 *   +--------------+
 *   | candidate_id |
 *   +--------------+
 *   | 123          |
 *   +--------------+
 * 
 * Why WHERE before GROUP BY?
 *   - WHERE filters rows BEFORE grouping
 *   - This removes irrelevant skills early, improving performance
 *   - Only candidates with at least one required skill are considered
 *   - Reduces the dataset size before aggregation
 * 
 * Why COUNT(DISTINCT skill) = 3?
 *   - DISTINCT ensures we count unique skills only
 *   - If a candidate has duplicate skills, they're counted once
 *   - = 3 ensures the candidate has exactly all three required skills
 *   - This is more reliable than checking each skill individually
 * 
 * Why not use AND conditions?
 *   - Alternative: HAVING SUM(skill = 'Python') > 0 
 *                  AND SUM(skill = 'Tableau') > 0
 *                  AND SUM(skill = 'PostgreSQL') > 0
 *   - COUNT(DISTINCT skill) = 3 is cleaner and more concise
 *   - Both approaches work, but COUNT(DISTINCT) is more elegant
 * 
 * Edge Cases:
 *   - Candidate with duplicate skills: COUNT(DISTINCT) handles this correctly
 *   - Candidate with extra skills: Still included if they have all three required
 *   - No candidates with all three: Returns empty result set
 */

# Write your MySQL query statement below

SELECT candidate_id
FROM Candidates
WHERE skill IN ('Python', 'Tableau', 'PostgreSQL')
GROUP BY candidate_id
HAVING COUNT(DISTINCT skill) = 3
ORDER BY candidate_id;

