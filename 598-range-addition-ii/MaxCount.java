/*
 * LeetCode 598: Range Addition II
 * 
 * Problem:
 * You are given an m x n matrix M initialized with all 0's and an array of
 * operations ops, where ops[i] = [ai, bi] means M[x][y] should be incremented
 * by one for all 0 <= x < ai and 0 <= y < bi.
 * 
 * Count and return the number of maximum integers in the matrix after performing
 * all the operations.
 * 
 * Approach: Find Intersection of All Operations
 * 
 * Key Insight:
 * - Each operation increments a submatrix from (0,0) to (ai-1, bi-1)
 * - The maximum value appears in cells that are incremented by ALL operations
 * - These cells form the intersection of all submatrices
 * - The intersection is determined by the minimum ai and minimum bi values
 * - Result = minRow * minCol (area of intersection)
 * 
 * Algorithm:
 * 1. Initialize minRow = m, minCol = n (no operations case)
 * 2. For each operation [ai, bi]:
 *    - Update minRow = min(minRow, ai)
 *    - Update minCol = min(minCol, bi)
 * 3. Return minRow * minCol
 * 
 * Time Complexity: O(k)
 *   - k = number of operations
 *   - Single pass through operations array
 * 
 * Space Complexity: O(1)
 *   - Only using constant extra space
 * 
 * Example 1:
 *   m = 3, n = 3, ops = [[2,2], [3,3]]
 *   
 *   Operation [2,2]: increments [0,0] to [1,1]
 *   [1, 1, 0]
 *   [1, 1, 0]
 *   [0, 0, 0]
 *   
 *   Operation [3,3]: increments [0,0] to [2,2]
 *   [2, 2, 1]
 *   [2, 2, 1]
 *   [1, 1, 1]
 *   
 *   Maximum value: 2
 *   Cells with max value: (0,0), (0,1), (1,0), (1,1) = 4 cells
 *   
 *   Using our approach:
 *   minRow = min(3, 2, 3) = 2
 *   minCol = min(3, 2, 3) = 2
 *   Result: 2 * 2 = 4 ✓
 * 
 * Example 2:
 *   m = 3, n = 3, ops = [[2,2], [3,3], [3,3], [3,3], [2,2], [3,3], [3,3], [3,3], [2,2], [3,3], [3,3], [3,3]]
 *   
 *   minRow = min(3, 2, 3, 3, 3, 2, 3, 3, 3, 2, 3, 3, 3) = 2
 *   minCol = min(3, 2, 3, 3, 3, 2, 3, 3, 3, 2, 3, 3, 3) = 2
 *   Result: 2 * 2 = 4
 * 
 * Example 3:
 *   m = 3, n = 3, ops = []
 *   
 *   No operations, all cells remain 0
 *   minRow = 3, minCol = 3
 *   Result: 3 * 3 = 9 (all cells have max value 0)
 * 
 * Why This Works?
 * 
 *   Each operation [ai, bi] increments cells in range:
 *   - Rows: 0 to ai-1 (inclusive)
 *   - Cols: 0 to bi-1 (inclusive)
 *   
 *   For a cell to have the maximum value, it must be:
 *   - Incremented by operation 1: row < a1, col < b1
 *   - Incremented by operation 2: row < a2, col < b2
 *   - Incremented by operation 3: row < a3, col < b3
 *   - ... and so on
 *   
 *   This means: row < min(a1, a2, a3, ...) AND col < min(b1, b2, b3, ...)
 *   
 *   Therefore: row < minRow AND col < minCol
 *   Number of such cells = minRow * minCol
 * 
 * Visual Example (m=3, n=3, ops=[[2,2], [3,3]]):
 * 
 *   Operation [2,2] affects:        Operation [3,3] affects:
 *   [X, X, 0]                     [X, X, X]
 *   [X, X, 0]                     [X, X, X]
 *   [0, 0, 0]                     [X, X, X]
 *   
 *   Intersection (both operations):
 *   [X, X, 0]  ← These cells have max value
 *   [X, X, 0]
 *   [0, 0, 0]
 *   
 *   Count: 2 * 2 = 4 cells
 * 
 * Edge Cases:
 * 
 *   - No operations: ops = []
 *     minRow = m, minCol = n
 *     Result: m * n (all cells have max value 0)
 * 
 *   - Single operation: ops = [[1,1]]
 *     minRow = 1, minCol = 1
 *     Result: 1 (only cell (0,0) has max value)
 * 
 *   - Operations with same dimensions: ops = [[2,2], [2,2], [2,2]]
 *     minRow = 2, minCol = 2
 *     Result: 4 (all operations affect same 2x2 submatrix)
 */

class Solution {

    public int maxCount(int m, int n, int[][] ops) {

        int minRow = m;

        int minCol = n;

        // Find minimum dimensions across all operations
        for (int[] op : ops) {

            minRow = Math.min(minRow, op[0]);

            minCol = Math.min(minCol, op[1]);

        }

        // Area of intersection = number of cells with maximum value
        return minRow * minCol;

    }

}

