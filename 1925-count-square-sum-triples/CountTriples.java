/**
 * LeetCode 1925: Count Square Sum Triples
 * 
 * Problem:
 * A square triple (a, b, c) is a triple where a, b, and c are integers
 * and a² + b² = c² (Pythagorean triple).
 * 
 * Given an integer n, return the number of square triples where 1 <= a, b, c <= n.
 * 
 * Note: (a, b, c) and (b, a, c) are counted as DIFFERENT triples!
 * 
 * Examples:
 *   n = 5  -> 2 triples: (3,4,5) and (4,3,5)
 *   n = 10 -> 4 triples: (3,4,5), (4,3,5), (6,8,10), (8,6,10)
 * 
 * Approach:
 * - Iterate through all possible pairs (a, b) where 1 <= a, b <= n
 * - Calculate c² = a² + b²
 * - Check if c² is a perfect square and c <= n
 * - If yes, we found a valid triple
 * 
 * Time Complexity: O(n²) - nested loops for all (a, b) pairs
 * Space Complexity: O(1) - only using a few variables
 */
class Solution {

    public int countTriples(int n) {
        int count = 0;
        
        // Try all possible values of a from 1 to n
        for (int a = 1; a <= n; a++) {
            // Try all possible values of b from 1 to n
            for (int b = 1; b <= n; b++) {
                // Calculate c² using the Pythagorean theorem: a² + b² = c²
                int c2 = a * a + b * b;
                
                // Get the integer square root of c²
                int c = (int) Math.sqrt(c2);
                
                // Two conditions must be met for a valid triple:
                // 1. c must be a perfect square root (c * c == c2)
                //    This ensures c is actually an integer, not a decimal
                //    e.g., if c2 = 25, sqrt(25) = 5, and 5*5 = 25 ✓
                //    e.g., if c2 = 26, sqrt(26) ≈ 5.1, int cast = 5, and 5*5 = 25 ≠ 26 ✗
                // 2. c must be within bounds (c <= n)
                if (c * c == c2 && c <= n) {
                    count++;
                }
            }
        }
        
        return count;
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: n = 5
        // Valid triples: (3,4,5), (4,3,5)
        System.out.println(sol.countTriples(5));   // Output: 2
        
        // Test 2: n = 10
        // Valid triples: (3,4,5), (4,3,5), (6,8,10), (8,6,10)
        System.out.println(sol.countTriples(10));  // Output: 4
    }
}

