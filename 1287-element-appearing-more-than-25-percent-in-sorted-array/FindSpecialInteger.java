/*
 * LeetCode 1287: Element Appearing More Than 25% In Sorted Array
 * 
 * Problem:
 * Given an integer array sorted in non-decreasing order, there is exactly one
 * integer in the array that occurs more than 25% of the time, return that integer.
 * 
 * Approach: Count Consecutive Occurrences
 * 
 * Key Insight:
 * - Since array is sorted, same elements appear consecutively
 * - We can count consecutive occurrences as we iterate
 * - If count exceeds n/4, we found the special integer
 * - Problem guarantees exactly one such element exists
 * 
 * Algorithm:
 * 1. Initialize count = 1 (first element is already counted)
 * 2. Iterate from index 1 to n-1:
 *    - If current element equals previous: increment count
 *      - If count > n/4: return current element
 *    - Else: reset count to 1 (new number encountered)
 * 3. Return arr[0] as fallback (shouldn't reach here due to constraints)
 * 
 * Time Complexity: O(n)
 *   - Single pass through the array
 * 
 * Space Complexity: O(1)
 *   - Only using constant extra space
 * 
 * Example 1:
 *   arr = [1, 2, 2, 6, 6, 6, 6, 7, 10]
 *   n = 9, threshold = 9/4 = 2.25, so need count > 2
 *   
 *   i=0: arr[0]=1, count=1
 *   i=1: arr[1]=2, arr[0]=1, different → count=1
 *   i=2: arr[2]=2, arr[1]=2, same → count=2
 *   i=3: arr[3]=6, arr[2]=2, different → count=1
 *   i=4: arr[4]=6, arr[3]=6, same → count=2
 *   i=5: arr[5]=6, arr[4]=6, same → count=3 > 2 ✓
 *   
 *   Result: 6
 * 
 * Example 2:
 *   arr = [1, 1]
 *   n = 2, threshold = 2/4 = 0.5, so need count > 0
 *   
 *   i=0: arr[0]=1, count=1
 *   i=1: arr[1]=1, arr[0]=1, same → count=2 > 0 ✓
 *   
 *   Result: 1
 * 
 * Example 3:
 *   arr = [1, 2, 3, 3, 3, 3, 3, 3, 3, 3, 4, 5]
 *   n = 12, threshold = 12/4 = 3, so need count > 3
 *   
 *   i=0: arr[0]=1, count=1
 *   i=1: arr[1]=2, different → count=1
 *   i=2: arr[2]=3, different → count=1
 *   i=3: arr[3]=3, same → count=2
 *   i=4: arr[4]=3, same → count=3
 *   i=5: arr[5]=3, same → count=4 > 3 ✓
 *   
 *   Result: 3
 * 
 * Why This Works?
 * 
 *   - Array is sorted, so identical elements are grouped together
 *   - We count consecutive occurrences efficiently
 *   - As soon as count exceeds n/4, we can return immediately
 *   - No need to count all occurrences or use extra data structures
 * 
 * Why Start with count = 1?
 * 
 *   - First element (arr[0]) is already "counted" when we start
 *   - We begin iteration from index 1
 *   - This avoids off-by-one errors
 * 
 * Alternative Approach (Using Sorted Property):
 * 
 *   Since array is sorted, we can check if arr[i] == arr[i + n/4]
 *   If they're equal, arr[i] appears more than 25% of the time.
 *   
 *   This approach is also O(n) but may be slightly more efficient
 *   as it doesn't need to count all occurrences.
 * 
 * Visual Example (arr = [1, 2, 2, 6, 6, 6, 6, 7, 10]):
 * 
 *   Index:  0  1  2  3  4  5  6  7  8
 *   Value:  1  2  2  6  6  6  6  7  10
 *   
 *   Iteration:
 *   i=1: 2 != 1 → count=1 (for 2)
 *   i=2: 2 == 2 → count=2 (for 2)
 *   i=3: 6 != 2 → count=1 (for 6)
 *   i=4: 6 == 6 → count=2 (for 6)
 *   i=5: 6 == 6 → count=3 > 2 ✓ Return 6
 * 
 * Edge Cases:
 * 
 *   - Single element: arr = [1] → count=1 > 0, returns 1
 *   - All same: arr = [5, 5, 5, 5] → count=4 > 1, returns 5
 *   - Element at start: arr = [1, 1, 1, 2, 3] → count=3 > 1, returns 1
 */

class Solution {

    public int findSpecialInteger(int[] arr) {

        int n = arr.length;

        int count = 1; // Start with first element counted

        for (int i = 1; i < n; i++) {

            if (arr[i] == arr[i - 1]) {

                count++;

                // Check if count exceeds 25% threshold
                if (count > n / 4) {

                    return arr[i];

                }

            } else {

                count = 1; // Reset for new number

            }

        }

        return arr[0]; // Fallback (problem guarantees one exists)

    }

}

