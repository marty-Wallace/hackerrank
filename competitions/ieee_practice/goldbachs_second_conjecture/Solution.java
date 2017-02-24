import java.io.*;
import java.util.*;
import java.math.BigInteger;

public class Solution {

    public static void main(String[] args) {
        long n = new Scanner(System.in).nextLong();
        while(true){
            long a = (long)(Math.random() * n) + 1;
            long b = (long)(Math.random() * n) + 1;
            if(a + b > n){
                continue;
            }
            long c = n - a - b;
            if(new BigInteger(Long.toString(a)).isProbablePrime(1) 
               && new BigInteger(Long.toString(b)).isProbablePrime(1) 
               && new BigInteger(Long.toString(c)).isProbablePrime(1)){
                System.out.println(a + " " + b + " " + c);
                break;
            }
        }
    }
}
