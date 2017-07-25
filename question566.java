/*
 * Reshape the Matrix (Easy)
 * 
 * In MATLAB, there is a very useful function called 'reshape', which can reshape a matrix into a new one with different size
 * but keep its original data.
 * You're given a matrix represented by a two-dimensional array, and two positive integers r and c representing the row 
 * number and column number of the wanted reshaped matrix, respectively.
 * The reshaped matrix need to be filled with all the elements of the original matrix in the same row-traversing order as 
 * they were.
 * If the 'reshape' operation with given parameters is possible and legal, output the new reshaped matrix; Otherwise, output 
 * the original matrix.
 */
/*
 * 思路：
 * 我的方法就是遍历一遍原数组即可，时间复杂度O(m*n)，空间复杂度O(m*n)，空间复杂度就是结果;
 * 
 * 编辑方法：
 * Approach #1 Using queue [Accepted]，就是先将原数组的元素放入一个队列里，时间复杂度O(2*m*n)，空间复杂度O(2*m*n)
 * Algorithm
   The simplest method is to extract all the elements of the given matrix by reading the elements in a row-wise fashion. In this implementation, we use a queue to put the extracted elements. Then, we can take out the elements of the queue formed in a serial order and arrange the elements in the resultant required matrix in a row-by-row order again.
   The formation of the resultant matrix won't be possible if the number of elements in the original matrix isn't equal to the number of elements in the resultant matrix.
 * public int[][] matrixReshape(int[][] nums, int r, int c) {
        int[][] res = new int[r][c];
        if (nums.length == 0 || r * c != nums.length * nums[0].length)
            return nums;
        int count = 0;
        Queue < Integer > queue = new LinkedList < > ();
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                queue.add(nums[i][j]);
            }
        }
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                res[i][j] = queue.remove();
            }
        }
        return res;
    }
    
 * Approach #2 Without using extra Space [Accepted]，就是我的方法
 * 
 * Approach #3 Using division and modulus [Accepted]
 * 思路是利用二维数组在内存中的存储特性（即实际上是一个一维数组，nums[i][j] = nums[m*i+j]），使用一个变量count统计目前访问了多少个元素，
 * ans[count/c][count%c]就是下一个元素应该放的位置，就不用每个循环都判断了
 * public int[][] matrixReshape(int[][] nums, int r, int c) {
        int[][] res = new int[r][c];
        if (nums.length == 0 || r * c != nums.length * nums[0].length)
            return nums;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums[0].length; j++) {
                res[count / c][count % c] = nums[i][j];
                count++;
            }
        }
        return res;
    }
 */
public class question566 {
	public int[][] matrixReshape(int[][] nums, int r, int c) {
        if(r*c != nums.length*nums[0].length || r == nums.length) {
            return nums;
        }
        
        int[][] ans = new int[r][c];
        int s = 0, t = 0;
        for(int i=0; i<nums.length; i++) {
            for(int j=0; j<nums[0].length; j++) {
                if(t == c) {
                    s++;
                    t = 0;
                }
                ans[s][t] = nums[i][j];
                t++;
            }
        }
        return ans;
    }
}
