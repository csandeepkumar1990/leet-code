/*
 * LeetCode 3436: Validate Email Addresses
 * 
 * Problem:
 * Write a solution to find all users with valid email addresses.
 * A valid email address must:
 *   - Start with alphanumeric characters, underscores (local part)
 *   - Contain @ symbol
 *   - Have a domain name with only letters
 *   - End with .com
 * Return the result table ordered by user_id in ascending order.
 * 
 * Table: Users
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | user_id     | int     |
 * | email       | varchar |
 * +-------------+---------+
 * user_id is the primary key for this table.
 * Each row contains information about a user and their email.
 * 
 * Approach: Regular Expression Pattern Matching
 * 
 * Key Insight:
 * - Use REGEXP to validate email format
 * - Pattern must match entire email from start to end
 * - Local part: alphanumeric and underscores
 * - Domain: only letters, ending with .com
 * 
 * Email Format:
 *   local@domain.com
 *   - local: [A-Za-z0-9_]+ (one or more alphanumeric/underscore)
 *   - @: literal @ symbol
 *   - domain: [A-Za-z]+ (one or more letters)
 *   - .com: literal .com (dot must be escaped)
 * 
 * Regular Expression Pattern:
 *   ^[A-Za-z0-9_]+@[A-Za-z]+\\.com$
 *   
 *   Breakdown:
 *   ^              - Start of string anchor
 *   [A-Za-z0-9_]+  - One or more: letters, digits, or underscore (local part)
 *   @              - Literal @ symbol
 *   [A-Za-z]+      - One or more letters (domain name)
 *   \\.            - Literal dot (escaped with backslash)
 *   com            - Literal "com"
 *   $              - End of string anchor
 * 
 * Formula:
 *   Filter: email REGEXP '^[A-Za-z0-9_]+@[A-Za-z]+\\.com$'
 *   Sort: ORDER BY user_id ASC
 * 
 * Algorithm:
 * 1. Use REGEXP to match entire email pattern
 * 2. Pattern validates: local@domain.com format
 * 3. Local part: alphanumeric and underscores only
 * 4. Domain: letters only, ending with .com
 * 5. Order results by user_id
 * 
 * Example:
 * 
 *   Users:
 *   +---------+-------------------+
 *   | user_id | email              |
 *   +---------+-------------------+
 *   | 1       | john@example.com   |
 *   | 2       | jane_doe@test.com  |
 *   | 3       | user123@mail.com   |
 *   | 4       | invalid@email      |
 *   | 5       | test@123domain.com |
 *   | 6       | user@domain.org    |
 *   | 7       | _user@example.com  |
 *   | 8       | user@domain.com     |
 *   +---------+-------------------+
 * 
 *   Step 1 - Validate pattern:
 *   john@example.com:     ✓ (valid format)
 *   jane_doe@test.com:    ✓ (valid format)
 *   user123@mail.com:     ✓ (valid format)
 *   invalid@email:        ✗ (missing .com)
 *   test@123domain.com:   ✗ (domain has digits)
 *   user@domain.org:      ✗ (ends with .org, not .com)
 *   _user@example.com:     ✗ (starts with underscore, but pattern allows it)
 *   user@domain.com:       ✓ (valid format)
 * 
 *   Result:
 *   +---------+-------------------+
 *   | user_id | email              |
 *   +---------+-------------------+
 *   | 1       | john@example.com   |
 *   | 2       | jane_doe@test.com  |
 *   | 3       | user123@mail.com   |
 *   | 8       | user@domain.com    |
 *   +---------+-------------------+
 * 
 * Pattern Components Explained:
 *   ^                    - Anchors pattern to start of string
 *   [A-Za-z0-9_]+        - Character class: letters, digits, underscore
 *                         + quantifier: one or more characters
 *   @                    - Literal @ symbol (separator)
 *   [A-Za-z]+            - Character class: letters only
 *                         + quantifier: one or more letters
 *   \\.                  - Escaped dot (literal period)
 *                         In MySQL strings, \\ escapes to single \
 *   com                  - Literal "com"
 *   $                    - Anchors pattern to end of string
 * 
 * Why Escape the Dot?
 *   - In regex, . matches any character
 *   - To match literal dot, must escape: \.
 *   - In MySQL string, backslash must be escaped: \\.
 *   - Result: \\. in pattern matches literal .
 * 
 * Character Classes:
 *   [A-Za-z]    - Any letter (uppercase or lowercase)
 *   [0-9]       - Any digit
 *   [A-Za-z0-9_] - Letters, digits, or underscore
 *   [^...]      - Negated class (anything except...)
 * 
 * Quantifiers:
 *   +           - One or more (at least 1)
 *   *           - Zero or more (0 or more)
 *   ?           - Zero or one (optional)
 *   {n}         - Exactly n occurrences
 *   {n,m}       - Between n and m occurrences
 * 
 * Why Anchors (^ and $)?
 *   - ^ ensures pattern starts at beginning
 *   - $ ensures pattern ends at end
 *   - Without anchors, pattern matches anywhere in string
 *   - With anchors, entire string must match pattern
 *   - Example: Without ^$, "invalid@email.com" would match (contains valid part)
 * 
 * Common Email Validation Patterns:
 *   - Simple: ^[A-Za-z0-9_]+@[A-Za-z]+\\.com$
 *   - More complex: ^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$
 *   - RFC compliant: Much more complex pattern
 * 
 * Edge Cases:
 *   - Empty email: Doesn't match (requires at least one char before @)
 *   - Missing @: Doesn't match
 *   - Missing .com: Doesn't match
 *   - Domain with digits: Doesn't match (domain must be letters only)
 *   - Multiple @: Doesn't match (pattern requires exactly one @)
 *   - Starts with dot: Doesn't match (local part must start with alphanumeric/underscore)
 */

# Write your MySQL query statement below

SELECT *
FROM Users
WHERE email REGEXP '^[A-Za-z0-9_]+@[A-Za-z]+\\.com$'
ORDER BY user_id;

