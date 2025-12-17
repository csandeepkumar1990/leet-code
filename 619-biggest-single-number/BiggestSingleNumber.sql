/*
 * LeetCode 619: Biggest Single Number
 * 
 * Problem:
 * A single number is a number that appeared only once in the MyNumbers table.
 * Find the largest single number. If there is no single number, return null.
 * 
 * Table: MyNumbers
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | num         | int  |
 * +-------------+------+
 * This table may contain duplicates (no primary key).
 * Each row contains an integer.
 * 
 * Example 1:
 * Input:
 * +-----+
 * | num |
 * +-----+
 * | 8   |
 * | 8   |
 * | 3   |
 * | 3   |
 * | 1   |
 * | 4   |
 * | 5   |
 * | 6   |
 * +-----+
 * 
 * Output:
 * +-----+
 * | num |
 * +-----+
 * | 6   |
 * +-----+
 * 
 * Explanation:
 * - 8 appears 2 times → not single ❌
 * - 3 appears 2 times → not single ❌
 * - 1 appears 1 time → single ✅
 * - 4 appears 1 time → single ✅
 * - 5 appears 1 time → single ✅
 * - 6 appears 1 time → single ✅
 * Single numbers: 1, 4, 5, 6 → largest is 6
 * 
 * Example 2:
 * Input: All numbers appear more than once
 * Output: null (no single numbers exist)
 * 
 * Approach:
 * - Subquery: Find all single numbers using GROUP BY and HAVING COUNT = 1
 * - Outer query: Use MAX() to find the largest single number
 * - MAX() returns NULL if the subquery returns no rows (handles no single numbers)
 */

# Write your MySQL query statement below

SELECT MAX(num) AS num
FROM (
    SELECT num
    FROM MyNumbers
    GROUP BY num
    HAVING COUNT(num) = 1
) AS SingleNumbers;

