/*
 * LeetCode 584: Find Customer Referee
 * 
 * Problem:
 * Find the names of customers that are NOT referred by the customer with id = 2.
 * 
 * Table: Customer
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | id          | int     |
 * | name        | varchar |
 * | referee_id  | int     |
 * +-------------+---------+
 * id is the primary key.
 * Each row indicates the id of a customer, their name, and the id of the
 * customer who referred them (NULL if not referred by anyone).
 * 
 * Example:
 * Input:
 * +----+------+------------+
 * | id | name | referee_id |
 * +----+------+------------+
 * | 1  | Will | null       |
 * | 2  | Jane | null       |
 * | 3  | Alex | 2          |
 * | 4  | Bill | null       |
 * | 5  | Zack | 1          |
 * | 6  | Mark | 2          |
 * +----+------+------------+
 * 
 * Output:
 * +------+
 * | name |
 * +------+
 * | Will |
 * | Jane |
 * | Bill |
 * | Zack |
 * +------+
 * 
 * Explanation:
 * - Will (id=1): referee_id = NULL → not referred by 2 ✅
 * - Jane (id=2): referee_id = NULL → not referred by 2 ✅
 * - Alex (id=3): referee_id = 2 → referred by 2 ❌
 * - Bill (id=4): referee_id = NULL → not referred by 2 ✅
 * - Zack (id=5): referee_id = 1 → not referred by 2 ✅
 * - Mark (id=6): referee_id = 2 → referred by 2 ❌
 * 
 * Approach:
 * - Check referee_id != 2 to exclude customers referred by id=2
 * - Also check referee_id IS NULL because NULL != 2 returns NULL (not TRUE)
 *   in SQL, so NULL values would be excluded without this check
 * 
 * Note: In SQL, any comparison with NULL returns NULL (unknown), not TRUE/FALSE.
 * So "NULL != 2" is NULL, which is treated as FALSE in WHERE clause.
 */

# Write your MySQL query statement below

SELECT name
FROM Customer
WHERE referee_id != 2 OR referee_id IS NULL;

