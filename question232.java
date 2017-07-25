/*
 * Implement Queue using Stacks (Easy)
 * 
 * Implement the following operations of a queue using stacks.
 * push(x) -- Push element x to the back of queue.
 * pop() -- Removes the element from in front of queue.
 * peek() -- Get the front element.
 * empty() -- Return whether the queue is empty.
 * 
 * Notes:
 * You must use only standard operations of a stack -- which means only push to top, peek/pop from top, size, and is empty 
 * operations are valid.
 * Depending on your language, stack may not be supported natively. You may simulate a stack by using a list or deque 
 * (double-ended queue), as long as you use only standard operations of a stack.
 * You may assume that all operations are valid (for example, no pop or peek operations will be called on an empty queue).
 */
/*
 * 思路：
 * 我的第一想法是在peek和pop方法时每次使用一个辅助stack，从而取出stack的栈底并还原，但这样要遍历两边stack，pop和peek的效率不高
 * 
 * 编辑的解法：
 * 1.Approach #1 (Two Stacks) Push - O(n) per operation, Pop - O(1) per operation.
 * 使用两个stack，只不过在push的时候就直接调整将原栈中的内容给逆向放到第二个栈中，然后将新数放在原栈的栈底，并将第二个栈中的结果放回原栈，保证原栈一定是最早push的元素在栈顶，而第二个栈平时都是空的，只是为了保证原栈的顺序。方法二则第二个栈平时需要保存一部分队列元素
 * class MyQueue {
 	Stack<Integer> s1 = new Stack();				
    Stack<Integer> s2 = new Stack();
    
    public void push(int x) {
    	while (!s1.isEmpty())
        	s2.push(s1.pop());
    	s1.push(x);
    	while (!s2.isEmpty())
        	s1.push(s2.pop());
	}
	
	public int pop() {
    	return s1.pop();
    }
    
    public int peek() {
  		return s1.peek();
	}
    
    public boolean empty() {
    	return s1.isEmpty();
	}
}
 * Approach #2 (Two Stacks) Push - O(1) per operation, Pop - Amortized O(1) per operation.
 * 使用两个stack，并且不修改push，一旦遇到pop或peek(二选一)，如果第二个栈为空，将原栈中的所有元素转到第二个栈中，否则简单的pop第二个栈即可。哪怕pop后面跟了push也不影响，因为第二个栈中的元素一定比后面push的元素位置靠前，要首先弹出
 * class MyQueue {

    Stack<Integer> input = new Stack();				
    Stack<Integer> output = new Stack();			//一定是反向的栈，output和input可能同时有数据，只有当output中的数据都pop完之后，再将input的数据导进去
    
    public void push(int x) {
        input.push(x);
    }

    public int pop() {
        peek();
        return output.pop();
    }

    public int peek() {
        if (output.empty())
            while (!input.empty())
                output.push(input.pop());
        return output.peek();
    }

    public boolean empty() {
        return input.empty() && output.empty();
    }
}
 */
import java.util.*;
public class question232 {
	Stack<Integer> fake_queue;
	/** Initialize your data structure here. */
    public question232() {
    	fake_queue = new Stack<>();
    }
    
    /** Push element x to the back of queue. */
    public void push(int x) {
    	fake_queue.push(x);    
    }
    
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
    	Stack<Integer> temp = new Stack<>();
        int size = fake_queue.size();
        int i = 1;
        while(i < size) {
        	temp.push(fake_queue.pop());
        	i++;
        }
        int top = fake_queue.pop();
        while(!temp.isEmpty()) {
            fake_queue.push(temp.pop());
        }
        return top;
    }
    
    /** Get the front element. */
    public int peek() {
    	Stack<Integer> temp = new Stack<>();
        while(!fake_queue.isEmpty()) {
        	temp.push(fake_queue.pop());
        }
        int top = temp.peek();
        while(!temp.isEmpty()) {
            fake_queue.push(temp.pop());
        }
        return top;
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return fake_queue.isEmpty();
    }
}
/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */