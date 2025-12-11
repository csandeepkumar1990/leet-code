/*
 * 383. Ransom Note
 * 
 * Problem:
 * Given two strings ransomNote and magazine, return true if ransomNote can
 * be constructed by using the letters from magazine, and false otherwise.
 * 
 * Each letter in magazine can only be used once in ransomNote.
 * 
 * Think of it like cutting out letters from a magazine to create a ransom note!
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: ransomNote = "a", magazine = "b"
 *   Output: false
 *   Explanation: Magazine has 'b', but we need 'a'. Cannot construct.
 * 
 * Example 2:
 *   Input: ransomNote = "aa", magazine = "ab"
 *   Output: false
 *   Explanation: 
 *     Magazine has: {'a': 1, 'b': 1}
 *     Need: {'a': 2}
 *     Only 1 'a' available, but need 2. Cannot construct.
 * 
 * Example 3:
 *   Input: ransomNote = "aa", magazine = "aab"
 *   Output: true
 *   Explanation:
 *     Magazine has: {'a': 2, 'b': 1}
 *     Need: {'a': 2}
 *     Sufficient 'a's available. Can construct!
 * 
 * Example 4:
 *   Input: ransomNote = "bg", magazine = "efjbdfbdgfjhhaiigfhbaejahgfbbgbjagbddfgdiaigdadhcfcj"
 *   Output: true
 *   Explanation: Both 'b' and 'g' exist in the magazine.
 * 
 * Constraints:
 *   - 1 <= ransomNote.length, magazine.length <= 10^5
 *   - ransomNote and magazine consist of lowercase English letters
 * 
 * Approach: Character Frequency Counting with HashMap
 * 
 * Key Idea: Count available letters in magazine, then "consume" them for
 * each letter needed in ransomNote.
 * 
 * Algorithm:
 * 1. Build frequency map of all characters in magazine
 * 2. For each character in ransomNote:
 *    - If character doesn't exist OR count is 0 → return false
 *    - Otherwise, decrement count (use one letter)
 * 3. If all characters satisfied → return true
 * 
 * Why decrement? Each magazine letter can only be used ONCE. By decrementing,
 * we track how many of each letter remain available.
 * 
 * Time Complexity: O(m + n)
 *   - O(m) to build frequency map from magazine
 *   - O(n) to check ransomNote characters
 *   - Where m = magazine.length, n = ransomNote.length
 * 
 * Space Complexity: O(1)
 *   - HashMap stores at most 26 lowercase letters
 *   - Constant space regardless of input size
 */

import java.util.*;

class Solution {

    public boolean canConstruct(String ransomNote, String magazine) {

        // HashMap to count available characters from magazine
        Map<Character, Integer> freq = new HashMap<>();

        // Step 1: Count all characters in magazine
        for (char c : magazine.toCharArray()) {

            // Increment count (default to 0 if not present)
            freq.put(c, freq.getOrDefault(c, 0) + 1);

        }

        // Step 2: Check if we can construct ransomNote
        for (char c : ransomNote.toCharArray()) {

            // Check if character is available
            if (!freq.containsKey(c) || freq.get(c) == 0) {

                return false; // Character not available or exhausted

            }

            // "Use" one instance of this character (decrement count)
            freq.put(c, freq.get(c) - 1);

        }

        // All characters in ransomNote were satisfied
        return true;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: ransomNote = "aa", magazine = "aab"
 * 
 * Step 1: Build frequency map from magazine "aab"
 *   'a' → freq = {a: 1}
 *   'a' → freq = {a: 2}
 *   'b' → freq = {a: 2, b: 1}
 * 
 * Step 2: Check ransomNote "aa"
 *   
 *   Character 1: 'a'
 *     freq.containsKey('a')? Yes ✓
 *     freq.get('a') = 2 > 0? Yes ✓
 *     Use one 'a': freq = {a: 1, b: 1}
 *   
 *   Character 2: 'a'
 *     freq.containsKey('a')? Yes ✓
 *     freq.get('a') = 1 > 0? Yes ✓
 *     Use one 'a': freq = {a: 0, b: 1}
 * 
 * All characters matched!
 * 
 * Output: true
 * 
 * 
 * Dry Run Example 2 (Failure Case):
 * 
 * Input: ransomNote = "aa", magazine = "ab"
 * 
 * Step 1: Build frequency map from magazine "ab"
 *   freq = {a: 1, b: 1}
 * 
 * Step 2: Check ransomNote "aa"
 *   
 *   Character 1: 'a'
 *     freq.get('a') = 1 > 0? Yes ✓
 *     Use one 'a': freq = {a: 0, b: 1}
 *   
 *   Character 2: 'a'
 *     freq.get('a') = 0 > 0? No ✗
 *     return false!
 * 
 * Output: false (not enough 'a's)
 * 
 * 
 * Visual Analogy:
 * 
 * Magazine: "a a b"     Ransom Note needs: "a a"
 *            ↓ ↓ ↓
 *           [a][a][b]   Cut out letters
 *            ↓ ↓
 *           [a][a]      ✓ Success! Note constructed.
 * 
 * Magazine: "a b"       Ransom Note needs: "a a"
 *            ↓ ↓
 *           [a][b]      Only one 'a' available
 *            ↓
 *           [a][?]      ✗ Fail! Missing second 'a'
 * 
 * 
 * Alternative Approach (Using int array - faster):
 * 
 * public boolean canConstruct(String ransomNote, String magazine) {
 *     int[] count = new int[26];  // For 'a' to 'z'
 *     
 *     // Count magazine characters
 *     for (char c : magazine.toCharArray()) {
 *         count[c - 'a']++;
 *     }
 *     
 *     // Check ransomNote
 *     for (char c : ransomNote.toCharArray()) {
 *         if (count[c - 'a'] == 0) {
 *             return false;
 *         }
 *         count[c - 'a']--;
 *     }
 *     
 *     return true;
 * }
 * 
 * Array indexing is faster than HashMap operations.
 * count[c - 'a'] maps 'a'→0, 'b'→1, ..., 'z'→25
 * 
 * 
 * Early Optimization (Optional):
 * 
 * if (ransomNote.length() > magazine.length()) {
 *     return false;  // Impossible if note is longer than magazine
 * }
 * 
 * This quick check can short-circuit obviously impossible cases!
 */

