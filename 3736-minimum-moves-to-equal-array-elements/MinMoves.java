/*
 * 3736. Minimum Moves to Equal Array Elements
 * 
 * Problem:
 * Given an integer array nums, return the minimum number of moves required
 * to make all array elements equal.
 * 
 * In one move, you can increment one element by 1.
 * 
 * The optimal strategy is to make all elements equal to the maximum value,
 * since we can only increment (not decrement).
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: nums = [1, 2, 3]
 *   Output: 3
 *   Explanation:
 *     Target = max = 3
 *     Element 1: needs 3 - 1 = 2 moves
 *     Element 2: needs 3 - 2 = 1 move
 *     Element 3: needs 3 - 3 = 0 moves
 *     Total = 2 + 1 + 0 = 3 moves
 * 
 * Example 2:
 *   Input: nums = [1, 1, 1]
 *   Output: 0
 *   Explanation: All elements already equal. No moves needed.
 * 
 * Example 3:
 *   Input: nums = [5, 10]
 *   Output: 5
 *   Explanation:
 *     Target = max = 10
 *     5 needs 10 - 5 = 5 moves
 *     Total = 5 moves
 * 
 * Example 4:
 *   Input: nums = [1, 5, 5, 5]
 *   Output: 4
 *   Explanation:
 *     Target = max = 5
 *     1 needs 5 - 1 = 4 moves
 *     5, 5, 5 need 0 moves each
 *     Total = 4 moves
 * 
 * Example 5:
 *   Input: nums = [2, 4, 6, 8, 10]
 *   Output: 20
 *   Explanation:
 *     Target = max = 10
 *     Moves: (10-2) + (10-4) + (10-6) + (10-8) + (10-10)
 *          = 8 + 6 + 4 + 2 + 0 = 20
 * 
 * Constraints:
 *   - 1 <= nums.length <= 10^5
 *   - 1 <= nums[i] <= 10^9
 * 
 * Approach: Sum of Differences from Maximum
 * 
 * Key Insight: Since we can only INCREMENT elements, the target must be
 * the maximum value (we can't decrease the max to meet others).
 * 
 * Algorithm:
 * 1. Find the maximum value in the array
 * 2. For each element, calculate how many increments needed: max - element
 * 3. Sum all the differences
 * 
 * Mathematical Formula:
 *   moves = Σ(max - nums[i]) for all i
 *         = n * max - Σ(nums[i])
 *         = n * max - sum(nums)
 * 
 * Time Complexity: O(n)
 *   - O(n) to find max (or O(n log n) if sorting)
 *   - O(n) to sum differences
 * 
 * Space Complexity: O(1)
 *   - Only a few variables (excluding sort's internal space)
 */

import java.util.*;

class Solution {

    public int minMoves(int[] nums) {

        // Sort array (optional - could just find max directly)
        // After sorting, max is at the last position
        Arrays.sort(nums);

        int n = nums.length;

        // Maximum value is the target (last element after sorting)
        int maxValue = nums[n - 1];

        // Count total moves needed
        int moves = 0;

        // For each element, add the difference from max
        for (int num : nums) {

            // Each element needs (maxValue - num) increments to reach max
            moves += maxValue - num;

        }

        return moves;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: nums = [1, 2, 3]
 * 
 * Step 1: Sort (already sorted)
 *   nums = [1, 2, 3]
 * 
 * Step 2: Find max
 *   maxValue = nums[2] = 3
 * 
 * Step 3: Calculate moves
 *   Initial: moves = 0
 *   
 *   num = 1: moves += 3 - 1 = 0 + 2 = 2
 *   num = 2: moves += 3 - 2 = 2 + 1 = 3
 *   num = 3: moves += 3 - 3 = 3 + 0 = 3
 * 
 * Output: 3
 * 
 * 
 * Visualization:
 * 
 * Initial:  [1, 2, 3]
 *            ↓  ↓  ↓
 * Target:   [3, 3, 3]
 * 
 *   1 → 3: +2 moves  █░░ → ███
 *   2 → 3: +1 move   ██░ → ███
 *   3 → 3: +0 moves  ███ → ███
 *                    ─────────
 *                    Total: 3 moves
 * 
 * 
 * Why Target Must Be Maximum?
 * 
 * Since we can only INCREMENT (add 1), we cannot reduce any element.
 * Therefore:
 *   - The target must be >= every element
 *   - The minimum such target is the maximum element
 *   - Any target > max would require extra unnecessary moves
 * 
 * Example: nums = [1, 5]
 *   Target = 5 (max): moves = 4 (optimal)
 *   Target = 6: moves = 5 + 1 = 6 (suboptimal)
 *   Target = 4: impossible! (can't reduce 5 to 4)
 * 
 * 
 * Mathematical Insight:
 * 
 * moves = Σ(max - nums[i])
 *       = (max - nums[0]) + (max - nums[1]) + ... + (max - nums[n-1])
 *       = n * max - (nums[0] + nums[1] + ... + nums[n-1])
 *       = n * max - sum(nums)
 * 
 * This formula shows moves = n * max - totalSum
 * 
 * 
 * Alternative Approach (Without Sorting - O(n)):
 * 
 * public int minMoves(int[] nums) {
 *     int max = nums[0];
 *     int sum = 0;
 *     
 *     // Find max and sum in single pass
 *     for (int num : nums) {
 *         max = Math.max(max, num);
 *         sum += num;
 *     }
 *     
 *     // moves = n * max - sum
 *     return nums.length * max - sum;
 * }
 * 
 * This avoids sorting and runs in pure O(n) time!
 * Uses the mathematical formula directly.
 * 
 * 
 * Note on Sorting:
 * 
 * Your solution sorts the array which costs O(n log n).
 * The sort isn't strictly necessary - you could find max in O(n).
 * However, sorting can be useful if you need other insights
 * (like median, or if the problem had different requirements).
 */

