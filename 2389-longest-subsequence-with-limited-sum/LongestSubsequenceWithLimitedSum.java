/*
 * LeetCode 2389: Longest Subsequence With Limited Sum
 *
 * Problem:
 * You are given an integer array nums of length n, and an integer array queries
 * of length m.
 *
 * Return an array answer of length m where answer[i] is the maximum size of a
 * subsequence that you can take from nums such that the sum of its elements is
 * less than or equal to queries[i].
 *
 * A subsequence is an array that can be derived from another array by deleting
 * some or no elements without changing the order of the remaining elements.
 *
 * Approach: Greedy with Sorting
 *
 * Key Insight:
 * - To maximize the length of a subsequence with sum <= query, we should take
 *   the smallest elements first
 * - Sort the array so we can greedily add the smallest numbers
 * - For each query, count how many smallest numbers we can add without exceeding
 *   the query limit
 *
 * Algorithm:
 * 1. Sort nums array in ascending order
 * 2. For each query:
 *    a. Initialize sum = 0, count = 0
 *    b. Iterate through sorted nums:
 *       - If sum + current number <= query, add it and increment count
 *       - Otherwise, break (can't add more)
 *    c. Store count in result
 * 3. Return result array
 *
 * Time Complexity: O(n log n + m * n), where n is the length of nums and m is
 *   the length of queries.
 *   - Sorting: O(n log n)
 *   - For each query, we iterate through nums: O(m * n)
 * Space Complexity: O(m) for the result array (excluding input arrays).
 *
 * Note: This can be optimized to O(n log n + m log n) using prefix sums and
 * binary search, but this greedy approach is straightforward and easy to understand.
 *
 * Example:
 *   Input: nums = [4, 5, 2, 1], queries = [3, 10, 21]
 *
 *   After sorting: nums = [1, 2, 4, 5]
 *
 *   Query 0 (limit = 3):
 *     - Add 1: sum = 1, count = 1
 *     - Add 2: sum = 3, count = 2
 *     - Can't add 4 (3 + 4 = 7 > 3)
 *     - Answer: 2
 *
 *   Query 1 (limit = 10):
 *     - Add 1: sum = 1, count = 1
 *     - Add 2: sum = 3, count = 2
 *     - Add 4: sum = 7, count = 3
 *     - Can't add 5 (7 + 5 = 12 > 10)
 *     - Answer: 3
 *
 *   Query 2 (limit = 21):
 *     - Add all: 1 + 2 + 4 + 5 = 12 <= 21
 *     - Answer: 4
 *
 *   Output: [2, 3, 4]
 *
 * Why Sorting Works:
 * - Taking the smallest elements first maximizes the count of elements we can
 *   include in the subsequence
 * - This is optimal because if we skip a smaller element to take a larger one,
 *   we can't include as many total elements
 */

import java.util.Arrays;

class Solution {

    public int[] answerQueries(int[] nums, int[] queries) {
        Arrays.sort(nums); // Sort so smallest numbers come first

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int sum = 0, count = 0;
            for (int num : nums) {
                if (sum + num <= queries[i]) {
                    sum += num;
                    count++;
                } else {
                    break; // can't add more numbers
                }
            }
            result[i] = count;
        }

        return result;
    }
}

