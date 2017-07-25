/*
 * Find Bottom Left Tree Value (Medium)
 * 
 * Given a binary tree, find the leftmost value in the last row of the tree.
 * Note: You may assume the tree (i.e., the given root node) is not NULL.
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
 * 1.BFS相当容易，一个变量不断保存当前遍历层的最左边结点，最后遍历完全就是想要的结果。
 * 2.DFS，遍历顺序均可。由于每一层的leftmost结点在任何一种顺序下都是这一层最先被访问的结点，因此我们的目标变成了找到最大层数的同时，记录第一次到最大层
 * 数的那个结点。当然也可以先找到树的最大高度，然后再次遍历一遍数，但是这样会遍历两次树，效率较差。我们可以用一个全局变量来保存当前遍历过的部分树的高度，
 * 一旦当前结点的层数大于这个值，则更新树的高度和结果为当前结点的值。遍历完一遍数后即可得到结果。先序和中序的非递归实现需要封装类，后序非递归则不需要。
 * 
 * BFS算法改进：机智啊这些人。当BFS从左向右遍历每层时，我们需要找到每一层的第一个结点，这就需要我们区分每一层。但是如果我们逆向思维，从右向左层次遍历呢？
 * 这时候最底层的leftmost结点一定是层次遍历的最后一个结点，我们不再管我们遍历到了哪一层，而是直接遍历一遍，最后的那个结点就是想要的结果。
 * public int findLeftMostNode(TreeNode root) {
    Queue<TreeNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        root = queue.poll();
        if (root.right != null)
            queue.add(root.right);
        if (root.left != null)
            queue.add(root.left);
    }
    return root.val;
}
 */
import java.util.*;
public class question513 {
	int height = 0;
    int dfs_ans = 0;
    
    public int findBottomLeftValue(TreeNode root) {         //DFS postorder iterative
        Stack<TreeNode> stack = new Stack<>();
        int flag = 0, ans = 0;
        TreeNode p = root, post = null;
        
        do {
            while(p != null) {
                stack.push(p);
                p = p.left;
            }
            
            flag = 1;
            post = null;
            while(!stack.isEmpty() && flag == 1) {
                p = stack.peek();
                if(stack.size() > height) {
                    height = stack.size();
                    ans = p.val;
                }
                if(p.right == post) {
                    stack.pop();
                    post = p;
                }
                else {
                    flag = 0;
                    p = p.right;
                }
            }
        } while(!stack.isEmpty());
        return ans;
    }
    
    
    public int findBottomLeftValue2(TreeNode root) {         //DFS postorder recursion，也可以将全局的height和ans当做一个数组参数传进去，这样就不用全局变量了
        helper(root, 0);
        return dfs_ans;
    }
    public void helper(TreeNode root, int length) {         //length表示从根节点到当前结点的前一个结点的高度
        if(root == null) {
            return;
        }
        
        helper(root.left, length + 1);						//显然采用递归方法，后序是最快的，因为其执行if语句中的语句的次数最少
        helper(root.right, length + 1);
        if(height < length + 1) {
            height = length + 1;
            dfs_ans = root.val;
        }
    }
    
    
	public int findBottomLeftValue1(TreeNode root) {         //BFS
        int ans = 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode front;
        while(!queue.isEmpty()) {
            int nodes = queue.size();
            ans = queue.peek().val;
            for(int i = 0; i < nodes; i++) {
            	front = queue.poll();		
                if(front.left != null) {
                    queue.offer(front.left);
                }
                if(front.right != null) {
                    queue.offer(front.right);
                }
            }
        }
        return ans;
    }
}
