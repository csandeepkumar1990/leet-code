/**
 * LeetCode 3492: Maximum Containers on a Ship
 * 
 * Problem: You are given an n × n cargo deck on a ship and need to load containers.
 *          Each cell on the deck can hold exactly one container.
 *          Each container weighs w units.
 *          The ship has a maximum weight capacity of maxWeight.
 *          
 *          Return the maximum number of containers that can be loaded onto the ship.
 * 
 * Key Insight: We have TWO constraints to satisfy:
 *              1. Space constraint: Can't exceed n × n containers (deck size)
 *              2. Weight constraint: Can't exceed maxWeight (ship capacity)
 *              
 *              The answer is the MINIMUM of these two limits!
 * 
 * Examples:
 *   Input: n = 2, w = 3, maxWeight = 15
 *   Output: 4
 *   Explanation: 
 *     - Space limit: 2 × 2 = 4 containers
 *     - Weight limit: 15 / 3 = 5 containers
 *     - Answer: min(4, 5) = 4 (space is the bottleneck)
 * 
 *   Input: n = 3, w = 5, maxWeight = 20
 *   Output: 4
 *   Explanation:
 *     - Space limit: 3 × 3 = 9 containers
 *     - Weight limit: 20 / 5 = 4 containers
 *     - Answer: min(9, 4) = 4 (weight is the bottleneck)
 * 
 * Constraints:
 *   - 1 <= n, w, maxWeight <= 100
 */

class Solution {

    /**
     * Returns the maximum number of containers that can be loaded.
     * 
     * @param n - The dimension of the cargo deck (n x n)
     * @param w - The weight of each container
     * @param maxWeight - The ship's maximum weight capacity
     * @return Maximum containers that can be loaded
     * 
     * Time Complexity: O(1) - simple arithmetic operations
     * Space Complexity: O(1) - no extra space needed
     */
    public int maxContainers(int n, int w, int maxWeight) {
        /*
         * Two constraints must be satisfied:
         * 
         * 1. SPACE CONSTRAINT
         *    The deck is n × n, so maximum containers = n * n
         *    Even if we had infinite weight capacity, we can't fit more.
         * 
         * 2. WEIGHT CONSTRAINT  
         *    Total weight of containers ≤ maxWeight
         *    If each container weighs w, max containers = maxWeight / w
         *    Integer division automatically floors the result in Java.
         * 
         * The actual answer is the MINIMUM of both constraints.
         */
        
        int maxBySpace = n * n;
        int maxByWeight = maxWeight / w;  // Integer division floors automatically
        
        return Math.min(maxBySpace, maxByWeight);
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.maxContainers(2, 3, 15)); // Output: 4
 * System.out.println(sol.maxContainers(3, 5, 20)); // Output: 4
 * System.out.println(sol.maxContainers(2, 2, 3));  // Output: 1
 * 
 * ═══════════════════════════════════════════════════════════════
 * CARGO DECK VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: n = 3, w = 5, maxWeight = 20
 * 
 *          3 × 3 Cargo Deck
 *        ┌─────┬─────┬─────┐
 *        │ [■] │ [■] │ [■] │   ← Row 1
 *        │ 5kg │ 5kg │ 5kg │
 *        ├─────┼─────┼─────┤
 *        │ [■] │ [ ] │ [ ] │   ← Row 2
 *        │ 5kg │     │     │
 *        ├─────┼─────┼─────┤
 *        │ [ ] │ [ ] │ [ ] │   ← Row 3
 *        │     │     │     │
 *        └─────┴─────┴─────┘
 *        
 *        [■] = Container loaded (5kg each)
 *        [ ] = Empty cell
 *        
 *        Total loaded: 4 containers
 *        Total weight: 4 × 5 = 20kg ≤ maxWeight (20kg) ✓
 *        
 *        Can't load 5th container: 5 × 5 = 25kg > 20kg ✗
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY MIN() OF TWO CONSTRAINTS?
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Scenario 1: SPACE is the bottleneck
 *   ─────────────────────────────────────
 *   n = 2, w = 1, maxWeight = 100
 *   
 *   Space: 2 × 2 = 4 containers max
 *   Weight: 100 / 1 = 100 containers max
 *   
 *   Answer: 4 (limited by deck space, not weight)
 *   
 *   
 *   Scenario 2: WEIGHT is the bottleneck
 *   ─────────────────────────────────────
 *   n = 10, w = 50, maxWeight = 100
 *   
 *   Space: 10 × 10 = 100 containers max
 *   Weight: 100 / 50 = 2 containers max
 *   
 *   Answer: 2 (limited by weight, not space)
 * 
 * ═══════════════════════════════════════════════════════════════
 * JAVA INTEGER DIVISION (NO Math.floor() NEEDED!)
 * ═══════════════════════════════════════════════════════════════
 * 
 *   In Java, dividing two integers automatically truncates:
 *   
 *   int result = 10 / 3;  // result = 3 (not 3.333...)
 *   int result = 7 / 2;   // result = 3 (not 3.5)
 *   
 *   This is different from JavaScript where:
 *   
 *   let result = 10 / 3;  // result = 3.333... (float!)
 *   let result = Math.floor(10 / 3);  // result = 3
 *   
 *   So in Java, maxWeight / w already gives us the floor!
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Container heavier than maxWeight:
 *    n = 5, w = 10, maxWeight = 5
 *    → 5 / 10 = 0 containers (can't load even one!)
 * 
 * 2. Exactly fills capacity:
 *    n = 2, w = 4, maxWeight = 16
 *    → Space: 4, Weight: 4 → 4 containers (perfect fit)
 * 
 * 3. Single cell deck:
 *    n = 1, w = 1, maxWeight = 100
 *    → Space: 1, Weight: 100 → 1 container (space limited)
 * 
 * 4. Very light containers:
 *    n = 2, w = 1, maxWeight = 1000
 *    → Space: 4, Weight: 1000 → 4 containers (space limited)
 * 
 * ═══════════════════════════════════════════════════════════════
 * ONE-LINER SOLUTION
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int maxContainers(int n, int w, int maxWeight) {
 *       return Math.min(n * n, maxWeight / w);
 *   }
 */

