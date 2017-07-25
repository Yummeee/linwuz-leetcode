/*
 * Diameter of Binary Tree (Easy)
 * 
 * Given a binary tree, you need to compute the length of the diameter of the tree. The diameter of a binary tree is the length of the longest
 * path between any two nodes in a tree. This path may or may not pass through the root.
 * 
 * Note: The length of path between two nodes is represented by the number of edges between them.
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
/*
 * 思路：
 * 此题实际上是一道求树的高度的问题，过每个结点的最大直径长度实际上就等于左子树高度加右子树高度
 * 递归求解即可
 */
public class question543 {
	public int max_length = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        if(root == null) {
            return 0;
        }
        treeHeight(root);
        return max_length;
    }
    public int treeHeight(TreeNode root) {
        if(root == null) {
            return 0;
        }
        int left_length = diameterOfBinaryTree(root.left);
        int right_length = diameterOfBinaryTree(root.right);
        int length = left_length + right_length;			//这时没算根节点，所以不能-1
        if(length > max_length) {
            max_length = length;
        }
        return Math.max(left_length, right_length)+1;
    }
}
