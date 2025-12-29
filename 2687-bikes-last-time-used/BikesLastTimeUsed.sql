/*
 * LeetCode 2687: Bikes Last Time Used
 * 
 * Problem:
 * Write a solution to find the last time each bike was used.
 * Return the result table ordered by end_time in descending order.
 * 
 * Table: Bikes
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | bike_number | int     |
 * | end_time    | datetime|
 * +-------------+---------+
 * (bike_number, end_time) is the primary key for this table.
 * Each row contains information about a bike usage session.
 * 
 * Approach: GROUP BY with MAX Aggregation
 * 
 * Key Insight:
 * - Need to find the latest (maximum) end_time for each bike
 * - Group by bike_number to aggregate sessions per bike
 * - Use MAX(end_time) to get the most recent usage time
 * - Order by end_time DESC to show most recently used bikes first
 * 
 * Formula:
 *   For each bike:
 *   last_time_used = MAX(end_time)
 * 
 * Algorithm:
 * 1. GROUP BY bike_number to aggregate sessions per bike
 * 2. Use MAX(end_time) to find the latest end_time for each bike
 * 3. ORDER BY end_time DESC (most recent first)
 * 
 * Example:
 * 
 *   Bikes:
 *   +-------------+---------------------+
 *   | bike_number | end_time            |
 *   +-------------+---------------------+
 *   | 1           | 2023-01-01 10:00:00|
 *   | 1           | 2023-01-02 15:30:00|
 *   | 1           | 2023-01-03 09:15:00|
 *   | 2           | 2023-01-01 12:00:00|
 *   | 2           | 2023-01-04 14:20:00|
 *   | 3           | 2023-01-02 11:00:00|
 *   +-------------+---------------------+
 * 
 *   Step 1 - Group by bike_number:
 *   Bike 1: [2023-01-01 10:00:00, 2023-01-02 15:30:00, 2023-01-03 09:15:00]
 *   Bike 2: [2023-01-01 12:00:00, 2023-01-04 14:20:00]
 *   Bike 3: [2023-01-02 11:00:00]
 * 
 *   Step 2 - Find MAX(end_time) for each bike:
 *   Bike 1: MAX(2023-01-01, 2023-01-02, 2023-01-03) = 2023-01-03 09:15:00
 *   Bike 2: MAX(2023-01-01, 2023-01-04) = 2023-01-04 14:20:00
 *   Bike 3: MAX(2023-01-02) = 2023-01-02 11:00:00
 * 
 *   Step 3 - Order by end_time DESC:
 *   Bike 2: 2023-01-04 14:20:00 (most recent)
 *   Bike 1: 2023-01-03 09:15:00
 *   Bike 3: 2023-01-02 11:00:00 (oldest)
 * 
 *   Result:
 *   +-------------+---------------------+
 *   | bike_number | end_time            |
 *   +-------------+---------------------+
 *   | 2           | 2023-01-04 14:20:00|
 *   | 1           | 2023-01-03 09:15:00|
 *   | 3           | 2023-01-02 11:00:00|
 *   +-------------+---------------------+
 * 
 * Why MAX(end_time)?
 *   - MAX() finds the latest (most recent) datetime value
 *   - For datetime values, MAX returns the most recent timestamp
 *   - This gives us the last time each bike was used
 * 
 * Why GROUP BY bike_number?
 *   - Aggregates sessions per bike
 *   - Without GROUP BY: Would get one row with overall MAX
 *   - With GROUP BY: One row per bike with its latest end_time
 * 
 * Why ORDER BY end_time DESC?
 *   - Most recently used bikes appear first
 *   - DESC means latest timestamp first
 *   - Useful for identifying which bikes were used most recently
 * 
 * MAX with Datetime:
 *   - MAX() works with datetime values
 *   - Compares timestamps chronologically
 *   - Latest timestamp has the maximum value
 *   - Example: MAX('2023-01-01', '2023-01-02') = '2023-01-02'
 * 
 * Alternative Approaches:
 *   - Using window function: ROW_NUMBER() OVER (PARTITION BY bike_number ORDER BY end_time DESC)
 *   - Using subquery: SELECT bike_number, (SELECT MAX(end_time) FROM Bikes b2 WHERE b2.bike_number = b1.bike_number)
 *   - GROUP BY with MAX is the simplest and most efficient
 * 
 * Edge Cases:
 *   - Bike with single session: MAX returns that session's end_time
 *   - Bike with multiple sessions: MAX returns the latest end_time
 *   - Multiple bikes with same end_time: All appear, sorted by end_time
 *   - Bike with no sessions: Not in result (no rows to group)
 */

# Write your MySQL query statement below

SELECT
    bike_number,
    MAX(end_time) AS end_time
FROM Bikes
GROUP BY bike_number
ORDER BY end_time DESC;

