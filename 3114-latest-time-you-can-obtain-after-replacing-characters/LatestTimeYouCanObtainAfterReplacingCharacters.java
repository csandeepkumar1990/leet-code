/*
 * LeetCode 3114: Latest Time You Can Obtain After Replacing Characters
 * 
 * Problem:
 * Given a string s representing a 12-hour format time with some '?' characters,
 * replace each '?' with a digit to form the LATEST valid time.
 * 
 * Format: "HH:MM" where HH is 00-11 and MM is 00-59
 * 
 * Approach: Greedy - Maximize Each Position
 * 
 * Key Insight:
 * - To get the latest time, maximize each digit while keeping it valid
 * - Hours (HH): Valid range 00-11
 * - Minutes (MM): Valid range 00-59
 * 
 * Hour Logic (positions 0 and 1):
 * - Both ??: Set to "11" (maximum hour)
 * - Only time[0] is ?: 
 *   - If time[1] <= '1', set time[0] = '1' (e.g., ?1 → 11)
 *   - Else set time[0] = '0' (e.g., ?5 → 05, can't be 15)
 * - Only time[1] is ?:
 *   - If time[0] == '1', set time[1] = '1' (e.g., 1? → 11)
 *   - Else set time[1] = '9' (e.g., 0? → 09)
 * 
 * Minute Logic (positions 3 and 4):
 * - time[3] = '?' → Set to '5' (max tens digit: 50-59)
 * - time[4] = '?' → Set to '9' (max units digit: X9)
 * 
 * Time Complexity: O(1) - fixed 5 characters
 * Space Complexity: O(1) - char array of fixed size
 * 
 * Example: s = "1?:?4"
 * 
 * Initial: "1?:?4"
 * 
 * Hour handling:
 * - time[0] = '1', time[1] = '?'
 * - Since time[0] == '1', set time[1] = '1'
 * - Result: "11:?4"
 * 
 * Minute handling:
 * - time[3] = '?' → set to '5'
 * - time[4] = '4' → already set
 * - Result: "11:54"
 * 
 * Return: "11:54"
 * 
 * Example: s = "?5:00"
 * 
 * Hour handling:
 * - time[0] = '?', time[1] = '5'
 * - Since '5' > '1', can't use 1? (would be 15, invalid)
 * - Set time[0] = '0'
 * - Result: "05:00"
 * 
 * Return: "05:00"
 * 
 * Visual (12-hour Clock Valid Hours):
 * 
 *   Valid: 00, 01, 02, 03, 04, 05, 06, 07, 08, 09, 10, 11
 *          └─────────────────────────────────────────────┘
 *                       00-11 (12 hours)
 * 
 *   For HH:
 *   - First digit: 0 or 1
 *   - If first digit is 1: second digit can only be 0 or 1
 *   - If first digit is 0: second digit can be 0-9
 * 
 *   Decision Tree for Hours:
 *   
 *   time[0] time[1]   Action
 *   ─────── ───────   ──────────────────────
 *     ?       ?       → "11" (max hour)
 *     ?      0-1      → "1?" (can use 10, 11)
 *     ?      2-9      → "0?" (must use 0X)
 *     0       ?       → "09" (max with 0 prefix)
 *     1       ?       → "11" (max with 1 prefix)
 */

class Solution {

    public String findLatestTime(String s) {

        char[] time = s.toCharArray();

        // ========== Handle Hours (HH) ==========
        
        if (time[0] == '?' && time[1] == '?') {

            // Both unknown: use maximum valid hour "11"
            time[0] = '1';

            time[1] = '1';

        } else if (time[0] == '?') {

            // First digit unknown, second is known
            // If second digit is 0 or 1, we can use 1X (10 or 11)
            // Otherwise, must use 0X (02-09, etc.)
            if (time[1] <= '1') {

                time[0] = '1'; // e.g., ?0 → 10, ?1 → 11

            } else {

                time[0] = '0'; // e.g., ?5 → 05 (can't be 15, invalid)

            }

        } else if (time[1] == '?') {

            // Second digit unknown, first is known
            if (time[0] == '1') {

                time[1] = '1'; // 1? → 11 (max hour starting with 1)

            } else {

                time[1] = '9'; // 0? → 09 (max hour starting with 0)

            }

        }

        // ========== Handle Minutes (MM) ==========

        // Tens digit of minutes (position 3)
        if (time[3] == '?') {

            time[3] = '5'; // Max tens digit for minutes (50-59)

        }

        // Units digit of minutes (position 4)
        if (time[4] == '?') {

            time[4] = '9'; // Max units digit (X9)

        }

        return new String(time);

    }

}

