/*
 * Longest Substring Without Repeating Characters (Medium)
 * 
 * Given a string, find the length of the longest substring without repeating characters.
 * Examples:
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * Given "bbbbb", the answer is "b", with the length of 1.
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring, "pwke" is a subsequence and not a substring.
 */


/*
 *Editorial Solution
 Approach #1 Brute Force [Time Limit Exceeded] O(n^3)
 public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int ans = 0;
        for (int i = 0; i < n; i++)
            for (int j = i + 1; j <= n; j++)
                if (allUnique(s, i, j)) ans = Math.max(ans, j - i);
        return ans;
    }

    public boolean allUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) return false;
            set.add(ch);
        }
        return true;
    }
}


Approach #2 Sliding Window [Accepted] O(2n)=O(n)
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            // try to extend the range [i, j]
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));   //省了一行代码...
                ans = Math.max(ans, j - i);
            }
            else {
                set.remove(s.charAt(i++));  //由于j没有动，所以可以一直删除到重复那个单词，这点由while和for循环的特性不同导致
            }
        }
        return ans;
    }
}

Approach #3 Sliding Window Optimized [Accepted] O(n)，相比方法二用的是HashMap，可以直接找到重复的单词的位置而不需循环多次，直接把前面的全清楚
这时HashMap中保存的就不是子串的字符了，而是所有字符及其最新的位置
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        Map<Character, Integer> map = new HashMap<>(); // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            if (map.containsKey(s.charAt(j))) {			//Math.max(map.get(s.charAt(j)), i)的值取i的含义：此重复的字符并不在我当前子串的范围内，是之前遇到时加入的，现子串已经将其跳过
                i = Math.max(map.get(s.charAt(j)), i); 	//直接找到重复字符原位置的下一位置（因此Map里存入的都是j+1）作为新子串的起始字符，然后重新算子串长度
            }											//由于map.get()如果没有返回的是null，所以这个if语句还不能省略，不然可以像下面的方法一样省略
            ans = Math.max(ans, j - i + 1);
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }
}


Java (Assuming ASCII 128)：O(n)
Commonly used tables are:
int[26] for Letters 'a' - 'z' or 'A' - 'Z'
int[128] for ASCII
int[256] for Extended ASCII

public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int n = s.length(), ans = 0;
        int[] index = new int[128]; // current index of character
        // try to extend the range [i, j]
        for (int j = 0, i = 0; j < n; j++) {
            i = Math.max(index[s.charAt(j)], i);
            ans = Math.max(ans, j - i + 1);
            index[s.charAt(j)] = j + 1;
        }
        return ans;
    }
}

 */


/*
 * 滑动窗口是解决array/string问题的常见方法之一！！！ 
 */

/*
 * 自己的犯得错误
 * 1：没有考虑字符串自身就无重复的情况，这时候max_length始终为0，所以最后要加一个判断
 * 2: 没有考虑字符串最后几个字符是最长无重复子串的情况，这时max_length仍为之前的旧值，所以最后要加一个判断
 * 3：没有确定输入的字符编码格式，直接认为就是256个ACSII码，因此用了一个256的整型数组来判断有没有重复
 * 4：没有考虑类似dvdf的情况，需要回溯而不是一次全部清空，即将clear()换成将重复字符即之前的所有字符remove，ArrayList会自动将所有剩余元素前移，代价较大，可以换LinkedList
 * 5：把清除操作放在了if(max_length <= ref.size()) 语句下，导致一些应该清除的情况没有清除，应该是只要出现重复，就一定要清除才对
 */

/*
 * 自己代码的优化改进：
 * 由于ArrayList在查询和插入、删除上效率较差，遍历数组时间复杂度为O(n)，可使用HashSet(不含重复元素)或HashMap去查找，时间复杂度为常数(HashSet就是底层使用的HashMap实现，把value设成一个默认的对象，key为存入的值)
 */

import java.util.*;

public class question3 {
    public int lengthOfLongestSubstring(String s) {
        int max_length = 0;         //max length of substring
        //int length = 0;             //length of current substring. If choose to use List, there is no need to use length
        //int[] ref = new int[256];      
        //ref is used to decide if there is a repeating character, if here is 256, the character sould be ASCII, if not, it is better to use List to save space
        ArrayList<Character> ref = new ArrayList<Character>();	//ref will always hold the current longest substring 
        for(int i=0; i<s.length(); i++) {
            char p = s.charAt(i);
            int pos = ref.indexOf(p);	//since ref is dynamic, this pos should be recorded to make sure remove() works well, or you use while(ref.get(0) !=p)
            if(pos != -1) {   //there is a repeating character
                if(max_length < ref.size()) {
                    max_length = ref.size();
                }
                for(int j=0; j<=pos; j++) {		//remove repeated character and characters in front of it
                	ref.remove(0);
                }  
                ref.add(p);
            }
            else {
                ref.add(p);
            }
        }
        
        if(max_length < ref.size()) {
            max_length = ref.size();
        }
        return max_length;
    }
    
   
    public static void main(String[] args) {
    	String str = "abcabcbb";
    	System.out.println("max length: " + new question3().lengthOfLongestSubstring(str));
    }
}