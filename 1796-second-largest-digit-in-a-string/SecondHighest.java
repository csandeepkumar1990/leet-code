/*
 * 1796. Second Largest Digit in a String
 * 
 * Problem:
 * Given an alphanumeric string s, return the second largest numerical digit
 * that appears in s, or -1 if it does not exist.
 * 
 * An alphanumeric string is a string consisting of lowercase English letters
 * and digits.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "dfa12321afd"
 *   Output: 2
 *   Explanation: The digits in s are [1, 2, 3, 2, 1]. 
 *                Unique digits: {1, 2, 3}
 *                Largest = 3, Second Largest = 2
 * 
 * Example 2:
 *   Input: s = "abc1111"
 *   Output: -1
 *   Explanation: The only digit is 1, so there's no second largest.
 * 
 * Example 3:
 *   Input: s = "ck077"
 *   Output: 0
 *   Explanation: Digits are [0, 7, 7]. Unique: {0, 7}
 *                Largest = 7, Second Largest = 0
 * 
 * Constraints:
 *   - 1 <= s.length <= 500
 *   - s consists of only lowercase English letters and/or digits
 * 
 * Approach: HashSet + Sorting
 * 
 * 1. Use a HashSet to store unique digits found in the string
 * 2. Iterate through each character in the string
 * 3. If character is a digit, extract its numeric value and add to set
 * 4. Convert set to list and sort in descending order
 * 5. If we have at least 2 unique digits, return the second one (index 1)
 * 6. Otherwise, return -1
 * 
 * Time Complexity: O(n + k log k)
 *   - O(n) to iterate through the string
 *   - O(k log k) to sort, where k is the number of unique digits (at most 10)
 *   - Since k <= 10, sorting is effectively O(1), so overall O(n)
 * 
 * Space Complexity: O(k) = O(1)
 *   - HashSet stores at most 10 unique digits (0-9)
 *   - List also stores at most 10 elements
 */

import java.util.*;

class Solution {

    public int secondHighest(String s) {

        // HashSet to store unique digits - automatically handles duplicates
        Set<Integer> set = new HashSet<>();

        // Iterate through each character in the string
        for (char c : s.toCharArray()) {

            // Check if the current character is a digit (0-9)
            if (Character.isDigit(c)) {

                // Convert character digit to its integer value
                // '0' -> 0, '1' -> 1, ..., '9' -> 9
                int x = Character.getNumericValue(c);

                // Add to set (duplicates are automatically ignored)
                set.add(x);

            }

        }

        // Convert HashSet to ArrayList for sorting
        List<Integer> list = new ArrayList<>(set);

        // Sort in descending order (largest first)
        // After sorting: [largest, second_largest, third_largest, ...]
        Collections.sort(list, Collections.reverseOrder());

        // If we have at least 2 unique digits, return the second largest
        if (list.size() >= 2) {

            return list.get(1); // Index 1 = second element = second largest

        }

        // Not enough unique digits (0 or 1 digit found)
        return -1;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "dfa12321afd"
 * 
 * Step 1: Iterate through string and collect digits
 *   'd' -> not a digit, skip
 *   'f' -> not a digit, skip
 *   'a' -> not a digit, skip
 *   '1' -> digit! set = {1}
 *   '2' -> digit! set = {1, 2}
 *   '3' -> digit! set = {1, 2, 3}
 *   '2' -> digit! set = {1, 2, 3} (2 already exists, no change)
 *   '1' -> digit! set = {1, 2, 3} (1 already exists, no change)
 *   'a' -> not a digit, skip
 *   'f' -> not a digit, skip
 *   'd' -> not a digit, skip
 * 
 * Step 2: Convert to list and sort descending
 *   list = [1, 2, 3] -> after sorting descending -> [3, 2, 1]
 * 
 * Step 3: Check size and return
 *   list.size() = 3 >= 2 ✓
 *   return list.get(1) = 2
 * 
 * Output: 2
 * 
 * 
 * Alternative Approach (Two Variables - O(n) time, O(1) space):
 * 
 * public int secondHighest(String s) {
 *     int first = -1;   // largest digit
 *     int second = -1;  // second largest digit
 *     
 *     for (char c : s.toCharArray()) {
 *         if (Character.isDigit(c)) {
 *             int digit = c - '0';
 *             
 *             if (digit > first) {
 *                 second = first;  // current largest becomes second
 *                 first = digit;   // new digit becomes largest
 *             } else if (digit > second && digit < first) {
 *                 second = digit;  // new second largest (must be different from first)
 *             }
 *         }
 *     }
 *     
 *     return second;
 * }
 * 
 * This alternative uses only two variables instead of a set and list!
 */

