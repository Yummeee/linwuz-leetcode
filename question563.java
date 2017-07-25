/*
 * Binary Tree Tilt (Easy)
 * 
 * Given a binary tree, return the tilt of the whole tree.
 * The tilt of a tree node is defined as the absolute difference between the sum of all left subtree node values and the sum of all right 
 * subtree node values. Null node has tilt 0.
 * The tilt of the whole tree is defined as the sum of all nodes' tilt.
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
 * 这道题本质上是求左子树和右子树所有结点之和，后续遍历符合逻辑。
 * 使用递归遍历求左右子树结点之和，再求当前结点的tilt值，时间复杂度O(n)，空间复杂度最坏情况下O(n)，一般是O(logn)，因为栈中最大高度即为树的高度
 * 此题不适合使用后续遍历非递归算法，因为你很难确定是左子树还是右子树，一个结点的右子树很可能是另一个更高结点的左子树
 */
public class question563 {
	int tilt = 0;
	public int findTilt1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        sum(root);
        return tilt;
    }
    public int sum(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        if(root.left==null && root.right==null) {
            return root.val;
        }
        int sum_left = sum(root.left);
        int sum_right = sum(root.right);
        tilt += Math.abs(sum_left - sum_right);
        return sum_left+sum_right+root.val;
    }
}
