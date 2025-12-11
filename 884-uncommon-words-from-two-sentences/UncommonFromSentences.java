/*
 * 884. Uncommon Words from Two Sentences
 * 
 * Problem:
 * A sentence is a string of single-space separated words where each word 
 * consists only of lowercase letters.
 * 
 * A word is uncommon if it appears exactly once in one of the sentences, 
 * and does not appear in the other sentence.
 * 
 * Given two sentences s1 and s2, return a list of all the uncommon words.
 * You may return the answer in any order.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 *   Output: ["sweet", "sour"]
 *   Explanation: 
 *     "this" appears in both → NOT uncommon
 *     "apple" appears in both → NOT uncommon
 *     "is" appears in both → NOT uncommon
 *     "sweet" appears only once (in s1) → uncommon ✓
 *     "sour" appears only once (in s2) → uncommon ✓
 * 
 * Example 2:
 *   Input: s1 = "apple apple", s2 = "banana"
 *   Output: ["banana"]
 *   Explanation:
 *     "apple" appears twice in s1 → NOT uncommon
 *     "banana" appears exactly once → uncommon ✓
 * 
 * Example 3:
 *   Input: s1 = "dog cat", s2 = "cat fish"
 *   Output: ["dog", "fish"]
 *   Explanation:
 *     "cat" appears in both sentences → NOT uncommon
 *     "dog" appears exactly once → uncommon ✓
 *     "fish" appears exactly once → uncommon ✓
 * 
 * Constraints:
 *   - 1 <= s1.length, s2.length <= 200
 *   - s1 and s2 consist of lowercase English letters and spaces
 *   - s1 and s2 do not have leading or trailing spaces
 *   - All the words in s1 and s2 are separated by a single space
 * 
 * Approach: HashMap Frequency Count
 * 
 * Key Insight: A word is uncommon if and only if it appears EXACTLY ONCE
 * across BOTH sentences combined.
 * 
 * Why? If a word appears:
 *   - In both sentences → count >= 2 → NOT uncommon
 *   - Multiple times in one sentence → count >= 2 → NOT uncommon
 *   - Exactly once in one sentence, not in other → count = 1 → uncommon ✓
 * 
 * Algorithm:
 * 1. Concatenate both sentences with a space separator
 * 2. Split into words and count frequency of each word using HashMap
 * 3. Collect all words with frequency exactly 1
 * 4. Return as String array
 * 
 * Time Complexity: O(n + m)
 *   - O(n + m) to split and iterate through all words
 *   - O(n + m) to iterate through HashMap entries
 *   - Where n = length of s1, m = length of s2
 * 
 * Space Complexity: O(n + m)
 *   - HashMap stores all unique words
 *   - Result list stores uncommon words
 */

import java.util.*;

class Solution {

    public String[] uncommonFromSentences(String s1, String s2) {

        // HashMap to store frequency of each word
        Map<String, Integer> freq = new HashMap<>();

        // Count frequencies from both sentences
        // Concatenate s1 and s2 with space, then split by space
        // This treats both sentences as one combined word pool
        for (String word : (s1 + " " + s2).split(" ")) {

            // getOrDefault returns 0 if word not found, then we add 1
            freq.put(word, freq.getOrDefault(word, 0) + 1);

        }

        // Collect words that appear exactly once (these are uncommon)
        List<String> result = new ArrayList<>();

        // Iterate through all entries in the frequency map
        for (Map.Entry<String, Integer> entry : freq.entrySet()) {

            // If frequency is 1, word is uncommon
            if (entry.getValue() == 1) {

                result.add(entry.getKey());

            }

        }

        // Convert List to String array
        // new String[0] is used as type hint; Java auto-sizes the array
        return result.toArray(new String[0]);

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s1 = "this apple is sweet", s2 = "this apple is sour"
 * 
 * Step 1: Concatenate sentences
 *   Combined = "this apple is sweet this apple is sour"
 * 
 * Step 2: Split and count frequencies
 *   "this"   → freq = {this: 1}
 *   "apple"  → freq = {this: 1, apple: 1}
 *   "is"     → freq = {this: 1, apple: 1, is: 1}
 *   "sweet"  → freq = {this: 1, apple: 1, is: 1, sweet: 1}
 *   "this"   → freq = {this: 2, apple: 1, is: 1, sweet: 1}
 *   "apple"  → freq = {this: 2, apple: 2, is: 1, sweet: 1}
 *   "is"     → freq = {this: 2, apple: 2, is: 2, sweet: 1}
 *   "sour"   → freq = {this: 2, apple: 2, is: 2, sweet: 1, sour: 1}
 * 
 * Step 3: Find words with frequency = 1
 *   "this"  → count 2 → skip
 *   "apple" → count 2 → skip
 *   "is"    → count 2 → skip
 *   "sweet" → count 1 → add to result ✓
 *   "sour"  → count 1 → add to result ✓
 * 
 * Step 4: Convert to array
 *   result = ["sweet", "sour"]
 * 
 * Output: ["sweet", "sour"]
 * 
 * 
 * Alternative using Stream API (Java 8+):
 * 
 * public String[] uncommonFromSentences(String s1, String s2) {
 *     Map<String, Long> freq = Stream.of((s1 + " " + s2).split(" "))
 *         .collect(Collectors.groupingBy(w -> w, Collectors.counting()));
 *     
 *     return freq.entrySet().stream()
 *         .filter(e -> e.getValue() == 1)
 *         .map(Map.Entry::getKey)
 *         .toArray(String[]::new);
 * }
 * 
 * This is a more functional approach using Java Streams!
 */

