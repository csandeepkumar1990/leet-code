/**
 * LeetCode 434: Number of Segments in a String
 * 
 * Problem: Given a string s, return the number of segments in the string.
 *          A segment is defined as a contiguous sequence of non-space characters.
 * 
 * Key Insight: Split by space and count non-empty tokens.
 *              Multiple consecutive spaces create empty strings after split,
 *              so we must filter them out.
 * 
 * Examples:
 *   Input: s = "Hello, my name is John"
 *   Output: 5
 *   Explanation: Segments are ["Hello,", "my", "name", "is", "John"]
 * 
 *   Input: s = "Hello"
 *   Output: 1
 * 
 *   Input: s = ""
 *   Output: 0
 * 
 *   Input: s = "   "
 *   Output: 0
 *   Explanation: Only spaces, no segments
 * 
 *   Input: s = "  foo    bar  "
 *   Output: 2
 *   Explanation: Leading/trailing/multiple spaces handled correctly
 * 
 * Constraints:
 *   - 0 <= s.length <= 300
 *   - s consists of lowercase/uppercase letters, digits, or spaces ' '
 */

class Solution {

    /**
     * Counts the number of segments (non-space character sequences) in a string.
     * 
     * @param s - The input string
     * @return The number of segments
     * 
     * Time Complexity: O(n) - split and iteration both traverse the string
     * Space Complexity: O(n) - storing the split array
     */
    public int countSegments(String s) {
        /*
         * Initialize segment counter
         */
        int segmentCount = 0;

        /*
         * Split the string by space character
         * 
         * Note: split(" ") splits on single spaces.
         * Consecutive spaces create empty strings in the array!
         * 
         * Example: "  foo    bar  ".split(" ")
         * Result:  ["", "", "foo", "", "", "", "bar", "", ""]
         *           ↑  ↑              ↑  ↑  ↑        ↑  ↑
         *           Empty strings from consecutive/leading/trailing spaces
         */
        String[] arr = s.split(" ");

        /*
         * Count only non-empty tokens
         * 
         * Empty strings come from:
         * - Leading spaces (before first word)
         * - Trailing spaces (after last word)
         * - Multiple consecutive spaces between words
         * 
         * We skip these by checking !token.isEmpty()
         */
        for (String token : arr) {
            if (!token.isEmpty()) {
                segmentCount++;
            }
        }

        return segmentCount;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.countSegments("Hello, my name is John")); // Output: 5
 * System.out.println(sol.countSegments("Hello"));                  // Output: 1
 * System.out.println(sol.countSegments(""));                       // Output: 0
 * System.out.println(sol.countSegments("   "));                    // Output: 0
 * System.out.println(sol.countSegments("  foo    bar  "));         // Output: 2
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY CHECK FOR EMPTY STRINGS?
 * ═══════════════════════════════════════════════════════════════
 * 
 * split(" ") behavior with consecutive spaces:
 * 
 * Input:  "  foo    bar  "
 *          ││   ││││   ││
 *          └┴───┴┴┴┴───┴┴── spaces
 * 
 * After split(" "):
 *   Index:  0    1    2    3    4    5    6    7    8
 *   Value: ["", "", "foo", "", "", "", "bar", "", ""]
 *           ↑   ↑          ↑   ↑   ↑          ↑   ↑
 *           └───┴──────────┴───┴───┴──────────┴───┴── empty strings
 * 
 * Only "foo" and "bar" are actual segments!
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: s = "Hello, my name is John"
 * ═══════════════════════════════════════════════════════════════
 * 
 * Step 1: Split by space
 *   arr = ["Hello,", "my", "name", "is", "John"]
 *   (no consecutive spaces, so no empty strings)
 * 
 * Step 2: Count non-empty tokens
 *   "Hello," → not empty → count = 1
 *   "my"     → not empty → count = 2
 *   "name"   → not empty → count = 3
 *   "is"     → not empty → count = 4
 *   "John"   → not empty → count = 5
 * 
 * Answer: 5 ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: Using split with regex "\\s+"
 * ═══════════════════════════════════════════════════════════════
 * 
 * A cleaner approach would be:
 *   String[] arr = s.trim().split("\\s+");
 *   return s.trim().isEmpty() ? 0 : arr.length;
 * 
 * \\s+ matches one or more whitespace characters,
 * avoiding empty strings from consecutive spaces.
 * But the current solution works correctly too!
 */

