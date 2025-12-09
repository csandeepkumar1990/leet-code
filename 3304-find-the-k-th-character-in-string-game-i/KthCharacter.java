/**
 * LeetCode 3304: Find the K-th Character in String Game I
 * 
 * String doubles each step: second half = first half shifted by 1.
 * "a" → "ab" → "abbc" → "abbcbccd" ...
 * Use recursion: if k in first half, recurse; if second half, shift result.
 */

class Solution {

    public char kthCharacter(int k) {
        long length = 1;

        // Find smallest power of 2 >= k
        while (length < k) {
            length *= 2;
        }

        return getChar(k, length);
    }

    private char getChar(long k, long length) {
        if (length == 1) return 'a';  // Base case

        long half = length / 2;

        if (k <= half) {
            // First half: same as previous iteration
            return getChar(k, half);
        } else {
            // Second half: next char of corresponding first-half position
            char c = getChar(k - half, half);
            return (char)(c + 1);
        }
    }
}

