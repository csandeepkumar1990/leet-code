/*
 * LeetCode 3438: Find Valid Pair
 * 
 * Problem:
 * Given a string s consisting of digits (0-9), find the first valid adjacent pair of digits.
 * 
 * A valid pair (a, b) must satisfy:
 * 1. The two digits are different (a != b)
 * 2. The frequency of digit 'a' in the string equals a (counts[a] == a)
 * 3. The frequency of digit 'b' in the string equals b (counts[b] == b)
 * 
 * Return the first valid pair found as a substring of length 2, or empty string if none exists.
 * 
 * Example:
 *   Input: s = "223344"
 *   Output: "23"
 *   
 *   Explanation:
 *     - Count frequencies: '2' appears 2 times, '3' appears 2 times, '4' appears 2 times
 *     - Check adjacent pairs:
 *       - "22": digits are same (2 == 2) ✗
 *       - "23": digits different (2 != 3), count[2] == 2 ✓, count[3] == 2 ✗ (need 3)
 *       - "33": digits are same (3 == 3) ✗
 *       - "34": digits different (3 != 4), count[3] == 2 ✗, count[4] == 2 ✗
 *       - "44": digits are same (4 == 4) ✗
 *     - Actually, if count[2] == 2 and count[3] == 3, then "23" would be valid
 * 
 * Approach: Frequency Counting + Linear Scan
 * 
 * Key Insight:
 * - First count the frequency of each digit (0-9) in the string
 * - Then scan for adjacent pairs where:
 *   - Digits are different
 *   - Each digit's frequency matches its numeric value
 * - Return the first valid pair found
 * 
 * Algorithm:
 * 1. Count frequency of each digit (0-9) in the string
 * 2. Traverse the string to find the first valid adjacent pair:
 *    a. Check if the two digits are different
 *    b. Check if count[first_digit] == first_digit
 *    c. Check if count[second_digit] == second_digit
 * 3. Return the first valid pair found, or empty string
 * 
 * Time Complexity: O(n)
 *   - Count frequencies: O(n) where n is the length of s
 *   - Scan for valid pair: O(n)
 *   - Total: O(n)
 * 
 * Space Complexity: O(1)
 *   - Frequency array of size 10: O(1)
 *   - No other data structures
 * 
 * Example Walkthrough:
 * 
 *   Input: s = "223344"
 *   
 *   Step 1: Count frequencies
 *     counts[0] = 0, counts[1] = 0, counts[2] = 2, counts[3] = 2,
 *     counts[4] = 2, counts[5-9] = 0
 *   
 *   Step 2: Check adjacent pairs
 *     i=0: "22" → a=2, b=2
 *           a == b ✗ (digits must be different)
 *     
 *     i=1: "23" → a=2, b=3
 *           a != b ✓
 *           counts[2] == 2 ✓
 *           counts[3] == 2 ✗ (need counts[3] == 3)
 *     
 *     i=2: "33" → a=3, b=3
 *           a == b ✗
 *     
 *     i=3: "34" → a=3, b=4
 *           a != b ✓
 *           counts[3] == 2 ✗ (need 3)
 *           counts[4] == 2 ✗ (need 4)
 *     
 *     i=4: "44" → a=4, b=4
 *           a == b ✗
 *   
 *   Result: "" (no valid pair)
 * 
 *   Input: s = "2233"
 *   
 *   Step 1: Count frequencies
 *     counts[2] = 2, counts[3] = 2
 *   
 *   Step 2: Check adjacent pairs
 *     i=0: "22" → a=2, b=2, a == b ✗
 *     i=1: "23" → a=2, b=3
 *           a != b ✓
 *           counts[2] == 2 ✓
 *           counts[3] == 2 ✗ (need 3)
 *     i=2: "33" → a=3, b=3, a == b ✗
 *   
 *   Result: "" (no valid pair)
 * 
 *   Input: s = "22334455"
 *   (Assuming this creates valid frequencies)
 * 
 * Visual Representation:
 * 
 *   s = "223344"
 *   Positions: 0 1 2 3 4 5
 *             "2 2 3 3 4 4"
 *   
 *   Frequency count:
 *     '0': 0 times
 *     '1': 0 times
 *     '2': 2 times ✓ (count == digit value)
 *     '3': 2 times ✗ (count != digit value, need 3)
 *     '4': 2 times ✗ (count != digit value, need 4)
 *   
 *   Adjacent pairs to check:
 *     [0,1]: "22" → same digit ✗
 *     [1,2]: "23" → different ✓, count[2]==2 ✓, count[3]==2 ✗
 *     [2,3]: "33" → same digit ✗
 *     [3,4]: "34" → different ✓, count[3]==2 ✗, count[4]==2 ✗
 *     [4,5]: "44" → same digit ✗
 *   
 *   No valid pair found → return ""
 * 
 * Understanding the Conditions:
 * 
 *   Condition 1: Digits must be different (a != b)
 *     - Prevents pairs like "22", "33", "44"
 *     - Ensures we have two distinct digits
 *   
 *   Condition 2: counts[a] == a
 *     - The digit 'a' must appear exactly 'a' times in the string
 *     - Example: digit '3' must appear exactly 3 times
 *   
 *   Condition 3: counts[b] == b
 *     - The digit 'b' must appear exactly 'b' times in the string
 *     - Example: digit '5' must appear exactly 5 times
 *   
 *   All three conditions must be satisfied for a valid pair.
 * 
 * Edge Cases:
 * 
 *   1. Empty string:
 *      s = ""
 *      Result: "" (no pairs possible)
 *   
 *   2. Single character:
 *      s = "2"
 *      Result: "" (need at least 2 characters for a pair)
 *   
 *   3. All same digits:
 *      s = "2222"
 *      Result: "" (all pairs have same digits)
 *   
 *   4. No valid pair:
 *      s = "123456"
 *      Result: "" (frequencies don't match digit values)
 *   
 *   5. Valid pair at the end:
 *      s = "112233"
 *      (If counts[2]==2 and counts[3]==3)
 *      Check "23" at position [2,3]
 *      Result: "23"
 * 
 * Important Notes:
 * 
 *   1. We return the FIRST valid pair found (leftmost)
 *   2. The pair must be adjacent in the original string
 *   3. Frequency is counted for the ENTIRE string, not just the pair
 *   4. Digit '0' can appear 0 times (counts[0] == 0), but can't form a valid pair
 *      since we need at least 2 characters and '0' appearing 0 times means it's not in the string
 *   5. Digit '1' must appear exactly once, '2' exactly twice, etc.
 */

class Solution {
    /**
     * Finds the first valid adjacent pair of digits in the string.
     * 
     * @param s The input string consisting of digits (0-9)
     * @return The first valid pair as a substring of length 2, or empty string if none exists
     * 
     * Time Complexity: O(n) where n is the length of s
     * Space Complexity: O(1)
     */
    public String findValidPair(String s) {
        // Step 1: Count frequency of each digit (0-9)
        int[] counts = new int[10];
        for (char c : s.toCharArray()) {
            counts[c - '0']++;
        }
        
        // Step 2: Traverse string to find the first valid adjacent pair
        for (int i = 0; i < s.length() - 1; i++) {
            char firstChar = s.charAt(i);
            char secondChar = s.charAt(i + 1);
            
            int a = firstChar - '0';  // First digit
            int b = secondChar - '0';  // Second digit
            
            // Condition 1: Digits must be different
            // Condition 2: Frequencies must match their numeric values
            if (a != b && counts[a] == a && counts[b] == b) {
                return s.substring(i, i + 2);
            }
        }
        
        return "";
    }
}

