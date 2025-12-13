/*
 * LeetCode 520: Detect Capital
 * 
 * Problem:
 * A word has correct capital usage if ONE of these conditions holds:
 * 1. All letters are uppercase: "USA"
 * 2. All letters are lowercase: "leetcode"
 * 3. Only first letter is uppercase: "Google"
 * 
 * Return true if the word uses capitals correctly.
 * 
 * Approach: Compare Against Valid Patterns
 * 
 * Key Insight:
 * - Generate all three valid forms of the word
 * - Check if the original matches any of them
 * 
 * Three Valid Forms:
 * 1. word.toUpperCase()          → "GOOGLE"
 * 2. word.toLowerCase()          → "google"
 * 3. First upper + rest lower    → "Google"
 * 
 * Algorithm:
 * 1. Generate lowercase version
 * 2. Generate uppercase version
 * 3. Generate capitalized version (first upper, rest lower)
 * 4. Return true if word matches any of these
 * 
 * Time Complexity: O(n) - string operations are O(n)
 * Space Complexity: O(n) - for the generated strings
 * 
 * Example: word = "FlaG"
 * 
 * Valid forms:
 * - lowercase: "flag"
 * - uppercase: "FLAG"
 * - capitalized: "Flag"
 * 
 * "FlaG" matches none → return false
 * 
 * Example: word = "Google"
 * 
 * Valid forms:
 * - lowercase: "google"
 * - uppercase: "GOOGLE"
 * - capitalized: "Google" ← MATCH!
 * 
 * return true
 * 
 * Visual (Three Valid Patterns):
 * 
 *   Pattern 1: ALL CAPS      Pattern 2: all lower    Pattern 3: Capitalize
 *   
 *   G O O G L E             g o o g l e             G o o g l e
 *   ↑ ↑ ↑ ↑ ↑ ↑             ↑ ↑ ↑ ↑ ↑ ↑             ↑ ↑ ↑ ↑ ↑ ↑
 *   U U U U U U             L L L L L L             U L L L L L
 *   
 *   (U = Uppercase, L = Lowercase)
 * 
 * Invalid Examples:
 *   "FlaG"  → F=U, l=L, a=L, G=U → Mixed, doesn't match any pattern
 *   "gOOGLE" → starts lower, rest mixed → Invalid
 * 
 * Alternative Approach (without creating strings):
 * - Count uppercase letters
 * - Valid if: count == 0 OR count == n OR (count == 1 AND first is upper)
 */

class Solution {

    public boolean detectCapitalUse(String word) {

        // Generate all three valid forms

        // Form 1: all lowercase
        String lowerCase = word.toLowerCase();

        // Form 2: all uppercase
        String upperCase = word.toUpperCase();

        // Form 3: only first letter uppercase, rest lowercase
        // Split into first char (uppercase) + rest (lowercase)
        String capitalized = word.substring(0, 1).toUpperCase() 
                           + word.substring(1).toLowerCase();

        // Check if word matches any valid form
        if (word.equals(lowerCase) || 
            word.equals(upperCase) || 
            word.equals(capitalized)) {

            return true;

        }

        return false;

    }

}

