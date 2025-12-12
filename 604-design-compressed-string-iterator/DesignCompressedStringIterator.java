/*
 * LeetCode 604: Design Compressed String Iterator
 * 
 * Problem:
 * Design an iterator that iterates over a compressed string.
 * Compressed format: char followed by count (e.g., "L1e2t1" → "Leet")
 * 
 * Implement:
 * - StringIterator(String compressedString): Initialize with compressed string
 * - next(): Return next character, or ' ' if exhausted
 * - hasNext(): Return true if more characters remain
 * 
 * Approach: Lazy Parsing with Current Character State
 * 
 * Key Insight:
 * - Don't decompress the entire string upfront (could be huge!)
 * - Parse on-demand: only parse next char+count when current is exhausted
 * - Maintain state: current character and remaining count
 * 
 * State Variables:
 * - s: the compressed string
 * - index: current position in compressed string
 * - currentChar: the character currently being expanded
 * - count: how many of currentChar remain to output
 * 
 * Algorithm:
 * 
 * next():
 * 1. If no more characters (hasNext false), return ' '
 * 2. If count == 0, parse next character and its count
 *    - Read single character
 *    - Read all following digits to form count (can be multi-digit!)
 * 3. Decrement count and return currentChar
 * 
 * hasNext():
 * - True if count > 0 (pending chars) OR index < s.length() (more to parse)
 * 
 * Time Complexity:
 * - next(): O(1) amortized (digit parsing is bounded)
 * - hasNext(): O(1)
 * 
 * Space Complexity: O(1) - only storing state, not decompressed string
 * 
 * Example: compressedString = "L1e2t1C1o1d1e1"
 * 
 * Decompressed: "LeetCode"
 * 
 * Parsing breakdown:
 * - "L1" → 'L' × 1
 * - "e2" → 'e' × 2
 * - "t1" → 't' × 1
 * - "C1" → 'C' × 1
 * - "o1" → 'o' × 1
 * - "d1" → 'd' × 1
 * - "e1" → 'e' × 1
 * 
 * Calls:
 * next() → 'L' (count: 1→0)
 * next() → 'e' (parse "e2", count: 2→1)
 * next() → 'e' (count: 1→0)
 * next() → 't' (parse "t1", count: 1→0)
 * ... and so on
 * 
 * Multi-digit count example: "a12b3"
 * - "a12" → 'a' × 12 (twelve a's!)
 * - "b3"  → 'b' × 3
 * 
 * Visual State Machine:
 * 
 *   ┌──────────────────────────────────────┐
 *   │ compressedString = "a12b3"           │
 *   │                     ↑                │
 *   │                   index              │
 *   │ currentChar = 'a', count = 12       │
 *   └──────────────────────────────────────┘
 *   
 *   After 12 next() calls:
 *   
 *   ┌──────────────────────────────────────┐
 *   │ compressedString = "a12b3"           │
 *   │                        ↑             │
 *   │                      index           │
 *   │ currentChar = 'b', count = 3        │
 *   └──────────────────────────────────────┘
 */

class StringIterator {

    private String s;         // The compressed string

    private int index;        // Current position in compressed string

    private char currentChar; // Character currently being expanded

    private int count;        // Remaining count of currentChar to output

    /**
     * Initialize the iterator with the compressed string.
     * We don't parse anything yet - parsing is done lazily in next().
     */
    public StringIterator(String compressedString) {

        this.s = compressedString;

        this.index = 0;

        this.count = 0;

        this.currentChar = ' '; // Placeholder until first parse

    }

    /**
     * Returns the next character in the decompressed string.
     * Returns ' ' (space) if no more characters are available.
     */
    public char next() {

        // No more characters to return
        if (!hasNext()) return ' ';

        // If current character exhausted, parse next char + count
        if (count == 0) {

            // Read the character (single letter)
            currentChar = s.charAt(index++);

            // Parse the count (can be multiple digits)
            int num = 0;

            while (index < s.length() && Character.isDigit(s.charAt(index))) {

                // Build number digit by digit
                num = num * 10 + (s.charAt(index) - '0');

                index++;

            }

            count = num; // Store the count

        }

        // Consume one instance of current character
        count--;

        return currentChar;

    }

    /**
     * Returns true if there are more characters to iterate.
     * Either we have pending characters (count > 0)
     * or there's more to parse (index < length).
     */
    public boolean hasNext() {

        return count > 0 || index < s.length();

    }

}

/**
 * Usage:
 * StringIterator iterator = new StringIterator("L1e2t1C1o1d1e1");
 * iterator.next();    // returns 'L'
 * iterator.next();    // returns 'e'
 * iterator.next();    // returns 'e'
 * iterator.next();    // returns 't'
 * iterator.next();    // returns 'C'
 * iterator.next();    // returns 'o'
 * iterator.next();    // returns 'd'
 * iterator.hasNext(); // returns true
 * iterator.next();    // returns 'e'
 * iterator.hasNext(); // returns false
 * iterator.next();    // returns ' '
 */

