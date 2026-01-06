/*
 * LeetCode 2833: Furthest Point From Origin
 * 
 * Problem:
 * You are given a string moves of length n consisting of only characters 'L', 'R', and '_'.
 * The string represents your movement on a number line starting from position 0.
 * 
 * In one move, you can:
 * - Move one step to the left if the character is 'L'
 * - Move one step to the right if the character is 'R'
 * - Move one step in any direction (left or right) if the character is '_'
 * 
 * Return the distance of the furthest point from origin (position 0) you can get to after
 * making all moves in any order you choose.
 * 
 * Approach: Greedy Strategy with Wildcard Optimization
 * 
 * Key Insight:
 * - Count the number of 'L', 'R', and '_' moves
 * - The net displacement from origin is determined by the difference between L and R moves
 * - All wildcards ('_') should be used in the direction that maximizes distance
 * - Maximum distance = |L_count - R_count| + underscore_count
 *   - The absolute difference gives the net displacement from fixed moves
 *   - All wildcards add to this distance in the dominant direction
 * 
 * Algorithm:
 * 1. Count occurrences of 'L', 'R', and '_' in the moves string
 * 2. Calculate the absolute difference between L and R counts
 * 3. Add the count of underscores (wildcards) to this difference
 * 4. Return the result
 * 
 * Time Complexity: O(n)
 *   - Single pass through the string to count characters: O(n)
 *   - Constant time calculations
 *   - Total: O(n) where n is the length of moves
 * 
 * Space Complexity: O(1)
 *   - Only using a few counter variables
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: moves = "L_RL__"
 * 
 *   Step 1: Count characters
 *     L: 2 occurrences
 *     R: 1 occurrence
 *     _: 3 occurrences
 * 
 *   Step 2: Calculate distance
 *     |L_count - R_count| = |2 - 1| = 1
 *     Add underscores: 1 + 3 = 4
 * 
 *   Result: 4
 * 
 *   Explanation:
 *     - Net displacement from fixed moves: 2 L - 1 R = 1 step left
 *     - All 3 wildcards can be used to go left: 3 more steps left
 *     - Total distance from origin: 1 + 3 = 4
 * 
 * Another Example:
 * 
 *   Input: moves = "R_L_"
 * 
 *   Step 1: Count characters
 *     L: 1 occurrence
 *     R: 1 occurrence
 *     _: 2 occurrences
 * 
 *   Step 2: Calculate distance
 *     |L_count - R_count| = |1 - 1| = 0
 *     Add underscores: 0 + 2 = 2
 * 
 *   Result: 2
 * 
 *   Explanation:
 *     - Net displacement from fixed moves: 1 L - 1 R = 0 (back to origin)
 *     - All 2 wildcards can be used in one direction: 2 steps
 *     - Total distance: 0 + 2 = 2
 * 
 * Visual Representation:
 * 
 *   moves = "L_RL__"
 *   Position: 0 (start)
 * 
 *   Fixed moves analysis:
 *     L: move left (-1)
 *     R: move right (+1)
 *     L: move left (-1)
 *     Net: -1 (1 step left from origin)
 * 
 *   Wildcards (_): 3
 *     All can be used to go left: -3
 *     Total displacement: -1 - 3 = -4
 *     Distance from origin: | -4 | = 4
 * 
 *   Or all can be used to go right: +3
 *     Total displacement: -1 + 3 = +2
 *     Distance from origin: | +2 | = 2
 * 
 *   Maximum distance: max(4, 2) = 4
 * 
 * Mathematical Proof:
 * 
 *   Let:
 *     L = count of 'L' moves
 *     R = count of 'R' moves
 *     W = count of '_' moves (wildcards)
 * 
 *   Net displacement from fixed moves: L - R
 *   (positive means left, negative means right, or vice versa depending on convention)
 * 
 *   To maximize distance, we want to maximize |final_position|
 *   Final position = (L - R) + W * direction
 * 
 *   If (L - R) >= 0, use all wildcards in the same direction: |L - R + W|
 *   If (L - R) < 0, use all wildcards in the opposite direction: |L - R - W|
 * 
 *   In both cases: |L - R| + W
 *   This is because:
 *     - |L - R + W| = |L - R| + W when (L - R) >= 0
 *     - |L - R - W| = |-(L - R) + W| = |R - L| + W = |L - R| + W when (L - R) < 0
 * 
 * Edge Cases:
 * 
 * 1. All wildcards:
 *    moves = "____"
 *    L_count = 0, R_count = 0, underscore_count = 4
 *    |0 - 0| + 4 = 4
 *    Result: 4 (all wildcards in one direction)
 * 
 * 2. All L moves:
 *    moves = "LLLL"
 *    L_count = 4, R_count = 0, underscore_count = 0
 *    |4 - 0| + 0 = 4
 *    Result: 4
 * 
 * 3. All R moves:
 *    moves = "RRRR"
 *    L_count = 0, R_count = 4, underscore_count = 0
 *    |0 - 4| + 0 = 4
 *    Result: 4
 * 
 * 4. Equal L and R:
 *    moves = "LR_LR"
 *    L_count = 2, R_count = 2, underscore_count = 1
 *    |2 - 2| + 1 = 1
 *    Result: 1 (wildcard determines direction)
 * 
 * 5. Empty string:
 *    moves = ""
 *    L_count = 0, R_count = 0, underscore_count = 0
 *    |0 - 0| + 0 = 0
 *    Result: 0 (stay at origin)
 * 
 * 6. Single character:
 *    moves = "_"
 *    L_count = 0, R_count = 0, underscore_count = 1
 *    |0 - 0| + 1 = 1
 *    Result: 1
 * 
 * Why This Works:
 * 
 *   The key insight is that wildcards can be used optimally:
 *   - If we have more L than R, we should use all wildcards as L to maximize leftward distance
 *   - If we have more R than L, we should use all wildcards as R to maximize rightward distance
 *   - If L and R are equal, we can use all wildcards in any direction
 * 
 *   The formula |L - R| + W captures this:
 *   - |L - R|: the net displacement from fixed moves (always positive)
 *   - + W: all wildcards add to the distance in the dominant direction
 * 
 * Alternative Approach (Simulation):
 * 
 *   Could simulate all possible assignments of wildcards, but that would be O(2^W)
 *   which is exponential. The greedy approach is optimal and O(n).
 */

class Solution {
    /**
     * Calculates the furthest distance from origin achievable with the given moves.
     * 
     * @param moves String containing 'L', 'R', and '_' characters
     * @return The maximum distance from origin (position 0) that can be achieved
     * 
     * Time Complexity: O(n) where n is the length of moves
     * Space Complexity: O(1)
     */
    public int furthestDistanceFromOrigin(String moves) {
        int lCount = 0;
        int rCount = 0;
        int underscoreCount = 0;

        // Iterate through the moves to count each character type
        for (char move : moves.toCharArray()) {
            if (move == 'L') {
                lCount++;
            } else if (move == 'R') {
                rCount++;
            } else {
                // Character is '_' (wildcard)
                underscoreCount++;
            }
        }

        // The furthest distance is the absolute difference between L and R,
        // plus all wildcards added to the dominant direction.
        // 
        // Explanation:
        // - |lCount - rCount| gives the net displacement from fixed moves
        // - underscoreCount represents wildcards that can be used optimally
        // - All wildcards should be used in the direction that maximizes distance
        // - Therefore: max_distance = |L - R| + W
        return Math.abs(lCount - rCount) + underscoreCount;
    }
}

