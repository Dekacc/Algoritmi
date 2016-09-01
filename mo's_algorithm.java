//Process the queries offline.
//Do sqrt decomposition on the array
//Sort the queries by the buckets first, and R index second
//Have two indexes l and r, and move them for every query in the sorted list

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
	public static int SQRT;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		String line = br.readLine();
		String[] split = line.split(" ");
		int N = Integer.parseInt(split[0]);
		SQRT = (int) Math.sqrt(N);
		int Q = Integer.parseInt(split[1]);
		
		line = br.readLine();
		split = line.split(" ");
		int[] arr = new int[N];
		for(int i = 0; i<N; i++) arr[i] = Integer.parseInt(split[i]);
		
		Query[] sortedQueries = new Query[Q];
		Query[] unsortedQueries = new Query[Q];
		for(int i = 0; i<Q; i++){
			line = br.readLine();
			split = line.split(" ");
			int L = Integer.parseInt(split[0]);
			int R = Integer.parseInt(split[1]);
			Query qq = new Query(L-1, R-1);
			sortedQueries[i] = qq;
			unsortedQueries[i] = qq;
		}
		Arrays.sort(sortedQueries);
		
		HashMap<Integer, Integer> occurances = new HashMap<>();
		occurances.put(arr[0], 1);
		int total = 0;
		if(arr[0] == 1) total = 1;
		int currentL = 0;
		int currentR = 0;
		for(Query q : sortedQueries){
			//MOVE R
			while(q.R < currentR){
				//move currentR left
				int occ = occurances.get(arr[currentR]);
				if(arr[currentR] == occ) total--;
				else if(arr[currentR] == (occ-1)) total++;
				occurances.put(arr[currentR], occ - 1);
				currentR--;
			}
			while(q.R > currentR){
				//move currentR right
				currentR++;
				Integer occ = occurances.get(arr[currentR]);
				if(occ == null) occ = 0;
				if(arr[currentR] == occ) total--;				
				else if(arr[currentR] == (occ+1)) total++;
				occurances.put(arr[currentR], occ + 1);
			}
			
			//MOVE L
			while(q.L < currentL){
				//move currentL left
				currentL--;
				Integer occ = occurances.get(arr[currentL]);
				if(occ == null) occ = 0;
				if(arr[currentL] == occ) total--;
				else if(arr[currentL] == (occ+1)) total++;
				occurances.put(arr[currentL], occ + 1);
			}
			while(q.L > currentL){
				//move currentL right
				int occ = occurances.get(arr[currentL]);
				if(arr[currentL] == occ) total--;
				else if(arr[currentL] == (occ-1)) total++;
				occurances.put(arr[currentL], occ - 1);
				currentL++;
			}
			q.ans = total;
		}
		for(Query q : unsortedQueries){
			sb.append(q.ans);
			sb.append("\n");
		}
		System.out.print(sb);
	}
}

class Query implements Comparable<Query>{
	int L;
	int R;
	int bucket;
	int ans;
	Query(int l, int r){
		L = l;
		R = r;
		bucket = L / Main.SQRT;
	}
	
	@Override
	public int compareTo(Query q) {
		int dif = bucket - q.bucket;
		if(dif == 0) return R - q.R;
		return dif;
	}
}
