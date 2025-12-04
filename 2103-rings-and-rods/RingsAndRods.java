import java.util.*;

/**
 * LeetCode 2103: Rings and Rods
 * 
 * Problem: There are n rings and each ring is either red, green, or blue. The rings 
 *          are distributed across ten rods labeled from 0 to 9.
 *          
 *          You are given a string 'rings' where rings[i] and rings[i+1] describe the
 *          color and rod of the i-th ring:
 *          - rings[i] is 'R', 'G', or 'B' (color)
 *          - rings[i+1] is '0' to '9' (rod number)
 *          
 *          Return the number of rods that have all three colors of rings on them.
 * 
 * Key Insight: Use bit manipulation! Each color is a bit in a 3-bit mask:
 *              R = 001 (1), G = 010 (2), B = 100 (4)
 *              A rod with all colors has mask = 111 (7)
 * 
 * Examples:
 *   Input: rings = "B0B6G0R6R0R6G9"
 *   Output: 1
 *   Explanation: 
 *     - Rod 0: B, G, R → all 3 colors ✓
 *     - Rod 6: B, R, R → missing G ✗
 *     - Rod 9: G → missing R, B ✗
 *     Only rod 0 has all three colors.
 * 
 *   Input: rings = "B0R0G0R9R0B0G0"
 *   Output: 1
 *   Explanation: Rod 0 has all three colors, rod 9 only has red.
 * 
 *   Input: rings = "G4"
 *   Output: 0
 *   Explanation: Only one ring on rod 4, cannot have all three colors.
 */
class Solution {

    /**
     * Counts the number of rods that have all three colors (R, G, B).
     * 
     * @param rings String where each pair of characters represents color and rod
     * @return Number of rods with all three colors
     * 
     * Approach: Bit Manipulation with HashMap
     *   1. Use a bitmask to track colors on each rod:
     *      - R (Red)   = 001 = 1
     *      - G (Green) = 010 = 2
     *      - B (Blue)  = 100 = 4
     *   2. OR the color bits together for each rod
     *   3. If rod's mask == 7 (111 in binary), it has all colors
     * 
     * Time Complexity: O(n) - single pass through the string + pass through rods
     * Space Complexity: O(1) - at most 10 rods (0-9) in the map
     */
    public int countPoints(String rings) {
        // Map to store the color bitmask for each rod
        Map<Integer, Integer> rodColors = new HashMap<>();

        // Iterate through the rings string. Each ring is represented by two characters:
        // the color and the rod number. So we increment by 2 in each step.
        for (int i = 0; i < rings.length(); i += 2) {
            // First character is the color
            char colorChar = rings.charAt(i);
            // Second character is the rod number (0-9)
            int rod = Character.getNumericValue(rings.charAt(i + 1));

            // Determine the bitmask for the current color
            // Using powers of 2 so each color occupies a unique bit position
            int colorMask = 0;
            if (colorChar == 'R') {
                colorMask = 1; // 001 in binary - Red uses bit 0
            } else if (colorChar == 'G') {
                colorMask = 2; // 010 in binary - Green uses bit 1
            } else if (colorChar == 'B') {
                colorMask = 4; // 100 in binary - Blue uses bit 2
            }

            // Use bitwise OR to add the current color to the rod's existing colors
            // getOrDefault returns 0 if rod not yet in map
            // Example: rod has R(001), adding G(010) → 001 | 010 = 011 (3)
            rodColors.put(rod, rodColors.getOrDefault(rod, 0) | colorMask);
        }

        // Count rods that have all three colors
        int count = 0;
        for (int mask : rodColors.values()) {
            // Check if all three bits are set: R(1) + G(2) + B(4) = 7 (111 in binary)
            if (mask == 7) {
                count++;
            }
        }

        return count;
    }
}

