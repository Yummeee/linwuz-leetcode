/*
 * Single Number III (Medium)
 * 
 * Given an array of numbers nums, in which exactly two elements appear only once and all the other elements 
 * appear exactly twice. Find the two elements that appear only once.
 * For example:
 * Given nums = [1, 2, 1, 3, 2, 5], return [3, 5]
 * 
 * Note:
   The order of the result is not important. So in the above example, [5, 3] is also correct.
   Your algorithm should run in linear runtime complexity. Could you implement it using only constant space 
   complexity?
 */
/*
 * 思路：
 * 1.使用hashmap，时间复杂度O(n)，空间复杂度O(n)
 * 2.位运算，非常巧妙。
 * The two numbers that appear only once must differ at some bit, this is how we can distinguish between them. Otherwise, they will be one of the duplicate numbers.
   One important point is that by XORing all the numbers, we actually get the XOR of the two target numbers (because XORing two duplicate numbers always results in 0). Consider the XOR result of the two target numbers; if some bit of the XOR result is 1, it means that the two target numbers differ at that location.
   Let’s say the at the ith bit, the two desired numbers differ from each other. which means one number has bit i equaling: 0, the other number has bit i equaling 1.
   Thus, all the numbers can be partitioned into two groups according to their bits at location i.
   the first group consists of all numbers whose bits at i is 0.
   the second group consists of all numbers whose bits at i is 1.
   Notice that, if a duplicate number has bit i as 0, then, two copies of it will belong to the first group. Similarly, if a duplicate number has bit i as 1, then, two copies of it will belong to the second group.
   by XoRing all numbers in the first group, we can get the first number.
   by XoRing all numbers in the second group, we can get the second number.
 */
import java.util.*;
public class question260 {
	public int[] singleNumber(int[] nums) {                     //思路2，位运算
        int[] res = new int[2];
        if(nums == null || nums.length == 0) {
            return res;
        }
        
        int xor = 0, num1 = 0, num2 = 0, bit = 0;
        for(int i = 0; i < nums.length; i++) {
            xor ^= nums[i]; 
        }
        //bit = xor & (~xor + 1);				// xor & -xor's two's complement representation
        bit = xor & (~(xor - 1));               //quickly locate the first bit of 1 in xor.
        for(int i = 0; i < nums.length; i++) {
            if((nums[i] & bit) > 0) {
                num1 ^= nums[i];
            }
            else {
                num2 ^= nums[i];
            }
        }
        res[0] = num1;
        res[1] = num2;
        return res;
    }
	
	public int[] singleNumber1(int[] nums) {			//思路1，HashMap
        int[] res = new int[2];
        if(nums == null || nums.length == 0) {
            return res;
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        int count = 0;
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if(entry.getValue() == 1) {
                res[count] = entry.getKey();
                count++;
            }
        }
        return res;
    }
}
