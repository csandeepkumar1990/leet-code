/*
 * LeetCode 597: Friend Requests I: Overall Acceptance Rate
 * 
 * Problem:
 * Write a query to find the overall acceptance rate of requests, which is the number
 * of acceptance divided by the number of requests. Return the answer rounded to 2
 * decimals places.
 * 
 * Note:
 * - The accepted column is not set for friend requests with 'requested' status.
 * - The acceptance rate is computed by dividing the number of accepted requests by
 *   the number of total requests.
 * - If there are no requests at all, you should return 0.00 as the accept_rate.
 * 
 * Table: FriendRequest
 * +----------------+---------+
 * | Column Name    | Type    |
 * +----------------+---------+
 * | sender_id      | int     |
 * | send_to_id     | int     |
 * | request_date   | date    |
 * +----------------+---------+
 * There is no primary key for this table, it may contain duplicates.
 * This table contains the ID of the user who sent the request, the ID of the user
 * who received the request, and the date of the request.
 * 
 * Table: RequestAccepted
 * +----------------+---------+
 * | Column Name    | Type    |
 * +----------------+---------+
 * | requester_id   | int     |
 * | accepter_id    | int     |
 * | accept_date    | date    |
 * +----------------+---------+
 * There is no primary key for this table, it may contain duplicates.
 * This table contains the ID of the user who sent the request, the ID of the user
 * who received the request, and the date when the request was accepted.
 * 
 * Approach: Calculate Acceptance Rate with Division
 * 
 * Key Insight:
 * - Acceptance rate = (number of accepted requests) / (number of total requests)
 * - Use COUNT(DISTINCT ...) to count unique request pairs
 * - Handle division by zero with IFNULL (when no requests exist)
 * - Round result to 2 decimal places
 * 
 * Formula:
 *   accept_rate = COUNT(DISTINCT accepted pairs) / COUNT(DISTINCT total pairs)
 * 
 * Algorithm:
 * 1. Count distinct accepted request pairs from RequestAccepted table
 * 2. Count distinct total request pairs from FriendRequest table
 * 3. Divide accepted by total (numerator / denominator)
 * 4. Round to 2 decimal places
 * 5. Use IFNULL to return 0.00 if denominator is 0 (no requests)
 * 
 * Time Complexity: O(n + m), where n is rows in FriendRequest, m is rows in RequestAccepted.
 *   Both subqueries scan their respective tables once.
 * 
 * Space Complexity: O(1), constant space for the result.
 * 
 * Example:
 * 
 *   FriendRequest:
 *   +-----------+------------+--------------+
 *   | sender_id | send_to_id  | request_date|
 *   +-----------+------------+--------------+
 *   | 1         | 2          | 2016/06/01  |
 *   | 1         | 3          | 2016/06/01  |
 *   | 1         | 4          | 2016/06/01  |
 *   | 2         | 3          | 2016/06/02  |
 *   | 3         | 4          | 2016/06/09  |
 *   +-----------+------------+--------------+
 * 
 *   RequestAccepted:
 *   +--------------+-------------+-------------+
 *   | requester_id | accepter_id | accept_date |
 *   +--------------+-------------+-------------+
 *   | 1            | 2          | 2016/06/03  |
 *   | 1            | 3          | 2016/06/08  |
 *   | 2            | 3          | 2016/06/08  |
 *   | 3            | 4          | 2016/06/09  |
 *   | 3            | 4          | 2016/06/10  | (duplicate)
 *   +--------------+-------------+-------------+
 * 
 *   Step 1 - Count distinct accepted pairs:
 *   Unique pairs: (1,2), (1,3), (2,3), (3,4)
 *   Note: (3,4) appears twice but COUNT(DISTINCT) counts it once
 *   accepted_count = 4
 * 
 *   Step 2 - Count distinct total request pairs:
 *   Unique pairs: (1,2), (1,3), (1,4), (2,3), (3,4)
 *   total_count = 5
 * 
 *   Step 3 - Calculate acceptance rate:
 *   accept_rate = 4 / 5 = 0.8
 *   Rounded to 2 decimals: 0.80
 * 
 *   Result:
 *   +------------+
 *   | accept_rate|
 *   +------------+
 *   | 0.80       |
 *   +------------+
 * 
 * Why COUNT(DISTINCT ...)?
 * 
 *   - FriendRequest and RequestAccepted may contain duplicate rows
 *   - Same request pair can appear multiple times
 *   - COUNT(DISTINCT sender_id, send_to_id) counts unique pairs only
 *   - Example: (3,4) appears twice in RequestAccepted but counted once
 * 
 * Why Two Columns in DISTINCT?
 * 
 *   - COUNT(DISTINCT sender_id, send_to_id) counts unique combinations
 *   - A request from user 1 to user 2 is different from user 2 to user 1
 *   - Both columns together form a unique request pair
 *   - This ensures we count each unique request relationship once
 * 
 * Why IFNULL?
 * 
 *   - If FriendRequest table is empty, denominator = 0
 *   - Division by zero returns NULL in SQL
 *   - Problem requires 0.00 when there are no requests
 *   - IFNULL(..., 0.00) converts NULL to 0.00
 * 
 * Why ROUND to 2 Decimal Places?
 * 
 *   - Problem explicitly asks for 2 decimal places
 *   - ROUND(value, 2) rounds to nearest hundredth
 *   - Example: 0.83333... → 0.83, 0.66666... → 0.67
 * 
 * Edge Cases:
 * 
 *   - No friend requests: IFNULL returns 0.00
 *   - No accepted requests: 0 / total = 0.00
 *   - All requests accepted: total / total = 1.00
 *   - Duplicate requests: COUNT(DISTINCT) handles correctly
 *   - Duplicate acceptances: COUNT(DISTINCT) handles correctly
 * 
 * Alternative Approaches:
 * 
 *   1. Using COALESCE instead of IFNULL:
 *      COALESCE(ROUND(...), 0.00) AS accept_rate
 *      (Both work the same in MySQL)
 * 
 *   2. Using CASE WHEN for division by zero:
 *      CASE 
 *          WHEN total_count = 0 THEN 0.00
 *          ELSE ROUND(accepted_count / total_count, 2)
 *      END AS accept_rate
 *      (More verbose but explicit)
 * 
 *   3. Using CTE for readability:
 *      WITH accepted AS (
 *          SELECT COUNT(DISTINCT requester_id, accepter_id) AS cnt
 *          FROM RequestAccepted
 *      ),
 *      total AS (
 *          SELECT COUNT(DISTINCT sender_id, send_to_id) AS cnt
 *          FROM FriendRequest
 *      )
 *      SELECT IFNULL(ROUND(accepted.cnt / total.cnt, 2), 0.00) AS accept_rate
 *      FROM accepted, total;
 *      (More readable but longer)
 * 
 * Important Notes:
 * 
 *   - The subqueries return scalar values (single numbers)
 *   - Division of two scalars is straightforward
 *   - Result is always a single row (one acceptance rate)
 *   - No GROUP BY needed since we're calculating overall rate
 */

# Write your MySQL query statement below

SELECT
    IFNULL(
        ROUND(
            (SELECT COUNT(DISTINCT requester_id, accepter_id)
             FROM RequestAccepted)
            /
            (SELECT COUNT(DISTINCT sender_id, send_to_id)
             FROM FriendRequest),
        2),
    0.00
    ) AS accept_rate;

