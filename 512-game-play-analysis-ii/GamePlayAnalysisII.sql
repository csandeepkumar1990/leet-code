/*
 * LeetCode 512: Game Play Analysis II
 * 
 * Problem:
 * Find the device that each player used to log in for the first time.
 * 
 * Table: Activity
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | player_id    | int     |
 * | device_id    | int     |
 * | event_date   | date    |
 * | games_played | int     |
 * +--------------+---------+
 * (player_id, event_date) is the primary key.
 * Each row is a record of a player who logged in and played games on some day.
 * 
 * Example:
 * Input:
 * +-----------+-----------+------------+--------------+
 * | player_id | device_id | event_date | games_played |
 * +-----------+-----------+------------+--------------+
 * | 1         | 2         | 2016-03-01 | 5            |
 * | 1         | 2         | 2016-05-02 | 6            |
 * | 2         | 3         | 2017-06-25 | 1            |
 * | 3         | 1         | 2016-03-02 | 0            |
 * | 3         | 4         | 2018-07-03 | 5            |
 * +-----------+-----------+------------+--------------+
 * 
 * Output:
 * +-----------+-----------+
 * | player_id | device_id |
 * +-----------+-----------+
 * | 1         | 2         |
 * | 2         | 3         |
 * | 3         | 1         |
 * +-----------+-----------+
 * 
 * Explanation:
 * - Player 1: first login on 2016-03-01 using device 2
 * - Player 2: first login on 2017-06-25 using device 3
 * - Player 3: first login on 2016-03-02 using device 1
 * 
 * Approach:
 * - Subquery: Find each player's first login date using MIN(event_date)
 * - Main query: Join Activity with the subquery to get the device_id
 *   for the first login date
 * - Join conditions: Match player_id AND event_date = first_login
 */

# Write your MySQL query statement below

SELECT a.player_id, a.device_id
FROM Activity a
JOIN (
    SELECT player_id, MIN(event_date) AS first_login
    FROM Activity
    GROUP BY player_id
) f
ON a.player_id = f.player_id
AND a.event_date = f.first_login;

