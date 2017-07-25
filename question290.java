/*
 * Word Pattern (Easy)
 * 
 * Given a pattern and a string str, find if str follows the same pattern.
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * 
 * Examples:
   pattern = "abba", str = "dog cat cat dog" should return true.
   pattern = "abba", str = "dog cat cat fish" should return false.
   pattern = "aaaa", str = "dog cat cat dog" should return false.
   pattern = "abba", str = "dog dog dog dog" should return false.
 * Notes:
   You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.  
 */

/*
 * 思路：此题有天坑，乍一看和question205基本一模一样，只不过从字符映射字符变成了字符一一映射单词，所以一开始我采用了205题的改进方法，只使用一个hash表，通
 * 过建立(a, 1), (word, 1)的组合来表示一一映射，但有一种case通不过，就是"abc"的pattern和"b c a"的str。发现由于205题是字符映射字符，a就是a，b就是b
 * 但是此题是字符映射字符串，'b'和"b"不同！所以前面的case是成立的，这样就不可能只用一个hash表了，只能乖乖一个hash表一个set表来表示一一映射。
 * 
 * 改进：仍使用一个HashMap，但是这个HashMap不指定键值对的类型，默认为Object，这样letter那里是Character，word那里是String类型，Java也会比较对象类型，
 * 这样就能区分'b'和"b"了
 */
import java.util.*;
public class question290 {
	public boolean wordPattern1(String pattern, String str) {
        if(pattern==null || str==null || pattern.length()==0 || str.length()==0) {
            return false;
        }
        
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) {
            return false;
        }
        String[] map = new String[26];
        HashSet<String> set = new HashSet<>();
        for(int i=0; i<strs.length; i++) {
            String n = map[pattern.charAt(i) - 'a'];
            if(n == null) {
                if(!set.add(strs[i])) {
                    return false;
                }
                map[pattern.charAt(i) - 'a'] = strs[i];
            }
            else {
                if(!n.equals(strs[i])) {
                    return false;
                }
            }
        }
        return true;
    }
	
	public boolean wordPattern(String pattern, String str) {
        if(pattern==null || str==null || pattern.length()==0 || str.length()==0) {
            return false;
        }
        
        String[] strs = str.split(" ");
        if(strs.length != pattern.length()) {
            return false;
        }
        HashMap map = new HashMap();
        for(Integer i=0; i<strs.length; i++) {				//如果这里用int i，则无法通过所有测试，调查发现是Java对基本数据类型的自动装箱和拆箱工作导致，当i>127时，java会为i建立两个不同的对象，哪怕i值相同，这时!=去比较对象的引用相同的数也是不等，这时必须用Objects.equals()来处理
            if(map.put(strs[i], i) != map.put(pattern.charAt(i), i)) {	//charAt非常重要
            	return false;
            }
        }
        return true;
	}
}
