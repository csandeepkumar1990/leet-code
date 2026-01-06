/*
 * LeetCode 3069: Distribute Elements Into Two Arrays
 * 
 * Problem:
 * You are given an array nums of distinct integers. You need to distribute the elements
 * of nums into two arrays arr1 and arr2 such that:
 * 
 * 1. The first element goes to arr1
 * 2. The second element goes to arr2
 * 3. For each subsequent element nums[i] (i >= 2):
 *    - If the last element of arr1 is greater than the last element of arr2,
 *      add nums[i] to arr1
 *    - Otherwise, add nums[i] to arr2
 * 
 * Return the array formed by concatenating arr1 and arr2 (arr1 followed by arr2).
 * 
 * Approach: Two-Array Distribution with Comparison
 * 
 * Key Insight:
 * - Use two ArrayLists to maintain arr1 and arr2
 * - Compare the last elements of both arrays to decide where to place the next element
 * - If arr1's last element > arr2's last element: add to arr1
 * - Otherwise (<=): add to arr2
 * - Finally, concatenate arr2 to arr1 and convert to array
 * 
 * Algorithm:
 * 1. Initialize two ArrayLists: arr1 and arr2
 * 2. Add nums[0] to arr1, nums[1] to arr2
 * 3. For each element nums[i] from index 2 onwards:
 *    a. Get the last elements of arr1 and arr2
 *    b. If last1 > last2: add nums[i] to arr1
 *    c. Otherwise: add nums[i] to arr2
 * 4. Concatenate arr2 to arr1
 * 5. Convert ArrayList to int[] and return
 * 
 * Time Complexity: O(n)
 *   - Single pass through nums: O(n)
 *   - ArrayList operations (add, get): O(1) amortized
 *   - Concatenation: O(m) where m is size of arr2
 *   - Array conversion: O(n)
 *   - Total: O(n)
 * 
 * Space Complexity: O(n)
 *   - Two ArrayLists storing all elements: O(n)
 *   - Result array: O(n)
 *   - Total: O(n)
 * 
 * Example:
 * 
 *   Input: nums = [2, 1, 3]
 * 
 *   Step 1: Initialize
 *     arr1 = [2]
 *     arr2 = [1]
 * 
 *   Step 2: Process nums[2] = 3
 *     last1 = 2, last2 = 1
 *     Since 2 > 1, add 3 to arr1
 *     arr1 = [2, 3]
 *     arr2 = [1]
 * 
 *   Step 3: Concatenate
 *     arr1.addAll(arr2) → arr1 = [2, 3, 1]
 * 
 *   Result: [2, 3, 1]
 * 
 * Another Example:
 * 
 *   Input: nums = [5, 4, 3, 8]
 * 
 *   Step 1: Initialize
 *     arr1 = [5]
 *     arr2 = [4]
 * 
 *   Step 2: Process nums[2] = 3
 *     last1 = 5, last2 = 4
 *     Since 5 > 4, add 3 to arr1
 *     arr1 = [5, 3]
 *     arr2 = [4]
 * 
 *   Step 3: Process nums[3] = 8
 *     last1 = 3, last2 = 4
 *     Since 3 <= 4, add 8 to arr2
 *     arr1 = [5, 3]
 *     arr2 = [4, 8]
 * 
 *   Step 4: Concatenate
 *     arr1.addAll(arr2) → arr1 = [5, 3, 4, 8]
 * 
 *   Result: [5, 3, 4, 8]
 * 
 * Visual Representation:
 * 
 *   nums = [5, 4, 3, 8]
 * 
 *   Initial:
 *     arr1: [5]
 *     arr2: [4]
 * 
 *   Process 3:
 *     Compare: 5 > 4? Yes → add 3 to arr1
 *     arr1: [5, 3]
 *     arr2: [4]
 * 
 *   Process 8:
 *     Compare: 3 > 4? No → add 8 to arr2
 *     arr1: [5, 3]
 *     arr2: [4, 8]
 * 
 *   Final:
 *     arr1: [5, 3, 4, 8]
 * 
 * Edge Cases:
 * 
 * 1. Minimum input (n = 2):
 *    nums = [1, 2]
 *    arr1 = [1], arr2 = [2]
 *    Result: [1, 2]
 * 
 * 2. All elements go to arr1:
 *    nums = [5, 1, 4, 3, 2]
 *    arr1 = [5], arr2 = [1]
 *    Process 4: 5 > 1 → arr1 = [5, 4]
 *    Process 3: 4 > 1 → arr1 = [5, 4, 3]
 *    Process 2: 3 > 1 → arr1 = [5, 4, 3, 2]
 *    Result: [5, 4, 3, 2, 1]
 * 
 * 3. All elements go to arr2 (after first two):
 *    nums = [1, 5, 2, 3, 4]
 *    arr1 = [1], arr2 = [5]
 *    Process 2: 1 <= 5 → arr2 = [5, 2]
 *    Process 3: 1 <= 2 → arr2 = [5, 2, 3]
 *    Process 4: 1 <= 3 → arr2 = [5, 2, 3, 4]
 *    Result: [1, 5, 2, 3, 4]
 * 
 * 4. Alternating pattern:
 *    nums = [3, 1, 2, 4]
 *    arr1 = [3], arr2 = [1]
 *    Process 2: 3 > 1 → arr1 = [3, 2]
 *    Process 4: 2 > 1 → arr1 = [3, 2, 4]
 *    Result: [3, 2, 4, 1]
 * 
 * Why Compare Last Elements?
 * 
 *   The problem requires comparing the "last" elements of both arrays to decide
 *   where to place the next element. This creates a dynamic distribution pattern
 *   where the decision depends on the current state of both arrays.
 * 
 * Why Use ArrayList?
 * 
 *   - Dynamic sizing: Arrays have fixed size, but we don't know final sizes of arr1 and arr2
 *   - Easy concatenation: ArrayList.addAll() is convenient
 *   - Efficient operations: O(1) amortized for add and get operations
 * 
 * Alternative Approach (Using Arrays):
 * 
 *   Could use two arrays with resizing, but ArrayList is more convenient and
 *   the performance difference is negligible for this problem.
 */

import java.util.ArrayList;

class Solution {
    /**
     * Distributes elements into two arrays based on comparing last elements,
     * then returns the concatenated result.
     * 
     * @param nums The input array of distinct integers
     * @return The concatenated array (arr1 followed by arr2)
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int[] resultArray(int[] nums) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        ArrayList<Integer> arr2 = new ArrayList<>();

        // First two elements: nums[0] goes to arr1, nums[1] goes to arr2
        arr1.add(nums[0]);
        arr2.add(nums[1]);

        // Process remaining elements starting from index 2
        for (int i = 2; i < nums.length; i++) {
            // Get the last elements of both arrays
            int last1 = arr1.get(arr1.size() - 1);
            int last2 = arr2.get(arr2.size() - 1);

            // If arr1's last element is greater, add to arr1
            // Otherwise (less than or equal), add to arr2
            if (last1 > last2) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]); // Handles less or equal case
            }
        }

        // Concatenate arr2 into arr1
        arr1.addAll(arr2);

        // Convert ArrayList to int[]
        int[] result = new int[arr1.size()];
        for (int i = 0; i < arr1.size(); i++) {
            result[i] = arr1.get(i);
        }

        return result;
    }
}

