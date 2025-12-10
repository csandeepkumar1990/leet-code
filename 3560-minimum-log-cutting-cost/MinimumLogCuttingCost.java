// 3560. Minimum Log Cutting Cost
// Find the minimum cost to cut logs so they fit in trucks of size k.
// Cutting cost is k * (excess length beyond k).
//
// Examples:
// Input: n = 5, m = 3, k = 5 -> Output: 0
//   Both dimensions <= k, no cutting needed
//
// Input: n = 10, m = 5, k = 6 -> Output: 24
//   n > k, need to cut. Cost = k * (n - k) = 6 * (10 - 6) = 24
//
// Input: n = 4, m = 12, k = 8 -> Output: 32
//   m > k, need to cut. Cost = k * (m - k) = 8 * (12 - 8) = 32
//
// Key Insight: Only the dimension exceeding k needs to be cut.
// Cut the longer log if it exceeds k, cost = k * (maxLog - k).
//
// Time Complexity: O(1) - constant operations
// Space Complexity: O(1) - only using constant extra space

class Solution {

    public long minCuttingCost(int n, int m, int k) {

        // If both logs fit in trucks, no cutting needed

        if (n <= k && m <= k)

            return 0;

        // Cut the longer log optimally

        int maxLog = Math.max(n, m);

        return (long) k * (maxLog - k);

    }

}

