/*
 * Base 7 (Easy)
 * 
 * Given an integer, return its base 7 string representation.
 * 
 * e.g.,
 * Input: 100, Output: "202"
 * Input: -7, Output: "-10"
 * 
 * Note: The input will be in range of [-1e7, 1e7].
 */
/*
 * 思路：就是简单的取余并除计算，负数就算正数表示最后加负号即可，还可转换成递归算法：
 * public String convertTo7(int num) {
    if (num < 0)
        return '-' + convertTo7(-num);
    if (num < 7)
        return num + "";
    return convertTo7(num / 7) + num % 7;
}
 */
public class question504 {
	public String convertToBase7(int num) {
        int n = Math.abs(num);
        String ans = "";
        do {
            int residue = n%7;
            ans = String.valueOf(residue) + ans;
            n /= 7;
        } while(n > 0);
        if(num < 0) {
            ans = "-" + ans;
        }
        return ans;
    }
}
