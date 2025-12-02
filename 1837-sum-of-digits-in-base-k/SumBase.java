/**
 * LeetCode 1837: Sum of Digits in Base K
 * 
 * Problem:
 * Given an integer n (in base 10) and a base k, return the sum of the digits
 * of n after converting n from base 10 to base k.
 * 
 * Examples:
 *   n = 34, k = 6 -> Output: 9
 *     34 in base 6 = 54 (since 5*6 + 4 = 34)
 *     Sum: 5 + 4 = 9
 * 
 *   n = 10, k = 10 -> Output: 1
 *     10 in base 10 = 10 (already base 10)
 *     Sum: 1 + 0 = 1
 * 
 * Approach:
 * We don't actually need to build the full base-k representation!
 * We can extract and sum digits on the fly using modulo and division:
 *   - n % k gives the rightmost digit in base k
 *   - n / k removes the rightmost digit
 * 
 * How base conversion works (34 to base 6):
 *   34 % 6 = 4  (rightmost digit)  -> sum = 4
 *   34 / 6 = 5  (remaining number)
 *   5 % 6 = 5   (next digit)       -> sum = 4 + 5 = 9
 *   5 / 6 = 0   (done!)
 *   Result: digits are [5, 4], representing "54" in base 6
 * 
 * Time Complexity: O(log_k(n)) - number of digits in base k representation
 * Space Complexity: O(1) - only using a few variables
 */
class Solution {

    public int sumBase(int n, int k) {
        int sum = 0;
        
        // Extract digits from right to left until n becomes 0
        while (n > 0) {
            // n % k extracts the rightmost digit in base k
            // Example: 34 % 6 = 4 (the ones place in base 6)
            sum += n % k;
            
            // n / k removes the rightmost digit (integer division)
            // Example: 34 / 6 = 5 (shifts right in base 6)
            n /= k;
        }
        
        return sum;
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: 34 in base 6 = "54", sum = 5 + 4 = 9
        System.out.println(sol.sumBase(34, 6));   // Output: 9
        
        // Test 2: 10 in base 10 = "10", sum = 1 + 0 = 1
        System.out.println(sol.sumBase(10, 10));  // Output: 1
        
        // Test 3: 100 in base 2 = "1100100", sum = 1+1+0+0+1+0+0 = 3
        System.out.println(sol.sumBase(100, 2));  // Output: 3
    }
}

