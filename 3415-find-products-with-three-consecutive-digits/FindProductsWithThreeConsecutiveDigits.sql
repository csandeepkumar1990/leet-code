/*
 * LeetCode 3415: Find Products With Three Consecutive Digits
 * 
 * Problem:
 * Write a solution to find all products whose name contains exactly
 * three consecutive digits.
 * Return the result table ordered by product_id in ascending order.
 * 
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | name        | varchar |
 * +-------------+---------+
 * product_id is the primary key for this table.
 * Each row contains information about a product.
 * 
 * Approach: Regular Expression Pattern Matching
 * 
 * Key Insight:
 * - Need to find products with exactly 3 consecutive digits in name
 * - Use REGEXP to match pattern [0-9]{3}
 * - Pattern matches any sequence of exactly 3 digits
 * 
 * Regular Expression Pattern:
 *   Pattern matches exactly 3 consecutive digits that are NOT part of a longer sequence
 *   - ^[0-9][0-9][0-9][^0-9]: 3 digits at start, followed by non-digit
 *   - [^0-9][0-9][0-9][0-9][^0-9]: 3 digits in middle, with non-digits before and after
 *   - [^0-9][0-9][0-9][0-9]$: 3 digits at end, preceded by non-digit
 *   - ^[0-9][0-9][0-9]$: Entire string is exactly 3 digits
 *   - Combined with OR (|) to match any of these cases
 * 
 * Formula:
 *   Filter: name REGEXP pattern matching exactly 3 consecutive digits
 *   Pattern: '^[0-9][0-9][0-9][^0-9]|[^0-9][0-9][0-9][0-9][^0-9]|[^0-9][0-9][0-9][0-9]$|^[0-9][0-9][0-9]$'
 *   Sort: ORDER BY product_id ASC
 * 
 * Algorithm:
 * 1. Use REGEXP to match exactly 3 consecutive digits (not part of longer sequence)
 * 2. Pattern matches any substring with exactly 3 consecutive digits
 * 3. Order results by product_id
 * 
 * Example:
 * 
 *   Products:
 *   +------------+-------------------+
 *   | product_id | name              |
 *   +------------+-------------------+
 *   | 1          | Product123        |
 *   | 2          | Item45            |
 *   | 3          | Widget789         |
 *   | 4          | Tool12            |
 *   | 5          | Gadget456         |
 *   | 6          | Device7            |
 *   +------------+-------------------+
 * 
 *   Step 1 - Check pattern (exactly 3 digits, not part of longer sequence):
 *   ABC123XYZ: Contains "123" with non-digits before/after ✓
 *   A12B34C: Contains "12" and "34" (2 digits each) ✗
 *   Product56789: Contains "567" but followed by "8" (digit) ✗
 *   NoDigitsHere: No digits ✗
 *   789Product: Starts with "789" followed by non-digit ✓
 *   Item003Description: Contains "003" with non-digits before/after ✓
 * 
 *   Result:
 *   +------------+-------------------+
 *   | product_id | name              |
 *   +------------+-------------------+
 *   | 1          | ABC123XYZ         |
 *   | 5          | 789Product        |
 *   | 6          | Item003Description|
 *   +------------+-------------------+
 * 
 *   Note: Product56789 is NOT included because "567" is part of longer sequence "56789"
 * 
 * REGEXP Operator:
 *   - REGEXP performs regular expression pattern matching
 *   - Returns TRUE if pattern matches, FALSE otherwise
 *   - Case-insensitive by default (unless using REGEXP BINARY)
 *   - Alternative syntax: name REGEXP pattern or name RLIKE pattern
 * 
 * Pattern Breakdown:
 *   [0-9]              - Character class: any digit from 0 to 9
 *   [^0-9]             - Character class: any non-digit character
 *   ^                   - Start of string anchor
 *   $                   - End of string anchor
 *   |                   - OR operator (matches any of the patterns)
 *   
 *   The pattern ensures 3 digits are:
 *   - Not preceded by a digit (or at start of string)
 *   - Not followed by a digit (or at end of string)
 * 
 * Common Patterns:
 *   [0-9][0-9][0-9] - Exactly 3 digits (reliable in MySQL)
 *   [0-9][0-9]      - Exactly 2 digits
 *   [0-9]+          - One or more digits
 *   [0-9]*          - Zero or more digits
 *   [0-9]?          - Zero or one digit
 *   Note: {n} quantifier syntax may not work reliably in MySQL REGEXP
 * 
 * Why [0-9] instead of \d?
 *   - MySQL REGEXP uses POSIX extended regular expressions
 *   - \d is not supported in MySQL REGEXP
 *   - Use [0-9] or [[:digit:]] for digits
 *   - [0-9] is more portable and widely used
 * 
 * Pattern Matching Behavior:
 *   - Matches exactly 3 consecutive digits that are NOT part of a longer sequence
 *   - "ABC123XYZ" matches (123 with non-digits before/after)
 *   - "789Product" matches (789 at start, followed by non-digit)
 *   - "Item003Description" matches (003 with non-digits before/after)
 *   - "Product56789" doesn't match (567 is part of longer sequence 56789)
 *   - "A12B34C" doesn't match (only 2-digit sequences)
 * 
 * Alternative Approaches:
 *   - Using LIKE with wildcards (more complex, less flexible)
 *   - Using SUBSTRING with pattern matching (inefficient)
 *   - Using REGEXP is the most elegant solution
 * 
 * Edge Cases:
 *   - Name with 4 digits: Still matches (contains 3 consecutive)
 *   - Name with 2 digits: Doesn't match
 *   - Name with multiple 3-digit sequences: Matches
 *   - Empty name: Doesn't match
 *   - Name with only digits: Matches if has 3+ digits
 */

# Write your MySQL query statement below

SELECT *
FROM Products
WHERE name REGEXP '^[0-9][0-9][0-9][^0-9]|[^0-9][0-9][0-9][0-9][^0-9]|[^0-9][0-9][0-9][0-9]$|^[0-9][0-9][0-9]$'
ORDER BY product_id;

