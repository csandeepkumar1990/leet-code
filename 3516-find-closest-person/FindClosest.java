/**
 * LeetCode 3516: Find Closest Person
 * 
 * Problem: Given three integers x, y, and z representing positions on a number line:
 *          - x is the position of Person 1
 *          - y is the position of Person 2
 *          - z is the position of Person 3 (stationary target)
 *          
 *          Both Person 1 and Person 2 move toward Person 3 at the same speed.
 *          Determine which person reaches Person 3 first.
 *          
 *          Return:
 *          - 1 if Person 1 arrives first
 *          - 2 if Person 2 arrives first
 *          - 0 if both arrive at the same time
 * 
 * Key Insight: Since both move at the same speed, whoever is CLOSER arrives first!
 *              Simply compare absolute distances: |x - z| vs |y - z|
 * 
 * Examples:
 *   Input: x = 2, y = 7, z = 4
 *   Output: 1
 *   Explanation:
 *     - Person 1 distance: |2 - 4| = 2
 *     - Person 2 distance: |7 - 4| = 3
 *     - Person 1 is closer → returns 1
 * 
 *   Input: x = 2, y = 5, z = 6
 *   Output: 2
 *   Explanation:
 *     - Person 1 distance: |2 - 6| = 4
 *     - Person 2 distance: |5 - 6| = 1
 *     - Person 2 is closer → returns 2
 * 
 *   Input: x = 1, y = 5, z = 3
 *   Output: 0
 *   Explanation:
 *     - Person 1 distance: |1 - 3| = 2
 *     - Person 2 distance: |5 - 3| = 2
 *     - Both equidistant → returns 0
 * 
 * Constraints:
 *   - -10^9 <= x, y, z <= 10^9
 */

class Solution {

    /**
     * Determines which person reaches the target first.
     * 
     * @param x - Position of Person 1
     * @param y - Position of Person 2
     * @param z - Position of Person 3 (target)
     * @return 1 if Person 1 closer, 2 if Person 2 closer, 0 if tie
     * 
     * Time Complexity: O(1) - constant time arithmetic
     * Space Complexity: O(1) - no extra space needed
     */
    public int findClosest(int x, int y, int z) {
        /*
         * Calculate absolute distances to target z
         * 
         * Math.abs() handles negative positions correctly:
         *   |(-5) - 3| = |-8| = 8
         *   |5 - 3| = |2| = 2
         * 
         * Distance = how many steps needed to reach z
         */
        int distance1 = Math.abs(x - z);
        int distance2 = Math.abs(y - z);

        /*
         * Compare distances:
         * - Equal distances → tie (return 0)
         * - distance1 smaller → Person 1 arrives first (return 1)
         * - distance2 smaller → Person 2 arrives first (return 2)
         */
        if (distance1 == distance2)
            return 0;
        return (distance1 < distance2 ? 1 : 2);
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.findClosest(2, 7, 4)); // Output: 1
 * System.out.println(sol.findClosest(2, 5, 6)); // Output: 2
 * System.out.println(sol.findClosest(1, 5, 3)); // Output: 0
 * 
 * ═══════════════════════════════════════════════════════════════
 * NUMBER LINE VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: x = 2, y = 7, z = 4
 * 
 *     Person 1          Target      Person 2
 *        ↓                ↓            ↓
 *   ─────●────────────────●────────────●─────
 *        2                4            7
 *        
 *        |<──── 2 ────>|  |<──── 3 ────>|
 *           distance1        distance2
 *        
 *        Person 1 is CLOSER (2 < 3) → returns 1
 * 
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: x = 1, y = 5, z = 3 (TIE)
 * 
 *     Person 1     Target     Person 2
 *        ↓           ↓           ↓
 *   ─────●───────────●───────────●─────
 *        1           3           5
 *        
 *        |<── 2 ──>| |<── 2 ──>|
 *         distance1   distance2
 *        
 *        Both EQUIDISTANT (2 == 2) → returns 0
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY Math.abs() IS ESSENTIAL
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Position can be on EITHER side of target:
 *   
 *   Case 1: Person to the LEFT of target
 *   ────●───────────●────
 *       x=2        z=5
 *       distance = |2 - 5| = |-3| = 3 ✓
 *   
 *   Case 2: Person to the RIGHT of target  
 *   ────●───────────●────
 *      z=2         x=5
 *       distance = |5 - 2| = |3| = 3 ✓
 *   
 *   Without abs(): 2 - 5 = -3 (WRONG - distance can't be negative!)
 * 
 * ═══════════════════════════════════════════════════════════════
 * HANDLING NEGATIVE POSITIONS
 * ═══════════════════════════════════════════════════════════════
 * 
 *   x = -5, y = 3, z = 0
 *   
 *        Person 1     Target   Person 2
 *           ↓           ↓         ↓
 *   ────────●───────────●─────────●────
 *          -5           0         3
 *           
 *   distance1 = |-5 - 0| = 5
 *   distance2 = |3 - 0| = 3
 *   
 *   Person 2 is closer → returns 2
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Person already at target:
 *    x = 5, y = 10, z = 5
 *    → distance1 = 0, distance2 = 5
 *    → Person 1 wins instantly (returns 1)
 * 
 * 2. Both at target:
 *    x = 5, y = 5, z = 5
 *    → distance1 = 0, distance2 = 0
 *    → Tie (returns 0)
 * 
 * 3. All same position:
 *    x = 3, y = 3, z = 3
 *    → Both distances = 0 → Tie (returns 0)
 * 
 * 4. Target between two persons (equidistant):
 *    x = 0, y = 10, z = 5
 *    → distance1 = 5, distance2 = 5
 *    → Tie (returns 0)
 * 
 * 5. Large negative numbers:
 *    x = -1000000000, y = 1000000000, z = 0
 *    → Both distances = 1000000000 → Tie (returns 0)
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE ONE-LINER SOLUTION
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int findClosest(int x, int y, int z) {
 *       return Integer.compare(Math.abs(x - z), Math.abs(y - z));
 *   }
 *   
 *   Wait, this returns -1, 0, 1 instead of 1, 0, 2!
 *   We need custom logic, so the explicit if-else is cleaner.
 * 
 * ═══════════════════════════════════════════════════════════════
 * COMPACT TERNARY VERSION
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int findClosest(int x, int y, int z) {
 *       int d1 = Math.abs(x - z), d2 = Math.abs(y - z);
 *       return d1 == d2 ? 0 : (d1 < d2 ? 1 : 2);
 *   }
 */

