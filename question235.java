/*
 * Lowest Common Ancestor of a Binary Search Tree (Easy)
 * 
 * Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.
 * According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes v and w as the lowest node in T 
 * that has both v and w as descendants (where we allow a node to be a descendant of itself).”
 */
/*
 * 思路：
 * 利用BST的性质查询，当查询结点是当前结点的左右孩子或相等时，即为所求目标结点。
 * 判断两个查询结点分别在当前结点的哪棵子树时，除了一般比大小外，还可以用乘法的性质，可以简化代码：
 * public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    while ((root.val - p.val) * (root.val - q.val) > 0)
        root = p.val < root.val ? root.left : root.right;
    return root;
}
 */
public class question235 {
	public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        while(root != null) {
            if(p.val>=root.val&&q.val<=root.val || p.val<=root.val&&q.val>=root.val) {
                break;
            }
            
            if(p.val>root.val && q.val>root.val) {
                root = root.right;
            }
            else {
                root = root.left;
            }
        }
        return root;
    }
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {			//改进上面的算法，每个循环少比较2次，提高效率
        while(root != null) {
            if(p.val==root.val || q.val==root.val) {
            	break;
            }
            if(p.val > root.val) {
            	if(q.val < root.val) {
            		break;
            	}
                root = root.right;
            }
            else {
            	if(q.val > root.val) {
            		break;
            	}
                root = root.left;
            }
        }
        return root;
    }
}
