/*
 * Total Hamming Distance (Medium)
 * 
 * The Hamming distance between two integers is the number of positions at which the corresponding bits are different.
 * Now your job is to find the total Hamming distance between all pairs of the given numbers.
 * 
 * Example:
   Input: 4, 14, 2
   Output: 6
   Explanation: In binary representation, the 4 is 0100, 14 is 1110, and 2 is 0010 (just
   showing the four bits relevant in this case). So the answer will be:
   HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance(14, 2) = 2 + 2 + 2 = 6.
 * 
 * Note:
   Elements of the given array are in the range of 0 to 10^9
   Length of the array will not exceed 10^4.
 */
/*
 * 思路：
 * 1.Brute Force，计算每一个元素对间的海明距离，时间复杂度O(n^2)。超时
 * 2.并行思想，从计算每两个数之间的海明距离这种串行方法转变为统计n个数的第i位在计算海明码是的贡献值是多少，这样只用进行32次即可。例如，对于
 * 第i位有k个1和n-k个0，因此这一位可以贡献k*(n-k)个海明码中的1。时间复杂度O(32*n)
 */
public class question477 {
	public int totalHammingDistance(int[] nums) {
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int res = 0;
        for(int i = 0; i < 32; i++) {
            int count = 0;
            for(int j = 0; j < nums.length; j++) {
                count += (nums[j] >> i) & 1;             //count how many 1s there in these numbers' i bit
            }
            res += (nums.length - count) * count;
        }
        return res;
	}
	
	public int totalHammingDistance1(int[] nums) {				//思路1
        if(nums == null || nums.length == 0) {
            return 0;
        }
        
        int res = 0;
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                res += bitCount(nums[i] ^ nums[j]);
            }
        }
        return res;
    }
    
	public int bitCount(int x) {			//arithmatic shift because input is always positive
        x = ((x >> 3) & 0x11111111) + ((x >> 2) & 0x11111111) + ((x >> 1) & 0x11111111) + (x & 0x11111111);
        x = (x >> 4) + x;
        x = ((x >> 8) & 0x000f000f) + (x & 0x000f000f);
        x = (x >> 16) + x;
        return x & 0x3f;
    }
}
