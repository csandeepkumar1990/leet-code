/**
 * LeetCode 3063: Linked List Frequency
 * 
 * Problem: Given the head of a linked list containing k distinct elements,
 *          return the head of a linked list of length k containing the frequency
 *          of each distinct element in the given list in any order.
 * 
 * Approach:
 *   1. Traverse the input list once
 *   2. Use a hash map to count occurrences of each value
 *   3. Create a new linked list
 *   4. Each node = frequency of one distinct value
 *   5. Return the head of the new list
 * 
 * Examples:
 *   Input: head = [1,1,2,1,2,3]
 *   Output: [3,2,1] (or any permutation like [1,2,3], [2,3,1], etc.)
 *   Explanation: 1 appears 3 times, 2 appears 2 times, 3 appears 1 time
 * 
 *   Input: head = [1,1,2,2,2]
 *   Output: [2,3] (or [3,2])
 *   Explanation: 1 appears 2 times, 2 appears 3 times
 * 
 *   Input: head = [6,5,4,3,2,1]
 *   Output: [1,1,1,1,1,1]
 *   Explanation: Each element appears exactly once
 */

import java.util.HashMap;
import java.util.Map;

/**
 * Definition for singly-linked list.
 */
class ListNode {
    int val;
    ListNode next;

    ListNode() {}

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class Solution {

    /**
     * Returns a linked list containing frequencies of each distinct element.
     * 
     * @param head - Head of the input linked list
     * @return Head of a new linked list with frequencies
     * 
     * Time Complexity: O(n) - single traversal of input list + iteration over distinct elements
     * Space Complexity: O(k) - where k is the number of distinct elements
     */
    public ListNode frequenciesOfElements(ListNode head) {
        // Step 1: Traverse the input list and count occurrences using hash map
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        ListNode current = head;

        while (current != null) {
            frequencyMap.put(current.val, frequencyMap.getOrDefault(current.val, 0) + 1);
            current = current.next;
        }

        // Step 2: Create a new linked list where each node = frequency of one distinct value
        ListNode dummy = new ListNode(0);  // Dummy node to simplify list construction
        ListNode tail = dummy;

        for (int frequency : frequencyMap.values()) {
            tail.next = new ListNode(frequency);
            tail = tail.next;
        }

        // Step 3: Return head of the new list
        return dummy.next;
    }
}

/**
 * Usage Example:
 * 
 * // Create input list: 1 -> 1 -> 2 -> 1 -> 2 -> 3
 * ListNode head = new ListNode(1);
 * head.next = new ListNode(1);
 * head.next.next = new ListNode(2);
 * head.next.next.next = new ListNode(1);
 * head.next.next.next.next = new ListNode(2);
 * head.next.next.next.next.next = new ListNode(3);
 * 
 * Solution sol = new Solution();
 * ListNode result = sol.frequenciesOfElements(head);
 * 
 * // Output list contains: 3 -> 2 -> 1 (frequencies of 1, 2, 3 respectively)
 * // Note: Order may vary based on HashMap iteration order
 */

