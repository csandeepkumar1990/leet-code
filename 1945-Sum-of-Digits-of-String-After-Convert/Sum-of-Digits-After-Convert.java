/**
 * LeetCode 1945: Sum of Digits of String After Convert
 * 
 * Problem Description:
 * --------------------
 * Given a string 's' consisting of lowercase English letters and an integer 'k':
 * 1. Convert 's' into an integer by replacing each letter with its position in the alphabet
 *    (i.e., 'a' = 1, 'b' = 2, ..., 'z' = 26) and concatenating all the integers together.
 * 2. Transform the integer by replacing it with the sum of its digits.
 * 3. Repeat step 2 exactly 'k' times.
 * 
 * Return the resulting integer after performing the operations described above.
 * 
 * Constraints:
 * - 1 <= s.length <= 100
 * - 1 <= k <= 10
 * - 's' consists of lowercase English letters only
 * 
 * =============================================================================================
 * EXAMPLES:
 * =============================================================================================
 * 
 * Example 1: s = "iiii", k = 1
 * ----------------------------
 * Step 1 - Convert letters to positions:
 *   'i' = 9, 'i' = 9, 'i' = 9, 'i' = 9
 *   Concatenated: "9999"
 * 
 * Step 2 - Transform (k=1 iteration):
 *   Sum of digits: 9 + 9 + 9 + 9 = 36
 * 
 * Output: 36
 * 
 * ----------------------------------------------------------------------------------------------
 * 
 * Example 2: s = "leetcode", k = 2
 * --------------------------------
 * Step 1 - Convert letters to positions:
 *   'l' = 12, 'e' = 5, 'e' = 5, 't' = 20, 'c' = 3, 'o' = 15, 'd' = 4, 'e' = 5
 *   Concatenated: "12552031545"
 * 
 * Step 2 - Transform (k=2 iterations):
 *   Iteration 1: 1+2+5+5+2+0+3+1+5+4+5 = 33
 *   Iteration 2: 3 + 3 = 6
 * 
 * Output: 6
 * 
 * ----------------------------------------------------------------------------------------------
 * 
 * Example 3: s = "zbax", k = 2
 * ----------------------------
 * Step 1 - Convert letters to positions:
 *   'z' = 26, 'b' = 2, 'a' = 1, 'x' = 24
 *   Concatenated: "262124"
 * 
 * Step 2 - Transform (k=2 iterations):
 *   Iteration 1: 2+6+2+1+2+4 = 17
 *   Iteration 2: 1 + 7 = 8
 * 
 * Output: 8
 * 
 * =============================================================================================
 * 
 * Time Complexity: O(n + k * m)
 *   - n = length of input string 's'
 *   - m = number of digits in the numeric string (at most 2*n since max position is 26)
 *   - Each transformation reduces the number significantly, so k iterations are fast
 * 
 * Space Complexity: O(n)
 *   - StringBuilder to store the concatenated numeric string
 */
class Solution {
    
    /**
     * Converts a string to its "lucky" integer value after k transformations.
     * 
     * @param s The input string containing lowercase English letters
     * @param k The number of digit-sum transformations to perform
     * @return The final integer after all transformations
     */
    public int getLucky(String s, int k) {
        
        // =====================================================================
        // STEP 1: Convert each character to its alphabetical position
        // =====================================================================
        // Each letter maps to its position: 'a'=1, 'b'=2, ..., 'z'=26
        // We concatenate all positions into a single numeric string
        // 
        // Example: "zbax" -> 'z'=26, 'b'=2, 'a'=1, 'x'=24 -> "262124"
        
        StringBuilder numericString = new StringBuilder();
        
        for (char character : s.toCharArray()) {
            // Calculate position using ASCII arithmetic:
            // 'a' has ASCII 97, so 'a' - 'a' + 1 = 1
            // 'b' has ASCII 98, so 'b' - 'a' + 1 = 2
            // 'z' has ASCII 122, so 'z' - 'a' + 1 = 26
            int position = character - 'a' + 1;
            numericString.append(position);
        }
        
        // Convert StringBuilder to String for digit processing
        String currentNumber = numericString.toString();
        
        // =====================================================================
        // STEP 2: Perform k iterations of digit sum transformation
        // =====================================================================
        // In each iteration, we sum all digits of the current number
        // 
        // Example with "262124" and k=2:
        //   Iteration 1: 2+6+2+1+2+4 = 17 -> currentNumber = "17"
        //   Iteration 2: 1+7 = 8 -> currentNumber = "8"
        
        while (k-- > 0) {
            int digitSum = 0;
            
            // Sum all digits in the current number string
            for (char digit : currentNumber.toCharArray()) {
                // Convert character to integer: '0'->0, '1'->1, ..., '9'->9
                // This works because '0' has ASCII 48, '1' has ASCII 49, etc.
                digitSum += digit - '0';
            }
            
            // Update currentNumber with the computed sum for the next iteration
            currentNumber = String.valueOf(digitSum);
        }
        
        // =====================================================================
        // STEP 3: Return the final result
        // =====================================================================
        return Integer.parseInt(currentNumber);
    }
}