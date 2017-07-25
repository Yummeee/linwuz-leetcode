/*
 * Maximum Depth of Binary Tree (Easy)
 * 
 * Given a binary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
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
 * 思路（时间复杂度都为O(n)）：
 * 1.递归求深度(DFS)，效率表现较差
 * 2.非递归方法(BFS)：可以用队列（这里用链表实现较好），用层次遍历的方法，并用一个int统计层数（速度比1还慢竟然，可能是链表操作时间代价比递归还大）
 * 3.后续遍历思想，求栈的最大深度（DFS）
 */
import java.util.*;
class TreeNode {
	int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) { val = x; }
}

public class question104 {
    public int maxDepth1(TreeNode root) {
        if(root == null) {
            return 0;
        }
        
        return Math.max(maxDepth1(root.left)+1, maxDepth1(root.right)+1);
    }
    
    public int maxDepth(TreeNode root) {
    	if(root == null) {
    		return 0;
    	}
    	
    	int level = 0;
    	Queue<TreeNode> queue = new LinkedList<>();			//no need to use front and rear pointer here, use size() <=> rear
    	
    	queue.add(root); 			//add the root of the tree
    	int cur_num = 1, next_num = 0;		//cur_num is the nodes number left in current level, next_num is the nodes number in next level
    	while(!queue.isEmpty()) {			//or size()
    		TreeNode front = queue.poll();	//retrieve and remove
    		cur_num--;
    		if(front.left != null) {
    			queue.offer(front.left);
    			next_num++;
    		}
    		if(front.right != null) {
    			queue.offer(front.right);
    			next_num++;
    		}
    		if(cur_num == 0) {			//finish one level
    			cur_num = next_num;
    			next_num = 0;
    			level++;
    		}
    	}
    	
    	return level;
    }
    public int maxDepth2(TreeNode root) {			//后序遍历DFS 
        if(root == null) {
            return 0;
        }
        
        Stack<TreeNode> stack = new Stack<>();
        TreeNode past;
        TreeNode p = root;
        int flag;
        int level = 0;
        do {
        	while(p!=null) {
        		stack.push(p);
        		p = p.left;
        	}
        	
        	level = Math.max(level, stack.size());  //只有入栈才会增加高度
        	past = null; 
        	flag = 1;
        	while(!stack.isEmpty() && flag==1) 
        	{
        		p = stack.peek();
	        	if(p.right == past) {			//p的右子树访问完，出栈
	        		stack.pop();
	        		past = p;
	        	}
	        	else {
	        		p = p.right;
	        		flag = 0;
	        	}
        	}
        } while(!stack.isEmpty());
        return level;
    }
}