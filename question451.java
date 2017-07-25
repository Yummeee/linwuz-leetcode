/*
 * Sort Characters By Frequency (Medium)
 * 
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 
 * Example 1:
 * Input: "tree"
   Output: "eert"
   Explanation:
   'e' appears twice while 'r' and 't' both appear once.
   So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * Example 2:
 * Input: "cccaaa"
   Output: "cccaaa"
   Explanation:
   Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
   Note that "cacaca" is incorrect, as the same characters must be together.  
 * Example 3:
 * Input: "Aabb"
   Output: "bbAa"
   Explanation:
   "bbaA" is also a valid answer, but "Aabb" is incorrect.
   Note that 'A' and 'a' are treated as two different characters.
 */
/*
 * 思路：
 * 1.使用HashMap，随后对HashMap的value排序，注意HashMap要转成List类型后用Collections.sort排序。时间复杂度最坏情况下O(nlogn)，
 * 即每一个字符都不一样。也可以将HashMap放到一个PriorityQueue中，时间复杂度一模一样。
 * 2.对字符出现次数排序进行一个基数或桶排序（基数排序是桶排序的一个特例，即所有排序元素被分为长度为1的N段），将出现次数一致的元素放在相同的索引
 * 处，时间复杂度O(s.length())，空间复杂度O(n)，n为字符个数的范围。最快的方法是用int[256]做hash表，但是输入就一定要在acsii范围。
 */
import java.util.*;
public class question451 {
	public String frequencySort(String s) {                //思路2
        if(s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder res = new StringBuilder();
        int max_times = 0;
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            int times = map.getOrDefault(temp, 0) + 1;
            max_times = Math.max(times, max_times);
            map.put(temp, times);
        }
        List<Character>[] bucket = new List[max_times];				//构建bucket，实际上是基数排序。因为分段范围是1
        for(Map.Entry<Character, Integer> entry : map.entrySet()) {	//将元素都放置在桶中
        	int freq = entry.getValue() - 1;
        	if(bucket[freq] == null) {
        		bucket[freq] = new ArrayList<>();
        	}
            bucket[freq].add(entry.getKey());
        }
        for(int i = max_times - 1; i >= 0; i--) {
        	if(bucket[i] != null) {
        		for(Character cur_char : bucket[i]) {
                    for(int j = 0; j <= i; j++) {
                        res.append(cur_char);
                    }
                }
        	}
        }
        return res.toString();
    }
	
	public String frequencySort1(String s) {				//思路1
        if(s == null || s.length() == 0 || s.length() == 1) {
            return s;
        }
        
        HashMap<Character, Integer> map = new HashMap<>();
        StringBuilder res = new StringBuilder();
        for(int i = 0; i < s.length(); i++) {
            char temp = s.charAt(i);
            map.put(temp, map.getOrDefault(temp, 0) + 1);
        }
        
        List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Character, Integer>>() {
                                public int compare(Map.Entry<Character, Integer> x, Map.Entry<Character, Integer> y) {
                                    return y.getValue() - x.getValue();
                                }
                        });
        for(Map.Entry<Character, Integer> entry : list) {
            char cur_letter = entry.getKey();
            for(int j = 0; j < entry.getValue(); j++) {
                res.append(cur_letter);
            }
        }
        return res.toString();
    }
}
