/**
 * LeetCode 3136: Valid Word
 * 
 * Problem: A word is considered valid if:
 *          1. It has a minimum length of 3 characters
 *          2. It contains only digits (0-9) and English letters (uppercase or lowercase)
 *          3. It includes at least one vowel (a, e, i, o, u - case insensitive)
 *          4. It includes at least one consonant (any letter that is not a vowel)
 * 
 * Examples:
 *   Input: word = "234Adas"
 *   Output: true
 *   Explanation: Length >= 3, only letters/digits, has vowel 'a', has consonant 'd'.
 * 
 *   Input: word = "b3"
 *   Output: false
 *   Explanation: Length < 3, fails condition 1.
 * 
 *   Input: word = "a3$e"
 *   Output: false
 *   Explanation: Contains '$' which is not a digit or letter.
 */

class Solution {

    /**
     * Checks if a word is valid based on the four conditions.
     * 
     * @param word - The input string to validate
     * @return true if the word is valid, false otherwise
     * 
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(1) - only using boolean flags
     */
    public boolean isValid(String word) {
        // Condition 1: Minimum of 3 characters
        if (word.length() < 3) {
            return false;
        }

        boolean hasVowel = false;
        boolean hasConsonant = false;

        for (char c : word.toCharArray()) {
            // Condition 2: Contains only digits and English letters
            if (!Character.isLetterOrDigit(c)) {
                return false;
            }

            if (Character.isLetter(c)) {
                char lowerC = Character.toLowerCase(c);
                // Condition 3 & 4: At least one vowel and one consonant
                if (lowerC == 'a' || lowerC == 'e' || lowerC == 'i' || lowerC == 'o' || lowerC == 'u') {
                    hasVowel = true;
                } else {
                    hasConsonant = true;
                }
            }
        }

        // Final check for vowel and consonant presence
        return hasVowel && hasConsonant;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.isValid("234Adas")); // Output: true
 * System.out.println(sol.isValid("b3"));      // Output: false
 * System.out.println(sol.isValid("a3$e"));    // Output: false
 * System.out.println(sol.isValid("aB3"));     // Output: true
 */

