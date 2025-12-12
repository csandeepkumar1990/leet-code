/*
 * LeetCode 2455: Average Value of Even Numbers That Are Divisible by Three
 * 
 * Problem:
 * Given an integer array nums, return the average value of all even integers
 * that are divisible by 3. Return 0 if no such numbers exist.
 * The average is the sum divided by count, rounded down to the nearest integer.
 * 
 * Approach: Single Pass with Divisibility Check
 * 
 * Key Insight:
 * - "Even AND divisible by 3" is equivalent to "divisible by 6"
 * - Why? LCM(2, 3) = 6
 * - A number divisible by both 2 and 3 must be divisible by 6
 * 
 * Mathematical Proof:
 * - Even: n % 2 == 0
 * - Divisible by 3: n % 3 == 0
 * - Both conditions together: n % 6 == 0
 * 
 * Algorithm:
 * 1. Iterate through all numbers
 * 2. If num % 6 == 0, add to sum and increment count
 * 3. Return sum / count (integer division = floor)
 * 4. Handle edge case: return 0 if count is 0
 * 
 * Time Complexity: O(n) - single pass through array
 * Space Complexity: O(1) - only two variables
 * 
 * Example:
 * Input: nums = [1, 3, 6, 10, 12, 15]
 * 
 * Check each number:
 * - 1 % 6 = 1  ❌
 * - 3 % 6 = 3  ❌ (divisible by 3, but not even)
 * - 6 % 6 = 0  ✓ → sum = 6, count = 1
 * - 10 % 6 = 4 ❌ (even, but not divisible by 3)
 * - 12 % 6 = 0 ✓ → sum = 18, count = 2
 * - 15 % 6 = 3 ❌ (divisible by 3, but not even)
 * 
 * Answer: 18 / 2 = 9
 * 
 * Visual:
 *   Numbers divisible by 6: {6, 12, 18, 24, 30, ...}
 *   These are ALL:
 *   - Even (divisible by 2) ✓
 *   - Divisible by 3 ✓
 */

class Solution {

    public int averageValue(int[] nums) {

        int sum = 0;    // Sum of qualifying numbers
        int count = 0;  // Count of qualifying numbers

        for (int num : nums) {

            // Check if number is divisible by 6
            // This is equivalent to checking:
            // (num % 2 == 0) && (num % 3 == 0)
            if (num % 6 == 0) {

                sum += num;

                count++;

            }

        }

        // Return average, or 0 if no qualifying numbers
        // Integer division automatically floors the result
        return count == 0 ? 0 : sum / count;

    }

}

