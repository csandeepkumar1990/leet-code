/*
 * LeetCode 58: Length of Last Word
 * 
 * Problem:
 * Given a string s consisting of words and spaces, return the length of
 * the last word in the string. A word is a maximal substring consisting
 * of non-space characters only.
 * 
 * Approach: Split and Iterate Backwards
 * 
 * Key Insight:
 * - Split by space creates array of words
 * - Empty strings may exist (from multiple spaces)
 * - Find last non-empty word from the end
 * 
 * Algorithm:
 * 1. Split string by space " "
 * 2. Iterate from end to find first non-empty word
 * 3. Return its length
 * 
 * Time Complexity: O(n) - split processes entire string
 * Space Complexity: O(n) - storing split words
 * 
 * Example 1: s = "Hello World"
 * 
 *   split(" ") → ["Hello", "World"]
 *   Last word: "World"
 *   Length: 5
 * 
 * Example 2: s = "   fly me   to   the moon  "
 * 
 *   split(" ") → ["", "", "", "fly", "me", "", "", "to", "", "", "the", "moon", "", ""]
 *                                                                        ↑
 *   Iterate backwards, skip empty strings, find "moon"
 *   Length: 4
 * 
 * Example 3: s = "luffy is still joyboy"
 * 
 *   split(" ") → ["luffy", "is", "still", "joyboy"]
 *   Last word: "joyboy"
 *   Length: 6
 * 
 * Why Check for Empty Strings?
 * 
 *   "a  b".split(" ") → ["a", "", "b"]
 *                            ↑
 *                       empty string from consecutive spaces
 * 
 *   "  a".split(" ") → ["", "", "a"]
 *   "a  ".split(" ") → ["a", "", ""]
 * 
 * ============================================================
 * ALTERNATIVE APPROACHES
 * ============================================================
 * 
 * Approach 2: Trim + Split (Simpler)
 * 
 *   public int lengthOfLastWord(String s) {
 *       String[] words = s.trim().split(" ");
 *       return words[words.length - 1].length();
 *   }
 * 
 *   - trim() removes leading/trailing spaces
 *   - Last element is guaranteed to be non-empty
 * 
 * Approach 3: Two Pointers (No Split, O(1) Space)
 * 
 *   public int lengthOfLastWord(String s) {
 *       int end = s.length() - 1;
 *       
 *       // Skip trailing spaces
 *       while (end >= 0 && s.charAt(end) == ' ') {
 *           end--;
 *       }
 *       
 *       // Count characters in last word
 *       int start = end;
 *       while (start >= 0 && s.charAt(start) != ' ') {
 *           start--;
 *       }
 *       
 *       return end - start;
 *   }
 * 
 *   Visual for "Hello World  ":
 *   
 *   H e l l o   W o r l d      
 *   0 1 2 3 4 5 6 7 8 9 10 11 12
 *                       ↑     ↑
 *                     start  end (after skipping spaces)
 *   
 *   end = 10, start = 5
 *   Length = 10 - 5 = 5
 * 
 * Approach 4: lastIndexOf (One-liner)
 * 
 *   public int lengthOfLastWord(String s) {
 *       s = s.trim();
 *       return s.length() - s.lastIndexOf(' ') - 1;
 *   }
 * 
 * ============================================================
 * COMPARISON
 * ============================================================
 * 
 * | Approach       | Time | Space | Notes                   |
 * |----------------|------|-------|-------------------------|
 * | Split + Loop   | O(n) | O(n)  | Your solution           |
 * | Trim + Split   | O(n) | O(n)  | Simpler, no empty check |
 * | Two Pointers   | O(n) | O(1)  | Most efficient          |
 * | lastIndexOf    | O(n) | O(1)  | Clever one-liner        |
 */

class Solution {

    public int lengthOfLastWord(String s) {

        String[] words = s.split(" ");

        int n = words.length;

        for (int i = n - 1; i >= 0; i--) {

            if (words[i].length() != 0) {

                return words[i].length();

            }

        }

        return 0;

    }

}

