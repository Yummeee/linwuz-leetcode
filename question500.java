/*
 * Keyboard Row (Easy)
 * 
 * Given a List of words, return the words that can be typed using letters of alphabet on only one row's of American keyboard
 * like the image below.
 * 
 * Note:
   You may use one character in the keyboard more than once.
   You may assume the input string will only contain letters of alphabet.
 */


/*
 * 思路：
 * 1.暴力法：一个二维数组，用数组寸每一行的所有字母，但显然此方法每查一个字符最坏要遍历一遍字母表
 * 2.用hash表，字母做key，行号做value
   方法2有一点需要注意，效率和空间的选择权衡，这道题都可以，但是也可以做参考
   如果hash表里只存小写字母，则要用toLowerCase应付大写字母时会遍历一遍每个单词，相当于所有字符最后遍历了2遍
   如果大小写均存，则只遍历一遍
 */

/*
 * 构建hash表的简洁方法：时间复杂度一样，但是代码简洁...
 * String [] keyboard = {"qwertyuiop", "asdfghjkl", "zxcvbnm"};
   		HashMap<Character, Integer> hm = new HashMap<Character, Integer>();
        for(int i = 0; i<keyboard.length; i++){
        	for(char c : keyboard[i].toCharArray()){
            	hm.put(c, i);
           	}
		}
 */

/*
 * 自己犯得几个比较严重的错误：
 * 1.没有确定输入的可能情况，简单就根据题目的例子就假设输入的单词一定是首字母大写，其余小写，实际上并不是这样。在面试中一定要先问清楚这些问题再做
 * 2.没有对极端情况进行处理，
 	 1）比如输入的words是空的（数组为空为[]，用{}初始化），即words初始化时用{}初始化，这时候words的长度为0，且并不等于null，只有直接令word等于null其才可能等于null
 	 2）没有找到一个符合标准的单词时，数组为空[]不等于[""]，后者等价于串数组有一个空串作为元素
 	 因此前后需要对这两种情况进行判断单独处理
 */
import java.util.*;
public class question500 {
	public HashMap createMap() {
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('a', 2);      //we could use toLowerCase to avoid store a & A, but will decrease efficiency, since toLowerCase will traverse each word
        map.put('b', 1);
        map.put('c', 1); 
        map.put('d', 2); 
        map.put('e', 3); 
        map.put('f', 2);
        map.put('g', 2); 
        map.put('h', 2); 
        map.put('i', 3); 
        map.put('j', 2); 
        map.put('k', 2); 
        map.put('l', 2); 
        map.put('m', 1); 
        map.put('n', 1); 
        map.put('o', 3); 
        map.put('p', 3); 
        map.put('q', 3); 
        map.put('r', 3); 
        map.put('s', 2); 
        map.put('t', 3); 
        map.put('u', 3); 
        map.put('v', 1); 
        map.put('w', 3); 
        map.put('x', 1); 
        map.put('y', 3); 
        map.put('z', 1);
        return map;
    }
    
	public String[] findWords(String[] words) {
		if(words.length == 0) {
			String[] filtered_words = {};
            return filtered_words;
        }
		
        HashMap<Character, Integer> map = createMap();
        String filtered_words_list = "";
        
        for(int i=0; i<words.length; i++) {
            String temp = words[i].toLowerCase();
            int row = map.get(temp.charAt(0));      //the row number this word's each character should be in
            int j = 1;
            for(; j<temp.length(); j++) {
                if(row != map.get(temp.charAt(j))) {
                    break;
                }
            }
            if(j == temp.length()) {                  //this word fit!
                filtered_words_list += words[i] + " "; 
            }
        }
        
        if(filtered_words_list.equals("")) {
			String[] filtered_words = {};
            return filtered_words;
        }
        String[] filtered_words = filtered_words_list.trim().split(" ");	//ArrayList有toArray方法也可以用，使filtered_words_list可以为List类型
        return filtered_words; 
    }
    
	
	public static void main(String[] args) {
		String[] a = {};
		new question500().findWords(a);
		
		
	}

}
