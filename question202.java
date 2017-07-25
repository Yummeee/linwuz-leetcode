/*
 * Happy Number (Easy)
 * 
 * Write an algorithm to determine if a number is "happy".
 * A happy number is a number defined by the following process: Starting with any positive integer, replace the number by the sum of the 
 * squares of its digits, and repeat the process until the number equals 1 (where it will stay), or it loops endlessly in a cycle which does
 * not include 1. Those numbers for which this process ends in 1 are happy numbers.
 */
/*
 * 思路：
 * 非Happy Number在不断重复题目叙述的过程中一定会重复计算到一个值且不为1，从而使得其永远在循环中，这样就可以使用HashSet来保存每一次计算的结果，如果重复出现必然是假的。实际上只各位出现的所有数字一样（顺序可以不一致）即可判断为假，但是这样要求所有的排列组合，代价较高
 * 数学证明：
 * Earlier posts gave the algorithm but did not explain why it is valid mathematically, and this is what this post is about: present a "short" mathematical proof.

First of all, it is easy to argue that starting from a number I, if some value - say a - appears again during the process after k steps, the initial number I cannot be a happy number. Because a will continuously become a after every k steps.

Therefore, as long as we can show that there is a loop after running the process continuously, the number is not a happy number.

There is another detail not clarified yet: For any non-happy number, will it definitely end up with a loop during the process? This is important, because it is possible for a non-happy number to follow the process endlessly while having no loop.

To show that a non-happy number will definitely generate a loop, we only need to show that for any non-happy number, all outcomes during the process are bounded by some large but finite integer N. If all outcomes can only be in a finite set (2,N], and since there are infinitely many outcomes for a non-happy number, there has to be at least one duplicate, meaning a loop!

Suppose after a couple of processes, we end up with a large outcome O1 with D digits where D is kind of large, say D>=4, i.e., O1 > 999 (If we cannot even reach such a large outcome, it means all outcomes are bounded by 999 ==> loop exists). We can easily see that after processing O1, the new outcome O2 can be at most 9^2*D < 100D, meaning that O2 can have at most 2+d(D) digits, where d(D) is the number of digits D have. It is obvious that 2+d(D) < D. We can further argue that O1 is the maximum (or boundary) of all outcomes afterwards. This can be shown by contradictory: Suppose after some steps, we reach another large number O3 > O1. This means we process on some number W <= 999 that yields O3. However, this cannot happen because the outcome of W can be at most 9^2*3 < 300 < O1.

Done.

Please leave your comment if any question or suggestion.


还有一种方法：
规律发现快乐数一定中间出现4！！！！
 public boolean isHappy(int n) {
        while (n != 1 && n != 4) {
            int t = 0;
            while (n) {
                t += (n % 10) * (n % 10);
                n /= 10;
            }
            n = t;
        }
        return n == 1;
    }
 */
import java.util.*;
public class question202 {
	public boolean isHappy(int n) {
        if(n == 1) {
            return true;
        }
        
        HashSet<Integer> set = new HashSet<>();
        while(set.add(n)) {
            int sum = 0;
            while(n > 0) {
                int temp = n%10;
                sum += temp*temp;
                n = n/10;
            }
            n = sum;
        }
        if(n != 1) {
        	return false;
        }
        return true;
    }
}
