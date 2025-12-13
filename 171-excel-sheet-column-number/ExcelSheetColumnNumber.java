/*
 * LeetCode 171: Excel Sheet Column Number
 * 
 * Problem:
 * Given a string columnTitle that represents the column title in Excel,
 * return its corresponding column number.
 * 
 * A → 1, B → 2, ... Z → 26, AA → 27, AB → 28, ... ZZ → 702
 * 
 * Approach: Base-26 to Decimal Conversion (1-indexed)
 * 
 * Key Insight:
 * This is like converting from base-26 to decimal, BUT:
 * - Normal base-26: A=0, B=1, ..., Z=25
 * - Excel columns: A=1, B=2, ..., Z=26
 * 
 * So we add 1 to each digit value: (char - 'A' + 1)
 * 
 * Base Conversion Formula:
 * For a number like "ABC" in base-26:
 *   result = A×26² + B×26¹ + C×26⁰
 * 
 * Iterative form (Horner's method):
 *   result = ((A × 26) + B) × 26 + C
 * 
 * Algorithm:
 * 1. Start with result = 0
 * 2. For each character from left to right:
 *    - Get digit value: (char - 'A' + 1)
 *    - Update result: result = result × 26 + value
 * 3. Return result
 * 
 * Time Complexity: O(n) where n = length of columnTitle
 * Space Complexity: O(1)
 * 
 * Example: "AB" → 28
 * 
 * Step 1: char = 'A'
 *   value = 'A' - 'A' + 1 = 1
 *   result = 0 × 26 + 1 = 1
 * 
 * Step 2: char = 'B'
 *   value = 'B' - 'A' + 1 = 2
 *   result = 1 × 26 + 2 = 28
 * 
 * Return: 28 ✓
 * 
 * Example: "ZY" → 701
 * 
 * Step 1: char = 'Z'
 *   value = 'Z' - 'A' + 1 = 26
 *   result = 0 × 26 + 26 = 26
 * 
 * Step 2: char = 'Y'
 *   value = 'Y' - 'A' + 1 = 25
 *   result = 26 × 26 + 25 = 676 + 25 = 701
 * 
 * Return: 701 ✓
 * 
 * Visual (Positional Value):
 * 
 *   "ABC"
 *    │││
 *    ││└─ C = 3  × 26⁰ = 3  × 1   = 3
 *    │└── B = 2  × 26¹ = 2  × 26  = 52
 *    └─── A = 1  × 26² = 1  × 676 = 676
 *                                   ────
 *                            Total: 731
 * 
 * Horner's Method (more efficient):
 * 
 *   "ABC"
 *    
 *   result = 0
 *   result = 0 × 26 + 1 = 1        (after 'A')
 *   result = 1 × 26 + 2 = 28       (after 'B')
 *   result = 28 × 26 + 3 = 731     (after 'C')
 * 
 * Letter Values:
 *   A=1, B=2, C=3, ... Y=25, Z=26
 *   
 *   Formula: value = char - 'A' + 1
 *            'C' - 'A' + 1 = 2 + 1 = 3 ✓
 */

class Solution {

    public int titleToNumber(String columnTitle) {

        int result = 0;

        // Process each character from left to right
        for (int i = 0; i < columnTitle.length(); i++) {

            // Get the 1-indexed value of current letter
            // 'A' → 1, 'B' → 2, ..., 'Z' → 26
            int value = columnTitle.charAt(i) - 'A' + 1;

            // Shift existing digits left (×26) and add current digit
            // This is Horner's method for base conversion
            result = result * 26 + value;

        }

        return result;

    }

}

