/*
 * LeetCode 3427: Sum of Variable Length Subarrays
 *
 * Problem:
 * Given an array nums, for each index i, we need to find the sum of all elements
 * in the subarray starting from max(0, i - nums[i]) to i (inclusive).
 * Return the total sum of all such subarrays.
 *
 * Approach:
 * - For each index i in the array:
 *   - Calculate the starting index: start = max(0, i - nums[i])
 *   - Sum all elements from start to i (inclusive)
 *   - Add this sum to the total
 * - Return the total sum
 *
 * Algorithm:
 * 1. Initialize total = 0
 * 2. For each index i from 0 to n-1:
 *    a. Calculate start = max(0, i - nums[i])
 *    b. Sum elements from start to i
 *    c. Add sum to total
 * 3. Return total
 *
 * Time Complexity: O(n^2) in the worst case, where n is the length of nums.
 *   For each index i, we may need to sum up to nums[i] elements.
 * Space Complexity: O(1) extra space.
 *
 * Example:
 *   Input: nums = [1, 2, 3]
 *   
 *   i=0: start = max(0, 0-1) = 0, sum = nums[0] = 1
 *   i=1: start = max(0, 1-2) = 0, sum = nums[0] + nums[1] = 1 + 2 = 3
 *   i=2: start = max(0, 2-3) = 0, sum = nums[0] + nums[1] + nums[2] = 1 + 2 + 3 = 6
 *   
 *   Total = 1 + 3 + 6 = 10
 *
 * Note: This can be optimized to O(n) using prefix sums, but this solution
 * demonstrates the straightforward approach.
 */

class Solution {

    public int subarraySum(int[] nums) {
        int total = 0;

        for (int i = 0; i < nums.length; i++) {
            int start = Math.max(0, i - nums[i]);

            for (int j = start; j <= i; j++) {
                total += nums[j];
            }
        }

        return total;
    }
}

