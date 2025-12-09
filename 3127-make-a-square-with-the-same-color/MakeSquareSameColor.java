/**
 * LeetCode 3127: Make a Square with the Same Color
 * 
 * Given 3x3 grid of 'B' and 'W'. Check if any 2x2 subgrid
 * can become same color by changing at most 1 cell.
 * True if 3+ cells in any 2x2 are same color.
 */

class Solution {

    public boolean canMakeSquare(char[][] grid) {
        // Check all four possible 2x2 subgrids
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                int black = 0, white = 0;

                // Count colors in current 2x2 subgrid
                for (int x = i; x < i + 2; x++) {
                    for (int y = j; y < j + 2; y++) {
                        if (grid[x][y] == 'B') black++;
                        else white++;
                    }
                }

                // 3+ same color means we can make all 4 same with 1 change
                if (black >= 3 || white >= 3)
                    return true;
            }
        }
        return false;
    }
}

