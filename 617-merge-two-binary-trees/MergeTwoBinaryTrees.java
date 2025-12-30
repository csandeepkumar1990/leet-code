/*
 * LeetCode 617: Merge Two Binary Trees
 * 
 * Problem:
 * You are given two binary trees root1 and root2.
 * 
 * Imagine that when you put one of them to cover the other, some nodes of the two trees
 * are overlapped while the others are not. You need to merge the two trees into a new
 * binary tree. The merge rule is that if two nodes overlap, then sum node values up as
 * the new value of the merged node. Otherwise, the NOT null node will be used as the
 * node of the new tree.
 * 
 * Return the merged tree.
 * 
 * Note: The merging process must start from the root nodes of both trees.
 * 
 * Approach: Recursive DFS (Depth-First Search)
 * 
 * Key Insight:
 * - Merge trees by summing overlapping node values
 * - If one node is null, use the other node (non-null)
 * - Recursively merge left and right subtrees
 * - Modify root1 in-place to create the merged tree
 * 
 * Algorithm:
 * 1. Base case: If root1 is null, return root2 (use root2's subtree)
 * 2. Base case: If root2 is null, return root1 (use root1's subtree)
 * 3. Merge current nodes: root1.val += root2.val
 * 4. Recursively merge left subtrees: root1.left = mergeTrees(root1.left, root2.left)
 * 5. Recursively merge right subtrees: root1.right = mergeTrees(root1.right, root2.right)
 * 6. Return root1 (merged tree)
 * 
 * Time Complexity: O(min(m, n)), where m and n are the number of nodes in root1 and root2.
 *   We visit each node at most once. If one tree is smaller, we stop when we reach null.
 * 
 * Space Complexity: O(min(m, n)), where m and n are the heights of root1 and root2.
 *   - Recursion stack: O(min(h1, h2)) for the call stack
 *   - Worst case: O(min(m, n)) for skewed trees
 *   - Best case: O(log(min(m, n))) for balanced trees
 * 
 * Example:
 * 
 *   root1:              root2:
 *       1                   2
 *      / \                 / \
 *     3   2               1   3
 *    /                     \   \
 *   5                       4   7
 * 
 *   Step 1 - Merge root nodes:
 *   root1.val = 1 + 2 = 3
 * 
 *   Step 2 - Merge left subtrees:
 *   root1.left (3) + root2.left (1)
 *   - root1.left.val = 3 + 1 = 4
 *   - root1.left.left (5) + root2.left.left (null) → 5
 *   - root1.left.right (null) + root2.left.right (4) → 4
 * 
 *   Step 3 - Merge right subtrees:
 *   root1.right (2) + root2.right (3)
 *   - root1.right.val = 2 + 3 = 5
 *   - root1.right.left (null) + root2.right.left (null) → null
 *   - root1.right.right (null) + root2.right.right (7) → 7
 * 
 *   Merged tree:
 *       3
 *      / \
 *     4   5
 *    / \   \
 *   5   4   7
 * 
 * Visual (Merging Process):
 * 
 *   root1:              root2:              Merged:
 *       1                   2                  3
 *      / \                 / \               / \
 *     3   2               1   3             4   5
 *    /                     \   \          / \   \
 *   5                       4   7         5   4   7
 * 
 *   Node-by-node:
 *   - Root: 1 + 2 = 3
 *   - Left child: 3 + 1 = 4
 *   - Right child: 2 + 3 = 5
 *   - Left-left: 5 + null = 5
 *   - Left-right: null + 4 = 4
 *   - Right-right: null + 7 = 7
 * 
 * Why Modify root1 In-Place?
 * 
 *   - More memory efficient (no new nodes created)
 *   - Reuses existing tree structure
 *   - Simpler implementation
 *   - Problem allows modifying the input trees
 * 
 * Why Return root2 When root1 is Null?
 * 
 *   - If root1 is null, there's nothing to merge
 *   - Return root2's entire subtree as-is
 *   - This handles the case where root1's branch doesn't exist
 *   - Example: root1.left is null, root2.left exists → use root2.left
 * 
 * Why Return root1 When root2 is Null?
 * 
 *   - If root2 is null, there's nothing to add
 *   - Return root1's subtree as-is (no changes needed)
 *   - This handles the case where root2's branch doesn't exist
 *   - Example: root1.left exists, root2.left is null → keep root1.left
 * 
 * Alternative Approach: Create New Tree
 * 
 *   public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
 *       if (root1 == null && root2 == null) return null;
 *       if (root1 == null) return root2;
 *       if (root2 == null) return root1;
 *       
 *       TreeNode merged = new TreeNode(root1.val + root2.val);
 *       merged.left = mergeTrees(root1.left, root2.left);
 *       merged.right = mergeTrees(root1.right, root2.right);
 *       return merged;
 *   }
 * 
 *   - Creates new nodes instead of modifying existing
 *   - More memory usage but doesn't modify input
 *   - Useful if you need to preserve original trees
 * 
 * Edge Cases:
 * 
 *   - Both trees are null: Returns null
 *   - root1 is null: Returns root2 (entire tree)
 *   - root2 is null: Returns root1 (entire tree)
 *   - One tree is much larger: Only processes overlapping nodes
 *   - Trees have different structures: Merges where they overlap
 * 
 * Base Cases Explained:
 * 
 *   1. if (root1 == null) return root2;
 *      - root1 doesn't exist, use root2's subtree
 *      - Handles: root1 branch missing, root2 branch exists
 * 
 *   2. if (root2 == null) return root1;
 *      - root2 doesn't exist, keep root1's subtree
 *      - Handles: root1 branch exists, root2 branch missing
 * 
 *   3. If both exist: Merge values and recurse
 *      - Sum the values
 *      - Recursively merge children
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
    public TreeNode mergeTrees(TreeNode root1, TreeNode root2) {
        if (root1 == null)
            return root2;
        if (root2 == null)
            return root1;
        
        root1.val += root2.val;
        root1.left = mergeTrees(root1.left, root2.left);
        root1.right = mergeTrees(root1.right, root2.right);

        return root1;
    }
}

