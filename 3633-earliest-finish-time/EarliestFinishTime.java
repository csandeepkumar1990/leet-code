/*
 * LeetCode 3633: Earliest Finish Time
 * 
 * Problem:
 * You need to complete two activities: one on land and one in water.
 * Each activity has multiple available time slots with start times and durations.
 * Activities cannot overlap - you must finish one before starting the other.
 * Find the earliest possible time to complete both activities.
 * 
 * Approach: Brute Force - Try All Combinations & Both Orders
 * 
 * Key Insight:
 * - We must try every pair of (land slot, water slot)
 * - For each pair, we have TWO orderings to consider:
 *   1. Land first, then Water
 *   2. Water first, then Land
 * - The second activity can only start after:
 *   - The first activity finishes, AND
 *   - Its own start time arrives (whichever is later)
 * 
 * Algorithm:
 * 1. For each land slot i and water slot j:
 *    - Calculate finish time for Land→Water order
 *    - Calculate finish time for Water→Land order
 *    - Track the minimum across all combinations
 * 
 * Time Complexity: O(n * m) where n = land slots, m = water slots
 * Space Complexity: O(1) - only using a few variables
 * 
 * Example:
 * landStartTime = [0, 5], landDuration = [2, 3]
 * waterStartTime = [1, 4], waterDuration = [3, 2]
 * 
 * Try land[0] + water[0]:
 *   Land→Water: finish land at 0+2=2, water starts max(2,1)=2, finish at 2+3=5
 *   Water→Land: finish water at 1+3=4, land starts max(4,0)=4, finish at 4+2=6
 * 
 * Try land[0] + water[1]:
 *   Land→Water: finish land at 2, water starts max(2,4)=4, finish at 4+2=6
 *   Water→Land: finish water at 4+2=6, land starts max(6,0)=6, finish at 6+2=8
 * 
 * ... and so on. Return the minimum finish time found.
 * 
 * Visual Timeline (Land→Water):
 * 
 *   Land:    [====]                    (start=0, duration=2, finish=2)
 *   Water:         [======]            (start=max(2,1)=2, duration=3, finish=5)
 *            0  1  2  3  4  5
 */

class Solution {

    public int earliestFinishTime(int[] landStartTime, int[] landDuration, int[] waterStartTime, int[] waterDuration) {

        // Track the minimum finish time across all combinations
        int minFinish = Integer.MAX_VALUE;

        // Try every combination of land slot and water slot
        for (int i = 0; i < landStartTime.length; i++) {

            for (int j = 0; j < waterStartTime.length; j++) {

                // ========== Case 1: Land first, then Water ==========
                // Complete land activity first
                int finishLand = landStartTime[i] + landDuration[i];

                // Water can start when:
                // - Land is finished, AND
                // - Water's scheduled start time arrives
                // (whichever comes later)
                int startWater = Math.max(finishLand, waterStartTime[j]);

                // Total finish time for this order
                int finishLW = startWater + waterDuration[j];

                minFinish = Math.min(minFinish, finishLW);

                // ========== Case 2: Water first, then Land ==========
                // Complete water activity first
                int finishWater = waterStartTime[j] + waterDuration[j];

                // Land can start when:
                // - Water is finished, AND  
                // - Land's scheduled start time arrives
                // (whichever comes later)
                int startLand = Math.max(finishWater, landStartTime[i]);

                // Total finish time for this order
                int finishWL = startLand + landDuration[i];

                minFinish = Math.min(minFinish, finishWL);

            }

        }

        return minFinish;

    }

}

