/*
 * 3582. Generate Tag
 * 
 * Problem:
 * Given a caption string, generate a hashtag by following these rules:
 * 
 * 1. Start with '#'
 * 2. Convert to camelCase:
 *    - First word: all lowercase
 *    - Subsequent words: capitalize first letter, rest lowercase
 * 3. Remove all non-letter characters (except the leading '#')
 * 4. Truncate to maximum 100 characters (including '#')
 * 5. If caption is empty/null, return "#"
 * 
 * Examples:
 * 
 * Example 1:
 *   Input: caption = "Hello World"
 *   Output: "#helloWorld"
 *   Explanation:
 *     Words: ["Hello", "World"]
 *     First word lowercase: "hello"
 *     Second word capitalized: "World"
 *     Result: "#helloWorld"
 * 
 * Example 2:
 *   Input: caption = "HAPPY NEW YEAR"
 *   Output: "#happyNewYear"
 *   Explanation:
 *     Words: ["HAPPY", "NEW", "YEAR"]
 *     Convert: "happy" + "New" + "Year"
 *     Result: "#happyNewYear"
 * 
 * Example 3:
 *   Input: caption = "  spaces   everywhere  "
 *   Output: "#spacesEverywhere"
 *   Explanation:
 *     Trim and split by whitespace handles multiple spaces.
 *     Words: ["spaces", "everywhere"]
 * 
 * Example 4:
 *   Input: caption = "hello123world"
 *   Output: "#helloworld"
 *   Explanation:
 *     Single word (no spaces), all lowercase
 *     Remove non-letters: "123" removed
 *     Result: "#helloworld"
 * 
 * Example 5:
 *   Input: caption = "Test! @Special# Characters$"
 *   Output: "#testSpecialCharacters"
 *   Explanation:
 *     Words: ["Test!", "@Special#", "Characters$"]
 *     camelCase: "test!" + "Special#" + "Characters$"
 *     Remove non-letters: "#testSpecialCharacters"
 * 
 * Example 6:
 *   Input: caption = "" (empty)
 *   Output: "#"
 *   Explanation: Empty caption returns just "#"
 * 
 * Example 7 (Truncation):
 *   Input: caption = "Very long caption with many words..." (> 100 chars)
 *   Output: First 100 characters of the generated tag
 * 
 * Constraints:
 *   - 0 <= caption.length <= 10^4
 *   - caption contains letters, digits, spaces, and special characters
 * 
 * Approach: Split → CamelCase → Clean → Truncate
 * 
 * Algorithm:
 * 1. Handle empty/null input → return "#"
 * 2. Trim and split by whitespace (\\s+ handles multiple spaces)
 * 3. Build camelCase:
 *    - First word: all lowercase
 *    - Other words: capitalize first letter + lowercase rest
 * 4. Remove non-letter characters (keep only a-z, A-Z)
 * 5. Truncate to 100 characters if needed
 * 
 * Time Complexity: O(n)
 *   - O(n) to split, build camelCase, and clean
 *   - Where n = caption.length
 * 
 * Space Complexity: O(n)
 *   - StringBuilder stores the result
 *   - Words array from split
 */

class Solution {

    public String generateTag(String caption) {

        // Handle empty or null input
        if (caption == null || caption.isEmpty()) {

            return "#";

        }

        // Split caption into words by whitespace
        // trim() removes leading/trailing spaces
        // \\s+ splits by one or more whitespace characters
        String[] words = caption.trim().split("\\s+");

        // Start building the hashtag
        StringBuilder tag = new StringBuilder("#");

        // Build camelCase from words
        for (int i = 0; i < words.length; i++) {

            // Skip empty words (safety check)
            if (words[i].isEmpty())
                continue;

            // Convert word to lowercase first
            String w = words[i].toLowerCase();

            if (i == 0) {
                // First word: all lowercase
                tag.append(w);

            } else {
                // Subsequent words: capitalize first letter
                tag.append(Character.toUpperCase(w.charAt(0)));

                // Append rest of word (if exists)
                if (w.length() > 1) {

                    tag.append(w.substring(1));

                }

            }

        }

        // Remove non-letter characters (except the leading '#')
        StringBuilder cleaned = new StringBuilder();
        cleaned.append('#'); // Keep the hashtag symbol

        for (int i = 1; i < tag.length(); i++) {

            char c = tag.charAt(i);

            // Only keep alphabetic characters
            if (Character.isLetter(c)) {

                cleaned.append(c);

            }

        }

        // Truncate to 100 characters maximum
        if (cleaned.length() > 100) {

            return cleaned.substring(0, 100);

        }

        return cleaned.toString();

    }

}

/*
 * Dry Run Example:
 * 
 * Input: caption = "Hello World 2024"
 * 
 * Step 1: Check empty → Not empty, continue
 * 
 * Step 2: Split
 *   caption.trim() = "Hello World 2024"
 *   words = ["Hello", "World", "2024"]
 * 
 * Step 3: Build camelCase
 *   tag = "#"
 *   
 *   i=0: w = "hello" (lowercase)
 *        First word → append as-is
 *        tag = "#hello"
 *   
 *   i=1: w = "world"
 *        Capitalize first: 'W' + "orld"
 *        tag = "#helloWorld"
 *   
 *   i=2: w = "2024"
 *        Capitalize first: '2' + "024"
 *        tag = "#helloWorld2024"
 * 
 * Step 4: Remove non-letters
 *   cleaned = "#"
 *   
 *   i=1: 'h' is letter → cleaned = "#h"
 *   i=2: 'e' is letter → cleaned = "#he"
 *   ... (continue for all letters)
 *   i=11: '2' NOT letter → skip
 *   i=12: '0' NOT letter → skip
 *   ...
 *   
 *   cleaned = "#helloWorld"
 * 
 * Step 5: Check length
 *   11 <= 100 → no truncation needed
 * 
 * Output: "#helloWorld"
 * 
 * 
 * CamelCase Rules:
 * 
 * "hello world example"
 *    ↓     ↓       ↓
 *  hello World Example
 *    ↓     ↓       ↓
 *  #helloWorldExample
 * 
 * First word: all lowercase
 * Other words: First letter uppercase, rest lowercase
 * 
 * 
 * Regex \\s+ Explained:
 * 
 * \s  = whitespace character (space, tab, newline)
 * +   = one or more occurrences
 * \\  = escape backslash in Java string
 * 
 * "a   b  c".split("\\s+") → ["a", "b", "c"]
 * 
 * This handles multiple consecutive spaces cleanly!
 * 
 * 
 * Why Two StringBuilders?
 * 
 * StringBuilder 1 (tag): Builds camelCase with possible non-letters
 * StringBuilder 2 (cleaned): Filters out non-letters
 * 
 * Alternative: Could filter during camelCase building, but separating
 * concerns makes the code more readable and maintainable.
 * 
 * 
 * Edge Cases:
 * 
 * Case 1: Empty string
 *   "" → "#"
 * 
 * Case 2: Only spaces
 *   "   ".trim() = "" → split produces []
 *   Loop doesn't execute → returns "#"
 * 
 * Case 3: Single word
 *   "Hello" → "#hello"
 * 
 * Case 4: All numbers
 *   "123 456" → "#" (all non-letters removed)
 * 
 * Case 5: Very long caption
 *   Result truncated to 100 chars including '#'
 * 
 * 
 * Alternative: Single Pass Approach
 * 
 * public String generateTag(String caption) {
 *     if (caption == null || caption.trim().isEmpty()) return "#";
 *     
 *     StringBuilder result = new StringBuilder("#");
 *     boolean capitalizeNext = false;
 *     boolean firstLetterSeen = false;
 *     
 *     for (char c : caption.toCharArray()) {
 *         if (Character.isWhitespace(c)) {
 *             capitalizeNext = firstLetterSeen; // Only cap after first word
 *         } else if (Character.isLetter(c)) {
 *             if (capitalizeNext) {
 *                 result.append(Character.toUpperCase(c));
 *                 capitalizeNext = false;
 *             } else {
 *                 result.append(Character.toLowerCase(c));
 *             }
 *             firstLetterSeen = true;
 *         }
 *         // Non-letters silently ignored
 *         
 *         if (result.length() >= 100) break;
 *     }
 *     
 *     return result.toString();
 * }
 * 
 * This approach processes everything in a single pass!
 */

