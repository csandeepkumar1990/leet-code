/*
 * 3707. Score Balance
 * 
 * Problem:
 * Given a string s consisting of lowercase English letters, determine if it
 * can be split into two non-empty parts such that the sum of character scores
 * in both parts are equal.
 * 
 * Character score: 'a' = 1, 'b' = 2, 'c' = 3, ..., 'z' = 26
 * 
 * Return true if such a split exists, false otherwise.
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: s = "ab"
 *   Output: false
 *   Explanation:
 *     Only split: "a" | "b"
 *     Left score: 1 (a=1)
 *     Right score: 2 (b=2)
 *     1 ≠ 2 → false
 * 
 * Example 2:
 *   Input: s = "aa"
 *   Output: true
 *   Explanation:
 *     Split: "a" | "a"
 *     Left score: 1
 *     Right score: 1
 *     1 = 1 → true
 * 
 * Example 3:
 *   Input: s = "aabb"
 *   Output: true
 *   Explanation:
 *     Split: "aa" | "bb"
 *     Left score: 1 + 1 = 2
 *     Right score: 2 + 2 = 4
 *     2 ≠ 4 → try next split
 *     
 *     Split: "aab" | "b"
 *     Left score: 1 + 1 + 2 = 4
 *     Right score: 2
 *     4 ≠ 2 → false (no valid split)
 *     
 *     Actually let's check all:
 *     "a"|"abb": 1 vs 5 ✗
 *     "aa"|"bb": 2 vs 4 ✗
 *     "aab"|"b": 4 vs 2 ✗
 *     → false
 * 
 * Example 4:
 *   Input: s = "abba"
 *   Output: true
 *   Explanation:
 *     Split: "ab" | "ba"
 *     Left: 1 + 2 = 3
 *     Right: 2 + 1 = 3
 *     3 = 3 → true
 * 
 * Example 5:
 *   Input: s = "abc"
 *   Output: true
 *   Explanation:
 *     Total = 1 + 2 + 3 = 6
 *     Split: "a"|"bc": 1 vs 5 ✗
 *     Split: "ab"|"c": 3 vs 3 ✓ → true
 * 
 * Constraints:
 *   - 2 <= s.length <= 10^5
 *   - s consists of lowercase English letters only
 * 
 * Approach: Prefix Sum with Total Precomputation
 * 
 * Key Insight: Instead of calculating both parts for each split,
 * precompute total and use: suffix = total - prefix
 * 
 * Algorithm:
 * 1. Calculate total score of entire string
 * 2. Iterate through possible split points (indices 0 to n-2)
 * 3. Maintain running prefix sum
 * 4. At each split: suffix = total - prefix
 * 5. If prefix == suffix, return true
 * 6. If no valid split found, return false
 * 
 * Why n-2? Both parts must be non-empty:
 *   - Split after index i means left = s[0..i], right = s[i+1..n-1]
 *   - i ranges from 0 to n-2 (ensures right part has at least 1 char)
 * 
 * Time Complexity: O(n)
 *   - O(n) to calculate total
 *   - O(n) to check all split points
 * 
 * Space Complexity: O(1)
 *   - Only a few integer variables
 */

class Solution {

    public boolean scoreBalance(String s) {

        int n = s.length();
        int total = 0;

        // Step 1: Calculate total score of entire string
        // Score: 'a'=1, 'b'=2, ..., 'z'=26
        // Formula: (c - 'a' + 1) converts char to score
        for (char c : s.toCharArray()) {

            total += (c - 'a' + 1);

        }

        int prefix = 0;

        // Step 2: Try splitting at every valid index
        // Split after index i: left = s[0..i], right = s[i+1..n-1]
        // Loop until n-2 to ensure right part is non-empty
        for (int i = 0; i < n - 1; i++) {

            // Add current character's score to prefix
            prefix += (s.charAt(i) - 'a' + 1);

            // Calculate suffix score (remaining part)
            int suffix = total - prefix;

            // Check if both parts have equal score
            if (prefix == suffix) {

                return true; // Found valid split!

            }

        }

        // No valid split found
        return false;

    }

}

/*
 * Dry Run Example:
 * 
 * Input: s = "abc"
 * 
 * Step 1: Calculate total
 *   'a' = 1, 'b' = 2, 'c' = 3
 *   total = 1 + 2 + 3 = 6
 * 
 * Step 2: Try splits
 *   Initial: prefix = 0
 *   
 *   i = 0: s[0] = 'a'
 *          prefix = 0 + 1 = 1
 *          suffix = 6 - 1 = 5
 *          1 ≠ 5 → continue
 *          Split: "a" | "bc" (1 vs 5)
 *   
 *   i = 1: s[1] = 'b'
 *          prefix = 1 + 2 = 3
 *          suffix = 6 - 3 = 3
 *          3 == 3 → return true!
 *          Split: "ab" | "c" (3 vs 3) ✓
 * 
 * Output: true
 * 
 * 
 * Character Score Formula:
 * 
 * c - 'a' + 1:
 *   'a' - 'a' + 1 = 0 + 1 = 1
 *   'b' - 'a' + 1 = 1 + 1 = 2
 *   'c' - 'a' + 1 = 2 + 1 = 3
 *   ...
 *   'z' - 'a' + 1 = 25 + 1 = 26
 * 
 * 
 * Visualization:
 * 
 * String: "abba"
 * Scores:  1 2 2 1
 * Total = 6
 * 
 * Split point 0: [a] | [bba]  →  1 vs 5  ✗
 *                 ↑     ↑
 *               prefix suffix
 * 
 * Split point 1: [ab] | [ba]  →  3 vs 3  ✓
 *                 ↑      ↑
 *               prefix suffix
 * 
 * 
 * Why This Approach is Efficient:
 * 
 * Naive approach: For each split, calculate both sums = O(n²)
 * 
 * Optimized approach:
 *   - Precompute total: O(n)
 *   - Running prefix sum: O(1) per iteration
 *   - Suffix = total - prefix: O(1)
 *   - Total: O(n)
 * 
 * 
 * Edge Cases:
 * 
 * Case 1: Minimum length (n=2)
 *   s = "aa" → "a"|"a" = 1 vs 1 → true
 *   s = "ab" → "a"|"b" = 1 vs 2 → false
 * 
 * Case 2: Odd total
 *   s = "ab" → total = 3 (odd)
 *   Can't split into two equal integer parts!
 *   (Though we still check all splits)
 * 
 * Case 3: All same characters
 *   s = "aaaa" → total = 4
 *   "aa"|"aa" = 2 vs 2 → true
 * 
 * 
 * Optimization Tip:
 * 
 * If total is odd, we can return false immediately:
 * 
 * if (total % 2 != 0) return false;
 * 
 * Because equal split requires total/2 for each part,
 * which is only possible if total is even.
 */

