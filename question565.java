/*
 * Array Nesting (Medium)
 * 
 * A zero-indexed array A consisting of N different integers is given. The array contains all integers in the 
 * range [0, N - 1].
 * Sets S[K] for 0 <= K < N are defined as follows:
 * S[K] = { A[K], A[A[K]], A[A[A[K]]], ... }.
 * Sets S[K] are finite for each K and should NOT contain duplicates.
 * Write a function that given an array A consisting of N integers, return the size of the largest set S[K] for
 * this array.
 * 
 * Example 1:
 * Input: A = [5,4,0,3,1,6,2]
   Output: 4
   Explanation: 
   A[0] = 5, A[1] = 4, A[2] = 0, A[3] = 3, A[4] = 1, A[5] = 6, A[6] = 2.
   One of the longest S[K]:
   S[0] = {A[0], A[5], A[6], A[2]} = {5, 6, 2, 0}
 *  
 * Note:
   N is an integer within the range [1, 20,000].
   The elements of A are all distinct.
   Each element of array A is an integer within the range [0, N-1].  
 */
/*
 * 思路：这道题实际上是一个DFS遍历！！！
 * 1.利用一个visited数组来记录哪些元素已经被访问过，在一个set中的元素永远会属于一个set，从一个set的某一个元素出发，直至重新访问回其本身，一
 * 定走过了相同的一个set。利用这一点性质，我们可以遍历一遍所有元素即可找出最大的set（即loop）。每次找一个仍未被访问的元素，从起开始构建
 * 一个新的set，这个set一定不会和之前的set有交集，否则这两个set可以合二为一了。利用hashset判断是否形成了回路，一旦形成，重新找另一个
 * 未被访问的元素构建新set，以此往复直至所有元素均被访问。最大的set在这过程中已经求出来。时间复杂度O(n)，空间复杂度O(n)。这种方法hashset其实
 * 可以不要，直接看什么时候又访问回当前set的起始元素即可。
 * 2.思路1可以精简，利用数组元素本身的性质0...N-1，类比之前那种找哪个元素缺失的题，通过置反原元素来判断是否访问过一个元素。可以使得extra
 * space降为O(1)，效率也会增高，但是时间复杂度理论值不变。实际上直接在原数组上将所有访问过的元素置-1即可...
 */
import java.util.*;
public class question565 {
	public int arrayNesting(int[] nums) {          //思路2，即思路1改进
        int max_size = Integer.MIN_VALUE;
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] >= 0) {                      //also includes 0
                int cur_set_size = 0;
                int cur_number = i;
                int next_number = -1;
                while(nums[cur_number] >= 0) {
                    next_number = nums[cur_number];
                    nums[cur_number] = -1;
                    cur_number = next_number;
                    cur_set_size++;
                }
                max_size = Math.max(max_size, cur_set_size);
            }
        }
        return max_size;
    }
	
	public int arrayNesting1(int[] nums) {			//思路1
        //since N is in the range of [1, 20000], there is no need to check the null or empty input
        int[] visited = new int[nums.length];
        int max_length = Integer.MIN_VALUE;            //0 is also Ok since the loop is no shorter than 1
        HashSet<Integer> set;
        for(int i = 0; i < visited.length; i++) {
            if(visited[i] == 0) {           //find an integer that has not been visited in a S[k] yet, it could be a start of a new S[k]
                set = new HashSet<>();
                int cur_number = i;
                while(set.add(cur_number)) {
                    visited[cur_number] = 1;
                    cur_number = nums[cur_number];
                }
                max_length = Math.max(max_length, set.size());
            }
        }
        return max_length;
    }
}
