/*
 * LeetCode 1294: Weather Type in Each Country
 * 
 * Problem:
 * Write a solution to find the type of weather in each country for November 2019.
 * 
 * The type of weather is:
 * - Cold if the average weather_state is less than or equal 15
 * - Hot if the average weather_state is greater than or equal to 25
 * - Warm otherwise
 * 
 * Return the result table in any order.
 * 
 * Table: Countries
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | country_id    | int     |
 * | country_name  | varchar |
 * +---------------+---------+
 * country_id is the primary key for this table.
 * Each row of this table contains the ID and the name of one country.
 * 
 * Table: Weather
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | country_id    | int     |
 * | weather_state | int     |
 * | day           | date    |
 * +---------------+---------+
 * (country_id, day) is the primary key for this table.
 * Each row of this table indicates the weather state in a country for one day.
 * 
 * Approach: JOIN + Date Filter + GROUP BY + CASE Expression
 * 
 * Key Insight:
 * - Join Countries with Weather to get weather data per country
 * - Filter weather records for November 2019
 * - Group by country to calculate average weather_state
 * - Use CASE expression to categorize based on average temperature
 * 
 * Algorithm:
 * 1. JOIN Countries with Weather on country_id
 * 2. WHERE clause filters dates: November 2019 (2019-11-01 to 2019-11-30)
 * 3. GROUP BY country_id and country_name
 * 4. Calculate AVG(weather_state) for each country
 * 5. Use CASE to categorize: <= 15 (Cold), >= 25 (Hot), else (Warm)
 * 
 * Time Complexity: O(n), where n is the number of weather records in November 2019.
 *   - JOIN: O(n) to match countries
 *   - WHERE filter: O(n) to filter dates
 *   - GROUP BY: O(n) to aggregate
 * 
 * Space Complexity: O(k), where k is the number of countries.
 *   - Result set contains one row per country
 * 
 * Example:
 * 
 *   Countries:
 *   +------------+--------------+
 *   | country_id | country_name |
 *   +------------+--------------+
 *   | 2          | USA          |
 *   | 3          | Australia    |
 *   | 7          | Peru         |
 *   | 5          | China        |
 *   | 8          | Morocco      |
 *   | 9          | Spain        |
 *   +------------+--------------+
 * 
 *   Weather (November 2019 only):
 *   +------------+---------------+------------+
 *   | country_id | weather_state | day        |
 *   +------------+---------------+------------+
 *   | 2          | 15            | 2019-11-01 |
 *   | 2          | 12            | 2019-11-02 |
 *   | 2          | 12            | 2019-11-03 |
 *   | 3          | -2            | 2019-11-23 |
 *   | 3          | -5            | 2019-11-24 |
 *   | 3          | -5            | 2019-11-25 |
 *   | 7          | 25            | 2019-11-21 |
 *   | 7          | 22            | 2019-11-22 |
 *   | 7          | 20            | 2019-11-23 |
 *   | 7          | 23            | 2019-11-24 |
 *   | 7          | 21            | 2019-11-25 |
 *   | 5          | 16            | 2019-11-07 |
 *   | 5          | 18            | 2019-11-09 |
 *   | 5          | 21            | 2019-11-23 |
 *   | 5          | 20            | 2019-11-28 |
 *   | 8          | 25            | 2019-11-05 |
 *   | 8          | 27            | 2019-11-06 |
 *   | 8          | 31            | 2019-11-07 |
 *   | 8          | 28            | 2019-11-08 |
 *   | 8          | 21            | 2019-11-09 |
 *   | 9          | 7             | 2019-10-23 |  ← October, excluded
 *   | 9          | 3             | 2019-12-23 |  ← December, excluded
 *   +------------+---------------+------------+
 * 
 *   Step 1 - JOIN and Filter November 2019:
 *   - USA (2): [15, 12, 12] → AVG = 13
 *   - Australia (3): [-2, -5, -5] → AVG = -4
 *   - Peru (7): [25, 22, 20, 23, 21] → AVG = 22.2
 *   - China (5): [16, 18, 21, 20] → AVG = 18.75
 *   - Morocco (8): [25, 27, 31, 28, 21] → AVG = 26.4
 *   - Spain (9): No November data → excluded (no JOIN match)
 * 
 *   Step 2 - GROUP BY and Calculate AVG:
 *   - USA: AVG = 13 → <= 15 → Cold
 *   - Australia: AVG = -4 → <= 15 → Cold
 *   - Peru: AVG = 22.2 → 15 < 22.2 < 25 → Warm
 *   - China: AVG = 18.75 → 15 < 18.75 < 25 → Warm
 *   - Morocco: AVG = 26.4 → >= 25 → Hot
 * 
 *   Result:
 *   +--------------+-------------+
 *   | country_name | weather_type|
 *   +--------------+-------------+
 *   | USA          | Cold        |
 *   | Australia    | Cold        |
 *   | Peru         | Warm        |
 *   | China        | Warm        |
 *   | Morocco      | Hot         |
 *   +--------------+-------------+
 * 
 * Why Date Range: day >= '2019-11-01' AND day < '2019-12-01'?
 * 
 *   - >= '2019-11-01': Includes November 1st and onwards
 *   - < '2019-12-01': Excludes December 1st and onwards
 *   - This covers all of November 2019 (Nov 1-30)
 *   - Using < '2019-12-01' is cleaner than <= '2019-11-30'
 *   - Handles leap years correctly (though not relevant for November)
 * 
 * Alternative Date Filters:
 * 
 *   Option 1 (used here):
 *   WHERE day >= '2019-11-01' AND day < '2019-12-01'
 *   - Clean and efficient
 *   - Uses indexes well
 * 
 *   Option 2:
 *   WHERE YEAR(day) = 2019 AND MONTH(day) = 11
 *   - More readable
 *   - May prevent index usage
 * 
 *   Option 3:
 *   WHERE day BETWEEN '2019-11-01' AND '2019-11-30'
 *   - Inclusive on both ends
 *   - Works but less flexible
 * 
 * CASE Expression Logic:
 * 
 *   CASE
 *       WHEN AVG(weather_state) <= 15 THEN 'Cold'
 *       WHEN AVG(weather_state) >= 25 THEN 'Hot'
 *       ELSE 'Warm'
 *   END
 * 
 *   - First checks if <= 15 (Cold)
 *   - Then checks if >= 25 (Hot)
 *   - Otherwise 15 < avg < 25 (Warm)
 *   - Order matters: checks conditions sequentially
 * 
 * Why GROUP BY country_id AND country_name?
 * 
 *   - GROUP BY country_id: Groups weather records per country
 *   - country_name is included for SELECT clause
 *   - Since country_name functionally depends on country_id, both are needed
 *   - Alternative: GROUP BY country_id only, but some databases require all non-aggregated columns
 * 
 * Why AVG() in CASE (not in separate column)?
 * 
 *   - CASE expression can use aggregate functions
 *   - AVG() is calculated once per group
 *   - More efficient than calculating AVG separately
 *   - Cleaner code (all logic in one expression)
 * 
 * Temperature Ranges:
 * 
 *   - Cold: AVG <= 15
 *     Examples: 10, 15, -5, 0
 * 
 *   - Warm: 15 < AVG < 25
 *     Examples: 16, 20, 24
 * 
 *   - Hot: AVG >= 25
 *     Examples: 25, 30, 35
 * 
 * Edge Cases:
 * 
 *   - Country with no November data: Excluded (no JOIN match)
 *   - Country with single day: AVG = that day's temperature
 *   - Country with all same temperature: AVG = that temperature
 *   - Country with exactly 15: Cold (<= 15)
 *   - Country with exactly 25: Hot (>= 25)
 *   - Country with exactly 20: Warm (15 < 20 < 25)
 * 
 * NULL Handling:
 * 
 *   - If weather_state is NULL, AVG() ignores NULL values
 *   - AVG of [10, NULL, 20] = 15 (not NULL)
 *   - If all values are NULL, AVG returns NULL
 *   - CASE with NULL: ELSE 'Warm' would be selected (may not be desired)
 * 
 * Performance Considerations:
 * 
 *   - Date range filter should use index on day column
 *   - JOIN on country_id should use index
 *   - GROUP BY requires sorting or hashing
 *   - Consider composite index on (country_id, day) for Weather table
 */

# Write your MySQL query statement below

SELECT
    c.country_name,
    CASE
        WHEN AVG(w.weather_state) <= 15 THEN 'Cold'
        WHEN AVG(w.weather_state) >= 25 THEN 'Hot'
        ELSE 'Warm'
    END AS weather_type
FROM Countries c
JOIN Weather w
    ON c.country_id = w.country_id
WHERE w.day >= '2019-11-01'
  AND w.day < '2019-12-01'
GROUP BY c.country_id, c.country_name;

