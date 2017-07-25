/*
 * Diagonal Traverse (Medium)
 * 
 * Given a matrix of M x N elements (M rows, N columns), return all elements of the matrix in diagonal order as
 * shown in the below image.
 * 
 * Note:
   The total number of elements of the given matrix will not exceed 10,000.
 */
/*
 * 思路：
 * 简单的遍历数组操作，分为向上和向下遍历两种情况，每种情况下还要考虑两种极端情况，时间复杂度O(n^2).
 * 
 * 更简洁的写法，分成了四种情况，分别碰数组的四个边界：
 * public int[] findDiagonalOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0) return new int[0];
        int m = matrix.length, n = matrix[0].length;
        
        int[] result = new int[m * n];
        int row = 0, col = 0, d = 1;

        for (int i = 0; i < m * n; i++) {
            result[i] = matrix[row][col];
            row -= d;													//利用d = 1/-1节省分情况讨论
            col += d;
            
            if (row >= m) { row = m - 1; col += 2; d = -d;}				//之前多走了一步，还原，col+2
            if (col >= n) { col = n - 1; row += 2; d = -d;}
            if (row < 0)  { row = 0; d = -d;}
            if (col < 0)  { col = 0; d = -d;}
        }
        
        return result;
    }
 */
public class question498 {
	public int[] findDiagonalOrder(int[][] matrix) {
		if(matrix == null) {
            return null;
        }
        if(matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        
        int[] res = new int[matrix.length * matrix[0].length];
        int direction = 0, count = 0, i = 0, j = 0;         //direction == 0 means read up while direction == 1 means read down
        while(count < res.length) {
            res[count] = matrix[i][j];
            if(direction == 0) {                            //update i, j
                if(i == 0 || j == matrix[0].length - 1) {   //reach fringe of the matrix, there are two cases
                    if(j < matrix[0].length - 1) {
                        j++;
                    }
                    else {
                        i++;
                    }
                    direction = 1;
                }
                else {
                    i--;
                    j++;
                }
            }
            else {
                if(i == matrix.length - 1 || j == 0) {      //reach fringe of the matrix, there are two cases
                    if(i < matrix.length - 1) {
                        i++;
                    }
                    else {
                        j++;
                    }
                    direction = 0;
                }
                else {
                    i++;
                    j--; 
                }
            }
            count++;
        }
        return res;
    }
}
