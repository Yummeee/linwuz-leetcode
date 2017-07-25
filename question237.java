/*
 * Delete Node in a Linked List (Easy)
 * 
 * Write a function to delete a node (except the tail) in a singly linked list, given only access to that node.
 * Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node with value 3, the linked list should become 1 -> 2 -> 4 
 * after calling your function.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
/*
 * 思路：
 * 这题好脑残，我一开始用的方法还好笨O(n)，实际上O(1)的复杂度...直接将next的值复制过来并连接到next.next即可
 * 注意，JVM有内存的自动垃圾回收机制，但是前提是者必须是一个悬空的结点！！
 */
/* 
public class question237 {
	public void deleteNode1(ListNode node) {			
        ListNode pre = null;
        while(node.next != null) {
            node.val = node.next.val;
            pre = node;
            node = node.next;
        }
        pre.next = null;
    }
	public void deleteNode(ListNode node) {
		ListNode post = node.next;
        node.val = post.val;
        node.next = post.next;
        post.next = null;		//这才是真正的正解，这时候node的原next结点才悬空
    }
}
*/