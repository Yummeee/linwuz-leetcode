/*
 * Find All Duplicates in an Array (Medium)
 * 
 * Given an array of integers, 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements that appear twice in this array.
 * 
 * Could you do it without extra space and in O(n) runtime?
 * Example:
 * Input:
   [4,3,2,7,8,2,3,1]
   Output:
   [2,3]
 */
/*
 * 思路：
 * 1.Brute Force，时间复杂度O(n^2)，extra space：O(1)
 * 2.使用hashset，时间复杂度O(n)，extra space：O(n)；或者用一个一维数组，统计每个数字的出现次数，出现为2的即为结果
 * 2.遍历一遍数组，对于index = abs(nums[i]) - 1，若nums[index] < 0, 则说明前面已经出现一次nums[i]，将abs(nums[i])加入结果
 * 否则将nums[index]置为相反数。这参考了Find Disappeared Numbers in an Array的思想，同时也巧妙避免了重复加duplicate元素，
 * 时间复杂度O(n)，extra space：O(1)
 */
import java.util.*;
public class question442 {
	public List<Integer> findDuplicates(int[] nums) {			//思路3
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        
        for(int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            int index = num - 1;
            if(nums[index] < 0) {
            	res.add(num);
            }
            else {
            	nums[index] = -nums[index];
            }
        }
        return res;  
    }
	
	public List<Integer> findDuplicates1(int[] nums) {			//思路2
        List<Integer> res = new ArrayList<>();
        if(nums == null || nums.length == 0) {
            return res;
        }
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                res.add(nums[i]);
            }
        }
        return res;
    }
}
