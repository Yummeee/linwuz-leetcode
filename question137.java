/*
 * Single Number II (Medium)，对应question260，Single Number III
 * 
 * Given an array of integers, every element appears three times except for one, which appears exactly once. Find that single one.
 * Note:
   Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */
/*
 * 思路：
 * 1.同样的，用HashMap做，但是在空间上表现差。
 * 2.统计每一位上1出现的次数，最后取余3的结果即为single number对应位上是0还是1，时间复杂度O(32n)。此方法可以拓展至其中所有数都出现k次
 * 除了一个数出现p次的情况（p % k != 0）,只需要将最后每一位的统计结果%k，若不为0则说明特殊的数该位为1。此方法也可以扩展到像260一样两个
 * 特殊的数（其中一个数出现b次，一个数出现c次，其余均出现a次）。最后每一位取余a的结果若为c%a或b%a，则说明在这一位这两个特殊的数不同，并
 * 以此将所有的数分为两组，这样每一组就变成了本题的情况，有一个数出现b/c次，其余数出现a次。
 * 这个方法相当于使用了32个计数器，每个计数器能统计到k次，k为除那个特殊的元素外其余元素在该位上1的出现的次数（到k就重置为0），实际上可以用
 * m >= logk个32bit数来实现，而不是32个m位计数器。
 * 
 * 3.更简洁的位运算，正如上面所说，用二进制模拟k进制运算。取m=logk个计数器记录这些数中每一位1的出现次数，例如如果除一个数外所有数出现7次，
 * 我们则可用三个计数器,ones,twos,threes来表示，当ones,twos,threes在某一位上均为1时，说明该位的1已经累积了7次，将三个数这一位均置0。
 * 否则按二进制的更新规则更新ones,twos,threes...，即当低位均为1时，本位置1，低位清0。不断统计，最后若特殊的数出现l次，找出那些出现为l次
 * 的二进制位（实际上最后剩下的对应计数（所有m个计数器对应位二进制之和）不为1的位即为特殊数不为1的位），即出现不为0的位，即为特殊数为1的位。
 * 更新规则如下：其中xm...x1分别代表m个32位的计数器，mask为进位的时候清0用。
 * for (int i : array) {
    xm ^= (xm-1 & ... & x1 & i);
    xm-1 ^= (xm-2 & ... & x1 & i);
    .....
    x1 ^= i;
    
    mask = ~(y1 & y2 & ... & ym)  where yj = xj  if kj = 1 and  yj = ~xj  if kj = 0 (j = 1 to m).
    //等价于mask = ~(x1 & x2 & ... & ~xj & ... & ym)
    xm &= mask;
    ......
    x1 &= mask;
}
  * 思路3也可以扩展到题260那种两个特殊数的情况。即不再统计m个计数器对应位之和不为0的那些位，而是找出m个计数器对应位之和为b%a或c%a，则代表两个
  * 特殊数在此位不一致。从而将所有数分为两组，重新进行思路3的统计即可。	
 */
public class question137 {
	int singleNumber(int A[], int n) {				//思路3的一般方法
	    int ones = 0, twos = 0, xthrees = 0;
	    for(int i = 0; i < n; ++i) {
	        //twos |= (ones & A[i]);
	    	twos ^= (ones & A[i]);
	        ones ^= A[i];
	        xthrees = ~(ones & twos);				//mask
	        ones &= xthrees;
	        twos &= xthrees;
	    }

	    return ones;
	}
	
	public int singleNumber(int[] A) {				//思路3的终极精炼方法
	    int ones = 0, twos = 0;
	    for(int i = 0; i < A.length; i++){
	        ones = (ones ^ A[i]) & ~twos;
	        twos = (twos ^ A[i]) & ~ones;
	    }
	    return ones;
	}
	
	public int singleNumber1(int[] nums) {			//思路2
        int[] digits = new int[32];  
        for(int i = 0; i < 32; i++)  
        {  
            for(int j = 0; j<nums.length; j++)  
            {  
                digits[i] += (nums[j] >> i) & 1;  
            }  
        }  
        int res = 0;  
        for(int i = 0; i<32; i++)  
        {  
            res += (digits[i] % 3) << i;  
        }  
        return res;  
    }
}
