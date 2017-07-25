/*
 * Subtree of Another Tree (Easy)
 * 
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a 
 * subtree of s. A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also 
 * be considered as a subtree of itself.
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
 * 思路：这道题比较适合用recursive的方法来做，因为两棵树相等的检查方式是递归的。当然也可以用非递归的算法来判断两棵树是否是相同的，如果用一个栈或者队列，一次
 * 需要pop两个元素；否则使用两个栈或两个队列
 * 1.首先判断树s和树t是否完全相同，若相同则直接返回true；否则，分别判断t和s.left以及s.right以及它们的左右子树是否有可能相同。此方法遍历方式不限，递归与否
 * 不限
 * 
 * 编辑解法：
 * Approach #1 Check All Subtrees [Accepted] 和我的思路1本质上是一样的，都是和s的所有子树比，只不过此方法是通过比较先序遍历的序列(string)来判断，每个点只被遍历一次，而我是利用判断函数来判断，且可以提前结束（||运算的性质），但一个点可能遍历多次
 * One of the simplest methods to solve this problem is to find all the subtrees of the given tree s and to check if t is one
 * of them. In order to determine the subtrees of the given tree s, we make use of the representation in terms of preorder 
 * traversal consisting of space separated values of the nodes.
 * public class Solution {
    HashSet < String > trees = new HashSet < > ();
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree = preorder(t, true);
        findAllSubTrees(s, true);
        return trees.contains(tree);
    }
    public String findAllSubTrees(TreeNode s, boolean left) {			//preorder变种，添加了加入set的环节
        if (s == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        String val = s.val + " " + findAllSubTrees(s.left, true) + " " + findAllSubTrees(s.right, false);
        trees.add(val);
        return val;
    }
    public String preorder(TreeNode t, boolean left) {
        if (t == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        return t.val + " " + preorder(t.left, true) + " " + preorder(t.right, false);
    }
}
Time complexity : O(m^2+n^2). A total of n nodes of the tree s and m nodes of tree t are traversed. Assuming string concatenation takes O(k) time for strings of length k.
Space complexity : O(n^2). In worst case(skewed tree) hashset size grows up to O(n). Here, n refers to the number of characters in the hashset.

 * Approach #2 Using Preorder Traversal [Accepted]
 * 只要对s和t做preorder，然后查看t的先序序列是不是s的子串就可以了
 * But, in order to use this approach, we need to treat the given tree in a different manner. Rather than assuming a null 
   value for the childern of the leaf nodes, we need to treat the left and right child as a lnull and rnull value respectively. 
   This is done to ensure that the t_preorder doesn't become a substring of s_​preorder even in cases when t isn't a subtree of s.
 *   
 * You can also note that we've added a '#' before every considering every value. If this isn't done, the trees of the form 
   s:[23, 4, 5] and t:[3, 4, 5] will also give a true result since the preorder string of the t("3 4 lnull rull 5 lnull rnull") 
   will be a substring of the preorder string of s("23 4 lnull rull 5 lnull rnull"). Adding a '#' before the node's value 
   solves this problem.
 *  
 * public class Solution {
    HashSet < String > trees = new HashSet < > ();
    public boolean isSubtree(TreeNode s, TreeNode t) {
        String tree1 = preorder(s, true);
        String tree2 = preorder(t, true);
        return tree1.indexOf(tree2) >= 0;
    }
    public String preorder(TreeNode t, boolean left) {
        if (t == null) {
            if (left)
                return "lnull";
            else
                return "rnull";
        }
        return "#"+t.val + " " +preorder(t.left, true)+" " +preorder(t.right, false);
    }
}
 Time complexity : O(m^2+n^2+m*n). A total of n nodes of the tree s and m nodes of tree t are traversed. Assuming string 
 concatenation takes O(k) time for strings of length k and indexOf takes O(m*n).
 Space complexity : O(max(m,n)). The depth of the recursion tree can go up to n for tree t and m for tree s in worst case.
 前两个方法如果不考虑string链接的时间复杂度则主要的时间复杂度变为O(m+n)
 
 * Approach #3 By Comparison of Nodes [Accepted] 和我的方法一模一样
 * public boolean isSubtree(TreeNode s, TreeNode t) {
        return traverse(s,t);
    }
    public boolean equals(TreeNode x,TreeNode y)
    {
        if(x==null && y==null)
            return true;
        if(x==null || y==null)
            return false;
        return x.val==y.val && equals(x.left,y.left) && equals(x.right,y.right);
    }
    public boolean traverse(TreeNode s,TreeNode t)
    {
        return  s!=null && ( equals(s,t) || traverse(s.left,t) || traverse(s.right,t));
    }
    Time complexity : O(m*n). In worst case(skewed tree) traverse function takes O(m*n) time.
    Space complexity : O(n). The depth of the recursion tree can go up to n. n refers to the number of nodes in s.
 */
public class question572 {
	public boolean isSubtree(TreeNode s, TreeNode t) {						//思路1递归实现
        if(t == null) {
            return true;
        }
        if(s == null) {
            return false;
        }
        return isIdentical(s, t) || isSubtree(s.left, t) || isSubtree(s.right, t);
    }
	
	public boolean isIdentical(TreeNode tree1, TreeNode tree2) {			//判断两棵树是否完全相同的递归算法
        if(tree1 == tree2) {
            return true;
        }
        if(tree1 == null && tree2 != null || tree1 != null && tree2 == null) {
            return false;
        }
        return (tree1.val == tree2.val) && isIdentical(tree1.left, tree2.left) && isIdentical(tree1.right, tree2.right);
    }
}
