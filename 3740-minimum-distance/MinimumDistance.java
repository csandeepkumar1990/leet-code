/*
 * LeetCode 3740: Minimum Distance
 * 
 * Problem:
 * Given an array nums, find three indices i < j < k such that:
 * - nums[i] == nums[j] == nums[k] (all three values are equal)
 * Return the minimum "distance" defined as 2 * (k - i).
 * Return -1 if no such triplet exists.
 * 
 * Approach: Brute Force - Check All Triplets
 * 
 * Key Insight:
 * - We need three indices with the same value
 * - Distance = 2 * (k - i) = twice the span from first to last index
 * - To minimize distance, we want k - i to be as small as possible
 * - The middle element j doesn't affect the distance, only needs same value
 * 
 * Distance Formula Intuition:
 * - (k - i) is the "span" from first to last index
 * - Multiplying by 2 could represent round-trip or total path length
 * 
 * Algorithm:
 * 1. Try all triplets (i, j, k) where i < j < k
 * 2. Check if all three values are equal
 * 3. Calculate distance: 2 * (k - i)
 * 4. Track the minimum distance found
 * 5. Return -1 if no valid triplet exists
 * 
 * Time Complexity: O(n³) - three nested loops
 * Space Complexity: O(1) - only tracking minimum
 * 
 * Example: nums = [1, 2, 1, 1, 3]
 * 
 * Valid triplets (same value):
 * - (0, 2, 3): nums = [1, 1, 1] ✓ → distance = 2 * (3 - 0) = 6
 * 
 * Answer: 6
 * 
 * Example: nums = [1, 1, 1, 2]
 * 
 * Valid triplets:
 * - (0, 1, 2): nums = [1, 1, 1] ✓ → distance = 2 * (2 - 0) = 4
 * 
 * Answer: 4 (indices are consecutive, minimal span)
 * 
 * Visual:
 *   indices:  0   1   2   3   4
 *   nums:    [1,  2,  1,  1,  3]
 *             ↑       ↑   ↑
 *             i       j   k
 *             └───────────┘
 *               span = 3
 *             distance = 2 * 3 = 6
 * 
 * Optimization Note:
 * For better performance, one could group indices by value using a HashMap,
 * then for each value with 3+ occurrences, compute minimum span.
 * This would reduce to O(n) but this brute force works for small inputs.
 */

class Solution {

    public int minimumDistance(int[] nums) {

        // Track minimum distance found
        int minValue = Integer.MAX_VALUE;

        // Try all possible first indices
        for (int i = 0; i < nums.length; i++) {

            // Try all possible middle indices (after i)
            for (int j = i + 1; j < nums.length; j++) {

                // Try all possible last indices (after j)
                for (int k = j + 1; k < nums.length; k++) {

                    // Check if all three elements have the same value
                    if (nums[i] == nums[j] && nums[j] == nums[k]) {

                        // Calculate distance: 2 * (span from i to k)
                        // Update minimum if this is smaller
                        minValue = Math.min(minValue, 2 * (k - i));

                    }

                }

            }

        }

        // Return -1 if no valid triplet found, otherwise return minimum distance
        return minValue == Integer.MAX_VALUE ? -1 : minValue;

    }

}

