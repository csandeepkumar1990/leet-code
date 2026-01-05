/*
 * LeetCode 2335: Minimum Amount of Time to Fill Cups
 * 
 * Problem:
 * You have a water dispenser that can dispense cold, warm, and hot water. Every second,
 * you can either fill 2 cups with different types of water, or 1 cup of any type of water.
 * 
 * You are given a 0-indexed integer array amount of length 3 where amount[0], amount[1],
 * and amount[2] denote the number of cold, warm, and hot water cups you need to fill
 * respectively.
 * 
 * Return the minimum number of seconds needed to fill up all the cups.
 * 
 * Approach: Greedy with Mathematical Analysis
 * 
 * Key Insight:
 * - We can fill 2 cups per second (different types) or 1 cup per second (any type)
 * - Strategy: Always fill 2 cups when possible (most efficient)
 * - If one type dominates (max >= sum of others), we need max seconds
 * - Otherwise, we can pair up cups efficiently
 * 
 * Algorithm:
 * 1. Find maximum value (max) and sum of all values
 * 2. Calculate remaining = sum - 2 * max
 * 3. If remaining <= 0: return max (one type dominates)
 * 4. If remaining > 0:
 *    - If remaining is even: return max + remaining / 2
 *    - If remaining is odd: return max + remaining / 2 + 1
 * 
 * Mathematical Reasoning:
 * 
 *   Case 1: One type dominates (max >= sum of others)
 *   - Example: [5, 0, 0] or [3, 1, 1]
 *   - We can only fill 1 cup per second for the dominant type
 *   - Time = max
 * 
 *   Case 2: No single type dominates (max < sum of others)
 *   - Example: [1, 4, 2] → max=4, sum=7, remaining=7-8=-1 → Case 1
 *   - Example: [5, 4, 4] → max=5, sum=13, remaining=13-10=3 → Case 2
 *   - We can pair up cups efficiently
 *   - Time = max + ceil(remaining / 2)
 * 
 * Why sum - 2 * maximum?
 * 
 *   - After using the maximum type, we have (sum - max) cups remaining
 *   - But we've already used max seconds, during which we could have filled
 *     2 * max cups (if pairing was possible)
 *   - However, we only filled max cups of the dominant type
 *   - Remaining cups = sum - max - max = sum - 2 * max
 *   - This represents cups that need additional time beyond max
 * 
 * Why Check if sum - 2 * max <= 0?
 * 
 *   - If sum - 2 * max <= 0, it means max >= (sum - max)
 *   - The dominant type has more cups than the other two combined
 *   - We can't pair efficiently, so we need max seconds
 *   - Example: [5, 1, 1] → max=5, sum=7, remaining=7-10=-3 ≤ 0
 *     → We need 5 seconds (one per second for the 5 cups)
 * 
 * Why Add (sum - 2 * max) / 2?
 * 
 *   - Remaining cups can be paired (2 cups per second)
 *   - If remaining is even: exactly remaining / 2 seconds needed
 *   - If remaining is odd: (remaining / 2) + 1 seconds needed
 *     (one extra second for the last unpaired cup)
 * 
 * Time Complexity: O(1)
 *   - Single pass through array to find max and sum: O(3) = O(1)
 *   - Constant time calculations
 * 
 * Space Complexity: O(1)
 *   - Only using a constant amount of extra space
 * 
 * Example:
 * 
 *   Input: amount = [1, 4, 2]
 * 
 *   Step 1: Find max and sum
 *   - max = 4
 *   - sum = 1 + 4 + 2 = 7
 * 
 *   Step 2: Calculate remaining
 *   - remaining = 7 - 2 * 4 = 7 - 8 = -1
 *   - remaining <= 0 → return max = 4
 * 
 *   Explanation:
 *   - Type 1 (cold): 1 cup
 *   - Type 2 (warm): 4 cups (dominant)
 *   - Type 3 (hot): 2 cups
 *   - We can pair: (cold, hot) in 1 second → [0, 4, 1]
 *   - We can pair: (hot, warm) in 1 second → [0, 3, 0]
 *   - Remaining: 3 warm cups, fill 1 per second → 3 seconds
 *   - Total: 1 + 1 + 3 = 5 seconds
 *   - But wait, let's reconsider...
 *   - Actually: max=4 means we need at least 4 seconds
 *   - During those 4 seconds, we can fill 2 cups per second = 8 cups
 *   - But we only need 7 cups total
 *   - So we can do it in 4 seconds: pair efficiently
 *   - Actually the answer is 4, which matches our formula
 * 
 * Another Example:
 * 
 *   Input: amount = [5, 4, 4]
 * 
 *   Step 1: Find max and sum
 *   - max = 5
 *   - sum = 5 + 4 + 4 = 13
 * 
 *   Step 2: Calculate remaining
 *   - remaining = 13 - 2 * 5 = 13 - 10 = 3
 *   - remaining > 0 and odd
 *   - return 5 + 3 / 2 + 1 = 5 + 1 + 1 = 7
 * 
 *   Explanation:
 *   - We need 5 cold, 4 warm, 4 hot
 *   - Strategy: Fill 2 cups per second when possible
 *   - Second 1: (warm, hot) → [5, 3, 3]
 *   - Second 2: (warm, hot) → [5, 2, 2]
 *   - Second 3: (warm, hot) → [5, 1, 1]
 *   - Second 4: (warm, hot) → [5, 0, 0]
 *   - Second 5: (cold) → [4, 0, 0]
 *   - Second 6: (cold) → [3, 0, 0]
 *   - Second 7: (cold) → [2, 0, 0]
 *   - Wait, we still have 2 cold left...
 *   - Actually, we can optimize: pair cold with warm/hot earlier
 *   - Better strategy: Always pair the two largest
 *   - This is a greedy approach, but our formula handles it correctly
 *   - The answer is 7 seconds
 * 
 * Yet Another Example:
 * 
 *   Input: amount = [2, 2, 2]
 * 
 *   Step 1: Find max and sum
 *   - max = 2
 *   - sum = 2 + 2 + 2 = 6
 * 
 *   Step 2: Calculate remaining
 *   - remaining = 6 - 2 * 2 = 6 - 4 = 2
 *   - remaining > 0 and even
 *   - return 2 + 2 / 2 = 2 + 1 = 3
 * 
 *   Explanation:
 *   - We need 2 of each type
 *   - Second 1: (cold, warm) → [1, 1, 2]
 *   - Second 2: (cold, hot) → [0, 1, 1]
 *   - Second 3: (warm, hot) → [0, 0, 0]
 *   - Total: 3 seconds ✓
 * 
 * Formula Breakdown:
 * 
 *   If sum - 2 * max <= 0:
 *     → max dominates, need max seconds
 *   
 *   If sum - 2 * max > 0:
 *     → Can pair efficiently
 *     → Need max seconds for dominant type
 *     → Plus ceil((sum - 2 * max) / 2) for remaining
 *     → Total = max + ceil((sum - 2 * max) / 2)
 * 
 * Why This Works:
 * 
 *   - The maximum type sets a lower bound: we need at least max seconds
 *   - During those max seconds, we can fill 2 * max cups if pairing perfectly
 *   - But we only filled max cups of the dominant type
 *   - Remaining cups = sum - max - max = sum - 2 * max
 *   - These remaining cups need additional time
 *   - We can pair them 2 at a time, so need ceil(remaining / 2) more seconds
 * 
 * Edge Cases:
 * 
 *   - All zeros: [0, 0, 0] → max=0, sum=0, remaining=0 → return 0
 *   - Single type: [5, 0, 0] → max=5, sum=5, remaining=-5 → return 5
 *   - Two types equal: [3, 3, 0] → max=3, sum=6, remaining=0 → return 3
 *   - All equal: [2, 2, 2] → max=2, sum=6, remaining=2 → return 3
 *   - One dominates: [10, 1, 1] → max=10, sum=12, remaining=-8 → return 10
 */

class Solution {
    public int fillCups(int[] amount) {
        int sum = 0;
        int maximum = 0;
        
        for (int num : amount) {
            maximum = Math.max(maximum, num);
            sum += num;
        }
        
        sum = sum - 2 * maximum;
        
        if (sum <= 0) {
            return maximum;
        }
        
        if (sum % 2 == 0) {
            return maximum + sum / 2;
        } else {
            return maximum + sum / 2 + 1;
        }
    }
}

