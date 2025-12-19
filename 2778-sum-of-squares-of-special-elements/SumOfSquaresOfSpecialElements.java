/*
 * LeetCode 2778: Sum of Squares of Special Elements
 * 
 * Problem:
 * You are given a 1-indexed integer array nums of length n.
 * An element nums[i] is called special if i divides n (i.e., n % i == 0).
 * Return the sum of the squares of all special elements of nums.
 * 
 * Approach: Linear Scan with Divisibility Check
 * 
 * Key Insight:
 * - Array is 1-indexed in problem, but 0-indexed in code
 * - For 0-indexed: check if n % (i+1) == 0
 * - Special indices are divisors of n
 * 
 * Algorithm:
 * 1. Get array length n
 * 2. For each index i (0-indexed):
 *    - Check if (i+1) divides n evenly
 *    - If yes, add nums[i]² to sum
 * 3. Return sum
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only using variables
 * 
 * Example 1: nums = [1, 2, 3, 4], n = 4
 * 
 *   1-indexed positions: 1, 2, 3, 4
 *   Divisors of 4: 1, 2, 4 (since 4%1=0, 4%2=0, 4%4=0)
 *   
 *   Position 1 (i=0): 4 % 1 = 0 ✓ → nums[0]² = 1² = 1
 *   Position 2 (i=1): 4 % 2 = 0 ✓ → nums[1]² = 2² = 4
 *   Position 3 (i=2): 4 % 3 = 1 ✗ → skip
 *   Position 4 (i=3): 4 % 4 = 0 ✓ → nums[3]² = 4² = 16
 *   
 *   Sum = 1 + 4 + 16 = 21
 * 
 * Example 2: nums = [2, 7, 1, 19, 18, 3], n = 6
 * 
 *   Divisors of 6: 1, 2, 3, 6
 *   
 *   Position 1: 6 % 1 = 0 ✓ → 2² = 4
 *   Position 2: 6 % 2 = 0 ✓ → 7² = 49
 *   Position 3: 6 % 3 = 0 ✓ → 1² = 1
 *   Position 4: 6 % 4 = 2 ✗ → skip
 *   Position 5: 6 % 5 = 1 ✗ → skip
 *   Position 6: 6 % 6 = 0 ✓ → 3² = 9
 *   
 *   Sum = 4 + 49 + 1 + 9 = 63
 * 
 * Visual:
 * 
 *   nums = [1, 2, 3, 4]
 *           ↑  ↑     ↑
 *           1  2     4  ← positions that divide n=4
 *           │  │     │
 *           1² 2²    4²
 *           │  │     │
 *           1 + 4 + 16 = 21
 * 
 * Index Conversion:
 *   Problem uses 1-indexed, code uses 0-indexed
 *   Position (1-indexed) = i + 1 (where i is 0-indexed)
 *   
 *   i=0 → position 1
 *   i=1 → position 2
 *   i=2 → position 3
 *   ...
 */

class Solution {

    public int sumOfSquares(int[] nums) {

        int n = nums.length;

        int sum = 0;

        for (int i = 0; i < nums.length; i++) {

            if (n % (i + 1) == 0) {

                sum += nums[i] * nums[i];

            }

        }

        return sum;

    }

}

