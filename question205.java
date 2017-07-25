/*
 * Isomorphic Strings (Easy)
 * 
 * Given two strings s and t, determine if they are isomorphic.
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while preserving the order of characters. No two 
 * characters may map to the same character but a character may map to itself.
 * 
 * For example,
   Given "egg", "add", return true.
   Given "foo", "bar", return false.
   Given "paper", "title", return true.
 * Note:
   You may assume both s and t have the same length.
 */
/* 答案里算的快的都是默认的输入时acsii，用的256的数组...不科学，这种方法在面试中如果没有交流不可取
 *  boolean isIsomorphic(String s, String t) {
        int[] m1 = new int[256];
        int[] m2 = new int[256];
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (m1[s[i]] != m2[t[i]]) return false;
            m1[s[i]] = i + 1;			//这里可能会溢出，因为String的长度可以超过2^32位
            m2[t[i]] = i + 1;
        }
        return true;
    }
    避免溢出修改成这样：
    int count = 1;
    for(int i=0; i<s.length(); i++) {
        if(m1[s[i]]!=m2[t[i]]) return false;
        if(m1[s[i]]==0) {
            m1[s[i]]=count;
	        m2[t[i]]=count;		//count最大不过256
            count++;
        }
    }
    
 * 思路：注意s和t是一一对应的关系，一个t中的字符只能被一个s中的字符所对应，a->b，c->b是不允许出现的。这里最初犯错了，只考虑了a->b，a->c这种情况
 * 1.使用hash表来表示s和t中字符间的映射关系，map.put(a, b)则表明a和b之间存在一个a->b的映射，前提是b没有被其它字符映射过。时间复杂度O(n^2)，因为containsValue是O(n)复杂度，空间复杂度O(n)
 * 2.改进方法1，再用一个HashSet来保存被映射的字符，从而替代containsValue()，时间复杂度O(n)
 */
import java.util.*;
public class question205 {
	public boolean isIsomorphic1(String s, String t) {
        if(s==null || t==null || s.length()!=t.length()) {
            return false;
        }
        
        HashMap<Character, Character> map = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            Character correspond = map.get(s.charAt(i));
            if(correspond == null) {
            	if(map.containsValue(t.charAt(i))) {			//这个操作效率比较低，应该有其它方法可以替代
            		return false;
            	}
                map.put(s.charAt(i), t.charAt(i));
            }
            else {
                if(!correspond.equals(t.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
	public boolean isIsomorphic2(String s, String t) {		//用HashSet来保存被映射的字符可以实现优化（对比HashMap）
        if(s==null || t==null || s.length()!=t.length()) {
            return false;
        }
        
        HashMap<Character, Character> map = new HashMap<>();
        HashSet<Character> set = new HashSet<>();
        for(int i=0; i<s.length(); i++) {
            Character correspond = map.get(s.charAt(i));
            if(correspond == null) {
            	if(!set.add(t.charAt(i))) {			//这个操作效率比较低，应该有其它方法可以替代
            		return false;
            	}
                map.put(s.charAt(i), t.charAt(i));
            }
            else {
                if(!correspond.equals(t.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
	/*
	public boolean isIsomorphic2(String s, String t) {			//失败的改进方法，但从理论分析时间复杂度变为O(n)。本来想用两个hash表来表示这个一一映射关系以避免使用containsValue，然而速度更慢
        if(s==null || t==null || s.length()!=t.length()) {		//或者遍历完s后将map清空然后重新遍历一遍t，也实际上变慢了
            return false;
        }
        
        HashMap<Character, Character> map1 = new HashMap<>();
        HashMap<Character, Character> map2 = new HashMap<>();
        for(int i=0; i<s.length(); i++) {
            Character correspond1 = map1.get(s.charAt(i));
            Character correspond2 = map2.get(t.charAt(i));
            if(correspond1 == null) {
                if(correspond2 == null) {
                    map1.put(s.charAt(i), t.charAt(i));
                    map2.put(t.charAt(i), s.charAt(i));
                }
                else {
                    return false;
                }
            }
            else {
                if(correspond2==null || !correspond1.equals(t.charAt(i))) {
                    return false;
                }
            }
        }
        return true;
    }
    */
}
