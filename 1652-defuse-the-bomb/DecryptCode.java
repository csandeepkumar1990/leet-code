/*
 * LeetCode 1652: Defuse the Bomb
 * 
 * Problem:
 * You have a bomb to defuse, and your time is running out! Your informant will
 * provide you with a circular array code of length n and a key k.
 * 
 * To decrypt the code, you must replace every number. All the numbers are replaced
 * simultaneously:
 * - If k > 0: replace the i-th number with the sum of the next k numbers
 * - If k < 0: replace the i-th number with the sum of the previous |k| numbers
 * - If k == 0: replace the i-th number with 0
 * 
 * As code is circular, the next element of code[n-1] is code[0], and the previous
 * element of code[0] is code[n-1].
 * 
 * Approach: Direct Sum Calculation with Circular Wrapping
 * 
 * Key Insight:
 * - For each position i, calculate sum of k adjacent elements
 * - Handle circular wrapping using modulo arithmetic
 * - For k > 0: sum next k elements (forward direction)
 * - For k < 0: sum previous |k| elements (backward direction)
 * 
 * Algorithm:
 * 1. If k == 0, return array of zeros
 * 2. For each position i in the array:
 *    - If k > 0: sum code[(i+1) % n] to code[(i+k) % n]
 *    - If k < 0: sum code[(i-1+n) % n] to code[(i-|k|+n) % n]
 * 3. Return the result array
 * 
 * Time Complexity: O(n * |k|)
 *   - For each of n positions, sum |k| elements
 * 
 * Space Complexity: O(n)
 *   - Result array of size n
 * 
 * Example 1: k > 0
 *   code = [5, 7, 1, 4], k = 3
 *   
 *   Position 0: sum next 3 → code[1] + code[2] + code[3] = 7 + 1 + 4 = 12
 *   Position 1: sum next 3 → code[2] + code[3] + code[0] = 1 + 4 + 5 = 10
 *   Position 2: sum next 3 → code[3] + code[0] + code[1] = 4 + 5 + 7 = 16
 *   Position 3: sum next 3 → code[0] + code[1] + code[2] = 5 + 7 + 1 = 13
 *   
 *   Result: [12, 10, 16, 13]
 * 
 * Example 2: k < 0
 *   code = [2, 4, 9, 3], k = -2
 *   
 *   Position 0: sum previous 2 → code[3] + code[2] = 3 + 9 = 12
 *   Position 1: sum previous 2 → code[0] + code[3] = 2 + 3 = 5
 *   Position 2: sum previous 2 → code[1] + code[0] = 4 + 2 = 6
 *   Position 3: sum previous 2 → code[2] + code[1] = 9 + 4 = 13
 *   
 *   Result: [12, 5, 6, 13]
 * 
 * Example 3: k == 0
 *   code = [1, 2, 3, 4], k = 0
 *   
 *   All positions replaced with 0
 *   Result: [0, 0, 0, 0]
 * 
 * Circular Wrapping:
 * 
 *   For k > 0 (forward):
 *   - (i + j) % n handles wrapping when i + j >= n
 *   - Example: n=4, i=3, j=1 → (3+1) % 4 = 0 (wraps to start)
 * 
 *   For k < 0 (backward):
 *   - (i - j + n) % n handles wrapping when i - j < 0
 *   - Example: n=4, i=0, j=1 → (0-1+4) % 4 = 3 (wraps to end)
 * 
 * Visual (k = 3, code = [5, 7, 1, 4]):
 * 
 *   Circular array:
 *        5 ← 4
 *        ↓   ↑
 *        7 → 1
 * 
 *   Position 0: next 3 = [7, 1, 4] → sum = 12
 *   Position 1: next 3 = [1, 4, 5] → sum = 10
 *   Position 2: next 3 = [4, 5, 7] → sum = 16
 *   Position 3: next 3 = [5, 7, 1] → sum = 13
 * 
 * Why Modulo Arithmetic?
 *   - Ensures indices stay within [0, n-1] range
 *   - (i + j) % n: wraps forward when i + j >= n
 *   - (i - j + n) % n: wraps backward when i - j < 0
 *   - Adding n before modulo ensures positive result
 */

import java.util.Arrays;

class Solution {

    public int[] decrypt(int[] code, int k) {

        int n = code.length;

        int[] result = new int[n];

        // Special case: k == 0, all elements become 0
        if (k == 0) {

            Arrays.fill(result, 0);

            return result;

        }

        // Calculate sum for each position
        for (int i = 0; i < n; i++) {

            int sum = 0;

            if (k > 0) { // Sum next k numbers

                for (int j = 1; j <= k; j++) {

                    sum += code[(i + j) % n]; // Wrap around forward

                }

            } else { // Sum previous |k| numbers

                for (int j = 1; j <= -k; j++) {

                    sum += code[(i - j + n) % n]; // Wrap around backwards

                }

            }

            result[i] = sum;

        }

        return result;

    }

}

