/*
 * Add Strings (Easy)
 * 
 * Given two non-negative integers num1 and num2 represented as string, return the sum of num1 and num2
 * Note:
   The length of both num1 and num2 is < 5100.		//超长数，一般的int、long会溢出
   Both num1 and num2 contains only digits 0-9.
   Both num1 and num2 does not contain any leading zero.
   You must not use any built-in BigInteger library or convert the inputs to integer directly.
 */
/*
 * 思路：
 * 和之前question3类似，有一点需要注意，当有字符串的拼接操作时，StringBuilder > StringBuffer(多线程安全) > String的+或concat效率！！！
 */
public class question415 {
	public String addStrings(String num1, String num2) {
        if(num1.equals("0") || num2.equals("0")) {
            return num1.equals("0")? num2:num1;
        }
        
        StringBuilder result = new StringBuilder();
        int i = num1.length()-1, j = num2.length()-1;
        int carry = 0;
        while(i>=0 || j>=0) {
            int p = i>=0? num1.charAt(i)-'0' : 0;
            int q = j>=0? num2.charAt(j)-'0' : 0;
            int temp = p+q+carry;
            int cur_ans = temp%10;
            carry = temp/10;
            result.append(cur_ans);
            i--;
            j--;
        }
        if(carry > 0) {
            result = result.append(carry);
        }
        return result.reverse().toString();
    }
}
