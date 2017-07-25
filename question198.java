/*
 * House Robber (Easy)
 * 
 * You are a professional robber planning to rob houses along a street. Each house has a certain amount of money stashed, the only constraint
 * stopping you from robbing each of them is that adjacent houses have security system connected and it will automatically contact the 
 * police if two adjacent houses were broken into on the same night.
 * 
 * Given a list of non-negative integers representing the amount of money of each house, determine the maximum amount of money you can rob 
 * tonight without alerting the police.
 */

/*
 * 思路：
 * 动态规划，记dp[i]为抢劫0...i栋房屋的最大赃款数，dp[0]=nums[0]，dp[1]=max(nums[0], nums[1])。则dp[i]的值只跟dp[i-1]和dp[i-2]有关，因为显然dp[i] >= dp[i-1]
 * 若考虑dp[i-3]，由于i-3与i中间隔了2栋房子，i-2与i中间隔了1栋房子，这两种情况无论如何都能抢第i栋房子，则dp[i-3]+nums[i]<=dp[i-2]+nums[i]始终成立，则仅需考虑dp[i-2]
 * 和dp[i-1]与dp[i]的关系。
 * 记pos为dp[i-1]最优解抢劫的最后一栋房子，若pos<i-1，显然dp[i] = dp[i-1]+nums[i];
 * 若pos == i-1，则有三种情况：
 * 1)dp[i] = dp[i-2]+nums[i];
 * 2)dp[i] = dp[i-1]-nums[i-1]+nums[i];
 * 3)dp[i] = dp[i-1];
 * 若nums[i] <= nums[i-1], 则只可能是2)或3)的一种，二者比较即可；
 * 若nums[i] > nums[i-1]，则只可能是1)或2)的一种，二者比较即可；
 * 
 * 事实上，不需要记录pos即可，当pos<i-1时，dp[i-1]必然是等于dp[i-2]的，且上述的第2)种情况和第1)种情况实际上是一种情况，因为如果dp[i-1]不取nums[i-1]，其实就变成了小于
 * 或等于dp[i-2]的一个数，是一定不会比1)大的。
 * 所以现在规则就变成
 * dp[i] = Math.max(dp[i-2]+nums[i], dp[i-1])
 * 
 * 优化空间的做法：
 * 类似53题，我们也不需要保存整个全局最优解，只需要两个变量分别保存pre0和pre1即可，其中pre0表示没有抢第i-1栋房子的最优值，即dp[i-2]，pre1表示抢第i-1栋房子的最优值，即
 * dp[i-3]+nums[i-1]，而不一定等于dp[i-1]。最后求pre1和pre0的最大值即可
 * public int rob(int[] num) {
    int pre0 = 0;
    int pre1 = 0;
    for (int n : num) {
        int temp = pre0;
        pre0 = Math.max(pre0, pre1);			//对于当前房子i，已经不抢当前结点，所以pre0直接去前一栋房子的两个值中的较大者即可
        pre1 = n + temp;
    }
    return Math.max(pre0, pre1);
}
 */
public class question198 {
	public int rob1(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        if(nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = nums[0];
        int pos = 0;
        if(nums[0] < nums[1]) {
            dp[1] = nums[1];
            pos = 1; 
        }
        for(int i=2; i<nums.length; i++) {
            if(pos == i-1) {
            	int temp = dp[i-2]+nums[i];
                if(nums[i] > nums[i-1]) {
                    dp[i] = Math.max(dp[i-1]-nums[i-1]+nums[i], temp);
                    pos = i;
                }
                else {
                    if(temp > dp[i-1]) {
                    	dp[i] = temp;
                        pos = i;
                    }
                    else {
                        dp[i] = dp[i-1];
                    }
                }
            }
            else {
                dp[i] = dp[i-1] + nums[i];
                pos = i;
            }
        }
        return dp[nums.length-1];
    }
	public int rob(int[] nums) {				//改进rob1，不需要pos变量，并将递推规则精简到两种
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }
        if(nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for(int i=2; i<nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2]+nums[i]);
        }
        return dp[nums.length-1];
    }
}
