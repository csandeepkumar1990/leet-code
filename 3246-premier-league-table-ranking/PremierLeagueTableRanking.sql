/*
 * LeetCode 3246: Premier League Table Ranking
 * 
 * Problem:
 * Write a solution to rank teams in the Premier League table.
 * Points are calculated as: wins * 3 + draws
 * Return the result table ordered by points (descending), then team_name (ascending).
 * Include the rank of each team.
 * 
 * Table: TeamStats
 * +----------------+---------+
 * | Column Name    | Type    |
 * +----------------+---------+
 * | team_id        | int     |
 * | team_name      | varchar |
 * | matches_played | int     |
 * | wins           | int     |
 * | draws          | int     |
 * | losses         | int     |
 * +----------------+---------+
 * team_id is the primary key for this table.
 * Each row contains information about a team's statistics.
 * 
 * Approach: Window Function with Calculated Points
 * 
 * Key Insight:
 * - Calculate points: wins * 3 + draws
 * - Use RANK() window function to assign ranks
 * - Order by points DESC, then team_name ASC
 * - Handle ties in points using team_name as tie-breaker
 * 
 * Points Calculation:
 *   - Win: 3 points
 *   - Draw: 1 point
 *   - Loss: 0 points
 *   - Total points = (wins * 3) + draws
 * 
 * Formula:
 *   points = wins * 3 + draws
 *   rank = RANK() OVER (ORDER BY points DESC)  ← Only rank by points!
 *   Final sort: ORDER BY points DESC, team_name ASC
 * 
 * Algorithm:
 * 1. Calculate points for each team
 * 2. Use RANK() window function to assign ranks
 * 3. Order by points DESC, team_name ASC
 * 
 * Example:
 * 
 *   TeamStats:
 *   +---------+----------------+----------------+------+-------+--------+
 *   | team_id | team_name      | matches_played | wins | draws | losses |
 *   +---------+----------------+----------------+------+-------+--------+
 *   | 1       | Manchester City| 10             | 6    | 2     | 2      |
 *   | 2       | Liverpool      | 10             | 6    | 2     | 2      |
 *   | 3       | Chelsea        | 10             | 5    | 3     | 2      |
 *   | 4       | Arsenal        | 10             | 4    | 4     | 2      |
 *   | 5       | Tottenham      | 10             | 3    | 5     | 2      |
 *   +---------+----------------+----------------+------+-------+--------+
 * 
 *   Step 1 - Calculate points:
 *   Manchester City: (6 * 3) + 2 = 18 + 2 = 20
 *   Liverpool:       (6 * 3) + 2 = 18 + 2 = 20
 *   Chelsea:         (5 * 3) + 3 = 15 + 3 = 18
 *   Arsenal:         (4 * 3) + 4 = 12 + 4 = 16
 *   Tottenham:       (3 * 3) + 5 = 9 + 5 = 14
 * 
 *   Step 2 - Calculate rank (ONLY by points):
 *   Manchester City: 20 points → Rank 1 (tied with Liverpool)
 *   Liverpool:       20 points → Rank 1 (tied with Manchester City)
 *   Chelsea:         18 points → Rank 3
 *   Arsenal:         16 points → Rank 4
 *   Tottenham:       14 points → Rank 5
 * 
 *   Step 3 - Sort by points DESC, then team_name ASC:
 *   Liverpool comes before Manchester City alphabetically
 * 
 *   Result:
 *   +---------+----------------+--------+----------+
 *   | team_id | team_name     | points | position |
 *   +---------+----------------+--------+----------+
 *   | 2       | Liverpool     | 20     | 1        |
 *   | 1       | Manchester City| 20    | 1        |
 *   | 3       | Chelsea       | 18     | 3        |
 *   | 4       | Arsenal       | 16     | 4        |
 *   | 5       | Tottenham     | 14     | 5        |
 *   +---------+----------------+--------+----------+
 * 
 *   Note: Both Liverpool and Manchester City have rank 1 (same points)
 * 
 * Common Error: Using Alias in ORDER BY
 *   - Problem: ORDER BY points DESC (points is an alias)
 *   - In MySQL, aliases in ORDER BY can cause issues with window functions
 *   - Solution: Use the actual expression or wrap in subquery/CTE
 * 
 * Why the Error Occurs:
 *   - When using window functions like RANK(), MySQL processes the query in stages
 *   - Aliases defined in SELECT may not be available in ORDER BY
 *   - Need to reference the actual expression or use a subquery
 * 
 * Fixed Query Approaches:
 *   1. Use expression directly: ORDER BY (wins * 3 + draws) DESC
 *   2. Use subquery/CTE to calculate points first
 *   3. Reference column position (not recommended)
 * 
 * RANK() Window Function:
 *   - RANK() assigns ranks with gaps for ties
 *   - Teams with same points get same rank
 *   - Next rank skips numbers (1, 2, 3, 3, 5...)
 *   - Alternative: DENSE_RANK() (no gaps: 1, 2, 3, 3, 4...)
 *   - Alternative: ROW_NUMBER() (no ties: 1, 2, 3, 4, 5...)
 * 
 * ORDER BY in RANK():
 *   - RANK() should ONLY consider points (not team_name)
 *   - Teams with same points get the same rank
 *   - The final ORDER BY uses team_name as tie-breaker for sorting (not ranking)
 * 
 * Critical Fix:
 *   - RANK() OVER (ORDER BY points DESC) - rank only by points
 *   - ORDER BY points DESC, team_name ASC - sort by points, then name
 *   - Teams with same points get same rank, but are sorted by name
 */

# Write your MySQL query statement below

SELECT 
    team_id,
    team_name,
    matches_played,
    wins,
    draws,
    losses,
    (wins * 3 + draws) AS points,
    RANK() OVER (ORDER BY (wins * 3 + draws) DESC) AS `rank`
FROM TeamStats
ORDER BY (wins * 3 + draws) DESC, team_name ASC;

