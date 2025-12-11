/*
 * 3658. GCD of Odd and Even Sums
 * 
 * Problem:
 * Given a positive integer n, calculate:
 *   - Sum of first n odd numbers: 1 + 3 + 5 + ... + (2n-1)
 *   - Sum of first n even numbers: 2 + 4 + 6 + ... + 2n
 * 
 * Return the GCD (Greatest Common Divisor) of these two sums.
 * 
 * Mathematical Formulas:
 *   - Sum of first n odd numbers = n²
 *   - Sum of first n even numbers = n × (n + 1)
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: n = 3
 *   Output: 3
 *   Explanation:
 *     Odd sum: 1 + 3 + 5 = 9 = 3² = 9
 *     Even sum: 2 + 4 + 6 = 12 = 3 × 4 = 12
 *     GCD(9, 12) = 3
 * 
 * Example 2:
 *   Input: n = 4
 *   Output: 4
 *   Explanation:
 *     Odd sum: 1 + 3 + 5 + 7 = 16 = 4² = 16
 *     Even sum: 2 + 4 + 6 + 8 = 20 = 4 × 5 = 20
 *     GCD(16, 20) = 4
 * 
 * Example 3:
 *   Input: n = 5
 *   Output: 5
 *   Explanation:
 *     Odd sum: 1 + 3 + 5 + 7 + 9 = 25 = 5² = 25
 *     Even sum: 2 + 4 + 6 + 8 + 10 = 30 = 5 × 6 = 30
 *     GCD(25, 30) = 5
 * 
 * Example 4:
 *   Input: n = 1
 *   Output: 1
 *   Explanation:
 *     Odd sum: 1 = 1² = 1
 *     Even sum: 2 = 1 × 2 = 2
 *     GCD(1, 2) = 1
 * 
 * Example 5:
 *   Input: n = 6
 *   Output: 6
 *   Explanation:
 *     Odd sum: 36 = 6²
 *     Even sum: 42 = 6 × 7
 *     GCD(36, 42) = 6
 * 
 * Constraints:
 *   - 1 <= n <= 10^9
 * 
 * Approach: Mathematical Formulas + Euclidean GCD Algorithm
 * 
 * Key Insight: Use closed-form formulas instead of summing!
 *   - Sum of first n odd numbers: n²
 *     Proof: 1 + 3 + 5 + ... + (2n-1) = n²
 *   - Sum of first n even numbers: n(n+1)
 *     Proof: 2 + 4 + 6 + ... + 2n = 2(1+2+...+n) = 2 × n(n+1)/2 = n(n+1)
 * 
 * Then calculate GCD using Euclidean algorithm.
 * 
 * Euclidean Algorithm:
 *   GCD(a, b) = GCD(b, a % b)  until b = 0
 *   When b = 0, GCD = a
 * 
 * Time Complexity: O(log(min(odd, even)))
 *   - O(1) to compute sums
 *   - O(log n) for Euclidean GCD algorithm
 * 
 * Space Complexity: O(1)
 *   - Only a few integer variables
 */

class Solution {

    public int gcdOfOddEvenSums(int n) {

        // Sum of first n odd numbers: 1 + 3 + 5 + ... + (2n-1) = n²
        int odd = n * n;

        // Sum of first n even numbers: 2 + 4 + 6 + ... + 2n = n(n+1)
        int even = n * (n + 1);

        // Calculate GCD using Euclidean algorithm (iterative)
        // GCD(a, b) = GCD(b, a % b) until b = 0
        while (even != 0) {

            int temp = even;      // Save current even
            even = odd % even;    // New even = remainder
            odd = temp;           // New odd = old even

        }

        // When even becomes 0, odd contains the GCD
        return odd;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: n = 4
 * 
 * Step 1: Calculate sums
 *   odd = n² = 4² = 16
 *   even = n(n+1) = 4 × 5 = 20
 * 
 * Step 2: Euclidean GCD algorithm
 *   
 *   Iteration 1: odd=16, even=20
 *     temp = 20
 *     even = 16 % 20 = 16
 *     odd = 20
 *     State: odd=20, even=16
 *   
 *   Iteration 2: odd=20, even=16
 *     temp = 16
 *     even = 20 % 16 = 4
 *     odd = 16
 *     State: odd=16, even=4
 *   
 *   Iteration 3: odd=16, even=4
 *     temp = 4
 *     even = 16 % 4 = 0
 *     odd = 4
 *     State: odd=4, even=0
 *   
 *   even = 0 → loop ends
 * 
 * Output: 4
 * 
 * 
 * Sum of Odd Numbers Formula Proof:
 * 
 * Visual proof (square numbers):
 * 
 * n=1:  ■           = 1 = 1²
 * 
 * n=2:  ■ ■         = 1 + 3 = 4 = 2²
 *       ■ ■
 *         ■
 *         
 * n=3:  ■ ■ ■       = 1 + 3 + 5 = 9 = 3²
 *       ■ ■ ■
 *       ■ ■ ■
 *         ■ ■
 *           ■
 * 
 * Each odd number adds an "L-shape" to the square!
 * 
 * 
 * Sum of Even Numbers Formula Proof:
 * 
 * Sum = 2 + 4 + 6 + ... + 2n
 *     = 2(1 + 2 + 3 + ... + n)
 *     = 2 × [n(n+1)/2]
 *     = n(n+1)
 * 
 * 
 * Euclidean Algorithm Visualization:
 * 
 * GCD(16, 20):
 *   20 = 16 × 1 + 4   →  GCD(20, 16)
 *   16 = 4 × 4 + 0    →  GCD(16, 4)
 *   4 = ... + 0       →  GCD(4, 0) = 4
 * 
 * 
 * Pattern Discovery:
 * 
 * Looking at results:
 *   n=1: GCD(1, 2) = 1
 *   n=2: GCD(4, 6) = 2
 *   n=3: GCD(9, 12) = 3
 *   n=4: GCD(16, 20) = 4
 *   n=5: GCD(25, 30) = 5
 *   n=6: GCD(36, 42) = 6
 * 
 * Pattern: GCD(n², n(n+1)) = n always!
 * 
 * Proof:
 *   odd = n²
 *   even = n(n+1) = n² + n
 *   
 *   GCD(n², n² + n) = GCD(n², n)  [since GCD(a, a+b) = GCD(a, b)]
 *                   = GCD(n × n, n)
 *                   = n × GCD(n, 1)
 *                   = n × 1
 *                   = n
 * 
 * So the answer is simply n! But the algorithm still works correctly.
 * 
 * 
 * Alternative (Direct Answer):
 * 
 * public int gcdOfOddEvenSums(int n) {
 *     return n;  // GCD(n², n(n+1)) = n always!
 * }
 * 
 * This is O(1) if you know the mathematical insight!
 * But your Euclidean approach is the general solution and demonstrates
 * the algorithm well.
 */

