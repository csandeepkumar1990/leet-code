/*
 * LeetCode 3318: Find X-Sum of All K-Long Subarrays I
 *
 * Problem:
 * Given an array nums, and integers k and x, for every contiguous subarray (window)
 * of length k, compute its "X-sum":
 *
 * - In that window, count the frequency of each distinct number.
 * - Sort the distinct numbers by:
 *     1) Higher frequency first
 *     2) If frequencies tie, larger number first
 * - Take the first x numbers from this sorted list (or all if fewer than x)
 * - The X-sum is the sum over (value * frequency) for these chosen numbers.
 *
 * Return an array answer where answer[i] is the X-sum of the subarray nums[i..i+k-1].
 *
 * Approach: Brute-force per window with frequency map
 *
 * For each starting index i of a k-length window:
 * 1. Build a frequency map of values nums[j] for j in [i, i + k - 1].
 * 2. Convert the map entries into a list of (value, frequency) pairs.
 * 3. Sort this list:
 *      - Primary key: frequency descending
 *      - Secondary key: value descending
 * 4. Take up to x entries from the head of the list and accumulate sum += value * frequency.
 * 5. Store the sum in answer[i].
 *
 * Time Complexity:
 * - There are (n - k + 1) windows.
 * - For each window:
 *     - O(k) to build the frequency map
 *     - O(m log m) to sort, where m is number of distinct values in the window (m ≤ k)
 * - Overall worst-case: O((n - k + 1) * (k + k log k)).
 *
 * Space Complexity:
 * - O(k) for the frequency map and list per window.
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public int[] findXSum(int[] nums, int k, int x) {
        int n = nums.length;
        int[] answer = new int[n - k + 1];

        for (int i = 0; i <= n - k; i++) {
            // Step 1: Count frequency of numbers in current window
            Map<Integer, Integer> freq = new HashMap<>();
            for (int j = i; j < i + k; j++) {
                freq.put(nums[j], freq.getOrDefault(nums[j], 0) + 1);
            }

            // Step 2: Sort by frequency desc, then value desc
            List<int[]> freqList = new ArrayList<>();
            for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
                freqList.add(new int[] { entry.getKey(), entry.getValue() });
            }

            freqList.sort((a, b) -> {
                if (b[1] != a[1]) {
                    return b[1] - a[1]; // higher frequency first
                }
                return b[0] - a[0]; // if tie, bigger value first
            });

            // Step 3: Pick top x and sum their occurrences
            int sum = 0;
            int picked = 0;
            for (int[] pair : freqList) {
                if (picked < x) {
                    sum += pair[0] * pair[1]; // value × frequency
                    picked++;
                } else {
                    break;
                }
            }

            answer[i] = sum;
        }

        return answer;
    }
}


