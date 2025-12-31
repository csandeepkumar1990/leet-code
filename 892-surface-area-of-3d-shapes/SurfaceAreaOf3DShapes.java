/*
 * LeetCode 892: Surface Area of 3D Shapes
 * 
 * Problem:
 * You are given an n x n grid where we place some 1 x 1 x 1 cubes that are axis-aligned
 * with the x, y, and z axes.
 * 
 * Each value v = grid[i][j] represents a tower of v cubes placed on top of cell (i, j).
 * 
 * Return the total surface area of the resulting shapes.
 * 
 * Approach: Calculate Surface Area and Subtract Overlaps
 * 
 * Key Insight:
 * - Each stack of cubes has 6 faces per cube, but we only see exposed faces
 * - For a stack of height h:
 *   - Top face: 1 (always exposed)
 *   - Bottom face: 1 (always exposed)
 *   - Side faces: h * 4 (one for each direction: north, south, east, west)
 *   - Initial area = 2 + h * 4
 * - When two adjacent stacks share a face, subtract the overlapping area
 * - Overlapping area = 2 * min(height1, height2) (both stacks lose that area)
 * 
 * Algorithm:
 * 1. For each cell with cubes (height > 0):
 *    a. Add initial surface area: 2 + height * 4
 *    b. If upper neighbor exists, subtract overlap: 2 * min(current, upper)
 *    c. If left neighbor exists, subtract overlap: 2 * min(current, left)
 * 2. Return total surface area
 * 
 * Why Check Only Upper and Left Neighbors?
 * - When processing cell (i, j), we check neighbors we've already processed
 * - Upper neighbor (i-1, j): already processed
 * - Left neighbor (i, j-1): already processed
 * - Lower and right neighbors will be checked when we process them
 * - This ensures each overlap is counted exactly once
 * 
 * Why Subtract 2 * min(height1, height2)?
 * - When two stacks share a face, both lose that face area
 * - The shared area = min(height1, height2) (the shorter stack's height)
 * - Each stack loses this area, so total loss = 2 * min(height1, height2)
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
 *   Grid representation:
 *   Position (0,0): height = 1
 *   Position (0,1): height = 2
 *   Position (1,0): height = 3
 *   Position (1,1): height = 4
 * 
 *   Processing (0,0): height = 1
 *   - Initial area: 2 + 1 * 4 = 6
 *   - No upper neighbor
 *   - No left neighbor
 *   - Total: 6
 * 
 *   Processing (0,1): height = 2
 *   - Initial area: 2 + 2 * 4 = 10
 *   - No upper neighbor
 *   - Left neighbor (0,0): min(2, 1) * 2 = 2
 *   - Total: 10 - 2 = 8
 *   - Running total: 6 + 8 = 14
 * 
 *   Processing (1,0): height = 3
 *   - Initial area: 2 + 3 * 4 = 14
 *   - Upper neighbor (0,0): min(3, 1) * 2 = 2
 *   - No left neighbor
 *   - Total: 14 - 2 = 12
 *   - Running total: 14 + 12 = 26
 * 
 *   Processing (1,1): height = 4
 *   - Initial area: 2 + 4 * 4 = 18
 *   - Upper neighbor (0,1): min(4, 2) * 2 = 4
 *   - Left neighbor (1,0): min(4, 3) * 2 = 6
 *   - Total: 18 - 4 - 6 = 8
 *   - Running total: 26 + 8 = 34
 * 
 *   Result: 34
 * 
 * Visual Representation:
 * 
 *   Top view:
 *   +---+---+
 *   | 1 | 2 |
 *   +---+---+
 *   | 3 | 4 |
 *   +---+---+
 * 
 *   3D view (side):
 *        2   4
 *        |   |
 *    1---3---4
 *    |   |   |
 *    |   |   |
 * 
 *   Surface area breakdown:
 *   - Top faces: 4 (one per stack)
 *   - Bottom faces: 4 (one per stack)
 *   - Side faces: varies based on heights and adjacencies
 *   - Shared faces: subtracted from both stacks
 * 
 * Another Example:
 * 
 *   Input: grid = [[2]]
 * 
 *   Processing (0,0): height = 2
 *   - Initial area: 2 + 2 * 4 = 10
 *   - No neighbors
 *   - Total: 10
 * 
 *   Verification:
 *   - Top face: 1
 *   - Bottom face: 1
 *   - 4 side faces: 2 + 2 + 2 + 2 = 8
 *   - Total: 1 + 1 + 8 = 10 ✓
 * 
 * Formula Breakdown:
 * 
 *   For a stack of height h:
 *   - Top face: 1 unit
 *   - Bottom face: 1 unit
 *   - 4 side faces: h units each = 4h units
 *   - Total: 2 + 4h
 * 
 *   For two adjacent stacks with heights h1 and h2:
 *   - Shared area: min(h1, h2) units
 *   - Each stack loses this area
 *   - Total loss: 2 * min(h1, h2)
 * 
 * Why Not Check All Four Neighbors?
 * 
 *   - If we check all four neighbors, we'd count each overlap twice
 *   - Example: cell (1,1) and (1,2) share a face
 *     - When processing (1,1), we'd subtract overlap with (1,2)
 *     - When processing (1,2), we'd subtract overlap with (1,1)
 *     - This counts the same overlap twice!
 *   - By checking only upper and left, we count each overlap exactly once
 * 
 * Edge Cases:
 * 
 *   - All zeros: No cubes, surface area = 0
 *   - Single cell: height = h, area = 2 + 4h
 *   - All same height: All overlaps are h, subtract accordingly
 *   - Isolated stacks: No overlaps, each contributes 2 + 4h
 *   - Very tall tower: Large side area, but overlaps still based on neighbors
 * 
 * Comparison with Problem 883 (Projection Area):
 * 
 *   Problem 883: Calculates 2D projections (shadows)
 *   - Top view: count non-zero cells
 *   - Front view: sum of max in each row
 *   - Side view: sum of max in each column
 * 
 *   Problem 892: Calculates actual 3D surface area
 *   - Accounts for all 6 faces of cubes
 *   - Subtracts overlapping faces between adjacent stacks
 *   - More complex calculation
 */

class Solution {
    public int surfaceArea(int[][] grid) {
        int gridSize = grid.length;
        int totalSurfaceArea = 0;
      
        // Iterate through each cell in the grid
        for (int row = 0; row < gridSize; row++) {
            for (int col = 0; col < gridSize; col++) {
                // Process only cells with cubes (height > 0)
                if (grid[row][col] > 0) {
                    // Calculate initial surface area for current stack of cubes
                    // Top face: 1, Bottom face: 1, Side faces: height * 4
                    totalSurfaceArea += 2 + grid[row][col] * 4;
                  
                    // Subtract overlapping area with the cell above (if exists)
                    // The overlapping area is twice the minimum height between adjacent cells
                    if (row > 0) {
                        int overlappingArea = Math.min(grid[row][col], grid[row - 1][col]) * 2;
                        totalSurfaceArea -= overlappingArea;
                    }
                  
                    // Subtract overlapping area with the cell to the left (if exists)
                    // The overlapping area is twice the minimum height between adjacent cells
                    if (col > 0) {
                        int overlappingArea = Math.min(grid[row][col], grid[row][col - 1]) * 2;
                        totalSurfaceArea -= overlappingArea;
                    }
                }
            }
        }
      
        return totalSurfaceArea; 
    }
}

