/*
 * LeetCode 1821: Find Customers With Positive Revenue this Year
 *
 * Problem:
 * Write a solution to report the customer_id of customers who have positive
 * revenue in the year 2021.
 *
 * Return the result table in any order.
 *
 * Table: Customers
 * +-------------+------+
 * | Column Name | Type |
 * +-------------+------+
 * | customer_id | int  |
 * | year        | int  |
 * | revenue     | int  |
 * +-------------+------+
 * (customer_id, year) is the primary key of this table.
 * This table contains the customer ID and the revenue of customers in different years.
 * Note that this table can contain duplicate rows.
 *
 * Approach: Simple WHERE Filter
 *
 * Key Insight:
 * - Need to filter customers where:
 *   1. year = 2021 (specific year requirement)
 *   2. revenue > 0 (positive revenue)
 * - Simple filtering with AND condition
 *
 * Algorithm:
 * 1. SELECT customer_id from Customers table
 * 2. WHERE year = 2021 AND revenue > 0
 *
 * Example:
 *
 *   Customers:
 *   +-------------+------+---------+
 *   | customer_id | year | revenue |
 *   +-------------+------+---------+
 *   | 1           | 2018 | 50      |
 *   | 1           | 2021 | 30      |
 *   | 1           | 2020 | 70      |
 *   | 2           | 2021 | -50     |
 *   | 3           | 2018 | 10      |
 *   | 3           | 2016 | 50      |
 *   | 4           | 2021 | 20      |
 *   +-------------+------+---------+
 *
 *   Filtering for year = 2021 AND revenue > 0:
 *   - Customer 1, year 2021: revenue = 30 > 0 → ✓
 *   - Customer 2, year 2021: revenue = -50 < 0 → ✗
 *   - Customer 3, year 2021: No record for 2021 → ✗
 *   - Customer 4, year 2021: revenue = 20 > 0 → ✓
 *
 *   Result:
 *   +-------------+
 *   | customer_id |
 *   +-------------+
 *   | 1           |
 *   | 4           |
 *   +-------------+
 *
 * Important Notes:
 * - Both conditions must be true (AND operator)
 * - revenue > 0 excludes zero and negative revenue
 * - Only customers with records in 2021 are considered
 * - If a customer has multiple records in 2021, they may appear multiple times
 *   (though the problem statement suggests this shouldn't happen based on
 *    primary key constraint)
 */

# Write your MySQL query statement below

SELECT customer_id
FROM Customers
WHERE year = 2021 AND revenue > 0;

