/*
 * LeetCode 3172: Second Day Verification
 * 
 * Problem:
 * Write a solution to find users who verified their email on the second day after signup.
 * Return the result table ordered by user_id in ascending order.
 * 
 * Table: Emails
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | email_id    | int     |
 * | user_id     | int     |
 * | signup_date | datetime|
 * +-------------+---------+
 * email_id is the primary key for this table.
 * Each row contains information about when a user signed up.
 * 
 * Table: Texts
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | text_id       | int     |
 * | email_id      | int     |
 * | signup_action | varchar |
 * | action_date   | datetime|
 * +-------------+---------+
 * text_id is the primary key for this table.
 * Each row contains information about an action taken on an email.
 * signup_action can be 'Verified' or 'Not Verified'.
 * 
 * Approach: JOIN with Date Comparison
 * 
 * Key Insight:
 * - Join Emails and Texts tables to link signup and verification
 * - Filter for 'Verified' actions only
 * - Check if action_date is exactly one day after signup_date
 * - Use DATE() to compare dates (ignoring time component)
 * - Use DATE_ADD() to add one day to signup_date
 * 
 * Date Comparison:
 *   We need: action_date = signup_date + 1 day
 *   
 *   Using DATE() to extract date portion (ignore time):
 *   DATE(action_date) = DATE_ADD(DATE(signup_date), INTERVAL 1 DAY)
 * 
 * Formula:
 *   Verified users where:
 *   - signup_action = 'Verified'
 *   - DATE(action_date) = DATE_ADD(DATE(signup_date), INTERVAL 1 DAY)
 * 
 * Algorithm:
 * 1. JOIN Emails and Texts on email_id
 * 2. Filter WHERE signup_action = 'Verified'
 * 3. Check if action_date is exactly 1 day after signup_date
 * 4. Use DISTINCT to avoid duplicate user_ids
 * 5. ORDER BY user_id
 * 
 * Example:
 * 
 *   Emails:
 *   +----------+---------+---------------------+
 *   | email_id | user_id | signup_date         |
 *   +----------+---------+---------------------+
 *   | 125      | 777     | 2023-06-14 09:00:00|
 *   | 236      | 695     | 2023-06-14 14:00:00|
 *   | 433      | 574     | 2023-06-15 10:00:00|
 *   +----------+---------+---------------------+
 * 
 *   Texts:
 *   +---------+----------+---------------+---------------------+
 *   | text_id | email_id | signup_action | action_date         |
 *   +---------+----------+---------------+---------------------+
 *   | 951     | 125      | Verified      | 2023-06-15 08:00:00|
 *   | 952     | 236      | Verified      | 2023-06-16 14:00:00|
 *   | 953     | 433      | Not Verified  | 2023-06-16 10:00:00|
 *   | 954     | 433      | Verified      | 2023-06-17 10:00:00|
 *   +---------+----------+---------------+---------------------+
 * 
 *   Step 1 - JOIN on email_id:
 *   User 777: signup 2023-06-14, verified 2023-06-15 → 1 day later ✓
 *   User 695: signup 2023-06-14, verified 2023-06-16 → 2 days later ✗
 *   User 574: signup 2023-06-15, verified 2023-06-17 → 2 days later ✗
 * 
 *   Step 2 - Filter Verified:
 *   All three have 'Verified' actions
 * 
 *   Step 3 - Check date difference:
 *   User 777: DATE(2023-06-15) = DATE_ADD(DATE(2023-06-14), INTERVAL 1 DAY)
 *             = 2023-06-15 = 2023-06-15 ✓
 *   
 *   User 695: DATE(2023-06-16) = DATE_ADD(DATE(2023-06-14), INTERVAL 1 DAY)
 *             = 2023-06-16 = 2023-06-15 ✗
 *   
 *   User 574: DATE(2023-06-17) = DATE_ADD(DATE(2023-06-15), INTERVAL 1 DAY)
 *             = 2023-06-17 = 2023-06-16 ✗
 * 
 *   Result:
 *   +---------+
 *   | user_id |
 *   +---------+
 *   | 777     |
 *   +---------+
 * 
 * Why DATE() function?
 *   - signup_date and action_date are DATETIME (include time)
 *   - We only care about the date, not the time
 *   - DATE() extracts just the date portion
 *   - Example: DATE('2023-06-14 09:00:00') = '2023-06-14'
 * 
 * Why DATE_ADD()?
 *   - Adds a time interval to a date
 *   - DATE_ADD(date, INTERVAL 1 DAY) adds exactly one day
 *   - Handles month/year boundaries correctly
 *   - Example: DATE_ADD('2023-06-14', INTERVAL 1 DAY) = '2023-06-15'
 * 
 * Why DISTINCT?
 *   - A user might have multiple emails or verification attempts
 *   - DISTINCT ensures each user_id appears only once
 *   - Prevents duplicate user_ids in the result
 * 
 * Alternative Date Comparison Methods:
 *   - DATEDIFF(action_date, signup_date) = 1
 *     (But this compares full datetime, might not work if times differ)
 *   - DATE(action_date) = DATE(signup_date) + INTERVAL 1 DAY
 *     (Similar approach, slightly different syntax)
 *   - DATE_SUB(DATE(action_date), INTERVAL 1 DAY) = DATE(signup_date)
 *     (Reverse: subtract 1 day from action_date)
 * 
 * Edge Cases:
 *   - User verifies at 23:59:59 on day 1, action_date is 00:00:01 on day 2
 *     → DATE() handles this correctly (both are same date)
 *   - User verifies exactly 24 hours later (but different calendar day)
 *     → DATE() comparison works correctly
 *   - Leap years and month boundaries
 *     → DATE_ADD() handles these automatically
 */

# Write your MySQL query statement below

SELECT DISTINCT e.user_id
FROM Emails e
JOIN Texts t
    ON e.email_id = t.email_id
WHERE
    t.signup_action = 'Verified'
    AND DATE(t.action_date) = DATE_ADD(DATE(e.signup_date), INTERVAL 1 DAY)
ORDER BY e.user_id;

