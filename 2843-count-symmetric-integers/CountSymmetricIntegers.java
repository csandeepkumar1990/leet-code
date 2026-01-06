/*
 * LeetCode 2843: Count Symmetric Integers
 * 
 * Problem:
 * You are given two positive integers low and high.
 * 
 * An integer x consisting of 2n digits is symmetric if the sum of the first n digits
 * of x is equal to the sum of the last n digits of x.
 * 
 * Numbers with an odd number of digits are never symmetric.
 * 
 * Return the number of symmetric integers in the range [low, high].
 * 
 * Approach: Iterate and Check Each Number
 * 
 * Key Insight:
 * - A symmetric integer must have an even number of digits
 * - Split the digits into two halves
 * - Sum of first half digits must equal sum of second half digits
 * - Iterate through the range and check each number
 * 
 * Algorithm:
 * 1. Iterate through each number from low to high (inclusive)
 * 2. For each number:
 *    a. Convert to string to get digits
 *    b. Check if length is even (odd length numbers cannot be symmetric)
 *    c. Calculate sum of first half digits
 *    d. Calculate sum of second half digits
 *    e. If sums are equal, increment count
 * 3. Return count
 * 
 * Time Complexity: O((high - low) * d)
 *   - Iterate through (high - low + 1) numbers
 *   - For each number, process d digits (where d is the number of digits)
 *   - In worst case, d can be up to the number of digits in high
 * 
 * Space Complexity: O(d)
 *   - String representation of the number (d digits)
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: low = 1, high = 100
 * 
 *   Check each number:
 *     1: length=1 (odd) → skip
 *     2: length=1 (odd) → skip
 *     ...
 *     10: length=2 (even)
 *         First half: '1' → sum = 1
 *         Second half: '0' → sum = 0
 *         1 != 0 → not symmetric
 *     
 *     11: length=2 (even)
 *         First half: '1' → sum = 1
 *         Second half: '1' → sum = 1
 *         1 == 1 → symmetric ✓
 *     
 *     12: length=2 (even)
 *         First half: '1' → sum = 1
 *         Second half: '2' → sum = 2
 *         1 != 2 → not symmetric
 *     
 *     ...
 *     22: length=2 (even)
 *         First half: '2' → sum = 2
 *         Second half: '2' → sum = 2
 *         2 == 2 → symmetric ✓
 *     
 *     ...
 *     100: length=3 (odd) → skip
 * 
 *   Result: Count of symmetric numbers (11, 22, 33, 44, 55, 66, 77, 88, 99)
 * 
 * Another Example:
 * 
 *   Input: low = 1200, high = 1230
 * 
 *   Check numbers:
 *     1200: length=4 (even)
 *           First half: '1', '2' → sum = 3
 *           Second half: '0', '0' → sum = 0
 *           3 != 0 → not symmetric
 *     
 *     1201: length=4 (even)
 *           First half: '1', '2' → sum = 3
 *           Second half: '0', '1' → sum = 1
 *           3 != 1 → not symmetric
 *     
 *     1202: length=4 (even)
 *           First half: '1', '2' → sum = 3
 *           Second half: '0', '2' → sum = 2
 *           3 != 2 → not symmetric
 *     
 *     1203: length=4 (even)
 *           First half: '1', '2' → sum = 3
 *           Second half: '0', '3' → sum = 3
 *           3 == 3 → symmetric ✓
 *     
 *     ...
 *     1221: length=4 (even)
 *           First half: '1', '2' → sum = 3
 *           Second half: '2', '1' → sum = 3
 *           3 == 3 → symmetric ✓
 * 
 * Visual Representation:
 * 
 *   Number: 1221
 *   Digits:  1  2 | 2  1
 *            └─┬─┘ └─┬─┘
 *         First half  Second half
 *            Sum: 3    Sum: 3
 *            └─────────┘
 *             3 == 3 → Symmetric ✓
 * 
 *   Number: 1234
 *   Digits:  1  2 | 3  4
 *            └─┬─┘ └─┬─┘
 *         First half  Second half
 *            Sum: 3    Sum: 7
 *            └─────────┘
 *             3 != 7 → Not symmetric ✗
 * 
 * Edge Cases:
 * 
 * 1. Single digit numbers:
 *    low = 1, high = 9
 *    All have odd length → none are symmetric
 *    Result: 0
 * 
 * 2. Two-digit symmetric numbers:
 *    low = 10, high = 99
 *    Examples: 11, 22, 33, 44, 55, 66, 77, 88, 99
 *    All have first digit == second digit
 *    Result: 9
 * 
 * 3. Four-digit symmetric numbers:
 *    low = 1000, high = 9999
 *    Examples: 1001 (1+0 == 0+1), 1010 (1+0 == 1+0), 1221 (1+2 == 2+1)
 *    Many possibilities
 * 
 * 4. Range with no symmetric numbers:
 *    low = 100, high = 109
 *    All are 3-digit (odd length) → none are symmetric
 *    Result: 0
 * 
 * 5. Same number (low == high):
 *    low = 11, high = 11
 *    11 is symmetric → Result: 1
 * 
 * 6. Large range:
 *    low = 1, high = 1000000
 *    Need to check all numbers in range
 * 
 * Why Check Even Length?
 * 
 *   - A symmetric integer must be split into two equal halves
 *   - If length is odd, we cannot split evenly
 *   - Example: 123 (length 3) cannot be split into two equal halves
 *   - Therefore, only even-length numbers can be symmetric
 * 
 * Alternative Approach (Mathematical):
 * 
 *   Instead of converting to string, we could extract digits mathematically:
 *   ```java
 *   private boolean isSymmetric(int num) {
 *       int digits = 0;
 *       int temp = num;
 *       while (temp > 0) {
 *           digits++;
 *           temp /= 10;
 *       }
 *       if (digits % 2 != 0) return false;
 *       
 *       int half = digits / 2;
 *       int divisor = (int) Math.pow(10, half);
 *       int firstHalf = num / divisor;
 *       int secondHalf = num % divisor;
 *       
 *       return sumDigits(firstHalf) == sumDigits(secondHalf);
 *   }
 *   ```
 *   But string approach is simpler and more readable.
 */

class Solution {
    /**
     * Counts the number of symmetric integers in the range [low, high].
     * 
     * @param low The lower bound of the range (inclusive)
     * @param high The upper bound of the range (inclusive)
     * @return The count of symmetric integers in the range
     * 
     * Time Complexity: O((high - low) * d) where d is the average number of digits
     * Space Complexity: O(d) for string representation
     */
    public int countSymmetricIntegers(int low, int high) {
        int count = 0;
        
        // Iterate through each number in the range
        for (int i = low; i <= high; i++) {
            if (isSymmetric(i)) {
                count++;
            }
        }
        
        return count;
    }

    /**
     * Checks if a number is symmetric.
     * A number is symmetric if it has an even number of digits and
     * the sum of the first half equals the sum of the second half.
     * 
     * @param num The number to check
     * @return true if the number is symmetric, false otherwise
     */
    private boolean isSymmetric(int num) {
        // Convert number to string to access individual digits
        String str = String.valueOf(num);
        int n = str.length();
        
        // Numbers with odd number of digits cannot be symmetric
        if (n % 2 != 0) {
            return false;
        }
        
        // Calculate sum of first half and second half
        int leftSum = 0;  // Sum of first half digits
        int rightSum = 0; // Sum of second half digits
        
        // Use two pointers: one from start, one from end
        // Move towards center, summing digits from each half
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            leftSum += str.charAt(i) - '0';   // Convert char to int and add to left sum
            rightSum += str.charAt(j) - '0';  // Convert char to int and add to right sum
        }
        
        // Symmetric if sums of both halves are equal
        return leftSum == rightSum;
    }
}

