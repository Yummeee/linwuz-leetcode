/*
 * Binary Tree Paths (Easy)
 * 
 * Given a binary tree, return all root-to-leaf paths.
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
 * 先序遍历即可，可用DFS的递归或一般非递归方式(必须要回溯)，或者用Morris Traversal，BFS也可以
 * 注意实际上回溯退掉当前结点的操作是可以省略的，只要在调用helper()的path参数上合成最新的路径串即可，但StringBuilder就不再适合这种方法了(不过本身就在回溯时不好用)。
 * 
 * 看到一种很巧妙的不利用helper函数的递归方法：就是每次新建一个LinkedList，一共要建n个
 * public List<String> binaryTreePaths(TreeNode root) {
        
        List<String> paths = new LinkedList<>();

        if(root == null) return paths;
        
        if(root.left == null && root.right == null){
            paths.add(root.val+"");
            return paths;
        }

         for (String path : binaryTreePaths(root.left)) {
             paths.add(root.val + "->" + path);
         }

         for (String path : binaryTreePaths(root.right)) {
             paths.add(root.val + "->" + path);
         }

         return paths;
        
    }
 */
import java.util.*;
public class question257 {
	//使用回溯
	public List<String> binaryTreePaths1(TreeNode root) {
        ArrayList<String> paths = new ArrayList<>();
        if(root == null) {
            return paths;
        }
        
        String initial = "" + root.val;
        if(root.left==null && root.right==null) {
        	paths.add(initial);
            return paths;
        }
        
        helper1(root.left, initial, paths);
        helper1(root.right, initial, paths);
        return paths;
    }
	public void helper1(TreeNode root, String cur_path, List<String> list) {
        if(root == null) {
            return;
        }
        
        String temp = "->" + root.val;
        cur_path += temp;
        if(root.left==null && root.right==null) {
        	list.add(cur_path);
        	cur_path = cur_path.substring(0, cur_path.length()-temp.length());
        	return;
        }
        
        helper1(root.left, cur_path, list);
        helper1(root.right, cur_path, list);
        cur_path = cur_path.substring(0, cur_path.length()-temp.length());
    } 
	
	
    //省略回溯
	public List<String> binaryTreePaths2(TreeNode root) {
        ArrayList<String> paths = new ArrayList<>();
        if(root != null) {
        	helper(root, "", paths);
        }
       
        return paths;
    }
	public void helper(TreeNode root, String cur_path, List<String> list) {
        cur_path += root.val + "";					//省略专门为root没有孩子节点而单独分情况处理
        if(root.left==null && root.right==null) {
        	list.add(cur_path);
        	return;
        }
        if(root.left != null) {
        	helper(root.left, cur_path + "->", list);
        }
        if(root.right != null) {
        	helper(root.right, cur_path + "->", list);
        }
    } 
    
	 private class node {		//类似题108构建平衡二叉排序树，以及题437，构建了一个辅助类用于回溯
	    	TreeNode root;
	    	String pre_path;		//pre_path表示根节点到这个结点前的路径
	    	public node(TreeNode root, String pre_path) {
	    		this.root = root;
	    		this.pre_path = pre_path;
	    	}
	}
	public List<String> binaryTreePaths3(TreeNode root) {			//先序非递归，利用上面这个类，可以轻易实现BFS，但Morris Traversal则不是很好，因为要为每个结点新建一个封装类，且类需要加左右结点，等价于又建了一棵树，morris的O(1)空间优势就没有了
        ArrayList<String> paths = new ArrayList<>();
        if(root == null) {
        	return paths;
        }
       
        Stack<node> stack = new Stack<>();
        stack.push(new node(root, ""));
        while(!stack.isEmpty()) {
        	node p = stack.pop();
        	String cur_path = p.pre_path + p.root.val;
        	if(p.root.left==null && p.root.right==null) {
        		paths.add(cur_path);
        	}
        	else {
	        	if(p.root.right != null) {
	        		stack.push(new node(p.root.right, cur_path+"->"));
	        	}
	        	if(p.root.left != null) {
	        		stack.push(new node(p.root.left, cur_path+"->"));
	        	}
        	}
        }
        return paths; 
    }
	
    public static void main(String[] args) {
    	String str = "1->2->3->-1";
    	String temp = "->" + -5;
    	str += temp;
    	System.out.println(str);
    	System.out.println(str.substring(0,str.length()));
    	str = str.substring(0, str.length()-temp.length());			
    	System.out.println(str);
    }
}
