// 3330. Find the Original Typed String I
// Count the number of possible original strings that could have been typed.
// Alice may have held a key too long, causing repeated characters.
//
// Examples:
// Input: "abbcccc" -> Output: 5
//   Possible originals: "abbcccc", "abbccc", "abbcc", "abccc", "abcc"
//   (each consecutive duplicate can be reduced by 1)
//
// Input: "abcd" -> Output: 1
//   No consecutive duplicates, only one possible original: "abcd"
//
// Input: "aaaa" -> Output: 4
//   Possible originals: "aaaa", "aaa", "aa", "a"
//
// Key Insight: Each pair of consecutive identical characters represents
// one potential "mistake" (extra keypress). Count all such pairs + 1.
//
// Time Complexity: O(n) - single pass through string
// Space Complexity: O(1) - only using a counter variable

class Solution {

    public int possibleStringCount(String word) {

        int ans = 1; // Base case: the original typed string (no mistakes)

        // Count consecutive duplicate pairs
        for (int i = 1; i < word.length(); i++) {

            // If current char equals previous, it could be an extra keypress
            if (word.charAt(i) == word.charAt(i - 1)) {

                ans++;

            }

        }

        return ans;

    }

}

