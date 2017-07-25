/*
 * Friend Circles (Medium) 这道题关于indirect的定义说得太不清楚了，如果按照更严格的定义，这题要难好多。简单的定义就是一个人只能属于一个friend circle，相当于找这个图的邻接矩阵的连同分量的个数
 * 
 * There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive
 * in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect 
 * friend of C. And we defined a friend circle is a group of students who are direct or indirect friends. (friend
 * circle严格定义来说其中中任两个人间的好友关系不能隔超过1个人，这个理解貌似是错的)
 * 
 * Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then
 * the ith and jth students are direct friends with each other, otherwise not. And you have to output the total
 * number of friend circles among all the students.
 * 
 * Note:
   N is in range [1,200].
   M[i][i] = 1 for all students.
   If M[i][j] = 1, then M[j][i] = 1.
 */
/*
 * 思路：由于circle的定义其实是简单的定义，这道题就等于找一个图的连通分量个数
 * 1.利用DFS和visited数组来遍历这个数组（实际上是图），visited数组可以通过M[i][i]来替代。时间复杂度O(n)，extra space O(1)
 * 2.使用Union find，并查集来实现。每遇到一个结点，判断其属于现有的哪个union，若都不属于则新建一个union
 */
public class question547 {
	private class Union {
		int count = 0;
		int[] parent, rank;
		public Union(int n) {
			count = n;
			parent = new int[n];
			rank = new int[n];
			for(int i = 0; i < n; i++) {
				parent[i] = i;
			}
		}
		
		public int find(int p) {
			while(p != parent[p]) {
				//parent[p] = parent[parent[p]];    // path compression by halving
				p = parent[p];
			}
			return p;
		}
		
		public void union(int x, int y) {
			int set_x = find(x);
			int set_y = find(y);
			if(set_x == set_y) {
			    return;
			}
			if(rank[set_x] > rank[set_y]) {
				parent[set_y] = set_x;
			}
			else {
				parent[set_x] = set_y;
				if(rank[set_x] == rank[set_y]) {
					rank[set_y]++;
				}
			}
			count--;
		}
	}
	public int findCircleNum(int[][] M) {				//思路2
		Union un = new Union(M.length);
        for(int i = 0; i < M.length; i++) {
        	for(int j = i + 1; j < M.length; j++) {		//must be i + 1, so as to not add duplicate elements to union
        		if(M[i][j] == 1) {
                     un.union(i, j);
                }
        	}
        }
        return un.count;
    }
	
	public int findCircleNum1(int[][] M) {				//思路1
        int circles = 0;
        for(int i = 0; i < M.length; i++) {
            if(M[i][i] == 1) {
                M[i][i] = -1;
                dfs(M, i);
                circles++;
            }
        }
        return circles;
    }
    
    public void dfs(int[][] M, int index) {
        for(int j = 0; j < M.length; j++) {              //N is a square, so M.length == M[0].length. And j cannot start at index + 1, since it may omit some possible paths
            if(M[index][j] == 1 && M[j][j] == 1) {       //student j has not been visited yet and there is a edge between j and index
                M[j][j] = -1;                            //use M[j][j] to indicate that student j has been visited, do not use a visited array, save extra space
                dfs(M, j);
            }
        }
    }
}
