import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        double[] catalan = new double[520];
        double previous = 1;
        catalan[3] = 1;
        double next;
        for(int i = 1; i < 517; i++){
            next = previous * 2 * (2*i + 1) / (i + 2);
            previous = next;
            catalan[(i+3)] = next;
        }
        while(input.hasNext()){
            double d = input.nextDouble();
            for(int i = 3; i < catalan.length; i++){
                if (d >= catalan[i] && d < catalan[i+1])
                    System.out.println(i);
            }
        }
    }
}
