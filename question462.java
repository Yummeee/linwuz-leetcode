/*
 * Minimum Moves to Equal Array Elements II (Easy)
 * 
 * Given a non-empty integer array, find the minimum number of moves required to make all array elements equal, 
 * where a move is incrementing a selected element by 1 or decrementing a selected element by 1.
 * You may assume the array's length is at most 10,000.
 * Example:
 * Input: [1,2,3]
 * Output: 2
 * Explanation:
   Only two moves are needed (remember each move increments or decrements one element):
   [1,2,3]  =>  [2,2,3]  =>  [2,2,2]
 */
/*
 * 思路：类似meeting point problem
 * 1.Brute Force, 对于[min(nums), max(nums)]范围内，穷举每一个值，计算移动到该值的总步数。时间复杂度O((max-min+1)*n);
 * 2.寻找nums的中位数，中位数即为目标。实际上数组长度无论是不是偶数，只要是nums[length/2]移动的距离就是一样的，时间复杂度O(nlogn)
 * 可以这样想，n1、n2...中位数、...、nk，对于n1和nk同时移动到目标点的最短距离是nk-n1,对于nk-1和n2，最短移动的距离是nk-1-n2，以此类推，不难发现，除了移动
 * 到中位数的位置，其余情况下移动的距离一定是不小于nk-n1 + nk-1-n2 + ...
 * public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int i = 0, j = nums.length-1;
        int count = 0;
        while(i < j){
            count += nums[j]-nums[i];
            i++;
            j--;
        }
        return count;
    }
 * 3.找中位数或可利用快速排序的思想将时间复杂度将至O(n)，但是这种方法在最坏情况下是O(n^2)
 * public int minMoves2(int[] A) {
    int sum = 0, median = quickselect(A, A.length/2+1, 0, A.length-1);
    for (int i=0;i<A.length;i++) sum += Math.abs(A[i] - median);
    return sum;
}

public int quickselect(int[] A, int k, int start, int end) {				//利用快速排序的思想找到中位数
    int l = start, r = end, pivot = A[(l+r)/2];
    while (l<=r) {
        while (A[l] < pivot) l++;
        while (A[r] > pivot) r--;
        if (l>=r) break;
        swap(A, l++, r--);
    }
    if (l-start+1 > k) return quickselect(A, k, start, l-1);
    if (l-start+1 == k && l==r) return A[l];
    return quickselect(A, k-r+start-1, r+1, end);
}

public void swap(int[] A, int i, int j) {
    int temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}
 */
import java.util.*;
public class question462 {
	public int minMoves(int[] nums) {               //下面方法的精简
        Arrays.sort(nums);
        int med = 0, moves = 0;
        med = nums[nums.length/2];
        for(int i = 0; i < nums.length/2; i++) {
            moves += med - nums[i];
        }
        for(int i = nums.length/2; i < nums.length; i++) {
            moves += nums[i] - med;
        }
        return moves;    
    }
	
	public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        int med = 0, moves = 0;
        if((nums.length & 1) == 0) {
            med = (nums[nums.length/2 - 1] + nums[nums.length/2]) / 2;
        }
        else {
            med = nums[nums.length/2];
        }
        
        for(int i = 0; i < nums.length/2; i++) {
            moves += med - nums[i];
        }
        for(int i = nums.length/2; i < nums.length; i++) {
            moves += nums[i] - med;
        }
        return moves;    
    }
}
