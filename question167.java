/*
 * Two Sum II - Input array is sorted (Easy)
 * 
 * Given an array of integers that is already sorted in ascending order, find two numbers such that they add up to a 
 * specific target number.
 * 
 * The function twoSum should return indices of the two numbers such that they add up to the target, where index1 must be 
 * less than index2. Please note that your returned answers (both index1 and index2) are not zero-based.
 * You may assume that each input would have exactly one solution and you may not use the same element twice!!!!
 * 
 * e.g.,
 * Input: numbers={2, 7, 11, 15}, target=9
   Output: index1=1, index2=2
 */

/*
 * 思路：
 * 1.同question1，用hash表，遍历一遍numbers的同时完成查找。注意这道题的下标是1到n，不是0到n-1（此方法相当之慢...），时间复杂度O(n)，空间复杂度O(n)
 * 2.遍历numbers，用二分查找target-numbers[i],超时，时间复杂度O(nlogn)，空间复杂度O(1)
 * 3.最优解：两个指针二路查找，一个起始在开头，一个起始在结尾。若两个指针之和小于target，左侧指针++，大于则右侧指针--，等于则退出循环。时间复杂度O(n)，空间复杂度O(1)
 */
import java.util.*;
public class question167 {
	public int[] twoSum1(int[] numbers, int target) {
        int[] indices = new int[2];
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<numbers.length; i++) {
            Integer n = map.get(target-numbers[i]);
            if(n == null) {
                map.put(numbers[i], i);
            }
            else {
                indices[0] = n+1;
                indices[1] = i+1;
            }
        }
        return indices;
    }
	
	public int[] twoSum2(int[] numbers, int target) {
        int[] indices = new int[2];
        
        for(int i=0; i<numbers.length; i++) {
            if(numbers[i] <= target) {		//如果元素中有0，则为取等号情况
                int indice_2 = binarySearch(numbers, target-numbers[i]);
                if(indice_2 != -1) {
                    indices[0] = i+1;
                    indices[1] = indice_2+1;
                    break;
                }
            }
        }
        
        return indices;
    }
    
    public int binarySearch(int[] nums, int target) {
        int low = 0, high = nums.length;
        int mid = 0;
        int indice = -1;
        while(low <= high) {
            mid = (low+high)/2;
            if(nums[mid] == target) {
                indice = mid;
                break;
            }
            else if(nums[mid] < target) {
                low = mid;
            }
            else {
                high = mid;
            }
        }
        
        return indice;
    }
    
    public int[] twoSum(int[] numbers, int target) {
        int[] indices = new int[2];
        if(numbers.length<2 || numbers==null) {
            return indices;
        }
        int left = 0, right = numbers.length-1;
        while(left < right) {
            int temp = numbers[left]+numbers[right];
        	if(temp == target)
        	{
        		indices[0] = left+1;
        		indices[1] = right+1;
        		break;
        	}
        	else if(temp > target) {
        		right--;
        	}
        	else {
        		left++;
        	}
        }
        
        return indices;
    }

}
