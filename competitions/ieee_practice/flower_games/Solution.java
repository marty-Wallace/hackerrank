import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long t = input.nextLong();
        while(t-->0){
            long a = input.nextLong(); 
             a -= Long.highestOneBit(a);
            System.out.println(a* 2 + 1);
        }
    }
}
