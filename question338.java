/*
 * Counting Bits (Medium)
 * 
 * Given a non negative integer number num. For every numbers i in the range 0 ≤ i ≤ num calculate the number of 1's in 
 * their binary representation and return them as an array.
 * 
 * Example:
   For num = 5 you should return [0,1,1,2,1,2].
 * Follow up:
   It is very easy to come up with a solution with run time O(n*sizeof(integer)). But can you do it in linear time O(n) /possibly in a single pass?
   Space complexity should be O(n).
   Can you do it like a boss? Do it without using any builtin function like __builtin_popcount in c++ or in any other language.
 */
/*
 * 思路：
 * 1.Brute Force，对每个数通过与1和异或算出位数，时间复杂度O(n*sizeof(integer))，空间复杂度O(n)；
 * 2.DP问题，可以发现一个动态规划公式，假设已知ans[i-1]，记i-1从最低位开始连续1的个数为n，则ans[i] = ans[i-1] - n + 1;  (n可以为0-log(i-1))
 * 3.根据题目提示：找2-3、4-7、8-15，不难发现1的个数的规律：每一区间的前半部和前一个区间的数的1的个数一致，后半部则是在前半部的值上加1；
 * 4.仍用数学规律，从1开始，遇到偶数时，其1的个数和该偶数除以2得到的数字的1的个数相同，遇到奇数时，其1的个数等于该奇数除以2得到的数字的1的个数再加1，等价于f[i] = f[i / 2] + i % 2.此规律可以直观的想出来
 * 5.找规律可发现，ans[i&(i-1)] + 1 = ans[i];
 * 严格来说，3、4、5也属于动态规划解法。比我的DP更彻底，更简单。
 */
public class question338 {	
	public int[] countBits(int num) {               //思路5
        int[] ans = new int[num + 1];
        for(int i = 1; i <= num; i++) {
        	ans[i] = ans[i & (i-1)] + 1;
        }
        return ans;
    }
	
	public int[] countBits4(int num) {               //思路4，Math找规律
        int[] ans = new int[num + 1];
        if(num == 0) {
            return ans;
        }
        ans[1] = 1;
        for(int i = 2; i <= num; i++) {
        	if(i % 2 == 0) {
        		ans[i] = ans[i>>1];
        	}
        	else {
        		ans[i] = ans[i>>1] + 1;
        	}
        }
        return ans;
    }
	
	public int[] countBits3(int num) {               //思路3，Math找规律
        int[] ans = new int[num + 1];
        if(num == 0) {
            return ans;
        }
        ans[1] = 1;
        int i = 2, k = 1;
        while(i <= num) {
            for(i = (int)Math.pow(2, k); i < (int)Math.pow(2, k+1); i++) {
                if(i > num) {
                    break;
                }
                int half = (int)(Math.pow(2, k+1) - Math.pow(2, k)) >> 1;
                if(i < (Math.pow(2, k) + half)) {		//当前区间前半部
                    ans[i] = ans[i - half];
                }
                else {									//当前区间后半部
                    ans[i] = ans[i - half] + 1;
                }
            }
            k++;
        }
        return ans;
    }
	
	public int[] countBits2(int num) {               //思路2，DP
        int[] ans = new int[num + 1];
        int least_ones = 0, cur_num;
        for(int i = 1; i <= num; i++) {
            ans[i] = ans[i-1] - least_ones + 1;
            if(least_ones != 0) {			//从最低位开始有连续0，加1后最低位一定是0
                least_ones = 0;
            }
            else {
                cur_num = i;
                while((cur_num & 1) == 1) {
                    least_ones++;
                    cur_num >>= 1;
                }
            }
        }
        return ans;
    }
	
	public int[] countBits1(int num) {				//思路1
        int[] ans = new int[num + 1];
        int count, cur_num;
        for(int i = 0; i <= num; i++) {
            count = 0;
            cur_num = i;
            while(cur_num > 0) {
                if((cur_num & 1) == 1) {
                    count++;
                }
                cur_num >>= 1;
            }
            ans[i] = count;
        }
        return ans;
    }
}
