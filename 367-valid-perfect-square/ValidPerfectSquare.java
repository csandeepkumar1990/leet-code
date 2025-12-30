/*
 * LeetCode 367: Valid Perfect Square
 * 
 * Problem:
 * Given a positive integer num, return true if num is a perfect square or false otherwise.
 * A perfect square is an integer that is the square of an integer. In other words, it is
 * the product of some integer with itself.
 * 
 * You must not use any built-in library function, such as sqrt.
 * 
 * Approach: Binary Search
 * 
 * Key Insight:
 * - Use binary search to find if there exists an integer whose square equals num
 * - Search space: [1, num/2 + 1] (since sqrt(num) <= num/2 for num > 1)
 * - For num = 0 or 1, return true directly (0² = 0, 1² = 1)
 * - Use long to prevent integer overflow when calculating mid * mid
 * 
 * Algorithm:
 * 1. Handle edge cases: num == 0 or num == 1 → return true
 * 2. Initialize left = 1, right = num / 2 + 1
 * 3. While left <= right:
 *    a. Calculate mid = left + (right - left) / 2 (prevents overflow)
 *    b. Calculate square = mid * mid
 *    c. If square == num: return true (found perfect square)
 *    d. If square < num: search right half (left = mid + 1)
 *    e. If square > num: search left half (right = mid - 1)
 * 4. If loop ends without finding: return false
 * 
 * Why right = num / 2 + 1?
 * - For num > 1, sqrt(num) <= num / 2
 * - Example: num = 16, sqrt(16) = 4, and 4 <= 16/2 = 8
 * - Adding 1 handles edge case where num = 2 (sqrt(2) ≈ 1.41, but we need to check 2)
 * - For num = 1, we handle it separately, so this is safe
 * 
 * Why use long instead of int?
 * - When mid is large, mid * mid might exceed Integer.MAX_VALUE
 * - Example: mid = 46341, mid * mid = 2147488281 > 2147483647 (Integer.MAX_VALUE)
 * - Using long prevents overflow and ensures correct comparison
 * 
 * Why mid = left + (right - left) / 2?
 * - Prevents integer overflow compared to (left + right) / 2
 * - Example: left = 2^30, right = 2^30
 *   - (left + right) / 2 might overflow
 *   - left + (right - left) / 2 is safe
 * - Both formulas are mathematically equivalent
 * 
 * Time Complexity: O(log n), where n is the value of num.
 *   Binary search reduces search space by half each iteration.
 * 
 * Space Complexity: O(1), only using a constant amount of extra space.
 * 
 * Example:
 * 
 *   Input: num = 16
 * 
 *   Initial: left = 1, right = 16/2 + 1 = 9
 * 
 *   Iteration 1:
 *     mid = 1 + (9 - 1) / 2 = 5
 *     square = 5 * 5 = 25
 *     25 > 16 → right = 5 - 1 = 4
 * 
 *   Iteration 2:
 *     mid = 1 + (4 - 1) / 2 = 2
 *     square = 2 * 2 = 4
 *     4 < 16 → left = 2 + 1 = 3
 * 
 *   Iteration 3:
 *     mid = 3 + (4 - 3) / 2 = 3
 *     square = 3 * 3 = 9
 *     9 < 16 → left = 3 + 1 = 4
 * 
 *   Iteration 4:
 *     mid = 4 + (4 - 4) / 2 = 4
 *     square = 4 * 4 = 16
 *     16 == 16 → return true
 * 
 *   Output: true
 * 
 * Another Example:
 * 
 *   Input: num = 14
 * 
 *   Initial: left = 1, right = 14/2 + 1 = 8
 * 
 *   Iteration 1:
 *     mid = 1 + (8 - 1) / 2 = 4
 *     square = 4 * 4 = 16
 *     16 > 14 → right = 4 - 1 = 3
 * 
 *   Iteration 2:
 *     mid = 1 + (3 - 1) / 2 = 2
 *     square = 2 * 2 = 4
 *     4 < 14 → left = 2 + 1 = 3
 * 
 *   Iteration 3:
 *     mid = 3 + (3 - 3) / 2 = 3
 *     square = 3 * 3 = 9
 *     9 < 14 → left = 3 + 1 = 4
 * 
 *   Loop ends (left = 4 > right = 3) → return false
 * 
 *   Output: false
 * 
 * Edge Cases:
 * - num = 0: 0² = 0 → return true
 * - num = 1: 1² = 1 → return true
 * - num = 2: Not a perfect square → return false
 * - num = Integer.MAX_VALUE: Large number, binary search handles efficiently
 * 
 * Alternative Approaches:
 * 1. Linear Search: O(√n) - check each integer from 1 to √n
 * 2. Math Trick: Use properties of perfect squares (sum of odd numbers)
 * 3. Newton's Method: Faster convergence but more complex
 * 
 * Binary Search Advantages:
 * - O(log n) time complexity (faster than linear)
 * - Simple to implement and understand
 * - Handles large numbers efficiently
 */

class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 0 || num == 1) {
            return true;
        }
        
        long left = 1;
        long right = num / 2 + 1;
        
        // Perform binary search
        while (left <= right) {
            long mid = left + (right - left) / 2; // Calculate mid to prevent potential overflow (left + right might exceed int max)
            long square = mid * mid;
            
            if (square == num) {
                return true; // Found an integer whose square is num
            } else if (square < num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        
        return false;
    }
}

