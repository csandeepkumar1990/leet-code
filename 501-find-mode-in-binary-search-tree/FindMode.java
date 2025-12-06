/**
 * LeetCode 501: Find Mode in Binary Search Tree
 * 
 * Problem: Given the root of a Binary Search Tree (BST), return the mode(s)
 *          (the most frequently occurring element(s)) in it. If there are
 *          multiple modes, return them in any order.
 * 
 * Key Insight: In a BST, in-order traversal visits nodes in sorted order.
 *              Equal values will be consecutive, making counting easy!
 *              We can find modes in O(1) extra space (excluding output).
 * 
 * Examples:
 *   Input: root = [1,null,2,2]
 *          1
 *           \
 *            2
 *           /
 *          2
 *   Output: [2]
 *   Explanation: 2 appears twice, 1 appears once. Mode is 2.
 * 
 *   Input: root = [0]
 *   Output: [0]
 * 
 *   Input: root = [1,1,2]
 *          1
 *         / \
 *        1   2
 *   Output: [1]
 *   Explanation: 1 appears twice, 2 appears once. Mode is 1.
 * 
 * Constraints:
 *   - Number of nodes: [1, 10^4]
 *   - -10^5 <= Node.val <= 10^5
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

class Solution {
    /*
     * Instance variables to track state during traversal:
     * - prev: the value of the previous node visited (null initially)
     * - currentCount: how many times we've seen the current value
     * - maxCount: the highest frequency seen so far
     * - modes: list of values that appear maxCount times
     */
    private Integer prev = null;
    private int currentCount = 0;
    private int maxCount = 0;
    private List<Integer> modes = new ArrayList<>();

    /**
     * Finds all modes (most frequent values) in a BST.
     * 
     * @param root - The root of the BST
     * @return Array of mode values
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack, h = tree height
     *                   O(1) extra space if we don't count output array
     */
    public int[] findMode(TreeNode root) {
        // Perform in-order traversal (visits nodes in sorted order for BST)
        inorder(root);

        // Convert ArrayList to int[] array for return
        int[] result = new int[modes.size()];
        for (int i = 0; i < modes.size(); i++) {
            result[i] = modes.get(i);
        }
        return result;
    }

    /**
     * In-order traversal: Left → Current → Right
     * For BST, this visits nodes in ascending sorted order.
     * Equal values will be consecutive, perfect for counting!
     */
    private void inorder(TreeNode node) {
        if (node == null) return;

        /*
         * STEP 1: Traverse left subtree (smaller values)
         */
        inorder(node.left);

        /*
         * STEP 2: Process current node
         * 
         * Since in-order visits BST in sorted order, equal values
         * are always consecutive. Compare with previous value:
         * - Same as prev? Increment count
         * - Different? Start counting new value from 1
         */
        if (prev != null && node.val == prev) {
            currentCount++;
        } else {
            currentCount = 1;
        }

        /*
         * STEP 3: Update modes list based on currentCount
         * 
         * Case 1: currentCount > maxCount
         *   → Found a new maximum! Clear old modes, add this value
         * 
         * Case 2: currentCount == maxCount
         *   → This value ties for most frequent, add to modes
         * 
         * Case 3: currentCount < maxCount
         *   → Not a mode, do nothing
         */
        if (currentCount > maxCount) {
            maxCount = currentCount;
            modes.clear();          // Previous modes are no longer valid
            modes.add(node.val);
        } else if (currentCount == maxCount) {
            modes.add(node.val);    // Tie! Add to existing modes
        }

        /*
         * STEP 4: Update prev for next iteration
         */
        prev = node.val;

        /*
         * STEP 5: Traverse right subtree (larger values)
         */
        inorder(node.right);
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * // Build tree: [1,null,2,2]
 * TreeNode root = new TreeNode(1);
 * root.right = new TreeNode(2);
 * root.right.left = new TreeNode(2);
 * int[] modes = sol.findMode(root); // Output: [2]
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY IN-ORDER TRAVERSAL WORKS FOR BST
 * ═══════════════════════════════════════════════════════════════
 * 
 * BST Property: left < root < right
 * 
 * In-order traversal visits: Left → Root → Right
 * This gives us nodes in SORTED order!
 * 
 * Example BST:     4
 *                 / \
 *                2   6
 *               / \   \
 *              1   2   6
 * 
 * In-order: 1 → 2 → 2 → 4 → 6 → 6
 *           (sorted, duplicates are consecutive!)
 * 
 * Counting becomes simple:
 *   1: count=1
 *   2: count=1 (new value)
 *   2: count=2 (same as prev) ← max so far
 *   4: count=1 (new value)
 *   6: count=1 (new value)
 *   6: count=2 (same as prev) ← ties with 2
 * 
 * Modes: [2, 6] (both appear twice)
 */

