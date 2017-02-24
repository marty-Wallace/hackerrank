import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int t = Integer.parseInt(input.nextLine());
        Bob b;
        Alice a;
        long d;
        while(t-->0){
            String[] line = input.nextLine().split(" ");
            a = new Alice(line[0]);
            b = new Bob(line[1]);
            d = Long.parseLong(line[2]);
            String wins;
            while(d-->0){
                //System.out.print(a.next + " " + b.next + "         " );
                if(a.next.equals("Scissors") && b.next.equals("Spock") && d > 3){
                    a.losses += (2 * (d/4));
                    a.wins += (d/4);
                    a.ties += (d/4);
                    d = d % 4;
                }else if(a.next.equals("Lizard") && b.next.equals("Spock")){
                    a.wins += d + 1;
                    break;
                }
                wins = best(a.next, b.next);
                if(wins.equals("tie")){
                   // System.out.println("tie");
                    a.ties();
                    b.ties();
                }else if(wins.equals("Alice")){
                    //System.out.println("Alice Wins");
                    a.wins();
                    b.loses();
                }else if(wins.equals("Bob")){
                    //System.out.println("Bob Wins");
                    a.loses(b.next);
                    b.wins();
                }
            }
            a.print();
        }
    }
    
    public static String best(String a, String b){
        if(a.equals(b)){
            return "tie";
        }
        if(a.equals("Spock")){
            if(b.equals("Lizard") || b.equals("Paper")){
                return "Bob";
            }else{
                return "Alice";
            }
        }else if(a.equals("Lizard")){
            if(b.equals("Rock") || b.equals("Scissors")){
                return "Bob";
            }else{
                return "Alice";
            }
        }else if(a.equals("Rock")){
            if(b.equals("Paper") || b.equals("Spock")){
                return "Bob";
            }else{
                return "Alice";
            }
        }else if(a.equals("Paper")){
            if(b.equals("Scissors") || b.equals("Lizard")){
                return "Bob";
            }else{
                return "Alice";
            }
        }else{ //a == "Scissors"
            if(b.equals("Rock") || b.equals("Spock")){
                return "Bob";
            }else{
                return "Alice";
            }
        }
    }
    
    
    static class Bob {
        String next;
        public Bob(String s){
            next = s;
        }
        
        public void wins(){
            if(!next.equals("Spock")){
                next = "Spock";
            }else{
                next = "Rock";
            }
        }
        public void loses() {
            if(!next.equals("Spock")){
                next = "Spock";
            }else{
                next = "Paper";
            }
        }
        public void ties() {
            if(!next.equals("Spock")){
                next = "Spock";
            }else{
                next = "Lizard";
            }
        }
    }
    
    static class Alice {
        Map <String, String> choice;
        String next;
        long wins;
        long ties;
        long losses;
        public Alice(String s){
            next = s;
            choice = new HashMap<String, String>();
            choice.put("Paper", "Scissors");
            choice.put("Rock", "Paper");
            choice.put("Lizard", "Rock");
            choice.put("Scissors", "Spock");
            choice.put("Spock", "Lizard");
            wins = 0;
            ties = 0;
            losses = 0;
        }
        
        public void wins(){
             wins++;
             return;
        }
        
        public void loses(String b){
            losses++;
            next = choice.get(b);
        }
        
        public void ties(){
            ties++;
            next = choice.get(next);
        }
        
        public void print(){
            if(wins > losses){
                System.out.printf("Alice wins, by winning %d game(s) and tying %d game(s)\n", wins, ties);
            }else if(wins < losses){
                System.out.printf("Bob wins, by winning %d game(s) and tying %d game(s)\n", losses, ties);
            }else{ //tie
                System.out.printf("Alice and Bob tie, each winning %d game(s) and tying %d game(s)\n", wins, ties);
            }
        }
    }
}
