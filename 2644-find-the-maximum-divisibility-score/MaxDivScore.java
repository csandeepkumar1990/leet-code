// 2644. Find the Maximum Divisibility Score
// Given two integer arrays nums and divisors, return the divisor with the maximum divisibility score.
// If there are ties, return the smallest divisor.

class Solution {

    public int maxDivScore(int[] nums, int[] divisors) {

        // Track the best score found and corresponding divisor
        int bestScore = -1;

        // Initialize with MAX_VALUE to handle tie-breaking (smaller divisor wins)
        int bestDivisor = Integer.MAX_VALUE;

        // Iterate through each divisor
        for (int d : divisors) {

            int score = 0;

            // Count how many numbers in nums are divisible by current divisor
            for (int num : nums) {

                if (num % d == 0) {

                    score++;

                }

            }

            // Update best if: higher score OR same score but smaller divisor
            if (score > bestScore || (score == bestScore && d < bestDivisor)) {

                bestScore = score;

                bestDivisor = d;

            }

        }

        return bestDivisor;

    }

}
