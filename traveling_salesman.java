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
	static int[][] next; //for path keeping (DELETE IF NO PATH IS ASKED, IT SPEEDS UP THE ALGORITHM)
	static int[][] distances;
	
    private static int solve(int bit, int pos){
        if(dp[pos][bit] != -1) return dp[pos][bit];
        
        int min = Integer.MAX_VALUE;
        int minIndex = -1; //for path keeping (DELETE IF NO PATH IS ASKED, IT SPEEDS UP THE ALGORITHM)
        
        for(int i = 1; i < N; i++){ //(starts with 1 because 0th index is always visited)
            if((bit & (1 << i)) == 0){
            	//min = Math.min(min, distances[i][pos] + solve(bit | (1 << i), i));
            	
            	//UNCOMENT THE PREVIOUS COMMENT, AND DELETE THIS IF NO PATH IS ASKED
            	int dist = distances[i][pos] + solve(bit | (1 << i), i);
                if(dist < min){
                	min = dist;
                	minIndex = i;
                }
                
            }
        }
        next[pos][bit] = minIndex; //for path keeping (DELETE IF NO PATH IS ASKED, IT SPEEDS UP THE ALGORITHM)
        return dp[pos][bit] = min;
    }

    public static int minimumPath(int[] x, int[] y) {
    	N = x.length;
        int S = 1 << N;
        dp = new int[N][S];
        next = new int[N][S];
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
        int x[] = {874,117,684,989,878};
        int y[] = {878,929,484,798,210};
        //long startTime = System.currentTimeMillis();
        System.out.println(minimumPath(x, y));
        //long endTime   = System.currentTimeMillis();
        //long totalTime = endTime - startTime;
        //System.out.println(totalTime + " ms");
        
        //PRINT PATH
        int current = 0;
        int bit = 1;
        System.out.print(current);
        while(true){
        	System.out.print(" -> ");
        	int n = next[current][bit];
        	System.out.print(n);
        	current = n;
        	if(current == 0) break;
        	bit = bit | (1<<current);
        }
    }
}
