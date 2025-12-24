/*
 * LeetCode 1260: Shift 2D Grid
 *
 * Problem:
 * Given a 2D grid of size m x n and an integer k. You need to shift the grid
 * k times.
 *
 * In one shift operation:
 * - Element at grid[i][j] moves to grid[i][j + 1]
 * - Element at grid[i][n - 1] moves to grid[i + 1][0]
 * - Element at grid[m - 1][n - 1] moves to grid[0][0]
 *
 * Return the 2D grid after applying k shift operations.
 *
 * Approach: Flatten, Shift, Reshape
 *
 * Key Insight:
 * - Treat the 2D grid as a 1D array (row-major order)
 * - Shifting k times in 2D is equivalent to rotating the 1D array k positions
 * - Flatten → Shift → Reshape
 *
 * Algorithm:
 * 1. Flatten the 2D grid into a 1D array (row-major order)
 * 2. Create shifted array: element at index i moves to (i + k) % total
 * 3. Reshape the shifted 1D array back into 2D grid
 *
 * Time Complexity: O(m * n), where m and n are grid dimensions.
 *   - Flattening: O(m * n)
 *   - Shifting: O(m * n)
 *   - Reshaping: O(m * n)
 * Space Complexity: O(m * n) for the flattened and shifted arrays.
 *
 * Example:
 *
 *   Input: grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1
 *
 *   Step 1: Flatten
 *     flat = [1, 2, 3, 4, 5, 6, 7, 8, 9]
 *
 *   Step 2: Shift (k = 1)
 *     For each index i, move to (i + 1) % 9:
 *       flat[0] = 1 → shifted[1] = 1
 *       flat[1] = 2 → shifted[2] = 2
 *       ...
 *       flat[8] = 9 → shifted[0] = 9
 *     shifted = [9, 1, 2, 3, 4, 5, 6, 7, 8]
 *
 *   Step 3: Reshape (m=3, n=3)
 *     Row 0: shifted[0..2] = [9, 1, 2]
 *     Row 1: shifted[3..5] = [3, 4, 5]
 *     Row 2: shifted[6..8] = [6, 7, 8]
 *
 *   Output: [[9,1,2],[3,4,5],[6,7,8]]
 *
 * Why (i + k) % total?
 * - Shifting k positions to the right means each element moves forward k spots
 * - The modulo operation handles wrapping around
 * - Element at position i should end up at position (i + k) % total
 *
 * Mapping Between 1D and 2D:
 * - 1D index → 2D: row = index / n, col = index % n
 * - 2D → 1D index: index = row * n + col
 *
 * Important Notes:
 * - The shift operation wraps around (last element goes to first)
 * - k can be larger than total elements (handled by modulo)
 * - Row-major order: elements are read row by row, left to right
 * - The solution creates new arrays (doesn't modify input in-place)
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int total = m * n;

        // Step 1: Flatten the grid into a 1D array
        int[] flat = new int[total];
        int index = 0;
        for (int[] row : grid) {
            for (int num : row) {
                flat[index++] = num;
            }
        }

        // Step 2: Create the shifted array
        int[] shifted = new int[total];
        for (int i = 0; i < total; i++) {
            shifted[(i + k) % total] = flat[i];
        }

        // Step 3: Convert back to 2D list
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<Integer> row = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                row.add(shifted[i * n + j]);
            }
            result.add(row);
        }

        return result;
    }
}

