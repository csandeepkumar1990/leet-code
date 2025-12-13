/*
 * LeetCode 3168: Minimum Number of Chairs in a Waiting Room
 * 
 * Problem:
 * Given a string s where:
 * - 'E' means a person enters the waiting room
 * - 'L' means a person leaves the waiting room
 * 
 * Return the minimum number of chairs needed so that everyone
 * who enters can sit down.
 * 
 * Approach: Track Peak Occupancy
 * 
 * Key Insight:
 * - We need enough chairs for the MAXIMUM number of people present at any time
 * - This is the "peak concurrent occupancy"
 * - Simulate: increment on 'E', decrement on 'L', track maximum
 * 
 * Algorithm:
 * 1. Track current occupancy (people in room)
 * 2. For each event:
 *    - 'E' (Enter): current++, update max
 *    - 'L' (Leave): current--
 * 3. Return the maximum occupancy seen
 * 
 * Time Complexity: O(n) where n = length of s
 * Space Complexity: O(1) - just tracking two integers
 * 
 * Example: s = "EEEELLLLL"
 * 
 * Event-by-event:
 *   'E': current = 1, max = 1
 *   'E': current = 2, max = 2
 *   'E': current = 3, max = 3
 *   'E': current = 4, max = 4  ← PEAK!
 *   'L': current = 3
 *   'L': current = 2
 *   'L': current = 1
 *   'L': current = 0
 *   'L': current = -1 (edge case, but problem guarantees valid input)
 * 
 * Return: 4
 * 
 * Example: s = "ELELEL"
 * 
 *   'E': current = 1, max = 1
 *   'L': current = 0
 *   'E': current = 1, max = 1
 *   'L': current = 0
 *   'E': current = 1, max = 1
 *   'L': current = 0
 * 
 * Return: 1 (only ever 1 person at a time)
 * 
 * Visual (Occupancy Over Time):
 * 
 *   s = "EELELLE"
 *   
 *   occupancy
 *       │
 *     3 │      ●
 *       │     /│\
 *     2 │    ● │ ●
 *       │   /  │  \
 *     1 │  ●   │   ●
 *       │ /    │    \
 *     0 └─┴────┴─────●──→ events
 *         E E L E L L E
 *   
 *   Peak = 3 chairs needed
 * 
 * Real-world Analogy:
 * - Think of a parking lot or meeting room
 * - Need enough spaces for peak demand
 * - Don't care about average, only maximum concurrent usage
 */

class Solution {

    public int minimumChairs(String s) {

        // Current number of people in the waiting room
        int current = 0;

        // Maximum occupancy seen (peak demand)
        int maxChairs = 0;

        // Process each event
        for (char c : s.toCharArray()) {

            if (c == 'E') {

                // Someone enters - need a chair
                current++;

                // Update peak if this is a new maximum
                maxChairs = Math.max(maxChairs, current);

            } else if (c == 'L') {

                // Someone leaves - frees up a chair
                current--;

            }

        }

        // Return the peak occupancy = minimum chairs needed
        return maxChairs;

    }

}

