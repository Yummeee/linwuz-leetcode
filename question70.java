/*
 * Climbing Stairs (Easy)
 * 
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
   
  Note: Given n will be a positive integer.
 */
/*
 * 思路：
 * 1.动态规划（不难发现就是一个斐波那契数列），记N[k]为到top=k一共的攀爬方法数，N[1] = 1, N[2] = 2. 
 * 对于N[k](k>=3)，N[k] = N[k-1]+N[k-2]，N[k-1]和N[k-2]已经是n=k-1和n=k-2的全局最优解，N[k]只需要在这两者的基础上选择走1步或者2步，一次到达。不能在N[k-2]的基础上*2，因为*2则说明最后2阶也可走2次1阶，但这种情况可能和N[k-1]重复
 * 
 * 下面是编辑给出的其余的方法
 * 2.Brute Force: 时间复杂度O(2^n)(可以构造一棵近似完全二叉树，层数为n，则中间的结点数近似为2^n)，空间复杂度O(n)(最高n层树)
 * public class Solution {
    public int climbStairs(int n) {
        climb_Stairs(0, n);
    }
    public int climb_Stairs(int i, int n) {	//climbStairs(i,n)=(i+1,n)+climbStairs(i+2,n), where i defines the current step and n defines the destination step.
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        return climb_Stairs(i + 1, n) + climb_Stairs(i + 2, n);
    }
 * 
 * 3.Recursion with memorization [Accepted]，时间复杂度O(n)，空间复杂度O(n)
 * 改进Brute Force方法，后者对于一个当前阶梯k到楼顶的计算要被重复计算多次，用一个数组保存从k-top的攀爬种数，可以使得当前阶梯k到楼顶的计算只进行一次
 * public int climbStairs(int n) {
        int memo[] = new int[n + 1];
        return climb_Stairs(0, n, memo);
    }
    public int climb_Stairs(int i, int n, int memo[]) {
        if (i > n) {
            return 0;
        }
        if (i == n) {
            return 1;
        }
        if (memo[i] > 0) {
            return memo[i];
        }
        memo[i] = climb_Stairs(i + 1, n, memo) + climb_Stairs(i + 2, n, memo);
        return memo[i];
    }
 * 
 * 4.Fibonacci Number [Accepted]: 时间复杂度O(n)，空间复杂度O(1)，即和DP的唯一区别是不需要用一个数组保存每个子问题的最优解
 * public int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        int first = 1;
        int second = 2;
        for (int i = 3; i <= n; i++) {
            int third = first + second;
            first = second;
            second = third;
        }
        return second;
    }
 *    
 * 5.Binets Method [Accepted](不考虑此方法了)
 * This is an interesting solution which uses matrix multiplication to obtain the nth Fibonacci Number.
 * 6.Fibonacci Formula，时间复杂度O(logn)，空间复杂度O(1)，Math.pow的时间复杂度为O(logn)
 * 即利用斐波那契数列计算公式直接计算出结果
 * public int climbStairs(int n) {
        double sqrt5=Math.sqrt(5);
        double fibn=Math.pow((1+sqrt5)/2,n+1)-Math.pow((1-sqrt5)/2,n+1);
        return (int)(fibn/sqrt5);
    }
 */
public class question70 {
	public int climbStairs(int n) {
        if(n <= 2) {
            return n;
        }
        
        int[] N = new int[n];
        N[0] = 1;
        N[1] = 2;
        for(int i=2; i<n; i++) {
            N[i] = N[i-1]+N[i-2];
        }
        return N[n-1];
    }
}
