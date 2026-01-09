/*
 * LeetCode 3769: Sort by Reflection
 * 
 * Problem:
 * Given an array of integers nums, sort the array by the "reflection" of each number.
 * The reflection of a number is obtained by reversing its binary representation.
 * 
 * Sorting Rules:
 * 1. Primary: Sort by reflection value (ascending)
 * 2. Secondary: If reflections are equal, sort by original value (ascending)
 * 
 * Example:
 *   Input: nums = [5, 2, 3]
 *   Output: [2, 3, 5]
 *   
 *   Explanation:
 *     - 5 in binary: "101" → reversed: "101" → reflection: 5
 *     - 2 in binary: "10" → reversed: "01" → reflection: 1
 *     - 3 in binary: "11" → reversed: "11" → reflection: 3
 *   
 *   Sorted by reflection: [2 (ref=1), 3 (ref=3), 5 (ref=5)]
 * 
 * Approach: Custom Comparator with Reflection Calculation
 * 
 * Key Insight:
 * - Convert each number to binary string
 * - Reverse the binary string
 * - Convert back to decimal to get reflection value
 * - Use custom comparator to sort by reflection, then by original value
 * 
 * Algorithm:
 * 1. Convert int[] to Integer[] for custom sorting
 * 2. Calculate reflection for each number
 * 3. Sort using custom comparator:
 *    - Primary: Compare reflection values
 *    - Secondary: Compare original values if reflections are equal
 * 4. Convert back to int[] and return
 * 
 * Time Complexity: O(n log n * k)
 *   - n log n for sorting
 *   - k is the average number of bits (log n), for reflection calculation
 *   - Total: O(n log n * log n) ≈ O(n log² n)
 * 
 * Space Complexity: O(n)
 *   - Integer array for sorting: O(n)
 *   - Binary string representation: O(log n) per number
 * 
 * Example Walkthrough:
 * 
 *   Input: nums = [5, 2, 3]
 *   
 *   Step 1: Calculate reflections
 *     5: binary = "101" → reversed = "101" → reflection = 5
 *     2: binary = "10" → reversed = "01" → reflection = 1
 *     3: binary = "11" → reversed = "11" → reflection = 3
 *   
 *   Step 2: Sort by reflection
 *     Reflections: [5: 5, 2: 1, 3: 3]
 *     Sorted: [2 (ref=1), 3 (ref=3), 5 (ref=5)]
 *   
 *   Output: [2, 3, 5]
 * 
 * Visual Representation:
 * 
 *   Number | Binary | Reversed | Reflection
 *   -------|--------|----------|------------
 *     5    | "101"  | "101"    |     5
 *     2    | "10"   | "01"     |     1
 *     3    | "11"   | "11"     |     3
 *     4    | "100"  | "001"    |     1
 *     6    | "110"  | "011"    |     3
 * 
 *   Sorted: [2 (ref=1), 4 (ref=1), 3 (ref=3), 6 (ref=3), 5 (ref=5)]
 *   Note: When reflections are equal (2 and 4 both have ref=1),
 *         sort by original value: 2 < 4
 * 
 * How Reflection Works:
 * 
 *   Reflection reverses the binary representation:
 *   
 *   Example 1: n = 5
 *     Binary: 101 (most significant bit first)
 *     Reversed: 101 (same in this case)
 *     Decimal: 1*2² + 0*2¹ + 1*2⁰ = 4 + 0 + 1 = 5
 *   
 *   Example 2: n = 2
 *     Binary: 10
 *     Reversed: 01
 *     Decimal: 0*2¹ + 1*2⁰ = 0 + 1 = 1
 *   
 *   Example 3: n = 4
 *     Binary: 100
 *     Reversed: 001
 *     Decimal: 0*2² + 0*2¹ + 1*2⁰ = 0 + 0 + 1 = 1
 * 
 * Important Notes:
 * 
 *   1. Leading zeros matter in reflection:
 *      - "10" reversed is "01" (not "1")
 *      - "100" reversed is "001" (not "1")
 *   
 *   2. When reflections are equal, use original value as tiebreaker:
 *      - 2 and 4 both have reflection = 1
 *      - Since 2 < 4, 2 comes first
 *   
 *   3. Zero case:
 *      - 0 in binary: "0"
 *      - Reversed: "0"
 *      - Reflection: 0
 * 
 * Edge Cases:
 * 
 *   1. Single element:
 *      nums = [5]
 *      Result: [5]
 *   
 *   2. All zeros:
 *      nums = [0, 0, 0]
 *      Result: [0, 0, 0] (all have reflection 0, sorted by original)
 *   
 *   3. Powers of 2:
 *      nums = [1, 2, 4, 8]
 *      Reflections: [1, 1, 1, 1]
 *      Result: [1, 2, 4, 8] (sorted by original value)
 *   
 *   4. All same reflection:
 *      nums = [2, 4, 8]
 *      All have reflection = 1
 *      Result: [2, 4, 8] (sorted by original value)
 * 
 * Why Use Integer[] Instead of int[]?
 * 
 *   Java's Arrays.sort() for primitive types doesn't support custom comparators.
 *   We need to use Integer[] (object array) to use a custom Comparator.
 * 
 * Reflection Calculation Details:
 * 
 *   The getReflection method:
 *   1. Converts integer to binary string using Integer.toBinaryString()
 *   2. Reverses the string using StringBuilder.reverse()
 *   3. Converts back to decimal using Long.parseLong(reversed, 2)
 *   
 *   Why Long instead of Integer?
 *   - Reversed binary might be larger than original
 *   - Example: 1 (binary "1") reversed is "1" = 1
 *   - But 2³¹-1 reversed could potentially be large
 *   - Using Long prevents overflow
 */

import java.util.Arrays;

class Solution {
    /**
     * Sorts the array by reflection value, with original value as tiebreaker.
     * 
     * @param nums The input array of integers
     * @return The sorted array (modified in-place)
     * 
     * Time Complexity: O(n log² n) where n is the length of nums
     * Space Complexity: O(n)
     */
    public int[] sortByReflection(int[] nums) {
        // Convert to Integer array for custom sorting
        // Java's Arrays.sort() for primitives doesn't support custom comparators
        Integer[] result = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            result[i] = nums[i];
        }
        
        // Custom sort by reflection, then by original value
        Arrays.sort(result, (a, b) -> {
            long refA = getReflection(a);
            long refB = getReflection(b);
            
            // Primary: Compare by reflection value
            if (refA != refB) {
                return Long.compare(refA, refB);
            }
            
            // Secondary: If reflections are equal, compare by original value
            return Integer.compare(a, b);
        });
        
        // Convert back to int[]
        for (int i = 0; i < nums.length; i++) {
            nums[i] = result[i];
        }
        
        return nums;
    }
    
    /**
     * Calculates the reflection of a number by reversing its binary representation.
     * 
     * @param n The input integer
     * @return The reflection value (binary reversed and converted to decimal)
     * 
     * How it works:
     * 1. Convert n to binary string (e.g., 5 → "101")
     * 2. Reverse the string (e.g., "101" → "101")
     * 3. Convert back to decimal (e.g., "101" → 5)
     * 
     * Special case: n = 0 returns 0
     */
    private long getReflection(int n) {
        if (n == 0) return 0;
        
        // Convert to binary string
        String binary = Integer.toBinaryString(n);
        
        // Reverse bits using StringBuilder
        String reversed = new StringBuilder(binary).reverse().toString();
        
        // Convert back to base-10 (using Long to handle potential large values)
        return Long.parseLong(reversed, 2);
    }
}

