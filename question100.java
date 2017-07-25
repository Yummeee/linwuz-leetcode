/*
 * Same Tree (Easy)
 * 
 * Given two binary trees, write a function to check if they are equal or not.
 * Two binary trees are considered equal if they are structurally identical and the nodes have the same value.
 */
/*
 * 思路：
 * 1.递归DFS即可，时间复杂度O(n)
 * 2.使用任何一种遍历方式，用一个栈或队列同时对两棵数进行遍历，两棵树同步进行操作，就是效率会明显底下
 * public boolean isSameTree(TreeNode p, TreeNode q) {		//Preorder
	     Stack<TreeNode> stack = new Stack<>();       
	     stack.push(p);
	     stack.push(q);
	     
	     while (!stack.isEmpty()) {
	    	 TreeNode qn = stack.pop() ;
	    	 TreeNode pn = stack.pop() ;
	    	 if(pn==null && qn==null) {
	    		 continue;
	    	 }
	    	 if(pn==null || qn==null) {
	    		 return false;
	    	 }
	    	 if (pn.val != qn.val) {
	    		 return false;
	    	 }
	    	 stack.push(pn.right);
	    	 stack.push(qn.right);
	    	 stack.push(pn.left);
	    	 stack.push(qn.left);
	     }		     
	     return true;	 
	 }
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
import java.util.*;
public class question100 {
	public boolean isSameTree1(TreeNode p, TreeNode q) {
        if(p==null && q==null) {
            return true;
        }
        if(p==null || q==null) {
            return false;
        }
        
        return (p.val==q.val? true:false) && isSameTree1(p.left, q.left) && isSameTree1(p.right, q.right);
        //return p.val==q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
	
	public boolean isSameTree(TreeNode p, TreeNode q) {
	     Stack<TreeNode> stack = new Stack<>();       
	     stack.push(p);
	     stack.push(q);
	     
	     while (!stack.isEmpty()) {
	    	 TreeNode qn = stack.pop() ;
	    	 TreeNode pn = stack.pop() ;
	    	 if(pn==null && qn==null) {
	    		 continue;
	    	 }
	    	 if(pn==null || qn==null) {
	    		 return false;
	    	 }
	    	 if (pn.val != qn.val) {
	    		 return false;
	    	 }
	    	 stack.push(pn.right);
	    	 stack.push(qn.right);
	    	 stack.push(pn.left);
	    	 stack.push(qn.left);
	     }		     
	     return true;	 
	 }
}
