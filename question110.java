/*
 *  Balanced Binary Tree (Easy)
 *  
 *  Given a binary tree, determine if it is height-balanced.
 *  For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of 
 *  every node never differ by more than 1.
 */
/*
 * 思路：
 * 利用DFS求解树的高度，对每一个根节点都判断其左右子树高度之间是否相差1，且最好采用子下而上的方式，即计算数的高度的同时进行平衡性判断。从上而下则会导致每个结点都被访问多次，平均时间复杂度O(nlogn)
 * 1.我的思路是将结果保存为一个类变量，在计算高度时若某个结点的左右子树不平衡，则将结果修改为假。缺点是在得到结果后（假）不能立即返回，还是要对整棵树求高度。
 * 2.将左右子树不平衡的树的高度设为负数，这样随后只要遇到就返回，避免了访问那些无关紧要的结点。
 * public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        return helper(root) != -1;
    }
    
    public int helper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left_depth = helper(root.left);
        if(left_depth == -1) {
        	return -1;
        }
        int right_depth = helper(root.right);
        if(right_depth == -1) {
        	return -1;
        }
        if(Math.abs(left_depth - right_depth) > 1) {
            return -1;
        }
        return Math.max(left_depth+1, right_depth+1);
    }
 */
public class question110 {
	boolean ans = true;
    public boolean isBalanced(TreeNode root) {
        if(root == null) {
            return true;
        }
        helper(root);
        return ans;
    }
    
    public int helper(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        int left_depth = helper(root.left);
        int right_depth = helper(root.right);
        if(Math.abs(left_depth - right_depth) > 1) {
            ans = false;
        }
        return Math.max(left_depth+1, right_depth+1);
    }
}
