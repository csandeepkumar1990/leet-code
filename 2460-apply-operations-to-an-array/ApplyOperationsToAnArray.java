/*
 * LeetCode 2460: Apply Operations to an Array
 *
 * Problem:
 * You are given a 0-indexed array nums of size n consisting of non-negative
 * integers. You need to apply n - 1 operations to this array where, in the
 * i-th operation (0-indexed), you will:
 *
 * - If nums[i] == nums[i + 1], then multiply nums[i] by 2 and set nums[i + 1]
 *   to 0. Otherwise, you skip this operation.
 *
 * After performing all operations, shift all the 0's to the end of the array.
 * - For example, the array [1, 0, 2, 0, 0, 1] after shifting all its 0's to
 *   the end becomes [1, 2, 1, 0, 0, 0].
 *
 * Return the resulting array.
 *
 * Approach: Two-Pass Algorithm
 *
 * Key Insight:
 * - First pass: Apply operations (double and zero out) for consecutive equal
 *   elements
 * - Second pass: Shift all non-zero elements to the front, maintaining order
 * - Fill remaining positions with zeros
 *
 * Algorithm:
 * 1. First pass: For each index i from 0 to n-2:
 *    a. If nums[i] == nums[i+1]:
 *       - nums[i] = 2 * nums[i]
 *       - nums[i+1] = 0
 * 2. Second pass: Shift non-zero elements to front:
 *    a. Use a write pointer (count) to track next position for non-zero
 *    b. Iterate through array, moving non-zero elements to front
 *    c. Fill remaining positions with zeros
 *
 * Time Complexity: O(n), where n is the length of nums.
 *   - First pass: O(n) to apply operations
 *   - Second pass: O(n) to shift zeros
 * Space Complexity: O(1), modifying the array in-place.
 *
 * Example:
 *
 *   Input: nums = [1, 2, 2, 1, 1, 0]
 *
 *   Step 1: Apply operations
 *   i=0: nums[0]=1, nums[1]=2 → not equal, skip
 *   i=1: nums[1]=2, nums[2]=2 → equal! nums[1]=4, nums[2]=0
 *        Array: [1, 4, 0, 1, 1, 0]
 *   i=2: nums[2]=0, nums[3]=1 → not equal, skip
 *   i=3: nums[3]=1, nums[4]=1 → equal! nums[3]=2, nums[4]=0
 *        Array: [1, 4, 0, 2, 0, 0]
 *   i=4: nums[4]=0, nums[5]=0 → not equal (both 0), skip
 *
 *   After operations: [1, 4, 0, 2, 0, 0]
 *
 *   Step 2: Shift zeros to end
 *   count=0: nums[0]=1 != 0 → nums[0]=1, count=1
 *   count=1: nums[1]=4 != 0 → nums[1]=4, count=2
 *   count=2: nums[2]=0 → skip
 *   count=2: nums[3]=2 != 0 → nums[2]=2, count=3
 *   count=3: nums[4]=0 → skip
 *   count=3: nums[5]=0 → skip
 *   Fill remaining: nums[3]=0, nums[4]=0, nums[5]=0
 *
 *   Output: [1, 4, 2, 0, 0, 0]
 *
 * Why Two-Pass Approach?
 * - First pass applies the operations sequentially
 * - Second pass reorganizes the array (shifts zeros)
 * - This separation makes the logic clear and correct
 *
 * Important Notes:
 * - Operations are applied sequentially from left to right
 * - After operations, zeros can appear anywhere in the array
 * - Non-zero elements must maintain their relative order
 * - All zeros are moved to the end
 */

class Solution {

    public int[] applyOperations(int[] nums) {
        int n = nums.length;
        int count = 0;
        
        // Step 1: Apply operations
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                nums[i] = 2 * nums[i];
                nums[i + 1] = 0;
            }
        }
        
        // Step 2: Shift non-zero elements to front
        for (int i = 0; i < n; i++) {
            if (nums[i] != 0) {
                nums[count++] = nums[i];
            }
        }
        
        // Fill remaining positions with zeros
        while (count < n) {
            nums[count++] = 0;
        }
        
        return nums;
    }
}

