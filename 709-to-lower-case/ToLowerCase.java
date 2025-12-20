/*
 * LeetCode 709: To Lower Case
 * 
 * Problem:
 * Given a string s, return the string after replacing every uppercase letter
 * with the same lowercase letter.
 * 
 * Approach 1: Built-in Method (Used Here)
 * 
 * Key Insight:
 * - Java's String.toLowerCase() handles all the work
 * - Simplest and most readable solution
 * 
 * Time Complexity: O(n) - iterates through each character
 * Space Complexity: O(n) - creates new string
 * 
 * Example:
 *   Input:  "Hello"
 *   Output: "hello"
 * 
 *   Input:  "LOVELY"
 *   Output: "lovely"
 * 
 * ============================================================
 * ALTERNATIVE APPROACHES
 * ============================================================
 * 
 * Approach 2: Manual ASCII Conversion
 * 
 *   public String toLowerCase(String s) {
 *       char[] chars = s.toCharArray();
 *       for (int i = 0; i < chars.length; i++) {
 *           if (chars[i] >= 'A' && chars[i] <= 'Z') {
 *               chars[i] = (char)(chars[i] + 32);
 *           }
 *       }
 *       return new String(chars);
 *   }
 * 
 *   ASCII Insight:
 *   - 'A' = 65, 'Z' = 90
 *   - 'a' = 97, 'z' = 122
 *   - Difference = 32
 *   - So 'A' + 32 = 'a'
 * 
 * Approach 3: Using OR with 32 (Bit Manipulation)
 * 
 *   public String toLowerCase(String s) {
 *       char[] chars = s.toCharArray();
 *       for (int i = 0; i < chars.length; i++) {
 *           if (chars[i] >= 'A' && chars[i] <= 'Z') {
 *               chars[i] |= 32;  // Set the 6th bit
 *           }
 *       }
 *       return new String(chars);
 *   }
 * 
 *   Bit Insight:
 *   'A' = 01000001 (65)
 *   'a' = 01100001 (97)
 *          ↑
 *        6th bit differs!
 *   
 *   OR with 32 (00100000) sets the 6th bit:
 *   01000001 | 00100000 = 01100001  ('A' → 'a')
 * 
 * Approach 4: Character.toLowerCase()
 * 
 *   public String toLowerCase(String s) {
 *       StringBuilder sb = new StringBuilder();
 *       for (char c : s.toCharArray()) {
 *           sb.append(Character.toLowerCase(c));
 *       }
 *       return sb.toString();
 *   }
 * 
 * ============================================================
 * ASCII TABLE REFERENCE
 * ============================================================
 * 
 *   Uppercase:  A  B  C  ...  Z
 *   ASCII:     65 66 67 ... 90
 * 
 *   Lowercase:  a  b  c  ...  z
 *   ASCII:     97 98 99 ... 122
 * 
 *   Gap: 97 - 65 = 32
 * 
 *   Conversion: uppercase + 32 = lowercase
 * 
 * Visual:
 * 
 *   Input:  "HeLLo"
 *            ↓↓  ↓
 *   Convert: H→h, L→l, L→l
 *   Output: "hello"
 */

class Solution {

    public String toLowerCase(String s) {

        String lowerCase = s.toLowerCase();

        return lowerCase;

    }

}

