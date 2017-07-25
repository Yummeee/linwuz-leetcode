/*
 * Remove Duplicates from Sorted List (Easy)
 * 
 * Given a sorted linked list, delete all duplicates such that each element appear only once.
 * For example,
   Given 1->1->2, return 1->2.
   Given 1->1->2->3->3, return 1->2->3
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
 * 遍历链表即可，注意边界情况。可用迭代法也可用递归，时间复杂度O(n)，迭代法空间复杂度O(1)，递归法空间复杂度O(n)
 * public ListNode deleteDuplicates(ListNode head) {	//迭代法2，不需要使用一个中间结点
        ListNode list = head;
         
         while(list != null) {
        	 if (list.next == null) {
        		 break;
        	 }
        	 if (list.val == list.next.val) {
        		 list.next = list.next.next;
        	 } else {
        		 list = list.next;
        	 }
         }
         
         return head;
    }
 * 递归法：相当于倒着来求链表，因为正着来的话和迭代法没什么区别了...
 * public ListNode deleteDuplicates(ListNode head) {
        if(head == null || head.next == null)return head;
        head.next = deleteDuplicates(head.next);
        return head.val == head.next.val ? head.next : head;
}
 */
public class question83 {
	public ListNode deleteDuplicates(ListNode head) {
        if(head == null) {
            return null;
        }
        
        ListNode p = head, pre = null;;
        while(p != null) {
            pre = p;
            while(p.next!=null && p.val==p.next.val) {
                p = p.next;
            }
            pre.next = p.next;
            p = pre.next;
        }
        return head;
    }
}
