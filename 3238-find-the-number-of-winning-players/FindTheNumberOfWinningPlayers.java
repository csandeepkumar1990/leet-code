/*
 * LeetCode 3238: Find the Number of Winning Players
 *
 * Problem:
 * There are n players numbered from 0 to n - 1. Each player picks balls of
 * different colors. A player wins if they have picked at least (player_id + 1)
 * balls of the same color.
 *
 * Given an integer n and a 2D array pick where pick[i] = [player_id, color],
 * return the number of winning players.
 *
 * Approach: Count Colors Per Player and Check Winning Condition
 *
 * Key Insight:
 * - A player wins if they have at least (player_id + 1) balls of any single color
 * - Player 0 wins if they pick at least 1 ball of any color
 * - Player 1 wins if they pick at least 2 balls of the same color
 * - Player 2 wins if they pick at least 3 balls of the same color
 * - etc.
 *
 * Algorithm:
 * 1. Build a map: player -> (color -> count) to track ball counts per color
 *    for each player
 * 2. For each pick, increment the count for that player-color combination
 * 3. For each player, check if any color has count >= (player_id + 1)
 * 4. Count all winning players
 *
 * Time Complexity: O(p + n * c), where p is the number of picks, n is the
 *   number of players, and c is the average number of colors per player.
 *   - Building the map: O(p)
 *   - Checking winners: O(n * c)
 * Space Complexity: O(n * c) for storing the nested map structure.
 *
 * Example:
 *
 *   Input: n = 4, pick = [[0,0],[1,0],[1,0],[2,1],[2,1],[2,0]]
 *
 *   Step 1: Build color counts per player
 *   Player 0: color 0 -> 1
 *   Player 1: color 0 -> 2
 *   Player 2: color 0 -> 1, color 1 -> 2
 *   Player 3: (no picks)
 *
 *   Step 2: Check winning conditions
 *   Player 0: color 0 count = 1 >= 0+1 = 1 → wins ✓
 *   Player 1: color 0 count = 2 >= 1+1 = 2 → wins ✓
 *   Player 2: max count = 2 < 2+1 = 3 → doesn't win ✗
 *   Player 3: no picks → doesn't win ✗
 *
 *   Output: 2
 *
 * Winning Condition Breakdown:
 *   - Player i wins if any color count >= (i + 1)
 *   - Player 0: needs >= 1 ball of same color
 *   - Player 1: needs >= 2 balls of same color
 *   - Player 2: needs >= 3 balls of same color
 *   - Player 3: needs >= 4 balls of same color
 *
 * Important Notes:
 * - The winning condition is based on a SINGLE color count, not total balls
 * - A player must have at least (player_id + 1) balls of the SAME color
 * - Players with no picks cannot win
 * - We check all colors for each player to find if any meets the threshold
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

class Solution {

    public int winningPlayerCount(int n, int[][] pick) {
        Map<Integer, Map<Integer, Integer>> playerColors = new HashMap<>();
        
        // Build map: player -> (color -> count)
        for (int[] p : pick) {
            int player = p[0];
            int color = p[1];
            playerColors.putIfAbsent(player, new HashMap<>());
            Map<Integer, Integer> colorCount = playerColors.get(player);
            colorCount.put(color, colorCount.getOrDefault(color, 0) + 1);
        }

        int winners = 0;
        
        // Check each player for winning condition
        for (int player = 0; player < n; player++) {
            Map<Integer, Integer> colorCount = playerColors.getOrDefault(player, Collections.emptyMap());
            boolean wins = false;
            
            // Check if any color has count >= (player + 1)
            for (int count : colorCount.values()) {
                if (count >= player + 1) {
                    wins = true;
                    break;
                }
            }
            
            if (wins) winners++;
        }

        return winners;
    }
}

