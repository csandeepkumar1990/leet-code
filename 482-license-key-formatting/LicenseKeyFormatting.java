/**
 * LeetCode 482: License Key Formatting
 * 
 * Problem: You are given a license key string s containing alphanumeric characters
 *          and dashes. Reformat the string such that:
 *          1. Each group contains exactly k characters (except possibly the first)
 *          2. The first group can have fewer than k chars, but must have at least 1
 *          3. Groups are separated by dashes
 *          4. All letters are converted to uppercase
 * 
 * Key Insight: Remove all dashes first, then rebuild from left to right.
 *              The first group gets the "remainder" characters (length % k),
 *              and all subsequent groups get exactly k characters.
 * 
 * Examples:
 *   Input: s = "5F3Z-2e-9-w", k = 4
 *   Output: "5F3Z-2E9W"
 *   Explanation: Without dashes: "5F3Z2E9W" (8 chars)
 *                8 % 4 = 0, so first group is also 4 chars
 *                Groups: "5F3Z" + "2E9W"
 * 
 *   Input: s = "2-5g-3-J", k = 2
 *   Output: "2-5G-3J"
 *   Explanation: Without dashes: "25G3J" (5 chars)
 *                5 % 2 = 1, so first group has 1 char
 *                Groups: "2" + "5G" + "3J"
 * 
 * Constraints:
 *   - 1 <= s.length <= 10^5
 *   - s consists of alphanumeric characters and dashes
 *   - 1 <= k <= 10^4
 */

class Solution {

    /**
     * Reformats a license key with groups of size k.
     * 
     * @param s - The original license key string
     * @param k - The size of each group (except possibly the first)
     * @return The reformatted license key
     * 
     * Time Complexity: O(n) - process each character twice (clean + build)
     * Space Complexity: O(n) - store the cleaned string and result
     */
    public String licenseKeyFormatting(String s, int k) {
        /*
         * STEP 1: Clean the input string
         * - Remove all dashes
         * - Convert to uppercase
         * 
         * Example: "5F3Z-2e-9-w" → "5F3Z2E9W"
         */
        String h = s.replace("-", "").toUpperCase();

        /*
         * STEP 2: Calculate first group length
         * 
         * Total chars divided into groups of k, the remainder goes to first group.
         * Example: 8 chars, k=3 → 8 % 3 = 2 (first group has 2 chars)
         * 
         * If remainder is 0, first group is also k chars (or string is empty).
         */
        int firstGroupLength = h.length() % k;
        StringBuilder result = new StringBuilder();

        /*
         * STEP 3: Handle the first group
         * 
         * Only add first group if it's not empty (remainder > 0)
         * Example: h = "25G3J", k = 2, firstGroupLength = 1
         *          First group: "2"
         */
        int index = 0;
        if (firstGroupLength > 0) {
            result.append(h.substring(0, firstGroupLength));
            index = firstGroupLength;
        }

        /*
         * STEP 4: Process remaining groups of exactly k characters
         * 
         * Add dash before each group (except if result is empty)
         * Append k characters at a time
         * 
         * Example: h = "25G3J", index = 1, k = 2
         *          Loop 1: append "-5G", index = 3
         *          Loop 2: append "-3J", index = 5
         *          Result: "2-5G-3J"
         */
        while (index < h.length()) {
            // Add dash separator (but not before the very first group)
            if (result.length() > 0) {
                result.append("-");
            }
            result.append(h.substring(index, index + k));
            index += k;
        }

        return result.toString();
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(sol.licenseKeyFormatting("5F3Z-2e-9-w", 4)); // "5F3Z-2E9W"
 * System.out.println(sol.licenseKeyFormatting("2-5g-3-J", 2));    // "2-5G-3J"
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: s = "2-5g-3-J", k = 2
 * ═══════════════════════════════════════════════════════════════
 * 
 * Step 1: Clean string
 *   "2-5g-3-J" → "25G3J" (5 chars)
 * 
 * Step 2: Calculate first group
 *   firstGroupLength = 5 % 2 = 1
 * 
 * Step 3: Add first group
 *   result = "2", index = 1
 * 
 * Step 4: Add remaining groups
 *   index=1: result = "2-5G", index = 3
 *   index=3: result = "2-5G-3J", index = 5
 * 
 * Final: "2-5G-3J" ✓
 * 
 * ═══════════════════════════════════════════════════════════════
 * VISUAL BREAKDOWN
 * ═══════════════════════════════════════════════════════════════
 * 
 * Original: "5F3Z-2e-9-w"
 *            ↓ (remove dashes, uppercase)
 * Cleaned:  "5F3Z2E9W" (8 chars)
 *            ↓ (8 % 4 = 0, so first group is also 4)
 * Groups:   [5F3Z][2E9W]
 *            ↓ (join with dashes)
 * Result:   "5F3Z-2E9W"
 */

