/*
 * Plus One (Easy)
 * 
 * Given a non-negative integer represented as a non-empty array of digits, plus one to the integer.
 * You may assume the integer do not contain any leading zero, except the number 0 itself.
 * The digits are stored such that the most significant digit is at the head of the list.(因此是从左向右读的数)
 */
/*
 * 思路：
 * 1.就是简单的加法运算，可以像链表加一样使用carry，注意由于这里是在原数组上直接修改，所以要首先保留原位+carry的结果，如果carry==0，则可直接退出返回修改后的digits，因为不可能再改变更高位的数了
 * 2.不使用carry，直接若该位小于9，则加1并退出，否则置0；
 * public int[] plusOne(int[] digits) {
    for (int i = digits.length - 1; i >=0; i--) {
        if (digits[i] != 9) {
            digits[i]++;
            break;
        } else {
            digits[i] = 0;
        }
    }
    if (digits[0] == 0) {
        int[] res = new int[digits.length+1];
        res[0] = 1;
        return res;
    }
    return digits;
}
 */
public class question66 {
	public int[] plusOne(int[] digits) {
        if(digits.length == 0) {
            return digits;
        }
        
        int carry = 1;          //plus one
        for(int i=digits.length-1; i>=0; i--) {
            int temp = carry+digits[i];
            digits[i] = temp % 10;
            carry = temp / 10;
            if(carry == 0) {
            	return digits;
            }
        }
        
        int[] ans = new int[digits.length+1];
        ans[0] = 1;
        for(int i=1; i<ans.length; i++) {
            ans[i] = digits[i-1];
        }
        return ans;
    }
}
