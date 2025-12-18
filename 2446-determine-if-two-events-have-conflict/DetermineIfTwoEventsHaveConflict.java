/*
 * LeetCode 2446: Determine if Two Events Have Conflict
 * 
 * Problem:
 * You are given two arrays of strings event1 and event2 representing two events
 * where: event[0] = start time, event[1] = end time (in "HH:MM" 24-hour format).
 * Return true if there is a conflict between the two events, false otherwise.
 * A conflict occurs when both events are happening at the same time (overlapping).
 * 
 * Approach: String Comparison for Interval Overlap
 * 
 * Key Insight:
 * - "HH:MM" format allows direct string comparison (lexicographic = chronological)
 * - "09:00" < "10:00" < "23:59" works correctly with compareTo()
 * - Two intervals overlap if: start1 <= end2 AND start2 <= end1
 * - Equivalently, NO overlap if: end1 < start2 OR end2 < start1
 * 
 * Algorithm:
 * 1. Extract start and end times from both events
 * 2. Check overlap condition using string comparison
 * 3. Return true if both conditions are satisfied (overlap exists)
 * 
 * Time Complexity: O(1) - fixed-length string comparisons
 * Space Complexity: O(1) - only storing references
 * 
 * Overlap Logic Explained:
 * 
 *   Two intervals [s1, e1] and [s2, e2] overlap if and only if:
 *   - Event1 starts before Event2 ends: s1 <= e2
 *   - Event2 starts before Event1 ends: s2 <= e1
 * 
 *   Both conditions must be true for an overlap.
 * 
 * Example 1: event1 = ["01:15","02:00"], event2 = ["02:00","03:00"]
 * 
 *   Timeline:
 *   01:00  01:15  02:00  03:00
 *     |      |======|      |
 *     |      event1 |      |
 *     |             |======|
 *     |             event2 |
 *                   ↑
 *             Touch point (overlap!)
 * 
 *   Check: "01:15" <= "03:00" ✓ AND "02:00" <= "02:00" ✓
 *   Result: true (they share the moment 02:00)
 * 
 * Example 2: event1 = ["01:00","02:00"], event2 = ["03:00","04:00"]
 * 
 *   Timeline:
 *   01:00  02:00  03:00  04:00
 *     |======|      |======|
 *     event1        event2
 *            ↑      ↑
 *          gap (no overlap)
 * 
 *   Check: "01:00" <= "04:00" ✓ BUT "03:00" <= "02:00" ✗
 *   Result: false
 * 
 * Example 3: event1 = ["10:00","12:00"], event2 = ["11:00","13:00"]
 * 
 *   Timeline:
 *   10:00  11:00  12:00  13:00
 *     |===========|
 *     |   event1  |======|
 *            |   event2  |
 *            |====|
 *            overlap!
 * 
 *   Check: "10:00" <= "13:00" ✓ AND "11:00" <= "12:00" ✓
 *   Result: true
 */

class Solution {

    public boolean haveConflict(String[] event1, String[] event2) {

        String start1 = event1[0];

        String end1 = event1[1];

        String start2 = event2[0];

        String end2 = event2[1];

        // If event1 starts before event2 ends AND event2 starts before event1 ends, they overlap
        return start1.compareTo(end2) <= 0 && start2.compareTo(end1) <= 0;

    }

}

