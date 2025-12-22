/*
 * LeetCode 1252: Cells with Odd Values in a Matrix
 * 
 * Problem:
 * There is an m x n matrix that is initialized to all 0's. There is also a 2D
 * array indices where indices[i] = [ri, ci] represents a 0-indexed location to
 * perform some increment operations on the matrix.
 * 
 * For each location indices[i], do both of the following:
 * 1. Increment all the cells on row ri
 * 2. Increment all the cells on column ci
 * 
 * Return the number of cells with odd values in the matrix after applying all
 * increment operations.
 * 
 * Approach: Track Row and Column Increments
 * 
 * Key Insight:
 * - Instead of simulating all increments, track how many times each row/column is incremented
 * - Value at cell (i, j) = rows[i] + cols[j]
 * - Cell is odd if (rows[i] + cols[j]) % 2 != 0
 * - This avoids O(m * n * k) simulation, reducing to O(m * n + k)
 * 
 * Algorithm:
 * 1. Create arrays to count increments for each row and column
 * 2. Process each index pair, incrementing corresponding row and column counters
 * 3. For each cell (i, j), check if rows[i] + cols[j] is odd
 * 4. Count and return total odd cells
 * 
 * Time Complexity: O(m * n + k)
 *   - k = length of indices array
 *   - O(k) to process indices
 *   - O(m * n) to check all cells
 * 
 * Space Complexity: O(m + n)
 *   - Arrays to track row and column increments
 * 
 * Example 1:
 *   m = 2, n = 3, indices = [[0,1], [1,1]]
 *   
 *   Step 1: Initialize matrix (all zeros)
 *   [0, 0, 0]
 *   [0, 0, 0]
 *   
 *   Step 2: Process indices
 *   Index [0,1]: increment row 0, column 1
 *   [1, 1, 1]  (row 0 incremented)
 *   [0, 1, 0]  (column 1 incremented)
 *   Result: [1, 2, 1]
 *           [0, 1, 0]
 *   
 *   Index [1,1]: increment row 1, column 1
 *   [1, 3, 1]  (column 1 incremented again)
 *   [1, 2, 1]  (row 1 incremented)
 *   Result: [1, 3, 1]
 *           [1, 2, 1]
 *   
 *   Odd values: (0,0)=1, (0,1)=3, (0,2)=1, (1,0)=1, (1,2)=1
 *   Result: 6
 * 
 *   Using our approach:
 *   rows = [1, 1]  (row 0 incremented once, row 1 incremented once)
 *   cols = [0, 2, 0]  (column 1 incremented twice)
 *   
 *   Check each cell:
 *   (0,0): 1 + 0 = 1 (odd) ✓
 *   (0,1): 1 + 2 = 3 (odd) ✓
 *   (0,2): 1 + 0 = 1 (odd) ✓
 *   (1,0): 1 + 0 = 1 (odd) ✓
 *   (1,1): 1 + 2 = 3 (odd) ✓
 *   (1,2): 1 + 0 = 1 (odd) ✓
 *   
 *   Result: 6
 * 
 * Example 2:
 *   m = 2, n = 2, indices = [[1,1], [0,0]]
 *   
 *   rows = [1, 1]
 *   cols = [1, 1]
 *   
 *   Check each cell:
 *   (0,0): 1 + 1 = 2 (even)
 *   (0,1): 1 + 1 = 2 (even)
 *   (1,0): 1 + 1 = 2 (even)
 *   (1,1): 1 + 1 = 2 (even)
 *   
 *   Result: 0
 * 
 * Why This Approach Works?
 * 
 *   Each increment operation affects:
 *   - All m cells in a row (row increment)
 *   - All n cells in a column (column increment)
 *   
 *   For cell (i, j):
 *   - It's incremented rows[i] times (from row increments)
 *   - It's incremented cols[j] times (from column increments)
 *   - Total value = rows[i] + cols[j]
 *   
 *   We only care if this sum is odd, not the exact value!
 * 
 * Mathematical Insight:
 * 
 *   Sum is odd if and only if:
 *   - One is odd and the other is even
 *   - Not both odd (odd + odd = even)
 *   - Not both even (even + even = even)
 * 
 * Optimization Note:
 * 
 *   We can optimize further by counting:
 *   - oddRows = number of rows with odd increment count
 *   - oddCols = number of columns with odd increment count
 *   - evenRows = m - oddRows
 *   - evenCols = n - oddCols
 *   
 *   Odd cells = oddRows * evenCols + evenRows * oddCols
 *   
 *   This reduces time to O(m + n + k)!
 * 
 * Visual Example (m=2, n=3, indices=[[0,1], [1,1]]):
 * 
 *   After processing indices:
 *   rows = [1, 1]
 *   cols = [0, 2, 0]
 *   
 *   Matrix calculation:
 *         col0  col1  col2
 *   row0: 1+0   1+2   1+0  → [1, 3, 1]
 *   row1: 1+0   1+2   1+0  → [1, 3, 1]
 *   
 *   Odd cells: all 6 cells (all have odd values)
 */

class Solution {

    public int oddCells(int m, int n, int[][] indices) {

        int[] rows = new int[m];

        int[] cols = new int[n];

        // Count increments for each row and column
        for (int[] index : indices) {

            rows[index[0]]++;

            cols[index[1]]++;

        }

        int oddCount = 0;

        // Calculate odd-valued cells
        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // Cell value = rows[i] + cols[j]
                // Check if sum is odd
                if ((rows[i] + cols[j]) % 2 != 0) {

                    oddCount++;

                }

            }

        }

        return oddCount;

    }

}

