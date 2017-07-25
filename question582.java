/*
 * Kill Process (Medium)
 * 
 */
/*
 * 思路：
 * 使用queue不断找到被杀进程的所有孩子进程，直至队列为空为止。
 */

public class question582 {
	int lily;
	char lucy;
	
	public question582(int number, char letter) {
		lily = number;
		lucy = letter;
	}
	public static void main(String[] args) {
		question582 test = new question582(1, 'c'); 		//实例化对象
		System.out.println(test.lily);
		System.out.println(test.lucy);
	}
}
