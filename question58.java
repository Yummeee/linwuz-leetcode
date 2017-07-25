/*
 * Length of Last Word (Easy)
 * 
 * Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in 
 * the string.
 * If the last word does not exist, return 0.
 * 
 * Note: A word is defined as a character sequence consists of non-space characters only.
 * For example, 
   Given s = "Hello World",
   return 5.
 */
/*
 * 思路：特殊情况即全为空格和结尾连续空格这两种
 * 1.利用split函数，由于不知道空格的数量和位置，要考虑末尾是连续空格的情况。根据split返回的string数组，若其不为空，则更新length值，最后的length值就是
 * 结果。时间复杂度O(n)，空间复杂度O(n);
 * 利用trim，思路1可以被改进，当时也是没想到trim才出此下策：
 * String[] arr = s.trim().split(" ");
   int length = arr[arr.length - 1].length();
   
 * 2.用一个指针遍历一遍s，当指针指向的元素不为空格时，res++，否则若res大于0，我们知道找到了一个单词，退出即可。一开始我是正向遍历的，还需要一个
 * cur_length变量，效率较低，后来发现由于是要找最后一个单词的长度，直接倒着遍历就行。时间复杂度O(n)，空间复杂度O(1)。
 
 * 3.妈的，忘记了还可以用trim()...时间复杂度O(n)，空间复杂度O(n)。
 * public int lengthOfLastWord(String s) {
    return s.trim().length()-s.trim().lastIndexOf(" ")-1;
}
 */
public class question58 {
	 public int lengthOfLastWord(String s) {			//思路2，反向遍历
        if(s == null || s.length() == 0) {
            return 0;
        }
	        
        int res = 0, i = s.length() - 1;
        while(i >= 0) {
            if(s.charAt(i--) != ' ') {
                res++;
            }
            else {
                if(res > 0) {		//说明找到一个单词
                    break;
                }
            }
        }
        return res;			
    }
	/*
	 public int lengthOfLastWord(String s) {			//思路2如果正向遍历的话
        if(s == null || s.length() == 0) {
            return 0;
        }
	        
        int cur_length = 0, i = 0, res= 0;
        while(i < s.length()) {
            if(s.charAt(i++) != ' ') {
                cur_length++;
            }
            else {
                if(cur_length > 0) {
                    res = cur_length;
                    cur_length = 0;
                }
            }
        }
        return cur_length == 0 ? res : cur_length;		//需要兼顾最后一个字符是单词时的情况
    }
	 */
	 
	public int lengthOfLastWord1(String s) {			//思路1
        if(s == null || s.length() == 0) {
            return 0;
        }
        
        String[] words = s.split(" ");
        int length = 0;
        for(String word : words) {
            if(!word.equals("")) {
                length = word.length();
            }
        }
        return length;
    }
	
	public static void main(String[] args) {
		System.out.println(new question58().lengthOfLastWord(" "));
	}
}
