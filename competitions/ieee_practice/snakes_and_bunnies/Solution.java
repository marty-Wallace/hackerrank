import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Solution {
	
	public static void main(String[]args) {
		final Scanner input = new Scanner(System.in);
		final int boardSize = Integer.parseInt(input.nextLine());
		Map <Character, Integer> map = new HashMap<Character, Integer>();
		Map <Integer, Integer> snakes= new HashMap<Integer, Integer>();
		Map <Integer, Integer> bunnies = new HashMap<Integer, Integer>();
		char[][]board = new char[boardSize][];
		for(int i = 0; i < boardSize; i++ ) {
			if(i % 2 == 1) {
				board[i] = new StringBuilder(input.nextLine()).reverse().toString().toCharArray();
			}else{
				board[i] = input.nextLine().toCharArray();
			}
		}
		int counter = 1;
		for(int i = boardSize -1; i >= 0; i--) {
			for(int j = 0; j < boardSize; j++) {
                //System.out.print(counter +" "+ board[i][j] + " ");
				if(map.containsKey(board[i][j])){
					if(Character.isAlphabetic(board[i][j])) {
						bunnies.put(counter, map.get(board[i][j]));
					}else if(Character.isDigit(board[i][j])) {
						snakes.put( map.get(board[i][j]), counter);
					}
				}else if(board[i][j] != '-'){
					map.put(board[i][j], counter);
				}
				counter++;
			}
            //System.out.println();
		}
		//System.out.println(snakes);
        //System.out.println(bunnies);
		final int numPlayers = Integer.parseInt(input.nextLine());
		int playerIndex = 0;
		int evil = -1;
		int[] playerPositions = new int[numPlayers];
		int roll1, roll2;
		int pos;
		ArrayList<Integer> seen;
        boolean lastDouble = false;
		while(input.hasNextLine() && evil == -1) {
			roll1 = Integer.parseInt(input.nextLine());
            if(!lastDouble){
                roll2 = Integer.parseInt(input.nextLine());
            }else{
                roll2 = 0;
            }
            if(roll1 == roll2) {
                lastDouble = true;
            }else{
                lastDouble = false;
            }
			seen = new ArrayList<Integer>();
			pos = playerPositions[playerIndex];
			pos += roll1 + roll2;
            pos = fixPos(pos, playerPositions, playerIndex);
            if(pos >= boardSize * boardSize) {
                playerPositions[playerIndex] = pos;
                break;
            }
			seen.add(pos);
			while(bunnies.containsKey(pos) || snakes.containsKey(pos)) {
				if (bunnies.containsKey(pos)) {
					pos = bunnies.get(pos);
				}else {
					pos = snakes.get(pos);
				}
                pos = fixPos(pos, playerPositions, playerIndex);
                if(pos >= boardSize * boardSize) {
                    playerPositions[playerIndex] = pos;
                    evil = -2;
                    break;
                }
				if(seen.contains(pos)) {
					evil = playerIndex + 1;
					break;
				}
				seen.add(pos);
			}
            //System.out.printf("Player %d at pos: %d\n", playerIndex + 1, pos);
			playerPositions[playerIndex] = pos;
            if(!lastDouble){
			     playerIndex = ++playerIndex % numPlayers;
            }
		}
		if(evil > -1) {
			System.out.printf("PLAYER %d WINS BY EVIL CYCLE!", evil );
		}else{
			for(int i : playerPositions) {
				System.out.print(Math.min(boardSize * boardSize, i) + " ");
			}
		}
		
	}
    
    public static int fixPos(int pos, int[]playerPositions, int playerIndex){
        for(int i = 0; i < playerPositions.length; i++) {
            if(i == playerIndex) {
                continue;
            }
            if(pos == playerPositions[i]){
                pos++;
                return fixPos(pos, playerPositions, playerIndex);
            }
        }
        return pos;
    }

}

