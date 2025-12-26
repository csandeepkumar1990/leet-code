/*
 * LeetCode 2082: The Number of Rich Customers
 *
 * Problem:
 * A customer is considered rich if they have at least one bill with an amount strictly greater than 500.
 *
 * Write an SQL query to report the number of rich customers.
 *
 * Return the result table in any order.
 *
 * Table: Store
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | bill_id     | int  |
 * | customer_id | int  |
 * | amount      | int  |
 * +-------------+------+
 * bill_id is the primary key for this table.
 * Each row contains information about the amount of one bill and the customer associated with it.
 *
 * Approach: COUNT with DISTINCT and WHERE Filter
 *
 * Key Insight:
 * - Need to count distinct customers who have at least one bill > 500
 * - Filter rows where amount > 500
 * - Count distinct customer_id values
 * - A customer with multiple bills > 500 should be counted only once
 *
 * Algorithm:
 * 1. Filter rows WHERE amount > 500
 * 2. Select DISTINCT customer_id from filtered rows
 * 3. COUNT the distinct customer_ids
 *
 * Why DISTINCT?
 * - A customer can have multiple bills with amount > 500
 * - We want to count each customer only once
 * - DISTINCT ensures no duplicate customer_id in the count
 *
 * Example:
 *
 *   Store:
 *   +---------+-------------+--------+
 *   | bill_id | customer_id | amount |
 *   +---------+-------------+--------+
 *   | 6       | 1           | 549    |  ← > 500 ✓
 *   | 8       | 1           | 834    |  ← > 500 ✓
 *   | 4       | 2           | 394    |  ← <= 500 ✗
 *   | 11      | 3           | 657    |  ← > 500 ✓
 *   | 13      | 3           | 257    |  ← <= 500 ✗
 *   | 14      | 4           | 501    |  ← > 500 ✓
 *   +---------+-------------+--------+
 *
 *   Processing:
 *
 *   Step 1: Filter WHERE amount > 500
 *   +---------+-------------+--------+
 *   | bill_id | customer_id | amount |
 *   +---------+-------------+--------+
 *   | 6       | 1           | 549    |  ← Customer 1
 *   | 8       | 1           | 834    |  ← Customer 1 (duplicate)
 *   | 11      | 3           | 657    |  ← Customer 3
 *   | 14      | 4           | 501    |  ← Customer 4
 *   +---------+-------------+--------+
 *
 *   Step 2: Get DISTINCT customer_id
 *   +-------------+
 *   | customer_id |
 *   +-------------+
 *   | 1           |  ← Appears twice, but DISTINCT keeps only one
 *   | 3           |
 *   | 4           |
 *   +-------------+
 *
 *   Step 3: COUNT(DISTINCT customer_id)
 *   Result: 3
 *
 *   Final Result:
 *   +------------+
 *   | rich_count |
 *   +------------+
 *   | 3          |
 *   +------------+
 *
 *   Explanation:
 *   - Customer 1: Has bills 549 and 834 (both > 500) → Rich ✓
 *   - Customer 2: Has bill 394 (<= 500) → Not rich ✗
 *   - Customer 3: Has bill 657 (> 500) → Rich ✓
 *   - Customer 4: Has bill 501 (> 500) → Rich ✓
 *   - Total rich customers: 3
 *
 * Example 2: Customer with Multiple Rich Bills
 *
 *   Store:
 *   +---------+-------------+--------+
 *   | bill_id | customer_id | amount |
 *   +---------+-------------+--------+
 *   | 1       | 1           | 600    |  ← > 500
 *   | 2       | 1           | 700    |  ← > 500
 *   | 3       | 1           | 800    |  ← > 500
 *   | 4       | 2           | 400    |  ← <= 500
 *   +---------+-------------+--------+
 *
 *   Processing:
 *   - After WHERE amount > 500: 3 rows (all for customer 1)
 *   - DISTINCT customer_id: {1}
 *   - COUNT: 1
 *
 *   Result:
 *   +------------+
 *   | rich_count |
 *   +------------+
 *   | 1          |
 *   +------------+
 *
 *   Note: Customer 1 has 3 bills > 500, but is counted only once
 *
 * Example 3: No Rich Customers
 *
 *   Store:
 *   +---------+-------------+--------+
 *   | bill_id | customer_id | amount |
 *   +---------+-------------+--------+
 *   | 1       | 1           | 500    |  ← = 500 (not > 500)
 *   | 2       | 2           | 400    |  ← < 500
 *   | 3       | 3           | 300    |  ← < 500
 *   +---------+-------------+--------+
 *
 *   Processing:
 *   - After WHERE amount > 500: 0 rows
 *   - DISTINCT customer_id: {} (empty set)
 *   - COUNT: 0
 *
 *   Result:
 *   +------------+
 *   | rich_count |
 *   +------------+
 *   | 0          |
 *   +------------+
 *
 * Alternative Approach: Using Subquery with EXISTS
 *
 *   SELECT COUNT(DISTINCT customer_id) AS rich_count
 *   FROM Store s1
 *   WHERE EXISTS (
 *       SELECT 1
 *       FROM Store s2
 *       WHERE s2.customer_id = s1.customer_id
 *         AND s2.amount > 500
 *   );
 *
 * Alternative Approach: Using GROUP BY and HAVING
 *
 *   SELECT COUNT(*) AS rich_count
 *   FROM (
 *       SELECT customer_id
 *       FROM Store
 *       WHERE amount > 500
 *       GROUP BY customer_id
 *   ) AS rich_customers;
 *
 * Why COUNT(DISTINCT customer_id) Instead of COUNT(customer_id)?
 * - COUNT(customer_id) would count NULL values if any exist
 * - COUNT(DISTINCT customer_id) excludes NULLs and removes duplicates
 * - In this problem, customer_id is likely NOT NULL, so both work
 * - DISTINCT is essential to avoid double-counting customers with multiple bills
 *
 * Important Notes:
 * - "strictly greater than 500" means amount > 500 (not >= 500)
 * - A customer needs at least ONE bill > 500 to be considered rich
 * - Multiple bills > 500 for the same customer still count as 1
 * - COUNT(DISTINCT ...) automatically handles NULL values (excludes them)
 * - The result is a single number (scalar value)
 *
 * Edge Cases:
 * - Empty table: Returns 0
 * - All amounts <= 500: Returns 0
 * - Customer with amount = 500: Not counted (strictly > 500)
 * - Customer with NULL amount: Not counted (NULL comparisons are false)
 *
 * ============================================================
 * SUMMARY OF KEY CONCEPTS
 * ============================================================
 *
 * | Concept              | Purpose                                    |
 * |----------------------|--------------------------------------------|
 * | WHERE amount > 500   | Filter bills with amount strictly > 500    |
 * | DISTINCT customer_id| Remove duplicate customers from count      |
 * | COUNT(DISTINCT ...)  | Count unique values, excluding NULLs       |
 * | "At least one"       | Customer needs only one qualifying bill    |
 *
 * COUNT vs COUNT(DISTINCT):
 * - COUNT(customer_id): Counts all rows, including duplicates
 * - COUNT(DISTINCT customer_id): Counts unique values only
 * - Example: {1, 1, 2, 3} → COUNT = 4, COUNT(DISTINCT) = 3
 */

# Write your MySQL query statement below

SELECT COUNT(DISTINCT customer_id) AS rich_count
FROM Store
WHERE amount > 500;

