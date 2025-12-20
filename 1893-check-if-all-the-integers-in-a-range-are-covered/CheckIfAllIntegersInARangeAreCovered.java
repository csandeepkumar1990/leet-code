/*
 * LeetCode 1893: Check if All the Integers in a Range Are Covered
 * 
 * Problem:
 * Given a 2D integer array ranges and two integers left and right, return true
 * if each integer in the inclusive range [left, right] is covered by at least
 * one interval in ranges.
 * 
 * Approach: Brute Force - Check Each Integer
 * 
 * Key Insight:
 * - For each number in [left, right], check if ANY range covers it
 * - If any number is not covered, return false immediately
 * - Only return true if ALL numbers are covered
 * 
 * Algorithm:
 * 1. For each integer i in [left, right]:
 *    - Check all ranges to see if any covers i
 *    - If no range covers i, return false
 * 2. If all integers are covered, return true
 * 
 * Time Complexity: O((right - left + 1) × n) where n = number of ranges
 * Space Complexity: O(1) - only using a boolean flag
 * 
 * Example 1: ranges = [[1,2],[3,4],[5,6]], left = 2, right = 5
 * 
 *   Check each integer in [2, 5]:
 *   
 *   i=2: [1,2] covers it? 2 >= 1 && 2 <= 2 ✓
 *   i=3: [3,4] covers it? 3 >= 3 && 3 <= 4 ✓
 *   i=4: [3,4] covers it? 4 >= 3 && 4 <= 4 ✓
 *   i=5: [5,6] covers it? 5 >= 5 && 5 <= 6 ✓
 *   
 *   All covered → return true
 * 
 * Example 2: ranges = [[1,10],[10,20]], left = 21, right = 21
 * 
 *   Check i=21:
 *   - [1,10]: 21 >= 1 && 21 <= 10? No
 *   - [10,20]: 21 >= 10 && 21 <= 20? No
 *   
 *   Not covered → return false
 * 
 * Visual:
 * 
 *   Ranges: [1,2]  [3,4]  [5,6]
 *           |--|   |--|   |--|
 *           1  2   3  4   5  6
 *   
 *   Target: [2, 5]
 *              |-------|
 *              2  3  4  5
 *   
 *   Check: 2 ✓  3 ✓  4 ✓  5 ✓  → All covered!
 * 
 * Example 3 (Gap): ranges = [[1,2],[4,5]], left = 1, right = 5
 * 
 *   [1,2]     [4,5]
 *   |--|      |--|
 *   1  2  3   4  5
 *         ↑
 *         3 is NOT covered!
 *   
 *   Return false
 * 
 * ============================================================
 * ALTERNATIVE APPROACHES
 * ============================================================
 * 
 * Approach 2: Boolean Array (Mark and Check)
 * 
 *   public boolean isCovered(int[][] ranges, int left, int right) {
 *       boolean[] covered = new boolean[52];  // 1 to 50
 *       for (int[] range : ranges) {
 *           for (int i = range[0]; i <= range[1]; i++) {
 *               covered[i] = true;
 *           }
 *       }
 *       for (int i = left; i <= right; i++) {
 *           if (!covered[i]) return false;
 *       }
 *       return true;
 *   }
 * 
 *   - Mark all covered numbers first
 *   - Then check if target range is fully covered
 * 
 * Approach 3: Difference Array (For large ranges)
 * 
 *   - Use difference array for efficient range marking
 *   - Good when ranges are very large
 * 
 * ============================================================
 * COMPLEXITY COMPARISON
 * ============================================================
 * 
 * | Approach        | Time                    | Space |
 * |-----------------|-------------------------|-------|
 * | Brute Force     | O((right-left) × n)     | O(1)  |
 * | Boolean Array   | O(sum of range lengths) | O(50) |
 * | Difference Array| O(n + range)            | O(50) |
 * 
 * For this problem (values 1-50), all approaches work well.
 */

class Solution {

    public boolean isCovered(int[][] ranges, int left, int right) {

        // Iterate through each integer in the target range [left, right]
        for (int i = left; i <= right; i++) {

            boolean covered = false; // Flag to check if the current integer 'i' is covered

            // Iterate through each given range
            for (int[] range : ranges) {

                int start = range[0];

                int end = range[1];

                // Check if the current integer 'i' is within the current range
                if (i >= start && i <= end) {

                    covered = true;

                    break;

                }

            }

            // If after checking all ranges, 'i' is not covered, return false immediately
            if (!covered) {

                return false;

            }

        }

        // If all integers in [left, right] are covered, return true
        return true;

    }

}

