/*
 * Binary Tree Level Order Traversal II (Easy)
 * 
 * Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf 
 *  to root).
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
 * 1.使用层次遍历，使用两个指针cur_num和next_num来判断当前层的结点遍历完没有
 * 或者像这样，一次把queue中的所有结点全部清出来，一个queue中的所有结点就是一层的所有结点：
 *  while (!queue.isEmpty()) {
            int size = queue.size();
            ArrayList<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode head = queue.poll();
                level.add(head.val);
                if (head.left != null) {
                    queue.offer(head.left);
                }
                if (head.right != null) {
                    queue.offer(head.right);
                }
            }
            result.add(level);
        }
 *
 * DFS先序遍历也可以，只不过需要确定每个结点的层数，然后再返回的List中找到那一层的位置，再对其中的List加元素
 * public List<List<Integer>> levelOrderBottom(TreeNode root) {
            List<List<Integer>> wrapList = new LinkedList<List<Integer>>();
            levelMaker(wrapList, root, 0);
            return wrapList;
        }
        
        public void levelMaker(List<List<Integer>> list, TreeNode root, int level) {
            if(root == null) return;
            if(level >= list.size()) {
                list.add(0, new LinkedList<Integer>());			//出现了新的一层
            }
            levelMaker(list, root.left, level+1);
            levelMaker(list, root.right, level+1);
            list.get(list.size()-level-1).add(root.val);
        }
 */
import java.util.*;
public class question107 {
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		List<List<Integer>> ans = new LinkedList<>();
        if(root == null) {
            return ans;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        List<Integer> level = new ArrayList<>();
        queue.offer(root);
        TreeNode front = null;
        int cur_num = 1, next_num = 0;
        
        while(!queue.isEmpty()) {
            front = queue.poll();
            level.add(front.val);
            cur_num--;
            if(front.left != null) {
                next_num++;
                queue.offer(front.left);
            }
            if(front.right != null) {
                next_num++;
                queue.offer(front.right);
            }
            if(cur_num == 0) {
                cur_num = next_num;
                next_num = 0;
                ans.add(0, level);
                level = new ArrayList<>();
            }
        }
        return ans;
    }
}
            
