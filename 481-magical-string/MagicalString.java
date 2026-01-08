/*
 * LeetCode 481: Magical String
 * 
 * Problem:
 * A magical string s consists of only '1' and '2' and obeys the following rules:
 * - The string s is magical because concatenating the number of contiguous '1's and '2's
 *   in the string s generates the string s itself.
 * - The first few elements of s is s = "1221121221221121122..."
 * 
 * Given an integer n, return the number of '1's in the first n number in the magical string s.
 * 
 * Example:
 *   Input: n = 6
 *   Output: 3
 *   Explanation: The first 6 elements of magical string s is "122112" and it contains
 *   three 1's, so return 3.
 * 
 *   Input: n = 1
 *   Output: 1
 * 
 * Approach: Simulation with Two Pointers
 * 
 * Key Insight:
 * - The magical string is self-generating: each group describes the next group
 * - Start with "122" as the base
 * - Use a "head" pointer to read how many times to write the next number
 * - Use a "tail" pointer to write the numbers
 * - Alternate between 1 and 2
 * 
 * Algorithm:
 * 1. Initialize the array with base sequence: [1, 2, 2]
 * 2. Use head pointer to read the count (how many times to write next number)
 * 3. Use tail pointer to write the next numbers
 * 4. Alternate between 1 and 2
 * 5. Count the number of 1's as we build the sequence
 * 6. Stop when we've generated n characters
 * 
 * Time Complexity: O(n)
 *   - We generate at most n characters
 *   - Each character is written once
 *   - Total: O(n) where n is the input
 * 
 * Space Complexity: O(n)
 *   - Array to store the magical string: O(n)
 * 
 * Example Walkthrough:
 * 
 *   n = 6
 *   
 *   Initial state:
 *     a = [1, 2, 2, ...]
 *     head = 2 (pointing to the third element '2')
 *     tail = 3 (where to write next)
 *     num = 1 (next number to write)
 *     count = 1 (one '1' so far)
 *   
 *   Iteration 1:
 *     a[head] = a[2] = 2, so write '1' two times
 *     a[3] = 1, count++ → count = 2
 *     a[4] = 1, count++ → count = 3
 *     tail = 5, num = 3 - 1 = 2, head = 3
 *     a = [1, 2, 2, 1, 1, ...]
 *   
 *   Iteration 2:
 *     a[head] = a[3] = 1, so write '2' one time
 *     a[5] = 2
 *     tail = 6, num = 3 - 2 = 1, head = 4
 *     a = [1, 2, 2, 1, 1, 2, ...]
 *   
 *   tail = 6 >= n = 6, stop
 *   
 *   Result: count = 3
 *   Sequence: "122112" contains 3 ones
 * 
 * Visual Representation:
 * 
 *   Step-by-step generation:
 *   
 *   Start: "122"
 *          ↑
 *          head=2, tail=3
 *   
 *   Read a[2]=2, write '1' two times:
 *   "122" + "11" = "12211"
 *            ↑
 *            tail=5, head=3
 *   
 *   Read a[3]=1, write '2' one time:
 *   "12211" + "2" = "122112"
 *             ↑
 *             tail=6, head=4
 *   
 *   tail >= n, stop
 *   
 *   Result: "122112" has 3 ones
 * 
 * How the Magical String Works:
 * 
 *   The magical string is self-describing:
 *   
 *   "1221121221221121122..."
 *   
 *   Let's break it down:
 *   - Position 0: '1' means "write one '1'" → we get '1' at position 0
 *   - Position 1: '2' means "write two '2's" → we get '2', '2' at positions 1, 2
 *   - Position 2: '2' means "write two '1's" → we get '1', '1' at positions 3, 4
 *   - Position 3: '1' means "write one '2'" → we get '2' at position 5
 *   - Position 4: '1' means "write one '1'" → we get '1' at position 6
 *   - Position 5: '2' means "write two '2's" → we get '2', '2' at positions 7, 8
 *   - And so on...
 * 
 *   The pattern:
 *   - Read a number at position i
 *   - That number tells us how many times to write the next number (alternating 1 and 2)
 *   - The next number alternates: if we just wrote 1, next is 2; if we just wrote 2, next is 1
 * 
 * Why num = 3 - num Works:
 * 
 *   This is a clever way to alternate between 1 and 2:
 *   - If num = 1, then 3 - 1 = 2
 *   - If num = 2, then 3 - 2 = 1
 *   - This ensures we always alternate
 * 
 * Edge Cases:
 * 
 *   1. n = 0:
 *      Result: 0 (no characters)
 *   
 *   2. n = 1:
 *      Result: 1 (the first character is '1')
 *   
 *   3. n = 2:
 *      Sequence: "12"
 *      Result: 1 (one '1')
 *   
 *   4. n = 3:
 *      Sequence: "122"
 *      Result: 1 (one '1')
 *   
 *   5. n = 4:
 *      Sequence: "1221"
 *      Result: 2 (two '1's)
 * 
 * Algorithm Details:
 * 
 *   The algorithm uses two pointers:
 *   - head: Points to the position we're reading from (tells us how many times to write)
 *   - tail: Points to where we're writing next
 *   
 *   Process:
 *   1. Read a[head] to know how many times to write
 *   2. Write 'num' that many times (up to n characters)
 *   3. Count 1's as we write
 *   4. Switch num (1 ↔ 2)
 *   5. Move head forward
 *   6. Repeat until tail >= n
 */

class Solution {
    /**
     * Counts the number of '1's in the first n characters of the magical string.
     * 
     * @param n The number of characters to consider
     * @return The number of '1's in the first n characters
     * 
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public int magicalString(int n) {
        // Edge cases
        if (n <= 0) return 0;
        if (n <= 3) return 1; // For "122", there is only one '1'
        
        // Initialize array with base sequence "122"
        int[] a = new int[n + 1];
        a[0] = 1;
        a[1] = 2;
        a[2] = 2;
        
        int head = 2;  // The third element '2' tells us how many times to write the next num
        int tail = 3;  // Where the next numbers will be placed
        int num = 1;   // The next number to append (alternates 1 and 2)
        int count = 1; // Count of 1's (starting with the one at index 0)
        
        // Generate the magical string until we have n characters
        while (tail < n) {
            // How many times do we write the current 'num'?
            // a[head] tells us the count
            for (int i = 0; i < a[head]; i++) {
                if (tail < n) {
                    a[tail] = num;
                    if (num == 1) count++;  // Count 1's as we write
                    tail++;
                }
            }
            
            // Switch number (1 -> 2 or 2 -> 1)
            // This alternates between 1 and 2
            num = 3 - num;
            
            // Move head to the next position to read the next count
            head++;
        }
        
        return count;
    }
}

