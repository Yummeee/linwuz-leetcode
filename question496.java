/*
 * Next Greater Element I (Easy)
 * 
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all the 
 * next greater numbers for nums1's elements in the corresponding places of nums2.The Next Greater Number of a number x in 
 * nums1 is the first greater number to its right in nums2. If it does not exist, output -1 for this number.
 * 
 * Note:
   All elements in nums1 and nums2 are unique.
   The length of both nums1 and nums2 would not exceed 1000.
 */

/*
 * 解法1：Brute Force，直接找到nums1中的元素在nums2中的位置（用HashMap寸nums2）并往后继续找，时间复杂度最差时O(n*m)，用不用hash这个方法都被accept
 * 解法2：用stack
 * Approach #3 Using Stack [Accepted] O(n+m)
 * 和我想的对nums建立next larger element映射表不谋而合，map(element, next larger element)，只不过stack用在建立映射表上！我后来想用stack直接得结果的思路想歪了！
 * 类似用栈判断括号匹配或将中缀表达式转换为后缀表达式以及后缀表达式计算；
 * 具体方法：
 * 1.top=-1;
   2.while(i<nums.length)
   3.如果栈为空(top==-1)，入栈当前元素，跳转到步骤5；
   4.栈不为空则if(nums[i]>stack[top])，则map.put(栈顶元素，当前元素)，pop(栈顶)，跳转到步骤3，否则入栈当前元素，跳转到步骤5；
   5.i++
 * 
 */
import java.util.*;
public class question496 {
	public int[] nextGreaterElement(int[] findNums, int[] nums) {
        if(findNums.length==0 || nums.length==0) {
            int[] result = {};
            return result;
        }
        
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            map.put(nums[i], i);
        }
        
        int[] result = new int[findNums.length];
        for(int i=0; i<result.length; i++) { 
           int j = map.get(findNums[i])+1;
           for(; j<nums.length; j++) {
               if(findNums[i] < nums[j]) {
                   result[i] = nums[j];
                   break;
               }
           }
           if(j == nums.length) {
               result[i] = -1;
           }
        }
        
        return result;
    }

}
