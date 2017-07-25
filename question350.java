/*
 * Intersection of Two Arrays II (Easy)
 * 
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2]
 * Note:
   Each element in the result should appear as many times as it shows in both arrays.
   The result can be in any order.
 *
 * Follow up:
   What if the given array is already sorted? How would you optimize your algorithm?
   What if nums1's size is small compared to nums2's size? Which algorithm is better?
   
   What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
   If only nums2 cannot fit in memory, put all elements of nums1 into a HashMap, read chunks of array that fit into the memory, and record the intersections.
   If both nums1 and nums2 are so huge that neither fit into the memory, sort them individually (external sort), then read (let's say) 2G (or 2) of each into memory and then using the 2 pointer technique, then read 2G (or 2) more from the array that has been exhausted. Repeat this until no more data to read from disk.
 */

/*
 * 思路：基本同349一致
 * 1.Brute Force
 * 2.使用HashMap（不能再使用HashSet），O(n)
 * 3.排序后，双指针，O(n)
 * 4.此题不太适合用排序一个数组再二分搜索。注意：由于重复元素也要算，但二分搜索只能返回第一个找到的index
 */
import java.util.*;
public class question350 {
	public int[] intersect1(int[] nums1, int[] nums2) {		//排序双指针
        int[] result = {};
        if(nums1.length==0 || nums2.length==0) {
            return result;
        }
        
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int i = 0, j = 0;
        List<Integer> inter = new ArrayList<>();
        while(i<nums1.length && j<nums2.length) {
            if(nums1[i] < nums2[j]) {
                i++;
            }
            else if(nums1[i] > nums2[j]) {
                j++;
            }
            else {
                inter.add(nums1[i]);
                i++;
                j++;
            }
        }
        result = new int[inter.size()];
        for(i=0; i<result.length; i++) {
            result[i] = inter.get(i);
        }
        return result;
    }
	
	public int[] intersect(int[] nums1, int[] nums2) {			//使用HashMap
        int[] result = {};
        if(nums1.length==0 || nums2.length==0) {
            return result;
        }
        
        HashMap<Integer, Integer> map1 = new HashMap<>();
        List<Integer> inter = new ArrayList<>();
        for(int number : nums1) {
            Integer times = map1.get(number);
            if(times != null) {
                map1.put(number, ++times);
            }
            else {
                map1.put(number, 1);
            }
        }
        for(int number : nums2) {
            Integer times = map1.get(number);
            if(times == null) {
                continue;
            }
            else {
                times--;
                inter.add(number);
                if(times == 0) {
                    map1.remove(number);
                }
                else {
                    map1.put(number, times);
                }
            }
        }
        result = new int[inter.size()];
        for(int i=0; i<result.length; i++) {
            result[i] = inter.get(i);
        }
        return result;
    }
}
