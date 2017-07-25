/*
 * Remove Duplicates from Sorted Array (Easy)
 * 
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * 
 * For example,
 * Given input array nums = [1,1,2],
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively. 
 * It doesn't matter what you leave beyond the new length.
 */
/*
 * 思路：典型的two pointer问题
 * 解法：一个慢指针始终指向下一个需要被覆盖的位置，快指针则遍历一遍数组。
 * 编辑解法和我的一模一样：
 * Approach #1 (Two Pointers) [Accepted]
 * Since the array is already sorted, we can keep two pointers i and j, where i is the slow-runner while j is the fast-runner.
 * As long as nums[i] = nums[j], we increment j to skip the duplicate.
 * When we encounter nums[j]≠nums[i], the duplicate run has ended so we must copy its value to nums[i+1]. i is then incremented
 * and we repeat the same process again until j reaches the end of array.
 * 
 * public int removeDuplicates(int[] nums) {
    if (nums.length == 0) return 0;
    int i = 0;
    for (int j = 1; j < nums.length; j++) {
        if (nums[j] != nums[i]) {			//和我的唯一不同是，慢指针一开始置0，然后由于慢指针及其前面的都是独一无二的元素了，j只需要和i比，不用和j-1比。但是覆盖的时候i就要先+1了，这时慢指针指的是已经调整好的最后一个独一无二的元素
            i++;
            nums[i] = nums[j];
        }
    }
    return i + 1;
}
 */
public class question26 {
	public int removeDuplicates(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        int slow = 1, fast = 1;             //slow points to the position that supposed to be overlapped
        
        while(slow<nums.length && fast<nums.length) {
            if(nums[fast-1] != nums[fast]) {
            	nums[slow] = nums[fast];
            	slow++;
            }
            fast++;
        }
        return slow;
    }
}
