/*
 * LeetCode 2339: All the Matches of the League
 * 
 * Problem:
 * Write a solution to report all the possible matches of the league.
 * Return the result table in any order.
 * 
 * Table: Teams
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | team_name   | varchar |
 * +-------------+---------+
 * team_name is the primary key for this table.
 * Each row contains information about one team.
 * 
 * Approach: Self-Join with Inequality Condition
 * 
 * Key Insight:
 * - Need to generate all possible match combinations
 * - Each team plays against every other team
 * - Use self-join to pair teams with each other
 * - Use <> (not equal) to exclude same team pairing
 * 
 * Self-Join Concept:
 *   A self-join joins a table with itself
 *   - Creates two instances of the same table (aliases t1, t2)
 *   - Pairs each row from t1 with each row from t2
 *   - With condition t1.team_name <> t2.team_name, excludes same team
 * 
 * Formula:
 *   For n teams, number of matches = n × (n - 1)
 *   Each team plays against every other team (both home and away)
 * 
 * Algorithm:
 * 1. Self-join Teams table (t1 and t2)
 * 2. Condition: t1.team_name <> t2.team_name (exclude same team)
 * 3. Select t1.team_name as home_team, t2.team_name as away_team
 * 
 * Example:
 * 
 *   Teams:
 *   +------------+
 *   | team_name  |
 *   +------------+
 *   | Lions      |
 *   | Tigers     |
 *   | Leopards   |
 *   +------------+
 * 
 *   Step 1 - Self-join with <> condition:
 *   t1.team_name | t2.team_name | Match?
 *   -------------|--------------|-------
 *   Lions        | Lions        | ✗ (same team)
 *   Lions        | Tigers       | ✓
 *   Lions        | Leopards     | ✓
 *   Tigers       | Lions        | ✓
 *   Tigers       | Tigers       | ✗ (same team)
 *   Tigers       | Leopards     | ✓
 *   Leopards     | Lions        | ✓
 *   Leopards     | Tigers       | ✓
 *   Leopards     | Leopards     | ✗ (same team)
 * 
 *   Step 2 - Result:
 *   +------------+------------+
 *   | home_team  | away_team  |
 *   +------------+------------+
 *   | Lions      | Tigers     |
 *   | Lions      | Leopards   |
 *   | Tigers     | Lions      |
 *   | Tigers     | Leopards   |
 *   | Leopards   | Lions      |
 *   | Leopards   | Tigers     |
 *   +------------+------------+
 * 
 *   Total: 3 teams × 2 opponents = 6 matches
 * 
 * Why Self-Join?
 *   - Need to pair each team with every other team
 *   - Self-join creates Cartesian product of teams with themselves
 *   - Then filter to exclude same team pairs
 * 
 * Why <> (not equal)?
 *   - Excludes pairing a team with itself
 *   - Includes both directions: A vs B and B vs A
 *   - For 3 teams: generates 6 matches (3 × 2)
 * 
 * Alternative: Using < (less than)
 *   - Condition: t1.team_name < t2.team_name
 *   - Excludes same team AND reverse pairs
 *   - For 3 teams: generates 3 matches (only one direction)
 *   - Example: Only "Lions vs Tigers", not "Tigers vs Lions"
 *   - Use this if problem requires unique pairs only
 * 
 * Comparison:
 *   Using <> (not equal):
 *   - Lions vs Tigers ✓
 *   - Tigers vs Lions ✓
 *   - Total: 6 matches (both directions)
 * 
 *   Using < (less than):
 *   - Lions vs Tigers ✓ (Lions < Tigers alphabetically)
 *   - Tigers vs Lions ✗ (Tigers < Lions is false)
 *   - Total: 3 matches (one direction only)
 * 
 * When to use which?
 *   - Use <> if problem requires all matches (home and away)
 *   - Use < if problem requires unique pairs only
 *   - Check problem requirements carefully
 * 
 * Performance Note:
 *   - Self-join creates n × n combinations
 *   - With <> filter: n × (n - 1) rows
 *   - For large datasets, this can be expensive
 *   - Consider if you really need both directions
 */

# Write your MySQL query statement below

SELECT t1.team_name AS home_team,
       t2.team_name AS away_team
FROM Teams t1
JOIN Teams t2
  ON t1.team_name <> t2.team_name;

