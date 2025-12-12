/*
 * LeetCode 3754: Sum and Multiply
 * 
 * Problem:
 * Given an integer n:
 * 1. Remove all zeros from n to form a new number x
 * 2. Calculate the sum of digits of x (or equivalently, sum of non-zero digits of n)
 * 3. Return x * sum
 * 
 * Approach: Single Pass Digit Processing
 * 
 * Key Insight:
 * - Process each digit of n
 * - Skip zeros (don't include in x or sum)
 * - Build x by appending non-zero digits
 * - Accumulate sum of non-zero digits
 * 
 * Algorithm:
 * 1. Convert n to string for easy digit iteration
 * 2. For each digit (skip if '0'):
 *    - Append to x: x = x * 10 + digit
 *    - Add to sum: sumDigits += digit
 * 3. Return x * sumDigits
 * 
 * Time Complexity: O(d) where d = number of digits in n
 * Space Complexity: O(d) for the string representation
 * 
 * Example 1: n = 102
 * 
 * Process digits:
 * - '1': x = 0*10 + 1 = 1,  sum = 0 + 1 = 1
 * - '0': skip (it's zero)
 * - '2': x = 1*10 + 2 = 12, sum = 1 + 2 = 3
 * 
 * x = 12, sum = 3
 * Result: 12 * 3 = 36
 * 
 * Example 2: n = 1050
 * 
 * Process digits:
 * - '1': x = 1,  sum = 1
 * - '0': skip
 * - '5': x = 15, sum = 6
 * - '0': skip
 * 
 * x = 15, sum = 6
 * Result: 15 * 6 = 90
 * 
 * Visual:
 *   n = 1 0 2 0 3
 *       │ ✗ │ ✗ │
 *       ▼   ▼   ▼
 *   x = 1   2   3  → 123
 *   sum = 1 + 2 + 3 = 6
 *   Result: 123 * 6 = 738
 * 
 * Why use long for x?
 * - After removing zeros, x could still be a large number
 * - The product x * sum could overflow int
 */

class Solution {

    public long sumAndMultiply(int n) {

        // Convert to string for easy digit-by-digit processing
        String s = Integer.toString(n);

        int sumDigits = 0;  // Sum of non-zero digits

        long x = 0;         // Number formed by removing zeros

        for (char c : s.toCharArray()) {

            // Skip zeros - they don't contribute to x or sum
            if (c == '0')

                continue;

            // Convert character to digit value
            int digit = c - '0';

            // Build x by appending this digit
            // x = x * 10 + digit shifts existing digits left and adds new one
            x = x * 10 + digit;

            // Add digit to running sum
            sumDigits += digit;

        }

        // Return the product of x and sum of digits
        return x * sumDigits;

    }

}

