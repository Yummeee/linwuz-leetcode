/*
 * Arranging Coins (Easy)
 * 
 * You have a total of n coins that you want to form in a staircase shape, where every k-th row must have exactly k coins.
 * Given n, find the total number of full staircase rows that can be formed.
 * n is a non-negative integer and fits within the range of a 32-bit signed integer.
 */
/*
 * 思路：
 * 1.Brute Force，计算1+2+...+i，使得和刚好大于等于n，利用等差数列公式可以计算出时间复杂度为O(sqrt(n)).
 * k^2+k=2n => k = (sqrt(8*n+1)-1)/2
 * 2.利用二分搜索寻找思路1中的i，利用等差数列公式判断mid与目标解的关系，注意一旦用到四则运算可能会溢出！！！！！要先转化成long
 * 3.直接利用思路1的公式求解
 */
public class question441 {
	public int arrangeCoins1(int n) {
        if(n == 0) {
            return 0;
        }
        
        return (int)((Math.sqrt((long)8*n+1)-1)/2);		//或使用8.0*n转换为double型
    }
	public int arrangeCoins2(int n) {
        if(n == 0) {
            return 0;
        }
        
        long low = 1, high = n;
        while(low <= high) {
            long mid = (long)(low+high)>>1;
            long temp = (1+mid)*mid>>1;
            
            if(temp <= n) {
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return (int)low-1;
    }
	
	public int arrangeCoins(int n) {		//修改arrangeCoins2，不用long，避免溢出
        if(n == 0) {
            return 0;
        }
        
        int low = 0, high = n;				//这里必须设0开始，不然n为极大值时mid会发生溢出
        while(low <= high) {
            int mid = (low+high)>>1;
            
            if(0.5*mid+0.5*mid*mid <= n) {		// Note that 0.5 * mid * mid + 0.5 * mid does not have overflow issue as the intermediate result is implicitly autoboxed to double data type.
                low = mid+1;
            }
            else {
                high = mid-1;
            }
        }
        return (int)low-1;
    }
	
	public static void main(String[] args) {
		System.out.println(new question441().arrangeCoins(2147483647));
	}
}
