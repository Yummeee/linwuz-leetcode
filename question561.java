/*
 *  Array Partition I (Easy)
 * 
 * Given an array of 2n integers, your task is to group these integers into n pairs of integer, say (a1, b1), (a2, b2), ..., (an, bn) which 
 * makes sum of min(ai, bi) for all i from 1 to n as large as possible.
 * 
 * e.g.,
 * Input: [1,4,3,2]
   Output: 4
   Explanation: n is 2, and the maximum sum of pairs is 4.
 *
 * Note:
   n is a positive integer, which is in the range of [1, 10000].
   All the integers in the array will be in the range of [-10000, 10000].
 */
/*
 * 思路：
 * 这道题实际上就是将数组排序后求第0、2、4、6...位的元素之和，时间复杂度O(nlogn)
 * 证明：
 * 1.Assume in each pair i, bi >= ai.
   2.Denote Sm = min(a1, b1) + min(a2, b2) + ... + min(an, bn). The biggest Sm is the answer of this problem. Given 1, Sm = a1 + a2 + ... + an.
   3.Denote Sa = a1 + b1 + a2 + b2 + ... + an + bn. Sa is constant for a given input.
   4.Denote di = |ai - bi|. Given 1, di = bi - ai. Denote Sd = d1 + d2 + ... + dn.
   5.So Sa = a1 + a1 + d1 + a2 + a2 + d2 + ... + an + an + di = 2Sm + Sd => Sm = (Sa - Sd) / 2. To get the max Sm, given Sa is constant, we need to make Sd as small as possible.
   6.So this problem becomes find pairs in an array making sum of di (distance between ai and bi) as small as possible. Apparently, sort the array and then the distance of adjacent elements is the smallest. If that's not intuitive enough, see attached picture. Case 1 has the smallest Sd.
 */
import java.util.*;
public class question561 {
	public int arrayPairSum(int[] nums) {
        if(nums.length == 2){
            return Math.min(nums[0], nums[1]);
        }
        
        Arrays.sort(nums);
        int sum = 0;
        for(int i=0; i<nums.length; i+=2) {
            sum += nums[i];
        }
        return sum;
    }
}
