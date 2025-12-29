/*
 * LeetCode 3774: Absolute Difference of K Smallest and Largest Elements
 * 
 * Problem:
 * Write a solution to calculate the absolute difference between the sum of
 * k smallest elements and the sum of k largest elements in a dataset.
 * 
 * Table: Numbers
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | num         | int     |
 * +-------------+---------+
 * id is the primary key for this table.
 * Each row contains a number.
 * 
 * Approach: Subqueries with ORDER BY and LIMIT
 * 
 * Key Insight:
 * - Find sum of k smallest elements (ORDER BY num ASC LIMIT k)
 * - Find sum of k largest elements (ORDER BY num DESC LIMIT k)
 * - Calculate absolute difference: ABS(largest_sum - smallest_sum)
 * 
 * Formula:
 *   smallest_sum = SUM of k smallest numbers
 *   largest_sum = SUM of k largest numbers
 *   result = ABS(largest_sum - smallest_sum)
 * 
 * Algorithm:
 * 1. Calculate sum of k smallest: ORDER BY num ASC, LIMIT k, then SUM
 * 2. Calculate sum of k largest: ORDER BY num DESC, LIMIT k, then SUM
 * 3. Calculate absolute difference using ABS()
 * 
 * Example:
 * 
 *   Numbers:
 *   +----+-----+
 *   | id | num |
 *   +----+-----+
 *   | 1  | 10  |
 *   | 2  | 5   |
 *   | 3  | 20  |
 *   | 4  | 15  |
 *   | 5  | 8   |
 *   | 6  | 12  |
 *   +----+-----+
 * 
 *   k = 3
 * 
 *   Step 1 - Find k smallest (k=3):
 *   Sorted ASC: [5, 8, 10, 12, 15, 20]
 *   k smallest: [5, 8, 10]
 *   smallest_sum = 5 + 8 + 10 = 23
 * 
 *   Step 2 - Find k largest (k=3):
 *   Sorted DESC: [20, 15, 12, 10, 8, 5]
 *   k largest: [20, 15, 12]
 *   largest_sum = 20 + 15 + 12 = 47
 * 
 *   Step 3 - Calculate absolute difference:
 *   result = ABS(47 - 23) = 24
 * 
 *   Result:
 *   +------+
 *   | diff |
 *   +------+
 *   | 24   |
 *   +------+
 * 
 * SQL Approach:
 *   Using subqueries to calculate each sum separately:
 *   - Subquery 1: SUM of k smallest (ORDER BY num ASC LIMIT k)
 *   - Subquery 2: SUM of k largest (ORDER BY num DESC LIMIT k)
 *   - Main query: ABS(largest_sum - smallest_sum)
 * 
 * Why Subqueries?
 *   - Need to calculate two separate sums
 *   - Each requires different ordering (ASC vs DESC)
 *   - Subqueries allow independent calculations
 *   - Results can be combined in main query
 * 
 * ORDER BY with LIMIT:
 *   - ORDER BY num ASC: Smallest values first
 *   - LIMIT k: Take first k rows (k smallest)
 *   - ORDER BY num DESC: Largest values first
 *   - LIMIT k: Take first k rows (k largest)
 * 
 * ABS Function:
 *   - ABS(value) returns absolute value
 *   - Always positive, regardless of sign
 *   - Example: ABS(47 - 23) = 24, ABS(23 - 47) = 24
 * 
 * Alternative Approach (Single Query):
 *   Could use window functions:
 *   - ROW_NUMBER() to rank values
 *   - Filter top k and bottom k
 *   - But subquery approach is clearer
 * 
 * Edge Cases:
 *   - k = 0: Would return 0 (no elements to sum)
 *   - k = total_rows: Both sums include all elements, difference = 0
 *   - All numbers same: smallest_sum = largest_sum, difference = 0
 *   - k > total_rows: LIMIT would return fewer than k rows
 */

# Write your MySQL query statement below

SELECT 
    ABS(
        (SELECT SUM(num) 
         FROM (SELECT num FROM Numbers ORDER BY num DESC LIMIT 3) AS largest) -
        (SELECT SUM(num) 
         FROM (SELECT num FROM Numbers ORDER BY num ASC LIMIT 3) AS smallest)
    ) AS diff;

/*
 * Note: Replace LIMIT 3 with the actual value of k
 * If k is a parameter, you might need to use a stored procedure or
 * prepare statement with dynamic SQL
 */

