/*
 * Guess Number Higher or Lower (Easy)
 * 
 * We are playing the Guess Game. The game is as follows:
 * I pick a number from 1 to n. You have to guess which number I picked.
 * Every time you guess wrong, I'll tell you whether the number is higher or lower.
 * You call a pre-defined API guess(int num) which returns 3 possible results (-1, 1, or 0):
 * -1 : My number is lower
    1 : My number is higher
    0 : Congrats! You got it!
 */

/*
 * 思路：
 * 1.Brute Force，从1试到n-1，超时
 * 2.二分搜索(我的方法)，注意计算mid时的溢出，发现还是用0.5*的方法默认转成double最快，比先转long再转int要快，后者会超时
 * int mid = low + (high - low) / 2;这样也可以避免溢出
 * 
 * 3.Ternary Search [Accepted] 就是分成3段的二分搜索拓展而已，时间复杂度变成O(log3(n))，根据编辑分析，效果不如二分搜索
 * In Binary Search, we choose the middle element as the pivot in splitting. In Ternary Search, we choose two pivots (say 
 * m1 and m2) such that the given range is divided into three equal parts. If the required number num is less than m1 then we
 * apply ternary search on the left segment of m1. If num lies between m1 and m2, we apply ternary search between m1 and m2. 
 * Otherwise we will search in the segment right to m2.
 * public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid1 = low + (high - low) / 3;
            int mid2 = high - (high - low) / 3;
            int res1 = guess(mid1);
            int res2 = guess(mid2);
            if (res1 == 0)
                return mid1;
            if (res2 == 0)
                return mid2;
            else if (res1 < 0)
                high = mid1 - 1;
            else if (res2 > 0)
                low = mid2 + 1;
            else {
                low = mid1 + 1;
                high = mid2 - 1;
            }
        }
        return -1;
    }
 */


/* The guess API is defined in the parent class GuessGame.
   @param num, your guess
   @return -1 if my number is lower, 1 if my number is higher, otherwise return 0
      int guess(int num); */
/*
public class Solution extends GuessGame {
   public int guessNumber(int n) {
        int low = 1, high = n;
        while(low <= high) {
            int mid = (int)(0.5*high+0.5*low);      //avoid overflow by using double
            //int mid = low + (high - low) / 2;
            int result = guess(mid);
            if(result == 0) {
                return mid;
            }
            else if(result == -1) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }
        return low;
    }
}
*/