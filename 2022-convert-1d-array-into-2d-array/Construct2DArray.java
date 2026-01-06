/*
 * LeetCode 2022: Convert 1D Array Into 2D Array
 * 
 * Problem:
 * You are given a 1D (one-dimensional) integer array original, and two integers, m and n.
 * You are tasked with converting original into a 2D array with m rows and n columns.
 * 
 * The conversion should be done in row-major order:
 * - original[0..n-1] goes into the first row
 * - original[n..2n-1] goes into the second row
 * - And so on
 * 
 * Return the constructed 2D array. If it is impossible to construct a 2D array with
 * m rows and n columns using all elements from original, return an empty 2D array.
 * 
 * Approach: Row-Major Order Conversion
 * 
 * Key Insight:
 * - Check if original.length == m * n (total elements must match)
 * - Fill 2D array row by row, column by column
 * - Use a single index to traverse the original array
 * - Map 1D index to 2D coordinates: row = i, col = j, index = i * n + j
 * 
 * Algorithm:
 * 1. Validate: if original.length != m * n, return empty 2D array
 * 2. Create result 2D array of size m x n
 * 3. Fill row by row:
 *    - For each row i from 0 to m-1:
 *      - For each column j from 0 to n-1:
 *        - result[i][j] = original[index++]
 * 4. Return result
 * 
 * Time Complexity: O(m * n)
 *   - Must fill all m * n positions in the 2D array
 *   - Each assignment is O(1)
 *   - Total: O(m * n)
 * 
 * Space Complexity: O(m * n)
 *   - Result 2D array of size m * n
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: original = [1, 2, 3, 4], m = 2, n = 2
 * 
 *   Step 1: Validate
 *     original.length = 4
 *     m * n = 2 * 2 = 4
 *     4 == 4 ✓ (valid)
 * 
 *   Step 2: Create 2D array
 *     result = new int[2][2]
 * 
 *   Step 3: Fill row by row
 *     Row 0:
 *       result[0][0] = original[0] = 1
 *       result[0][1] = original[1] = 2
 *     Row 1:
 *       result[1][0] = original[2] = 3
 *       result[1][1] = original[3] = 4
 * 
 *   Result:
 *     [[1, 2],
 *      [3, 4]]
 * 
 * Another Example:
 * 
 *   Input: original = [1, 2, 3], m = 1, n = 3
 * 
 *   Step 1: Validate
 *     original.length = 3
 *     m * n = 1 * 3 = 3
 *     3 == 3 ✓ (valid)
 * 
 *   Step 2: Fill
 *     Row 0:
 *       result[0][0] = 1
 *       result[0][1] = 2
 *       result[0][2] = 3
 * 
 *   Result: [[1, 2, 3]]
 * 
 * Visual Representation:
 * 
 *   original = [1, 2, 3, 4, 5, 6], m = 2, n = 3
 * 
 *   Conversion (row-major order):
 *     Index 0: 1 → result[0][0]
 *     Index 1: 2 → result[0][1]
 *     Index 2: 3 → result[0][2]
 *     Index 3: 4 → result[1][0]
 *     Index 4: 5 → result[1][1]
 *     Index 5: 6 → result[1][2]
 * 
 *   Result:
 *     [[1, 2, 3],
 *      [4, 5, 6]]
 * 
 *   Mapping formula:
 *     For 1D index k → 2D position (i, j):
 *       i = k / n  (row)
 *       j = k % n  (column)
 * 
 * Edge Cases:
 * 
 * 1. Invalid dimensions (original.length != m * n):
 *    original = [1, 2, 3], m = 2, n = 2
 *    original.length = 3, m * n = 4
 *    3 != 4 → return new int[0][0]
 * 
 * 2. Single row:
 *    original = [1, 2, 3], m = 1, n = 3
 *    Result: [[1, 2, 3]]
 * 
 * 3. Single column:
 *    original = [1, 2, 3], m = 3, n = 1
 *    Result: [[1],
 *             [2],
 *             [3]]
 * 
 * 4. Single element:
 *    original = [5], m = 1, n = 1
 *    Result: [[5]]
 * 
 * 5. Empty original array:
 *    original = [], m = 0, n = 0
 *    original.length = 0, m * n = 0
 *    0 == 0 ✓
 *    Result: new int[0][0] (empty 2D array)
 * 
 * 6. Large array:
 *    original = [1, 2, ..., 100], m = 10, n = 10
 *    Result: 10x10 matrix filled row by row
 * 
 * Row-Major Order:
 * 
 *   Row-major order means elements are stored row by row:
 *   - First row: indices 0 to n-1
 *   - Second row: indices n to 2n-1
 *   - Third row: indices 2n to 3n-1
 *   - And so on
 * 
 *   This is the standard way arrays are stored in memory in most programming languages.
 * 
 * Index Mapping:
 * 
 *   Converting 1D index to 2D coordinates:
 *     row = index / n
 *     col = index % n
 * 
 *   Converting 2D coordinates to 1D index:
 *     index = row * n + col
 * 
 *   Example: n = 3
 *     index 0 → (0, 0): 0 / 3 = 0, 0 % 3 = 0
 *     index 1 → (0, 1): 1 / 3 = 0, 1 % 3 = 1
 *     index 3 → (1, 0): 3 / 3 = 1, 3 % 3 = 0
 *     index 5 → (1, 2): 5 / 3 = 1, 5 % 3 = 2
 * 
 * Why Check original.length != m * n?
 * 
 *   - If original.length < m * n: not enough elements to fill the 2D array
 *   - If original.length > m * n: too many elements, some would be left out
 *   - Only when they're equal can we perfectly convert 1D to 2D
 * 
 * Alternative Approach (Using Index Calculation):
 * 
 *   Instead of using a separate index variable, we could calculate:
 *   ```java
 *   for (int i = 0; i < m; i++) {
 *       for (int j = 0; j < n; j++) {
 *           result[i][j] = original[i * n + j];
 *       }
 *   }
 *   ```
 *   This is equivalent but more explicit about the index mapping.
 */

class Solution {
    /**
     * Converts a 1D array into a 2D array with m rows and n columns.
     * 
     * @param original The 1D array to convert
     * @param m The number of rows in the 2D array
     * @param n The number of columns in the 2D array
     * @return The 2D array if conversion is possible, empty 2D array otherwise
     * 
     * Time Complexity: O(m * n)
     * Space Complexity: O(m * n)
     */
    public int[][] construct2DArray(int[] original, int m, int n) {
        // Check if total elements match
        // If original.length != m * n, we cannot construct a valid 2D array
        if (original.length != m * n) {
            return new int[0][0];
        }

        // Create the result 2D array
        int[][] result = new int[m][n];
        int index = 0; // Index to traverse the original array

        // Fill the 2D array row by row
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Assign element from original array to current position
                // Then increment index to move to next element
                result[i][j] = original[index++];
            }
        }

        return result;
    }
}

