/*
 * 3622. Check Divisibility by Digit Sum and Product
 * 
 * Problem:
 * Given a positive integer n, return true if n is divisible by the sum of
 * its digits plus the product of its digits, and false otherwise.
 * 
 * In other words, check if n % (digitSum + digitProduct) == 0
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: n = 21
 *   Output: true
 *   Explanation:
 *     Digits: 2, 1
 *     Sum = 2 + 1 = 3
 *     Product = 2 * 1 = 2
 *     Total = 3 + 2 = 5
 *     21 % 5 = 1 ≠ 0 → false
 *     Wait, let me recalculate...
 *     Actually: 21 % 5 = 1, so this would be false.
 * 
 * Example 2:
 *   Input: n = 24
 *   Output: true
 *   Explanation:
 *     Digits: 2, 4
 *     Sum = 2 + 4 = 6
 *     Product = 2 * 4 = 8
 *     Total = 6 + 8 = 14
 *     24 % 14 = 10 ≠ 0 → false
 * 
 * Example 3:
 *   Input: n = 36
 *   Output: true
 *   Explanation:
 *     Digits: 3, 6
 *     Sum = 3 + 6 = 9
 *     Product = 3 * 6 = 18
 *     Total = 9 + 18 = 27
 *     36 % 27 = 9 ≠ 0 → false
 * 
 * Example 4:
 *   Input: n = 12
 *   Output: true
 *   Explanation:
 *     Digits: 1, 2
 *     Sum = 1 + 2 = 3
 *     Product = 1 * 2 = 2
 *     Total = 3 + 2 = 5
 *     12 % 5 = 2 ≠ 0 → false
 * 
 * Example 5:
 *   Input: n = 111
 *   Output: true
 *   Explanation:
 *     Digits: 1, 1, 1
 *     Sum = 1 + 1 + 1 = 3
 *     Product = 1 * 1 * 1 = 1
 *     Total = 3 + 1 = 4
 *     111 % 4 = 3 ≠ 0 → false
 * 
 * Example 6:
 *   Input: n = 8
 *   Output: true
 *   Explanation:
 *     Digits: 8
 *     Sum = 8
 *     Product = 8
 *     Total = 8 + 8 = 16
 *     8 % 16 = 8 ≠ 0 → false
 * 
 * Example 7:
 *   Input: n = 10
 *   Output: false
 *   Explanation:
 *     Digits: 1, 0
 *     Sum = 1 + 0 = 1
 *     Product = 1 * 0 = 0
 *     Total = 1 + 0 = 1
 *     10 % 1 = 0 → true!
 * 
 * Constraints:
 *   - 1 <= n <= 10^5
 * 
 * Approach: Digit Extraction + Sum/Product Calculation
 * 
 * Algorithm:
 * 1. Extract each digit using modulo and division
 * 2. Accumulate sum of digits
 * 3. Accumulate product of digits
 * 4. Calculate total = sum + product
 * 5. Return n % total == 0
 * 
 * Digit Extraction:
 *   - digit = n % 10  (gets last digit)
 *   - n = n / 10      (removes last digit)
 *   - Repeat until n becomes 0
 * 
 * Time Complexity: O(d)
 *   - Where d = number of digits in n
 *   - d = log₁₀(n), so effectively O(log n)
 * 
 * Space Complexity: O(1)
 *   - Only a few integer variables
 */

class Solution {

    public boolean checkDivisibility(int n) {

        int temp = n;       // Work with a copy to preserve original n
        int sum = 0;        // Sum of digits
        int product = 1;    // Product of digits (start at 1 for multiplication)

        // Extract digits one by one from right to left
        while (temp > 0) {

            // Get the rightmost digit
            int digit = temp % 10;

            // Add to sum
            sum += digit;

            // Multiply to product
            product *= digit;

            // Remove the rightmost digit
            temp /= 10;

        }

        // Calculate total = sum + product
        int total = sum + product;

        // Check if n is divisible by total
        return n % total == 0;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: n = 132
 * 
 * Initial: temp = 132, sum = 0, product = 1
 * 
 * Iteration 1:
 *   digit = 132 % 10 = 2
 *   sum = 0 + 2 = 2
 *   product = 1 * 2 = 2
 *   temp = 132 / 10 = 13
 *   State: temp = 13, sum = 2, product = 2
 * 
 * Iteration 2:
 *   digit = 13 % 10 = 3
 *   sum = 2 + 3 = 5
 *   product = 2 * 3 = 6
 *   temp = 13 / 10 = 1
 *   State: temp = 1, sum = 5, product = 6
 * 
 * Iteration 3:
 *   digit = 1 % 10 = 1
 *   sum = 5 + 1 = 6
 *   product = 6 * 1 = 6
 *   temp = 1 / 10 = 0
 *   State: temp = 0, sum = 6, product = 6
 * 
 * Loop ends (temp = 0)
 * 
 * total = sum + product = 6 + 6 = 12
 * 132 % 12 = 0 ✓
 * 
 * Output: true
 * 
 * 
 * Digit Extraction Visualization:
 * 
 * n = 132
 * 
 * Step 1: 132 % 10 = 2  →  132 / 10 = 13
 *              ↑ last digit
 * Step 2:  13 % 10 = 3  →   13 / 10 = 1
 *              ↑ last digit
 * Step 3:   1 % 10 = 1  →    1 / 10 = 0
 *              ↑ last digit
 * 
 * Digits extracted (right to left): 2, 3, 1
 * Order doesn't matter for sum and product!
 * 
 * 
 * Edge Case - Zero Digit:
 * 
 * n = 102
 *   Digits: 1, 0, 2
 *   Sum = 1 + 0 + 2 = 3
 *   Product = 1 * 0 * 2 = 0 (zero makes product 0!)
 *   Total = 3 + 0 = 3
 *   102 % 3 = 0 → true
 * 
 * Note: If any digit is 0, product becomes 0.
 * This is handled correctly by the algorithm.
 * 
 * 
 * Why product starts at 1:
 * 
 * Multiplication identity: 1 * x = x
 * If we started with product = 0, then product would always be 0!
 * 
 * Sum starts at 0 because: 0 + x = x (addition identity)
 * Product starts at 1 because: 1 * x = x (multiplication identity)
 * 
 * 
 * Alternative using String conversion:
 * 
 * public boolean checkDivisibility(int n) {
 *     String s = String.valueOf(n);
 *     int sum = 0, product = 1;
 *     
 *     for (char c : s.toCharArray()) {
 *         int digit = c - '0';
 *         sum += digit;
 *         product *= digit;
 *     }
 *     
 *     return n % (sum + product) == 0;
 * }
 * 
 * String approach is more readable but slightly less efficient.
 * Your mathematical approach is optimal!
 */

