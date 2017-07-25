/*
 * Linked List Random Node (Medium)
 * 
 * Given a singly linked list, return a random node's value from the linked list. Each node must have the same 
 * probability of being chosen.
 * 
 * Follow up:
   What if the linked list is extremely large and its length is unknown to you? Could you solve this efficiently
   without using extra space?
 * 
 * Example:
 * // Init a singly linked list [1,2,3].
   ListNode head = new ListNode(1);
   head.next = new ListNode(2);
   head.next.next = new ListNode(3);
   Solution solution = new Solution(head);

   // getRandom() should return either 1, 2, or 3 randomly. Each element should have equal probability of returning.
   solution.getRandom();
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
 * 1.如果能够知道链表的长度，随机生成一个1~length之间的随机整数i，java的Random类能保证此伪随机整数是均匀分布的，直接访问链表的第i个结点即可
 * 2.Follow up: 当链表相当大（一般化问题则是数据流）时且获取其长度不实际，则通过reservoir sampling来实现。
 */
import java.util.*;
public class question382 {
	ListNode head = null;
	Random random = null;
    /** @param head The linked list's head.
        Note that the head is guaranteed to be not null, so it contains at least one node. */
    public question382(ListNode head) {
        this.head = head;
        random = new Random();
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        int count = 1, res = head.val;
        ListNode p = head.next;
        while(p != null) {
            count++;
            int random_no = random.nextInt(count);           //生成一个0 ~ count-1的随机数，因此每个数的概率是1/count
            if(random_no < 1) {                     //若random_no == 0，则说明取当前结点的概率为1/count，满足条件。实际上random_no可以取任何一个介于0 ~ count-1的随机数，都代表着1/count的概率
                res = p.val;
            }
            p = p.next;
        }
        return res;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(head);
 * int param_1 = obj.getRandom();
 */
