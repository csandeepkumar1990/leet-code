/**
 * LeetCode 3314: Construct the Minimum Bitwise Array I
 * 
 * For each num, find minimum x where x | (x+1) = num.
 * Find first 0-bit from position 1, flip bit at (position-1).
 * Special case: num=2 has no valid answer.
 */

import java.util.List;

class Solution {

    public int[] minBitwiseArray(List<Integer> nums) {
        int n = nums.size();
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            int currentNum = nums.get(i);

            // No valid x exists for 2 (x | (x+1) cannot equal 2)
            if (currentNum == 2) {
                result[i] = -1;
            } else {
                // Find first 0-bit starting from position 1
                for (int bitPosition = 1; bitPosition < 32; bitPosition++) {
                    if ((currentNum >> bitPosition & 1) == 0) {
                        // Flip bit at (bitPosition - 1) to get minimum x
                        result[i] = currentNum ^ (1 << (bitPosition - 1));
                        break;
                    }
                }
            }
        }

        return result;
    }
}

