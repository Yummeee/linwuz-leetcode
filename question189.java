/*
 * Rotate Array (Easy)
 * 
 * Rotate an array of n elements to the right by k steps.
   For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * Note:
   Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 *  
 * Hint:
   Could you do it in-place with O(1) extra space?
   Related problem: Reverse Words in a String II
 */
/*
 * 思路：有个坑，我们循环替换一次元素不一定能遍历数组中的所有元素，可能只能遍历一部分。经证明，一次循环替换能遍历的数量是nums.length/(k和nums.length的最大公约数)
 * 1.采用循环遍历移动元素的方式，比如将第一个元素移到正确的位置后，找到该位置原元素的正确位置后移动，不断重复直到回到第一个元素的位置。
 * 1)采用一个辅助数组用于表示对应位置的元素是否被替换过。时间复杂度O(n)，extra space O(n)。因为一次循环移动不一定能遍历所有元素。
 * 2)找到k和数组长度的最大公约数d，其就是需要重复多少次循环遍历的次数才能访问完所有的元素，每次访问的元素个数为nums.length/d。时间复杂度O(n)，空间复杂度O(n)，但是extra space O(1)
 * 2.Brute Force，每次将一个元素后移一位，重复k次这个过程。时间复杂度O(k*n)
 * 3.使用一个辅助数组，遍历目标数组，将每个元素放在辅助数组其应该在的位置，然后将辅助数组的内容拷贝回目标数组。时间复杂度O(n)，extra space O(n)
 * 
 * 编辑解法：
 * Approach #1 Brute Force [Time Limit Exceeded]，即思路2
 * public class Solution {
    public void rotate(int[] nums, int k) {
        int temp, previous;
        for (int i = 0; i < k; i++) {
            previous = nums[nums.length - 1];
            for (int j = 0; j < nums.length; j++) {
                temp = nums[j];
                nums[j] = previous;
                previous = temp;
            }
        }
    }
}

 * Approach #2 Using Extra Array [Accepted]，即思路3
 * public class Solution {
    public void rotate(int[] nums, int k) {
        int[] a = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            a[(i + k) % nums.length] = nums[i];
        }
        for (int i = 0; i < nums.length; i++) {
            nums[i] = a[i];
        }
    }
}

 * Approach #3 Using Cyclic Replacements [Accepted]，即思路1
 * 编辑的方法感觉比我的好，其用的是正向思维，我不需要知道我要进行多少次循环才能访问完所有的元素，我用一个变量count来统计我替换了多少个元素了，只要
 * count == nums.length则说明可以退出了。我也不需要知道每次循环我遍历多少个元素，只要没有回到起始元素，就继续。我把问题想复杂了。
 * public void rotate(int[] nums, int k) {
        k = k % nums.length;
        int count = 0;
        for (int start = 0; count < nums.length; start++) {
            int current = start;
            int prev = nums[start];
            do {
                int next = (current + k) % nums.length;
                int temp = nums[next];
                nums[next] = prev;
                prev = temp;
                current = next;
                count++;
            } while (start != current);
        }
    }
  
 * Approach #4 Using Reverse [Accepted] 机智啊，时间复杂度O(n)，extra space O(1)
 * In this approach, we firstly reverse all the elements of the array. Then, reversing the first k elements followed by 
 * reversing the rest n−k elements gives us the required result. 
 * 先整个翻转过去，然后分别翻转前k个和后n-k个元素即可
 * public void rotate(int[] nums, int k) {
        k %= nums.length;
        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }
    public void reverse(int[] nums, int start, int end) {				//两个元素的交换还可以异或实现！
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
 */
public class question189 {
	public void rotate(int[] nums, int k) {				//思路1.2
        if(nums == null || nums.length <= 1 || k == 0 || k == nums.length) {
            return;
        } 
        
        //k = k % nums.length;
        int count, temp1, temp2;
        int loop = maxCommonDivisor(k, nums.length);		//k和nums.length的最大公约数是需要遍历数组的次数，遍历这么多次后才会访问数组中的每个元素
        for(int i = 0; i < loop; i++) {
            temp1 = nums[i];
            count = nums.length / loop;        //一次循环只能遍历nums.length/loop个元素
            while(count > 0) {					//循环依次替换元素
                i = (i + k) % nums.length;
                temp2 = nums[i];         //被替换数temp2
                nums[i] = temp1;         //替换数temp1
                temp1 = temp2;
                count--;
            }    
        }
    }
	public int maxCommonDivisor(int num1, int num2) {			//找到k和nums.length的最大公约数，这个数就是需要遍历数组的次数
        if(num1 == 0 || num2 == 0) {
            return 0;
        }
        
        int larger = num1 >= num2 ? num1 : num2;
        int smaller = num1 < num2 ? num1 : num2;
        int divisor = 1;
        for(int i = smaller; i > 1; i--) {
            if((larger % i == 0) && (smaller % i == 0)) {
                divisor = i;
                break;
            }
        }
        return divisor;
    }
	
	public static void main(String[] args) {
		int[] nums = {1,2,3,4,5,6};
		new question189().rotate(nums, 4);
	}
}
