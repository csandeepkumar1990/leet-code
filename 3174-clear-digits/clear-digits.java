/**
 * LeetCode 3174 - Clear Digits
 * 
 * Problem: Given a string s, remove all digits and their closest non-digit 
 * character to the left. Repeat until no digits remain.
 * 
 * Example: "abc12" -> "abc1" -> "ab" (digit '1' removes 'c', digit '2' removes 'b')
 * 
 * Approach: Use a stack (StringBuilder) to track non-digit characters.
 * - When we see a digit, pop the last non-digit from the stack
 * - When we see a non-digit, push it onto the stack
 * 
 * Time Complexity: O(n) - single pass through the string
 * Space Complexity: O(n) - stack can hold up to n characters
 */
class Solution {
    public String clearDigits(String s) {
        // Use StringBuilder as a stack to store non-digit characters
        StringBuilder stack = new StringBuilder();
        
        // Iterate through each character in the string
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                // Found a digit: remove the closest non-digit on the left (top of stack)
                if (stack.length() > 0) {
                    stack.deleteCharAt(stack.length() - 1);
                }
            } else {
                // Found a non-digit: push it onto the stack
                stack.append(c);
            }
        }

        // Return the remaining characters after all digits are processed
        return stack.toString();
    }
}
