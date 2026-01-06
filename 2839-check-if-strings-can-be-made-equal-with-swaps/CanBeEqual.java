/*
 * LeetCode 2839: Check if Strings Can Be Made Equal With Swaps
 * 
 * Problem:
 * You are given two strings s1 and s2, both of length 4, consisting of only lowercase
 * English letters.
 * 
 * You can apply the following operation on s1 any number of times:
 * - Choose any two indices i and j such that i and j have the same parity (both even or both odd)
 * - Swap the characters at positions i and j in s1
 * 
 * Return true if it is possible to make s1 equal to s2, otherwise return false.
 * 
 * Approach: Parity-Based Character Matching
 * 
 * Key Insight:
 * - Characters at even positions (0, 2) can only be swapped with each other
 * - Characters at odd positions (1, 3) can only be swapped with each other
 * - Characters at even positions cannot be swapped with characters at odd positions
 * - Therefore, we need to check if the multiset of characters at even positions matches,
 *   and if the multiset of characters at odd positions matches
 * 
 * Algorithm:
 * 1. Check even positions (0 and 2):
 *    - Either s1[0] == s2[0] && s1[2] == s2[2] (already matching)
 *    - Or s1[0] == s2[2] && s1[2] == s2[0] (can be swapped)
 * 2. Check odd positions (1 and 3):
 *    - Either s1[1] == s2[1] && s1[3] == s2[3] (already matching)
 *    - Or s1[1] == s2[3] && s1[3] == s2[1] (can be swapped)
 * 3. Return true only if both even and odd positions can be matched
 * 
 * Time Complexity: O(1)
 *   - Fixed string length of 4
 *   - Constant time comparisons
 * 
 * Space Complexity: O(1)
 *   - Only using a few boolean variables
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: s1 = "abcd", s2 = "cdab"
 * 
 *   Even positions (0, 2):
 *     s1: 'a' at 0, 'c' at 2
 *     s2: 'c' at 0, 'a' at 2
 *     Check: s1[0] == s2[2] ('a' == 'a') ✓
 *            s1[2] == s2[0] ('c' == 'c') ✓
 *     evenMatch = true
 * 
 *   Odd positions (1, 3):
 *     s1: 'b' at 1, 'd' at 3
 *     s2: 'd' at 1, 'b' at 3
 *     Check: s1[1] == s2[3] ('b' == 'b') ✓
 *            s1[3] == s2[1] ('d' == 'd') ✓
 *     oddMatch = true
 * 
 *   Result: true (both even and odd positions can be matched)
 * 
 * Another Example:
 * 
 *   Input: s1 = "abcd", s2 = "abdc"
 * 
 *   Even positions (0, 2):
 *     s1: 'a' at 0, 'c' at 2
 *     s2: 'a' at 0, 'c' at 2
 *     Check: s1[0] == s2[0] ('a' == 'a') ✓
 *            s1[2] == s2[2] ('c' == 'c') ✓
 *     evenMatch = true
 * 
 *   Odd positions (1, 3):
 *     s1: 'b' at 1, 'd' at 3
 *     s2: 'b' at 1, 'd' at 3
 *     Wait, s2 is "abdc", so:
 *     s2: 'b' at 1, 'c' at 3? No, let me recheck...
 *     Actually s2 = "abdc":
 *       s2[0] = 'a', s2[1] = 'b', s2[2] = 'd', s2[3] = 'c'
 *     Odd positions: s2[1] = 'b', s2[3] = 'c'
 *     s1 odd: 'b' at 1, 'd' at 3
 *     Check: s1[1] == s2[1] ('b' == 'b') ✓
 *            s1[3] == s2[3] ('d' == 'c') ✗
 *     Check swap: s1[1] == s2[3] ('b' == 'c') ✗
 *                 s1[3] == s2[1] ('d' == 'b') ✗
 *     oddMatch = false
 * 
 *   Result: false (odd positions cannot be matched)
 * 
 * Visual Representation:
 * 
 *   s1 = "abcd", s2 = "cdab"
 * 
 *   Positions:  0    1    2    3
 *   s1:         a    b    c    d
 *   s2:         c    d    a    b
 * 
 *   Even positions (0, 2):
 *     s1: a, c
 *     s2: c, a
 *     Can swap: a↔c in s1 to get c, a ✓
 * 
 *   Odd positions (1, 3):
 *     s1: b, d
 *     s2: d, b
 *     Can swap: b↔d in s1 to get d, b ✓
 * 
 *   Result: true
 * 
 * Why Parity Matters:
 * 
 *   The constraint says we can only swap characters at positions with the same parity.
 *   This means:
 *   - Even positions (0, 2) form one "group" - they can swap with each other
 *   - Odd positions (1, 3) form another "group" - they can swap with each other
 *   - Characters cannot move between groups
 * 
 *   Therefore, for s1 to become s2:
 *   - The multiset of characters at even positions in s1 must equal
 *     the multiset of characters at even positions in s2
 *   - The multiset of characters at odd positions in s1 must equal
 *     the multiset of characters at odd positions in s2
 * 
 * Edge Cases:
 * 
 * 1. Already equal:
 *    s1 = "abcd", s2 = "abcd"
 *    evenMatch: a==a && c==c → true
 *    oddMatch: b==b && d==d → true
 *    Result: true
 * 
 * 2. Only even positions swapped:
 *    s1 = "abcd", s2 = "cbad"
 *    evenMatch: a==c && c==a → true (swapped)
 *    oddMatch: b==b && d==d → true (same)
 *    Result: true
 * 
 * 3. Only odd positions swapped:
 *    s1 = "abcd", s2 = "adcb"
 *    evenMatch: a==a && c==c → true (same)
 *    oddMatch: b==d && d==b → true (swapped)
 *    Result: true
 * 
 * 4. Both swapped:
 *    s1 = "abcd", s2 = "cdab"
 *    evenMatch: a==c && c==a → true
 *    oddMatch: b==d && d==b → true
 *    Result: true
 * 
 * 5. Cannot match (different characters):
 *    s1 = "abcd", s2 = "abce"
 *    evenMatch: a==a && c==c → true
 *    oddMatch: b==b && d==e → false
 *    Result: false
 * 
 * 6. Same character at both positions:
 *    s1 = "aabb", s2 = "bbaa"
 *    evenMatch: a==b && a==a → false? Wait, let me check:
 *      s1[0]='a', s1[2]='a'
 *      s2[0]='b', s2[2]='a'
 *      Check: s1[0]==s2[0] && s1[2]==s2[2] → a==b && a==a → false
 *      Check swap: s1[0]==s2[2] && s1[2]==s2[0] → a==a && a==b → false
 *    Actually, this should work because we have two 'a's at even positions.
 *    The multiset is {a, a} in both, so it should match.
 *    But our check doesn't handle this case properly...
 * 
 *    Actually, wait. If s1 = "aabb" and s2 = "bbaa":
 *      s1 even: 'a', 'a'
 *      s2 even: 'b', 'a'
 *      Multiset s1 even: {a, a}
 *      Multiset s2 even: {a, b}
 *      These are different! So it should return false, which our code does.
 * 
 * Mathematical Proof:
 * 
 *   For two strings to be equal after parity-based swaps:
 *   - The multiset of characters at even positions must match
 *   - The multiset of characters at odd positions must match
 * 
 *   For length 4 strings with 2 even and 2 odd positions:
 *   - Even positions: need to check if {s1[0], s1[2]} == {s2[0], s2[2]}
 *   - Odd positions: need to check if {s1[1], s1[3]} == {s2[1], s2[3]}
 * 
 *   Our solution checks:
 *   - Even: (s1[0]==s2[0] && s1[2]==s2[2]) || (s1[0]==s2[2] && s1[2]==s2[0])
 *   - Odd: (s1[1]==s2[1] && s1[3]==s2[3]) || (s1[1]==s2[3] && s1[3]==s2[1])
 * 
 *   This correctly checks if the multisets match (either same order or swapped).
 */

class Solution {
    /**
     * Determines if s1 can be made equal to s2 by swapping characters at positions
     * with the same parity (even with even, odd with odd).
     * 
     * @param s1 First string (length 4)
     * @param s2 Second string (length 4)
     * @return true if s1 can be transformed into s2, false otherwise
     * 
     * Time Complexity: O(1) - fixed string length
     * Space Complexity: O(1)
     */
    public boolean canBeEqual(String s1, String s2) {
        // Check if even positions (0 and 2) can be matched
        // Either they're already in the same positions, or they can be swapped
        boolean evenMatch = (s1.charAt(0) == s2.charAt(0) && s1.charAt(2) == s2.charAt(2)) ||
                            (s1.charAt(0) == s2.charAt(2) && s1.charAt(2) == s2.charAt(0));

        // Check if odd positions (1 and 3) can be matched
        // Either they're already in the same positions, or they can be swapped
        boolean oddMatch = (s1.charAt(1) == s2.charAt(1) && s1.charAt(3) == s2.charAt(3)) ||
                           (s1.charAt(1) == s2.charAt(3) && s1.charAt(3) == s2.charAt(1));

        // Both even and odd positions must be matchable
        return evenMatch && oddMatch;
    }
}

