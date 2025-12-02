/**
 * LeetCode 1844: Replace All Digits with Characters
 * 
 * Problem:
 * Given a string s where:
 *   - Even indices (0, 2, 4...) contain lowercase letters
 *   - Odd indices (1, 3, 5...) contain digits
 * 
 * Replace each digit at odd index i with: shift(s[i-1], s[i])
 * where shift(c, x) returns the character x positions after c in the alphabet.
 * 
 * Examples:
 *   shift('a', 5) = 'f'  (a -> b -> c -> d -> e -> f)
 *   shift('x', 0) = 'x'  (no shift)
 * 
 * Example 1: "a1c1e1" -> "abcdef"
 *   s[1] = '1' -> shift('a', 1) = 'b'
 *   s[3] = '1' -> shift('c', 1) = 'd'
 *   s[5] = '1' -> shift('e', 1) = 'f'
 * 
 * Example 2: "a1b2c3d4e" -> "abbdcfdhe"
 *   s[1] = '1' -> shift('a', 1) = 'b'
 *   s[3] = '2' -> shift('b', 2) = 'd'
 *   s[5] = '3' -> shift('c', 3) = 'f'
 *   s[7] = '4' -> shift('d', 4) = 'h'
 * 
 * Approach:
 * - Convert string to char array for in-place modification
 * - Iterate through odd indices only (i = 1, 3, 5...)
 * - For each digit, shift the previous character by that amount
 * - Character arithmetic works because chars are ASCII values
 *   ('a' + 1 = 'b', since 97 + 1 = 98)
 * 
 * Time Complexity: O(n) - single pass through the string
 * Space Complexity: O(n) - char array to hold the result
 */
class Solution {

    public String replaceDigits(String s) {
        // Convert to char array for efficient in-place modification
        // Strings in Java are immutable, so we need a mutable structure
        char[] chars = s.toCharArray();
        
        // Process only odd indices (1, 3, 5, ...) where digits are located
        for (int i = 1; i < chars.length; i += 2) {
            // Get the base character 'c' at the even index (i-1)
            // This is the letter we'll shift from
            char baseChar = chars[i - 1];
            
            // Get the digit character 'x' at the odd index (i)
            // Need to convert from char '1' to int 1
            char digitChar = chars[i];
            int offset = Character.getNumericValue(digitChar);
            // Alternative: int offset = digitChar - '0';
            // '1' - '0' = 49 - 48 = 1
            
            // Perform the shift operation using character arithmetic
            // Characters are stored as ASCII values, so:
            // 'a' + 1 = 97 + 1 = 98 = 'b'
            // 'c' + 3 = 99 + 3 = 102 = 'f'
            char shiftedChar = (char) (baseChar + offset);
            
            // Replace the digit at index i with the shifted character
            chars[i] = shiftedChar;
        }
        
        // Convert char array back to String
        return new String(chars);
    }
}

// Test cases
class Main {
    public static void main(String[] args) {
        Solution sol = new Solution();
        
        // Test 1: Each letter shifts by 1
        System.out.println(sol.replaceDigits("a1c1e1"));    // Output: "abcdef"
        
        // Test 2: Different shift amounts
        System.out.println(sol.replaceDigits("a1b2c3d4e")); // Output: "abbdcfdhe"
        
        // Test 3: Single character (no digits to replace)
        System.out.println(sol.replaceDigits("a"));         // Output: "a"
    }
}

