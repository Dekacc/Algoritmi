//Ja naogja najgolemata suma na podniza od dadena niza.
//Dokoloku site elementi na nizata se negativni, se pecati 0.

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        //INPUT
        int N = sc.nextInt();
        int[] array = new int[N];
        for(int i = 0; i < N; i++){
            array[i] = sc.nextInt();
        }

        //CALCULATION
        int currentSum = 0;
        int maxSum = -1;
        for(int i : array){
            currentSum = Math.max(0, currentSum + i); //ako stane negativna sumata, se resetira na 0.
            maxSum = Math.max(maxSum, currentSum);
        }

        //OUTPUT
        System.out.println(maxSum);
    }
}