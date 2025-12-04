/**
 * LeetCode 1952: Three Divisors
 * 
 * Problem: Given an integer n, return true if n has exactly three positive divisors.
 *          Otherwise, return false.
 * 
 * Key Insight: A number has exactly 3 divisors if and only if it is the square
 *              of a prime number (e.g., 4=2², 9=3², 25=5², 49=7²).
 *              The three divisors are: 1, the prime, and the square itself.
 * 
 * Example: n = 4 → divisors are {1, 2, 4} → returns true
 *          n = 6 → divisors are {1, 2, 3, 6} → returns false
 */
class Solution {
    /**
     * Checks if the given number has exactly three positive divisors.
     * 
     * @param n The input number to check
     * @return true if n has exactly 3 divisors, false otherwise
     * 
     * Approach: Count divisors between 2 and n-1 (exclusive).
     *           Since 1 and n are always divisors, we only need to find
     *           exactly 1 additional divisor for the total to be 3.
     * 
     * Time Complexity: O(n) - we iterate through all numbers from 2 to n-1
     * Space Complexity: O(1) - only using a counter variable
     */
    public boolean isThree(int n) {
        // Counter to track divisors found (excluding 1 and n)
        int divisorCount = 0;
      
        // Check each potential divisor from 2 to n-1
        for (int i = 2; i < n; i++) {
            // If i divides n evenly (remainder is 0), it's a divisor
            if (n % i == 0) {
                divisorCount++;
            }
        }
      
        // A number has exactly 3 divisors when it has exactly 1 divisor 
        // between 2 and n-1 (since 1 and n are always divisors)
        // Total divisors = 1 (for '1') + divisorCount + 1 (for 'n') = 3
        return divisorCount == 1;
    }
}