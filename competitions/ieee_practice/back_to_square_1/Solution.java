import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input =new Scanner(System.in);
        int size = input.nextInt();
        
        while(size != 0){
            if(size == 1){
                System.out.println(1);
                size = input.nextInt();
                continue;
            }
            double sum = 1;
            double value = 1;
            double[] probs = new double[size-1];
            for(int i = 0; i < size-1; i++) {
                probs[i] = input.nextDouble();
            }
            for(int i = probs.length-1; i >=0; i--){
                sum += value/probs[i];
                value /= probs[i];
            }
            System.out.println(Math.round(sum));
            size = input.nextInt();
        }
    }
}

