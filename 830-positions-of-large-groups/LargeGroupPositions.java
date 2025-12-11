/*
 * 830. Positions of Large Groups
 * 
 * Problem:
 * In a string s of lowercase letters, these letters form consecutive groups
 * of the same character.
 * 
 * For example, "abbxxxxzyy" has groups: "a", "bb", "xxxx", "z", "yy"
 * 
 * A group is identified by an interval [start, end], where start and end
 * denote the start and end indices of the group.
 * 
 * A group is called "large" if it has 3 or more characters.
 * 
 * Return the intervals of every large group sorted in increasing order by
 * start index.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "abbxxxxzzy"
 *   Output: [[3, 6]]
 *   Explanation:
 *     Groups: "a"(0), "bb"(1-2), "xxxx"(3-6), "zz"(7-8), "y"(9)
 *     Large groups (length >= 3): "xxxx" at [3, 6]
 * 
 * Example 2:
 *   Input: s = "abc"
 *   Output: []
 *   Explanation:
 *     Groups: "a"(0), "b"(1), "c"(2)
 *     No group has length >= 3
 * 
 * Example 3:
 *   Input: s = "abcdddeeeeaabbbcd"
 *   Output: [[3, 5], [6, 9], [12, 14]]
 *   Explanation:
 *     Groups: "a", "b", "c", "ddd", "eeee", "aa", "bbb", "c", "d"
 *     Large groups: "ddd"[3,5], "eeee"[6,9], "bbb"[12,14]
 * 
 * Example 4:
 *   Input: s = "aba"
 *   Output: []
 *   Explanation: No consecutive groups of length >= 3
 * 
 * Example 5:
 *   Input: s = "aaa"
 *   Output: [[0, 2]]
 *   Explanation: Entire string is one large group
 * 
 * Constraints:
 *   - 1 <= s.length <= 1000
 *   - s contains lowercase English letters only
 * 
 * Approach: Two Pointers (Sliding Window)
 * 
 * Key Idea: Use two pointers to find each group's boundaries.
 *   - i = start of current group
 *   - j = end of current group + 1 (first different character)
 * 
 * Algorithm:
 * 1. Start with i = 0 (beginning of first group)
 * 2. Move j forward while s[j] == s[i] (same character)
 * 3. Group spans [i, j-1], length = j - i
 * 4. If length >= 3, add [i, j-1] to result
 * 5. Set i = j to start next group
 * 6. Repeat until end of string
 * 
 * Time Complexity: O(n)
 *   - Each character is visited at most twice (once by i, once by j)
 *   - Single pass through the string
 * 
 * Space Complexity: O(1)
 *   - Excluding output, only a few variables used
 *   - Output can have at most n/3 groups
 */

import java.util.*;

class Solution {

    public List<List<Integer>> largeGroupPositions(String s) {

        List<List<Integer>> result = new ArrayList<>();
        int n = s.length();
        int i = 0; // Start pointer (beginning of current group)

        while (i < n) {

            int j = i; // End pointer (will find end of current group)

            // Move j forward while same character repeats
            // j stops at first different character (or end of string)
            while (j < n && s.charAt(j) == s.charAt(i)) {

                j++;

            }

            // Current group spans indices [i, j-1]
            // Group length = j - i
            if (j - i >= 3) { // Large group (3 or more characters)

                // Add interval [start, end] to result
                result.add(Arrays.asList(i, j - 1));

            }

            // Move to next group (j is already at start of next group)
            i = j;

        }

        return result;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "abbxxxxzzy"
 *             0123456789
 * 
 * Initial: result = [], i = 0
 * 
 * Iteration 1: i = 0
 *   j = 0
 *   s[0] = 'a'
 *   Inner loop: j=0, s[0]='a'==s[0] → j=1
 *               j=1, s[1]='b'≠'a' → stop
 *   Group: [0, 0], length = 1 - 0 = 1
 *   1 < 3 → not large, skip
 *   i = 1
 * 
 * Iteration 2: i = 1
 *   j = 1
 *   s[1] = 'b'
 *   Inner loop: j=1, s[1]='b'=='b' → j=2
 *               j=2, s[2]='b'=='b' → j=3
 *               j=3, s[3]='x'≠'b' → stop
 *   Group: [1, 2], length = 3 - 1 = 2
 *   2 < 3 → not large, skip
 *   i = 3
 * 
 * Iteration 3: i = 3
 *   j = 3
 *   s[3] = 'x'
 *   Inner loop: j=3,4,5,6 → 'x'=='x'
 *               j=7, s[7]='z'≠'x' → stop
 *   Group: [3, 6], length = 7 - 3 = 4
 *   4 >= 3 → LARGE! Add [3, 6]
 *   result = [[3, 6]]
 *   i = 7
 * 
 * Iteration 4: i = 7
 *   j = 7
 *   s[7] = 'z'
 *   Inner loop: j=7,8 → 'z'=='z'
 *               j=9, s[9]='y'≠'z' → stop
 *   Group: [7, 8], length = 2
 *   2 < 3 → not large, skip
 *   i = 9
 * 
 * Iteration 5: i = 9
 *   j = 9
 *   s[9] = 'y'
 *   Inner loop: j=9 → 'y'=='y'
 *               j=10, 10 >= n → stop
 *   Group: [9, 9], length = 1
 *   1 < 3 → not large, skip
 *   i = 10
 * 
 * i = 10 >= n → loop ends
 * 
 * Output: [[3, 6]]
 * 
 * 
 * Visualization:
 * 
 * String: a b b x x x x z z y
 * Index:  0 1 2 3 4 5 6 7 8 9
 * 
 * Groups found:
 *   [0,0] "a"    → length 1 (not large)
 *   [1,2] "bb"   → length 2 (not large)
 *   [3,6] "xxxx" → length 4 ✓ LARGE
 *   [7,8] "zz"   → length 2 (not large)
 *   [9,9] "y"    → length 1 (not large)
 * 
 * 
 * Why j - i gives length:
 * 
 *   i           j
 *   ↓           ↓
 *   x x x x z z y
 *   3 4 5 6 7 8 9
 *   
 *   Group: indices 3, 4, 5, 6
 *   Length = j - i = 7 - 3 = 4 ✓
 *   End index = j - 1 = 6 ✓
 * 
 * 
 * Alternative Approach (Single Loop with Counter):
 * 
 * public List<List<Integer>> largeGroupPositions(String s) {
 *     List<List<Integer>> result = new ArrayList<>();
 *     int start = 0;
 *     
 *     for (int i = 1; i <= s.length(); i++) {
 *         // If end of string OR different character
 *         if (i == s.length() || s.charAt(i) != s.charAt(start)) {
 *             if (i - start >= 3) {
 *                 result.add(Arrays.asList(start, i - 1));
 *             }
 *             start = i;  // Start new group
 *         }
 *     }
 *     
 *     return result;
 * }
 * 
 * This uses a single loop but same O(n) complexity. Your two-pointer
 * approach is more intuitive and explicit!
 */

