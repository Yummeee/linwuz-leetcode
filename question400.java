/*
 * Nth Digit (Easy)
 * 
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * 
 * Note:
   n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 */
/* 
 * 思路：Math.ceil()操作数必须要double型才能得到想要的结果！！
 * 首先找到含有第n个digit的数的位数，再找到含有第n个digit的正整数，随后确定其第几位是nth digit
 * 精简的代码：
 * public int findNthDigit(int n) {
		int len = 1;			//位数
		long count = 9;			//基数9
		int start = 1;			//含有第n个digit的数的位数的起始数，即我的base/10

		while (n > len * count) {
			n -= len * count;
			len += 1;
			count *= 10;
			start *= 10;
		}

		start += (n - 1) / len;		//找到含有第n个digit的正整数，这里的n就是我的dif
		String s = Integer.toString(start);
		return Character.getNumericValue(s.charAt((n - 1) % len));
	}
 */
public class question400 {
	public int findNthDigit(int n) {
        if(n < 10) {
            return n;
        }
        
        long digits = 0, base = 1, num = 0, bit = 0;			//digits有溢出风险，所以设成long
        int i = 1, dif = 0;
        while(digits < n) {			//首先确定含有nth digit的数有几位，即i-1，正整数中1位数有9个、2位数由90个（180位）、3位数有900个（2700位）...
            digits += i * 9 * base;
            base *= 10;
            i++;		
        }
        digits -= (--i) * 9 * (base/10);
        dif = n - (int)digits;
        num = base/10 + (int)Math.ceil(1.0 * dif/i) - 1;		//含有nth digit的正整数，base/10是该位数的第一个数
        if(dif%i != 0) {										
        	bit = i - dif%i;
        	i = 0;
            while(i++ < bit) {
                num /= 10;
            }
        }
        return (int)(num % 10);
    }
	
	public static void main(String[] args) {
		System.out.println(new question400().findNthDigit(10));
	}
}
