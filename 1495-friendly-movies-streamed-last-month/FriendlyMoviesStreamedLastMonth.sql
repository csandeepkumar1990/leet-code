/*
 * LeetCode 1495: Friendly Movies Streamed Last Month
 * 
 * Problem:
 * Write a solution to report the distinct titles of the kid-friendly movies
 * streamed in June 2020. Assume today is July 2020.
 * 
 * Table: TVProgram
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | program_date  | date    |
 * | content_id    | int     |
 * | channel       | varchar |
 * +---------------+---------+
 * (program_date, content_id) is the primary key.
 * content_id is a foreign key to Content table.
 * 
 * Table: Content
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | content_id    | int     |  (Primary Key)
 * | title         | varchar |
 * | Kids_content  | enum    |  ('Y', 'N')
 * | content_type  | varchar |
 * +---------------+---------+
 * 
 * Approach: JOIN with Multiple Filters
 * 
 * Key Insight:
 * - "Kid-friendly" = Kids_content = 'Y'
 * - "Movies" = content_type = 'Movies'
 * - "Last month" = June 2020 (since today is July 2020)
 * - Use DISTINCT to avoid duplicate titles
 * 
 * Algorithm:
 * 1. JOIN TVProgram with Content on content_id
 * 2. Filter: Kids_content = 'Y'
 * 3. Filter: content_type = 'Movies'
 * 4. Filter: program_date in June 2020
 * 5. SELECT DISTINCT title
 * 
 * Example:
 * 
 *   TVProgram:
 *   +--------------+------------+---------+
 *   | program_date | content_id | channel |
 *   +--------------+------------+---------+
 *   | 2020-06-10   | 1          | LC      |
 *   | 2020-05-11   | 2          | LC      |  ← May (wrong month)
 *   | 2020-06-12   | 3          | LC      |
 *   | 2020-06-10   | 1          | Netflix |  ← Same movie, diff channel
 *   | 2020-06-15   | 4          | ABC     |
 *   +--------------+------------+---------+
 * 
 *   Content:
 *   +------------+----------------+--------------+--------------+
 *   | content_id | title          | Kids_content | content_type |
 *   +------------+----------------+--------------+--------------+
 *   | 1          | Leetcode Movie | Y            | Movies       | ✓
 *   | 2          | Alg. for Kids  | Y            | Series       | ✗ (Series)
 *   | 3          | Database Exp   | N            | Movies       | ✗ (not kid-friendly)
 *   | 4          | Aladdin        | Y            | Movies       | ✓
 *   +------------+----------------+--------------+--------------+
 * 
 *   Filtering:
 *   - content_id=1: Kids_content=Y ✓, Movies ✓, June ✓ → Include
 *   - content_id=2: Kids_content=Y ✓, Series ✗       → Exclude
 *   - content_id=3: Kids_content=N ✗                  → Exclude
 *   - content_id=4: Kids_content=Y ✓, Movies ✓, June ✓ → Include
 * 
 *   Result (DISTINCT titles):
 *   +----------------+
 *   | title          |
 *   +----------------+
 *   | Leetcode Movie |
 *   | Aladdin        |
 *   +----------------+
 * 
 * Why DISTINCT?
 *   - Same movie can be streamed on multiple channels
 *   - Same movie can be streamed on multiple dates
 *   - We only want unique movie titles
 * 
 *   Example: "Leetcode Movie" streamed on:
 *   - 2020-06-10 on LC
 *   - 2020-06-10 on Netflix
 *   Without DISTINCT: would appear twice
 *   With DISTINCT: appears once ✓
 * 
 * Date Range:
 *   program_date >= '2020-06-01' AND program_date < '2020-07-01'
 *   - Includes: June 1 through June 30
 *   - Excludes: July 1 onwards
 */

SELECT DISTINCT c.title
FROM TVProgram t
JOIN Content c
    ON t.content_id = c.content_id
WHERE c.Kids_content = 'Y'
  AND c.content_type = 'Movies'
  AND t.program_date >= '2020-06-01'
  AND t.program_date < '2020-07-01';

