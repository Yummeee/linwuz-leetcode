/*
 * Find Largest Value in Each Tree Row (Medium)
 * 
 * You need to find the largest value in each row of a binary tree.
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
 * 1.典型的BFS问题
 * 2.DFS，每遇到一个新层的第一个结点，将其值加入到队列中，若遇到该层的又一个结点，则和该结点比较。记录层数的方法可以用递归传参数，DFS目前我感觉只适合先序
 * 遍历，因为后续和中序，虽然我知道目前是第几层的结点，但是没办法保证结果的顺序是按照层数从小到大自动排列的，这在插入或者寻找时比较困难。当然你可以先统计层数
 * 然后直接做一个层数大小的数组...
 */
import java.util.*;
public class question515 {
	public List<Integer> largestValues(TreeNode root) {				//DFS先序递归算法
		List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        
        helper(root, 0, ans);
        return ans;
	}
	
	public void helper(TreeNode root, int level, List<Integer> ans) {
	    if(root == null) {
	        return;
	    }
	    
	    if(level == ans.size()) {
	        ans.add(root.val);
	    }
	    else {
	        ans.set(level, Math.max(ans.get(level), root.val));
	    }
	    
	    helper(root.left, level + 1, ans);
	    helper(root.right, level + 1, ans);
	}
	
	public List<Integer> largestValues1(TreeNode root) {			//BFS算法
		List<Integer> ans = new ArrayList<>();
        if(root == null) {
            return ans;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        TreeNode front;
        queue.offer(root);
        while(!queue.isEmpty()) {
            int level_size = queue.size(), level_max = Integer.MIN_VALUE;
            for(int i = 0; i < level_size; i++) {
                front = queue.poll();
                if(front.val > level_max) {
                    level_max = front.val;
                }
                if(front.left != null) {
                    queue.offer(front.left);
                }
                if(front.right != null) {
                    queue.offer(front.right);
                }
            }
            ans.add(level_max);
        }
        return ans;
    }
}
