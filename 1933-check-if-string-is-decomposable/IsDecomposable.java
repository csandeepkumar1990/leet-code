/**
 * LeetCode 1933: Check if String Is Decomposable Into Value-Equal Substrings
 * 
 * Problem:
 * Decompose a digit string into consecutive value-equal substrings where:
 *   - Exactly ONE substring has length 2
 *   - All remaining substrings have length 3
 * 
 * A "value-equal" string means all characters are the same (e.g., "111", "22")
 * 
 * Examples:
 *   "000111000" -> false (no length-2 substring: ["000", "111", "000"])
 *   "00011111222" -> true (["000", "111", "11", "222"] - one length-2)
 *   "011100022233" -> false (first '0' alone can't form length 2 or 3)
 * 
 * Approach:
 * - Group consecutive identical characters
 * - Each group length must be divisible by 3, OR leave remainder 2
 * - Exactly one group can have remainder 2 (that's our required length-2 substring)
 * - If remainder is 1, it's impossible (can't make 1 from 2s and 3s)
 * 
 * Time Complexity: O(n) - single pass through the string
 * Space Complexity: O(1) - only using a few variables
 */
class Solution {

    public boolean isDecomposable(String s) {
        // Track if we've already used our one allowed length-2 substring
        boolean isSeen = false;
        int n = s.length();
        int i = 0;
        
        // Process each group of consecutive identical characters
        while (i < n) {
            char ch = s.charAt(i);  // Current character
            int j = i;
            
            // Find the end of this group (where characters stop being equal)
            while (j < n && s.charAt(j) == ch) {
                j++;
            }
            
            // Calculate the length of this group
            int len = j - i;
            
            // Check how this group can be decomposed
            if (len % 3 == 0) {
                // Perfect! Can be split entirely into length-3 substrings
                // e.g., "111111" -> ["111", "111"]
            } else if (len % 3 == 2) {
                // Can be split into length-3 substrings + one length-2
                // e.g., "11111" (len=5) -> ["111", "11"]
                // But we can only have ONE length-2 substring total
                if (isSeen)
                    return false;  // Already used our length-2, can't have another
                isSeen = true;     // Mark that we've used the length-2
            } else {
                // len % 3 == 1: Impossible to decompose!
                // e.g., "1111" (len=4) -> can't make from 2s and 3s
                // 4 = 3+1 (bad), 4 = 2+2 (would need two 2s from same group)
                return false;
            }
            
            // Move to the next group
            i = j;
        }
        
        // Must have exactly one length-2 substring (not zero, not more)
        return isSeen;
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: No length-2 substring exists
        System.out.println(sol.isDecomposable("000111000"));      // false
        
        // Test 2: Valid decomposition with one length-2
        System.out.println(sol.isDecomposable("00011111222"));    // true
        
        // Test 3: Single '0' can't form valid substring
        System.out.println(sol.isDecomposable("011100022233"));   // false
    }
}

