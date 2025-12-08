/**
 * LeetCode 3581: Count Odd Letters from Number
 * 
 * Problem: Given a positive integer n, convert each digit to its corresponding
 *          lowercase English word, concatenate all words to form a string s,
 *          then return the count of distinct characters that appear an ODD
 *          number of times in s.
 * 
 * Key Insight: 
 *   1. Map each digit (0-9) to its English word
 *   2. Build the concatenated string
 *   3. Count character frequencies using HashMap
 *   4. Count how many characters have odd frequency
 * 
 * Examples:
 *   Input: n = 41
 *   Output: 5
 *   Explanation:
 *     - 4 → "four", 1 → "one"
 *     - s = "fourone"
 *     - Character frequencies: f=1, o=2, u=1, r=1, n=1, e=1
 *     - Odd frequency chars: f, u, r, n, e (5 characters)
 *     - Answer: 5
 * 
 *   Input: n = 10
 *   Output: 4
 *   Explanation:
 *     - 1 → "one", 0 → "zero"
 *     - s = "onezero"
 *     - Character frequencies: o=2, n=1, e=2, z=1, r=1
 *     - Odd frequency chars: n, z, r (3 characters)
 *     - Answer: 3
 * 
 *   Input: n = 22
 *   Output: 0
 *   Explanation:
 *     - 2 → "two", 2 → "two"
 *     - s = "twotwo"
 *     - Character frequencies: t=2, w=2, o=2
 *     - All even frequencies!
 *     - Answer: 0
 * 
 * Constraints:
 *   - 1 <= n <= 10^9
 */

import java.util.HashMap;
import java.util.Map;

class Solution {

    /*
     * Mapping of digits (0-9) to their English words
     * Index corresponds to the digit value
     */
    private static final String[] DIGIT_WORDS = {
        "zero",   // 0
        "one",    // 1
        "two",    // 2
        "three",  // 3
        "four",   // 4
        "five",   // 5
        "six",    // 6
        "seven",  // 7
        "eight",  // 8
        "nine"    // 9
    };

    /**
     * Returns count of distinct characters with odd frequency.
     * 
     * @param n - The input positive integer
     * @return Number of characters appearing odd number of times
     * 
     * Time Complexity: O(d) where d = number of digits in n
     * Space Complexity: O(1) - at most 26 characters in HashMap
     */
    public int countOddLetters(int n) {
        /*
         * STEP 1: Convert number to string to access each digit
         */
        String numStr = Integer.toString(n);

        /*
         * STEP 2: Build the concatenated word string
         * 
         * For each digit, append its corresponding English word
         * 
         * Example: 41 → "4" + "1" → "four" + "one" → "fourone"
         */
        StringBuilder wordBuilder = new StringBuilder();
        
        for (char digit : numStr.toCharArray()) {
            int digitValue = digit - '0';  // Convert char to int
            wordBuilder.append(DIGIT_WORDS[digitValue]);
        }

        String wordString = wordBuilder.toString();

        /*
         * STEP 3: Count frequency of each character using HashMap
         */
        Map<Character, Integer> charFrequency = new HashMap<>();
        
        for (char c : wordString.toCharArray()) {
            charFrequency.put(c, charFrequency.getOrDefault(c, 0) + 1);
        }

        /*
         * STEP 4: Count characters with ODD frequency
         * 
         * A number is odd if number % 2 != 0
         */
        int oddCount = 0;
        
        for (int frequency : charFrequency.values()) {
            if (frequency % 2 != 0) {
                oddCount++;
            }
        }

        return oddCount;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.countOddLetters(41)); // Output: 5
 * System.out.println(sol.countOddLetters(10)); // Output: 3
 * System.out.println(sol.countOddLetters(22)); // Output: 0
 * 
 * ═══════════════════════════════════════════════════════════════
 * DIGIT TO WORD MAPPING
 * ═══════════════════════════════════════════════════════════════
 * 
 *   ┌────────┬──────────┬────────────────┐
 *   │ Digit  │  Word    │  Letters       │
 *   ├────────┼──────────┼────────────────┤
 *   │   0    │  "zero"  │  z, e, r, o    │
 *   │   1    │  "one"   │  o, n, e       │
 *   │   2    │  "two"   │  t, w, o       │
 *   │   3    │  "three" │  t, h, r, e, e │
 *   │   4    │  "four"  │  f, o, u, r    │
 *   │   5    │  "five"  │  f, i, v, e    │
 *   │   6    │  "six"   │  s, i, x       │
 *   │   7    │  "seven" │  s, e, v, e, n │
 *   │   8    │  "eight" │  e, i, g, h, t │
 *   │   9    │  "nine"  │  n, i, n, e    │
 *   └────────┴──────────┴────────────────┘
 * 
 * ═══════════════════════════════════════════════════════════════
 * STEP-BY-STEP VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: n = 41
 * 
 * STEP 1: Convert to string
 *         41 → "41"
 * 
 * STEP 2: Build word string
 *         '4' → DIGIT_WORDS[4] → "four"
 *         '1' → DIGIT_WORDS[1] → "one"
 *         wordString = "fourone"
 * 
 * STEP 3: Count character frequencies
 *   ┌─────────────────────────────┐
 *   │      FREQUENCY MAP          │
 *   ├──────────┬──────────────────┤
 *   │   Char   │     Count        │
 *   ├──────────┼──────────────────┤
 *   │   'f'    │       1          │ ← ODD
 *   │   'o'    │       2          │ ← even
 *   │   'u'    │       1          │ ← ODD
 *   │   'r'    │       1          │ ← ODD
 *   │   'n'    │       1          │ ← ODD
 *   │   'e'    │       1          │ ← ODD
 *   └──────────┴──────────────────┘
 * 
 * STEP 4: Count odd frequencies
 *         oddCount = 5 (f, u, r, n, e)
 * 
 * Result: 5
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY % 2 != 0 FOR ODD CHECK
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Modulo 2 gives remainder when divided by 2:
 *   
 *   1 % 2 = 1 → ODD (1 != 0) ✓
 *   2 % 2 = 0 → EVEN (0 == 0)
 *   3 % 2 = 1 → ODD (1 != 0) ✓
 *   4 % 2 = 0 → EVEN (0 == 0)
 *   5 % 2 = 1 → ODD (1 != 0) ✓
 *   
 *   Alternative: (frequency & 1) == 1 (bitwise AND)
 * 
 * ═══════════════════════════════════════════════════════════════
 * INTERESTING CASE: REPEATED DIGIT
 * ═══════════════════════════════════════════════════════════════
 * 
 *   n = 22 → "two" + "two" = "twotwo"
 *   
 *   Each letter appears twice:
 *   t=2, w=2, o=2
 *   
 *   All even! → oddCount = 0
 *   
 *   This happens because repeating ANY word an even number
 *   of times makes all its letters have even frequency!
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Single digit:
 *    n = 5 → "five" → f=1, i=1, v=1, e=1 → oddCount = 4
 * 
 * 2. All same digits:
 *    n = 111 → "oneoneone" 
 *    → o=3(odd), n=3(odd), e=3(odd)
 *    → oddCount = 3
 * 
 * 3. Zero included:
 *    n = 10 → "onezero"
 *    → o=2, n=1, e=2, z=1, r=1
 *    → oddCount = 3 (n, z, r)
 * 
 * 4. Large number:
 *    n = 1000000000 (10 digits)
 *    → Works fine, just more iterations
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: BIT MANIPULATION (XOR TOGGLE)
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int countOddLetters(int n) {
 *       String numStr = Integer.toString(n);
 *       int[] parity = new int[26];  // 0 = even, 1 = odd
 *       
 *       for (char digit : numStr.toCharArray()) {
 *           String word = DIGIT_WORDS[digit - '0'];
 *           for (char c : word.toCharArray()) {
 *               parity[c - 'a'] ^= 1;  // Toggle: 0→1, 1→0
 *           }
 *       }
 *       
 *       int oddCount = 0;
 *       for (int p : parity) {
 *           oddCount += p;
 *       }
 *       return oddCount;
 *   }
 *   
 *   XOR trick: x ^= 1 flips between 0 and 1
 *   - If count is even (0), adding 1 makes it odd (1)
 *   - If count is odd (1), adding 1 makes it even (0)
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: USING int[26] ARRAY INSTEAD OF HASHMAP
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int countOddLetters(int n) {
 *       String numStr = Integer.toString(n);
 *       int[] freq = new int[26];
 *       
 *       for (char digit : numStr.toCharArray()) {
 *           String word = DIGIT_WORDS[digit - '0'];
 *           for (char c : word.toCharArray()) {
 *               freq[c - 'a']++;
 *           }
 *       }
 *       
 *       int oddCount = 0;
 *       for (int f : freq) {
 *           if (f % 2 != 0) oddCount++;
 *       }
 *       return oddCount;
 *   }
 */

