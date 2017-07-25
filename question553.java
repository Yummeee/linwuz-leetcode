/*
 * Optimal Division (Medium)
 * 
 * Given a list of positive integers, the adjacent integers will perform the float division. For example, [2,3,4] -> 
 * 2 / 3 / 4.
 * However, you can add any number of parenthesis at any position to change the priority of operations. You should find out 
 * how to add parenthesis to get the maximum result, and return the corresponding expression in string format. Your 
 * expression should NOT contain redundant parenthesis.
 * 
 * Example:
 * Input: [1000,100,10,2]
   Output: "1000/(100/10/2)"
   Explanation:
   1000/(100/10/2) = 1000/((100/10)/2) = 200
   However, the bold parenthesis in "1000/((100/10)/2)" are redundant, 
   since they don't influence the operation priority. So you should return "1000/(100/10/2)". 

   Other cases:
   1000/(100/10)/2 = 50
   1000/(100/(10/2)) = 50
   1000/100/10/2 = 0.5
   1000/100/(10/2) = 2
 * Note:
   The length of the input array is [1, 10].
   Elements in the given array will be in range [2, 1000].
   There is only one optimal division for each test case.  
 */
/*
 * 思路：结果是double，所以不用担心溢出
 * Brute Force，找出所有可能的除法顺序，找到最大的结果并生成字符串，具体实现可以用分治的递归方法。将数组中的数分成两部分，最大的结果一定是从前一部分
 * 以某一顺序除法得到的最大值除以后一部分以某一顺序除法得到的最小值。这是一个嵌套的递归迭代过程，但某一段的除法值可能会被重复计算多次。
 * 
 * 编辑解法：
 * Approach #1 Brute Force [Accepted] 和我的思路一致
 * Brute force of this problem is to divide the list into two parts left and right and call function for these two parts. We will iterate ii from start to end so that left=(start,i) and right=(i+1,end).
 * left and right parts return their maximum and minimum value and corresponding strings.
 * Minimum value can be found by dividing minimum of left by maximum of right i.e. minVal=left.min/right.max.
 * Similarly,Maximum value can be found by dividing maximum of left value by minimum of right value. i.e. maxVal=left.max/right.min.
 * public String optimalDivision(int[] nums) {
        T t = optimal(nums, 0, nums.length - 1, "");
        return t.max_str;
    }
    class T {
        float max_val, min_val;
        String min_str, max_str;
    }
    public T optimal(int[] nums, int start, int end, String res) {
        T t = new T();
        if (start == end) {
            t.max_val = nums[start];
            t.min_val = nums[start];
            t.min_str = "" + nums[start];
            t.max_str = "" + nums[start];
            return t;
        }
        t.min_val = Float.MAX_VALUE;
        t.max_val = Float.MIN_VALUE;
        t.min_str = t.max_str = "";
        for (int i = start; i < end; i++) {
            T left = optimal(nums, start, i, "");
            T right = optimal(nums, i + 1, end, "");
            if (t.min_val > left.min_val / right.max_val) {
                t.min_val = left.min_val / right.max_val;
                t.min_str = left.min_str + "/" + (i + 1 != end ? "(" : "") + right.max_str + (i + 1 != end ? ")" : "");
            }
            if (t.max_val < left.max_val / right.min_val) {
                t.max_val = left.max_val / right.min_val;
                t.max_str = left.max_str + "/" + (i + 1 != end ? "(" : "") + right.min_str + (i + 1 != end ? ")" : "");
            }
        }
        return t;
    }
}
 * Time complexity : O(n!). Number of permutations of expression after applying brackets will be in O(n!) where n is the number of items in the list.
   Space complexity: O(n^2). Depth of recursion tree will be O(n) and each node contains string of maximum length O(n).
   
   
   
 * Approach #2 Using Memorization [Accepted] 将方法1中的一些冗余call只进行一次，即保存[i,j]间的元素进行某一顺序的除法运算的最大值和最小值。如果像我的方法一样，分成两个函数分别计算最大值和最小值。则需要两个这样的数组，一个统计最大值，一个统计最小值
 * In the above approach we called optimal function recursively for ever start and end. We can notice that there are many 
 * redundant calls in the above approach, we can reduce these calls by using memorization to store the result of different 
 * function calls. Here, memo array is used for this purpose.
 * class T {
        float max_val, min_val;
        String min_str, max_str;
    }
    public String optimalDivision(int[] nums) {
        T[][] memo = new T[nums.length][nums.length];
        T t = optimal(nums, 0, nums.length - 1, "", memo);
        return t.max_str;
    }
    public T optimal(int[] nums, int start, int end, String res, T[][] memo) {
        if (memo[start][end] != null)
            return memo[start][end];
        T t = new T();
        if (start == end) {
            t.max_val = nums[start];
            t.min_val = nums[start];
            t.min_str = "" + nums[start];
            t.max_str = "" + nums[start];
            memo[start][end] = t;
            return t;
        }
        t.min_val = Float.MAX_VALUE;
        t.max_val = Float.MIN_VALUE;
        t.min_str = t.max_str = "";
        for (int i = start; i < end; i++) {
            T left = optimal(nums, start, i, "", memo);
            T right = optimal(nums, i + 1, end, "", memo);
            if (t.min_val > left.min_val / right.max_val) {
                t.min_val = left.min_val / right.max_val;
                t.min_str = left.min_str + "/" + (i + 1 != end ? "(" : "") + right.max_str + (i + 1 != end ? ")" : "");
            }
            if (t.max_val < left.max_val / right.min_val) {
                t.max_val = left.max_val / right.min_val;
                t.max_str = left.max_str + "/" + (i + 1 != end ? "(" : "") + right.min_str + (i + 1 != end ? ")" : "");
            }
        }
        memo[start][end] = t;
        return t;
    }
}
Time complexity : O(n^3). memo array of size n^2 is filled and filling of each cell of the memo array takes O(n) time.
Space complexity : O(n^3). memo array of size n^2 where each cell of array contains string of length O(n).



 * Approach #3 Using some Math [Accepted] 我一开始也想到了一点...
 * Using some simple math we can find the easy solution of this problem. Consider the input in the form of [a,b,c,d], now 
 * we have to set priority of operations to maximize a/b/c/d. We know that to maximize fraction p/q, q(denominator) should 
 * be minimized. So, to maximize a/b/c/d we have to first minimize b/c/d. Now our objective turns to minimize the expression 
 * b/c/d.
 * There are two possible combinations of this expression, b/(c/d) and (b/c)/d.
 * b/(c/d)        (b/c)/d = b/c/d
   => (b*d)/c        b/(d*c)
   => d/c            1/(d*c)
 * Obviously, d/c > 1/(d*c) for d>1.(两边同乘cd)
 * You can see that second combination will always be less than first one for numbers greater than 1. So, the answer will be
 * a/(b/c/d). Similarly for expression like a/b/c/d/e/f... answer will be a/(b/c/d/e/f...).所以结果永远是这种形式的，因为输入都是正整数
 * public class Solution {
    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return nums[0] + "";
        if (nums.length == 2)
            return nums[0] + "/" + nums[1];
        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");
        return res.toString();
    }
}
Time complexity : O(n). Single loop to traverse numsnums array.
Space complexity : O(n). resres variable is used to store the result.
此方法还可以这样理解：
X1/X2/X3/../Xn will always be equal to (X1/X2) * Y, no matter how you place parentheses. i.e no matter how you place parentheses, X1 always goes to the numerator and X2 always goes to the denominator. Hence you just need to maximize Y. And Y is maximized when it is equal to X3 *..*Xn. So the answer is always X1/(X2/X3/../Xn) = (X1 *X3 *..*Xn)/X2
 */
public class question553 {
	public String optimalDivision(int[] nums) {				//思路1。
        StringBuilder exp = new StringBuilder();
        helperMax(nums, 0, nums.length - 1, exp);
        return exp.toString();
    }
	
	public double helperMax(int[] nums, int start, int end, StringBuilder exp) {
		exp.delete(0, exp.length());
        if(start == end) {
            exp.append(String.valueOf(nums[start]));
            return (double)nums[start];
        }
        if(end - start == 1) {
            exp.append(String.valueOf(nums[start]) + "/" + String.valueOf(nums[end]));
            return (1.0 * nums[start]) / nums[end];
        } 
        
        double result = 0, cur_result = 0, left_max = 0, right_min = 0;
        StringBuilder exp_left = new StringBuilder(), exp_right = new StringBuilder();
        for(int i = start; i < end; i++) {
            left_max = helperMax(nums, start, i, exp_left);					//前一部分最大值
            right_min = helperMin(nums, i + 1, end, exp_right);				//后一部分最小值
            cur_result = left_max / right_min;
            if(cur_result > result) {
                result = cur_result;
                exp.delete(0, exp.length());
                if(i == end - 1) {       //右半部分只有一个数字，不需要加括号了
                    exp.append(exp_left.toString() + "/" +  exp_right.toString());
                }
                else {
                    exp.append(exp_left.toString() + "/(" +  exp_right.toString() + ")");
                }
            }
        }
        return result;
    }
	
	public double helperMin(int[] nums, int start, int end, StringBuilder exp) {
		exp.delete(0, exp.length());
		if(start == end) {
            exp.append(nums[start]);
            return (double)nums[start];
        }
        if(end - start == 1) {
            exp.append(String.valueOf(nums[start]) + "/" + String.valueOf(nums[end]));
            return (1.0 * nums[start]) / nums[end];
        } 
        
        double result = Double.MAX_VALUE, cur_result = 0, left_min = 0, right_max = 0;
        StringBuilder exp_left = new StringBuilder(), exp_right = new StringBuilder();
        for(int i = start; i < end; i++) {
        	left_min = helperMin(nums, start, i, exp_left);					//前一部分最小值
        	right_max = helperMax(nums, i + 1, end, exp_right);				//后一部分最大值
            cur_result = left_min / right_max;
            if(cur_result < result) {
                result = cur_result;
                exp.delete(0, exp.length());
                if(i == end - 1) {       //右半部分只有一个数字，不需要加括号了
                    exp.append(exp_left.toString() + "/" +  exp_right.toString());
                }
                else {
                    exp.append(exp_left.toString() + "/(" +  exp_right.toString() + ")");
                }
            }
        }
        return result;
    }
	
	public static void main(String[] args) {
		int[] nums = {6,2,3,4,5};
		System.out.println(new question553().optimalDivision(nums));
	}
}
