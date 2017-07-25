/*
 * Max Consecutive Ones (Easy)
 * 
 * Given a binary array, find the maximum number of consecutive 1s in this array.
 */


/*
 * 非常简单的一道题，最好的时间复杂度也只能为0(n)
 * 唯一的变种就是在统计当前连续1的长度时，可以用加+乘的方法，也可以用传统的统计比较。前者好处就是不用遇到0后手动置0，代码更简洁一点，每个循环少if判断一次
 */
public class question485 {
	public int findMaxConsecutiveOnes(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        
        int length = 0;
        int max = 0;
        for(int i=0; i<nums.length; i++) {
            length = (length+nums[i])*nums[i];
            if(length > max) {
                max = length;
            }
        }
        return max;
	}
}
