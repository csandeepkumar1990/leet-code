/*
 * LeetCode 415: Add Strings
 * 
 * Problem:
 * Given two non-negative integers num1 and num2 represented as strings,
 * return the sum of num1 and num2 as a string.
 * 
 * Constraints: Must not use BigInteger or convert strings to integers directly.
 * 
 * Approach: Grade-School Addition (Right to Left)
 * 
 * Key Insight:
 * - Simulate how we add numbers by hand: start from rightmost digit
 * - Add corresponding digits plus carry
 * - Keep track of carry for next position
 * 
 * Algorithm:
 * 1. Start from the last digit of both strings
 * 2. Add digits + carry, compute new digit and carry
 * 3. Move left until both strings exhausted AND no carry remains
 * 4. Reverse result (we built it backwards)
 * 
 * Handling Different Lengths:
 * - Use 0 for missing digits when one string is shorter
 * - Continue as long as any pointer valid OR carry exists
 * 
 * Time Complexity: O(max(n, m)) where n, m are lengths of num1, num2
 * Space Complexity: O(max(n, m)) for the result string
 * 
 * Example: num1 = "456", num2 = "77"
 * 
 *       4 5 6
 *     +   7 7
 *     -------
 * 
 * Step 1: i=2, j=1
 *   6 + 7 + 0 = 13
 *   digit = 3, carry = 1
 *   result = "3"
 * 
 * Step 2: i=1, j=0
 *   5 + 7 + 1 = 13
 *   digit = 3, carry = 1
 *   result = "33"
 * 
 * Step 3: i=0, j=-1 (j exhausted, use 0)
 *   4 + 0 + 1 = 5
 *   digit = 5, carry = 0
 *   result = "335"
 * 
 * Reverse: "533"
 * 
 * Example with final carry: "999" + "1"
 * 
 *   9 9 9
 * +     1
 * -------
 * 
 * Steps: 9+1=10 → 9+1=10 → 9+1=10 → carry=1
 * Result (before reverse): "0001"
 * After reverse: "1000" ✓
 * 
 * Visual (Addition Process):
 * 
 *     num1:     4   5   6
 *               ↑   ↑   ↑
 *               i=0 i=1 i=2 (start here)
 *     
 *     num2:         7   7
 *                   ↑   ↑
 *                   j=0 j=1 (start here)
 *     
 *     Process right-to-left:
 *     
 *     Position 2: 6 + 7 = 13 → write 3, carry 1
 *     Position 1: 5 + 7 + 1 = 13 → write 3, carry 1
 *     Position 0: 4 + 0 + 1 = 5 → write 5, carry 0
 *     
 *     Built: "335" → reverse → "533"
 */

class Solution {

    public String addStrings(String num1, String num2) {

        StringBuilder result = new StringBuilder();

        // Start from the rightmost digit of each number
        int i = num1.length() - 1;

        int j = num2.length() - 1;

        int carry = 0;

        // Continue while there are digits to process OR carry remains
        while (i >= 0 || j >= 0 || carry > 0) {

            // Get digit from num1 (or 0 if exhausted)
            int digit1 = (i >= 0) ? num1.charAt(i) - '0' : 0;

            // Get digit from num2 (or 0 if exhausted)
            int digit2 = (j >= 0) ? num2.charAt(j) - '0' : 0;

            // Add both digits plus carry from previous position
            int sum = digit1 + digit2 + carry;

            // New carry for next position (1 if sum >= 10, else 0)
            carry = sum / 10;

            // Current digit to append (sum mod 10)
            result.append(sum % 10);

            // Move to next digit (left)
            i--;

            j--;

        }

        // We built the number backwards, so reverse it
        return result.reverse().toString();

    }

}

