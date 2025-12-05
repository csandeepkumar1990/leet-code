/**
 * LeetCode 3099: Harshad Number
 * 
 * Problem: An integer divisible by the sum of its digits is called a Harshad number.
 *          Given an integer x, return the sum of digits of x if x is a Harshad number,
 *          otherwise return -1.
 * 
 * Key Insight: A Harshad number is divisible by its digit sum.
 *              For example: 18 → digit sum = 1+8 = 9 → 18 % 9 == 0 ✓
 * 
 * Examples:
 *   Input: x = 18
 *   Output: 9
 *   Explanation: Sum of digits = 1 + 8 = 9. 18 is divisible by 9, so return 9.
 * 
 *   Input: x = 23
 *   Output: -1
 *   Explanation: Sum of digits = 2 + 3 = 5. 23 is not divisible by 5, so return -1.
 * 
 *   Input: x = 1
 *   Output: 1
 *   Explanation: Sum of digits = 1. 1 is divisible by 1, so return 1.
 */

class Solution {

    /**
     * Returns the digit sum if x is a Harshad number, otherwise -1.
     * 
     * @param x - The input integer
     * @return Sum of digits if Harshad number, -1 otherwise
     * 
     * Time Complexity: O(log x) - number of digits in x
     * Space Complexity: O(1) - only using a few variables
     */
    public int sumOfTheDigitsOfHarshadNumber(int x) {
        int original = x;  // Store original value before modifying x
        int digitSum = 0;

        while (x > 0) {
            digitSum += x % 10;  // Get the last digit and add to sum
            x /= 10;             // Remove the last digit
        }

        return (original % digitSum == 0) ? digitSum : -1;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.sumOfTheDigitsOfHarshadNumber(18)); // Output: 9
 * System.out.println(sol.sumOfTheDigitsOfHarshadNumber(23)); // Output: -1
 * System.out.println(sol.sumOfTheDigitsOfHarshadNumber(1));  // Output: 1
 * 
 * Harshad numbers (also called Niven numbers) examples:
 * 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 18, 20, 21, 24, 27, 30, 36, 40, 42...
 */

