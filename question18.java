/*
 * 4Sum (Medium)
 * 
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find
 * all unique quadruplets in the array which gives the sum of target.
 * Note: The solution set must not contain duplicate quadruplets.
 * 
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
   A solution set is:
   [
     [-1,  0, 0, 1],
     [-2, -1, 1, 2],
     [-2,  0, 0, 2]
   ]
 */
/*
 * 思路：
 * 这种N-Sum的题，最多能将复杂度降为O(n^(N-1))。我的思路是首先确定前两个数，然后再对剩余的数进行twoSum求解。其实标准的思路是fourSum->
 * threeSum->twoSum。但是如果判断重复在fourSum和twoSum部分均用hash来做，虽然理论时间复杂度不变，但是会超时，因此选择先对数组进行排序，
 * 这样就可以轻易的排除重复。此外，对于twoSum，如果数组有序，完全没有必要用传统的hash实现，仅需利用two pointers，一个从左向右，一个从右
 * 向左扫描，若和大于目标，则右边减1，否则左边加1（two pointers非常常见的一种运用，已经遇到多次）即可。
 * 
 * 用fourSum->threeSum->twoSum结构并进行优化的方法
 * public List<List<Integer>> fourSum(int[] nums, int target) {
		ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
		int len = nums.length;
		if (nums == null || len < 4)
			return res;

		Arrays.sort(nums);

		int max = nums[len - 1];
		if (4 * nums[0] > target || 4 * max < target)
			return res;

		int i, z;
		for (i = 0; i < len; i++) {
			z = nums[i];
			if (i > 0 && z == nums[i - 1])// avoid duplicate
				continue;
			if (z + 3 * max < target) // z is too small
				continue;
			if (4 * z > target) // z is too large
				break;
			if (4 * z == target) { // z is the boundary
				if (i + 3 < len && nums[i + 3] == z)
					res.add(Arrays.asList(z, z, z, z));
				break;
			}

			threeSumForFourSum(nums, target - z, i + 1, len - 1, res, z);
		}

		return res;
	}

	
	public void threeSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1) {
		if (low + 1 >= high)
			return;

		int max = nums[high];
		if (3 * nums[low] > target || 3 * max < target)
			return;

		int i, z;
		for (i = low; i < high - 1; i++) {
			z = nums[i];
			if (i > low && z == nums[i - 1]) // avoid duplicate
				continue;
			if (z + 2 * max < target) // z is too small
				continue;

			if (3 * z > target) // z is too large
				break;

			if (3 * z == target) { // z is the boundary
				if (i + 1 < high && nums[i + 2] == z)
					fourSumList.add(Arrays.asList(z1, z, z, z));
				break;
			}

			twoSumForFourSum(nums, target - z, i + 1, high, fourSumList, z1, z);
		}

	}

	
	public void twoSumForFourSum(int[] nums, int target, int low, int high, ArrayList<List<Integer>> fourSumList,
			int z1, int z2) {

		if (low >= high)
			return;

		if (2 * nums[low] > target || 2 * nums[high] < target)
			return;

		int i = low, j = high, sum, x;
		while (i < j) {
			sum = nums[i] + nums[j];
			if (sum == target) {
				fourSumList.add(Arrays.asList(z1, z2, nums[i], nums[j]));

				x = nums[i];
				while (++i < j && x == nums[i]) // avoid duplicate
					;
				x = nums[j];
				while (i < --j && x == nums[j]) // avoid duplicate
					;
			}
			if (sum < target)
				i++;
			if (sum > target)
				j--;
		}
		return;
	}
 */
import java.util.*;
public class question18 {
	public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums == null) {
            return null;
        }
        if(nums.length < 4) {
            return new ArrayList<>();
        }
        
        Arrays.sort(nums);                              //sort to avoid duplicate
        List<List<Integer>> res = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            if(i >= 1 && nums[i-1] == nums[i]) {		//avoid duplicate
                continue;
            }
            
            for(int j = i + 1; j < nums.length; j++) {
                if(j > i + 1 && nums[j-1] == nums[j]) {
                    continue;
                }
                
                twoSum(nums, j, target - nums[i] - nums[j], res, nums[i], nums[j]); 
            }
        }
        return res;
    }
	
	public void twoSum(int[] nums, int index, int target, List<List<Integer>> res, int other1, int other2) {
        int i = index + 1, j = nums.length - 1;
        while(i < j) {
            int sum = nums[i] + nums[j];
            if(sum == target) {
                //List<Integer> current = new ArrayList<>();
                //current.add(other1);
                //current.add(other2);
                //current.add(nums[i]);
                //current.add(nums[j]);
                res.add(Arrays.asList(other1, other2, nums[i], nums[j]));
                i++;
                j--;
                while(i < j && nums[i - 1] == nums[i]) {          //avoid duplicate
                    i++;
                }
                while(i < j && nums[j] == nums[j + 1]) {
                    j--;
                }
            }
            else if(sum < target) {
                i++;
            }
            else {
                j--;
            }
        }
    }
	
	/*
	public List<List<Integer>> fourSum1(int[] nums, int target) {			//未优化，超时
        if(nums == null) {
            return null;
        }
        if(nums.length < 4) {
            return new ArrayList<>();
        }
        
        List<List<Integer>> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();                //use 2 hashsets to avoid duplicate
        HashSet<HashSet<Integer>> set2 = new HashSet<>();                
        for(int i = 0; i < nums.length; i++) {
            if(!set.add(nums[i])) {
                continue;
            }
            
            for(int j = i + 1; j < nums.length; j++) {
                List<List<Integer>> left = twoSum1(nums, j, target - nums[i] - nums[j]);
                if(left.size() != 0) {
                    for(List<Integer> candidate : left) {
                        HashSet<Integer> temp = new HashSet<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(candidate.get(0));
                        temp.add(candidate.get(1));
                        if(set2.add(temp)) {
                            candidate.add(nums[i]);
                            candidate.add(nums[j]);
                            res.add(candidate);
                        }
                    }
                }
            }
        }
        return res;
    }
	
	public List<List<Integer>> twoSum1(int[] nums, int index, int target) {			//未根据排序优化twoSum，其实没必要用hash了，可以用two pointers来做
        HashSet<Integer> set = new HashSet<>();
        List<List<Integer>> res = new ArrayList<>();
        boolean flag = false;                           //flag to decide when meet cases like 4 + 4 = 8 
        for(int i = index + 1; i < nums.length; i++) {
            int residue = target - nums[i];
            if(set.contains(residue) && (!set.contains(nums[i]) || residue == nums[i])) {
                if(residue == nums[i]) {
                    if(flag == false) {
                        flag = true;
                    }
                    else {
                        continue;
                    }
                }
                List<Integer> current = new ArrayList<>();
                current.add(residue);
                current.add(nums[i]);
                res.add(current);
            }
            set.add(nums[i]);
        }
        return res;
    }
	*/
}
