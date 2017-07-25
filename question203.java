/*
 * Remove Linked List Elements (Easy)
 * 
 * Remove all elements from a linked list of integers that have value val.
 * 
 * Example
   Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
   Return: 1 --> 2 --> 3 --> 4 --> 5
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
 * 1.新建一个链表
 * 2.直接在原链表上删除，但是要注意链表头满足val的情况。递归或迭代均可
 * 递归：
 * public ListNode removeElements(ListNode head, int val) {
        if (head == null) return null;
        head.next = removeElements(head.next, val);
        return head.val == val ? head.next : head;
   }
 * 实际上触发Java的垃圾回收机制不一定需要将一个结点的next给前后置空，只需要不引用这个结点就可以，这样非递归方法还可以进行精简
 * 
 */
public class question203 {
	public ListNode removeElements1(ListNode head, int val) {			//思路2一实现方式
        ListNode p = head, pre = null;
        while(p != null) {
            if(p.val == val) {
                if(pre == null) {
                    pre = p;
                    p = p.next;
                    head = p;
                    pre.next = null;				//我这样刻意将删除结点前后置空的做法就和C语言专门free一下没有区别了
                    pre = null;
                }
                else {
                    pre.next = p.next;
                    p.next = null;
                    p = pre.next;
                }
            }
            else {
                pre = p;
                p = p.next;
            }
        }
        return head;
    }
	public ListNode removeElements(ListNode head, int val) {			//思路2精简，首先可以先不管head，最后再处理，也可以一开始就处理。或者构建一个假头结点，然后从假头结点的next开始
        if(head == null) {
        	return null;
        }
        
		ListNode p = head.next, pre = head;
        while(p != null) {
            if(p.val == val) {
                pre.next = p.next;
            }
            else {
                pre = p;
            }
            p = p.next;
        }
        return head.val == val ? head.next : head;
    }
}
