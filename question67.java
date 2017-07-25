/*
 * Add Binary (Easy)
 * 
 * Given two binary strings, return their sum (also a binary string).
 * For example,
 * a = "11"
 * b = "1"
 * Return "100".
 */
/*
 * 思路：
 * 1.将每一位字符转换为int然后按位遵循二进制加法规则计算，可以提高效率的一点就是当一个数并另一个长，且carry=0时可以不用计算之后的值了。求carry和本位的值还总是可以用%base，/base来计算
 * 2.将整个数转换为int然后使用半加法计算，用此方法有溢出风险，无论选择long还是int都一样，因为string的长度可以是无限的，test case也已证明此想法。不可取
 */
public class question67 {
	public String addBinary(String a, String b) {			//思路1解法
        StringBuilder res = new StringBuilder();
        /*
        if(a == null || b == null || a.length() == 0 || b.length() == 0) {
            return res.append("").toString();
        }
        */
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while(i >= 0 || j >= 0) {
            int p = i >= 0 ? a.charAt(i--) - '0' : 0;
            int q = j >= 0 ? b.charAt(j--) - '0' : 0;
            res.append(p ^ q ^ carry);				//求carry和本位的值还总是可以用%base，/base来计算
            carry = p & q | p & carry | q & carry;
        }
        if(carry == 1) {
            res.append(carry);
        }
        return res.reverse().toString();
    }
	
	public String addBinary1(String a, String b) {			//思路1解法改进，算到需要计算的地方
        StringBuilder res = new StringBuilder();
        int shorter = Math.min(a.length(), b.length());
        int longer = Math.max(a.length(), b.length());
        String longer_str = longer == a.length() ? a : b;
        String shorter_str = shorter == b.length() ? b : a;
        int i = 0, carry = 0;
        while(i < shorter) {
            int p = longer_str.charAt(longer - i - 1) - '0';
            int q = shorter_str.charAt(shorter - i - 1) - '0';
            res.append(p ^ q ^ carry);				//求carry和本位的值还总是可以用%base，/base来计算
            carry = p & q | p & carry | q & carry;
            i++;
        }
        i = longer - shorter - 1;
        while(i >= 0 && carry == 1) {
        	int p = longer_str.charAt(i--) - '0';
        	res.append(p ^ carry);	
        	carry = p & carry;
        }
        res = res.reverse();
        if(i >= 0) {
        	res.insert(0, longer_str.substring(0, i+1));
        }
        else if(carry == 1) {
            res.insert(0, carry);
        }
        return res.toString();
    }
	/*
	public String addBinary(String a, String b) {		//思路2解法
        long num1 = Long.parseLong(a, 2);
        long num2 = Long.parseLong(b, 2);
        
        while(num2 != 0) {
            long carry = num1 & num2;
            num1 = num1 ^ num2;
            num2 = carry << 1;
        }
        return Long.toString(num1, 2);
    }
    */
}
