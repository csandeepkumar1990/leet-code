/**
 * LeetCode 3456: Find Special Substring of Length K
 * 
 * Check if string has a block of exactly k consecutive same characters.
 * Track consecutive runs, return true if any run equals k.
 */

class Solution {

    public boolean hasSpecialSubstring(String s, int k) {
        int count = 1;

        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                count++;  // extend current run
            } else {
                if (count == k)
                    return true;  // found exact-length block
                count = 1;  // reset for new character
            }
        }

        return count == k;  // check the last run
    }
}

