/*
 * Complex Number Multiplication (Medium)
 * 
 * Given two strings representing two complex numbers. 复数乘法
   You need to return a string representing their multiplication. Note i2 = -1 according to the definition.
 *  
 * Example 1:
 * Input: "1+1i", "1+1i"
   Output: "0+2i"
   Explanation: (1 + i) * (1 + i) = 1 + i^2 + 2 * i = 2i, and you need convert it to the form of 0+2i.  
 * Example 2:
 * Input: "1+-1i", "1+-1i"
   Output: "0+-2i"
   Explanation: (1 - i) * (1 - i) = 1 + i^2 - 2 * i = -2i, and you need convert it to the form of 0+-2i. 
 *  
 * Note:
   The input strings will not have extra blank.
   The input strings will be given in the form of a+bi, where the integer a and b will both belong to the range of [-100, 100]. 
   And the output should be also in this form.
 */
/*
 * 简单的复数乘法，取出实部虚部的值依次计算即可，(x1+y1*i) * (x2+y2*i) = (x1x2-y1y2) + (x1y2+x2y1)i
 * 编辑给的解法基本一样，只不过是用的split而不是indexOf取实部虚部的值
 * public String complexNumberMultiply(String a, String b) {
        String x[] = a.split("\\+|i");
        String y[] = b.split("\\+|i");
        int a_real = Integer.parseInt(x[0]);
        int a_img = Integer.parseInt(x[1]);
        int b_real = Integer.parseInt(y[0]);
        int b_img = Integer.parseInt(y[1]);
        return (a_real * b_real - a_img * b_img) + "+" + (a_real * b_img + a_img * b_real) + "i";
    }
  Time complexity : O(1)O(1). Here splitting takes constant time as length of the string is very small (<20)(<20).
  Space complexity : O(1)O(1). Constant extra space is used.
 */
public class question537 {
	public String complexNumberMultiply(String a, String b) {
        if(a == null || b == null || a.length() == 0 || b.length() == 0) {
            return "";
        }
        
        int x1, y1 ,x2, y2;
        int index1 = a.indexOf('+');
        x1 = Integer.parseInt(a.substring(0, index1));
        y1 = Integer.parseInt(a.substring(index1 + 1, a.indexOf('i')));
        int index2 = b.indexOf('+');
        x2 = Integer.parseInt(b.substring(0, index2));
        y2 = Integer.parseInt(b.substring(index2 + 1, b.indexOf('i')));
        return (x1 * x2 -  y1 * y2) + "+" + (x2 * y1 +  x1 * y2) + "i";
    }
}
