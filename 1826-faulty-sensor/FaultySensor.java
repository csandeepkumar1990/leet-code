/*
 * LeetCode 1826: Faulty Sensor
 * 
 * Problem:
 * An experiment is being conducted in a lab. To ensure accuracy, there are two sensors
 * collecting data simultaneously. You are given 2 arrays sensor1 and sensor2, where
 * sensor1[i] and sensor2[i] are the ith data points collected by the two sensors.
 * 
 * However, this type of sensor has a tendency to produce errors. The errors are of two types:
 * 1. A sensor can drop a value and replace it with a random value (defect type 1)
 * 2. A sensor can duplicate a value (defect type 2)
 * 
 * However, in this problem, we only deal with defect type 1: a sensor drops one value.
 * 
 * You need to determine which sensor has the defect. It is guaranteed that exactly one
 * sensor has a defect, and the defect is that it dropped exactly one value.
 * 
 * Return:
 * - 1 if sensor1 has the defect
 * - 2 if sensor2 has the defect
 * - -1 if it cannot be determined (both sensors could have the defect)
 * 
 * Approach: Two-Pointer Comparison with Drop Detection
 * 
 * Key Insight:
 * - One sensor dropped exactly one value
 * - We need to check if sensor1 can be transformed into sensor2 by dropping one value
 * - We also need to check if sensor2 can be transformed into sensor1 by dropping one value
 * - If both are possible, return -1 (ambiguous)
 * - If neither is possible, return -1 (invalid input)
 * - Otherwise, return the sensor number that has the defect
 * 
 * Algorithm:
 * 1. Check if sensor2 can be transformed into sensor1 by dropping one value
 *    (meaning sensor1 might be correct, sensor2 has defect)
 * 2. Check if sensor1 can be transformed into sensor2 by dropping one value
 *    (meaning sensor2 might be correct, sensor1 has defect)
 * 3. If both are true: ambiguous, return -1
 * 4. If neither is true: invalid, return -1
 * 5. Otherwise: return the sensor with the defect
 * 
 * The canReplace helper function:
 * - Uses two pointers to traverse both arrays
 * - When values match, advance both pointers
 * - When values don't match, assume A (first array) dropped a value, so advance only A's pointer
 * - Track the dropped value
 * - At the end, check if we successfully matched all of B's values (except possibly the last one)
 * - Also ensure the last value of B is not the dropped value (to avoid false positives)
 * 
 * Time Complexity: O(n), where n is the length of the sensor arrays
 *   - Each canReplace call: O(n) to traverse both arrays
 *   - Two calls to canReplace: O(2n) = O(n)
 * 
 * Space Complexity: O(1)
 *   - Only using a few variables (pointers, flags)
 *   - No additional data structures
 * 
 * Example:
 * 
 *   Input: sensor1 = [2, 3, 4, 5], sensor2 = [2, 1, 3, 4]
 * 
 *   Check if sensor2 can replace sensor1 (sensor1 has defect):
 *     A = [2, 1, 3, 4], B = [2, 3, 4, 5]
 *     i=0, j=0: A[0]=2, B[0]=2, match → i=1, j=1
 *     i=1, j=1: A[1]=1, B[1]=3, no match → drop A[1]=1, i=2
 *     i=2, j=1: A[2]=3, B[1]=3, match → i=3, j=2
 *     i=3, j=2: A[3]=4, B[2]=4, match → i=4, j=3
 *     i=4, j=3: i >= A.length, stop
 *     j=3 < B.length-1? No, j=3 == B.length-1
 *     B[j]=5, droppedValue=1, 5 != 1? Yes
 *     Result: true (sensor1 has defect)
 * 
 *   Check if sensor1 can replace sensor2 (sensor2 has defect):
 *     A = [2, 3, 4, 5], B = [2, 1, 3, 4]
 *     i=0, j=0: A[0]=2, B[0]=2, match → i=1, j=1
 *     i=1, j=1: A[1]=3, B[1]=1, no match → drop A[1]=3, i=2
 *     i=2, j=1: A[2]=4, B[1]=1, no match → drop A[2]=4, i=3
 *     i=3, j=1: A[3]=5, B[1]=1, no match → drop A[3]=5, i=4
 *     i=4, j=1: i >= A.length, stop
 *     j=1 < B.length-1? Yes (1 < 3)
 *     Result: false (sensor2 does not have defect)
 * 
 *   Final: oneDefect=true, twoDefect=false
 *   Return: 1 (sensor1 has the defect)
 * 
 * Another Example:
 * 
 *   Input: sensor1 = [1, 2, 3, 4], sensor2 = [1, 2, 3, 4]
 * 
 *   Both arrays are identical, so:
 *   - sensor2 can replace sensor1: true (no drops needed)
 *   - sensor1 can replace sensor2: true (no drops needed)
 *   Result: -1 (ambiguous, cannot determine)
 * 
 * Edge Cases:
 * 
 * 1. Arrays are identical:
 *    - Both canReplace return true
 *    - Return -1 (ambiguous)
 * 
 * 2. Arrays differ by one value at the end:
 *    - One canReplace returns true
 *    - Return the sensor with defect
 * 
 * 3. Arrays differ by one value in the middle:
 *    - One canReplace returns true
 *    - Return the sensor with defect
 * 
 * Why check B[j] != droppedValue?
 * 
 *   This prevents false positives. If the last value of B equals the dropped value,
 *   it means we could have matched it, so the transformation is not valid.
 * 
 *   Example:
 *     A = [1, 2, 3], B = [1, 2, 1]
 *     If we drop A[2]=3, we get [1, 2] which matches B[0:2]=[1, 2]
 *     But B[2]=1, and we dropped 3, not 1
 *     However, if B[2] was 3 (the dropped value), it would be ambiguous
 */

class Solution {
    /**
     * Determines which sensor has a defect (dropped one value).
     * 
     * @param sensor1 First sensor's data array
     * @param sensor2 Second sensor's data array
     * @return 1 if sensor1 has defect, 2 if sensor2 has defect, -1 if ambiguous or invalid
     */
    public int badSensor(int[] sensor1, int[] sensor2) {
        // Check if sensor2 can be transformed into sensor1 by dropping one value
        // (meaning sensor1 is correct, sensor2 has defect)
        final boolean oneDefect = canReplace(sensor2, sensor1);

        // Check if sensor1 can be transformed into sensor2 by dropping one value
        // (meaning sensor2 is correct, sensor1 has defect)
        final boolean twoDefect = canReplace(sensor1, sensor2);

        // If both are possible, it's ambiguous (cannot determine)
        if (oneDefect && twoDefect)
            return -1;

        // If neither is possible, it's invalid input
        if (!oneDefect && !twoDefect)
            return -1;

        // Return the sensor with the defect
        return oneDefect ? 1 : 2;
    }

    /**
     * Checks if array A can be transformed into array B by dropping exactly one value.
     * 
     * The transformation is valid if:
     * 1. We can match all values of B (except possibly the last one) by traversing A
     * 2. When A and B don't match, we drop the value from A and continue
     * 3. At the end, we must have matched B.length - 1 values
     * 4. The last value of B must not be the dropped value (to avoid false positives)
     * 
     * @param A The array that might have dropped a value
     * @param B The target array we're trying to match
     * @return true if A can be transformed into B by dropping one value, false otherwise
     */
    private boolean canReplace(int[] A, int[] B) {
        int i = 0; // A's index
        int j = 0; // B's index
        int droppedValue = -1; // Track the value that was dropped from A

        // Traverse array A
        while (i < A.length) {
            if (A[i] == B[j]) {
                // Values match, advance both pointers
                ++i;
                ++j;
            } else {
                // Values don't match, assume A dropped this value
                droppedValue = A[i];
                ++i; // Only advance A's pointer
            }
        }

        // Check if we successfully matched all of B (except possibly the last element)
        // and the last element of B is not the dropped value
        return j == B.length - 1 && B[j] != droppedValue;
    }
}

