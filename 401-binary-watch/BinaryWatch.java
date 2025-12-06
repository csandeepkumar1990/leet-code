/**
 * LeetCode 401: Binary Watch
 * 
 * Problem: A binary watch has 4 LEDs for hours (0-11) and 6 LEDs for minutes (0-59).
 *          Each LED represents a power of 2. Given the number of LEDs that are ON,
 *          return all possible times the watch could display.
 * 
 *          Hour LEDs:    8  4  2  1     (4 LEDs, max = 8+4 = 12, but valid 0-11)
 *          Minute LEDs: 32 16  8  4  2  1  (6 LEDs, max = 32+16+8+2+1 = 59)
 * 
 * Key Insight: Instead of generating combinations, brute force all possible times!
 *              There are only 12 × 60 = 720 possible times.
 *              For each time, count the bits set in hour + bits set in minute.
 *              If total equals turnedOn, it's a valid time.
 * 
 * Examples:
 *   Input: turnedOn = 1
 *   Output: ["0:01","0:02","0:04","0:08","0:16","0:32","1:00","2:00","4:00","8:00"]
 *   Explanation: Each time has exactly 1 LED on (1 bit set total)
 * 
 *   Input: turnedOn = 9
 *   Output: []
 *   Explanation: Impossible - max bits = 4 (hour) + 6 (minute) = 10,
 *                but can't represent valid time with 9 bits
 * 
 * Constraints:
 *   - 0 <= turnedOn <= 10
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    /**
     * Returns all possible times for a binary watch with turnedOn LEDs lit.
     * 
     * @param turnedOn - Number of LEDs that are ON
     * @return List of valid time strings in "H:MM" format
     * 
     * Time Complexity: O(1) - always check 12 × 60 = 720 combinations
     * Space Complexity: O(1) - output size is bounded (max ~200 times)
     */
    public List<String> readBinaryWatch(int turnedOn) {
        List<String> result = new ArrayList<>();

        /*
         * Brute force: check all possible times
         * 
         * Instead of complex bit combination generation,
         * we simply iterate through all valid times and
         * count how many bits are set.
         * 
         * This is efficient because:
         * - Only 720 total combinations (12 hours × 60 minutes)
         * - Integer.bitCount() is O(1) using CPU instruction
         */

        // Iterate through all possible hours (0-11)
        for (int hour = 0; hour < 12; hour++) {
            // Iterate through all possible minutes (0-59)
            for (int minute = 0; minute < 60; minute++) {
                /*
                 * Count total bits set in both hour and minute
                 * 
                 * Integer.bitCount() returns the number of 1-bits
                 * in the binary representation of the integer.
                 * 
                 * Example: hour=5 (101), minute=3 (11)
                 *          bitCount(5) = 2, bitCount(3) = 2
                 *          total = 4 LEDs on
                 */
                int totalBitsSet = Integer.bitCount(hour) + Integer.bitCount(minute);

                // If total bits match the required LEDs on, it's a valid time
                if (totalBitsSet == turnedOn) {
                    /*
                     * Format time string: "H:MM"
                     * %d   = hour (no leading zero)
                     * %02d = minute (with leading zero if needed)
                     * 
                     * Examples: "0:01", "1:30", "11:59"
                     */
                    String timeString = String.format("%d:%02d", hour, minute);
                    result.add(timeString);
                }
            }
        }

        return result;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.readBinaryWatch(1));
 * // Output: [0:01, 0:02, 0:04, 0:08, 0:16, 0:32, 1:00, 2:00, 4:00, 8:00]
 * 
 * System.out.println(sol.readBinaryWatch(2));
 * // Output: [0:03, 0:05, 0:06, 0:09, 0:10, 0:12, 0:17, 0:18, 0:20, ...]
 * 
 * ═══════════════════════════════════════════════════════════════
 * BINARY WATCH VISUALIZATION
 * ═══════════════════════════════════════════════════════════════
 * 
 *         HOURS              MINUTES
 *    ┌─────────────┐    ┌───────────────────┐
 *    │ 8  4  2  1  │    │ 32 16  8  4  2  1 │
 *    │ ○  ○  ○  ○  │    │ ○  ○  ○  ○  ○  ○  │
 *    └─────────────┘    └───────────────────┘
 *       (4 LEDs)              (6 LEDs)
 * 
 * Example: 5:37
 *    Hours = 5 = 4 + 1 = 0101 (2 bits)
 *    Minutes = 37 = 32 + 4 + 1 = 100101 (3 bits)
 *    Total LEDs on = 2 + 3 = 5
 * 
 *    ┌─────────────┐    ┌───────────────────┐
 *    │ 8  4  2  1  │    │ 32 16  8  4  2  1 │
 *    │ ○  ●  ○  ●  │    │ ●  ○  ○  ●  ○  ● │
 *    └─────────────┘    └───────────────────┘
 *         5                     37
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY BRUTE FORCE WORKS BEST HERE
 * ═══════════════════════════════════════════════════════════════
 * 
 * Alternative approaches:
 * 1. Backtracking to generate bit combinations
 * 2. Use combinations formula C(10, turnedOn)
 * 
 * But brute force is simpler and just as fast because:
 * - Only 720 iterations (12 × 60)
 * - No complex recursion or combination logic
 * - Integer.bitCount() is a single CPU instruction (POPCNT)
 * - Easy to understand and maintain
 * 
 * ═══════════════════════════════════════════════════════════════
 * BIT COUNT EXAMPLES
 * ═══════════════════════════════════════════════════════════════
 * 
 *   Hour | Binary | Bits     Minute | Binary  | Bits
 *   -----|--------|-----     -------|---------|-----
 *    0   |  0000  |  0         0    | 000000  |  0
 *    1   |  0001  |  1         1    | 000001  |  1
 *    2   |  0010  |  1         3    | 000011  |  2
 *    3   |  0011  |  2        15    | 001111  |  4
 *    5   |  0101  |  2        31    | 011111  |  5
 *    7   |  0111  |  3        59    | 111011  |  5
 *   11   |  1011  |  3
 * 
 * Max bits: hour=11 (3 bits) + minute=59 (5 bits) = 8 bits
 * So turnedOn > 8 always returns empty list!
 */

