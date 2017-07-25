/*
 * Reverse Linked List (Easy)
 * 
 * Reverse a singly linked list.
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
 * 1.头插法新建一个链表，时间复杂度和空间复杂度均为O(n)，也可用递归实现；
 * 递归：
 * public ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) return head;
    ListNode p = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return p;
}
 * 2.改进方法1，不需要建立一个新链表，除了第一个结点外，其余结点p找到其下一个结点q=p.next，将p.next置为反向链表的表头，并p=q，循环至p为null停止。空间复杂度降为O(1)
 * 其实第一个结点也符合一般规律，将方向链表的表头一开始视作null即可
 * public ListNode reverseList(ListNode head) {
    ListNode prev = null;			//反向链表表头
    ListNode curr = head;
    while (curr != null) {
        ListNode nextTemp = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextTemp;
    }
    return prev;
}
 */
public class question206 {
	public ListNode reverseList1(ListNode head) {
        ListNode reverse = null, tail = null;
        if(head == null) {
            return reverse;
        }
        
        while(head != null) {
            if(reverse == null) {
                reverse = head;
                tail = head;
            }
            else {
                ListNode cur = new ListNode(head.val);
                cur.next = reverse;
                reverse = cur;
            }
            head = head.next;
        }
        tail.next = null;
        return reverse;
	}
	
	public ListNode reverseList(ListNode head) {
        ListNode reverse = head;
        if(head == null) {
            return reverse;
        }
        ListNode p = reverse.next, p_next = null;
        while(p != null) {
            p_next = p.next;
            p.next = reverse;
            reverse = p;
            p = p_next;
        }
        head.next = null;
        return reverse;
    }
}
