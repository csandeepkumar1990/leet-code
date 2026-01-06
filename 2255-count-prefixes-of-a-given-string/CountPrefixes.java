/*
 * LeetCode 2255: Count Prefixes of a Given String
 * 
 * Problem:
 * You are given a string array words and a string s, where words[i] and s comprise only
 * of lowercase English letters.
 * 
 * Return the number of strings in words that are a prefix of s.
 * 
 * A prefix of a string is a substring that occurs at the beginning of the string.
 * A substring is a contiguous sequence of characters within a string.
 * 
 * Approach: String Prefix Matching
 * 
 * Key Insight:
 * - A word is a prefix of s if s starts with that word
 * - Use String.startsWith() method to check if s begins with each word
 * - Count how many words satisfy this condition
 * 
 * Algorithm:
 * 1. Initialize count = 0
 * 2. For each word in words:
 *    a. Check if s.startsWith(word)
 *    b. If true, increment count
 * 3. Return count
 * 
 * Time Complexity: O(n * m)
 *   - n = number of words in the array
 *   - m = average length of words
 *   - For each word, startsWith() checks up to word.length() characters
 *   - Total: O(n * m)
 * 
 * Space Complexity: O(1)
 *   - Only using a counter variable
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: words = ["a","b","c","ab","bc","abc"], s = "abc"
 * 
 *   Check each word:
 *     "a":     s.startsWith("a")? Yes → count = 1
 *     "b":     s.startsWith("b")? No  → count = 1
 *     "c":     s.startsWith("c")? No  → count = 1
 *     "ab":    s.startsWith("ab")? Yes → count = 2
 *     "bc":    s.startsWith("bc")? No  → count = 2
 *     "abc":   s.startsWith("abc")? Yes → count = 3
 * 
 *   Result: 3
 * 
 * Visual Representation:
 * 
 *   s = "abc"
 * 
 *   Prefixes of "abc":
 *     - "a"    ✓ (matches words[0])
 *     - "ab"   ✓ (matches words[3])
 *     - "abc"  ✓ (matches words[5])
 * 
 *   Non-prefixes:
 *     - "b"    ✗ (doesn't start with "b")
 *     - "c"    ✗ (doesn't start with "c")
 *     - "bc"   ✗ (doesn't start with "bc")
 * 
 * Another Example:
 * 
 *   Input: words = ["a","a"], s = "aa"
 * 
 *   Check each word:
 *     "a": s.startsWith("a")? Yes → count = 1
 *     "a": s.startsWith("a")? Yes → count = 2
 * 
 *   Result: 2
 * 
 * Edge Cases:
 * 
 * 1. Empty string in words:
 *    words = [""], s = "abc"
 *    s.startsWith("")? Yes (empty string is a prefix of any string)
 *    Result: 1
 * 
 * 2. Empty string s:
 *    words = ["a", "b"], s = ""
 *    s.startsWith("a")? No
 *    s.startsWith("b")? No
 *    Result: 0
 * 
 * 3. Word longer than s:
 *    words = ["abcd"], s = "abc"
 *    s.startsWith("abcd")? No (s is shorter than word)
 *    Result: 0
 * 
 * 4. All words are prefixes:
 *    words = ["a", "ab", "abc"], s = "abc"
 *    All three are prefixes
 *    Result: 3
 * 
 * 5. No words are prefixes:
 *    words = ["x", "y", "z"], s = "abc"
 *    None start with s
 *    Result: 0
 * 
 * 6. Single character:
 *    words = ["a"], s = "a"
 *    s.startsWith("a")? Yes
 *    Result: 1
 * 
 * Why Use startsWith()?
 * 
 *   - Built-in Java method that efficiently checks if a string starts with a prefix
 *   - Handles edge cases correctly (empty string, length checks)
 *   - More readable and less error-prone than manual character comparison
 *   - Optimized implementation in Java's String class
 * 
 * Alternative Approach (Manual Comparison):
 * 
 *   Could manually check characters:
 *   ```java
 *   if (word.length() <= s.length()) {
 *       boolean isPrefix = true;
 *       for (int i = 0; i < word.length(); i++) {
 *           if (word.charAt(i) != s.charAt(i)) {
 *               isPrefix = false;
 *               break;
 *           }
 *       }
 *       if (isPrefix) count++;
 *   }
 *   ```
 *   But startsWith() is cleaner and more efficient.
 * 
 * Prefix vs Substring:
 * 
 *   - Prefix: substring that starts at the beginning of the string
 *   - All prefixes are substrings, but not all substrings are prefixes
 *   - Example: "abc" has prefixes: "", "a", "ab", "abc"
 *   - Example: "abc" has substrings: "", "a", "b", "c", "ab", "bc", "abc"
 *   - This problem specifically asks for prefixes (starting from index 0)
 */

class Solution {
    /**
     * Counts how many strings in words are prefixes of s.
     * 
     * @param words Array of strings to check
     * @param s The target string to check prefixes against
     * @return The number of strings in words that are prefixes of s
     * 
     * Time Complexity: O(n * m) where n is the number of words and m is average word length
     * Space Complexity: O(1)
     */
    public int countPrefixes(String[] words, String s) {
        int count = 0;
        
        // Check each word to see if it's a prefix of s
        for (String word : words) {
            // startsWith() returns true if s begins with word
            // This is exactly what we need to check if word is a prefix of s
            if (s.startsWith(word)) {
                count++;
            }
        }
        
        return count;
    }
}

