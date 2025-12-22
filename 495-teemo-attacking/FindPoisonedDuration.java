/*
 * LeetCode 495: Teemo Attacking
 * 
 * Problem:
 * Our hero Teemo is attacking an enemy Ashe with poison attacks! When Teemo
 * attacks Ashe, Ashe gets poisoned for a exactly duration seconds. More
 * formally, an attack at second t will mean Ashe is poisoned during the
 * inclusive time interval [t, t + duration - 1].
 * 
 * If Teemo attacks again before the poison effect ends, the timer for it is
 * reset, and the poison effect will end duration seconds after the new attack.
 * 
 * You are given a non-decreasing integer array timeSeries, where timeSeries[i]
 * denotes that Teemo attacks Ashe at second timeSeries[i], and an integer duration.
 * 
 * Return the total number of seconds that Ashe is poisoned.
 * 
 * Approach: Calculate Overlapping Intervals
 * 
 * Key Insight:
 * - Each attack poisons for duration seconds
 * - If next attack comes before current poison ends, the overlap is lost
 * - For each attack (except first), add min(duration, timeBetweenAttacks)
 * - First attack always contributes full duration
 * 
 * Algorithm:
 * 1. Start with totalPoisonedTime = duration (first attack)
 * 2. For each subsequent attack:
 *    - Calculate time difference from previous attack
 *    - Add min(duration, timeBetweenAttacks) to total
 *    - This handles both overlapping and non-overlapping cases
 * 3. Return totalPoisonedTime
 * 
 * Time Complexity: O(n)
 *   - n = length of timeSeries
 *   - Single pass through the array
 * 
 * Space Complexity: O(1)
 *   - Only using constant extra space
 * 
 * Example 1:
 *   timeSeries = [1, 4], duration = 2
 *   
 *   Attack at 1: poisons [1, 2] → contributes 2 seconds
 *   Attack at 4: poisons [4, 5] → contributes 2 seconds
 *   Total: 4 seconds
 *   
 *   Using our approach:
 *   totalPoisonedTime = 2 (first attack)
 *   i=1: timeBetween = 4-1 = 3, add min(2, 3) = 2
 *   Total: 2 + 2 = 4 ✓
 * 
 * Example 2:
 *   timeSeries = [1, 2], duration = 2
 *   
 *   Attack at 1: poisons [1, 2] → contributes 2 seconds
 *   Attack at 2: poisons [2, 3] → but [1,2] already covered
 *                Only adds 1 second (from 2 to 3)
 *   Total: 2 + 1 = 3 seconds
 *   
 *   Using our approach:
 *   totalPoisonedTime = 2 (first attack)
 *   i=1: timeBetween = 2-1 = 1, add min(2, 1) = 1
 *   Total: 2 + 1 = 3 ✓
 * 
 * Example 3:
 *   timeSeries = [1, 2, 3, 4, 5], duration = 5
 *   
 *   Attack at 1: poisons [1, 5]
 *   Attack at 2: resets, poisons [2, 6] (overlaps with [1,5])
 *   Attack at 3: resets, poisons [3, 7] (overlaps)
 *   Attack at 4: resets, poisons [4, 8] (overlaps)
 *   Attack at 5: resets, poisons [5, 9] (overlaps)
 *   
 *   Total: 1 + 1 + 1 + 1 + 5 = 9 seconds
 *   
 *   Using our approach:
 *   totalPoisonedTime = 5 (first attack)
 *   i=1: timeBetween = 1, add min(5, 1) = 1
 *   i=2: timeBetween = 1, add min(5, 1) = 1
 *   i=3: timeBetween = 1, add min(5, 1) = 1
 *   i=4: timeBetween = 1, add min(5, 1) = 1
 *   Total: 5 + 1 + 1 + 1 + 1 = 9 ✓
 * 
 * Why This Works?
 * 
 *   For each attack (except the first):
 *   - If timeBetweenAttacks >= duration:
 *     → Previous poison ended before this attack
 *     → Add full duration (no overlap)
 *   
 *   - If timeBetweenAttacks < duration:
 *     → Previous poison was still active
 *     → Overlap = duration - timeBetweenAttacks
 *     → New contribution = timeBetweenAttacks
 *     → Add min(duration, timeBetweenAttacks) = timeBetweenAttacks
 * 
 * Visual Example (timeSeries = [1, 2], duration = 2):
 * 
 *   Timeline:
 *   0  1  2  3  4
 *   |  |  |  |  |
 *   
 *   Attack 1 at t=1: [1, 2] poisoned
 *   Attack 2 at t=2: [2, 3] poisoned (overlaps with [1,2])
 *   
 *   Total poisoned: [1, 3] = 3 seconds
 *   
 *   Calculation:
 *   - First attack: 2 seconds
 *   - Second attack: timeBetween = 1, add min(2, 1) = 1 second
 *   - Total: 3 seconds ✓
 * 
 * Edge Cases:
 * 
 *   - Single attack: timeSeries = [1], duration = 2
 *     Result: 2 (only first attack contributes)
 * 
 *   - No overlap: timeSeries = [1, 5, 10], duration = 2
 *     Attack 1: [1, 2] → 2 seconds
 *     Attack 2: [5, 6] → 2 seconds (5-1=4 >= 2, no overlap)
 *     Attack 3: [10, 11] → 2 seconds (10-5=5 >= 2, no overlap)
 *     Total: 6 seconds
 * 
 *   - Continuous attacks: timeSeries = [1, 2, 3], duration = 2
 *     Attack 1: [1, 2] → 2 seconds
 *     Attack 2: [2, 3] → 1 second (overlap)
 *     Attack 3: [3, 4] → 1 second (overlap)
 *     Total: 4 seconds
 */

class Solution {

    public int findPoisonedDuration(int[] timeSeries, int duration) {

        int arrayLength = timeSeries.length;

        int totalPoisonedTime = duration;

        // Iterate through consecutive attack times
        for (int i = 1; i < arrayLength; i++) {

            // Calculate the time difference between current and previous attack
            int timeBetweenAttacks = timeSeries[i] - timeSeries[i - 1];

            // Add the minimum of duration and time between attacks
            // This handles both overlapping and non-overlapping cases
            totalPoisonedTime += Math.min(duration, timeBetweenAttacks);

        }

        // Return the total time the target remains poisoned
        return totalPoisonedTime;

    }

}

