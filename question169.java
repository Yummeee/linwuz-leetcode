/*
 * Majority Element (Easy) 
 * 
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.
 * You may assume that the array is non-empty and the majority element always exist in the array.
 */
/*
 * 思路：实际运行速度：3=5>4>6>2>1
 * 1.Brute Force，时间复杂度O(n^2)
 * 2.HashMap，时间复杂度O(2n)，虽然是1阶的复杂度，但是hash表操作导致效率也较低
 
 * 3.Moore’s Voting Algorithm (只适用于找一个数组的半数以上元素，且在此元素确实存在时才有效)，时间复杂度O(n)，效率最高。
 * 原理：我们用一个变量c来记录一个候选majority element，以及f(c)来记录其出现次数，c即为当前阶段出现次数超过半数的元素。
 * 在遍历开始之前，该元素c为空，f(c)=0。
 * 然后在遍历数组A时，如果f(c)为0，表示当前并没有候选元素，也就是说之前的遍历过程中并没有找到超过半数的元素。那么，如果超过半数的元素c存在，那么c在剩下的子数组中，
 * 出现次数也一定超过半数。因此我们可以将原始问题转化为它的子问题。此时c赋值为当前元素, 同时f(c)=1。     
 * 如果当前元素A[i] == c, 那么f(c) += 1。(没有找到不同元素，只需要把相同元素累计起来)
 * 如果当前元素A[i] != c，那么f(c) -= 1。 (相当于删除1个c)，不对A[i]做任何处理(相当于删除A[i])
 * 此方法其实的思想就是：既然最多元素出现了不止n/2次，那用抵消的思想，用它和与它不相等的元素一一相消，剩下的一定就是最多的那个元素。
 
 * 4.将数组排序后找中位数即为结果，时间复杂度O(nlogn)
 * public int majorityElement(int[] nums) {
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
 * 5.分治算法(这题很类似归并排序)：找数组前n/2的majority element和后n/2的majority element，分别记为A、B。若A==B，即为A；若不等，则通过探查一遍数组即可得出结果，T(n) = 2T(n/2)+O(n)，时间复杂度O(nlogn)
 * public int majorityElement(int[] nums) {
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        
        return findMajority(nums, 0, nums.length-1);
    }
    public int findMajority(int[] nums, int start, int end) {
        if(end-start+1 == 1) {
            return nums[start];
        }
        int lengthA = (end-start+1)/2;
        int A = findMajority(nums, start, start+lengthA-1);
        int B = findMajority(nums, start+lengthA, end);
        
        if(A == B) {
            return A;
        }
        else {
            int count = 0;
            for(int i=start; i<=end; i++) {
                if(nums[i] == A) {
                    count++;
                }
            }
            if(count >= (end-start+1)/2+1) {
                return A;
            }
            return B;
        }
    }
 * 6.位操作，循环32次，每一次循环对数组的每一个元素的ith位统计1的数量和0的数量，majority element的ith的值一定是数量较大的那个数(0或1)，时间复杂度O(32*n)
 */
import java.util.*;
public class question169 {
	public int majorityElement1(int[] nums) {			//HashMap
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        int threshold = nums.length/2 + 1;
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++) {
            Integer n = map.get(nums[i]);
            if(n != null) {
                map.put(nums[i], ++n);
            }
            else {
                map.put(nums[i], 1);
            }
        }
        
        for(int i=0; i<nums.length; i++) {
            Integer n = map.get(nums[i]);
            if(n >= threshold) {
            	return nums[i];
            }
        }
        return -1;        
    }
	public int majorityElement2(int[] nums) {				//Moore's voting algorithm
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        int threshold = nums.length/2 + 1;
        int majority = 0;
        int count = 0;
        for(int i=0; i<nums.length; i++) {
            if(count == 0) {
                majority = nums[i];
                count++;
                continue;
            }
            if(nums[i] == majority) {
                count++;
            }
            else {
                count--;
            }
        }
        return majority;
    }
	
	public int majorityElement3(int[] nums) {				//Divide and Conquer
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        
        return findMajority(nums, 0, nums.length-1);
    }
    public int findMajority(int[] nums, int start, int end) {
        if(end-start+1 == 1) {
            return nums[start];
        }
        int lengthA = (end-start+1)/2;
        int A = findMajority(nums, start, start+lengthA-1);
        int B = findMajority(nums, start+lengthA, end);
        
        if(A == B) {
            return A;
        }
        else {
            int count = 0;
            for(int i=start; i<=end; i++) {
                if(nums[i] == A) {
                    count++;
                }
            }
            if(count >= (end-start+1)/2+1) {
                return A;
            }
            return B;
        }
    }
    public int majorityElement(int[] nums) {			//bit manipulation
        if(nums.length==1 || nums.length==2) {
            return nums[0];
        }
        //if always exist majority, then nums.length==2 can also add to the first if
        int[] bits = new int[32];
        for(int i=0; i<32; i++) {
            for(int j=0; j<nums.length; j++) {
                if(((nums[j]>>i) & 1) == 1) {
                    bits[i]++;
                }
            }
        }
        int majority = 0;
        int threshold = nums.length/2 + 1;
        for(int i=0; i<32; i++) {
            if(bits[i] >= threshold) {
                majority |= 1<<i;
            }
        }
        return majority;
    }
}
