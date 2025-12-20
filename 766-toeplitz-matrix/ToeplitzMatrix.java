/*
 * LeetCode 766: Toeplitz Matrix
 * 
 * Problem:
 * Given an m x n matrix, return true if the matrix is Toeplitz.
 * A matrix is Toeplitz if every diagonal from top-left to bottom-right
 * has the same elements.
 * 
 * Approach: Compare Each Element with Top-Left Neighbor
 * 
 * Key Insight:
 * - In a Toeplitz matrix, each element equals its top-left diagonal neighbor
 * - matrix[i][j] == matrix[i-1][j-1] must be true for all valid i, j
 * - Only need to check from row 1 and column 1 (row 0 and col 0 have no neighbor)
 * 
 * Algorithm:
 * 1. Start from (1, 1) - first cell with a diagonal neighbor
 * 2. For each cell, compare with top-left neighbor
 * 3. If any mismatch, return false
 * 4. If all match, return true
 * 
 * Time Complexity: O(m × n) - visit each cell once
 * Space Complexity: O(1) - no extra space needed
 * 
 * Example 1: Toeplitz Matrix (returns true)
 * 
 *   matrix = [[1, 2, 3, 4],
 *             [5, 1, 2, 3],
 *             [9, 5, 1, 2]]
 * 
 *   Diagonals (top-left to bottom-right):
 *   [9]           - single element ✓
 *   [5, 5]        - all same ✓
 *   [1, 1, 1]     - all same ✓
 *   [2, 2, 2]     - all same ✓
 *   [3, 3]        - all same ✓
 *   [4]           - single element ✓
 * 
 *   Visual (same color = same diagonal):
 *   [1] [2] [3] [4]
 *   [5] [1] [2] [3]
 *   [9] [5] [1] [2]
 *    ↖   ↖   ↖   ↖  (each matches its top-left)
 * 
 * Example 2: NOT Toeplitz (returns false)
 * 
 *   matrix = [[1, 2],
 *             [2, 2]]
 * 
 *   Check (1,1): matrix[1][1]=2 vs matrix[0][0]=1
 *   2 ≠ 1 → return false!
 * 
 * Comparison Pattern:
 * 
 *   For a 3x4 matrix:
 *   
 *   [0,0] [0,1] [0,2] [0,3]
 *   [1,0] [1,1] [1,2] [1,3]
 *   [2,0] [2,1] [2,2] [2,3]
 * 
 *   Comparisons made:
 *   (1,1) vs (0,0) ✓
 *   (1,2) vs (0,1) ✓
 *   (1,3) vs (0,2) ✓
 *   (2,1) vs (1,0) ✓
 *   (2,2) vs (1,1) ✓
 *   (2,3) vs (1,2) ✓
 * 
 *   Each cell (except row 0 and col 0) is compared to its top-left neighbor.
 * 
 * Why Start from (1,1)?
 * 
 *   - Row 0: No row above, so no top-left neighbor
 *   - Col 0: No column to the left, so no top-left neighbor
 *   - Starting at (1,1) ensures matrix[i-1][j-1] is always valid
 * 
 * Visual (Arrow shows comparison):
 * 
 *   [a] [b] [c]
 *    ↘   ↘   ↘
 *   [d] [a?][b?]    a? must equal a, b? must equal b
 *    ↘   ↘   ↘
 *   [e] [d?][a?]    d? must equal d, a? must equal a
 */

class Solution {

    public boolean isToeplitzMatrix(int[][] matrix) {

        // Iterate from the second row and second column
        for (int i = 1; i < matrix.length; i++) {

            for (int j = 1; j < matrix[0].length; j++) {

                // Compare current element with its top-left diagonal neighbor
                if (matrix[i][j] != matrix[i - 1][j - 1]) {

                    return false; // Not a Toeplitz matrix

                }

            }

        }

        return true; // It is a Toeplitz matrix

    }

}

