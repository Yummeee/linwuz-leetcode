/*
 * Ugly Number (Easy)
 * 
 * Write a program to check whether a given number is an ugly number.
 * 
 * Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. For example, 6, 8 are ugly while 14 is not ugly since it 
 * includes another prime factor 7.
 * Note that 1 is typically treated as an ugly number.
 */
/*
 * 方法2：比我的初始方法判断次数少，效率更高
 * public boolean isUgly(int num) {
    if(num==1) return true;
    if(num==0) return false;
	while(num%2==0) num=num>>1;
	while(num%3==0) num=num/3;
	while(num%5==0) num=num/5;
    return num==1;
}
 */
public class question263 {
	public boolean isUgly(int num) {
        while(num > 1) {
            if(num%2 == 0) {
                num = num>>1;
                continue;
            }
            else if(num%3 == 0) {
                num = num/3;
                continue;
            }
            else if(num%5 == 0) {
                num = num/5;
                continue;
            }
            else {
                break;
            }
        }
        
        return num == 1;
    }
}
