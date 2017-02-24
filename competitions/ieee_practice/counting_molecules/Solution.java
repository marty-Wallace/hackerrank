import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        double c = sc.nextDouble();
        double h = sc.nextDouble();
        double o = sc.nextDouble();
        long w = (long)((o/2) - c + Math.round(h/4));
        long d = (long)(o - c - w);
        long g = (long)((c - d)/6);
        if((long)o == (w+2*d+6*g) && (long)c == (d + 6 * g) && (long)h == (2*w + 12*g)){
            System.out.println(w + " " + d + " " + g);
        }else{
            System.out.println("Error");
        }
        
    }
}
