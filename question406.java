/*
 * Queue Reconstruction by Height (Medium) 之前是个hard难度，改成了medium难度...所以做了2个小时...
 * 
 * Suppose you have a random list of people standing in a queue. Each person is described by a pair of integers (h, k), where
 * h is the height of the person and k is the number of people in front of this person who have a height greater than or equal
 * to h. Write an algorithm to reconstruct the queue.
 * 
 * Note:
   The number of people is less than 1,100.
 * Example:
 * Input:    [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
   Output:   [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]  
 */
/*
 * 思路：先将个子高的位置排好后，再怎么对个子矮的排，都不会影响个子高的人的相对位置
 * 1.首先对输入数组进行两次排序，第一次对数组按k值进行从低到高的排列，第二次对数组按h值进行从高到低的排列，这样由于arrays.sort是稳定排序，输入数组不仅在身
 * 高h上是降序的，同时相同h的元素间，其k值是保持升序的。之所以要k值保持升序是因为这不仅符合直观思考（即相同身高的人，在重构的结果中，k值小的应该在前，也就确定了相同h的元素间的相对顺序），且若
 * 相同h下k值大的在前，其先找到一个合适的插入位置，则其可能需要在k值小的元素插入后调整自己的位置。即无法保证对于一个排序后的元素i，其最终的重构位置一定是
 * 在[0,i]下标之间的。之所以要对身高h从大到小排序而不是从小到大排序，则是因为在k保证相同h下升序时一个元素i的重构后的位置只可能是在[0,i]的，不可能向后，因为
 * 后面的元素只会比自己小，往后找不会降低自己的k值；而前面的元素只会不小于元素i，往前找自己的k值才能找到满意的位置。如果i<k，则输入一定是invalid的。在进行
 * 完二次排序后，则对每个元素，从数组起始一直遍历到当前元素的位置，遇到一个不小于自己的元素，则对count=k减1，当count为0时，说明已经找到了该元素在目前遍历
 * 的元素中的正确位置（此局部最优解同时也是全局最优解，无需再次调整，如果不对k值排序还需调整）。将元素插入该位置并后移部分元素即可。时间复杂度在最坏的情况下
 * 为O(n^2)，即类似[1,0],[2,0],[3,0],[4,0]...这种情况，对于元素i每次找到位置后都要移动i位。extra space O(1)
 * 
 * 我的思路的简洁代码，实际上两个排序可以用一个comparator实现...且用ArrayList存结果就不需要写那么多移动的代码：
 * public int[][] reconstructQueue(int[][] people) {
        if (people == null || people.length == 0 || people[0].length == 0)
            return new int[0][0];
            
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                if (b[0] == a[0]) return a[1] - b[1];	//身高相同，按k升序
                return b[0] - a[0];					//身高不同，降序
            }
        });
        
        int n = people.length;
        ArrayList<int[]> tmp = new ArrayList<>();
        for (int i = 0; i < n; i++)
            tmp.add(people[i][1], new int[]{people[i][0], people[i][1]});			//之前的排序导致先将个子高的位置排好后，再怎么对个子矮的排，都不会影响个子高的人的相对位置

        int[][] res = new int[people.length][2];
        int i = 0;
        for (int[] k : tmp) {
            res[i] = k;
            i++;
        }
        return res;
    }
    
 * 实际上也可以按身高的升序，同一身高下k的降序来排列。这样每次循环找当前数组中第一次遇到的k值最小的元素，顺序地放入一个新的数组中即可。   
 * 
 */
import java.util.*;
public class question406 {
	public int[][] reconstructQueue(int[][] people) {
        if(people == null || people.length <= 1) {
            return people;
        }
        
        Arrays.sort(people, new Comparator<int[]>() {       
            @Override
            public int compare(int[] x, int[] y) {		//即按k从低到高排列	，稳定排序
                return x[1] - y[1];
            }
        });
        Arrays.sort(people, new Comparator<int[]>() {      
            @Override
            public int compare(int[] x, int[] y) {			//即按人的身高h从高到低排列，这样既保证身高有序，由于是稳定的排序，同时保证身高相同的人对于k也有序。k小的人理应在同样身高k大的人前面，这样才能保证随后的插入一定局部最优也是全局最优，不然每插入一个身高h的人还需要调整其余已插入的身高为h的人
                return y[0] - x[0];
            }
        });
        for(int i = 0; i < people.length; i++) {
            int count = people[i][1];       
            for(int j = 0; j <= i; j++) {    //由于首先对k排了序，所以不可能出现j超出i范围的情况
                if(count == 0) {            //找到插入位置j，需要将people[j]到people[i-1]的元素后移一位
                    int[] temp = people[i];
                    for(int k = i - 1; k >= j; k--) {
                        people[k+1] = people[k];
                    }
                    people[j] = temp;
                    break;
                }
                else {
                    if(people[j][0] >= people[i][0]) {
                        count--;
                    }
                }
            }
        }
        return people;
    }
	
	public static void main(String[] args) {
		int[][] people = {{7,0}, {4,4}, {7,1}, {5,2}, {6,1}, {5,0}};
		new question406().reconstructQueue(people);
	}
}
