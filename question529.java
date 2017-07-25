/*
 * Minesweeper (Medium)
 * 
 * Let's play the minesweeper game (Wikipedia, online game)!
 * You are given a 2D char matrix representing the game board. 'M' represents an unrevealed mine, 'E' represents
 * an unrevealed empty square, 'B' represents a revealed blank square that has no adjacent (above, below, left,
 * right, and all 4 diagonals) mines, digit ('1' to '8') represents how many mines are adjacent to this revealed
 * square, and finally 'X' represents a revealed mine.
 * Now given the next click position (row and column indices) among all the unrevealed squares ('M' or 'E'), 
 * return the board after revealing this position according to the following rules:
 1.If a mine ('M') is revealed, then the game is over - change it to 'X'.
 2.If an empty square ('E') with no adjacent mines is revealed, then change it to revealed blank ('B') and all of its adjacent unrevealed squares should be revealed recursively.
 3.If an empty square ('E') with at least one adjacent mine is revealed, then change it to a digit ('1' to '8') representing the number of adjacent mines.
 4.Return the board when no more squares will be revealed.
 *
 * Note:
   The range of the input matrix's height and width is [1,50].
   The click position will only be an unrevealed square ('M' or 'E'), which also means the input board contains at least one clickable square.
   The input board won't be a stage when game is over (some mines have been revealed).
   For simplicity, not mentioned rules should be ignored in this problem. For example, you don't need to reveal all the unrevealed mines when the game is over, consider any cases that you will win the game or flag any squares.
 */
/*
 * 思路：
 * DFS或者BFS遍历，BFS的使用队列，DFS若是非递归使用栈。
 */
public class question529 {
	public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0], y = click[1];
        if(x < 0 || y < 0 || x >= board.length || y >= board[0].length) {
            return board;
        }
        if(board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        if(board[x][y] != 'E') {
            return board;
        }
        int mines_num = countMines(board, click);
        if(mines_num == 0) {
            board[x][y] = 'B';
            for(int i = x - 1; i <= x + 1; i++) {
                for(int j = y - 1; j <= y + 1; j++) {
                    board = updateBoard(board, new int[]{i, j});
                }
            }
        }
        else {
            board[click[0]][click[1]] =  (char)(mines_num + 48);
        }
        return board;
    }
	
	public int countMines(char[][] board, int[] click) {
        int x = click[0], y = click[1], count = 0;
        for(int i = x - 1; i <= x + 1; i++) {
            for(int j = y - 1; j <= y + 1; j++) {
                if(i >= 0 && j >= 0 && i < board.length && j < board[0].length && board[i][j] == 'M') {
                    count++;
                }
            }
        }
        return count;
    }
	
	public static void main(String[] args) {
		char[][] board = new char[][] { {'E', 'E', 'E', 'E', 'E'},
										{'E', 'E', 'M', 'E', 'E'},
										{'E', 'E', 'E', 'E', 'E'},
										{'E', 'E', 'E', 'E', 'E'}};
		int[] click = new int[] {3, 0};
		new question529().updateBoard(board, click);
	}
}
