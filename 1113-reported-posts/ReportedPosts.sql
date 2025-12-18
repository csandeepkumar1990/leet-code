/*
 * LeetCode 1113: Reported Posts
 * 
 * Problem:
 * Write a solution to report the number of posts reported yesterday for each
 * report reason. Assume today is 2019-07-05.
 * 
 * Table: Actions
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | user_id       | int     |
 * | post_id       | int     |
 * | action_date   | date    |
 * | action        | enum    |  ('view', 'like', 'reaction', 'comment', 'report', 'share')
 * | extra         | varchar |  (report reason when action = 'report')
 * +---------------+---------+
 * No primary key. May have duplicates.
 * 
 * Key Points:
 * - "Yesterday" = 2019-07-04 (since today is 2019-07-05)
 * - Only count 'report' actions
 * - 'extra' column contains the report reason (spam, racism, etc.)
 * - Count DISTINCT posts (same post can be reported multiple times)
 * 
 * Approach: Filter + GROUP BY Report Reason
 * 
 * Algorithm:
 * 1. Filter: action = 'report' AND action_date = '2019-07-04'
 * 2. Group by: extra (report_reason)
 * 3. Count: DISTINCT post_id (avoid counting same post multiple times)
 * 
 * Example:
 * 
 *   Actions:
 *   +---------+---------+-------------+--------+--------+
 *   | user_id | post_id | action_date | action | extra  |
 *   +---------+---------+-------------+--------+--------+
 *   | 1       | 1       | 2019-07-01  | view   | NULL   |
 *   | 1       | 1       | 2019-07-01  | like   | NULL   |
 *   | 1       | 1       | 2019-07-01  | share  | NULL   |
 *   | 2       | 4       | 2019-07-04  | view   | NULL   |  ← not a report
 *   | 2       | 4       | 2019-07-04  | report | spam   |  ✓ report on 07-04
 *   | 3       | 4       | 2019-07-04  | report | spam   |  ✓ same post, same reason
 *   | 4       | 3       | 2019-07-02  | report | spam   |  ← wrong date
 *   | 5       | 2       | 2019-07-04  | report | racism |  ✓ report on 07-04
 *   | 5       | 5       | 2019-07-04  | report | racism |  ✓ report on 07-04
 *   +---------+---------+-------------+--------+--------+
 * 
 *   Filtered (action='report' AND date='2019-07-04'):
 *   +---------+---------+--------+
 *   | user_id | post_id | extra  |
 *   +---------+---------+--------+
 *   | 2       | 4       | spam   |
 *   | 3       | 4       | spam   |  ← same post as above
 *   | 5       | 2       | racism |
 *   | 5       | 5       | racism |
 *   +---------+---------+--------+
 * 
 *   Group by extra (report_reason):
 *   - spam:   posts {4, 4} → DISTINCT = {4} → count = 1
 *   - racism: posts {2, 5} → DISTINCT = {2, 5} → count = 2
 * 
 *   Result:
 *   +---------------+--------------+
 *   | report_reason | report_count |
 *   +---------------+--------------+
 *   | spam          | 1            |
 *   | racism        | 2            |
 *   +---------------+--------------+
 * 
 * Why COUNT(DISTINCT post_id)?
 *   - Multiple users can report the same post for the same reason
 *   - We want unique posts per reason, not total reports
 *   - Post 4 was reported twice for spam → count as 1 post
 */

SELECT 
    extra AS report_reason,
    COUNT(DISTINCT post_id) AS report_count
FROM Actions
WHERE action = 'report'
  AND action_date = '2019-07-04'
GROUP BY extra;

