/**
 * LeetCode 476: Number Complement
 * 
 * Problem: The complement of an integer is the integer you get when you flip
 *          all the 0's to 1's and all the 1's to 0's in its binary representation.
 *          Given a positive integer num, return its complement.
 *          (No leading zeros in the binary representation)
 * 
 * Key Insight: XOR with a mask of all 1s (same bit length) flips all bits!
 *              num XOR mask = complement
 *              5 (101) XOR 7 (111) = 2 (010)
 * 
 * Examples:
 *   Input: num = 5
 *   Output: 2
 *   Explanation: 5 = 101 (binary)
 *                Complement = 010 = 2
 * 
 *   Input: num = 1
 *   Output: 0
 *   Explanation: 1 = 1 (binary)
 *                Complement = 0
 * 
 *   Input: num = 7
 *   Output: 0
 *   Explanation: 7 = 111 (binary)
 *                Complement = 000 = 0
 * 
 * Constraints:
 *   - 1 <= num < 2^31
 */

class Solution {

    /**
     * Returns the complement of the given number.
     * 
     * @param num - The positive integer to complement
     * @return The complement (all bits flipped)
     * 
     * Time Complexity: O(log n) - toBinaryString takes O(log n)
     * Space Complexity: O(log n) - binary string storage
     */
    public int findComplement(int num) {
        /*
         * STEP 1: Find the bit length of num
         * 
         * We need to know how many bits are "active" in num.
         * Leading zeros don't count!
         * 
         * Example: num = 5 (binary: 101)
         *          bitLength = 3
         */
        int bitLength = Integer.toBinaryString(num).length();

        /*
         * STEP 2: Create a mask of all 1s with the same bit length
         * 
         * Formula: mask = (1 << bitLength) - 1
         * 
         * How it works:
         *   1 << 3 = 1000 (binary) = 8
         *   8 - 1  = 0111 (binary) = 7
         * 
         * This gives us a mask with exactly bitLength number of 1s.
         * 
         * Example: bitLength = 3
         *          1 << 3 = 8 (1000)
         *          8 - 1  = 7 (0111) ← mask
         */
        int mask = (1 << bitLength) - 1;

        /*
         * STEP 3: XOR num with mask to flip all bits
         * 
         * XOR truth table:
         *   0 XOR 1 = 1 (0 becomes 1)
         *   1 XOR 1 = 0 (1 becomes 0)
         * 
         * XOR with 1 flips the bit!
         * 
         * Example: num = 5 (101), mask = 7 (111)
         *   101
         * ^ 111
         * -----
         *   010 = 2 ← complement!
         */
        return num ^ mask;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.findComplement(5)); // Output: 2
 * System.out.println(sol.findComplement(1)); // Output: 0
 * System.out.println(sol.findComplement(7)); // Output: 0
 * 
 * ═══════════════════════════════════════════════════════════════
 * BIT MANIPULATION BREAKDOWN
 * ═══════════════════════════════════════════════════════════════
 * 
 * Why (1 << n) - 1 creates n ones?
 * 
 *   1 << 0 = 1     = 1      → 1 - 1 = 0      = (empty)
 *   1 << 1 = 10    = 2      → 2 - 1 = 1      = 1
 *   1 << 2 = 100   = 4      → 4 - 1 = 3      = 11
 *   1 << 3 = 1000  = 8      → 8 - 1 = 7      = 111
 *   1 << 4 = 10000 = 16     → 16 - 1 = 15    = 1111
 * 
 * Pattern: (1 << n) - 1 = n consecutive 1s
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: num = 5
 * ═══════════════════════════════════════════════════════════════
 * 
 * Step 1: bitLength = "101".length() = 3
 * 
 * Step 2: mask = (1 << 3) - 1 = 8 - 1 = 7 (111 in binary)
 * 
 * Step 3: num ^ mask = 5 ^ 7
 *         101
 *       ^ 111
 *       -----
 *         010 = 2
 * 
 * Answer: 2 ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * XOR TRUTH TABLE (for reference)
 * ═══════════════════════════════════════════════════════════════
 * 
 *   A | B | A ^ B
 *  ---|---|------
 *   0 | 0 |   0
 *   0 | 1 |   1    ← 0 XOR 1 = 1 (flips 0 to 1)
 *   1 | 0 |   1
 *   1 | 1 |   0    ← 1 XOR 1 = 0 (flips 1 to 0)
 * 
 * XOR with 1 always flips the bit!
 */

