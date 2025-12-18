/*
 * LeetCode 1179: Reformat Department Table
 * 
 * Problem:
 * Reformat the Department table so that there is a row for each id and
 * a column for each month's revenue. Return the result in any order.
 * 
 * Table: Department
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | revenue     | int     |
 * | month       | varchar |
 * +-------------+---------+
 * (id, month) is the primary key.
 * Month values: "Jan", "Feb", "Mar", ... "Dec"
 * 
 * Approach: PIVOT using CASE WHEN + GROUP BY
 * 
 * Key Insight:
 * - This is a classic row-to-column pivot transformation
 * - Use CASE WHEN to conditionally select revenue for each month
 * - Use MAX (or SUM) as aggregation to collapse multiple rows per id
 * - GROUP BY id to get one row per department
 * 
 * Why MAX with CASE WHEN?
 * - CASE WHEN returns revenue for matching month, NULL otherwise
 * - MAX ignores NULLs and picks the actual revenue value
 * - Since (id, month) is unique, there's only one value per cell
 * 
 * Before (Rows):                    After (Columns):
 * +----+-------+---------+          +----+----------+----------+-----+
 * | id | month | revenue |          | id | Jan_Rev  | Feb_Rev  | ... |
 * +----+-------+---------+          +----+----------+----------+-----+
 * | 1  | Jan   | 8000    |   →      | 1  | 8000     | 7000     | ... |
 * | 1  | Feb   | 7000    |          | 2  | 9000     | NULL     | ... |
 * | 1  | Mar   | 6000    |          | 3  | NULL     | 10000    | ... |
 * | 2  | Jan   | 9000    |          +----+----------+----------+-----+
 * | 3  | Feb   | 10000   |
 * +----+-------+---------+
 * 
 * How CASE WHEN Works (for id=1):
 * 
 *   Row 1: month='Jan', revenue=8000
 *   Row 2: month='Feb', revenue=7000
 *   Row 3: month='Mar', revenue=6000
 * 
 *   CASE WHEN month='Jan' THEN revenue END:
 *     Row 1: 8000  ← matches
 *     Row 2: NULL  ← doesn't match
 *     Row 3: NULL  ← doesn't match
 *   
 *   MAX(8000, NULL, NULL) = 8000 ✓
 * 
 *   CASE WHEN month='Feb' THEN revenue END:
 *     Row 1: NULL
 *     Row 2: 7000  ← matches
 *     Row 3: NULL
 *   
 *   MAX(NULL, 7000, NULL) = 7000 ✓
 * 
 * Visual (Pivot Transformation):
 * 
 *   Original data (vertical):
 *   
 *   id=1: Jan→8000, Feb→7000, Mar→6000
 *   id=2: Jan→9000
 *   id=3: Feb→10000
 *   
 *   Pivoted (horizontal):
 *   
 *   id │ Jan  │ Feb   │ Mar  │ Apr  │ ...
 *   ───┼──────┼───────┼──────┼──────┼────
 *   1  │ 8000 │ 7000  │ 6000 │ NULL │ ...
 *   2  │ 9000 │ NULL  │ NULL │ NULL │ ...
 *   3  │ NULL │ 10000 │ NULL │ NULL │ ...
 * 
 * Note: NULL means no revenue data for that month
 */

SELECT
    id,
    MAX(CASE WHEN month = 'Jan' THEN revenue END) AS Jan_Revenue,
    MAX(CASE WHEN month = 'Feb' THEN revenue END) AS Feb_Revenue,
    MAX(CASE WHEN month = 'Mar' THEN revenue END) AS Mar_Revenue,
    MAX(CASE WHEN month = 'Apr' THEN revenue END) AS Apr_Revenue,
    MAX(CASE WHEN month = 'May' THEN revenue END) AS May_Revenue,
    MAX(CASE WHEN month = 'Jun' THEN revenue END) AS Jun_Revenue,
    MAX(CASE WHEN month = 'Jul' THEN revenue END) AS Jul_Revenue,
    MAX(CASE WHEN month = 'Aug' THEN revenue END) AS Aug_Revenue,
    MAX(CASE WHEN month = 'Sep' THEN revenue END) AS Sep_Revenue,
    MAX(CASE WHEN month = 'Oct' THEN revenue END) AS Oct_Revenue,
    MAX(CASE WHEN month = 'Nov' THEN revenue END) AS Nov_Revenue,
    MAX(CASE WHEN month = 'Dec' THEN revenue END) AS Dec_Revenue
FROM Department
GROUP BY id;

