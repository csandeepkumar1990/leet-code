/*
 * LeetCode 1853: Convert Date Format
 * 
 * Problem:
 * Write a solution to convert each date in the Days table into the format
 * "day_name, month_name day, year" (e.g., "Monday, January 1, 2024").
 * Return the result table in any order.
 * 
 * Table: Days
 * +-------------+---------+
 * | Column Name | Type    |
 * +-------------+---------+
 * | day         | date    |
 * +-------------+---------+
 * day is the primary key for this table.
 * Each row contains a date.
 * 
 * Approach: DATE_FORMAT Function
 * 
 * Key Insight:
 * - Use DATE_FORMAT to convert date to custom string format
 * - Format: "Weekday, Month Day, Year"
 * - Use format specifiers to extract date components
 * 
 * DATE_FORMAT Format Specifiers:
 *   %W  - Full weekday name (Monday, Tuesday, etc.)
 *   %M  - Full month name (January, February, etc.)
 *   %e  - Day of month (1-31, no leading zero)
 *   %Y  - Four-digit year (2024, 2023, etc.)
 * 
 * Format Pattern:
 *   '%W, %M %e, %Y'
 *   - %W: Weekday name
 *   - , : Comma and space
 *   - %M: Month name
 *   - %e: Day (no leading zero)
 *   - , : Comma and space
 *   - %Y: Year
 * 
 * Formula:
 *   formatted_date = DATE_FORMAT(day, '%W, %M %e, %Y')
 * 
 * Algorithm:
 * 1. Use DATE_FORMAT function on day column
 * 2. Apply format pattern '%W, %M %e, %Y'
 * 3. Return formatted date string
 * 
 * Example:
 * 
 *   Days:
 *   +------------+
 *   | day        |
 *   +------------+
 *   | 2022-04-12 |
 *   | 2021-08-09 |
 *   | 2020-06-26 |
 *   | 2024-01-01 |
 *   +------------+
 * 
 *   Step 1 - Apply DATE_FORMAT:
 *   2022-04-12 → "Tuesday, April 12, 2022"
 *   2021-08-09 → "Monday, August 9, 2021"
 *   2020-06-26 → "Friday, June 26, 2020"
 *   2024-01-01 → "Monday, January 1, 2024"
 * 
 *   Result:
 *   +-------------------------+
 *   | day                     |
 *   +-------------------------+
 *   | Tuesday, April 12, 2022 |
 *   | Monday, August 9, 2021  |
 *   | Friday, June 26, 2020   |
 *   | Monday, January 1, 2024 |
 *   +-------------------------+
 * 
 * DATE_FORMAT Function:
 *   DATE_FORMAT(date, format_string)
 *   - date: Date value to format
 *   - format_string: Pattern with format specifiers
 *   - Returns: Formatted string
 * 
 * Common Format Specifiers:
 *   %W  - Full weekday name (Monday, Tuesday, ...)
 *   %w  - Weekday number (0=Sunday, 1=Monday, ...)
 *   %M  - Full month name (January, February, ...)
 *   %m  - Month number (01-12)
 *   %b  - Abbreviated month (Jan, Feb, ...)
 *   %e  - Day of month (1-31, no leading zero)
 *   %d  - Day of month (01-31, with leading zero)
 *   %Y  - Four-digit year (2024)
 *   %y  - Two-digit year (24)
 *   %D  - Day with suffix (1st, 2nd, 3rd, ...)
 * 
 * Why %e instead of %d?
 *   - %e: Day without leading zero (1, 2, ..., 31)
 *   - %d: Day with leading zero (01, 02, ..., 31)
 *   - Format requires "January 1" not "January 01"
 *   - %e is more natural for this format
 * 
 * Format String Breakdown:
 *   '%W, %M %e, %Y'
 *   - %W: "Monday", "Tuesday", etc.
 *   - , : Literal comma
 *   - (space): Literal space
 *   - %M: "January", "February", etc.
 *   - %e: "1", "2", ..., "31" (no leading zero)
 *   - , : Literal comma
 *   - (space): Literal space
 *   - %Y: "2024", "2023", etc.
 * 
 * Alternative Formats:
 *   - '%W, %M %D, %Y' → "Monday, January 1st, 2024"
 *   - '%a, %b %e, %Y' → "Mon, Jan 1, 2024" (abbreviated)
 *   - '%M %e, %Y' → "January 1, 2024" (no weekday)
 * 
 * Case Sensitivity:
 *   - DATE_FORMAT output is case-sensitive
 *   - Weekday and month names start with capital letter
 *   - Example: "Monday" not "monday"
 * 
 * Edge Cases:
 *   - Single digit day: %e handles correctly (1, not 01)
 *   - Leap year dates: Handled correctly
 *   - Different years: %Y shows full year
 *   - Different months: %M shows full month name
 */

# Write your MySQL query statement below

SELECT 
    DATE_FORMAT(day, '%W, %M %e, %Y') AS day
FROM Days;

