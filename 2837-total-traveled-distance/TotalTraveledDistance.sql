/*
 * LeetCode 2837: Total Traveled Distance
 * 
 * Problem:
 * Write a solution to report the distance traveled by each user.
 * Return the result table ordered by user_id in ascending order.
 * 
 * Table: Users
 * +---------+---------+
 * | Column Name | Type |
 * +---------+---------+
 * | user_id | int     |
 * | name    | varchar |
 * +---------+---------+
 * user_id is the primary key for this table.
 * Each row contains the id and the name of a user.
 * 
 * Table: Rides
 * +---------+---------+
 * | Column Name | Type |
 * +---------+---------+
 * | ride_id | int     |
 * | user_id | int     |
 * | distance | int    |
 * +---------+---------+
 * ride_id is the primary key for this table.
 * Each row contains the id of a ride, the id of the user who traveled
 * in that ride, and the distance traveled in that ride.
 * 
 * Approach: LEFT JOIN + GROUP BY + COALESCE
 * 
 * Key Insight:
 * - Need to include ALL users, even those with no rides
 * - LEFT JOIN ensures users without rides appear in result
 * - COALESCE handles NULL distances (users with no rides)
 * - GROUP BY aggregates distances per user
 * 
 * Algorithm:
 * 1. LEFT JOIN Users with Rides on user_id
 * 2. GROUP BY user_id and name to aggregate per user
 * 3. SUM the distance for each user
 * 4. Use COALESCE to convert NULL to 0 for users with no rides
 * 5. ORDER BY user_id in ascending order
 * 
 * Formula:
 *   traveled_distance = COALESCE(SUM(distance), 0)
 *   - If user has rides: SUM of all distances
 *   - If user has no rides: 0 (NULL converted by COALESCE)
 * 
 * Time Complexity: O(n + m), where n is number of users, m is number of rides.
 *   - LEFT JOIN: O(n + m) to match rides to users
 *   - GROUP BY: O(n + m) to aggregate
 *   - SUM: O(m) to calculate totals
 * 
 * Space Complexity: O(n), where n is the number of users.
 *   - Result set contains one row per user
 * 
 * Example:
 * 
 *   Users:
 *   +---------+---------+
 *   | user_id | name    |
 *   +---------+---------+
 *   | 1       | Alice   |
 *   | 2       | Bob     |
 *   | 3       | Alex    |
 *   | 4       | Donald  |
 *   | 7       | Lee     |
 *   | 13      | Jonathan|
 *   | 19      | Elvis   |
 *   +---------+---------+
 * 
 *   Rides:
 *   +---------+---------+----------+
 *   | ride_id | user_id | distance |
 *   +---------+---------+----------+
 *   | 1       | 1       | 120      |
 *   | 2       | 2       | 317      |
 *   | 3       | 3       | 222      |
 *   | 4       | 7       | 100      |
 *   | 5       | 13      | 312      |
 *   | 6       | 19      | 50       |
 *   | 7       | 7       | 120      |
 *   | 8       | 19      | 400      |
 *   | 9       | 7       | 230      |
 *   +---------+---------+----------+
 * 
 *   Step 1 - LEFT JOIN Users with Rides:
 *   +---------+---------+----------+
 *   | user_id | name    | distance |
 *   +---------+---------+----------+
 *   | 1       | Alice   | 120      |
 *   | 2       | Bob     | 317      |
 *   | 3       | Alex    | 222      |
 *   | 4       | Donald  | NULL     |  ← No rides
 *   | 7       | Lee     | 100      |
 *   | 7       | Lee     | 120      |
 *   | 7       | Lee     | 230      |
 *   | 13      | Jonathan| 312      |
 *   | 19      | Elvis   | 50       |
 *   | 19      | Elvis   | 400      |
 *   +---------+---------+----------+
 * 
 *   Step 2 - GROUP BY and SUM:
 *   - User 1 (Alice): 120
 *   - User 2 (Bob): 317
 *   - User 3 (Alex): 222
 *   - User 4 (Donald): NULL → COALESCE to 0
 *   - User 7 (Lee): 100 + 120 + 230 = 450
 *   - User 13 (Jonathan): 312
 *   - User 19 (Elvis): 50 + 400 = 450
 * 
 *   Result:
 *   +---------+---------+-------------------+
 *   | user_id | name    | traveled distance |
 *   +---------+---------+-------------------+
 *   | 1       | Alice   | 120               |
 *   | 2       | Bob     | 317               |
 *   | 3       | Alex    | 222               |
 *   | 4       | Donald  | 0                 |
 *   | 7       | Lee     | 450               |
 *   | 13      | Jonathan| 312               |
 *   | 19      | Elvis   | 450               |
 *   +---------+---------+-------------------+
 * 
 * Why LEFT JOIN?
 * 
 *   - Problem requires ALL users to appear in result
 *   - INNER JOIN would exclude users with no rides
 *   - LEFT JOIN preserves all users from Users table
 *   - Rides without matching users are excluded (but shouldn't exist)
 * 
 * Why COALESCE?
 * 
 *   - Users with no rides have NULL distance after LEFT JOIN
 *   - SUM(NULL) returns NULL (not 0)
 *   - COALESCE(SUM(distance), 0) converts NULL to 0
 *   - Ensures users with no rides show 0 distance traveled
 * 
 * Why GROUP BY user_id AND name?
 * 
 *   - Both columns appear in SELECT
 *   - MySQL requires all non-aggregated columns in GROUP BY
 *   - name is functionally dependent on user_id (one-to-one)
 *   - Grouping by both ensures correct aggregation
 * 
 * Alternative: Using IFNULL
 * 
 *   SELECT
 *       u.user_id,
 *       u.name,
 *       IFNULL(SUM(r.distance), 0) AS `traveled distance`
 *   FROM Users u
 *   LEFT JOIN Rides r
 *       ON u.user_id = r.user_id
 *   GROUP BY u.user_id, u.name
 *   ORDER BY u.user_id ASC;
 * 
 *   - IFNULL is MySQL-specific (COALESCE is standard SQL)
 *   - Both work the same way for this use case
 *   - COALESCE is more portable across databases
 * 
 * Column Alias with Space:
 * 
 *   - Column alias "traveled distance" contains a space
 *   - Must be wrapped in backticks (`) in MySQL
 *   - Backticks allow identifiers with spaces or special characters
 *   - Without backticks, MySQL treats it as two separate identifiers
 * 
 * Edge Cases:
 * 
 *   1. User with no rides:
 *      - LEFT JOIN produces NULL distance
 *      - COALESCE converts to 0
 *      - Result: user_id, name, 0
 * 
 *   2. User with single ride:
 *      - SUM returns that ride's distance
 *      - Result: user_id, name, distance
 * 
 *   3. User with multiple rides:
 *      - SUM returns sum of all distances
 *      - Result: user_id, name, total_distance
 * 
 *   4. User with rides totaling 0:
 *      - SUM returns 0 (not NULL)
 *      - COALESCE not needed, but doesn't hurt
 *      - Result: user_id, name, 0
 * 
 *   5. NULL distances in Rides table:
 *      - SUM ignores NULL values
 *      - If all distances are NULL, SUM returns NULL
 *      - COALESCE converts to 0
 * 
 * NULL Handling:
 * 
 *   - LEFT JOIN: Users without rides → NULL distance
 *   - SUM: Ignores NULL values in calculation
 *   - SUM of all NULLs: Returns NULL
 *   - COALESCE: Converts NULL to 0
 *   - Final result: Always a number (never NULL)
 * 
 * Performance Considerations:
 * 
 *   - LEFT JOIN on user_id should use index
 *   - GROUP BY requires sorting or hashing
 *   - Consider indexing on Rides.user_id
 *   - Consider indexing on Users.user_id (primary key, already indexed)
 * 
 * Why ORDER BY user_id?
 * 
 *   - Problem specifies ascending order by user_id
 *   - Ensures consistent, predictable output
 *   - Primary key is already indexed, so sorting is efficient
 */

# Write your MySQL query statement below

SELECT
    u.user_id,
    u.name,
    COALESCE(SUM(r.distance), 0) AS `traveled distance`
FROM Users u
LEFT JOIN Rides r
    ON u.user_id = r.user_id
GROUP BY u.user_id, u.name
ORDER BY u.user_id ASC;

