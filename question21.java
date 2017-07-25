/*
 * Merge Two Sorted Lists (Easy)
 * 
 * Merge two sorted linked lists and return it as a new list. The new list should be made by splicing together the nodes of the first two 
 * lists.
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
 * 1.迭代（新建或不新建链表均可）
 * 2.递归
 * public ListNode mergeTwoLists(ListNode l1, ListNode l2){
		if(l1 == null) return l2;
		if(l2 == null) return l1;
		if(l1.val < l2.val){
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		} else{
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		}
}
 */
public class question21 {
	public ListNode mergeTwoLists1(ListNode l1, ListNode l2) {			//新建一个链表
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        
        ListNode head = null, tail = null;
        while(l1!=null && l2!=null) {
            int temp;
            if(l1.val < l2.val) {
                temp = l1.val;
                l1 = l1.next;
            }
            else {
                temp = l2.val;
                l2 = l2.next;
            }
            if(head == null) {
                head = new ListNode(temp);
                tail = head;
            }
            else {
                tail.next = new ListNode(temp);
                tail = tail.next;
            }
        }
        ListNode p = l1==null? l2:l1;
        while(p != null) {
            int temp = p.val;
            tail.next = new ListNode(temp);
            p = p.next;
            tail = tail.next;
        }
        tail.next = null;
        return head;
    }
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {			//不新建一个链表
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }
        
        ListNode head = null, tail = null;
        while(l1!=null && l2!=null) {
        	ListNode temp = null;
            if(l1.val < l2.val) {
                temp = l1;
                l1 = l1.next;
            }
            else {
                temp = l2;
                l2 = l2.next;
            }
            if(head == null) {
                head = temp;
                tail = head;
            }
            else {
                tail.next = temp;
                tail = tail.next;
            }
        }
        ListNode p = l1==null? l2:l1;
        tail.next = p;
        return head;
    }
}
