/*
 * Merge Sorted Array (Easy)
 * 
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * Note:
   You may assume that nums1 has enough space (size that is greater or equal to m + n) to hold additional elements from nums2.
   The number of elements initialized in nums1 and nums2 are m and n respectively.
 */
/*
 * 思路：
 * 1.遍历nums2中的元素，找到其在nums1中的位置（可以二分查找），并将其后的元素均后移一个。时间复杂度O(n*m)，extra space：O(1)（严格上讲不是空间复杂度）
 * 2.将nums2中的元素放在nums1的最后，然后用arrays.sort排序，时间复杂度O(log(m+n))，extra space：O(1)
 * 3.用二路归并，将nums1和nums2在一个中间数组中排好序，然后再复制回nums1，时间复杂度O(m+n)，空间复杂度O(m+n)
 * 4.思路3改进，不需要一个辅助数组，从后往前排序，仍是二路归并。时间复杂度O(m+n)，extra space：O(1)
 */
public class question88 {
	public void merge(int[] nums1, int m, int[] nums2, int n) {			//思路4实现
        int i = m-1, j = n-1, k = m+n-1;
        while(i>-1 && j>-1) {				//是绝对不会覆盖掉没有排序的nums1元素的，因为nums1的总长度不小于m+n，所以每个元素都会有自己的位置
        	//nums1[k--] = nums1[i] > nums2[j] ? nums1[i--] : nums2[j--];
            if(nums1[i] > nums2[j]) {
                nums1[k--] = nums1[i--];
            }
            else {
                nums1[k--] = nums2[j--];
            }
        }
        while(j > -1) {					//说明nums1已经完全排好了，剩下的nums2的元素均比nums1[0]小，如果是i>-1而j==-1，那就不用动了，因为nums1一开始已经在原处放好
        	nums1[k--] = nums2[j--];
        }
    }
	
	public void merge1(int[] nums1, int m, int[] nums2, int n) {			//思路3实现
        if(n == 0) {
            return;
        }
        
        int[] temp = new int[m+n];
        int i = 0, j = 0, k = 0;
        while(i<m && j<n) {
            if(nums1[i] < nums2[j]) {
                temp[k] = nums1[i];
                i++;
            }
            else {
                temp[k] = nums2[j];
                j++;
            }
            k++;
        }
        while(i < m) {
            temp[k] = nums1[i];
            i++;
            k++;
        }
        while(j < n) {
            temp[k] = nums2[j];
            j++;
            k++;
        }
        
        for(i=0; i<m+n; i++) {
            nums1[i] = temp[i];
        }
    }
}
