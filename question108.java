/*
 * Convert Sorted Array to Binary Search Tree (Easy)
 * 
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
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
 * 由于没有平衡因子，因此一定不是用一般的AVL方法，通过二分法，每次取中间结点插入应该就是一棵平衡二叉树（哈哈哈哈哈老子终于第一次就时间超过了所有人！）
 * 1.分治思想，递归方法，时间复杂度O(n)，空间复杂度：辅助空间O(logn)、结果空间O(n)，时间超过了99%的人！
 * 2.将方法1转换为迭代算法，BFS或DFS均可，但是效率会比较低，因为有大量的栈或队列的操作。如果不新建一个类，封装当前结点为mid的上下边界值的话则需要维护3个栈或者队列
 * public class MyNode{
    TreeNode node;
    int start;
    int end;
    
    public MyNode(int start, int end, TreeNode node){
        this.start = start;
        this.end = end;
        this.node = node;
    }
}


public TreeNode sortedArrayToBST(int[] nums) {
    if(nums.length ==0 ) return null;
    
    Stack<MyNode> stack = new Stack<MyNode>();
    int mid = 0 + (nums.length -1 - 0)/2;
    TreeNode root = new TreeNode(nums[mid]);
    MyNode MyRoot = new MyNode(0, nums.length -1, root);
    stack.push(MyRoot);
    while(!stack.isEmpty()){
        MyNode curr = stack.pop();
        int oldMid = curr.start + (curr.end - curr.start)/2;
        if(oldMid -1 >= curr.start){
            mid = curr.start + (oldMid-1 - curr.start)/2;
            root = new TreeNode(nums[mid]);
            stack.push(new MyNode(curr.start, oldMid - 1, root));
            curr.node.left = root;
        }
        
        if(oldMid +1 <= curr.end){
            mid = oldMid +1 + (curr.end - oldMid -1)/2;
            root = new TreeNode(nums[mid]);
            stack.push(new MyNode(oldMid + 1, curr.end, root));
            curr.node.right = root;
        }    
    }
    
    return MyRoot.node;
}
 */
import java.util.*;
public class question108 {
	public TreeNode sortedArrayToBST1(int[] nums) {
        if(nums.length == 0) {
            return null;
        }
        
        TreeNode root = helper(nums, 0, nums.length-1);
        return root;
    }
    public TreeNode helper(int[] nums, int start, int end) {
        if(start > end) {
            return null;
        }
        if(start == end) {
            TreeNode root = new TreeNode(nums[start]);
            root.left = null;
            root.right = null;
            return root;
        }
        
        int mid = (start+end)>>1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, start, mid-1);
        root.right = helper(nums, mid+1, end);
        return root;
    }
    public TreeNode sortedArrayToBST(int[] nums) {
        if(nums.length == 0) {
            return null;
        }
        
        Stack<TreeNode> tree_stack = new Stack<>();
        Stack<Integer> left_edge = new Stack<>();
        Stack<Integer> right_edge = new Stack<>();
        
        TreeNode root = new TreeNode(0);
        root.left = null;
        root.right = null;
        tree_stack.push(root);
        left_edge.push(0);
        right_edge.push(nums.length-1);
        
        while(!tree_stack.isEmpty()) {
            int low = left_edge.pop();
            int high = right_edge.pop();
            TreeNode p = tree_stack.pop();
            
            int mid  = low + (high-low)/2; // avoid overflow
            p.val = nums[mid];
            if(mid+1 <= high) {
                p.right = new TreeNode(0);
                p.right.left = null;
                p.right.right = null;
                tree_stack.push(p.right);
                left_edge.push(mid+1);
                right_edge.push(high);
            }
            if(low <= mid-1) {      
                p.left = new TreeNode(0);
                p.left.left = null;
                p.left.right = null;
                tree_stack.push(p.left);
                left_edge.push(low);
                right_edge.push(mid-1);
            }
        }
        return root;
    }
}
