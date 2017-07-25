/*
 * Palindrome Number (Easy)
 * 
 * Determine whether an integer is a palindrome. Do this without extra space.
 * 
 * Some hints:
   Could negative integers be palindromes? (ie, -1)
   If you are thinking of converting the integer to string, note the restriction of using extra space.
   You could also try reversing an integer. However, if you have solved the problem "Reverse Integer", you know that the reversed integer might overflow. How would you handle such case?
   There is a more generic way of solving this problem.
 */
/*
 * 思路：看了一下别人的算法，默认的负数都不属于回文，出题的脑子有坑吧，也不写清楚还来个反问
 * 1.将数字转换成String后判断，时间复杂度O(n)，空间复杂度O(n)，n为整数的位数，不符合题目O(1)的空间复杂度的要求
 * 2.使用数学方法，利用%10和*10运算将数字翻转过来，翻转后的两数相等则说明是回文数，注意可能会溢出。实际上没要必要处理溢出，因为int范围内的满足要求的回文一定是不会溢出的，溢出的自然也不相等，不影响判断
 * 3.首先获取数的位数，然后每次取当前数的第一位和最高位进行比较，没有溢出问题
 */
public class question9 {
	public boolean isPalindrome1(int x) {			//没有处理溢出，避免溢出的话就是只翻转一半，一定不会溢出
        if(x < 0) {
            return false;
        }
        
        int reverse = 0, n = x;
        while(n != 0) {
            reverse = reverse*10 + n%10;
            n /= 10;
        }
        if(reverse == x) {
            return true;
        }
        return false;
    }
	
	public boolean isPalindrome2(int x) {			//避免溢出，只翻转一半，且利用比大小来避免判断原来数字的位数
        if(x < 0 || (x!=0 && x%10==0)) {			//除了10、220这种情况，不然算出来也可能是对的，10的倍数的必然不是回文串也
            return false;
        }
        
        int reverse = 0, n = x;
        while(n > reverse) {
            reverse = reverse*10 + n%10;
            n /= 10;
        }
        if(reverse==n || reverse/10==n) {
            return true;
        }
        return false;
    }
	
	public boolean isPalindrome(int x) {			//思路3，每次取对应位比较法
        if(x < 0) {			
            return false;
        }
        
        int base = 1, n = x;				//base说明x有多少位
        while(n/10 > 0) {
            base = base*10;
            n /= 10;
        }
         
        while(base > 1) {                   //无论数有奇数位还是偶数位都没有关系
        	int high = x/base;
        	int low = x%10;
        	if(high != low) {
        		return false;
        	}
        	x %= base;
        	x /= 10;
        	base /= 100;
        }
        return true;
    }
}
