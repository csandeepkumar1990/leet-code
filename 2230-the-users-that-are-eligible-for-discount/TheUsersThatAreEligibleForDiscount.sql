/*
 * LeetCode 2230: The Users That Are Eligible for Discount
 * 
 * Problem:
 * Write a solution to create a stored procedure that finds users eligible for a discount.
 * The procedure should accept:
 *   - startDate: start of the date range
 *   - endDate: end of the date range
 *   - minAmount: minimum purchase amount
 * 
 * Return distinct user_ids who made purchases:
 *   - Within the date range (inclusive)
 *   - With amount >= minAmount
 * 
 * Table: Purchases
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | user_id     | int     |
 * | time_stamp  | datetime|
 * | amount      | int     |
 * +-------------+---------+
 * (user_id, time_stamp) is the primary key for this table.
 * Each row contains information about a purchase.
 * 
 * Approach: Stored Procedure with Parameter Filtering
 * 
 * Key Insight:
 * - Create a stored procedure that accepts parameters
 * - Filter purchases by date range and minimum amount
 * - Return distinct user_ids who meet the criteria
 * 
 * Stored Procedure Syntax:
 *   CREATE PROCEDURE procedure_name(param1 TYPE, param2 TYPE, ...)
 *   BEGIN
 *       -- SQL statements
 *   END
 * 
 * Formula:
 *   Eligible users where:
 *   - time_stamp >= startDate AND time_stamp <= endDate
 *   - amount >= minAmount
 * 
 * Algorithm:
 * 1. Filter purchases within date range (inclusive)
 * 2. Filter purchases with amount >= minAmount
 * 3. Select DISTINCT user_id to avoid duplicates
 * 4. ORDER BY user_id for sorted results
 * 
 * Example:
 * 
 *   Purchases:
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 09:03:00 | 4416   |
 *   | 2       | 2022-03-19 19:24:02 | 678    |
 *   | 3       | 2022-03-18 12:03:09 | 4523   |
 *   | 3       | 2022-03-30 09:43:42 | 626    |
 *   | 4       | 2022-03-15 10:00:00 | 1500   |
 *   | 4       | 2022-03-25 14:30:00 | 800    |
 *   +---------+---------------------+--------+
 * 
 *   Call: getUserIDs('2022-03-08', '2022-03-20', 1000)
 *   Parameters:
 *   - startDate: 2022-03-08
 *   - endDate: 2022-03-20
 *   - minAmount: 1000
 * 
 *   Step 1 - Filter by date range:
 *   User 1: 2022-04-20 → Outside range ✗
 *   User 2: 2022-03-19 → Within range ✓
 *   User 3: 2022-03-18 → Within range ✓
 *   User 3: 2022-03-30 → Outside range ✗
 *   User 4: 2022-03-15 → Within range ✓
 *   User 4: 2022-03-25 → Outside range ✗
 * 
 *   Step 2 - Filter by amount >= 1000:
 *   User 2: 678 < 1000 ✗
 *   User 3: 4523 >= 1000 ✓
 *   User 4: 1500 >= 1000 ✓
 * 
 *   Result:
 *   +---------+
 *   | user_id |
 *   +---------+
 *   | 3       |
 *   | 4       |
 *   +---------+
 * 
 * Stored Procedure Benefits:
 *   - Reusable: Can be called multiple times with different parameters
 *   - Encapsulation: Logic is stored in the database
 *   - Performance: Can be optimized by the database
 *   - Security: Can control access to the procedure
 * 
 * Calling the Procedure:
 *   CALL getUserIDs('2022-03-08', '2022-03-20', 1000);
 * 
 * Date Range Comparison:
 *   - time_stamp >= startDate: Includes purchases on or after start date
 *   - time_stamp <= endDate: Includes purchases on or before end date
 *   - Both conditions make the range inclusive
 *   - Alternative: time_stamp BETWEEN startDate AND endDate (also inclusive)
 * 
 * Why DISTINCT?
 *   - A user might have multiple purchases meeting the criteria
 *   - DISTINCT ensures each user_id appears only once
 *   - Prevents duplicate user_ids in the result
 * 
 * Parameter Types:
 *   - DATE: For date values (without time)
 *   - INT: For integer values (amount)
 *   - Parameters are used directly in WHERE clause
 * 
 * Edge Cases:
 *   - No purchases in date range: Returns empty result
 *   - No purchases >= minAmount: Returns empty result
 *   - startDate > endDate: Returns empty result (no dates satisfy both conditions)
 *   - Multiple purchases by same user: DISTINCT handles duplicates
 * 
 * Alternative Date Comparison:
 *   - BETWEEN startDate AND endDate (more concise, same result)
 *   - time_stamp >= startDate AND time_stamp <= endDate (explicit, clearer)
 *   - Both are inclusive of boundaries
 */

CREATE PROCEDURE getUserIDs(startDate DATE, endDate DATE, minAmount INT)
BEGIN
	# Write your MySQL query statement below.
	SELECT DISTINCT user_id
	FROM Purchases
	WHERE amount >= minAmount
	  AND time_stamp >= startDate
	  AND time_stamp <= endDate
	ORDER BY user_id;
END

