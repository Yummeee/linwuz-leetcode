/*
 * 3Sum (Medium)
 * 
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique 
 * triplets in the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * 
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
   A solution set is:
   [
     [-1, 0, 1],
     [-1, -1, 2]
   ]
 */
/*
 * 思路：
 * 参见第18题
 */
import java.util.*;
public class question15 {
	public List<List<Integer>> threeSum(int[] nums) {			//合成一个函数
        if(nums == null) {
            return null;
        }
        if(nums.length < 3) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        for(int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if(i > 0 && nums[i - 1] == x) {
                continue;
            }
            
            if(x + 2 * max < 0) {  //too small
                continue;
            }
            
            /* for any target, not just 0 */
            if(3 * x > 0) {         //too big
                break;
            }
            
            if(i < nums.length - 2 && 3 * x == 0 && nums[i + 2] == x) {
                res.add(Arrays.asList(x, x, x));
                break;
            }
            
            int j = i + 1, k = nums.length - 1, target = 0 - x;
            while(j < k) {
                int sum = nums[j] + nums[k];
                if(sum == target) {
                    res.add(Arrays.asList(x, nums[j], nums[k]));
                    j++;
                    k--;
                    while(j < k && nums[j - 1] == nums[j]) {
                        j++;
                    }
                    while(j < k && nums[k] == nums[k + 1]) {
                        k--;
                    }
                }
                else if(sum < target) {
                    j++;
                }
                else {
                    k--;
                }
            }
        }
        return res;
    }
	
	public List<List<Integer>> threeSum1(int[] nums) {
        if(nums == null) {
            return null;
        }
        if(nums.length < 3) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int max = nums[nums.length - 1];
        for(int i = 0; i < nums.length; i++) {
            int x = nums[i];
            if(i > 0 && nums[i - 1] == x) {
                continue;
            }
            
            if(x + 2 * max < 0) {  //too small
                continue;
            }
            
            /* for any target, not just 0 */
            if(3 * x > 0) {         //too big
                break;
            }
            
            if(i < nums.length - 2 && 3 * x == 0 && nums[i + 2] == x) {
                res.add(Arrays.asList(x, x, x));
                break;
            }
            twoSum(nums, i, 0 - x, res, x);
        }
        return res;
    }
	
	public void twoSum(int[] nums, int index, int target, List<List<Integer>> res, int other) {
        int i = index + 1;
        int j = nums.length - 1;
        while(i < j) {
            int sum = nums[i] + nums[j];
            if(sum == target) {
                res.add(Arrays.asList(other, nums[i], nums[j]));
                i++;
                j--;
                while(i < j && nums[i - 1] == nums[i]) {
                    i++;
                }
                while(i < j && nums[j] == nums[j + 1]) {
                    j--;
                }
            }
            else if(sum < target) {
                i++;
            }
            else {
                j--;
            }
        }
    }
}
