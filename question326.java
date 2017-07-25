/*
 * Power of Three (Easy)
 * 
 * Given an integer, write a function to determine if it is a power of three.
 * Follow up:
   Could you do it without using any loop / recursion?
 */
/*
 * 思路：
 * 1.不断循环余3、除3，最后结果为1就是3的幂，速度超过88.75%
 * 2.计算log(3)(n)，利用换底公式等价于log(n)/log(3)，注意这里一定要用10为底数（我就是用e错了），不能用自然数或者2为底数，否则当n=243时会出错，原因是因为java的问题...现在问题就变成了判断log10n / log103是否为整数
 * 判断是否为整数的方法则很多
 * 如果一定要用e或者2为底，编辑给出的方法是：return (Math.log(n) / Math.log(3) + epsilon) % 1 <= 2 * epsilon; 破解浮点数精度误差使用epsilon，即a == b?用Math.abs(a-b)<epsilon来判断
 * https://discuss.leetcode.com/topic/33536/a-summary-of-all-solutions-new-method-included-at-15-30pm-jan-8th/2
 * 
 * 3.3^19 = 1162261467, 3^20>Max32Int，所以只需要对1162261467%n即可，为0即是幂...(靠这也太他妈投机取巧了)，但是此方法不适合基数为合数的情况，比如4、6...可用n=j*base^k求证，j为base的一个因数时也成立
 * public boolean isPowerOfThree(int n) {
    // 1162261467 is 3^19,  3^20 is bigger than int  
    return ( n>0 && 1162261467%n==0);			//n>0必须要，否则可能为负数
}
   如果我们不知道3^19大于Max32Int,可以这样求出来这个数：
   int maxPowerOfThree = (int)Math.pow(3, (int)(Math.log(0x7fffffff) / Math.log(3)));
    return n>0 && maxPowerOfThree%n==0
    
  * 4.将此数转换为以3进制表达串
  * The idea is to convert the original number into radix-3 format and check if it is of format 10* where 0* means k zeros with k>=0.
    public boolean isPowerOfThree(int n) {
    return Integer.toString(n, 3).matches("10*");
    }
 */
public class question326 {
	public boolean isPowerOfThree1(int n) {
        if(n == 1) {
            return true;
        }
        if(n%3!=0 || n==0) {
            return false;
        }
    
        while(n%3 == 0) {
           n /= 3;
        } 
        if(n != 1) {
            return false;
        }
        return true;
    }
	public boolean isPowerOfThree(int n) {
        String str = String.valueOf(Math.log10(n)/Math.log10(3));
        return str.matches("^[0-9]+\\.0+$")? true:false;
    }
}
