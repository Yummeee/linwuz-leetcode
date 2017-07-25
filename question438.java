/*
 * Find All Anagrams in a String (Easy)
 * 
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.
 * Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.
 * The order of output does not matter.
 */
/*
 * 思路：
 * 1.利用hash表，由于输入只是英文小写字母，所以可以用int[26]来做hash表。首先在构建p的hash表时同时处理s的前p.length()个字符，结束后，得到的表中记录的是
 * p和s的前p.length()个字符的差距。随后依次访问s剩余的字符，每访问一个字符就将新的一个字符对应到hash表中，并从hash表中去除之前的第一个字符。就好像一直维
 * 持一个大小为p.length()的滑动窗口一样，每新加一个字符，去掉原窗口的第一个字符。若出现表中的所有数均为0，则输出当前窗口第一个字符的起始下标。时间复杂度O(26*m)，
 * 空间复杂度O(1);
 * 2.改进思路1，不使用一个judge函数每次遍历整个hash表来判断是否满足输出条件。使用一个left和一个right指针来表示滑动窗口的范围，right-left就是滑动窗口的
 * 大小。滑动窗口的大小在一开始经历增长之后维持在p.length()，用一个变量来表示目前窗口中的单词统计之后还剩下几个p中的单词有待匹配，count==0则说明找到一个
 * 子串，输出left。
 */
import java.util.*;
public class question438 {
	public List<Integer> findAnagrams1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()) {           //since p is non-empty, s.length()==0 is covered
            return res;
        }
        
        int[] table = new int[26];
        for(int i=0; i<p.length(); i++) {
            table[p.charAt(i) - 'a']++;
            table[s.charAt(i) - 'a']--;
        }
        
        int i = p.length();
        while(i < s.length()) {
            if(judge(table)) {
                res.add(i - p.length());
            }
            table[s.charAt(i-p.length()) - 'a']++;
            table[s.charAt(i) - 'a']--;
            i++;
        }
        if(judge(table)) {				//不在这里再判断一次最后一个字符可能会漏掉，又犯了一次类似的错误
            res.add(i - p.length());
        }
        return res;
    }
	
	public boolean judge(int[] table) {
        for(int i=0; i<table.length; i++) {
            if(table[i] != 0) {
                return false;
            }
        }
        return true;
    }
	
	public List<Integer> findAnagrams(String s, String p) {			//改进方法1
        List<Integer> res = new ArrayList<>();
        if(s.length() < p.length()) {           //since p is non-empty, s.length()==0 is covered
            return res;
        }
        
        int[] table = new int[26];
        for(int i=0; i<p.length(); i++) {
            table[p.charAt(i) - 'a']++;
        }
        
        int left = 0, right = 0, count = p.length();
        while(right < s.length()) {
        	//move right everytime, if the character exists in p's hash, decrease the count
            //current hash value >= 1 means the character is existing in p
            if(table[s.charAt(right++) - 'a']-- >= 1) {
                count--;
            }
            //when the count is down to 0, means we found the right anagram
            //then add window's left to result list
            if(count == 0) {
                res.add(left);
            }
            //if we find the window's size equals to p, then we have to move left (narrow the window) to find the new match window
            //++ to reset the hash because we kicked out the left
            //only increase the count if the character is in p
            //the count >= 0 indicate it was original in the hash, cuz it won't go beyond 0 if it is not
            if(right-left == p.length() && table[s.charAt(left++) - 'a']++ >= 0) {
                count++;
            }
        }
        return res;
    }
	
}
