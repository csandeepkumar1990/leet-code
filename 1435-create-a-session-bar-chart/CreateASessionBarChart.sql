/*
 * LeetCode 1435: Create a Session Bar Chart
 * 
 * Problem:
 * Write a solution to report the count of sessions in each duration bin:
 * - [0-5>     : duration >= 0 and < 5 minutes
 * - [5-10>    : duration >= 5 and < 10 minutes
 * - [10-15>   : duration >= 10 and < 15 minutes
 * - 15 or more: duration >= 15 minutes
 * 
 * Table: Sessions
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | session_id    | int     |  (Primary Key)
 * | duration      | int     |  (in seconds)
 * +---------------+---------+
 * 
 * Approach: Virtual Bin Table + LEFT JOIN
 * 
 * Key Insight:
 * - Create a virtual table defining the bins and their ranges
 * - LEFT JOIN ensures all bins appear (even with 0 count)
 * - Duration is in SECONDS, so 5 min = 300 sec
 * 
 * Algorithm:
 * 1. Create bin definitions using UNION ALL
 * 2. LEFT JOIN with Sessions based on duration range
 * 3. GROUP BY bin and COUNT sessions
 * 4. ORDER BY min_d to maintain bin order
 * 
 * Bin Definitions (Virtual Table):
 * 
 *   +-------------+-------+-------+
 *   | bin         | min_d | max_d |
 *   +-------------+-------+-------+
 *   | [0-5>       | 0     | 300   |  (0 to 5 min)
 *   | [5-10>      | 300   | 600   |  (5 to 10 min)
 *   | [10-15>     | 600   | 900   |  (10 to 15 min)
 *   | 15 or more  | 900   | NULL  |  (15+ min, no upper bound)
 *   +-------------+-------+-------+
 * 
 * JOIN Condition Explained:
 * 
 *   CASE 1: max_d IS NOT NULL (bounded bins)
 *     s.duration >= b.min_d AND s.duration < b.max_d
 *     
 *     Example: [5-10> bin
 *     - Session 350 sec: 350 >= 300 AND 350 < 600 ✓
 *     - Session 600 sec: 600 >= 300 AND 600 < 600 ✗ (goes to next bin)
 * 
 *   CASE 2: max_d IS NULL (unbounded "15 or more" bin)
 *     s.duration >= b.min_d
 *     
 *     Example: 15+ bin
 *     - Session 950 sec: 950 >= 900 ✓
 *     - Session 1200 sec: 1200 >= 900 ✓
 * 
 * Why LEFT JOIN?
 * 
 *   If no sessions fall in [10-15> range:
 *   - INNER JOIN: [10-15> row would be missing
 *   - LEFT JOIN: [10-15> row appears with total = 0 ✓
 * 
 * Example:
 * 
 *   Sessions:
 *   +------------+----------+
 *   | session_id | duration |
 *   +------------+----------+
 *   | 1          | 120      |  → [0-5>     (2 min)
 *   | 2          | 350      |  → [5-10>    (5.8 min)
 *   | 3          | 400      |  → [5-10>    (6.7 min)
 *   | 4          | 950      |  → 15+       (15.8 min)
 *   | 5          | 1200     |  → 15+       (20 min)
 *   +------------+----------+
 * 
 *   Result:
 *   +-------------+-------+
 *   | bin         | total |
 *   +-------------+-------+
 *   | [0-5>       | 1     |
 *   | [5-10>      | 2     |
 *   | [10-15>     | 0     |  ← Still appears thanks to LEFT JOIN
 *   | 15 or more  | 2     |
 *   +-------------+-------+
 * 
 * Visual (Bar Chart):
 * 
 *   [0-5>      | █
 *   [5-10>     | ██
 *   [10-15>    |
 *   15 or more | ██
 * 
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 * 
 * | Concept      | Purpose                                    |
 * |--------------|--------------------------------------------|
 * | UNION ALL    | Creates virtual bin table with 4 rows      |
 * | LEFT JOIN    | Ensures all 4 bins appear (even if cnt=0)  |
 * | NULL max_d   | Handles unbounded "15 or more" case        |
 * | 5*60         | Converts minutes to seconds (5 min = 300s) |
 * | ORDER BY     | Keeps bins in correct ascending order      |
 * 
 * UNION ALL vs UNION:
 * - UNION ALL: Keeps all rows (faster, no duplicate check)
 * - UNION: Removes duplicates (slower)
 * - Here we use UNION ALL because each bin is unique
 * 
 * Why Create Virtual Table?
 * - No actual table exists for bins
 * - Creates structure on-the-fly
 * - Each SELECT returns one bin definition
 * - Combined into single result set for JOIN
 */

SELECT
    b.bin,
    COUNT(s.session_id) AS total
FROM (
    SELECT '[0-5>' AS bin, 0 AS min_d, 5*60 AS max_d
    UNION ALL
    SELECT '[5-10>', 5*60, 10*60
    UNION ALL
    SELECT '[10-15>', 10*60, 15*60
    UNION ALL
    SELECT '15 or more', 15*60, NULL
) b
LEFT JOIN Sessions s
ON (
    (b.max_d IS NOT NULL AND s.duration >= b.min_d AND s.duration < b.max_d)
    OR
    (b.max_d IS NULL AND s.duration >= b.min_d)
)
GROUP BY b.bin
ORDER BY b.min_d;

