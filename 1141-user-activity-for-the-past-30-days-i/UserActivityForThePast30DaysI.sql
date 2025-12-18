/*
 * LeetCode 1141: User Activity for the Past 30 Days I
 * 
 * Problem:
 * Write a solution to find the daily active user count for a period of 30 days
 * ending 2019-07-27 inclusively. A user was active on some day if they made
 * at least one activity on that day.
 * 
 * Table: Activity
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | user_id       | int     |
 * | session_id    | int     |
 * | activity_date | date    |
 * | activity_type | enum    |
 * +---------------+---------+
 * No primary key. May contain duplicates.
 * 
 * Date Range:
 * - End date: 2019-07-27 (inclusive)
 * - Start date: 2019-06-28 (30 days including both endpoints)
 * - DATE_SUB('2019-07-27', INTERVAL 29 DAY) = '2019-06-28'
 * 
 * Why INTERVAL 29 DAY (not 30)?
 *   2019-07-27 - 29 days = 2019-06-28
 *   From 06-28 to 07-27 = 30 days (inclusive on both ends)
 * 
 * Approach: GROUP BY Date with COUNT DISTINCT
 * 
 * Key Insight:
 * - Group by activity_date to get daily counts
 * - Use COUNT(DISTINCT user_id) because a user may have multiple activities per day
 * - Filter to 30-day window using BETWEEN
 * 
 * Example:
 * 
 *   Activity:
 *   +---------+------------+---------------+---------------+
 *   | user_id | session_id | activity_date | activity_type |
 *   +---------+------------+---------------+---------------+
 *   | 1       | 1          | 2019-07-20    | open_session  |
 *   | 1       | 1          | 2019-07-20    | scroll_down   |  (same user, same day)
 *   | 1       | 1          | 2019-07-20    | end_session   |  (same user, same day)
 *   | 2       | 4          | 2019-07-20    | open_session  |
 *   | 2       | 4          | 2019-07-21    | send_message  |
 *   | 2       | 4          | 2019-07-21    | end_session   |
 *   | 3       | 2          | 2019-07-21    | open_session  |
 *   | 3       | 2          | 2019-07-21    | send_message  |
 *   +---------+------------+---------------+---------------+
 * 
 *   Group by activity_date:
 * 
 *   2019-07-20:
 *     - user 1: 3 activities → count as 1 (DISTINCT)
 *     - user 2: 1 activity  → count as 1
 *     Total: 2 active users
 * 
 *   2019-07-21:
 *     - user 2: 2 activities → count as 1 (DISTINCT)
 *     - user 3: 2 activities → count as 1 (DISTINCT)
 *     Total: 2 active users
 * 
 *   Result:
 *   +------------+--------------+
 *   | day        | active_users |
 *   +------------+--------------+
 *   | 2019-07-20 | 2            |
 *   | 2019-07-21 | 2            |
 *   +------------+--------------+
 * 
 * Why COUNT(DISTINCT user_id)?
 * 
 *   Without DISTINCT (WRONG):
 *   2019-07-20: COUNT = 4 (counts each row)
 * 
 *   With DISTINCT (CORRECT):
 *   2019-07-20: COUNT = 2 (counts unique users)
 * 
 * Note: Days with no activity won't appear in the result (which is expected)
 */

SELECT 
    activity_date AS day,
    COUNT(DISTINCT user_id) AS active_users
FROM Activity
WHERE activity_date BETWEEN DATE_SUB('2019-07-27', INTERVAL 29 DAY) AND '2019-07-27'
GROUP BY activity_date;

