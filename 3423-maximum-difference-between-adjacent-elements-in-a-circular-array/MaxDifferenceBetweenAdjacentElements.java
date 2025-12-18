/*
 * LeetCode 3423: Maximum Difference Between Adjacent Elements in a Circular Array
 * 
 * Problem:
 * Given a circular array nums, find the maximum absolute difference between
 * adjacent elements. In a circular array, the first and last elements are
 * also considered adjacent.
 * 
 * Approach: Linear Scan with Modulo for Circular Wrap
 * 
 * Key Insight:
 * - Use modulo operator to handle circular wraparound
 * - (i + 1) % n gives the next index, wrapping from last to first
 * - Track maximum absolute difference across all adjacent pairs
 * 
 * Algorithm:
 * 1. Initialize maxDiff = 0
 * 2. For each index i from 0 to n-1:
 *    - Calculate next index using (i + 1) % n
 *    - Compute absolute difference |nums[i] - nums[nextIndex]|
 *    - Update maxDiff if current difference is larger
 * 3. Return maxDiff
 * 
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using variables
 * 
 * Example 1: nums = [1, 2, 4]
 * 
 *   Circular array visualization:
 *       ┌───┐
 *       │ 1 │ ← index 0
 *       └─┬─┘
 *         │
 *       ┌─▼─┐
 *       │ 2 │ ← index 1
 *       └─┬─┘
 *         │
 *       ┌─▼─┐
 *       │ 4 │ ← index 2
 *       └─┬─┘
 *         │ (wraps back)
 *         └──→ index 0
 * 
 *   Adjacent pairs:
 *   i=0: |nums[0] - nums[1]| = |1 - 2| = 1
 *   i=1: |nums[1] - nums[2]| = |2 - 4| = 2
 *   i=2: |nums[2] - nums[0]| = |4 - 1| = 3  ← circular wrap!
 * 
 *   Result: max(1, 2, 3) = 3
 * 
 * Example 2: nums = [-5, -10, -5]
 * 
 *   Adjacent pairs:
 *   i=0: |(-5) - (-10)| = |5| = 5
 *   i=1: |(-10) - (-5)| = |-5| = 5
 *   i=2: |(-5) - (-5)| = |0| = 0  ← circular wrap
 * 
 *   Result: max(5, 5, 0) = 5
 * 
 * Modulo Trick Explained:
 * 
 *   For n = 4:
 *   i=0: (0+1) % 4 = 1
 *   i=1: (1+1) % 4 = 2
 *   i=2: (2+1) % 4 = 3
 *   i=3: (3+1) % 4 = 0  ← wraps to first element!
 */

class Solution {

    public int maxAdjacentDistance(int[] nums) {

        int n = nums.length;

        int maxDiff = 0;

        for (int i = 0; i < n; i++) {

            int nextIndex = (i + 1) % n; // wrap around for circular

            int diff = Math.abs(nums[i] - nums[nextIndex]);

            maxDiff = Math.max(maxDiff, diff);

        }

        return maxDiff;

    }

}

