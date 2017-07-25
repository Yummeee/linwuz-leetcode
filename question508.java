/*
 * Most Frequent Subtree Sum (Medium)
 * 
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is 
 * defined as the sum of all the node values formed by the subtree rooted at that node (including the node 
 * itself). So what is the most frequent subtree sum value? If there is a tie, return all the values with the 
 * highest frequency in any order.
 * 
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.
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
 * 任意顺序遍历一遍树，统计所有子树的结点和，存入一个hashmap中，建立一个子树和->出现次数的映射。同时在遍历过程中，统计出现最多的子树和次数。
 * 可选递归也可选非递归（非递归我目前想到只有后序有可能性），非递归需要注意子树的和的传递，这需要一个封装类来实现
 */
import java.util.*;
public class question508 {
	int max_num = 0;
    public int[] findFrequentTreeSum(TreeNode root) {				//递归
        HashMap<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        
        ArrayList<Integer> list = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == max_num) {
                list.add(entry.getKey());
            }
        }
        int[] res = new int[list.size()];
        /*
        for(int i = 0; i < res.length; i++) {
        	res[i] = list.get(i);
        	return res;
        }
        */
        return list.parallelStream().mapToInt(Integer::intValue).toArray();
    }
    
    public int helper(TreeNode root, HashMap<Integer, Integer> map) {
        if(root == null) {
            return 0;
        }
        int right = helper(root.right, map);
        int left = helper(root.left, map);
        int subtree_sum = right + root.val + left;              //also avoid overflow
        int times = map.getOrDefault(subtree_sum, 0) + 1;
        max_num = Math.max(times, max_num);
        map.put(subtree_sum, times);
        return subtree_sum;
    }
}
