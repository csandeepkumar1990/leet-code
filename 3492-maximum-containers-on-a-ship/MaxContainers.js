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
 *     - Weight limit: floor(15 / 3) = 5 containers
 *     - Answer: min(4, 5) = 4 (space is the bottleneck)
 * 
 *   Input: n = 3, w = 5, maxWeight = 20
 *   Output: 4
 *   Explanation:
 *     - Space limit: 3 × 3 = 9 containers
 *     - Weight limit: floor(20 / 5) = 4 containers
 *     - Answer: min(9, 4) = 4 (weight is the bottleneck)
 * 
 * Constraints:
 *   - 1 <= n, w, maxWeight <= 100
 */

/**
 * Returns the maximum number of containers that can be loaded.
 * 
 * @param {number} n - The dimension of the cargo deck (n x n)
 * @param {number} w - The weight of each container
 * @param {number} maxWeight - The ship's maximum weight capacity
 * @return {number} - Maximum containers that can be loaded
 * 
 * Time Complexity: O(1) - simple arithmetic operations
 * Space Complexity: O(1) - no extra space needed
 */
var maxContainers = function(n, w, maxWeight) {
    /*
     * Two constraints must be satisfied:
     * 
     * 1. SPACE CONSTRAINT
     *    The deck is n × n, so maximum containers = n * n
     *    Even if we had infinite weight capacity, we can't fit more.
     * 
     * 2. WEIGHT CONSTRAINT  
     *    Total weight of containers ≤ maxWeight
     *    If each container weighs w, max containers = floor(maxWeight / w)
     *    We use floor because we can't load partial containers.
     * 
     * The actual answer is the MINIMUM of both constraints.
     */
    
    const maxBySpace = n * n;
    const maxByWeight = Math.floor(maxWeight / w);
    
    return Math.min(maxBySpace, maxByWeight);
};

/**
 * One-liner solution (same logic, condensed)
 */
var maxContainersOneLiner = function(n, w, maxWeight) {
    return Math.min(n * n, Math.floor(maxWeight / w));
};

/**
 * Usage Example:
 * 
 * console.log(maxContainers(2, 3, 15)); // Output: 4
 * console.log(maxContainers(3, 5, 20)); // Output: 4
 * console.log(maxContainers(2, 2, 3));  // Output: 1
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
 * WHY Math.floor() IS NEEDED
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Example: maxWeight = 10, w = 3
 *   
 *   10 / 3 = 3.333...
 *   
 *   We can't load 3.33 containers!
 *   We can only load WHOLE containers.
 *   
 *   Math.floor(3.333) = 3 containers
 *   
 *   Verification: 3 × 3 = 9 ≤ 10 ✓
 *                 4 × 3 = 12 > 10 ✗
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Container heavier than maxWeight:
 *    n = 5, w = 10, maxWeight = 5
 *    → floor(5/10) = 0 containers (can't load even one!)
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
 */

module.exports = { maxContainers, maxContainersOneLiner };

