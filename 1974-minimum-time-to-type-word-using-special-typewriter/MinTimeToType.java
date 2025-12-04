/**
 * LeetCode 1974: Minimum Time to Type Word Using Special Typewriter
 * 
 * Problem: There is a special typewriter with lowercase English letters 'a' to 'z' 
 *          arranged in a circle. A pointer initially points to 'a'.
 *          
 *          Each second, you can:
 *          - Move the pointer one character clockwise or counterclockwise
 *          - Type the character the pointer is currently on
 *          
 *          Given a string 'word', return the minimum number of seconds to type it.
 * 
 * Key Insight: The letters form a circle! To move between two letters, we can go:
 *              - Clockwise (direct distance)
 *              - Counterclockwise (26 - direct distance)
 *              Choose the shorter path.
 * 
 *        Visual of the circular keyboard:
 *              a - b - c - d - e
 *            /                   \
 *          z                       f
 *          |                       |
 *          y                       g
 *          |                       |
 *          x                       h
 *          |                       |
 *          ...       ...         ...
 * 
 * Examples:
 *   Input: word = "abc"
 *   Output: 5
 *   Explanation: 
 *     - 'a': pointer at 'a', type 'a' (1 sec) → total = 1
 *     - 'b': move 'a'→'b' (1 sec) + type (1 sec) → total = 3
 *     - 'c': move 'b'→'c' (1 sec) + type (1 sec) → total = 5
 * 
 *   Input: word = "bza"
 *   Output: 7
 *   Explanation:
 *     - 'b': move 'a'→'b' (1 sec) + type (1 sec) → total = 2
 *     - 'z': move 'b'→'z' counterclockwise (2 sec) + type (1 sec) → total = 5
 *           (clockwise would be 24 steps, counterclockwise is faster!)
 *     - 'a': move 'z'→'a' (1 sec) + type (1 sec) → total = 7
 * 
 *   Input: word = "zjpc"
 *   Output: 34
 */
class Solution {

    /**
     * Calculates the minimum time to type the given word on a circular typewriter.
     * 
     * @param word The word to type
     * @return Minimum number of seconds to type the word
     * 
     * Approach:
     *   1. Start at 'a', for each character in word:
     *   2. Calculate direct distance: |current - target|
     *   3. Calculate circular distance: 26 - direct (going the other way)
     *   4. Take minimum of both distances (shortest path)
     *   5. Add 1 second for typing each character
     * 
     * Time Complexity: O(n) - single pass through the word
     * Space Complexity: O(1) - only using a few variables
     */
    public int minTimeToType(String word) {
        // Initialize total time with the length of word (1 second per character to type)
        // This accounts for all the typing actions upfront
        int totalTime = word.length();
      
        // Start from character 'a' as the initial position (pointer starts at 'a')
        char currentPosition = 'a';
      
        // Iterate through each character in the word
        for (char targetChar : word.toCharArray()) {
            // Calculate the direct distance between current position and target character
            // Using ASCII values: 'b' - 'a' = 1, 'c' - 'a' = 2, etc.
            int directDistance = Math.abs(currentPosition - targetChar);
          
            // Calculate the minimum distance considering circular movement
            // On a circle of 26 letters, if direct is X steps, going the other way is (26 - X) steps
            // Example: 'a' to 'z' → direct = 25, circular = 26 - 25 = 1 (counterclockwise is faster!)
            int minimumDistance = Math.min(directDistance, 26 - directDistance);
          
            // Add the movement time to total time
            totalTime += minimumDistance;
          
            // Update current position to the target character for next iteration
            currentPosition = targetChar;
        }
      
        return totalTime;
    }
}

