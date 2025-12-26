/*
 * LeetCode 2026: Low-Quality Problems
 *
 * Problem:
 * Write an SQL query to report the IDs of the low-quality problems.
 * A LeetCode problem is low-quality if the like percentage of the problem
 * (number of likes divided by the total number of votes) is strictly less than 60%.
 *
 * Return the result table ordered by problem_id in ascending order.
 *
 * Table: Problems
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | problem_id  | int  |
 * | likes       | int  |
 * | dislikes    | int  |
 * +-------------+------+
 * problem_id is the primary key for this table.
 * Each row of this table indicates the number of likes and dislikes of a LeetCode problem.
 *
 * Approach: Mathematical Inequality Comparison
 *
 * Key Insight:
 * - Need to find problems where like percentage < 60%
 * - Like percentage = likes / (likes + dislikes)
 * - Condition: likes / (likes + dislikes) < 0.6
 * - To avoid division by zero and floating point issues, use cross-multiplication
 * - Original condition: likes * 5 < (likes + dislikes) * 3
 *
 * Mathematical Derivation:
 *
 *   We want: likes / (likes + dislikes) < 0.6
 *
 *   Multiply both sides by (likes + dislikes):
 *   likes < 0.6 * (likes + dislikes)
 *
 *   Multiply both sides by 5 to eliminate decimal:
 *   5 * likes < 3 * (likes + dislikes)
 *
 *   Expand right side:
 *   5 * likes < 3 * likes + 3 * dislikes
 *
 *   Subtract 3 * likes from both sides:
 *   2 * likes < 3 * dislikes
 *
 *   This means: likes < 1.5 * dislikes
 *   Or equivalently: likes / (likes + dislikes) < 0.6
 *
 * Algorithm:
 * 1. Filter problems where likes * 5 < (likes + dislikes) * 3
 * 2. This is equivalent to like percentage < 60%
 * 3. ORDER BY problem_id
 *
 * Why Use Cross-Multiplication Instead of Division?
 *
 *   Division approach: likes / (likes + dislikes) < 0.6
 *   - Risk of division by zero if likes + dislikes = 0
 *   - Floating point precision issues
 *   - Requires handling NULL cases
 *
 *   Cross-multiplication: likes * 5 < (likes + dislikes) * 3
 *   - No division, so no division by zero risk
 *   - Integer arithmetic (more precise)
 *   - Simpler and faster
 *
 * Example:
 *
 *   Problems:
 *   +------------+-------+----------+
 *   | problem_id | likes | dislikes |
 *   +------------+-------+----------+
 *   | 6          | 1290  | 425      |
 *   | 11         | 2677  | 8659     |
 *   | 1          | 9626  | 5621     |
 *   | 7          | 8562  | 5330     |
 *   | 13         | 2864  | 6074     |
 *   +------------+-------+----------+
 *
 *   Processing:
 *
 *   Problem 6:
 *   - likes = 1290, dislikes = 425
 *   - Total votes = 1290 + 425 = 1715
 *   - Like percentage = 1290 / 1715 ≈ 75.2% > 60% → High quality ✗
 *   - Check: 1290 * 5 = 6450, (1290 + 425) * 3 = 5145
 *   - 6450 < 5145? No → Not low quality
 *
 *   Problem 11:
 *   - likes = 2677, dislikes = 8659
 *   - Total votes = 2677 + 8659 = 11336
 *   - Like percentage = 2677 / 11336 ≈ 23.6% < 60% → Low quality! ✓
 *   - Check: 2677 * 5 = 13385, (2677 + 8659) * 3 = 34008
 *   - 13385 < 34008? Yes → Low quality
 *
 *   Problem 1:
 *   - likes = 9626, dislikes = 5621
 *   - Total votes = 9626 + 5621 = 15247
 *   - Like percentage = 9626 / 15247 ≈ 63.1% > 60% → High quality ✗
 *   - Check: 9626 * 5 = 48130, (9626 + 5621) * 3 = 45741
 *   - 48130 < 45741? No → Not low quality
 *
 *   Problem 7:
 *   - likes = 8562, dislikes = 5330
 *   - Total votes = 8562 + 5330 = 13892
 *   - Like percentage = 8562 / 13892 ≈ 61.6% > 60% → High quality ✗
 *   - Check: 8562 * 5 = 42810, (8562 + 5330) * 3 = 41676
 *   - 42810 < 41676? No → Not low quality
 *
 *   Problem 13:
 *   - likes = 2864, dislikes = 6074
 *   - Total votes = 2864 + 6074 = 8938
 *   - Like percentage = 2864 / 8938 ≈ 32.0% < 60% → Low quality! ✓
 *   - Check: 2864 * 5 = 14320, (2864 + 6074) * 3 = 26814
 *   - 14320 < 26814? Yes → Low quality
 *
 *   Result (ordered by problem_id):
 *   +------------+
 *   | problem_id |
 *   +------------+
 *   | 11         |  ← 23.6% likes
 *   | 13         |  ← 32.0% likes
 *   +------------+
 *
 * Alternative Approach: Using Division (with NULL handling)
 *
 *   SELECT problem_id
 *   FROM Problems
 *   WHERE (likes + dislikes) > 0
 *     AND (likes * 1.0 / (likes + dislikes)) < 0.6
 *   ORDER BY problem_id;
 *
 * Alternative Approach: Using Ratio Comparison
 *
 *   SELECT problem_id
 *   FROM Problems
 *   WHERE likes * 1.0 / NULLIF(likes + dislikes, 0) < 0.6
 *   ORDER BY problem_id;
 *
 * Edge Cases:
 * - If likes + dislikes = 0: Division would cause error, but cross-multiplication
 *   works fine (0 * 5 = 0, 0 * 3 = 0, 0 < 0 is false, so not selected)
 * - If likes = 0: 0 * 5 = 0, (0 + dislikes) * 3 = 3 * dislikes
 *   - If dislikes > 0: 0 < 3 * dislikes is true → Low quality ✓
 *   - If dislikes = 0: 0 < 0 is false → Not selected
 *
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 *
 * | Concept              | Purpose                                    |
 * |----------------------|--------------------------------------------|
 * | Cross-multiplication | Avoid division, handle edge cases safely   |
 * | Like percentage     | likes / (likes + dislikes)                |
 * | 60% threshold       | Problems with < 60% likes are low-quality  |
 * | Integer comparison   | More precise than floating point division  |
 *
 * Why 5 and 3?
 * - 0.6 = 3/5
 * - To compare likes/(likes+dislikes) < 3/5
 * - Cross-multiply: 5 * likes < 3 * (likes + dislikes)
 */

# Write your MySQL query statement below

SELECT problem_id
FROM Problems
WHERE likes * 5 < (likes + dislikes) * 3
ORDER BY problem_id;

