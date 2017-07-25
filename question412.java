import java.util.*;

/*
 * Fizz Buzz (Easy)
 * 
 * Write a program that outputs the string representation of numbers from 1 to n.
 * But for multiples of three it should output “Fizz” instead of the number and for the multiples of five output “Buzz”. 
 * For numbers which are multiples of both three and five output “FizzBuzz”.
 * 
 * n = 15,
   Return:
   [
      "1",
      "2",
      "Fizz",
      "4",
      "Buzz",
      "Fizz",
      "7",
      "8",
      "Fizz",
      "Buzz",
      "11",
      "Fizz",
      "13",
      "14",
      "FizzBuzz"
    ]
 */

/*
 * 很简单的一道题，但有些细节需要注意，效率！
 * 如果选择求余运算，我们想要求余运算的次数尽可能少，但我编写的方法却可能最多判断3次才输出，我们可以做到平均判断2次就输出其实
 	if (i % 3 == 0)
    {
     	if(i % 5 == 0)
     		printf("FizzBuzz\n");
    	else
            printf("Fizz\n");
    }
    else if (i % 5 == 0)
    {
    	printf("Buzz\n");
    }
    else
   	{
		printf("%d\n", i);
	}
 * 
 */
public class question412 {
	public List<String> fizzBuzz(int n) {
        List<String> result = null;
        if(n<1) {       //just in case that n does not fit request
            return result;
        }
        
        result = new ArrayList<String>();
        for(int i=1; i<=n; i++) {
            if(i%3==0 && i%5==0) {      //multiples of 3 and 5
                result.add("FizzBuzz");
            } 
            else if(i%3 == 0) {         //multiples of 3
                result.add("Fizz");
            }
            else if(i%5 == 0) {         //multiples of 5
                result.add("Buzz");
            }
            else {
            	result.add(new Integer(i).toString());   //faster
                //result.add("" + i);
            }
        }
        return result;
    }

}
