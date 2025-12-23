/*
 * LeetCode 3477: Fruits Into Baskets II
 *
 * Problem:
 * You are given two arrays of integers, fruits and baskets, each of length n,
 * where fruits[i] represents the quantity of the ith type of fruit, and
 * baskets[j] represents the capacity of the jth basket.
 *
 * From left to right, place the fruits according to these rules:
 * - Each fruit type must be placed in the leftmost available basket with a
 *   capacity greater than or equal to the quantity of that fruit type.
 * - Each basket can hold only one type of fruit.
 * - If a fruit type cannot be placed in any basket, it remains unplaced.
 *
 * Return the number of fruit types that remain unplaced after all possible
 * allocations are made.
 *
 * Approach: Greedy Placement with Leftmost Available Basket
 *
 * Key Insight:
 * - Process fruits from left to right (in order)
 * - For each fruit, find the leftmost available basket that can hold it
 * - Mark baskets as used once they contain a fruit
 * - Count fruits that cannot be placed
 *
 * Algorithm:
 * 1. Initialize a boolean array `used` to track which baskets are taken
 * 2. For each fruit in order:
 *    a. Try to find the leftmost available basket with capacity >= fruit size
 *    b. If found, mark basket as used and mark fruit as placed
 *    c. If not found, increment unplaced count
 * 3. Return the count of unplaced fruits
 *
 * Time Complexity: O(n^2) in the worst case, where n is the length of arrays.
 *   For each fruit, we may need to check all baskets.
 * Space Complexity: O(n) for the `used` boolean array.
 *
 * Example:
 *   Input: fruits = [4, 2, 5], baskets = [3, 5, 2]
 *
 *   Fruit 0 (size 4):
 *     - Check basket 0: capacity 3 < 4, skip
 *     - Check basket 1: capacity 5 >= 4, use it ✓
 *   Fruit 1 (size 2):
 *     - Check basket 0: capacity 3 >= 2, use it ✓
 *   Fruit 2 (size 5):
 *     - Check basket 0: used, skip
 *     - Check basket 1: used, skip
 *     - Check basket 2: capacity 2 < 5, skip
 *     - Cannot place ✗
 *
 *   Output: 1 (one fruit type remains unplaced)
 *
 * Why Greedy Works:
 * - We process fruits in order and always choose the leftmost available basket
 * - This ensures we don't "waste" baskets on larger fruits when smaller ones
 *   could use them
 * - The leftmost-first rule is optimal because it maximizes basket utilization
 */

class Solution {

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        int n = fruits.length;
        boolean[] used = new boolean[n]; // track which baskets are already taken
        int unplacedCount = 0;

        for (int fruit : fruits) {
            boolean placed = false;
            for (int j = 0; j < n; j++) {
                if (!used[j] && baskets[j] >= fruit) {
                    used[j] = true; // basket is now taken
                    placed = true;
                    break; // go to next fruit
                }
            }
            if (!placed) {
                unplacedCount++;
            }
        }

        return unplacedCount;
    }
}

