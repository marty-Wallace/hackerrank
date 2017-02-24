import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input =  new Scanner(System.in);
        int t = input.nextInt();
        while(t-->0){
            int g = input.nextInt();
            int total = 0;
            for(int i = 0; i < g; i++){
                int s = input.nextInt();
                for(int j = 0; j < s; j++){
                    total += input.nextInt() / 2;
                }
            }
            System.out.println(total % 2 == 0 ? "Bob" : "Alice");
        }
    }
}
