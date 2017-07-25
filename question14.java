/*
 * Longest Common Prefix (Easy)
 * 
 * Write a function to find the longest common prefix string amongst an array of strings.
 */
/*
 * 思路：
 * 1.将str数组的第一个元素置为结果res，然后遍历str数组，将res与每个元素比较，res不断截断置目前的最长公共前缀。时间复杂度O(S)，空间复杂度O(1)，S是str数组中字符数之和
 * 2.将strs排序后直接比第一个和最后一个，时间复杂度O(nlogn * k)，k是每个元素的平均长度
 * 
 * 编辑解法：
 * Approach #1 (Horizontal scanning)，就是我的思路
   有一种变种实现：这种倒着找速度竟然比我正着找快得多，只能说test case一般都是倒着来的
   public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0)    return "";
        String pre = strs[0];
        for(int i=1; i<strs.length; i++) {
            while(strs[i].indexOf(pre) != 0)    //startsWith，不停找当前前缀和str[i]的公共前缀
                pre = pre.substring(0,pre.length()-1);
        }
        return pre;
    }
    
 * Approach #2 (Vertical scanning)，复杂度与方法1一致
   Algorithm 就是每次比所有元素的一位字符，可以当数组末尾的元素很短时可以更早退出
   Imagine a very short string is at the end of the array. The above approach will still do SS comparisons. One way to 
   optimize this case is to do vertical scanning. We compare characters from top to bottom on the same column (same character
   index of the strings) before moving on to the next column.
   
   public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";
    for (int i = 0; i < strs[0].length() ; i++){
        char c = strs[0].charAt(i);
        for (int j = 1; j < strs.length; j ++) {
            if (i == strs[j].length() || strs[j].charAt(i) != c)
                return strs[0].substring(0, i);             
        }
    }
    return strs[0];
	}
	
 * Approach #3 (Divide and conquer) 实际上这个方法我考虑过，就类似于归并排序一样，找左半边的LCP和右半边的LCP，再去比这两个LCP，不断分解
   Algorithm
   To apply the observation above, we use divide and conquer technique, where we split the LCP(S1...Sj) problem into two 
   subproblems LCP(S1...Smid) and LCP(Smid+1...Sj), where mid is (i+j)/2. We use their solutions lcpLeft and lcpRight to 
   construct the solution of the main problem LCP(S1...Sj). To accomplish this we compare one by one the characters of 
   lcpLeft and lcpRight till there is no character match. The found common prefix of lcpLeft and lcpRight is the solution of
   the LCP(S1...Sj).
   public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0) return "";    
        return longestCommonPrefix(strs, 0 , strs.length - 1);
	}

	private String longestCommonPrefix(String[] strs, int l, int r) {
    	if (l == r) {
        	return strs[l];
    	}
    	else {
        	int mid = (l + r)/2;
        	String lcpLeft =   longestCommonPrefix(strs, l , mid);
        	String lcpRight =  longestCommonPrefix(strs, mid + 1,r);
        	return commonPrefix(lcpLeft, lcpRight);
   		}
	}

	String commonPrefix(String left,String right) {
    	int min = Math.min(left.length(), right.length());       
    	for (int i = 0; i < min; i++) {
        	if ( left.charAt(i) != right.charAt(i) )
            	return left.substring(0, i);
    	}
    	return left.substring(0, min);
	}
	Complexity Analysis
	In the worst case we have n equal strings with length m
	Time complexity : O(S), where S is the number of all characters in the array, S = m*n. Time complexity is T(n)=2T(n/2)+O(m)
​	Therefore time complexity is O(S). In the best case this algorithm performs O(minLen*n) comparisons, where minLen is the
 	shortest string of the array
	Space complexity : O(m*log(n))
	There is a memory overhead since we store recursive calls in the execution stack. There are log(n) recursive calls, each store 
	need m space to store the result, so space complexity is O(m*log(n))
	
 * Approach #4 (Binary search)，此方法太牛了...分治我觉得我还可以想到，这个太难了
   The idea is to apply binary search method to find the string with maximum value L, which is common prefix of all of the 
   strings. The algorithm searches space is the interval (0…minLen), where minLen is minimum string length and the maximum 
   possible common prefix. Each time search space is divided in two equal parts, one of them is discarded, because it is 
   sure that it doesn't contain the solution. There are two possible cases: S[1...mid] is not a common string. This means 
   that for each j > i S[1..j] is not a common string and we discard the second half of the search space. S[1...mid] is 
   common string. This means that for for each i < j S[1..i] is a common string and we discard the first half of the search 
   space, because we try to find longer common prefix.
   实际上思路就是，比如将数组的第一个元素一分为二，将其前半部与与剩余的所有元素比较，若均出现在剩余元素中，则将后半部一分为二，加到前半部上继续去找，否则将
   前半部一分为二，在用前半部的前半部去找，直至停下。相当于在string上做二分搜索，但是high = mid-1，或者low=mid+1的判断依据变为str[0].substring(0, mid)
   是否是公共前缀
   
   public String longestCommonPrefix(String[] strs) {
    if (strs == null || strs.length == 0)
        return "";
    int minLen = Integer.MAX_VALUE;
    for (String str : strs)
        minLen = Math.min(minLen, str.length());
    int low = 1;
    int high = minLen;
    while (low <= high) {
        int middle = (low + high) / 2;
        if (isCommonPrefix(strs, middle))
            low = middle + 1;
        else
            high = middle - 1;
    }
    return strs[0].substring(0, (low + high) / 2);
}

private boolean isCommonPrefix(String[] strs, int len){
    String str1 = strs[0].substring(0,len);
    for (int i = 1; i < strs.length; i++)
        if (!strs[i].startsWith(str1))
            return false;
    return true;
}
Complexity Analysis
In the worst case we have n equal strings with length m
Time complexity : O(S*log(n)), where S is the sum of all characters in all strings.
The algorithm makes log(n) iterations, for each of them there are S = m*n comparisons, which gives in total O(S*log(n)) time complexity.
Space complexity : O(1).
We only used constant extra space.
 */
public class question14 {
	public String longestCommonPrefix(String[] strs) {			//垂直比较
	    if (strs == null || strs.length == 0) return "";
	    for (int i = 0; i < strs[0].length() ; i++){
	        char c = strs[0].charAt(i);
	        for (int j = 1; j < strs.length; j ++) {
	            if (i == strs[j].length() || strs[j].charAt(i) != c)
	                return strs[0].substring(0, i);             
	        }
	    }
	    return strs[0];
	}
	
	public String longestCommonPrefix1(String[] strs) {			//思路1，水平比较
        String res = "";
        if(strs == null || strs.length == 0) {
            return res;
        }
        
        res = strs[0];
        for(int i=1; i<strs.length; i++) {
            int j = 0;
            while(j < res.length() && j < strs[i].length()) {
                if(res.charAt(j) != strs[i].charAt(j)) {
                    break;
                }
                j++;					//j++一定不能放上去，不然在取str[i]的第
            }
            if(j == 0) {
                res = "";
                return res;
            }
            res = res.substring(0, j);
        }
        return res;
    }
	
	public static void main(String[] args) {
		//String[] strs = {"a", "b"};
		//System.out.println(new question14().longestCommonPrefix(strs));
		String str = "   a b c def  ghi   ";
		String[] words = str.split(" ");
		for(String word : words)
			System.out.println(word);
	}
}
