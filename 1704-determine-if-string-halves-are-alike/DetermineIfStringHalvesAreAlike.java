/*
 * LeetCode 1704: Determine if String Halves Are Alike
 * 
 * Problem:
 * Given a string s of even length, split it into two halves:
 * - First half: s[0] to s[n/2 - 1]
 * - Second half: s[n/2] to s[n - 1]
 * Two halves are "alike" if they have the same number of vowels.
 * Vowels are: a, e, i, o, u (both uppercase and lowercase)
 * 
 * Approach: Two-Pass Vowel Counting
 * 
 * Key Insight:
 * - Simply count vowels in each half separately
 * - Use a Set for O(1) vowel lookup
 * - Compare the two counts
 * 
 * Algorithm:
 * 1. Create a Set containing all vowels (upper and lowercase)
 * 2. Count vowels in the first half (indices 0 to n/2 - 1)
 * 3. Count vowels in the second half (indices n/2 to n - 1)
 * 4. Return true if counts are equal
 * 
 * Time Complexity: O(n) - single pass through the entire string
 * Space Complexity: O(1) - vowel set has fixed size of 10
 * 
 * Example:
 * Input: s = "book"
 * 
 * First half:  "bo" → vowels: 'o' → count1 = 1
 * Second half: "ok" → vowels: 'o' → count2 = 1
 * 
 * count1 == count2 → true
 * 
 * Example:
 * Input: s = "textbook"
 * 
 * First half:  "text" → vowels: 'e' → count1 = 1
 * Second half: "book" → vowels: 'o', 'o' → count2 = 2
 * 
 * count1 != count2 → false
 * 
 * Visual:
 *   s = "AbCdEfGh"
 *        ├───┤├───┤
 *        half1 half2
 *        A,E   (none)
 *         2  !=  0  → false
 */

import java.util.Set;

class Solution {

    public boolean halvesAreAlike(String s) {

        // Set of all vowels (both lowercase and uppercase)
        // Using Set.of() for immutable set with O(1) lookup
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');

        int n = s.length();

        int half = n / 2;

        // Counters for vowels in each half
        int count1 = 0;

        int count2 = 0;

        // Count vowels in the first half [0, half)
        for (int i = 0; i < half; i++) {

            if (vowels.contains(s.charAt(i))) {

                count1++;

            }

        }

        // Count vowels in the second half [half, n)
        for (int i = half; i < n; i++) {

            if (vowels.contains(s.charAt(i))) {

                count2++;

            }

        }

        // Halves are alike if they have equal vowel counts
        return count1 == count2;

    }

}

