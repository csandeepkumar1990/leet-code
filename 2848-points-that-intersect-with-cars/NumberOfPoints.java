/*
 * LeetCode 2848: Points That Intersect With Cars
 * 
 * Problem:
 * You are given a 0-indexed 2D integer array nums representing the coordinates of cars
 * parked on a number line. For any index i, nums[i] = [start_i, end_i] where start_i is
 * the starting point of the i-th car and end_i is the ending point of the i-th car.
 * 
 * Return the number of integer points on the line that are covered with any part of a car.
 * 
 * Approach: Difference Array (Sweep Line Algorithm)
 * 
 * Key Insight:
 * - Use a difference array to mark where coverage starts and ends
 * - For each car [start, end], mark start with +1 and end+1 with -1
 * - Compute running sum to determine which points are covered
 * - A point is covered if the running sum > 0 at that position
 * 
 * Algorithm:
 * 1. Create a difference array to track coverage changes
 * 2. For each car [start, end]:
 *    - Increment count[start] (coverage starts here)
 *    - Decrement count[end + 1] (coverage ends after this point)
 * 3. Compute running sum through the array
 * 4. Count positions where running sum > 0 (covered by at least one car)
 * 5. Return the count
 * 
 * Time Complexity: O(n + MAX)
 *   - n = number of cars
 *   - MAX = maximum possible point (100 in constraints)
 *   - Process each car: O(n)
 *   - Sweep through difference array: O(MAX)
 *   - Total: O(n + MAX) = O(MAX) since MAX is constant
 * 
 * Space Complexity: O(MAX)
 *   - Difference array of size MAX + 2
 *   - MAX = 100 (constant)
 * 
 * Example:
 * 
 *   Input: nums = [[3,6],[1,5],[4,7]]
 * 
 *   Step 1: Build difference array
 *     Car [3,6]: count[3]++, count[7]--
 *     Car [1,5]: count[1]++, count[6]--
 *     Car [4,7]: count[4]++, count[8]--
 * 
 *     count array:
 *       index: 0  1  2  3  4  5  6  7  8  9 ...
 *       value: 0  1  0  1  1  0 -1 -1 -1  0 ...
 * 
 *   Step 2: Compute running sum
 *     i=0: runningSum=0, covered=0
 *     i=1: runningSum=0+1=1 > 0 → covered=1 (point 1 covered)
 *     i=2: runningSum=1+0=1 > 0 → covered=2 (point 2 covered)
 *     i=3: runningSum=1+1=2 > 0 → covered=3 (point 3 covered)
 *     i=4: runningSum=2+1=3 > 0 → covered=4 (point 4 covered)
 *     i=5: runningSum=3+0=3 > 0 → covered=5 (point 5 covered)
 *     i=6: runningSum=3-1=2 > 0 → covered=6 (point 6 covered)
 *     i=7: runningSum=2-1=1 > 0 → covered=7 (point 7 covered)
 *     i=8: runningSum=1-1=0 → not covered
 * 
 *   Result: 7 (points 1, 2, 3, 4, 5, 6, 7 are covered)
 * 
 * Visual Representation:
 * 
 *   Number line:  0  1  2  3  4  5  6  7  8  9
 * 
 *   Car [3,6]:              [====]
 *   Car [1,5]:         [========]
 *   Car [4,7]:              [=======]
 * 
 *   Coverage:         [===============]
 *                     1  2  3  4  5  6  7
 * 
 *   Covered points: 1, 2, 3, 4, 5, 6, 7 → 7 points
 * 
 * Another Example:
 * 
 *   Input: nums = [[1,3],[5,8]]
 * 
 *   Step 1: Build difference array
 *     Car [1,3]: count[1]++, count[4]--
 *     Car [5,8]: count[5]++, count[9]--
 * 
 *     count array:
 *       index: 0  1  2  3  4  5  6  7  8  9 ...
 *       value: 0  1  0  0 -1  1  0  0  0 -1 ...
 * 
 *   Step 2: Compute running sum
 *     i=1: runningSum=1 > 0 → covered=1
 *     i=2: runningSum=1 > 0 → covered=2
 *     i=3: runningSum=1 > 0 → covered=3
 *     i=4: runningSum=0 → not covered
 *     i=5: runningSum=1 > 0 → covered=4
 *     i=6: runningSum=1 > 0 → covered=5
 *     i=7: runningSum=1 > 0 → covered=6
 *     i=8: runningSum=1 > 0 → covered=7
 * 
 *   Result: 7 (points 1, 2, 3, 5, 6, 7, 8)
 * 
 * Why Difference Array?
 * 
 *   The difference array technique efficiently tracks intervals:
 *   - Instead of marking every point in [start, end] individually (O(n * range))
 *   - We mark only the boundaries: start and end+1 (O(n))
 *   - Then compute coverage with a single sweep (O(MAX))
 *   - Much more efficient for large ranges
 * 
 * How It Works:
 * 
 *   For a car covering [start, end]:
 *   - count[start]++ means "coverage increases by 1 starting here"
 *   - count[end+1]-- means "coverage decreases by 1 after this point"
 * 
 *   When we compute running sum:
 *   - runningSum tells us how many cars cover the current point
 *   - If runningSum > 0, at least one car covers this point
 *   - If runningSum == 0, no car covers this point
 * 
 * Edge Cases:
 * 
 * 1. Single car:
 *    nums = [[1,5]]
 *    Covered: 1, 2, 3, 4, 5 → 5 points
 * 
 * 2. Overlapping cars:
 *    nums = [[1,3],[2,4]]
 *    Covered: 1, 2, 3, 4 → 4 points
 *    (Point 2 and 3 are covered by both cars)
 * 
 * 3. Adjacent cars (no overlap):
 *    nums = [[1,2],[3,4]]
 *    Covered: 1, 2, 3, 4 → 4 points
 * 
 * 4. Nested cars:
 *    nums = [[1,5],[2,4]]
 *    Covered: 1, 2, 3, 4, 5 → 5 points
 *    (Car [2,4] is completely inside car [1,5])
 * 
 * 5. Single point cars:
 *    nums = [[1,1],[2,2],[3,3]]
 *    Covered: 1, 2, 3 → 3 points
 * 
 * 6. All cars at same position:
 *    nums = [[5,5],[5,5],[5,5]]
 *    Covered: 5 → 1 point
 *    (All cars cover the same point)
 * 
 * Alternative Approach (Using Set):
 * 
 *   Could use a Set to track all covered points:
 *   ```java
 *   Set<Integer> covered = new HashSet<>();
 *   for (int[] car : nums) {
 *       for (int i = car[0]; i <= car[1]; i++) {
 *           covered.add(i);
 *       }
 *   }
 *   return covered.size();
 *   ```
 *   Time: O(n * range), Space: O(range)
 *   Less efficient for large ranges, but simpler to understand.
 * 
 * Why end+1?
 * 
 *   We decrement at end+1 (not end) because:
 *   - The car covers all points from start to end (inclusive)
 *   - Point 'end' is still covered
 *   - Point 'end+1' is the first point NOT covered
 *   - So we mark the end of coverage at end+1
 */

import java.util.List;

class Solution {
    /**
     * Counts the number of integer points covered by at least one car.
     * 
     * @param nums List of lists where nums.get(i) = [start_i, end_i] represents a car
     * @return The number of unique integer points covered by any car
     * 
     * Time Complexity: O(n + MAX) where n is number of cars, MAX is max point value
     * Space Complexity: O(MAX)
     */
    public int numberOfPoints(List<List<Integer>> nums) {
        // Maximum possible point value (from constraints: 1 <= end_i <= 100)
        final int MAX = 100;
        
        // Difference array to track coverage changes
        // Size MAX + 2 to handle end+1 safely
        int[] count = new int[MAX + 2];

        // Mark the start and end+1 positions for each car
        for (List<Integer> car : nums) {
            int start = car.get(0);
            int end = car.get(1);
            
            // Mark start of coverage: increment at start position
            count[start]++;
            
            // Mark end of coverage: decrement at end+1 position
            // (end+1 because the car covers points from start to end inclusive)
            count[end + 1]--;
        }

        // Compute the running sum to determine covered points
        int coveredPoints = 0;
        int runningSum = 0;
        
        // Iterate through all possible points (1 to MAX, inclusive)
        for (int i = 1; i <= MAX; i++) {
            // Update running sum with the change at position i
            runningSum += count[i];
            
            // If running sum > 0, at least one car covers this point
            if (runningSum > 0) {
                coveredPoints++;
            }
        }

        return coveredPoints;
    }
}

