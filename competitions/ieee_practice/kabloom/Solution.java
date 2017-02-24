import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = Integer.parseInt(input.nextLine());
        while(n != 0) {
            //read in input, remove spaces then split into char arrays
            char[] line1 = input.nextLine().replaceAll(" ", "").toCharArray(); //hand 1
            char[] line2 = input.nextLine().replaceAll(" ", "").toCharArray(); //hand 2 
            assert line1.length == line2.length && line1.length == n;  //sanity check
            
            int[][] dp = new int[n][n]; //create dp table 
            dp[0][0] = valueOf(line1[0], line2[0]); //set the value of the first choice
            // now we will set the values for all choices with the outer cards
            for(int i = 1; i < n; i++){
                dp[0][i] = Math.max(valueOf(line1[0], line2[i]), dp[0][i-1]);
                dp[i][0] = Math.max(valueOf(line1[i], line2[0]), dp[i-1][0]);
            }
            //now we can iterate through the table setting it's values
            //to the max of 
            //the cell north of it, 
            //or the cell east of it, 
            //or the cell north-east of it + the value of the current cell
            int value = 0;
            for(int j = 1; j < n; j++) {    
                for(int i = 1; i < n; i++) {
                    value = valueOf(line1[i], line2[j]);
                    dp[i][j] = Math.max(Math.max(dp[i-1][j], value + dp[i-1][j-1]),dp[i][j-1] );
                }
            }
            //table is fully built, now we can print out our answer
            System.out.println(dp[n-1][n-1]);
            
            //read in the next test case size 
            n = Integer.parseInt(input.nextLine());
        }
    }
    
    //returns the value of a pairing of 2 cards c and d
    public static int valueOf(char c1, char c2){
        //set the match to be a garbage character
        char match = 'x'; 
        //if c1 is a joker or c1 == c2 then set the match to be c2
        if(c1 == 'R' || c1 == c2){
            match = c2;
        //else if c2 is a joker then set the match to be c1
        }else if(c2 == 'R'){
            match = c1;
        }else{
            //no matches and no jokers return 0 points
            return 0;
        }
        //now grab the value of the match 
        return valueOf(match);
    }
    
    //returns the value of a match of two c's
    public static int valueOf(char c){
        if(Character.isDigit(c)) { //digit return double 
            return Integer.parseInt(Character.toString(c)) * 2;
        }else if(c == 'A'){ //aces are 20 * 2
            return 40;
        }else if(c == 'J' || c == 'Q' || c == 'K'){ //face cards or 15 * 2
            return 30; 
        }else if(c == 'T'){ //T is 10, so 10 * 2
            return 20;
        }else if(c == 'R'){ // 2 jokers is 50 * 2
            return 100;
        }
        //should never reach here with good input
        return 0; //garbage cards return 0
    }
}
