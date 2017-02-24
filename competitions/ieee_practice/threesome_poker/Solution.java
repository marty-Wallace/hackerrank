import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String[] line = input.nextLine().split(" " );
        int p1 = Integer.parseInt(line[0]);
        int p2 = Integer.parseInt(line[1]);
        int p3 = Integer.parseInt(line[2]);
        Queue<Gamestate> q = new LinkedList<Gamestate>();
        q.add(new Gamestate(p1, p2, p3, 1, null));
        while(!q.isEmpty() ){
            Gamestate current = q.poll();
            if(current.depth == 13){
                System.out.println("Ok");
                break;
            }
            q.addAll(current.nextStates());
            if(current.isWinner()) {
                System.out.print(current.toString());
                break;
            }
        }
        
    }
    static class Gamestate {
        int p1, p2, p3, depth;
        Gamestate parent;
        public Gamestate(int p1, int p2, int p3, int depth, Gamestate parent) {
            this.p1 = p1; 
            this.p2 = p2; 
            this.p3 = p3;
            this.depth = depth;
            this.parent = parent;
        }
        
        public String toString(){ 
            if(this.parent != null){
                return parent.toString() + p1 + " " + p2 + " " + p3 + " \n";
            }
            return p1 + " " + p2 + " " + p3 + " \n";
        }
        
        public boolean isWinner() {
            return p1 == 0 || p2 == 0 || p3 == 0;
        }
        
        public Queue<Gamestate> nextStates() {
            Queue<Gamestate> states = new LinkedList<Gamestate>();
            if(p1 <= p2 && p1 <= p3){
                states.add(new Gamestate(p1*2, p2-p1, p3, depth+1, this));
                states.add(new Gamestate(p1*2, p2, p3-p1, depth+1, this));
                if(p2 <= p3){
                    states.add(new Gamestate(p1, p2*2, p3-p2, depth+1, this));
                }else{
                    states.add(new Gamestate(p1, p2-p3, p3*2, depth+1, this));
                }
            }else if(p2 <= p1 && p2 <= p3){
                if(p1 <= p3){
                    states.add(new Gamestate(p1*2, p2, p3-p1, depth+1, this));
                }
                states.add(new Gamestate(p1-p2, p2*2, p3, depth+1, this));
                states.add(new Gamestate(p1, p2*2, p3-p2, depth+1, this));
                if(p3 <= p1){
                    states.add(new Gamestate(p1-p3, p2, p3*2, depth+1, this));
                }
            }else{
                if(p1 <= p2){
                    states.add(new Gamestate(p1*2, p2-p1, p3, depth+1, this));
                }else{
                    states.add(new Gamestate(p1-p2, p2*2, p3, depth+1, this));
                }
                states.add(new Gamestate(p1-p3, p2, p3*2, depth+1, this));
                states.add(new Gamestate(p1, p2-p3, p3*2, depth+1, this));
            }
            return states;
        }
    }
}
