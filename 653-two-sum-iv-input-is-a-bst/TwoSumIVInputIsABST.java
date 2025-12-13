/*
 * LeetCode 653: Two Sum IV - Input is a BST
 * 
 * Problem:
 * Given the root of a Binary Search Tree and a target number k,
 * return true if there exist two elements in the BST such that
 * their sum equals k.
 * 
 * Approach: DFS + HashSet (Two Sum Pattern)
 * 
 * Key Insight:
 * - This is the classic Two Sum problem applied to a tree
 * - For each node, check if (k - node.val) was seen before
 * - Use a HashSet to store visited values for O(1) lookup
 * 
 * Why HashSet Works:
 * - If we need a + b = k, then b = k - a
 * - When visiting node with value 'a', check if 'k - a' exists in set
 * - If yes, we found a pair!
 * - If no, add 'a' to set for future nodes to find
 * 
 * Algorithm:
 * 1. DFS traversal of the tree
 * 2. At each node:
 *    - Check if complement (k - node.val) is in HashSet
 *    - If yes, return true (found a pair)
 *    - If no, add current value to HashSet
 * 3. Recursively check left and right subtrees
 * 4. Return false if no pair found
 * 
 * Time Complexity: O(n) - visit each node once
 * Space Complexity: O(n) - HashSet + recursion stack
 * 
 * Example: k = 9
 * 
 *        5
 *       / \
 *      3   6
 *     / \   \
 *    2   4   7
 * 
 * DFS Traversal:
 * 1. Visit 5: complement = 9-5 = 4, set = {} → not found, add 5
 *    set = {5}
 * 2. Visit 3: complement = 9-3 = 6, set = {5} → not found, add 3
 *    set = {5, 3}
 * 3. Visit 2: complement = 9-2 = 7, set = {5,3} → not found, add 2
 *    set = {5, 3, 2}
 * 4. Visit 4: complement = 9-4 = 5, set = {5,3,2} → FOUND! 5 exists!
 *    Return true (4 + 5 = 9)
 * 
 * Visual (Two Sum Pattern):
 * 
 *   Target k = 9
 *   
 *   Current node: 4
 *   Complement: 9 - 4 = 5
 *   
 *   HashSet: {5, 3, 2}
 *             ↑
 *             5 is here!
 *   
 *   Found pair: 4 + 5 = 9 ✓
 * 
 * Note: This solution works for any binary tree, not just BST.
 * For BST-specific optimization, could use two-pointer with inorder traversal.
 */

import java.util.HashSet;
import java.util.Set;

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

    public boolean findTarget(TreeNode root, int k) {

        // HashSet to store values we've seen during traversal
        Set<Integer> visited = new HashSet<>();

        return dfs(root, k, visited);

    }

    /**
     * DFS helper that searches for a pair summing to k.
     * Uses HashSet to track visited values for O(1) complement lookup.
     */
    private boolean dfs(TreeNode node, int k, Set<Integer> visited) {

        // Base case: null node
        if (node == null) {

            return false;

        }

        // Calculate the complement we're looking for
        // If node.val + complement = k, then complement = k - node.val
        int complement = k - node.val;

        // Check if the complement has been visited
        // If yes, we found two nodes that sum to k!
        if (visited.contains(complement)) {

            return true;

        }

        // Add the current node's value to the visited set
        // Future nodes can find this as their complement
        visited.add(node.val);

        // Recursively search in left and right subtrees
        // Return true if either subtree finds a valid pair
        return dfs(node.left, k, visited) || dfs(node.right, k, visited);

    }

}

