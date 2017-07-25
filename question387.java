/*
 * First Unique Character in a String (Easy)
 * 
 * Given a string, find the first non-repeating character in it and return it's index. If it doesn't exist, return -1.
 * e.g.,
 * s = "leetcode"    return 0.
 * s = "loveleetcode"    return 2.
 * 
 * Note: You may assume the string contain only lowercase letters.
 */
/*
 * 思路：注意是要找第一个独一无二的单词
 * 1.Brute Force，时间复杂度O(n^2)
 * 2.使用HashMap或整型数组(26个元素，因为输入只有a-z)，后者效率高一些，时间复杂度O(n+26)。改进：改进，times里存第一次出现的index，若出现不止一次，则置-1。最后找不为-1的最小index，省空间
 * 3.仍使用HashMap或整型数组，不用2的改进方法，且不用indice数组保存第一次出现的下标，时间复杂度O(2n)
 * int firstUniqChar(string s) {
          int table[26] = {0};
          for (char ch : s)
              table[ch - 'a']++;
          
          for (int i = 0; i < s.size(); i++)
              if (table[s[i] - 'a'] == 1) return i;
             
         return -1;
     }
 * 4.使用一个fast和一个slow指针，slow始终指向当前的独一无二的字符（贪心思想！！！这个方法超巧妙），fast每个循环+1。每个循环首先令times[fast指向字符-'a']++，若此操作使times[slow指向字符-'a']
 * 大于1，说明当前slow指向的字符不再是独一无二的，slow一直加一直至times[slow指向字符-'a']<=1。若slow>fast，则说明fast扫过的区域每个都有重复的，此时slow=fast+1，重置fast=slow即可。复杂度O(2n)
 */
public class question387 {
	public int firstUniqChar1(String s) {			//改进，times里存第一次出现的index，若出现不止一次，则置-1。最后找不为-1的最小index，省空间
        if(s.length()==0 || s==null) {
            return -1;
        }
        
        int[] times = new int[26];
        int[] indice = new int[26];   //record the first index of letter 'a'+i
        for(int i=0; i<s.length(); i++) {
            int index = s.charAt(i) - 'a';
            if(times[index] == 0) {
                indice[index] = i;
            }
            times[index]++;
        }
        int min_index = s.length();
        for(int i=0; i<times.length; i++) {
            if(times[i] == 1) {             //unique letter
                if(min_index > indice[i]) {
                    min_index = indice[i];
                }
            }
        }
        return min_index==s.length()? -1:min_index;
    }
	
	public int firstUniqChar(String s) {
        if(s.length()==0 || s==null) {
            return -1;
        }
        
        char[] str = s.toCharArray();
        int slow = 0;          //slow pointer
        int fast = 1;          //fast pointer
        int[] times = new int[26];
        times[str[slow] - 'a']++;
        while(fast<str.length) {
            times[str[fast] - 'a']++;
            while(slow<str.length && times[str[slow] - 'a']>1) {          //find next known unique letter
                slow++;
            }
            if(slow == str.length) {
                return -1;
            }
            if(slow > fast) {       //slow can only be fast+1
                fast = slow;
                times[str[slow] - 'a']++;
            }
            fast++;
        }
        return slow;
    }
}
