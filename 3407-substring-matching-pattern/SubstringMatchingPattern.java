/**
 * LeetCode 3407: Substring Matching Pattern
 * 
 * Problem: Given a string s and a pattern p containing exactly one '*',
 *          determine if p can be made a substring of s by replacing the '*'
 *          with any sequence of zero or more characters.
 * 
 * Key Insight: Split the pattern at '*' into prefix and suffix.
 *              Then find if there exists a position where:
 *              - prefix starts at some index i
 *              - suffix starts at some index j >= i + prefix.length()
 *              This means the '*' matches everything between them!
 * 
 * Examples:
 *   Input: s = "leetcode", p = "ee*e"
 *   Output: true
 *   Explanation:
 *     - Split "ee*e" → prefix = "ee", suffix = "e"
 *     - Find "ee" in s → found at index 1
 *     - Find "e" after index 3 → found at index 7
 *     - '*' matches "tcod" (characters between)
 *     - Substring "eetcode" matches pattern!
 * 
 *   Input: s = "car", p = "c*v"
 *   Output: false
 *   Explanation:
 *     - Split "c*v" → prefix = "c", suffix = "v"
 *     - Find "c" in s → found at index 0
 *     - Find "v" after index 1 → NOT FOUND
 *     - No matching substring exists
 * 
 *   Input: s = "luck", p = "u*"
 *   Output: true
 *   Explanation:
 *     - Split "u*" → prefix = "u", suffix = ""
 *     - Find "u" in s → found at index 1
 *     - Empty suffix always matches!
 *     - Substrings "u", "uc", "uck" all match
 * 
 * Constraints:
 *   - 1 <= s.length <= 50
 *   - 1 <= p.length <= 50
 *   - s contains only lowercase English letters
 *   - p contains only lowercase English letters and exactly one '*'
 */

class Solution {

    /**
     * Checks if pattern p can match a substring of s.
     * 
     * @param s - The string to search in
     * @param p - The pattern with exactly one '*'
     * @return true if pattern matches a substring, false otherwise
     * 
     * Time Complexity: O(n * m) where n = s.length, m = p.length
     * Space Complexity: O(m) for storing prefix and suffix
     */
    public boolean hasMatch(String s, String p) {
        /*
         * STEP 1: Find the '*' position and split into prefix/suffix
         * 
         * Using substring instead of split (faster, no regex overhead)
         * 
         * "ee*e" → starIdx=2, prefix="ee", suffix="e"
         * "u*"   → starIdx=1, prefix="u", suffix=""
         * "*abc" → starIdx=0, prefix="", suffix="abc"
         */
        int starIdx = p.indexOf('*');
        String prefix = p.substring(0, starIdx);
        String suffix = p.substring(starIdx + 1);

        /*
         * STEP 2: Find the FIRST occurrence of prefix in s
         * 
         * Key insight: We only need to find the FIRST prefix!
         * If suffix exists after it, we're done.
         * 
         * Why? If a later prefix position works, the first one
         * would also work (same suffix, more room for '*' to match)
         */
        int prefixIdx = s.indexOf(prefix);
        
        if (prefixIdx == -1) {
            // Prefix not found at all
            return false;
        }

        /*
         * STEP 3: Check if suffix exists after the prefix
         * 
         * Search for suffix starting at (prefixIdx + prefix.length())
         * This ensures '*' matches everything in between
         * 
         * Example: s = "leetcode", prefix = "ee", suffix = "e"
         *   prefixIdx = 1
         *   Search for "e" starting at index 1 + 2 = 3
         *   Found at index 7 ✓
         */
        int suffixIdx = s.indexOf(suffix, prefixIdx + prefix.length());
        
        return suffixIdx != -1;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.hasMatch("leetcode", "ee*e")); // Output: true
 * System.out.println(sol.hasMatch("car", "c*v"));       // Output: false
 * System.out.println(sol.hasMatch("luck", "u*"));       // Output: true
 * 
 * ═══════════════════════════════════════════════════════════════
 * PATTERN MATCHING VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: s = "leetcode", p = "ee*e"
 * 
 * STEP 1: Split pattern at '*'
 *         
 *         p = "ee*e"
 *              ↓
 *         ┌────┴────┐
 *       prefix    suffix
 *        "ee"      "e"
 * 
 * STEP 2-3: Find prefix, then suffix
 * 
 *         s = "l e e t c o d e"
 *              0 1 2 3 4 5 6 7
 *                ↑───↑       ↑
 *              prefix      suffix
 *              (1-2)        (7)
 *                  └──*──┘
 *                 "tcod" (matched by *)
 * 
 * Result: TRUE - "eetcode" matches "ee*e"
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY FIRST PREFIX IS SUFFICIENT (NO LOOP NEEDED!)
 * ═══════════════════════════════════════════════════════════════
 * 
 * Key insight: If ANY prefix position works, the FIRST one works too!
 * 
 * Proof: Suppose prefix at position i and suffix at j is valid.
 *        If there's a prefix at i' < i, then:
 *        - j >= i + len(prefix) > i' + len(prefix)
 *        - So suffix at j is also valid for prefix at i'!
 * 
 * Example: s = "aabaa", p = "a*a"
 * 
 *   First prefix at index 0
 *   s = "a a b a a"
 *        ↑       
 *      prefix=0
 *   Search suffix "a" from index 1
 *   Found at index 1 ✓
 *   
 *   No need to try other prefix positions!
 * 
 * ═══════════════════════════════════════════════════════════════
 * SPECIAL CASES: EMPTY PREFIX OR SUFFIX
 * ═══════════════════════════════════════════════════════════════
 * 
 * Case 1: Pattern starts with '*' (empty prefix)
 *   p = "*abc" → prefix = "", suffix = "abc"
 *   s.indexOf("") returns 0 (empty string found everywhere!)
 *   Just need to find "abc" anywhere in s
 * 
 * Case 2: Pattern ends with '*' (empty suffix)
 *   p = "abc*" → prefix = "abc", suffix = ""
 *   s.indexOf("") always returns the position we start from
 *   Just need to find "abc" anywhere in s
 * 
 * Case 3: Pattern is just '*'
 *   p = "*" → prefix = "", suffix = ""
 *   Always TRUE! '*' matches entire string
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY split("\\*", -1)?
 * ═══════════════════════════════════════════════════════════════
 * 
 *   The -1 parameter is crucial!
 *   
 *   Without -1 (default behavior):
 *   "u*".split("\\*")    → ["u"]      ← suffix lost!
 *   "*abc".split("\\*")  → ["", "abc"] ← works
 *   
 *   With -1:
 *   "u*".split("\\*", -1)   → ["u", ""]    ← suffix preserved!
 *   "*abc".split("\\*", -1) → ["", "abc"]  ← works
 *   
 *   -1 means: keep ALL parts, including trailing empty strings
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Empty suffix (p = "abc*"):
 *    s = "xyzabc", p = "abc*"
 *    → prefix = "abc", suffix = ""
 *    → Find "abc" at index 3, empty suffix always matches
 *    → TRUE
 * 
 * 2. Empty prefix (p = "*xyz"):
 *    s = "axyzb", p = "*xyz"
 *    → prefix = "", suffix = "xyz"
 *    → Empty prefix found at 0, find "xyz" from 0
 *    → Found at index 1
 *    → TRUE
 * 
 * 3. '*' matches empty string:
 *    s = "abc", p = "ab*c"
 *    → prefix = "ab", suffix = "c"
 *    → Find "ab" at 0, find "c" from index 2
 *    → Found at index 2 (immediately after prefix!)
 *    → TRUE (the '*' matches zero characters)
 * 
 * 4. Overlapping would-be match:
 *    s = "aa", p = "a*a"
 *    → prefix = "a", suffix = "a"
 *    → Find "a" at 0, find "a" from index 1
 *    → Found at index 1
 *    → TRUE (the '*' matches zero characters)
 * 
 * 5. No match possible:
 *    s = "abc", p = "x*y"
 *    → prefix = "x", suffix = "y"
 *    → "x" not found in s
 *    → FALSE
 * 
 * ═══════════════════════════════════════════════════════════════
 * ONE-LINER VERSION
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public boolean hasMatch(String s, String p) {
 *       int i = p.indexOf('*');
 *       return s.indexOf(p.substring(i+1), 
 *                        s.indexOf(p.substring(0,i)) + i) >= 0;
 *   }
 *   
 *   Note: This fails if prefix not found (-1 + i causes issues)
 *   The explicit version with checks is safer and clearer.
 */

