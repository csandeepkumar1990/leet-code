// 3360. Stone Removal Game
// Alice and Bob play a game with n stones. Alice goes first.
// Alice removes 10 stones, then each player removes 1 less than previous turn.
// The player who cannot make a valid move loses.
//
// Examples:
// Input: n = 12 -> Output: true
//   Alice removes 10 (n=2), Bob must remove 9 but only 2 left -> Bob loses
//
// Input: n = 1 -> Output: false
//   Alice must remove 10 but only 1 stone -> Alice loses
//
// Key Insight: Simulate the game turn by turn. Track whose turn it is
// and check if current player can make the required move.
//
// Time Complexity: O(1) - at most 10 iterations (remove goes 10->1)
// Space Complexity: O(1) - only using constant extra space

class Solution {

    public boolean canAliceWin(int n) {

        int remove = 10;

        boolean aliceTurn = true;

        while (true) {

            if (n < remove) {

                // current player cannot move -> loses

                return !aliceTurn;

            }

            n -= remove;

            remove--;

            if (remove == 0) {

                // next player must remove 0 -> automatic loss

                return aliceTurn;

            }

            aliceTurn = !aliceTurn; // switch turns

        }

    }

}

