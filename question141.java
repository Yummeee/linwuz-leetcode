/*
 * Linked List Cycle (Easy)
 * 
 * Given a linked list, determine if it has a cycle in it.
 * 
 * Follow up:
   Can you solve it without using extra space?
 */
/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */

/*
 * 思路：注意cycle不一定是头尾相连，尾结点指向其任何一个前驱结点都算
 * 1.使用HashSet，遍历链表，若一个结点在Set中已经存在，则证明存在环路，时间复杂度O(n)，空间复杂度O(n)
 * 2.访问完每个结点后，将其next域置为head，然后如果head再次出现就说明存在环路。这样会破坏原有链表结构，但是时间复杂度O(n)，空间复杂度O(1)
 * 
 * 编辑的解法：
 * Approach #1 (Hash Table) [Accepted]，等同于我的思路1
 * 
 * Approach #2 (Two Pointers) [Accepted]，我的思路还是太僵硬，总认为two pointers只适用于slow指针只在部分情况下移动的问题！！！！
 * Intuition
   Imagine two runners running on a track at different speed. What happens when the track is actually a circle?
 * Algorithm
   The space complexity can be reduced to O(1) by considering two pointers at different speed - a slow pointer and a fast
   pointer. The slow pointer moves one step at a time while the fast pointer moves two steps at a time.
   
   If there is no cycle in the list, the fast pointer will eventually reach the end and we can return false in this case.
   
   Now consider a cyclic list and imagine the slow and fast pointers are two runners racing around a circle track. The fast 
   runner will eventually meet the slow runner. Why? Consider this case (we name it case A) - The fast runner is just one 
   step behind the slow runner. In the next iteration, they both increment one and two steps respectively and meet each 
   other.

   How about other cases? For example, we have not considered cases where the fast runner is two or three steps behind the 
   slow runner yet. This is simple, because in the next or next's next iteration, this case will be reduced to case A 
   mentioned above.
 * public boolean hasCycle(ListNode head) {
    if (head == null || head.next == null) {
        return false;
    }
    ListNode slow = head;
    ListNode fast = head.next;
    while (slow != fast) {
        if (fast == null || fast.next == null) {
            return false;
        }
        slow = slow.next;
        fast = fast.next.next;
    }
    return true;
}
 */
import java.util.*;
public class question141 {
	public boolean hasCycle1(ListNode head) {				//使用HashSet方法
        HashSet<ListNode> set = new HashSet<>();
        while(head != null) {
            if(!set.add(head)) {
                return true;
            }
            head = head.next;   
        }
        return false;
    }
	
	public boolean hasCycle2(ListNode head) {				//将每个非head结点的next置为head的方法
        if(head == null) {
            return false;
        }
        
        ListNode p = head.next, post = null;
        while(p != null) {
            if(p == head) {
                return true;
            }
            post = p.next;
            p.next = head;
            p = post;
        }
        return false;
    }
	
	public boolean hasCycle(ListNode head) {				//two pointer方法
        ListNode slow = head, fast = head;
        while(fast!=null && fast.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
            if(slow == fast) {
            	return true;
            }
        }
        return false;
    }
}
