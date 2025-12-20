/*
 * LeetCode 566: Reshape the Matrix
 * 
 * Problem:
 * Given an m x n matrix and two integers r and c, reshape the matrix to have
 * r rows and c columns. If reshape is not possible (different total elements),
 * return the original matrix.
 * 
 * Approach: Linear Indexing with Division and Modulo
 * 
 * Key Insight:
 * - Treat matrix as a 1D array conceptually
 * - Use a single counter k to track position
 * - Convert k to 2D coordinates: row = k/c, col = k%c
 * 
 * Algorithm:
 * 1. Check if m×n == r×c (reshape possible)
 * 2. Create new r×c matrix
 * 3. Iterate through original matrix, filling new matrix using k
 * 4. k/c gives row, k%c gives column in new matrix
 * 
 * Time Complexity: O(m × n) - visit each element once
 * Space Complexity: O(r × c) - for the new matrix
 * 
 * Example: nums = [[1,2],[3,4]], r=1, c=4
 * 
 *   Original (2×2):     Reshaped (1×4):
 *   [1, 2]              [1, 2, 3, 4]
 *   [3, 4]      →
 * 
 *   Process:
 *   k=0: num=1 → ans[0/4][0%4] = ans[0][0] = 1
 *   k=1: num=2 → ans[1/4][1%4] = ans[0][1] = 2
 *   k=2: num=3 → ans[2/4][2%4] = ans[0][2] = 3
 *   k=3: num=4 → ans[3/4][3%4] = ans[0][3] = 4
 * 
 * Example 2: nums = [[1,2],[3,4]], r=4, c=1
 * 
 *   Original (2×2):     Reshaped (4×1):
 *   [1, 2]              [1]
 *   [3, 4]      →       [2]
 *                       [3]
 *                       [4]
 * 
 *   Process:
 *   k=0: num=1 → ans[0/1][0%1] = ans[0][0] = 1
 *   k=1: num=2 → ans[1/1][1%1] = ans[1][0] = 2
 *   k=2: num=3 → ans[2/1][2%1] = ans[2][0] = 3
 *   k=3: num=4 → ans[3/1][3%1] = ans[3][0] = 4
 * 
 * Invalid Reshape: nums = [[1,2],[3,4]], r=3, c=2
 * 
 *   Original: 2×2 = 4 elements
 *   Target: 3×2 = 6 elements
 *   4 ≠ 6 → Return original matrix!
 * 
 * The Magic Formula: k/c and k%c
 * 
 *   For a matrix with c columns:
 *   
 *   k:   0  1  2  3  4  5  6  7  8  ...
 *   row: 0  0  0  1  1  1  2  2  2  ...  (k / 3, if c=3)
 *   col: 0  1  2  0  1  2  0  1  2  ...  (k % 3, if c=3)
 * 
 *   This maps linear index k to 2D coordinates!
 * 
 *   Example with c=4:
 *   k=0 → (0,0)    k=4 → (1,0)
 *   k=1 → (0,1)    k=5 → (1,1)
 *   k=2 → (0,2)    k=6 → (1,2)
 *   k=3 → (0,3)    k=7 → (1,3)
 * 
 * Visual (Reading Order):
 * 
 *   Original 2×3:          Linear order:       New 3×2:
 *   [1, 2, 3]              1→2→3→4→5→6         [1, 2]
 *   [4, 5, 6]                                  [3, 4]
 *                                              [5, 6]
 *   
 *   Elements flow in row-major order (left-to-right, top-to-bottom)
 */

class Solution {

    public int[][] matrixReshape(int[][] nums, int r, int c) {

        int m = nums.length, n = nums[0].length;

        // If total elements don't match, return original matrix
        if (m * n != r * c) {

            return nums;

        }

        int[][] ans = new int[r][c];

        int k = 0;

        for (int[] row : nums) {

            for (int num : row) {

                ans[k / c][k % c] = num;

                k++;

            }

        }

        return ans;

    }

}

