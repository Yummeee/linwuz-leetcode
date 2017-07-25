/*
 * Implement Stack using Queues (Easy)
 * 
 * Implement the following operations of a stack using queues.
   push(x) -- Push element x onto stack.
   pop() -- Removes the element on top of the stack.
   top() -- Get the top element.
   empty() -- Return whether the stack is empty.
 * Notes:  
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty
 * operations are valid.
   Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque 
   (double-ended queue), as long as you use only standard operations of a queue.
   You may assume that all operations are valid (for example, no pop or top operations will be called on an empty stack).
 *
 */

/*
 * 思路：
 * 1.使用两个队列，在push的时候把新元素push到空队列中，并将另一个队列中的全部元素依次poll()之后再push到先前那个空队列，这样保证任何时候只有一个队列有元素，
 * 且元素顺序就为栈的出栈顺序。O(n) time complexity for one push(), O(1) for other operations (Two Queues, push - O(n), pop O(1))
 * 2.使用一个队列达到思路1的效果，将新元素push到队尾后，将之前队列中的出栈顺序的元素依次出对再入队，这样也保持了队列中的元素顺序即为出栈顺序，时间复杂度和思
 * 路1一致 (One Queue, push - O(n), pop O(1))
 * 3.最后一种方法，Two Queues, push - O(1), pop O(n)。就是在pop的时候将原队列中的钱top-1个元素都移走，输出最后一个元素再把剩余的元素存回去。
 * // Push element x onto stack.
   public void push(int x) {
      q1.add(x);
      top = x;
   }
 * public void pop() {
    while (q1.size() > 1) {
        top = q1.remove();
        q2.add(top);
    }
    q1.remove();
    Queue<Integer> temp = q1;
    q1 = q2;
    q2 = temp;
}
 */
import java.util.*;

public class question225 {
	/*思路1方案
	Queue<Integer> queue1;
    Queue<Integer> queue2;
    
    // Initialize your data structure here. 
    public question225() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    // Push element x onto stack. 				//O(n) time complexity for one push()
    public void push(int x) {
        if(queue1.isEmpty()) {
            queue1.offer(x);
            while(!queue2.isEmpty()) {
                queue1.offer(queue2.poll());
            }
        }
        else {
            queue2.offer(x);
            while(!queue1.isEmpty()) {
                queue2.offer(queue1.poll());
            }
        }
    }
    
    // Removes the element on top of the stack and returns that element. 
    public int pop() {
    	return queue1.isEmpty()? queue2.poll():queue1.poll();  //如果这里连续读两下拿top，需要判断是否size是大于1的，不然会报错
    }
    
    // Get the top element. 
    public int top() {
        return queue1.isEmpty()? queue2.peek():queue1.peek();
    }
    
    // Returns whether the stack is empty. 
    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
    */
	//思路2方案
	Queue<Integer> queue;
    /** Initialize your data structure here. */
    public question225() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        int size = queue.size();
        queue.offer(x);
        for(int i=0; i<size; i++) {
            queue.offer(queue.poll());
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
	
}

/**
 * Your MyStack object will be instantiated and called as such:
 * MyStack obj = new MyStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * boolean param_4 = obj.empty();
 */