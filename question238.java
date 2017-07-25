/*
 * Product of Array Except Self (Medium)
 * 
 * Given an array of n integers where n > 1, nums, return an array output such that output[i] is equal to the 
 * product of all the elements of nums except nums[i].
 * 
 * Solve it without division and in O(n).
 * For example, given [1,2,3,4], return [24,12,8,6].
 * 
 * Follow up:
   Could you solve it with constant space complexity? (Note: The output array does not count as extra space for
   the purpose of space complexity analysis.)
 */
/*
 * 思路：
 * 对于结果数组中的一个元素res[i]，其值应为nums[0], nums[1]...nums[i-1]的乘积，再乘以nums[i+1]...nums[n]的乘积。因此我们可以首先
 * 从前往后计算每个元素前的元素的乘积，这仅需遍历一遍数组即可，并将结果放入结果数组。随后从后往前在计算每个元素后的元素的乘积，并与先前计算的
 * 结果相乘即可。只需遍历两边数组，时间复杂度O(n)，extra space: O(1)。
 * 
 * 也可以连那两个变量都不要：
 * public int[] productExceptSelf(int[] nums) {
    int n = nums.length;
    int[] res = new int[n];
    res[0] = 1;
    for (int i = 1; i < n; i++) {
        res[i] = res[i - 1] * nums[i - 1];
    }
    int right = 1;
    for (int i = n - 1; i >= 0; i--) {
        res[i] *= right;
        right *= nums[i];
    }
    return res;
}
 */
public class question238 {
	public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int first_half = 1, second_half = 1;
        for(int i = 0; i < nums.length; i++) {                  //calculate the multiplies of the elements in front of nums[i] 
            res[i] = first_half;
            first_half *= nums[i];
        }
        for(int i = nums.length - 1; i >= 0; i--) {             //calculate the multiplies of the elements behind nums[i] 
            res[i] *= second_half;
            second_half *= nums[i];
        }
        return res;
    }
}
