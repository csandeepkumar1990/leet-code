/**
 * LeetCode 405: Convert a Number to Hexadecimal
 * 
 * Problem: Given an integer num, return its hexadecimal representation as a string.
 *          For negative integers, use two's complement representation.
 *          No leading zeros (except for "0" itself). Use lowercase letters.
 *          Cannot use built-in library methods.
 * 
 * Key Insight: Process 4 bits at a time (1 hex digit = 4 bits).
 *              Use unsigned right shift (>>>) to handle negative numbers correctly.
 *              Two's complement is automatically handled by Java's int representation!
 * 
 * Examples:
 *   Input: num = 26
 *   Output: "1a"
 *   Explanation: 26 = 16 + 10 = 1×16 + 10 = "1a"
 * 
 *   Input: num = -1
 *   Output: "ffffffff"
 *   Explanation: -1 in two's complement (32 bits) = all 1s = 0xFFFFFFFF
 * 
 *   Input: num = 0
 *   Output: "0"
 * 
 * Constraints:
 *   - -2^31 <= num <= 2^31 - 1
 */

class Solution {

    /**
     * Converts an integer to its hexadecimal string representation.
     * 
     * @param num - The integer to convert (can be negative)
     * @return The hexadecimal string (lowercase, no leading zeros)
     * 
     * Time Complexity: O(1) - max 8 iterations (32 bits / 4 bits per hex digit)
     * Space Complexity: O(1) - StringBuilder holds at most 8 characters
     */
    public String toHex(int num) {
        /*
         * Handle special case: zero
         * Without this, the while loop would never execute for num = 0
         */
        if (num == 0) {
            return "0";
        }

        StringBuilder hexBuilder = new StringBuilder();

        /*
         * Process 4 bits at a time (one hex digit = 4 bits)
         * 
         * Why 4 bits? Because hex is base-16 and 2^4 = 16
         * Each hex digit (0-9, a-f) represents exactly 4 binary bits.
         * 
         * Example: 26 in binary = 0001 1010
         *                         └──┘ └──┘
         *                          1    a   → "1a"
         */
        while (num != 0) {
            /*
             * Extract the lowest 4 bits using bitwise AND with 0xF (15 = 1111)
             * 
             * num & 0xF isolates the last 4 bits:
             *   Any bits: ...XXXX
             *   0xF mask: ...1111
             *   Result:   ...0000XXXX (only last 4 bits preserved)
             */
            int hexDigit = num & 0xF;  // 0xF = 15 in binary: 1111

            if (hexDigit < 10) {
                // For values 0-9, append the digit directly
                hexBuilder.append(hexDigit);
            } else {
                /*
                 * For values 10-15, convert to 'a'-'f'
                 * 
                 * hexDigit - 10 gives: 10→0, 11→1, 12→2, 13→3, 14→4, 15→5
                 * Adding 'a' (ASCII 97) converts to: 'a', 'b', 'c', 'd', 'e', 'f'
                 */
                char hexChar = (char) (hexDigit - 10 + 'a');
                hexBuilder.append(hexChar);
            }

            /*
             * Unsigned right shift by 4 bits to process the next hex digit
             * 
             * Why >>> instead of >>?
             * - >> (signed shift): fills with sign bit (1 for negatives)
             * - >>> (unsigned shift): always fills with 0
             * 
             * For negative numbers, >> would cause infinite loop!
             * >>> ensures we eventually reach 0.
             */
            num >>>= 4;
        }

        /*
         * Reverse because we processed least significant digit first
         * 
         * Example for 26: we appended 'a' then '1' → "a1"
         * After reverse: "1a" ✓
         */
        return hexBuilder.reverse().toString();
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.toHex(26));  // Output: "1a"
 * System.out.println(sol.toHex(-1));  // Output: "ffffffff"
 * System.out.println(sol.toHex(0));   // Output: "0"
 * System.out.println(sol.toHex(16));  // Output: "10"
 * 
 * ═══════════════════════════════════════════════════════════════
 * HEX DIGIT MAPPING
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Value  |  Hex  |  Binary
 *   -------|-------|--------
 *     0    |   0   |  0000
 *     1    |   1   |  0001
 *     ...  |  ...  |  ...
 *     9    |   9   |  1001
 *    10    |   a   |  1010
 *    11    |   b   |  1011
 *    12    |   c   |  1100
 *    13    |   d   |  1101
 *    14    |   e   |  1110
 *    15    |   f   |  1111
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: num = 26
 * ═══════════════════════════════════════════════════════════════
 * 
 * 26 in binary: 0001 1010
 * 
 * Iteration 1:
 *   hexDigit = 26 & 0xF = 26 & 15 = 10 → 'a'
 *   hexBuilder = "a"
 *   num = 26 >>> 4 = 1
 * 
 * Iteration 2:
 *   hexDigit = 1 & 0xF = 1 → '1'
 *   hexBuilder = "a1"
 *   num = 1 >>> 4 = 0
 * 
 * Loop ends (num = 0)
 * Reverse: "a1" → "1a"
 * 
 * Answer: "1a" ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY NEGATIVE NUMBERS WORK (TWO'S COMPLEMENT)
 * ═══════════════════════════════════════════════════════════════
 * 
 * In Java, int is 32 bits using two's complement:
 * 
 * -1 in two's complement = all 1s = 1111 1111 1111 1111 1111 1111 1111 1111
 *                                   └──┘ └──┘ └──┘ └──┘ └──┘ └──┘ └──┘ └──┘
 *                                    f    f    f    f    f    f    f    f
 *                                   = "ffffffff"
 * 
 * Using >>> (unsigned shift) treats the number as unsigned,
 * so it correctly shifts in 0s from the left, eventually reaching 0.
 * 
 * With >> (signed shift), -1 would stay -1 forever (infinite loop)!
 */

