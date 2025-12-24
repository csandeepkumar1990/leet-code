/*
 * LeetCode 1995: Count Special Quadruplets
 *
 * Problem:
 * Given a 0-indexed integer array nums, return the number of distinct
 * quadruplets (a, b, c, d) such that:
 * - nums[a] + nums[b] + nums[c] == nums[d]
 * - a < b < c < d
 *
 * Approach: Brute Force with Four Nested Loops
 *
 * Key Insight:
 * - Need to find all quadruplets (a, b, c, d) where indices are in order
 * - Check if nums[a] + nums[b] + nums[c] == nums[d]
 * - Use four nested loops to check all possible combinations
 *
 * Algorithm:
 * 1. Iterate through all possible values of a (0 to n-4)
 * 2. For each a, iterate through b (a+1 to n-3)
 * 3. For each b, iterate through c (b+1 to n-2)
 * 4. For each c, iterate through d (c+1 to n-1)
 * 5. Check if nums[a] + nums[b] + nums[c] == nums[d]
 * 6. Count all valid quadruplets
 *
 * Time Complexity: O(n^4), where n is the length of nums.
 *   We check all possible quadruplets with four nested loops.
 * Space Complexity: O(1), only using a constant amount of extra space.
 *
 * Why This Works:
 * - The constraints ensure indices are in order: a < b < c < d
 * - We check all possible combinations exhaustively
 * - For small input sizes (n <= 50), O(n^4) is acceptable
 *
 * Example:
 *
 *   Input: nums = [1, 1, 1, 3, 5]
 *
 *   Check all quadruplets:
 *   a=0, b=1, c=2, d=3: nums[0]+nums[1]+nums[2] = 1+1+1 = 3 == nums[3] = 3 ✓
 *   a=0, b=1, c=2, d=4: nums[0]+nums[1]+nums[2] = 1+1+1 = 3 != nums[4] = 5 ✗
 *   a=0, b=1, c=3, d=4: nums[0]+nums[1]+nums[3] = 1+1+3 = 5 == nums[4] = 5 ✓
 *   a=0, b=2, c=3, d=4: nums[0]+nums[2]+nums[3] = 1+1+3 = 5 == nums[4] = 5 ✓
 *   a=1, b=2, c=3, d=4: nums[1]+nums[2]+nums[3] = 1+1+3 = 5 == nums[4] = 5 ✓
 *
 *   Output: 4
 *
 * Another Example:
 *
 *   Input: nums = [1, 2, 3, 6]
 *
 *   Check all quadruplets:
 *   a=0, b=1, c=2, d=3: nums[0]+nums[1]+nums[2] = 1+2+3 = 6 == nums[3] = 6 ✓
 *
 *   Output: 1
 *
 * Index Constraints:
 * - a ranges from 0 to n-4 (need at least 3 more indices after a)
 * - b ranges from a+1 to n-3 (need at least 2 more indices after b)
 * - c ranges from b+1 to n-2 (need at least 1 more index after c)
 * - d ranges from c+1 to n-1 (last index)
 *
 * Important Notes:
 * - Indices must be strictly increasing: a < b < c < d
 * - We check all possible combinations (brute force approach)
 * - For larger inputs, this could be optimized using hash maps or other
 *   techniques, but for n <= 50, this is efficient enough
 */

class Solution {

    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int count = 0;

        for (int a = 0; a < n; a++) {
            for (int b = a + 1; b < n; b++) {
                for (int c = b + 1; c < n; c++) {
                    for (int d = c + 1; d < n; d++) {
                        if (nums[a] + nums[b] + nums[c] == nums[d]) {
                            count++;
                        }
                    }
                }
            }
        }

        return count;
    }
}

