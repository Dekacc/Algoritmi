import java.util.Scanner;

public class Main {
    //NAIVE ITTERATIVE O(n^3)
    public static long[][] mat_multiply(long[][] A, long[][] B, long MOD){
        int widthA = A[0].length;
        int widthB = B[0].length;
        int heightA = A.length;

        long[][] C = new long[heightA][widthB];
        for(int i = 0; i<heightA; i++){
            for(int j = 0; j<widthB; j++){
                C[i][j] = 0;
                for(int k = 0; k<widthA; k++){
                    C[i][j] += (A[i][k]%MOD) * (B[k][j]%MOD);
                    C[i][j] %= MOD;               //comment this line if no mod is required
                }
            }
        }
        return C;
    }

    //RECURSIVE DIVIDE-AND-CONQUER ALGORITHM FOR MATRIX EXPONENTIAITON IN O(N^3 * log(pow)) TIME
    public static long[][] mat_power(long[][] mat, long pow, long MOD)
    {
        if(pow == 1) return mat;
        if(pow % 2 == 0) return mat_power(mat_multiply(mat, mat, MOD), pow/2, MOD);
        return mat_multiply(mat, mat_power(mat_multiply(mat, mat, MOD), pow/2, MOD), MOD);
    }

    public static void main(String[] args){
        //TODO: main method
    }
}
