/*
 * Number of 1 Bits (Easy)
 * 
 * Write a function that takes an unsigned integer and returns the number of ’1' bits it has (also known as the Hamming weight).
 * 
 * For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, so the function should return 3.
 */
/*
 * 思路：
 * 1.直接调用Integer的bitCount方法...简直丧病
 * 2.Bit Manipulation: 有两种计算方法
 * 1)常规的每次右移一位n，并与1相与的结果+count(count += n&1; n = n>>1;)
 * 2)n&(n-1)，如果n!=0，则count++。这是一种trick方法，每次可以将最后的一个one bit给清掉
 */
public class question191 {
	// you need to treat n as an unsigned value
    public int hammingWeight1(int n) {
        return Integer.bitCount(n);
    }
    
    public int hammingWeight(int n) {
    	int count = 0;
    	while(n != 0) {
    		n = n&(n-1);
    		count++;
    	}
        return count;
    }
}
