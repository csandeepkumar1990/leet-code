/*
 * LeetCode 2205: The Number of Users That Are Eligible for Discount
 *
 * Problem:
 * A user is eligible for a discount if they have made at least one purchase in the inclusive
 * interval of time [startDate, endDate] with at least minAmount amount.
 *
 * Write an SQL query to report the number of users that are eligible for a discount.
 *
 * Table: Purchases
 * +-------------+----------+
 * | Column Name | Type     |
 * +-------------+----------+
 * | user_id     | int      |
 * | time_stamp  | datetime |
 * | amount      | int      |
 * +-------------+----------+
 * (user_id, time_stamp) is the primary key for this table.
 * Each row contains information about the purchase time and the amount paid by a user.
 *
 * Function Signature:
 * CREATE FUNCTION getUserIDs(startDate DATE, endDate DATE, minAmount INT) RETURNS INT
 *
 * Parameters:
 * - startDate: Start of the date range (inclusive)
 * - endDate: End of the date range (inclusive)
 * - minAmount: Minimum purchase amount required
 *
 * Returns:
 * - INT: Count of distinct users eligible for discount
 *
 * Approach: MySQL Function with COUNT(DISTINCT) and Multiple WHERE Conditions
 *
 * Key Insight:
 * - Need to count distinct users who made at least one qualifying purchase
 * - A purchase qualifies if:
 *   1. time_stamp is within [startDate, endDate] (inclusive)
 *   2. amount >= minAmount
 * - Use COUNT(DISTINCT user_id) to count each user only once
 * - Wrap query in a function that returns INT
 *
 * Algorithm:
 * 1. Filter purchases WHERE time_stamp >= startDate AND time_stamp <= endDate
 * 2. Filter purchases WHERE amount >= minAmount
 * 3. Count DISTINCT user_id from filtered results
 * 4. Return the count as INT
 *
 * Date Range Comparison:
 * - time_stamp >= startDate: Purchase on or after start date
 * - time_stamp <= endDate: Purchase on or before end date
 * - Combined: Inclusive range [startDate, endDate]
 * - Alternative: time_stamp BETWEEN startDate AND endDate
 *
 * Example:
 *
 *   Purchases:
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 09:00:00| 1000   |  ← Qualifies ✓
 *   | 1       | 2022-04-21 10:00:00| 2000   |  ← Qualifies ✓
 *   | 2       | 2022-04-19 08:00:00| 3000   |  ← Before startDate ✗
 *   | 2       | 2022-04-20 11:00:00| 500    |  ← Amount < minAmount ✗
 *   | 3       | 2022-04-20 12:00:00| 2000   |  ← Qualifies ✓
 *   | 4       | 2022-04-22 13:00:00| 1500   |  ← Qualifies ✓
 *   | 5       | 2022-04-23 14:00:00| 800    |  ← After endDate ✗
 *   +---------+---------------------+--------+
 *
 *   Function Call:
 *   SELECT getUserIDs('2022-04-20', '2022-04-22', 1000);
 *
 *   Processing:
 *
 *   Step 1: Filter by date range [2022-04-20, 2022-04-22]
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 09:00:00| 1000   |  ← In range
 *   | 1       | 2022-04-21 10:00:00| 2000   |  ← In range
 *   | 2       | 2022-04-20 11:00:00| 500    |  ← In range
 *   | 3       | 2022-04-20 12:00:00| 2000   |  ← In range
 *   | 4       | 2022-04-22 13:00:00| 1500   |  ← In range
 *   +---------+---------------------+--------+
 *
 *   Step 2: Filter by amount >= 1000
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 09:00:00| 1000   |  ← Qualifies
 *   | 1       | 2022-04-21 10:00:00| 2000   |  ← Qualifies
 *   | 3       | 2022-04-20 12:00:00| 2000   |  ← Qualifies
 *   | 4       | 2022-04-22 13:00:00| 1500   |  ← Qualifies
 *   +---------+---------------------+--------+
 *
 *   Step 3: Get DISTINCT user_id
 *   +---------+
 *   | user_id |
 *   +---------+
 *   | 1       |  ← Appears twice, but DISTINCT keeps one
 *   | 3       |
 *   | 4       |
 *   +---------+
 *
 *   Step 4: COUNT(DISTINCT user_id)
 *   Result: 3
 *
 *   Explanation:
 *   - User 1: Has 2 qualifying purchases → Counted once ✓
 *   - User 2: Has purchase in range but amount < 1000 → Not eligible ✗
 *   - User 3: Has 1 qualifying purchase → Counted ✓
 *   - User 4: Has 1 qualifying purchase → Counted ✓
 *   - User 5: Purchase after endDate → Not eligible ✗
 *
 * Example 2: Edge Cases
 *
 *   Function Call:
 *   SELECT getUserIDs('2022-04-20', '2022-04-20', 1000);
 *
 *   Purchases:
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 00:00:00| 2000   |  ← Qualifies (same day)
 *   | 2       | 2022-04-20 23:59:59| 1500   |  ← Qualifies (same day)
 *   | 3       | 2022-04-19 23:59:59| 2000   |  ← Before startDate ✗
 *   | 4       | 2022-04-21 00:00:00| 2000   |  ← After endDate ✗
 *   +---------+---------------------+--------+
 *
 *   Result: 2 (users 1 and 2)
 *
 * Example 3: No Qualifying Users
 *
 *   Function Call:
 *   SELECT getUserIDs('2022-04-20', '2022-04-22', 5000);
 *
 *   Purchases:
 *   +---------+---------------------+--------+
 *   | user_id | time_stamp          | amount |
 *   +---------+---------------------+--------+
 *   | 1       | 2022-04-20 09:00:00| 1000   |  ← Amount < 5000 ✗
 *   | 2       | 2022-04-21 10:00:00| 2000   |  ← Amount < 5000 ✗
 *   +---------+---------------------+--------+
 *
 *   Result: 0
 *
 * Alternative Approach: Using BETWEEN for Date Range
 *
 *   CREATE FUNCTION getUserIDs(startDate DATE, endDate DATE, minAmount INT) RETURNS INT
 *   BEGIN
 *     RETURN (
 *       SELECT COUNT(DISTINCT user_id)
 *       FROM Purchases
 *       WHERE time_stamp BETWEEN startDate AND endDate
 *         AND amount >= minAmount
 *     );
 *   END;
 *
 * Why Use >= and <= Instead of BETWEEN?
 * - Both work the same for inclusive ranges
 * - >= and <= are more explicit about inclusivity
 * - BETWEEN is more concise but can be ambiguous with time components
 * - For datetime columns, BETWEEN includes the entire day
 *
 * Function Structure:
 * - CREATE FUNCTION: Defines a reusable function
 * - RETURNS INT: Specifies return type
 * - BEGIN...END: Function body block
 * - RETURN (...): Returns the result of the subquery
 * - Subquery must return a single value (scalar)
 *
 * Important Notes:
 * - Date range is INCLUSIVE: [startDate, endDate]
 * - time_stamp is DATETIME, so comparisons include time component
 * - amount >= minAmount means "greater than or equal to"
 * - COUNT(DISTINCT user_id) ensures each user counted once
 * - Function returns INT, so result is always an integer
 * - If no qualifying users, COUNT returns 0
 *
 * Edge Cases:
 * - Empty Purchases table: Returns 0
 * - No purchases in date range: Returns 0
 * - All purchases below minAmount: Returns 0
 * - startDate = endDate: Only purchases on that specific day
 * - Multiple purchases by same user: User counted once
 * - NULL values: COUNT(DISTINCT) excludes NULLs
 *
 * MySQL Function Syntax:
 * - Functions must be created before use
 * - Can be called in SELECT statements: SELECT getUserIDs(...)
 * - Parameters are passed by value
 * - Function name is case-insensitive in MySQL
 *
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 *
 * | Concept              | Purpose                                    |
 * |----------------------|--------------------------------------------|
 * | MySQL Function       | Reusable code that returns a value         |
 * | Date Range Filter    | time_stamp >= startDate AND <= endDate     |
 * | Amount Filter        | amount >= minAmount                        |
 * | COUNT(DISTINCT)      | Count unique users, excluding duplicates   |
 * | Inclusive Range      | Both startDate and endDate are included    |
 *
 * Date Comparison Notes:
 * - DATE '2022-04-20' compared with DATETIME '2022-04-20 10:00:00'
 * - DATE is implicitly converted to DATETIME '2022-04-20 00:00:00'
 * - time_stamp >= '2022-04-20' matches '2022-04-20 00:00:00' and later
 * - time_stamp <= '2022-04-20' matches '2022-04-20 23:59:59' and earlier
 */

CREATE FUNCTION getUserIDs(startDate DATE, endDate DATE, minAmount INT) RETURNS INT
BEGIN
  RETURN (
      # Write your MySQL query statement below.
      SELECT COUNT(DISTINCT user_id) AS user_cnt
      FROM Purchases
      WHERE amount >= minAmount
        AND time_stamp >= startDate
        AND time_stamp <= endDate
  );
END

