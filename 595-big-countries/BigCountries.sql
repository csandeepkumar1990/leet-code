/*
 * LeetCode 595: Big Countries
 * 
 * Problem:
 * A country is big if:
 *   - it has an area of at least three million (i.e., 3,000,000 km²), or
 *   - it has a population of at least twenty-five million (i.e., 25,000,000).
 * 
 * Write a solution to find the name, population, and area of the big countries.
 * Return the result table in any order.
 * 
 * Table: World
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | name        | varchar |
 * | continent   | varchar |
 * | area        | int     |
 * | population  | int     |
 * | gdp         | bigint  |
 * +-------------+---------+
 * name is the primary key for this table.
 * Each row contains information about the name of a country, the continent to which
 * it belongs, its area, the population, and its GDP value.
 * 
 * Approach: Simple WHERE Clause with OR Condition
 * 
 * Key Insight:
 * - A country is "big" if it meets EITHER condition (OR, not AND)
 * - Filter countries where area >= 3,000,000 OR population >= 25,000,000
 * - Select only name, population, and area columns
 * 
 * Algorithm:
 * 1. SELECT name, population, area from World table
 * 2. WHERE clause filters rows:
 *    - area >= 3000000 (at least 3 million km²), OR
 *    - population >= 25000000 (at least 25 million people)
 * 3. Return matching countries
 * 
 * Time Complexity: O(n), where n is the number of rows in World table.
 *   Full table scan to check each row against the conditions.
 * 
 * Space Complexity: O(k), where k is the number of big countries.
 *   Result set contains only big countries.
 * 
 * Example:
 * 
 *   World:
 *   +-----------------+------------+---------+------------+--------------+
 *   | name            | continent  | area    | population | gdp          |
 *   +-----------------+------------+---------+------------+--------------+
 *   | Afghanistan     | Asia       | 652230  | 25500100   | 20343000000  |
 *   | Albania         | Europe     | 28748   | 2831741    | 12960000000  |
 *   | Algeria         | Africa     | 2381741 | 37100000   | 188681000000 |
 *   | Andorra         | Europe     | 468     | 78115      | 3712000000   |
 *   | Angola          | Africa     | 1246700 | 20609294   | 100990000000 |
 *   | Russia          | Europe     | 17098242| 146000000  | 1283162000000|
 *   | Canada          | North America| 9984670| 38000000  | 1780000000000|
 *   +-----------------+------------+---------+------------+--------------+
 * 
 *   Step 1 - Apply WHERE conditions:
 *   - Afghanistan: area = 652230 < 3000000 ✗, population = 25500100 >= 25000000 ✓ → Include
 *   - Albania: area = 28748 < 3000000 ✗, population = 2831741 < 25000000 ✗ → Exclude
 *   - Algeria: area = 2381741 < 3000000 ✗, population = 37100000 >= 25000000 ✓ → Include
 *   - Andorra: area = 468 < 3000000 ✗, population = 78115 < 25000000 ✗ → Exclude
 *   - Angola: area = 1246700 < 3000000 ✗, population = 20609294 < 25000000 ✗ → Exclude
 *   - Russia: area = 17098242 >= 3000000 ✓ → Include (meets area condition)
 *   - Canada: area = 9984670 >= 3000000 ✓ → Include (meets area condition)
 * 
 *   Result:
 *   +-----------------+------------+---------+
 *   | name            | population | area    |
 *   +-----------------+------------+---------+
 *   | Afghanistan     | 25500100   | 652230  |
 *   | Algeria         | 37100000   | 2381741 |
 *   | Russia          | 146000000  | 17098242|
 *   | Canada          | 38000000   | 9984670 |
 *   +-----------------+------------+---------+
 * 
 * Why OR (not AND)?
 * - Problem states: "area >= 3M OR population >= 25M"
 * - A country is big if it meets EITHER condition
 * - Using AND would require BOTH conditions (too restrictive)
 * - Example: Russia has big area but might not have big population
 * 
 * OR vs AND:
 * 
 *   OR (correct):
 *   - area >= 3000000 OR population >= 25000000
 *   - Includes countries that meet either condition
 *   - More inclusive: larger result set
 * 
 *   AND (incorrect):
 *   - area >= 3000000 AND population >= 25000000
 *   - Requires both conditions to be true
 *   - More restrictive: smaller result set
 * 
 * Why No ORDER BY?
 * - Problem states "Return the result table in any order"
 * - No specific ordering requirement
 * - Can add ORDER BY if needed (e.g., ORDER BY name)
 * 
 * Column Selection:
 * - Problem asks for: name, population, area
 * - Don't need continent or gdp columns
 * - SELECT only required columns (more efficient)
 * 
 * Edge Cases:
 * - Country with area exactly 3,000,000: Included (>=)
 * - Country with population exactly 25,000,000: Included (>=)
 * - Country with area 2,999,999: Excluded (doesn't meet area condition)
 * - Country with population 24,999,999: Excluded (doesn't meet population condition)
 * - Country meeting both conditions: Included (OR is satisfied)
 * - Country meeting neither condition: Excluded
 * 
 * NULL Values:
 * - If area or population is NULL, the comparison returns NULL
 * - NULL OR anything = NULL (not TRUE)
 * - NULL values would be excluded from results
 * - This is typically the desired behavior
 */

# Write your MySQL query statement below

SELECT
    name,
    population,
    area
FROM World
WHERE area >= 3000000
   OR population >= 25000000;

