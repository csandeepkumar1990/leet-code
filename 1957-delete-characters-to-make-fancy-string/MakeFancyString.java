/**
 * LeetCode 1957: Delete Characters to Make Fancy String
 * 
 * Problem: A fancy string is a string where no three consecutive characters are equal.
 *          Given a string s, delete the minimum possible number of characters from s 
 *          to make it fancy. Return the final string after the deletion.
 * 
 * Key Insight: We only need to ensure no character appears more than 2 times consecutively.
 *              We can build a new string by checking the last 2 characters before adding.
 * 
 * Examples:
 *   Input: s = "leeetcode"
 *   Output: "leetcode"
 *   Explanation: Remove one 'e' from the first group of 'e's to create "leetcode".
 *                No three consecutive characters are equal, so return "leetcode".
 * 
 *   Input: s = "aaabaaaa"
 *   Output: "aabaa"
 *   Explanation: Remove 'a' from the first group to get "aabaaaa", then remove 2 'a's 
 *                from the second group to get "aabaa". No three consecutive characters 
 *                are equal, so return "aabaa".
 * 
 *   Input: s = "aab"
 *   Output: "aab"
 *   Explanation: No three consecutive characters are equal, so return "aab".
 */
class Solution {

    /**
     * Makes the input string "fancy" by removing characters so no three
     * consecutive characters are the same.
     * 
     * @param s The input string to process
     * @return A fancy string with no three consecutive equal characters
     * 
     * Approach: Iterate through the string and only append a character if it
     *           doesn't create three consecutive equal characters.
     * 
     * Time Complexity: O(n) - single pass through the string
     * Space Complexity: O(n) - StringBuilder to store the result
     */
    public String makeFancyString(String s) {
        // StringBuilder for efficient string concatenation
        StringBuilder sb = new StringBuilder();

        // Iterate through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            // Get the current character
            char currentChar = s.charAt(i);

            // Only add the character if:
            // 1. We have fewer than 2 characters in result (i < 2), OR
            // 2. Current char is different from the previous char, OR
            // 3. Current char is different from the char before previous
            // This ensures we never have 3 consecutive equal characters
            if (i < 2 || currentChar != s.charAt(i - 1) || currentChar != s.charAt(i - 2)) {
                sb.append(currentChar);
            }
            // If all three are the same, we skip (don't append) the current character
        }

        // Convert StringBuilder to String and return
        return sb.toString();
    }
}

