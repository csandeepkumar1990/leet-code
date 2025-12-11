/*
 * 2899. Last Visited Integers
 * 
 * Problem:
 * Given an integer array nums where nums[i] is either a positive integer or -1.
 * 
 * We need to find repetitively the most recent positive integer that appears
 * before a consecutive sequence of -1s.
 * 
 * More formally:
 * - Let k be the number of consecutive -1s seen so far (including current)
 * - If k positive integers have been seen before, return the k-th most recent one
 * - Otherwise, return -1
 * 
 * Return an array containing all the results for each -1 encountered.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: nums = [1, 2, -1, -1, -1]
 *   Output: [2, 1, -1]
 *   Explanation:
 *     Index 0: nums[0] = 1 → seen = [1]
 *     Index 1: nums[1] = 2 → seen = [2, 1]
 *     Index 2: nums[2] = -1 → k=1, return seen[0] = 2  → ans = [2]
 *     Index 3: nums[3] = -1 → k=2, return seen[1] = 1  → ans = [2, 1]
 *     Index 4: nums[4] = -1 → k=3, seen.size()=2 < 3   → ans = [2, 1, -1]
 * 
 * Example 2:
 *   Input: nums = [1, -1, 2, -1, -1]
 *   Output: [1, 2, 1]
 *   Explanation:
 *     Index 0: nums[0] = 1 → seen = [1]
 *     Index 1: nums[1] = -1 → k=1, return seen[0] = 1  → ans = [1]
 *     Index 2: nums[2] = 2 → k resets, seen = [2, 1]
 *     Index 3: nums[3] = -1 → k=1, return seen[0] = 2  → ans = [1, 2]
 *     Index 4: nums[4] = -1 → k=2, return seen[1] = 1  → ans = [1, 2, 1]
 * 
 * Example 3:
 *   Input: nums = [-1, -1]
 *   Output: [-1, -1]
 *   Explanation: No positive integers seen before any -1.
 * 
 * Constraints:
 *   - 1 <= nums.length <= 100
 *   - nums[i] == -1 or 1 <= nums[i] <= 100
 * 
 * Approach: Maintain Seen List with Consecutive Counter
 * 
 * Key Idea: 
 *   - Track all seen positive integers in reverse order (most recent first)
 *   - Track count of consecutive -1s (k)
 *   - For each -1, retrieve the k-th element from seen list (0-indexed: k-1)
 * 
 * Algorithm:
 * 1. Maintain a 'seen' list with most recent positive at front (index 0)
 * 2. Track 'k' = count of consecutive -1s
 * 3. For each element:
 *    - If positive: add to front of seen, reset k to 0
 *    - If -1: increment k, return k-th most recent (or -1 if not enough)
 * 
 * Time Complexity: O(n²)
 *   - O(n) iterations
 *   - seen.add(0, val) is O(n) for ArrayList insertion at front
 * 
 * Space Complexity: O(n)
 *   - 'seen' list can store up to n positive integers
 *   - 'ans' list stores results for each -1
 */

import java.util.*;

class Solution {

    public List<Integer> lastVisitedIntegers(int[] nums) {

        List<Integer> ans = new ArrayList<>();   // Result list for -1 queries
        List<Integer> seen = new ArrayList<>();  // Positive integers (most recent first)
        int k = 0;                               // Count of consecutive -1s

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] != -1) {
                // Found a positive integer

                k = 0;  // Reset consecutive -1 counter

                // Add to FRONT of list (most recent first)
                // seen.add(0, x) inserts x at index 0, shifting others right
                seen.add(0, nums[i]);

            } else {
                // Found -1, need to return k-th most recent integer

                k++;  // Increment consecutive -1 counter

                // Check if we have enough integers to return the k-th one
                if (seen.size() >= k) {

                    // Return k-th most recent (k-1 because 0-indexed)
                    ans.add(seen.get(k - 1));

                } else {

                    // Not enough positive integers seen
                    ans.add(-1);

                }

            }

        }

        return ans;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: nums = [1, 2, -1, -1, -1]
 * 
 * Initial: ans = [], seen = [], k = 0
 * 
 * i = 0: nums[0] = 1 (positive)
 *        k = 0 (reset)
 *        seen.add(0, 1) → seen = [1]
 *        State: ans = [], seen = [1], k = 0
 * 
 * i = 1: nums[1] = 2 (positive)
 *        k = 0 (reset)
 *        seen.add(0, 2) → seen = [2, 1]  (2 is now most recent)
 *        State: ans = [], seen = [2, 1], k = 0
 * 
 * i = 2: nums[2] = -1
 *        k = 0 + 1 = 1
 *        seen.size() = 2 >= k = 1 ✓
 *        ans.add(seen.get(0)) = ans.add(2)
 *        State: ans = [2], seen = [2, 1], k = 1
 * 
 * i = 3: nums[3] = -1
 *        k = 1 + 1 = 2
 *        seen.size() = 2 >= k = 2 ✓
 *        ans.add(seen.get(1)) = ans.add(1)
 *        State: ans = [2, 1], seen = [2, 1], k = 2
 * 
 * i = 4: nums[4] = -1
 *        k = 2 + 1 = 3
 *        seen.size() = 2 < k = 3 ✗
 *        ans.add(-1)
 *        State: ans = [2, 1, -1], seen = [2, 1], k = 3
 * 
 * Output: [2, 1, -1]
 * 
 * 
 * Visualization of 'seen' list behavior:
 * 
 * seen = [most_recent, second_recent, third_recent, ...]
 *         index 0      index 1        index 2
 * 
 * When k consecutive -1s occur:
 *   k=1 → return seen[0] (most recent)
 *   k=2 → return seen[1] (second most recent)
 *   k=3 → return seen[2] (third most recent)
 *   ...
 * 
 * 
 * Alternative Approach (Using Deque for O(1) front insertion):
 * 
 * public List<Integer> lastVisitedIntegers(int[] nums) {
 *     List<Integer> ans = new ArrayList<>();
 *     Deque<Integer> seen = new ArrayDeque<>();
 *     int k = 0;
 *     
 *     for (int num : nums) {
 *         if (num != -1) {
 *             k = 0;
 *             seen.addFirst(num);  // O(1) insertion at front
 *         } else {
 *             k++;
 *             if (seen.size() >= k) {
 *                 // Convert deque to list for index access, or use iterator
 *                 int idx = 0;
 *                 for (int val : seen) {
 *                     if (idx == k - 1) {
 *                         ans.add(val);
 *                         break;
 *                     }
 *                     idx++;
 *                 }
 *             } else {
 *                 ans.add(-1);
 *             }
 *         }
 *     }
 *     return ans;
 * }
 * 
 * Your ArrayList approach is actually simpler for random access with get(k-1)!
 */

