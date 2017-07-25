/*
 * Reverse Integer (Easy)
 * 
 * Reverse digits of an integer.
   Example1: x = 123, return 321
   Example2: x = -123, return -321
 *  
 * Note:
   The input is assumed to be a 32-bit signed integer. Your function should return 0 when the reversed integer overflows.  
 */
/*
 * 思路：一开始我想了若干种判断溢出的方法，结果发现无论正数还是负数，逆转过来后有可能仍是正数或负数，所以符号判断不可取。且当最低位是不超过2时，次低位或者以后的位仍可能导致翻转后溢出，所以判断某一特定位的范围来确定是否溢出不可取
 * 1.计算翻转后的值，将ans首先置为long，这样一定不会溢出。然后将ans转换为int型，若是溢出的情况则ans != (int)ans，时间复杂度O(1)
 * 2.ans的类型仍为int，在计算ans的循环中判断当前迭代ans有可能溢出没有。若ans > (MAX_VALUE - x%10) / 10，则当前迭代会导致溢出。感觉这种做法更proper。
 */
public class question7 {
	public int reverse(int x) {			//思路2精简，不需要区分正负，且不用考虑减去余数，因为溢出一定是发生在最后一次循环，而且一定不会是因为最高位翻转到最低位而溢出的，因为最高位只可能是0、1、2，否则就不满足x为int的条件。只可能是在这之前导致溢出的位数已经出现过了
        int ans = 0;
        while(x != 0) {
        	if(Math.abs(ans) > Integer.MAX_VALUE / 10) {
        		return 0;
        	}
            ans *= 10;
            ans += x % 10;				//还可以算(ans*10 + x%10)/10是否等于ans
            x = x/10;
        }
        return ans;
    }
	
	public int reverse2(int x) {			//思路2
		if(x == Integer.MIN_VALUE) {
			return 0;
		}
		
		//int sign = x < 0 ? -1 : 1;
		//x = Math.abs(x);
		int sign = 1;
		if(x < 0) {
			sign = -1;
			x = -x;
		}
        int ans = 0;
        while(x != 0) {
        	if(ans > (Integer.MAX_VALUE - (x % 10)) / 10) {
        		return 0;
        	}
            ans *= 10;
            ans += x % 10;
            x = x/10;
        }
        return ans * sign;
    }
	
	public int reverse1(int x) {			//思路1
        //if(Math.abs(x) < 10) {    x为MIN_VALUE的时候也会溢出    
            //return x;
        //}
        
        long ans = 0;               
        while(x != 0) {
            ans *= 10;
            ans += x % 10;
            x = x/10;
        }
        return ans == (int)ans ? (int)ans : 0;
    }
}
