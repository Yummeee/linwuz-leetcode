/*
 * Implement strStr() (Easy)
 * 
 * Implement strStr(). 就是返回字符串中首次出现指定子串的索引下标，KMP算法啊...
   Returns the index of the first occurrence of needle in haystack, or -1 if needle is not part of haystack.
 */
//空串是任意串的子串！！！！
/*
 * 思路：
 * 1.特征串匹配，所以可以用KMP算法
 * 2.妈的，用java.String自带的indexOf算法最快，就一行...狗逼...
 * 3.用Substring和equals搭配也相当快...思路和brute force一样
 * 4.暴力的O(m*n)的brute force方法
 */
public class question28 {
	public int strStr1(String haystack, String needle) {
		return haystack.indexOf(needle);
	}
	
	public int strStr(String haystack, String needle) {
		if(haystack == null || needle == null || haystack.length() < needle.length()) {
            return -1;
        }
        if(needle.length() == 0) {
            return 0;
        }
        
        int[] next = getNext(needle);
        int i = 0, j = 0;
        while(i < haystack.length() && j < needle.length()) {
            if(j == -1 || haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            else {
                j = next[j];
            }
        }
        
        if(j == needle.length()) {
            return i - needle.length();
        }
        return -1;
    }
	
	public int[] getNext(String str) {			//原KMP求next算法
        int[] next = new int[str.length()];
        int k = -1, i = 0;
        next[0] = -1;
        while(i < str.length()-1) {
            if(k == -1 || str.charAt(i) == str.charAt(k)) {
                next[++i] = ++k;
            }
            else {
                k = next[k];
            }
        }
        return next;
    }
	
	public int[] getNext2(String str) {			//改进的求next算法
        int[] next = new int[str.length()];
        int k = -1, i = 0;
        next[0] = -1;
        while(i < str.length()) {
            if(k == -1 || str.charAt(i) == str.charAt(k)) {
                i++;
                k++;
                if(str.charAt(i) != str.charAt(k)) {
                    next[i] = k;
                }
                else {
                    next[i] = next[k];
                }
            }
            else {
                k = next[k];
            }
        }
        return next;
    }
}
