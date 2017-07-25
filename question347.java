/*
 * Top K Frequent Elements (Medium)
 * 
 * Given a non-empty array of integers, return the k most frequent elements.
 * For example,
   Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: 
   You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
   Your algorithm's time complexity must be better than O(n log n), where n is the array's size.
 */
/*
 * 思路：
 * 1.类比sort characters by frequency (451)那道题，使用hashmap+桶排序对所有元素按照出现次数进行排序。时间复杂度O(n)，extra space: O(n)
 * 2.同451那道题的思路1，即将hashmap转换成list后用comparator进行比较。时间复杂度O(nlogn)，n为独一无二的元素个数
 * 3.同451一样，也可以用priorityqueue来对出现次数排序，时间复杂度O(nlogn)，但实际效率比思路1还高。这种方法还能继续优化，始终保持
 * priorityqueue中元素数量为k，超过k就poll掉最后的元素（但这时queue需要从小到大排序了）。
 * public class Solution {
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
           
        PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = 
                         new PriorityQueue<>((a,b)->(b.getValue()-a.getValue()));			//等价于一个new Comparator,简写方法
        for(Map.Entry<Integer,Integer> entry: map.entrySet()){
            maxHeap.add(entry);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, Integer> entry = maxHeap.poll();
            res.add(entry.getKey());
        }
        return res;
    }
}
  优化：时间复杂度降为O(Nlogk)
  public List<Integer> topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> counterMap = new HashMap<>();
    for(int num : nums) {
        int count = counterMap.getOrDefault(num, 0);
        counterMap.put(num, count+1);
    }
    
    PriorityQueue<Map.Entry<Integer, Integer>> pq = new PriorityQueue<>((a, b) -> a.getValue()-b.getValue());
    for(Map.Entry<Integer, Integer> entry : counterMap.entrySet()) {
        pq.offer(entry);
        if(pq.size() > k) pq.poll();
    }
    
    List<Integer> res = new LinkedList<>();
    while(!pq.isEmpty()) {
        res.add(0, pq.poll().getKey());
    }
    return res;
}
 * 4.利用hashmap和treemap的排序，treemap以freq做键，list<integer>做value。
 * public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int n: nums){
            map.put(n, map.getOrDefault(n,0)+1);
        }
        
        TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
        for(int num : map.keySet()){
           int freq = map.get(num);
           if(!freqMap.containsKey(freq)){
               freqMap.put(freq, new LinkedList<>());
           }
           freqMap.get(freq).add(num);
        }
        
        List<Integer> res = new ArrayList<>();
        while(res.size()<k){
            Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
            res.addAll(entry.getValue());
        }
        return res;
    }
 */
import java.util.*;
public class question347 {
	public List<Integer> topKFrequent(int[] nums, int k) {				//思路1
        HashMap<Integer, Integer> map = new HashMap<>();
        int max_frequence = 0;
        for(int i = 0; i < nums.length; i++) {
            int frequence = map.getOrDefault(nums[i], 0) + 1;
            max_frequence = Math.max(max_frequence, frequence);
            map.put(nums[i], frequence);
        }
        
        List<Integer>[] bucket = new List[max_frequence];                //create the bucket that has the range from 1 - max_frequence
        List<Integer> res = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : map.entrySet()) {
            int key = entry.getKey();
            int value = entry.getValue() - 1;                   //to accommodate array's index
            if(bucket[value] == null) {
                bucket[value] = new ArrayList<Integer>();
            }
            bucket[value].add(key);
        }
        for(int i = bucket.length - 1; i >= 0; i--) {
            if(bucket[i] != null) {
            	/*
                Iterator<Integer> it = bucket[i].iterator();
                while(it.hasNext()) {
                    if(k == 0) {
                        break;
                    }
                    res.add(it.next());
                    k--;
                }
                */
            	//use arraylist.addAll() to add elements
            	if(k <= 0) {
            		break;
            	}
            	res.addAll(bucket[i]);
            	k -= bucket[i].size();
            }
        }
        return res;
    }
}
