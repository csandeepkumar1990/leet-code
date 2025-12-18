/*
 * LeetCode 2788: Split Strings by Separator
 * 
 * Problem:
 * Given an array of strings words and a character separator, split each string
 * in words by separator. Return an array of strings containing the new strings
 * formed after the splits, excluding empty strings.
 * 
 * Approach: Regex Split with Escaping
 * 
 * Key Insight:
 * - Use String.split() to divide each word by the separator
 * - Must escape the separator with "\\" for regex special characters
 *   (like '.', '|', '$', etc.)
 * - Filter out empty strings that result from consecutive separators
 *   or separators at the start/end
 * 
 * Algorithm:
 * 1. Initialize result list
 * 2. For each word in words:
 *    - Split by separator (escaped for regex safety)
 *    - Add non-empty parts to result
 * 3. Return result list
 * 
 * Time Complexity: O(n × m) where n = number of words, m = average word length
 * Space Complexity: O(n × m) for storing result
 * 
 * Example 1: words = ["one.two.three","four.five","six"], separator = '.'
 * 
 *   "one.two.three" → split by '.' → ["one", "two", "three"]
 *   "four.five"     → split by '.' → ["four", "five"]
 *   "six"           → split by '.' → ["six"]
 * 
 *   Result: ["one","two","three","four","five","six"]
 * 
 * Example 2: words = ["$easy$","$problem$"], separator = '$'
 * 
 *   "$easy$"    → split by '$' → ["", "easy", ""]  → filter → ["easy"]
 *   "$problem$" → split by '$' → ["", "problem", ""] → filter → ["problem"]
 * 
 *   Result: ["easy","problem"]
 * 
 * Why Escape the Separator?
 * 
 *   separator = '.'
 *   
 *   word.split(".")   ← BAD: '.' means "any character" in regex
 *   word.split("\\.")  ← GOOD: "\\." matches literal '.'
 *   
 *   Using "\\" + separator handles all special regex characters:
 *   . | $ ^ * + ? ( ) [ ] { } \
 * 
 * Visual (Empty String Handling):
 * 
 *   Input: "$easy$"
 *          ↓
 *   Split: ["", "easy", ""]
 *           ↓     ↓     ↓
 *   Check: empty  ✓   empty
 *           ↓     ↓     ↓
 *   Add:   skip  add   skip
 *          ↓
 *   Output: ["easy"]
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<String> splitWordsBySeparator(List<String> words, char separator) {

        List<String> result = new ArrayList<>();

        for (String word : words) {

            // Split using regex, escaping special characters if needed
            String[] parts = word.split("\\" + separator);

            for (String part : parts) {

                if (!part.isEmpty()) { // exclude empty strings

                    result.add(part);

                }

            }

        }

        return result;

    }

}

