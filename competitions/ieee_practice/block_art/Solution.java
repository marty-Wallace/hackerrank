import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int row = input.nextInt();
        int col = input.nextInt();
        int q = input.nextInt();
        input.nextLine();
        List<Rectangle> adds = new ArrayList<Rectangle>();
        List<Rectangle> subs = new ArrayList<Rectangle>();
        for(int i = 0; i < q; i++){
            String[] line = input.nextLine().split(" ");
            char c = line[0].charAt(0);
            int sRow = Integer.parseInt(line[1]);
            int sCol = Integer.parseInt(line[2]);
            int eRow = Integer.parseInt(line[3]);
            int eCol = Integer.parseInt(line[4]);
            if(c == 'a'){
                adds.add(new Rectangle(sRow, sCol, eRow, eCol));
            }else if(c == 'r'){
                subs.add(new Rectangle(sRow, sCol, eRow, eCol));
            }else{
                Rectangle qu = new Rectangle(sRow, sCol, eRow, eCol);
                for(Rectangle r : adds){
                    qu.add(r);
                }
                for(Rectangle r : subs){
                    qu.sub(r);
                }
                System.out.println(qu);
            }
            
        }
    }
    static class Rectangle {
        int sRow;
        int sCol;
        int eRow;
        int eCol;
        int total;
        public Rectangle(int sRow, int sCol, int eRow, int eCol){
            this.sRow = sRow;
            this.sCol = sCol;
            this.eRow = eRow + 1;
            this.eCol = eCol + 1;
            this.total = 0;
        }
        
        public void add(Rectangle r){
            total += Math.max(0, Math.min(r.eCol, this.eCol) - Math.max(r.sCol, this.sCol)) * Math.max(0, Math.min(r.eRow, this.eRow ) - Math.max(r.sRow, this.sRow));
        }
        
        public void sub(Rectangle r){
                        total -= Math.max(0, Math.min(r.eCol, this.eCol) - Math.max(r.sCol, this.sCol)) * Math.max(0, Math.min(r.eRow, this.eRow ) - Math.max(r.sRow, this.sRow));
        }
        public String toString() {
            return "" + this.total;
        }
        
    }
}
