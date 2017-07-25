/*
 * Valid Palindrome (Easy)
 * 
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 * For example,
   "A man, a plan, a canal: Panama" is a palindrome.
   "race a car" is not a palindrome.
 * 
 * Note:
   Have you consider that the string might be empty? This is a good question to ask during an interview.
   For the purpose of this problem, we define empty string as valid palindrome.
 */
/*
 * 思路：注意这道题的例子，大写和小写字母是一致的，妈的坑
 * 1.两个指针从左至右和从右至左地遍历，遇到非字母和数字跳过，若两个指针同时指向的字符不等(大小写算等，首先将string小写了)，则返回false。时间复杂度O(n)
 * 思路1可能的改进：
 * 1)由于substring是重新new一个string，代价太高，还是要用char。
 * 2)mathes正则表达式的代价较高，可以自己写一个函数，或者用Character自带的isLetterOrDigit()
 * 3)不将整个string小写，只在遍历string时将字母或数字小写
 * 
 * 2.使用java.String的replaceAll方法将所有非字母数字的字符设置为空，然后直接reverse字符串比较是否相等，时间复杂度O(n)
 * public boolean isPalindrome(String s) {
        String actual = s.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
        String rev = new StringBuffer(actual).reverse().toString();
        return actual.equals(rev);
    }
 */
public class question125 {
	public boolean isPalindrome(String s) {
        if(s == null) {
            return false;
        }
        
        int i = 0, j = s.length() - 1;
        while(i < j) {
            //while(i < s.length() && !isCharacter(s.charAt(i))) {
        	while(i < s.length() && !Character.isLetterOrDigit(s.charAt(i))) {
                i++;
            }
            //while(j >= 0 && !isCharacter(s.charAt(j))) {
        	while(j >= 0 && !Character.isLetterOrDigit(s.charAt(j))) {
                j--;
            }
            if(i < s.length() && j > i && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {		//here may bigger the boundery, be careful!!!
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
	
	public boolean isCharacter(char c) {
        if(c >= '0' && c <= '9') {
            return true;
        }
        else if(c >= 'a' && c <= 'z') {
            return true;
        }
        else {
            return false;
        }
    }
	
	public boolean isPalindrome1(String s) {
        if(s == null) {
            return false;
        }
        //if(s.length() == 0) {         //empty string
           // return true;
        //}
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while(i < j) {
            while(i < s.length() && !s.substring(i, i+1).matches("[0-9a-zA-Z]")) {  //here may bigger the boundery, be careful!!!
                i++;
            }
            while(j >= 0 && !s.substring(j, j+1).matches("[0-9a-zA-Z]")) {    //here may bigger the boundery, be careful!!!
                j--;
            }
            if(i < s.length() && j > i && s.charAt(i) != s.charAt(j)) {		//here may bigger the boundery, be careful!!!
                return false;
            }
            i++;
            j--;
        }
        return true;
    }
	
	public static void main(String[] args) {
		System.out.println(new question125().isPalindrome(".,"));
	}
}
