/*
 * Factorial Trailing Zeroes (Easy) 
 * 
 * Given an integer n, return the number of trailing zeroes in n!.
 * Note: Your solution should be in logarithmic time complexity.
 */
/*
 * 思路：
 * 由于2*5才能得到10，所以要考虑1...n中到底含有多少个5和多少个2，实际上5的数量远远比2的数量少，所以只需要考虑1...n的所有数中含有约数5的数量
 * 由于25含有2个5，n/5可能少算一个25中的5，同理125有3个5，n/5和n/25后还少算一个125中的5，所以要不断n/5，直至n == 0，第一个n/5考虑5^1，第二个n/5则
 * 对应5^2=25，以此类推
 * 时间复杂度就是log5(n)，满足题目要求，除法也不会存在Brute Force那种直接累乘溢出的情况
 */
public class question172 {
	public int trailingZeroes(int n) {
		int ans = 0;
        while(n > 0) {
			ans += n/5;
			n = n/5;
		}
		return ans;    
	}
}
