/*
 * LeetCode 3411: Maximum Subarray With Equal Products
 * 
 * Problem:
 * Given an array of positive integers nums, return the length of the longest
 * subarray where: product of all elements = GCD × LCM of all elements.
 * 
 * Approach: Brute Force with Running GCD/LCM/Product
 * 
 * Key Insight:
 * - For a valid subarray: product == GCD × LCM
 * - This property holds when elements are "compatible" (share factors nicely)
 * - Maintain running product, GCD, and LCM as we extend subarray
 * 
 * Mathematical Background:
 * - GCD(a,b) = greatest common divisor
 * - LCM(a,b) = least common multiple
 * - For two numbers: a × b = GCD(a,b) × LCM(a,b)
 * - For more numbers, product = GCD × LCM only in special cases
 * 
 * Algorithm:
 * 1. For each starting index:
 *    - Initialize product=1, gcd=0, lcm=1
 *    - Extend to each ending index:
 *      - Update product, gcd, lcm with new element
 *      - If product == gcd × lcm, update maxLength
 * 2. Return maxLength
 * 
 * Time Complexity: O(n² × log(max_element)) - nested loops with GCD computation
 * Space Complexity: O(1) - only using variables
 * 
 * Example: nums = [1, 2, 1, 2, 1, 1, 1]
 * 
 *   Subarray [1,2,1]:
 *   - product = 1 × 2 × 1 = 2
 *   - GCD(1,2,1) = 1
 *   - LCM(1,2,1) = 2
 *   - GCD × LCM = 1 × 2 = 2 ✓ (equals product)
 *   - Length = 3
 * 
 *   Subarray [2,1,2]:
 *   - product = 2 × 1 × 2 = 4
 *   - GCD(2,1,2) = 1
 *   - LCM(2,1,2) = 2
 *   - GCD × LCM = 1 × 2 = 2 ✗ (not equal to 4)
 * 
 * Why product == GCD × LCM Works:
 * 
 *   For [a, b]: a × b = GCD(a,b) × LCM(a,b)  [Always true for 2 elements]
 *   
 *   For [a, b, c]: product = GCD × LCM is NOT always true
 *   
 *   Example [2, 3, 4]:
 *   - product = 24
 *   - GCD = 1
 *   - LCM = 12
 *   - GCD × LCM = 12 ≠ 24 ✗
 *   
 *   Example [1, 1, 1]:
 *   - product = 1
 *   - GCD = 1
 *   - LCM = 1
 *   - GCD × LCM = 1 == 1 ✓
 * 
 * GCD Formula (Euclidean Algorithm):
 *   GCD(a, b) = GCD(b, a % b) until b = 0, then return a
 *   
 *   GCD(12, 8):
 *   12 % 8 = 4 → GCD(8, 4)
 *   8 % 4 = 0  → return 4
 * 
 * LCM Formula:
 *   LCM(a, b) = a × (b / GCD(a, b))
 *   
 *   LCM(12, 8) = 12 × (8 / 4) = 12 × 2 = 24
 */

class Solution {

    public int maxLength(int[] nums) {

        int n = nums.length;

        int maxLength = 0;

        for (int start = 0; start < n; start++) {

            long product = 1;

            int gcd = 0;

            long lcm = 1;

            for (int end = start; end < n; end++) {

                product *= nums[end];

                gcd = gcd == 0 ? nums[end] : computeGCD(gcd, nums[end]);

                lcm = lcm(nums[end], lcm);

                if (product == gcd * lcm) {

                    maxLength = Math.max(maxLength, end - start + 1);

                }

            }

        }

        return maxLength;

    }

    // GCD using Euclidean algorithm
    private int computeGCD(int a, int b) {

        while (b != 0) {

            int tmp = b;

            b = a % b;

            a = tmp;

        }

        return a;

    }

    // LCM using formula: lcm(a,b) = a * (b / gcd(a,b))
    private long lcm(int a, long b) {

        return (a * (b / computeGCD(a, (int) b)));

    }

}

