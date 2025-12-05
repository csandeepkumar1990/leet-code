/**
 * LeetCode 2932: Maximum Strong Pair XOR I
 * 
 * Problem: You are given a 0-indexed integer array nums. A pair of integers x and y
 *          is called a strong pair if it satisfies the condition:
 *          |x - y| <= min(x, y)
 * 
 *          You need to select two integers from nums such that they form a strong pair
 *          and their bitwise XOR is the maximum among all strong pairs in the array.
 *          Return the maximum XOR value out of all possible strong pairs.
 * 
 * Key Insight: A pair (x, y) is strong if the absolute difference is at most the
 *              smaller of the two values. This means the numbers must be "close enough"
 *              relative to their magnitude.
 * 
 * Examples:
 *   Input: nums = [1,2,3,4,5]
 *   Output: 7
 *   Explanation: Strong pairs are (2,3), (3,4), (4,5), (2,4), (3,5), (3,6), (4,6), (5,6).
 *                Maximum XOR is 3 XOR 4 = 7.
 * 
 *   Input: nums = [10,100]
 *   Output: 0
 *   Explanation: Only strong pair is (10,10) with XOR = 0.
 *                (10,100) is not strong since |10-100| = 90 > min(10,100) = 10.
 * 
 *   Input: nums = [5,6,25,30]
 *   Output: 7
 *   Explanation: Strong pairs include (5,6) and (25,30). Max XOR is 5 XOR 6 = 7.
 */

class Solution {

    /**
     * Finds the maximum XOR value among all strong pairs in the array.
     * 
     * @param nums - Array of integers
     * @return Maximum XOR value from all strong pairs
     * 
     * Time Complexity: O(n²) - checking all pairs
     * Space Complexity: O(1) - only using a few variables
     */
    public int maximumStrongPairXor(int[] nums) {
        int max_xor = 0;

        for (int x : nums) {
            for (int y : nums) {
                // Check if (x, y) forms a strong pair
                if (Math.abs(x - y) <= Math.min(x, y)) {
                    max_xor = Math.max(max_xor, x ^ y);
                }
            }
        }

        return max_xor;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * int[] nums = {1, 2, 3, 4, 5};
 * System.out.println(sol.maximumStrongPairXor(nums)); // Output: 7
 * 
 * Note: The condition |x - y| <= min(x, y) can also be written as:
 *       x <= 2*y AND y <= 2*x (when both x, y > 0)
 */

