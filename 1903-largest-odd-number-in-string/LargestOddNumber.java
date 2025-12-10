// 1903. Largest Odd Number in String
// Return the largest odd number that is a non-empty substring of num.
//
// Examples:
// Input: num = "52"    -> Output: "5"   (5 is the only odd digit, largest odd substring)
// Input: num = "4206"  -> Output: ""    (no odd digits exist)
// Input: num = "35427" -> Output: "35427" (ends with 7 which is odd, entire string is odd)
//
// Key Insight: A number is odd if its last digit is odd (1, 3, 5, 7, 9).
// To get the largest odd substring, find the rightmost odd digit and take everything before it.
// This works because leading digits make a number larger (e.g., "35" > "5").
//
// Time Complexity: O(n) - single pass from right to left
// Space Complexity: O(1) - only using a few variables

class Solution {

    public String largestOddNumber(String num) {

        // Traverse from right to find the rightmost odd digit
        // We go right-to-left because we want the longest possible prefix ending in odd
        for (int i = num.length() - 1; i >= 0; i--) {

            // Check if current digit is odd (1, 3, 5, 7, 9)
            // Convert char to int by subtracting '0'
            if ((num.charAt(i) - '0') % 2 != 0) {

                // Return substring from start to this odd digit (inclusive)
                // This gives us the largest odd number possible
                return num.substring(0, i + 1);

            }

        }

        // No odd digit found - return empty string
        return "";

    }

}
