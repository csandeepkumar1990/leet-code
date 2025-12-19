/*
 * LeetCode 2319: Check if Matrix Is X-Matrix
 * 
 * Problem:
 * A square matrix is an X-Matrix if:
 * 1. All elements on the diagonals are non-zero
 * 2. All other elements are zero
 * Return true if grid is an X-Matrix, otherwise false.
 * 
 * Approach: Check Each Cell's Diagonal Status
 * 
 * Key Insight:
 * - Primary diagonal: i == j (top-left to bottom-right)
 * - Secondary diagonal: i + j == n - 1 (top-right to bottom-left)
 * - A cell is on a diagonal if EITHER condition is true
 * 
 * Algorithm:
 * 1. For each cell (i, j):
 *    - Check if it's on a diagonal: (i == j) OR (i + j == n - 1)
 *    - If diagonal: must be non-zero (return false if zero)
 *    - If not diagonal: must be zero (return false if non-zero)
 * 2. If all checks pass, return true
 * 
 * Time Complexity: O(n²) - check every cell
 * Space Complexity: O(1) - only using variables
 * 
 * Diagonal Conditions Explained:
 * 
 *   For n = 4:
 *   
 *   Primary diagonal (i == j):
 *   (0,0), (1,1), (2,2), (3,3)
 *   
 *   Secondary diagonal (i + j == n-1 = 3):
 *   (0,3): 0+3=3 ✓
 *   (1,2): 1+2=3 ✓
 *   (2,1): 2+1=3 ✓
 *   (3,0): 3+0=3 ✓
 * 
 * Example 1: Valid X-Matrix
 * 
 *   grid = [[2, 0, 0, 1],
 *           [0, 3, 1, 0],
 *           [0, 5, 2, 0],
 *           [4, 0, 0, 2]]
 * 
 *   Visual:
 *   [2]  0   0  [1]     X = diagonal (non-zero) ✓
 *    0  [3][1]  0       0 = non-diagonal (zero) ✓
 *    0  [5][2]  0   
 *   [4]  0   0  [2]
 * 
 *   Result: true
 * 
 * Example 2: Invalid X-Matrix
 * 
 *   grid = [[5, 7, 0],
 *           [0, 3, 1],
 *           [0, 5, 0]]
 * 
 *   Visual:
 *   [5]  7  [0]     ← position (0,2) is diagonal but = 0! ✗
 *    0  [3]  1      ← position (1,2) is non-diagonal but = 1! ✗
 *   [0]  5  [0]     ← position (2,0) is diagonal but = 0! ✗
 * 
 *   Result: false
 * 
 * Visual Pattern (X shape):
 * 
 *   n=5:
 *   X . . . X
 *   . X . X .
 *   . . X . .      X = must be non-zero
 *   . X . X .      . = must be zero
 *   X . . . X
 * 
 *   Center cell (2,2) is on BOTH diagonals
 */

class Solution {

    public boolean checkXMatrix(int[][] grid) {

        int n = grid.length;

        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {

                boolean isDiagonal = (i == j) || (i + j == n - 1);

                if (isDiagonal) {

                    if (grid[i][j] == 0) return false;

                } else {

                    if (grid[i][j] != 0) return false;

                }

            }

        }

        return true;

    }

}

