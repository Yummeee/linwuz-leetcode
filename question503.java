/*
 * Next Greater Element II (Medium)
 * 
 * Given a circular array (the next element of the last element is the first element of the array), print the 
 * Next Greater Number for every element. The Next Greater Number of a number x is the first greater number to 
 * its traversing-order next in the array, which means you could search circularly to find its next greater 
 * number. If it doesn't exist, output -1 for this number.
 * 
 * Example 1:
 * Input: [1,2,1]
   Output: [2,-1,2]
   Explanation: The first 1's next greater number is 2; 
   The number 2 can't find next greater number; 
   The second 1's next greater number needs to search circularly, which is also 2.
 * Note: The length of given array won't exceed 10000.  
 */
/*
 * 思路：
 * 1.Brute Force，对每个元素均遍历其后的每一个元素直至找到一个比其大的元素为止。时间复杂度在最坏情况下O(n^2)，即所有元素倒序排列时
 * 2.使用栈，遍历数组两次就一定能找到所有元素的next greater element。每遍历一个元素，判断其是否比栈顶元素大，若是，则不断出栈至栈空或此
 * 条件不再满足，随后将该元素入栈。这样既可找到每个元素的下一个更大的元素。为了实现方便的需要，构造了一个封装类来记录每个元素的下标，时间复杂
 * 度O(n)
 * 3.方法2还可以得以改进，不使用封装类。将stack中保存的元素存为元素下标即可。
 * 编辑给的此算法，和我的基本一致，顺序反过来，这样的好处是既不用将数组初始化-1，也不会做无用的访问。当i在nums.length到2*nums.length-1
 * 时，是找的每个元素的右侧第一个比其大的元素。0到nums.length-1时则是左侧
 * public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = 2 * nums.length - 1; i >= 0; --i) {
            while (!stack.empty() && nums[stack.peek()] <= nums[i % nums.length]) {
                stack.pop();			//这时可以放心出栈，因为对于左边的一个元素，如果当前元素右边的元素比当前元素小，如果当前元素
                都不能满足左边一个元素的条件，当前元素右侧的也一定不可能满足
            }
            res[i % nums.length] = stack.empty() ? -1 : nums[stack.peek()];
            stack.push(i % nums.length);
        }
        return res;
    }
 */
import java.util.*;
public class question503 {
	private class Element {								//思路2对应使用的封装类
        int value = 0;
        int index = 0;
        public Element(int n, int index) {
            value = n;
            this.index = index;
        }
    }
	
	public int[] nextGreaterElements(int[] nums) {		//思路3
        if(nums == null) {
            return null;
        }
        
        int[] res = new int[nums.length];
        if(nums.length == 0) {  
            return res;
        }
        
        Arrays.fill(res, -1);							//initialize result array
        Stack<Integer> stack = new Stack<>();
        stack.push(0);
        for(int i = 1; i < 2 * nums.length; i++) {
            int index = i % nums.length;                //almost traverse two times before you can find all elements' next greater element, index is the real position we are traversing right now
            while(!stack.isEmpty() && nums[stack.peek()] < nums[index]) {
                res[stack.pop()] = nums[index];
            }
            stack.push(index);
        }
        return res;
    }
	
	public int[] nextGreaterElements1(int[] nums) {		//思路2
        if(nums == null) {
            return null;
        }
        
        int[] res = new int[nums.length];
        if(nums.length == 0) {  
            return res;
        }
        for(int i = 0; i < nums.length; i++) {          //initialize result array
            res[i] = -1;
        }
        
        Stack<Element> stack = new Stack<>();
        stack.push(new Element(nums[0], 0));
        for(int i = 1; i < 2 * nums.length; i++) {
            int index = i % nums.length;                //almost traverse two times before you can find all elements' next greater element, index is the real position we are traversing right now
            while(!stack.isEmpty() && stack.peek().value < nums[index]) {
                Element top = stack.pop();
                res[top.index] = nums[index];
            }
            stack.push(new Element(nums[index], index));
        }
        return res;
    }
}
