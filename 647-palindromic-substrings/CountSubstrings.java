/*
 * LeetCode 647: Palindromic Substrings
 * 
 * Problem:
 * Given a string s, return the number of palindromic substrings in it.
 * A string is a palindrome when it reads the same backward as forward.
 * A substring is a contiguous sequence of characters within the string.
 * 
 * Example:
 *   Input: s = "abc"
 *   Output: 3
 *   Explanation: Three palindromic strings: "a", "b", "c".
 * 
 *   Input: s = "aaa"
 *   Output: 6
 *   Explanation: Six palindromic strings: "a", "a", "a", "aa", "aa", "aaa".
 * 
 * Approach: Expand Around Centers
 * 
 * Key Insight:
 * - Every palindrome has a center (or two centers for even-length palindromes)
 * - For each possible center, expand outward while characters match
 * - Two types of centers:
 *   1. Single character (odd-length palindromes): center at index i
 *   2. Gap between characters (even-length palindromes): center between i and i+1
 * 
 * Algorithm:
 * 1. Iterate through each character in the string
 * 2. For each position i, consider it as a potential center:
 *    a. Expand around single character (odd-length palindromes)
 *    b. Expand around gap between i and i+1 (even-length palindromes)
 * 3. For each expansion, count palindromes found while characters match
 * 4. Return total count
 * 
 * Time Complexity: O(n²)
 *   - Outer loop: O(n) iterations
 *   - expand() function: O(n) in worst case (when entire string is palindrome)
 *   - Total: O(n²) where n is the length of the string
 * 
 * Space Complexity: O(1)
 *   - Only using a few variables
 *   - No additional data structures
 * 
 * Example Walkthrough:
 * 
 *   Input: s = "abc"
 *   
 *   i = 0:
 *     expand(s, 0, 0): left=0, right=0, 'a' == 'a' ✓ → count=1
 *                      left=-1, right=1 → invalid, stop
 *     expand(s, 0, 1): left=0, right=1, 'a' == 'b' ✗ → count=0
 *     Total: 1
 *   
 *   i = 1:
 *     expand(s, 1, 1): left=1, right=1, 'b' == 'b' ✓ → count=1
 *                      left=0, right=2, 'a' == 'c' ✗ → stop
 *     expand(s, 1, 2): left=1, right=2, 'b' == 'c' ✗ → count=0
 *     Total: 1 + 1 = 2
 *   
 *   i = 2:
 *     expand(s, 2, 2): left=2, right=2, 'c' == 'c' ✓ → count=1
 *                      left=1, right=3 → invalid, stop
 *     expand(s, 2, 3): left=2, right=3 → invalid, stop
 *     Total: 2 + 1 = 3
 *   
 *   Result: 3
 * 
 *   Input: s = "aaa"
 *   
 *   i = 0:
 *     expand(s, 0, 0): left=0, right=0, 'a' == 'a' ✓ → count=1
 *                      left=-1, right=1 → invalid, stop
 *     expand(s, 0, 1): left=0, right=1, 'a' == 'a' ✓ → count=1
 *                      left=-1, right=2 → invalid, stop
 *     Total: 1 + 1 = 2
 *   
 *   i = 1:
 *     expand(s, 1, 1): left=1, right=1, 'a' == 'a' ✓ → count=1
 *                      left=0, right=2, 'a' == 'a' ✓ → count=2
 *                      left=-1, right=3 → invalid, stop
 *     expand(s, 1, 2): left=1, right=2, 'a' == 'a' ✓ → count=1
 *                      left=0, right=3 → invalid, stop
 *     Total: 2 + 2 + 1 = 5
 *   
 *   i = 2:
 *     expand(s, 2, 2): left=2, right=2, 'a' == 'a' ✓ → count=1
 *                      left=1, right=3 → invalid, stop
 *     expand(s, 2, 3): left=2, right=3 → invalid, stop
 *     Total: 5 + 1 = 6
 *   
 *   Result: 6
 * 
 * Visual Representation:
 * 
 *   s = "abc"
 *   Positions: 0  1  2
 *             a  b  c
 *   
 *   Odd-length palindromes (center at single character):
 *     i=0: [a] → "a"
 *     i=1: [b] → "b"
 *     i=2: [c] → "c"
 *   
 *   Even-length palindromes (center between characters):
 *     i=0: gap between 0 and 1 → "ab" ✗ (not palindrome)
 *     i=1: gap between 1 and 2 → "bc" ✗ (not palindrome)
 *   
 *   Total: 3 palindromes
 * 
 *   s = "aaa"
 *   Positions: 0  1  2
 *             a  a  a
 *   
 *   Odd-length palindromes:
 *     i=0: [a] → "a"
 *     i=1: [a] → "a", [a-a-a] → "aaa"
 *     i=2: [a] → "a"
 *   
 *   Even-length palindromes:
 *     i=0: gap between 0 and 1 → "aa"
 *     i=1: gap between 1 and 2 → "aa"
 *   
 *   Total: 6 palindromes
 * 
 * Why This Works:
 * 
 *   The expand around centers approach is efficient because:
 *   1. Every palindrome has a unique center (or two centers for even-length)
 *   2. By checking all possible centers, we find all palindromes
 *   3. We avoid redundant checks by expanding from each center
 *   4. The expansion stops as soon as characters don't match
 * 
 *   For a string of length n:
 *   - Number of odd centers: n (one for each character)
 *   - Number of even centers: n-1 (one between each pair of characters)
 *   - Total centers: 2n-1
 *   - Each expansion takes O(n) in worst case
 *   - Total time: O(n²)
 * 
 * Edge Cases:
 * 
 *   1. Empty string:
 *      s = ""
 *      Result: 0 (no substrings)
 *   
 *   2. Single character:
 *      s = "a"
 *      Result: 1 (the character itself)
 *   
 *   3. All same characters:
 *      s = "aaaa"
 *      Result: 10 (all substrings are palindromes)
 *      Substrings: "a", "a", "a", "a", "aa", "aa", "aa", "aaa", "aaa", "aaaa"
 *   
 *   4. No palindromes except single characters:
 *      s = "abcde"
 *      Result: 5 (each character is a palindrome)
 *   
 *   5. Mixed palindromes:
 *      s = "racecar"
 *      Result: 10
 *      Includes: "r", "a", "c", "e", "c", "a", "r", "cec", "aceca", "racecar"
 * 
 * Alternative Approaches:
 * 
 *   1. Brute Force: O(n³)
 *      - Check every substring if it's a palindrome
 *      - Too slow for large inputs
 *   
 *   2. Dynamic Programming: O(n²) time, O(n²) space
 *      - Use DP table to store if substring is palindrome
 *      - More space than expand around centers
 *   
 *   3. Manacher's Algorithm: O(n) time
 *      - More complex but optimal
 *      - Overkill for this problem
 */

class Solution {
    /**
     * Counts the number of palindromic substrings in the given string.
     * 
     * @param s The input string
     * @return The number of palindromic substrings
     * 
     * Time Complexity: O(n²) where n is the length of s
     * Space Complexity: O(1)
     */
    public int countSubstrings(String s) {
        int count = 0;
        
        // Iterate through each character as a potential center
        for (int i = 0; i < s.length(); i++) {
            // Expand around single character (odd length palindromes)
            // Example: "aba" with center at 'b'
            count += expand(s, i, i);
            
            // Expand around gap between characters (even length palindromes)
            // Example: "abba" with center between the two 'b's
            count += expand(s, i, i + 1);
        }
        
        return count;
    }
    
    /**
     * Expands around a center (or two centers) and counts palindromic substrings.
     * 
     * @param s The input string
     * @param left The left starting position (inclusive)
     * @param right The right starting position (inclusive)
     * @return The number of palindromic substrings found by expanding from this center
     * 
     * How it works:
     * - Starts with left and right at the center(s)
     * - Expands outward while characters match
     * - Each valid expansion represents a palindrome
     * - Stops when characters don't match or indices go out of bounds
     */
    private int expand(String s, int left, int right) {
        int internalCount = 0;
        
        // Expand outward while characters match and indices are valid
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            internalCount++;  // Found a palindrome
            left--;            // Expand left
            right++;           // Expand right
        }
        
        return internalCount;
    }
}

