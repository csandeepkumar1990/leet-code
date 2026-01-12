/*
 * 923. 3Sum With Multiplicity
 * 
 * Problem:
 * Given an integer array arr and an integer target, return the number of tuples
 * (i, j, k) where i < j < k and arr[i] + arr[j] + arr[k] == target.
 * Return the answer modulo 10^9 + 7.
 * 
 * Constraint: 0 <= arr[i] <= 100
 * 
 * Approach: Counting + Combinatorics
 * Since values are limited to 0-100, instead of iterating through indices O(n^3),
 * we count frequencies and iterate through values O(100^2).
 * 
 * Example 1: arr = [1,1,2,2,3,3,4,4,5,5], target = 8
 * 
 *   Frequencies: count[1]=2, count[2]=2, count[3]=2, count[4]=2, count[5]=2
 * 
 *   Valid (a,b,c) where a <= b <= c and a+b+c = 8:
 *   | a | b | c | Case          | Formula              | Count |
 *   |---|---|---|---------------|----------------------|-------|
 *   | 1 | 2 | 5 | all different | 2 * 2 * 2            |   8   |
 *   | 1 | 3 | 4 | all different | 2 * 2 * 2            |   8   |
 *   | 2 | 2 | 4 | a == b        | C(2,2) * 2 = 1 * 2   |   2   |
 *   | 2 | 3 | 3 | b == c        | 2 * C(2,2) = 2 * 1   |   2   |
 *   
 *   Total = 8 + 8 + 2 + 2 = 20
 * 
 * Example 2: arr = [1,1,2,2,2,2], target = 5
 * 
 *   Frequencies: count[1]=2, count[2]=4
 *   
 *   Valid: (1,2,2) -> b == c case
 *   count[1] * C(count[2],2) = 2 * (4*3/2) = 2 * 6 = 12
 * 
 * Example 3: arr = [2,2,2,2,2], target = 6
 * 
 *   Frequencies: count[2]=5
 *   
 *   Valid: (2,2,2) -> all same case
 *   C(5,3) = 5*4*3/6 = 10
 * 
 * Four Cases:
 *   1. a == b == c: Choose 3 from n -> C(n,3) = n(n-1)(n-2)/6
 *   2. a == b != c: Choose 2 from count[a], multiply by count[c]
 *   3. a != b == c: count[a] * choose 2 from count[b]
 *   4. a != b != c: Simple multiplication count[a] * count[b] * count[c]
 * 
 * Why a <= b <= c?
 *   Ensures we count each unique value combination once, then use
 *   combinatorics to account for all valid index arrangements.
 * 
 * Time Complexity: O(100^2) = O(1) - constant since values bounded by 100
 * Space Complexity: O(101) = O(1) - for the count array
 */
class Solution {

    public int threeSumMulti(int[] arr, int target) {

        long MOD = 1_000_000_007;

        // Count frequency of each number
        long[] count = new long[101];
        for (int num : arr) {
            count[num]++;
        }

        long result = 0;

        // Try all a <= b <= c
        for (int a = 0; a <= 100; a++) {
            for (int b = a; b <= 100; b++) {
                int c = target - a - b;
                if (c < 0 || c > 100 || c < b) continue;

                if (a == b && b == c) {
                    // All same: C(count[a], 3)
                    result += count[a] * (count[a] - 1) * (count[a] - 2) / 6;
                } 
                else if (a == b) {
                    // a == b != c: C(count[a], 2) * count[c]
                    result += count[a] * (count[a] - 1) / 2 * count[c];
                } 
                else if (b == c) {
                    // a != b == c: count[a] * C(count[b], 2)
                    result += count[a] * count[b] * (count[b] - 1) / 2;
                } 
                else {
                    // a != b != c
                    result += count[a] * count[b] * count[c];
                }

                result %= MOD;
            }
        }

        return (int) result;
    }
}

