/*
 * K-diff Pairs in an Array (Easy)
 * 
 * Given an array of integers and an integer k, you need to find the number of unique k-diff pairs in the array. Here a 
 * k-diff pair is defined as an integer pair (i, j), where i and j are both numbers in the array and their absolute 
 * difference is k.
 * 
 * Example 1:
 * Input: [3, 1, 4, 1, 5], k = 2
   Output: 2
   Explanation: There are two 2-diff pairs in the array, (1, 3) and (3, 5).
   Although we have two 1s in the input, we should only return the number of unique pairs.
 * Example 2:
 * Input:[1, 2, 3, 4, 5], k = 1
   Output: 4
   Explanation: There are four 1-diff pairs in the array, (1, 2), (2, 3), (3, 4) and (4, 5).
 * Example 3:
 * Input: [1, 3, 1, 5, 4], k = 0
   Output: 1
   Explanation: There is one 0-diff pair in the array, (1, 1).  
 *
 *  Note:
    The pairs (i, j) and (j, i) count as the same pair.
    The length of the array won't exceed 10,000.
    All the integers in the given input belong to the range: [-1e7, 1e7].
 */
/*
 * 思路：k<0是无解的！！但是输入没有说一定不小于0。就是2 sum的变种！！！！！
 * 1.Brute Force, 对数组中的每个元素nums[i]，遍历其后的元素，遇到|nums[j]-nums[i]| == k则输出。为了保证找到的pair是unqiue的，可以用hashset将
 * pair (i,j)和(j,i)存入，或者hashmap就不用pair。时间复杂度O(n^2)，空间复杂度O(n)。如果首先对nums数组排序，则不用使用hash表，利用几个变量即可避免重复
 * 
 * 2.Two pointers，将数组先排序，然后一个快指针i和一个慢指针j，类似于heaters那道题的two pointer思想。时间复杂度O(nlogn)，空间复杂度O(1)，实际运行
 * 中比95%的人要快，比那些理论复杂度O(1)的方法要快。指针i遍历一遍nums，同时指针j在这个过程中一共遍历一遍nums。对于每个nums[i]，找到nums[j] >= nums[i]+k，
 * 否则j一直后移，如果j遍历完nums，还是nums[j] < nums[i]+k，由于nums现在有序，nums[i]之后的元素绝对不可能再找到pair。如果nums[j] == nums[i]+k,
 * 则只要i != j（必须要是数对，不能自己匹配自己），且nums[i]和nums[j]不等于之前找到的那对元素（避免重复），则说明又找到一对元素。如果nums[j] > nums[i]+k,
 * 则有可能nums[j] == nums[i+1]+k，所以此时j不变，i继续走一个去判断
 * 
 * 3.使用HashMap通过统计每个元素的出现次数来找到元素对。若k==0, 统计出现次数不为1的元素的个数就是结果；若k>0，则对每个元素nums[i]，寻找map中是否有
 * nums[i]+k存在，若有则加1，最后的计数即为结果。时间复杂度O(n)，空间复杂度O(n)。其实不难发现是Two sum的变种
 * public int findPairs(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k < 0)   return 0;
        
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int i : nums) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (k == 0) {
                //count how many elements in the array that appear more than twice.
                if (entry.getValue() >= 2) {
                    count++;
                } 
            } else {
                if (map.containsKey(entry.getKey() + k)) {
                    count++;
                }
            }
        }
        
        return count;
    }
}
 *
 * 4. Two pointers方法，和我的方法基本思想一致！时间复杂度O(nlogn)，空间复杂度O(1)
 * public int findPairs(int[] nums, int k) {
    int ans = 0;
    Arrays.sort(nums);
    for (int i = 0, j = 0; i < nums.length; i++) {		//max(j, i+1)就类似我的防止k==0, i==j且nums[i]=nums[j]出现，不可能nums[i]>nums[j]，因为我的方法这时会令j++的
        for (j = Math.max(j, i + 1); j < nums.length && (long) nums[j] - nums[i] < k; j++) ;		//相减防溢出...找到第一个j使得nums[j] >= nums[i] + k
        if (j < nums.length && (long) nums[j] - nums[i] == k) ans++;
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) i++;		//忽略重复元素
    }
    return ans;
}
 */
import java.util.*;
public class question532 {
	public int findPairs(int[] nums, int k) {			//思路2
        if(nums == null || nums.length <= 1 || k < 0) {
            return 0;
        }
        
        Arrays.sort(nums);
        int res = 0, j = 1, pre_left = Integer.MIN_VALUE, pre_right = Integer.MIN_VALUE;			//i is slower pointer, j is faster pointer
        for(int i=0; i<nums.length; i++) {
            while(j < nums.length && nums[j] < nums[i]+k) {
                j++;
            }
            if(j == nums.length) {
                break;
            }
            if(nums[i]+k == nums[j]) {
                if(i != j && nums[i] != pre_left && nums[j] != pre_right) {         //i != j is for k == 0
                    res++;
                    pre_left = nums[i];
                    pre_right = nums[j];
                }
                if(i == j) {		//if not, next loop still i == j, so we keep i still, move j since there is still chance nums[i] could find a pair
                	i--;
                }
                j++;                //important to add j here, since this nums[j] has been used, for nums[i+1] > nums[i], it cannot be used again; as for nums[i+1] = nums[i], we should skip nums[j]
            }
        }
        return res;
    }
	
	
}
