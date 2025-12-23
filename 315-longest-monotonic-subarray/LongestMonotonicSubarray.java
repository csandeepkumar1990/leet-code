/*
 * LeetCode 315: Longest Monotonic Subarray
 *
 * Problem:
 * You are given an array of integers nums. A subarray is monotonic if it is
 * either strictly increasing or strictly decreasing.
 *
 * Return the length of the longest monotonic subarray.
 *
 * A subarray is a contiguous sequence of elements within an array.
 *
 * Approach: Track Increasing and Decreasing Sequences
 *
 * Key Insight:
 * - Need to find the longest subarray that is either strictly increasing or
 *   strictly decreasing
 * - Track both increasing and decreasing sequence lengths simultaneously
 * - Reset counters when the trend changes or when elements are equal
 * - Update maximum length at each step
 *
 * Algorithm:
 * 1. Initialize maxLength = 1 (minimum subarray length)
 * 2. Track two counters: increasingLength and decreasingLength
 * 3. For each element starting from index 1:
 *    a. If nums[i] > nums[i-1]: increment increasingLength, reset decreasingLength
 *    b. If nums[i] < nums[i-1]: increment decreasingLength, reset increasingLength
 *    c. If nums[i] == nums[i-1]: reset both counters (breaks monotonicity)
 * 4. Update maxLength with the maximum of current increasing/decreasing lengths
 * 5. Return maxLength
 *
 * Time Complexity: O(n), where n is the length of nums.
 *   We traverse the array once.
 * Space Complexity: O(1), only using a constant amount of extra space.
 *
 * Example:
 *
 *   Input: nums = [1, 4, 3, 3, 2]
 *
 *   i=0: nums[0]=1
 *     increasingLength=1, decreasingLength=1, maxLength=1
 *
 *   i=1: nums[1]=4 > nums[0]=1
 *     increasingLength=2, decreasingLength=1, maxLength=2
 *
 *   i=2: nums[2]=3 < nums[1]=4
 *     increasingLength=1, decreasingLength=2, maxLength=2
 *
 *   i=3: nums[3]=3 == nums[2]=3
 *     increasingLength=1, decreasingLength=1, maxLength=2
 *
 *   i=4: nums[4]=2 < nums[3]=3
 *     increasingLength=1, decreasingLength=2, maxLength=2
 *
 *   Output: 2
 *
 *   Explanation: The longest monotonic subarray is [4, 3] or [3, 2], both
 *   have length 2.
 *
 * Another Example:
 *
 *   Input: nums = [3, 2, 1]
 *
 *   i=0: nums[0]=3
 *     increasingLength=1, decreasingLength=1, maxLength=1
 *
 *   i=1: nums[1]=2 < nums[0]=3
 *     increasingLength=1, decreasingLength=2, maxLength=2
 *
 *   i=2: nums[2]=1 < nums[1]=2
 *     increasingLength=1, decreasingLength=3, maxLength=3
 *
 *   Output: 3
 *
 *   Explanation: The entire array [3, 2, 1] is strictly decreasing.
 *
 * Why Reset Counters?
 * - When nums[i] > nums[i-1]: We're in an increasing trend, so decreasing
 *   sequence is broken → reset decreasingLength
 * - When nums[i] < nums[i-1]: We're in a decreasing trend, so increasing
 *   sequence is broken → reset increasingLength
 * - When nums[i] == nums[i-1]: Both sequences are broken → reset both
 *
 * Important Notes:
 * - "Strictly" means no equal elements allowed in the subarray
 * - Equal elements break both increasing and decreasing sequences
 * - We track both sequences simultaneously to find the maximum
 * - Edge case: empty array returns 0 (handled by initial check)
 */

class Solution {

    public int longestMonotonicSubarray(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        int maxLength = 1;
        int increasingLength = 1;
        int decreasingLength = 1;

        for (int i = 1; i < nums.length; i++) {
            // Check for strictly increasing
            if (nums[i] > nums[i - 1]) {
                increasingLength++;
                decreasingLength = 1; // Reset decreasing if increasing
            } 
            // Check for strictly decreasing
            else if (nums[i] < nums[i - 1]) {
                decreasingLength++;
                increasingLength = 1; // Reset increasing if decreasing
            } 
            // If equal, both increasing and decreasing sequences are broken
            else {
                increasingLength = 1;
                decreasingLength = 1;
            }
            maxLength = Math.max(maxLength, Math.max(increasingLength, decreasingLength));
        }

        return maxLength;
    }
}

