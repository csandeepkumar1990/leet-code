/**
 * LeetCode 3038: Maximum Number of Operations With the Same Score I
 * 
 * Remove first two elements repeatedly. Count how many times
 * consecutive pairs have the same sum as the first pair.
 * Stop when sum differs.
 */

class Solution {

    public int maxOperations(int[] nums) {
        // First pair sets the target score
        int score = nums[0] + nums[1];
        int count = 1;

        // Check consecutive pairs from index 2 onwards
        for (int i = 2; i < nums.length - 1; i += 2) {
            if (nums[i] + nums[i + 1] == score) {
                count++;
            } else {
                break;  // Stop at first mismatch
            }
        }

        return count;
    }
}

