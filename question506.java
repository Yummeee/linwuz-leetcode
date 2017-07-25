/*
 * Relative Ranks (Easy)
 * 
 * Given scores of N athletes, find their relative ranks and the people with the top three highest scores, who will be awarded
 * medals: "Gold Medal", "Silver Medal" and "Bronze Medal".
 * 
 * Example 1:
 * Input: [5, 4, 3, 2, 1]
   Output: ["Gold Medal", "Silver Medal", "Bronze Medal", "4", "5"]
   Explanation: The first three athletes got the top three highest scores, so they got "Gold Medal", "Silver Medal" and "Bronze Medal". 
   For the left two athletes, you just need to output their relative ranks according to their scores.
   
 * Note:
   N is a positive integer and won't exceed 10,000.
   All the scores of athletes are guaranteed to be unique.
 */

/*
 * 思路：要对运动员得分排序，然后确定排序后的得分在原数组的位置。为减少遍历次数，排序时需保存每一个元素对应的原数组下标，可以用一个额外数组(同步移动下标的位置)，或者TreeMap,HashMap等实现
 * 主要是排序方式：
 * 1.简单排序
 * 2.利用TreeMap，BST
 * 3.Java封装的Arrays.sort方法（了解Java比较器！）
 * Example:
nums[i]    : [10, 3, 8, 9, 4]
pair[i][0] : [10, 3, 8, 9, 4]
pair[i][1] : [ 0, 1, 2, 3, 4]
After sort:
pair[i][0] : [10, 9, 8, 4, 3]
pair[i][1] : [ 0, 3, 2, 4, 1]
 * public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        int[][] pair = new int[nums.length][2];
        
        for (int i = 0; i < nums.length; i++) {
            pair[i][0] = nums[i];
            pair[i][1] = i;
        }
        
        Arrays.sort(pair, (a, b) -> (b[0] - a[0]));
        
        String[] result = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[pair[i][1]] = "Gold Medal";
            }
            else if (i == 1) {
                result[pair[i][1]] = "Silver Medal";
            }
            else if (i == 2) {
                result[pair[i][1]] = "Bronze Medal";
            }
            else {
                result[pair[i][1]] = (i + 1) + "";
            }
        }

        return result;
    }
}
Also we can use an one dimension array. This will save a little bit space but space complexity is still O(n).

public class Solution {
    public String[] findRelativeRanks(int[] nums) {
        Integer[] index = new Integer[nums.length];
        
        for (int i = 0; i < nums.length; i++) {
            index[i] = i;
        }
        
        Arrays.sort(index, (a, b) -> (nums[b] - nums[a]));
        
        String[] result = new String[nums.length];

        for (int i = 0; i < nums.length; i++) {
            if (i == 0) {
                result[index[i]] = "Gold Medal";
            }
            else if (i == 1) {
                result[index[i]] = "Silver Medal";
            }
            else if (i == 2) {
                result[index[i]] = "Bronze Medal";
            }
            else {
                result[index[i]] = (i + 1) + "";
            }
        }

        return result;
    }
}
 */
import java.util.*;
import java.util.Map.Entry;
public class question506 {
	public String[] findRelativeRanks(int[] nums) {
        String[] ranks = {};
        if(nums.length==0 || nums==null) {
            return ranks;
        }
        
        ranks = new String[nums.length];
        TreeMap<Integer, Integer> set = new TreeMap<>();
        for(int i=0; i<nums.length; i++) {
            set.put(nums[i],i);
        }
        //set is ordered
        Entry<Integer, Integer> temp = set.lastEntry();
        int count = 1;
        while(temp != null) {
        	if(count > 3) {
        		ranks[temp.getValue()] = String.valueOf(count);
        	}
        	else if(count == 1) {
        		ranks[temp.getValue()] = "Gold Medal";
        	}
        	else if(count == 2) {
        		ranks[temp.getValue()] = "Silver Medal";
        	}
        	else if(count == 3) {
        		ranks[temp.getValue()] = "Bronze Medal";
        	}
        	count++;
        	temp = set.lowerEntry(temp.getKey());
        }
        
        return ranks;
    }

}
