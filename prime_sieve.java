import java.util.*;
 
public class SieveOfEratosthenes {
    static boolean[] bs; // faster than BitSet because of set() and get()
    static ArrayList<Integer> primes;
    static long size; // this is 10^7 around max it can pass in programming contests.
    // With this we can find in reasonable time primes if a number is a prime if its under 10^14
   
    public static void sieve(long upperbound){
        size = upperbound + 1; // we add 1 to include upperbound
        bs = new boolean[(int) size];
        primes = new ArrayList<>();
       
        for(long i = 2 ; i < size ; i++){
            if(!bs[(int)i]){
                for(long j = i*i ; j < size ; j+=i){
                    bs[(int)j] = true;
                }
                primes.add((int)i);
            }
        }
    }
   
    public static boolean isPrime(long number){
        if(number < 2) return false;
    	  if(number < size) return !bs[(int)number];
       
        int limit = (int)Math.sqrt(number);
        for(Integer prime : primes){
            if(number%prime == 0) return true;
            if(prime > limit) break;
        }
        return false;
    }
 
    public int gcd(int a, int b){ return b == 0 ? a : gcd(b, a%b); }
    public int lcm(int a, int b){ return a * ( b / gcd(b, a%b)); }
   
    public static void main(String[] args) {
        sieve(100000000); // finishes in less then a second
        System.out.println(isPrime(4));
    }
}
