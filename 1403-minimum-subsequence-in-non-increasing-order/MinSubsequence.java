/*
 * LeetCode 1403: Minimum Subsequence in Non-Increasing Order
 * 
 * Problem:
 * Given the array nums, obtain a subsequence of the array whose sum of elements
 * is strictly greater than the sum of the non included elements in such subsequence.
 * 
 * If there are multiple solutions, return the subsequence with minimum size and if
 * there still exist multiple solutions, return the subsequence with the maximum
 * total sum of all its elements. A subsequence of an array can be obtained by
 * erasing some (possibly zero) elements from the array.
 * 
 * Note that the solution with the given constraints is guaranteed to be unique.
 * Also return the answer sorted in non-increasing order.
 * 
 * Approach: Greedy - Pick Largest Elements First
 * 
 * Key Insight:
 * - To minimize subsequence size while maximizing sum, always pick largest elements
 * - Sort array, then pick from largest to smallest until condition is met
 * - Condition: subsequenceSum > remainingSum
 *   Which is equivalent to: subsequenceSum > totalSum - subsequenceSum
 *   Which simplifies to: 2 * subsequenceSum > totalSum
 * 
 * Algorithm:
 * 1. Sort array in ascending order
 * 2. Calculate total sum of all elements
 * 3. Iterate from largest to smallest (backwards)
 * 4. Add elements to subsequence until sum > remaining sum
 * 5. Return subsequence (already in non-increasing order)
 * 
 * Time Complexity: O(n log n)
 *   - Sorting: O(n log n)
 *   - Sum calculation and iteration: O(n)
 * 
 * Space Complexity: O(n)
 *   - Result list (worst case: all elements)
 * 
 * Example 1:
 *   nums = [4, 3, 10, 9, 8]
 *   
 *   Step 1: Sort → [3, 4, 8, 9, 10]
 *   Step 2: Total sum = 34
 *   
 *   Step 3: Pick from largest:
 *   - Pick 10: subseqSum = 10, remaining = 24, 10 > 24? No
 *   - Pick 9: subseqSum = 19, remaining = 15, 19 > 15? Yes ✓
 *   
 *   Result: [10, 9]
 * 
 * Example 2:
 *   nums = [4, 4, 7, 6, 7]
 *   
 *   Step 1: Sort → [4, 4, 6, 7, 7]
 *   Step 2: Total sum = 28
 *   
 *   Step 3: Pick from largest:
 *   - Pick 7: subseqSum = 7, remaining = 21, 7 > 21? No
 *   - Pick 7: subseqSum = 14, remaining = 14, 14 > 14? No
 *   - Pick 6: subseqSum = 20, remaining = 8, 20 > 8? Yes ✓
 *   
 *   Result: [7, 7, 6]
 * 
 * Example 3:
 *   nums = [6]
 *   
 *   Total sum = 6
 *   Pick 6: subseqSum = 6, remaining = 0, 6 > 0? Yes ✓
 *   
 *   Result: [6]
 * 
 * Why Greedy Works?
 * 
 *   Goal: Minimize size, maximize sum
 *   
 *   - If we need sum > remaining, we want largest possible elements
 *   - Picking largest elements first minimizes the count needed
 *   - This is optimal because:
 *     * Any smaller element would require more elements to reach threshold
 *     * Largest elements contribute most to sum per element
 * 
 * Why Sort Ascending Then Iterate Backwards?
 * 
 *   - Arrays.sort() sorts in ascending order
 *   - We want largest first, so iterate from end to start
 *   - Result is naturally in non-increasing order
 * 
 * Condition Check:
 * 
 *   We stop when: subseqSum > totalSum - subseqSum
 *   
 *   Rearranging: 2 * subseqSum > totalSum
 *   
 *   This means: subseqSum > totalSum / 2
 *   
 *   So we need more than half the total sum!
 * 
 * Visual Example (nums = [4, 3, 10, 9, 8]):
 * 
 *   Sorted: [3, 4, 8, 9, 10]
 *   Total: 34
 *   
 *   Iteration:
 *   i=4: pick 10 → sum=10, remaining=24 → continue
 *   i=3: pick 9  → sum=19, remaining=15 → 19 > 15 ✓ STOP
 *   
 *   Result: [10, 9]
 * 
 * Why This Guarantees Minimum Size?
 * 
 *   - We always pick the largest available element
 *   - This maximizes sum per element
 *   - Therefore minimizes number of elements needed
 *   - No smaller element can achieve the same with fewer picks
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {

    public List<Integer> minSubsequence(int[] nums) {

        Arrays.sort(nums); // Sort ascending first

        int totalSum = 0;

        for (int num : nums) {

            totalSum += num;

        }

        List<Integer> result = new ArrayList<>();

        int subseqSum = 0;

        // Pick from largest to smallest
        for (int i = nums.length - 1; i >= 0; i--) {

            subseqSum += nums[i];

            result.add(nums[i]);

            // Check if subsequence sum exceeds remaining sum
            if (subseqSum > totalSum - subseqSum) {

                break;

            }

        }

        return result; // Already in non-increasing order

    }

}

