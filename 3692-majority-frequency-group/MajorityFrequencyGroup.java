/*
 * 3692. Majority Frequency Group
 * 
 * Problem:
 * Given a string s, group all characters by their frequency of occurrence.
 * Return the characters that belong to the "majority" frequency group.
 * 
 * The majority frequency group is:
 * 1. The group with the maximum number of distinct characters
 * 2. If there's a tie, choose the group with the larger frequency value
 * 
 * Return the characters in the majority group as a string (sorted alphabetically).
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "aabbcc"
 *   Output: "abc"
 *   Explanation:
 *     Frequencies: a=2, b=2, c=2
 *     Groups by frequency:
 *       freq 2: [a, b, c] → size 3
 *     Only one group, return "abc"
 * 
 * Example 2:
 *   Input: s = "aabbc"
 *   Output: "ab"
 *   Explanation:
 *     Frequencies: a=2, b=2, c=1
 *     Groups by frequency:
 *       freq 2: [a, b] → size 2
 *       freq 1: [c] → size 1
 *     Largest group (size 2) wins → "ab"
 * 
 * Example 3:
 *   Input: s = "abcdef"
 *   Output: "abcdef"
 *   Explanation:
 *     Frequencies: a=1, b=1, c=1, d=1, e=1, f=1
 *     Groups by frequency:
 *       freq 1: [a, b, c, d, e, f] → size 6
 *     All characters have same frequency → "abcdef"
 * 
 * Example 4:
 *   Input: s = "aabbccd"
 *   Output: "abc"
 *   Explanation:
 *     Frequencies: a=2, b=2, c=2, d=1
 *     Groups by frequency:
 *       freq 2: [a, b, c] → size 3
 *       freq 1: [d] → size 1
 *     Largest group wins → "abc"
 * 
 * Example 5:
 *   Input: s = "aabcd"
 *   Output: "bcd"
 *   Explanation:
 *     Frequencies: a=2, b=1, c=1, d=1
 *     Groups by frequency:
 *       freq 2: [a] → size 1
 *       freq 1: [b, c, d] → size 3
 *     Size 3 > size 1 → "bcd"
 * 
 * Example 6 (Tie-breaker):
 *   Input: s = "aabbcd"
 *   Output: "ab"
 *   Explanation:
 *     Frequencies: a=2, b=2, c=1, d=1
 *     Groups by frequency:
 *       freq 2: [a, b] → size 2
 *       freq 1: [c, d] → size 2
 *     Tie in size! Higher frequency (2 > 1) wins → "ab"
 * 
 * Constraints:
 *   - 1 <= s.length <= 10^5
 *   - s consists of lowercase English letters only
 * 
 * Approach: Frequency Count → Group by Frequency → Find Majority
 * 
 * Algorithm:
 * 1. Count frequency of each character (array of size 26)
 * 2. Group characters by their frequency (Map<freq, List<chars>>)
 * 3. Find the majority group:
 *    - Maximum group size wins
 *    - Tie-breaker: larger frequency value
 * 4. Build result string from winning group's characters
 * 
 * Time Complexity: O(n + 26) = O(n)
 *   - O(n) to count frequencies
 *   - O(26) to build groups and find majority
 * 
 * Space Complexity: O(26) = O(1)
 *   - Frequency array of size 26
 *   - Map with at most 26 entries
 */

import java.util.*;

class Solution {

    public String majorityFrequencyGroup(String s) {

        // Step 1: Count frequency of each character
        int[] freq = new int[26];
        for (char c : s.toCharArray()) {

            freq[c - 'a']++;

        }

        // Step 2: Group characters by their frequency
        // Map: frequency → list of characters with that frequency
        Map<Integer, List<Character>> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {

            if (freq[i] > 0) { // Only include characters that appear in s

                // computeIfAbsent: creates list if key doesn't exist
                // Then adds the character to that list
                map.computeIfAbsent(freq[i], k -> new ArrayList<>())
                    .add((char) (i + 'a'));

            }

        }

        // Step 3: Find the majority frequency group
        // Criteria: max group size, tie-breaker: larger frequency
        int bestFreq = -1;   // Frequency of the winning group
        int bestSize = -1;   // Size of the winning group

        for (Map.Entry<Integer, List<Character>> entry : map.entrySet()) {

            int k = entry.getKey();           // Current frequency
            int size = entry.getValue().size(); // Number of chars with this freq

            // Update best if:
            // - Current group is larger, OR
            // - Same size but higher frequency (tie-breaker)
            if (size > bestSize || (size == bestSize && k > bestFreq)) {

                bestSize = size;
                bestFreq = k;

            }

        }

        // Step 4: Build result string from winning group
        StringBuilder result = new StringBuilder();

        for (char c : map.get(bestFreq)) {

            result.append(c);

        }

        return result.toString();

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "aabbcd"
 * 
 * Step 1: Count frequencies
 *   a: 2, b: 2, c: 1, d: 1
 *   freq = [2, 2, 1, 1, 0, 0, ..., 0]
 *           a  b  c  d
 * 
 * Step 2: Group by frequency
 *   i=0 (a): freq[0]=2 → map.get(2).add('a') → {2: [a]}
 *   i=1 (b): freq[1]=2 → map.get(2).add('b') → {2: [a,b]}
 *   i=2 (c): freq[2]=1 → map.get(1).add('c') → {2: [a,b], 1: [c]}
 *   i=3 (d): freq[3]=1 → map.get(1).add('d') → {2: [a,b], 1: [c,d]}
 * 
 * Step 3: Find majority group
 *   Initial: bestFreq = -1, bestSize = -1
 *   
 *   Entry (2, [a,b]): k=2, size=2
 *     2 > -1 → update: bestSize=2, bestFreq=2
 *   
 *   Entry (1, [c,d]): k=1, size=2
 *     2 > 2? No
 *     2 == 2 && 1 > 2? No (tie-breaker fails)
 *     → no update
 *   
 *   Winner: freq=2, size=2
 * 
 * Step 4: Build result
 *   map.get(2) = [a, b]
 *   result = "ab"
 * 
 * Output: "ab"
 * 
 * 
 * Visualization:
 * 
 * String: "aabbcd"
 * 
 * Character Frequencies:
 *   a ██ (2)
 *   b ██ (2)
 *   c █  (1)
 *   d █  (1)
 * 
 * Grouped by Frequency:
 *   ┌─────────────────────────┐
 *   │ Freq 2: [a, b] size=2  │ ← Winner (higher freq in tie)
 *   ├─────────────────────────┤
 *   │ Freq 1: [c, d] size=2  │
 *   └─────────────────────────┘
 * 
 * 
 * Why computeIfAbsent?
 * 
 * map.computeIfAbsent(freq[i], k -> new ArrayList<>()).add(char)
 * 
 * This is equivalent to:
 *   if (!map.containsKey(freq[i])) {
 *       map.put(freq[i], new ArrayList<>());
 *   }
 *   map.get(freq[i]).add(char);
 * 
 * computeIfAbsent is more concise and idiomatic Java 8+!
 * 
 * 
 * Why iterate i from 0 to 25?
 * 
 * By iterating through the alphabet in order (a→z),
 * characters are automatically added to lists in alphabetical order.
 * This ensures the output is sorted without explicit sorting!
 * 
 * 
 * Tie-Breaker Logic:
 * 
 * Condition: size > bestSize || (size == bestSize && k > bestFreq)
 * 
 *   size > bestSize       → strictly larger group wins
 *   size == bestSize      → tie in group size
 *   k > bestFreq          → higher frequency breaks the tie
 * 
 * Example: Groups {freq2: [a,b], freq1: [c,d]}
 *   Both have size 2 (tie)
 *   freq 2 > freq 1 → freq2 group wins
 */

