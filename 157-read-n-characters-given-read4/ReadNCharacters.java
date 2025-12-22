/*
 * LeetCode 157: Read N Characters Given Read4
 * 
 * Problem:
 * Given a file and assume that you can only read the file using a given method
 * read4, implement a method to read n characters.
 * 
 * The API read4 reads 4 consecutive characters from the file, then writes those
 * characters into the buffer array buf4.
 * 
 * The return value is the number of actual characters read. Note that read4() has
 * its own file pointer, much like FILE *fp in C.
 * 
 * Definition of read4:
 *     Parameter:  char[] buf4
 *     Returns:    int
 * 
 * Note: buf4[] is destination not source, the results from read4 will be copied
 * to buf4[].
 * 
 * By using the read4 method, implement the method read such that:
 * - It should read n characters from the file and store them in the given buffer buf
 * - You cannot directly manipulate the file
 * - The return value is the number of actual characters read
 * 
 * Definition of read:
 *     Parameter:  char[] buf, int n
 *     Returns:    int
 * 
 * Note: buf[] is destination not source, you will need to write the results to buf[].
 * 
 * Approach: Read in Chunks of 4
 * 
 * Key Insight:
 * - read4 reads up to 4 characters at a time
 * - We need to read n characters, which may require multiple read4 calls
 * - Copy characters from buf4 to buf until we've read n characters or reached EOF
 * - EOF is indicated when read4 returns fewer than 4 characters
 * 
 * Algorithm:
 * 1. Create temporary buffer buf4 of size 4
 * 2. While total < n:
 *    - Call read4(buf4) to read up to 4 characters
 *    - If read4 returns 0, we've reached EOF → break
 *    - Copy characters from buf4 to buf (up to n - total)
 *    - Update total count
 * 3. Return total characters read
 * 
 * Time Complexity: O(n)
 *   - In worst case, we read n characters
 *   - Each read4 call reads up to 4 characters
 *   - Total calls: ceil(n/4)
 * 
 * Space Complexity: O(1)
 *   - Only using fixed-size buffer buf4 (size 4)
 *   - No additional data structures
 * 
 * Example 1:
 *   File: "abc", n = 4
 *   
 *   Call 1: read4(buf4) → reads "abc" (3 chars), returns 3
 *           Copy 3 chars to buf: "abc"
 *           total = 3 < 4, but read4 returned 3 < 4 (EOF)
 *           Break
 *   
 *   Result: 3 characters ("abc")
 * 
 * Example 2:
 *   File: "abcde", n = 5
 *   
 *   Call 1: read4(buf4) → reads "abcd" (4 chars), returns 4
 *           Copy 4 chars to buf: "abcd"
 *           total = 4 < 5, continue
 *   
 *   Call 2: read4(buf4) → reads "e" (1 char), returns 1
 *           Copy 1 char to buf: "abcde"
 *           total = 5, break
 *   
 *   Result: 5 characters ("abcde")
 * 
 * Example 3:
 *   File: "abcdefghij", n = 3
 *   
 *   Call 1: read4(buf4) → reads "abcd" (4 chars), returns 4
 *           Copy min(4, 3) = 3 chars to buf: "abc"
 *           total = 3, break
 *   
 *   Result: 3 characters ("abc")
 * 
 * Why Check count == 0 for EOF?
 * 
 *   - read4 returns the number of characters actually read
 *   - If file has no more characters, read4 returns 0
 *   - This indicates EOF, so we should stop reading
 * 
 * Why Check total < n in Copy Loop?
 * 
 *   - We only want to read n characters total
 *   - If read4 returns 4 but we only need 2 more, we should only copy 2
 *   - The condition `i < count && total < n` ensures we:
 *     * Don't copy more than read4 returned
 *     * Don't copy more than n characters total
 * 
 * Visual Example (File: "abcdef", n = 5):
 * 
 *   Iteration 1:
 *   read4(buf4) → "abcd" (count = 4)
 *   Copy to buf: "abcd" (total = 4)
 *   
 *   Iteration 2:
 *   read4(buf4) → "ef" (count = 2)
 *   Copy to buf: "abcdef" (but we only need 1 more)
 *   Copy only 1: "abcde" (total = 5)
 *   Break
 *   
 *   Result: 5 characters ("abcde")
 * 
 * Edge Cases:
 * 
 *   - n = 0: Return 0 immediately
 *   - File shorter than n: Read all available characters
 *   - File exactly n characters: Read all n characters
 *   - File longer than n: Read exactly n characters
 *   - Empty file: Return 0
 * 
 * Important Notes:
 * 
 *   - read4 maintains its own file pointer
 *   - Each call to read4 reads from where it left off
 *   - We cannot rewind or seek in the file
 *   - buf4 is overwritten on each read4 call
 */

/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4);
 */

public class Solution extends Reader4 {

    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    public int read(char[] buf, int n) {

        char[] buf4 = new char[4];

        int total = 0; // Total characters read so far

        while (total < n) {

            int count = read4(buf4); // Read up to 4 chars

            if (count == 0) {

                break; // EOF

            }

            // Copy chars from buf4 to buf
            // Stop when we've copied all from buf4 or reached n characters
            for (int i = 0; i < count && total < n; i++) {

                buf[total++] = buf4[i];

            }

        }

        return total;

    }

}

