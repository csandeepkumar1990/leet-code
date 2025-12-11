/*
 * 1154. Day of the Year
 * 
 * Problem:
 * Given a string date representing a Gregorian calendar date formatted as
 * YYYY-MM-DD, return the day number of the year.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: date = "2019-01-09"
 *   Output: 9
 *   Explanation: January 9th is the 9th day of the year.
 * 
 * Example 2:
 *   Input: date = "2019-02-10"
 *   Output: 41
 *   Explanation: 
 *     January has 31 days
 *     February 10th = 31 + 10 = 41st day of the year
 * 
 * Example 3:
 *   Input: date = "2003-03-01"
 *   Output: 60
 *   Explanation:
 *     2003 is NOT a leap year
 *     Jan(31) + Feb(28) + 1 = 60th day
 * 
 * Example 4:
 *   Input: date = "2004-03-01"
 *   Output: 61
 *   Explanation:
 *     2004 IS a leap year (divisible by 4, not by 100)
 *     Jan(31) + Feb(29) + 1 = 61st day
 * 
 * Example 5:
 *   Input: date = "2000-12-31"
 *   Output: 366
 *   Explanation:
 *     2000 IS a leap year (divisible by 400)
 *     Last day of a leap year = 366
 * 
 * Constraints:
 *   - date.length == 10
 *   - date[4] == date[7] == '-'
 *   - date represents a valid calendar date between Jan 1, 1900 and Dec 31, 2019
 * 
 * Approach: Parse Date + Sum Previous Months + Leap Year Check
 * 
 * Algorithm:
 * 1. Parse the date string to extract year, month, day
 * 2. Define days in each month (default: non-leap year)
 * 3. Adjust February to 29 days if leap year
 * 4. Sum days from all previous months + current day
 * 
 * Leap Year Rules:
 *   - Divisible by 400 → leap year (e.g., 2000)
 *   - Divisible by 100 but not 400 → NOT leap year (e.g., 1900)
 *   - Divisible by 4 but not 100 → leap year (e.g., 2004)
 *   - Otherwise → NOT leap year
 * 
 * Time Complexity: O(1)
 *   - String parsing is O(10) = O(1) for fixed-length date
 *   - Loop runs at most 11 times (for December)
 * 
 * Space Complexity: O(1)
 *   - Fixed-size array of 12 months
 */

class Solution {

    public int dayOfYear(String date) {

        // Split date string "YYYY-MM-DD" into parts
        String[] parts = date.split("-");

        // Parse each component
        int year = Integer.parseInt(parts[0]);   // e.g., "2019" → 2019
        int month = Integer.parseInt(parts[1]);  // e.g., "02" → 2
        int day = Integer.parseInt(parts[2]);    // e.g., "10" → 10

        // Days in each month for a non-leap year
        // Index: 0=Jan, 1=Feb, 2=Mar, ..., 11=Dec
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        // Adjust February for leap years
        if (isLeapYear(year)) {

            daysInMonth[1] = 29; // February has 29 days in leap year

        }

        // Start with the day of the current month
        int dayOfYear = day;

        // Add days from all previous months (0 to month-2)
        // e.g., if month=3 (March), add days from Jan (index 0) and Feb (index 1)
        for (int i = 0; i < month - 1; i++) {

            dayOfYear += daysInMonth[i];

        }

        return dayOfYear;

    }

    // Helper method to determine if a year is a leap year
    private boolean isLeapYear(int year) {

        // Leap year rules (in order of precedence):
        // 1. Divisible by 400 → YES (e.g., 2000)
        // 2. Divisible by 100 → NO (e.g., 1900)
        // 3. Divisible by 4 → YES (e.g., 2004)
        // 4. Otherwise → NO
        return (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0);

    }

}

/*
 * Dry Run Example:
 * 
 * Input: date = "2019-02-10"
 * 
 * Step 1: Parse date
 *   parts = ["2019", "02", "10"]
 *   year = 2019, month = 2, day = 10
 * 
 * Step 2: Check leap year
 *   2019 % 400 = 19 ≠ 0
 *   2019 % 4 = 3 ≠ 0
 *   → NOT a leap year
 *   daysInMonth = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31]
 * 
 * Step 3: Calculate day of year
 *   dayOfYear = day = 10
 *   
 *   Loop: i from 0 to month-2 = 0 (only January)
 *   i = 0: dayOfYear += daysInMonth[0] = 10 + 31 = 41
 * 
 * Output: 41
 * 
 * 
 * Dry Run Example 2 (Leap Year):
 * 
 * Input: date = "2004-03-01"
 * 
 * Step 1: Parse
 *   year = 2004, month = 3, day = 1
 * 
 * Step 2: Leap year check
 *   2004 % 400 = 4 ≠ 0
 *   2004 % 4 = 0 ✓ AND 2004 % 100 = 4 ≠ 0 ✓
 *   → IS a leap year
 *   daysInMonth[1] = 29 (February updated)
 * 
 * Step 3: Calculate
 *   dayOfYear = 1
 *   i = 0: dayOfYear += 31 → 32 (January)
 *   i = 1: dayOfYear += 29 → 61 (February, leap year!)
 * 
 * Output: 61
 * 
 * 
 * Leap Year Logic Explained:
 * 
 * The Gregorian calendar leap year rules:
 * 
 * Year    % 400   % 100   % 4    Leap?   Why?
 * ----    -----   -----   ---    -----   ----
 * 2000    = 0     = 0     = 0    YES     Divisible by 400
 * 1900    ≠ 0     = 0     = 0    NO      Divisible by 100 but not 400
 * 2004    ≠ 0     ≠ 0     = 0    YES     Divisible by 4 but not 100
 * 2019    ≠ 0     ≠ 0     ≠ 0    NO      Not divisible by 4
 * 
 * Formula: (year % 400 == 0) || (year % 4 == 0 && year % 100 != 0)
 * 
 * 
 * Days in Each Month (Mnemonic):
 * 
 * "30 days has September, April, June, and November.
 *  All the rest have 31, except February alone,
 *  Which has 28 days clear, and 29 in each leap year."
 * 
 *   Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec
 *   31  28  31  30  31  30  31  31  30  31  30  31
 *       ↑
 *   (29 in leap year)
 * 
 * 
 * Alternative using Java's LocalDate (if allowed):
 * 
 * import java.time.LocalDate;
 * 
 * public int dayOfYear(String date) {
 *     return LocalDate.parse(date).getDayOfYear();
 * }
 * 
 * One-liner using built-in date API!
 */

