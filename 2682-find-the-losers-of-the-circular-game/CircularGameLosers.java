/**
 * LeetCode 2682: Find the Losers of the Circular Game
 * 
 * n friends in circle pass ball. Each turn i, move i*k steps clockwise.
 * Game ends when someone receives ball twice.
 * Return friends who never received the ball (1-indexed).
 */

import java.util.*;

class Solution {

    public int[] circularGameLosers(int n, int k) {
        boolean[] received = new boolean[n];
        int current = 0;  // 0-based index (friend 1)
        int step = 1;     // step multiplier

        // Pass ball until someone receives it twice
        while (!received[current]) {
            received[current] = true;
            current = (current + step * k) % n;  // move i*k steps clockwise
            step++;
        }

        // Collect losers (never received ball)
        List<Integer> losers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!received[i]) {
                losers.add(i + 1);  // convert to 1-based
            }
        }

        // Convert list to array
        int[] result = new int[losers.size()];
        for (int i = 0; i < losers.size(); i++) {
            result[i] = losers.get(i);
        }

        return result;
    }
}

