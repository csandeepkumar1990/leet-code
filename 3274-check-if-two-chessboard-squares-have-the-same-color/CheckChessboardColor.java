/**
 * LeetCode 3274: Check if Two Chessboard Squares Have the Same Color
 * 
 * Two squares same color if (col_diff + row_diff) is even.
 * Chessboard alternates colors, so parity determines match.
 */

class Solution {

    public boolean checkTwoChessboards(String coordinate1, String coordinate2) {
        int columnDifference = coordinate1.charAt(0) - coordinate2.charAt(0);
        int rowDifference = coordinate1.charAt(1) - coordinate2.charAt(1);

        // Same color if total difference is even
        return (columnDifference + rowDifference) % 2 == 0;
    }
}

