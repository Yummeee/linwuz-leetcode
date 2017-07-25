/*
 * Construct the Rectangle (Easy)
 * For a web developer, it is very important to know how to design a web page's size. So, given a specific rectangular web 
 * page’s area, your job by now is to design a rectangular web page, whose length L and width W satisfy the following 
 * requirements:
 * 1. The area of the rectangular web page you designed must equal to the given target area.

   2. The width W should not be larger than the length L, which means L >= W.

   3. The difference between length L and width W should be as small as possible.
 * You need to output the length L and the width W of the web page you designed in sequence.
 * 
 * Example:
 * Input: 4
 * Output: [2, 2]
 * Explanation: The target area is 4, and all the possible ways to construct it are [1,4], [2,2], [4,1]. 
   But according to requirement 2, [1,4] is illegal; according to requirement 3,  [4,1] is not optimal compared to [2,2]. 
   So the length L is 2, and the width W is 2.
   
 * Note:
 * The given area won't exceed 10,000,000 and is a positive integer
 * The web page's width and length you designed must be positive integers.
 * 此题等价于求一个数的所有因子
 */

/*
 * 思路：
 * 1.从L=sqrt(n)开始找，第一个满足L为n的因子，且大于n/L的即为目标解，时间复杂度O(sqrt(n));
 * 2.从W=sqrt(n)开始向下找，减少运算，可以省略一个取最大值的过程！！时间从93ms降到了6ms，哈哈哈哈哈我输在了这里！
 */

public class question492 {
	public int[] constructRectangle1(int area) {
        //given area is positive, so no need to check the extreme situation
        int L = 0, W = 0;
        L = (int)Math.sqrt(area);
        while(area%L != 0) {
            L++;
        }
        
        L = Math.max(L, area/L); 
        W = area/L;
        int[] ans = {L, W};
        return ans;
    }
}
