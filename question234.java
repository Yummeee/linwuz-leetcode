/*
 * Palindrome Linked List (Easy)
 * 
 * Given a singly linked list, determine if it is a palindrome.
 * Follow up:
   Could you do it in O(n) time and O(1) space?
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
 * 此题出题较为模糊，可能会疑惑到底是listnode是回文，还是listnode中的数字组合到一起是一个回文数，但实际上应该是前者，因为listnode才是这道题中的基本元素
 * 注意head为空时true而不是false
 * 在评论区有人指出了一个问题，空间复杂度的前提是input read only，即不能修改输入。It is a common misunderstanding that the space complexity
 * of a program is just how much the size of additional memory space being used besides input. An important prerequisite is 
 * neglected the above definition: the input has to be read-only. By definition, changing the input and change it back is not
 * allowed (or the input size should be counted when doing so). Another way of determining the space complexity of a program 
 * is to simply look at how much space it has written to. Reversing a singly linked list requires writing to O(n) memory 
 * space, thus the space complexities for all "reverse-the-list"-based approaches are O(n), not O(1). 所以这类题用extra space来
 * 表示则更贴切，因为按照定义此题的空间复杂度是永远不可能达到1的。不过从实用的角度出发，一般说的space多指extra space的这种定义而不是严格的space complexity，
 * 因为实际中我们考虑空间的使用多半是由于我们的内存所限，需要考虑程序整个使用的空间大小，而不在乎到底修改不修改输入与否。
 * 所以我之前那道判断链表是否有回路的题的修改每个结点的next到head结点的空间复杂度不是O(1)，只是额外空间是O(1)
 * 
 * 具体方法：
 * 1.遍历一遍listnode，同时创建一个list保存每个结点的值，随后再遍历一遍list来确定是否是回文链表，时间复杂度O(2n)，空间复杂度O(n)
 * 2.首先遍历一遍listnode确定有多少个结点，随后遍历至1/2链表长度处，将剩余的链表逆转过来，再从头比较两个链表的每一个结点，若有一个不一样则不是回文串，时间
 * 复杂度O(3n)，额外空间O(1)（看了评论不能叫空间复杂度了...），此方法参考了题206逆转链表以及题9回文数的思想
 * 还可以改进此方法，在找到中间结点的同时将前一半结点逆转，只需一个结点一次走两个结点，另一个进行前半部翻转，等快结点走到链表尾部即可。有点像那道判断链表是否有
 * 回路的题
 * public boolean isPalindrome(ListNode head) {
        if(head == null) {
            return true;
        }
        ListNode p1 = head;
        ListNode p2 = head;
        ListNode p3 = p1.next;
        ListNode pre = p1;
        //find mid pointer, and reverse head half part
        while(p2.next != null && p2.next.next != null) {
            p2 = p2.next.next;
            pre = p1;
            p1 = p3;
            p3 = p3.next;
            p1.next = pre;
        }

        //odd number of elements, need left move p1 one step
        if(p2.next == null) {
            p1 = p1.next;
        }
       
        //compare from mid to head/tail
        while(p3 != null) {
            if(p1.val != p3.val) {
                return false;
            }
            p1 = p1.next;
            p3 = p3.next;
        }
        return true;
        
    }
 */
public class question234 {
	public boolean isPalindrome(ListNode head) {			//Follow up的解法
        if(head == null) {
            return false;
        }
        if(head.next == null) {
            return true;
        }
        
        ListNode p = head, head2 = null, pre = null;
        int count = 1;          //to accomodate both even and odd nodes，多数一个，这样奇数的时候能绕过正中间那个结点而偶数无影响
        while(p != null) {
            count++;
            p = p.next;
        }
        p = head;
        for(int i=0; i<count>>1; i++) {
            p = p.next;
        }
        while(p != null) {
            ListNode temp = p.next;
            p.next = pre;
            pre = p;
            p = temp;
        }
        head2 = pre;
        while(head2 != null) {					//head有可能比head2中的有效结点多一个，即链表结点数为奇数的情况
            if(head2.val != head.val) {
                return false;
            }
            head2 = head2.next;
            head = head.next;
        }
        return true;
    }
}
