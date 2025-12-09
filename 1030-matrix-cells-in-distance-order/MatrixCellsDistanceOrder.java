/**
 * LeetCode 1030: Matrix Cells in Distance Order
 * 
 * Problem: Given a matrix with 'rows' rows and 'cols' columns, and a center
 *          cell (rCenter, cCenter), return all cell coordinates sorted by
 *          their Manhattan distance from the center.
 *          
 *          Manhattan Distance = |r1 - r2| + |c1 - c2|
 * 
 * Key Insight: Collect all coordinates, then sort by distance using a comparator.
 *              The comparator calculates Manhattan distance for each comparison.
 * 
 * Examples:
 *   Input: rows = 1, cols = 2, rCenter = 0, cCenter = 0
 *   Output: [[0,0], [0,1]]
 *   Explanation:
 *     - (0,0) distance = |0-0| + |0-0| = 0
 *     - (0,1) distance = |0-0| + |1-0| = 1
 *     - Sorted: [[0,0], [0,1]]
 * 
 *   Input: rows = 2, cols = 2, rCenter = 0, cCenter = 1
 *   Output: [[0,1], [0,0], [1,1], [1,0]]
 *   Explanation:
 *     - (0,0) distance = 1
 *     - (0,1) distance = 0  ← center
 *     - (1,0) distance = 2
 *     - (1,1) distance = 1
 *     - Sorted by distance: 0, 1, 1, 2
 * 
 *   Input: rows = 2, cols = 3, rCenter = 1, cCenter = 2
 *   Output: [[1,2], [0,2], [1,1], [0,1], [1,0], [0,0]]
 * 
 * Constraints:
 *   - 1 <= rows, cols <= 100
 *   - 0 <= rCenter < rows
 *   - 0 <= cCenter < cols
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {

    /**
     * Returns all matrix cells sorted by Manhattan distance from center.
     * 
     * @param rows - Number of rows in the matrix
     * @param cols - Number of columns in the matrix
     * @param rCenter - Row index of the center cell
     * @param cCenter - Column index of the center cell
     * @return 2D array of coordinates sorted by distance
     * 
     * Time Complexity: O(n log n) where n = rows × cols (sorting dominates)
     * Space Complexity: O(n) for storing all coordinates
     */
    public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
        /*
         * STEP 1: Create a list to store all cell coordinates
         * 
         * We use List<int[]> where each int[] is {row, col}
         */
        List<int[]> cells = new ArrayList<>();

        /*
         * STEP 2: Add all coordinates (r, c) into the list
         * 
         * Iterate through every cell in the matrix
         * 
         * For a 2×3 matrix:
         *   (0,0), (0,1), (0,2)
         *   (1,0), (1,1), (1,2)
         */
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                cells.add(new int[]{r, c});
            }
        }

        /*
         * STEP 3: Sort the cells by their Manhattan distance from center
         * 
         * Manhattan Distance = |row - rCenter| + |col - cCenter|
         * 
         * We use a lambda comparator:
         *   a, b are two cells (int arrays)
         *   Compare their distances, return negative/zero/positive
         *   
         * distA - distB:
         *   < 0 → a comes first (a is closer)
         *   = 0 → same distance (order doesn't matter)
         *   > 0 → b comes first (b is closer)
         */
        Collections.sort(cells, (a, b) -> {
            int distA = Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter);
            int distB = Math.abs(b[0] - rCenter) + Math.abs(b[1] - cCenter);
            return distA - distB;  // Sort by smaller distance first
        });

        /*
         * STEP 4: Convert the list back to a 2D array to return
         * 
         * LeetCode expects int[][] as return type
         */
        int[][] result = new int[cells.size()][2];
        for (int i = 0; i < cells.size(); i++) {
            result[i] = cells.get(i);
        }

        return result;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * int[][] result = sol.allCellsDistOrder(2, 2, 0, 1);
 * // Output: [[0,1], [0,0], [1,1], [1,0]]
 * 
 * ═══════════════════════════════════════════════════════════════
 * MANHATTAN DISTANCE VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: rows = 3, cols = 3, rCenter = 1, cCenter = 1
 * 
 *         col 0   col 1   col 2
 *       ┌───────┬───────┬───────┐
 * row 0 │   2   │   1   │   2   │
 *       ├───────┼───────┼───────┤
 * row 1 │   1   │   0   │   1   │  ← center at (1,1)
 *       ├───────┼───────┼───────┤
 * row 2 │   2   │   1   │   2   │
 *       └───────┴───────┴───────┘
 *       
 *       Numbers show Manhattan distance from center (1,1)
 *       
 * Sorted order by distance:
 *   Distance 0: (1,1)
 *   Distance 1: (0,1), (1,0), (1,2), (2,1)
 *   Distance 2: (0,0), (0,2), (2,0), (2,2)
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY MANHATTAN DISTANCE?
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Manhattan distance = |Δrow| + |Δcol|
 *   
 *   It's called "Manhattan" because it's like navigating
 *   a grid of city blocks (like Manhattan, NYC) where you
 *   can only move horizontally or vertically.
 *   
 *          ●───────────┐
 *          │           │
 *          │           ▼
 *          │           ●
 *          
 *   Euclidean (straight line): √(Δrow² + Δcol²)
 *   Manhattan (grid walk):     |Δrow| + |Δcol|
 * 
 * ═══════════════════════════════════════════════════════════════
 * STEP-BY-STEP TRACE
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: rows = 2, cols = 2, rCenter = 0, cCenter = 1
 * 
 * STEP 1-2: Collect all cells
 *   cells = [(0,0), (0,1), (1,0), (1,1)]
 * 
 * STEP 3: Calculate distances and sort
 *   ┌────────┬──────────────────────────────┬──────────┐
 *   │  Cell  │  Distance Calculation        │ Distance │
 *   ├────────┼──────────────────────────────┼──────────┤
 *   │ (0,0)  │ |0-0| + |0-1| = 0 + 1        │    1     │
 *   │ (0,1)  │ |0-0| + |1-1| = 0 + 0        │    0     │ ← center
 *   │ (1,0)  │ |1-0| + |0-1| = 1 + 1        │    2     │
 *   │ (1,1)  │ |1-0| + |1-1| = 1 + 0        │    1     │
 *   └────────┴──────────────────────────────┴──────────┘
 *   
 *   After sorting: [(0,1), (0,0), (1,1), (1,0)]
 *                     0      1      1      2
 * 
 * STEP 4: Convert to array and return
 *   result = [[0,1], [0,0], [1,1], [1,0]]
 * 
 * ═══════════════════════════════════════════════════════════════
 * LAMBDA COMPARATOR EXPLAINED
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Collections.sort(cells, (a, b) -> {
 *       int distA = Math.abs(a[0] - rCenter) + Math.abs(a[1] - cCenter);
 *       int distB = Math.abs(b[0] - rCenter) + Math.abs(b[1] - cCenter);
 *       return distA - distB;
 *   });
 *   
 *   The comparator returns:
 *   - Negative: a should come BEFORE b
 *   - Zero: a and b are equal (order doesn't matter)
 *   - Positive: a should come AFTER b
 *   
 *   distA - distB:
 *   - If distA < distB → negative → a comes first ✓
 *   - If distA = distB → zero → tie
 *   - If distA > distB → positive → b comes first ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Single cell matrix:
 *    rows=1, cols=1, rCenter=0, cCenter=0
 *    → Output: [[0,0]] (only one cell)
 * 
 * 2. Center at corner:
 *    rows=2, cols=2, rCenter=0, cCenter=0
 *    → Distances: (0,0)=0, (0,1)=1, (1,0)=1, (1,1)=2
 * 
 * 3. Center at edge:
 *    rows=3, cols=3, rCenter=1, cCenter=0
 *    → Cells expand outward from left-middle
 * 
 * 4. Ties (same distance):
 *    Multiple cells may have the same distance.
 *    Their relative order doesn't matter per problem.
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: BFS (BREADTH-FIRST SEARCH)
 * ═══════════════════════════════════════════════════════════════
 * 
 *   BFS naturally visits cells in order of distance!
 *   Start from center, expand outward layer by layer.
 *   
 *   public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
 *       boolean[][] visited = new boolean[rows][cols];
 *       int[][] result = new int[rows * cols][2];
 *       int idx = 0;
 *       
 *       Queue<int[]> queue = new LinkedList<>();
 *       queue.offer(new int[]{rCenter, cCenter});
 *       visited[rCenter][cCenter] = true;
 *       
 *       int[][] dirs = {{0,1}, {0,-1}, {1,0}, {-1,0}};
 *       
 *       while (!queue.isEmpty()) {
 *           int[] cell = queue.poll();
 *           result[idx++] = cell;
 *           
 *           for (int[] dir : dirs) {
 *               int nr = cell[0] + dir[0];
 *               int nc = cell[1] + dir[1];
 *               if (nr >= 0 && nr < rows && nc >= 0 && nc < cols 
 *                   && !visited[nr][nc]) {
 *                   visited[nr][nc] = true;
 *                   queue.offer(new int[]{nr, nc});
 *               }
 *           }
 *       }
 *       return result;
 *   }
 *   
 *   Time: O(rows × cols) - no sorting needed!
 *   Space: O(rows × cols) for visited array and queue
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: BUCKET SORT BY DISTANCE
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Since max distance is bounded (rows + cols - 2), we can use
 *   bucket sort for O(n) time complexity!
 *   
 *   public int[][] allCellsDistOrder(int rows, int cols, int rCenter, int cCenter) {
 *       int maxDist = rows - 1 + cols - 1;  // Maximum possible distance
 *       List<List<int[]>> buckets = new ArrayList<>();
 *       
 *       for (int i = 0; i <= maxDist; i++) {
 *           buckets.add(new ArrayList<>());
 *       }
 *       
 *       // Put each cell in its distance bucket
 *       for (int r = 0; r < rows; r++) {
 *           for (int c = 0; c < cols; c++) {
 *               int dist = Math.abs(r - rCenter) + Math.abs(c - cCenter);
 *               buckets.get(dist).add(new int[]{r, c});
 *           }
 *       }
 *       
 *       // Collect from buckets in order
 *       int[][] result = new int[rows * cols][2];
 *       int idx = 0;
 *       for (List<int[]> bucket : buckets) {
 *           for (int[] cell : bucket) {
 *               result[idx++] = cell;
 *           }
 *       }
 *       return result;
 *   }
 *   
 *   Time: O(rows × cols) - linear!
 *   Space: O(rows × cols)
 */

