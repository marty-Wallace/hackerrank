import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = input.nextInt();
        while(t-->0){
            int data = input.nextInt();
            int n = input.nextInt();
            int[] nums = new int[data];
            int sum = 0;
            int min = Integer.MAX_VALUE;
            int max = Integer.MIN_VALUE;
            int counter = 0;
            double [] avgs = new double[(int)(Math.ceil((double)data/n))];
            int [] ranges = new int [(int)(Math.ceil((double)data/n))];
            for(int i = 0; i < data; i++){
                nums[i] = input.nextInt();
                sum += nums[i];
                min = Math.min(nums[i], min);
                max = Math.max(nums[i], max);
                if((i + 1) % n == 0){
                    avgs[counter] = (double) sum / (double) n;
                    ranges[counter] = max - min;
                    max = 0; min = 0; sum = 0;
                    counter++;
                }else if(i == data-1){
                    avgs[counter] = sum/((double)n);
                    ranges[counter] = max - min;
                    max = 0; min = 0; sum = 0;
                    counter++;
                }
            }
            double A2 = 0;
            switch(n){
                case 2: A2 = 1.880; break;
                case 3: A2 = 1.023; break;
                case 4: A2 = 0.729; break;
                case 5: A2 = 0.577; break;
                case 6: A2 = 0.483; break;
                case 7: A2 = 0.419; break;
                case 8: A2 = 0.373; break;
                case 9: A2 = 0.337; break;
                case 10: A2 = 0.308; break;
            }
            double gave = 0;
            double rage = 0;
            for(int z = 0; z < avgs.length; z++){
                gave += avgs[z];
                rage += ranges[z];
            }
            gave /= avgs.length;
            rage /= ranges.length;
            //System.out.println("Xave: " + gave);
            //System.out.println("Rave " + rage);
            double uclx = gave + A2*rage;
            double lclx = gave - A2*rage;
            double clx = gave;
            //System.out.println("UCLX: " + uclx);
            //System.out.println("LCLX: " + lclx);
            //System.out.println("CLX: " + clx);
            
            boolean above = false;
            int sameside = 0;
            
            
            boolean printed = false;
            for(int i = 0; i < nums.length; i++){
                if(nums[i] > uclx || nums[i] < lclx){
                    System.out.println("Out of Control");
                    printed = true;
                    break;
                }
                int oneabove = 0;
                int onebelow = 0;
                for(int one = i; one > Math.max(0, i-5); one--){
                    if(nums[one] > (clx+(uclx-clx)/3)){
                        oneabove++;
                    }
                    if(nums[one] < (clx-(clx-lclx)/3)){
                        onebelow++;
                    }
                    if(Math.max(oneabove, onebelow) >= 4){
                        System.out.println("Out of Control");
                        printed = true;
                    }
                }
                if(printed){
                    break;
                }
                int twoabove = 0;
                int twobelow = 0;
                for(int one = i; one > Math.max(0, i-3); one--){
                    if(nums[one] > (clx+(2*(uclx-clx)/3))){
                        twoabove++;
                    }
                    if(nums[one] < ((clx-2*(clx-lclx)/3))){
                        twobelow++;
                    }
                    if(Math.max(twoabove, twobelow) >= 2){
                        System.out.println("Out of Control");
                        printed = true;
                    }
                }
                if(printed){
                    break;
                }
                if((nums[i] > clx == above) || i == 0){
                    sameside += 1;
                    if(sameside >= 8){
                        System.out.println("Out of Control");
                        printed = true;
                    }
                }else if(nums[i] == clx){
                    sameside = 0;
                
                }else{
                    sameside = 1;
                    above = nums[i] > clx;
                }
                if(printed){
                    break;
                }
            }
            if(!printed){
                System.out.println("In Control");
            }
            
            
        }
    }
}
