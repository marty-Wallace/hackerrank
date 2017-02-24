import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) 
    {
        final Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while(t-->0) 
        { 
            //do solution
            int num = input.nextInt();
            Booking[] bookings = new Booking[num];
            for(int _n = 0; _n < num; _n++)
            {
                bookings[_n] = new Booking(input.nextInt(), input.nextInt(), input.nextInt());
                // read in Bookings
            }
            Arrays.sort(bookings); //sort the array so we can start with all the things that occur at hour 1, then 2 ...
            int[] dp = new int[49]; //this is 49 length because there are 48 possible hours that can be used need 1 extra
            int index = 0; //set index
            for(int i = 0; i < dp.length-1; i++) //for each possible hour
            { 
                //for each booking that starts at hour i & make sure that index is less than the total number of bookings
                while(index < num && bookings[index].start == i)  
                { 
                    //welcome to my crib, this is where the magic happens
                    dp[bookings[index].end] = Math.max(dp[bookings[index].end], dp[i] + bookings[index].value); 
                    //set the ending time of this booking to be the maximum of what it already was
                    //and the value of the booking plus the best value found at the time it started
                    ++index; //increment counter
                }
                // the hour spot after this one can obviously have the score this one has so set the max of this hours score and the next hours score
                dp[i+1] = Math.max(dp[i], dp[i+1]);
            }
            System.out.println(dp[dp.length-1]); //print out the best value found at 
        }
        input.close();
    }
    
    //Make a class to store the information and sort it based on starting time
    static class Booking implements Comparable<Booking>  
    {
        int start;
        int end;
        int value;
        public Booking(int start, int end, int value) 
        {
            this.start = start;
            this.end = end;
            this.value = value;
        }
        public int compareTo(Booking b) 
        {
            if(this.start < b.start) {
                return -1;
            }else if(this.start == b.start) {
                return 0;
            }
            return 1;
        }
    }
}
