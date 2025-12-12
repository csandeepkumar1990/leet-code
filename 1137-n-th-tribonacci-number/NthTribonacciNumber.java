/*
 * LeetCode 1137: N-th Tribonacci Number
 * 
 * Problem:
 * The Tribonacci sequence is defined as:
 * - T(0) = 0
 * - T(1) = 1
 * - T(2) = 1
 * - T(n) = T(n-1) + T(n-2) + T(n-3) for n >= 3
 * 
 * Given n, return the value of T(n).
 * 
 * Approach: Iterative with Sliding Window (Space Optimized DP)
 * 
 * Key Insight:
 * - Similar to Fibonacci, but sum of LAST 3 numbers instead of 2
 * - We only need the last 3 values to compute the next one
 * - No need to store the entire sequence
 * 
 * Comparison:
 * Fibonacci:  F(n) = F(n-1) + F(n-2)         [2 terms]
 * Tribonacci: T(n) = T(n-1) + T(n-2) + T(n-3) [3 terms]
 * 
 * Algorithm:
 * 1. Handle base cases: n=0→0, n=1→1, n=2→1
 * 2. Use three variables as a sliding window: t0, t1, t2
 * 3. For each step, compute next = t0 + t1 + t2
 * 4. Slide the window: t0←t1, t1←t2, t2←next
 * 5. After n-2 iterations, t2 holds T(n)
 * 
 * Time Complexity: O(n) - single loop from 3 to n
 * Space Complexity: O(1) - only 3 variables
 * 
 * Tribonacci Sequence:
 * n:    0  1  2  3  4  5  6   7   8   9  10  ...
 * T(n): 0  1  1  2  4  7  13  24  44  81 149 ...
 * 
 * Example: Computing T(5)
 * 
 * Initial: t0=0, t1=1, t2=1
 * 
 * i=3: next = 0+1+1 = 2
 *      t0=1, t1=1, t2=2    → T(3) = 2
 * 
 * i=4: next = 1+1+2 = 4
 *      t0=1, t1=2, t2=4    → T(4) = 4
 * 
 * i=5: next = 1+2+4 = 7
 *      t0=2, t1=4, t2=7    → T(5) = 7
 * 
 * Return t2 = 7
 * 
 * Visual (Sliding Window):
 * 
 *   Sequence: [0, 1, 1, 2, 4, 7, 13, ...]
 *              └──┬──┘
 *              t0 t1 t2 → next = 0+1+1 = 2
 * 
 *                 └──┬──┘
 *                 t0 t1 t2 → next = 1+1+2 = 4
 * 
 *                    └──┬──┘
 *                    t0 t1 t2 → next = 1+2+4 = 7
 */

class Solution {

    public int tribonacci(int n) {

        // Base case: T(0) = 0
        if (n == 0)

            return 0;

        // Base cases: T(1) = T(2) = 1
        if (n == 1 || n == 2)

            return 1;

        // Initialize sliding window with first 3 values
        int t0 = 0;  // T(n-3)

        int t1 = 1;  // T(n-2)

        int t2 = 1;  // T(n-1)

        // Build up from T(3) to T(n)
        for (int i = 3; i <= n; i++) {

            // T(n) = T(n-1) + T(n-2) + T(n-3)
            int next = t0 + t1 + t2;

            // Slide the window forward
            t0 = t1;   // t0 now holds what was t1

            t1 = t2;   // t1 now holds what was t2

            t2 = next; // t2 now holds the new value

        }

        // t2 contains T(n)
        return t2;

    }

}

