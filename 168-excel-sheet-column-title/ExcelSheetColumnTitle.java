/*
 * LeetCode 168: Excel Sheet Column Title
 * 
 * Problem:
 * Given an integer columnNumber, return its corresponding Excel column title.
 * 
 * Excel columns: A, B, C, ... Z, AA, AB, ... AZ, BA, ... ZZ, AAA, ...
 * 1 → A, 2 → B, ... 26 → Z, 27 → AA, 28 → AB, ... 702 → ZZ, 703 → AAA
 * 
 * Approach: Modified Base-26 Conversion (1-indexed)
 * 
 * Key Insight:
 * This is like base-26, BUT:
 * - Normal base-26: digits are 0-25 (0, 1, 2, ..., 25)
 * - Excel columns: digits are 1-26 (A=1, B=2, ..., Z=26)
 * 
 * The Problem with 1-indexed:
 * - In normal base conversion, after Z (26), next is "10" (base 26)
 * - In Excel, after Z (26), next is "AA" (27)
 * - We need to "shift" by subtracting 1 before each modulo
 * 
 * Why Subtract 1?
 * - Normal: 26 % 26 = 0, but we want Z (position 25, which is 'A' + 25)
 * - By doing (26-1) % 26 = 25, we get 'Z' correctly
 * - This converts from 1-indexed (1-26) to 0-indexed (0-25)
 * 
 * Algorithm:
 * 1. While columnNumber > 0:
 *    - Subtract 1 (shift to 0-indexed)
 *    - Get remainder = columnNumber % 26
 *    - Convert to letter: 'A' + remainder
 *    - Divide columnNumber by 26
 * 2. Reverse the result (we build from right to left)
 * 
 * Time Complexity: O(log₂₆ n) - number of digits in base 26
 * Space Complexity: O(log₂₆ n) - for the StringBuilder
 * 
 * Example: columnNumber = 28
 * 
 * Iteration 1:
 *   columnNumber = 28 - 1 = 27
 *   remainder = 27 % 26 = 1
 *   letter = 'A' + 1 = 'B'
 *   sb = "B"
 *   columnNumber = 27 / 26 = 1
 * 
 * Iteration 2:
 *   columnNumber = 1 - 1 = 0
 *   remainder = 0 % 26 = 0
 *   letter = 'A' + 0 = 'A'
 *   sb = "BA"
 *   columnNumber = 0 / 26 = 0
 * 
 * Reverse: "AB"
 * 
 * Example: columnNumber = 701 → "ZY"
 * 
 * Column Number Reference:
 *   1  →  A       27 → AA      703 → AAA
 *   2  →  B       28 → AB
 *   ...           ...
 *   26 →  Z       52 → AZ
 *                 53 → BA
 *                 ...
 *                702 → ZZ
 * 
 * Visual (Why we subtract 1):
 * 
 *   Normal base-26:    Excel columns:
 *   0 → "0"            1 → "A"
 *   25 → "P"           26 → "Z"
 *   26 → "10"          27 → "AA"  (not "A0"!)
 *   
 *   The "columnNumber--" aligns Excel's 1-26 to 0-25
 *   so that modulo and division work correctly.
 */

class Solution {

    public String convertToTitle(int columnNumber) {

        StringBuilder sb = new StringBuilder();

        while (columnNumber > 0) {

            // CRUCIAL: Shift to 0-indexed before modulo
            // This converts 1-26 range to 0-25 range
            columnNumber--;

            // Get the current digit (0-25)
            int remainder = columnNumber % 26;

            // Convert to letter ('A' = 0, 'B' = 1, ..., 'Z' = 25)
            char letter = (char) ('A' + remainder);

            // Append to result (building right-to-left)
            sb.append(letter);

            // Move to next digit
            columnNumber /= 26;

        }

        // Reverse since we built the string backwards
        return sb.reverse().toString();

    }

}

