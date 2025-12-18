/*
 * LeetCode 3402: Minimum Operations to Make Columns Strictly Increasing
 * 
 * Problem:
 * You are given a m x n grid. In one operation, you can select any cell and
 * increment its value by 1. Return the minimum number of operations needed
 * to make all columns strictly increasing (each element > element above it).
 * 
 * Approach: Greedy Column-wise Fix
 * 
 * Key Insight:
 * - Process grid row by row, from top to bottom
 * - For each cell, ensure it's strictly greater than the cell above
 * - If not, increment it to be exactly (above + 1)
 * - Greedy works because we only care about making columns increasing
 * 
 * Algorithm:
 * 1. Start from row 1 (row 0 has no constraint from above)
 * 2. For each cell at (i, j):
 *    - If grid[i][j] <= grid[i-1][j], it violates strictly increasing
 *    - Calculate diff = grid[i-1][j] - grid[i][j] + 1
 *    - Increment grid[i][j] by diff (making it exactly grid[i-1][j] + 1)
 *    - Add diff to total operations
 * 3. Return total sum of operations
 * 
 * Time Complexity: O(m × n) - visit each cell once
 * Space Complexity: O(1) - modify grid in place
 * 
 * Example: grid = [[3,2],[1,3],[3,4],[0,1]]
 * 
 *   Column 0:          Column 1:
 *   [3]                [2]
 *   [1] → need 1>3     [3] → 3>2 ✓
 *       → diff=3       
 *       → [4]          
 *   [3] → need 3>4     [4] → 4>3 ✓
 *       → diff=2
 *       → [5]
 *   [0] → need 0>5     [1] → need 1>4
 *       → diff=6           → diff=4
 *       → [6]              → [5]
 * 
 *   Original:    After fixing:
 *   [3, 2]       [3, 2]
 *   [1, 3]  →    [4, 3]  (diff: 3, 0)
 *   [3, 4]       [5, 4]  (diff: 2, 0)
 *   [0, 1]       [6, 5]  (diff: 6, 4)
 * 
 *   Total operations: 3 + 2 + 6 + 4 = 15
 * 
 * Why Greedy Works:
 * 
 *   We want MINIMUM operations, so we make each element the
 *   SMALLEST valid value: exactly (element above) + 1
 * 
 *   Making it larger than necessary would only increase
 *   the operations needed for elements below.
 * 
 * Visual (Single Column):
 * 
 *   Before:  Constraint:   After:    Operations:
 *     5        (base)        5          0
 *     3        > 5           6          3
 *     8        > 6           8          0
 *     2        > 8           9          7
 *                                      ──
 *                            Total:    10
 */

class Solution {

    public int minimumOperations(int[][] grid) {

        int sum = 0;

        for (int i = 1; i < grid.length; i++) {

            for (int j = 0; j < grid[0].length; j++) {

                if (grid[i][j] <= grid[i - 1][j]) {

                    int diff = grid[i - 1][j] - grid[i][j] + 1;

                    grid[i][j] += diff; // Apply the increment

                    sum += diff; // Count the operations

                }

            }

        }

        return sum;

    }

}

