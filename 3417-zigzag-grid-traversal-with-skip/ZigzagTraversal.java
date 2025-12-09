/**
 * LeetCode 3417: Zigzag Grid Traversal With Skip
 * 
 * Traverse grid in zigzag (left-right, then right-left alternating rows).
 * Only add every other element (skip odd positions in traversal).
 */

import java.util.*;

class Solution {

    public List<Integer> zigzagTraversal(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        List<Integer> result = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < rows; i++) {
            if (i % 2 == 0) {
                // Even row: traverse left to right
                for (int j = 0; j < cols; j++) {
                    if (count % 2 == 0) result.add(grid[i][j]);  // Skip odd positions
                    count++;
                }
            } else {
                // Odd row: traverse right to left
                for (int j = cols - 1; j >= 0; j--) {
                    if (count % 2 == 0) result.add(grid[i][j]);  // Skip odd positions
                    count++;
                }
            }
        }
        return result;
    }
}

