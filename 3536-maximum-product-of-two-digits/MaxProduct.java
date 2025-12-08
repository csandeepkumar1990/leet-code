/**
 * LeetCode 3536: Maximum Product of Two Digits
 * 
 * Problem: Given a positive integer n, return the maximum product of any two
 *          digits in n.
 * 
 * Key Insight: Extract all digits, sort them, and multiply the two largest!
 *              After sorting in ascending order, the two largest are at the end.
 * 
 * Examples:
 *   Input: n = 31
 *   Output: 3
 *   Explanation: Digits are [3, 1]. Max product = 3 × 1 = 3
 * 
 *   Input: n = 22
 *   Output: 4
 *   Explanation: Digits are [2, 2]. Max product = 2 × 2 = 4
 * 
 *   Input: n = 124
 *   Output: 8
 *   Explanation: Digits are [1, 2, 4]. Max product = 4 × 2 = 8
 * 
 *   Input: n = 7SEE29
 *   Output: 81
 *   Explanation: Digits are [7, 9, 2, 9]. Sorted: [2, 7, 9, 9]. Max = 9 × 9 = 81
 * 
 * Constraints:
 *   - 10 <= n <= 10^9 (at least 2 digits guaranteed)
 */

import java.util.Arrays;

class Solution {

    /**
     * Returns the maximum product of any two digits in n.
     * 
     * Approach: Insert digits into array → Sort → Get max 2 → Multiply
     * 
     * @param n - The input positive integer
     * @return Maximum product of two digits
     * 
     * Time Complexity: O(d log d) where d = number of digits (at most 10)
     * Space Complexity: O(d) for the digit array
     */
    public int maxProduct(int n) {
        /*
         * STEP 1: Convert number to string, then to character array
         * 
         * Example: n = 7929
         *   String.valueOf(7929) → "7929"
         *   "7929".toCharArray() → ['7', '9', '2', '9']
         */
        char[] digits = String.valueOf(n).toCharArray();

        /*
         * STEP 2: Sort the array in ascending order
         * 
         * Example: ['7', '9', '2', '9']
         *   After sort: ['2', '7', '9', '9']
         *   
         * Character comparison works because '0' < '1' < ... < '9'
         * in ASCII/Unicode values.
         */
        Arrays.sort(digits);

        /*
         * STEP 3: Get the two largest digits (last two elements)
         * 
         * Example: ['2', '7', '9', '9']
         *   digits[length-1] = '9' (largest)
         *   digits[length-2] = '9' (second largest)
         * 
         * Convert char to int by subtracting '0':
         *   '9' - '0' = 57 - 48 = 9
         */
        int length = digits.length;
        int max1 = digits[length - 1] - '0';  // Largest digit
        int max2 = digits[length - 2] - '0';  // Second largest digit

        /*
         * STEP 4: Return the product of the two largest
         * 
         * Example: 9 × 9 = 81
         */
        return max1 * max2;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.maxProduct(31));   // Output: 3
 * System.out.println(sol.maxProduct(22));   // Output: 4
 * System.out.println(sol.maxProduct(124));  // Output: 8
 * System.out.println(sol.maxProduct(7929)); // Output: 81
 * 
 * ═══════════════════════════════════════════════════════════════
 * STEP-BY-STEP VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 * Example: n = 7929
 * 
 * STEP 1: Extract digits into array
 * ┌─────────────────────────────────┐
 * │  7929 → "7929" → ['7','9','2','9']  │
 * └─────────────────────────────────┘
 *                    ↓
 *        ┌───┬───┬───┬───┐
 *        │ 7 │ 9 │ 2 │ 9 │  (unsorted)
 *        └───┴───┴───┴───┘
 * 
 * STEP 2: Sort the array
 *        ┌───┬───┬───┬───┐
 *        │ 2 │ 7 │ 9 │ 9 │  (sorted ascending)
 *        └───┴───┴───┴───┘
 *                    ↑   ↑
 *                  max2 max1
 * 
 * STEP 3: Get max 2 values
 *        max1 = 9 (last element)
 *        max2 = 9 (second-to-last element)
 * 
 * STEP 4: Calculate product
 *        result = 9 × 9 = 81
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY char - '0' CONVERTS TO INT
 * ═══════════════════════════════════════════════════════════════
 * 
 *   ASCII values:
 *   '0' = 48
 *   '1' = 49
 *   '2' = 50
 *   ...
 *   '9' = 57
 *   
 *   To get the actual digit value:
 *   '5' - '0' = 53 - 48 = 5 ✓
 *   '9' - '0' = 57 - 48 = 9 ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY SORTING WORKS
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Character comparison uses ASCII values:
 *   '0' < '1' < '2' < ... < '9'
 *   
 *   So Arrays.sort() on char[] correctly orders digits!
 *   
 *   After ascending sort:
 *   - First element = smallest digit
 *   - Last element = largest digit
 *   - Second-to-last = second largest digit
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Same digits:
 *    n = 22 → ['2', '2'] → sorted: ['2', '2'] → 2 × 2 = 4
 * 
 * 2. Contains zero:
 *    n = 103 → ['1', '0', '3'] → sorted: ['0', '1', '3'] → 3 × 1 = 3
 * 
 * 3. All same digits:
 *    n = 5555 → ['5','5','5','5'] → sorted: same → 5 × 5 = 25
 * 
 * 4. Minimum input (2 digits):
 *    n = 10 → ['1', '0'] → sorted: ['0', '1'] → 1 × 0 = 0
 * 
 * 5. Maximum product (99...):
 *    n = 99 → ['9', '9'] → sorted: same → 9 × 9 = 81 (max possible!)
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: USING int[] ARRAY
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int maxProduct(int n) {
 *       String s = String.valueOf(n);
 *       int[] digits = new int[s.length()];
 *       
 *       // Insert each digit into array
 *       for (int i = 0; i < s.length(); i++) {
 *           digits[i] = s.charAt(i) - '0';
 *       }
 *       
 *       // Sort the array
 *       Arrays.sort(digits);
 *       
 *       // Get max 2 values and return product
 *       int len = digits.length;
 *       return digits[len - 1] * digits[len - 2];
 *   }
 * 
 * ═══════════════════════════════════════════════════════════════
 * ALTERNATIVE: WITHOUT SORTING (Track Two Max)
 * ═══════════════════════════════════════════════════════════════
 * 
 *   public int maxProduct(int n) {
 *       int max1 = 0, max2 = 0;  // Two largest digits
 *       
 *       while (n > 0) {
 *           int digit = n % 10;  // Extract last digit
 *           n /= 10;             // Remove last digit
 *           
 *           if (digit >= max1) {
 *               max2 = max1;     // Demote old max
 *               max1 = digit;    // New max
 *           } else if (digit > max2) {
 *               max2 = digit;    // New second max
 *           }
 *       }
 *       return max1 * max2;
 *   }
 *   
 *   This is O(d) time without sorting overhead!
 */

