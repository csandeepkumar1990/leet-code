/*
 * LeetCode 1939: Users That Actively Request Confirmation Messages
 * 
 * Problem:
 * Write a solution to find the IDs of users that requested a confirmation message
 * twice within a 24-hour window. Two messages exactly 24 hours apart are considered
 * to be within the window.
 * 
 * Return the result table ordered by user_id in ascending order.
 * 
 * Table: Confirmations
 * +----------------+----------+
 * | Column Name    | Type     |
 * +----------------+----------+
 * | user_id        | int      |
 * | time_stamp     | datetime |
 * +----------------+----------+
 * (user_id, time_stamp) is the primary key for this table.
 * Each row contains information about the confirmation message requested at a certain
 * time by a user.
 * 
 * Approach: Window Function LAG with Time Difference Calculation
 * 
 * Key Insight:
 * - Need to find users with confirmations within 24 hours of each other
 * - Use LAG window function to get previous timestamp for each user
 * - Calculate time difference between current and previous confirmation
 * - Filter users where difference <= 24 hours
 * 
 * Algorithm:
 * 1. Use LAG to get previous time_stamp for each user (partitioned by user_id)
 * 2. Calculate time difference using TIMESTAMPDIFF
 * 3. Filter rows where prev_time is not NULL and difference <= 24 hours
 * 4. Select DISTINCT user_id
 * 
 * Time Complexity: O(n log n), where n is the number of confirmations.
 *   - Window function requires sorting: O(n log n)
 *   - Filtering and DISTINCT: O(n)
 * 
 * Space Complexity: O(n), for the window function result set.
 * 
 * Example:
 * 
 *   Confirmations:
 *   +---------+---------------------+
 *   | user_id | time_stamp          |
 *   +---------+---------------------+
 *   | 1       | 2023-01-01 00:00:00 |
 *   | 1       | 2023-01-01 12:00:00 |
 *   | 1       | 2023-01-02 00:00:00 |
 *   | 2       | 2023-01-01 00:00:00 |
 *   | 2       | 2023-01-02 00:00:00 |
 *   | 3       | 2023-01-01 00:00:00 |
 *   | 3       | 2023-01-03 00:00:00 |
 *   +---------+---------------------+
 * 
 *   Step 1 - Apply LAG (partitioned by user_id, ordered by time_stamp):
 *   +---------+---------------------+---------------------+
 *   | user_id | time_stamp          | prev_time           |
 *   +---------+---------------------+---------------------+
 *   | 1       | 2023-01-01 00:00:00 | NULL                |
 *   | 1       | 2023-01-01 12:00:00 | 2023-01-01 00:00:00 |
 *   | 1       | 2023-01-02 00:00:00 | 2023-01-01 12:00:00 |
 *   | 2       | 2023-01-01 00:00:00 | NULL                |
 *   | 2       | 2023-01-02 00:00:00 | 2023-01-01 00:00:00 |
 *   | 3       | 2023-01-01 00:00:00 | NULL                |
 *   | 3       | 2023-01-03 00:00:00 | 2023-01-01 00:00:00 |
 *   +---------+---------------------+---------------------+
 * 
 *   Step 2 - Calculate time differences:
 *   - User 1, row 2: 12:00:00 - 00:00:00 = 12 hours <= 24 hours ✓
 *   - User 1, row 3: 00:00:00 - 12:00:00 = 12 hours <= 24 hours ✓
 *   - User 2, row 2: 00:00:00 - 00:00:00 = 24 hours <= 24 hours ✓
 *   - User 3, row 2: 00:00:00 - 00:00:00 = 48 hours > 24 hours ✗
 * 
 *   Step 3 - Filter and get DISTINCT user_id:
 *   - User 1: appears in filtered results (has confirmations within 24h)
 *   - User 2: appears in filtered results (has confirmations within 24h)
 *   - User 3: excluded (no confirmations within 24h)
 * 
 *   Result:
 *   +---------+
 *   | user_id |
 *   +---------+
 *   | 1       |
 *   +---------+
 *   | 2       |
 *   +---------+
 * 
 * Why LAG Window Function?
 * 
 *   - LAG gets the previous row's value within the partition
 *   - PARTITION BY user_id: Separate window for each user
 *   - ORDER BY time_stamp: Order confirmations chronologically
 *   - LAG(time_stamp): Gets previous confirmation time for same user
 *   - First confirmation for each user: prev_time = NULL
 * 
 * LAG Function Syntax:
 * 
 *   LAG(column, offset, default) OVER (
 *       PARTITION BY partition_column
 *       ORDER BY order_column
 *   )
 * 
 *   - column: Column to get previous value from
 *   - offset: How many rows back (default 1)
 *   - default: Value if no previous row (default NULL)
 *   - PARTITION BY: Groups rows (separate window per user)
 *   - ORDER BY: Orders rows within partition
 * 
 * Why PARTITION BY user_id?
 * 
 *   - Each user has their own sequence of confirmations
 *   - LAG should only look at previous confirmations from same user
 *   - Without PARTITION BY: LAG would mix confirmations from different users
 *   - Example: User 1's second confirmation should compare with User 1's first, not User 2's
 * 
 * Why ORDER BY time_stamp?
 * 
 *   - Confirmations must be ordered chronologically
 *   - LAG needs ordered sequence to find "previous" confirmation
 *   - Without ORDER BY: LAG might get wrong previous value
 *   - Ensures we compare consecutive confirmations in time order
 * 
 * TIMESTAMPDIFF Function:
 * 
 *   TIMESTAMPDIFF(unit, start, end)
 *   - unit: SECOND, MINUTE, HOUR, DAY, etc.
 *   - start: Earlier timestamp
 *   - end: Later timestamp
 *   - Returns: Difference in specified unit
 * 
 *   TIMESTAMPDIFF(SECOND, prev_time, time_stamp):
 *   - Calculates difference in seconds
 *   - prev_time is earlier, time_stamp is later
 *   - Returns positive number (seconds between them)
 * 
 * Why 24 * 60 * 60 Seconds?
 * 
 *   - 24 hours = 24 * 60 minutes = 24 * 60 * 60 seconds
 *   - 24 * 60 * 60 = 86,400 seconds
 *   - This is exactly 24 hours
 *   - Using seconds gives precise comparison
 * 
 * Alternative: Using HOURS Unit
 * 
 *   WHERE TIMESTAMPDIFF(HOUR, prev_time, time_stamp) <= 24
 * 
 *   - Simpler: 24 hours directly
 *   - Less precise: rounds to nearest hour
 *   - Both approaches work, but SECONDS is more precise
 * 
 * Why prev_time IS NOT NULL?
 * 
 *   - First confirmation for each user has prev_time = NULL
 *   - Can't calculate time difference with NULL
 *   - TIMESTAMPDIFF with NULL returns NULL
 *   - NULL <= 24*60*60 evaluates to NULL (not TRUE)
 *   - Filtering NULL ensures we only check pairs of confirmations
 * 
 * Why DISTINCT?
 * 
 *   - A user might have multiple pairs within 24 hours
 *   - Example: User 1 has confirmations at 0:00, 12:00, 24:00
 *     - Pair 1: (0:00, 12:00) within 24h
 *     - Pair 2: (12:00, 24:00) within 24h
 *   - Both pairs would appear in result
 *   - DISTINCT ensures each user appears only once
 * 
 * Edge Cases:
 * 
 *   - User with single confirmation: prev_time = NULL → excluded
 *   - User with confirmations exactly 24 hours apart: Included (<= 24h)
 *   - User with confirmations 24 hours 1 second apart: Excluded (> 24h)
 *   - User with multiple confirmations in same second: Included (0 seconds <= 24h)
 *   - User with confirmations spanning multiple days: Checked correctly
 * 
 * Performance Considerations:
 * 
 *   - Window function requires sorting: O(n log n)
 *   - Consider indexing on (user_id, time_stamp) for better performance
 *   - DISTINCT requires additional processing
 *   - For large datasets, this query can be expensive
 */

# Write your MySQL query statement below

SELECT DISTINCT user_id
FROM (
    SELECT
        user_id,
        time_stamp,
        LAG(time_stamp) OVER (
            PARTITION BY user_id
            ORDER BY time_stamp
        ) AS prev_time
    FROM Confirmations
) t
WHERE prev_time IS NOT NULL
  AND TIMESTAMPDIFF(SECOND, prev_time, time_stamp) <= 24 * 60 * 60;

