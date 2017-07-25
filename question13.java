/*
 * Roman to Integer (Easy)
 * 
 * Given a roman numeral, convert it to an integer.
 * Input is guaranteed to be within the range from 1 to 3999.
 */
/*
 * 思路：
 * 掌握罗马数字构造规则，从左往右读，左边比右边高则加此数，左边比右边低则减此数
 * 具体方法：
 * 1.使用hashmap保存一个字符数值映射表，遍历字符串时读取相应的数值。但是速度不稳定，有时会超时，有时则超过90%
 * 2.使用一个和字符串一样长的数组保存每一位的数值，然后遍历该数组，运用规则计算结果
 *  public int romanToInt(String s) {
    int nums[]=new int[s.length()];
    for(int i=0;i<s.length();i++){
        switch (s.charAt(i)){
            case 'M':
                nums[i]=1000;
                break;
            case 'D':
                nums[i]=500;
                break;
            case 'C':
                nums[i]=100;
                break;
            case 'L':
                nums[i]=50;
                break;
            case 'X' :
                nums[i]=10;
                break;
            case 'V':
                nums[i]=5;
                break;
            case 'I':
                nums[i]=1;
                break;
        }
    }
    int sum=0;
    for(int i=0;i<nums.length-1;i++){
        if(nums[i]<nums[i+1])
            sum-=nums[i];
        else
            sum+=nums[i];
    }
    return sum+nums[nums.length-1];
}

 */
import java.util.*;
public class question13 {
	 //I(1)，V(5)，X(10)，L(50)，C(100)，D(500)，M(1000)
	public int romanToInt1(String s) {		//基础版，每个字符要访问两遍
        int ans = 0;
        HashMap<Character, Integer> value_map = new HashMap<>();
        value_map.put('I', 1);
        value_map.put('V', 5);
        value_map.put('X', 10);
        value_map.put('L', 50);
        value_map.put('C', 100);
        value_map.put('D', 500);
        value_map.put('M', 1000);
        
        int i = 0; 
        while(i<s.length()-1) {
            int value = value_map.get(s.charAt(i));
            int value_right = value_map.get(s.charAt(i+1));
            if(value >= value_right) {     //left >= right, add
                ans += value;
            }
            else {
                ans -= value;
            }
            i++;
        }
        ans += value_map.get(s.charAt(s.length()-1));
        return ans;
    }
	
	public int romanToInt(String s) {			//改进，对于每个字符只访问一次hashmap
        int ans = 0;
        HashMap<Character, Integer> value_map = new HashMap<>();
        value_map.put('I', 1);
        value_map.put('V', 5);
        value_map.put('X', 10);
        value_map.put('L', 50);
        value_map.put('C', 100);
        value_map.put('D', 500);
        value_map.put('M', 1000);
        
        int i = 0; 
        int value = value_map.get(s.charAt(i));          //left
        int next = 0;                                   //right
        while(i<s.length()-1) {
            next = value_map.get(s.charAt(i+1));        //we get next value every time instead of current value
            if(value >= next) {     //left >= right, add
                ans += value;
            }
            else {      //right > left
                ans -= value;
            }
            value = next;
            i++;
        }
        ans += value_map.get(s.charAt(i));
        return ans;
    }
}
