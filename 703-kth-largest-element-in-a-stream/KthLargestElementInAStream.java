/*
 * LeetCode 703: Kth Largest Element in a Stream
 * 
 * Problem:
 * Design a class to find the kth largest element in a stream. Note that it is the kth
 * largest element in the sorted order, not the kth distinct element.
 * 
 * Implement KthLargest class:
 * - KthLargest(int k, int[] nums): Initializes the object with the integer k and the
 *   stream of integers nums.
 * - int add(int val): Appends the integer val to the stream and returns the element
 *   representing the kth largest element in the stream.
 * 
 * Approach: Min Heap (PriorityQueue) of Size K
 * 
 * Key Insight:
 * - Maintain a min heap of size k containing the k largest elements
 * - The root of the min heap is the kth largest element (smallest among the k largest)
 * - When adding a new element:
 *   1. Add it to the heap
 *   2. If heap size > k, remove the smallest (root)
 *   3. Return the root (kth largest)
 * 
 * Why Min Heap (not Max Heap)?
 * - We need the kth largest, which is the smallest among the k largest elements
 * - Min heap keeps the smallest at the root
 * - Root = smallest of k largest = kth largest element
 * - If we used max heap, we'd need to keep all elements and find kth largest (inefficient)
 * 
 * Algorithm:
 * Constructor:
 * 1. Initialize min heap with capacity k
 * 2. Add all initial numbers using add() method
 * 
 * add(val):
 * 1. Add val to min heap (offer)
 * 2. If heap size > k, remove smallest element (poll)
 * 3. Return root of heap (peek) - this is the kth largest
 * 
 * Time Complexity:
 * - Constructor: O(n log k), where n is length of nums
 *   - Adding n elements, each add() is O(log k)
 * - add(): O(log k)
 *   - offer(): O(log k) to add element
 *   - poll(): O(log k) to remove if size > k
 *   - peek(): O(1) to get root
 * 
 * Space Complexity: O(k)
 *   - Min heap stores at most k elements
 *   - Much better than storing all elements O(n)
 * 
 * Example:
 * 
 *   k = 3, nums = [4, 5, 8, 2]
 * 
 *   Constructor:
 *   - Initialize minHeap (capacity 3)
 *   - add(4): heap = [4], size = 1 ≤ 3, return 4
 *   - add(5): heap = [4, 5], size = 2 ≤ 3, return 4
 *   - add(8): heap = [4, 5, 8], size = 3 ≤ 3, return 4
 *   - add(2): heap = [2, 4, 5, 8], size = 4 > 3
 *             poll() removes 2, heap = [4, 5, 8], return 4
 * 
 *   After constructor: heap = [4, 5, 8], kth largest = 4
 * 
 *   add(3):
 *   - offer(3): heap = [3, 4, 5, 8], size = 4 > 3
 *   - poll() removes 3, heap = [4, 5, 8]
 *   - peek() returns 4
 * 
 *   add(5):
 *   - offer(5): heap = [4, 5, 5, 8], size = 4 > 3
 *   - poll() removes 4, heap = [5, 5, 8]
 *   - peek() returns 5
 * 
 *   add(10):
 *   - offer(10): heap = [5, 5, 8, 10], size = 4 > 3
 *   - poll() removes 5, heap = [5, 8, 10]
 *   - peek() returns 5
 * 
 * Visual (Min Heap):
 * 
 *   After adding [4, 5, 8, 2]:
 *        4
 *       / \
 *      5   8
 *     /
 *    2
 *   
 *   After poll() (remove 2):
 *        4
 *       / \
 *      5   8
 *   
 *   kth largest = 4 (root of min heap)
 * 
 * Why Remove Smallest When Size > k?
 * 
 *   - We only need to keep the k largest elements
 *   - Min heap root is the smallest among current elements
 *   - If we have k+1 elements, the smallest is not in top k
 *   - Removing it maintains exactly k largest elements
 *   - Root remains the kth largest
 * 
 * Min Heap Properties:
 * 
 *   - Parent ≤ Children (min at root)
 *   - Complete binary tree structure
 *   - Efficient insertion and removal: O(log n)
 *   - Root access: O(1)
 * 
 * PriorityQueue in Java:
 * 
 *   - Default is min heap (natural ordering)
 *   - offer(val): Add element, O(log n)
 *   - poll(): Remove and return root, O(log n)
 *   - peek(): Return root without removing, O(1)
 *   - size(): Get heap size, O(1)
 * 
 * Alternative Approach: Max Heap (Inefficient)
 * 
 *   - Use max heap to store all elements
 *   - To get kth largest: remove k-1 largest elements
 *   - Time: O(n log n) for constructor, O(k log n) for add()
 *   - Space: O(n) to store all elements
 *   - Much less efficient than min heap approach
 * 
 * Edge Cases:
 * 
 *   - k = 1: Heap always has 1 element (largest so far)
 *   - Empty nums: Heap starts empty, first add() initializes
 *   - All same values: Heap maintains k copies, root is that value
 *   - Very large k: Heap size = k, but k might be close to n
 * 
 * Why Use add() in Constructor?
 * 
 *   - Reuses the same logic for adding elements
 *   - Ensures heap size never exceeds k
 *   - Maintains consistency between constructor and add()
 *   - Cleaner code (DRY principle)
 * 
 * Performance Comparison:
 * 
 *   Min Heap (this solution):
 *   - Constructor: O(n log k)
 *   - add(): O(log k)
 *   - Space: O(k)
 * 
 *   Sorting approach:
 *   - Constructor: O(n log n)
 *   - add(): O(n log n) - need to sort again
 *   - Space: O(n)
 * 
 *   Max Heap approach:
 *   - Constructor: O(n log n)
 *   - add(): O(k log n) - remove k-1 elements
 *   - Space: O(n)
 */

import java.util.PriorityQueue;

class KthLargest {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargest(int k, int[] nums) {
        this.k = k;
        // Initialize min heap with initial capacity of k for efficiency
        this.minHeap = new PriorityQueue<>(k);

        // Add all initial numbers to the heap
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        // Add the new value to the min heap
        minHeap.offer(val);

        // If heap size exceeds k, remove the smallest element
        // This ensures we only keep the k largest elements
        if (minHeap.size() > k) {
            minHeap.poll();
        }

        // The root of the min heap is the kth largest element
        return minHeap.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */

