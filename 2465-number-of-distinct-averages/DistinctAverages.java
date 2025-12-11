/*
 * 2465. Number of Distinct Averages
 * 
 * Problem:
 * You are given a 0-indexed integer array nums of even length.
 * 
 * As long as nums is not empty, you must repetitively:
 *   1. Find the minimum number and remove it
 *   2. Find the maximum number and remove it
 *   3. Calculate the average of these two numbers
 * 
 * The average of two numbers a and b is (a + b) / 2.
 * 
 * Return the number of DISTINCT averages calculated.
 * 
 * Note: The test cases are generated such that the answer fits in a 32-bit integer.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: nums = [4, 1, 4, 0, 3, 5]
 *   Output: 2
 *   Explanation:
 *     After sorting: [0, 1, 3, 4, 4, 5]
 *     
 *     Round 1: min=0, max=5, avg=(0+5)/2 = 2.5
 *     Round 2: min=1, max=4, avg=(1+4)/2 = 2.5
 *     Round 3: min=3, max=4, avg=(3+4)/2 = 3.5
 *     
 *     Distinct averages: {2.5, 3.5} → 2
 * 
 * Example 2:
 *   Input: nums = [1, 100]
 *   Output: 1
 *   Explanation:
 *     Only one pair: min=1, max=100
 *     avg = (1+100)/2 = 50.5
 *     Distinct averages: {50.5} → 1
 * 
 * Example 3:
 *   Input: nums = [2, 2, 2, 2]
 *   Output: 1
 *   Explanation:
 *     After sorting: [2, 2, 2, 2]
 *     Round 1: avg = (2+2)/2 = 2.0
 *     Round 2: avg = (2+2)/2 = 2.0
 *     Distinct averages: {2.0} → 1
 * 
 * Example 4:
 *   Input: nums = [1, 2, 3, 4, 5, 6]
 *   Output: 3
 *   Explanation:
 *     After sorting: [1, 2, 3, 4, 5, 6]
 *     Round 1: (1+6)/2 = 3.5
 *     Round 2: (2+5)/2 = 3.5
 *     Round 3: (3+4)/2 = 3.5
 *     Distinct averages: {3.5} → 1
 *     Wait! All same? Yes - symmetric distribution around center!
 * 
 * Constraints:
 *   - 2 <= nums.length <= 100
 *   - nums.length is even
 *   - 0 <= nums[i] <= 100
 * 
 * Approach: Sort + Two Pointers + HashSet
 * 
 * Key Insight: After sorting, min is always at left, max is always at right.
 * Instead of actually removing elements, use two pointers!
 * 
 * Algorithm:
 * 1. Sort the array (min at start, max at end)
 * 2. Use two pointers: left (min) and right (max)
 * 3. Calculate average, add to HashSet (handles duplicates)
 * 4. Move pointers inward (simulates removal)
 * 5. Return size of HashSet
 * 
 * Why sort? After sorting:
 *   - nums[left] is always the current minimum
 *   - nums[right] is always the current maximum
 *   - No need to search for min/max each time
 * 
 * Time Complexity: O(n log n)
 *   - O(n log n) for sorting
 *   - O(n) for two-pointer traversal
 * 
 * Space Complexity: O(n)
 *   - HashSet stores at most n/2 averages
 */

import java.util.*;

class Solution {

    public int distinctAverages(int[] nums) {

        // Sort array so min is at front, max is at back
        Arrays.sort(nums);

        // HashSet to store distinct averages (auto-handles duplicates)
        Set<Double> distinct = new HashSet<>();

        // Two pointers: left for min, right for max
        int left = 0, right = nums.length - 1;

        // Process pairs until pointers meet
        while (left < right) {

            // Calculate average of current min and max
            // IMPORTANT: Divide by 2.0 (not 2) to get double division
            // Integer division would truncate: (3+4)/2 = 3, not 3.5
            double avg = (nums[left] + nums[right]) / 2.0;

            // Add to set (duplicates automatically ignored)
            distinct.add(avg);

            // Move pointers inward (simulates removing min and max)
            left++;
            right--;

        }

        // Return count of distinct averages
        return distinct.size();

    }

}

/*
 * Dry Run Example:
 * 
 * Input: nums = [4, 1, 4, 0, 3, 5]
 * 
 * Step 1: Sort
 *   nums = [0, 1, 3, 4, 4, 5]
 * 
 * Initial: left = 0, right = 5, distinct = {}
 * 
 * Iteration 1:
 *   left = 0, right = 5
 *   nums[left] = 0 (min), nums[right] = 5 (max)
 *   avg = (0 + 5) / 2.0 = 2.5
 *   distinct = {2.5}
 *   left++, right-- → left = 1, right = 4
 * 
 * Iteration 2:
 *   left = 1, right = 4
 *   nums[left] = 1 (min), nums[right] = 4 (max)
 *   avg = (1 + 4) / 2.0 = 2.5
 *   distinct = {2.5} (2.5 already exists, no change)
 *   left++, right-- → left = 2, right = 3
 * 
 * Iteration 3:
 *   left = 2, right = 3
 *   nums[left] = 3 (min), nums[right] = 4 (max)
 *   avg = (3 + 4) / 2.0 = 3.5
 *   distinct = {2.5, 3.5}
 *   left++, right-- → left = 3, right = 2
 * 
 * Loop ends (left >= right)
 * 
 * Return distinct.size() = 2
 * 
 * Output: 2
 * 
 * 
 * Why Two Pointers Work:
 * 
 * After sorting: [min₁, min₂, ..., max₂, max₁]
 *                  ↑                       ↑
 *                left                    right
 * 
 * Round 1: Pair (min₁, max₁) → left++, right--
 * Round 2: Pair (min₂, max₂) → left++, right--
 * ...
 * 
 * Each round automatically picks current min and max!
 * 
 * 
 * Important: Integer vs Double Division
 * 
 * WRONG: (nums[left] + nums[right]) / 2
 *        This is integer division! (3+4)/2 = 3 (truncated)
 * 
 * CORRECT: (nums[left] + nums[right]) / 2.0
 *          Dividing by 2.0 forces double division: (3+4)/2.0 = 3.5
 * 
 * 
 * Alternative: Using sum instead of average
 * 
 * public int distinctAverages(int[] nums) {
 *     Arrays.sort(nums);
 *     Set<Integer> distinct = new HashSet<>();
 *     
 *     int left = 0, right = nums.length - 1;
 *     while (left < right) {
 *         // Store sum instead of average (avoids floating point)
 *         // Same sum → same average, distinct sums → distinct averages
 *         distinct.add(nums[left] + nums[right]);
 *         left++;
 *         right--;
 *     }
 *     
 *     return distinct.size();
 * }
 * 
 * This avoids floating point precision issues entirely!
 */

