/*
 * LeetCode 3146: Permutation Difference Between Two Strings
 * 
 * Problem:
 * Given two strings s and t (both are permutations of each other),
 * find the "permutation difference" - the sum of absolute differences
 * between the index of each character in s and its index in t.
 * 
 * Formula: Σ |index_in_s(c) - index_in_t(c)| for all characters c
 * 
 * Approach: HashMap for Index Lookup
 * 
 * Key Insight:
 * - Store index of each character in s in a HashMap
 * - For each character in t at position i, find its position in s
 * - Add the absolute difference to result
 * 
 * Algorithm:
 * 1. Build a HashMap: character → index in s
 * 2. For each position i in t:
 *    - Get the character c at position i
 *    - Look up its position in s from the map
 *    - Add |position_in_s - i| to result
 * 3. Return result
 * 
 * Time Complexity: O(n) where n = length of strings
 * Space Complexity: O(n) for the HashMap (or O(26) since lowercase letters)
 * 
 * Example: s = "abc", t = "bac"
 * 
 * Step 1: Build index map from s
 *   indexMap = {'a': 0, 'b': 1, 'c': 2}
 * 
 * Step 2: Process t = "bac"
 *   i=0, c='b': index in s = 1, diff = |1 - 0| = 1
 *   i=1, c='a': index in s = 0, diff = |0 - 1| = 1
 *   i=2, c='c': index in s = 2, diff = |2 - 2| = 0
 * 
 * Total: 1 + 1 + 0 = 2
 * 
 * Visual:
 *   s:  a   b   c
 *       0   1   2   ← indices in s
 *       │   │   │
 *       │   ↓   │
 *   t:  b   a   c
 *       0   1   2   ← indices in t
 * 
 *   'a': index 0 in s, index 1 in t → |0-1| = 1
 *   'b': index 1 in s, index 0 in t → |1-0| = 1
 *   'c': index 2 in s, index 2 in t → |2-2| = 0
 *                                      ─────
 *                                Total:   2
 * 
 * Example: s = "abcde", t = "edbac"
 * 
 *   s indices: a=0, b=1, c=2, d=3, e=4
 *   
 *   t = "edbac"
 *        01234
 *   
 *   'e': |4-0| = 4
 *   'd': |3-1| = 2
 *   'b': |1-2| = 1
 *   'a': |0-3| = 3
 *   'c': |2-4| = 2
 *              ──
 *        Total: 12
 * 
 * Note: Since both strings are permutations, they contain
 * the same characters, just in different positions.
 */

import java.util.HashMap;
import java.util.Map;

class Solution {

    public int findPermutationDifference(String s, String t) {

        // Map to store index of each character in s
        Map<Character, Integer> indexMap = new HashMap<>();

        // Build the index map: character → its index in s
        for (int i = 0; i < s.length(); i++) {

            indexMap.put(s.charAt(i), i);

        }

        int result = 0;

        // For each character in t, find its position difference
        for (int i = 0; i < t.length(); i++) {

            char c = t.charAt(i);

            // Get index of this character in s
            int indexInS = indexMap.get(c);

            // Add absolute difference of positions
            result += Math.abs(indexInS - i);

        }

        return result;

    }

}

