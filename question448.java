/*
 * Find All Numbers Disappeared in an Array (Easy)
 * 
 * Given an array of integers where 1 ≤ a[i] ≤ n (n = size of array), some elements appear twice and others appear once.
 * Find all the elements of [1, n] inclusive that do not appear in this array.
 * Could you do it without extra space and in O(n) runtime? You may assume the returned list does not count as extra space.
 * 
 * e.g.,
 * Input: [4,3,2,7,8,2,3,1]
 * Output: [5,6]
 */

/*
 * 思路：不能老是被之前的题的思路所束缚，只往一个方向想了！这题就被那个一堆数只有一个出现奇数次的给带歪了进入了死胡同
 * 1.Brute Force，时间复杂度O(n)，空间复杂度O(n)。就是再维持一个数组A并初始化为0或者其它的一个数，遍历目标数组时将A[nums[i]-1]++，最后再遍历一遍A即可
 * 此方法很好想，想到但因不符合题意没有写，但是竟然也被accept了！...速度比方法2还快...
 * 2.由于数组元素是1...n，正好和数组下标0...n-1对应，这样当nums[abs(nums[i])-1]>0时，将nums[abs(nums[i])-1]置为复数，遍历完成后查询哪个位置的元素为正，pos+1即为缺失元素
 */
import java.util.*;
public class question448 {
	public List<Integer> findDisappearedNumbers(int[] nums) {
        ArrayList<Integer> miss = new ArrayList<>();
        if(nums==null || nums.length==0) {
            return miss;
        }
        
        for(int i=0; i<nums.length; i++) {
            int pos = Math.abs(nums[i]);             //since nums[i] maybe negative in loops before
            if(nums[pos-1] > 0) {
                 nums[pos-1] = -nums[pos-1];        //in case that some double appears number will flip to positive again
            }
        }
        
        for(int i=0; i<nums.length; i++) {
            if(nums[i] > 0) {
                miss.add(i+1);
            }
        }
        return miss;
    }
}
