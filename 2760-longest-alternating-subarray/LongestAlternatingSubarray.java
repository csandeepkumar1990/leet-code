/*
 * LeetCode 2760: Longest Alternating Subarray
 * 
 * Problem:
 * You are given a 0-indexed integer array nums and an integer threshold.
 * 
 * Find the longest subarray of nums starting at index l and ending at index r
 * (0 <= l <= r < nums.length) that satisfies the following conditions:
 * 
 * 1. nums[l] % 2 == 0 (starts with even number)
 * 2. For all indices i in the range [l, r - 1], nums[i] % 2 != nums[i + 1] % 2
 *    (adjacent elements have different parity)
 * 3. For all indices i in the range [l, r], nums[i] <= threshold
 * 
 * Return the length of the longest such subarray, or 0 if no such subarray exists.
 * 
 * Approach: Sliding Window with Greedy Expansion
 * 
 * Key Insight:
 * - Find valid starting points (even number and <= threshold)
 * - Expand window while conditions are met:
 *   - Next element <= threshold
 *   - Next element has different parity than current
 * - Track maximum length found
 * 
 * Algorithm:
 * 1. Iterate through array, treating each index as potential start
 * 2. If start is valid (even and <= threshold):
 *    a. Expand window while conditions hold
 *    b. Update maximum length
 * 3. Move to next potential start
 * 4. Return maximum length found
 * 
 * Time Complexity: O(n²), where n is the length of nums.
 *   - In worst case, we check each index as start: O(n)
 *   - For each start, we may expand to end: O(n)
 *   - Total: O(n²)
 *   - In practice, often better due to early termination
 * 
 * Space Complexity: O(1), only using a constant amount of extra space.
 * 
 * Example:
 * 
 *   Input: nums = [3, 2, 5, 4], threshold = 5
 * 
 *   start = 0: nums[0] = 3 (odd) → skip
 *   start = 1: nums[1] = 2 (even, <= 5) ✓
 *     - Expand: end = 1
 *     - Check end+1 = 2: nums[2] = 5 (odd, <= 5, different parity) ✓
 *       end = 2
 *     - Check end+1 = 3: nums[3] = 4 (even, <= 5, different parity) ✓
 *       end = 3
 *     - Check end+1 = 4: out of bounds → stop
 *     - Length = 3 - 1 + 1 = 3
 *     - maxLen = 3
 * 
 *   start = 2: nums[2] = 5 (odd) → skip
 *   start = 3: nums[3] = 4 (even, <= 5) ✓
 *     - Expand: end = 3
 *     - Check end+1 = 4: out of bounds → stop
 *     - Length = 3 - 3 + 1 = 1
 *     - maxLen = max(3, 1) = 3
 * 
 *   Result: 3 (subarray [2, 5, 4])
 * 
 * Another Example:
 * 
 *   Input: nums = [1, 2], threshold = 2
 * 
 *   start = 0: nums[0] = 1 (odd) → skip
 *   start = 1: nums[1] = 2 (even, <= 2) ✓
 *     - Expand: end = 1
 *     - Check end+1 = 2: out of bounds → stop
 *     - Length = 1 - 1 + 1 = 1
 * 
 *   Result: 1 (subarray [2])
 * 
 * Yet Another Example:
 * 
 *   Input: nums = [2, 3, 4, 5], threshold = 4
 * 
 *   start = 0: nums[0] = 2 (even, <= 4) ✓
 *     - Expand: end = 0
 *     - Check end+1 = 1: nums[1] = 3 (odd, <= 4, different parity) ✓
 *       end = 1
 *     - Check end+1 = 2: nums[2] = 4 (even, <= 4, different parity) ✓
 *       end = 2
 *     - Check end+1 = 3: nums[3] = 5 (odd, > 4) ✗ → stop
 *     - Length = 2 - 0 + 1 = 3
 *     - maxLen = 3
 * 
 *   start = 1: nums[1] = 3 (odd) → skip
 *   start = 2: nums[2] = 4 (even, <= 4) ✓
 *     - Expand: end = 2
 *     - Check end+1 = 3: nums[3] = 5 (odd, > 4) ✗ → stop
 *     - Length = 2 - 2 + 1 = 1
 *     - maxLen = max(3, 1) = 3
 * 
 *   Result: 3 (subarray [2, 3, 4])
 * 
 * Why Check Parity with % 2?
 * 
 *   - nums[i] % 2 returns 0 for even, 1 for odd
 *   - nums[i] % 2 != nums[i+1] % 2 ensures different parity
 *   - Example: 2 % 2 = 0, 3 % 2 = 1 → 0 != 1 ✓
 *   - Example: 2 % 2 = 0, 4 % 2 = 0 → 0 != 0 ✗
 * 
 * Why Start with Even Number?
 * 
 *   - Problem requirement: subarray must start with even number
 *   - If start is odd, skip it (can't be a valid subarray start)
 *   - This is a constraint from the problem statement
 * 
 * Expansion Condition:
 * 
 *   while (end + 1 < n &&
 *          nums[end + 1] <= threshold &&
 *          (nums[end] % 2 != nums[end + 1] % 2))
 * 
 *   - end + 1 < n: Check bounds
 *   - nums[end + 1] <= threshold: Next element within threshold
 *   - nums[end] % 2 != nums[end + 1] % 2: Different parity
 *   - All three conditions must be true to expand
 * 
 * Why Move Start by 1 Each Time?
 * 
 *   - We check every possible starting position
 *   - Even if current subarray is long, there might be a longer one starting later
 *   - Example: [2, 3, 4, 5, 2, 3] with threshold = 5
 *     - Start at 0: [2, 3, 4, 5] → length 4
 *     - Start at 4: [2, 3] → length 2
 *     - But we need to check all starts to find maximum
 * 
 * Edge Cases:
 * 
 *   - No valid subarray: All numbers odd or all > threshold → return 0
 *   - Single element: Even and <= threshold → length 1
 *   - Entire array valid: All even-odd alternating and <= threshold
 *   - Threshold very small: Many numbers excluded
 *   - All even numbers: Can't alternate (need odd numbers too)
 * 
 * Optimization Note:
 * 
 *   - Current approach: O(n²) worst case
 *   - Could optimize by not checking starts that are already part of a longer subarray
 *   - But current approach is clear and correct
 *   - For typical inputs, performs well due to early termination
 */

class Solution {
    public int longestAlternatingSubarray(int[] nums, int threshold) {
        int n = nums.length;
        int maxLen = 0;
        int start = 0; // start index of current valid subarray

        while (start < n) {
            // Rule 1: Must start with even and ≤ threshold
            if (nums[start] % 2 != 0 || nums[start] > threshold) {
                start++;
                continue;
            }

            int end = start;
            // Expand while alternating parity and within threshold
            while (end + 1 < n &&
                   nums[end + 1] <= threshold &&
                   (nums[end] % 2 != nums[end + 1] % 2)) {
                end++;
            }

            // Update maximum length
            maxLen = Math.max(maxLen, end - start + 1);

            // Move start to next index after start (search for next valid subarray)
            start++;
        }

        return maxLen;
    }
}

