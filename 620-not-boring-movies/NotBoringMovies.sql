/*
 * LeetCode 620: Not Boring Movies
 * 
 * Problem:
 * Write a solution to report the movies with an odd-numbered ID and a description that
 * is not "boring".
 * 
 * Return the result table ordered by rating in descending order.
 * 
 * Table: Cinema
 * +----------------+----------+
 * | Column Name    | Type     |
 * +----------------+----------+
 * | id             | int      |
 * | movie          | varchar  |
 * | description    | varchar  |
 * | rating         | float    |
 * +----------------+----------+
 * id is the primary key for this table.
 * Each row contains information about the name of a movie, its genre, and its rating.
 * rating is a 2 decimal places float in the range [0, 10]
 * 
 * Approach: WHERE Clause with Modulo and String Comparison
 * 
 * Key Insight:
 * - Filter movies with odd-numbered IDs (id % 2 = 1)
 * - Exclude movies with description = 'boring'
 * - Order results by rating in descending order (highest first)
 * 
 * Algorithm:
 * 1. SELECT * from Cinema table
 * 2. WHERE clause filters rows:
 *    - id % 2 = 1 (odd-numbered ID)
 *    - AND description <> 'boring' (description is not 'boring')
 * 3. ORDER BY rating DESC (highest rating first)
 * 
 * Time Complexity: O(n log n), where n is the number of rows in Cinema table.
 *   - Filtering: O(n) to check each row
 *   - Sorting: O(n log n) for ORDER BY
 * 
 * Space Complexity: O(k), where k is the number of movies matching the criteria.
 *   Result set contains only movies with odd IDs and non-boring descriptions.
 * 
 * Example:
 * 
 *   Cinema:
 *   +----+------------+-------------+--------+
 *   | id | movie      | description | rating |
 *   +----+------------+-------------+--------+
 *   | 1  | War        | great 3D    | 8.9    |
 *   | 2  | Science    | fiction     | 8.5    |
 *   | 3  | irish      | boring      | 6.2    |
 *   | 4  | Ice song   | Fantacy     | 8.6    |
 *   | 5  | House card | Interesting | 9.1    |
 *   +----+------------+-------------+--------+
 * 
 *   Step 1 - Filter by id % 2 = 1 (odd IDs):
 *   - id = 1: 1 % 2 = 1 ✓ (odd)
 *   - id = 2: 2 % 2 = 0 ✗ (even)
 *   - id = 3: 3 % 2 = 1 ✓ (odd)
 *   - id = 4: 4 % 2 = 0 ✗ (even)
 *   - id = 5: 5 % 2 = 1 ✓ (odd)
 *   Remaining: [1, 3, 5]
 * 
 *   Step 2 - Filter by description <> 'boring':
 *   - id = 1: description = 'great 3D' ≠ 'boring' ✓
 *   - id = 3: description = 'boring' = 'boring' ✗
 *   - id = 5: description = 'Interesting' ≠ 'boring' ✓
 *   Remaining: [1, 5]
 * 
 *   Step 3 - ORDER BY rating DESC:
 *   - id = 5: rating = 9.1 (highest)
 *   - id = 1: rating = 8.9
 * 
 *   Result:
 *   +----+------------+-------------+--------+
 *   | id | movie      | description | rating |
 *   +----+------------+-------------+--------+
 *   | 5  | House card | Interesting | 9.1    |
 *   | 1  | War        | great 3D    | 8.9    |
 *   +----+------------+-------------+--------+
 * 
 * Why Modulo Operator (% 2 = 1)?
 * 
 *   - id % 2 returns the remainder when id is divided by 2
 *   - If remainder is 1, the number is odd
 *   - If remainder is 0, the number is even
 *   - Example: 5 % 2 = 1 (odd), 4 % 2 = 0 (even)
 * 
 * Why description <> 'boring'?
 * 
 *   - <> means "not equal to"
 *   - Filters out movies with description exactly equal to 'boring'
 *   - Case-sensitive: 'Boring' ≠ 'boring' (would be included)
 *   - Alternative: description != 'boring' (same meaning)
 * 
 * Why ORDER BY rating DESC?
 * 
 *   - Problem requires results ordered by rating in descending order
 *   - DESC means highest rating first
 *   - Movies with same rating: order is not specified (implementation-dependent)
 *   - Can add secondary sort: ORDER BY rating DESC, id ASC for consistent ordering
 * 
 * Modulo Operator Examples:
 * 
 *   id  | id % 2 | Odd? | Included?
 *   ----|--------|------|-----------
 *   1   | 1      | Yes  | ✓
 *   2   | 0      | No   | ✗
 *   3   | 1      | Yes  | ✓
 *   4   | 0      | No   | ✗
 *   5   | 1      | Yes  | ✓
 *   6   | 0      | No   | ✗
 * 
 * String Comparison:
 * 
 *   description | <> 'boring' | Included?
 *   ------------|------------|-----------
 *   'great 3D'  | True       | ✓
 *   'boring'    | False      | ✗
 *   'Interesting'| True       | ✓
 *   'Boring'     | True       | ✓ (case-sensitive)
 *   'BORING'     | True       | ✓ (case-sensitive)
 * 
 * Alternative: Using != Instead of <>
 * 
 *   SELECT *
 *   FROM Cinema
 *   WHERE id % 2 = 1
 *     AND description != 'boring'
 *   ORDER BY rating DESC;
 * 
 *   - != and <> are equivalent in MySQL
 *   - Both mean "not equal to"
 *   - <> is SQL standard, != is MySQL-specific
 * 
 * Alternative: Using NOT
 * 
 *   SELECT *
 *   FROM Cinema
 *   WHERE id % 2 = 1
 *     AND NOT description = 'boring'
 *   ORDER BY rating DESC;
 * 
 *   - NOT description = 'boring' is equivalent to description <> 'boring'
 *   - More verbose but sometimes clearer
 * 
 * Edge Cases:
 * 
 *   - All movies have even IDs: Returns empty result
 *   - All movies have 'boring' description: Returns empty result
 *   - No movies match both conditions: Returns empty result
 *   - Movies with NULL description: NULL <> 'boring' evaluates to NULL (excluded)
 *   - Movies with NULL rating: Included if other conditions match (sorted last)
 *   - Case sensitivity: 'Boring' ≠ 'boring' (would be included)
 * 
 * NULL Handling:
 * 
 *   - If description is NULL, description <> 'boring' evaluates to NULL
 *   - NULL is treated as FALSE in WHERE clause
 *   - Movies with NULL description are excluded
 *   - If rating is NULL, ORDER BY treats it as lowest value (appears last)
 * 
 * Performance Considerations:
 * 
 *   - Modulo operation is fast (simple arithmetic)
 *   - String comparison is fast for exact matches
 *   - ORDER BY requires sorting, which can be expensive for large tables
 *   - Consider indexing on (id, description, rating) for better performance
 */

# Write your MySQL query statement below

SELECT *
FROM Cinema
WHERE id % 2 = 1
  AND description <> 'boring'
ORDER BY rating DESC;

