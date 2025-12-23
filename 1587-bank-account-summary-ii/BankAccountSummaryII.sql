/*
 * LeetCode 1587: Bank Account Summary II
 *
 * Problem:
 * Write a solution to report the name and balance of users with a balance
 * higher than 10000. The balance of an account is equal to the sum of the
 * amounts of all transactions involving that account.
 *
 * Return the result table in any order.
 *
 * Table: Users
 * +--------------+---------+
 * | Column Name  | Type    |
 * +--------------+---------+
 * | account      | int     |
 * | name         | varchar |
 * +--------------+---------+
 * account is the primary key for this table.
 * Each row of this table contains the account number of each user in the bank.
 *
 * Table: Transactions
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | trans_id      | int     |
 * | account       | int     |
 * | amount        | int     |
 * | transacted_on | date    |
 * +---------------+---------+
 * trans_id is the primary key for this table.
 * Each row contains all changes made to all accounts.
 * amount is positive if the user received money and negative if they
 * transferred money. All accounts start with a balance of 0.
 *
 * Approach: JOIN and GROUP BY with HAVING
 *
 * Key Insight:
 * - Need to join Users with Transactions to get all transactions per account
 * - Sum all transaction amounts per account to calculate balance
 * - Filter accounts with balance > 10000 using HAVING clause
 *
 * Algorithm:
 * 1. JOIN Users with Transactions on account number
 * 2. GROUP BY account and name to aggregate transactions per user
 * 3. SUM(amount) to calculate total balance for each account
 * 4. HAVING balance > 10000 to filter high-balance accounts
 *
 * Why HAVING instead of WHERE?
 * - WHERE filters rows before aggregation
 * - HAVING filters groups after aggregation
 * - Since we're filtering on SUM(amount), we need HAVING
 *
 * Example:
 *
 *   Users:
 *   +---------+---------+
 *   | account | name    |
 *   +---------+---------+
 *   | 900001  | Alice   |
 *   | 900002  | Bob     |
 *   | 900003  | Charlie |
 *   +---------+---------+
 *
 *   Transactions:
 *   +----------+---------+--------+--------------+
 *   | trans_id | account | amount | transacted_on|
 *   +----------+---------+--------+--------------+
 *   | 1        | 900001  | 7000   | 2020-08-01   |
 *   | 2        | 900001  | 7000   | 2020-09-01   |
 *   | 3        | 900001  | -3000  | 2020-09-02   |
 *   | 4        | 900002  | 1000   | 2020-09-12   |
 *   | 5        | 900003  | 6000   | 2020-08-07   |
 *   | 6        | 900003  | 6000   | 2020-09-07   |
 *   +----------+---------+--------+--------------+
 *
 *   After JOIN and GROUP BY:
 *   - Alice (900001): 7000 + 7000 - 3000 = 11000 > 10000 ✓
 *   - Bob (900002): 1000 <= 10000 ✗
 *   - Charlie (900003): 6000 + 6000 = 12000 > 10000 ✓
 *
 *   Result:
 *   +-------+---------+
 *   | name  | balance|
 *   +-------+---------+
 *   | Alice | 11000  |
 *   | Charlie| 12000 |
 *   +-------+---------+
 *
 * Important Notes:
 * - JOIN ensures we only consider users who have transactions
 * - GROUP BY must include both account and name (name depends on account)
 * - HAVING uses the alias 'balance' defined in SELECT
 * - Positive amounts increase balance, negative amounts decrease it
 */

# Write your MySQL query statement below

SELECT
    u.name,
    SUM(t.amount) AS balance
FROM Users u
JOIN Transactions t
    ON u.account = t.account
GROUP BY u.account, u.name
HAVING balance > 10000;

