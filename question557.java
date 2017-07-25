import java.util.ArrayList;

/*
 * Reverse Words in a String III (easy)
 * 
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving 
 * whitespace and initial word order.
 * e.g.,
   Input: "Let's take LeetCode contest"
   Output: "s'teL ekat edoCteeL tsetnoc"  
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.
 */

/*
 * 犯的错误：（这也能错...这么简单的题...）
 * 最后一个单词输出不了，因为输出的判断条件没有考虑除了空格还有结尾...循环完还要再出一次栈，把最后一个单词输出
 * 最大的问题：超时了...时间复杂度是O(n)...太狗了
 */

/*
 * 仔细看了给的答案，用自定义方法实际上也对s遍历了两遍，和我超时的方法一样，只能说java封装的reverse()确实减少了操作，只遍历一半，但是自定义方法竟然也对 
 */


/*
 * Editorial Solution:
 * Approach #1 Simple Solution[Accepted] O(n)
   The first method is really simple. We simply split up the given string based on whitespaces and put the individual words 
   in an array of strings. Then, we reverse each individual string and concatenate the result. We return the result after 
   removing the additional whitespaces at the end.
   就是利用Stringbuffer的逆转方法，但是大量用String封装的split等方法竟然也OK
   public String reverseWords(String s) {
        String words[] = s.split(" ");
        String res = "";
        for (String word: words)
            res += new StringBuffer(word).reverse().toString() + " ";
        return res.trim();
    }
    
 * Approach #2 Without using pre-defined split and reverse function [Accepted] O(n)
   We can create our own split and reverse function. Split function splits the string based on the delimiter " "(space) and 
   returns the array of words. Reverse function returns the string after reversing the characters.
   
   public class Solution {
    public String reverseWords(String s) {
        String words[] = split(s);
        String res = "";
        for (String word: words)
            res += reverse(word) + " ";
        return res.trim();
    }
    public String[] split(String s) {
        ArrayList < String > words = new ArrayList < > ();
        String word = "";
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ') {
                words.add(word);
                word = "";
            } else
                word += s.charAt(i);
        }
        words.add(word);
        return words.toArray(new String[words.size()]);
    }
    public String reverse(String s) {
        String res = "";
        for (int i = 0; i < s.length(); i++)
            res = s.charAt(i) + res;
        return res;
    }
}
 * 
 */
public class question557 {
	/*自己的超时代码，换成AayList做栈也是超时的，看来和空间没关系
	public String reverseWords(String s) {
        String inverse = "";
        char[] stack = new char[s.length()];     //use stack's property to inverse each word
        int top = -1;
        
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i)!=' ') {
                stack[++top] = s.charAt(i);    //push current character to the stack
            }
            else {                      //finish one word, pop all characters out 
                for(; top>=0; top--) {
                    inverse += stack[top];
                }
                inverse += " ";
            }
        }
        
        for(; top>=0; top--) {     //for the last word, which cannot be loaded in the loop
            inverse += stack[top];
        }
        return inverse;
    }
	*/
	public String reverseWords(String s) {
        String words[] = s.split(" ");
        String res = "";
        for (String word: words)
            res += new StringBuffer(word).reverse().toString() + " ";
        return res.trim();
    }
	
	
	public static void main(String[] args) {
		String result = new question557().reverseWords("hehhhhhhe");
		System.out.println(result);
	}
}
