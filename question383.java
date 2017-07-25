/*
 * Ransom Note (Easy)
 * 
 * Given an arbitrary ransom note string and another string containing letters from all the magazines, write a function that
 * will return true if the ransom note can be constructed from the magazines ; otherwise, it will return false.
 * 
 * Each letter in the magazine string can only be used once in your ransom note.
 * Note:
   You may assume that both strings contain only lowercase letters.
 *
 * e.g.,
   canConstruct("a", "b") -> false
   canConstruct("aa", "ab") -> false
   canConstruct("aa", "aab") -> true
 */

/*
 * 思路：
 * 1.使用HashMap，将magazine中字符存入，value为该字符出现次数。随后遍历ransom note字符串，哈希表中的字符数出现一次该字符则减一，若存在magazine中
 * 没有的字符或者value减为负数，则失败并退出；时间复杂度O(m+n)
 * 2.由于输入限定了只有26个字母，所以可以用一个整形数组来保存magazine中字符出现的次数。时间复杂度为O(m+n)，但显然更高效。
 * 
 */

import java.util.*;
public class question383 {
	public boolean canConstruct1(String ransomNote, String magazine) {		//O(m+n)
        if(ransomNote.length()==0 || ransomNote==null) {
            return true;
        }
        if(magazine.length()==0 || magazine==null) {
            return false;
        }
        
        boolean ans = true;
        HashMap<Character, Integer> map = new HashMap<>();
        for(int i=0; i<magazine.length(); i++) {
            Character temp = magazine.charAt(i);
            Integer n = map.get(temp);
            if(n == null) {
                map.put(temp, 1);
            }
            else {
                map.put(temp, ++n);			//n++就错了！下面的--n也是，时刻记着这两个式子的不同！
            }
        }
        for(int i=0; i<ransomNote.length(); i++) {
            Character temp = ransomNote.charAt(i);
            Integer n = map.get(temp);
            if(n == null) {
                ans = false;
                break;
            }
            else {
                if(n == 0) {
                    ans = false;
                    break;
                }
                else {
                    map.put(temp, --n);
                }
            }
        }
        
        return ans;
    }
	public boolean canConstruct(String ransomNote, String magazine) {
        int[] letters = new int[26];        //default value is 0s
        for(int i=0; i<magazine.length(); i++) {
            letters[magazine.charAt(i)-'a']++;
        }
        for(int i=0; i<ransomNote.length(); i++) {
            if((--letters[ransomNote.charAt(i)-'a']) < 0) {
                return false;
            }
        }
        
        return true;
    }
}
