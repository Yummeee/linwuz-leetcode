/*
 * Search Insert Position (Easy)
 * 
 * Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were 
 * inserted in order.
 * You may assume no duplicates in the array.
 * e.g.,
 * [1,3,5,6], 5 → 2
   [1,3,5,6], 2 → 1
   [1,3,5,6], 7 → 4
   [1,3,5,6], 0 → 0
 */

/*
 * 思路：
 * 不能再典型的二分搜索问题...自己写一个或者用Arrays自带的函数都可以...（还可以用brute force，太蠢了）
 */
import java.util.*;
public class question35 {
	public int searchInsert1(int[] nums, int target) {
        int index = Arrays.binarySearch(nums, target);
        return index>=0? index:Math.abs(index)-1;
    }
	public int searchInsert(int[] nums, int target) {			//自己写一个二分搜索
        int low = 0, high = nums.length-1;
        while(low <= high) {
            int mid = (low+high)>>1;
            if(nums[mid] == target) {
                return mid;
            }
            else if(nums[mid] > target) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
        return low;
    }
}
