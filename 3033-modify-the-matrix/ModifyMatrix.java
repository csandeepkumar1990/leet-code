/**
 * LeetCode 3033: Modify the Matrix
 * 
 * Replace each -1 with the maximum value in its column.
 * Process column by column: find max, then replace -1s.
 */

class Solution {

    public int[][] modifiedMatrix(int[][] matrix) {
        // Process each column
        for (int i = 0; i < matrix[0].length; i++) {
            // Find max value in current column
            int maxInCol = 0;
            for (int j = 0; j < matrix.length; j++) {
                maxInCol = Math.max(maxInCol, matrix[j][i]);
            }

            // Replace all -1s with column max
            for (int j = 0; j < matrix.length; j++) {
                if (matrix[j][i] == -1) {
                    matrix[j][i] = maxInCol;
                }
            }
        }
        return matrix;
    }
}

