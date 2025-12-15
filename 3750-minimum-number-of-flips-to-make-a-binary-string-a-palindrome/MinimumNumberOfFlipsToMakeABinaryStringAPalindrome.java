/*
 * LeetCode 3750: Minimum Number of Flips to Make a Binary String a Palindrome
 * 
 * Problem:
 * Given a positive integer n, return the minimum number of bit flips required
 * to make the binary representation of n a palindrome.
 * 
 * A palindrome reads the same forwards and backwards.
 * 
 * Approach: Two-Pointer Comparison
 * 
 * Key Insight:
 * - Convert n to binary string
 * - Compare characters from both ends moving inward
 * - Each mismatch requires exactly ONE flip (flip either bit to match)
 * - Only iterate through HALF the string to avoid double counting
 * 
 * Algorithm:
 * 1. Convert integer to binary string
 * 2. Use two pointers: start (i) and end (len-1-i)
 * 3. For each mismatch, increment flip counter
 * 4. Return total flips needed
 * 
 * Time Complexity: O(log n) - binary representation has log₂(n) bits
 * Space Complexity: O(log n) - for storing the binary string
 * 
 * Example: n = 3750
 * 
 * Binary: "111010100110" (length 12)
 * 
 * Comparing pairs (only first half):
 *   i=0: s[0]='1' vs s[11]='0' → mismatch ✗ (flip needed)
 *   i=1: s[1]='1' vs s[10]='1' → match ✓
 *   i=2: s[2]='1' vs s[9]='1'  → match ✓
 *   i=3: s[3]='0' vs s[8]='0'  → match ✓
 *   i=4: s[4]='1' vs s[7]='0'  → mismatch ✗ (flip needed)
 *   i=5: s[5]='0' vs s[6]='1'  → mismatch ✗ (flip needed)
 * 
 * Return: 3 flips
 * 
 * Visual:
 * 
 *   Original:  1 1 1 0 1 0 | 1 0 0 1 1 0
 *              ↓         ↓   ↓         ↓
 *   Pairs:    [0,11]   [4,7] [5,6]
 *              ✗         ✗     ✗
 * 
 *   After flipping 3 bits to make palindrome:
 *   Option 1:  1 1 1 0 1 0 | 0 1 0 1 1 1  (flip positions 6, 7, 11)
 *   Option 2:  0 1 1 0 0 1 | 1 0 0 1 1 0  (flip positions 0, 4, 5)
 * 
 * Why iterate only half?
 * - Each pair of positions (i, len-1-i) is checked once
 * - If we iterate full length, we'd count each mismatch twice
 *   (once at position i, once at position len-1-i)
 */

class Solution {

    public int minimumFlips(int n) {

        // Convert integer to binary string representation
        String s = Integer.toBinaryString(n);

        // Counter for mismatched bit pairs
        int flips = 0;

        int len = s.length();

        // Compare characters from both ends, moving inward
        // Only iterate through first half to avoid double counting
        for (int i = 0; i < len / 2; i++) {

            // Compare character at position i with its mirror position
            if (s.charAt(i) != s.charAt(len - 1 - i)) {

                // Mismatch found - need one flip to make them equal
                flips++;

            }

        }

        // Return total flips needed for palindrome
        return flips;

    }

}

