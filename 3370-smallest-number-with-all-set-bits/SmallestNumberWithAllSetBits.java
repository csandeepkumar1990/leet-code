// 3370. Smallest Number With All Set Bits
// Find the smallest number >= n where all bits are set to 1.
// Numbers with all set bits are: 1, 3, 7, 15, 31, ... (2^k - 1)
//
// Examples:
// Input: n = 5 -> Output: 7
//   5 in binary = 101, smallest all-1s number >= 5 is 7 (111)
//
// Input: n = 10 -> Output: 15
//   10 in binary = 1010, smallest all-1s number >= 10 is 15 (1111)
//
// Input: n = 3 -> Output: 3
//   3 in binary = 11, already all 1s
//
// Key Insight: Find the smallest power of 2 greater than n, then subtract 1.
// This gives a number with all bits set that covers n's bit length.
//
// Time Complexity: O(log n) - number of bit shifts needed
// Space Complexity: O(1) - only using constant extra space

class Solution {

    public int smallestNumber(int n) {

        int x = 1;

        while (x - 1 < n) {

            x <<= 1; // x = x * 2

        }

        return x - 1;

    }

}

