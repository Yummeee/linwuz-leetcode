/*
 * Sum of Two Integers (Easy)
 * 
 * Calculate the sum of two integers a and b, but you are not allowed to use the operator + and -.
 * Example: Given a = 1 and b = 2, return 3.
 */

/*
 * 思路：
 * 1.按位运算操作，经过实验可发现正正、正负、负负三种情况规则一致。本位的结果可观察得出，进位的结果利用真值表可求出逻辑表达式，然后对a、b的每一位都进行同样
 * 2.改进：使用半加法思想。将两位的异或等于本位的和（XOR的不进位加法性质）推广到多位数，先计算不带进位的和，再对和的每一位加上进位即可。
 * int getSum(int a, int b) {  
        while(b != 0)
        {  	
            int carry = a & b;  //两个数均为1才会发生进位（其中一个数在这里是原数不进位相加之和）
            a = a ^ b;  
            b = carry << 1;  //进位要和下一位相加
        }  
        return a;  
    }
 */

public class question371 {
	public int getSum(int a, int b) {       //bit manipulation, use & to decide whether there is a carry or not, use | to add
        int carry = 0;  
        int ai = 0, bi = 0, ci = 0;         //ai represents ith bit of a, bi represents ith bit of b, ci represents ith bit of result 
        int result = 0;
        
        for(int i=0; i<=31; i++) {
            ai = (a>>i)&1;
            bi = (b>>i)&1;
            ci = ai^bi^carry;
            carry = (ai&bi)|(ai&carry)|(bi&carry);            //update carry，use digit logic to get the update formulate
            result = (ci<<i)|result;
        }
        return result;
    }
}
