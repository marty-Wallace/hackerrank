import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String line = input.nextLine();
        while(!line.equals("END")){
            
            int counter = 1;
            while(line.length() > 1 || line.length() != Long.parseLong(line)){
                counter++;
                line = Long.toString(line.length());
            }
           
            
            System.out.println(counter);
            line = input.nextLine();
        }
    }
}
