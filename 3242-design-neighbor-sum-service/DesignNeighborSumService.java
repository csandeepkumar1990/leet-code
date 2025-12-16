/*
 * LeetCode 3242: Design Neighbor Sum Service
 * 
 * Problem:
 * Design a class that can compute the sum of adjacent or diagonal neighbors
 * of any element in a 2D grid.
 * 
 * - adjacentSum(value): Returns sum of elements adjacent to value (up, down, left, right)
 * - diagonalSum(value): Returns sum of diagonal neighbors (4 corners)
 * 
 * Approach: HashMap for O(1) Position Lookup
 * 
 * Key Insight:
 * - Store each value's position in a HashMap during initialization
 * - For each query, lookup position in O(1) and check neighbors
 * - Use direction arrays to iterate over adjacent/diagonal positions
 * 
 * Algorithm:
 * 1. Constructor: Build position map (value → [row, col])
 * 2. adjacentSum: Get position, sum 4 cardinal neighbors (↑↓←→)
 * 3. diagonalSum: Get position, sum 4 diagonal neighbors (↖↗↙↘)
 * 
 * Time Complexity:
 *   - Constructor: O(n²) to build position map
 *   - adjacentSum: O(1) - check fixed 4 neighbors
 *   - diagonalSum: O(1) - check fixed 4 neighbors
 * 
 * Space Complexity: O(n²) for storing position map
 * 
 * Example:
 * 
 *   grid = [[0, 1, 2],
 *           [3, 4, 5],
 *           [6, 7, 8]]
 * 
 *   adjacentSum(4):
 *     Position of 4: (1, 1)
 *     Adjacent: ↑(1) + ↓(7) + ←(3) + →(5) = 16
 * 
 *   diagonalSum(4):
 *     Position of 4: (1, 1)
 *     Diagonal: ↖(0) + ↗(2) + ↙(6) + ↘(8) = 16
 * 
 *   adjacentSum(1):
 *     Position of 1: (0, 1)
 *     Adjacent: ↑(out) + ↓(4) + ←(0) + →(2) = 6
 * 
 * Visual:
 * 
 *   For value 4 at center:
 * 
 *   Adjacent (↑↓←→):          Diagonal (↖↗↙↘):
 *        [1]                    [0]   [2]
 *     [3][4][5]                   [4]
 *        [7]                    [6]   [8]
 * 
 *   Sum = 1+3+5+7 = 16        Sum = 0+2+6+8 = 16
 */

class NeighborSum {

    private int[][] grid;
    private int n;
    private Map<Integer, int[]> position;

    /**
     * Initialize with grid and build position lookup map
     */
    public NeighborSum(int[][] grid) {

        this.grid = grid;
        this.n = grid.length;
        this.position = new HashMap<>();

        // Store position of each value for O(1) lookup
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                position.put(grid[i][j], new int[] { i, j });
            }
        }

    }

    /**
     * Returns sum of horizontally and vertically adjacent neighbors
     * Directions: top, bottom, left, right
     */
    public int adjacentSum(int value) {

        int[] pos = position.get(value);
        int r = pos[0], c = pos[1];
        int sum = 0;

        // Cardinal directions: up, down, left, right
        int[][] directions = {
                { -1, 0 }, // top
                { 1, 0 },  // bottom
                { 0, -1 }, // left
                { 0, 1 }   // right
        };

        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            // Check bounds before accessing
            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                sum += grid[nr][nc];
            }
        }

        return sum;

    }

    /**
     * Returns sum of diagonally adjacent neighbors
     * Directions: top-left, top-right, bottom-left, bottom-right
     */
    public int diagonalSum(int value) {

        int[] pos = position.get(value);
        int r = pos[0], c = pos[1];
        int sum = 0;

        // Diagonal directions: all 4 corners
        int[][] directions = {
                { -1, -1 }, // top-left
                { -1, 1 },  // top-right
                { 1, -1 },  // bottom-left
                { 1, 1 }    // bottom-right
        };

        for (int[] d : directions) {
            int nr = r + d[0];
            int nc = c + d[1];
            // Check bounds before accessing
            if (nr >= 0 && nr < n && nc >= 0 && nc < n) {
                sum += grid[nr][nc];
            }
        }

        return sum;

    }

}

/**
 * Your NeighborSum object will be instantiated and called as such:
 * NeighborSum obj = new NeighborSum(grid);
 * int param_1 = obj.adjacentSum(value);
 * int param_2 = obj.diagonalSum(value);
 */

