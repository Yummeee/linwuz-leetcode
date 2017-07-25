/*
 * Intersection of Two Linked Lists (Easy)
 * 
 * Write a program to find the node at which the intersection of two singly linked lists begins.
 * Notes:
   If the two linked lists have no intersection at all, return null.
   The linked lists must retain their original structure after the function returns.
   You may assume there are no cycles anywhere in the entire linked structure.
   Your code should preferably run in O(n) time and use only O(1) memory.
 */
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
/*
 * 思路：一开始忘记了一点隐含前提，根据list的性质（只有一个后继结点），两个list的一旦相交就不可能再分开，且在同一个结点终止，不可能一个链表提前中止
 * 1.用HashSet，遍历两个链表，将结点依次存入set中，出现重复则退出，时间复杂度O(n)，空间复杂度O(n)，空间不符合要求
 * 2.Brute Force，对于list1中的每一个结点，遍历list2找是否有相同的结点，时间复杂度O(n^2)，空间复杂度O(1)，时间不符合要求
 * 
 * 3.时间复杂度O(n)，空间复杂度O(1)的方法：Two pointers，基本思想就是如果不记录访问历史(思路1、2均相当于记录了历史)情况下找到这个相交点，则一定要两个
 * 指针同时访问到这个相交点
 * 1)根据list的隐含性质，统计两个链表的长度差，然后较长的链表先移动长度差个结点，此时两个链表距离起始相交结点的距离相同（在同一个起跑线上），然后同时往后找，
 * 遇到相同结点返回即可
 * 2)太巧妙了！！！！同时遍历两个链表，当一个链表到结尾之后，将其置为另一个链表的开头，继续遍历，直到两个指针指向的结点相遇！实际上当两个指针均置换为对方链表的
 * 头结点时，我们就相当于完成了一个找两个链表的长度差并将较长链表的指针向后移动长度差个结点的过程，只不过这个时候较长链表的指针原先是指向较短链表的。若没有交集，
 * 则两个指针会同时为null
 */
import java.util.*;
public class question160 {
	public ListNode getIntersectionNode(ListNode headA, ListNode headB) {	//思路3.2	
        if(headA == null || headB == null) {
            return null;
        }
        
        ListNode p = headA, q = headB;
        while(p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }
	
	public ListNode getIntersectionNode2(ListNode headA, ListNode headB) {	//思路3.1	
        if(headA == null || headB == null) {
            return null;
        }
        
        ListNode p = headA, q = headB;
        int lengthA = 0, lengthB = 0;
        while(p != null) {
            lengthA++;
            p = p.next;
        }
        while(q != null) {
            lengthB++;
            q = q.next;
        }
        p = lengthA <= lengthB ? headA : headB;          //p <=> shorter list
        q = lengthA > lengthB ? headA : headB;           //q <=> longer list
        for(int i=0; i<Math.abs(lengthA - lengthB); i++) {
            q = q.next;
        }
        while(p != null && q != null) {
            if(p == q) {
                return p;
            }
            p = p.next;
            q = q.next;
        }
        return null;
    }
	
	public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {		//思路1
        if(headA == null || headB == null) {
            return null;
        }
        
        HashSet<ListNode> set = new HashSet<>();
        ListNode p = headA, q = headB;
        while(p !=null || q != null) {
            if(p != null) {
                if(!set.add(p)) {
                    return p;
                }
                p = p.next;
            }
            if(q != null) {
                if(!set.add(q)) {
                    return q;
                }
                q = q.next;
            }
        }
        return null;
    }
}
