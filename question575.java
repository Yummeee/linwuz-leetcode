/*
 * Distribute Candies (Easy)
 * 
 * Given an integer array with even length, where different numbers in this array represent different kinds of candies. Each 
 * number means one candy of the corresponding kind. You need to distribute these candies equally in number to brother and 
 * sister. Return the maximum number of kinds of candies the sister could gain.
 * 
 * Example 1:
 * Input: candies = [1,1,2,2,3,3]
   Output: 3
   Explanation:
   There are three different kinds of candies (1, 2 and 3), and two candies for each kind.
   Optimal distribution: The sister has candies [1,2,3] and the brother has candies [1,2,3], too. 
   The sister has three different kinds of candies.
 * Example 2:
 * Input: candies = [1,1,2,3]
   Output: 2
   Explanation: For example, the sister has candies [2,3] and the brother has candies [1,1]. 
   The sister has two different kinds of candies, the brother has only one kind of candies.   
 * 
 * Note:  
   The length of the given array is in range [2, 10,000], and will be even.
   The number in given array is in range [-100,000, 100,000].
 */
/*
 * 思路：此题比较简单，本质就是统计数组中不同元素有多少种。
 * 1.使用hashset，遍历一遍数组，将元素加入hashset。如果最后hashset的大小超过数组长度一般，由于二人平分，妹妹最多只能得到数组长度一半种糖果；否则，则最多
 * 得到hashset的大小种糖果。时间复杂度O(n)，空间复杂度O(1)
 * 2.不用hashset，将数组首先排序，随后一个指针遍历一遍数组也可以统计出数组中不同元素的个数。时间复杂度O(nlogn)，空间复杂度O(n)，extra space O(1)
 * 
 * 编辑还给出了两种brute force的方法：
 * Approach #1 Brute Force [Time Limit Exceeded] 利用全排列...
 * The brute force approach is really simple. We can generate all the permutations of the given numsnums array representing 
 * the candies and determine the number of unique elements in the first half of the generated array.
 * public class Solution {
    int max_kind = 0;
    public int distributeCandies(int[] nums) {
        permute(nums, 0);
        return max_kind;
    }
    public void permute(int[] nums, int l) {
        if (l == nums.length - 1) {
            HashSet < Integer > set = new HashSet < > ();
            for (int i = 0; i < nums.length / 2; i++) {
                set.add(nums[i]);
            }
            max_kind = Math.max(max_kind, set.size());
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            permute(nums, l + 1);
            swap(nums, i, l);
        }
    }
    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
}
   Time complexity : O(n!). A total of n! permutations are possible for numsnums array of size n.
   Space complexity : O(n). The depth of the recursion tree can go upto n.
   
 * Approach #2 Better Brute Force [Time Limit Exceeded]:
 * 每次遇到一个没遇到过的单词，将所有与其相等的单词置为invalid
 *  public int distributeCandies(int[] candies) {
        int count = 0;
        for (int i = 0; i < candies.length && count < candies.length / 2; i++) {
            if (candies[i] != Integer.MIN_VALUE) {
                count++;
                for (int j = i + 1; j < candies.length; j++) {
                    if (candies[j] == candies[i])
                        candies[j] = Integer.MIN_VALUE;
                }
            }
        }
        return count;
    }
    Time complexity : O(n^2) We traverse over all the elements of candiescandies for every new element found. In the worst case, we do so for every element of candiescandies array. n refers to the size of candiescandies array.
    Space complexity : O(1). Constant space is used.我怀疑这里是不对的，因为修改了原输入
 */
import java.util.*;
public class question575 {
	public int distributeCandies(int[] candies) {               //sort
        Arrays.sort(candies);
        int threshold = candies.length >> 1, num = 1;           //if num = 0, we will miss one element, since i != j , there are two different elements
        for(int i = 1; i < candies.length; i++) {
            if(num == threshold) {          //in case that length == 2
                break;
            }
            if(candies[i] != candies[i-1]) {
                num++;
            }
        }
        return num;
    }
	
	public int distributeCandies2(int[] candies) {              //hashset 2
        //length of the given array is in range [2, 10,000]
        HashSet<Integer> set = new HashSet<>();
        int threshold = candies.length >> 1;
        for(int i = 0; i < candies.length; i++) {
            set.add(candies[i]);
            if(set.size() == threshold) {
                break;
            }
        }
        return set.size();
    }
	
	public int distributeCandies1(int[] candies) {
        //length of the given array is in range [2, 10,000]
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < candies.length; i++) {
            set.add(candies[i]);
        }
        return set.size() > (candies.length >> 1) ? (candies.length >> 1) : set.size();
    }
}
