/**
 * LeetCode 3258: Count Substrings That Satisfy K-Constraint I
 * 
 * Count substrings with at most k zeros OR at most k ones.
 * Uses sliding window: expand right, shrink left when both exceed k.
 * 
 * Example 1:
 *   Input: s = "10101", k = 1
 *   Output: 12
 *   Valid substrings: "1", "0", "1", "0", "1", "10", "01", "10", "01", "101", "010", "101"
 *   Each has ≤1 zero OR ≤1 one.
 * 
 * Example 2:
 *   Input: s = "1010101", k = 2
 *   Output: 25
 * 
 * Example 3:
 *   Input: s = "11111", k = 1
 *   Output: 15
 *   All substrings valid (0 zeros, which is ≤1).
 */

class Solution {

    public int countKConstraintSubstrings(String s, int k) {
        int count = 0;
        int[] freq = new int[2];  // freq[0] = zeros, freq[1] = ones
        int left = 0;

        for (int right = 0; right < s.length(); right++) {
            // Add current char to window
            freq[s.charAt(right) - '0']++;

            // Shrink window while BOTH counts exceed k
            while (freq[0] > k && freq[1] > k) {
                freq[s.charAt(left) - '0']--;
                left++;
            }

            // All substrings ending at right are valid
            count += right - left + 1;
        }

        return count;
    }
}

