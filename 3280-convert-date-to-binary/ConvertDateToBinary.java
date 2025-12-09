/**
 * LeetCode 3280: Convert Date to Binary
 * 
 * Convert "YYYY-MM-DD" to binary format.
 * Split by "-", convert each part to binary, rejoin with "-".
 */

class Solution {

    public String convertDateToBinary(String date) {
        String[] parts = date.split("-");

        // Convert each part (year, month, day) to binary
        for (int i = 0; i < 3; i++) {
            parts[i] = Integer.toBinaryString(Integer.parseInt(parts[i]));
        }

        // Rejoin with "-"
        return String.join("-", parts);
    }
}

