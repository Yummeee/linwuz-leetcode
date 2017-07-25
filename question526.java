/*
 * Beautiful Arrangement (Medium)
 * 
 * Suppose you have N integers from 1 to N. We define a beautiful arrangement as an array that is constructed by
 * these N numbers successfully if one of the following is true for the ith position (1 ≤ i ≤ N) in this array:
 * 1.The number at the ith position is divisible by i.
 * 2.i is divisible by the number at the ith position.
 * Now given N, how many beautiful arrangements can you construct?
 * 
 * Example 1:
 * Input: 2
   Output: 2
   Explanation: 
   The first beautiful arrangement is [1, 2]:
   Number at the 1st position (i=1) is 1, and 1 is divisible by i (i=1).
   Number at the 2nd position (i=2) is 2, and 2 is divisible by i (i=2).
   The second beautiful arrangement is [2, 1]:
   Number at the 1st position (i=1) is 2, and 2 is divisible by i (i=1).
   Number at the 2nd position (i=2) is 1, and i (i=2) is divisible by 1.
 * Note:
   N is a positive integer and will not exceed 15  
 */
/*
 * 思路：
 * 1.Brute Force，求出1-N的所有全排列并一一判断是否是满足题意的全排列，但是会超时，时间复杂度O(n!)，就当复习全排列的写法了。
 * 2.改进BF算法，求排列前先判断此交换能否使得交换后的数组满足题目要求。之前将限制条件设的太死导致会漏掉一些情况。事实上，交换前只需要判断
 * 前面的元素，即nums[i]移到k位置是满足题意的即可，而暂时不必管靠后的nums[k]移到i位置是否满足题意。因为nums[k]最终的位置不一定是固定
 * 在i的，而nums[i]的位置在这一次全排列是固定到k的，因此nums[i]必须要满足题意。而nums[k]可以暂时不满足题意，只要之后再次被交换到一个
 * 满足题意的位置即可，如果条件限制死的话则会漏掉一些情况，这些情况下nums[k]在1...i-1位置处有满足题意的位置，但是由于i不满足就被漏掉了。
 * 
 * 注：编辑给的方法构造全排列是从左往右构造，和我的方向正好相反。
 * int count = 0;
    public int countArrangement(int N) {
        int[] nums = new int[N];
        for (int i = 1; i <= N; i++)
            nums[i - 1] = i;
        permute(nums, 0);
        return count;
    }
    public void permute(int[] nums, int l) {
        if (l == nums.length) {
            count++;
        }
        for (int i = l; i < nums.length; i++) {
            swap(nums, i, l);
            if (nums[l] % (l + 1) == 0 || (l + 1) % nums[l] == 0)
                permute(nums, l + 1);
            swap(nums, i, l);
        }
    }
    public void swap(int[] nums, int x, int y) {
        int temp = nums[x];
        nums[x] = nums[y];
        nums[y] = temp;
    }
    
 * Approach #3 Backtracking [Accepted] 实际上没方法2快，这两个方法的理论复杂度一致。这两个方法本质上都是通过根据一个违背题目的点排除
 * 其构成的所有全排列来减少计算。这种方法不涉及元素交换，仅通过visited数组来构造一个满足题意的全排列。每次取visited中第一个还没有用的元素
 * 判断其能否放在posth处。
 * Algorithm
   The idea behind this approach is simple. We try to create all the permutations of numbers from 1 to N. We 
   can fix one number at a particular position and check for the divisibility criteria of that number at the
   particular position. But, we need to keep a track of the numbers which have already been considered earlier
   so that they aren't reconsidered while generating the permutations. If the current number doesn't satisfy 
   the divisibility criteria, we can leave all the permutations that can be generated with that number at the 
   particular position. This helps to prune the search space of the permutations to a great extent. We do so 
   by trying to place each of the numbers at each position.
   We make use of a visited array of size N. Here, visited[i] refers to the ith number being already placed/
   not placed in the array being formed till now(True indicates that the number has already been placed).
   We make use of a calculate function, which puts all the numbers pending numbers from 1 to N(i.e. not placed
   till now in the array), indicated by a FalseFalse at the corresponding visited[i] position, and tries to 
   create all the permutations with those numbers starting from the pos index onwards in the current array. 
   While putting the posth number, we check whether the ith number satisfies the divisibility criteria on the 
   go i.e. we continue forward with creating the permutations with the number ii at the pos​th position only if
   the number i and pos satisfy the given criteria. Otherwise, we continue with putting the next numbers at 
   the same position and keep on generating the permutations.
	int count = 0;
    public int countArrangement(int N) {
        boolean[] visited = new boolean[N + 1];
        calculate(N, 1, visited);
        return count;
    }
    public void calculate(int N, int pos, boolean[] visited) {
        if (pos > N)
            count++;
        for (int i = 1; i <= N; i++) {
            if (!visited[i] && (pos % i == 0 || i % pos == 0)) {
                visited[i] = true;
                calculate(N, pos + 1, visited);
                visited[i] = false;
            }
        }
    }
 */
public class question526 {
	public int countArrangement(int N) {
        int[] nums = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            nums[i] = i;
        }
        return permutation(nums, N);
    }
    
    public int permutation(int[] nums, int k) {
        if(k == 1) {
            return 1;
        }
        
        int count = 0;
        for(int i = 1; i <= k; i++) {
            if((nums[i] % k == 0) || (k % nums[i] == 0)) {
                int temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
                count += permutation(nums, k - 1);
                temp = nums[i];
                nums[i] = nums[k];
                nums[k] = temp;
            }
        }
        return count;
    }
    
	public int countArrangement1(int N) {
        int[] nums = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            nums[i] = i;
        }
        return permutation1(nums, N);
    }
    
    public int permutation1(int[] nums, int k) {				//思路1：Brute Force
        if(k == 1) {
            if(helper(nums)) {
            	for(int i = 1; i < nums.length; i++) {
            		System.out.print(nums[i] + " ");
            	}
            	System.out.println("");
                return 1;
            }
            else {
                return 0;
            }
        }
        
        int count = 0;
        for(int i = 1; i <= k; i++) {
        	int temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
            count += permutation1(nums, k - 1);
            temp = nums[i];
            nums[i] = nums[k];
            nums[k] = temp;
        }
        return count;
    }
    
    public boolean helper(int[] nums) {
        for(int i = 1; i < nums.length; i++) {
            if((nums[i] % i != 0) && (i % nums[i] != 0)) {
                return false;
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	System.out.println(new question526().countArrangement(6));
    }
}
