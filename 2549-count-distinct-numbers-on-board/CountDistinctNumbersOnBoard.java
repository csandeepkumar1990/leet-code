/*
 * LeetCode 2549: Count Distinct Numbers on Board
 * 
 * Problem:
 * You have a board with number n on it. Every day, for every number x on the board,
 * you find all numbers i (1 <= i <= n) where x % i == 1, and add i to the board.
 * After 10^9 days, return the count of distinct numbers on the board.
 * 
 * Approach: Mathematical Pattern Recognition
 * 
 * Key Insight:
 * For any number x > 1 on the board:
 *   x % (x - 1) = 1  ← This is ALWAYS true!
 * 
 * Why? Because x = (x-1) * 1 + 1, so remainder is always 1.
 * 
 * Chain Reaction:
 * - Start with n on board
 * - n % (n-1) = 1 → add (n-1)
 * - (n-1) % (n-2) = 1 → add (n-2)
 * - (n-2) % (n-3) = 1 → add (n-3)
 * - ... continues until we reach 2
 * - 2 % 1 = 0 (not 1), so we stop at 2
 * 
 * Final Board Contains: {2, 3, 4, ..., n}
 * Count = n - 1 distinct numbers
 * 
 * Special Case:
 * - n = 1: Only 1 is on board (no valid i satisfies 1 % i == 1)
 * 
 * Time Complexity: O(1) - direct formula
 * Space Complexity: O(1) - no extra space
 * 
 * Example: n = 5
 * 
 * Day 0: {5}
 * Day 1: 5 % 4 = 1 → {5, 4}
 * Day 2: 4 % 3 = 1 → {5, 4, 3}
 * Day 3: 3 % 2 = 1 → {5, 4, 3, 2}
 * Day 4+: 2 % 1 = 0 (no new additions)
 * 
 * Final: {2, 3, 4, 5} → 4 distinct numbers = n - 1
 * 
 * Mathematical Proof:
 * For x > 1:  x = 1 * (x-1) + 1
 *             Therefore: x % (x-1) = 1 ✓
 * 
 * This means every number x automatically adds (x-1) to the board,
 * creating a cascade from n down to 2.
 */

class Solution {

    public int distinctIntegers(int n) {

        // Special case: n = 1
        // No number i exists where 1 % i == 1
        // (1 % 1 = 0, and we need i >= 1)
        // So only 1 remains on the board
        if (n == 1) {

            return 1;

        }

        // For n > 1:
        // The board will eventually contain all numbers from 2 to n
        // That's exactly (n - 1) distinct numbers
        // 
        // Numbers on board: {2, 3, 4, ..., n}
        // Count: n - 2 + 1 = n - 1
        return n - 1;

    }

}

