/*
 * Teemo Attacking (Medium)
 * 
 * In LLP world, there is a hero called Teemo and his attacking can make his enemy Ashe be in poisoned condition. 
 * Now, given the Teemo's attacking ascending time series towards Ashe and the poisoning time duration per 
 * Teemo's attacking, you need to output the total time that Ashe is in poisoned condition.
 * 
 * You may assume that Teemo attacks at the very beginning of a specific time point, and makes Ashe be in 
 * poisoned condition immediately.
 * 
 * Example 1:
   Input: [1,4], 2
   Output: 4
   Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned immediately. 
   This poisoned status will last 2 seconds until the end of time point 2. 
   And at time point 4, Teemo attacks Ashe again, and causes Ashe to be in poisoned status for another 2 seconds. 
   So you finally need to output 4.
 * Example 2:
   Input: [1,2], 2
   Output: 3
   Explanation: At time point 1, Teemo starts attacking Ashe and makes Ashe be poisoned. 
   This poisoned status will last 2 seconds until the end of time point 2. 
   However, at the beginning of time point 2, Teemo attacks Ashe again who is already in poisoned status. 
   Since the poisoned status won't add up together, though the second poisoning attack will still work at time point 2, it will stop at the end of time point 3. 
   So you finally need to output 3.  
 * 
 * Note:
   You may assume the length of given time series array won't exceed 10000.
   You may assume the numbers in the Teemo's attacking time series and his poisoning time duration per attacking 
   are non-negative integers, which won't exceed 10,000,000.
 */
/*
 * 思路：
 * 遍历一遍数组，如果time[i] - time[i-1]小于duration，这时中毒事件需要重置，中毒时间增加time[i] - time[i-1]，否则增加duration
 * 时间复杂度O(n)，extra space: O(1)
 * 
 * 类似的思路：参考了merge intervals
 * Algorithm:
   Use two variable to record current start and end point.
   If the start of new interval is greater than current end, meaning NO overlapping, we can sum the current interval length to result and then update start and end.
   Otherwise just update the current end;
   
   public int findPosisonedDuration(int[] timeSeries, int duration) {
        if (timeSeries == null || timeSeries.length == 0 || duration == 0) return 0;
        
        int result = 0, start = timeSeries[0], end = timeSeries[0] + duration;
        for (int i = 1; i < timeSeries.length; i++) {
            if (timeSeries[i] > end) {			//没有overlap
                result += end - start;
                start = timeSeries[i];
            }
            end = timeSeries[i] + duration;
        }
        result += end - start;
        
        return result;
    }
 * 
 */
public class question495 {
	public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries == null || timeSeries.length == 0) {
            return 0;
        }
        if(timeSeries.length == 1) {
            return duration;
        }
        
        int duration_time = 0;
        for(int i = 1; i < timeSeries.length; i++) {
        	int slot = timeSeries[i] - timeSeries[i-1];
            if(slot < duration) {
                duration_time += slot;
            }
            else {
                duration_time += duration;
            }
            
        }
        return duration_time + duration;
    }
}
