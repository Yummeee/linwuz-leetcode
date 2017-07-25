/*
 * Reverse String II (Easy)
 * 
 * Given a string and an integer k, you need to reverse the first k characters for every 2k characters counting from the start of the string.
 * If there are less than k characters left, reverse all of them. If there are less than 2k but greater than or equal to k characters, then 
 * reverse the first k characters and left the other as original.
 * 
 * e.g.,
 * Input: s = "abcdefg", k = 2 Output: "bacdfeg"
 * Restrictions:
   The string consists of lower English letters only.
   Length of the given string and k will in the range [1, 10000]
 */
/*
 * 思路：
 * 自己写或者利用java封装的方法交换字符即可
 */
public class question541 {
	public String reverseStr1(String s, int k) {
        if(s.length() <= k) {
            return new StringBuffer(s).reverse().toString();   
        }
        
        char[] str = s.toCharArray();
        int i = 0;
        for(; i+k<=str.length; i += k<<1)
        {
            for(int j=0; j<k>>1; j++) {
                char temp = str[i+j];
                str[i+j] = str[i+k-1-j];
                str[i+k-1-j] = temp;
            }
        }
        if(i<str.length-1) {
            for(int j=0; j<(str.length-i)>>1; j++) {
                char temp = str[i+j];
                str[i+j] = str[str.length-1-j];
                str[str.length-1-j] = temp;
            }
        }
        String reverse = String.valueOf(str);
        return reverse;
    }
	public String reverseStr(String s, int k) {
        if(s.length() <= k) {
            return new StringBuffer(s).reverse().toString();   
        }
        
        String reverse = "";
        int i = 0;
        for(; i+(k<<1)<=s.length(); i += k<<1)
        {
            reverse += new StringBuffer(s.substring(i,i+k)).reverse().toString() + s.substring(i+k,i+(k<<1));			
        }
        
        if(i+k<=s.length()) {		//剩下的数能凑够一个k
            reverse += new StringBuffer(s.substring(i,i+k)).reverse().toString() + s.substring(i+k,s.length());
        }
        else if(i<s.length()) {		//剩下的数凑不够一个k
             reverse += new StringBuffer(s.substring(i, s.length())).reverse().toString();
        }
        
        return reverse;
    }
	
	public static void main(String[] args) {
		System.out.println(new question541().reverseStr("abcdefg", 2));
	}
}
