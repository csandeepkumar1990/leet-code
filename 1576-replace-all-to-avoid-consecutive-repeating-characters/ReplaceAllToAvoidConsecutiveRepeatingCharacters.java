/*
 * LeetCode 1576: Replace All ?'s to Avoid Consecutive Repeating Characters
 * 
 * Problem:
 * Given a string s containing only lowercase English letters and '?' characters,
 * replace every '?' with a lowercase letter such that no two adjacent characters
 * are the same. Return any valid result.
 * 
 * Approach: Greedy Single Pass
 * 
 * Key Insight:
 * - We only need 3 letters ('a', 'b', 'c') to guarantee we can always find a valid replacement
 * - Why? Because each '?' has at most 2 neighbors (left and right)
 * - With 3 choices, at least one will always be different from both neighbors
 * 
 * Algorithm:
 * 1. Convert string to char array for in-place modification
 * 2. Iterate through each character
 * 3. When we find a '?', try 'a', 'b', 'c' in order
 * 4. Pick the first one that doesn't match either neighbor
 * 
 * Time Complexity: O(n) - single pass through the string
 * Space Complexity: O(n) - for the char array (required since strings are immutable in Java)
 * 
 * Example:
 * Input:  "?zs"
 * Output: "azs"
 * - '?' at index 0: no left neighbor, right neighbor is 'z'
 * - Try 'a': not equal to 'z' ✓ → replace with 'a'
 * 
 * Example:
 * Input:  "j?qg??b"
 * Output: "jaqgacb"
 * - '?' at index 1: left='j', right='q' → 'a' works
 * - '?' at index 4: left='g', right='?' → 'a' works
 * - '?' at index 5: left='a', right='b' → 'c' works (not 'a' or 'b')
 */

class Solution {

    public String modifyString(String s) {

        // Convert to char array for efficient in-place modifications
        char[] arr = s.toCharArray();

        int n = arr.length;

        // Process each character in the string
        for (int i = 0; i < n; i++) {

            // Only process '?' characters
            if (arr[i] == '?') {

                // Try characters 'a', 'b', 'c' - we only need 3 options
                // because each position has at most 2 neighbors
                for (char c = 'a'; c <= 'c'; c++) {

                    boolean isValid = true;

                    // Check left neighbor (if exists)
                    // Make sure our choice doesn't match the character to the left
                    if (i > 0 && arr[i - 1] == c) {

                        isValid = false;

                    }

                    // Check right neighbor (if exists)
                    // Make sure our choice doesn't match the character to the right
                    // Note: right neighbor could still be '?' but that's fine,
                    // it will be handled when we reach that index
                    if (i < n - 1 && arr[i + 1] == c) {

                        isValid = false;

                    }

                    // If this character doesn't conflict with neighbors, use it
                    if (isValid) {

                        arr[i] = c;

                        break; // Move to the next '?'

                    }

                }

            }

        }

        // Convert char array back to string and return
        return new String(arr);

    }

}

