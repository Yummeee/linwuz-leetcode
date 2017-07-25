/*
 * Add Two Numbers (Medium)
 * You are given two non-empty linked lists representing two non-negative integers. 
 * The digits are stored in reverse order and each of their nodes contain a single digit. 
 * Add the two numbers and return it as a linked list.
 * 
 * e.g., Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)     Output: 7 -> 0 -> 8
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
 * 我的解题思路：(这个方法有可能被判超时，有时候会被accept，有时则不会，用编辑的方法做标准方法)
 * 这题就是简单的链表操作，就是有一点很坑！加法计算会进位！不要忘记这一点！（第一次提交就因为没看清错了）
 * 加法进位永远只可能+1
 * 对于非最低位，一般有两至三种进位情况，需要根据当前位置是否均小于两个数的最高位或者只小于一个数的最高位来决定
 * 对于前者，三种进位情况：
 * 1.该位被进位且仍需进位
 * 2.该位未被进位但这次需进位
 * 3.该位无论如何不需要进位
 * 对于后者，只有1、3这两种情况
 */

/*
 * 编辑给的答案（代码更简洁）： 
 * 不用boolean flag，使用int carry = 0|1，这样无论任何情况都需要加carry，就不用这么多if条件了
 * 且对于一个数比另一个加数位数多时，只需将较小的加数的值在超出长度时设为0，就不需要写多个while循环了
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
    ListNode dummyHead = new ListNode(0);
    ListNode p = l1, q = l2, curr = dummyHead;
    int carry = 0;
    while (p != null || q != null) {
        int x = (p != null) ? p.val : 0;
        int y = (q != null) ? q.val : 0;
        int sum = carry + x + y;
        carry = sum / 10;
        curr.next = new ListNode(sum % 10);
        curr = curr.next;
        if (p != null) p = p.next;
        if (q != null) q = q.next;
    }
    if (carry > 0) {
        curr.next = new ListNode(carry);
    }
    return dummyHead.next;
}
 */

class ListNode {
   int val;
   ListNode next;
   ListNode(int x) { val = x; }
}

public class question2 {
	 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
		 ListNode head = null, tail = null;
		 ListNode p1 = l1, p2 = l2;
		 boolean flag = false;          //用于判断是否进位
		 while((p1!=null) && (p2!=null)) {
			 if(head == null) {			//对于最低位，不可能被进位
				 if((p1.val+p2.val)/10 >= 1) {
					 flag = true;        //说明需要进位
				 }
				 head = new ListNode((p1.val+p2.val)%10);
				 head.next = null;
				 tail = head;
			 }
			 else {						
				 if(flag == true) {
					 tail.next = new ListNode((p1.val+p2.val+1)%10);        //需要被进位
				 }
				 else {
					 tail.next = new ListNode((p1.val+p2.val)%10);           //不需要被进位
				 }
				 if(flag == true && (p1.val+p2.val+1)/10 >= 1) {
					 flag = true;
				 }
				 else if((p1.val+p2.val)/10 >= 1) {
					 flag = true;
				 }
				 else {
					 flag = false;
				 }
				 tail = tail.next;
			 } 
			 p1 = p1.next;
			 p2 = p2.next;
		 }
		 while(p1 != null) {
			 if(flag == true) {
				 tail.next = new ListNode((p1.val+1)%10);        //进位
			 }
			 else {
				 tail.next = new ListNode(p1.val);           //不进位
			 }
			 if(flag==true && (p1.val+1)/10 >= 1) {
				 flag = true;
			 }
			 else {
				 flag = false;
			 }
			 tail = tail.next;
			 p1 = p1.next;
		 }
		 while(p2 != null) {
			 if(flag == true) {
				 tail.next = new ListNode((p2.val+1)%10);        //进位
			 }
			 else {
				 tail.next = new ListNode(p2.val);           //不进位
			 }
			 if(flag==true && (p2.val+1)/10 >= 1) {
				 flag = true;
			 }
			 else {
				 flag = false;
			 }
			 tail = tail.next;
			 p2 = p2.next;
		 }
		 if(flag == true)			//由于进使得结果比两个数的最大者多出一位
		 {
			 tail.next = new ListNode(1); 
			 tail = tail.next;
			 flag = false;
		 }
		 tail.next = null;

		 return head;
	 }
}
