/*
 * LeetCode 3550: Smallest Index With Digit Sum Equal To Index
 * 
 * Problem:
 * Given an array nums, return the smallest index i such that the sum of
 * digits of nums[i] equals i. Return -1 if no such index exists.
 * 
 * Approach: Linear Scan with Digit Sum Calculation
 * 
 * Key Insight:
 * - For each element, calculate sum of its digits
 * - Compare digit sum with index
 * - Return first match (smallest index)
 * 
 * Algorithm:
 * 1. Iterate through array with index i
 * 2. For each nums[i], calculate digit sum:
 *    - Extract last digit: num % 10
 *    - Remove last digit: num /= 10
 *    - Repeat until num = 0
 * 3. If digit sum == i, return i
 * 4. If no match found, return -1
 * 
 * Time Complexity: O(n × d) where d = max digits in any number
 * Space Complexity: O(1) - only using variables
 * 
 * Example 1: nums = [1, 3, 2]
 * 
 *   i=0: nums[0]=1 → digit sum=1 → 1 ≠ 0 ✗
 *   i=1: nums[1]=3 → digit sum=3 → 3 ≠ 1 ✗
 *   i=2: nums[2]=2 → digit sum=2 → 2 == 2 ✓
 *   
 *   Return: 2
 * 
 * Example 2: nums = [1, 10, 11]
 * 
 *   i=0: nums[0]=1  → digit sum=1     → 1 ≠ 0 ✗
 *   i=1: nums[1]=10 → digit sum=1+0=1 → 1 == 1 ✓
 *   
 *   Return: 1
 * 
 * Example 3: nums = [10, 2, 3, 4]
 * 
 *   i=0: nums[0]=10 → digit sum=1+0=1 → 1 ≠ 0 ✗
 *   i=1: nums[1]=2  → digit sum=2     → 2 ≠ 1 ✗
 *   i=2: nums[2]=3  → digit sum=3     → 3 ≠ 2 ✗
 *   i=3: nums[3]=4  → digit sum=4     → 4 ≠ 3 ✗
 *   
 *   Return: -1 (no match)
 * 
 * Digit Sum Calculation:
 * 
 *   num = 123
 *   
 *   Step 1: sum += 123 % 10 = 3    num = 123 / 10 = 12
 *   Step 2: sum += 12 % 10  = 2    num = 12 / 10  = 1
 *   Step 3: sum += 1 % 10   = 1    num = 1 / 10   = 0
 *   
 *   sum = 3 + 2 + 1 = 6
 * 
 * Visual:
 * 
 *   Index:     0    1    2    3    4
 *   nums:    [22,  13,  42,   3,  10]
 *             │    │    │    │    │
 *   digit    2+2  1+3  4+2   3   1+0
 *   sum:      4    4    6    3    1
 *             │    │    │    │    │
 *   == idx?   ✗    ✗    ✗    ✓   
 *                            ↑
 *                      Return 3
 */

class Solution {

    public int smallestIndex(int[] nums) {

        for (int i = 0; i < nums.length; i++) {

            int num = nums[i];

            int sum = 0;

            // Calculate sum of digits directly
            while (num > 0) {

                sum += num % 10;

                num /= 10;

            }

            if (sum == i) {

                return i;

            }

        }

        return -1;

    }

}

