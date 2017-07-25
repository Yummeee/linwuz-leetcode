/*
 * Minimum Depth of Binary Tree (Easy)
 * 
 * Given a binary tree, find its minimum depth.
 * The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.
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
 * 思路：这题根本就不难，结果我脑子秀逗了错了这么多次...
 * 和普通的求树的最大高度的遍历不同，此题不能直接返回左右子树高度的较小值，一定要确保能到一个叶子结点。不然像[1,2]这种树，找到的长度不是到叶子结点的长度
 * DFS或BFS均可
 */
import java.util.*;
public class question111 {
	/*
	 * public int minDepth(TreeNode root) {  		//DFS递归的精简实现
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
       
    }
	 */
	public int minDepth1(TreeNode root) {				//DFS递归
        if(root == null) {
            return 0;
        }
        
        if(root.left != null) {
            if(root.right != null) {
                return Math.min(minDepth1(root.left)+1, minDepth1(root.right)+1); 
            }
            return minDepth1(root.left)+1; 
        }
        
        if(root.right !=null) {
            return minDepth1(root.right)+1;
        }
        return 1;           //leaves
    }
	
	public int minDepth2(TreeNode root) {				//BFS
        if(root == null) {
            return 0;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int level = 1;
        while(!queue.isEmpty()) {
        	int num = queue.size();					//也可以用cur_num. next_num而不用for循环来实现
            for(int i=0; i<num; i++) {
                TreeNode p = queue.poll();
                if(p.left==null && p.right==null) {
                    return level;
                }
                if(p.left != null) {
                    queue.offer(p.left);
                }
                if(p.right != null) {
                    queue.offer(p.right);
                }
            }
            level++;
        }
        return level;
    }
	
	public int minDepth3(TreeNode root) {		//DFS后序非递归，当叶子结点为栈顶时栈的大小就是根到叶子结点的距离
		if(root == null) {
            return 0;
        }
		
		Stack<TreeNode> stack = new Stack<>();
		int min = Integer.MAX_VALUE;
		TreeNode p = root, past = null;
		int flag = 0;
		do {
			while(p != null) {
				stack.push(p);
				p = p.left;
			}
			
			past = null;
			flag = 1;
			while(!stack.isEmpty() && flag==1) {
				p = stack.peek();
				if(p.left==null && p.right==null) {
					min = Math.min(min, stack.size());
				}
				
				if(p.right == past) {
					stack.pop();
					past = p;
				}
				else {
					p = p.right;
					flag = 0;
				}
			}
		} while(!stack.isEmpty());
		return min;
	}
	
	private class node {
		int height;
		TreeNode root;
		public node(TreeNode root, int height) {
			this.height = height;
			this.root = root;
		}
	}
	public int minDepth(TreeNode root) {			//DFS先序非递归，需要一个封装类，保存当前结点的高度，经过试验不需要是不行的，没有封装类而利用栈的size回溯不能满足根节点的左子树既有左孩子又有右孩子
		if(root == null) {
            return 0;
        }
		
		int min = Integer.MAX_VALUE, height = 0;
		Stack<node> stack = new Stack<>();
		TreeNode p = root;
		while(!stack.isEmpty() || p!=null) {
			while(p != null) {
				height++;
				stack.push(new node(p, height));
				p = p.left;
			}
			
			if(!stack.isEmpty()) {
				node temp = stack.pop();
				p = temp.root;
				if(p.right==null) {
					if(p.left==null) {
						min = Math.min(min, temp.height);
					}
				}
				height = temp.height;			//如果p有孩子，成立。如果p无孩子，还会继续退栈，不影响
				p = p.right;
			}
		}
		return min;
	}
}
