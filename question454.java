/*
 * 4Sum II (Medium)
 * 
 * Given four lists A, B, C, D of integer values, compute how many tuples (i, j, k, l) there are such that A[i]
 * + B[j] + C[k] + D[l] is zero.
 * 
 * To make problem a bit easier, all A, B, C, D have same length of N where 0 ≤ N ≤ 500. All integers are in the
 * range of -228 to 228 - 1 and the result is guaranteed to be at most 231 - 1.
 * 
 * Example:
 * Input:
   A = [ 1, 2]
   B = [-2,-1]
   C = [-1, 2]
   D = [ 0, 2]
   Output:
   2
   Explanation:
   The two tuples are:
   1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
   2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
/*
 * 思路：
 * 1.参考题18，4Sum，将4个数组合为一个数组，进行4Sum计算，且由于下标均独一无二的，所以可以出现重复四元组，时间复杂度O(n^3)，肯定超时；如果
 * 选择分别将A、B、C、D排序，然后利用two pointers遍历C和D会出错，因为只有C的最大元素小于D的最小元素时才能遍历到所有数，除非对C和D排序后的结果
 * 做一个merge，这样时间复杂度是O(n^2 * logn)，有了一定优化（从某种程度上说也是二分法吧）。
 * 2.由于此题实际上可以找重复元组，因此我们可以将时间复杂度压缩到O(n^2)。首先计算出A与B、C与D的所有可能和，将结果放入两个HashMap中，记录
 * （和，和的出现次数）的映射关系。随后对这两个HashMap的结果进行一次twoSum即可（用其中一个map作为twoSum用到的hashmap）
 * 
 * 思路2改进：
 * 实际上没必要在一开始同时计算两组（A与B、C与D）各自的和，只需计算一组。随后直接用该组得到的结果作为twoSum的hashmap, 对另外两组进行一个
 * 二层遍历，在计算这两组的所有的和的同时进行twoSum，这样就可以少做一次二层遍历。
 * 
 * 如果用二分法，时间复杂度就是O(n^2 * log(n^2))，对A与B之和的结果进行排序后，在找另一半时用二分法。
 * 
 * 思路2的思考过程：一种最brute force的方法是四重循环， 时间复杂度为O(n^4). 稍微优化一下将D数组的元素放到hash表中，第四个元素的查找即可达到O(1)，这样总的时间复杂度为O(n^3), 空间复杂度为O(n)。再进一步可以两个两个的求元素和，也就是先求出A,B数组的任意两个元素和放到hash表中，然后在计算C, D任意两个元素和的时候查找一下hash看能不能找到C, D这两个元素的负数，如果可以找到说明这四个元素相加是为0的。还需要注意的重复元素，在做hash的时候value为个数即可。这样时间复杂度降为O(n^2), 空间复杂度为O(n^2).
 */
import java.util.*;
public class question454 {
	public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {				//思路2改进
        if(A == null || A.length == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map = fillMap(A, B);
        int res = 0;
        for(int i = 0; i < C.length; i++) {
            for(int j = 0; j < D.length; j++) {
                int sum = C[i] + D[j];
                res += map.getOrDefault(-sum, 0); 
            }
        }
        return res;
    }
	
	public int fourSumCount1(int[] A, int[] B, int[] C, int[] D) {					//思路2
        if(A == null || A.length == 0) {
            return 0;
        }
        
        HashMap<Integer, Integer> map1 = fillMap(A, B);
        HashMap<Integer, Integer> map2 = fillMap(C, D);
        int res = 0;
        for(Map.Entry<Integer, Integer> entry : map2.entrySet()) {
            Integer temp = map1.get(0 - entry.getKey());
            if(temp != null) {
                res += temp.intValue() * entry.getValue();
            }
        }
        return res;
    }
    
    public HashMap<Integer, Integer> fillMap(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums1.length; i++) {
            for(int j = 0; j < nums2.length; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        return map;
    }
}
