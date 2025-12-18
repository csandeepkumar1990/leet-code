/*
 * LeetCode 3042: Count Prefix and Suffix Pairs I
 * 
 * Problem:
 * Given a 0-indexed string array words, return the number of index pairs (i, j)
 * such that i < j and words[i] is both a prefix AND a suffix of words[j].
 * 
 * A string str1 is a prefix of str2 if str2 starts with str1.
 * A string str1 is a suffix of str2 if str2 ends with str1.
 * 
 * Approach: Brute Force with Built-in Methods
 * 
 * Key Insight:
 * - For each pair (i, j) where i < j, check if words[i] is both prefix and suffix
 * - Use startsWith() for prefix check, endsWith() for suffix check
 * - Both conditions must be true simultaneously
 * 
 * Algorithm:
 * 1. Initialize count = 0
 * 2. For each pair (i, j) with i < j:
 *    - Check if words[j] starts with words[i] (prefix)
 *    - Check if words[j] ends with words[i] (suffix)
 *    - If both true, increment count
 * 3. Return count
 * 
 * Time Complexity: O(n² × m) where n = words.length, m = average word length
 * Space Complexity: O(1) - only using a counter
 * 
 * Example 1: words = ["a","aba","ababa","aa"]
 * 
 *   Pairs to check (i < j):
 *   (0,1): "a" prefix of "aba"? ✓  "a" suffix of "aba"? ✓  → count++
 *   (0,2): "a" prefix of "ababa"? ✓  "a" suffix of "ababa"? ✓  → count++
 *   (0,3): "a" prefix of "aa"? ✓  "a" suffix of "aa"? ✓  → count++
 *   (1,2): "aba" prefix of "ababa"? ✓  "aba" suffix of "ababa"? ✓  → count++
 *   (1,3): "aba" prefix of "aa"? ✗  (skip)
 *   (2,3): "ababa" prefix of "aa"? ✗  (skip)
 * 
 *   Result: 4
 * 
 * Example 2: words = ["pa","papa","ma","mama"]
 * 
 *   (0,1): "pa" prefix of "papa"? ✓  "pa" suffix of "papa"? ✓  → count++
 *   (0,2): "pa" prefix of "ma"? ✗  (skip)
 *   (0,3): "pa" prefix of "mama"? ✗  (skip)
 *   (1,2): "papa" prefix of "ma"? ✗  (skip)
 *   (1,3): "papa" prefix of "mama"? ✗  (skip)
 *   (2,3): "ma" prefix of "mama"? ✓  "ma" suffix of "mama"? ✓  → count++
 * 
 *   Result: 2
 * 
 * Visual (Prefix + Suffix Check):
 * 
 *   words[i] = "aba"
 *   words[j] = "ababa"
 * 
 *   Prefix check:     Suffix check:
 *   a b a b a         a b a b a
 *   └─┬─┘             └─┬─┘
 *   a b a             a b a
 *     ✓                 ✓
 * 
 *   Both match → Valid pair!
 */

class Solution {

    public int countPrefixSuffixPairs(String[] words) {

        int count = 0;

        for (int i = 0; i < words.length; i++) {

            for (int j = i + 1; j < words.length; j++) {

                if (words[j].startsWith(words[i]) && words[j].endsWith(words[i])) {

                    count++;

                }

            }

        }

        return count;

    }

}

