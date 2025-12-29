/*
 * LeetCode 2377: Sort the Olympic Table
 * 
 * Problem:
 * Write a solution to sort the Olympic table according to the following rules:
 * 1. Countries with more gold medals should appear first
 * 2. If there's a tie in gold medals, countries with more silver medals come next
 * 3. If there's still a tie, countries with more bronze medals follow
 * 4. If all medal counts are the same, countries are sorted alphabetically
 * 
 * Table: Olympic
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | country     | varchar |
 * | gold_medals | int     |
 * | silver_medals| int    |
 * | bronze_medals| int    |
 * +-------------+---------+
 * country is the primary key for this table.
 * Each row contains information about a country's medal count.
 * 
 * Approach: Multi-Column ORDER BY
 * 
 * Key Insight:
 * - Sort by multiple columns in priority order
 * - Gold medals first (highest priority)
 * - Then silver medals (tie-breaker for gold)
 * - Then bronze medals (tie-breaker for silver)
 * - Finally country name (tie-breaker for all medals)
 * 
 * Sorting Priority:
 *   1. gold_medals DESC (most important)
 *   2. silver_medals DESC (if gold tied)
 *   3. bronze_medals DESC (if silver tied)
 *   4. country ASC (if all medals tied)
 * 
 * Formula:
 *   ORDER BY
 *     gold_medals DESC,
 *     silver_medals DESC,
 *     bronze_medals DESC,
 *     country ASC
 * 
 * Algorithm:
 * 1. Sort by gold_medals in descending order
 * 2. For countries with same gold medals, sort by silver_medals DESC
 * 3. For countries with same gold and silver, sort by bronze_medals DESC
 * 4. For countries with all medals same, sort by country name ASC
 * 
 * Example:
 * 
 *   Olympic:
 *   +---------+-------------+---------------+---------------+
 *   | country | gold_medals | silver_medals | bronze_medals |
 *   +---------+-------------+---------------+---------------+
 *   | China   | 10          | 10            | 20            |
 *   | USA     | 10          | 20            | 20            |
 *   | Japan   | 5           | 5             | 5             |
 *   | France  | 10          | 10            | 20            |
 *   | Germany | 5           | 10            | 5             |
 *   +---------+-------------+---------------+---------------+
 * 
 *   Step 1 - Sort by gold_medals DESC:
 *   Group 1 (gold=10): China, USA, France
 *   Group 2 (gold=5): Japan, Germany
 * 
 *   Step 2 - Within gold=10, sort by silver_medals DESC:
 *   USA (silver=20) → first
 *   China (silver=10), France (silver=10) → tied
 * 
 *   Step 3 - Within China/France (gold=10, silver=10), sort by bronze_medals DESC:
 *   China (bronze=20), France (bronze=20) → tied
 * 
 *   Step 4 - Within China/France (all medals tied), sort by country ASC:
 *   China (alphabetically before France)
 * 
 *   Step 5 - Within gold=5, sort by silver_medals DESC:
 *   Germany (silver=10) → first
 *   Japan (silver=5) → second
 * 
 *   Final Result:
 *   +---------+-------------+---------------+---------------+
 *   | country | gold_medals | silver_medals | bronze_medals |
 *   +---------+-------------+---------------+---------------+
 *   | USA     | 10          | 20            | 20            |
 *   | China   | 10          | 10            | 20            |
 *   | France  | 10          | 10            | 20            |
 *   | Germany | 5           | 10            | 5             |
 *   | Japan   | 5           | 5             | 5             |
 *   +---------+-------------+---------------+---------------+
 * 
 * Multi-Column ORDER BY:
 *   - Columns are evaluated left to right
 *   - First column is primary sort key
 *   - Subsequent columns are tie-breakers
 *   - Only used when previous columns have equal values
 * 
 * Why DESC for medals?
 *   - Higher medal counts should appear first
 *   - DESC (descending) sorts from highest to lowest
 *   - Standard Olympic ranking: most medals first
 * 
 * Why ASC for country?
 *   - Alphabetical order as final tie-breaker
 *   - ASC (ascending) sorts A to Z
 *   - Ensures consistent ordering when all medals are equal
 * 
 * ORDER BY Evaluation:
 *   1. Compare gold_medals first
 *      - If different: sort by gold_medals (higher first)
 *      - If same: move to next column
 *   2. Compare silver_medals (only if gold tied)
 *      - If different: sort by silver_medals (higher first)
 *      - If same: move to next column
 *   3. Compare bronze_medals (only if gold and silver tied)
 *      - If different: sort by bronze_medals (higher first)
 *      - If same: move to next column
 *   4. Compare country (only if all medals tied)
 *      - Sort alphabetically (A to Z)
 * 
 * Alternative Approaches:
 *   - Using ROW_NUMBER() with multiple ORDER BY columns
 *   - Using CASE statements in ORDER BY (more complex)
 *   - Direct multi-column ORDER BY is simplest and most efficient
 */

# Write your MySQL query statement below

SELECT *
FROM Olympic
ORDER BY gold_medals DESC,
         silver_medals DESC,
         bronze_medals DESC,
         country ASC;

