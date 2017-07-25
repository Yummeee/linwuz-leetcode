/*
 * Symmetric Tree (Easy)
 * 
 * Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
 * 
 * Note:
   Bonus points if you could solve it both recursively and iteratively.
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
 * 一开始完全想错了，想利用中序遍历找对称，但是这是一个充分非必要条件，所以不能这样做。包括一般的从左到右的层次遍历，每一层像数组一样判断对称也会由于null的存在导致错误
 * 实际上，对称要满足，root的左右子树是互相的mirror
 * Two trees are a mirror reflection of each other if:
 * Their two roots have the same value.
 * The right subtree of each tree is a mirror reflection of the left subtree of the other tree.
 * 
 * 可以用递归DFS，非递归DFS，非递归BFS做，而这些遍历都不是遵循先、中、后序遍历，无所谓遍历方式
 * 
 * 还有一种很巧妙的解法：如果后序遍历和中序遍历的结果正好相反，则说明是对称的
 */
import java.util.*;
public class question101 {
	public boolean isSymmetric1(TreeNode root) {			//递归DFS
        if(root == null) {
            return true;
        }
        return helper(root.left, root.right);
    }
    public boolean helper(TreeNode left, TreeNode right) {
        if(left==null || right==null) {
            return left==right;
        }
        if(left.val != right.val) {
            return false;
        }
        return helper(left.left, right.right) && helper(left.right, right.left);
    }
    
    public boolean isSymmetric2(TreeNode root) {				//非递归DFS
        if(root == null) {
            return true;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root.left);
        stack.push(root.right);
        while(!stack.isEmpty()) {
            TreeNode right = stack.pop();
            TreeNode left = stack.pop();
            if(left==null || right==null) {
                if(left==right) {
                    continue;
                }
                return false;
            }
            if(left.val != right.val) {
                return false;
            }
            stack.push(left.left);
            stack.push(right.right);
            stack.push(left.right);
            stack.push(right.left);
        }
        return true;
    }
    public boolean isSymmetric(TreeNode root) {			//非递归BFS
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode t1 = q.poll();
            TreeNode t2 = q.poll();
            if (t1 == null && t2 == null) continue;
            if (t1 == null || t2 == null) return false;
            if (t1.val != t2.val) return false;
            q.add(t1.left);
            q.add(t2.right);
            q.add(t1.right);
            q.add(t2.left);
        }
        return true;
    }
}
