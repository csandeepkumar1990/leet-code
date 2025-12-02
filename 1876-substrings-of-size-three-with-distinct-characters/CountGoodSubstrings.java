import java.util.HashSet;

/**
 * LeetCode 1876: Substrings of Size Three with Distinct Characters
 * 
 * Problem:
 * A string is "good" if there are no repeated characters.
 * Given a string s, return the number of good substrings of length 3.
 * 
 * Note: Multiple occurrences of the same substring are counted separately!
 * 
 * Examples:
 *   "xyzzaz" -> 1
 *     Substrings: "xyz"✓, "yzz"✗, "zza"✗, "zaz"✗
 *     Only "xyz" has all distinct characters
 * 
 *   "aababcabc" -> 4
 *     Substrings: "aab"✗, "aba"✗, "bab"✗, "abc"✓, "bca"✓, "cab"✓, "abc"✓
 *     Four substrings have all distinct characters
 * 
 * Approach:
 * - Use a sliding window of size 3
 * - For each window, check if all 3 characters are unique using a HashSet
 * - HashSet automatically handles duplicates, so if size == 3, all chars are unique
 * 
 * Time Complexity: O(n) - single pass through the string
 *                  (HashSet operations are O(1) for small fixed-size windows)
 * Space Complexity: O(1) - HashSet holds at most 3 characters
 */
class Solution {

    public int countGoodSubstrings(String s) {
        // Edge case: string too short to have any length-3 substrings
        if (s.length() < 3) return 0;
        
        int count = 0;
        
        // Slide a window of size 3 across the string
        // i represents the starting index of each window
        // We stop at s.length() - 3 to ensure we have 3 characters to check
        for (int i = 0; i <= s.length() - 3; i++) {
            // Use a HashSet to track unique characters in this window
            // HashSet doesn't allow duplicates, so adding a duplicate won't increase size
            HashSet<Character> set = new HashSet<>();
            
            // Add all 3 characters of the current window to the set
            set.add(s.charAt(i));       // First character
            set.add(s.charAt(i + 1));   // Second character
            set.add(s.charAt(i + 2));   // Third character
            
            // If set size is 3, all characters are distinct (no duplicates)
            // Example: "abc" -> set = {a, b, c} -> size = 3 ✓
            // Example: "aba" -> set = {a, b} -> size = 2 ✗ (duplicate 'a')
            if (set.size() == 3) {
                count++;
            }
        }
        
        return count;
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: Only "xyz" is good
        System.out.println(sol.countGoodSubstrings("xyzzaz"));    // Output: 1
        
        // Test 2: "abc", "bca", "cab", "abc" are good
        System.out.println(sol.countGoodSubstrings("aababcabc")); // Output: 4
        
        // Test 3: Edge case - string too short
        System.out.println(sol.countGoodSubstrings("ab"));        // Output: 0
    }
}

