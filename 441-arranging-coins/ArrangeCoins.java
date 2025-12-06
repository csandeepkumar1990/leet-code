/**
 * LeetCode 441: Arranging Coins
 * 
 * Problem: You have n coins and want to build a staircase. The staircase
 *          consists of k rows where the i-th row has exactly i coins.
 *          The last row may be incomplete.
 *          Return the number of COMPLETE rows you can build.
 * 
 * Key Insight: Row 1 needs 1 coin, row 2 needs 2 coins, row 3 needs 3 coins...
 *              Keep subtracting row costs until we run out of coins.
 *              Count how many complete rows we formed.
 * 
 * Examples:
 *   Input: n = 5
 *   Output: 2
 *   Explanation:
 *     Row 1: *       (1 coin,  4 remaining)
 *     Row 2: * *     (2 coins, 2 remaining)
 *     Row 3: * *     (incomplete - need 3, have 2)
 *     Complete rows: 2
 * 
 *   Input: n = 8
 *   Output: 3
 *   Explanation:
 *     Row 1: *         (1 coin,  7 remaining)
 *     Row 2: * *       (2 coins, 5 remaining)
 *     Row 3: * * *     (3 coins, 2 remaining)
 *     Row 4: * *       (incomplete - need 4, have 2)
 *     Complete rows: 3
 * 
 *   Input: n = 1
 *   Output: 1
 * 
 * Constraints:
 *   - 1 <= n <= 2^31 - 1
 */

class Solution {

    /**
     * Returns the number of complete staircase rows that can be built.
     * 
     * @param n - The total number of coins available
     * @return The number of complete rows
     * 
     * Time Complexity: O(√n) - loop runs approximately √(2n) times
     *                  (sum of 1+2+...+k = k(k+1)/2 ≈ n, so k ≈ √(2n))
     * Space Complexity: O(1) - only using a few variables
     */
    public int arrangeCoins(int n) {
        /*
         * count: tracks how many complete rows we've built
         * 
         * We iterate through row numbers (1, 2, 3, ...)
         * For each row i, we need i coins to complete it.
         */
        int count = 0;

        /*
         * Loop through each potential row
         * i represents the current row number (and coins needed for that row)
         */
        for (int i = 1; i <= n; i++) {
            /*
             * Check if we have enough coins for row i
             * 
             * Since we're modifying n inside the loop, we check if i <= n
             * where n is the REMAINING coins.
             */
            if (i <= n) {
                // We can complete this row!
                count++;    // Increment complete row count
                n -= i;     // Subtract coins used for this row
            } else {
                // Not enough coins for this row - stop here
                return count;
            }
        }

        /*
         * If we exit the loop normally, it means we used all coins
         * exactly (n became 0 or negative in the last iteration)
         */
        return count;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.arrangeCoins(5));  // Output: 2
 * System.out.println(sol.arrangeCoins(8));  // Output: 3
 * System.out.println(sol.arrangeCoins(1));  // Output: 1
 * 
 * ═══════════════════════════════════════════════════════════════
 * VISUAL STAIRCASE for n = 8
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Row 1: *           uses 1 coin,  remaining = 7
 *   Row 2: * *         uses 2 coins, remaining = 5
 *   Row 3: * * *       uses 3 coins, remaining = 2
 *   Row 4: * *         INCOMPLETE (need 4, have 2)
 *          ↑
 *     Complete rows = 3
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: n = 5
 * ═══════════════════════════════════════════════════════════════
 * 
 * Initial: n = 5, count = 0
 * 
 * i=1: Is 1 <= 5? YES
 *      count = 1, n = 5 - 1 = 4
 * 
 * i=2: Is 2 <= 4? YES
 *      count = 2, n = 4 - 2 = 2
 * 
 * i=3: Is 3 <= 2? NO
 *      return count = 2 ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY O(√n) TIME COMPLEXITY?
 * ═══════════════════════════════════════════════════════════════
 * 
 * Total coins for k complete rows = 1 + 2 + 3 + ... + k = k(k+1)/2
 * 
 * If k(k+1)/2 = n, then k ≈ √(2n)
 * 
 * So we iterate approximately √(2n) times → O(√n)
 * 
 * Example: n = 8
 *   k(k+1)/2 = 8 → k ≈ 3.7 → 3 complete rows
 *   We loop only 4 times (i = 1, 2, 3, 4)
 */

