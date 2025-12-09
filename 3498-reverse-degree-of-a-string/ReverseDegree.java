/**
 * LeetCode 3498: Reverse Degree of a String
 * 
 * For each char, reverse position = 26 - (char - 'a').
 * Sum of (reverse position * 1-based index) for all chars.
 * 'a'→26, 'b'→25, ..., 'z'→1
 */

class Solution {

    public int reverseDegree(String s) {
        int product = 0;

        for (int i = 0; i < s.length(); i++) {
            // Reverse alphabet: 'a'=26, 'b'=25, ..., 'z'=1
            int index = 26 - (s.charAt(i) - 'a');
            product += index * (i + 1);  // multiply by 1-based position
        }

        return product;
    }
}

