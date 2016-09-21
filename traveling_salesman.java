import java.util.Arrays;

/**
*   Held-Karp algorithm for finding shortest route in TSP
*
*   Time complexity: O(2^N * N^2)
*   Space complexity: O(2^n)
*
*   https://en.wikipedia.org/wiki/Held%E2%80%93Karp_algorithm
*   
*   (this code solves for N = 20 in ~2.7 sec on my shitty pc)
*/

public class Costumes {
	static int N;
	static int[][] dp;
	static int[][] distances;
	
    private static int solve(int bit, int pos){
        if(dp[pos][bit] != -1) return dp[pos][bit];
        
        int min = Integer.MAX_VALUE;
        for(int i = 1; i < N; i++){ //(starts with 1 because 0th index is always visited)
            if((bit & (1 << i)) == 0){
                min = Math.min(min, distances[i][pos] + solve(bit | (1 << i), i));
            }
        }
        return dp[pos][bit] = min;
    }

    public static int minimumPath(int[] x, int[] y) {
    	N = x.length;
        int S = 1 << N;
        dp = new int[N][S];
        distances = new int[N][N];
        
        //fill distances matrix
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                distances[i][j] = Math.abs(x[i] - x[j]) + Math.abs(y[i] - y[j]);
            }
        }

        //fill dp with -1
        for(int i = 0; i < N; i++) Arrays.fill(dp[i], -1);
        
        //fill dp with termination cases for the recursion
        for(int i = 0, sm = S-1; i<N; i++) dp[i][sm] = distances[0][i];

        //get solution
        return solve(1, 0);
    }

    public static void main(String[] args){
//        int x[] = {166,866,171,523,614,533,728,262,77,29,730,828,273,704,959,149,630,928,657,69};
//        int y[] = {19,981,324,868,493,564,834,559,23,168,674,894,538,436,251,240,412,994,2,69};
//        long startTime = System.currentTimeMillis();
//        System.out.println(minimumPath(x, y));
//        long endTime   = System.currentTimeMillis();
//        long totalTime = endTime - startTime;
//        System.out.println(totalTime + " ms");
    }
}
