/*
 * Repeated Substring Pattern (Easy)
 * 
 * Given a non-empty string check if it can be constructed by taking a substring of it and appending multiple copies of the substring 
 * together. You may assume the given string consists of lowercase English letters only and its length will not exceed 10000.
 */
/*
 * 思路：
 * 1.找到一个和第一个字符相等的字符后，利用该子串向后查找，只要一个子串不匹配就继续向后找下一个和第一个字符相等的字符。空间复杂度O(1)，时间复杂度最好O(n)，最差O(n^2)，即aaaaaaaaaaaaaaaaaab这种形式
 * 多种实现方式：
 * 1) 利用substring（最快）； 2)利用StringBuilder的append构建一个新串；3)利用正则表达式（最慢）
 * 2.改进方法1，实际上从中间开始向左找比方法1效率稍微高一些。例如abcabcabcabc，如果从中间找则可直接找到最大的满足目标的子串，而不是只找到abc。而这种类型的串有一半，即重复次数为偶数
 * 
 * 3.使用KMP算法（时间复杂度最优是O(n)，但最差比O(n^2)好，近似也为O(n)），但是只使用构造next数组那一部分(且不是改进的KMP算法)，将目标串当做特征串，求目标串对应的next数组。若目标串的确由一个子串重复构建出现，则从最短的满足题意的子串sub开始，next值是从1,2,3,...,s.length()-sub.length()
 * 而s.length()-sub.length() % sub.length() == 0
 */
public class question459 {
	public boolean repeatedSubstringPattern1(String s) {
        boolean ans = false;
        if(s.length() < 2) {
            return ans;
        }
        
        int i = 1;
        while(i <= (s.length()>>1)) {			//由于超过一半则一定不可能成立，只需判断到一半即可
            if(s.charAt(0) == s.charAt(i)) {
                int length = i;
                if(s.length()%length == 0) {
                    String sub = s.substring(0, i);
                    int j = i;
                    for(; j+length<=s.length(); j += length) {
                        if(!sub.equals(s.substring(j, j+length))) {
                            break;
                        }
                    }
                    if(j == s.length()) {
                        ans = true;
                        break;
                    }   
                }
            }
            i++;
        }
        return ans;
    }
	public boolean repeatedSubstringPattern2(String s) {
        boolean ans = false;
        if(s.length() < 2) {
            return ans;
        }
        
        int i = 1;
        while(i <= (s.length()>>1)) {			//由于超过一半则一定不可能成立，只需判断到一半即可
            if(s.charAt(0) == s.charAt(i)) {
                int length = i;
                if(s.length()%length == 0) {
                    String sub = s.substring(0, i);
                    StringBuilder str = new StringBuilder();
                    for(int j=0; j<s.length()/length; j++) {			//另一种判断实现方式，直接用当前得到的sub构造一个str，看是否与s相等，但这样其实效率更低，因为额外复制了很多次
                        str.append(sub);								//还有一种判断方式：利用正则式判断
                    }
                    if(str.toString().equals(s)) {
                        ans = true;
                        break;
                    }   
                }
            }
            i++;
        }
        return ans;
    }
	public boolean repeatedSubstringPattern3(String s) {
        boolean ans = false;
        if(s.length() < 2) {
            return ans;
        }
        
        int i = s.length()>>1;
        while(i >= 1) {			
        	if(s.charAt(0) == s.charAt(i)) {
                int length = i;
                if(s.length()%length == 0) {
                    String sub = s.substring(0, i);
                    int j = i;
                    for(; j+length<=s.length(); j += length) {
                        if(!sub.equals(s.substring(j, j+length))) {
                            break;
                        }
                    }
                    if(j == s.length()) {
                        ans = true;
                        break;
                    }   
                }
            }
            i--;
        }
        return ans;
    }
	public boolean repeatedSubstringPattern(String s) {			//KMP算法
        boolean ans = false;
        if(s.length() < 2) {
            return ans;
        }
        
        int[] next = new int[s.length()+1];		
        next[0] = -1;
        int k = -1;
        int i = 0;
        while(i < s.length()) {
        	if(k==-1 || s.charAt(i)==s.charAt(k)) {
        		next[++i] = ++k;
        	}
        	else {
        		k = next[k];
        	}
        }
        if(next[s.length()]!=0 && next[s.length()]%(s.length()-next[s.length()])==0) {
        	ans = true;
        }
        return ans;
    }
}
