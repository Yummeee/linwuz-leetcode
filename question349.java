/*
 * Intersection of Two Arrays (Easy)
 * 
 * Given two arrays, write a function to compute their intersection.
 * Example:
   Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2]
 * Note:
   Each element in the result must be unique.
   The result can be in any order.
 */

/*
 * 思路：
 * 1.使用HashSet，循环遍历两个数组即可，时间复杂度O(m+n)
 * public int[] intersection(int[] nums1, int[] nums2) {			//一个人给的代码！基本和我的一致，主要看这里如何取出Set中的值的！
        Set<Integer> set = new HashSet<>();
        Set<Integer> intersect = new HashSet<>();
        for (int i = 0; i < nums1.length; i++) {
            set.add(nums1[i]);
        }
        for (int i = 0; i < nums2.length; i++) {
            if (set.contains(nums2[i])) {
                intersect.add(nums2[i]);
            }
        }
        int[] result = new int[intersect.size()];
        int i = 0;
        for (Integer num : intersect) {							//set中的值取出！或者用interset.iterator()，得到Iterator类型变量it，然后在it.next()不为空时循环
            result[i++] = num;
        }
        return result;
    }
 * 2.Brute Force，时间复杂度O(m*n)
 * 3.先排序，再用两个指针依次比较，时间复杂度O(nlogn)
 * public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set = new HashSet<>();
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0;
        int j = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                set.add(nums1[i]);
                i++;
                j++;
            }
        }
        int[] result = new int[set.size()];
        int k = 0;
        for (Integer num : set) {
            result[k++] = num;
        }
        return result;
    }
 * 4.对其中一个排序，然后用二分搜索查找另一个数组中的每一个元素，时间复杂度O(nlogn)
 */
import java.util.*;
public class question349 {
	public int[] intersection(int[] nums1, int[] nums2) {
        int[] ans = {};
        if(nums1.length==0 || nums2.length==0) {
            return ans;
        }
        
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> temp = new ArrayList<>();
        for(int i=0; i<nums1.length; i++) {
            set.add(nums1[i]);
        }
        for(int i=0; i<nums2.length; i++) {
            if(set.contains(nums2[i])) {
            	set.remove(nums2[i]); 			//must do this, or intersection may have same number
                temp.add(nums2[i]);
            }
        }
        ans = new int[temp.size()];
        for(int i=0; i<ans.length; i++) {
        	ans[i] = temp.get(i);
        }
        
        return ans;   
    }
}
