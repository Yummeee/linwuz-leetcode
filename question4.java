/*
 * Median of Two Sorted Arrays (Hard)
 * 
 * There are two sorted arrays nums1 and nums2 of size m and n respectively.
 * Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).
 */


/*
 * 这题做的时候完全没有思路...然而是算法设计与分析课本上找无序数组第k小元素的变种...
 * 思想是分治算法
核心是将原问题转变成一个寻找第k小数的问题（假设两个原序列升序排列），这样中位数实际上是第(m+n)/2小的数。所以只要解决了第k小数的问题，原问题也得以解决。
首先假设数组A和B的元素个数都大于k/2，我们比较A[k/2-1]和B[k/2-1]两个元素，这两个元素分别表示A的第k/2小的元素和B的第k/2小的元素。
这两个元素比较共有三种情况：>、<和=。如果A[k/2-1]<B[k/2-1]，这表示A[0]到A[k/2-1]的元素都在A和B合并之后的前k小的元素中。换句话说，A[k/2-1]不可能大于两数组合并之后的第k小值，所以我们可以将其抛弃。

证明也很简单，可以采用反证法。假设A[k/2-1]大于合并之后的第k小值，我们不妨假定其为第（k+1）小值。由于A[k/2-1]小于B[k/2-1]，所以B[k/2-1]至少是第（k+2）小值。
但实际上，在A中至多存在k/2-1个元素小于A[k/2-1]，B中也至多存在k/2-1个元素小于A[k/2-1]，所以小于A[k/2-1]的元素个数至多有k/2+ k/2-2，小于k，这与A[k/2-1]是第（k+1）的数矛盾。
当A[k/2-1]>B[k/2-1]时存在类似的结论。
当A[k/2-1]=B[k/2-1]时，我们已经找到了第k小的数，也即这个相等的元素，我们将其记为m。由于在A和B中分别有k/2-1个元素小于m，所以m即是第k小的数。(这里可能有人会有疑问，如果k为奇数，则m不是第k小的数。这里是进行了理想化考虑，在实际代码中略有不同，是先求k/2，然后利用k-k/2获得另一个数。)

边界条件：
如果A或者B为空，则直接返回B[k-1]或者A[k-1]；
如果k为1，我们只需要返回A[0]和B[0]中的较小值；
如果A[k/2-1]=B[k/2-1]，返回其中一个；

时间复杂度O(logk)，因为每次最好情况下(时间为常数，就是几个比较)都删除掉当前k的1/2，类比二分搜索。又因为这里k是(m+n)/2，所以时间复杂度是O(log(m+n))
 */
public class question4 {
	public double findKth(int[] a, int a_start, int a_end, int[] b, int b_start, int b_end, int k) {
        //always assume that m is smaller than or equal to n
		int m = a_end-a_start+1;
		int n = b_end-b_start+1;
        if(m > n) {
            return findKth(b, b_start, b_end, a, a_start, a_end, k);
        }
        if(m == 0) {        //if a or b is empty, just return the biggest element of the other array
            return b[b_start+k-1];
        }
        if(k == 1) {
            return Math.min(a[a_start], b[b_start]);
        }
        
        int pa = Math.min(k/2, m);
        int pb = k-pa;			//in order to make sure pb>=0 is always right, makes m<=n 
        if(a[a_start+pa-1] < b[b_start+pb-1]) {
            return findKth(a, a_start+pa, a_end, b, b_start, b_end, k-pa);
        }
        else if(a[a_start+pa-1] > b[b_start+pb-1]) {
            return findKth(a, a_start, a_end, b, b_start+pb, b_end, k-pb);
        }
        else {
            return a[a_start+pa-1];
        }
    }
    
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        double mid = 0;
        //transfer this problem to finding the kth least number of [num1,num2]
        if((m+n)%2 == 1) {
            int k = (m+n)/2 + 1;       
            mid = findKth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k);
        }
        else {
            int k1 = (m+n)/2;
            int k2 = k1+1;
            mid = (findKth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k1)+findKth(nums1, 0, nums1.length-1, nums2, 0, nums2.length-1, k2))/2;
        }
        return mid;
    }
    
    public static void main(String[] args) {
    	int[] nums1 = {2};
    	int[] nums2 = {};
    	double mid = new question4().findMedianSortedArrays(nums1, nums2);
    	System.out.println("mdian is: " + mid);
    }
}
