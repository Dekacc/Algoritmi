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
                    C[i][j] += A[i][k] * B[k][j];
                    C[i][j] %= MOD;               //comment this line if no mod is required
                }
            }
        }
        return C;
    }

    public static long[][] mat_power(long[][] mat, long pow, long MOD){
        //returns mat^pow in O(log(pow)) iteratively
        if(pow == 1) return mat;
        long[][] tmp = mat;
        boolean first = true;
        while(pow > 1){
            if(pow % 2 == 1){
                if(!first) tmp = mat_multiply(tmp, mat, MOD);
                else first = false;
            }
            mat = mat_multiply(mat, mat, MOD);
            pow /= 2;
        }
        if(first) return mat;
        else return mat_multiply(mat, tmp, MOD);
    }

    public static void main(String[] args){
        //TODO: main method
    }
}
