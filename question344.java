/*
 * Reverse String (Easy)
 * 
 * Write a function that takes a string as input and returns the string reversed.
 * e.g.,
   Given s = "hello", return "olleh".
 */

/*
 * Java要return的变量一定要初始化！！！
 * 靠我的栈又超时了...需要遍历两遍s，即O(2n)。阅读了一下reverse代码，发现reverse()是通过从尾做一次右移一位（除以2），即从中间开始向两头扩散着两两交换，只会遍历一半的s，效率确实更高,O(n/2)
 * 还有其余的只遍历不超过一遍的方法：
   1. 直接reverse += s.charAt(i)，但是i是从大变小的，相当于输出即为反向，遍历一遍，(n)；
   2. 改进java的reverse()算法，只遍历一半，交换char[i]和char[s.length()-i-1];
   3. 利用异或运算的性质进行交换操作，其余同方法2，也只遍历一半，AxorAxorB = B
      ch[start] = (char) (ch[start] ^ ch[end]);
      ch[end] = (char) (ch[start] ^ ch[end]);		//此时end变成了原start
      ch[start] = (char) (ch[start] ^ ch[end]);		//此时start变成了end
   4. 利用递归(分治思想)：O(n)
      int length = s.length();
      if (length <= 1) {
          return s;
      }
      String leftStr = s.substring(0, length / 2);
      String rightStr = s.substring(length / 2, length);
      return reverseString7(rightStr) + reverseString7(leftStr);   
 * 
 */
public class question344 {
	public class Solution {
	    public String reverseString(String s) {
	        /*
	        String reverse = "";
	        if(s.equals("")) {
	            return reverse;
	        }
	        char[] stack = new char[s.length()];
	        int top = -1;
	        for(int i=0; i<s.length(); i++) {
	            stack[++top] = s.charAt(i);
	        }
	        
	        for(; top>=0; top--) {
	            reverse += stack[top];
	        }
	        */
	        String reverse = new StringBuffer(s).reverse().toString();
	        return reverse;
	    }
	}
}
