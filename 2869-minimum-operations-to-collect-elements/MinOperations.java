/*
 * 2869. Minimum Operations to Collect Elements
 * 
 * Problem:
 * You are given a 0-indexed array nums of positive integers and an integer k.
 * 
 * In one operation, you can remove the last element of the array and add it
 * to your collection.
 * 
 * Return the minimum number of operations needed to collect elements 1, 2, ..., k.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: nums = [3, 1, 5, 4, 2], k = 2
 *   Output: 4
 *   Explanation: Need to collect {1, 2}
 *     Operation 1: Remove 2 → collected = {2}
 *     Operation 2: Remove 4 → collected = {2} (4 > k, ignore)
 *     Operation 3: Remove 5 → collected = {2} (5 > k, ignore)
 *     Operation 4: Remove 1 → collected = {1, 2} ✓
 *     After 4 operations, we have collected both 1 and 2.
 * 
 * Example 2:
 *   Input: nums = [3, 1, 5, 4, 2], k = 5
 *   Output: 5
 *   Explanation: Need to collect {1, 2, 3, 4, 5}
 *     Must remove all 5 elements to collect everything.
 * 
 * Example 3:
 *   Input: nums = [3, 2, 5, 3, 1], k = 3
 *   Output: 4
 *   Explanation: Need to collect {1, 2, 3}
 *     Operation 1: Remove 1 → collected = {1}
 *     Operation 2: Remove 3 → collected = {1, 3}
 *     Operation 3: Remove 5 → collected = {1, 3} (5 > k, ignore)
 *     Operation 4: Remove 2 → collected = {1, 2, 3} ✓
 * 
 * Example 4:
 *   Input: nums = [1, 2, 3], k = 3
 *   Output: 3
 *   Explanation: All elements are needed, remove all 3.
 * 
 * Constraints:
 *   - 1 <= nums.length <= 50
 *   - 1 <= nums[i] <= nums.length
 *   - 1 <= k <= nums.length
 *   - The input is generated such that you can collect elements 1, 2, ..., k.
 * 
 * Approach: Iterate from End with HashSet
 * 
 * Key Insight: Since we can only remove from the END of the array, we simulate
 * the operations by iterating from right to left.
 * 
 * Algorithm:
 * 1. Start from the last element (index n-1)
 * 2. For each element, if it's in range [1, k], add to our collected set
 * 3. Once the set has k elements, we've collected all required numbers
 * 4. Return the count of operations (n - current_index)
 * 
 * Why HashSet?
 *   - Automatically handles duplicates (if we see 2 twice, it only counts once)
 *   - O(1) lookup and insertion
 *   - Easy to check if we have all k elements (set.size() == k)
 * 
 * Time Complexity: O(n)
 *   - Single pass through the array (worst case)
 *   - HashSet operations are O(1)
 * 
 * Space Complexity: O(k)
 *   - HashSet stores at most k unique elements
 */

import java.util.*;

class Solution {

    public int minOperations(List<Integer> nums, int k) {

        // HashSet to track which numbers from 1 to k we've collected
        Set<Integer> collected = new HashSet<>();

        int n = nums.size();

        // Start from the end of the list (simulating removing last element)
        for (int i = n - 1; i >= 0; i--) {

            int val = nums.get(i);

            // Only collect if the value is in our target range [1, k]
            // Values > k are not needed for our collection
            if (val <= k) {

                collected.add(val); // HashSet ignores duplicates automatically

            }

            // Check if we have collected all numbers from 1 to k
            // Since we only add values <= k, having k elements means we have all of 1..k
            if (collected.size() == k) {

                // Number of operations = elements removed from end
                // We started at index n-1, now at index i
                // Elements removed: indices i, i+1, i+2, ..., n-1 = (n-1) - i + 1 = n - i
                return n - i;

            }

        }

        // Problem guarantees a solution exists, but return -1 as fallback
        return -1;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: nums = [3, 1, 5, 4, 2], k = 2
 * Need to collect: {1, 2}
 * 
 * n = 5
 * Initial: collected = {}
 * 
 * i = 4: val = nums[4] = 2
 *        2 <= 2? Yes → collected = {2}
 *        collected.size() = 1, k = 2 → not done yet
 * 
 * i = 3: val = nums[3] = 4
 *        4 <= 2? No → skip
 *        collected = {2}
 *        collected.size() = 1 → not done yet
 * 
 * i = 2: val = nums[2] = 5
 *        5 <= 2? No → skip
 *        collected = {2}
 *        collected.size() = 1 → not done yet
 * 
 * i = 1: val = nums[1] = 1
 *        1 <= 2? Yes → collected = {2, 1}
 *        collected.size() = 2, k = 2 → DONE!
 *        return n - i = 5 - 1 = 4
 * 
 * Output: 4
 * 
 * 
 * Visualization of Operations:
 * 
 * [3, 1, 5, 4, 2]  →  Remove 2  →  [3, 1, 5, 4]     collected: {2}
 * [3, 1, 5, 4]     →  Remove 4  →  [3, 1, 5]        collected: {2}
 * [3, 1, 5]        →  Remove 5  →  [3, 1]           collected: {2}
 * [3, 1]           →  Remove 1  →  [3]              collected: {1, 2} ✓ DONE!
 * 
 * Total: 4 operations
 * 
 * 
 * Why val <= k check works:
 * 
 * We need to collect exactly {1, 2, 3, ..., k}
 * Any value > k is irrelevant to our goal
 * Any value in [1, k] is something we need (exactly once)
 * HashSet ensures we don't count duplicates
 * When set.size() == k, we must have all of {1, 2, ..., k} because:
 *   - We only add values in [1, k]
 *   - HashSet prevents duplicates
 *   - k unique values from [1, k] = all values from 1 to k
 */

