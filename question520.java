/*
 * Detect Capital (Easy)
 * 
 * Given a word, you need to judge whether the usage of capitals in it is right or not.
 * We define the usage of capitals in a word to be right when one of the following cases holds:
 * 1.All letters in this word are capitals, like "USA".
 * 2.All letters in this word are not capitals, like "leetcode".
 * 3.Only the first letter in this word is capital if it has more than one letter, like "Google".
 * 
 * Otherwise, we define that this word doesn't use capitals in a right way.
 * Note: The input will be a non-empty word consisting of uppercase and lowercase latin letters(less u,j,w than English letters).
 * 
 * e.g.,Input: "FlaG" Output: False
 */

/*
 * 犯的错误：又没考虑边界！根据题意，这道题的边界是word.length() == 1而不是word为空或为空串！！！！！长点记性吧！！！！！
 */

/*
 * 还有一种更简洁的做法：统计大写字母个数，个数应该为0、1或n，为1时还要是在首部（这种方法就包含了边界情况）
 * public boolean detectCapitalUse(String word) {
        int cnt = 0;
        for(char c: word.toCharArray()) if('Z' - c >= 0) cnt++;
        return ((cnt==0 || cnt==word.length()) || (cnt==1 && 'Z' - word.charAt(0)>=0));
    }
 */
public class question520 {
	public boolean detectCapitalUse(String word) {
        boolean ans = true;
        if(word.length() <= 1) {		//
            return ans;
        }
        
        boolean lower_case = false;     //indicate following letters should be lower case or upper_case
        if(word.charAt(0) >= 'a') {
            lower_case = true;                  //leetcode situation
        }
        else {
            if(word.charAt(1) >= 'a') {         //Google situation
                lower_case = true;
            }                                   //USA situation
        }
        //word[0] will always be read before
        if(lower_case == true) {
            for(int i=1; i<word.length(); i++) {
                if(word.charAt(i) < 'a') {
                    ans = false;
                    break;
                }
            }
        }
        else {
            for(int i=1; i<word.length(); i++) {
                if(word.charAt(i) >= 'a') {
                    ans = false;
                    break;
                }
            }
        }
        
        
        return ans;
    }
}
