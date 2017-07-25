/*
 * Valid Perfect Square (Easy)
 * 
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * Note: Do not use any built-in library function such as sqrt.
 */
/*
 * 思路：
 * 从1,...,num/2中找到一个数k，使得k*k == num则为true，否则为false。又因为1,...,num/2有序，因此可以使用二分法查找。
 * 注意：要考虑四则运算超界的问题！！！！！！！当num足够大的时候，x*x很可能超界，x<=num/2.
 * 
 * 还有两种方法（属于数学方法，利用数的性质，有时候确实不好想到）：
 * 1.一个square数一定是1+3+5+7+9+...，所以：此方法时间复杂度O(sqrt(n))，因为不难发现1对应1个数，4对应前2个数的和，9对应前三个数的和，以此类推...
 * 时间复杂度还可以计算出来：
 * Let x be the number of iterations needed to solve the problem, and let Σ be the sum from i = 1 to x. Σ(1 + 2i) = n => x + 2Σi = n => x + 2(x(x+1)) = n => 2x^2 + 3x = n => x = [-3 +/- sqrt(9 + 8n)]/4 => you can see that n is in a square root term, so the complexity should be O(sqrt(n)).
 * public boolean isPerfectSquare(int num) {  //此方法没有超界的问道
     int i = 1;
     while (num > 0) {
         num -= i;
         i += 2;
     }
     return num == 0;
 }
 * 
 * 2.Newton Method: 时间复杂度近似为一个常数，用于计算一个数的sqrt
 * public boolean isPerfectSquare_newton(int num) { // O(1)
    // Newtons's method.
    // Find square root of num and square it
    // square == num ? true : false;

    long t = num;
    while (t * t > num) {
        t = (t + num / t) / 2;
    }
    return t * t == num;
}
 *
 */
public class question367 {
	public boolean isPerfectSquare(int num) {
        if(num == 1) {
            return true;
        }
        
        int low = 2;
        int high = num>>1;
        while(low <= high) {
            int mid = (low+high)>>1;
            long temp = (long)mid*mid;
            if(temp == num) {
                return true;
            }
            
            if(temp < num) {
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return false;
    }
	public static void main(String[] args) {
		System.out.println(new question367().isPerfectSquare(808201));
	}
}
