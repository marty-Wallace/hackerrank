import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = Integer.parseInt(input.nextLine());
        while(t-->0){
            int h = Integer.parseInt(input.nextLine());
            int w = Integer.parseInt(input.nextLine());
            char[][] map = new char[h][];
            for(int i = 0; i < h; i++){
                map[i] = input.nextLine().toCharArray();
            }
            int c_count = 0; //connected region counter
            int h_count = 0; //hole region counter
            for(int i = 0; i < h; i++) {
                for(int j = 0; j < w; j++) {
                    if(map[i][j] == 'X'){
                        fillX(i, j, map);
                        c_count++;
                    }else if(map[i][j] == 'O' && !fillO(i, j, map)){
                        h_count++;
                    }
                }
            }
            System.out.println(c_count - h_count);
        }
    }
    
    public static void fillX(int i, int j, char[][]map){
        //check out of bounds
        if(i < 0 || i >= map.length || j < 0 || j >= map[0].length)
            return;
        //if not an 'X' then don't consider further
        if(map[i][j] != 'X')
            return;
        //now we do our work, set this cell marked
        map[i][j] = 'x';
        //now recurse over surrounding cells
        for(int a = -1; a < 2; a++)
            for(int b = -1; b < 2; b++)
                if(!(a == 0 && b == 0))
                    fillX(i + a, j + b, map);
            
    }
    
    public static boolean fillO(int i, int j, char[][]map){
        //check out of bounds
        if(i < 0 || i >= map.length || j < 0 || j >= map[0].length)
            return false;
        //if not an 'O' then don't consider further
        if(map[i][j] != 'O')
            return false;
        //now we do our work, set this cell marked
        map[i][j] = 'o';
        
        //set the value of this call if it touches an edge
        boolean ret = i == 0 || i == map.length-1 || j == 0 || j == map[0].length-1;
        // now || with all subsequent calls
        for(int a = -1; a < 2; a++)
            for(int b = -1; b < 2; b++)
                if(a != b && a+b != 0) //no diagonals
                     ret |= fillO(i+a, j+b, map);
        return ret; 
    }
}
