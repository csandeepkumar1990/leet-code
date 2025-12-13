/*
 * LeetCode 800: Similar RGB Color
 * 
 * Problem:
 * Given an RGB color in the format "#AABBCC", find the closest color
 * that can be expressed in shorthand format "#XYZ" (which expands to #XXYYZZ).
 * 
 * The similarity is measured by the square of the difference for each component.
 * We want to minimize: (R1-R2)² + (G1-G2)² + (B1-B2)²
 * 
 * Approach: Round Each Component to Nearest Shorthand Value
 * 
 * Key Insight:
 * - Shorthand colors have repeated hex digits: #RGB → #RRGGBB
 * - Examples: #123 → #112233, #FFF → #FFFFFF
 * - Repeated hex digits in decimal are: 0x00, 0x11, 0x22, ..., 0xFF
 * - These are all MULTIPLES OF 17!
 *   - 0x00 = 0,  0x11 = 17,  0x22 = 34, ..., 0xFF = 255
 * 
 * Why Multiples of 17?
 * - 0x11 = 1*16 + 1 = 17
 * - 0x22 = 2*16 + 2 = 34 = 2*17
 * - 0xAB where A=B: A*16 + A = A*17
 * 
 * Algorithm:
 * 1. For each color component (R, G, B):
 *    - Parse the 2-digit hex value
 *    - Find the nearest multiple of 17
 *    - Round: if remainder > 8, round up; else round down
 * 2. Combine the three rounded values
 * 
 * Rounding Logic:
 * - value / 17 = quotient (which multiple of 17 we're near)
 * - value % 17 = remainder (how far from that multiple)
 * - if remainder > 8 (more than halfway to next), round up
 * 
 * Time Complexity: O(1) - fixed 3 components
 * Space Complexity: O(1) - constant space
 * 
 * Example: color = "#09f166"
 * 
 * Component 1: "09" = 9
 *   - 9 / 17 = 0, 9 % 17 = 9
 *   - 9 > 8, so round up: q = 1
 *   - shorthand = 1 * 17 = 17 = 0x11
 * 
 * Component 2: "f1" = 241
 *   - 241 / 17 = 14, 241 % 17 = 3
 *   - 3 ≤ 8, so keep: q = 14
 *   - shorthand = 14 * 17 = 238 = 0xEE
 * 
 * Component 3: "66" = 102
 *   - 102 / 17 = 6, 102 % 17 = 0
 *   - 0 ≤ 8, exact match!
 *   - shorthand = 6 * 17 = 102 = 0x66
 * 
 * Result: "#11ee66"
 * 
 * Shorthand Values Table:
 * Hex:  00  11  22  33  44  55  66  77  88  99  AA  BB  CC  DD  EE  FF
 * Dec:   0  17  34  51  68  85 102 119 136 153 170 187 204 221 238 255
 * Mult: 0×17 1×17 2×17 ... ... ... ... ... ... ... ... ... ... ... 15×17
 */

class Solution {

    public String similarRGB(String color) {

        StringBuilder result = new StringBuilder("#");

        // Process each of the 3 color components (R, G, B)
        // They start at positions 1, 3, 5 (after the '#')
        for (int i = 1; i < color.length(); i += 2) {

            // Extract the 2-character hex component
            String part = color.substring(i, i + 2);

            // Convert hex string to decimal integer
            int value = Integer.parseInt(part, 16);

            // Find nearest multiple of 17 (shorthand values)
            // Shorthand hex like "AA" = 10*16 + 10 = 10*17 = 170
            int q = value / 17;  // which multiple of 17

            int r = value % 17;  // distance from that multiple

            // Round to nearest: if remainder > 8 (halfway point), round up
            if (r > 8)

                q++;

            // Calculate the actual shorthand value
            int shorthandValue = q * 17;

            // Append as 2-digit hex (with leading zero if needed)
            result.append(String.format("%02x", shorthandValue));

        }

        return result.toString();

    }

}

