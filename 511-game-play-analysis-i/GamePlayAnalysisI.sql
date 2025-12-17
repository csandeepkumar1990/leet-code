/*
 * LeetCode 511: Game Play Analysis I
 * 
 * Problem:
 * Find the first login date for each player.
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
 * +-----------+-------------+
 * | player_id | first_login |
 * +-----------+-------------+
 * | 1         | 2016-03-01  |
 * | 2         | 2017-06-25  |
 * | 3         | 2016-03-02  |
 * +-----------+-------------+
 * 
 * Explanation:
 * - Player 1 logged in on 2016-03-01 and 2016-05-02 → first login: 2016-03-01
 * - Player 2 logged in on 2017-06-25 only → first login: 2017-06-25
 * - Player 3 logged in on 2016-03-02 and 2018-07-03 → first login: 2016-03-02
 * 
 * Approach:
 * - GROUP BY player_id to aggregate all login dates per player
 * - MIN(event_date) finds the earliest (first) login date for each player
 */

# Write your MySQL query statement below

SELECT player_id, MIN(event_date) AS first_login
FROM Activity
GROUP BY player_id;

