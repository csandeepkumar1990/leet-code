/*
 * LeetCode 3059: Count Emails by Domain
 * 
 * Problem:
 * Write a solution to find the number of emails for each domain that ends with '.com'.
 * Return the result table ordered by domain in ascending order.
 * 
 * Table: Emails
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | email_id    | int     |
 * | email       | varchar |
 * +-------------+---------+
 * email_id is the primary key for this table.
 * Each row contains an email address.
 * 
 * Approach: String Extraction with GROUP BY
 * 
 * Key Insight:
 * - Extract domain from email addresses (part after '@')
 * - Filter domains that end with '.com'
 * - Count emails per domain
 * - Use SUBSTRING_INDEX to extract domain portion
 * 
 * Formula:
 *   domain = SUBSTRING_INDEX(email, '@', -1)
 *   Filter: domain LIKE '%.com'
 *   Count: COUNT(*) GROUP BY domain
 * 
 * Algorithm:
 * 1. Extract domain using SUBSTRING_INDEX(email, '@', -1)
 * 2. Filter with WHERE to keep only '.com' domains
 * 3. GROUP BY domain to aggregate emails per domain
 * 4. COUNT(*) to get number of emails per domain
 * 5. ORDER BY domain for ascending order
 * 
 * Example:
 * 
 *   Emails:
 *   +----------+---------------------------+
 *   | email_id | email                     |
 *   +----------+---------------------------+
 *   | 336      | hwkiy@test.com            |
 *   | 627      | adcmaf@example.com        |
 *   | 497      | vkflbf@example.com        |
 *   | 482      | bqyee@example.com         |
 *   | 36       | mredm@test.com            |
 *   | 777      | scnxj@example.org         |  ← not .com
 *   | 234      | xqnavi@leetcode.com       |
 *   +----------+---------------------------+
 * 
 *   Step 1 - Extract domains:
 *   hwkiy@test.com        → test.com
 *   adcmaf@example.com    → example.com
 *   vkflbf@example.com    → example.com
 *   bqyee@example.com     → example.com
 *   mredm@test.com        → test.com
 *   scnxj@example.org     → example.org  (filtered out)
 *   xqnavi@leetcode.com   → leetcode.com
 * 
 *   Step 2 - Filter .com domains:
 *   test.com, example.com, example.com, example.com, test.com, leetcode.com
 * 
 *   Step 3 - Group and count:
 *   example.com: 3 emails
 *   leetcode.com: 1 email
 *   test.com: 2 emails
 * 
 *   Result:
 *   +-------------+-------+
 *   | email_domain| count |
 *   +-------------+-------+
 *   | example.com | 3     |
 *   | leetcode.com| 1     |
 *   | test.com    | 2     |
 *   +-------------+-------+
 * 
 * SUBSTRING_INDEX Function:
 *   - SUBSTRING_INDEX(str, delimiter, count)
 *   - Extracts substring before/after delimiter
 *   - count > 0: from left, count < 0: from right
 *   - SUBSTRING_INDEX(email, '@', -1) gets everything after the last '@'
 *   - Example: 'user@example.com' → 'example.com'
 * 
 * Common Error: Column Alias Mismatch
 *   - SELECT ... AS email_domain
 *   - GROUP BY domain  ❌ (wrong - 'domain' doesn't exist)
 *   - GROUP BY email_domain  ✅ (correct - matches the alias)
 *   - In MySQL, you can reference aliases in GROUP BY and ORDER BY
 *   - But the alias name must match exactly!
 * 
 * Alternative Approaches:
 *   - Use full expression: GROUP BY SUBSTRING_INDEX(email, '@', -1)
 *   - Use REGEXP_SUBSTR (if supported)
 *   - Use RIGHT() with LOCATE() to find '@' position
 */

# Write your MySQL query statement below

SELECT
    SUBSTRING_INDEX(email, '@', -1) AS email_domain,
    COUNT(*) AS count
FROM Emails
WHERE SUBSTRING_INDEX(email, '@', -1) LIKE '%.com'
GROUP BY email_domain
ORDER BY email_domain;

