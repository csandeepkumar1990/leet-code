/*
 * LeetCode 572: Subtree of Another Tree
 * 
 * Problem:
 * Given the roots of two binary trees root and subRoot, return true if there
 * is a subtree of root with the same structure and node values as subRoot.
 * 
 * A subtree of a binary tree is a tree that consists of a node in the tree
 * and all of this node's descendants.
 * 
 * Approach: Recursive Tree Traversal + Tree Comparison
 * 
 * Key Insight:
 * - For each node in root, check if the subtree starting at that node
 *   is identical to subRoot
 * - Use two recursive functions:
 *   1. isSubtree: traverse root to find potential starting points
 *   2. isSameTree: check if two trees are identical
 * 
 * Algorithm:
 * 1. If root is null, subRoot can't exist → return false
 * 2. If tree rooted at current node equals subRoot → return true
 * 3. Otherwise, recursively check left and right subtrees
 * 
 * Time Complexity: O(m * n) where m = nodes in root, n = nodes in subRoot
 *                  For each node in root, we might compare with entire subRoot
 * Space Complexity: O(h) where h = height of root tree (recursion stack)
 *                   Worst case O(m) for skewed tree
 * 
 * Example:
 * 
 *   root:          subRoot:
 *       3             4
 *      / \           / \
 *     4   5         1   2
 *    / \
 *   1   2
 * 
 * Traversal:
 *   isSubtree(3, subRoot):
 *     isSameTree(3, 4) → false (3 ≠ 4)
 *     isSubtree(4, subRoot):
 *       isSameTree(4, 4) → checking...
 *         4 == 4 ✓
 *         isSameTree(1, 1) → true
 *         isSameTree(2, 2) → true
 *       → returns true!
 *     → returns true
 *   → returns true
 * 
 * Answer: true (subtree rooted at node 4 matches subRoot)
 * 
 * Visual:
 * 
 *       3                    
 *      / \           
 *    [4]  5    ←  This subtree [4,1,2] matches subRoot
 *    / \              4
 *   1   2            / \
 *                   1   2
 * 
 * Edge Cases:
 * - root = null → false (empty tree has no subtrees)
 * - subRoot = null → technically true (empty tree is subtree of any tree)
 * - Both trees identical → true
 */

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

    public boolean isSubtree(TreeNode root, TreeNode subRoot) {

        // If main tree is empty, subRoot can't exist
        if (root == null) {
            return false;
        }

        // If trees are identical from this node
        if (isSameTree(root, subRoot)) {
            return true;
        }

        // Otherwise, check left and right subtrees
        return isSubtree(root.left, subRoot) ||
               isSubtree(root.right, subRoot);

    }

    /**
     * Helper method to check if two trees are identical
     * Trees are identical if they have same structure and same values
     */
    private boolean isSameTree(TreeNode s, TreeNode t) {

        // Both null -> identical (reached end of both branches)
        if (s == null && t == null) {
            return true;
        }

        // One null or values differ -> not identical
        if (s == null || t == null || s.val != t.val) {
            return false;
        }

        // Check left and right subtrees recursively
        return isSameTree(s.left, t.left) &&
               isSameTree(s.right, t.right);

    }

}

