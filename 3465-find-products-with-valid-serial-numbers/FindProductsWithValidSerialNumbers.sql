/*
 * LeetCode 3465: Find Products with Valid Serial Numbers
 * 
 * Problem:
 * Write a solution to find all products whose description contains a valid serial number.
 * A valid serial number format is:
 *   - Starts with "SN"
 *   - Followed by exactly 4 digits
 *   - Contains a hyphen "-"
 *   - Followed by exactly 4 digits
 * The serial number can appear anywhere in the description.
 * Return the result table ordered by product_id in ascending order.
 * 
 * Table: Products
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | product_id  | int     |
 * | name        | varchar |
 * | description | varchar |
 * +-------------+---------+
 * product_id is the primary key for this table.
 * Each row contains information about a product.
 * 
 * Approach: Regular Expression Pattern Matching
 * 
 * Key Insight:
 * - Use REGEXP to match serial number pattern
 * - Pattern: SN + 4 digits + hyphen + 4 digits
 * - Serial number can appear anywhere in description
 * 
 * Serial Number Format:
 *   SN####-####
 *   - SN: Literal characters "SN"
 *   - ####: Exactly 4 digits
 *   - -: Literal hyphen
 *   - ####: Exactly 4 digits
 * 
 * Regular Expression Pattern:
 *   (^|[^A-Za-z0-9])SN[0-9]{4}-[0-9]{4}([^0-9]|$)
 *   
 *   Breakdown:
 *   (^|[^A-Za-z0-9])      - Start of string OR non-alphanumeric character
 *                          - Ensures "SN" is not part of a word or alphanumeric sequence (e.g., "ASN", "1SN")
 *   SN                    - Literal "SN" (case-sensitive)
 *   [0-9]{4}              - Exactly 4 digits before hyphen (quantifier syntax)
 *   -                     - Literal hyphen
 *   [0-9]{4}              - Exactly 4 digits after hyphen (quantifier syntax)
 *   ([^0-9]|$)            - Followed by non-digit OR end of string
 *                          - Ensures exactly 4 digits (not 5+)
 * 
 * Formula:
 *   Filter: REGEXP_LIKE(description, '(^|[^A-Za-z0-9])SN[0-9]{4}-[0-9]{4}([^0-9]|$)', 'c')
 *   Sort: ORDER BY product_id ASC
 * 
 * Algorithm:
 * 1. Use REGEXP_LIKE to match serial number pattern
 * 2. Pattern: SN followed by 4 digits, hyphen, 4 digits
 * 3. 'c' flag ensures case-sensitive matching
 * 4. Order results by product_id
 * 
 * Example:
 * 
 *   Products:
 *   +------------+--------+---------------------------+
 *   | product_id | name   | description               |
 *   +------------+--------+---------------------------+
 *   | 1          | Widget | Product SN1234-5678      |
 *   | 2          | Gadget | Serial: SN9876-5432      |
 *   | 3          | Tool   | SN12-34 Invalid          |
 *   | 4          | Item   | Code: SN12345-6789       |
 *   | 5          | Device | SN0001-9999 is valid      |
 *   | 6          | Part   | No serial number here    |
 *   +------------+--------+---------------------------+
 * 
 *   Step 1 - Validate pattern SN####-####:
 *   Widget A: "SN1234-5678" → ✓ (valid: SN at start, exactly 4 digits)
 *   Widget B: "SN9876-1234" → ✓ (valid: SN at start, exactly 4 digits)
 *   Widget C: "SN1234-56789" → ✗ (5 digits after hyphen, not exactly 4)
 *   Widget D: No pattern    → ✗ (no SN pattern found)
 *   Widget E: "ASN4321-8765" → ✗ (SN is part of "ASN", not standalone)
 * 
 *   Result:
 *   +------------+-----------+----------------------------------------------+
 *   | product_id | name      | description                                  |
 *   +------------+-----------+----------------------------------------------+
 *   | 1          | Widget A  | This is a sample product with SN1234-5678  |
 *   | 2          | Widget B  | A product with serial SN9876-1234 in the... |
 *   | 5          | Widget E  | Check out SN4321-8765 in this description  |
 *   +------------+-----------+----------------------------------------------+
 * 
 *   Note: Widget C (SN1234-56789) is excluded because it has 5 digits after hyphen
 * 
 * Pattern Components Explained:
 *   SN                    - Literal characters "SN" (case-sensitive)
 *   [0-9]                 - Character class: any digit (0-9)
 *   [0-9][0-9][0-9][0-9]  - Exactly 4 digits (repeated pattern)
 *   -                     - Literal hyphen character
 * 
 * Why Not Use {4} Quantifier?
 *   - Pattern [0-9]{4} may not work reliably in MySQL REGEXP
 *   - MySQL REGEXP uses POSIX extended regular expressions
 *   - Quantifier syntax {n} may not be supported in all MySQL versions
 *   - Solution: Repeat the pattern [0-9] four times
 *   - More reliable: [0-9][0-9][0-9][0-9]
 * 
 * Common Error: Using {4} Quantifier
 *   - Problem: REGEXP 'SN[0-9]{4}-[0-9]{4}'
 *   - Issue: {4} quantifier may not work in MySQL REGEXP
 *   - Fix: Use repeated pattern [0-9][0-9][0-9][0-9]
 * 
 * Pattern Matching Behavior:
 *   - Matches anywhere in the description string
 *   - "Product SN1234-5678" → matches (SN preceded by space, exactly 4 digits)
 *   - "SN1234-5678 Item" → matches (SN at start, exactly 4 digits, followed by space)
 *   - "SN1234-56789" → doesn't match (5 digits after hyphen)
 *   - "SN123-4567" → doesn't match (only 3 digits before hyphen)
 *   - "SN12345-6789" → doesn't match (5 digits before hyphen)
 *   - "ASN4321-8765" → doesn't match (SN is part of "ASN", not standalone)
 * 
 * Why (^|[^A-Za-z0-9]) before SN is needed:
 *   - Without it: "ASN4321-8765" would match (finds "SN4321-8765" inside "ASN")
 *   - With it: Ensures "SN" is not part of a longer word or alphanumeric sequence
 *   - ^: Start of string (SN can be at beginning)
 *   - [^A-Za-z0-9]: Non-alphanumeric character (space, punctuation, etc.)
 *   - Together: SN must be at start OR preceded by non-alphanumeric character
 *   - This prevents matching "ASN", "1SN", "XSN", etc.
 * 
 * Why ([^0-9]|$) after digits is needed:
 *   - Without it: "SN1234-56789" would match (finds "SN1234-5678")
 *   - With it: Ensures exactly 4 digits after hyphen
 *   - [^0-9]: Non-digit character (space, letter, punctuation, etc.)
 *   - $: End of string
 *   - Together: 4 digits must be followed by non-digit OR end of string
 * 
 * Serial Number Examples:
 *   Valid:
 *   - SN1234-5678
 *   - SN0000-9999
 *   - SN9876-5432
 *   
 *   Invalid:
 *   - ASN4321-8765 (SN is part of "ASN", not standalone)
 *   - SN123-4567 (only 3 digits before hyphen)
 *   - SN12345-6789 (5 digits before hyphen)
 *   - SN1234-567 (only 3 digits after hyphen)
 *   - SN1234-56789 (5 digits after hyphen - not exactly 4)
 *   - sn1234-5678 (lowercase "sn", not "SN")
 * 
 * Edge Cases:
 *   - Description with multiple serial numbers: Matches if any is valid
 *   - Serial number at start: Matches
 *   - Serial number at end: Matches
 *   - Serial number in middle: Matches
 *   - Case sensitivity: "SN" required (not "sn" or "Sn")
 *   - Leading zeros: Valid (e.g., SN0001-9999)
 */

# Write your MySQL query statement below

SELECT *
FROM Products
WHERE REGEXP_LIKE(
  description,
  '(^|[^A-Za-z0-9])SN[0-9]{4}-[0-9]{4}([^0-9]|$)',
  'c'
)
ORDER BY product_id;

