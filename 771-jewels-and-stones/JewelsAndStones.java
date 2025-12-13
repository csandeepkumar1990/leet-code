/*
 * LeetCode 771: Jewels and Stones
 * 
 * Problem:
 * You're given strings jewels (types of stones that are jewels) and 
 * stones (the stones you have). Each character in stones is a type of stone.
 * Count how many of your stones are also jewels.
 * 
 * Note: Letters are case-sensitive ('a' and 'A' are different types).
 * 
 * Approach: HashSet for O(1) Lookup
 * 
 * Key Insight:
 * - For each stone, we need to check if it's a jewel
 * - Using a HashSet gives O(1) lookup time
 * - Much faster than scanning jewels string each time (O(n×m) vs O(n+m))
 * 
 * Algorithm:
 * 1. Add all jewel types to a HashSet
 * 2. For each stone, check if it's in the jewel set
 * 3. Count matches
 * 
 * Time Complexity: O(j + s) where j = jewels.length, s = stones.length
 *   - O(j) to build the HashSet
 *   - O(s) to check each stone
 * 
 * Space Complexity: O(j) for the HashSet
 * 
 * Example: jewels = "aA", stones = "aAAbbbb"
 * 
 * Step 1: Build jewel set
 *   jewelSet = {'a', 'A'}
 * 
 * Step 2: Check each stone
 *   'a' → in set? YES → count = 1
 *   'A' → in set? YES → count = 2
 *   'A' → in set? YES → count = 3
 *   'b' → in set? NO  → count = 3
 *   'b' → in set? NO  → count = 3
 *   'b' → in set? NO  → count = 3
 *   'b' → in set? NO  → count = 3
 * 
 * Return: 3
 * 
 * Visual:
 *   jewels: "aA" → HashSet: {'a', 'A'}
 *   
 *   stones: "aAAbbbb"
 *            ↓↓↓
 *            ✓✓✓✗✗✗✗
 *            
 *   3 stones are jewels
 * 
 * Why HashSet over other approaches?
 * 
 * Approach          | Time      | Space
 * ------------------|-----------|-------
 * Brute force       | O(j × s)  | O(1)
 * HashSet (this)    | O(j + s)  | O(j)
 * Sorting           | O(j log j + s log s) | O(1)
 * 
 * HashSet is optimal when j << s (few jewel types, many stones)
 */

import java.util.HashSet;
import java.util.Set;

class Solution {

    public int numJewelsInStones(String jewels, String stones) {

        // Store all jewel types in a HashSet for O(1) lookup
        Set<Character> jewelSet = new HashSet<>();

        int count = 0;

        // Build the jewel set - O(j)
        for (int i = 0; i < jewels.length(); i++) {

            jewelSet.add(jewels.charAt(i));

        }

        // Check each stone against the jewel set - O(s)
        for (int i = 0; i < stones.length(); i++) {

            // O(1) lookup in HashSet
            if (jewelSet.contains(stones.charAt(i))) {

                count++;

            }

        }

        return count;

    }

}

