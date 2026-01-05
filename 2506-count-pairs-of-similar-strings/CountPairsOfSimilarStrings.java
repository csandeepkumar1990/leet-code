/*
 * LeetCode 2506: Count Pairs Of Similar Strings
 * 
 * Problem:
 * You are given a 0-indexed string array words.
 * 
 * Two strings are similar if they consist of the same characters.
 * - For example, "abca" and "cba" are similar since both consist of characters 'a', 'b', and 'c'.
 * - However, "abacba" and "bcfd" are not similar since they do not consist of the same characters.
 * 
 * Return the number of pairs (i, j) such that 0 <= i < j < words.length and the two strings
 * words[i] and words[j] are similar.
 * 
 * Approach: Bitmasking with HashMap
 * 
 * Key Insight:
 * - Two strings are similar if they have the same set of characters (ignoring order and frequency)
 * - Use bitmask to represent character set: each bit represents a letter (a-z)
 * - Count pairs of words with the same bitmask
 * - Use HashMap to track frequency of each bitmask
 * 
 * Algorithm:
 * 1. For each word, create a bitmask representing its character set
 * 2. For each bitmask, count how many words have that bitmask
 * 3. Count pairs: if n words have same bitmask, number of pairs = n * (n-1) / 2
 * 4. Use incremental counting: when we see a word with mask m, add current count to result
 * 
 * Bitmask Representation:
 * 
 *   - Use integer to represent set of characters
 *   - Each bit position represents a letter: bit 0 = 'a', bit 1 = 'b', ..., bit 25 = 'z'
 *   - Set bit if character exists in word
 *   - Example: "abc" → mask = 0b111 (bits 0, 1, 2 set)
 *   - Example: "aac" → mask = 0b101 (bits 0, 2 set, same as "ac")
 * 
 * Time Complexity: O(n * m), where:
 *   - n = number of words
 *   - m = average length of words
 *   - For each word, iterate through characters to build bitmask
 * 
 * Space Complexity: O(n)
 *   - HashMap stores at most n different bitmasks
 * 
 * Example:
 * 
 *   Input: words = ["aba", "aabb", "abcd", "bac", "aabc"]
 * 
 *   Word 0: "aba"
 *   - Characters: 'a', 'b', 'a'
 *   - Bitmask: 1 << ('a' - 'a') | 1 << ('b' - 'a') = 1 << 0 | 1 << 1 = 0b11 = 3
 *   - freq: {3: 0} → result += 0 = 0
 *   - Update: freq: {3: 1}
 * 
 *   Word 1: "aabb"
 *   - Characters: 'a', 'a', 'b', 'b'
 *   - Bitmask: 1 << 0 | 1 << 1 = 0b11 = 3
 *   - freq: {3: 1} → result += 1 = 1 (pair with "aba")
 *   - Update: freq: {3: 2}
 * 
 *   Word 2: "abcd"
 *   - Characters: 'a', 'b', 'c', 'd'
 *   - Bitmask: 1 << 0 | 1 << 1 | 1 << 2 | 1 << 3 = 0b1111 = 15
 *   - freq: {3: 2, 15: 0} → result += 0 = 1
 *   - Update: freq: {3: 2, 15: 1}
 * 
 *   Word 3: "bac"
 *   - Characters: 'b', 'a', 'c'
 *   - Bitmask: 1 << 1 | 1 << 0 | 1 << 2 = 0b111 = 7
 *   - freq: {3: 2, 15: 1, 7: 0} → result += 0 = 1
 *   - Update: freq: {3: 2, 15: 1, 7: 1}
 * 
 *   Word 4: "aabc"
 *   - Characters: 'a', 'a', 'b', 'c'
 *   - Bitmask: 1 << 0 | 1 << 1 | 1 << 2 = 0b111 = 7
 *   - freq: {3: 2, 15: 1, 7: 1} → result += 1 = 2 (pair with "bac")
 *   - Update: freq: {3: 2, 15: 1, 7: 2}
 * 
 *   Pairs:
 *   - ("aba", "aabb"): same mask 3 ✓
 *   - ("bac", "aabc"): same mask 7 ✓
 * 
 *   Result: 2
 * 
 * Why Bitmask?
 * 
 *   - Efficient representation of character set
 *   - O(1) comparison: same mask = same character set
 *   - Space efficient: one integer per word
 *   - Fast to compute: O(m) where m is word length
 * 
 * Bitmask Operations:
 * 
 *   Setting bit for character 'c':
 *   mask |= 1 << (ch - 'a')
 *   - ch - 'a' gives position: 'a'→0, 'b'→1, 'c'→2, ..., 'z'→25
 *   - 1 << position sets that bit
 *   - |= combines with existing bits
 * 
 *   Example: "abc"
 *   - 'a': mask |= 1 << 0 → mask = 0b1
 *   - 'b': mask |= 1 << 1 → mask = 0b11
 *   - 'c': mask |= 1 << 2 → mask = 0b111
 * 
 * Why Incremental Counting?
 * 
 *   - When we see a word with mask m, there are already freq[m] words with same mask
 *   - Each of those words can pair with current word
 *   - So we add freq[m] to result
 *   - Then increment freq[m] for future pairs
 *   - This counts all pairs without double counting
 * 
 * Incremental vs Final Counting:
 * 
 *   Incremental (used here):
 *   - Count pairs as we process words
 *   - result += freq.getOrDefault(mask, 0)
 *   - Then update freq
 *   - Simpler and more efficient
 * 
 *   Final counting:
 *   - First count all frequencies
 *   - Then for each mask with count n, add n * (n-1) / 2
 *   - More complex but produces same result
 * 
 * Edge Cases:
 * 
 *   - Empty words: result = 0
 *   - Single word: result = 0 (no pairs)
 *   - All words different character sets: result = 0
 *   - All words same character set: result = n * (n-1) / 2
 *   - Words with duplicate characters: handled correctly (bitmask ignores frequency)
 * 
 * Character Set Comparison:
 * 
 *   - "aba" and "aabb": both have {'a', 'b'} → same mask → similar ✓
 *   - "abc" and "abcd": different sets → different masks → not similar ✗
 *   - "a" and "aa": both have {'a'} → same mask → similar ✓
 *   - "ab" and "ba": both have {'a', 'b'} → same mask → similar ✓
 * 
 * Why | (OR) Operation?
 * 
 *   - OR combines bits: 0b01 | 0b10 = 0b11
 *   - Setting multiple bits: mask |= bit sets the bit without clearing others
 *   - Example: mask = 0b01, then mask |= 0b10 → mask = 0b11
 *   - This builds the character set incrementally
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    public int similarPairs(String[] words) {
        Map<Integer, Integer> freq = new HashMap<>();
        int result = 0;

        for (String word : words) {
            // Create bitmask
            int mask = 0;
            for (char ch : word.toCharArray()) {
                mask |= 1 << (ch - 'a');
            }

            // Count pairs with same mask
            result += freq.getOrDefault(mask, 0);

            // Update frequency
            freq.put(mask, freq.getOrDefault(mask, 0) + 1);
        }

        return result;
    }
}

