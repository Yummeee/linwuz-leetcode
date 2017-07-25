/*
 * Best Time to Buy and Sell Stock (Easy) 类比question122
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), design an algorithm to find
 * the maximum profit.
 * 
 * e.g.,
 * Input: [7, 1, 5, 3, 6, 4] Output: 5;    max. difference = 6-1 = 5 (not 7-1 = 6, as selling price needs to be larger than buying price)
 * Input: [7, 6, 4, 3, 1] Output: 0;       In this case, no transaction is done, i.e. max profit = 0.
 */
/*
 * 思路:
 * 1.Brute Force, 时间复杂度在最坏情况下O(n^2)，空间复杂度O(1)
 * 2.动态规划，我自己设计的规则为：时间复杂度O(n)
 * 记P[k]为第0天到第k天能够一次操作得到的最大利润，，P[0] = 0；pos为P[k-1]取得最大利润情况下卖出的那一天，min为0...k-1天里股票价值最低的那一天，pos和min初始值均为0;
 * P[k] = P[k-1], if max(P[k-1]+prices[k]-prices[pos], prices[k]-prices[min]) <= P[k] 且 if(prices[k] < prices[min]), min = k;
 * P[k] = max(P[k-1]+prices[k]-prices[pos], prices[k]-prices[min]), pos = k;
 * 
 * 实际上，看了编辑的答案...发现把问题搞复杂了...当前的最优解一定是当前这一段中差值最大的一对波峰-波谷。
 * public int maxProfit(int prices[]) {
        int minprice = Integer.MAX_VALUE;
        int maxprofit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < minprice)
                minprice = prices[i];
            else if (prices[i] - minprice > maxprofit)
                maxprofit = prices[i] - minprice;
        }
        return maxprofit;
    }
 *
 * 3.Kadane's Algorithm （感觉有点像moore voting algorithm）
 * The logic to solve this problem is same as "max subarray problem" using Kadane's Algorithm. Since no body has mentioned this so far, I thought it's a good thing for everybody to know.
 * Here, the logic is to calculate the difference (maxCur += prices[i] - prices[i-1]) of the original array, and find a contiguous subarray giving maximum profit. If the difference falls below 0, reset it to zero.
 * public int maxProfit(int[] prices) {
        int maxCur = 0, maxSoFar = 0;
        for(int i = 1; i < prices.length; i++) {
            maxCur = Math.max(0, maxCur += prices[i] - prices[i-1]);		//maxCur是局部最优解
            maxSoFar = Math.max(maxCur, maxSoFar);							//maxSoFar是全局最优解
        }
        return maxSoFar;
    }
 * maxCur = current maximum value
 * maxSoFar = maximum value found so far
 */
public class question121 {
	public int maxProfit(int[] prices) {
        if(prices.length == 1) {
            return 0;
        }
        
        int[] profit = new int[prices.length];
        int max_profit = 0;
        int pos = 0, min = 0;
        for(int i=1; i<profit.length; i++) {
            profit[i] = Math.max(profit[i-1]+prices[i]-prices[pos], prices[i]-prices[min]);
            if(profit[i] <= profit[i-1]) {
                profit[i] = profit[i-1];
                if(prices[i] < prices[min]) {
                    min = i;
                }
            }
            else {
                pos = i;
                if(profit[i] > max_profit) {
                    max_profit = profit[i];
                }
            }
        }
        return max_profit;
    }
}
