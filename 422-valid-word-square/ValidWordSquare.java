/*
 * LeetCode 422: Valid Word Square
 *
 * Problem:
 * Given a sequence of words, check whether it forms a valid word square.
 *
 * A sequence of words forms a valid word square if the kth row and kth column
 * read the same string, where 0 <= k < max(number of rows, length of longest word).
 *
 * Approach:
 * - Let n = words.size().
 * - For each position (i, j) in the grid defined by the words:
 *     - Ensure that the corresponding column index j is within bounds (j < n).
 *     - Ensure that the row index i is within the bounds of the j-th word (i < words.get(j).length()).
 *     - Compare words.get(i).charAt(j) with words.get(j).charAt(i); if they differ, it's not a valid word square.
 * - If any bounds check fails or a mismatch is found, return false.
 * - If we finish all checks without returning false, then it is a valid word square.
 *
 * Time Complexity: O(L^2) in the worst case, where L is the maximum length of the words,
 * because we potentially compare each character once.
 * Space Complexity: O(1) extra space.
 */

import java.util.List;

class Solution {

    public boolean validWordSquare(List<String> words) {
        int n = words.size();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < words.get(i).length(); j++) {
                // Check bounds for column side
                if (j >= n || i >= words.get(j).length()) {
                    return false;
                }
                // Compare characters
                if (words.get(i).charAt(j) != words.get(j).charAt(i)) {
                    return false;
                }
            }
        }

        return true;
    }
}


