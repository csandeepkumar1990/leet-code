/*
 * LeetCode 603: Consecutive Available Seats
 * 
 * Problem:
 * Several friends at a cinema ticket office would like to reserve consecutive available seats.
 * Can you help to query all the consecutive available seats order by the seat_id using the
 * following cinema table?
 * 
 * Note:
 * - The seat_id is an auto-increment integer, and free is bool ('1' means free, and '0' means
 *   occupied.).
 * - Consecutive available seats are more than 2(inclusive) seats consecutively available.
 * 
 * Table: cinema
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | seat_id     | int  |
 * | free        | bool |
 * +-------------+------+
 * seat_id is an auto-increment primary key for this table.
 * Each row contains information about the seat id and whether it is free or not.
 * free is '1' if the seat is free, and '0' if it is occupied.
 * 
 * Approach: Self-Join with ABS Function
 * 
 * Key Insight:
 * - Find seats that are consecutive (seat_id difference = 1) and both are free
 * - Use self-join to pair each seat with its adjacent seats
 * - ABS(a.seat_id - b.seat_id) = 1 finds consecutive seats
 * - Both seats must be free (a.free = 1 AND b.free = 1)
 * - Use DISTINCT to avoid duplicates when a seat has multiple consecutive free neighbors
 * 
 * Algorithm:
 * 1. Self-join cinema table (aliases a and b)
 * 2. Join condition: ABS(a.seat_id - b.seat_id) = 1 (consecutive seats)
 * 3. Filter: a.free = 1 AND b.free = 1 (both seats must be free)
 * 4. Select DISTINCT a.seat_id to get all seats that are part of consecutive pairs
 * 5. ORDER BY a.seat_id for sorted output
 * 
 * Time Complexity: O(n²), where n is the number of seats.
 *   Self-join creates n × n combinations, then filters.
 *   In practice, with proper indexing, this can be optimized.
 * 
 * Space Complexity: O(k), where k is the number of consecutive free seat pairs.
 *   Result set contains seats that are part of consecutive free pairs.
 * 
 * Example:
 * 
 *   cinema:
 *   +---------+------+
 *   | seat_id | free |
 *   +---------+------+
 *   | 1       | 1    |
 *   | 2       | 0    |
 *   | 3       | 1    |
 *   | 4       | 1    |
 *   | 5       | 1    |
 *   | 6       | 0    |
 *   | 7       | 1    |
 *   | 8       | 1    |
 *   +---------+------+
 * 
 *   Step 1 - Self-join with ABS(seat_id difference) = 1:
 *   Pairs where |a.seat_id - b.seat_id| = 1:
 *   - (1,2), (2,1), (2,3), (3,2), (3,4), (4,3), (4,5), (5,4), (5,6), (6,5), (6,7), (7,6), (7,8), (8,7)
 * 
 *   Step 2 - Filter where both are free (a.free = 1 AND b.free = 1):
 *   - (1,2): a.free=1, b.free=0 ✗
 *   - (3,4): a.free=1, b.free=1 ✓ → seat_id 3
 *   - (4,3): a.free=1, b.free=1 ✓ → seat_id 4
 *   - (4,5): a.free=1, b.free=1 ✓ → seat_id 4
 *   - (5,4): a.free=1, b.free=1 ✓ → seat_id 5
 *   - (7,8): a.free=1, b.free=1 ✓ → seat_id 7
 *   - (8,7): a.free=1, b.free=1 ✓ → seat_id 8
 * 
 *   Step 3 - DISTINCT a.seat_id:
 *   Unique seat_ids: [3, 4, 5, 7, 8]
 * 
 *   Step 4 - ORDER BY a.seat_id:
 *   Result: [3, 4, 5, 7, 8]
 * 
 *   Result:
 *   +---------+
 *   | seat_id |
 *   +---------+
 *   | 3       |
 *   | 4       |
 *   | 5       |
 *   | 7       |
 *   | 8       |
 *   +---------+
 * 
 *   Explanation:
 *   - Seats 3, 4, 5 are consecutive and all free
 *   - Seats 7, 8 are consecutive and both free
 *   - Seat 1 is free but seat 2 is occupied (not consecutive free)
 * 
 * Why Self-Join?
 * 
 *   - Need to compare each seat with its adjacent seats
 *   - Self-join creates pairs of seats from the same table
 *   - Allows checking if two seats are consecutive and both free
 *   - Without self-join, we can't easily check adjacent seats
 * 
 * Why ABS(a.seat_id - b.seat_id) = 1?
 * 
 *   - Consecutive seats have seat_id difference of exactly 1
 *   - ABS() ensures we catch both directions: (3,4) and (4,3)
 *   - Example: seat 3 and seat 4 are consecutive
 *     - |3 - 4| = 1 ✓
 *     - |4 - 3| = 1 ✓
 *   - Without ABS: would need two conditions (a.seat_id = b.seat_id + 1 OR a.seat_id = b.seat_id - 1)
 * 
 * Why DISTINCT?
 * 
 *   - A seat can be part of multiple consecutive pairs
 *   - Example: Seat 4 is consecutive with both seat 3 and seat 5
 *   - Without DISTINCT: seat 4 would appear twice in results
 *   - DISTINCT ensures each seat appears only once
 * 
 * Why Both Conditions (a.free = 1 AND b.free = 1)?
 * 
 *   - Both seats in the pair must be free
 *   - If either seat is occupied, they're not consecutive available seats
 *   - Example: Seat 1 is free but seat 2 is occupied → not a valid pair
 *   - Only pairs where both are free are included
 * 
 * Alternative Approach (Using Window Functions):
 * 
 *   WITH consecutive AS (
 *       SELECT seat_id,
 *              free,
 *              LAG(seat_id) OVER (ORDER BY seat_id) AS prev_seat,
 *              LAG(free) OVER (ORDER BY seat_id) AS prev_free,
 *              LEAD(seat_id) OVER (ORDER BY seat_id) AS next_seat,
 *              LEAD(free) OVER (ORDER BY seat_id) AS next_free
 *       FROM cinema
 *   )
 *   SELECT DISTINCT seat_id
 *   FROM consecutive
 *   WHERE (free = 1 AND prev_free = 1 AND seat_id - prev_seat = 1)
 *      OR (free = 1 AND next_free = 1 AND next_seat - seat_id = 1)
 *   ORDER BY seat_id;
 * 
 *   - More complex but potentially more efficient
 *   - Uses window functions to check previous/next seats
 *   - Self-join approach is simpler and more intuitive
 * 
 * Edge Cases:
 * 
 *   - No consecutive free seats: Returns empty result
 *   - All seats free: Returns all seats (all are consecutive)
 *   - All seats occupied: Returns empty result
 *   - Single free seat: Not included (needs at least 2 consecutive)
 *   - Gaps in seat_id: Works correctly (only checks difference = 1)
 * 
 * Performance Considerations:
 * 
 *   - Self-join creates O(n²) combinations
 *   - With proper indexing on seat_id, this can be optimized
 *   - For large datasets, window function approach might be faster
 *   - But self-join is simpler and more readable
 */

# Write your MySQL query statement below

SELECT DISTINCT a.seat_id
FROM cinema a
JOIN cinema b
ON ABS(a.seat_id - b.seat_id) = 1
AND a.free = 1
AND b.free = 1
ORDER BY a.seat_id;

