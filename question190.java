/*
 * Reverse Bits (Easy)
 * 
 * Reverse bits of a given 32 bits unsigned integer.
 * For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 
 * (represented in binary as 00111001011110000010100101000000).
 * 
 * Follow up:
   If this function is called many times, how would you optimize it?
 * Related problem: Reverse Integer  
 */
/*
 * 思路：
 * 就每次取最低位加到已翻转的部分上，同时已翻转的部分要左移1位。取最低位用与1计算，>>>是无符号右移，而>>是带符号右移，等价于/2。这里必须要做无符号右移
 * 改进：斯坦福提出的一种算法，十分巧妙，利用类似归并排序的分治思想，16位16位交换，8再位8位交换，再4位4位交换，2位2位交换即可。
 * public int reverseBits(int n) {
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
    
 * Follow up：
 * 由于多次调用，考虑把之前计算的结果保存起来后面继续使用，显然整个数的翻转结果没有价值，则保存更低一级的单位的结果byte，保存一个int的4个byte的翻转结果到一
 * 个类的hash表。
 */
import java.util.*;
public class question190 {
	// you need treat n as an unsigned value
	HashMap<Integer, Integer> cache = new HashMap<>();				
    public int reverseBits(int n) {						//follow up
        int res = 0, bits = 0;
        while(n != 0) {
            int cur_byte = n & 0xff;
            res = (res << 8) | cache.getOrDefault(cur_byte, reverseByte(cur_byte));
            bits += 8;
            n = n >>> 8;
        }
        res = res << (32 - bits);
        return res;
    }
    public int reverseByte(int n) {
        int res = 0, bits = 0, origin = n;
        while(n != 0) {
            res = (res << 1) | (n & 1);
            bits ++;
            n = n >>> 1;
        }
        res = res << (8 - bits);
        cache.put(origin, res);
        return res;
    }
    
	public int reverseBits2(int n) {			//斯坦福改进
        n = (n >>> 16) | (n << 16);
        n = ((n & 0xff00ff00) >>> 8) | ((n & 0x00ff00ff) << 8);
        n = ((n & 0xf0f0f0f0) >>> 4) | ((n & 0x0f0f0f0f) << 4);
        n = ((n & 0xcccccccc) >>> 2) | ((n & 0x33333333) << 2);
        n = ((n & 0xaaaaaaaa) >>> 1) | ((n & 0x55555555) << 1);
        return n;
    }
	
	public int reverseBits3(int n) {			//改进变种，不用逻辑右移就可以做到	
        n = ((n >> 16) & 0x0000ffff) | (n << 16);
        n = ((n >> 8)  & 0x00ff00ff) | ((n << 8) & 0xff00ff00);
        n = ((n >> 4)  & 0x0f0f0f0f) | ((n << 4) & 0xf0f0f0f0);
        n = ((n >> 2) & 0x33333333) | ((n << 2) & 0xcccccccc);
        n = ((n >> 1) & 0x55555555) | ((n << 1) & 0xaaaaaaaa);
        return n;
    }
	
	public int reverseBits1(int n) {
        int res = 0, bits = 0;
        while(n != 0) {
        	res = (res << 1) | (n & 1);				//取最低位并加到已翻转的位数上
            bits++;
            n = n >>> 1;							//必须无符号右移
        }
        res = res << (32 - bits);
        return res;
    }
	
	public int countBits(int n) {
		n = (n & 0x55555555) + ((n >> 1) & 0x55555555);
		n = (n & 0x33333333) + ((n >> 2) & 0x33333333);
		n = (n & 0x0f0f0f0f) + ((n >> 4) & 0x0f0f0f0f);
		n = (n & 0x00ff00ff) + ((n >> 8) & 0x00ff00ff);
		n = (n & 0x0000ffff) + ((n >> 16) & 0x0000ffff);
		return n;
	}
	
	public static void main(String[] args) {
		System.out.println(new question190().countBits(0x00000003));
	}
}
