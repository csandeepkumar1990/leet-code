/*
 * LeetCode 611: Valid Triangle Number
 * 
 * Problem:
 * Given an integer array nums, return the number of triplets chosen from the array
 * that can make triangles if we take them as side lengths of a triangle.
 * 
 * A triangle is valid if the sum of any two sides is greater than the third side.
 * 
 * Example:
 *   Input: nums = [2,2,3,4]
 *   Output: 3
 *   Explanation: Valid combinations are:
 *     2,3,4 (using the first 2)
 *     2,3,4 (using the second 2)
 *     2,2,3
 * 
 * Approach: Two Pointers with Fixed Largest Side
 * 
 * Key Insight:
 * - For a triangle with sides a, b, c (where c is the largest), we need: a + b > c
 * - If we sort the array, we can fix the largest side and use two pointers to find valid pairs
 * - When nums[left] + nums[right] > nums[i], all pairs from left to right-1 with right are valid
 * 
 * Algorithm:
 * 1. Sort the array in ascending order
 * 2. Fix the largest side 'c' at index i (iterate from right to left, starting from n-1)
 * 3. Use two pointers (left=0, right=i-1) to find pairs (a, b) such that a + b > c
 * 4. When nums[left] + nums[right] > nums[i]:
 *    - All pairs (nums[left], nums[right]), (nums[left+1], nums[right]), ..., 
 *      (nums[right-1], nums[right]) are valid
 *    - Count += (right - left)
 *    - Decrement right to try a smaller middle side
 * 5. Otherwise, increment left to increase the smallest side
 * 
 * Time Complexity: O(n²)
 *   - Sorting: O(n log n)
 *   - Outer loop: O(n) iterations
 *   - Two pointers: O(n) in worst case per iteration
 *   - Total: O(n log n) + O(n²) = O(n²)
 * 
 * Space Complexity: O(1)
 *   - Only using a few variables
 *   - Arrays.sort() uses O(log n) space for sorting, but we consider it O(1) for this analysis
 * 
 * Example Walkthrough:
 * 
 *   Input: nums = [2, 2, 3, 4]
 *   
 *   Step 1: Sort (already sorted): [2, 2, 3, 4]
 *   
 *   Step 2: Fix largest side c = nums[3] = 4 (i = 3)
 *           left = 0, right = 2
 *           
 *           Check: nums[0] + nums[2] = 2 + 3 = 5 > 4 ✓
 *           Valid pairs: (nums[0], nums[2]), (nums[1], nums[2])
 *           Count += (2 - 0) = 2
 *           right-- → right = 1
 *           
 *           Check: nums[0] + nums[1] = 2 + 2 = 4 ≯ 4 ✗
 *           left++ → left = 1
 *           
 *           Check: nums[1] + nums[1] = 2 + 2 = 4 ≯ 4 ✗
 *           left++ → left = 2, but left >= right, exit
 *           
 *           Count = 2
 *   
 *   Step 3: Fix largest side c = nums[2] = 3 (i = 2)
 *           left = 0, right = 1
 *           
 *           Check: nums[0] + nums[1] = 2 + 2 = 4 > 3 ✓
 *           Valid pairs: (nums[0], nums[1])
 *           Count += (1 - 0) = 1
 *           right-- → right = 0
 *           
 *           left >= right, exit
 *           
 *           Count = 2 + 1 = 3
 *   
 *   Step 4: i = 1, but i >= 2 is required, so we stop
 *   
 *   Result: 3
 * 
 * Visual Representation:
 * 
 *   Sorted array: [2, 2, 3, 4]
 *                 a  b  c  d
 *   
 *   For c = 4 (index 3):
 *     Try a + c = 2 + 3 = 5 > 4 ✓
 *       Valid: (a, c), (b, c) → count += 2
 *     Try a + b = 2 + 2 = 4 ≯ 4 ✗
 *       Move left pointer
 *   
 *   For c = 3 (index 2):
 *     Try a + b = 2 + 2 = 4 > 3 ✓
 *       Valid: (a, b) → count += 1
 *   
 *   Total: 3 valid triangles
 * 
 * Why This Works:
 * 
 *   The key insight is that when nums[left] + nums[right] > nums[i]:
 *     - Since the array is sorted, nums[left] ≤ nums[left+1] ≤ ... ≤ nums[right-1]
 *     - Therefore: nums[left] + nums[right] ≤ nums[left+1] + nums[right] ≤ ... ≤ nums[right-1] + nums[right]
 *     - All of these sums are > nums[i], so all pairs from left to right-1 with right are valid
 *     - This gives us (right - left) valid triplets: (left, right), (left+1, right), ..., (right-1, right)
 * 
 * Edge Cases:
 * 
 *   1. Empty array or less than 3 elements:
 *      nums = [] or [1, 2]
 *      Result: 0 (need at least 3 elements for a triangle)
 *   
 *   2. All zeros:
 *      nums = [0, 0, 0]
 *      Result: 0 (0 + 0 = 0 ≯ 0)
 *   
 *   3. All same positive numbers:
 *      nums = [5, 5, 5]
 *      Result: 1 (5 + 5 = 10 > 5 ✓)
 *   
 *   4. No valid triangles:
 *      nums = [1, 2, 3]
 *      Result: 0 (1 + 2 = 3 ≯ 3)
 *   
 *   5. All valid triangles:
 *      nums = [3, 4, 5, 6, 7]
 *      Result: Multiple valid combinations
 */

import java.util.Arrays;

class Solution {
    /**
     * Counts the number of valid triangles that can be formed from the array.
     * 
     * @param nums Array of integers representing side lengths
     * @return The number of triplets that can form valid triangles
     * 
     * Time Complexity: O(n²) where n is the length of nums
     * Space Complexity: O(1)
     */
    public int triangleNumber(int[] nums) {
        int count = 0;
        int n = nums.length;
        
        // Step 1: Sort the array
        // This allows us to fix the largest side and use two pointers
        Arrays.sort(nums);
        
        // Step 2: Fix the largest side 'c' at index i
        // Iterate from right to left, starting from n-1
        // We need at least 3 elements, so i >= 2
        for (int i = n - 1; i >= 2; i--) {
            int left = 0;
            int right = i - 1;
            
            // Step 3: Use Two Pointers to find pairs (a, b)
            // We want to find all pairs where nums[left] + nums[right] > nums[i]
            while (left < right) {
                if (nums[left] + nums[right] > nums[i]) {
                    // If nums[left] + nums[right] > nums[i], then
                    // nums[left+1] + nums[right] > nums[i], ...,
                    // nums[right-1] + nums[right] > nums[i] are all true.
                    // This is because the array is sorted in ascending order.
                    count += (right - left);
                    right--; // Try a smaller 'middle' side
                } else {
                    left++; // Increase the smallest side to meet the condition
                }
            }
        }
        
        return count;
    }
}

