/*
 * LeetCode 2432: The Employee That Worked on the Longest Task
 * 
 * Problem:
 * There are n employees. You are given a 2D array logs where logs[i] = [id, leaveTime]
 * indicates the employee id worked on a task that started at the end of the previous
 * task and ended at leaveTime. The first task starts at time 0.
 * Return the id of the employee who worked the longest task. If there's a tie,
 * return the smallest id.
 * 
 * Approach: Single Pass with Duration Tracking
 * 
 * Key Insight:
 * - Task duration = current leaveTime - previous leaveTime
 * - First task: duration = leaveTime - 0
 * - Track max duration and employee id
 * - On tie (same duration), keep smaller id
 * 
 * Algorithm:
 * 1. Initialize maxDuration=0, employeeId=MAX_VALUE, prevTime=0
 * 2. For each log [id, leaveTime]:
 *    - Calculate duration = leaveTime - prevTime
 *    - Update if duration > max OR (duration == max AND id < current best)
 *    - Update prevTime = leaveTime
 * 3. Return employeeId
 * 
 * Time Complexity: O(m) where m = number of logs
 * Space Complexity: O(1) - only using variables
 * 
 * Example 1: n=10, logs=[[0,3],[2,5],[0,9],[1,15]]
 * 
 *   Timeline:
 *   0────3────5─────────9──────────────15
 *   │    │    │         │              │
 *   └─3──┘    └────4────┘              │
 *    emp 0         emp 0               │
 *        └──2─┘              └────6────┘
 *         emp 2                emp 1
 * 
 *   Log 1: id=0, leaveTime=3  → duration = 3-0 = 3
 *   Log 2: id=2, leaveTime=5  → duration = 5-3 = 2
 *   Log 3: id=0, leaveTime=9  → duration = 9-5 = 4
 *   Log 4: id=1, leaveTime=15 → duration = 15-9 = 6 ← MAX
 * 
 *   Result: Employee 1 (duration 6)
 * 
 * Example 2: n=26, logs=[[1,1],[3,7],[2,12],[7,17]]
 * 
 *   Log 1: id=1, leaveTime=1  → duration = 1-0 = 1
 *   Log 2: id=3, leaveTime=7  → duration = 7-1 = 6
 *   Log 3: id=2, leaveTime=12 → duration = 12-7 = 5
 *   Log 4: id=7, leaveTime=17 → duration = 17-12 = 5
 * 
 *   Result: Employee 3 (duration 6)
 * 
 * Example 3 (Tie): logs=[[0,10],[1,20]]
 * 
 *   Log 1: id=0, leaveTime=10 → duration = 10-0 = 10
 *   Log 2: id=1, leaveTime=20 → duration = 20-10 = 10
 * 
 *   Tie! Both have duration 10
 *   Return smaller id: Employee 0
 * 
 * Tie-Breaking Logic:
 * 
 *   if (duration > maxDuration ||
 *       (duration == maxDuration && id < employeeId))
 *       
 *   Case 1: duration > max → always update
 *   Case 2: duration == max AND smaller id → update (tie-breaker)
 *   Case 3: duration == max AND larger id → keep current (smaller wins)
 * 
 * Why Initialize employeeId = MAX_VALUE?
 *   - Ensures any valid employee id will be smaller
 *   - First comparison will always update employeeId
 *   - Alternative: could use -1 and handle separately
 */

class Solution {

    public int hardestWorker(int n, int[][] logs) {

        int maxDuration = 0;

        int employeeId = Integer.MAX_VALUE;

        int prevTime = 0;

        for (int[] log : logs) {

            int id = log[0];

            int leaveTime = log[1];

            int duration = leaveTime - prevTime;

            // Update if we find a longer duration or same duration with smaller ID
            if (duration > maxDuration || (duration == maxDuration && id < employeeId)) {

                maxDuration = duration;

                employeeId = id;

            }

            prevTime = leaveTime;

        }

        return employeeId;

    }

}

