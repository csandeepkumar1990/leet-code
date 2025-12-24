/*
 * LeetCode 3507: Minimum Pair Removal to Sort Array I
 *
 * Problem:
 * You are given an array nums. You can perform the following operation any
 * number of times:
 *
 * - Select the adjacent pair with the minimum sum in nums. If multiple such
 *   pairs exist, choose the leftmost one.
 * - Replace the pair with their sum.
 *
 * Return the minimum number of operations needed to make the array
 * non-decreasing.
 *
 * An array is non-decreasing if each element is greater than or equal to the
 * previous element.
 *
 * Approach: Greedy Algorithm
 *
 * Key Insight:
 * - Repeatedly find the adjacent pair with minimum sum
 * - Replace that pair with their sum (reduces array size by 1)
 * - Continue until array is non-decreasing
 * - Greedy choice: always merge the pair with minimum sum
 *
 * Algorithm:
 * 1. While array is not non-decreasing:
 *    a. Check if array is non-decreasing
 *    b. Find the adjacent pair with minimum sum (leftmost if ties)
 *    c. Replace the pair with their sum
 *    d. Increment operation count
 * 2. Return total number of operations
 *
 * Time Complexity: O(n^3) in the worst case, where n is the length of nums.
 *   - Each operation reduces array size by 1: O(n) operations
 *   - Each operation checks non-decreasing: O(n)
 *   - Each operation finds minimum pair: O(n)
 *   - Total: O(n^3)
 * Space Complexity: O(n) for the list.
 *
 * Example:
 *
 *   Input: nums = [5, 2, 3, 1]
 *
 *   Operation 1:
 *     Check: [5, 2, 3, 1] is not non-decreasing (2 < 5, 1 < 3)
 *     Find minimum sum pairs:
 *       (5, 2) = 7
 *       (2, 3) = 5
 *       (3, 1) = 4 ← minimum
 *     Replace (3, 1) with 4: [5, 2, 4]
 *
 *   Operation 2:
 *     Check: [5, 2, 4] is not non-decreasing (2 < 5)
 *     Find minimum sum pairs:
 *       (5, 2) = 7
 *       (2, 4) = 6 ← minimum
 *     Replace (2, 4) with 6: [5, 6]
 *
 *   Operation 3:
 *     Check: [5, 6] is non-decreasing ✓
 *     Done!
 *
 *   Output: 2
 *
 * Why Greedy Works?
 * - Merging the minimum sum pair minimizes the impact on the array
 * - Smaller sums are less likely to create new inversions
 * - The process eventually terminates when array becomes non-decreasing
 *
 * Important Notes:
 * - "Non-decreasing" means each element >= previous element
 * - We check the entire array after each operation
 * - If multiple pairs have the same minimum sum, choose leftmost
 * - Each operation reduces array size by 1
 * - The algorithm terminates when array is sorted
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public int minimumPairRemoval(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for (int num : nums) list.add(num);

        int operations = 0;

        while (true) {
            // Check if array is non-decreasing
            boolean nonDecreasing = true;
            for (int i = 1; i < list.size(); i++) {
                if (list.get(i) < list.get(i - 1)) {
                    nonDecreasing = false;
                    break;
                }
            }
            if (nonDecreasing) break;

            // Find the adjacent pair with minimum sum
            int minSum = Integer.MAX_VALUE;
            int index = -1;
            for (int i = 0; i < list.size() - 1; i++) {
                int sum = list.get(i) + list.get(i + 1);
                if (sum < minSum) {
                    minSum = sum;
                    index = i;
                }
            }

            // Replace the pair with their sum
            list.set(index, list.get(index) + list.get(index + 1));
            list.remove(index + 1);
            operations++;
        }

        return operations;
    }
}

