/*
 * Binary Watch (Easy)
 * 
 * A binary watch has 4 LEDs on the top which represent the hours (0-11), and the 6 LEDs on the bottom represent the minutes (0-59).
 * Each LED represents a zero or one, with the least significant bit on the right.
 * 
 * Given a non-negative integer n which represents the number of LEDs that are currently on, return all possible times the watch could represent.
 * Input: n = 1 Return: ["1:00", "2:00", "4:00", "8:00", "0:01", "0:02", "0:04", "0:08", "0:16", "0:32"]
 * Note:
   The order of output does not matter.
   The hour must not contain a leading zero, for example "01:00" is not valid, it should be "1:00".
   The minute must be consist of two digits and may contain a leading zero, for example "10:2" is not valid, it should be "10:02".
 */
/*
 * 思路：这道题好变态...虽然是简单难度...注意小时的范围是0-11，分钟的范围是0-59，我第一次就算出来了12小时，还怀疑答案错了...
 * 1.将所有情况枚举出来存入两个二维数组里（小时亮1、2、3、4栈灯的所有情况，分钟类似）。这个方法显然是最快的算法，但是不scalable.

 * 2.递归回溯，求小时和分钟的各自所有组合并求每种组合的和。在求组合的和的过程中，我参考了数据结构PPT中求m个数中k个数的所有组合的方法，先确定第k个数，然后求从剩下的
 * m-1个数中取k-1个数的所有组合。这里还可以采用另外一种方法：这种思路和我们平时枚举组合的结果的过程一致，不是像我的方法从后往前数，而是从前往后数，刚好是我的方法的翻转
 * public List<String> readBinaryWatch(int num) {
        List<String> res = new ArrayList<>();
        int[] nums1 = new int[]{8, 4, 2, 1}, nums2 = new int[]{32, 16, 8, 4, 2, 1};
        for(int i = 0; i <= num; i++) {
            List<Integer> list1 = generateDigit(nums1, i);			//小时中亮i盏灯
            List<Integer> list2 = generateDigit(nums2, num - i);	//分钟亮num-i盏灯
            for(int num1: list1) {
                if(num1 >= 12) continue;
                for(int num2: list2) {
                    if(num2 >= 60) continue;
                    res.add(num1 + ":" + (num2 < 10 ? "0" + num2 : num2));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if(count == 0) {
            res.add(sum);
            return;
        }
        
        for(int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);    
        }
    }
    
 * 3.bit manipulation(给跪了，太牛逼了)！！！利用了Integer类型的bitCount方法。由于1、2、4、8、16、32都是2的整数倍，每个数的二进制都只有1个1，所以他们的和的二进制中1
 * 的个数即为参与到相加的数的个数，即亮灯的个数！又因为利用1、2、4、8中某几个数的和可以表示0-11的所有数，分钟同理，因此直接去搜索整个解空间，看哪几个小时的bit之和+分钟的bit
 * 之和等于num（即亮灯个数）
 * public List<String> readBinaryWatch(int num) {  
       List<String> list = new ArrayList<String>();  
       if(num < 0) return list;  
       for(int h=0; h<12; h++){  
           for(int m=0; m<60; m++){  
               if(Integer.bitCount(h) + Integer.bitCount(m) == num){  
               //if (Integer.bitCount(h * 64 + m) == num)
                   list.add(String.format("%d:%02d",h,m));  
               }  
           }  
       }  
       return list;  
    }  
 */
import java.util.*;
public class question401 {
	public List<String> readBinaryWatch(int num) {
        if(num<0 || num>10) {
            return null;
        }
	        
        List<String> possibilities = new ArrayList<>();
        int[] hour_options = new int[]{1,2,4,8};
        int[] minute_options = new int[]{1,2,4,8,16,32};
        for(int i=0; i<=4; i++) {
            int j = num-i;
            if(j > 6) {
                continue;
            }
            if(j < 0) {
                break;
            }
            //decide hour
            List<Integer> hours = findAllCombine(hour_options, hour_options.length-1, i);
            //decide minute
            List<Integer> minutes = findAllCombine(minute_options, minute_options.length-1, j);
            //combine hour and minute
            for(Integer hour : hours) {
            	if(hour >= 12) {
            		continue;
            	}
                for(Integer minute : minutes) {
                	if(minute >= 60) {
                		continue;
                	}
                    if(minute < 10) {
                        possibilities.add(hour + ":0" + minute);
                    }
                    else {
                    	 possibilities.add(hour + ":" + minute);
                    }
                }
            }
        }
        return possibilities;
    }
	 
	 public List<Integer> findAllCombine(int[] nums, int start, int k) {    //choose k elements from nums, start is the kth number's bottom line (only when k>1)
        List<Integer> results = new ArrayList<>();
        if(k == 0) {
            results.add(0);
            return results;
        }
	    
        for(int i=start; i>=k-1; i--) {				
            if(k > 1) {
            	int origin = results.size();
                results.addAll(findAllCombine(nums, i-1, k-1));			//因为第k个数有多种取值可能，所以必须要确定每种取值可能带来的情况个数
                for(int j=origin; j<results.size(); j++) {
                	results.set(j, results.get(j)+nums[i]);
                }
            }
            else {		//k == 1
            	for(int j=0; j<=start; j++) {
            		results.add(nums[j]);
            	}
            	break;
            }
        }
        return results;
    }
}
