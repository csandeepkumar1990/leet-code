/*
 * LeetCode 2451: Odd String Difference
 * 
 * Problem:
 * Given an array of equal-length strings, the "difference array" of a string
 * is defined as: diff[i] = s[i+1] - s[i] (character differences).
 * 
 * All strings have the same difference array EXCEPT one "odd" string.
 * Find and return that odd string.
 * 
 * Approach: Compute Difference Arrays + Find Majority
 * 
 * Key Insight:
 * - Convert each string to its difference array (signature)
 * - All strings share the same signature except one
 * - Find the majority signature, then find the string that differs
 * 
 * Difference Array Example:
 * - "abc" → [b-a, c-b] = [1, 1]
 * - "adc" → [d-a, c-d] = [3, -1]
 * 
 * Algorithm:
 * 1. Compute difference arrays for first 3 strings
 * 2. Determine the majority pattern (at least 2 of 3 will match)
 * 3. Find the string whose difference array doesn't match majority
 * 
 * Why Check First 3?
 * - With n ≥ 3 strings and only 1 odd one
 * - At least 2 of the first 3 must have the same (majority) pattern
 * - If d1 == d2 or d1 == d3, then d1 is majority
 * - Otherwise, d2 is majority (d1 is the odd one among first 3)
 * 
 * Time Complexity: O(n × m) where n = number of words, m = word length
 * Space Complexity: O(m) for difference string
 * 
 * Example: words = ["adc", "wzy", "abc"]
 * 
 * Difference arrays:
 * - "adc" → [d-a, c-d] = [3, -1] → "3,-1,"
 * - "wzy" → [z-w, y-z] = [3, -1] → "3,-1,"
 * - "abc" → [b-a, c-b] = [1, 1]  → "1,1,"
 * 
 * d1 = "3,-1,", d2 = "3,-1,", d3 = "1,1,"
 * d1 == d2 ✓ → majority = "3,-1,"
 * 
 * Find odd one:
 * - "adc" → "3,-1," == majority → skip
 * - "wzy" → "3,-1," == majority → skip
 * - "abc" → "1,1," ≠ majority → FOUND!
 * 
 * Return: "abc"
 * 
 * Visual:
 *   word:    a   d   c
 *            │   │   │
 *   diff:    └─3─┘─-1─┘
 *            
 *   word:    a   b   c
 *            │   │   │
 *   diff:    └─1─┘─1─┘
 *   
 *   "abc" has different diff pattern → it's the odd one!
 */

class Solution {

    public String oddString(String[] words) {

        int n = words[0].length();

        // Lambda function to compute difference array as a string
        // Using string representation for easy comparison
        java.util.function.Function<String, String> diff = w -> {

            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < n - 1; i++) {

                // Compute difference between consecutive characters
                sb.append(w.charAt(i + 1) - w.charAt(i)).append(",");

            }

            return sb.toString();

        };

        // Compute difference arrays for first 3 strings
        String d1 = diff.apply(words[0]);

        String d2 = diff.apply(words[1]);

        String d3 = diff.apply(words[2]);

        // Determine the majority pattern
        // At least 2 of the first 3 must have the same pattern
        // If d1 matches d2 OR d1 matches d3, then d1 is the majority
        // Otherwise, d2 must be the majority (since d2 and d3 would match)
        String majority = (d1.equals(d2) || d1.equals(d3)) ? d1 : d2;

        // Find the odd string (the one with different difference array)
        for (String w : words) {

            if (!diff.apply(w).equals(majority)) {

                return w;

            }

        }

        return ""; // Should never reach here given problem constraints

    }

}

