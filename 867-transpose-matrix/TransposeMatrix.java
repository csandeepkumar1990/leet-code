/*
 * LeetCode 867: Transpose Matrix
 * 
 * Problem:
 * Given a 2D integer array matrix, return the transpose of matrix.
 * The transpose of a matrix is the matrix flipped over its main diagonal,
 * switching the matrix's row and column indices.
 * 
 * Approach: Direct Element Mapping
 * 
 * Key Insight:
 * - In a transpose, element at position (i, j) moves to position (j, i)
 * - If original matrix is m×n, transposed matrix is n×m
 * - Rows become columns and columns become rows
 * 
 * Algorithm:
 * 1. Get dimensions: m rows, n columns
 * 2. Create new matrix with swapped dimensions: n×m
 * 3. For each element at (i, j), place it at (j, i) in result
 * 
 * Time Complexity: O(m × n) - visit each element once
 * Space Complexity: O(m × n) - for the result matrix
 * 
 * Example 1: matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 
 *   Original (3×3):     Transpose (3×3):
 *   [1, 2, 3]           [1, 4, 7]
 *   [4, 5, 6]    →      [2, 5, 8]
 *   [7, 8, 9]           [3, 6, 9]
 * 
 *   (0,0)→(0,0): 1→1    (0,1)→(1,0): 2→2    (0,2)→(2,0): 3→3
 *   (1,0)→(0,1): 4→4    (1,1)→(1,1): 5→5    (1,2)→(2,1): 6→6
 *   (2,0)→(0,2): 7→7    (2,1)→(1,2): 8→8    (2,2)→(2,2): 9→9
 * 
 * Example 2: matrix = [[1,2,3],[4,5,6]] (non-square)
 * 
 *   Original (2×3):     Transpose (3×2):
 *   [1, 2, 3]           [1, 4]
 *   [4, 5, 6]    →      [2, 5]
 *                       [3, 6]
 * 
 * Visual Mapping:
 * 
 *   matrix[i][j] ──────→ result[j][i]
 *        ↓                    ↓
 *   row i, col j         row j, col i
 * 
 *   Original:           Result:
 *   ┌─────────────┐     ┌─────────┐
 *   │ a  b  c  d  │     │ a  e  i │
 *   │ e  f  g  h  │ →   │ b  f  j │
 *   │ i  j  k  l  │     │ c  g  k │
 *   └─────────────┘     │ d  h  l │
 *      (3×4)            └─────────┘
 *                          (4×3)
 */

class Solution {

    public int[][] transpose(int[][] matrix) {

        int m = matrix.length;

        int n = matrix[0].length;

        int[][] result = new int[n][m];

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                result[j][i] = matrix[i][j];

            }

        }

        return result;

    }

}

