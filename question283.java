/*
 * Move Zeroes (Easy)
 * 
 * Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the 
 * non-zero elements.
 * 
 * For example, given nums = [0, 1, 0, 3, 12], after calling your function, nums should be [1, 3, 12, 0, 0].
 * Note:
   You must do this in-place without making a copy of the array.
   Minimize the total number of operations.
 */

/*
 * 思路：
 * 1.Brute Force且不使用任何辅助空间，双重循环，遇到一个0即从其后面找第一个非0数与之交换，时间复杂度最差为O(n^2);
 * 2.遍历一遍目标数组，使用辅助空间保存所有遇到的0的位置，遇到一个非0数则与第一个0进行交换，时间复杂度为O(n)，空间复杂度最差为O(n)。此方法要注意交换后要将第一个0的新位置存入辅助
 * 空间！否则将可能出现后面的非0数无法移到前面的0之前的情况
 * 3.使用两个指针仅遍历一遍数组且不使用辅助空间，时间复杂度O(n)，空间复杂度O(1)。由于0的顺序可以丢弃，我们只需要保留非0数的顺序，那么我们可以将所有非0数都
 * 移到最前面(用一个插入位置指针来保存非0数当前应该插入到哪里，没插一个非0数该指针后移一位)，最后将所有的位置全部置0。有点像插入排序的思想，不过插入的位置是固定的。
 *  public void moveZeroes(int[] nums) {
    if (nums == null || nums.length == 0) return;        

    int insertPos = 0;
    for (int num: nums) {
        if (num != 0) nums[insertPos++] = num;
    }        

    while (insertPos < nums.length) {
        nums[insertPos++] = 0;
    }
}
 * 4.最后一种空间和操作数量都最优的方法（一开始想到了不知道为啥又否决了...），时间复杂度O(n)，空间复杂度O(1)。不需要像思路3一样写最后的0。同思路3仍是两个
 * 指针，只不过是在当前指针非0时交换插入位置指针和当前扫描指针指向元素的值，并同时后移两个指针，因为两个指针如果有间隔，中间一定间隔的是0!
 * public void moveZeroes(int[] nums) {

    int j = 0;
    for(int i = 0; i < nums.length; i++) {
        if(nums[i] != 0) {
            int temp = nums[j];
            nums[j] = nums[i];
            nums[i] = temp;
            j++;
        }
    }
} 
 */
import java.util.*;
public class question283 {
	public void moveZeroes1(int[] nums) {
        if(nums.length==0 || nums==null) {
            return;
        }
        
        int p = 0;        //p is the pointer to the current zero element
        Queue<Integer> zeros = new LinkedList<>();
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==0) {
                zeros.offer(i);       
                continue;
            }
            
            if(!zeros.isEmpty()) {      //swap the first 0 that needs to be rearranged and a non-zero element
                p = zeros.poll();
                nums[p] = nums[i];
                nums[i] = 0;
                zeros.offer(i);         //must put the 0 to the tail of the queue
            }
        }
    }
	public void moveZeroes(int[] nums) {
	    if (nums == null || nums.length == 0) return;        

	    int insertPos = 0;
	    for (int num: nums) {
	        if (num != 0) nums[insertPos++] = num;
	    }        

	    while (insertPos < nums.length) {
	        nums[insertPos++] = 0;
	    }
	}

}
