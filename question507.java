/*
 * Perfect Number (Easy)
 * 
 * We define the Perfect Number is a positive integer that is equal to the sum of all its positive divisors except itself.
 * Now, given an integer n, write a function that returns true when it is a perfect number and false when it is not.
 * 
 * e.g.,
 * Input: 28
   Output: True
   Explanation: 28 = 1 + 2 + 4 + 7 + 14
 * Note: The input number n will not exceed 100,000,000. (1e8)
 */
/*
 * 思路：
 * 1.Brute Force, i从1一直遍历到sqrt(n)。因为超过sqrt(n)的i一旦能整除一定是比i小的，之前已经访问过对应的num/i了。最后统计能整除num的i和num/i的和，超时。时间复杂度O(sqrt(n))，空间复杂度O(1)
 * 2.Euclid-Euler Theorem [Accepted] 这个方法鬼能想出来...Euclid证明一个质数p，如果2^p-1次方也是质数，则2^(p-1) * (2^p-1)一定是一个完全数，在int范围内就几个满足条件的，而目前为止找到的48个完全数都是偶数，还没找到奇数
 * public class Solution {
    public int pn(int p) {
        return (1 << (p - 1)) * ((1 << p) - 1);				//2^(p-1) * (2^p-1)
    }
    public boolean checkPerfectNumber(int num) {
        int[] primes=new int[]{2,3,5,7,13,17,19,31};
        for (int prime: primes) {
            if (pn(prime) == num)
                return true;
        }
        return false;
    }
}
 */
public class question507 {
	public boolean checkPerfectNumber(int num) {
        if(num == 1) {
            return false;
        }
        
        int end = (int)Math.sqrt(num);
        long sum = 1;
        for(int i=2; i<=end; i++) {
            if(num%i == 0) {
                sum += i+num/i;
            }
        }
        if(sum == num) {
            return true;
        }
        return false;
    }
}
