/*
 * LeetCode 1379: Find a Corresponding Node of a Binary Tree in a Clone of That Tree
 * 
 * Problem:
 * Given two binary trees: original and cloned (exact copy of original),
 * and a reference to a node 'target' in the original tree,
 * return a reference to the SAME node in the cloned tree.
 * 
 * Note: You cannot modify the trees or the target node.
 * 
 * Approach: Parallel DFS Traversal
 * 
 * Key Insight:
 * - Both trees have identical structure
 * - Traverse both trees simultaneously in the same pattern
 * - When we find target in original, we're at the corresponding position in cloned
 * 
 * Why Compare References (not values)?
 * - Values might not be unique in the tree
 * - We need to find the EXACT node, not just same value
 * - original == target compares object references
 * 
 * Algorithm:
 * 1. If current node is null, return null (base case)
 * 2. If original node IS the target, return cloned node (found it!)
 * 3. Recursively search left subtrees
 * 4. If found in left, return it
 * 5. Otherwise, search right subtrees
 * 
 * Time Complexity: O(n) - visit each node at most once
 * Space Complexity: O(h) - recursion stack, h = height of tree
 *   - O(log n) for balanced tree
 *   - O(n) for skewed tree
 * 
 * Example:
 * 
 * Original Tree:        Cloned Tree:
 *       7                     7'
 *      / \                   / \
 *     4   3                 4'  3'
 *        / \                   / \
 *       6   19               6'  19'
 * 
 * target = node with value 3 in original
 * 
 * Traversal:
 * 1. original=7, cloned=7' → not target, go left
 * 2. original=4, cloned=4' → not target, no children, return null
 * 3. Back to 7, try right
 * 4. original=3, cloned=3' → FOUND! original == target
 * 5. Return cloned node (3')
 * 
 * Visual (Parallel Traversal):
 * 
 *   Original        Cloned
 *      7     ←→        7'      ← compare: 7 == target? No
 *     /               /
 *    4      ←→       4'        ← compare: 4 == target? No
 *    
 *      7               7'
 *       \               \
 *        3    ←→        3'     ← compare: 3 == target? YES!
 *                              ← return 3' (cloned node)
 */

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

class Solution {

    /**
     * Find the node in cloned tree that corresponds to target in original tree.
     * 
     * @param original The original binary tree
     * @param cloned   The cloned binary tree (exact copy)
     * @param target   Reference to a node in the original tree
     * @return Reference to the corresponding node in the cloned tree
     */
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {

        // Base case: reached null node
        if (original == null)

            return null;

        // Found the target! Return corresponding cloned node
        // We compare references (==) not values (.equals)
        // because we need the exact same node, not just same value
        if (original == target)

            return cloned;

        // Search in left subtree first
        TreeNode left = getTargetCopy(original.left, cloned.left, target);

        // If found in left subtree, return it
        if (left != null)

            return left;

        // Otherwise, search in right subtree
        return getTargetCopy(original.right, cloned.right, target);

    }

}

