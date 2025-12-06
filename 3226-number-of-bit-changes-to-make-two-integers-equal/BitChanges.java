/**
 * LeetCode 3226: Number of Bit Changes to Make Two Integers Equal
 * 
 * Problem: You are given two positive integers n and k. You can choose any bit
 *          in n that is equal to 1 and change it to 0. Return the number of
 *          changes needed to make n equal to k. If it's impossible, return -1.
 * 
 * Key Insight: We can only turn 1s into 0s in n. So:
 *              - If k has a 1 where n has a 0, it's IMPOSSIBLE (can't turn 0→1)
 *              - Otherwise, count how many 1s in n need to become 0s
 * 
 * Examples:
 *   Input: n = 13, k = 4
 *   Output: 2
 *   Explanation: n = 1101, k = 0100
 *                Change bits at positions 0 and 3 (turn 1→0)
 *                Result: 0100 = 4 ✓
 * 
 *   Input: n = 21, k = 21
 *   Output: 0
 *   Explanation: n and k are already equal, no changes needed.
 * 
 *   Input: n = 14, k = 13
 *   Output: -1
 *   Explanation: n = 1110, k = 1101
 *                k has a 1 at position 0 where n has 0. Impossible!
 * 
 * Constraints:
 *   - 1 <= n, k <= 10^6
 */

class Solution {

    /**
     * Returns minimum bit changes to transform n into k (only 1→0 allowed).
     * 
     * @param n - The starting integer
     * @param k - The target integer
     * @return Number of changes needed, or -1 if impossible
     * 
     * Time Complexity: O(1) - bitwise operations are constant time
     * Space Complexity: O(1) - only using a few variables
     */
    public int minChanges(int n, int k) {
        /*
         * STEP 1: Check if transformation is possible
         * 
         * ~n gives us all bits that are 0 in n (inverted)
         * k & ~n gives us bits that are 1 in k BUT 0 in n
         * 
         * If this is non-zero, k requires some 1s where n has 0s
         * Since we can only change 1→0 (not 0→1), it's impossible!
         * 
         * Example: n=14 (1110), k=13 (1101)
         *   ~n = ...0001
         *   k & ~n = 1101 & 0001 = 0001 ≠ 0 → IMPOSSIBLE
         */
        if ((k & ~n) != 0) {
            return -1;
        }

        /*
         * STEP 2: Count how many 1s in n need to be turned off
         * 
         * n & ~k gives us bits that are 1 in n BUT 0 in k
         * These are exactly the bits we need to turn off (1→0)
         * 
         * Example: n=13 (1101), k=4 (0100)
         *   ~k = ...1011
         *   n & ~k = 1101 & 1011 = 1001 (two 1s)
         *   We need to turn off 2 bits → return 2
         */
        int toTurnOff = n & ~k;

        // Integer.bitCount() counts the number of 1-bits
        return Integer.bitCount(toTurnOff);
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.minChanges(13, 4));  // Output: 2
 * System.out.println(sol.minChanges(21, 21)); // Output: 0
 * System.out.println(sol.minChanges(14, 13)); // Output: -1
 * 
 * ═══════════════════════════════════════════════════════════════
 * BIT MANIPULATION CHEAT SHEET
 * ═══════════════════════════════════════════════════════════════
 * 
 * ~n        : Inverts all bits of n (0↔1)
 * k & ~n    : Bits that are 1 in k AND 0 in n (need 0→1, impossible!)
 * n & ~k    : Bits that are 1 in n AND 0 in k (need 1→0, count these)
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: n = 13, k = 4
 * ═══════════════════════════════════════════════════════════════
 * 
 * n = 13 = 1101 (binary)
 * k = 4  = 0100 (binary)
 * 
 * Step 1: Can we do it?
 *   ~n = 0010
 *   k & ~n = 0100 & 0010 = 0000 = 0 ✓ (possible!)
 * 
 * Step 2: How many changes?
 *   ~k = 1011
 *   n & ~k = 1101 & 1011 = 1001
 *   bitCount(1001) = 2
 * 
 * Answer: 2 changes needed ✓
 */

