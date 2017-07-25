/*
 * Convert BST to Greater Tree (Medium)
 * 
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is 
 * changed to the original key plus sum of all keys greater than the original key in BST.
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
 * 根据BST的性质，中序遍历（优先遍历右孩子->根->左孩子）将是降序排列。已经访问的结点一定比当前结点大，而未访问的结点一定比当前结点小。
 * 1.我的想法是：对于一个结点，其修改后的值应为：本身的值加上右子树之和，加上来自祖先结点的比该点大的结点之和（当此结点在某一个左子树上）。但我的
 * 方法其实没有利用BST这个倒序排列的性质，只利用了右孩子都比根大还有一个位于左子树上的点比其所有祖先结点以及祖先结点的其余孩子结点都小。
 * 2.DFS中序非递归，用一个值保存目前访问过的所有结点之和，即为下一个要访问结点应该增加的值。用中序非递归则不用保存类属性
 */
import java.util.*;
public class question538 {
	int greater = 0;				//已访问结点之和，一定均是比当前结点还大的结点，未访问的，最后遍历完成为全部结点之和
	public TreeNode convertBST(TreeNode root) {				    //DFS中序非递归
		if(root == null) {
			return null;
		}
		
		int sum = 0;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode p = root;
		while(!stack.isEmpty() || p != null) {
		    while(p != null) {
		        stack.push(p);
		        p = p.right;
		    }
		    
		    p = stack.pop();
		    p.val += sum;
		    sum = p.val;
		    p = p.left;
		}
		return root;
	}
	
	public TreeNode convertBST2(TreeNode root) {				//思路2
		if(root == null) {
			return null;
		}
		
		convertBST(root.right);
		root.val += greater;
		greater = root.val;
		convertBST(root.left);
		return root;
	}
	public TreeNode convertBST1(TreeNode root) {			//思路1：有点麻烦有点绕
        helper(root, 0);
        return root;
    }
    
    public int helper(TreeNode root, int greater) {			//greater来自祖先结点的比该点大的结点之和（当此结点在某一个左子树上），返回以该点为根节点的BST的和
        if(root == null) {
            return 0;
        }
        int origin = root.val;
        int right = helper(root.right, greater);
        root.val += right + greater;
        int left = helper(root.left, root.val);
        return left + right + origin;
    }
}
