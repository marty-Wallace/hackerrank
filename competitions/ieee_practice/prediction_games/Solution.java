import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int cases = Integer.parseInt(input.nextLine());
        for(int c = 0; c < cases; c++) {
            int numPlayers = Integer.parseInt(input.next());
            Player[] players = new Player[numPlayers];
            int numComps = Integer.parseInt(input.nextLine().trim());
            for(int p = 0; p < numPlayers; p++) {
                players[p] = new Player(input.nextLine());
                for(int cm = 0; cm < numComps; cm++ ){
                	players[p].addGuess(new int[]{Integer.parseInt(input.next().trim()), Integer.parseInt(input.nextLine().trim())});
                }
            }
            ArrayList<Integer>qs = new ArrayList<Integer>();
            for(int cm = 0; cm < numComps; cm++) {
            	String line = input.nextLine();
            	if(line.equals("? ?")){
            		qs.add(cm);
            	}else{
            		for(Player p : players) {
            			int[] guess = p.getGuess(cm);
            			p.score += score(guess[0], guess[1], Integer.parseInt(line.split(" ")[0]), Integer.parseInt(line.split(" ")[1]));
            		}
            	}
            }
            Arrays.sort(players);
        	for(int i = 0; i < players.length; i++) {
            	Player current = players[i];
            	if(current == null) {continue;}
            	for(int pN = i; pN < players.length; pN++) {
            		Player p = players[pN];
            		if(p == null || p == current) {
            			continue;
            		}
            		int diff = current.score - p.score;
            		int swing = 0;
            		for(int g : qs) {
            			swing += swing(current.getGuess(g), p.getGuess(g));
            		}
            		if(diff > swing) {
            			players[pN] = null;	
            		}
            	}
        	}

            Arrays.sort(players, new Comparator<Player>() {

				@Override
				public int compare(Player p1, Player p2) {
					if(p1 == null && p2 == null) {
						return 0;
					}else if(p1 == null) {
						return -1;
					}else if(p2 == null) {
						return 1;
					}else{
						return p1.name.compareTo(p2.name);
					}
				}
            	
            });
            StringBuilder print = new StringBuilder();
            String space = "";
            for(Player p : players) {
            	if (p != null) {
            		print.append(space + p.name);
            		space = " ";
            	}
            }
            System.out.println(print.toString());
        }
    }

    public static int swing(int[] p1, int[]p2) {
    	int swing = 0;
    	if((p1[0] > p1[1] && p2[0] < p2[1] ) || (p1[0] < p1[1] && p2[0] > p2[1])){
    		swing = 10;
    	}
    	swing += Math.min(5, Math.abs((p1[0] - p1[1])-(p2[0] - p2[1])));
    	swing += Math.min(5, Math.abs(p1[0] - p2[0])) + Math.min(5, Math.abs(p1[1] - p2[1]));
    	return swing;
    }
    
    public static int score(int p1, int p2, int s1, int s2) {
    	
        int winner = 0;
        if((p1 > p2 && s1 > s2) || (p1 < p2 && s1 < s2)) {
            winner = 10;
        }
        return Math.max(0, 5 - Math.abs(p1-s1)) + Math.max(0, 5 - Math.abs(p2 - s2)) + Math.max(0, 5 - Math.abs((s1-p1)-(s2-p2))) + winner;
    
    }
    
    static class Player implements Comparable<Player>{
    	
        String name;
        ArrayList<int[]> guesses = new ArrayList<int[]>();
        Map<String, Integer> diffs = new HashMap<String, Integer>();
        int score;
        public Player(String name){
            this.name = name;
            this.score = 0;
        }
        void addGuess(int[] guess){
            guesses.add(guess);
        }
        
        int[] popGuess() {
            return guesses.remove(0);
        }
        int[] peekGuess() {
        	return guesses.get(0);
        }
        int[] getGuess(int i) {
        	return guesses.get(i);
        }
		@Override
		public int compareTo(Player p) {
			if(p.score > this.score) {
				return 1;
			}
			if(p.score < this.score) {
				return -1;
			}
			return 0;
		}
        
    }
}
