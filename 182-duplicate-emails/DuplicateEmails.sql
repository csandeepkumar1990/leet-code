/*
 * LeetCode 182: Duplicate Emails
 * 
 * Problem:
 * Given a Person table, find all duplicate emails.
 * 
 * Table: Person
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | email       | varchar |
 * +-------------+---------+
 * id is the primary key.
 * Each row contains an email. Emails will not contain uppercase letters.
 * 
 * Example:
 * Input:
 * +----+---------+
 * | id | email   |
 * +----+---------+
 * | 1  | a@b.com |
 * | 2  | c@d.com |
 * | 3  | a@b.com |
 * +----+---------+
 * 
 * Output:
 * +---------+
 * | Email   |
 * +---------+
 * | a@b.com |
 * +---------+
 * 
 * Explanation:
 * - a@b.com appears 2 times (id=1 and id=3) → duplicate, included
 * - c@d.com appears 1 time (id=2) → not a duplicate, excluded
 * 
 * Approach:
 * - GROUP BY email to aggregate rows with the same email
 * - HAVING COUNT(*) > 1 filters groups that have more than one occurrence
 */

# Write your MySQL query statement below

SELECT email
FROM Person
GROUP BY email
HAVING COUNT(*) > 1;

