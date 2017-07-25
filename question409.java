/*
 * Longest Palindrome (Easy)
 * 
 * Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those 
 * letters.
 * This is case sensitive, for example "Aa" is not considered a palindrome here.
 * 
 * Note:
   Assume the length of given string will not exceed 1,010.
 * Input: "abccccdd"
   Output: 7
   Explanation:
   One longest palindrome that can be built is "dccaccd", whose length is 7.
 */
/*
 * 思路：
 * 1.简单的hash表，统计次数出现次数大于等于2的字符即可。注意，陷阱1：'a'和'Z'不是紧挨着的，中间差6个字符；陷阱2，次数不小于2的不能只+2，要加出现的次数（要考虑奇偶性）
 * 2.使用HashSet，但是效率不及hash表
 * public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
}
 * 
 */
public class question409 {
	public int longestPalindrome1(String s) {
        if(s.length() == 0) {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        
        int[] times = new int[52];
        for(int i=0; i<s.length(); i++) {
            Character cur = s.charAt(i);
            if(cur <= 'Z')
            {
                times[cur - 'A']++;
            }
            else {
                times[cur - 'A' - 6]++;
            }
        }
        
        int count = 0;
        for(int i=0; i<52; i++) {
            if(times[i] >= 2) {
                if(times[i]%2 == 0) {
                    count += times[i];
                }
                else {
                    count += times[i]-1;
                }
            }
        }
        if(count < s.length()) {
            count++;
        }
        return count;
    }
	public int longestPalindrome(String s) {		//改进，减少不必要操作和判断
        if(s.length() == 0) {
            return 0;
        }
        if(s.length() == 1) {
            return 1;
        }
        
        int[] times = new int[58];
        for(int i=0; i<s.length(); i++) {
            times[s.charAt(i) - 'A']++;
        }
        
        int count = 0;
        for(int i=0; i<58; i++) {
            if(times[i] >= 2) {
                count += (times[i]/2) * 2;
                //count += (times[i]>>1)<<1;
            }
        }
        if(count < s.length()) {
            count++;
        }
        return count;
    }
}
