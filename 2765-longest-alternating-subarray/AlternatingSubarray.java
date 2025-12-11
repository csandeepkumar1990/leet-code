/*
 * 2765. Longest Alternating Subarray
 * 
 * Problem:
 * You are given a 0-indexed integer array nums. A subarray s of length m is
 * called alternating if:
 * 
 *   - m is greater than 1
 *   - s[1] = s[0] + 1
 *   - The pattern follows: s[i] - s[i-1] alternates between 1 and -1
 *     i.e., s[0], s[0]+1, s[0], s[0]+1, s[0], ...
 * 
 * In other words, differences should be: [+1, -1, +1, -1, +1, ...]
 * 
 * Return the maximum length of an alternating subarray, or -1 if none exists.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: nums = [2, 3, 4, 3, 4]
 *   Output: 4
 *   Explanation: 
 *     Subarray [3, 4, 3, 4] at indices [1, 4]:
 *       3 → 4 (diff = +1) ✓
 *       4 → 3 (diff = -1) ✓
 *       3 → 4 (diff = +1) ✓
 *     Length = 4 (maximum)
 * 
 * Example 2:
 *   Input: nums = [4, 5, 6]
 *   Output: 2
 *   Explanation:
 *     [4, 5] is alternating (diff = +1), length = 2
 *     [5, 6] is alternating (diff = +1), length = 2
 *     But [4, 5, 6] is NOT alternating (needs +1, -1 but got +1, +1)
 * 
 * Example 3:
 *   Input: nums = [1, 2, 1, 2, 1, 2]
 *   Output: 6
 *   Explanation: Entire array is alternating: +1, -1, +1, -1, +1
 * 
 * Example 4:
 *   Input: nums = [5, 5, 5]
 *   Output: -1
 *   Explanation: No pair has difference +1, so no alternating subarray exists.
 * 
 * Constraints:
 *   - 2 <= nums.length <= 100
 *   - 1 <= nums[i] <= 10^4
 * 
 * Approach: Single Pass with Pattern Tracking
 * 
 * Key Insight: Track current subarray length and expected difference.
 * 
 * Pattern of differences in alternating subarray:
 *   Length 1: no difference yet (just one element)
 *   Length 2: diff = +1 (first pair must be +1)
 *   Length 3: diff = -1 (second pair must be -1)
 *   Length 4: diff = +1 (third pair must be +1)
 *   ...
 * 
 * Formula: Expected diff at position with currLen elements:
 *   - If currLen is ODD (1, 3, 5...), next diff should be +1
 *   - If currLen is EVEN (2, 4, 6...), next diff should be -1
 *   - So: expected = (currLen % 2 == 1) ? +1 : -1
 * 
 * Algorithm:
 * 1. Track currLen (current subarray length, starts at 1)
 * 2. For each pair, check if difference matches expected pattern
 * 3. If matches: extend current subarray (currLen++)
 * 4. If not: try to restart with current pair (if diff == +1, currLen = 2)
 * 5. Track maximum length found
 * 
 * Time Complexity: O(n)
 *   - Single pass through the array
 * 
 * Space Complexity: O(1)
 *   - Only a few integer variables
 */

class Solution {

    public int alternatingSubarray(int[] nums) {

        int n = nums.length;
        int maxLen = -1;   // Maximum alternating subarray length found (-1 if none)
        int currLen = 1;   // Current subarray length (starts with 1 element)

        for (int i = 1; i < n; i++) {

            // Calculate expected difference based on current subarray length
            // currLen=1 (odd) → need +1 to extend to length 2
            // currLen=2 (even) → need -1 to extend to length 3
            // currLen=3 (odd) → need +1 to extend to length 4
            int expectedDiff = (currLen % 2 == 1) ? 1 : -1;
            int actualDiff = nums[i] - nums[i - 1];

            // Check if current pair continues the alternating pattern
            if (actualDiff == expectedDiff) {

                currLen++;  // Extend current alternating subarray
                maxLen = Math.max(maxLen, currLen);

            } else {

                // Pattern broken - try to restart from current pair
                if (actualDiff == 1) {
                    // Current pair has diff +1, can start new alternating subarray
                    currLen = 2;  // New subarray of length 2: [nums[i-1], nums[i]]
                    maxLen = Math.max(maxLen, currLen);
                } else {
                    // Can't even start a new subarray (need diff +1 to start)
                    currLen = 1;  // Reset to single element
                }

            }

        }

        return maxLen;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: nums = [2, 3, 4, 3, 4]
 * 
 * Initial: maxLen = -1, currLen = 1
 * 
 * i = 1: nums[1] = 3, nums[0] = 2
 *        expectedDiff = (1 % 2 == 1) ? 1 : -1 = 1
 *        actualDiff = 3 - 2 = 1
 *        1 == 1 ✓ Pattern continues!
 *        currLen = 2, maxLen = max(-1, 2) = 2
 *        State: maxLen = 2, currLen = 2
 * 
 * i = 2: nums[2] = 4, nums[1] = 3
 *        expectedDiff = (2 % 2 == 1) ? 1 : -1 = -1
 *        actualDiff = 4 - 3 = 1
 *        1 != -1 ✗ Pattern broken!
 *        But actualDiff == 1, so restart: currLen = 2
 *        maxLen = max(2, 2) = 2
 *        State: maxLen = 2, currLen = 2
 * 
 * i = 3: nums[3] = 3, nums[2] = 4
 *        expectedDiff = (2 % 2 == 1) ? 1 : -1 = -1
 *        actualDiff = 3 - 4 = -1
 *        -1 == -1 ✓ Pattern continues!
 *        currLen = 3, maxLen = max(2, 3) = 3
 *        State: maxLen = 3, currLen = 3
 * 
 * i = 4: nums[4] = 4, nums[3] = 3
 *        expectedDiff = (3 % 2 == 1) ? 1 : -1 = 1
 *        actualDiff = 4 - 3 = 1
 *        1 == 1 ✓ Pattern continues!
 *        currLen = 4, maxLen = max(3, 4) = 4
 *        State: maxLen = 4, currLen = 4
 * 
 * Output: 4
 * 
 * 
 * Visualizing the Alternating Pattern:
 * 
 * For subarray [3, 4, 3, 4]:
 * 
 *   Index:     0    1    2    3
 *   Value:     3    4    3    4
 *   Diff:         +1   -1   +1
 *   Expected:     +1   -1   +1   ✓ All match!
 * 
 * currLen → Expected next diff:
 *   1 → +1 (odd)
 *   2 → -1 (even)
 *   3 → +1 (odd)
 *   4 → -1 (even)
 *   ...
 * 
 * 
 * Why Restart Logic Works:
 * 
 * When pattern breaks at index i:
 *   - We check if nums[i] - nums[i-1] == +1
 *   - If yes: [nums[i-1], nums[i]] can start a new alternating subarray
 *   - If no: we can't start anything, reset to length 1
 * 
 * Example: nums = [2, 3, 4, 3, 4]
 *   At i=2: [2,3,4] breaks because 4-3=+1 but expected -1
 *   But [3,4] itself is valid start (diff=+1), so currLen=2
 *   Then [3,4,3,4] continues properly
 */

