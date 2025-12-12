/*
 * LeetCode 2605: Form Smallest Number From Two Digit Arrays
 * 
 * Problem:
 * Given two arrays of unique digits nums1 and nums2, return the smallest number
 * that contains at least one digit from each array.
 * 
 * Approach: Check Common Digits First, Then Two-Digit Combination
 * 
 * Key Insight:
 * - A single-digit number is always smaller than a two-digit number
 * - If arrays share a common digit, the answer is the smallest common digit
 * - Otherwise, form the smallest two-digit number using min from each array
 * 
 * Two Cases:
 * 1. Common digit exists → Return smallest common digit (single digit answer)
 * 2. No common digit → Build smallest two-digit number from minimums
 * 
 * Algorithm:
 * 1. Put all digits from nums1 into a HashSet
 * 2. Check each digit in nums2 - if it's in the set, track the minimum common
 * 3. If common digit found, return it (guaranteed smaller than any 2-digit)
 * 4. Otherwise, find min of each array and form smallest 2-digit number
 * 
 * Time Complexity: O(n + m) where n = len(nums1), m = len(nums2)
 * Space Complexity: O(n) for the HashSet
 * 
 * Example 1: Common digit exists
 * nums1 = [4, 1, 3], nums2 = [5, 7, 1]
 * Common digits: {1}
 * Answer: 1 (single digit is smallest)
 * 
 * Example 2: No common digit
 * nums1 = [3, 5, 2, 6], nums2 = [8, 7, 4]
 * No common digits
 * min1 = 2, min2 = 4
 * Options: 24 or 42
 * Answer: 24 (smaller of the two)
 * 
 * Why single digit wins:
 * - Smallest single digit: 1
 * - Smallest two-digit: 10
 * - Any common digit (1-9) < any two-digit number (10-99)
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    public int minNumber(int[] nums1, int[] nums2) {

        // Step 1: Build a set from nums1 for O(1) lookup
        Set<Integer> set = new HashSet<>();

        for (int num : nums1) {

            set.add(num);

        }

        // Step 2: Find the smallest common digit (if any)
        int common = Integer.MAX_VALUE;

        for (int num : nums2) {

            // Check if this digit exists in both arrays
            if (set.contains(num)) {

                common = Math.min(common, num);

            }

        }

        // If a common digit exists, it's always the answer
        // (single digit < any two-digit number)
        if (common != Integer.MAX_VALUE) {

            return common;

        }

        // Step 3: No common digit → must form a two-digit number
        // Find the minimum digit in each array
        int min1 = Arrays.stream(nums1).min().getAsInt();

        int min2 = Arrays.stream(nums2).min().getAsInt();

        // Build the smallest possible two-digit number
        // Either min1 as tens digit (min1 * 10 + min2)
        // Or min2 as tens digit (min2 * 10 + min1)
        // The smaller one wins
        return Math.min(min1 * 10 + min2, min2 * 10 + min1);

    }

}

