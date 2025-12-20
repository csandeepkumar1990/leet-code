/*
 * LeetCode 67: Add Binary
 * 
 * Problem:
 * Given two binary strings a and b, return their sum as a binary string.
 * 
 * Approach: Two Pointers from Right + Carry
 * 
 * Key Insight:
 * - Add digits from right to left (like elementary school addition)
 * - Track carry for overflow (when sum > 1 in binary)
 * - Continue while digits remain OR carry exists
 * 
 * Algorithm:
 * 1. Start pointers at the end of both strings
 * 2. Add digits + carry, compute new digit (sum % 2) and carry (sum / 2)
 * 3. Append to result, move pointers left
 * 4. Reverse result at the end
 * 
 * Time Complexity: O(max(m, n)) - process both strings once
 * Space Complexity: O(max(m, n)) - for the result string
 * 
 * Binary Addition Rules:
 * 
 *   0 + 0 = 0, carry 0
 *   0 + 1 = 1, carry 0
 *   1 + 0 = 1, carry 0
 *   1 + 1 = 0, carry 1  (2 in binary = "10")
 *   1 + 1 + 1 = 1, carry 1  (3 in binary = "11")
 * 
 * Example 1: a = "11", b = "1"
 * 
 *       1 1
 *     +   1
 *     -----
 *     1 0 0
 * 
 *   Step-by-step:
 *   i=1, j=0: sum = 0 + 1 + 1 = 2 → digit=2%2=0, carry=2/2=1
 *   i=0, j=-1: sum = 1 + 1 + 0 = 2 → digit=2%2=0, carry=2/2=1
 *   i=-1, j=-1: sum = 1 + 0 + 0 = 1 → digit=1%2=1, carry=1/2=0
 *   
 *   Result (built backwards): "001" → reverse → "100"
 * 
 * Example 2: a = "1010", b = "1011"
 * 
 *       1 0 1 0
 *     + 1 0 1 1
 *     ---------
 *     1 0 1 0 1
 * 
 *   Position 3: 0 + 1 = 1, carry = 0
 *   Position 2: 1 + 1 = 0, carry = 1
 *   Position 1: 0 + 0 + 1 = 1, carry = 0
 *   Position 0: 1 + 1 = 0, carry = 1
 *   Extra:      carry = 1
 *   
 *   Result: "10101"
 * 
 * Why sum % 2 and sum / 2?
 * 
 *   sum can be 0, 1, 2, or 3:
 *   
 *   sum=0: 0%2=0 (digit), 0/2=0 (carry)
 *   sum=1: 1%2=1 (digit), 1/2=0 (carry)
 *   sum=2: 2%2=0 (digit), 2/2=1 (carry)
 *   sum=3: 3%2=1 (digit), 3/2=1 (carry)
 * 
 * Why charAt(i) - '0'?
 * 
 *   '0' has ASCII value 48
 *   '1' has ASCII value 49
 *   
 *   '0' - '0' = 48 - 48 = 0
 *   '1' - '0' = 49 - 48 = 1
 *   
 *   Converts char to int!
 * 
 * Visual (Processing Order):
 * 
 *   a = "1 0 1 0"
 *        ← ← ← ←  (process right to left)
 *            i
 * 
 *   b = "1 0 1 1"
 *        ← ← ← ←
 *            j
 * 
 *   Result built: "1" → "01" → "101" → "0101" → "10101"
 *                  ↓
 *                reverse
 *                  ↓
 *               "10101"
 * 
 * Why while (i >= 0 || j >= 0 || carry > 0)?
 * 
 *   - i >= 0: Still digits in a
 *   - j >= 0: Still digits in b
 *   - carry > 0: Need to add final carry (e.g., "1" + "1" = "10")
 */

class Solution {

    public String addBinary(String a, String b) {

        StringBuilder result = new StringBuilder();

        int i = a.length() - 1;

        int j = b.length() - 1;

        int carry = 0;

        // process both strings from end to start
        while (i >= 0 || j >= 0 || carry > 0) {

            int sum = carry;

            if (i >= 0) {

                sum += a.charAt(i) - '0'; // convert char to int

                i--;

            }

            if (j >= 0) {

                sum += b.charAt(j) - '0';

                j--;

            }

            result.append(sum % 2); // append binary digit

            carry = sum / 2; // update carry

        }

        return result.reverse().toString();

    }

}

