/*
 * Contains Duplicate II (Easy)
 * 
 * Given an array of integers and an integer k, find out whether there are two distinct indices i and j in the array such 
 * that nums[i] = nums[j] and the absolute difference between i and j is at most k.
 */
/*
 * 思路：
 * 1.Brute Force，对于第i个元素，遍历其后的所有元素，找到满足条件的则输出，否则后移一个元素。时间复杂度O(n^2)，空间复杂度O(1)
 * 2.Hash表，遍历一遍元素，将（元素值，下标）存入哈希表。时间复杂度O(n)，空间复杂度O(n)
 * 3.使用HashSet，其中的不重复元素数量始终保持在k，因为哪怕相等，如果间隔大于k也是不满足题意的，这样就相当于维护了一个大小为k的滑动窗口，若在窗口中即Set中
 * 的元素有重复的，就一定是满足题意的，不需要再去判断是否是间隔不超过k。时间复杂度O(n)，空间复杂度O(n)，空间上表现更好，但remove语句的使用使得效率不稳定
 */
import java.util.*;
public class question219 {
	public boolean containsNearbyDuplicate(int[] nums, int k) {			//思路3解法，使用hashset维持滑动窗口
        if(nums==null || nums.length==0) {
            return false;
        }
        
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(i > k) {
            	set.remove(nums[i-k-1]);			//维护窗口大小，但次语句效率较低
            }
            if(!set.add(nums[i])) {
            	return true;
            }
        }
        return false;
    }
	
	public boolean containsNearbyDuplicate1(int[] nums, int k) {			//思路2解法，使用hash表
        if(nums==null || nums.length==0) {
            return false;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            Integer n = map.put(nums[i], i);    //n = j;
            if(n != null) {                     //nums[i] == nums[j]
                if(i - n.intValue() <= k) {		//i is larger than n all the time
                    return true;
                }
            }
        }
        return false;
    }
}
