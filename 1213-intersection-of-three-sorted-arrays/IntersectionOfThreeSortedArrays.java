/*
 * LeetCode 1213: Intersection of Three Sorted Arrays
 * 
 * Problem:
 * Given three integer arrays arr1, arr2, arr3 sorted in strictly increasing order,
 * return a sorted array of only the integers that appeared in all three arrays.
 * 
 * Approach: Three Pointers
 * 
 * Key Insight:
 * - Since arrays are sorted, we can use three pointers moving forward
 * - When all three values match, we found a common element
 * - Otherwise, advance the pointer(s) pointing to the minimum value
 * - This works because smaller values can't appear later in sorted arrays
 * 
 * Algorithm:
 * 1. Initialize three pointers i, j, k at the start of each array
 * 2. While all pointers are within bounds:
 *    - If all three values are equal: add to result, advance all pointers
 *    - Otherwise: find minimum value and advance pointer(s) at that value
 * 3. Return the result list
 * 
 * Time Complexity: O(n1 + n2 + n3) - each pointer moves forward only
 * Space Complexity: O(1) - excluding output array
 * 
 * Example: arr1 = [1,2,3,4,5], arr2 = [1,2,5,7,9], arr3 = [1,3,4,5,8]
 * 
 *   Step 1: i=0, j=0, k=0
 *           arr1[0]=1, arr2[0]=1, arr3[0]=1  → All equal! Add 1
 *           i++, j++, k++
 * 
 *   Step 2: i=1, j=1, k=1
 *           arr1[1]=2, arr2[1]=2, arr3[1]=3
 *           min=2, advance i and j (both have min)
 * 
 *   Step 3: i=2, j=2, k=1
 *           arr1[2]=3, arr2[2]=5, arr3[1]=3
 *           min=3, advance i and k
 * 
 *   Step 4: i=3, j=2, k=2
 *           arr1[3]=4, arr2[2]=5, arr3[2]=4
 *           min=4, advance i and k
 * 
 *   Step 5: i=4, j=2, k=3
 *           arr1[4]=5, arr2[2]=5, arr3[3]=5  → All equal! Add 5
 *           i++, j++, k++
 * 
 *   Result: [1, 5]
 * 
 * Visual (Pointer Movement):
 * 
 *   arr1: [1, 2, 3, 4, 5]
 *          ↑  ↑  ↑  ↑  ↑
 *          i→ i→ i→ i→ i→ (done)
 * 
 *   arr2: [1, 2, 5, 7, 9]
 *          ↑  ↑  ↑
 *          j→ j→ j→ (done)
 * 
 *   arr3: [1, 3, 4, 5, 8]
 *          ↑  ↑  ↑  ↑
 *          k→ k→ k→ k→ (done)
 * 
 *   Match at: 1 ✓        5 ✓
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {

        List<Integer> result = new ArrayList<>();

        int i = 0, j = 0, k = 0; // pointers for arr1, arr2, arr3

        while (i < arr1.length && j < arr2.length && k < arr3.length) {

            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {

                result.add(arr1[i]);

                i++; j++; k++;

            } 

            else {

                int minVal = Math.min(arr1[i], Math.min(arr2[j], arr3[k]));

                if (arr1[i] == minVal) i++;

                if (arr2[j] == minVal) j++;

                if (arr3[k] == minVal) k++;

            }

        }

        return result;

    }

}

