/*
 * LeetCode 1582: Special Positions in a Binary Matrix
 * 
 * Problem:
 * Given an m x n binary matrix, return the number of "special" positions.
 * A position (i, j) is special if:
 * - mat[i][j] == 1
 * - All other elements in row i are 0
 * - All other elements in column j are 0
 * 
 * In other words: the 1 is the ONLY 1 in its row AND column.
 * 
 * Approach: Pre-count Rows and Columns
 * 
 * Key Insight:
 * - Instead of checking entire row/column for each 1
 * - Pre-compute count of 1s in each row and column
 * - A position is special if: value=1, rowCount=1, colCount=1
 * 
 * Algorithm:
 * 1. Count 1s in each row → rowCount[i]
 * 2. Count 1s in each column → colCount[j]
 * 3. For each cell with value 1:
 *    - If rowCount[i] == 1 AND colCount[j] == 1 → it's special!
 * 
 * Time Complexity: O(m × n) - two passes through matrix
 * Space Complexity: O(m + n) - for row and column count arrays
 * 
 * Example:
 * mat = [[1, 0, 0],
 *        [0, 0, 1],
 *        [1, 0, 0]]
 * 
 * Step 1: Count 1s
 *   rowCount = [1, 1, 1]  (each row has one 1)
 *   colCount = [2, 0, 1]  (col 0 has two 1s, col 2 has one 1)
 * 
 * Step 2: Check each 1
 *   (0,0): mat=1, rowCount[0]=1 ✓, colCount[0]=2 ✗ → NOT special
 *   (1,2): mat=1, rowCount[1]=1 ✓, colCount[2]=1 ✓ → SPECIAL!
 *   (2,0): mat=1, rowCount[2]=1 ✓, colCount[0]=2 ✗ → NOT special
 * 
 * Answer: 1
 * 
 * Visual:
 *        col 0  col 1  col 2
 *         [2]    [0]    [1]  ← colCount
 *          ↓      ↓      ↓
 *   [1] → [1]    [0]    [0]   row 0: has 1, but col 0 has 2 ones
 *   [1] → [0]    [0]    [1]   row 1: has 1, col 2 has 1 → SPECIAL ★
 *   [1] → [1]    [0]    [0]   row 2: has 1, but col 0 has 2 ones
 *          ↑             ↑
 *       shared!       unique!
 * 
 * Why the 1 at (1,2) is special:
 * - It's the only 1 in row 1 ✓
 * - It's the only 1 in column 2 ✓
 * - Like being a "lonely" 1 with no neighbors in its row or column
 */

class Solution {

    public int numSpecial(int[][] mat) {

        int m = mat.length;    // number of rows

        int n = mat[0].length; // number of columns

        // Count of 1s in each row and column
        int[] rowCount = new int[m];

        int[] colCount = new int[n];

        // Step 1: Count 1s in each row and column
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                if (mat[i][j] == 1) {

                    rowCount[i]++;  // increment row count

                    colCount[j]++;  // increment column count

                }

            }

        }

        int specialCount = 0;

        // Step 2: Check each cell for special condition
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Special if: value is 1, only 1 in row, only 1 in column
                if (mat[i][j] == 1 && rowCount[i] == 1 && colCount[j] == 1) {

                    specialCount++;

                }

            }

        }

        return specialCount;

    }

}

