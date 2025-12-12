/*
 * LeetCode 3745: Maximize Expression of Three
 * 
 * Problem:
 * Given an array nums, choose three elements a, b, c to maximize: a + b - c
 * Return the maximum value of this expression.
 * 
 * Approach: Greedy Selection via Sorting
 * 
 * Key Insight:
 * To maximize (a + b - c):
 * - Maximize (a + b): Pick the TWO LARGEST numbers
 * - Minimize c: Pick the SMALLEST number (subtracting small = adding more)
 * 
 * Mathematical Intuition:
 * a + b - c = (a + b) + (-c)
 * 
 * To maximize:
 * - (a + b) should be as LARGE as possible → pick largest two
 * - (-c) should be as LARGE as possible → c should be as SMALL as possible
 * 
 * Algorithm:
 * 1. Sort the array in ascending order
 * 2. Pick nums[n-1] and nums[n-2] as a and b (two largest)
 * 3. Pick nums[0] as c (smallest)
 * 4. Return a + b - c
 * 
 * Time Complexity: O(n log n) - due to sorting
 * Space Complexity: O(1) - sorting in place (or O(log n) for sort stack)
 * 
 * Example: nums = [3, 1, 5, 2, 8]
 * 
 * After sorting: [1, 2, 3, 5, 8]
 *                 ↑        ↑  ↑
 *                 c        b  a
 * 
 * a = 8 (largest)
 * b = 5 (second largest)
 * c = 1 (smallest)
 * 
 * Result: 8 + 5 - 1 = 12
 * 
 * Why this is optimal:
 * - Any other choice of a, b would give smaller sum
 * - Any other choice of c would subtract more
 * 
 * Visual:
 *   Sorted: [1, 2, 3, 5, 8]
 *            │           └─┬─┘
 *            │             │
 *         subtract      add these
 *         (minimize)    (maximize)
 * 
 * Note: The problem guarantees at least 3 elements in the array.
 */

import java.util.Arrays;

class Solution {

    public int maximizeExpressionOfThree(int[] nums) {

        // Sort array to easily identify largest and smallest elements
        Arrays.sort(nums);

        int n = nums.length;

        // Pick the two largest elements (for a + b)
        int a = nums[n - 1];  // Largest

        int b = nums[n - 2];  // Second largest

        // Pick the smallest element (for -c, we want c to be minimum)
        int c = nums[0];      // Smallest

        // Return the maximized expression
        return a + b - c;

    }

}

