/*
 * Maximum Subarray (Easy) 类似121题
 * 
 * Find the contiguous subarray within an array (containing at least one number) which has the largest sum.
 * For example, given the array [-2,1,-3,4,-1,2,1,-5,4],
   the contiguous subarray [4,-1,2,1] has the largest sum = 6
   
 * More practice:
   If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.  
 */
/*
 * 思路：分治和二分搜索的区别在于分治的subproblem的求解是一个递推过程，而分治的分成若干份的subproblem的求解是并列关系!!!
 * 1.Brute Force，时间复杂度O(n^2)，空间复杂度O(1)。就像找一个字符串的所有子串一样，找到数组的所有连续子数组求和
 * 
 * 2.动态规划: 使用一个全局最优一个局部最优变量的动态规划算法是常用的DP方法！！！！！！！！！！！
 * 我的思路是（也是错了将近10次才写对...）：对于一个数组，其最大子数组元素和一定出现在：非负值+nums[i]>sum[i-1]时，这个非负值是当前元素前的若干个元素之和，这个和必须为非负
 * 数，一旦小于0则将其重置为0。否则,sum[i] = sum[i-1]。实际上不难发现，这个非负值pre_sum就是局部最优解，包含当前元素为子数组中最后元素的局部最优解。
 * 这个思路已经很类似于Kadane算法！！
 * public int maxSubArray(int[] A) {
        int n = A.length;
        int[] dp = new int[n];	//dp[i] means the maximum subarray ending with A[i]，局部最优;
        dp[0] = A[0];
        int max = dp[0];	//全局最优
        
        for(int i = 1; i < n; i++){
            dp[i] = A[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0);
            max = Math.max(max, dp[i]);
        }
        
        return max;
}

 * 3.分治算法：
 * 我的思路是：同样采用了动态规划的中的非负值pre_sum概念，当pre_sum降为负时，则说明如果最优解一定出现在当前元素之前的一部分数组和之后的一部分数组之间，这个点就是分割点
 * 然而不对，我的思路实际上只是对之前的DP改进，只用一个值来保存全局最优
 * 
 * 正确地分治思路：时间复杂度O(nlogn)
 * 类似于二分搜索法，我们需要把数组一分为二，分别找出左边和右边的最大子数组之和，然后还要从中间开始向左右分别扫描，求出的最大值分别和左右两边得出的最大值相比较取最大的那一个。
 * public int maxSubArray(int[] nums) {
        if (nums.length == 0) return 0;
        return helper(nums, 0, nums.length - 1);
    }
    public int helper(int[] nums, int left, int right) {
        if (left >= right) return nums[left];
        int mid = left + (right - left) / 2;
        int lmax = helper(nums, left, mid - 1);				//找左侧的最大子数组之和
        int rmax = helper(nums, mid + 1, right);			//找右侧的最大子数组之和
        int mmax = nums[mid], t = mmax;						//从中间开始向左右找，该子数组一定含有中间这个元素
        for (int i = mid - 1; i >= left; --i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        t = mmax;
        for (int i = mid + 1; i <= right; ++i) {
            t += nums[i];
            mmax = Math.max(mmax, t);
        }
        return Math.max(mmax, Math.max(lmax, rmax));
    }
 */
public class question53 {
	public int maxSubArray1(int[] nums) {			//DP 动态规划
        if(nums.length == 1) {
            return nums[0];
        }
        
        int pre_sum = nums[0];						//pre_sum就是以当前元素结尾的子数组的和的局部最优解
        int[] sum = new int[nums.length];			//sum则是全局最优解，实际上没必要保存所有的全局最优解，只用一个即可，这就变成了Kadane算法
        sum[0] = nums[0];
        for(int i=1; i<nums.length; i++) {
            if(pre_sum<0) {
                pre_sum = 0;
            }
            if(pre_sum+nums[i] > sum[i-1]) {
                sum[i] = pre_sum+nums[i];
            }
            else {
                sum[i] = sum[i-1];
            }
            pre_sum += nums[i];
        }
        return sum[sum.length-1];
    }
	
	public int maxSubArray(int[] nums) {			//实际上是上面的DP算法的改进
        if(nums.length == 1) {
            return nums[0];
        }
        
        return helper(nums, 0);
    }
    public int helper(int nums[], int start) {
        if(start == nums.length) {
            return nums[start];
        }
        
        int pre_sum = nums[start];		//局部最优
        int sum = nums[start];			//全局最优
        for(int i=start+1; i<nums.length; i++) {
            if(pre_sum<0) {
               return Math.max(sum, helper(nums, i));			//找到分割点，进行分割
            }
            if(pre_sum+nums[i] > sum) {
                sum = pre_sum+nums[i];			//等价于这句话：max = Math.max(max, dp[i]);
            }
            pre_sum += nums[i];
        }
        return sum;
    }
}

/*
if(pre_sum<0) {			//将上面的递归转换为Kadane的思想
pre_sum = 0;			
}
pre_sum += nums[i];
if(pre_sum > sum) {
 sum = pre_sum;			
}

*/