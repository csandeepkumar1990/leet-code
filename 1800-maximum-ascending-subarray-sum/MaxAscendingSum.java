// 1800. Maximum Ascending Subarray Sum
// Return the maximum sum of an ascending subarray in nums.
// An ascending subarray has strictly increasing consecutive elements.
//
// Examples:
// Input: [10,20,30,5,10,50] -> Output: 65 (subarray [5,10,50] = 65)
// Input: [10,20,30,40,50]   -> Output: 150 (entire array is ascending)
// Input: [12,17,15,13,10,11,12] -> Output: 33 (subarray [10,11,12] = 33)
//
// Time Complexity: O(n) - single pass through array
// Space Complexity: O(1) - only using a few variables

class Solution {

    public int maxAscendingSum(int[] nums) {

        if (nums == null || nums.length == 0) {

            return 0;

        }

        int maxSum = nums[0]; // Initialize maxSum with the first element

        int currentSum = nums[0]; // Initialize currentSum with the first element

        for (int i = 1; i < nums.length; i++) {

            if (nums[i] > nums[i - 1]) {

                // If the current element is greater than the previous, extend the ascending subarray
                currentSum += nums[i];

            } else {

                // If not, the ascending subarray breaks, reset currentSum
                currentSum = nums[i];

            }

            // Update maxSum if currentSum is greater
            maxSum = Math.max(maxSum, currentSum);

        }

        return maxSum;

    }

}

