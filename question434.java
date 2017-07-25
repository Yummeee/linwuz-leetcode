/*
 * Number of Segments in a String (Easy)
 * 
 * Count the number of segments in a string, where a segment is defined to be a contiguous sequence of non-space characters.
 * 
 * Please note that the string does not contain any non-printable characters.
 */

/*
 * 思路：很简单的字符串问题，实现方法有很多
 * 1.查非空格段的数量，如果不使用我这样的方法，就一定要考虑第一个字符不是空格的处理：
 * public int countSegments(String s) {
		int ans = 0;
		char[] c = s.toCharArray();
		for ( int i = 0; i < c.length; ++i ) {
			if ( c[i] != ' ' && ( i == 0 || c[i-1] == ' ' ) ) ++ans;
		}
		return ans;
    }
 * 2.利用Java自带的split()方法，但是使用的是split(String regex)，利用正则表达式来匹配多个连续空格的情况，使得返回的数组一定不含空串元素：(split效率较低)
 * public int countSegments(String s) {
    return ("x " + s).split(" +").length - 1;			//"x "处理了s本身全部由空格，或开头跟若干个空格这种情况
}
 * 不添加"x "的方法，将s开头结尾的空格全部去掉
 * public int countSegments(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        
        return s.trim().isEmpty() ? 0 : s.trim().split("\\s+").length;	// \\s代表空格
    }
 */
public class question434 {
	public int countSegments(String s) {
        if(s.length() == 0) {
            return 0;
        }
        
        int count = 0;
        boolean flag = false;
        for(int i=0; i<s.length(); i++) {
            if(s.charAt(i) != ' ') {
                if(flag == false) {
                    flag = true;
                    count++;
                }
            }
            else {
                flag = false;
            }
        }
        return count;
    }
}
