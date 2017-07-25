/*
 * Longest Uncommon Subsequence I (Easy)
 * 
 * Given a group of two strings, you need to find the longest uncommon subsequence of this group of two strings. The longest 
 * uncommon subsequence is defined as the longest subsequence of one of these strings and this subsequence should not be any 
 * subsequence of the other strings.
 * 
 * A subsequence is a sequence that can be derived from one sequence by deleting some characters without changing the order
 * of the remaining elements. Trivially, any string is a subsequence of itself and an empty string is a subsequence of any 
 * string.
 * 
 * The input will be two strings, and the output needs to be the length of the longest uncommon subsequence. If the longest 
 * uncommon subsequence doesn't exist, return -1.
 * 
 * e.g.,
 * Input: "aba", "cdc"
   Output: 3
   Explanation: The longest uncommon subsequence is "aba" (or "cdc"), 
   because "aba" is a subsequence of "aba", 
   but not a subsequence of any other strings in the group of two strings.
   
 * Note:
   Both strings' lengths will not exceed 100.
   Only letters from a ~ z will appear in input strings.
 */

/*
 * 思路：(好险，注意这里的子串定义不同于一般的子串，aa也是aba的子串，所以字符串a有2^(a.length())个子串...)
 * 这个包装的这么厉害，仔细一分析，发现巨简单！
 * 1.如果两个串长度不等，显然最长不相等子串是那个较长的串
 * 2.如果两个串长度相等，若两个串相等，显然无解
 * 3.如果两个串长度相等，但不等，最长不相等子串就是两个串本身
 * 且1和3可以合并为一种情况
 * 时间复杂度O(min(a.length(),b.length()))
 * 
 * 还有Brute Force方法，超时。将a,b的所有子串存入一个hash表中，值为子串出现的次数，最后次数为1的子串的长度即为所求
 *  public int findLUSlength(String a, String b) {
        HashMap < String, Integer > map = new HashMap < > ();
        for (String s: new String[] {a, b}) {
            for (int i = 0; i < (1 << s.length()); i++) {			//i循环2^length()次，将i化为二进制即等于所有子串的情况，对应位取1则此子串含有该字符
                String t = "";
                for (int j = 0; j < s.length(); j++) {
                    if (((i >> j) & 1) != 0)
                        t += s.charAt(j);
                }
                if (map.containsKey(t))
                    map.put(t, map.get(t) + 1);
                else
                    map.put(t, 1);
            }
        }
        int res = -1;
        for (String s: map.keySet()) {
            if (map.get(s) == 1)
                res = Math.max(res, s.length());
        }
        return res;
    }
 */
public class question521 {
	public int findLUSlength(String a, String b) {
        if(a.equals(b)) {        //include two empty strings
            return -1;
        }
        else {
            return Math.max(a.length(), b.length());
        }
    }
}
