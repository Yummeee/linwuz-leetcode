/*
 * Reverse Vowels of a String (Easy)
 * 
 * Write a function that takes a string as input and reverse only the vowels of a string.
 * 
 * Example 1:
   Given s = "hello", return "holle".
 *
 * Example 2:
   Given s = "leetcode", return "leotcede".
 * The vowels does not include the letter "y".
 */
/*
 * 思路：
 * 两个指针，一个从左往右，一个从右往左扫描。遇到元音字母停下，否则一直前进，知道找到一对元音字母进行交换，然后继续前进，直至两个指针相遇
 */
public class question345 {
	public String reverseVowels1(String s) {
        if(s==null || s.length()==1) {
            return s;
        }
        
        String targets = "aeiouAEIOU";
        char[] str = s.toCharArray();
        int i = 0, j = str.length-1;
        while(i < j) {
            while(i<j && !targets.contains(String.valueOf(str[i]))) {			//首先要判断i、j的范围，不然直接访问数组可能会导致溢出
                i++;
            }
            while(i<j && !targets.contains(String.valueOf(str[j]))) {
                j--;
            }
            if(i < j) {
                char temp = str[i];
                str[i] = str[j];
                str[j] = temp;
            }
            i++;			//不加这个就陷入死循环了
            j--;
        }
        return new String(str);
    }
	public String reverseVowels(String s) {				//我的想法是对的，用if判断是不是元音比用string的contains或match效率高，那个还要构建一个新的string
        if(s==null || s.length()==1) {
            return s;
        }
        
        //String targets = "aeiouAEIOU";
        char[] str = s.toCharArray();
        int i = 0, j = str.length-1;
        while(i < j) {
            while(i<j && !isVowel(str[i])) {
                i++;
            }
            while(i<j && !isVowel(str[j])) {
                j--;
            }
            if(i < j) {
                char temp = str[i];
                str[i] = str[j];
                str[j] = temp;
            }
            i++;
            j--;
        }
        return new String(str);
    }
    
    public boolean isVowel(char s) {
        if(s=='a' || s=='e' || s=='i' || s=='o' || s=='u' || s=='A' || s=='E' || s=='I' || s=='O' || s=='U') {
        	return true;
        }
        return false;
    }
}
