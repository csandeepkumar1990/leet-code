/*
 * LeetCode 2891: Find Heavy Animals
 * 
 * Problem:
 * Write a solution to find animals with weight greater than 100 kilograms.
 * Return the result table ordered by weight in descending order.
 * Return only the name column.
 * 
 * Table: Animals
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | name        | varchar |
 * | species     | varchar |
 * | weight      | int     |
 * +-------------+---------+
 * name is the primary key for this table.
 * Each row contains information about an animal.
 * weight is in kilograms.
 * 
 * Approach: Filter with WHERE and ORDER BY
 * 
 * Key Insight:
 * - Filter animals where weight > 100
 * - Sort by weight in descending order (heaviest first)
 * - Return only the name column
 * 
 * Formula:
 *   Filter: weight > 100
 *   Sort: ORDER BY weight DESC
 *   Select: name only
 * 
 * Algorithm:
 * 1. Filter WHERE weight > 100
 * 2. ORDER BY weight DESC (heaviest first)
 * 3. SELECT name (only return name column)
 * 
 * Example:
 * 
 *   Animals:
 *   +----------+----------+--------+
 *   | name     | species  | weight |
 *   +----------+----------+--------+
 *   | Elephant | Mammal   | 5000   |
 *   | Lion     | Mammal   | 200    |
 *   | Dog      | Mammal   | 30     |
 *   | Bear     | Mammal   | 400    |
 *   | Cat      | Mammal   | 5      |
 *   | Tiger    | Mammal   | 250    |
 *   +----------+----------+--------+
 * 
 *   Step 1 - Filter weight > 100:
 *   Elephant: 5000 > 100 ✓
 *   Lion: 200 > 100 ✓
 *   Dog: 30 > 100 ✗
 *   Bear: 400 > 100 ✓
 *   Cat: 5 > 100 ✗
 *   Tiger: 250 > 100 ✓
 * 
 *   Step 2 - Sort by weight DESC:
 *   Elephant: 5000 (heaviest)
 *   Bear: 400
 *   Tiger: 250
 *   Lion: 200
 * 
 *   Result:
 *   +----------+
 *   | name     |
 *   +----------+
 *   | Elephant |
 *   | Bear     |
 *   | Tiger    |
 *   | Lion     |
 *   +----------+
 * 
 * Why WHERE weight > 100?
 *   - Filters animals with weight greater than 100 kg
 *   - Excludes lighter animals
 *   - Only heavy animals are included in result
 * 
 * Why ORDER BY weight DESC?
 *   - Heaviest animals appear first
 *   - DESC means highest weight first
 *   - Makes it easy to see the heaviest animals
 * 
 * Why SELECT name only?
 *   - Problem requires only the name column
 *   - Other columns (species, weight) are not needed
 *   - Simplifies the output
 * 
 * Comparison with Pandas:
 *   Pandas: animals[animals['weight'] > 100].sort_values(by='weight', ascending=False)[['name']]
 *   SQL:    SELECT name FROM Animals WHERE weight > 100 ORDER BY weight DESC
 *   - Both filter by weight > 100
 *   - Both sort by weight descending
 *   - Both return only name column
 * 
 * Edge Cases:
 *   - Animal with weight exactly 100: Not included (> 100, not >= 100)
 *   - Animal with weight 101: Included
 *   - Multiple animals with same weight: All included, order may vary
 *   - No animals with weight > 100: Returns empty result
 */

# Write your MySQL query statement below

SELECT name
FROM Animals
WHERE weight > 100
ORDER BY weight DESC;

