/*
 * LeetCode 3471: Largest Integer That Appears in Exactly One Window
 * 
 * Problem:
 * You are given an integer array nums and an integer k. A window is a contiguous
 * subarray of length k.
 * 
 * Return the largest integer that appears in exactly one window. If no such integer
 * exists, return -1.
 * 
 * Approach: Sliding Window with HashMap Counting
 * 
 * Key Insight:
 * - Need to check all subarrays (windows) of length k
 * - For each window, track which numbers appear (using Set to avoid duplicates)
 * - Count how many windows each number appears in
 * - Find the largest number that appears in exactly one window
 * 
 * Algorithm:
 * 1. Iterate through all windows of length k (from index 0 to n-k)
 * 2. For each window:
 *    a. Use Set to track unique numbers in the window (avoid double counting)
 *    b. Update global count map for each unique number
 * 3. Find the largest number with count == 1
 * 4. Return the result (or -1 if none found)
 * 
 * Time Complexity: O(n * k), where n is the length of nums and k is the window size.
 *   - Outer loop: O(n - k + 1) ≈ O(n) iterations
 *   - Inner loop: O(k) to process each window
 *   - Set operations: O(k) per window
 *   - Total: O(n * k)
 * 
 * Space Complexity: O(n)
 *   - HashMap: O(n) to store counts for all numbers
 *   - Set: O(k) for each window (can be reused)
 *   - Overall: O(n)
 * 
 * Example:
 * 
 *   Input: nums = [1, 2, 3, 2, 1], k = 3
 * 
 *   Windows of length 3:
 *   - Window 0: [1, 2, 3] (indices 0-2)
 *     Unique numbers: {1, 2, 3}
 *     Count: 1→1, 2→1, 3→1
 * 
 *   - Window 1: [2, 3, 2] (indices 1-3)
 *     Unique numbers: {2, 3} (2 appears twice but counted once)
 *     Count: 1→1, 2→2, 3→2
 * 
 *   - Window 2: [3, 2, 1] (indices 2-4)
 *     Unique numbers: {1, 2, 3}
 *     Count: 1→2, 2→3, 3→3
 * 
 *   Numbers with count == 1: None (all appear in multiple windows)
 *   Result: -1
 * 
 * Another Example:
 * 
 *   Input: nums = [1, 2, 3, 4, 5], k = 3
 * 
 *   Windows:
 *   - Window 0: [1, 2, 3] → {1, 2, 3}
 *     Count: 1→1, 2→1, 3→1
 * 
 *   - Window 1: [2, 3, 4] → {2, 3, 4}
 *     Count: 1→1, 2→2, 3→2, 4→1
 * 
 *   - Window 2: [3, 4, 5] → {3, 4, 5}
 *     Count: 1→1, 2→2, 3→3, 4→2, 5→1
 * 
 *   Numbers with count == 1: {1, 5}
 *   Largest: 5
 *   Result: 5
 * 
 * Why Use Set in Each Window?
 * 
 *   - A number can appear multiple times in the same window
 *   - We only want to count it once per window
 *   - Example: window [2, 3, 2] should count 2 only once
 *   - Set ensures each number is counted at most once per window
 * 
 * Why Count Windows, Not Occurrences?
 * 
 *   - Problem asks: "appears in exactly one window"
 *   - We need to know how many windows contain the number
 *   - Not how many times it appears total
 *   - Example: number 2 appears in window [2, 3, 2] → count window once
 * 
 * Window Processing:
 * 
 *   For window starting at index i:
 *   - Elements: nums[i], nums[i+1], ..., nums[i+k-1]
 *   - Use Set to get unique numbers: {nums[i], nums[i+1], ..., nums[i+k-1]}
 *   - For each unique number, increment its window count
 * 
 * Finding Result:
 * 
 *   - Iterate through all numbers in countInWindows
 *   - Filter: count == 1 (appears in exactly one window)
 *   - Find maximum among filtered numbers
 *   - Return -1 if no such number exists
 * 
 * Edge Cases:
 * 
 *   - k = 1: Each element is its own window
 *   - k = n: Only one window (entire array)
 *   - All numbers unique: Each appears in k windows (if k > 1)
 *   - All numbers same: One number appears in all windows
 *   - No number appears in exactly one window: Return -1
 * 
 * Optimization Note:
 * 
 *   - Current approach: O(n * k) time
 *   - Could optimize with sliding window technique to O(n) time
 *   - But current approach is clear and correct
 *   - For small k, O(n * k) is acceptable
 * 
 * Alternative: Sliding Window Optimization
 * 
 *   - Maintain a window as we slide
 *   - Remove left element, add right element
 *   - Update counts incrementally
 *   - Time: O(n) instead of O(n * k)
 *   - More complex but faster for large k
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        Map<Integer, Integer> countInWindows = new HashMap<>();

        // Step 1: Check each subarray of length k
        for (int i = 0; i <= n - k; i++) {
            // Use a set to avoid double counting same number in the same window
            Set<Integer> seen = new HashSet<>();

            for (int j = i; j < i + k; j++) {
                seen.add(nums[j]);
            }

            // Update global counts
            for (int num : seen) {
                countInWindows.put(num, countInWindows.getOrDefault(num, 0) + 1);
            }
        }

        // Step 2: Find largest number that appears in exactly one window
        int result = -1;
        for (Map.Entry<Integer, Integer> entry : countInWindows.entrySet()) {
            if (entry.getValue() == 1) {
                result = Math.max(result, entry.getKey());
            }
        }

        return result;
    }
}

