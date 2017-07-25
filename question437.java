/*
 * Path Sum III (Easy)
 * 
 * You are given a binary tree in which each node contains an integer value.
   Find the number of paths that sum to a given value.
   The path does not need to start or end at the root or a leaf, but it must go downwards (traveling only from parent nodes to child nodes).
   The tree has no more than 1,000 nodes and the values are in the range -1,000,000 to 1,000,000.
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
 * 1.递归遍历(此题较为适合先序)，真他妈绕，需要确定当前的点能不能做一个新路径的起点，这需要一个额外的判断变量，而能做起点的点有四种路径选择，不能做起点的点只有两种选择
 * DFS的另一种递归方式（实际上和我的方法大同小异，但是更直观）：时间复杂度最坏O(n^2)，树为一条直线，一般为O(nlogn)
 * pathSum(root, sum)代表的是寻找以root为根节点的树中长度为sum的路径个数，则此问题可以递归地定义为：对于一棵以root为根节点的树，其pathSum(root, sum) = pathSum(root.left, sum)+
 * pathSum(root.right, sum) + 以root为起点的长度为sum的路径个数(findPath(root, sum))
 * 然后findPath(root, sum)也是一个递归问题
 * public int pathSum(TreeNode root, int sum) {
        if(root == null)
            return 0;
        return findPath(root, sum) + pathSum(root.left, sum) + pathSum(root.right, sum);
    }
    
    public int findPath(TreeNode root, int sum){
        int res = 0;
        if(root == null)
            return res;
        if(sum == root.val)
            res++;
        res += findPath(root.left, sum - root.val);
        res += findPath(root.right, sum - root.val);
        return res;
    }
 *
 *  
 *  2.有一个超巧妙的方法，将其转化为一个类似Two Sum的问题，看了几遍愣是没看懂！太巧了！此方法每个节点只访问一次（先序遍历），所以时间复杂度降为O(n)，辅助空间复杂度平均O(logn)，因为栈中包括preSum中每个时刻最多保存的是从根到某一个叶子结点的轨迹
 *  这个方法本质上是通过查找根节点到每一个叶子结点(先序遍历到每一个最底部的结点)的路径上所有可能满足要求的子路径数量，累加得到这个值
 *  public int pathSum(TreeNode root, int sum) {			//我后面将此算法转换成了非递归，类似题108构建平衡二叉排序树，构建了一个辅助类用于退栈时还原
        HashMap<Integer, Integer> preSum = new HashMap();
        preSum.put(0,1);			//root结点到自己长度为0，0长度路径数量+1
        return helper(root, 0, sum, preSum);
    }
    
    public int helper(TreeNode root, int currSum, int target, HashMap<Integer, Integer> preSum) {		//preSum记录的是从根节点(绝对根节点，即root)到某一个分支（根节点到一个叶子节点）的路径上每一个结点所经历的路径的权值和，因为是先序遍历
        if (root == null) {					//currSum表示根节点（绝对根节点，此方法就以根节点为基准，而不是由若干个相对根节点）到当前结点的路径权值之和
            return 0;
        }
        
        currSum += root.val;
        int res = preSum.getOrDefault(currSum - target, 0);			//取出结果不唯一则说明在根节点到当前结点的路径上有至少一个子路径长度满足要求，而这个子路径可以是根节点为起点，也可以不是根节点为起点。因为currSum-target = k，k为根节点到该节点路径权值和，则target = currSum-k，即符合题意的路径是从k对应结点到当前结点的路径
        preSum.put(currSum, preSum.getOrDefault(currSum, 0) + 1);
        
        res += helper(root.left, currSum, target, preSum) + helper(root.right, currSum, target, preSum);
        preSum.put(currSum, preSum.get(currSum) - 1);		//退掉该结点，以免其影响自己的父亲结点的兄弟结点的查找
        return res;
    }
 *   
 */
import java.util.*;
public class question437 {
	int path_num = 0;
    public int pathSum1(TreeNode root, int sum) {
        helper(root, sum, false);
        return path_num;
    }
    public void helper(TreeNode root, int sum, boolean flag) {
        if(root == null) {
            return;
        }
        
        if(sum-root.val == 0) {
            path_num++;
        }
        if(flag == true) {						//flag == true，说明此点不是路径上的起点，只能继续像左右孩子传递这个路径
            helper(root.left, sum-root.val, true);
            helper(root.right, sum-root.val, true);
        }
        else {
            helper(root.left, sum, false);		//flag == false，则说明此点可以是路径上的起点，也可以不在路径中
            helper(root.right, sum, false);
            helper(root.left, sum-root.val, true);
            helper(root.right, sum-root.val, true);
        }
        
    }
    
    private class node {		//类似题108构建平衡二叉排序树，构建了一个辅助类用于退栈时还原
    	TreeNode root;
    	boolean visit;
    	int curSum;
    	public node(TreeNode root, int curSum, boolean flag) {
    		this.root = root;
    		this.visit = flag;
    		this.curSum = curSum;
    	}
    }
    public int pathSum(TreeNode root, int sum) {				//将那个Two Sum方法改成非递归算法，由于是先序遍历，因此可以很轻易地改成先序遍历非递归，但是由于栈操作效率应该会低
    	int ans = 0;
    	if(root == null) {
    		return ans;
    	}
        
    	HashMap<Integer, Integer> preSum = new HashMap<>();
    	Stack<node> stack = new Stack<>();
    	int curSum = 0;
    	stack.push(new node(root, curSum, false));
    	preSum.put(0, 1);
    	
    	while(!stack.isEmpty()) {
    		node p = stack.peek();
    		if(p.visit == true) {			//该结点已被访问过，需要退出以免其影响自己的父亲结点的兄弟结点的查找
    			stack.pop();
    			preSum.put(p.curSum, preSum.get(p.curSum)-1);
    			continue;
    		}
    		
    		p.visit = true;
    		curSum = p.curSum+p.root.val;
    		p.curSum = curSum;
    		ans += preSum.getOrDefault(curSum - sum, 0);
    		preSum.put(curSum, preSum.getOrDefault(curSum, 0)+1);
    		if(p.root.right != null) {
    			stack.push(new node(p.root.right, curSum, false));
    		}
    		if(p.root.left != null) {
    			stack.push(new node(p.root.left, curSum, false));
    		}
    	}
    	
        return ans;
    }
}
