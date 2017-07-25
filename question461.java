/*
 * Hamming Distance (Easy)
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Given two integers x and y, calculate the Hamming distance.
 * Note:
   0 ≤ x, y < 231
e.g., 
   Input: x = 1, y = 4
   Output: 2
   Explanation:
   1   (0 0 0 1)
   4   (0 1 0 0)
 */

/*
 * 我的思路：
 * 直接x与y异或可以得出多少位不同
 * 然后xor右移或者1左移并相与，结果不唯一说明该位不为0，即不同位加1
 */
public class question461 {
	public int hammingDistance(int x, int y) {
        int xor  = x^y;
        int count = 0;
        while(xor != 0) {
            count += xor&1;
            xor = xor>>1;
        }
        return count;
    }
}
