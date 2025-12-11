/*
 * 1119. Remove Vowels from a String
 * 
 * Problem:
 * Given a string s, remove the vowels 'a', 'e', 'i', 'o', and 'u' from it,
 * and return the new string.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "leetcodeisacommunityforcoders"
 *   Output: "ltcdscmmntyfrcdrs"
 *   Explanation: All vowels (e, o, i, a, u) are removed from the string.
 * 
 * Example 2:
 *   Input: s = "aeiou"
 *   Output: ""
 *   Explanation: All characters are vowels, so the result is an empty string.
 * 
 * Example 3:
 *   Input: s = "abc"
 *   Output: "bc"
 *   Explanation: Only 'a' is a vowel, so it's removed.
 * 
 * Constraints:
 *   - 1 <= s.length <= 1000
 *   - s consists of only lowercase English letters
 * 
 * Approach: Iterative Replacement
 * 
 * 1. Define all vowel characters (both lowercase and uppercase for robustness)
 * 2. Iterate through each vowel character
 * 3. For each vowel, replace all its occurrences in the string with empty string
 * 4. Return the modified string
 * 
 * Time Complexity: O(n * 10) = O(n)
 *   - We iterate through 10 vowels (5 lowercase + 5 uppercase)
 *   - For each vowel, replace() scans the entire string of length n
 *   - Total: 10 * n operations
 * 
 * Space Complexity: O(n)
 *   - Each replace() creates a new string
 *   - The final string can be at most n characters
 */

class Solution {

    public String removeVowels(String s) {

        // Define all vowels - both lowercase and uppercase for completeness
        // Note: Problem states only lowercase, but handling both is more robust
        String vowels = "aeiouAEIOU";

        // Start with the original string
        String newString = s;

        // Iterate through each vowel character
        for (char c : vowels.toCharArray()) {

            // Replace all occurrences of current vowel with empty string
            // String.valueOf(c) converts char to String for replace() method
            // replace() returns a new string with all occurrences replaced
            newString = newString.replace(String.valueOf(c), "");

        }

        // Return the string with all vowels removed
        return newString;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "hello"
 * 
 * Initial: newString = "hello"
 * 
 * Iteration 1: c = 'a' → newString = "hello".replace("a", "") = "hello" (no change)
 * Iteration 2: c = 'e' → newString = "hello".replace("e", "") = "hllo"  (e removed)
 * Iteration 3: c = 'i' → newString = "hllo".replace("i", "")  = "hllo"  (no change)
 * Iteration 4: c = 'o' → newString = "hllo".replace("o", "")  = "hll"   (o removed)
 * Iteration 5: c = 'u' → newString = "hll".replace("u", "")   = "hll"   (no change)
 * (Remaining uppercase vowels have no effect on lowercase input)
 * 
 * Output: "hll"
 * 
 * Alternative Approach (StringBuilder - more efficient):
 * 
 * public String removeVowels(String s) {
 *     StringBuilder sb = new StringBuilder();
 *     String vowels = "aeiou";
 *     
 *     for (char c : s.toCharArray()) {
 *         if (vowels.indexOf(c) == -1) {  // if c is NOT a vowel
 *             sb.append(c);               // keep it
 *         }
 *     }
 *     
 *     return sb.toString();
 * }
 * 
 * This alternative has O(n) time with only one pass through the string!
 */
