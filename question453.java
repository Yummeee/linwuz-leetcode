/*
 * Minimum Moves to Equal Array Elements (Easy)
 * 
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, 
 * where a move is incrementing n - 1 elements by 1.
 * 
 * e.g.,
 * Input:
   [1,2,3]
   Output:
   3
   Explanation:
   Only three moves are needed (remember each move increments two elements):
   [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 */

/*
 * 思路（就和Nim Game一样是一个数学问题）：
 * 观察后不难发现，遵循这样的规则将会有最小的move次数：
 * 找出最大数，对其余的n-1个数加一（若有两个最大数，则在这两个数中任选一个加一），不断循环，直至所有数一样大
 * 
 * 根据此规则不难发现moves的值实际上就是最小的数与其余数之间差之和
 *
 * 另一种思路(纯数学)，时间复杂度O(n)：(这种思路其实等价于上述思路，上述思路由于算法设计失误导致复杂度为2n)
 * 由题知sum + moves * (n - 1) = x * n
 * 而x = minNum + moves
 * 所以moves = sum - minNum*n (逻辑上等价于n-1个数加1等于1个数减1使得所有数相等)
 */
import java.util.*;
public class question453 {
	public int minMoves1(int[] nums) {		//排序O(nlogn)
        if(nums.length == 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int moves = 0;
        for(int i=nums.length-1; i>=1; i--) {
            moves += nums[i]-nums[0];
        }
        return moves;
    }
	public int minMoves2(int[] nums) {		//遍历找最小O(2n)
        if(nums.length == 0) {
            return 0;
        }
        
        //Arrays.sort(nums);
        int min = java.lang.Integer.MAX_VALUE;
        for(int i=0; i<nums.length; i++) {
            if(nums[i] < min) {
                min = nums[i];
            }
        }
        int moves = 0;
        for(int i=0; i<nums.length; i++) {
            moves += nums[i]-min;
        }
        return moves;
    }
	 public int minMoves(int[] nums) {		//O(n)
	        if(nums.length == 0) {
	            return 0;
	        }
	        
	        //Arrays.sort(nums);
	        int min = java.lang.Integer.MAX_VALUE;
	        int sum = 0;
	        for(int i=0; i<nums.length; i++) {
	            if(nums[i] < min) {
	                min = nums[i];
	            }
	            sum += nums[i];
	        }
	        int moves = sum-nums.length*min;
	        return moves;
	    }
}
