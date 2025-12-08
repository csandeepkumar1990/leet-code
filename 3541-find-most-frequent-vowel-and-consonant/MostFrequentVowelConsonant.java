/**
 * LeetCode 3541: Find Most Frequent Vowel and Consonant
 * 
 * Problem: Given a string s consisting of lowercase English letters, return the
 *          sum of the maximum frequency of any vowel and the maximum frequency
 *          of any consonant in s.
 *          
 *          Vowels: a, e, i, o, u
 *          Consonants: all other lowercase letters (b, c, d, f, g, ...)
 * 
 * Key Insight: Use a HashMap to count character frequencies, then find the
 *              maximum frequency among vowels and consonants separately.
 * 
 * Examples:
 *   Input: s = "successes"
 *   Output: 6
 *   Explanation:
 *     - Vowels: 'u' appears 1 time, 'e' appears 2 times → max vowel freq = 2
 *     - Consonants: 's' appears 4 times, 'c' appears 2 times → max consonant freq = 4
 *     - Answer: 2 + 4 = 6
 * 
 *   Input: s = "aeioubcdf"
 *   Output: 2
 *   Explanation:
 *     - Each vowel appears once → max vowel freq = 1
 *     - Each consonant appears once → max consonant freq = 1
 *     - Answer: 1 + 1 = 2
 * 
 * Constraints:
 *   - 1 <= s.length <= 100
 *   - s consists of lowercase English letters only
 *   - s contains at least one vowel and one consonant
 */

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

class Solution {

    /**
     * Returns sum of max vowel frequency and max consonant frequency.
     * 
     * @param s - Input string of lowercase letters
     * @return Sum of maximum frequencies
     * 
     * Time Complexity: O(n) where n = length of string
     * Space Complexity: O(1) - at most 26 entries in HashMap
     */
    public int maxFreqSum(String s) {
        /*
         * STEP 1: Create HashMap to store character frequencies
         * 
         * Key: character
         * Value: frequency count
         */
        Map<Character, Integer> charFrequency = new HashMap<>();

        /*
         * STEP 2: Count frequency of each character
         * 
         * getOrDefault() returns the current count (or 0 if not present)
         * Then we add 1 and put it back
         * 
         * Example: "success"
         *   s → 3, u → 1, c → 2, e → 1
         */
        for (char c : s.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        /*
         * STEP 3: Define vowels set for O(1) lookup
         */
        Set<Character> vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');

        /*
         * STEP 4: Find max frequency for vowels and consonants
         * 
         * Iterate through HashMap entries and categorize each character
         */
        int maxVowelFreq = 0;
        int maxConsonantFreq = 0;

        for (Map.Entry<Character, Integer> entry : charFrequency.entrySet()) {
            char c = entry.getKey();
            int freq = entry.getValue();

            if (vowels.contains(c)) {
                // It's a vowel - update max vowel frequency
                maxVowelFreq = Math.max(maxVowelFreq, freq);
            } else {
                // It's a consonant - update max consonant frequency
                maxConsonantFreq = Math.max(maxConsonantFreq, freq);
            }
        }

        /*
         * STEP 5: Return sum of both max frequencies
         */
        return maxVowelFreq + maxConsonantFreq;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.maxFreqSum("successes")); // Output: 6
 * System.out.println(sol.maxFreqSum("aeioubcdf")); // Output: 2
 * System.out.println(sol.maxFreqSum("aabb"));      // Output: 4
 * 
 * ═══════════════════════════════════════════════════════════════
 * HASHMAP VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: s = "successes"
 * 
 * STEP 1-2: Build frequency HashMap
 * 
 *   ┌─────────────────────────────┐
 *   │      CHARACTER COUNTS       │
 *   ├──────────┬──────────────────┤
 *   │   Key    │     Value        │
 *   ├──────────┼──────────────────┤
 *   │   's'    │       4          │  ← consonant
 *   │   'u'    │       1          │  ← vowel
 *   │   'c'    │       2          │  ← consonant
 *   │   'e'    │       2          │  ← vowel
 *   └──────────┴──────────────────┘
 * 
 * STEP 3-4: Categorize and find max
 * 
 *   VOWELS:                    CONSONANTS:
 *   ┌────────────────┐         ┌────────────────┐
 *   │ 'u' → 1        │         │ 's' → 4  ← MAX │
 *   │ 'e' → 2  ← MAX │         │ 'c' → 2        │
 *   └────────────────┘         └────────────────┘
 *   maxVowelFreq = 2           maxConsonantFreq = 4
 * 
 * STEP 5: Return sum
 *   
 *   Result = 2 + 4 = 6
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY HASHMAP?
 * ═══════════════════════════════════════════════════════════════
 * 
 *   HashMap provides:
 *   - O(1) insertion: put(key, value)
 *   - O(1) lookup: get(key), getOrDefault(key, default)
 *   - O(1) check: containsKey(key)
 *   
 *   Perfect for counting frequencies!
 *   
 *   Alternative: int[26] array
 *   - Slightly faster (no hashing overhead)
 *   - But HashMap is more readable and flexible
 * 
 * ═══════════════════════════════════════════════════════════════
 * getOrDefault() EXPLAINED
 * ═══════════════════════════════════════════════════════════════
 * 
 *   charFrequency.getOrDefault(c, 0) + 1
 *   
 *   This does:
 *   1. Look up character c in the map
 *   2. If found → return its current count
 *   3. If not found → return default value (0)
 *   4. Add 1 to increment the count
 *   
 *   Without getOrDefault:
 *   
 *   if (charFrequency.containsKey(c)) {
 *       charFrequency.put(c, charFrequency.get(c) + 1);
 *   } else {
 *       charFrequency.put(c, 1);
 *   }
 *   
 *   Much more verbose!
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Single vowel, single consonant:
 *    s = "ab" → maxVowel = 1, maxConsonant = 1 → Result = 2
 * 
 * 2. All same vowel:
 *    s = "aaaab" → maxVowel = 4, maxConsonant = 1 → Result = 5
 * 
 * 3. All same consonant:
 *    s = "bbbba" → maxVowel = 1, maxConsonant = 4 → Result = 5
 * 
 * 4. Equal frequencies:
 *    s = "aabb" → maxVowel = 2, maxConsonant = 2 → Result = 4
 * 
 * 5. All five vowels with consonants:
 *    s = "aeiouaeioubbbb" 
 *    → maxVowel = 2 (each vowel appears twice)
 *    → maxConsonant = 4 ('b' appears 4 times)
 *    → Result = 6
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: USING STRING.CONTAINS() FOR VOWELS
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int maxFreqSum(String s) {
 *       Map<Character, Integer> freq = new HashMap<>();
 *       String vowels = "aeiou";
 *       
 *       for (char c : s.toCharArray()) {
 *           freq.put(c, freq.getOrDefault(c, 0) + 1);
 *       }
 *       
 *       int maxV = 0, maxC = 0;
 *       for (var entry : freq.entrySet()) {
 *           int f = entry.getValue();
 *           if (vowels.indexOf(entry.getKey()) >= 0) {
 *               maxV = Math.max(maxV, f);
 *           } else {
 *               maxC = Math.max(maxC, f);
 *           }
 *       }
 *       return maxV + maxC;
 *   }
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: USING int[26] ARRAY
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int maxFreqSum(String s) {
 *       int[] freq = new int[26];
 *       String vowels = "aeiou";
 *       
 *       for (char c : s.toCharArray()) {
 *           freq[c - 'a']++;
 *       }
 *       
 *       int maxV = 0, maxC = 0;
 *       for (int i = 0; i < 26; i++) {
 *           if (freq[i] > 0) {
 *               char c = (char) ('a' + i);
 *               if (vowels.indexOf(c) >= 0) {
 *                   maxV = Math.max(maxV, freq[i]);
 *               } else {
 *                   maxC = Math.max(maxC, freq[i]);
 *               }
 *           }
 *       }
 *       return maxV + maxC;
 *   }
 */

