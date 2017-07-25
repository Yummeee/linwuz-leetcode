/*
 * Fraction Addition and Subtraction (Medium)
 * 
 * Given a string representing an expression of fraction addition and subtraction, you need to return the 
 * calculation result in string format. The final result should be irreducible fraction. If your final result 
 * is an integer, say 2, you need to change it to the format of fraction that has denominator 1. So in this
 * case, 2 should be converted to 2/1.
 * 
 * Example 1:
   Input:"-1/2+1/2"
   Output: "0/1"
 * Example 2:
   Input:"-1/2+1/2+1/3"
   Output: "1/3"
 * Example 3:
   Input:"1/3-1/2"
   Output: "-1/6" 
 * Example 4:
   Input:"5/3+1/3"
   Output: "2/1"   
 * Note:
   1.The input string only contains '0' to '9', '/', '+' and '-'. So does the output.
   2.Each fraction (input and output) has format ±numerator/denominator. If the first input fraction or the output is positive, then '+' will be omitted.
   3.The input only contains valid irreducible fractions, where the numerator and denominator of each fraction will always be in the range [1,10]. If the denominator is 1, it means this fraction is actually an integer in a fraction format defined above.
   4.The number of given fractions will be in the range [1,10].
   5.The numerator and denominator of the final result are guaranteed to be valid and in the range of 32-bit int.
 */
/*
 * 思路：
 * 1.每次算两个分数的和，其中第一个分数为之前所有分数的和，第二个分数为新读取的一个分数。一开始将第一个分数置为0/1，并将减法视为加上一个负数，
 * 将所有运算均视作加法运算，并将计算结果约分（求最大公约数）。两个数的最小公倍数lca(a, b) = a*b / gcd(a, b)
 * 2.一次保留所有分数以及其符号，并找出所有分母的最小公倍数，随后一次计算出最后的结果，编辑解法1，也是我最初的想法。
 * 
 * 一种非常简洁的算法：
 * public String fractionAddition(String expression) {
    String[] fracs = expression.split("(?=[-,+])"); // splits input string into individual fractions
    String res = "0/1";
    for (String frac : fracs) res = add(res, frac); // add all fractions together
    return res;
	}

	public String add(String frac1, String frac2) {
	    int[] f1 = Stream.of(frac1.split("/")).mapToInt(Integer::parseInt).toArray(), 
	          f2 = Stream.of(frac2.split("/")).mapToInt(Integer::parseInt).toArray();
	    int numer = f1[0]*f2[1] + f1[1]*f2[0], denom = f1[1]*f2[1];
	    String sign = "";
	    if (numer < 0) {sign = "-"; numer *= -1;}
	    return sign + numer/gcd(numer, denom) + "/" + denom/gcd(numer, denom); // construct reduced fraction
	}
	
	// Computes gcd using Euclidean algorithm
	public int gcd(int x, int y) { return x == 0 || y == 0 ? x + y : gcd(y, x % y); }
 */
public class question592 {
	public String fractionAddition(String expression) {				//思路1
        String res = "";
        if(expression == null || expression.length() == 0) {
            return res;
        }
        
        long[] operand1 = new long[2];                              //numerator <=> operand[0], denominator <=> operand[1]
        long[] operand2 = new long[2];
        operand1[0] = 0;                                            //regard '-' op as '+' a negative number 
        operand1[1] = 1;
        int i = 0;
        while(i < expression.length()) {
            int index1 = expression.indexOf('/', i);
            operand2[0] = Long.valueOf(expression.substring(i, index1));            //read numerator
            int j = index1 + 1;
            for(; j < expression.length(); j++) {
                if(expression.charAt(j) == '+' || expression.charAt(j) == '-') {
                    break;
                }
            }
            int index2 = j; 
            operand2[1] = Long.valueOf(expression.substring(index1 + 1, index2));   //read denominator
            //calculate the addition result of operand1 and operand2
            operand1[0] = operand1[0] * operand2[1] + operand2[0] * operand1[1];
            operand1[1] = operand1[1] * operand2[1];
            long gcd_value = gcd(Math.abs(operand1[0]), Math.abs(operand1[1]));
            operand1[0] /= gcd_value;
            operand1[1] /= gcd_value;
            //update i
            i = index2;
        }
        res = String.valueOf(operand1[0]) + "/" + String.valueOf(operand1[1]);
        return res;
    }
	
	public long gcd(long x, long y) {                                //find the greatest common divisor between x and y
        return x == 0 || y == 0 ? x + y : gcd(y, x % y);
    }
	
	public static void main(String[] args) {
		String exp = "-1/3+1/2-1/2";
		for(String str : exp.split("(?=[-,+])"))
			System.out.println(str);
	}
}
