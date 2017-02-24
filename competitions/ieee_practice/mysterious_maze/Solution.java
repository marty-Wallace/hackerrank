import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int size = input.nextInt();
        int[][] map = new int[size][size];
        input.nextLine();
        String line = input.nextLine();
        int counter = 0;
        boolean solved = false;
        while(!line.equals("-1")){
            int i = Integer.parseInt(line.split(" ")[0]) - 1;
            int j = Integer.parseInt(line.split(" ")[1]) - 1;
            counter++;
            map[i][j] |= 1;
            if(i == 0){
                map[i][j] |= 2;
            }
            if(i == map.length -1){
                map[i][j] |= 4;
            }
            add(map, i, j);
            if(map[i][j] == 7){
                solved = true;
                break;
            }
            line = input.nextLine();
        }
        if(solved){
            System.out.println(counter);
        }else{
            System.out.println(-1);
        }
    }
    
    public static void add(int[][]map, int i, int j) {
        if(i < 0 || j < 0 || i >= map.length || j >= map[0].length){
            return;
        }
        if((map[i][j] & 1) != 1){
            return;
        }
        if(i > 0){
            map[i][j] |= map[i-1][j];
        }
        if( i < map.length-1){
            map[i][j] |= map[i+1][j];
        }
        if(j > 0){
            map[i][j] |= map[i][j-1];
        }
        if(j < map[0].length -1){
            map[i][j] |= map[i][j+1];
        }
        
        if(i > 0 && map[i-1][j] % 2 == 1 && map[i-1][j] != map[i][j]){
            add(map, i-1, j);
        }
        if( i < map.length-1 && map[i+1][j] % 2 == 1&& map[i+1][j] != map[i][j]){
            add(map, i+1, j);
        }
        if(j > 0 && map[i][j-1] % 2 == 1 && map[i][j-1] != map[i][j]){
            add(map, i, j-1);
        }
        if(j < map[0].length -1 && map[i][j+1] % 2 == 1 && map[i][j+1] != map[i][j]){
            add(map, i, j+1);
        }
       
    }
    
    public static void printMap(int[][]map){
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " " );
            }
            System.out.println();
        }
        System.out.println();
    }
}
