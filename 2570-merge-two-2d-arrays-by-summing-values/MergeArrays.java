/**
 * LeetCode 2570: Merge Two 2D Arrays by Summing Values
 * 
 * Merge two sorted [id, value] arrays. Same id → sum values.
 * Uses two-pointer technique (like merge sort merge step).
 */

import java.util.*;

class Solution {

    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        List<int[]> result = new ArrayList<>();
        int i = 0, j = 0;

        // Two-pointer merge
        while (i < nums1.length && j < nums2.length) {
            int id1 = nums1[i][0];
            int id2 = nums2[j][0];

            if (id1 == id2) {
                // Same id → sum values
                result.add(new int[]{id1, nums1[i][1] + nums2[j][1]});
                i++;
                j++;
            } else if (id1 < id2) {
                result.add(nums1[i++]);
            } else {
                result.add(nums2[j++]);
            }
        }

        // Add remaining elements
        while (i < nums1.length) result.add(nums1[i++]);
        while (j < nums2.length) result.add(nums2[j++]);

        // Convert list to 2D array
        return result.toArray(new int[result.size()][]);
    }
}

