/*
 * Island Perimeter (Easy)
 * 
 * You are given a map in form of a two-dimensional integer grid where 1 represents land and 0 represents water. Grid cells 
 * are connected horizontally/vertically (not diagonally). The grid is completely surrounded by water, and there is exactly 
 * one island (i.e., one or more connected land cells). The island doesn't have "lakes" (water inside that isn't connected 
 * to the water around the island). One cell is a square with side length 1. The grid is rectangular, width and height don't
 * exceed 100. Determine the perimeter of the island.
 */

/*
 * 一般有两种思路：
 * 1.就像我的代码，计算每个1格子贡献边的数量
 * 2.在查重复的内边时，每个格子查自己的左侧和上侧的格子为1的数量（也防止重复查边），最后4*格子总数-2*repeat(比我的方法少比两次)
 * 
 */
public class question463 {
	public int perimeterContributor(int i, int j, int[][] grid) {
        int count = 0;
        if((i-1<0) || grid[i-1][j] == 0) {
            count++;
        }
        if((i+1)==grid.length || grid[i+1][j] == 0) {
            count++;
        }
        if((j-1<0) || grid[i][j-1] == 0) {
            count++;
        }
        if((j+1)==grid[0].length || grid[i][j+1] == 0) {
            count++;
        }
        return count;
    }
    
    public int islandPerimeter(int[][] grid) {
        if(grid.length == 0) {
            return 0;
        }
        if(grid[0].length == 0) {
            return 0;
        }
        
        int perimeter = 0;
        for(int i=0; i<grid.length; i++) {
            for(int j=0; j<grid[0].length; j++) {
                if(grid[i][j] == 1) {
                    perimeter += perimeterContributor(i, j, grid);   //put perimeterContributor's code here will be faster
                }
            }
        }
    
        return perimeter;
    }
}
