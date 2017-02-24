import java.io.*; 
import java.util.*; 
import java.text.*; 
import java.math.*; 
import java.util.regex.*; 
public class Solution {  
    static Scanner input = new Scanner(System.in);     
    public static void main(String[] args) {         
        int rootOfSquare = input.nextInt();         
        int totalOfMain =0;         
        int totalOfReverse=0;         
        int count=0;         
        int column = 0, row = 0;         
        int rootBol = (rootOfSquare * 2) + 1;         
        boolean [] errors = new boolean [rootBol];         
        for (int m = 0; m < rootBol; m++) {             
            errors[m] = true;         
        }         
        int [][] squareMultiD = new int[rootOfSquare][rootOfSquare];         
        for ( int i =0; i< rootOfSquare; i++){             
            for ( int j = 0; j< rootOfSquare; j++){                 
                squareMultiD[i][j]=input.nextInt();                 
                if(i==j){                     
                    totalOfMain+=squareMultiD[i][j];                 
                }                 
                if (i+j==(rootOfSquare-1)){                     
                    totalOfReverse+=squareMultiD[i][j];                 
                }               
            }          
        }         
        if(totalOfReverse != totalOfMain){             
            errors[rootOfSquare]=false;             
            count++;         
        }         
        for(int i =0; i<rootOfSquare; i++){             
            row = 0;             
            column = 0;             
            for( int j =0; j< rootOfSquare; j++){ 
                row+= squareMultiD[i][j];                
                column+= squareMultiD[j][i];             
            }   
            if(row!=totalOfMain){                 
                errors[(rootOfSquare+i+1)]=false;                 
                count++;             
            }             
            if(column!=totalOfMain){                 
                errors[(rootOfSquare-i-1)]=false; 
                count++;             
            }         
        }         
        System.out.println(count);         
        for(int k=0; k < rootBol; k++ ){             
            if (!errors[k]){                 
                System.out.println(k-rootOfSquare);             
            }         
        } 
    }     
} 


