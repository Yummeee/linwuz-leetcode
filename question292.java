/*
 * Nim Game (Easy)
 * 
 * You are playing the following Nim Game with your friend: There is a heap of stones on the table, each time one of you take
 * turns to remove 1 to 3 stones. The one who removes the last stone will be the winner. You will take the first turn to 
 * remove the stones.
 * 
 * Both of you are very clever and have optimal strategies for the game. Write a function to determine whether you can win 
 * the game given the number of stones in the heap.
 * For example, if there are 4 stones in the heap, then you will never win the game: no matter 1, 2, or 3 stones you remove, 
 * the last stone will always be removed by your friend.
 */

/*
 * 思路：
 * 1.递归枚举所有情况	//最后会栈溢出...
 * 2.找到规律，Leetcode最简单代码！没有之一，我的思想最近好僵：
 * 4是先手必败局，5-7则先手分别在第一次可以选择1、2、3转换成自己的必胜局(即对手的必败局)，8则无论选1-3任何一个对方都是5-7的必胜局，9-11则可以转换成必胜局，
 * 以此类推，4的倍数时先手必输
 */
public class question292 {
    public boolean canWinNim(int n) {
        if(n%4 == 0) {
        	return false;
        }
        return true;
    }
}
