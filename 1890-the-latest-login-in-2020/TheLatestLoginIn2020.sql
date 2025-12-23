/*
 * LeetCode 1890: The Latest Login in 2020
 *
 * Problem:
 * Write a solution to report the latest login for all users in the year 2020.
 * Do not include the users who did not login in 2020.
 *
 * Return the result table in any order.
 *
 * Table: Logins
 * +----------------+----------+
 * | Column Name    | Type     |
 * +----------------+----------+
 * | user_id        | int      |
 * | time_stamp     | datetime |
 * +----------------+----------+
 * (user_id, time_stamp) is the primary key for this table.
 * Each row contains information about the login time for the user with ID user_id.
 *
 * Approach: Filter, Group, and Aggregate
 *
 * Key Insight:
 * - Need to find the latest (maximum) login time for each user
 * - Only consider logins from year 2020
 * - Use GROUP BY to aggregate per user
 * - Use MAX() to find the latest timestamp
 *
 * Algorithm:
 * 1. Filter logins WHERE YEAR(time_stamp) = 2020
 * 2. GROUP BY user_id to aggregate per user
 * 3. SELECT MAX(time_stamp) to get the latest login for each user
 * 4. Return user_id and last_stamp
 *
 * Why MAX() for Latest Time?
 * - MAX() on datetime/timestamp returns the most recent (latest) value
 * - Since timestamps are comparable, MAX finds the latest login
 * - Alternative: ORDER BY time_stamp DESC LIMIT 1 per group (less efficient)
 *
 * Why YEAR() Function?
 * - YEAR(time_stamp) extracts the year from a datetime
 * - Filters to only include logins from 2020
 * - Alternative: time_stamp >= '2020-01-01' AND time_stamp < '2021-01-01'
 *
 * Example:
 *
 *   Logins:
 *   +---------+---------------------+
 *   | user_id | time_stamp          |
 *   +---------+---------------------+
 *   | 6       | 2020-06-30 15:06:07 |
 *   | 6       | 2021-04-21 14:06:06 |
 *   | 6       | 2019-03-07 00:18:15 |
 *   | 8       | 2020-02-01 05:10:53 |
 *   | 8       | 2020-12-30 00:46:50 |
 *   | 2       | 2020-01-16 02:49:50 |
 *   | 2       | 2019-12-14 21:08:00 |
 *   | 14      | 2019-01-01 00:00:00 |
 *   | 14      | 2021-01-01 00:00:00 |
 *   +---------+---------------------+
 *
 *   After WHERE YEAR(time_stamp) = 2020:
 *   +---------+---------------------+
 *   | user_id | time_stamp          |
 *   +---------+---------------------+
 *   | 6       | 2020-06-30 15:06:07 |
 *   | 8       | 2020-02-01 05:10:53 |
 *   | 8       | 2020-12-30 00:46:50 |
 *   | 2       | 2020-01-16 02:49:50 |
 *   +---------+---------------------+
 *
 *   After GROUP BY user_id and MAX(time_stamp):
 *   - User 6: MAX(2020-06-30 15:06:07) = 2020-06-30 15:06:07
 *   - User 8: MAX(2020-02-01 05:10:53, 2020-12-30 00:46:50) = 2020-12-30 00:46:50
 *   - User 2: MAX(2020-01-16 02:49:50) = 2020-01-16 02:49:50
 *   - User 14: No logins in 2020 → excluded
 *
 *   Result:
 *   +---------+---------------------+
 *   | user_id | last_stamp          |
 *   +---------+---------------------+
 *   | 2       | 2020-01-16 02:49:50 |
 *   | 6       | 2020-06-30 15:06:07 |
 *   | 8       | 2020-12-30 00:46:50 |
 *   +---------+---------------------+
 *
 * Important Notes:
 * - YEAR() function extracts year from datetime
 * - MAX() on datetime returns the latest (most recent) timestamp
 * - GROUP BY ensures we get one result per user
 * - Users without logins in 2020 are automatically excluded by WHERE clause
 * - Result shows only users who logged in at least once in 2020
 */

# Write your MySQL query statement below

SELECT 
    user_id, 
    MAX(time_stamp) AS last_stamp
FROM Logins
WHERE YEAR(time_stamp) = 2020
GROUP BY user_id;

