import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt(); 
        while(t-->0) {
            int c = input.nextInt();
            int[] colours = new int[c];
            int[] distances = new int[c];
            Arrays.fill(distances, 9000);
            Map<Integer, Integer> map = new HashMap<Integer, Integer>();
            for(int i = 0; i < c; i++) {
                colours[i] = input.nextInt(); 
                if(map.containsKey(colours[i])){
                    int last = map.get(colours[i]);
                    distances[last] = i;
                    map.remove(colours[i]);
                    map.put(colours[i], i);
                }else{
                    map.put(colours[i], i);
                }
            }
            int switches = 1; 
            int c1 = colours[0];
            int c2 = -1;
            int lasti1 = distances[0];
            int lasti2 = 9000;
            for(int i = 1; i < c; i++) {
                if(colours[i] == c1) {
                    lasti1 = distances[i];
                }else if(colours[i] == c2) {
                    lasti2 = distances[i];
                }else{
                    switches++;
                    if(lasti2 > lasti1){
                        c2 = colours[i];
                        lasti2 = distances[i];
                    }else{
                        c1 = colours[i];
                        lasti1 = distances[i];
                    }
                }
            }
            System.out.println(switches);
        }
    }
}
