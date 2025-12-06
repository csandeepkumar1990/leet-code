/**
 * LeetCode 3216: Lexicographically Smallest String After a Swap
 * 
 * Problem: Given a string s containing only digits, return the lexicographically
 *          smallest string that can be obtained after swapping adjacent digits
 *          at most once. Two digits can only be swapped if they have the same
 *          parity (both even or both odd).
 * 
 * Key Insight: To get the smallest string, find the first pair of adjacent digits
 *              where swapping would make the string smaller (left > right) and
 *              they have the same parity. Perform only one swap.
 * 
 * Examples:
 *   Input: s = "45320"
 *   Output: "43520"
 *   Explanation: Swap '5' and '3' (both odd), string becomes "43520".
 * 
 *   Input: s = "001"
 *   Output: "001"
 *   Explanation: No swap needed, string is already smallest possible.
 * 
 * Constraints:
 *   - 2 <= s.length <= 100
 *   - s consists only of digits
 */

class Solution {

    /**
     * Returns the lexicographically smallest string after at most one adjacent swap.
     * 
     * @param s - The input string of digits
     * @return The smallest possible string after one valid swap
     * 
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(n) - character array to manipulate the string
     */
    public String getSmallestString(String s) {
        /*
         * Convert string to char array for easy manipulation
         * Strings are immutable in Java, so we need a mutable structure
         */
        char[] arr = s.toCharArray();
        int n = arr.length;

        /*
         * Iterate through adjacent pairs looking for a beneficial swap
         * We want to find the FIRST opportunity to make the string smaller
         */
        for (int i = 0; i < n - 1; i++) {
            // Convert char digits to their numeric values
            int a = arr[i] - '0';       // current digit
            int b = arr[i + 1] - '0';   // next digit

            /*
             * Check two conditions for a valid and beneficial swap:
             * 
             * 1. Same parity: (a % 2) == (b % 2)
             *    - Both even: 0,2,4,6,8 (remainder 0 when divided by 2)
             *    - Both odd:  1,3,5,7,9 (remainder 1 when divided by 2)
             * 
             * 2. Swapping makes it smaller: b < a
             *    - If the next digit is smaller, swapping will make
             *      the string lexicographically smaller
             */
            if ((a % 2) == (b % 2) && b < a) {
                // Perform the swap
                char temp = arr[i];
                arr[i] = arr[i + 1];
                arr[i + 1] = temp;
                
                // Break immediately - only one swap allowed
                // First beneficial swap gives the smallest result
                break;
            }
        }

        // Convert char array back to string
        return new String(arr);
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.getSmallestString("45320")); // Output: "43520"
 * System.out.println(sol.getSmallestString("001"));   // Output: "001"
 * System.out.println(sol.getSmallestString("21"));    // Output: "21" (different parity, no swap)
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: s = "45320"
 * ═══════════════════════════════════════════════════════════════
 * 
 * i=0: a=4 (even), b=5 (odd)  → Different parity, skip
 * i=1: a=5 (odd),  b=3 (odd)  → Same parity AND 3 < 5 → SWAP!
 * 
 * Before: "45320"
 * After:  "43520"
 * 
 * We stop here (only one swap allowed).
 * Result: "43520" ✓
 */

