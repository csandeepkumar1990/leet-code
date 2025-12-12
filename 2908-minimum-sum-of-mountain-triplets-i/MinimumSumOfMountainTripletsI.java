/*
 * LeetCode 2908: Minimum Sum of Mountain Triplets I
 * 
 * Problem:
 * Given an array nums, find indices i < j < k such that:
 * - nums[i] < nums[j] (left side goes up to peak)
 * - nums[k] < nums[j] (right side goes down from peak)
 * This forms a "mountain" shape with j as the peak.
 * Return the minimum sum nums[i] + nums[j] + nums[k], or -1 if no such triplet exists.
 * 
 * Approach: Brute Force - Check All Triplets
 * 
 * Key Insight:
 * - A mountain triplet has the middle element (j) as the highest
 * - Both left (i) and right (k) elements must be strictly less than the peak
 * - We need i < j < k (indices in order)
 * 
 * Algorithm:
 * 1. Try all possible triplets (i, j, k) where i < j < k
 * 2. Check if it forms a valid mountain: nums[i] < nums[j] AND nums[k] < nums[j]
 * 3. Track the minimum sum among all valid mountain triplets
 * 
 * Time Complexity: O(n³) - three nested loops
 * Space Complexity: O(1) - only using a few variables
 * 
 * Example:
 * Input: nums = [8, 6, 1, 5, 3]
 * 
 * Valid mountain triplets:
 * - (2, 3, 4): nums = [1, 5, 3] → 1 < 5 ✓, 3 < 5 ✓ → sum = 9
 * 
 * Output: 9
 * 
 * Visual (Mountain Shape):
 * 
 *        nums[j]=5
 *           /\
 *          /  \
 *   nums[i]=1  nums[k]=3
 * 
 * The peak (j) must be strictly greater than both sides.
 * 
 * Note: This is the brute force solution suitable for small inputs.
 * For larger inputs, an O(n) solution using prefix/suffix minimums exists.
 */

class Solution {

    public int minimumSum(int[] nums) {

        // Track minimum sum found; start with MAX_VALUE
        int minValue = Integer.MAX_VALUE;

        // Flag to check if any valid mountain triplet was found
        boolean found = false;

        // Try all possible positions for i (left element)
        for (int i = 0; i < nums.length; i++) {

            // Try all possible positions for j (peak element)
            // j must be after i
            for (int j = i + 1; j < nums.length; j++) {

                // Try all possible positions for k (right element)
                // k must be after j
                for (int k = j + 1; k < nums.length; k++) {

                    // Check if this triplet forms a valid mountain:
                    // - nums[i] < nums[j]: left element less than peak
                    // - nums[k] < nums[j]: right element less than peak
                    if (nums[i] < nums[j] && nums[k] < nums[j]) {

                        // Calculate sum of this mountain triplet
                        int sum = nums[i] + nums[j] + nums[k];

                        // Update minimum if this sum is smaller
                        minValue = Math.min(minValue, sum);

                        // Mark that we found at least one valid triplet
                        found = true;

                    }

                }

            }

        }

        // Return minimum sum if found, otherwise -1
        return found ? minValue : -1;

    }

}

