/*
 * Contains Duplicate (Easy)
 * 
 * Given an array of integers, find if the array contains any duplicates. Your function should return true if any value appears at least 
 * twice in the array, and it should return false if every element is distinct.
 */
/*
 * 思路：
 * 1.Brute Force, O(n^2)
 * 2.排序再找是否有连续两个元素一致O(nlogn)，实际运行中方法2竟然是最快的，可以说是set的操作导致效率变低。从此有经验了，如果能排序加O(n)的遍历数组比O(n)使用Hash表要快(除非hash表也是自己的数组)
 * 3.使用HashSet即可，遇到重复的即退出，O(n)。注意，HashSet的add方法附带了contains方法的功能，若是已有该元素，会返回false，这样可以减少hashset操作
 */
import java.util.*;
public class question217 {
	public boolean containsDuplicate1(int[] nums) {
        if(nums.length == 0) {
            return false;
        }
        
        HashSet<Integer> set = new HashSet<>();
        for(int i=0; i<nums.length; i++) {
            if(!set.add(nums[i])) {
                return true;
            }

        }
        return false;
    }

}
