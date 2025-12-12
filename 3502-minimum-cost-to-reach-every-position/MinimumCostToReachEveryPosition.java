/*
 * LeetCode 3502: Minimum Cost to Reach Every Position
 * 
 * Problem:
 * You are given an integer array cost of size n. You start at position 0.
 * For each position i (0 <= i < n), you need to find the minimum cost to reach
 * position i, where you can move from any position j to position i (j <= i)
 * by paying cost[j].
 * 
 * Approach: Running/Prefix Minimum
 * 
 * Key Insight:
 * - To reach position i, we can start from any position j where j <= i
 * - The cost to reach position i is the minimum cost among all positions from 0 to i
 * - This is essentially finding the prefix minimum at each index
 * 
 * Algorithm:
 * 1. Keep track of the minimum cost seen so far (running minimum)
 * 2. At each position i, update the running minimum with cost[i]
 * 3. The answer for position i is the running minimum up to that point
 * 
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(n) - for the answer array (O(1) extra space)
 * 
 * Example:
 * Input:  cost = [5, 3, 4, 1, 3]
 * Output: [5, 3, 3, 1, 1]
 * 
 * Walkthrough:
 * - i=0: minCost = min(MAX, 5) = 5  → answer[0] = 5
 * - i=1: minCost = min(5, 3) = 3   → answer[1] = 3
 * - i=2: minCost = min(3, 4) = 3   → answer[2] = 3
 * - i=3: minCost = min(3, 1) = 1   → answer[3] = 1
 * - i=4: minCost = min(1, 3) = 1   → answer[4] = 1
 * 
 * Visual:
 * cost:   [5, 3, 4, 1, 3]
 *          ↓  ↓  ↓  ↓  ↓
 * answer: [5, 3, 3, 1, 1]  (prefix minimum at each position)
 */

class Solution {

    public int[] minCosts(int[] cost) {

        int n = cost.length;

        // Array to store the minimum cost to reach each position
        int[] answer = new int[n];

        // Track the minimum cost seen so far (running minimum)
        // Initialize to MAX_VALUE so any cost will be smaller
        int minCost = Integer.MAX_VALUE;

        // Single pass: compute prefix minimum at each position
        for (int i = 0; i < n; i++) {

            // Update running minimum with current position's cost
            minCost = Math.min(minCost, cost[i]);

            // The minimum cost to reach position i is the smallest cost
            // among all positions from 0 to i (the prefix minimum)
            answer[i] = minCost;

        }

        return answer;

    }

}

