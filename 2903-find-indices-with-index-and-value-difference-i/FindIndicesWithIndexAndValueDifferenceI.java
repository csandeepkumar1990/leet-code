/*
 * LeetCode 2903: Find Indices With Index and Value Difference I
 * 
 * Problem:
 * Given an array nums, and two integers indexDifference and valueDifference,
 * find two indices i and j such that:
 * - |i - j| >= indexDifference (indices are far enough apart)
 * - |nums[i] - nums[j]| >= valueDifference (values differ enough)
 * 
 * Return [i, j] if found, or [-1, -1] if no such pair exists.
 * 
 * Approach: Brute Force - Check All Pairs
 * 
 * Key Insight:
 * - We need BOTH conditions to be satisfied simultaneously
 * - Try all pairs (i, j) and check both conditions
 * - Return first valid pair found
 * 
 * Conditions Explained:
 * 1. |i - j| >= indexDifference
 *    - The indices must be at least indexDifference apart
 *    - Example: if indexDifference = 2, valid pairs: (0,2), (1,4), etc.
 * 
 * 2. |nums[i] - nums[j]| >= valueDifference
 *    - The values at those indices must differ by at least valueDifference
 *    - Example: if valueDifference = 3, nums[i]=5, nums[j]=8 works (|5-8|=3)
 * 
 * Algorithm:
 * 1. For each index i from 0 to n-1:
 *    For each index j from 0 to n-1:
 *      - Check if |i - j| >= indexDifference
 *      - Check if |nums[i] - nums[j]| >= valueDifference
 *      - If both true, return [i, j]
 * 2. If no pair found, return [-1, -1]
 * 
 * Time Complexity: O(n²) - checking all pairs
 * Space Complexity: O(1) - only returning a fixed-size array
 * 
 * Example: nums = [5, 1, 4, 1], indexDifference = 2, valueDifference = 4
 * 
 * Check pairs:
 * - (0, 0): |0-0|=0 < 2 ❌
 * - (0, 1): |0-1|=1 < 2 ❌
 * - (0, 2): |0-2|=2 ≥ 2 ✓, |5-4|=1 < 4 ❌
 * - (0, 3): |0-3|=3 ≥ 2 ✓, |5-1|=4 ≥ 4 ✓ → Return [0, 3]
 * 
 * Visual:
 *   nums:    [5,  1,  4,  1]
 *   indices:  0   1   2   3
 *             ↑           ↑
 *             i=0         j=3
 *             
 *   Index diff: |0 - 3| = 3 ≥ 2 ✓
 *   Value diff: |5 - 1| = 4 ≥ 4 ✓
 *   
 *   Both conditions satisfied! Return [0, 3]
 * 
 * Note: This brute force approach works well for small arrays.
 * For larger inputs (LeetCode 2905), an O(n) solution using
 * prefix max/min tracking would be more efficient.
 */

class Solution {

    public int[] findIndices(int[] nums, int indexDifference, int valueDifference) {

        // Try all possible pairs (i, j)
        for (int i = 0; i < nums.length; i++) {

            for (int j = 0; j < nums.length; j++) {

                // Check both conditions:
                // 1. Indices are at least indexDifference apart
                // 2. Values differ by at least valueDifference
                if (Math.abs(i - j) >= indexDifference && 
                    Math.abs(nums[i] - nums[j]) >= valueDifference) {

                    // Found a valid pair - return immediately
                    return new int[] {i, j};

                }

            }

        }

        // No valid pair found
        return new int[] {-1, -1};

    }

}

