/*
 * Remove Element (Easy)
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length.
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 * The order of elements can be changed. It doesn't matter what you leave beyond the new length.
 */
/*
 * 思路：
 * 遍历一遍数组，遇到一个需要被删除的元素，记下其下标，当再一次遇到目标元素时，将其间的所有元素前移，前移的位数由shift决定，shift一开始为0，随着遇到目标元素
 * 次数增加，shift每次均加1。这种解法属于two pointer，采用了一个快指针和一个慢指针。时间复杂度O(2n)
 * 
 * 编辑解法：更加简化了此我的方法。
 * 1.Approach #1 (Two Pointers) [Accepted] 时间复杂度O(n)
 * 事实上，没有必要遇到目标元素时再移动，而是在不是目标元素时移动。指针i指向下一个需要被覆盖的位置，指针j指向当前访问到的位置。
 * public int removeElement(int[] nums, int val) {
    int i = 0;
    for (int j = 0; j < nums.length; j++) {
        if (nums[j] != val) {
            nums[i] = nums[j];
            i++;
        }
    }
    return i;
}
 * 这个方法非常像26题：Remove Duplicates from Sorted Array.
 * 
 * 2.Approach #2 (Two Pointers - when elements to remove are rare) [Accepted] 时间复杂度O(n)
 * 对于nums=[4,1,2,3,5]，val=4，实际上我们没有必要将1、2、3、5均前提一位，因为题目中说可以改变循序，我们只需把5和覆盖掉4就可以了
 * Algorithm:
 * When we encounter nums[i] = valnums[i]=val, we can swap the current element out with the last element and dispose the last
 * one. This essentially reduces the array's size by 1.直至i指的元素不为目标元素时，我们才往后移一个。
 * public int removeElement(int[] nums, int val) {
    int i = 0;
    int n = nums.length;
    while (i < n) {
        if (nums[i] == val) {
            nums[i] = nums[n - 1];
            // reduce array size by one
            n--;
        } else {
            i++;
        }
    }
    return n;
}
 */
public class question27 {
	public int removeElement1(int[] nums, int val) {
        if(nums.length == 0) {
            return 0;
        }
        
        int length = nums.length;
        int last_pos = -1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] == val) {
                if(last_pos >= 0) {
                    int shift = nums.length - length - 1;
                    for(int j=last_pos; j<i-1; j++) {
                        nums[j-shift] = nums[j+1];
                    }
                }
                last_pos = i;
                length--;
            }
            else if(i == nums.length-1) {
                if(last_pos >= 0) {
                    int shift = nums.length - length - 1;
                    for(int j=last_pos; j<i; j++) {
                        nums[j-shift] = nums[j+1];
                    }
                }
            }
        }
        return length;
    }
	
	public int removeElement(int[] nums, int val) {			//精简removElement1的代码
        if(nums.length == 0) {
            return 0;
        }
        
        int length = nums.length;
        int last_pos = -1;
        for(int i=0; i<nums.length; i++) {
            if(nums[i]==val || i==nums.length-1) {
                if(last_pos >= 0) {
                    int shift = nums.length - length - 1;
                    for(int j=last_pos; j<i; j++) {
                        nums[j-shift] = nums[j+1];
                    }
                }
                if(nums[i] == val) {
                	last_pos = i;
                	length--;
                }
            }
        }
        return length;
    }
}
