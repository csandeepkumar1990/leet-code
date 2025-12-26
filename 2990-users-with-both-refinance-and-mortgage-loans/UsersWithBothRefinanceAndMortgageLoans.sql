/*
 * LeetCode 2990: Users With Both Refinance and Mortgage Loans
 * 
 * Problem:
 * Write a solution to find all users who have both a Refinance loan
 * and a Mortgage loan.
 * Return the results ordered by user_id in ascending order.
 * 
 * Table: Loans
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | loan_id     | int     |
 * | user_id     | int     |
 * | loan_type   | varchar |
 * +-------------+---------+
 * loan_id is the primary key for this table.
 * Each row contains information about a loan including its ID, user ID, and type.
 * 
 * Approach: GROUP BY with HAVING and Conditional SUM
 * 
 * Key Insight:
 * - Need to find users who have BOTH 'Refinance' AND 'Mortgage' loan types
 * - Group by user_id to aggregate loans per user
 * - Use conditional SUM to count occurrences of each loan type
 * - Check if both counts are > 0
 * 
 * Formula:
 *   refinance_count = SUM(loan_type = 'Refinance')
 *   mortgage_count = SUM(loan_type = 'Mortgage')
 *   Filter: refinance_count > 0 AND mortgage_count > 0
 * 
 * Algorithm:
 * 1. GROUP BY user_id to aggregate loans per user
 * 2. Calculate SUM(loan_type = 'Refinance') - counts Refinance loans
 * 3. Calculate SUM(loan_type = 'Mortgage') - counts Mortgage loans
 * 4. Use HAVING to filter users where both sums > 0
 * 5. ORDER BY user_id for ascending order
 * 
 * Example:
 * 
 *   Loans:
 *   +---------+---------+------------+
 *   | loan_id | user_id | loan_type  |
 *   +---------+---------+------------+
 *   | 683     | 101     | Refinance  |
 *   | 911     | 102     | Mortgage   |
 *   | 547     | 101     | Mortgage   |
 *   | 564     | 102     | Refinance  |
 *   | 789     | 103     | Mortgage   |
 *   | 234     | 103     | Refinance  |
 *   | 890     | 101     | Refinance  |
 *   | 456     | 104     | Mortgage   |
 *   +---------+---------+------------+
 * 
 *   Step 1 - Group by user_id:
 *   User 101: Refinance, Mortgage, Refinance
 *   User 102: Mortgage, Refinance
 *   User 103: Mortgage, Refinance
 *   User 104: Mortgage
 * 
 *   Step 2 - Calculate conditional sums:
 *   User 101: SUM(loan_type = 'Refinance') = 1 + 0 + 1 = 2 > 0 ✓
 *             SUM(loan_type = 'Mortgage') = 0 + 1 + 0 = 1 > 0 ✓
 *             → Has both!
 * 
 *   User 102: SUM(loan_type = 'Refinance') = 0 + 1 = 1 > 0 ✓
 *             SUM(loan_type = 'Mortgage') = 1 + 0 = 1 > 0 ✓
 *             → Has both!
 * 
 *   User 103: SUM(loan_type = 'Refinance') = 0 + 1 = 1 > 0 ✓
 *             SUM(loan_type = 'Mortgage') = 1 + 0 = 1 > 0 ✓
 *             → Has both!
 * 
 *   User 104: SUM(loan_type = 'Refinance') = 0 = 0 ✗
 *             SUM(loan_type = 'Mortgage') = 1 > 0 ✓
 *             → Missing Refinance!
 * 
 *   Result:
 *   +---------+
 *   | user_id |
 *   +---------+
 *   | 101     |
 *   | 102     |
 *   | 103     |
 *   +---------+
 * 
 * Why SUM(loan_type = 'Refinance') > 0?
 *   - In MySQL, boolean expressions return 1 (true) or 0 (false)
 *   - loan_type = 'Refinance' evaluates to 1 when true, 0 when false
 *   - SUM(loan_type = 'Refinance') counts how many rows match the condition
 *   - If sum > 0, it means at least one row has loan_type = 'Refinance'
 *   - This is a common pattern for conditional counting/existence checking
 * 
 * Why > 0 instead of = 1 or >= 1?
 *   - > 0 checks for existence (at least one occurrence)
 *   - = 1 would only match users with exactly one Refinance loan
 *   - >= 1 is equivalent to > 0, but > 0 is more idiomatic
 *   - We don't care about the exact count, just that it exists
 * 
 * Alternative Approaches:
 *   - COUNT(CASE WHEN loan_type = 'Refinance' THEN 1 END) > 0
 *   - COUNTIF(loan_type = 'Refinance') > 0 (some databases)
 *   - Using EXISTS with subqueries (more verbose)
 *   - Using INTERSECT (if supported)
 * 
 * Performance Note:
 *   - SUM with boolean expression is efficient and readable
 *   - The condition is evaluated once per row during aggregation
 *   - No need for multiple passes or complex joins
 */

# Write your MySQL query statement below

SELECT user_id
FROM Loans
GROUP BY user_id
HAVING 
    SUM(loan_type = 'Refinance') > 0
    AND
    SUM(loan_type = 'Mortgage') > 0
ORDER BY user_id;

