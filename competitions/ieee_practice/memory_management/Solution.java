import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while(t-->0) {
            int mem = input.nextInt();
            int size = input.nextInt();
            int num = input.nextInt();
            
            int index = 0;
            int[] nums = new int[mem]; //use for FIFO 
            ArrayList<Integer> LRU = new ArrayList<Integer>();
            Arrays.fill(nums, -1);
            int lCount = 0; 
            int fCount = 0; 
            boolean full = false;
            for(int i = 0; i < num; i++) {
                int n = input.nextInt() / size; 
                
                if(!contains(nums, n)){ //FIFO 
                    if( full || !contains(nums, -1)){
                       fCount++;
                       full = true;
                    }
                    nums[index++] = n;
                }
                
                index %= mem;
                
                if(! LRU.contains(n)){ //LRU
                    if(LRU.size() >= mem) {
                        lCount++;
                    }
                    LRU.add(n);
                    
                }else{
                    LRU.remove(LRU.indexOf(n));
                    
                    LRU.add(n);
                    
                }
                
                if(LRU.size() > mem) {
                    LRU.remove(0);
                }
                
                
            }
            System.out.print(fCount > lCount ? "yes " : "no ");
            System.out.println(fCount + " " + lCount);
        }
    }
    
    public static boolean contains(int[] n, int find) {
    	for(int i = 0; i < n.length; i++) {
    		if(n[i] == find) {
    			return true;
    		}
    	}
    	return false;
    }
}
