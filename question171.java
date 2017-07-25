/*
 * Excel Sheet Column Number (Easy) (related to 168)
 * 
 * Related to question Excel Sheet Column Title
 * Given a column title as appear in an Excel sheet, return its corresponding column number.
 * 
 * e.g.,
 * A -> 1
   B -> 2
   C -> 3
   ...
   Z -> 26
   AA -> 27
   AB -> 28 
 */
/*
 * 思路：
 * 参考第168题，纯数学题。有一点注意！我们的第0位实际上是s串的最后一个数，正好是反的！！所以如果要是从计算公式的第0项开始算起，需要反过来...
 * 如果是先计算公式的第n-1项，就不需要，比如这样：
 * public int titleToNumber(String s) {
    
        int result  = 0;
        for (int i = 0; i < s.length(); i++){
            result *= 26;	//n-1's factor is 26^(n-1), so the first one will be zero
            result += ((s.charAt(i) - 'A') + 1);    
        }
    
        return result;
    }
 */
public class question171 {
	public int titleToNumber(String s) {
        if(s==null || s.length()==0) {
            return 0;
        }
        
        int num = 0;
        int multi_factor = 1;
        for(int i=s.length()-1; i>=0; i--) {
            num += multi_factor * (s.charAt(i)-'A'+1);
            multi_factor *= 26;
        }
        return num;
    }
}
