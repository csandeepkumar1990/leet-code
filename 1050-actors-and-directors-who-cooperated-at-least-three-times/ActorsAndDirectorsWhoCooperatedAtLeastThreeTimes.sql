/*
 * LeetCode 1050: Actors and Directors Who Cooperated At Least Three Times
 * 
 * Problem:
 * Write a solution to find all the pairs (actor_id, director_id) where the actor
 * has cooperated with the director at least three times.
 * 
 * Return the result table in any order.
 * 
 * Table: ActorDirector
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | actor_id    | int     |
 * | director_id | int     |
 * | timestamp   | int     |
 * +-------------+---------+
 * timestamp is the primary key for this table.
 * Each row contains information about the timestamp when an actor worked with a director.
 * 
 * Approach: GROUP BY with HAVING Clause
 * 
 * Key Insight:
 * - Need to find actor-director pairs that appear at least 3 times
 * - Group by both actor_id and director_id to count occurrences of each pair
 * - Use HAVING to filter groups with COUNT(*) >= 3
 * 
 * Algorithm:
 * 1. SELECT actor_id and director_id from ActorDirector table
 * 2. GROUP BY actor_id, director_id to aggregate rows per pair
 * 3. HAVING COUNT(*) >= 3 to filter pairs with at least 3 cooperations
 * 4. Return matching pairs
 * 
 * Time Complexity: O(n), where n is the number of rows in ActorDirector table.
 *   - GROUP BY requires scanning all rows
 *   - HAVING filters groups after aggregation
 * 
 * Space Complexity: O(k), where k is the number of unique actor-director pairs.
 *   - GROUP BY creates groups for each unique pair
 *   - Result set contains pairs with at least 3 cooperations
 * 
 * Example:
 * 
 *   ActorDirector:
 *   +----------+-------------+-----------+
 *   | actor_id | director_id | timestamp |
 *   +----------+-------------+-----------+
 *   | 1        | 1           | 0         |
 *   | 1        | 1           | 1         |
 *   | 1        | 1           | 2         |
 *   | 1        | 2           | 3         |
 *   | 2        | 1           | 4         |
 *   | 2        | 1           | 5         |
 *   | 2        | 1           | 6         |
 *   | 2        | 1           | 7         |
 *   +----------+-------------+-----------+
 * 
 *   Step 1 - GROUP BY actor_id, director_id:
 *   - (1, 1): 3 rows (timestamps 0, 1, 2)
 *   - (1, 2): 1 row (timestamp 3)
 *   - (2, 1): 4 rows (timestamps 4, 5, 6, 7)
 * 
 *   Step 2 - HAVING COUNT(*) >= 3:
 *   - (1, 1): COUNT = 3 >= 3 ✓
 *   - (1, 2): COUNT = 1 < 3 ✗
 *   - (2, 1): COUNT = 4 >= 3 ✓
 * 
 *   Result:
 *   +----------+-------------+
 *   | actor_id | director_id |
 *   +----------+-------------+
 *   | 1        | 1           |
 *   | 2        | 1           |
 *   +----------+-------------+
 * 
 * Why GROUP BY actor_id, director_id?
 * 
 *   - Groups rows by the combination of actor and director
 *   - Each unique (actor_id, director_id) pair forms one group
 *   - Allows counting how many times each pair appears
 *   - Example: (1, 1) appears 3 times → one group with count 3
 * 
 * Why HAVING instead of WHERE?
 * 
 *   - WHERE filters rows BEFORE grouping
 *   - HAVING filters groups AFTER aggregation
 *   - Since we're filtering on COUNT(*) (aggregate function), we need HAVING
 *   - WHERE COUNT(*) >= 3 would cause an error
 * 
 * WHERE vs HAVING:
 * 
 *   WHERE:
 *   - Filters individual rows before grouping
 *   - Cannot use aggregate functions
 *   - Example: WHERE actor_id = 1 (filters rows)
 * 
 *   HAVING:
 *   - Filters groups after aggregation
 *   - Can use aggregate functions (COUNT, SUM, AVG, etc.)
 *   - Example: HAVING COUNT(*) >= 3 (filters groups)
 * 
 * Query Execution Order:
 * 
 *   1. FROM ActorDirector
 *   2. GROUP BY actor_id, director_id
 *   3. HAVING COUNT(*) >= 3
 *   4. SELECT actor_id, director_id
 * 
 * Why COUNT(*) >= 3?
 * 
 *   - COUNT(*) counts all rows in each group
 *   - Each row represents one cooperation (one timestamp)
 *   - >= 3 means at least 3 cooperations
 *   - Could also use COUNT(timestamp) >= 3 (same result if no NULLs)
 * 
 * Alternative: Using COUNT(timestamp)
 * 
 *   SELECT actor_id, director_id
 *   FROM ActorDirector
 *   GROUP BY actor_id, director_id
 *   HAVING COUNT(timestamp) >= 3;
 * 
 *   - COUNT(timestamp) counts non-NULL timestamps
 *   - COUNT(*) counts all rows (including NULLs)
 *   - Since timestamp is PRIMARY KEY (cannot be NULL), both are equivalent
 *   - COUNT(*) is slightly more efficient
 * 
 * Edge Cases:
 * 
 *   - No pairs with 3+ cooperations: Returns empty result
 *   - All pairs have exactly 3 cooperations: All pairs included
 *   - Some pairs have many cooperations: Included if >= 3
 *   - Duplicate timestamps: Each row is counted (even if timestamp is duplicate)
 *   - Single actor-director pair: Included if appears 3+ times
 * 
 * Performance Considerations:
 * 
 *   - GROUP BY requires sorting or hashing (depends on database)
 *   - Consider indexing on (actor_id, director_id) for better performance
 *   - HAVING is evaluated after grouping, so it's efficient
 * 
 * Composite Key Insight:
 * 
 *   - The combination (actor_id, director_id) forms a composite key
 *   - Multiple rows can have the same (actor_id, director_id) with different timestamps
 *   - This is why we group by both columns together
 *   - Each timestamp represents a separate cooperation event
 */

# Write your MySQL query statement below

SELECT actor_id, director_id
FROM ActorDirector
GROUP BY actor_id, director_id
HAVING COUNT(*) >= 3;

