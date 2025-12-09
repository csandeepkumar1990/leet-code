/**
 * LeetCode 3731: Find Missing Elements
 * 
 * Find all missing integers between min and max of array.
 * Use HashSet for O(1) lookup, iterate through range.
 */

import java.util.*;

class Solution {

    public List<Integer> findMissingElements(int[] nums) {
        ArrayList<Integer> arr = new ArrayList<>();
        Set<Integer> set = new HashSet<>();

        Arrays.sort(nums);
        int n = nums.length;
        int min = nums[0];
        int max = nums[n - 1];

        // Add all elements to set for fast lookup
        for (int i = 0; i < n; i++) {
            set.add(nums[i]);
        }

        // Find missing numbers in range [min, max)
        for (int i = min; i < max; i++) {
            if (!set.contains(i)) {
                arr.add(i);
            }
        }

        return arr;
    }
}

