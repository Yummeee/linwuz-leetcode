/*
 * Pascal's Triangle II (Easy)
 * 
 * Given an index k, return the kth row of the Pascal's triangle.
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 */
/*
 * 思路：类似Pascal's Triangle I，可以用公式解，或者递推。注意公式的乘法可能导致溢出！！！错了三次了已经！！！！不长记性
 * 
 * 递推的方法相当巧妙，根本不需要用两个List来实现，只需要在一个List上反复递推。从后往前推可以保证上一层的数据在无用前不会丢失！
 * 也可以从前往后推，但是每次需要在开头插个1而不是结尾插个1，效率会低一些
 * public List<Integer> getRow(int rowIndex) {
	List<Integer> list = new ArrayList<Integer>();
	if (rowIndex < 0)
		return list;

	for (int i = 0; i < rowIndex + 1; i++) {
		list.add(0, 1);
		for (int j = 1; j < list.size() - 1; j++) {
			list.set(j, list.get(j) + list.get(j + 1));
		}
	}
	return list;
}
 */
import java.util.*;
public class question119 {
	public List<Integer> getRow1(int rowIndex) {
        ArrayList<Integer> ans = new ArrayList<>();
        
        int j = 0;
        while(j <= rowIndex) {
            if(j==0 || j==rowIndex) {
                ans.add(1);
            }
            else {
                ans.add((int)((long)ans.get(j-1)*(rowIndex-j+1)/j));			//乘法有可能会溢出...尼玛，第一道题可没有溢出
            }
            j++;
        }
        return ans;
    }
	
	public List<Integer> getRow(int rowIndex) {								//这个方法相当巧妙，自始至终只用到一个List，每次循环从后往前覆盖掉上一层的结果，但是不会使信息丢失
        ArrayList<Integer> ans = new ArrayList<>();
        
        int i = 0;
        while(i <= rowIndex) {
        	ans.add(1);
        	int j = i-1;
        	while(j > 0) {
        		ans.set(j, ans.get(j-1)+ans.get(j));
        		j--;
        	}
        	i++;
        }
        return ans;
	}
}
