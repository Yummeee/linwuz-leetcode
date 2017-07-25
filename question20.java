/*
 * Valid Parentheses (Easy)
 * 
 * Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.
 * The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.
 */
/*
 * 思路：
 * 典型的用栈判断括号匹配的问题，0101问题，用栈实现。
 * public boolean isValid(String s) {			//避免了用case语句或3个if-else的实现方式...
        Stack<Integer> p = new Stack<>();
        for(int i = 0; i < s.length(); i++) {
            int q = "(){}[]".indexOf(s.substring(i, i + 1));
            if(q % 2 == 1) {
                if(p.isEmpty() || p.pop() != q - 1) return false;
            } else p.push(q);
        }
        return p.isEmpty();
    }
 */
import java.util.*;
public class question20 {
	public boolean isValid(String s) {
        if(s.length()%2 == 1) {
            return false;
        }
        
        Stack<Character> stack = new Stack<>();
        for(int i=0; i<s.length(); i++) {
            switch (s.charAt(i)) {
                case ')': if(stack.isEmpty() || !stack.pop().equals('(')) {			//栈不为空别忘记了！！！！！！
                    return false;
                } break;
                case ']': if(stack.isEmpty() || !stack.pop().equals('[')) {
                    return false;
                } break;
                case '}': if(stack.isEmpty() || !stack.pop().equals('{')) {
                    return false;
                } break;
                default: stack.push(s.charAt(i)); break;
            }
        }
     
        return stack.isEmpty();					//这种简化代码的方法还没有学会！！！！看了多少次了 还是蠢蠢地用if
    }
}
