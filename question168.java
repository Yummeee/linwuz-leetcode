/*
 * Excel Sheet Column Title (Easy) (related to 171)
 * 
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * 
 * e.g.,
 * 1 -> A
   2 -> B
   3 -> C
   ...
   26 -> Z
   27 -> AA
   28 -> AB 
 */
/*
 * 思路：
 * 一道纯数学题...找出计算公式即可，不难。唯一的一点陷阱是每次遇到Z的处理。因为Z是26的倍数，所以要对n先减一，再取余。
 *  一个等价于我的方法的方法：
 *  public String convertToTitle(int n) {
        StringBuilder result = new StringBuilder();

        while(n>0){
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }
    此等价方法可以转换为递归方法：
    public String convertToTitle(int n) {
    	return n == 0 ? "" : convertToTitle(--n / 26) + (char)('A' + (n % 26));
    }
 */
public class question168 {
	public String convertToTitle(int n) {
        if(n == 0) {
            return null;
        }
        String title = "";
        do {
            int residue = n%26;
            if(residue == 0) {
                title = "Z"+title;
                n = n/26 - 1;
            }
            else {
                title = String.valueOf((char) ('A' + residue - 1)) + title;
                 n = n/26;
            }
        } while(n > 0);
        return title;
    }
}
