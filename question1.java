import java.util.*;

/*
 * Two Sum (Easy)
 * 
 * Given an array of integers, return indices of the two numbers such that they add up to a specific target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 */

/*
 * 解题思路：
 * 显然不能使用循环遍历的方式，因为这样必然导致O(n^2)的时间复杂度，而题目要求每个element尽量不要使用多次，两层遍历则使除第一个元素外的其它元素被访问多次，这样提交会提示超时
 * 标准答案里写的用双层遍历也被accepted了...
 * 
 * 最优解（即我的解题思路）：采用hash表解决，其访问时间为常数
 * 由于这里要返回的是数组的下标，因此将数组元素作为key，而下标作为value
 * 在每次加入一个新元素时，都去判断一次目前数组中是否存在两个元素之和等于target，事实上匹配成功一定发生在hash表录入一个新元素时
 * 所以录入数组元素到hash表中和判断是否满足target可以同时进行
 * 由于只循环了一遍数组，每个循环进行1次hash查找，时间为常数，所以时间复杂度为O(n),且除正解外，其余元素只会在录入时被访问一次
 * 注意：有一点陷阱，当target为数组中其中一个数的2倍时，一定要重新找一个解，这个是错的，即找到的两个元素的index不能一致，这里第一次做错了！
 * 
 * 还请注意Hashtable和HashMap的区别：多线程修改哈希表只能用前者，除非使用HashMap的改进，ConcurrentHashMap，但是单线程下后者效率高，这是二者的主要区别
 * 类别ArrayList和Vector之间的区别
 * 
 * 
 * 其实根本没必要查两次hash表
 * public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
        int complement = target - nums[i];
        if (map.containsKey(complement)) {
            return new int[] { map.get(complement), i };
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("No two sum solution");
}
 */
public class question1 {
	public int[] twoSum(int[] nums, int target) {
		int[] indices = new int[2];
		Hashtable<Integer,Integer> table = new Hashtable<Integer,Integer>();
		
		for(int i=0; i<nums.length; i++) {
			Integer n = table.get(target-nums[i]);
			if(n == null) {
				table.put(nums[i], i);       	 //说明hash表中还没有此元素，存入
			}
			n = table.get(target-nums[i]);
			if(n != null && n<i) {						//说明找到了两个元素
				indices[0] = n;
				indices[1] = i;
			}
		}
		return indices;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,5,8,13,15};
		int target = 23;
		
		int[] indices = new question1().twoSum(nums,target);
		System.out.println(indices[0] + " " + indices[1]);
	}
}
