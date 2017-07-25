/*
 * Number Complement (Easy)
 * 
 * Given a positive integer, output its complement number. The complement strategy is to flip the bits of its binary 
 * representation.
 * 
 * e.g.,
   Input: 5
   Output: 2
   Explanation: The binary representation of 5 is 101 (no leading zero bits), and its complement is 010. So you need to 
   output 2.
 */

/*
 * 我的策略：
 * 很简单，先求补，并统计原数最高位是哪一位，构造一个位数与原数一致，各位全为1的数，并与求补后的数与运算将leading-zeros清零
 * 反了一个低级错误:
 * 第二个循环要循环bits-1次，不是bits次！
 */

/*
 * 还有一种更巧妙的方法，利用二进制的运算技巧，这样就不用算bits，直接求p
 *  int findComplement(int num) {
        int x = ~0;			//原方法这里用的unsigned，但是java没有unsigned，只有int，所以取非一定是包括第32位也取反
        while((x&num) != 0)
            x <<= 1;		//~x即为自己的方法求的p
        return ~x & ~num;
    }
 */

public class question476 {
	public int findComplement(int num) {
        int complement = ~num;
        
        int i = 0, bits = 0;     //bits is used to count the number of useful binary bit (not leading zeros) of num
        while(i < 32) {
            int temp = (num>>i)&1;
            if(temp == 1) {
                bits = i;          //after the while loop, count will hold the number of useful binary bit of num
            }
            i++;
        }
        
        int p = 1;
        for(i=1; i<=bits; i++) {	//find the number that has bits 1s 
            p = (p<<1)|1;
        }
        
        complement &= p;
        return complement;
    }
}
