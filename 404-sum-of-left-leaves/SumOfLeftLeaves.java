/**
 * LeetCode 404: Sum of Left Leaves
 * 
 * Problem: Given the root of a binary tree, return the sum of all left leaves.
 *          A leaf is a node with no children.
 *          A left leaf is a leaf that is the LEFT child of its parent.
 * 
 * Key Insight: We need to check TWO things for a left leaf:
 *              1. The node is a LEFT child of its parent
 *              2. The node is a LEAF (no children)
 *              We can only determine if a node is a "left child" from the parent!
 * 
 * Examples:
 *   Input: root = [3,9,20,null,null,15,7]
 *          
 *              3
 *             / \
 *            9   20
 *               /  \
 *              15   7
 *   
 *   Output: 24
 *   Explanation: Left leaves are 9 and 15. Sum = 9 + 15 = 24
 * 
 *   Input: root = [1]
 *   Output: 0
 *   Explanation: Root has no left child, so no left leaves.
 * 
 *   Input: root = [1,2,3,4,5]
 *              1
 *             / \
 *            2   3
 *           / \
 *          4   5
 *   Output: 4
 *   Explanation: Only node 4 is a left leaf.
 * 
 * Constraints:
 *   - Number of nodes: [1, 1000]
 *   - -1000 <= Node.val <= 1000
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

    /**
     * Calculates the sum of all left leaves in a binary tree.
     * 
     * @param root - The root of the binary tree
     * @return Sum of values of all left leaf nodes
     * 
     * Time Complexity: O(n) - visit each node once
     * Space Complexity: O(h) - recursion stack, h = tree height
     */
    public int sumOfLeftLeaves(TreeNode root) {
        /*
         * Base case: empty tree has no left leaves
         */
        if (root == null) {
            return 0;
        }

        /*
         * Start by recursively getting sum from right subtree
         * 
         * Why process right first? It's just a style choice.
         * We're accumulating the total sum, order doesn't matter.
         */
        int sum = sumOfLeftLeaves(root.right);

        /*
         * Now handle the left child
         * This is where the main logic happens!
         */
        if (root.left != null) {
            /*
             * Check if left child is a LEAF node
             * A leaf has no children (both left and right are null)
             */
            if (root.left.left == null && root.left.right == null) {
                /*
                 * Found a LEFT LEAF!
                 * Add its value directly to sum
                 * 
                 * Why check from parent? Because we need to know:
                 * 1. Is it a LEFT child? (yes, it's root.left)
                 * 2. Is it a LEAF? (yes, no children)
                 * Both conditions met → it's a left leaf!
                 */
                sum += root.left.val;
            } else {
                /*
                 * Left child is NOT a leaf (has children)
                 * Recurse into the left subtree to find left leaves there
                 */
                sum += sumOfLeftLeaves(root.left);
            }
        }

        return sum;
    }
}

/**
 * Usage Example:
 * 
 * Solution sol = new Solution();
 * 
 * // Build tree: [3,9,20,null,null,15,7]
 * TreeNode root = new TreeNode(3);
 * root.left = new TreeNode(9);
 * root.right = new TreeNode(20);
 * root.right.left = new TreeNode(15);
 * root.right.right = new TreeNode(7);
 * 
 * System.out.println(sol.sumOfLeftLeaves(root)); // Output: 24
 * 
 * ═══════════════════════════════════════════════════════════════
 * VISUAL WALKTHROUGH: [3,9,20,null,null,15,7]
 * ═══════════════════════════════════════════════════════════════
 * 
 *              3
 *             / \
 *            9   20
 *           (L)  / \
 *              15   7
 *             (L)
 * 
 * (L) = Left Leaf
 * 
 * Recursion trace:
 * 
 * sumOfLeftLeaves(3):
 *   sum = sumOfLeftLeaves(20)           → returns 15
 *   root.left = 9 exists
 *   9 is a leaf (no children)           → sum += 9
 *   return 15 + 9 = 24 ✓
 * 
 * sumOfLeftLeaves(20):
 *   sum = sumOfLeftLeaves(7)            → returns 0
 *   root.left = 15 exists
 *   15 is a leaf (no children)          → sum += 15
 *   return 0 + 15 = 15
 * 
 * sumOfLeftLeaves(7):
 *   sum = sumOfLeftLeaves(null)         → returns 0
 *   root.left = null                    → skip
 *   return 0
 * 
 * ═══════════════════════════════════════════════════════════════
 * WHY CHECK FROM PARENT NODE?
 * ═══════════════════════════════════════════════════════════════
 * 
 * A node cannot know if it's a "left child" by itself!
 * 
 *              3
 *             / \
 *            9   20
 * 
 * Node 9 doesn't know it's a "left" child.
 * Only node 3 (parent) knows that 9 is its LEFT child.
 * 
 * That's why we check from the parent:
 *   if (root.left != null && isLeaf(root.left))
 *       ↑ parent       ↑ checking left child
 * 
 * ═══════════════════════════════════════════════════════════════
 * EDGE CASES
 * ═══════════════════════════════════════════════════════════════
 * 
 * 1. Single node (root only): No left child → return 0
 * 
 * 2. Right-skewed tree:
 *        1
 *         \
 *          2
 *           \
 *            3
 *    No left leaves → return 0
 * 
 * 3. Left-skewed tree:
 *        1
 *       /
 *      2
 *     /
 *    3    ← This is a left leaf!
 *    return 3
 */

