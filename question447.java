/*
 * Number of Boomerangs (Easy)
 * 
 * Given n points in the plane that are all pairwise distinct, a "boomerang" is a tuple of points (i, j, k) such that the distance between i 
 * and j equals the distance between i and k (the order of the tuple matters)(导致解的数目一定是2的倍数).
 * Find the number of boomerangs. You may assume that n will be at most 500 and coordinates of points are all in the range [-10000, 10000] 
 * (inclusive).
 * 
 * Example:
 * Input: [[0,0],[1,0],[2,0]]
 * Output: 2
 * Explanation: The two boomerangs are [[1,0],[0,0],[2,0]] and [[1,0],[2,0],[0,0]]
 */
/*
 * 思路：
 * 1.Brute Force，时间复杂度O(n^3)
 * 2.使用HashMap, 以distance作为键值，该distance出现次数作为value(每一次出现都代表一个点对)，双重循环遍历所有点，计算任意两点间距离。因为i=p时,任意的j=q满足
 * distance(p,q1)和distance(p,q2)相等一定是符合题意的一个boomrange。每次计算更新该distance的value，对于每个点i，循环一遍j后的map中存的就是以点i为第一个点的所有符合
 * 题意的情况。循环遍历map的value值，对于每个distance对应的value值，对应的boomerang数计算公式为C(value,2)*2，乘2是因为顺序敏感，最后计算公式即为value(value-1)。累加
 * 即可，时间复杂度O(n^2)，空间复杂度O(n)
 */
import java.util.*;
public class question447 {
	public int numberOfBoomerangs(int[][] points) {
        if(points.length < 3) {
            return 0;
        }
        
        HashMap<Integer, Integer> distance_map = new HashMap<>();
        int ans = 0;
        for(int i=0; i<points.length; i++) {
            for(int j=0; j<points.length; j++) {
                if(i == j) {
                    continue;
                }
                int dist = distance(points[i], points[j]);
                distance_map.put(dist, distance_map.getOrDefault(dist, 0)+1);
            }
            for(int num : distance_map.values()) {
                ans += num*(num-1);
            }
            distance_map.clear();				//不能忘记清0！！！！
        }
        return ans;
    }
    public int distance(int[] node1, int[] node2) {
        int dist = (node2[0]-node1[0])*(node2[0]-node1[0]) + (node2[1]-node1[1])*(node2[1]-node1[1]);
        return dist;
    }
}
