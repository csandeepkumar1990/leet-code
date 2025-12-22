/*
 * LeetCode 463: Island Perimeter
 * 
 * Problem:
 * You are given row x col grid representing a map where grid[i][j] = 1 represents
 * land and grid[i][j] = 0 represents water.
 * 
 * Grid cells are connected horizontally/vertically (not diagonally). The grid is
 * completely surrounded by water, and there is exactly one island (i.e., one or
 * more connected land cells).
 * 
 * The island doesn't have "lakes", meaning the water inside isn't connected to
 * the water around the island. One cell is a square with side length 1. The grid
 * is rectangular, width and height don't exceed 100.
 * 
 * Determine the perimeter of the island.
 * 
 * Approach: Count Edges and Subtract Shared Edges
 * 
 * Key Insight:
 * - Each land cell contributes 4 edges to the perimeter
 * - When two land cells are adjacent, they share an edge
 * - Each shared edge reduces perimeter by 2 (one edge from each cell)
 * - Check only upper and left neighbors to avoid double counting
 * 
 * Algorithm:
 * 1. For each land cell, add 4 to perimeter
 * 2. If upper neighbor is land, subtract 2 (shared vertical edge)
 * 3. If left neighbor is land, subtract 2 (shared horizontal edge)
 * 4. Return total perimeter
 * 
 * Time Complexity: O(rows * cols)
 *   - Visit each cell once
 * 
 * Space Complexity: O(1)
 *   - Only using constant extra space
 * 
 * Example 1:
 *   grid = [[0,1,0,0],
 *           [1,1,1,0],
 *           [0,1,0,0],
 *           [1,1,0,0]]
 *   
 *   Land cells: 7 cells
 *   Initial perimeter: 7 * 4 = 28
 *   
 *   Shared edges:
 *   - (0,1) with (1,1): vertical edge → -2
 *   - (1,0) with (1,1): horizontal edge → -2
 *   - (1,1) with (1,2): horizontal edge → -2
 *   - (1,1) with (2,1): vertical edge → -2
 *   - (2,1) with (3,1): vertical edge → -2
 *   - (3,0) with (3,1): horizontal edge → -2
 *   
 *   Total: 28 - 12 = 16
 * 
 * Example 2:
 *   grid = [[1]]
 *   
 *   Land cells: 1 cell
 *   Initial perimeter: 1 * 4 = 4
 *   No shared edges
 *   Total: 4
 * 
 * Example 3:
 *   grid = [[1,0]]
 *   
 *   Land cells: 1 cell
 *   Initial perimeter: 1 * 4 = 4
 *   No shared edges
 *   Total: 4
 * 
 * Why Check Only Upper and Left Neighbors?
 * 
 *   When processing cell (i, j):
 *   - Upper neighbor (i-1, j): already processed, safe to check
 *   - Left neighbor (i, j-1): already processed, safe to check
 *   - Lower neighbor (i+1, j): not yet processed, will be checked when we reach it
 *   - Right neighbor (i, j+1): not yet processed, will be checked when we reach it
 *   
 *   By checking only upper and left, we count each shared edge exactly once!
 * 
 * Why Subtract 2 for Each Shared Edge?
 * 
 *   When two land cells share an edge:
 *   - Each cell initially contributes 4 edges
 *   - The shared edge is counted in both cells' 4 edges
 *   - We need to remove 2 edges (one from each cell) to account for the shared edge
 *   - Therefore: subtract 2 for each shared edge
 * 
 * Visual Example (grid = [[1,1], [1,0]]):
 * 
 *   Grid:
 *   1 1
 *   1 0
 *   
 *   Processing:
 *   (0,0): land → +4, no neighbors → perimeter = 4
 *   (0,1): land → +4, left(0,0) is land → -2 → perimeter = 6
 *   (1,0): land → +4, upper(0,0) is land → -2 → perimeter = 8
 *   (1,1): water → skip
 *   
 *   Total: 8
 *   
 *   Verification:
 *   - Top edge of (0,0): 1
 *   - Right edge of (0,0): shared with (0,1) → counted once
 *   - Bottom edge of (0,0): shared with (1,0) → counted once
 *   - Left edge of (0,0): 1
 *   - Top edge of (0,1): 1
 *   - Right edge of (0,1): 1
 *   - Bottom edge of (0,1): 1
 *   - Top edge of (1,0): shared with (0,0) → counted once
 *   - Right edge of (1,0): 1
 *   - Bottom edge of (1,0): 1
 *   - Left edge of (1,0): 1
 *   Total: 8 ✓
 * 
 * Edge Cases:
 * 
 *   - Single cell island: grid = [[1]]
 *     Result: 4 (all 4 edges are perimeter)
 * 
 *   - Single row: grid = [[1,1,1]]
 *     Land cells: 3 → 12
 *     Shared edges: 2 → -4
 *     Result: 8
 * 
 *   - L-shaped island: grid = [[1,1], [1,0]]
 *     Land cells: 3 → 12
 *     Shared edges: 2 → -4
 *     Result: 8
 */

class Solution {

    public int islandPerimeter(int[][] grid) {

        int rows = grid.length;

        int cols = grid[0].length;

        int perimeter = 0;

        for (int i = 0; i < rows; i++) {

            for (int j = 0; j < cols; j++) {

                if (grid[i][j] == 1) {

                    perimeter += 4; // Each land cell adds 4 sides

                    // Check upper neighbor
                    // If upper cell is also land, subtract 2 for shared vertical edge
                    if (i > 0 && grid[i - 1][j] == 1) {

                        perimeter -= 2;

                    }

                    // Check left neighbor
                    // If left cell is also land, subtract 2 for shared horizontal edge
                    if (j > 0 && grid[i][j - 1] == 1) {

                        perimeter -= 2;

                    }

                }

            }

        }

        return perimeter;

    }

}

