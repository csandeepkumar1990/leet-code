/*
 * 3726. Remove Zeros from Number
 * 
 * Problem:
 * Given a positive integer n, return the number formed by removing all
 * zeros from n.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: n = 102
 *   Output: 12
 *   Explanation: Remove the '0' → "102" becomes "12" → 12
 * 
 * Example 2:
 *   Input: n = 1000
 *   Output: 1
 *   Explanation: Remove all three '0's → "1000" becomes "1" → 1
 * 
 * Example 3:
 *   Input: n = 12345
 *   Output: 12345
 *   Explanation: No zeros to remove, number stays the same.
 * 
 * Example 4:
 *   Input: n = 10203040
 *   Output: 1234
 *   Explanation: Remove all '0's → "10203040" becomes "1234"
 * 
 * Example 5:
 *   Input: n = 9000009
 *   Output: 99
 *   Explanation: Remove all five '0's → "9000009" becomes "99"
 * 
 * Example 6:
 *   Input: n = 500
 *   Output: 5
 *   Explanation: Remove trailing zeros → "500" becomes "5"
 * 
 * Constraints:
 *   - 1 <= n <= 10^18
 *   - The result will not have leading zeros (since we start with positive n)
 * 
 * Approach: String Conversion + Replace
 * 
 * Algorithm:
 * 1. Convert number to string
 * 2. Remove all '0' characters using replace("0", "")
 * 3. Convert back to long
 * 4. Handle edge case: if all digits were zeros (shouldn't happen with positive n)
 * 
 * Why String approach?
 *   - Simple and readable
 *   - Built-in replace() handles all occurrences
 *   - Works efficiently for the given constraints
 * 
 * Time Complexity: O(d)
 *   - Where d = number of digits in n
 *   - String operations are linear in length
 * 
 * Space Complexity: O(d)
 *   - String representation of the number
 */

class Solution {

    public long removeZeros(long n) {

        // Convert number to string and remove all '0' characters
        // replace("0", "") replaces every '0' with empty string
        String cleaned = String.valueOf(n).replace("0", "");

        // Handle edge case: if string becomes empty (all zeros)
        // Return 0, though this shouldn't happen with positive n input
        // Otherwise, parse back to long
        return cleaned.isEmpty() ? 0 : Long.parseLong(cleaned);

    }

}

/*
 * Dry Run Example:
 * 
 * Input: n = 10203040
 * 
 * Step 1: Convert to String
 *   String.valueOf(10203040) = "10203040"
 * 
 * Step 2: Remove all '0's
 *   "10203040".replace("0", "")
 *   = "1" + "" + "2" + "" + "3" + "" + "4" + ""
 *   = "1234"
 * 
 * Step 3: Check and convert back
 *   cleaned = "1234" (not empty)
 *   Long.parseLong("1234") = 1234
 * 
 * Output: 1234
 * 
 * 
 * Visualization:
 * 
 * Input:  1 0 2 0 3 0 4 0
 *           ↓   ↓   ↓   ↓
 *         remove zeros
 *           ↓   ↓   ↓   ↓
 * Output: 1   2   3   4     → 1234
 * 
 * 
 * Why use long instead of int?
 * 
 * Constraint says n can be up to 10^18
 * int range: -2^31 to 2^31-1 ≈ ±2.1 × 10^9
 * long range: -2^63 to 2^63-1 ≈ ±9.2 × 10^18
 * 
 * 10^18 exceeds int, so long is necessary!
 * 
 * 
 * Edge Cases:
 * 
 * Case 1: No zeros
 *   n = 12345 → "12345".replace("0","") = "12345" → 12345
 * 
 * Case 2: All zeros except one digit
 *   n = 1000 → "1000".replace("0","") = "1" → 1
 * 
 * Case 3: Zeros in middle
 *   n = 101 → "101".replace("0","") = "11" → 11
 * 
 * Case 4: Leading digit followed by zeros
 *   n = 50 → "50".replace("0","") = "5" → 5
 * 
 * 
 * Alternative Approach (Mathematical - without String):
 * 
 * public long removeZeros(long n) {
 *     long result = 0;
 *     long multiplier = 1;
 *     
 *     while (n > 0) {
 *         long digit = n % 10;
 *         if (digit != 0) {
 *             result = digit * multiplier + result;
 *             multiplier *= 10;
 *         }
 *         n /= 10;
 *     }
 *     
 *     return result;
 * }
 * 
 * This builds the result digit by digit, skipping zeros.
 * More complex but avoids String conversion overhead.
 * 
 * 
 * Why isEmpty() check?
 * 
 * Long.parseLong("") throws NumberFormatException!
 * 
 * Although the problem guarantees positive n (so at least one non-zero digit),
 * the isEmpty() check makes the code robust and defensive.
 * 
 * Your ternary operator handles this elegantly:
 *   cleaned.isEmpty() ? 0 : Long.parseLong(cleaned)
 */

