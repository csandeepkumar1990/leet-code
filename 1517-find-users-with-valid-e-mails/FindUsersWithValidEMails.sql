/*
 * LeetCode 1517: Find Users With Valid E-Mails
 * 
 * Problem:
 * Write a solution to find the users who have valid emails.
 * 
 * A valid e-mail has a prefix name and a domain where:
 * - The prefix name is a string that may contain letters (upper or lower case),
 *   digits, underscore '_', period '.', and/or dash '-'. The prefix name must
 *   start with a letter.
 * - The domain is '@leetcode.com'.
 * 
 * Return the result table in any order.
 * 
 * Table: Users
 * +---------------+---------+
 * | Column Name   | Type    |
 * +---------------+---------+
 * | user_id       | int     |
 * | name          | varchar |
 * | mail          | varchar |
 * +---------------+---------+
 * user_id is the primary key for this table.
 * This table contains information of the users signed up in a website. Some
 * e-mails are invalid.
 * 
 * Example:
 * Input:
 * Users table:
 * +---------+-----------+-------------------------+
 * | user_id | name      | mail                    |
 * +---------+-----------+-------------------------+
 * | 1       | Winston   | winston@leetcode.com    |
 * | 2       | Jonathan  | jonathanisgreat          |
 * | 3       | Annabelle | bella-@leetcode.com     |
 * | 4       | Sally     | sally.come@leetcode.com |
 * | 5       | Marwan    | quarz#2020@leetcode.com |
 * | 6       | David     | david69@gmail.com       |
 * | 7       | Shapiro   | .shapo@leetcode.com    |
 * +---------+-----------+-------------------------+
 * 
 * Output:
 * +---------+-----------+-------------------------+
 * | user_id | name      | mail                    |
 * +---------+-----------+-------------------------+
 * | 1       | Winston   | winston@leetcode.com    |
 * | 3       | Annabelle | bella-@leetcode.com     |
 * | 4       | Sally     | sally.come@leetcode.com |
 * +---------+-----------+-------------------------+
 * 
 * Explanation:
 * The mail of user 2 does not have a domain.
 * The mail of user 5 has # sign which is not allowed.
 * The mail of user 6 does not have leetcode domain.
 * The mail of user 7 starts with a period.
 * 
 * Approach: Regular Expression Pattern Matching
 * 
 * Key Insight:
 * - Prefix must start with a letter [A-Za-z]
 * - Prefix can contain letters, digits, underscore, period, dash [A-Za-z0-9_.-]*
 * - Domain must be exactly '@leetcode.com'
 * - Use regular expression to validate the pattern
 * 
 * Pattern Breakdown:
 * - ^ : Start of string
 * - [A-Za-z] : First character must be a letter
 * - [A-Za-z0-9_.-]* : Remaining prefix characters (0 or more)
 * - @leetcode\\.com : Domain (escape dot with \\.)
 * - $ : End of string
 * 
 * Algorithm:
 * 1. Filter Users WHERE mail matches the regex pattern
 * 2. Select user_id, name, and mail
 * 
 * Why Regular Expression?
 * 
 *   - Email validation requires pattern matching
 *   - Multiple conditions: starts with letter, allowed characters, exact domain
 *   - Regular expressions handle complex pattern matching efficiently
 * 
 * Pattern Details:
 * 
 *   ^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$
 *   
 *   - ^ : Anchor to start of string
 *   - [A-Za-z] : First character must be letter (required)
 *   - [A-Za-z0-9_.-]* : Zero or more allowed characters
 *     * Letters: A-Z, a-z
 *     * Digits: 0-9
 *     * Special: underscore _, period ., dash -
 *   - @leetcode\\.com : Domain (dot escaped as \\.)
 *   - $ : Anchor to end of string
 * 
 * Why Escape the Dot?
 * 
 *   - In regex, . matches any character
 *   - We want literal dot in '@leetcode.com'
 *   - Escape with \\. to match literal dot
 * 
 * REGEXP_LIKE Syntax (Oracle):
 * 
 *   REGEXP_LIKE(string, pattern, match_parameter)
 *   - 'c' flag: case-sensitive matching (default)
 *   - 'i' flag: case-insensitive matching
 * 
 * Alternative MySQL Syntax:
 * 
 *   WHERE mail REGEXP '^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$'
 *   or
 *   WHERE mail RLIKE '^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$'
 * 
 * Visual Example:
 * 
 *   Valid emails:
 *   - winston@leetcode.com ✓ (starts with letter, valid domain)
 *   - bella-@leetcode.com ✓ (starts with letter, dash allowed, valid domain)
 *   - sally.come@leetcode.com ✓ (starts with letter, period allowed, valid domain)
 * 
 *   Invalid emails:
 *   - jonathanisgreat ✗ (no domain)
 *   - quarz#2020@leetcode.com ✗ (# not allowed in prefix)
 *   - david69@gmail.com ✗ (wrong domain)
 *   - .shapo@leetcode.com ✗ (starts with period, not letter)
 * 
 * Edge Cases:
 * 
 *   - Single letter prefix: a@leetcode.com ✓
 *   - Long prefix: verylongprefixname123@leetcode.com ✓
 *   - Prefix with all allowed chars: a_b-c.123@leetcode.com ✓
 *   - Empty prefix: @leetcode.com ✗ (must start with letter)
 *   - Wrong domain: user@leetcode.org ✗
 *   - Multiple @: user@@leetcode.com ✗
 *   - Domain with extra chars: user@leetcode.com.org ✗
 * 
 * Important Notes:
 * 
 *   - Prefix must start with a letter (not digit, not special char)
 *   - Only specific special characters allowed: _, ., -
 *   - Domain must be exactly '@leetcode.com' (case-sensitive)
 *   - No spaces or other special characters allowed
 *   - REGEXP_LIKE is Oracle syntax; MySQL uses REGEXP or RLIKE
 */

# Write your MySQL query statement below

SELECT user_id, name, mail
FROM Users
WHERE REGEXP_LIKE(
    mail,
    '^[A-Za-z][A-Za-z0-9_.-]*@leetcode\\.com$',
    'c'
);

