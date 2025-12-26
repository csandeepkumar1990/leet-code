/*
 * LeetCode 2072: The Winner University
 *
 * Problem:
 * There is a competition between New York University and California University.
 * The competition is held between the same number of students from both universities.
 * The university that has more excellent students wins the competition.
 * If the two universities have the same number of excellent students, the competition ends in a draw.
 *
 * An excellent student is a student that scored 90% or more in the exam.
 *
 * Write an SQL query to report:
 * - "New York University" if New York University wins,
 * - "California University" if California University wins,
 * - "No Winner" if the competition ends in a draw.
 *
 * Table: NewYork
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | student_id  | int  |
 * | score       | int  |
 * +-------------+------+
 * student_id is the primary key for this table.
 * Each row contains information about a student from New York University and their score.
 *
 * Table: California
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | student_id  | int  |
 * | score       | int  |
 * +-------------+------+
 * student_id is the primary key for this table.
 * Each row contains information about a student from California University and their score.
 *
 * Approach: Subquery Count with CASE Statement
 *
 * Key Insight:
 * - Count excellent students (score >= 90) from each university
 * - Compare the two counts
 * - Use CASE statement to determine winner based on comparison
 * - Use implicit cross join (comma) to combine the two counts
 *
 * Algorithm:
 * 1. Count students with score >= 90 from NewYork table
 * 2. Count students with score >= 90 from California table
 * 3. Cross join the two counts (creates a single row with both counts)
 * 4. Use CASE to compare counts and return appropriate winner
 *
 * How Implicit Cross Join Works:
 *
 *   Subquery 1 (ny): Returns single row with count
 *   +-----+
 *   | cnt |
 *   +-----+
 *   |  3  |
 *   +-----+
 *
 *   Subquery 2 (ca): Returns single row with count
 *   +-----+
 *   | cnt |
 *   +-----+
 *   |  2  |
 *   +-----+
 *
 *   Cross Join (comma): Combines into one row
 *   +-----+-----+
 *   | ny  | ca  |
 *   +-----+-----+
 *   |  3  |  2  |
 *   +-----+-----+
 *
 *   CASE Evaluation:
 *   - ny.cnt (3) > ca.cnt (2)? Yes → 'New York University'
 *
 * Example:
 *
 *   NewYork:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 89    |
 *   | 2          | 92    |  ← Excellent (>= 90)
 *   | 3          | 95    |  ← Excellent (>= 90)
 *   | 4          | 88    |
 *   | 5          | 91    |  ← Excellent (>= 90)
 *   +------------+-------+
 *   Excellent count: 3
 *
 *   California:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 90    |  ← Excellent (>= 90)
 *   | 2          | 87    |
 *   | 3          | 93    |  ← Excellent (>= 90)
 *   | 4          | 85    |
 *   | 5          | 88    |
 *   +------------+-------+
 *   Excellent count: 2
 *
 *   Processing:
 *   - ny.cnt = 3 (students 2, 3, 5 from NewYork)
 *   - ca.cnt = 2 (students 1, 3 from California)
 *   - Comparison: 3 > 2 → New York University wins!
 *
 *   Result:
 *   +----------------------+
 *   | winner               |
 *   +----------------------+
 *   | New York University  |
 *   +----------------------+
 *
 * Example 2: Tie Scenario
 *
 *   NewYork:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 90    |  ← Excellent
 *   | 2          | 95    |  ← Excellent
 *   +------------+-------+
 *   Excellent count: 2
 *
 *   California:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 92    |  ← Excellent
 *   | 2          | 93    |  ← Excellent
 *   +------------+-------+
 *   Excellent count: 2
 *
 *   Processing:
 *   - ny.cnt = 2
 *   - ca.cnt = 2
 *   - Comparison: 2 > 2? No, 2 < 2? No → ELSE 'No Winner'
 *
 *   Result:
 *   +------------+
 *   | winner     |
 *   +------------+
 *   | No Winner  |
 *   +------------+
 *
 * Example 3: California Wins
 *
 *   NewYork:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 85    |
 *   | 2          | 88    |
 *   +------------+-------+
 *   Excellent count: 0
 *
 *   California:
 *   +------------+-------+
 *   | student_id | score |
 *   +------------+-------+
 *   | 1          | 90    |  ← Excellent
 *   | 2          | 95    |  ← Excellent
 *   | 3          | 92    |  ← Excellent
 *   +------------+-------+
 *   Excellent count: 3
 *
 *   Processing:
 *   - ny.cnt = 0
 *   - ca.cnt = 3
 *   - Comparison: 0 < 3 → California University wins!
 *
 *   Result:
 *   +----------------------+
 *   | winner               |
 *   +----------------------+
 *   | California University|
 *   +----------------------+
 *
 * Alternative Approach: Using CTEs (Common Table Expressions)
 *
 *   WITH ny AS (
 *       SELECT COUNT(*) AS cnt
 *       FROM NewYork
 *       WHERE score >= 90
 *   ),
 *   ca AS (
 *       SELECT COUNT(*) AS cnt
 *       FROM California
 *       WHERE score >= 90
 *   )
 *   SELECT
 *       CASE
 *           WHEN ny.cnt > ca.cnt THEN 'New York University'
 *           WHEN ny.cnt < ca.cnt THEN 'California University'
 *           ELSE 'No Winner'
 *       END AS winner
 *   FROM ny, ca;
 *
 * Alternative Approach: Using Explicit CROSS JOIN
 *
 *   SELECT
 *       CASE
 *           WHEN ny.cnt > ca.cnt THEN 'New York University'
 *           WHEN ny.cnt < ca.cnt THEN 'California University'
 *           ELSE 'No Winner'
 *       END AS winner
 *   FROM
 *       (SELECT COUNT(*) AS cnt FROM NewYork WHERE score >= 90) ny
 *   CROSS JOIN
 *       (SELECT COUNT(*) AS cnt FROM California WHERE score >= 90) ca;
 *
 * Why Use Subqueries in FROM Clause?
 * - Each subquery returns exactly one row (aggregate COUNT)
 * - Cross join of two single-row results = one row
 * - Allows easy comparison of the two counts
 * - Clean and readable syntax
 *
 * CASE Statement Logic:
 * - WHEN ny.cnt > ca.cnt: New York has more excellent students → NY wins
 * - WHEN ny.cnt < ca.cnt: California has more excellent students → CA wins
 * - ELSE: Counts are equal → No Winner (draw)
 *
 * Important Notes:
 * - score >= 90 means "90 or higher" (inclusive)
 * - COUNT(*) counts all rows, including those with NULL scores (if any)
 * - If a university has no excellent students, COUNT(*) returns 0
 * - The implicit cross join (comma) is equivalent to CROSS JOIN
 * - Both subqueries always return exactly one row, so cross join is safe
 *
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 *
 * | Concept          | Purpose                                    |
 * |------------------|--------------------------------------------|
 * | Subquery in FROM | Calculate count for each university        |
 * | Cross Join       | Combine two single-row results             |
 * | CASE Statement   | Conditional logic to determine winner      |
 * | COUNT(*)         | Count rows matching condition              |
 * | Score >= 90      | Filter for excellent students              |
 */

# Write your MySQL query statement below

SELECT
    CASE
        WHEN ny.cnt > ca.cnt THEN 'New York University'
        WHEN ny.cnt < ca.cnt THEN 'California University'
        ELSE 'No Winner'
    END AS winner
FROM
    (SELECT COUNT(*) AS cnt FROM NewYork WHERE score >= 90) ny,
    (SELECT COUNT(*) AS cnt FROM California WHERE score >= 90) ca;

