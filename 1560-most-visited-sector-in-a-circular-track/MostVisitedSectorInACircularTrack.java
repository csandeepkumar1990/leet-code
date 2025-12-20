/*
 * LeetCode 1560: Most Visited Sector in a Circular Track
 * 
 * Problem:
 * Given a circular track with n sectors (labeled 1 to n) and an array rounds
 * where rounds[i] represents the sector the marathon ends at after round i,
 * return the most visited sectors sorted in ascending order.
 * 
 * Approach: Only Start and End Matter!
 * 
 * Key Insight (Critical!):
 * - Middle rounds don't affect the answer!
 * - Only the START sector and END sector determine the result
 * - All sectors between start and end (inclusive) are visited one more time
 * 
 * Why? Every complete lap visits all sectors equally. Only the partial
 * lap from start to end creates the difference.
 * 
 * Algorithm:
 * 1. Get start = rounds[0] and end = rounds[last]
 * 2. If start <= end: result is [start, start+1, ..., end]
 * 3. If start > end: result is [1, ..., end] + [start, ..., n] (wrapped)
 * 
 * Time Complexity: O(n) - at most n sectors in result
 * Space Complexity: O(1) - excluding result list
 * 
 * Example 1: n = 4, rounds = [1, 3, 1, 2]
 * 
 *   Start = 1, End = 2
 *   
 *   Path visualization:
 *   Start at 1 → go to 3 → go to 1 (full lap) → go to 2
 *   
 *   Sector visits:
 *   1: visited at start, visited again on lap, visited again → 3 times
 *   2: visited once, visited on lap, visited at end → 3 times
 *   3: visited once, visited on lap → 2 times
 *   4: visited on lap → 1 time
 *   
 *   Most visited (3 times): [1, 2]
 *   
 *   Using formula: start=1 <= end=2 → [1, 2] ✓
 * 
 * Example 2: n = 4, rounds = [3, 1]
 * 
 *   Start = 3, End = 1
 *   
 *   Path: 3 → 4 → 1 (wrapped around)
 *   
 *   Visits: 3→1, 4→1, 1→1
 *   Most visited: [1, 3, 4]
 *   
 *   Using formula: start=3 > end=1 → [1] + [3, 4] = [1, 3, 4] ✓
 * 
 * Visual (Circular Track):
 * 
 *   Case 1: start <= end (no wrap)
 *   
 *        1 ← 2
 *        ↓   ↑
 *        4 → 3
 *   
 *   start=1, end=3: Sectors [1,2,3] visited extra
 * 
 *   Case 2: start > end (wraps around)
 *   
 *        1 ← 2
 *        ↓   ↑
 *        4 → 3
 *   
 *   start=3, end=1: Path goes 3→4→1
 *   Sectors [1] + [3,4] = [1,3,4] visited extra
 * 
 * Why Middle Rounds Don't Matter:
 * 
 *   rounds = [1, 3, 1, 2]
 *   
 *   Round 1: 1 → 3 (visits 1,2,3)
 *   Round 2: 3 → 1 (visits 3,4,1) ← full lap through all
 *   Round 3: 1 → 2 (visits 1,2)
 *   
 *   After any full lap, all sectors are visited equally.
 *   The EXTRA visits only come from the partial segment [start, end].
 * 
 * Why Output Must Be Sorted:
 * 
 *   Case 2 (wrap): We add [1..end] first, then [start..n]
 *   This naturally produces sorted output!
 *   
 *   start=3, end=1, n=4:
 *   [1] + [3, 4] = [1, 3, 4] ← already sorted!
 */

import java.util.ArrayList;
import java.util.List;

class Solution {

    public List<Integer> mostVisited(int n, int[] rounds) {

        List<Integer> result = new ArrayList<>();

        int start = rounds[0];

        int end = rounds[rounds.length - 1];

        if (start <= end) {

            // No wrap: simple range from start to end
            for (int i = start; i <= end; i++) {

                result.add(i);

            }

        } else {

            // Wrap around: [1..end] + [start..n]
            for (int i = 1; i <= end; i++) {

                result.add(i);

            }

            for (int i = start; i <= n; i++) {

                result.add(i);

            }

        }

        return result;

    }

}

