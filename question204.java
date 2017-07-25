/*
 * Count Primes (Easy)
 * 
 * Description:
   Count the number of prime numbers less than a non-negative number, n.
 */
/*
 * 思路：
 * 1.Brute Force，对于小于n的每一个数，判断其是不是质数，判断质数使用Brute Force遍历到sqrt(n)，时间复杂度是O(n^1.5)，超时
 * public int countPrimes(int n) {
        int count = 0;
        for(int i=1; i<n; i++) {
            if(isPrime(i)) {
                count++;
            }
        }
        return count;
    }
	
	public boolean isPrime(int n) {
		if(n == 1) {
            return false;
        }
        if(n == 2) {
            return true;
        }
        
        int threshold = (int)Math.sqrt(n);
        for(int i=2; i<=threshold; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
 * 2.Sieve of Eratosthenes，快速找到所有小于n的素数的算法，时间复杂度O(n)。思路是从p=2开始，将p、2*p、3*p...kp（kp+p>n）标记为非素数，随后从p+1...n
 * 中再找一个未被标记的元素，其一定为素数，同样标记其倍数，不断重复此过程直至所有元素均被标记。原理：若到p时没有被标记，p一定为素数，否则应该是一个较小的数的倍数，
 * 时间复杂度O(NloglogN)
 * 此题的hints不错
 */
public class question204 {
	public int countPrimes(int n) {            //Sieve of Eratosthenes改进
		if(n < 3) {
			return 0;
		}
        int[] isPrime = new int[n];             //0 represents is Prime, 1 represents is not Prime
        int count = n/2;						//由于质数只可能是奇数，所以我们只用遍历奇数就可以了，跳过所有的偶数
        for(int i=3; i*i<n; i+=2) {				//实际上i到sqrt(n)时所有合数已经被标记出来了
            if(isPrime[i] == 0) {               //find a prime number
                for(int j=i*i; j<n; j += 2*i) {	//2*i 3*i 4*i ... (i-1)*i已经被标记过了，所以不用从j=i开始，且j若是j += i，i为奇数则j为偶数，不需要判断，只需要判断j+2i
                	if(isPrime[j] == 0) {		//避免重复，因为现在count是减，所以不能重复标记合数了
                		isPrime[j] = 1; 
                		count--;
                	}
                }
            }
        }
        return count;
    }
	
	public int countPrimes2(int n) {            //Sieve of Eratosthenes
        int[] isPrime = new int[n];             //0 represents is Prime, 1 represents is not Prime
        int count = 0;
        for(int i=2; i<n; i++) {
            if(isPrime[i] == 0) {               //find a prime number
                count++;
                for(int j=2; i*j<n; j++) {
                    isPrime[i * j] = 1; 
                }
            }
        }
        return count;
    }
	
	public int countPrimes1(int n) {			//超时
        int count = 0;
        for(int i=1; i<n; i++) {
            if(isPrime(i)) {
                count++;
            }
        }
        return count;
    }
	
	public boolean isPrime(int n) {
		if(n == 1) {
            return false;
        }
        if(n == 2) {
            return true;
        }
        
        int threshold = (int)Math.sqrt(n);
        for(int i=2; i<=threshold; i++) {
            if(n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
