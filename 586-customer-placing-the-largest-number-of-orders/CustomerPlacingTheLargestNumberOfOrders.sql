/*
 * LeetCode 586: Customer Placing the Largest Number of Orders
 * 
 * Problem:
 * Find the customer_number for the customer who has placed the largest
 * number of orders.
 * 
 * Table: Orders
 * +-----------------+---------+
 * | Column Name     | Type    |
 * +-----------------+---------+
 * | order_number    | int     |
 * | customer_number | int     |
 * +-----------------+---------+
 * order_number is the primary key.
 * Each row contains information about an order and the customer who placed it.
 * 
 * Example:
 * Input:
 * +--------------+-----------------+
 * | order_number | customer_number |
 * +--------------+-----------------+
 * | 1            | 1               |
 * | 2            | 2               |
 * | 3            | 3               |
 * | 4            | 3               |
 * +--------------+-----------------+
 * 
 * Output:
 * +-----------------+
 * | customer_number |
 * +-----------------+
 * | 3               |
 * +-----------------+
 * 
 * Explanation:
 * - Customer 1: 1 order
 * - Customer 2: 1 order
 * - Customer 3: 2 orders ← largest number of orders ✅
 * 
 * Approach:
 * - GROUP BY customer_number to count orders per customer
 * - ORDER BY COUNT(*) DESC to sort by order count (highest first)
 * - LIMIT 1 to get only the top customer
 * 
 * Note: GROUP BY 1 is shorthand for GROUP BY the first column in SELECT
 */

# Write your MySQL query statement below

SELECT customer_number
FROM Orders
GROUP BY 1
ORDER BY COUNT(*) DESC
LIMIT 1;

