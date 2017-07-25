/*
 * Sqrt(x) (Easy)
 * 
 * Implement int sqrt(int x).
   Compute and return the square root of x.
 */
/*
 * 思路：类似367：square number那道题，解法基本一致
 * 1.Brute Force，从x/2一直遍历到1。时间复杂度O(n)
 * 2.由于需要从1...x/2找到目标值，可以用binary search来做，时间复杂度降低为O(logn)
 * 3.同square number那道题，还有一种newton解法。时间复杂度近似O(1)
 * t = x;
   while (t * t > x) {
        t = (t + x / t) / 2;
    }
    return t;
    
   4.使用位运算，太fancy，时间复杂度O(logn)
   public int sqrt(int x) {
    long ans = 0;
    long bit = 1l << 16;			//ans由于是x的平方，一定不超过16位
    while(bit > 0) {
        ans |= bit;
        if (ans * ans > x) {
            ans ^= bit;				//因为ans大于最后的结果，所以取消掉刚才或运算加上的一位，前面已经计算的高位结果由于异或特性被保留
        }
        bit >>= 1;
    }
    return (int)ans;
}
 */
public class question69 {
	public int mySqrt(int x) {
		//if(x < 0) {				//不加也被accept了，看来test case没有考虑负数
            //return 0;				//将Double.NaN强制转换成int值为0
        //}
        if(x <= 1) {
            return x;
        }
        
        int low = 0, high = x >> 1;			//low可以初始化为1，这样就不用考虑1的特殊情况了
        while(low <= high) {
            int mid = low + ((high - low) >> 1);		//shit，>>优先级和+一样
            long temp = (long)mid * mid;
            if(temp == x) {
                return mid;
            }
            else if(temp < x) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return high;
    }
	
	public static void main(String[] args) {
		System.out.println(new question69().mySqrt(2));
	}
}
