/**
 * LeetCode 408: Valid Word Abbreviation
 * 
 * Problem: A string can be abbreviated by replacing any number of non-adjacent
 *          substrings with their lengths. The abbreviation must NOT have leading zeros.
 *          Given a word and an abbreviation abbr, return whether abbr is valid.
 * 
 * Key Insight: Use two pointers - one for word, one for abbr.
 *              - If letter: must match exactly
 *              - If digit: parse the full number, skip that many chars in word
 *              - Leading zeros are invalid (e.g., "01" is not allowed)
 * 
 * Examples:
 *   Input: word = "internationalization", abbr = "i12iz4n"
 *   Output: true
 *   Explanation: i + (12 chars: nternational) + iz + (4 chars: atio) + n
 *                = internationalization ✓
 * 
 *   Input: word = "apple", abbr = "a2e"
 *   Output: false
 *   Explanation: a + (2 chars: pp) + e = "appe" ≠ "apple"
 * 
 *   Input: word = "substitution", abbr = "s10n"
 *   Output: true
 *   Explanation: s + (10 chars: ubstitutio) + n = substitution ✓
 * 
 *   Input: word = "substitution", abbr = "s010n"
 *   Output: false
 *   Explanation: Leading zero in "010" is not allowed
 * 
 * Constraints:
 *   - 1 <= word.length <= 20
 *   - word consists of only lowercase English letters
 *   - 1 <= abbr.length <= 10
 *   - abbr consists of lowercase letters and digits
 */

class Solution {

    /**
     * Checks if the abbreviation is valid for the given word.
     * 
     * @param word - The original word
     * @param abbr - The abbreviation to validate
     * @return true if abbr is a valid abbreviation of word
     * 
     * Time Complexity: O(n + m) - traverse both strings once
     * Space Complexity: O(1) - only using pointers and a number variable
     */
    public boolean validWordAbbreviation(String word, String abbr) {
        int i = 0; // pointer for word
        int j = 0; // pointer for abbr

        /*
         * Process both strings simultaneously
         * Continue while both pointers are within bounds
         */
        while (i < word.length() && j < abbr.length()) {
            char c = abbr.charAt(j);

            if (Character.isLetter(c)) {
                /*
                 * CASE 1: Current abbr character is a LETTER
                 * 
                 * Letters must match exactly at current positions.
                 * If mismatch, abbreviation is invalid.
                 */
                if (word.charAt(i) != c) {
                    return false;
                }
                i++;
                j++;
            } else {
                /*
                 * CASE 2: Current abbr character is a DIGIT
                 * 
                 * First, check for leading zeros - they're not allowed!
                 * "a01b" is invalid even though "a1b" would be valid.
                 */
                if (c == '0') return false; // leading zeros not allowed

                /*
                 * Parse the complete number (could be multi-digit)
                 * Example: "i12iz4n" → parse "12" as number 12
                 */
                int num = 0;
                while (j < abbr.length() && Character.isDigit(abbr.charAt(j))) {
                    num = num * 10 + (abbr.charAt(j) - '0');
                    j++;
                }

                /*
                 * Skip 'num' characters in word
                 * These characters are represented by the number in abbr
                 */
                i += num;
            }
        }

        /*
         * Final validation: Both pointers must reach the end
         * 
         * - If i < word.length(): word has extra characters not covered
         * - If j < abbr.length(): abbr has extra characters not matched
         * 
         * Both must be exactly at the end for a valid abbreviation.
         */
        return i == word.length() && j == abbr.length();
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.validWordAbbreviation("internationalization", "i12iz4n")); // true
 * System.out.println(sol.validWordAbbreviation("apple", "a2e"));                     // false
 * System.out.println(sol.validWordAbbreviation("substitution", "s10n"));             // true
 * System.out.println(sol.validWordAbbreviation("substitution", "s010n"));            // false
 * 
 * ═══════════════════════════════════════════════════════════════
 * VISUAL BREAKDOWN: word = "internationalization", abbr = "i12iz4n"
 * ═══════════════════════════════════════════════════════════════
 * 
 * word: i n t e r n a t i o n a l i z a t i o n
 *       ↑ └─────────────────────┘ ↑ ↑ └───────┘ ↑
 *       i        (12 chars)       i z  (4 chars) n
 * 
 * abbr: i  12  i z  4  n
 *       ↑  ↑   ↑ ↑  ↑  ↑
 *       │  │   │ │  │  └── matches 'n'
 *       │  │   │ │  └───── skip 4 chars (atio)
 *       │  │   │ └──────── matches 'z'
 *       │  │   └────────── matches 'i'
 *       │  └────────────── skip 12 chars (nternational)
 *       └───────────────── matches 'i'
 * 
 * Result: TRUE ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: word = "apple", abbr = "a2e"
 * ═══════════════════════════════════════════════════════════════
 * 
 * Initial: i = 0, j = 0
 * 
 * j=0: abbr[0] = 'a' (letter)
 *      word[0] = 'a' → match!
 *      i = 1, j = 1
 * 
 * j=1: abbr[1] = '2' (digit)
 *      num = 2
 *      i = 1 + 2 = 3, j = 2
 * 
 * j=2: abbr[2] = 'e' (letter)
 *      word[3] = 'l' → NO MATCH! ✗
 *      return false
 * 
 * Why? "a2e" means: a + (2 chars) + e = a + pp + e = "appe"
 *      But word is "apple" (5 chars), not "appe" (4 chars)
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY LEADING ZEROS ARE INVALID
 * ═══════════════════════════════════════════════════════════════
 * 
 * "a01b" for "ab" is invalid because:
 * - "01" has a leading zero
 * - The valid abbreviation would be "a1b" or just "2"
 * - Leading zeros create ambiguity (is it 01, 001, 0001?)
 * 
 * We catch this by checking: if (c == '0') return false;
 * This triggers only when a number STARTS with 0.
 */

