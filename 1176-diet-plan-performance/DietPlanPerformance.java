/**
 * LeetCode 1176: Diet Plan Performance
 * 
 * For every k consecutive days, if total calories T:
 * - T < lower → lose 1 point
 * - T > upper → gain 1 point
 * - else → no change
 * 
 * Return total points. Uses sliding window for O(n) time.
 */

class Solution {

    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int points = 0;
        int n = calories.length;

        // Calculate sum of first k days (initial window)
        int windowSum = 0;
        for (int i = 0; i < k; i++) {
            windowSum += calories[i];
        }

        // Evaluate first window
        if (windowSum < lower) points--;
        else if (windowSum > upper) points++;

        // Slide window: remove old element, add new element
        for (int i = k; i < n; i++) {
            windowSum += calories[i] - calories[i - k];

            // Evaluate current window
            if (windowSum < lower) points--;
            else if (windowSum > upper) points++;
        }

        return points;
    }
}
