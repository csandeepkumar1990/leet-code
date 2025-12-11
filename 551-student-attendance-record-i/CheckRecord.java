/*
 * 551. Student Attendance Record I
 * 
 * Problem:
 * You are given a string s representing an attendance record for a student
 * where each character signifies whether the student was absent, late, or
 * present on that day. The record only contains the following three characters:
 * 
 *   'A': Absent
 *   'L': Late
 *   'P': Present
 * 
 * The student is eligible for an attendance award if they meet BOTH criteria:
 *   1. The student was absent ('A') for strictly fewer than 2 days total
 *   2. The student was never late ('L') for 3 or more consecutive days
 * 
 * Return true if the student is eligible for an attendance award, false otherwise.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "PPALLP"
 *   Output: true
 *   Explanation: 
 *     - Absences: 1 (< 2) ✓
 *     - Max consecutive lates: 2 ("LL") (< 3) ✓
 *     - Student is eligible!
 * 
 * Example 2:
 *   Input: s = "PPALLL"
 *   Output: false
 *   Explanation:
 *     - Absences: 1 (< 2) ✓
 *     - Consecutive lates: 3 ("LLL") (>= 3) ✗
 *     - Student is NOT eligible (3 consecutive lates)
 * 
 * Example 3:
 *   Input: s = "PPALLA"
 *   Output: false
 *   Explanation:
 *     - Absences: 2 (>= 2) ✗
 *     - Student is NOT eligible (too many absences)
 * 
 * Example 4:
 *   Input: s = "LLLP"
 *   Output: false
 *   Explanation:
 *     - Starts with 3 consecutive lates → NOT eligible
 * 
 * Constraints:
 *   - 1 <= s.length <= 1000
 *   - s[i] is either 'A', 'L', or 'P'
 * 
 * Approach: Single Pass with Two Counters
 * 
 * Track two things as we iterate:
 *   1. Total absences (must stay < 2)
 *   2. Current consecutive late streak (must stay < 3)
 * 
 * Key Logic:
 *   - 'A': Increment absences, reset late streak (A breaks consecutive L's)
 *   - 'L': Increment late streak
 *   - 'P': Reset late streak (P breaks consecutive L's)
 * 
 * Early termination: Return false immediately if either condition is violated.
 * 
 * Time Complexity: O(n)
 *   - Single pass through the string
 *   - Early termination possible
 * 
 * Space Complexity: O(1)
 *   - Only two integer counters used
 */

class Solution {

    public boolean checkRecord(String s) {

        // Counter for total absences
        int absents = 0;

        // Counter for current consecutive late streak
        int consecutiveLate = 0;

        // Iterate through each day's record
        for (char c : s.toCharArray()) {

            if (c == 'A') {
                // Absent day found
                absents++;

                // Check if absences exceeded limit (>= 2 means not eligible)
                if (absents >= 2) return false;

                // Reset late streak (absence breaks consecutive lates)
                consecutiveLate = 0;

            } else if (c == 'L') {
                // Late day found
                consecutiveLate++;

                // Check if 3+ consecutive lates (not eligible)
                if (consecutiveLate >= 3) return false;

            } else {
                // Present day ('P')
                // Reset late streak (present breaks consecutive lates)
                consecutiveLate = 0;

            }

        }

        // Passed all checks - student is eligible for award
        return true;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "PPALLP"
 * 
 * Initial: absents = 0, consecutiveLate = 0
 * 
 * Day 1: 'P' → Present
 *        consecutiveLate = 0 (reset, was already 0)
 *        State: absents = 0, consecutiveLate = 0
 * 
 * Day 2: 'P' → Present
 *        consecutiveLate = 0
 *        State: absents = 0, consecutiveLate = 0
 * 
 * Day 3: 'A' → Absent
 *        absents = 1 (< 2, continue)
 *        consecutiveLate = 0 (reset)
 *        State: absents = 1, consecutiveLate = 0
 * 
 * Day 4: 'L' → Late
 *        consecutiveLate = 1 (< 3, continue)
 *        State: absents = 1, consecutiveLate = 1
 * 
 * Day 5: 'L' → Late
 *        consecutiveLate = 2 (< 3, continue)
 *        State: absents = 1, consecutiveLate = 2
 * 
 * Day 6: 'P' → Present
 *        consecutiveLate = 0 (reset)
 *        State: absents = 1, consecutiveLate = 0
 * 
 * Loop ends → return true (eligible!)
 * 
 * Output: true
 * 
 * 
 * Dry Run Example 2 (Failure Case):
 * 
 * Input: s = "PPALLL"
 * 
 * Day 1: 'P' → consecutiveLate = 0
 * Day 2: 'P' → consecutiveLate = 0
 * Day 3: 'A' → absents = 1, consecutiveLate = 0
 * Day 4: 'L' → consecutiveLate = 1
 * Day 5: 'L' → consecutiveLate = 2
 * Day 6: 'L' → consecutiveLate = 3 → >= 3 → return false!
 * 
 * Output: false
 * 
 * 
 * Alternative Approach (Using String methods):
 * 
 * public boolean checkRecord(String s) {
 *     // Count A's using replace trick
 *     int absences = s.length() - s.replace("A", "").length();
 *     
 *     // Check for "LLL" substring
 *     boolean hasThreeLates = s.contains("LLL");
 *     
 *     return absences < 2 && !hasThreeLates;
 * }
 * 
 * This is more concise but less efficient (multiple passes through string).
 * Your approach is better for performance!
 */

