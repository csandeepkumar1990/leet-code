/**
 * LeetCode 2042: Check if Numbers Are Ascending in a Sentence
 * 
 * Problem: A sentence is a list of tokens separated by a single space with no 
 *          leading or trailing spaces. Every token is either a positive number 
 *          (consisting of digits 0-9 with no leading zeros) or a word (consisting 
 *          of lowercase English letters).
 *          
 *          Return true if the numbers in the sentence are strictly increasing 
 *          from left to right, false otherwise.
 * 
 * Key Insight: Split the sentence into tokens, check only numeric tokens, 
 *              and verify each number is greater than the previous one.
 * 
 * Examples:
 *   Input: s = "1 box has 3 blue 4 red 6 green and 12 yellow marbles"
 *   Output: true
 *   Explanation: The numbers are: 1, 3, 4, 6, 12 → strictly increasing.
 * 
 *   Input: s = "hello world 5 x 5"
 *   Output: false
 *   Explanation: The numbers are: 5, 5 → not strictly increasing (5 is not > 5).
 * 
 *   Input: s = "sunset is at 7 51 pm overnight lows will be in the low 50 and 60 s"
 *   Output: false
 *   Explanation: The numbers are: 7, 51, 50, 60 → 50 is not > 51.
 */
class Solution {

    /**
     * Checks if all numbers in the sentence appear in strictly ascending order.
     * 
     * @param s The input sentence containing words and numbers
     * @return true if numbers are strictly ascending, false otherwise
     * 
     * Approach:
     *   1. Split sentence into tokens by space
     *   2. For each token, check if it's a number (starts with digit)
     *   3. Compare with previous number - must be strictly greater
     *   4. Return false immediately if any number is not greater than previous
     * 
     * Time Complexity: O(n) - where n is the length of the string
     * Space Complexity: O(n) - for storing the split tokens
     */
    public boolean areNumbersAscending(String s) {
        // Split sentence into individual tokens (words and numbers)
        String[] tokens = s.split(" ");
        
        // Track the previous number encountered (0 since all numbers are positive)
        int prev = 0;

        // Iterate through each token
        for (String token : tokens) {
            // Check if the token is a number (first character is a digit)
            if (Character.isDigit(token.charAt(0))) {
                // Parse the token to get the numeric value
                int num = Integer.parseInt(token);
                
                // Check if current number is NOT strictly greater than previous
                // Numbers must be strictly increasing, so equal values also fail
                if (num <= prev) {
                    return false;
                }
                
                // Update previous number for next comparison
                prev = num;
            }
            // If token is a word (not a number), we simply skip it
        }
        
        // All numbers were in strictly ascending order
        return true;
    }
}

