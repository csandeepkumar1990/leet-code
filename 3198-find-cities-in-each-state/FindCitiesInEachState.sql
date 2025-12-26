/*
 * LeetCode 3198: Find Cities in Each State
 * 
 * Problem:
 * Write a solution to find all cities in each state.
 * Return the result table with state and cities columns.
 * Cities should be concatenated and separated by commas, sorted alphabetically.
 * The result table should be ordered by state in ascending order.
 * 
 * Table: Cities
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | city        | varchar |
 * | state       | varchar |
 * +-------------+---------+
 * (city, state) is the primary key for this table.
 * Each row contains information about a city and its state.
 * 
 * Approach: GROUP BY with GROUP_CONCAT
 * 
 * Key Insight:
 * - Group cities by state
 * - Concatenate city names within each state
 * - Sort cities alphabetically within each group
 * - Use GROUP_CONCAT with ORDER BY and SEPARATOR
 * 
 * GROUP_CONCAT Function:
 *   GROUP_CONCAT(expression ORDER BY sort_expr SEPARATOR 'delimiter')
 *   - Concatenates values from multiple rows into a single string
 *   - ORDER BY: sorts values before concatenation
 *   - SEPARATOR: specifies delimiter between values (default is comma)
 *   - Only works with GROUP BY
 * 
 * Formula:
 *   For each state:
 *   cities = GROUP_CONCAT(city ORDER BY city ASC SEPARATOR ', ')
 * 
 * Algorithm:
 * 1. GROUP BY state to aggregate cities per state
 * 2. Use GROUP_CONCAT to concatenate city names
 * 3. ORDER BY city ASC within GROUP_CONCAT to sort alphabetically
 * 4. Use SEPARATOR ', ' to format output nicely
 * 5. ORDER BY state to sort final results by state
 * 
 * Example:
 * 
 *   Cities:
 *   +--------------+------------+
 *   | city         | state      |
 *   +--------------+------------+
 *   | Los Angeles  | California |
 *   | San Diego    | California |
 *   | San Francisco| California |
 *   | Buffalo      | New York   |
 *   | New York City| New York   |
 *   | Rochester    | New York   |
 *   | Austin       | Texas      |
 *   | Dallas       | Texas      |
 *   | Houston      | Texas      |
 *   +--------------+------------+
 * 
 *   Step 1 - Group by state:
 *   California: [Los Angeles, San Diego, San Francisco]
 *   New York:   [Buffalo, New York City, Rochester]
 *   Texas:      [Austin, Dallas, Houston]
 * 
 *   Step 2 - Sort cities alphabetically within each state:
 *   California: [Los Angeles, San Diego, San Francisco] (already sorted)
 *   New York:   [Buffalo, New York City, Rochester] (already sorted)
 *   Texas:      [Austin, Dallas, Houston] (already sorted)
 * 
 *   Step 3 - Concatenate with ', ' separator:
 *   California: "Los Angeles, San Diego, San Francisco"
 *   New York:   "Buffalo, New York City, Rochester"
 *   Texas:      "Austin, Dallas, Houston"
 * 
 *   Step 4 - Order by state:
 *   California, New York, Texas (alphabetically)
 * 
 *   Result:
 *   +------------+------------------------------------------+
 *   | state      | cities                                   |
 *   +------------+------------------------------------------+
 *   | California | Los Angeles, San Diego, San Francisco   |
 *   | New York   | Buffalo, New York City, Rochester       |
 *   | Texas      | Austin, Dallas, Houston                  |
 *   +------------+------------------------------------------+
 * 
 * GROUP_CONCAT Syntax:
 *   GROUP_CONCAT(
 *       expression
 *       [ORDER BY sort_expr [ASC | DESC]]
 *       [SEPARATOR 'delimiter']
 *   )
 * 
 *   - expression: column or expression to concatenate
 *   - ORDER BY: optional sorting within the concatenation
 *   - SEPARATOR: optional delimiter (default is comma ',')
 * 
 * Why ORDER BY city ASC in GROUP_CONCAT?
 *   - Ensures cities are listed alphabetically within each state
 *   - Makes output more readable and predictable
 *   - Problem requirement: cities should be sorted alphabetically
 * 
 * Why SEPARATOR ', '?
 *   - Default separator is ',' (comma only)
 *   - ', ' (comma + space) is more readable
 *   - Example: "City1, City2, City3" vs "City1,City2,City3"
 * 
 * Why ORDER BY state at the end?
 *   - Sorts the final result set by state name
 *   - Makes output ordered and easier to read
 *   - Problem requirement: results ordered by state
 * 
 * GROUP_CONCAT Limitations:
 *   - Maximum length: controlled by group_concat_max_len (default 1024)
 *   - For very long concatenations, may need to increase this limit
 *   - SET SESSION group_concat_max_len = 10000; (if needed)
 * 
 * Alternative Approaches:
 *   - Using STRING_AGG (PostgreSQL, SQL Server)
 *   - Using LISTAGG (Oracle)
 *   - Manual concatenation with subqueries (more complex)
 */

# Write your MySQL query statement below

SELECT 
    state,
    GROUP_CONCAT(city ORDER BY city ASC SEPARATOR ', ') AS cities
FROM Cities
GROUP BY state
ORDER BY state;

