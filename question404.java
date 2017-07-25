/*
 * Sum of Left Leaves (Easy)
 * 
 * Find the sum of all left leaves in a given binary tree.
 */
/*
 * 思路（如果只有根节点则为0，根节点不是叶子节点！！！！）：
 * 1.递归遍历树，统计左叶子节点的和即可，下面是一个不用指针指明该结点时左孩子还是右孩子的算法：
 * public int sumOfLeftLeaves(TreeNode root) {
    if(root == null) return 0;
    int ans = 0;
    if(root.left != null) {
        if(root.left.left == null && root.left.right == null) ans += root.left.val;			//左孩子是叶子结点
        else ans += sumOfLeftLeaves(root.left);												//继续在左孩子找
    }
    ans += sumOfLeftLeaves(root.right);														//在右孩子找
    
    return ans;
}
   将此方法改为迭代法：
   public int sumOfLeftLeaves(TreeNode root) {
    if(root == null) return 0;
    int ans = 0;
    Stack<TreeNode> stack = new Stack<TreeNode>();
    stack.push(root);
    
    while(!stack.empty()) {
        TreeNode node = stack.pop();
        if(node.left != null) {
            if (node.left.left == null && node.left.right == null)
                ans += node.left.val;
            else
                stack.push(node.left);
        }
        if(node.right != null) {
            if (node.right.left != null || node.right.right != null)
                stack.push(node.right);
        }
    }
    return ans;
}
 * 2.层次遍历BFS（若左孩子是叶节点，直接加，否则加入队列；只有当右孩子不是叶节点时才加入队列）
 * 3.任何一种有序遍历均可（若左孩子是叶节点，直接加，否则加入栈；只有当右孩子不是叶节点时才加入栈）
 * 需要注意要判断一个结点时左孩子还是右孩子，一般通过父母来确定，而不是通过孩子来确定
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
public class question404 {
	 public int sumOfLeftLeaves1(TreeNode root) {
	        return sum(root, 0);				//如果只有根节点，则为0！！！！
	    }
	 public int sum(TreeNode root, int left) {
        if(root == null) {
            return 0;
        }
        if(root.left==null && root.right==null) {
            if(left == 1) {
                return root.val;
            }
            return 0;
        }
        return sum(root.left, 1)+sum(root.right, 0);
    }
	
	 public int sumOfLeftLeaves(TreeNode root) {             //BFS
        if(root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int ans = 0;
        while(!queue.isEmpty()) {
            TreeNode front = queue.poll();
            if(front.left != null) {                    
                if(front.left.left==null && front.left.right==null) {   //left tree is a leaf node
                    ans += front.left.val;
                }
                else {
                    queue.offer(front.left);
                }
            }
            if(front.right != null) {
                if(front.right.left!=null || front.right.right!=null) {     //only when right tree is not a leaf node
                    queue.offer(front.right);
                }
            }
        }
        return ans;
    }    
}
 