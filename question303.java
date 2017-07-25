/*
 * Range Sum Query - Immutable (Easy)
 * Given an integer array nums, find the sum of the elements between indices i and j (i ≤ j), inclusive.
 * 
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
   sumRange(0, 2) -> 1
   sumRange(2, 5) -> -1
   sumRange(0, 5) -> -3
 *  
 * Note:
   You may assume that the array does not change.
   There are many calls to sumRange function. 
 */
/*
 * 思路：
 * 1.Brute Force，每一次调用都遍历从i到j即可，初始化时间复杂度O(1)，每次调用O(n)，超时不可取
 * 2.DP，由于多次调用，可以用一个一维数组记录(0,i)元素对之间的sum。dp[i] = dp[i-1] + nums[i]进行初始化，这样初始化时间复杂度O(n)，每次调用O(1)。这个DP策略太智障了...一开始用个二维DP加hash表记录先前调用的方法竟然后栈溢出...
 * 甚至可以不用DP数组...由于nums不变，所以可以直接在nums上修改
 * 
 * 3.编辑还给了一个初始化时间复杂度O(n^2)，每次调用O(1)的方法...用HashMap<Pair<Integer, Integer>, Integer>保存i,j间sum...
 * private Map<Pair<Integer, Integer>, Integer> map = new HashMap<>();

public NumArray(int[] nums) {
    for (int i = 0; i < nums.length; i++) {
        int sum = 0;
        for (int j = i; j < nums.length; j++) {
            sum += nums[j];
            map.put(Pair.create(i, j), sum);
        }
    }
}

public int sumRange(int i, int j) {
    return map.get(Pair.create(i, j));
}

 */
import java.util.*;
public class question303 {
	int[] nums;			//change nums to nums[i] = sum of (0,i)
	public question303(int[] nums) {
        if(nums.length > 0) {
            this.nums = nums;
            for(int i=1; i<nums.length; i++) {
                nums[i] = nums[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        return i == 0 ? nums[j] : nums[j] - nums[i-1];
    }
	/*
    int[] dp;           //dp[i] = sum of (0, i);
    public question303(int[] nums) {
        if(nums.length > 0) {
            this.nums = nums;
            dp = new int[nums.length];
            dp[0] = nums[0];
            for(int i=1; i<nums.length; i++) {
                dp[i] = dp[i-1] + nums[i];
            }
        }
    }
    
    public int sumRange(int i, int j) {
        return dp[j] - dp[i] + nums[i];
    }
    */
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * int param_1 = obj.sumRange(i,j);
 */