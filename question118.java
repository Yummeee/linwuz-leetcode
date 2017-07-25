/*
 * Pascal's Triangle (Easy)
 * 
 * Given numRows, generate the first numRows of Pascal's triangle.
 */
/*
 * 思路：
 * 1.使用递推法，DP
 * 2.数学公式法
 * 从i=2，即第三行开始，有：
 * Xi,0 = Xi,i =1, Xi,j = Xi,j-1 * (i-j+1)/j
 * 
 * 精简的代码：
 * public List<List<Integer>> generate(int numRows)
{
	List<List<Integer>> allrows = new ArrayList<List<Integer>>();
	ArrayList<Integer> row = new ArrayList<Integer>();
	for(int i=0;i<numRows;i++)
	{
		row.add(0, 1);
		for(int j=1;j<row.size()-1;j++)
			row.set(j, row.get(j)+row.get(j+1));
		allrows.add(new ArrayList<Integer>(row));
	}
	return allrows;
	
}
 */
import java.util.*;
public class question118 {
	public List<List<Integer>> generate1(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows == 0) {
            return triangle;
        }
        
        ArrayList<Integer> row = new ArrayList<>();					//这段代码可以省略，然后再while循环中判断是不是开头结尾
        row.add(1);
        triangle.add(row);
        if(numRows == 1) {
            return triangle;
        }
        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        triangle.add(row);
        if(numRows == 2) {
            return triangle;
        }
        
        int i = 2;
        while(i < numRows) {
            row = new ArrayList<>();
            row.add(1);
            int j = 1;
            //int pre = 1;
            while(j < i) {
                row.add(row.get(j-1)*(i-j+1)/j);
                j++;
            }
            row.add(1);
            triangle.add(row);
            i++;
        }
        return triangle;
    }
	
	public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if(numRows == 0) {
            return triangle;
        }
        
        ArrayList<Integer> row = new ArrayList<>();
        row.add(1);
        triangle.add(row);
        if(numRows == 1) {
            return triangle;
        }
        row = new ArrayList<>();
        row.add(1);
        row.add(1);
        triangle.add(row);
        if(numRows == 2) {
            return triangle;
        }
        
        int i = 2;
        while(i < numRows) {
            row = new ArrayList<>();
            row.add(1);
            int j = 1;
            while(j < i) {
                row.add(triangle.get(i-1).get(j-1)+triangle.get(i-1).get(j));
                j++;
            }
            row.add(1);
            triangle.add(row);
            i++;
        }
        return triangle;
    }
}
