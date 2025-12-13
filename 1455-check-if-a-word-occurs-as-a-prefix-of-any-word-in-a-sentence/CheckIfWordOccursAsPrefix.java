/*
 * LeetCode 1455: Check If a Word Occurs As a Prefix of Any Word in a Sentence
 * 
 * Problem:
 * Given a sentence (string of words separated by spaces) and a searchWord,
 * check if searchWord is a prefix of any word in the sentence.
 * Return the 1-indexed position of the first word with this prefix.
 * Return -1 if no such word exists.
 * 
 * Approach: Split and Check Each Word
 * 
 * Key Insight:
 * - Split sentence into words
 * - For each word, check if searchWord is its prefix
 * - A prefix means the word starts with searchWord
 * 
 * Algorithm:
 * 1. Split sentence by spaces into array of words
 * 2. For each word:
 *    - Skip if word is shorter than searchWord (can't have it as prefix)
 *    - Check if first `length` characters equal searchWord
 *    - If match, return 1-indexed position (i + 1)
 * 3. Return -1 if no match found
 * 
 * Time Complexity: O(n * m) where n = words in sentence, m = searchWord length
 * Space Complexity: O(n) for the split array
 * 
 * Example: sentence = "i love eating burger", searchWord = "burg"
 * 
 * Words: ["i", "love", "eating", "burger"]
 *         1     2       3         4
 * 
 * Check each:
 * - "i" (len 1) < "burg" (len 4) → skip
 * - "love" (len 4): "love".substring(0,4) = "love" ≠ "burg" → no
 * - "eating" (len 6): "eati" ≠ "burg" → no
 * - "burger" (len 6): "burg" == "burg" → YES! Return 4
 * 
 * Example: sentence = "this problem is an easy problem", searchWord = "pro"
 * 
 * Words: ["this", "problem", "is", "an", "easy", "problem"]
 *          1       2         3     4     5       6
 * 
 * - "this": "thi" ≠ "pro"
 * - "problem": "pro" == "pro" → YES! Return 2
 * 
 * Note: Even though "problem" appears again at position 6,
 *       we return 2 (first occurrence).
 * 
 * Visual:
 *   sentence: "i love eating burger"
 *                            └──┬──┘
 *                               │
 *   searchWord: "burg"     matches prefix!
 *                          
 *   word:     b u r g e r
 *             └─┬─┴─┬─┘
 *   searchWord: b u r g
 *               prefix ✓
 */

class Solution {

    public int isPrefixOfWord(String sentence, String searchWord) {

        int length = searchWord.length();

        // Split sentence into individual words
        String[] arr = sentence.split(" ");

        // Check each word (0-indexed internally, return 1-indexed)
        for (int i = 0; i < arr.length; i++) {

            // Skip words shorter than searchWord
            // They can't possibly have searchWord as prefix
            if (arr[i].length() < length) {

                continue;

            }

            // Check if searchWord matches the beginning of this word
            // substring(0, length) gets the prefix of same length as searchWord
            if (searchWord.equals(arr[i].substring(0, length))) {

                // Return 1-indexed position
                return i + 1;

            }

        }

        // No word has searchWord as prefix
        return -1;

    }

}

