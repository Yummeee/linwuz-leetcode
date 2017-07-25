/*
 * Valid Anagram (Easy)
 * 
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * For example,
   s = "anagram", t = "nagaram", return true.
   s = "rat", t = "car", return false.
 * 
 * Note:
   You may assume the string contains only lowercase alphabets.
 * Follow up:
   What if the inputs contain unicode characters? How would you adapt your solution to such case?
 */
/*
 * 思路：
 * 此题不能用异或，因为类似两个串都是每个字符出现偶数次也会得到0的结果，即结果会有假阳性
 * 1.利用整型数组记录26个字母的出现次数，时间复杂度O(n+26)，如果输入是unicode则利用hashmap，hashmap有多种遍历方式
 * 2.对两个串（首先转化为字符数组）排序，排序后的串相等则匹配。时间复杂度O(nlogn)
 * public boolean isAnagram(String s, String t) {
    if (s.length() != t.length()) {
        return false;
    }
    char[] str1 = s.toCharArray();
    char[] str2 = t.toCharArray();
    Arrays.sort(str1);
    Arrays.sort(str2);
    return Arrays.equals(str1, str2);
}

 */
public class question242 {
	public boolean isAnagram(String s, String t) {
        if(s.length()==0 && t.length()==0) {
            return true;
        }
        if(s.length() != t.length()) {
            return false;
        }
        
        int[] times = new int[26];
        for(int i=0; i<s.length(); i++) {
            times[s.charAt(i) - 'a']++;
            times[t.charAt(i) - 'a']++;
        }
        
        for(int i=0; i<26; i++) {
            if(times[i] != 0) {
                return false;
            }
        }
        return true;
    }
}
