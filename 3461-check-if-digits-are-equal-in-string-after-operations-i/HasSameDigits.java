// 3461. Check if Digits Are Equal in String After Operations I
// Repeatedly replace consecutive pairs with (sum % 10) until 2 digits remain.
// Return true if both final digits are equal.
//
// Examples:
// Input: "3902" -> "392" -> "21" -> Output: false (2 != 1)
// Input: "34789" -> "7157" -> "862" -> "48" -> Output: false (4 != 8)
//
// Time Complexity: O(n^2) - each iteration reduces length by 1
// Space Complexity: O(n) - for StringBuilder

class Solution {

    public boolean hasSameDigits(String s) {

        // Continue operations until the string has exactly two digits
        while (s.length() > 2) {

            StringBuilder newS = new StringBuilder();

            // Iterate through consecutive pairs
            for (int i = 0; i < s.length() - 1; i++) {

                int digit1 = s.charAt(i) - '0';

                int digit2 = s.charAt(i + 1) - '0';

                // Sum of adjacent digits mod 10
                int sumMod10 = (digit1 + digit2) % 10;

                newS.append(sumMod10);

            }

            s = newS.toString();

        }

        // Check if the two remaining digits are equal
        return s.charAt(0) == s.charAt(1);

    }

}

