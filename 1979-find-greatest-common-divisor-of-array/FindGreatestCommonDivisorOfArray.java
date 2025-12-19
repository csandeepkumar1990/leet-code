/*
 * LeetCode 1979: Find Greatest Common Divisor of Array
 * 
 * Problem:
 * Given an integer array nums, return the greatest common divisor (GCD)
 * of the smallest and largest numbers in the array.
 * 
 * Approach: Find Min/Max + Euclidean Algorithm
 * 
 * Key Insight:
 * - First find the minimum and maximum values in array
 * - Then compute GCD of those two values
 * - Use Euclidean algorithm for efficient GCD calculation
 * 
 * Algorithm:
 * 1. Single pass to find min and max
 * 2. Apply Euclidean algorithm: GCD(a,b) = GCD(b, a%b) until b=0
 * 3. Return the final non-zero value
 * 
 * Time Complexity: O(n + log(min(a,b))) - array scan + GCD computation
 * Space Complexity: O(1) - only using variables
 * 
 * Euclidean Algorithm:
 * 
 *   GCD(a, b) where a > b:
 *   - Replace (a, b) with (b, a % b)
 *   - Repeat until one becomes 0
 *   - The non-zero value is the GCD
 * 
 *   Example: GCD(48, 18)
 *   Step 1: 48 % 18 = 12 → (18, 12)
 *   Step 2: 18 % 12 = 6  → (12, 6)
 *   Step 3: 12 % 6 = 0   → (6, 0)
 *   GCD = 6
 * 
 * Example 1: nums = [2, 5, 6, 9, 10]
 * 
 *   Find min/max:
 *   - min = 2
 *   - max = 10
 * 
 *   GCD(10, 2):
 *   Step 1: 10 % 2 = 0 → (2, 0)
 *   GCD = 2
 * 
 *   Result: 2
 * 
 * Example 2: nums = [7, 5, 6, 8, 3]
 * 
 *   Find min/max:
 *   - min = 3
 *   - max = 8
 * 
 *   GCD(8, 3):
 *   Step 1: 8 % 3 = 2 → (3, 2)
 *   Step 2: 3 % 2 = 1 → (2, 1)
 *   Step 3: 2 % 1 = 0 → (1, 0)
 *   GCD = 1
 * 
 *   Result: 1
 * 
 * Example 3: nums = [3, 3]
 * 
 *   min = 3, max = 3
 *   GCD(3, 3):
 *   Step 1: 3 % 3 = 0 → (3, 0)
 *   GCD = 3
 * 
 *   Result: 3
 * 
 * Visual (Why Euclidean Works):
 * 
 *   GCD(48, 18):
 *   
 *   48 = 2 × 18 + 12    ← 48 % 18 = 12
 *   ──────────────
 *   18 = 1 × 12 + 6     ← 18 % 12 = 6
 *   ──────────────
 *   12 = 2 × 6 + 0      ← 12 % 6 = 0 (done!)
 *   ──────────────
 *   GCD = 6
 *   
 *   Verification: 48 = 6×8, 18 = 6×3
 */

class Solution {

    public int findGCD(int[] nums) {

        int min = nums[0];

        int max = nums[0];

        // Find the smallest and largest number in the array
        for (int num : nums) {

            if (num < min) min = num;

            if (num > max) max = num;

        }

        // Compute GCD using Euclidean algorithm (inlined)
        while (min != 0) {

            int temp = min;

            min = max % min;

            max = temp;

        }

        return max;

    }

}

