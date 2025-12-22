/*
 * LeetCode 1331: Rank Transform of an Array
 * 
 * Problem:
 * Given an array of integers arr, replace each element with its rank.
 * The rank represents how large the element is. The rank has the following rules:
 * - Rank is an integer starting from 1
 * - The larger the element, the larger the rank
 * - If two elements are equal, their rank must be the same
 * - Rank should be as small as possible
 * 
 * Approach: Sort and Map Ranks
 * 
 * Key Insight:
 * - Rank is determined by the sorted order of unique elements
 * - Equal elements get the same rank (no gaps in ranking)
 * - We need to map each unique value to its rank, then replace original elements
 * 
 * Algorithm:
 * 1. Clone and sort the array to determine rank order
 * 2. Create a HashMap to map each unique value to its rank
 * 3. Traverse sorted array, assign ranks sequentially (skip duplicates)
 * 4. Replace each element in original array with its rank from the map
 * 
 * Time Complexity: O(n log n)
 *   - Sorting: O(n log n)
 *   - Building rank map: O(n)
 *   - Transforming array: O(n)
 * 
 * Space Complexity: O(n)
 *   - Sorted array copy: O(n)
 *   - HashMap: O(n) for unique elements
 * 
 * Example 1:
 *   arr = [40, 10, 20, 30]
 *   
 *   Step 1: Sort → [10, 20, 30, 40]
 *   
 *   Step 2: Assign ranks:
 *   10 → rank 1
 *   20 → rank 2
 *   30 → rank 3
 *   40 → rank 4
 *   
 *   Step 3: Transform original array:
 *   [40, 10, 20, 30] → [4, 1, 2, 3]
 *   
 *   Result: [4, 1, 2, 3]
 * 
 * Example 2:
 *   arr = [100, 100, 100]
 *   
 *   Step 1: Sort → [100, 100, 100]
 *   
 *   Step 2: Assign ranks:
 *   100 → rank 1 (all same value, same rank)
 *   
 *   Step 3: Transform:
 *   [100, 100, 100] → [1, 1, 1]
 *   
 *   Result: [1, 1, 1]
 * 
 * Example 3:
 *   arr = [37, 12, 28, 9, 100, 56, 80, 5, 12]
 *   
 *   Step 1: Sort → [5, 9, 12, 12, 28, 37, 56, 80, 100]
 *   
 *   Step 2: Assign ranks (skip duplicate 12):
 *   5 → rank 1
 *   9 → rank 2
 *   12 → rank 3 (first occurrence, second 12 gets same rank)
 *   28 → rank 4
 *   37 → rank 5
 *   56 → rank 6
 *   80 → rank 7
 *   100 → rank 8
 *   
 *   Step 3: Transform:
 *   [37, 12, 28, 9, 100, 56, 80, 5, 12]
 *   → [5, 3, 4, 2, 8, 6, 7, 1, 3]
 *   
 *   Result: [5, 3, 4, 2, 8, 6, 7, 1, 3]
 * 
 * Why Clone Before Sorting?
 *   - We need the original array to transform it
 *   - Sorting modifies the array in place
 *   - Clone preserves original order for final transformation
 * 
 * Why HashMap for Rank Mapping?
 *   - O(1) lookup when replacing elements
 *   - Handles duplicates automatically (same key maps to same rank)
 *   - Efficient for any value range
 * 
 * Visual (Rank Assignment):
 * 
 *   Original: [40, 10, 20, 30]
 *              ↓
 *   Sorted:   [10, 20, 30, 40]
 *              ↓
 *   Rank Map: {10→1, 20→2, 30→3, 40→4}
 *              ↓
 *   Result:   [4, 1, 2, 3]
 * 
 * Handling Duplicates:
 * 
 *   arr = [100, 100, 50]
 *   
 *   Sorted: [50, 100, 100]
 *   
 *   Rank assignment:
 *   - First 50 → rank 1
 *   - First 100 → rank 2
 *   - Second 100 → already in map, skip (gets rank 2)
 *   
 *   Result: [2, 2, 1]
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int[] arrayRankTransform(int[] arr) {

        int[] sorted = arr.clone();

        Arrays.sort(sorted);

        Map<Integer, Integer> rankMap = new HashMap<>();

        int rank = 1;

        // Assign ranks to unique elements in sorted order
        for (int num : sorted) {

            if (!rankMap.containsKey(num)) {

                rankMap.put(num, rank++);

            }

        }

        // Replace each element with its rank
        for (int i = 0; i < arr.length; i++) {

            arr[i] = rankMap.get(arr[i]);

        }

        return arr;

    }

}

