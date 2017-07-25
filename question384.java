/*
 * Shuffle an Array (Medium) 与382那道水塘抽样一样也是求等概率抽样，但是这道题由于数组长度已知更简单
 * 
 * Shuffle a set of numbers without duplicates.
 * 
 * Example:
   // Init an array with set 1, 2, and 3.
   int[] nums = {1,2,3};
   Solution solution = new Solution(nums);

   // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
   solution.shuffle();

   // Resets the array back to its original configuration [1,2,3].
   solution.reset();

   // Returns the random shuffling of array [1,2,3].
   solution.shuffle();
 */
/*
 * 思路：
 * 为了每次生成一个数组的全排列且每种全排列被生成的几率相等，我们需要保证每个数组元素出现在数组的任何一个位置均是等可能的。因此，我们遍历nums
 * 数组，并每次生成一个(0~nums.length-1)的随机数组下标，随后交换当前遍历元素和生成随机下标对应的元素，这样就可保证每个元素出现在任何一个
 * 位置的概率是均等的。为了还原数组，我们在初始化实例时就额外保存一个原数组即可。reset，shuffle，初始化的时间复杂度均为O(n)
 * 
 * 实际上，我们每次不需要随机生成一个(0~nums.length-1)的数组下标，这道题本质上是生成一个全排列。我们在确定了每一个位置的元素之后，只需要调整
 * 后面的元素即可，这样并不影响生成每一种全排列的均等概率，因为我仍保证了每个数组元素出现在数组的任何一个位置均是等可能的。因此随机数的范围即可是
 * (0~nums.length-1-当前访问数组下标)
 */
import java.util.*;
public class question384 {
	int[] current_nums = null;
    int[] origin_nums = null;
    Random random = null;
    
    public question384(int[] nums) {
        random = new Random();
        current_nums = nums;
        origin_nums = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            origin_nums[i] = nums[i];
        }
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        for(int i = 0; i < current_nums.length; i++) {
            current_nums[i] = origin_nums[i];
        }
        return current_nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        for(int i = 0; i < current_nums.length; i++) {
            int pos = i + random.nextInt(current_nums.length - i);
            //int pos = random.nextInt(current_nums.length);   均可
            int temp = current_nums[i];
            current_nums[i] = current_nums[pos];
            current_nums[pos] = temp;
        }
        return current_nums;
    }
}
/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */