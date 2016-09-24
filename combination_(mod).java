import java.util.Arrays;

public class Mod {
	/* This function calculates (a^b)%MOD */	
	public static long pow(long a, int b, int MOD){
	    long x=1, y=a; 
	    while(b > 0){
	        if(b%2 == 1){
	            x = (x * y);
	            if(x > MOD) x %= MOD;
	        }
	        y = (y * y);
	        if(y > MOD) y %= MOD; 
	        b /= 2;
	    }
	    return x;
	}
	 
	/*  Modular Multiplicative Inverse
	    Using Euler's Theorem
	    a^(phi(m)) = 1 (mod m)
	    a^(-1) = a^(m-2) (mod m) */
	public static long InverseEuler(long n, int MOD)
	{
	    return pow(n, MOD - 2, MOD);
	}
	
	//can solve C(N, K) % MOD fast for N <= 10^7
	public static long C(int n, int r, int MOD)
	{
	    long[] f = new long[n+1];
	    Arrays.fill(f, 1);
	    for (int i = 2; i <= n; i++) f[i]= (f[i-1]*i) % MOD;
	    
	    return (f[n]*((InverseEuler(f[r], MOD) * InverseEuler(f[n-r], MOD)) % MOD)) % MOD;
	}
	
	public static void main(String[] args){
		//TODO: main method
		System.out.println(C(10000000, 5000000, 1000000007));
	}
}
