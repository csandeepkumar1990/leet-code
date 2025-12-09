/**
 * LeetCode 3206: Alternating Groups I
 * 
 * Circular array of colors. Count tiles where both neighbors
 * are different color (alternating triplet centered at i).
 * Uses modulo for circular indexing.
 */

class Solution {

    public int numberOfAlternatingGroups(int[] colors) {
        int n = colors.length;
        if (n < 3) return 0;  // no triplet possible

        int count = 0;

        for (int i = 0; i < n; i++) {
            // Circular indexing: (i-1+n)%n wraps around, (i+1)%n wraps around
            int prev = colors[(i - 1 + n) % n];
            int next = colors[(i + 1) % n];

            // Check if current tile differs from both neighbors
            if (colors[i] != prev && colors[i] != next) {
                count++;
            }
        }

        return count;
    }
}

