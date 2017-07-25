/*
 * Add Digits (Easy)
 * 
 * Given a non-negative integer num, repeatedly add all its digits until the result has only one digit.
 * For example:
   Given num = 38, the process is like: 3 + 8 = 11, 1 + 1 = 2. Since 2 has only one digit, return it.
   
 * Follow up:
   Could you do it without any loop/recursion in O(1) runtime?
 */

/*
 * 思路：
 * 1.递归方法，对各位求和，若结果小于10则返回，否则继续调用方法(对各位求和除了传统的取余计算方法外还可以将num转换为String并用对每一位减'0'得该位的值)
 * 2.迭代方法，将思路1编程循环
 * 一种更简洁的迭代计算方法：
 * public int addDigits(int num) {  
        while(num>=10){  
            num = (num/10)+num%10;  
        }  
        return num;  
    }  

 * 3.计算一些数会发现，从10开始随着数递增结果是1-9循环，所以直接对9取余，结果不为0就是最终的结果，否则结果为9，即求(n-1)%9+1
 */
public class question258 {
	public int addDigits1(int num) {
        if(num < 10) {
            return num;
        }
        
        int result = 0;
        while ((num/(double)10) > 0) {			//make 10 become doubel to make sure the result could be (0,1)
            result += num%10;
            num = num/10;
        }
        
        if(result < 10) {
            return result;
        }
        else {
            return addDigits1(result);
        } 
    }
	
	public int addDigits2(int num) {
        if(num < 10) {
            return num;
        }
        
        int result = 0;
        do {
                result = 0;					//if do not set result to 0, it will become infinite loop
                while ((num/(double)10) > 0) {
                    result += num%10;
                    num = num/10;
                }
                num = result;				//to start the new loop
        } while(result >= 10);
        
        return result;
    }
	
	public int addDigits(int num) {
        if(num < 10) {
            return num;
        }
        
        int result = 0;
        
        result = num%9;
        if(result == 0) {
            result = 9;
        }
        
        return result;
    }
}
