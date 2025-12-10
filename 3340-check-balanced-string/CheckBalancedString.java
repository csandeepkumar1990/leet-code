// 3340. Check Balanced String
// Determine if a string of digits is balanced.
// A string is balanced if the sum of digits at even indices equals
// the sum of digits at odd indices.
//
// Examples:
// Input: "1234" -> Output: false
//   Even indices (0,2): 1 + 3 = 4
//   Odd indices (1,3): 2 + 4 = 6
//   4 != 6, not balanced
//
// Input: "24123" -> Output: true
//   Even indices (0,2,4): 2 + 1 + 3 = 6
//   Odd indices (1,3): 4 + 2 = 6
//   6 == 6, balanced
//
// Time Complexity: O(n) - single pass through string
// Space Complexity: O(1) - only using two counter variables

class Solution {

    public boolean isBalanced(String num) {

        int sumEven = 0, sumOdd = 0;

        for (int i = 0; i < num.length(); i++) {

            int digit = num.charAt(i) - '0';

            if (i % 2 == 0)

                sumEven += digit;

            else

                sumOdd += digit;

        }

        return sumEven == sumOdd;

    }

}

