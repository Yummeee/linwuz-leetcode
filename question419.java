/*
 * Battleships in a Board (Medium)
 * 
 * Given an 2D board, count how many battleships are in it. The battleships are represented with 'X's, empty slots are 
 * represented with '.'s. You may assume the following rules:
 * 1.You receive a valid board, made of only battleships or empty slots.
 * 2.Battleships can only be placed horizontally or vertically. In other words, they can only be made of the shape 1xN 
 * (1 row, N columns) or Nx1 (N rows, 1 column), where N can be of any size.
 * 3.At least one horizontal or vertical cell separates between two battleships - there are no adjacent battleships.
 * 
 * Example:
 * X..X
   ...X
   ...X
   In the above board there are 2 battleships.
 * Invalid Example:
 * ...X
   XXXX
   ...X
   This is an invalid board that you will not receive - as battleships will always have a cell separating between them.
 *  
 * Follow up:
   Could you do it in one-pass, using only O(1) extra memory and without modifying the value of the board?
 */
/*
 * 思路：
 * 1.每当遍历到一个新的battleship（水平方向或者垂直方向），将此battleship的所有X置为'.'之后（防止battleship重复计算）继续遍历。时间复杂度O(n^2 + 所有'X'的个数)，即O(n^2)，空间复杂度O(n^2)，extra space: O(1)，属于DFS算法
 * 1).设置一个与矩阵同样大小的布尔数组visited，用于表示某一个位置是否已经被访问过 
   2).遍历矩阵的每一个位置，如果是标志船的’X’并且该位置没有被访问过，就增加船的数目，然后通过DFS算法把整个船找到，并且把船的所有位置都设为已访问。 
   3).最后返回找到的船的数目。
 * 2.我的思路，参照follow up的要求，遍历一遍二维数组。若当前结点前方是边界或者'.'，count++；若当前结点上方是'X'，本身也是'X'，则count--，因为这是同一个
 * 垂直的battleship，而该battleship的第一个点已经统计了该船，随后该船的点又被统计，这时要删掉。即本质是所有的船都从水平角度统计，对于垂直的船则去除重复
 * 统计的部分。时间复杂度O(n^2)，空间复杂度O(1)
 * 
 * 思路2改进，实际上我们就统计一个船的第一个cell，即其top-left结点，当非边界点的左边和上边都是'.'的时候，就是一艘新船。基本等价于我的思路，就是将我的第二个
 * 的if条件放到第一个里面就是
 * 
 */
public class question419 {
	public int countBattleships(char[][] board) {									//思路2改进
        if(board == null || board.length == 0 || board[0].length == 0) {            
            return 0;
        }
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
            	//if(board[i][j]=='X' && (i==0 || board[i-1][j]!='X') && (j==0 || board[i][j-1]!='X')) count++;
            	if(board[i][j] == '.') {
            		continue;
            	}
            	if(j > 0 && board[i][j-1] == 'X') {
            		continue;
            	}
            	if(i > 0 && board[i-1][j] == 'X') {
            		continue;
            	}
            	count++;
            }
        }
        return count;
	}
	
	public int countBattleships1(char[][] board) {									//思路2
        if(board == null || board.length == 0 || board[0].length == 0) {            //maybe useless, since it may counted as invalid board
            return 0;
        }
        
        int count = 0;
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < board[0].length; j++) {
            	if((j == 0 && board[i][j] == 'X') || (j>0 && board[i][j-1] == '.' && board[i][j] == 'X')) {
                    count++;
                }
                if(i > 0 && (board[i-1][j] == 'X' && board[i][j] == 'X')) {          //this one has already been counted in last loop, this loop if for eliminating multi-count vertical battleships
                    count--;
                }
            }
        }
        return count;
    }
}
