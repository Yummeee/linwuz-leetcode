/*
 * Min Stack (Easy)
 * 
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
   push(x) -- Push element x onto stack.
   pop() -- Removes the element on top of the stack.
   top() -- Get the top element.
   getMin() -- Retrieve the minimum element in the stack.
 * 
 * Example:
 * MinStack minStack = new MinStack();
   minStack.push(-2);
   minStack.push(0);
   minStack.push(-3);
   minStack.getMin();   --> Returns -3.
   minStack.pop();
   minStack.top();      --> Returns 0.
   minStack.getMin();   --> Returns -2.
 */
/*
 * 思路：注意getMin的问题，如果min被pop()了怎么办！！此题核心是O(1)得到min
 * 1.我的初始方法，感觉弱爆了。只用一个min值保存当前栈中的最小值，如果pop的值等于min值，则用iterator遍历一遍stack，找到新的最小值。pop的时间复杂度
 * 分析：worst case：所有元素从小到大依次入栈，这时pop的复杂度是O(n)。
 * 2.使用两个栈，一个栈保存存入的数值，一个栈记录min值的出现情况。当前的值不小于min栈的top时，将其入栈；否则不入，因为这个值比当前的最小值要大，pop这个值
 * 的时候一定不会影响到min。实际上min栈的top一定是当前最小元素。所有操作时间复杂度均为O(1)。
 * Stack<Integer> stack;
   Stack<Integer> mins;
   public MinStack() {
        stack = new Stack<>();
        mins = new Stack<>();
    }
    
   public void push(int x) {
        stack.push(x);
        if(mins.isEmpty() || x <= mins.peek()) {
            mins.push(x);
        }
    }
    
   public void pop() {
       if(mins.peek().equals(stack.pop())) {			//java的装箱机制，当小于128时，同一个数会直接用同一个对象来表示，==来区分值相同的两个integer对象就不好用了
            mins.pop();
       }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return mins.peek();
    }
    
 3.只用一个栈，不使用min栈，同时使用一个min变量。若当前的值不小于min值，则将min值入栈，再将x入栈，同时将min置为x。若pop时min == stack.pop()，则将min置为stack.pop()。
 相当于对每个min值，其前面刚好再存一个前一个min值，多个元素会被重复存一次，所有操作均为O(1)
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        // only push the old minimum value when the current 
        // minimum value changes after pushing the new value x
        if(x <= min){          
            stack.push(min);
            min=x;
        }
        stack.push(x);
    }

    public void pop() {
        // if pop operation could result in the changing of the current minimum value, 
        // pop twice and change the current minimum value to the last minimum value.
        if(stack.pop() == min) min=stack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
    
 4.使用一个栈，使用min指针，但stack中不重复存元素。太巧妙了！！栈中存的值只有两种情况：比min大时存和当前min的差值，比min小时即代表两个min的差距，可以用于还原min值
    long min;				//由于后面要用到减法，所以可能会溢出，因此用long
    Stack<Long> stack;

    public MinStack(){
        stack=new Stack<>();
    }
    
    public void push(int x) {
        if (stack.isEmpty()){
            stack.push(0L);				//存0入栈，最后退栈时min+0就是第一个入栈的元素
            min=x;
        }else{
            stack.push(x-min);//Could be negative if min value needs to change				//所以出栈时如果遇到一个负数，说明这个数是当前的min
            if (x<min) min=x;																//实际上对于x>min栈中存的是当前结点和min的差，对于x<min，存的是新min和旧min之间的差距
        }
    }

    public void pop() {
        if (stack.isEmpty()) return;
        
        long pop=stack.pop();
        
        if (pop<0)  min=min-pop;//If negative, increase the min value，这时stack中存的是两个min的差值
        
    }

    public int top() {
        long top=stack.peek();
        if (top>0){
            return (int)(top+min);				//说明当时x>min，存的是x和min的差值
        }else{
           return (int)(min);					//stack存的是两个min的差值，min存的就是当前结点的值
        }
    }

    public int getMin() {
        return (int)min;
    }
 
 */
import java.util.*;
public class question155 {
	int min;
    Stack<Integer> stack;
    /** initialize your data structure here. */
    public question155() {
        stack = new Stack<>();
        min = Integer.MAX_VALUE;
    }
    
   public void push(int x) {
        stack.push(x);
        if(x < min) {
            min = x;
        }
    }
    	
   public void pop() {								//不合题意的愚蠢的方法
       if(min == stack.pop()) {
            min = Integer.MAX_VALUE;
            Iterator it = stack.iterator();
            while(it.hasNext()) {
                min = Math.min(min, (int)it.next());			//it.next() returns Object, needs to change
            }
       }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
