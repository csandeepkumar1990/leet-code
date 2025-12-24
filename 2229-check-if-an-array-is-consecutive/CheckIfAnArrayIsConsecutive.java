/*
 * LeetCode 2229: Check if an Array Is Consecutive
 *
 * Problem:
 * Given an integer array nums, return true if nums is consecutive, otherwise
 * return false.
 *
 * An array is consecutive if it contains every number in the range
 * [x, x + n - 1] (inclusive), where x is the minimum number in the array and
 * n is the length of the array.
 *
 * Approach: Range and Duplicate Check
 *
 * Key Insight:
 * - An array is consecutive if:
 *   1. It contains no duplicates
 *   2. The range from min to max equals the array length
 * - If both conditions are true, the array must contain all numbers from
 *   min to max exactly once
 *
 * Algorithm:
 * 1. Find the minimum and maximum values in the array
 * 2. Track all numbers in a Set to detect duplicates
 * 3. Check two conditions:
 *    a. Range check: (max - min + 1) == nums.length
 *    b. No duplicates: set.size() == nums.length
 * 4. Return true if both conditions are met
 *
 * Why This Works:
 * - If range (max - min + 1) == length, then the array should contain
 *   exactly all numbers from min to max
 * - If set.size() == length, there are no duplicates
 * - Together, these ensure the array contains each number from min to max
 *   exactly once (consecutive)
 *
 * Time Complexity: O(n), where n is the length of nums.
 *   - Single pass to find min, max, and build set: O(n)
 *   - Set operations are O(1) average case
 * Space Complexity: O(n) for the HashSet.
 *
 * Example:
 *
 *   Input: nums = [1, 3, 4, 2]
 *
 *   Step 1: Find min, max, and build set
 *     min = 1, max = 4
 *     set = {1, 3, 4, 2}
 *
 *   Step 2: Check conditions
 *     Range: max - min + 1 = 4 - 1 + 1 = 4 == nums.length = 4 ✓
 *     No duplicates: set.size() = 4 == nums.length = 4 ✓
 *
 *   Output: true
 *   Explanation: Array contains all numbers from 1 to 4 exactly once.
 *
 *   Input: nums = [1, 3]
 *
 *   Step 1: Find min, max, and build set
 *     min = 1, max = 3
 *     set = {1, 3}
 *
 *   Step 2: Check conditions
 *     Range: max - min + 1 = 3 - 1 + 1 = 3 != nums.length = 2 ✗
 *
 *   Output: false
 *   Explanation: Missing number 2 in the range [1, 3].
 *
 *   Input: nums = [1, 2, 2, 3]
 *
 *   Step 1: Find min, max, and build set
 *     min = 1, max = 3
 *     set = {1, 2, 3} (duplicate 2 removed)
 *
 *   Step 2: Check conditions
 *     Range: max - min + 1 = 3 - 1 + 1 = 3 != nums.length = 4 ✗
 *     No duplicates: set.size() = 3 != nums.length = 4 ✗
 *
 *   Output: false
 *   Explanation: Contains duplicate 2.
 *
 * Edge Cases:
 * - Empty array: return false
 * - Single element: return true (range [x, x] has length 1)
 * - Negative numbers: works correctly (min can be negative)
 *
 * Important Notes:
 * - "Consecutive" means all numbers from min to max are present exactly once
 * - The range check ensures no gaps in the sequence
 * - The duplicate check ensures no repeated numbers
 * - Both conditions together guarantee consecutiveness
 */

import java.util.HashSet;
import java.util.Set;

class Solution {

    public boolean isConsecutive(int[] nums) {
        if (nums == null || nums.length == 0)
            return false;

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            set.add(num);
        }

        // Check if range matches and no duplicates
        return (max - min + 1 == nums.length) && (set.size() == nums.length);
    }
}

