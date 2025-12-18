/*
 * LeetCode 717: 1-bit and 2-bit Characters
 * 
 * Problem:
 * We have two special characters:
 * - The first character can be represented by one bit: 0
 * - The second character can be represented by two bits: 10 or 11
 * 
 * Given a binary array bits that ends with 0, return true if the last 
 * character must be a one-bit character.
 * 
 * Approach: Greedy Traversal
 * 
 * Key Insight:
 * - If we see a 1, it MUST be the start of a 2-bit character (10 or 11)
 * - If we see a 0, it's a 1-bit character
 * - The array always ends with 0, so we need to determine if that 
 *   final 0 is standalone or part of a 2-bit "10"
 * 
 * Algorithm:
 * 1. Start from the beginning of the array
 * 2. If current bit is 1, skip 2 positions (2-bit character)
 * 3. If current bit is 0, skip 1 position (1-bit character)
 * 4. Stop before the last element
 * 5. If we land exactly on the last index, it's a 1-bit character
 * 
 * Time Complexity: O(n) - single pass through the array
 * Space Complexity: O(1) - only using a pointer variable
 * 
 * Examples:
 * 
 * Example 1: bits = [1, 0, 0]
 *   i=0: bits[0]=1 → 2-bit char "10", i+=2 → i=2
 *   i=2: i == n-1 (2 == 2) ✓
 *   Last bit is standalone 0 → return true
 *   Decoding: [1,0] + [0] = "10" + "0"
 * 
 * Example 2: bits = [1, 1, 1, 0]
 *   i=0: bits[0]=1 → 2-bit char "11", i+=2 → i=2
 *   i=2: bits[2]=1 → 2-bit char "10", i+=2 → i=4
 *   i=4: i > n-1 (4 > 3) ✗
 *   Last 0 is part of "10" → return false
 *   Decoding: [1,1] + [1,0] = "11" + "10"
 * 
 * Visual:
 * 
 *   [1, 0, 0]           [1, 1, 1, 0]
 *    └──┘  └─ 1-bit      └──┘ └──┘
 *    2-bit    (true)     2-bit 2-bit (false)
 */

class Solution {

    public boolean isOneBitCharacter(int[] bits) {

        int i = 0;

        int n = bits.length;

        // Traverse until the second-to-last bit
        while (i < n - 1) {

            if (bits[i] == 1) {

                i += 2; // Two-bit character

            } else {

                i += 1; // One-bit character

            }

        }

        // If we stopped exactly at the last bit, it's a one-bit character
        return i == n - 1;

    }

}

