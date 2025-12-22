/*
 * LeetCode 2248: Intersection of Multiple Arrays
 * 
 * Problem:
 * Given a 2D integer array nums where each nums[i] is a non-empty array of distinct
 * positive integers, return the list of integers that are present in each array of
 * nums sorted in ascending order.
 * 
 * Approach: Count Occurrences with HashMap
 * 
 * Key Insight:
 * - An integer appears in the intersection if and only if it appears in ALL arrays
 * - We can count how many arrays each integer appears in
 * - If count equals total number of arrays, it's in the intersection
 * 
 * Algorithm:
 * 1. Use HashMap to count occurrences of each integer across all arrays
 * 2. For each integer, increment its count when encountered
 * 3. After counting, collect integers whose count equals totalArrays
 * 4. Sort the result list in ascending order
 * 
 * Time Complexity: O(n * m + k log k)
 *   - n = number of arrays, m = average array length
 *   - k = number of integers in intersection (for sorting)
 * 
 * Space Complexity: O(k)
 *   - k = total unique integers across all arrays
 * 
 * Example 1:
 *   nums = [[3,1,2,4,5], [1,2,3,4], [3,4,5,6]]
 *   
 *   Count occurrences:
 *   1: appears in arrays 0, 1 → count = 2 (not in all 3)
 *   2: appears in arrays 0, 1 → count = 2 (not in all 3)
 *   3: appears in arrays 0, 1, 2 → count = 3 ✓
 *   4: appears in arrays 0, 1, 2 → count = 3 ✓
 *   5: appears in arrays 0, 2 → count = 2 (not in all 3)
 *   6: appears in array 2 → count = 1 (not in all 3)
 *   
 *   Result: [3, 4]
 * 
 * Example 2:
 *   nums = [[1,2,3], [4,5,6]]
 *   
 *   No common integers across arrays
 *   Result: []
 * 
 * Example 3:
 *   nums = [[7,34,45,10,12,27,13], [27,21,45,10,12,13]]
 *   
 *   Common integers: 10, 12, 13, 27, 45
 *   Result: [10, 12, 13, 27, 45]
 * 
 * Why HashMap?
 *   - Efficient O(1) lookup and update
 *   - Handles any integer range (not limited to 1-1000)
 *   - More flexible than fixed-size array approach
 * 
 * Alternative Approach (if integers are in range 1-1000):
 *   Use fixed-size array instead of HashMap for better performance:
 *   int[] count = new int[1001];
 *   This avoids HashMap overhead and naturally produces sorted result
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    public List<Integer> intersection(int[][] nums) {

        Map<Integer, Integer> countMap = new HashMap<>();

        int totalArrays = nums.length;

        // Count occurrences of each integer across all arrays
        for (int[] arr : nums) {

            for (int num : arr) {

                countMap.put(num, countMap.getOrDefault(num, 0) + 1);

            }

        }

        // Collect integers that appear in all arrays
        List<Integer> result = new ArrayList<>();

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {

            if (entry.getValue() == totalArrays) {

                result.add(entry.getKey());

            }

        }

        // Sort result in ascending order
        Collections.sort(result);

        return result;

    }

}

