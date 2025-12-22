/*
 * LeetCode 1407: Top Travellers
 * 
 * Problem:
 * Write a solution to report the distance traveled by each user.
 * Return the result table ordered by travelled_distance in descending order,
 * if two or more users traveled the same distance, order them by their name
 * in ascending order.
 * 
 * Table: Users
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | name          | varchar |
 * +---------------+---------+
 * id is the primary key for this table.
 * name is the name of the user.
 * 
 * Table: Rides
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | user_id       | int     |
 * | distance      | int     |
 * +---------------+---------+
 * id is the primary key for this table.
 * user_id is the id of the user who traveled the distance "distance".
 * 
 * Example:
 * Input:
 * Users table:
 * +------+-----------+
 * | id   | name      |
 * +------+-----------+
 * | 1    | Alice     |
 * | 2    | Bob       |
 * | 3    | Alex      |
 * | 4    | Donald    |
 * | 7    | Lee       |
 * | 13   | Jonathan  |
 * | 19   | Elvis     |
 * +------+-----------+
 * 
 * Rides table:
 * +------+----------+----------+
 * | id   | user_id  | distance |
 * +------+----------+----------+
 * | 1    | 1        | 120      |
 * | 2    | 2        | 317      |
 * | 3    | 3        | 222      |
 * | 4    | 7        | 100      |
 * | 5    | 13       | 312      |
 * | 6    | 19       | 50       |
 * | 7    | 7        | 120      |
 * | 8    | 19       | 400      |
 * | 9    | 7        | 230      |
 * +------+----------+----------+
 * 
 * Output:
 * +----------+--------------------+
 * | name     | travelled_distance |
 * +----------+--------------------+
 * | Elvis    | 450                |
 * | Lee      | 450                |
 * | Bob      | 317                |
 * | Jonathan | 312                |
 * | Alex     | 222                |
 * | Alice    | 120                |
 * | Donald   | 0                  |
 * +----------+--------------------+
 * 
 * Explanation:
 * Elvis and Lee traveled 450 miles, Elvis is the top traveller as his name is
 * alphabetically smaller. Similarly, Bob, Jonathan, Alex, and Alice have been
 * ordered by the name in ascending order. Donald did not make any rides, so his
 * distance is 0.
 * 
 * Approach: LEFT JOIN with GROUP BY and Aggregation
 * 
 * Key Insight:
 * - We need ALL users from the Users table
 * - Some users may not have any rides in the Rides table
 * - Use LEFT JOIN to include all users, with NULL for missing rides
 * - Use SUM to aggregate distance per user
 * - Use COALESCE to replace NULL with 0 for users with no rides
 * - GROUP BY user id and name to aggregate
 * - ORDER BY distance DESC, name ASC
 * 
 * Algorithm:
 * 1. LEFT JOIN Users with Rides on id = user_id
 * 2. GROUP BY user id and name
 * 3. SUM the distance for each user
 * 4. COALESCE to handle NULL (users with no rides)
 * 5. ORDER BY travelled_distance DESC, name ASC
 * 
 * Why LEFT JOIN?
 * 
 *   - INNER JOIN would exclude users without any rides
 *   - RIGHT JOIN would exclude users not in Rides (but all users should be shown)
 *   - LEFT JOIN ensures all users are included, with 0 for missing rides
 * 
 * Why GROUP BY?
 * 
 *   - Multiple rides can belong to the same user
 *   - We need to aggregate (SUM) distances per user
 *   - GROUP BY groups rows by user, allowing SUM to work correctly
 *   - Must include both id and name in GROUP BY (or use functional dependency)
 * 
 * Why COALESCE?
 * 
 *   - LEFT JOIN returns NULL when no match is found
 *   - SUM of NULL values is NULL (not 0)
 *   - COALESCE(SUM(r.distance), 0) returns 0 for users with no rides
 *   - Alternative: IFNULL(SUM(r.distance), 0) works the same in MySQL
 * 
 * Why ORDER BY travelled_distance DESC, name ASC?
 * 
 *   - Primary sort: distance descending (highest first)
 *   - Secondary sort: name ascending (alphabetical) for ties
 *   - Example: Elvis and Lee both have 450, but Elvis comes first alphabetically
 * 
 * Visual Example:
 * 
 *   Users:          Rides:           After LEFT JOIN:
 *   +----+----+     +----+----+----+  +----+----+----+
 *   | id |name|     | id |uid |dist|  | id |name|dist|
 *   +----+----+     +----+----+----+  +----+----+----+
 *   | 1  |Alice|    | 1  | 1  |120 |  | 1  |Alice|120 |
 *   | 2  |Bob  |    | 2  | 2  |317 |  | 2  |Bob  |317 |
 *   | 4  |Donald|   | 3  | 3  |222 |  | 4  |Donald|null|
 *   +----+----+     +----+----+----+  +----+----+----+
 * 
 *   After GROUP BY and SUM:
 *   +-------+----+  +----------+------------------+
 *   | id    |name|  | name     | travelled_distance|
 *   +-------+----+  +----------+------------------+
 *   | 1     |Alice|  | Alice    | 120              |
 *   | 2     |Bob  |  | Bob      | 317              |
 *   | 4     |Donald| | Donald   | 0                |
 *   +-------+----+  +----------+------------------+
 * 
 *   After ORDER BY:
 *   +----------+------------------+
 *   | name     | travelled_distance|
 *   +----------+------------------+
 *   | Bob      | 317              |
 *   | Alice    | 120              |
 *   | Donald   | 0                |
 *   +----------+------------------+
 * 
 * Edge Cases:
 * 
 *   - All users have rides: All distances will be non-zero
 *   - No users have rides: All distances will be 0
 *   - Some users have rides: Mixed zero and non-zero distances
 *   - Empty Users table: Returns empty result
 *   - Empty Rides table: All distances will be 0
 *   - Multiple rides per user: SUM aggregates correctly
 *   - Users with same distance: Sorted by name alphabetically
 * 
 * Important Notes:
 * 
 *   - GROUP BY must include all non-aggregated columns
 *   - In MySQL 5.7+, we can GROUP BY id only if name is functionally dependent
 *   - COALESCE handles both NULL (no rides) and actual 0 values correctly
 *   - ORDER BY can reference column aliases (travelled_distance)
 */

# Write your MySQL query statement below

SELECT 
    u.name,
    COALESCE(SUM(r.distance), 0) AS travelled_distance
FROM Users u
LEFT JOIN Rides r
ON u.id = r.user_id
GROUP BY u.id, u.name
ORDER BY travelled_distance DESC, u.name ASC;

