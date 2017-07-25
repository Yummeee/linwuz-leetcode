/*
 * Single Number (Easy)
 * Given an array of integers, every element appears twice except for one. Find that single one.
 * 
 * Note:
 * Your algorithm should have a linear runtime complexity. Could you implement it without using extra memory?
 */

/*
 * 两种方法：
 * 1.遍历一遍，构建一个HashMap，value是出现次数，时间复杂度O(n)，空间复杂度O(n)
 * 2.利用a^a=0，0^a=a，a^a^b=b这一性质，将所有数异或一遍，最后的结果就是那个出现一次的数，时间复杂度O(n)，空间复杂度O(1) (Google面试题，这个异或性质超有用)
 */
public class question136 {
	public int singleNumber(int[] nums) {
        int result = 0;
        if(nums.length == 0) {
            return result;
        }
        for(int i=0; i<nums.length; i++) {
            result = result^nums[i];
        }
        return result;
    }
}
