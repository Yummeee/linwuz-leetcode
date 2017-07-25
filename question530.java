/*
 * Minimum Absolute Difference in BST (Easy)
 * 
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * Note: There are at least two nodes in this BST.
 */
/*
 * 思路：
 * 1.递归方法，找当前结点对应的最小差（一定发生顺序相邻的两个结点间，即每一个结点右子树的最左下结点或左子树的最右下结点），并与子结点的最小差比较，时间复杂度
 * O(n)，空间复杂度O(n)(考虑到栈)；
 * 2.任何一种遍历方式BST，然后判断当前结点与序列中前驱或后继结点的最小差值，思路与1基本一致；
 * 3.中序遍历(递归或非递归)一定是从小到大进行遍历的，直接每访问一个结点的值时计算与前一个访问结点的差并记录下最小的差，时间复杂度O(n)，空间复杂度O(1)，速度
 * 和1一致;
 * 
 * 注：此题若不是BST的话，可借助TreeSet，任何一种遍历方式把结点存入TreeSet，并借助自带函数找到当前结点在目前TreeSet中的上下界，这个时间复杂度是LogN，一共是NLogN
 * public class Solution {
     int min = Integer.MAX_VALUE;
     TreeSet<Integer> ts = new TreeSet<Integer>();     

     public int getMinimumDifference(TreeNode root) {
         if(root == null){
             return min;
         }
         
         if(!ts.isEmpty()){
             if(ts.floor(root.val) != null){
                 min = Math.min(min, root.val-ts.floor(root.val));
             }
             if(ts.ceiling(root.val) != null){
                 min = Math.min(min, ts.ceiling(root.val)-root.val);
             }
         }
         ts.add(root.val);
         
         getMinimumDifference(root.left);
         getMinimumDifference(root.right);
         return min;
     }
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
public class question530 {
	public int getMinimumDifference1(TreeNode root) {
        //recursion exit
        if(root == null || (root.left==null && root.right==null)) {
            return java.lang.Integer.MAX_VALUE;
        }

        int root_value = root.val;
        TreeNode p = root.left;
        int difference_left = 0;
        if(p != null) {
            while(p.right != null) {
                p = p.right;
            }
            difference_left = root_value - p.val;
        }
        else {
            difference_left = java.lang.Integer.MAX_VALUE;
        }
        
        TreeNode q = root.right;
        int difference_right = 0;
        if(q != null) {
            while(q.left != null) {
                q = q.left;
            }
            difference_right = q.val - root_value;
        }
        else {
            difference_right = java.lang.Integer.MAX_VALUE;
        }
        int absolute_root = Math.min(difference_left, difference_right);
        int absolute_left = getMinimumDifference1(root.left);
        int absolute_right = getMinimumDifference1(root.right);
        return Math.min(Math.min(absolute_root, absolute_left), absolute_right);
    }
	
	 public int getMinimumDifference(TreeNode root) {
	        Stack<TreeNode> stack = new Stack<>();
	        TreeNode p = root;
	        int pre = -1;
	        int ans = java.lang.Integer.MAX_VALUE;
	        while(!stack.isEmpty() || p!=null) {
	            while(p != null){
	                stack.push(p);
	                p = p.left;
	            }
	            
	            if(!stack.isEmpty()) {
	                p = stack.pop();
	                if(pre != -1) {
	                	ans = Math.min(ans,p.val-pre);
	                }
	                pre = p.val;
	                p = p.right;
	            }
	        }
	        return ans;
	    }
	 
	public static void main(String[] args) {
		int a = java.lang.Integer.MAX_VALUE;
		int b = 1;
		System.out.println(Math.min(a, b));
	}
}
