/*
 * LeetCode 1607: Sellers With No Sales
 *
 * Problem:
 * Find the names of all sellers who did not make any sales in the year 2020.
 *
 * Tables:
 * Seller
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | seller_id   | int     |
 * | seller_name | varchar |
 * +-------------+---------+
 * seller_id is the primary key for this table.
 *
 * Orders
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | order_id    | int     |
 * | seller_id   | int     |
 * | sale_date   | date    |
 * | sale_amount | int     |
 * +-------------+---------+
 * order_id is the primary key for this table.
 *
 * Requirement:
 * - Return seller_name for sellers who have no orders in year 2020.
 * - Order the result by seller_name.
 *
 * Approach: LEFT JOIN + NULL filter
 *
 * Key Idea:
 * - LEFT JOIN Seller with Orders filtered to year 2020.
 * - For sellers with no 2020 orders, the joined order_id will be NULL.
 * - Filter WHERE o.order_id IS NULL to keep only those sellers.
 *
 * Algorithm:
 * 1. LEFT JOIN Seller s with Orders o on seller_id AND YEAR(sale_date) = 2020.
 * 2. In WHERE, keep rows where o.order_id IS NULL (no 2020 sales).
 * 3. SELECT seller_name and ORDER BY seller_name.
 */

# Write your MySQL query statement below

SELECT s.seller_name
FROM Seller s
LEFT JOIN Orders o
    ON s.seller_id = o.seller_id 
    AND YEAR(o.sale_date) = 2020
WHERE o.order_id IS NULL
ORDER BY s.seller_name;


