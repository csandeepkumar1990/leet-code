/*
 * LeetCode 3222: Find the Winning Player in Coin Game
 * 
 * Problem:
 * Alice and Bob play a coin game. They have coins worth 75 and 10.
 * Each turn, a player must pick exactly:
 *   - 1 coin worth 75
 *   - 4 coins worth 10
 *   - Total: 75 + 40 = 115 per turn
 * 
 * Alice goes first. The player who cannot make a valid move loses.
 * Given x (number of 75-value coins) and y (number of 10-value coins),
 * return the winner's name.
 * 
 * Approach: Game Theory - Count Total Moves
 * 
 * Key Insight:
 * - Each valid move requires: 1 coin of 75 AND 4 coins of 10
 * - Maximum moves possible = min(x, y/4)
 * - Alice moves on turns 1, 3, 5... (odd turns)
 * - Bob moves on turns 2, 4, 6... (even turns)
 * - If total moves is ODD → Alice makes last move → Alice wins
 * - If total moves is EVEN → Bob makes last move → Bob wins
 * 
 * Algorithm:
 * 1. Calculate max moves = min(x, y/4)
 * 2. If maxMoves is odd → Alice wins
 * 3. If maxMoves is even → Bob wins
 * 
 * Time Complexity: O(1) - just arithmetic operations
 * Space Complexity: O(1) - no extra space used
 * 
 * Example 1: x = 2, y = 7
 * 
 *   Max moves = min(2, 7/4) = min(2, 1) = 1
 *   1 is odd → Alice wins
 * 
 *   Game play:
 *     Turn 1 (Alice): Takes 1×75 + 4×10 → Remaining: x=1, y=3
 *     Turn 2 (Bob): Cannot take (needs 4 tens, only 3 left) → Bob loses
 * 
 * Example 2: x = 4, y = 11
 * 
 *   Max moves = min(4, 11/4) = min(4, 2) = 2
 *   2 is even → Bob wins
 * 
 *   Game play:
 *     Turn 1 (Alice): Takes 1×75 + 4×10 → Remaining: x=3, y=7
 *     Turn 2 (Bob): Takes 1×75 + 4×10 → Remaining: x=2, y=3
 *     Turn 3 (Alice): Cannot take (needs 4 tens, only 3 left) → Alice loses
 * 
 * Visual:
 * 
 *   Each move consumes:
 *   ┌─────┐ ┌──┐┌──┐┌──┐┌──┐
 *   │ 75  │ │10││10││10││10│  = 115 total
 *   └─────┘ └──┘└──┘└──┘└──┘
 * 
 *   Limiting factor: whichever runs out first
 *   - If x < y/4 → limited by 75-value coins
 *   - If x > y/4 → limited by 10-value coins
 */

class Solution {

    public String winningPlayer(int x, int y) {

        // Calculate maximum number of complete moves possible
        // Each move needs: 1 coin of 75 + 4 coins of 10
        int maxMoves = Math.min(x, y / 4);

        // Alice goes first (moves 1, 3, 5...)
        // Bob goes second (moves 2, 4, 6...)
        // If maxMoves is even, last move was Bob's → next player (Alice) loses → Bob wins
        // If maxMoves is odd, last move was Alice's → next player (Bob) loses → Alice wins
        if (maxMoves % 2 == 0) {
            return "Bob";
        } else {
            return "Alice";
        }

    }

}

