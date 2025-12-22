/*
 * LeetCode 1421: NPV Queries
 * 
 * Problem:
 * Write a solution to find the npv of each query of the Queries table.
 * Return the result table in any order.
 * 
 * Table: NPV
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | id            | int     |
 * | year          | int     |
 * | npv           | int     |
 * +---------------+---------+
 * (id, year) is the primary key of this table.
 * The table has information about the npv of each investment of different
 * companies in different years.
 * 
 * Table: Queries
 * +---------------+---------+
 * | Column Name   | Type    |
 * | id            | int     |
 * | year          | int     |
 * +---------------+---------+
 * (id, year) is the primary key of this table.
 * The table has information about the queries.
 * 
 * Example:
 * Input:
 * NPV table:
 * +------+--------+--------+
 * | id   | year   | npv    |
 * +------+--------+--------+
 * | 1    | 2018   | 100    |
 * | 7    | 2020   | 30     |
 * | 13   | 2019   | 40     |
 * | 1    | 2019   | 113    |
 * | 2    | 2008   | 121    |
 * | 3    | 2009   | 12     |
 * | 11   | 2020   | 99     |
 * | 7    | 2019   | 0      |
 * +------+--------+--------+
 * 
 * Queries table:
 * +------+--------+
 * | id   | year   |
 * +------+--------+
 * | 1    | 2019   |
 * | 2    | 2008   |
 * | 3    | 2009   |
 * | 7    | 2018   |
 * | 7    | 2019   |
 * | 7    | 2020   |
 * | 13   | 2019   |
 * +------+--------+
 * 
 * Output:
 * +------+--------+--------+
 * | id   | year   | npv    |
 * +------+--------+--------+
 * | 1    | 2019   | 113    |
 * | 2    | 2008   | 121    |
 * | 3    | 2009   | 12     |
 * | 7    | 2018   | 0      |
 * | 7    | 2019   | 0      |
 * | 7    | 2020   | 30     |
 * | 13   | 2019   | 40     |
 * +------+--------+--------+
 * 
 * Explanation:
 * - The npv of (1, 2019) is 113 (present in NPV table)
 * - The npv of (2, 2008) is 121 (present in NPV table)
 * - The npv of (3, 2009) is 12 (present in NPV table)
 * - The npv of (7, 2018) is not present in NPV table, so we consider it 0
 * - The npv of (7, 2019) is 0 (present in NPV table, the value is 0)
 * - The npv of (7, 2020) is 30 (present in NPV table)
 * - The npv of (13, 2019) is 40 (present in NPV table)
 * 
 * Approach: LEFT JOIN with COALESCE
 * 
 * Key Insight:
 * - We need ALL queries from the Queries table
 * - Some queries may not have a corresponding npv in the NPV table
 * - Use LEFT JOIN to include all queries, with NULL for missing npv
 * - Use COALESCE to replace NULL with 0
 * - Join condition requires BOTH id AND year to match
 * 
 * Algorithm:
 * 1. Start with Queries table (left table)
 * 2. LEFT JOIN with NPV on both id AND year
 * 3. Select id, year, and COALESCE(n.npv, 0) as npv
 * 4. Queries without matching npv will have 0 in npv column
 * 
 * Why LEFT JOIN?
 * 
 *   - INNER JOIN would exclude queries without matching npv
 *   - RIGHT JOIN would exclude queries not in NPV (but all queries should be shown)
 *   - LEFT JOIN ensures all queries are included, with 0 for missing npv
 * 
 * Why Join on Both id AND year?
 * 
 *   - NPV table has composite primary key (id, year)
 *   - Same id can have different npv values in different years
 *   - We need to match both id and year to get the correct npv
 *   - Example: id=1 has npv=100 in 2018 and npv=113 in 2019
 * 
 * Why COALESCE?
 * 
 *   - LEFT JOIN returns NULL when no match is found
 *   - Problem requires 0 for missing npv values
 *   - COALESCE(n.npv, 0) returns npv if not NULL, otherwise 0
 *   - Alternative: IFNULL(n.npv, 0) works the same in MySQL
 * 
 * Visual Example:
 * 
 *   Queries:          NPV:              Result:
 *   +----+----+      +----+----+----+  +----+----+----+
 *   | id |year|      | id |year| npv|  | id |year| npv|
 *   +----+----+      +----+----+----+  +----+----+----+
 *   | 1  |2019|      | 1  |2018| 100|  | 1  |2019| 113|
 *   | 2  |2008|      | 1  |2019| 113|  | 2  |2008| 121|
 *   | 7  |2018|      | 7  |2019| 0  |  | 7  |2018| 0  |
 *   | 7  |2019|      | 7  |2020| 30 |  | 7  |2019| 0  |
 *   +----+----+      +----+----+----+  | 7  |2020| 30 |
 *                                      +----+----+----+
 * 
 *   LEFT JOIN matches:
 *   - (1, 2019) → npv=113 ✓
 *   - (2, 2008) → npv=121 ✓
 *   - (7, 2018) → no match → npv=NULL → COALESCE → 0
 *   - (7, 2019) → npv=0 ✓
 *   - (7, 2020) → npv=30 ✓
 * 
 * Edge Cases:
 * 
 *   - All queries have npv: All npv values will be non-zero
 *   - No queries have npv: All npv values will be 0
 *   - Some queries have npv: Mixed zero and non-zero values
 *   - Empty Queries table: Returns empty result
 *   - Empty NPV table: All npv values will be 0
 *   - npv value is 0: Should return 0 (not NULL), handled by COALESCE
 * 
 * Important Notes:
 * 
 *   - Join condition uses AND: q.id = n.id AND q.year = n.year
 *   - This ensures we match the exact (id, year) combination
 *   - COALESCE handles both NULL (no match) and actual 0 values correctly
 */

# Write your MySQL query statement below

SELECT 
    q.id,
    q.year,
    COALESCE(n.npv, 0) AS npv
FROM Queries q
LEFT JOIN NPV n
ON q.id = n.id AND q.year = n.year;

