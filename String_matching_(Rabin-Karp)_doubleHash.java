import java.io.IOException;
import java.io.InputStream;
import java.util.InputMismatchException;

public class Solution {
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
   
    public static int hash(String s, int smallPrime, int bigPrime){
        long h = 0;
        for(int i = 0, len = s.length(); i < len; i++){
            h *= smallPrime;
            h += s.charAt(i);
            h %= bigPrime;
        }
        return (int)h;
    }
	
	private static int search(String text, String pattern) {
		int plength = pattern.length();
		int tlength = text.length();
		
        //idiot proof
        if(plength > tlength) return 0;
           
        int SMALL_PRIME = 103;
        int BIG_PRIME = 1000003;
        int BIG_PRIME2 = 1000033;
		int SMALL_PRIME2 = 101;
        
        int pwr = (int)pow(SMALL_PRIME, plength - 1, BIG_PRIME);
        int pwr2 = (int)pow(SMALL_PRIME2, plength - 1, BIG_PRIME2);
        int phash = hash(pattern, SMALL_PRIME, BIG_PRIME); //pattern hash
        int phash2 = hash(pattern, SMALL_PRIME2, BIG_PRIME2); //pattern hash
        int fhash = hash(text.substring(0, plength), SMALL_PRIME, BIG_PRIME); //first hash
        int fhash2 = hash(text.substring(0, plength), SMALL_PRIME2, BIG_PRIME2); //first hash
           
        int counter = 0;
           
        if(phash == fhash && phash2 == fhash2){
            counter++;
        }
     
        //Rabin-Karp alg
        for(int i = 0, len = text.length() - plength; i < len; i++){
            fhash -= (text.charAt(i) * pwr)%BIG_PRIME;
            fhash *= SMALL_PRIME;
            fhash += text.charAt(i + plength);
            fhash = (fhash % BIG_PRIME + BIG_PRIME) % BIG_PRIME;
     
            fhash2 -= (text.charAt(i) * pwr2)%BIG_PRIME2;
            fhash2 *= SMALL_PRIME2;
            fhash2 += text.charAt(i + plength);
            fhash2 = (fhash2 % BIG_PRIME2 + BIG_PRIME2) % BIG_PRIME2;
            
            if(fhash == phash && fhash2 == phash2){
                counter++;
            }
        }
        return counter;
    }
	
	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		FastScanner2 sc = new FastScanner2(System.in);
		int N = sc.nextInt();
		String[] pattern = new String[N];
		int[] weight = new int[N];
		for(int i = 0; i<N; i++){
			pattern[i] = sc.next();
			weight[i] = sc.nextInt();
		}
		
		String text = sc.next();
		long total = 0;
		for(int i = 0; i<N; i++){
			total += weight[i] * search(text, pattern[i]);
		}
		System.out.println(total);
        long endTime   = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println(totalTime + " ms");
	}
}

class FastScanner2{
    private InputStream stream;
    private byte[] buf = new byte[1024];
    private int curChar;
    private int numChars;
    private SpaceCharFilter filter;

    public FastScanner2(InputStream stream)
    {
        this.stream = stream;
    }

    public int read()
    {
        if (numChars == -1)
            throw new InputMismatchException();
        if (curChar >= numChars)
        {
            curChar = 0;
            try
            {
                numChars = stream.read(buf);
            } catch (IOException e)
            {
                throw new InputMismatchException();
            }
            if (numChars <= 0)
                return -1;
        }
        return buf[curChar++];
    }

    public int nextInt()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-')
        {
            sgn = -1;
            c = read();
        }
        int res = 0;
        do
        {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }

    public String next()
    {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        StringBuilder res = new StringBuilder();
        do
        {
            res.appendCodePoint(c);
            c = read();
        } while (!isSpaceChar(c));
        return res.toString();
    }
    
    public String nextLine()
    {
        int c = read();
        StringBuilder res = new StringBuilder();
        do
        {
            res.appendCodePoint(c);
            c = read();
        } while (!isLineEndChar(c));
        return res.toString();
    }    
    
    public double nextDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        double res = 0;
        while (!isSpaceChar(c) && c != '.') {
            if (c == 'e' || c == 'E')
                return res * Math.pow(10, nextInt());
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        }
        if (c == '.') {
            c = read();
            double m = 1;
            while (!isSpaceChar(c)) {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, nextInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                m /= 10;
                res += (c - '0') * m;
                c = read();
            }
        }
        return res * sgn;
    }
    
    public long nextLong() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
        if (c == '-') {
            sgn = -1;
            c = read();
        }
        long res = 0;
        do {
            if (c < '0' || c > '9')
                throw new InputMismatchException();
            res *= 10;
            res += c - '0';
            c = read();
        } while (!isSpaceChar(c));
        return res * sgn;
    }
    
    public boolean isSpaceChar(int c)
    {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
    }
    
    public boolean isLineEndChar(int c)
    {
        if (filter != null)
            return filter.isSpaceChar(c);
        return c == '\n' || c == '\r' || c == -1;
    }

    public interface SpaceCharFilter
    {
        public boolean isSpaceChar(int ch);
    }
}
