import java.math.BigInteger;
import java.util.ArrayList;

public class Main {	
	//Basic formula calculation, with a simple cancelation of the bigger term in the denominator (FASTEST)
	//Time complexity: O(min(K, N-K))
	//
	//Overflows on N = 30 (use combination2 for N > 29)  <--CAREFUL!
	public static long combination1(int N, int K){
		int min = Math.min(N-K, K);
		long broitel = 1;
		long imenitel = 1;
		for(int i = 1; i <= min; i++){
			imenitel *= i;
			broitel *= N-i+1;
		}
		return broitel / imenitel;
	}
	
	//Divides on every step, so it doesn't overflow so fast, it is slower though
	//Time complexity: O(min(K, N-K))
	//
	//Overflows on N = 62 (use combination3 for N > 62)  <--CAREFUL!
	public static long combination2(long N, long K){
	    K = Math.min(N-K, K);
	    long r = 1;
	    for(int i = 1; i <= K; i++){
	        r *= N--;
	        r /= i;
	    }
	    return r;
	}
	
	//BigInteger implementation of the combination1 function (VERY SLOW)
	//Time compexity: O(min(K, N-K))
	//
	//Doesn't overflow, but it's very slow, use only for N < 100, if N > 100 use combination4
	public static BigInteger combination3(int N, int K){
		int min = Math.min(N-K, K);
		BigInteger broitel = BigInteger.ONE;
		BigInteger imenitel = BigInteger.ONE;
		for(int i = 1; i <= min; i++){
			imenitel = imenitel.multiply(BigInteger.valueOf(i));
			broitel = broitel.multiply(BigInteger.valueOf(N-i+1));
		}
		return broitel.divide(imenitel);
	}
	
	//PRIME SIEVE USED IN combination4
	static boolean[] bs;
	static ArrayList<Integer> all_primes;
	static long size;
	public static void sieve(long upperbound){
        size = upperbound + 1; // we add 1 to include upperbound
        bs = new boolean[(int) size];
        all_primes = new ArrayList<>();
       
        for(long i = 2 ; i < size ; i++){
            if(!bs[(int)i]){
                for(long j = i*i ; j < size ; j+=i){
                    bs[(int)j] = true;
                }
                all_primes.add((int)i);
            }
        }
    }
	//PRIME SIEVE CODE ENDS
	
	//Implementation with prime factor decomposition. VERY FAST for big values of N.
	//(Source: Computing Binomial Coefficients, P. Goetgheluck)
	//Requires a list of all primes less than N (generated once by the sieve)
	//REMEMBER TO CALL SIEVE FUNCTION BEFORE CALLING COMBINATION4, ALSO MAKE SURE ALL PRIMES LESS THAN N ARE GENERATED
	public static BigInteger combination4(int N, int K){
		if ((K == 0) || (K == N)) return BigInteger.ONE;
		K = Math.min(N-K, K);
	    int nk = N - K;
	    int rootN = (int) Math.sqrt(N);
	    int n_half = N/2;
	    ArrayList<Integer> exponents = new ArrayList<>();
	    
	    for(int prime : all_primes){
	    	if(prime > N) break;
	    	
	    	if (prime > nk) exponents.add(1);
	    	else if (prime > n_half) exponents.add(0);
	    	else if (prime > rootN){
	        	if (N % prime < K % prime) exponents.add(1);
	        	else exponents.add(0);
	        }
	    	else{
	    		int r = 0, tmpN = N, tmpK = K, a, b, total = 0;
	        
	    		while(tmpN > 0){
	    			a = tmpN % prime;
	    			b = (tmpK%prime) + r;
	    			tmpN /= prime;
	    			tmpK /= prime;
	        	
	    			if(a<b){
	    				total++;
	    				r = 1;
	    			}
	    			else r = 0;
	    		}
	    		exponents.add(total);
	    	}
	    }
	    
	    //joining all prime factors in one BigInteger
	    BigInteger R = BigInteger.ONE;
	    for(int i = 0, len = exponents.size(); i<len; i++){
	    	int exp = exponents.get(i);
	    	if(exp > 0) R = R.multiply(BigInteger.valueOf((long) Math.pow(all_primes.get(i), exp)));
	    }
	    
	    return R;
	}
	
	public static void main(String[] args) {
//		long startTime = System.currentTimeMillis();

		int N = 10000000;
		sieve(N);
		System.out.println(combination4(N, N/2));
		
//      long endTime   = System.currentTimeMillis();
//      long totalTime = endTime - startTime;
//      System.out.println(totalTime + " ms");
	}
}
