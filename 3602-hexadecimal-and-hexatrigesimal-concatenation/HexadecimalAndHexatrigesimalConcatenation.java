/*
 * LeetCode 3602: Hexadecimal and Hexatrigesimal Concatenation
 * 
 * Problem:
 * Given an integer n, return the concatenation of:
 * - n² converted to hexadecimal (base 16)
 * - n³ converted to hexatrigesimal (base 36)
 * Both should be uppercase.
 * 
 * Approach: Direct Base Conversion Using Built-in Methods
 * 
 * Key Concepts:
 * 
 * 1. Hexadecimal (Base 16):
 *    - Digits: 0-9, A-F
 *    - Each digit represents 4 bits
 *    - Example: 255 → FF
 * 
 * 2. Hexatrigesimal (Base 36):
 *    - Digits: 0-9, A-Z (uses all alphanumeric characters)
 *    - Largest single-character base using standard alphanumerics
 *    - Example: 35 → Z, 36 → 10
 * 
 * Why use long?
 * - n can be up to 10^5 or more
 * - n² can overflow int (max ~2.1 billion)
 * - n³ definitely needs long to avoid overflow
 * 
 * Algorithm:
 * 1. Calculate n² and n³ using long to prevent overflow
 * 2. Convert n² to hexadecimal using Long.toHexString()
 * 3. Convert n³ to base 36 using Long.toString(value, 36)
 * 4. Convert both to uppercase and concatenate
 * 
 * Time Complexity: O(log n) - for base conversions
 * Space Complexity: O(log n) - for storing result strings
 * 
 * Example: n = 2
 * - n² = 4 → hex = "4"
 * - n³ = 8 → base36 = "8"
 * - Result: "48"
 * 
 * Example: n = 10
 * - n² = 100 → hex = "64" (6×16 + 4 = 100)
 * - n³ = 1000 → base36 = "RS" (27×36 + 28 = 1000)
 * - Result: "64RS"
 * 
 * Base Conversion Reference:
 * 
 * Hexadecimal (16):     Hexatrigesimal (36):
 * 0-9  → 0-9            0-9  → 0-9
 * 10   → A              10   → A
 * 11   → B              11   → B
 * ...                   ...
 * 15   → F              25   → P
 *                       26   → Q
 *                       ...
 *                       35   → Z
 */

class Solution {

    public String concatHex36(int n) {

        // Use long to prevent overflow for large n
        // n² and n³ can exceed int range
        long square = (long) n * n;

        long cube = (long) n * n * n;

        // Convert n² to hexadecimal (base 16)
        // Long.toHexString() returns lowercase, so we uppercase it
        String hex = Long.toHexString(square).toUpperCase();

        // Convert n³ to base 36 (hexatrigesimal)
        // Long.toString(value, radix) converts to any base 2-36
        String base36 = Long.toString(cube, 36).toUpperCase();

        // Concatenate: hexadecimal part + base36 part
        return hex + base36;

    }

}

