/**
 * LeetCode 3178: Find the Child Who Has the Ball After K Seconds
 * 
 * Problem: There are n children numbered from 0 to n-1 standing in a queue.
 *          Child 0 initially holds a ball and passes it to the right.
 *          After each second, the ball is passed to the next child.
 *          When the ball reaches either end (child 0 or n-1), direction reverses.
 *          Return the number of the child who has the ball after k seconds.
 * 
 * Key Insight: The ball movement forms a cycle of length 2*(n-1).
 *              Use modulo to find position within the current cycle.
 * 
 * Examples:
 *   Input: n = 3, k = 5
 *   Output: 1
 *   Explanation: Passes: 0→1→2→1→0→1 (5 passes), child 1 has the ball.
 * 
 *   Input: n = 5, k = 6
 *   Output: 2
 *   Explanation: Passes: 0→1→2→3→4→3→2 (6 passes), child 2 has the ball.
 * 
 *   Input: n = 4, k = 2
 *   Output: 2
 *   Explanation: Passes: 0→1→2 (2 passes), child 2 has the ball.
 */

class Solution {

    /**
     * Determines which child has the ball after k seconds.
     * 
     * @param n - The number of children
     * @param k - The number of seconds
     * @return The index of the child holding the ball
     * 
     * Time Complexity: O(1) - constant time calculation
     * Space Complexity: O(1) - only using a few variables
     */
    public int numberOfChild(int n, int k) {
        /*
         * STEP 1: Calculate the cycle length
         * 
         * The ball bounces back and forth between child 0 and child n-1.
         * One full cycle: 0 → 1 → 2 → ... → (n-1) → (n-2) → ... → 1 → 0
         * 
         * Going right: takes (n-1) steps to reach from 0 to n-1
         * Going left:  takes (n-1) steps to reach from n-1 back to 0
         * Total cycle = 2 * (n-1) seconds
         * 
         * Example: n=4 → cycle = 2*(4-1) = 6
         * Path: 0→1→2→3→2→1→0 (repeats)
         */
        int cycleLength = 2 * (n - 1);
        
        /*
         * STEP 2: Find position within the current cycle using modulo
         * 
         * Since the pattern repeats every cycleLength seconds,
         * we only need to know where we are within one cycle.
         * 
         * Example: n=4, k=10
         * cycleLength = 6, remainingTime = 10 % 6 = 4
         * So k=10 is equivalent to k=4 in terms of position
         */
        int remainingTime = k % cycleLength;
        
        /*
         * STEP 3: Determine direction and calculate position
         * 
         * First half of cycle (0 to n-1): Ball moves RIGHT
         *   - remainingTime directly gives us the position
         *   - remainingTime = 0 → position 0
         *   - remainingTime = 1 → position 1
         *   - remainingTime = n-1 → position n-1
         * 
         * Second half of cycle (n to 2n-3): Ball moves LEFT
         *   - Ball has reached n-1 and is bouncing back
         *   - stepsPast = how many steps past the rightmost child
         *   - Subtract stepsPast from (n-1) to get position
         */
        if (remainingTime < n) {
            // Ball is in first half of cycle, moving RIGHT (0 → n-1)
            // Position equals remainingTime directly
            return remainingTime;
        } else {
            // Ball is in second half of cycle, moving LEFT (n-1 → 0)
            // Calculate how many steps past position (n-1)
            int stepsPast = remainingTime - (n - 1);
            // Subtract from rightmost position to get current position
            return (n - 1) - stepsPast;
        }
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.numberOfChild(3, 5)); // Output: 1
 * System.out.println(sol.numberOfChild(5, 6)); // Output: 2
 * System.out.println(sol.numberOfChild(4, 2)); // Output: 2
 * 
 * ═══════════════════════════════════════════════════════════════
 * CYCLE VISUALIZATION for n=4 (children: 0, 1, 2, 3)
 * ═══════════════════════════════════════════════════════════════
 * 
 * cycleLength = 2 * (4-1) = 6
 * 
 * k:         0    1    2    3    4    5    6    7    8    ...
 * Position:  0 → 1 → 2 → 3 → 2 → 1 → 0 → 1 → 2 → ...
 *            |------ RIGHT ------|------ LEFT ------|
 *            |<-------- ONE FULL CYCLE (6 steps) -------->|
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: n=5, k=6
 * ═══════════════════════════════════════════════════════════════
 * 
 * Step 1: cycleLength = 2 * (5-1) = 8
 * 
 * Step 2: remainingTime = 6 % 8 = 6
 * 
 * Step 3: Is 6 < 5? NO → Ball is moving LEFT
 *         stepsPast = 6 - (5-1) = 6 - 4 = 2
 *         position = (5-1) - 2 = 4 - 2 = 2
 * 
 * Answer: Child 2 has the ball ✓
 * 
 * Verification: 0→1→2→3→4→3→2 (6 passes) → position 2 ✓
 */

