/*
 * Find Mode in Binary Search Tree (Easy)
 * 
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
   Assume a BST is defined as follows:
   The left subtree of a node contains only nodes with keys less than or equal to the node's key.
   The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
   Both the left and right subtrees must also be binary search trees.
 * 
 * 
 * Note: If a tree has more than one mode, you can return them in any order.
   Follow up: Could you do that without using any extra space? (Assume that the implicit stack space incurred due to recursion does not count).
 */
/*
 * 思路：此题的限制导致没有遍历一次树就能查出众数并空间O(1)的方法，我绞尽脑汁也不可能想出来...
 * 1.遍历BST(迭代、递归均可)，使用HashMap，用两个全局变量分别保存众数的个数和众数的出现次数，以便得到最后的结果。但是没有利用BST的性质，时间复杂度O(n)，空间复杂度O(n)
 * 2.中序遍历BST，将遍历的结果放入生成一个List，找到连续出现最多次的元素，时间复杂度O(n)，空间复杂度O(n)
 * 
 * 3.进行一个two passes，即遍历两次树，第一次确定有多少个众数，第二次去查众数都是哪些，这样才能保证除了输出以外，空间都是O(1)。
 * 根据BST的性质，相同的结点一定是连续遍历到的，因此只用保存先前访问的一个结点就可以判断众数的数量了，将结果保存为一个类变量，在完成第一次遍历后再初始化它，这样就可以区分第一次和第二次遍历
 */
import java.util.*;
public class question501 {
	/*
	int max_num = 0;			//当前出现的最多的元素的个数，最后即等于众数出现的次数
    int modes_num = 0;			//众数的个数
    public int[] findMode1(TreeNode root) {
        int[] ans = {};
        if(root == null) {
            return ans;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        traverse(map, root);
        ans = new int[modes_num];
        int count = 0;
        for(Map.Entry<Integer, Integer> each : map.entrySet()) {
            if(each.getValue() == max_num) {
                ans[count] = each.getKey();
                count++;
            }
        }
        return ans;
    }
    public void traverse(HashMap<Integer, Integer> map, TreeNode root) {
        if(root == null) {
            return;
        }
        
        Integer n = map.getOrDefault(root.val, 0);
        map.put(root.val, ++n);
        if(n > max_num) {
            max_num = n;
            modes_num = 1;
        }
        else if(n == max_num) {
            modes_num++;
        }
        traverse(map, root.left);
        traverse(map, root.right);
    }
    */
	int cur_val;                //当前正在访问的连续结点
    int cur_val_count = 0;      //当前正在访问的连续结点的数量
    int modes_num = 0;              //众数的数量
    int max_num = 0;            //当前出现的最多的数的数量
    int[] ans;                  //结果
    
    public int[] findMode(TreeNode root) {
        helper(root);
        ans = new int[modes_num];			//当root==null时相当于ans = {}，可以满足边界情况，而ans=null则不行
        modes_num = 0;
        cur_val_count = 0;
        helper(root);
        
        return ans;
    }
    
    public void helper(TreeNode root) {
        if(root == null) {
            return;
        }
        
        helper(root.left);
        
        if(cur_val == root.val) {
            cur_val_count++;
        }
        else {
            cur_val = root.val;
            cur_val_count = 1;
        }
        
        if(cur_val_count > max_num) {		//第二次执行时一定不会执行此语句
            modes_num = 1;
            max_num = cur_val_count;
        }
        else if(cur_val_count == max_num) {
            if(ans == null) {           	//用ans来判断是第一次遍历还是第二次，所以ans要设成类的变量
                modes_num++;
            }
            else {
                ans[modes_num++] = root.val;
            }
        }
        helper(root.right);
    }
}
