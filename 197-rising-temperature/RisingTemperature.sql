/*
 * LeetCode 197: Rising Temperature
 * 
 * Problem:
 * Find all dates' IDs where the temperature is higher than the previous day.
 * 
 * Table: Weather
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | recordDate    | date    |
 * | temperature   | int     |
 * +---------------+---------+
 * id is the primary key.
 * Each row contains the temperature on a certain day.
 * 
 * Example:
 * Input:
 * +----+------------+-------------+
 * | id | recordDate | temperature |
 * +----+------------+-------------+
 * | 1  | 2015-01-01 | 10          |
 * | 2  | 2015-01-02 | 25          |
 * | 3  | 2015-01-03 | 20          |
 * | 4  | 2015-01-04 | 30          |
 * +----+------------+-------------+
 * 
 * Output:
 * +----+
 * | id |
 * +----+
 * | 2  |
 * | 4  |
 * +----+
 * 
 * Explanation:
 * - id=2 (2015-01-02): temp=25, previous day temp=10 → 25 > 10 ✅
 * - id=3 (2015-01-03): temp=20, previous day temp=25 → 20 < 25 ❌
 * - id=4 (2015-01-04): temp=30, previous day temp=20 → 30 > 20 ✅
 * 
 * Approach:
 * - Self-join Weather table: w1 is current day, w2 is previous day
 * - Join condition: w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
 *   This links each day to its previous day
 * - Filter: w1.temperature > w2.temperature (today warmer than yesterday)
 * 
 * Alternative join condition:
 * - DATEDIFF(w1.recordDate, w2.recordDate) = 1
 */

# Write your MySQL query statement below

SELECT w1.id
FROM Weather w1
JOIN Weather w2
  ON w1.recordDate = DATE_ADD(w2.recordDate, INTERVAL 1 DAY)
WHERE w1.temperature > w2.temperature;

