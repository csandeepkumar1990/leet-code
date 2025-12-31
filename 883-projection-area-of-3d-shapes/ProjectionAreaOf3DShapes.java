/*
 * LeetCode 883: Projection Area of 3D Shapes
 * 
 * Problem:
 * You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned
 * with the x, y, and z axes.
 * 
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of cell (i, j).
 * 
 * We view the "projection" of these cubes onto the xy, yz, and zx planes.
 * A projection is like a shadow, that maps 3D shape to a 2D plane.
 * 
 * We are viewing the "shadow" when looking at the cubes from the top, the front, and the side.
 * 
 * Return the total projection area.
 * 
 * Approach: Calculate Three Projections Separately
 * 
 * Key Insight:
 * - Top view (xy-plane): Count all non-zero cells (each cube contributes 1 unit)
 * - Front view (yz-plane): Sum of maximum height in each row
 * - Side view (zx-plane): Sum of maximum height in each column
 * - Total = top + front + side
 * 
 * Algorithm:
 * 1. Top view: Count cells where grid[i][j] > 0
 * 2. Front view: For each row i, find max(grid[i][j]) and sum them
 * 3. Side view: For each column j, find max(grid[i][j]) and sum them
 * 4. Return sum of all three projections
 * 
 * Time Complexity: O(n²), where n is the grid size.
 *   We iterate through all n × n cells once.
 * 
 * Space Complexity: O(1), only using a constant amount of extra space.
 * 
 * Example:
 * 
 *   Input: grid = [[1,2],[3,4]]
 * 
 *   Grid representation (3D):
 *   Position (0,0): height = 1
 *   Position (0,1): height = 2
 *   Position (1,0): height = 3
 *   Position (1,1): height = 4
 * 
 *   Top view (xy-plane):
 *   - Count non-zero cells: 4 cells (all are non-zero)
 *   - topViewArea = 4
 * 
 *   Front view (yz-plane - looking from front):
 *   - Row 0: max(1, 2) = 2
 *   - Row 1: max(3, 4) = 4
 *   - frontViewArea = 2 + 4 = 6
 * 
 *   Side view (zx-plane - looking from side):
 *   - Column 0: max(1, 3) = 3
 *   - Column 1: max(2, 4) = 4
 *   - sideViewArea = 3 + 4 = 7
 * 
 *   Total = 4 + 6 + 7 = 17
 * 
 * Visual Representation:
 * 
 *   Top View (xy-plane):
 *   +---+---+
 *   | 1 | 2 |
 *   +---+---+
 *   | 3 | 4 |
 *   +---+---+
 *   Area = 4 (all cells visible)
 * 
 *   Front View (yz-plane):
 *   Looking from front, we see the maximum height in each row:
 *   Row 0: max(1, 2) = 2
 *   Row 1: max(3, 4) = 4
 *   Area = 2 + 4 = 6
 * 
 *   Side View (zx-plane):
 *   Looking from side, we see the maximum height in each column:
 *   Column 0: max(1, 3) = 3
 *   Column 1: max(2, 4) = 4
 *   Area = 3 + 4 = 7
 * 
 * Another Example:
 * 
 *   Input: grid = [[2]]
 * 
 *   Top view: 1 (one non-zero cell)
 *   Front view: 2 (max in row 0 = 2)
 *   Side view: 2 (max in column 0 = 2)
 *   Total = 1 + 2 + 2 = 5
 * 
 * Why Top View = Count Non-Zero Cells?
 * 
 *   - Top view shows the "footprint" of the cubes
 *   - Each cell with height > 0 contributes exactly 1 unit area
 *   - Multiple cubes stacked vertically still show as 1 unit from top
 *   - Example: cell with height 5 shows as 1 unit from top
 * 
 * Why Front View = Sum of Max in Each Row?
 * 
 *   - Front view looks along the y-axis (from front)
 *   - For each row, we see the tallest tower in that row
 *   - All shorter towers are hidden behind the tallest one
 *   - Example: row [1, 5, 3] shows as height 5 from front
 * 
 * Why Side View = Sum of Max in Each Column?
 * 
 *   - Side view looks along the x-axis (from side)
 *   - For each column, we see the tallest tower in that column
 *   - All shorter towers are hidden behind the tallest one
 *   - Example: column [2, 4, 1] shows as height 4 from side
 * 
 * Implementation Details:
 * 
 *   - Use nested loops to iterate through grid
 *   - For each row i, track maxHeightInRow
 *   - For each column j, track maxHeightInColumn using grid[j][i]
 *   - Note: grid[j][i] accesses column i, row j (transposed access)
 * 
 * Why grid[j][i] for Column Maximum?
 * 
 *   - To get column i, we need to access all rows j
 *   - grid[j][i] gives us element at row j, column i
 *   - This is the transposed access pattern
 *   - Example: column 0 = [grid[0][0], grid[1][0], grid[2][0], ...]
 * 
 * Edge Cases:
 * 
 *   - All zeros: topView = 0, frontView = 0, sideView = 0, total = 0
 *   - Single cell: All three views contribute
 *   - All same height: frontView = n * height, sideView = n * height
 *   - One tall tower: frontView and sideView dominated by that tower
 * 
 * Optimization Note:
 * 
 *   - We can calculate column maximums in a separate loop for clarity
 *   - Current approach calculates both row and column maxes in one pass
 *   - Both approaches are O(n²), but separate loops might be more readable
 */

class Solution {
    public int projectionArea(int[][] grid) {
        int topViewArea = 0;      // xy-plane projection (looking from above)
        int frontViewArea = 0;     // yz-plane projection (looking from front)
        int sideViewArea = 0;      // zx-plane projection (looking from side)
      
        int n = grid.length;
      
        // Iterate through each row
        for (int i = 0; i < n; i++) {
            int maxHeightInRow = 0;     // Maximum height in current row (for front view)
            int maxHeightInColumn = 0;  // Maximum height in current column (for side view)
          
            // Iterate through each column
            for (int j = 0; j < n; j++) {
                // Count non-zero cells for top view projection
                if (grid[i][j] > 0) {
                    topViewArea++;
                }
              
                // Track maximum height in current row (grid[i][j])
                maxHeightInRow = Math.max(maxHeightInRow, grid[i][j]);
              
                // Track maximum height in current column (grid[j][i])
                maxHeightInColumn = Math.max(maxHeightInColumn, grid[j][i]);
            }
          
            // Add the maximum heights to respective projection areas
            frontViewArea += maxHeightInRow;
            sideViewArea += maxHeightInColumn;
        }
      
        // Return the sum of all three projection areas
        return topViewArea + frontViewArea + sideViewArea; 
    }
}

