/*
 * LeetCode 1886: Determine Whether Matrix Can Be Obtained By Rotation
 *
 * Problem:
 * Given two n x n binary matrices mat and target, return true if it is possible
 * to make mat equal to target by rotating mat in 90-degree steps (clockwise),
 * otherwise return false.
 *
 * We can rotate mat by 0°, 90°, 180°, or 270° (i.e., up to 3 rotations).
 *
 * Approach: Simulate Rotations and Compare
 *
 * Key Ideas:
 * - At most 4 orientations matter: the original matrix plus 3 successive
 *   90-degree clockwise rotations.
 * - For each orientation:
 *     1. Check if mat equals target.
 *     2. If not equal, build a new matrix that is mat rotated 90 degrees clockwise.
 * - If any orientation matches target, return true; otherwise false.
 *
 * Rotation Formula (90 degrees clockwise):
 *   For each cell (i, j) in mat:
 *     rotated[j][n - 1 - i] = mat[i][j]
 *
 * Equality Check:
 * - Compare all corresponding entries mat[i][j] and target[i][j].
 * - Early break as soon as a mismatch is found to keep it efficient.
 *
 * Time Complexity:
 * - For each of 4 rotations:
 *     - O(n^2) to compare
 *     - O(n^2) to build the rotated matrix
 * - Overall: O(4 * n^2) = O(n^2).
 *
 * Space Complexity:
 * - O(n^2) extra for the temporary rotated matrix.
 */

class Solution {

    public boolean findRotation(int[][] mat, int[][] target) {
        int n = mat.length;

        // Try all 4 possible orientations: 0°, 90°, 180°, 270°
        for (int r = 0; r < 4; r++) {
            // Check equality between current mat and target
            boolean equal = true;
            for (int i = 0; i < n && equal; i++) {
                for (int j = 0; j < n; j++) {
                    if (mat[i][j] != target[i][j]) {
                        equal = false;
                        break;
                    }
                }
            }

            if (equal) {
                return true;
            }

            // Rotate mat 90 degrees clockwise for the next iteration
            int[][] rotated = new int[n][n];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    rotated[j][n - 1 - i] = mat[i][j];
                }
            }
            mat = rotated;
        }

        return false;
    }
}


