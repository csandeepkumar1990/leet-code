/*
 * LeetCode 359: Logger Rate Limiter
 * 
 * Problem:
 * Design a logger system that receives a stream of messages along with their timestamps.
 * Each unique message should only be printed at most every 10 seconds.
 * 
 * Implement:
 * - Logger(): Initialize the logger
 * - shouldPrintMessage(int timestamp, String message): Returns true if the message should
 *   be printed in the given timestamp, otherwise returns false.
 * 
 * Approach: HashMap to Track Last Print Time
 * 
 * Key Insight:
 * - Use a HashMap to store the last timestamp each message was printed
 * - For each message, check if it's new OR if 10+ seconds have passed since last print
 * - Update the timestamp when printing is allowed
 * 
 * Algorithm:
 * 1. Check if message exists in map
 * 2. If message doesn't exist OR (current timestamp - last timestamp) >= 10:
 *    - Update last print time to current timestamp
 *    - Return true (allow print)
 * 3. Otherwise return false (don't allow print)
 * 
 * Time Complexity:
 * - shouldPrintMessage(): O(1) - HashMap operations are O(1) average case
 * 
 * Space Complexity: O(M) - where M is the number of unique messages
 * 
 * Example:
 * Logger logger = new Logger();
 * logger.shouldPrintMessage(1, "foo");  // returns true, next "foo" allowed at 11
 * logger.shouldPrintMessage(2, "bar");  // returns true, next "bar" allowed at 12
 * logger.shouldPrintMessage(3, "foo");  // returns false, 3-1 < 10
 * logger.shouldPrintMessage(8, "bar");  // returns false, 8-2 < 10
 * logger.shouldPrintMessage(10, "foo"); // returns false, 10-1 < 10
 * logger.shouldPrintMessage(11, "foo"); // returns true, 11-1 >= 10
 */

import java.util.HashMap;
import java.util.Map;

class Logger {
    private Map<String, Integer> lastPrintTime;

    public Logger() {
        lastPrintTime = new HashMap<>();
    }

    public boolean shouldPrintMessage(int timestamp, String message) {
        if (!lastPrintTime.containsKey(message) || timestamp - lastPrintTime.get(message) >= 10) {
            lastPrintTime.put(message, timestamp); // update last printed time
            return true; // allow print
        }
        return false;
    }
}

/**
 * Your Logger object will be instantiated and called as such:
 * Logger obj = new Logger();
 * boolean param_1 = obj.shouldPrintMessage(timestamp,message);
 */

