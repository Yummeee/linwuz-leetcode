/*
 * Arithmetic Slices (Medium)
 * 
 * A sequence of number is called arithmetic if it consists of at least three elements and if the difference between any two 
 * consecutive elements is the same.
 * 
 * For example, these are arithmetic sequence:
 * 1, 3, 5, 7, 9
   7, 7, 7, 7
   3, -1, -5, -9
 * The following sequence is not arithmetic.
 * 1, 1, 2, 5, 7
 * 
 * A zero-indexed array A consisting of N numbers is given. A slice of that array is any pair of integers (P, Q) such that 
 * 0 <= P < Q < N.
 * A slice (P, Q) of array A is called arithmetic if the sequence:
   A[P], A[p + 1], ..., A[Q - 1], A[Q] is arithmetic. In particular, this means that P + 1 < Q. (因为slice sequence要不小于3个数)
 * The function should return the number of arithmetic slices in the array A.
 * 
 * Example:
   A = [1, 2, 3, 4]
   return: 3, for 3 arithmetic slices in A: [1, 2, 3], [2, 3, 4] and [1, 2, 3, 4] itself.
 */
/*
 * 思路：
 * 1.Brute Force，找到A的所有slice然后判断其是否是arithmetic的。时间复杂度O(n^3)，空间复杂度O(1)
 * 2.Two pointes，维持一个滑动窗口，滑动窗口若大小不小于3，则其中一定含有的是一个arithmetic sequence，记窗口大小为k，则其中包含的子arithmetic
 * sequences个数为长度为3的串的个数，4的串的个数，5的串的个数...之和，等于(k-3+1) + (k-4+1) + ... + 1，若right指针遇到一个元素使得滑动窗口的串不再
 * 是一个arithmetic sequence，则left = right - 1，重置滑动窗口的大小为2，重新开始寻找。时间复杂度O(n)，空间复杂度O(1)
 * 
 * 3.DP，这道题的DP解法也是参考的我的two pointers解法，通过思路2我们已经知道，对于一个长度为3的arithmetic sequence，其包含的所有子序列为1；如果长度
 * 为4，则是1+2=3；如果长度为5，则是1+2+3=6；长度为6则是1+2+3+4=10...依次类推。所以对于dp[i]，表示0...i的元素含有的所有arithmetic sequences，设
 * 置一个add变量表示对于当前长度的arithmetic sequence，已经加到了1+2+3+...+add，则dp[i] = dp[i-1] + add。如果A[i]破坏了这一序列的连续性，则add
 * 置0。dp数组可以直接由一个count变量表示，时间复杂度O(n)，空间复杂度O(1)。和two pointers的区别是，DP方法并不是一次计算一个局部最长的arithmetic sequence
 * 包含的子序列个数，而是不断累加。两个方法的本质是一样的。
 */
public class question413 {
	public int numberOfArithmeticSlices(int[] A) {             //DP，和two pointers本质一样
        if(A == null || A.length < 3) {
            return 0;
        }
        int count = 0, add = 0;
        for(int i = 2; i < A.length; i++) {
            if(A[i] - A[i-1] == A[i-1] - A[i-2]) {
                count += ++add; 
            }
            else {
                add = 0;
            }
        }
        return count;
    }
	
	public int numberOfArithmeticSlices1(int[] A) {					//two pointers
        if(A == null || A.length < 3) {
            return 0;
        }
        
        int i = 0, j = 2, pre = A[1] - A[0], count = 0;
        while(j <= A.length) {
        	if(j < A.length && A[j] - A[j-1] == pre) {
                j++;
            }
            else {
                if(j - i >= 3) {                //不少于三个数，j目前已经比有效窗口多了1，所以j在计算时值要减1。同时兼容了A的末尾是一个arithmetic sequence的情况
                    count += (int)(0.5 * (j - i - 1) * (j - i - 2));
                }
                if(j < A.length) {
                	pre = A[j] - A[j-1]; 
                }
                i = j - 1;						//left = right - 1，缩小窗口大小到2
            	j++;
            }
        }
       
        return count;
    }
	public static void main(String[] args) {
		int[] A = {1,2,3,8,9,10};
		System.out.println(new question413().numberOfArithmeticSlices(A));
	}
}
