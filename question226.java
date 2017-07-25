/*
 * Invert Binary Tree (Easy)
 * 
 * Invert a binary tree. (就是翻转二叉树)
 */

/*
 * 思路：
 * 1.使用递归即可，注意一点，由于没有构建新树，要对左子树或右子树先保存一个副本！！！（属于DFS）
 * 2.非递归实现，层次遍历BFS，每次交换左右根节点
 * 3.DFS用栈，后序遍历，左右子树都交换完后处理根节点，符合后序遍历逻辑
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
public class question226 {
	public TreeNode invertTree1(TreeNode root) {			//recursive DFS
        if(root == null) {
            return null;
        }
        TreeNode temp = root.left;
        root.left = invertTree1(root.right);
        root.right = invertTree1(temp);
        return root;
    }
	
	public TreeNode invertTree2(TreeNode root) {			//level order BFS
        if(root == null) {
            return null;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode front = null;
        while(!queue.isEmpty()) {
        	front = queue.poll();
        	if(front.left != null) {
        		queue.offer(front.left);
        	}
        	if(front.right != null) {
        		queue.offer(front.right);
        	}
        	TreeNode temp = front.left;
        	front.left = front.right;
        	front.right = temp;
        }
        return root;
    }
	
	public TreeNode invertTree3(TreeNode root) {			//后序遍历DFS 
        if(root == null) {
            return null;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode past;
        TreeNode p = root;
        int flag;
        do {
        	while(p!=null) {
        		stack.push(p);
        		p = p.left;
        	}
        	
        	past = null; 
        	flag = 1;
        	while(!stack.isEmpty() && flag==1) 
        	{
        		p = stack.peek();
	        	if(p.right==past) {			//p的左右子树均交换好，进行交换
	        		TreeNode temp = p.left;
	        		p.left = p.right;
	        		p.right = temp;
	        		stack.pop();
	        		past = p;
	        	}
	        	else {
	        		p = p.right;
	        		flag = 0;
	        	}
        	}
        } while(!stack.isEmpty());
        return root;
    }
}
