/*
 * Assign Cookies (Easy)
 * 
 * Assume you are an awesome parent and want to give your children some cookies. But, you should give each child at most one 
 * cookie. Each child i has a greed factor gi, which is the minimum size of a cookie that the child will be content with; and
 * each cookie j has a size sj. If sj >= gi, we can assign the cookie j to the child i, and the child i will be content. Your
 * goal is to maximize the number of your content children and output the maximum number.
 * 
 * Note:
   You may assume the greed factor is always positive. 
   You cannot assign more than one cookie to one child
 */

/*
 * 思路：贪心方法，从最不贪心的孩子分配最接近其贪心值且令其满意的饼干大小，即为最优解。
 * 1.首先对孩子的贪心值和饼干的大小排序，从第一个孩子开始，将size不小于他的贪心值的第一块饼干分给他，时间复杂度O(n^2)
 * 2.两个指针分别指向排序后的贪心值和饼干大小数组，若g[i]<=s[j]，则i++，j++，满意人数++；否则j++，指针i,j任一个超出length则跳出，时间复杂度O(nlogn)，
 * 相比思路1，每次未从饼干大小数组的第一个开始重新遍历，减少了遍历必定错误解和已经分配解的操作。
 */
import java.util.*;
public class question455 {
	public int findContentChildren1(int[] g, int[] s) {
        int content = 0;
        if(g.length==0 || s.length==0) {
            return content;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        
        boolean increment = false;
        for(int i=0; i<g.length; i++) {
            for(int j=0; j<s.length; j++) {
                if(g[i] <= s[j]) {
                    increment = true;      //find a content child
                    s[j] = 0;       //this cookie has been assigned
                    break;
                }
            }
            if(increment == false) {
                break;
            }
            content++;
            increment = false;
        }
        return content;
	}
	
	public int findContentChildren(int[] g, int[] s) {
        if(g.length==0 || s.length==0) {
            return 0;
        }
        Arrays.sort(g);
        Arrays.sort(s);
        int i = 0, j = 0;
        while(i<g.length && j<s.length) {
            if(g[i] <= s[j]){
                i++;					
                j++;
            }
            else {
                j++;
            }
        }
        return i;				//实际上i=content!!
    }
}
