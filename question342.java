/*
 * Power of Four (Easy)
 * 
 * Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 
 * Example:
 * Given num = 16, return true. Given num = 5, return false.
 * Follow up: Could you solve it without loops/recursion?
 */
/*
 * 参考326题
 * 但是还有一种新方法，4，8这种2的幂的数可以参考
 * The basic idea is from power of 2, We can use "n&(n-1) == 0" to determine if n is power of 2. 
 * For power of 4, the additional restriction is that in binary form, the only "1" should always located at the odd position. 
 * For example, 4^0 = 1, 4^1 = 100, 4^2 = 10000. So we can use "num & 0x55555555==num" to check if "1" is located at the odd 
 * position.
 * 
 * public boolean isPowerOfFour(int num) {
        return (num > 0) && ((num & (num - 1)) == 0) && ((num & 0x55555555) == num);
    }
 * 或者(num-1)%3==0可以代替(num & 0x55555555) == num
 */
public class question342 {
	public boolean isPowerOfFour1(int num) {
        String str = Integer.toString(num, 4);
        return str.matches("^10*$");
    }
	public boolean isPowerOfFour2(int n) {
        if(n == 1) {
            return true;
        }
        if(n%4!=0 || n==0) {
            return false;
        }
    
        while(n%4 == 0) {
           n /= 4;
        } 
        if(n != 1) {
            return false;
        }
        return true;
    }
}
