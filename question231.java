/*
 * Power of Two (Easy)
 * 
 * Given an integer, write a function to determine if it is a power of two.
 */
/*
 * 完全类比question326
 * 但是比326多一种解法：因为2的幂永远的二进制只有一个1，实际上等价于326的转化为3进制
 * public boolean isPowerOfTwo(int n) {
        return n>0 && Integer.bitCount(n) == 1; //或者((n & (n-1)) == 0) && n>0
    }
 */
public class question231 {
	public boolean isPowerOfTwo(int n) {
        return n>0 && Math.pow(2, 31)%n == 0? true:false;
    }
}
