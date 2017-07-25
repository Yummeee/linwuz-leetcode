/*
 * Heaters (Easy)
 * 
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the 
 * houses.
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all 
 * houses could be covered by those heaters.
 * So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius
 * standard of heaters.
 * 
 * Note:
   Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
   Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
   As long as a house is in the heaters' warm radius range, it can be warmed.
   All the heaters follow your radius standard and the warm radius will the same.
 * e.g.,
 * Input: [1,2,3],[2]
   Output: 1
   Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
   Input: [1,2,3,4],[1,4]
   Output: 1
   Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
 */
/*
 * 思路：二分搜索的变种，妈的果然题目没有说houses和heaters不一定有序，需要先排序...且radius可以为0...本房子出就为0
 * 我的方法：首先对houses（实际上houses可以不排序）和heaters排序使其有序，随后对每一个houses[i]，在heaters中用二分搜索找其位置。其位置一共有四种情况：
 * 1)pos为非负数，说明houses[i]处有heaters[pos]，不管半径是多少，这个点都一定会被覆盖，不用再考虑
 * 剩下三种情况为pos为负数，说明houses[i]位置处没有heater，这个点需要被radius覆盖到才行
 * 2)pos == 0，说明houses[i]在所有heater的左侧，这样离houses[i]最近的heater一定是heaters[pos]，即heater[0]，只要radius不小于heaters[pos] - houses[i]
 * 就一定能覆盖这个点，因此取radius = max(radius, heaters[pos] - houses[i]);
 * 3)pos == heaters.length，与第2种情况正好相反，houses[i]在所有heater的右侧，这样离houses[i]最近的heater一定是heater[pos-1]，即heaters[heaters.length-1]
 * 只要radius不小于houses[i] - heaters[pos-1]就一定能覆盖这个点，因此取radius = max(radius, houses[i] - heaters[pos-1]);
 * 4)pos > 0且pos < heaters.length，此时houses[i]夹在heaters[pos]与heaters[pos-1]之间，这时需要比较这两个heater与houses[i]间的距离，取最小
 * 者，只要radius不小于这个最小者一定可以覆盖这个点。
 * 总的来说思路就是radius只能变大，找到让所有点都被最近的heater覆盖的最小radius。即找到每个屋子距离最近的加热器，记录其位置差，所有的位置差里面最长的那一个就是最小的加热器半径了
 * 
 * 改进1：当第三种情况发生时，由于houses已经被排序，所有houses[i]到houses[houses.length-1]一定都会是这种情况，这时直接找houses[houses.length-1]
 * 和heaters[pos-1]比即可，radius一定要不小于它们的差
 * 改进2：放弃改进1，这样houses其实可以不被排序，精简代码：
 * public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(heaters);
        int result = Integer.MIN_VALUE;
        
        for (int house : houses) {
            int index = Arrays.binarySearch(heaters, house);
            if (index < 0) {
        	index = -(index + 1);
            }
            int dist1 = index - 1 >= 0 ? house - heaters[index - 1] : Integer.MAX_VALUE;
            int dist2 = index < heaters.length ? heaters[index] - house : Integer.MAX_VALUE;
        
            result = Math.max(result, Math.min(dist1, dist2));
        }
        
        return result;
    }
 * 改进3：
 * 将houses和heaters均排序，然后两个指针遍历一遍houses和heaters，找到每个houses最近的heaters并记录其距离差，求距离差的最大值，时间复杂度除排序外从O(mlogn)降为O(m+n)
 *  public int findRadius(int[] houses, int[] heaters) {		//改进3
		int radius = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int j = 0;				//heater的指针，实际上找到一个house的位置后，下一个house只需要继续往后找
        for(int i=0; i<houses.length; i++) {
        	while(j < heaters.length-1 && Math.abs(heaters[j + 1] - houses[i]) <= Math.abs(heaters[j] - houses[i])) {		//说明heaters[j]和heaters[j+1]都在houses[i]左侧，或者在j和j+1之间，但更靠近j+1
        		j++;
        	}
        	radius = Math.max(radius, Math.abs(heaters[j] - houses[i]));
        }
        return radius;
	}
 */
import java.util.*;
public class question475 {
	public int findRadius(int[] houses, int[] heaters) {		//改进3
		int radius = 0;
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int j = 0;				//heater的指针，实际上找到一个house位于heater的位置后，下一个house只需要继续往后找，因为houses也是有序的
        for(int i=0; i<houses.length; i++) {
        	while(j < heaters.length && houses[i] > heaters[j]) {
        		j++;
        	}
        	if(j == 0) {
        		radius = Math.max(radius, heaters[j] - houses[i]);
        	}
        	else if(j == heaters.length) {
        		radius = Math.max(radius, houses[houses.length-1] - heaters[j-1]);
        	}
        	else {
        		radius = Math.max(radius, Math.min(houses[i] - heaters[j-1], heaters[j] - houses[i]));
        	}
        }
        return radius;
	}
	
	public int findRadius1(int[] houses, int[] heaters) {		//我的方法的改进2
        int radius = 0;
        //Arrays.sort(houses);
        Arrays.sort(heaters);
        for(int i=0; i<houses.length; i++) {
            int pos = Arrays.binarySearch(heaters, houses[i]);
            if(pos < 0) {			//说明此房子位置处没有heater
                pos = -(pos + 1);
                //houses[i]只可能在headters[pos]左边，除非其比所有heaters都大
                if(pos > 0) {
                    if(pos == heaters.length) {                 //此房子在所有heaters右侧
                        radius = Math.max(radius, houses[i] - heaters[pos-1]);
                    	//radius = Math.max(radius, houses[houses.length-1] - heaters[pos-1]); 如果houses有序的话
                    	//break;
                    }
                    else {          //此房子夹在两个heaters之间
                        int temp = Math.min(heaters[pos] - houses[i], houses[i] - heaters[pos-1]);
                        radius = Math.max(radius, temp);
                    }
                }
                else {          //此房子在所有heaters左侧
                    radius = Math.max(radius, heaters[pos] - houses[i]);
                }
            }
            else {			//此房子出有heater，不管多少radius都会被覆盖
                continue;
            }
        }
        return radius;
    }
}
