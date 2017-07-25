/*
 * Convert a Number to Hexadecimal (Easy)
 * 
 * Given an integer, write an algorithm to convert it to hexadecimal. For negative integer, two’s complement method is used.
 * Note:
   All letters in hexadecimal (a-f) must be in lowercase.
   The hexadecimal string must not contain extra leading 0s. If the number is zero, it is represented by a single zero character '0'; 
   otherwise, the first character in the hexadecimal string will not be the zero character.
   The given number is guaranteed to fit within the range of a 32-bit signed integer.
   You must not use any method provided by the library which converts/formats the number to hex directly.
 *  
 * e.g.,
 * Input:26 Output:"1a"
 * Input:-1 Output:"ffffffff"
 */
/*
 * 思路：
 * 1.将十进制首先转换成二进制，然后再用HashMap做每4位一处理的运算，对于二进制长度不是4的倍数的数，用0补齐，时间复杂度O(logn)
 * 2.Bit Manipulation，由于0-15和0-9-f是一一对应的关系，因此哈希表可以用一个大小为16的char数组表示。每四位的值可以用num&15得到！！！(我的脑袋秀逗了，这都没有想到)
 * 如果想用求余16得到每四位的值，需要先将原数扩展成long，这样无论正负都被转化为一个正数。即long num_new = num & 0x00000000ffffffff;
 * public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.insert(0, map[num & 0b1111]);
            num = num >>> 4;			//无符号右移
        }
        return sb.toString();
    }
 */
import java.util.*;
public class question405 {
	public String toHex1(int num) {
		HashMap<String, String> map = new HashMap<>();
	    map.put("0", "0");
	    map.put("1", "1");
	    map.put("10", "2");
	    map.put("11", "3");
	    map.put("100", "4");
	    map.put("101", "5");
	    map.put("110", "6");
	    map.put("111", "7");
	    map.put("1000", "8");
	    map.put("1001", "9");
	    map.put("1010", "a");
	    map.put("1011", "b");
	    map.put("1100", "c");
	    map.put("1101", "d");
	    map.put("1110", "e");
	    map.put("1111", "f");
	    
	    String binary = Integer.toBinaryString(num);
        StringBuilder result = new StringBuilder();
        if(binary.length()%4 == 3) {
            binary = "0" + binary;
        }
        else if(binary.length()%4 == 2) {
            binary = "00" + binary;
        }
        else if(binary.length()%4 == 1) {
            binary = "000" + binary;
        }
        for(int i=binary.length(); i-4>=0; i -= 4) {
            String temp = map.get(binary.substring(i-4, i));
            result.append(temp);
            
        }
        return result.reverse().toString();
	}
	 public String toHex(int num) {
        if (num == 0) return "0";
        char[] map = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            sb.append(map[num & 0b1111]);
            num = num >>> 4;			//无符号右移
        }
        return sb.reverse().toString();
    }
}

