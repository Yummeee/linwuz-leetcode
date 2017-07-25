/*
 * Longest Palindromic Substring (Medium)
 * 
 * Given a string s, find the longest palindromic (就是一个字符串从左往右和从右往左读是完全一样的...一开始理解错了) substring in s. You may assume that the 
 * maximum length of s is 1000.
 * 
 * e.g.,
   Input: "babad"
   Output: "bab"
   Note: "aba" is also a valid answer.
 */

/*
 * 此题首先要搞清楚是输出所有还是只输出一个解，这点题目没有说清楚（根据题意应该是求一个即可，但是面试中还是要沟通清楚）
 * 我的思路（有点类似那道找不重复最长子串的题）：
 * 首先要写一个判断算法，确定一个字符串是否是回文串，由于回文串是一个递归结构，所以可以用递归或迭代的方式，时间复杂度均为O(length/2)
 * Brute Force, 找到所有子串，一个一个判断是否是回文串 (提交显示超时)，O(n^3)
 * 需要找到一个削减需要判断的子串个数的方法或者缩短判断一个子串为回文串的时间
 * 
*/

/*
 * Editorial Solution:
 * Approach #1 (Longest Common Substring) [Accepted] (Each time we find a longest common substring candidate, we check if 
 * the substring’s indices are the same as the reversed substring’s original indices. If it is, then we attempt to update 
 * the longest palindrome found so far; if not, we skip this and find the next candidate.)
 * 
 * 
 * Approach #2 (Brute Force) [Time Limit Exceeded]
 * The obvious brute force solution is to pick all possible starting and ending positions for a substring, and verify if it 
 * is a palindrome.
 * 
 * 
 * Approach #3 (Dynamic Programming) [Accepted] //即对我的Brute Force做了改进，减少了很多次重复判断，比如我已经知道了一个串中间的子串aba为回文
 * 判断cabac时不需要再判断aba，直接看开头结尾相等与否
 * We define P(i,j) as following:
 * P[i,j] = true， s[i] = s[j]且P[i+1,j-1] = true
 * P[i,j] = false, 其余情况
 * P[i,i] = true, P[i,i+1] = true if s[i]=s[i+1]
 * 这个动态规划公式和我的递归判断式简直一模一样
 * This yields a straight forward DP solution(二维动态规划), which we first initialize the one and two letters palindromes, and work our 
 * way up finding all three letters palindromes, and so on...
 * 完成这个矩阵的构建即找到目标，时间复杂度O(n^2)，空间复杂度O(n^2)，但是相比方法4显然其可以找到所有最优解;
 * 
 * 
 * Approach #4 (Expand Around Center) [Accepted]
 * In fact, we could solve it in O(n^2) time using only constant space.
 * We observe that a palindrome mirrors around its center. Therefore, a palindrome can be expanded from its center, and there
 * are only 2n-1 such centers.
 * You might be asking why there are 2n - 1 but not n centers? The reason is the center of a palindrome can be in between
 * two letters. Such palindromes have even number of letters
	public String longestPalindrome(String s) {
	    int start = 0, end = 0;
	    for (int i = 0; i < s.length(); i++) {
	        int len1 = expandAroundCenter(s, i, i);		//考虑子串长为奇数情况
	        int len2 = expandAroundCenter(s, i, i + 1);	//考虑子串长为偶数情况，即中心为一个虚拟的点
	        int len = Math.max(len1, len2);
	        if (len > end - start) {
	            start = i - (len - 1) / 2;
	            end = i + len / 2;
	        }
	    }
	    return s.substring(start, end + 1);
	}
	
	private int expandAroundCenter(String s, int left, int right) {
	    int L = left, R = right;
	    while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
	        L--;
	        R++;
	    }
	    return R - L - 1;
	}
 *
 *
 *Approach #5 (Manacher's Algorithm) [Accepted]
 *There is even an O(n) algorithm called Manacher's algorithm, explained here in detail. However, it is a non-trivial 
 *algorithm, and no one expects you to come up with this algorithm in a 45 minutes coding session. But, 
 *please go ahead and understand it, I promise it will be a lot of fun.
 */


/*
 * substring(i,j)是取下标i到j-1的子串，不包含下标j!!!! 
 */
import java.util.*;
public class question5 {
	 public boolean isPalindrome1(String p) {         //we assume p's length is bigger than 0 here
		 boolean flag = true;
	     int start = 0;
	     if(p.length()%2 == 0) {         //even length string
	    	 start = p.length()/2;
	     }
	     else {
	    	 start = p.length()/2-1;
	     }
	     for(int i=start; i>=0; i--) {
	    	 if((p.charAt(i)^p.charAt(p.length()-i-1)) != 0) {      //two characters are not same
	    		 flag = false;
	             break;
	    	 }
	     }
	     return flag;
	 }
	    
    public boolean isPalindrome2(String p) {         //此方法有动态规划的影子
        if(p.length() == 1) {           //exit point
            return true;
        }
        else if(p.length() == 0) {
            return true;
        }
        else {                          //main loop
        	return ((p.charAt(0)^p.charAt(p.length()-1)) == 0)&&isPalindrome2(p.substring(1,p.length()-1));
        }
    }
    //此方法就等价于编辑给的解法4！！！！
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        
        int length = s.length();    
        int max = 0;
        String result = "";
        for(int i = 1; i <= 2 * length - 1; i++){
            int count = 1;
            while(i - count >= 0 && i + count <= 2 * length  && get(s, i - count) == get(s, i + count)){
                count++;
            }
            count--; // there will be one extra count for the outbound #
            if(count > max) {
                result = s.substring((i - count) / 2, (i + count) / 2);
                max = count;
            }
        }
        
        return result;
    }
    
    private char get(String s, int i) {			//相当于把原串组织成# a[0] # a[1] # a[2] # ...
        if(i % 2 == 0)
            return '#';
        else 
            return s.charAt(i / 2);
    }
}
