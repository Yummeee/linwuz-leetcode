/*
 * Third Maximum Number (Easy)
 * 
 * Given a non-empty array of integers, return the third maximum number in this array. If it does not exist, return the 
 * maximum number. The time complexity must be in O(n).
 * 
 * Example 1:
   Input: [3, 2, 1]
   Output: 1
   Explanation: The third maximum is 1.
 * Example 2:
   Input: [1, 2]
   Output: 2
   Explanation: The third maximum does not exist, so the maximum (2) is returned instead.  
 * Example 3:
   Input: [2, 2, 3, 1]
   Output: 1
   Explanation: Note that the third maximum here means the third maximum distinct number. Both numbers with value 2 are both considered as second maximum.  
 */
/*
 * 思路: 这题不难，坑也太多了吧...nums的元素可以是Integer.MIN_VALUE，对于重复出现最大数，第二大数的处理...太多坑了，算法思路很简单，最后逼得没办法不想
 * 太麻烦才用的Long变量...
 * 1.遍历数组nums，设3个变量分别记录当前遍历过的数中的前3大数，注意相等值的处理。由于输入可以是int的最小值，这个很难处理，所以将这三个变量的初值设为Long的
 * 最小值。遍历一遍后如果third_max != Long.MIN_VALUE则说明有第三大值出现。时间复杂度O(n)，空间复杂度O(1)。
 * 
 * 如果用int，如何解决nums中有int最小值的问题
 * 1)用Integer类型表示前三大元素，初始值置为null
 *      Integer max1 = null;
        Integer max2 = null;
        Integer max3 = null;
        for (Integer n : nums) {
            if (n.equals(max1) || n.equals(max2) || n.equals(max3)) continue;				
            if (max1 == null || n > max1) {			
                max3 = max2;
                max2 = max1;
                max1 = n;
            } else if (max2 == null || n > max2) {
                max3 = max2;
                max2 = n;
            } else if (max3 == null || n > max3) {			//max == null就处理了int_min情况
                max3 = n;
            }
        }
        return max3 == null ? max1 : max3;
 * 2)使用3个布尔变量或者用位操作来判断是否第三个值被替换，还是自己设置的初值。当时觉得麻烦没用
 * public int thirdMax(int[] nums) {  
        int max = Integer.MIN_VALUE, second = Integer.MIN_VALUE, third = Integer.MIN_VALUE, status = 7;  
        for(int i = 0; i < nums.length; i++){  
            if((nums[i] == max && (status & 4) == 0)  ||( nums[i]== second  && (status & 2) == 0)||( nums[i] == third && (status & 1) == 0)){  
                continue;  		//这里&4，2，1就是为了判断输入如果是int_Min，且max,second,third若有一个没赋值，相当于这个时候重赋了min
            }  
            status = status >> 1;  //赋值一次，哪怕输入时int_min，只要不是重复值，就理应赋值
            if(nums[i] > max){  
                third = second;  
                second = max;  
                max = nums[i];  
            }  
            else if(nums[i] > second){  
                third = second;  
                second = nums[i];  
            }  
            else if(nums[i] > third){  
                third = nums[i];  
            }  
        }  
        if((status&7) != 0){  
            return max;  
        }  
        else{  
            return third;  
        }  
 
 * 2.使用java的PriorityQueue类，其可以自动的将存入队列的元素以natural order的形式存放，就好比用了一次Arrays.sort，只要时刻保证这个queue中的元素
 * 只有不超过3个，时间复杂度也能控制在O(nlog3)=O(n)
 * public int thirdMax(int[] nums) {
       PriorityQueue<Integer> pq = new PriorityQueue<>();
       Set<Integer> set = new HashSet<>();			
       for(int n : nums) {
           if(set.add(n)) {
               pq.offer(n);
               if(pq.size() > 3 ) pq.poll();
           }
       }
       if(pq.size() == 2) pq.poll();
       return pq.peek();
    }
 */
public class question414 {
	public int thirdMax(int[] nums) {
        long first_max = Long.MIN_VALUE, second_max = Long.MIN_VALUE, third_max = Long.MIN_VALUE;		//处理INT_MIN
        for(int i=0; i<nums.length; i++) {
        	if(nums[i] == first_max || nums[i] == second_max || nums[i] == third_max) {			//处理重复元素情况
        		continue;
        	}
            if(nums[i] > first_max) {
                third_max = second_max;
                second_max = first_max;
                first_max = nums[i];
            }
            else if(nums[i] > second_max) {
            //else if(nums[i] > second_max && nums[i] != first_max) {
                third_max = second_max;
                second_max = nums[i];
            }
            else if(nums[i] > third_max) {
            //else if(nums[i] > third_max && nums[i] != first_max && nums[i] != second_max) {	
                third_max = nums[i];
            }
        }
      
        return third_max != Long.MIN_VALUE ? (int)third_max : (int)first_max;
    }
}
