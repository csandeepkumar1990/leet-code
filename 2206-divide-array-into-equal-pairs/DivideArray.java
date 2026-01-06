/*
 * LeetCode 2206: Divide Array Into Equal Pairs
 * 
 * Problem:
 * You are given an integer array nums consisting of 2 * n integers.
 * 
 * You need to divide nums into n pairs such that:
 * - Each element belongs to exactly one pair
 * - The elements present in a pair are equal
 * 
 * Return true if you can divide nums into n pairs, otherwise return false.
 * 
 * Approach: Frequency Counting with Even Check
 * 
 * Key Insight:
 * - To form pairs where both elements are equal, each number must appear an even number of times
 * - If any number appears an odd number of times, we cannot pair all occurrences
 * - Count frequency of each number, then check if all frequencies are even
 * 
 * Algorithm:
 * 1. Count frequency of each number using HashMap
 * 2. Check if all frequencies are even (divisible by 2)
 * 3. Return true if all are even, false otherwise
 * 
 * Time Complexity: O(n)
 *   - Single pass to count frequencies: O(n)
 *   - Single pass to check frequencies: O(k) where k is number of unique elements
 *   - Total: O(n) since k <= n
 * 
 * Space Complexity: O(n)
 *   - HashMap stores frequency of each unique number
 *   - In worst case, all numbers are unique: O(n)
 * 
 * Example:
 * 
 *   Input: nums = [3, 2, 3, 2, 2, 2]
 * 
 *   Step 1: Count frequencies
 *     3: 2 occurrences
 *     2: 4 occurrences
 * 
 *   Step 2: Check if all frequencies are even
 *     3: 2 % 2 = 0 ✓ (even)
 *     2: 4 % 2 = 0 ✓ (even)
 * 
 *   Result: true
 *   Pairs: (3, 3), (2, 2), (2, 2)
 * 
 * Another Example:
 * 
 *   Input: nums = [1, 2, 3, 4]
 * 
 *   Step 1: Count frequencies
 *     1: 1 occurrence
 *     2: 1 occurrence
 *     3: 1 occurrence
 *     4: 1 occurrence
 * 
 *   Step 2: Check if all frequencies are even
 *     1: 1 % 2 = 1 ✗ (odd)
 *     2: 1 % 2 = 1 ✗ (odd)
 *     3: 1 % 2 = 1 ✗ (odd)
 *     4: 1 % 2 = 1 ✗ (odd)
 * 
 *   Result: false
 *   Cannot form pairs: each number appears only once
 * 
 * Visual Representation:
 * 
 *   nums = [3, 2, 3, 2, 2, 2]
 * 
 *   Frequency map:
 *     3 → 2
 *     2 → 4
 * 
 *   Pairing:
 *     [3, 3]  ← pair 1
 *     [2, 2]  ← pair 2
 *     [2, 2]  ← pair 3
 * 
 *   All numbers can be paired ✓
 * 
 *   nums = [1, 2, 3, 4]
 * 
 *   Frequency map:
 *     1 → 1
 *     2 → 1
 *     3 → 1
 *     4 → 1
 * 
 *   Cannot form pairs: each number appears once (odd count) ✗
 * 
 * Edge Cases:
 * 
 * 1. All same numbers (even count):
 *    nums = [1, 1, 1, 1]
 *    Frequency: 1 → 4 (even)
 *    Result: true
 *    Pairs: (1, 1), (1, 1)
 * 
 * 2. All same numbers (odd count):
 *    nums = [1, 1, 1]
 *    Frequency: 1 → 3 (odd)
 *    Result: false
 *    Cannot pair all: one 1 will be left unpaired
 * 
 * 3. All different numbers:
 *    nums = [1, 2, 3, 4, 5, 6]
 *    All frequencies are 1 (odd)
 *    Result: false
 * 
 * 4. Mixed even and odd:
 *    nums = [1, 1, 2, 2, 3]
 *    Frequencies: 1→2 (even), 2→2 (even), 3→1 (odd)
 *    Result: false
 *    Cannot pair 3 (appears once)
 * 
 * 5. Empty array:
 *    nums = []
 *    No frequencies to check
 *    Result: true (0 pairs can be formed)
 * 
 * 6. Two elements (same):
 *    nums = [5, 5]
 *    Frequency: 5 → 2 (even)
 *    Result: true
 *    Pair: (5, 5)
 * 
 * 7. Two elements (different):
 *    nums = [5, 6]
 *    Frequencies: 5→1 (odd), 6→1 (odd)
 *    Result: false
 * 
 * Why Check for Even Frequencies?
 * 
 *   To form pairs where both elements are equal:
 *   - Each number must appear at least twice
 *   - If a number appears 2k times, we can form k pairs
 *   - If a number appears 2k+1 times, we can form k pairs but one is left unpaired
 *   - Therefore, all frequencies must be even for successful pairing
 * 
 * Mathematical Proof:
 * 
 *   Let f(x) be the frequency of number x.
 *   To form pairs, we need: f(x) = 2k for some integer k ≥ 0
 *   This means: f(x) % 2 = 0 (even)
 * 
 *   If any f(x) % 2 ≠ 0, then f(x) = 2k + 1 for some k
 *   This means we can form k pairs, leaving 1 unpaired element
 *   Therefore, we cannot divide the array into n pairs
 * 
 * Alternative Approach (Using Set):
 * 
 *   We could use a Set to toggle presence:
 *   ```java
 *   Set<Integer> set = new HashSet<>();
 *   for (int num : nums) {
 *       if (set.contains(num)) {
 *           set.remove(num);
 *       } else {
 *           set.add(num);
 *       }
 *   }
 *   return set.isEmpty();
 *   ```
 *   This works because:
 *   - Adding and removing cancels out pairs
 *   - If all numbers can be paired, set will be empty
 *   - If any number appears odd times, it will remain in the set
 *   Time: O(n), Space: O(n)
 * 
 * Why HashMap Approach?
 * 
 *   - More explicit: clearly shows frequency counting
 *   - Easier to understand the logic
 *   - Can be extended to return which numbers have odd frequencies
 *   - Set approach is more space-efficient in some cases but less intuitive
 */

import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * Determines if the array can be divided into n pairs where each pair contains equal elements.
     * 
     * @param nums The input array of integers (length is 2 * n)
     * @return true if array can be divided into n equal pairs, false otherwise
     * 
     * Time Complexity: O(n) where n is the length of nums
     * Space Complexity: O(n) for the frequency map
     */
    public boolean divideArray(int[] nums) {
        // Count frequency of each number
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            // Increment frequency count for each number
            // getOrDefault returns 0 if key doesn't exist, otherwise returns current value
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // Check if all frequencies are even
        // If any frequency is odd, we cannot pair all occurrences of that number
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            if (entry.getValue() % 2 != 0) {
                // Found a number with odd frequency, cannot form pairs
                return false;
            }
        }

        // All frequencies are even, can form pairs
        return true;
    }
}

