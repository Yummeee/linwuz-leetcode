/*
 * Path Sum (Easy)
 * 
 * Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the 
 * path equals the given sum.
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
 * 思路：就是437题Path Sum III的退化版...
 * 递归或者非递归均可，如果选择非递归（几种遍历顺序均可）则需要回溯。如果使用后序，则不需要封装专门的类来保存到当前结点的路径结点和，如果是中序或先序则需要
 */
import java.util.*;
public class question112 {
	public boolean hasPathSum1(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        
        if(root.left==null && root.right==null) {
            return root.val == sum;
        }
        
        return hasPathSum1(root.left, sum-root.val) || hasPathSum1(root.right, sum-root.val);
    }
	
	public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) {
            return false;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root, past = null;
        int flag = 0, cur_sum = sum;
        do {
            while(p != null) {
            	cur_sum -= p.val;
                stack.push(p);
                p = p.left;
            }
            
            flag = 1;
            past = null;
            while(!stack.isEmpty() && flag==1) {
            	p = stack.peek();
                if(p.left==null && p.right==null && cur_sum == 0) {
                    return true;
                }
                if(p.right == past) {
                    stack.pop();
                    cur_sum += p.val;           //还原
                    past = p;
                }
                else {
                    p = p.right;
                    flag = 0;
                }
            }
        } while(!stack.isEmpty());
        
        return false;
    }
}
