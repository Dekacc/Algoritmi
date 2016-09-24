public class Lucas {
	//Calculate C(N, K) % P for prime P in O(P)
	static long SmallC(int n, int r, int MOD)
	{
	    long[][] c = new long[2][r+1];
	 
	    for (int i=0; i<=n; i++)
	    {
	        for (int k=0; k<=r && k<=i; k++)
	            if (k==0 || k==i)
	                c[i&1][k] = 1;
	            else
	                c[i&1][k] = (c[(i-1)&1][k-1] + c[(i-1)&1][k])%MOD;
	    }
	    return c[n&1][r];
	}
	
	static long C(int n, int m, int p)
	{
	    if (n==0 && m==0) return 1;
	    int ni = n % p;
	    int mi = m % p;
	    if (mi>ni) return 0;
	    return C(n/p, m/p, p) * SmallC(ni, mi, p);
	}
	
	public static void main(String[] args) {
		System.out.println(C(10000000, 5000000, 1000000007));
	}
}
