// 3545. Minimum Deletions for At Most K Distinct Characters
// Find the minimum number of character deletions to make string have at most k distinct characters.
//
// Examples:
// Input: s = "abc", k = 2 -> Output: 1
//   Delete any one character to have 2 distinct chars
//
// Input: s = "aabb", k = 2 -> Output: 0
//   Already has 2 distinct characters (a, b)
//
// Input: s = "aaabbbccc", k = 1 -> Output: 6
//   Keep only one character type, delete the other two (3 + 3 = 6)
//
// Key Insight: To minimize deletions, remove the characters with lowest frequencies.
// Sort by frequency and delete the least frequent character types until we have k or fewer.
//
// Time Complexity: O(n + 26 log 26) = O(n) - count frequencies and sort at most 26 entries
// Space Complexity: O(26) = O(1) - frequency array and list of at most 26 elements

import java.util.*;

class Solution {

    public int minDeletion(String s, int k) {

        int[] freq = new int[26];

        for (char c : s.toCharArray()) {

            freq[c - 'a']++;

        }

        // Collect frequencies of characters that appear

        List<Integer> list = new ArrayList<>();

        for (int f : freq) {

            if (f > 0)

                list.add(f);

        }

        // Number of distinct characters

        int distinct = list.size();

        // If already <= k distinct, no deletions needed

        if (distinct <= k)

            return 0;

        // Sort frequencies in ascending order (delete smaller ones first)

        Collections.sort(list);

        int deletions = 0;

        int toRemove = distinct - k; // how many character types to delete

        // Delete the least frequent character types

        for (int i = 0; i < toRemove; i++) {

            deletions += list.get(i);

        }

        return deletions;

    }

}

