/*
 * LeetCode 3571: Shortest Superstring
 * 
 * Problem:
 * Given two strings s1 and s2, find the shortest string that contains
 * both s1 and s2 as substrings.
 * If multiple answers exist with same length, return lexicographically smallest.
 * 
 * Approach: Check Containment + Overlap Merging
 * 
 * Key Insight:
 * Three possible cases for the shortest superstring:
 * 1. s1 contains s2 → answer is s1
 * 2. s2 contains s1 → answer is s2
 * 3. Neither contains other → merge with maximum overlap
 * 
 * Merging Strategy:
 * - Find where suffix of one string matches prefix of another
 * - Merge at the maximum overlap point
 * - Try both orderings: s1+s2 and s2+s1
 * 
 * Overlap Example:
 * s1 = "abcde", s2 = "cdefg"
 *       ───┘     └───
 *       suffix   prefix
 *       "cde" == "cde" (overlap of 3)
 * 
 * Merged: "abcde" + "fg" = "abcdefg" (length 7, not 10)
 * 
 * Algorithm:
 * 1. Check if s1 contains s2 → return s1
 * 2. Check if s2 contains s1 → return s2
 * 3. Compute merge(s1, s2) - s1 followed by s2 with overlap
 * 4. Compute merge(s2, s1) - s2 followed by s1 with overlap
 * 5. Return shorter result (or lexicographically smaller if tied)
 * 
 * Time Complexity: O(n²) where n = max(len(s1), len(s2))
 *   - contains() is O(n*m), overlap check is O(n²)
 * Space Complexity: O(n) for creating merged strings
 * 
 * Example 1: s1 = "abc", s2 = "bcd"
 * 
 * - s1 contains s2? No
 * - s2 contains s1? No
 * - merge(s1, s2): "abc" + "bcd"
 *   - overlap "c" (len 1): "abc"suffix "c" == "bcd"prefix "b"? No
 *   - overlap "bc" (len 2): "abc"suffix "bc" == "bcd"prefix "bc"? Yes!
 *   - maxOverlap = 2
 *   - result: "abc" + "d" = "abcd"
 * - merge(s2, s1): "bcd" + "abc"
 *   - no overlap found
 *   - result: "bcdabc"
 * 
 * Answer: "abcd" (length 4 < length 6)
 * 
 * Visual (Overlap Merging):
 * 
 *   s1: a  b  c
 *          └──┼──┘
 *             │  overlap
 *          ┌──┼──┐
 *   s2:    b  c  d
 *   
 *   Merged: a  b  c  d
 *           └──────────┘
 */

class Solution {

    public String shortestSuperstring(String s1, String s2) {

        // Case 1: s1 already contains s2 as substring
        // No need to add anything - s1 is the answer
        if (s1.contains(s2))

            return s1;

        // Case 2: s2 already contains s1 as substring
        // No need to add anything - s2 is the answer
        if (s2.contains(s1))

            return s2;

        // Case 3: Neither contains the other
        // Try both merge orderings and pick the shorter one
        String opt1 = merge(s1, s2); // s1 followed by s2

        String opt2 = merge(s2, s1); // s2 followed by s1

        // Return shorter string, or lexicographically smaller if same length
        return opt1.length() <= opt2.length() ? opt1 : opt2;

    }

    /**
     * Merges string a followed by string b with maximum overlap.
     * Finds where suffix of a matches prefix of b and combines them.
     * 
     * Example: merge("abc", "bcd")
     *   - suffix "bc" of "abc" == prefix "bc" of "bcd"
     *   - return "abc" + "d" = "abcd"
     */
    private String merge(String a, String b) {

        int maxOverlap = 0;

        int minLen = Math.min(a.length(), b.length());

        // Try all possible overlap lengths from 1 to minLen
        // Check if suffix of 'a' (last i chars) equals prefix of 'b' (first i chars)
        for (int i = 1; i <= minLen; i++) {

            // a's suffix of length i
            String aSuffix = a.substring(a.length() - i);

            // b's prefix of length i
            String bPrefix = b.substring(0, i);

            // If they match, we found an overlap
            if (aSuffix.equals(bPrefix)) {

                maxOverlap = i;

            }

        }

        // Merge: keep all of a, append b without the overlapping prefix
        return a + b.substring(maxOverlap);

    }

}

