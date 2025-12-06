/**
 * LeetCode 492: Construct the Rectangle
 * 
 * Problem: A web developer needs to design a rectangular web page with given area.
 *          Return [length, width] where:
 *          1. length * width = area
 *          2. length >= width
 *          3. The difference (length - width) should be as small as possible
 * 
 * Key Insight: To minimize (length - width), we want length and width as close
 *              as possible. The closest they can be is when both equal √area
 *              (a perfect square). Start from √area and work downward to find
 *              the largest width that divides the area evenly.
 * 
 * Examples:
 *   Input: area = 4
 *   Output: [2, 2]
 *   Explanation: 2 * 2 = 4, and 2 - 2 = 0 (minimum difference)
 * 
 *   Input: area = 37
 *   Output: [37, 1]
 *   Explanation: 37 is prime, only factors are 37 and 1
 * 
 *   Input: area = 122122
 *   Output: [427, 286]
 *   Explanation: 427 * 286 = 122122, difference = 141
 * 
 * Constraints:
 *   - 1 <= area <= 10^7
 */

class Solution {

    /**
     * Constructs rectangle dimensions with minimal length-width difference.
     * 
     * @param area - The required area of the rectangle
     * @return [length, width] where length >= width
     * 
     * Time Complexity: O(√area) - worst case, iterate from √area down to 1
     * Space Complexity: O(1) - only using a few variables
     */
    public int[] constructRectangle(int area) {
        /*
         * Start with width = √area (rounded down)
         * 
         * Why √area? Because for a given area:
         * - If width = √area, then length = area/width = √area
         * - This is the point where length = width (minimum difference = 0)
         * - Any width smaller than √area means length > √area
         * 
         * Example: area = 12
         * √12 ≈ 3.46 → start with width = 3
         */
        int width = (int) Math.sqrt(area);

        /*
         * Find the largest width that divides area evenly
         * 
         * Decrease width until (area % width == 0)
         * Since we start from √area and go down, the first valid width
         * we find gives us the minimum (length - width) difference.
         * 
         * Example: area = 12, start width = 3
         * - width = 3: 12 % 3 = 0 ✓ (found it!)
         * - length = 12 / 3 = 4
         * - Result: [4, 3]
         */
        while (area % width != 0) {
            width--;
        }

        // Calculate length from area and width
        int length = area / width;

        // Return [length, width] where length >= width (guaranteed by our approach)
        return new int[] {length, width};
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * System.out.println(Arrays.toString(sol.constructRectangle(4)));      // [2, 2]
 * System.out.println(Arrays.toString(sol.constructRectangle(37)));     // [37, 1]
 * System.out.println(Arrays.toString(sol.constructRectangle(122122))); // [427, 286]
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY START FROM √AREA?
 * ═══════════════════════════════════════════════════════════════
 * 
 * For any rectangle with area A:
 *   length × width = A
 *   length = A / width
 * 
 * The difference is minimized when length ≈ width ≈ √A
 * 
 * Visual for area = 12:
 * 
 *   width | length | diff
 *   ------|--------|------
 *     1   |   12   |  11
 *     2   |    6   |   4
 *     3   |    4   |   1  ← minimum! (closest to √12 ≈ 3.46)
 *     4   |    3   |  -1  (invalid: length < width)
 * 
 * By starting at √area and going down, we find the optimal width first!
 * 
 * ═══════════════════════════════════════════════════════════════
 * WORKED EXAMPLE: area = 24
 * ═══════════════════════════════════════════════════════════════
 * 
 * Step 1: width = √24 ≈ 4.89 → width = 4
 * 
 * Step 2: Find divisor
 *   - width = 4: 24 % 4 = 0 ✓ (found!)
 * 
 * Step 3: length = 24 / 4 = 6
 * 
 * Answer: [6, 4] ✓
 */

