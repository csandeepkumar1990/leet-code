/*
 * LeetCode 3364: Minimum Sum Subarray
 * 
 * Problem:
 * Given a list of integers nums and two integers l and r, find the minimum positive sum
 * among all subarrays with lengths between l and r (inclusive).
 * 
 * A subarray is a contiguous part of the array. The subarray length must be at least l
 * and at most r.
 * 
 * Return the minimum positive sum. If no positive sum exists, return -1.
 * 
 * Approach: Prefix Sum with Sliding Window
 * 
 * Key Insight:
 * - Use prefix sums to efficiently calculate subarray sums in O(1) time
 * - For a subarray from index i to j (inclusive), sum = prefix[j+1] - prefix[i]
 * - Check all possible subarray lengths from l to r
 * - For each length, check all starting positions
 * - Track the minimum positive sum encountered
 * 
 * Algorithm:
 * 1. Build prefix sum array: prefix[i] = sum of nums[0..i-1]
 *    - prefix[0] = 0
 *    - prefix[i+1] = prefix[i] + nums[i]
 * 2. For each subarray length from l to r:
 *    a. For each starting position i where i + len <= n:
 *       - Calculate sum = prefix[i + len] - prefix[i]
 *       - If sum > 0, update minSum = min(minSum, sum)
 * 3. Return minSum if found, otherwise return -1
 * 
 * Time Complexity: O(n * (r - l + 1))
 *   - Building prefix array: O(n)
 *   - For each length (r - l + 1 possible lengths):
 *     - Check all starting positions: O(n) per length
 *   - Total: O(n) + O(n * (r - l + 1)) = O(n * (r - l + 1))
 * 
 * Space Complexity: O(n)
 *   - Prefix sum array of size n + 1
 * 
 * Example:
 * 
 *   Input: nums = [1, -2, 3, 4, -1], l = 2, r = 4
 * 
 *   Step 1: Build prefix array
 *     prefix = [0, 1, -1, 2, 6, 5]
 *     Explanation:
 *       prefix[0] = 0
 *       prefix[1] = 0 + 1 = 1
 *       prefix[2] = 1 + (-2) = -1
 *       prefix[3] = -1 + 3 = 2
 *       prefix[4] = 2 + 4 = 6
 *       prefix[5] = 6 + (-1) = 5
 * 
 *   Step 2: Check subarrays of length 2
 *     i=0: sum = prefix[2] - prefix[0] = -1 - 0 = -1 (not positive, skip)
 *     i=1: sum = prefix[3] - prefix[1] = 2 - 1 = 1 (positive, minSum = 1)
 *     i=2: sum = prefix[4] - prefix[2] = 6 - (-1) = 7 (positive, minSum = 1)
 *     i=3: sum = prefix[5] - prefix[3] = 5 - 2 = 3 (positive, minSum = 1)
 * 
 *   Step 3: Check subarrays of length 3
 *     i=0: sum = prefix[3] - prefix[0] = 2 - 0 = 2 (positive, minSum = 1)
 *     i=1: sum = prefix[4] - prefix[1] = 6 - 1 = 5 (positive, minSum = 1)
 *     i=2: sum = prefix[5] - prefix[2] = 5 - (-1) = 6 (positive, minSum = 1)
 * 
 *   Step 4: Check subarrays of length 4
 *     i=0: sum = prefix[4] - prefix[0] = 6 - 0 = 6 (positive, minSum = 1)
 *     i=1: sum = prefix[5] - prefix[1] = 5 - 1 = 4 (positive, minSum = 1)
 * 
 *   Result: 1 (minimum positive sum is 1, from subarray [3] at length 1... wait, 
 *            but we only check lengths 2-4, so the minimum is from [3, 4] starting at index 1)
 * 
 * Visual Representation:
 * 
 *   nums = [1, -2, 3, 4, -1]
 *   prefix = [0, 1, -1, 2, 6, 5]
 * 
 *   Length 2 subarrays:
 *     [1, -2]: prefix[2] - prefix[0] = -1 - 0 = -1
 *     [-2, 3]: prefix[3] - prefix[1] = 2 - 1 = 1 ✓ (minSum = 1)
 *     [3, 4]:  prefix[4] - prefix[2] = 6 - (-1) = 7
 *     [4, -1]: prefix[5] - prefix[3] = 5 - 2 = 3
 * 
 *   Length 3 subarrays:
 *     [1, -2, 3]:   prefix[3] - prefix[0] = 2 - 0 = 2
 *     [-2, 3, 4]:   prefix[4] - prefix[1] = 6 - 1 = 5
 *     [3, 4, -1]:   prefix[5] - prefix[2] = 5 - (-1) = 6
 * 
 *   Length 4 subarrays:
 *     [1, -2, 3, 4]:    prefix[4] - prefix[0] = 6 - 0 = 6
 *     [-2, 3, 4, -1]:   prefix[5] - prefix[1] = 5 - 1 = 4
 * 
 *   Minimum positive sum: 1
 * 
 * Why Use Prefix Sums?
 * 
 *   Without prefix sums, calculating each subarray sum would take O(length) time:
 *     - For length l: O(l) per subarray
 *     - For length r: O(r) per subarray
 *     - Total: O(n * (r-l+1) * average_length) = O(n * (r-l+1) * (l+r)/2)
 * 
 *   With prefix sums, each sum calculation is O(1):
 *     - sum[i..j] = prefix[j+1] - prefix[i]
 *     - Total: O(n * (r-l+1))
 * 
 * Edge Cases:
 * 
 * 1. All negative numbers:
 *    nums = [-1, -2, -3], l = 1, r = 3
 *    All subarray sums are negative
 *    Result: -1 (no positive sum)
 * 
 * 2. Single positive element:
 *    nums = [5], l = 1, r = 1
 *    Result: 5
 * 
 * 3. Mixed positive and negative:
 *    nums = [1, -5, 2], l = 1, r = 3
 *    Need to find minimum positive sum among all valid subarrays
 * 
 * 4. Minimum sum is at boundary:
 *    nums = [1, 2, 3], l = 1, r = 3
 *    All sums are positive, minimum is 1 (single element)
 */

import java.util.List;

class Solution {
    /**
     * Finds the minimum positive sum among all subarrays with lengths between l and r.
     * 
     * @param nums The input list of integers
     * @param l The minimum subarray length (inclusive)
     * @param r The maximum subarray length (inclusive)
     * @return The minimum positive sum, or -1 if no positive sum exists
     * 
     * Time Complexity: O(n * (r - l + 1))
     * Space Complexity: O(n)
     */
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {
        int n = nums.size();
        int[] prefix = new int[n + 1];

        // Build prefix sums
        // prefix[i] = sum of nums[0..i-1]
        // prefix[0] = 0 (sum of empty subarray)
        // prefix[i+1] = prefix[i] + nums[i]
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int minSum = Integer.MAX_VALUE;

        // Check all subarray lengths between l and r
        for (int len = l; len <= r; len++) {
            // Check all starting positions for subarrays of this length
            // i + len <= n ensures we don't go out of bounds
            for (int i = 0; i + len <= n; i++) {
                // Calculate sum of subarray from index i to i+len-1
                // Using prefix sum: sum = prefix[i + len] - prefix[i]
                int sum = prefix[i + len] - prefix[i];
                
                // Only consider positive sums
                if (sum > 0) {
                    minSum = Math.min(minSum, sum);
                }
            }
        }

        // Return -1 if no positive sum was found, otherwise return the minimum
        return minSum == Integer.MAX_VALUE ? -1 : minSum;
    }
}

