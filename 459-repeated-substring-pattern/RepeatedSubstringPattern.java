/*
 * LeetCode 459: Repeated Substring Pattern
 * 
 * Problem:
 * Given a string s, check if it can be constructed by taking a substring
 * and repeating it multiple times.
 * 
 * Examples:
 * - "abab" → "ab" repeated 2 times → true
 * - "aba" → cannot be formed by repeating → false
 * - "abcabcabc" → "abc" repeated 3 times → true
 * 
 * Approach: Double String Trick
 * 
 * Key Insight (Brilliant Math!):
 * If s is made of repeated pattern p, then:
 * - s = p + p + p + ... (k times)
 * - s + s = p + p + p + ... (2k times)
 * 
 * When we concatenate s with itself and remove first and last chars:
 * - If s has a repeated pattern, s will still appear in the middle
 * - If s has NO repeated pattern, s won't appear (we broke the only occurrence)
 * 
 * Why This Works:
 * 
 * Case 1: s = "abab" (has pattern "ab")
 *   s + s = "abababab"
 *   Remove first & last: "bababa"
 *   Does "bababa" contain "abab"? → "b[abab]a" YES!
 *   
 * Case 2: s = "abc" (no repeated pattern)
 *   s + s = "abcabc"
 *   Remove first & last: "bcab"
 *   Does "bcab" contain "abc"? → NO!
 * 
 * Mathematical Intuition:
 * - Original s+s has s at positions 0 and len(s)
 * - By removing first char, we break the s at position 0
 * - By removing last char, we break the s at position len(s)
 * - If s STILL appears, there must be another occurrence in between
 * - That's only possible if s has a repeating pattern!
 * 
 * Algorithm:
 * 1. Concatenate s with itself: ss = s + s
 * 2. Remove first and last character: sub = ss[1:-1]
 * 3. Check if s exists in sub
 * 
 * Time Complexity: O(n²) - substring search (can be O(n) with KMP)
 * Space Complexity: O(n) - for the concatenated string
 * 
 * Example: s = "abcabc"
 * 
 * Step 1: ss = "abcabc" + "abcabc" = "abcabcabcabc"
 *                                     0123456789...
 * 
 * Step 2: sub = ss[1:11] = "bcabcabcab"
 *         (removed 'a' at start and 'c' at end)
 * 
 * Step 3: Does "bcabcabcab" contain "abcabc"?
 *              "bc[abcabc]ab" → YES!
 * 
 * Return: true (pattern is "abc")
 * 
 * Visual:
 * 
 *   s = "abab"
 *   
 *   s + s:     a b a b | a b a b
 *              ↑               ↑
 *              remove          remove
 *   
 *   sub:         b a b   a b a
 *                  └──┬──┘
 *                  "abab" found in middle!
 *   
 *   This means "abab" has a repeating pattern ✓
 */

class Solution {

    public boolean repeatedSubstringPattern(String s) {

        // Step 1: Concatenate the string with itself
        // This creates a string where s appears at position 0 and position n
        String ss = s + s;

        // Step 2: Remove first and last character
        // This breaks the two original occurrences of s
        String sub = ss.substring(1, ss.length() - 1);

        // Step 3: Check if s still appears in the modified string
        // If yes, s must have a repeating pattern!
        return sub.contains(s);

    }

}

