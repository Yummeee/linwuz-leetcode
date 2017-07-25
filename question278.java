/*
 * First Bad Version (Easy)
 * 
 * You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your
 * product fails the quality check. Since each version is developed based on the previous version, all the versions after a 
 * bad version are also bad.
 * 
 * Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones
 * to be bad.
 * You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the
 * first bad version. You should minimize the number of calls to the API.
 */

/*
 * 思路：
 * 好明显的Binary Search，时间复杂度O(logn)。
 */

/* The isBadVersion API is defined in the parent class VersionControl.
      boolean isBadVersion(int version); */
public class question278 {
	/*
	 public int firstBadVersion(int n) {
        if(n == 1) {
            return 1;
        }
        int low = 1, high = n;
        while(low < high) {											//low == high说明一定是这个了，不需要再判断。加个等号就超时了...
            int mid = low + ((high - low) >> 1);					//avoid overflow
            if(isBadVersion(mid)) {
            	high = mid;
            }
            else {
                low = mid + 1;
            }
        }
        return high;
    }
    
	public int firstBadVersion1(int n) {
        if(n == 1) {
            return 1;
        }
        int low = 1, high = n;
        while(low <= high) {
            int mid = low + ((high - low) >> 1);					//avoid overflow
            if(isBadVersion(mid)) {
                if(mid > 1 && isBadVersion(mid - 1)) {				//为了减少调用次数，否则就直接high = mid，最后return high了
                    high = mid - 1;
                }
                else {
                    return mid;
                }
            }
            else {
                low = mid + 1;
            }
        }
        return low;
    }
    */
}
