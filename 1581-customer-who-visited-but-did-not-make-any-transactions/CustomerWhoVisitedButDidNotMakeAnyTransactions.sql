/*
 * LeetCode 1581: Customer Who Visited but Did Not Make Any Transactions
 * 
 * Problem:
 * Write a solution to find the IDs of the users who visited without making any
 * transactions and the number of times they made these types of visits.
 * 
 * Return the result table sorted in any order.
 * 
 * Table: Visits
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | visit_id    | int     |
 * | customer_id | int     |
 * +-------------+---------+
 * visit_id is the primary key for this table.
 * This table contains information about the customers who visited the mall.
 * 
 * Table: Transactions
 * +----------------+---------+
 * | Column Name    | Type    |
 * +----------------+---------+
 * | transaction_id | int     |
 * | visit_id       | int     |
 * | amount         | int     |
 * +----------------+---------+
 * transaction_id is the primary key for this table.
 * This table contains information about the transactions made during the
 * visit_id.
 * 
 * Example:
 * Input:
 * Visits table:
 * +----------+-------------+
 * | visit_id | customer_id |
 * +----------+-------------+
 * | 1        | 23          |
 * | 2        | 9           |
 * | 4        | 30          |
 * | 5        | 54          |
 * | 6        | 96          |
 * | 7        | 54          |
 * | 8        | 54          |
 * +----------+-------------+
 * 
 * Transactions table:
 * +----------------+----------+--------+
 * | transaction_id | visit_id | amount |
 * +----------------+----------+--------+
 * | 2              | 5        | 310    |
 * | 3              | 5        | 300    |
 * | 9              | 5        | 200    |
 * | 12             | 1        | 910    |
 * | 13             | 2        | 970    |
 * +----------------+----------+--------+
 * 
 * Output:
 * +-------------+----------------+
 * | customer_id | count_no_trans |
 * +-------------+----------------+
 * | 54          | 2              |
 * | 30          | 1              |
 * | 96          | 1              |
 * +-------------+----------------+
 * 
 * Explanation:
 * Customer with id = 23 visited the mall once and made one transaction during
 * the visit with id = 1.
 * Customer with id = 9 visited the mall once and made one transaction during
 * the visit with id = 2.
 * Customer with id = 30 visited the mall once and did not make any transactions.
 * Customer with id = 54 visited the mall three times. During 2 visits (id = 5, 7)
 * they did not make any transactions, and during one visit (id = 8) they made
 * 3 transactions.
 * Customer with id = 96 visited the mall once and did not make any transactions.
 * As we can see, users with IDs 30 and 96 visited the mall one time without
 * making any transactions. Also, user 54 visited the mall twice and did not
 * make any transactions.
 * 
 * Approach: LEFT JOIN with NULL Filter
 * 
 * Key Insight:
 * - We need ALL visits from the Visits table
 * - Some visits may not have corresponding transactions
 * - Use LEFT JOIN to include all visits, with NULL for missing transactions
 * - Filter WHERE transaction_id IS NULL to get visits without transactions
 * - GROUP BY customer_id and COUNT to get number of such visits per customer
 * 
 * Algorithm:
 * 1. LEFT JOIN Visits with Transactions on visit_id
 * 2. Filter WHERE transaction_id IS NULL (visits without transactions)
 * 3. GROUP BY customer_id
 * 4. COUNT(*) to count visits without transactions per customer
 * 
 * Why LEFT JOIN?
 * 
 *   - INNER JOIN would exclude visits without transactions
 *   - RIGHT JOIN would exclude visits not in Transactions (but all visits
 *     should be considered)
 *   - LEFT JOIN ensures all visits are included, with NULL for missing transactions
 * 
 * Why WHERE transaction_id IS NULL?
 * 
 *   - LEFT JOIN returns NULL for transaction_id when no match is found
 *   - We only want visits where no transaction exists
 *   - Filtering for NULL gives us exactly those visits
 * 
 * Why COUNT(*)?
 * 
 *   - After filtering for NULL transactions, each row represents one visit
 *   - COUNT(*) counts the number of such visits per customer
 *   - Alternative: COUNT(v.visit_id) works the same since visit_id is never NULL
 * 
 * Why GROUP BY customer_id?
 * 
 *   - Multiple visits can belong to the same customer
 *   - We need to aggregate (COUNT) visits per customer
 *   - GROUP BY groups rows by customer, allowing COUNT to work correctly
 * 
 * Visual Example:
 * 
 *   Visits:          Transactions:      After LEFT JOIN:
 *   +----+----+      +----+----+----+   +----+----+----+----+
 *   |vid |cid |      |tid |vid |amt |   |vid |cid |tid |amt |
 *   +----+----+      +----+----+----+   +----+----+----+----+
 *   | 1  | 23 |      | 12 | 1  |910 |   | 1  | 23 | 12 |910 |
 *   | 2  | 9  |      | 13 | 2  |970 |   | 2  | 9  | 13 |970 |
 *   | 4  | 30 |      | 2  | 5  |310 |   | 4  | 30 |null|null|
 *   | 5  | 54 |      | 3  | 5  |300 |   | 5  | 54 | 2  |310 |
 *   | 6  | 96 |      | 9  | 5  |200 |   | 5  | 54 | 3  |300 |
 *   | 7  | 54 |      +----+----+----+   | 5  | 54 | 9  |200 |
 *   | 8  | 54 |                          | 6  | 96 |null|null|
 *   +----+----+                          | 7  | 54 |null|null|
 *                                         | 8  | 54 |null|null|
 *                                         +----+----+----+----+
 * 
 *   Note: visit_id 5 appears multiple times in join (one per transaction)
 *   But after WHERE transaction_id IS NULL, we only get visits without transactions:
 * 
 *   After WHERE transaction_id IS NULL:
 *   +----+----+----+----+
 *   |vid |cid |tid |amt |
 *   +----+----+----+----+
 *   | 4  | 30 |null|null|
 *   | 6  | 96 |null|null|
 *   | 7  | 54 |null|null|
 *   | 8  | 54 |null|null|
 *   +----+----+----+----+
 * 
 *   After GROUP BY and COUNT:
 *   +----+----------------+
 *   |cid | count_no_trans |
 *   +----+----------------+
 *   | 30 | 1              |
 *   | 54 | 2              | (visit_id 7 and 8)
 *   | 96 | 1              |
 *   +----+----------------+
 * 
 * Edge Cases:
 * 
 *   - All visits have transactions: Returns empty result
 *   - No visits have transactions: Returns all customers with their visit counts
 *   - Some visits have transactions: Returns only customers with visits without transactions
 *   - Empty Visits table: Returns empty result
 *   - Empty Transactions table: Returns all customers with their visit counts
 *   - Customer with multiple visits without transactions: Counted correctly
 * 
 * Important Notes:
 * 
 *   - LEFT JOIN preserves all visits
 *   - WHERE filters after join (visits without transactions)
 *   - GROUP BY aggregates per customer
 *   - COUNT(*) counts rows per group
 *   - Result shows customers who visited but didn't make transactions
 */

# Write your MySQL query statement below

SELECT 
    v.customer_id,
    COUNT(*) AS count_no_trans
FROM Visits v
LEFT JOIN Transactions t
ON v.visit_id = t.visit_id
WHERE t.transaction_id IS NULL
GROUP BY v.customer_id;

