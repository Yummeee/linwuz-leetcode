/*
 * Best Time to Buy and Sell Stock II (Easy)
 * 
 * Say you have an array for which the ith element is the price of a given stock on day i.
 * Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the 
 * stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy 
 * again).
 */
/*
 * 思路：
 * 只要后一个点大于前一个点，就可以是利润，时间复杂度O(n)，思想属于贪心算法：我们可以不考虑之后超出一天的情况，无论第三天是升还是跌，第二天只要是涨的就可以算进利润
 * 
 * 非贪心算法：找到一段时间内价格的波谷和波峰再相减：
 * public int maxProfit(int[] prices) {
    int profit = 0, i = 0;
    while (i < prices.length) {
        // find next local minimum
        while (i < prices.length-1 && prices[i+1] <= prices[i]) i++;
        int min = prices[i++]; // need increment to avoid infinite loop for "[1]"
        // find next local maximum
        while (i < prices.length-1 && prices[i+1] >= prices[i]) i++;
        profit += i < prices.length ? prices[i++] - min : 0;
    }
    return profit;
}
 */
public class question122 {
	public int maxProfit(int[] prices) {
        if(prices.length == 1 || prices.length==0 || prices==null) {
            return 0;
        }
        
        int profit = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i]>prices[i-1]) {
                profit += prices[i]-prices[i-1];
            }
        }
        return profit;
    }
}
