/*
 * LeetCode 1142: User Activity for the Past 30 Days II
 * 
 * Problem:
 * Write a solution to find the average number of sessions per user for a
 * period of 30 days ending 2019-07-27 inclusively, rounded to 2 decimal places.
 * Sessions counted only if they have at least one activity in that period.
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
 * activity_type: 'open_session', 'end_session', 'scroll_down', 'send_message'
 * 
 * Date Range:
 * - End date: 2019-07-27 (inclusive)
 * - Start date: 2019-06-28 (30 days including both endpoints)
 * - DATE_SUB('2019-07-27', INTERVAL 29 DAY) = '2019-06-28'
 * 
 * Approach: CTE + Nested Aggregation
 * 
 * Algorithm:
 * 1. CTE: Get distinct (user_id, session_id) pairs within date range
 * 2. Inner query: Count sessions per user
 * 3. Outer query: Calculate average sessions across all users
 * 4. IFNULL: Handle edge case when no users exist (return 0.00, not NULL)
 * 
 * Key Insight:
 * - Must use IFNULL because AVG() returns NULL when no rows exist
 * - LeetCode expects 0.00, not NULL for empty results
 * 
 * Example:
 * 
 *   Activity (within date range):
 *   +---------+------------+---------------+
 *   | user_id | session_id | activity_date |
 *   +---------+------------+---------------+
 *   | 1       | 1          | 2019-07-20    |
 *   | 1       | 1          | 2019-07-20    |  (same session)
 *   | 1       | 2          | 2019-07-21    |
 *   | 2       | 3          | 2019-07-21    |
 *   | 2       | 4          | 2019-07-21    |
 *   | 2       | 4          | 2019-07-22    |  (same session)
 *   +---------+------------+---------------+
 * 
 *   Step 1 - Distinct (user_id, session_id):
 *   +---------+------------+
 *   | user_id | session_id |
 *   +---------+------------+
 *   | 1       | 1          |
 *   | 1       | 2          |
 *   | 2       | 3          |
 *   | 2       | 4          |
 *   +---------+------------+
 * 
 *   Step 2 - Sessions per user:
 *   +---------+---------------+
 *   | user_id | session_count |
 *   +---------+---------------+
 *   | 1       | 2             |
 *   | 2       | 2             |
 *   +---------+---------------+
 * 
 *   Step 3 - Average:
 *   AVG(2, 2) = 4 / 2 = 2.00
 * 
 *   Result:
 *   +---------------------------+
 *   | average_sessions_per_user |
 *   +---------------------------+
 *   | 2.00                      |
 *   +---------------------------+
 * 
 * Edge Case (No Users):
 *   - If no activity in date range, AVG returns NULL
 *   - IFNULL(NULL, 0.00) = 0.00
 */

WITH user_sessions AS (
    SELECT 
        user_id,
        session_id
    FROM Activity
    WHERE activity_date BETWEEN DATE_SUB('2019-07-27', INTERVAL 29 DAY) AND '2019-07-27'
    GROUP BY user_id, session_id
)
SELECT 
    IFNULL(ROUND(AVG(session_count), 2), 0.00) AS average_sessions_per_user
FROM (
    SELECT 
        user_id,
        COUNT(session_id) AS session_count
    FROM user_sessions
    GROUP BY user_id
) AS t;

