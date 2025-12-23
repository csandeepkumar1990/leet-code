/*
 * LeetCode 1809: Ad-Free Sessions
 *
 * Problem:
 * Write a solution to report all the sessions that did not get shown any ads.
 *
 * Return the result table in any order.
 *
 * Table: Playback
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | session_id  | int  |
 * | customer_id | int  |
 * | start_time  | int  |
 * | end_time    | int  |
 * +-------------+------+
 * session_id is the column with unique values for this table.
 * customer_id is the ID of the customer watching this session.
 * The session runs during the inclusive interval [start_time, end_time].
 * It is guaranteed that start_time <= end_time and that two sessions for the
 * same customer do not intersect.
 *
 * Table: Ads
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | ad_id       | int  |
 * | customer_id | int  |
 * | timestamp   | int  |
 * +-------------+------+
 * ad_id is the column with unique values for this table.
 * customer_id is the ID of the customer viewing this ad.
 * timestamp is the moment of time at which the ad was shown.
 *
 * Approach: LEFT JOIN with Time Range Check and NULL Filter
 *
 * Key Insight:
 * - A session is ad-free if NO ad was shown during its time range
 * - Need to check if any ad's timestamp falls within [start_time, end_time]
 * - Use LEFT JOIN to find sessions with matching ads
 * - Filter for NULL ad_id to get sessions without any ads
 *
 * Algorithm:
 * 1. LEFT JOIN Playback with Ads on:
 *    a. customer_id matches (same customer)
 *    b. ad timestamp is BETWEEN start_time AND end_time (ad shown during session)
 * 2. WHERE ad_id IS NULL to filter sessions with no matching ads
 * 3. Return session_id
 *
 * Why LEFT JOIN?
 * - LEFT JOIN keeps all rows from Playback (left table)
 * - If no matching ad is found, ad columns will be NULL
 * - This allows us to identify ad-free sessions
 *
 * Why Check Both customer_id AND timestamp?
 * - customer_id: Ensures we only match ads shown to the same customer
 * - timestamp BETWEEN: Ensures the ad was shown during the session
 * - Both conditions must be true for an ad to be considered "shown during session"
 *
 * Example:
 *
 *   Playback:
 *   +------------+-------------+------------+----------+
 *   | session_id | customer_id | start_time | end_time |
 *   +------------+-------------+------------+----------+
 *   | 1          | 1           | 1          | 5        |
 *   | 2          | 1           | 15         | 17        |
 *   | 3          | 2           | 10         | 12        |
 *   | 4          | 2           | 17         | 28        |
 *   +------------+-------------+------------+----------+
 *
 *   Ads:
 *   +--------+-------------+-----------+
 *   | ad_id  | customer_id | timestamp |
 *   +--------+-------------+-----------+
 *   | 1      | 1           | 3         |  ← Shown during session 1
 *   | 2      | 1           | 16        |  ← Shown during session 2
 *   | 3      | 2           | 11        |  ← Shown during session 3
 *   | 4      | 2           | 20        |  ← Shown during session 4
 *   | 5      | 2           | 30        |  ← Not during any session
 *   +--------+-------------+-----------+
 *
 *   After LEFT JOIN:
 *   +------------+-------------+------------+----------+--------+-------------+-----------+
 *   | session_id | customer_id | start_time | end_time | ad_id  | customer_id | timestamp |
 *   +------------+-------------+------------+----------+--------+-------------+-----------+
 *   | 1          | 1           | 1          | 5        | 1      | 1           | 3         |  ← Has ad
 *   | 2          | 1           | 15         | 17        | 2      | 1           | 16        |  ← Has ad
 *   | 3          | 2           | 10         | 12        | 3      | 2           | 11        |  ← Has ad
 *   | 4          | 2           | 17         | 28        | 4      | 2           | 20        |  ← Has ad
 *   +------------+-------------+------------+----------+--------+-------------+-----------+
 *
 *   After WHERE ad_id IS NULL:
 *   (No rows - all sessions had ads)
 *
 *   If we had a session without ads:
 *   Session 5: customer_id=3, start_time=1, end_time=5
 *   After LEFT JOIN: ad_id would be NULL (no matching ad)
 *   After WHERE: This session would be included
 *
 * Edge Cases:
 * - Session with no ads for that customer: ad_id IS NULL → included
 * - Session with ads outside time range: ad_id IS NULL → included
 * - Session with ads for different customer: ad_id IS NULL → included
 * - Session with ad during time range: ad_id NOT NULL → excluded
 *
 * Important Notes:
 * - The JOIN condition includes both customer_id match AND timestamp range
 * - BETWEEN is inclusive: [start_time, end_time]
 * - LEFT JOIN ensures all sessions are considered, even if they have no ads
 * - WHERE ad_id IS NULL filters to only ad-free sessions
 */

# Write your MySQL query statement below

SELECT p.session_id
FROM Playback p
LEFT JOIN Ads a
    ON p.customer_id = a.customer_id
    AND a.timestamp BETWEEN p.start_time AND p.end_time
WHERE a.ad_id IS NULL;

