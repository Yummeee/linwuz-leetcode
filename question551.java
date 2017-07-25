/*
 * Student Attendance Record I (Easy)
 * 
 * You are given a string representing an attendance record for a student. The record only contains the following three characters:
 * 'A' : Absent.
   'L' : Late.
   'P' : Present.
 * A student could be rewarded if his attendance record doesn't contain more than one 'A' (absent) or more than two continuous 'L' (late).
 * You need to return whether the student could be rewarded according to his attendance record. 
 */
/*
 * 思路：
 * 1.遍历，时间复杂度O(n)，效率比2高
 * 2.使用Java的matches()方法判断正则表达式，contains不适合A*A
 */

public class question551 {
	public boolean checkRecord1(String s) {
        if(s.length() <= 1) {
            return true;
        }
        
        char[] str = s.toCharArray();
        boolean flag = false;
        for(int i=0; i<str.length; i++) {
            if(str[i] == 'A') {
                if(flag == false) {
                    flag = true;
                }
                else {
                    return false;
                }
            }
            
            if(i<str.length-2 && str[i] == 'L') {
                if(str[i+1] == 'L') {
                    if(str[i+2] == 'L') {
                        return false;
                    }
                    i++;
                }
            }
        }
        return true;
	}
	public boolean checkRecord(String s) {
        return !s.matches(".*A.*A.*") && !s.matches(".*LLL.*");
    }
}
