/*
 * Count and Say (Easy)
 * 
 * The count-and-say sequence is the sequence of integers beginning as follows:
 * 1, 11, 21, 1211, 111221, ...
 * 
 * 1 is read off as "one 1" or 11
 * 11 is read off as "two 1s" or 21
 * 21 is read off as "one 2, then one 1" or 1211.
 * Given an integer n, generate the nth sequence.
 * Note: The sequence of integers will be represented as a string.
 */
/*
 * 思路：
 * 就是简单的记字符数并输出，但是题意也太模糊了吧，错了几次都是没看懂题，字符串里也不是只有1和2。再一次证明了stringbuilder在连接上的高效
 */
public class question38 {
	public String countAndSay(int n) {
        StringBuilder ans = new StringBuilder();
        if(n == 0) {
            return ans.append("").toString();
        }
        
        ans.append("1");
        int i = 2, count = 1;;
        while(i <= n) {
            char[] str = ans.toString().toCharArray();
            ans = ans.delete(0, ans.length());
            for(int j=0; j<str.length; j++) {
                while(j<str.length-1 && str[j]==str[j+1]) {
                    count++;
                    j++;
                }
                ans.append(count);
                ans.append(str[j]);
                count = 1;
            }
            i++;
        }
        return ans.toString();
    }
	
	public static void main(String[] args) {
		System.out.println(new question38().countAndSay(6));
	}
}
