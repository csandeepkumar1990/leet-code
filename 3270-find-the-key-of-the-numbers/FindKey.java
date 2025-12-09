/**
 * LeetCode 3270: Find the Key of the Numbers
 * 
 * Given 3 numbers, pad to 4 digits, take min digit at each position.
 * Return resulting number (leading zeros removed via parseInt).
 * 
 * Example: num1=1, num2=10, num3=1000
 *   "0001", "0010", "1000" → min at each pos → "0000" → 0
 */

class Solution {

    public int generateKey(int num1, int num2, int num3) {
        // Pad each number to 4 digits with leading zeros
        String s1 = String.format("%04d", num1);
        String s2 = String.format("%04d", num2);
        String s3 = String.format("%04d", num3);

        StringBuilder key = new StringBuilder();

        // Take minimum digit at each position
        for (int i = 0; i < 4; i++) {
            int d1 = s1.charAt(i) - '0';
            int d2 = s2.charAt(i) - '0';
            int d3 = s3.charAt(i) - '0';

            int minDigit = Math.min(d1, Math.min(d2, d3));
            key.append(minDigit);
        }

        // parseInt removes leading zeros
        return Integer.parseInt(key.toString());
    }
}

