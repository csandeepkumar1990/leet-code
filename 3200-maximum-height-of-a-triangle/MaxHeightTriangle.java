/**
 * LeetCode 3200: Maximum Height of a Triangle
 * 
 * Build triangle where each row alternates colors (red/blue).
 * Row i needs i balls. Return max rows possible.
 * Try starting with red, then blue, take the max.
 */

class Solution {

    public int maxHeightOfTriangle(int red, int blue) {
        // Try both starting colors, return the better result
        return Math.max(helper(red, blue, true), helper(red, blue, false));
    }

    private int helper(int red, int blue, boolean startRed) {
        int height = 0;
        int row = 1;
        boolean useRed = startRed;

        while (true) {
            // Try to place 'row' balls of current color
            if (useRed) {
                if (red >= row) {
                    red -= row;
                } else {
                    break;  // not enough red balls
                }
            } else {
                if (blue >= row) {
                    blue -= row;
                } else {
                    break;  // not enough blue balls
                }
            }

            // Successfully placed row, move to next
            height++;
            useRed = !useRed;  // alternate color
            row++;
        }

        return height;
    }
}

