import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.StringTokenizer;

//WARNING: pri koristenje na sc.next() i sc.nextLine(), moze da se izbagira ako ima uste zborovi vo linijata po sc.next()

public class Main {
	public static void main(String[] args) {
		FastScanner sc = new FastScanner(System.in);
		//TODO: main method
	}
}

class FastScanner {
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public FastScanner(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }
    
    public String nextLine(){
    	try {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
    
    public long nextLong(){
    	return Long.parseLong(next());
    }
    
    public double nextDouble(){
    	return Double.parseDouble(next());
    }
}
