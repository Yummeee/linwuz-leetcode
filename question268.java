/*
 * Missing Number (Easy)
 * 
 * Given an array containing n distinct numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array.
 * For example, Given nums = [0, 1, 3] return 2.
 * 
 * Note:
   Your algorithm should run in linear runtime complexity. Could you implement it using only constant extra space complexity?
 */
/*
 * 思路：
 * 1.Brute Force，时间复杂度O(n^2)，空间复杂度O(1)，不符合要求
 * 2.利用0...n-1和数组下标一一对应的特性，将nums[nums[i]]置负，寻找正数，其下标即为解，类似之前的一道题，那道题是有些数出现两次，找到缺失的数。时间复杂度O(2n)，空间复杂度
 * O(1)，符合要求。注意，和之前不一样的是，由于引入了0，可能0正好在缺失的数的位置上，这样所有数也均为非正，如果n没出现则此情况不成立。所以要找到0的位置和确定n出现没有。
 * 3.计算1...n的和减去数组元素的和即为结果，时间复杂度O(n)，空间复杂度O(1)，符合要求，但可能会溢出，可以将sum设为long或者
 * public int missingNumber(int[] nums) {
        int sum = 0;
        for(int i = 0; i < nums.length; i++){
            sum += i;
            sum -= nums[i];
        }
        sum += nums.length;
        return sum;
    }
 * 4.排序后找第一个下标与元素不一致的数即可，时间复杂度O(nlogn)，空间复杂度O(1)，不符合要求
 * 
 * 5.Bit Manipulation，转化为Single Number问题，异或每个元素和0-n，最后的结果就是缺失的那个数，时间复杂度O(n)，空间复杂度O(1)，符合要求
 * public int missingNumber(int[] nums) {
    int xor = 0, i = 0;
	for (i = 0; i < nums.length; i++) {
		xor = xor ^ i ^ nums[i];
	}
	return xor ^ i;
}

 * 6.改进方法4，排序后使用二分查找，用中间的下标值和中间的元素比对，若是下标值小于中间的元素，说明缺失元素在左边，将right置为mid并继续，反之left = mid+1。当数组本身有序时此
 * 方法最佳
 * 7.交换思想，将nums[i]，放到i的位置上，直到nums[i] == i或nums[i]==n，i后移一位，而nums[i] != i的即为missing number。
 * public int missingNumber(int[] nums) {  
        int i=0;  
          
        while(i<nums.length) {  
            int x = nums[i];  
            if(x>=nums.length) {++i; continue;}  
            if(x!=i) swap(nums, i, x);  
            else i++;  
        }  
  
        for(int j=0; j<nums.length; j++) {  
            if(j != nums[j]) return j;  
        }  
          
        return nums.length;  
    }  
      
    private void swap(int[] nums, int i, int j) {  
        int temp = nums[i];  
        nums[i] = nums[j];  
        nums[j] = temp;  
    }  
 */
public class question268 {
	public int missingNumber1(int[] nums) {		//我首先采用的方法2
        int i = 0;
        boolean flag = false;           //to see whether there is n or not
        for(; i<nums.length; i++) {
            int index = Math.abs(nums[i]);
            if(index == nums.length) {   //index == n, skip
                flag = true;
                continue;
            }
            else {
                nums[index] = -nums[index];
            }
        }
        i = 0;
        int j = -1;
        for(; i<nums.length; i++) {
            if(nums[i] > 0) {
                return i;
            }
            if(nums[i] == 0) {          //in case that nums[missing number] = 0 
                j = i;
            }
        }
        return flag==true? j:nums.length;
    }
	
	public int missingNumber(int[] nums) {		//方法3
        int supposed_sum = ((nums.length+1)*nums.length)>>1;
        int real_sum = 0;
        for(int i=0; i<nums.length; i++) {
            real_sum += nums[i];
        }
        return (supposed_sum-real_sum);
    }
}
