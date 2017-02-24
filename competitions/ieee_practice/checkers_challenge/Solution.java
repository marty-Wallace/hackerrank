import java.io.*;
import java.util.*;

public class Solution {
	public static final int UP = 1, LEFT = 2, RIGHT = 3, DOWN = 4;
	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		int t= Integer.parseInt(input.nextLine());
		while(t-->0) {
			char[][] map = new char[8][];
			int kingI = -1;
			int kingJ = -1;
			int blackCount = 0;
			for(int i = 0; i < 8; i ++){
				map[i] = input.nextLine().toCharArray();
				Arrays.toString(map);
				for(int j = 0; j < 8; j++){
					if(map[i][j] == 'o'){
						kingI = i;
						kingJ = j;
					}else if(map[i][j] == 'x'){
						blackCount++;
					}
				}
			}
			Stack<State> s = new Stack<State>();
			int wins = 0;
			s.add(new State(false, 0, map, kingI, kingJ, blackCount, 0));
			while(!s.isEmpty()){
				State current = s.pop();
				//current.printMap();
				if(current.isWon()){
					wins++;
					continue;
				}
				Stack<State> next = current.nextStates();
				for(State ss : next){
					s.push(ss);
				}

			}
			System.out.println(wins);
			if(t != 0) {
				input.nextLine();
			}
		}
	}

	static char[][]copyArray(char[][] c){
		char[][] ret = new char[c.length][c[0].length];
		for(int i = 0; i < c.length; i++){
			for(int j = 0; j < c[0].length; j++) {
				ret[i][j] = c[i][j];
			}
		}
		return ret;
	}

	static class State {

		int turn;
		boolean isKing;
		char[][]map;
		int i, j;
		int blackcount;
		int prevDir;
		public State(boolean isKing, int turn, char[][]map, int i, int j, int blackcount, int prevDir){
			this.isKing = isKing || i == 0;
			this.turn = turn;
			this.map = map;
			this.i = i;
			this.j = j;
			this.blackcount = blackcount;
			this.prevDir = prevDir;
		}

		public void printMap() {
			System.out.println("turn: " + turn + " i:" + i + " j:" + j + " blackcount:" + blackcount);
			for(int i = 0; i < 8; i++) {
				for(int j = 0; j < 8; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
		}

		public Stack<State> nextStates() {
			Stack<State> ret = new Stack<State>();
			if(!isKing) {
				if(i > 1 && map[i-1][j] == 'x' && map[i-2][j] == '.'){
					char[][] m = copyArray(map);
					m[i][j] = '.';
					m[i-1][j] = '.';
					m[i-2][j] = 'o';
					ret.push(new State(isKing, turn+1, m, i - 2, j, blackcount-1, UP));
				}
				if(j > 1 && map[i][j-1] == 'x' && map[i][j-2] == '.'){
					char [][] m = copyArray(map);
					m[i][j] = '.';
					m[i][j-1] = '.';
					m[i][j-2] = 'o';
					ret.push(new State(isKing, turn+1, m, i, j - 2, blackcount-1, LEFT));
				}
				if(j < map.length-2 && map[i][j+1] == 'x' && map[i][j+2] == '.'){
					char [][] m = copyArray(map);
					m[i][j] = '.';
					m[i][j+1] = '.';
					m[i][j+2] = 'o';
					ret.push(new State(isKing, turn+1, m, i, j+2, blackcount-1, DOWN));
				}

			}else{
				if(prevDir != DOWN){ //then look up
					for(int newI = i; newI > 0; newI--){
						if(map[newI][j] == 'x'){
							for(int newNewI = newI -1; newNewI >= 0; newNewI--){
								if(map[newNewI][j] == '.'){
									char[][]m = copyArray(map);
									m[newI][j] = '.';
									m[i][j] = '.';
									m[newNewI][j] = 'o';
									ret.push(new State(isKing, turn+1, m, newNewI, j, blackcount-1, UP));
								}else if(map[newNewI][j] == 'x'){
									break;
								}
							}
							break;
						}
					}

				}
				if(prevDir != RIGHT){ // go left
					for(int newJ = j; newJ > 0; newJ--){
						if(map[i][newJ] == 'x'){
							for(int newNewJ = newJ - 1; newNewJ >= 0; newNewJ--){
								if(map[i][newNewJ] == '.'){
									char[][]m = copyArray(map);
									m[i][newJ] = '.';
									m[i][j] = '.';
									m[i][newNewJ] = 'o';
									ret.push(new State(isKing, turn+1, m, i, newNewJ, blackcount-1, LEFT));
								}else if(map[i][newNewJ] == 'x'){
									break;
								}
							}
							break;
						}
					}
				}
				if(prevDir != LEFT){ // go RIGHT
					for(int newJ = j; newJ < map[0].length-1; newJ++){
						if(map[i][newJ] == 'x'){
							for(int newNewJ = newJ + 1; newNewJ < map[0].length; newNewJ++){
								if(map[i][newNewJ] == '.'){
									char[][]m = copyArray(map);
									m[i][newJ] = '.';
									m[i][j] = '.';
									m[i][newNewJ] = 'o';
									ret.push(new State(isKing, turn+1, m, i, newNewJ, blackcount-1, RIGHT));
								}else if(map[i][newNewJ] == 'x'){
									break;
								}
							}
							break;
						}
					}
				}				
				if(prevDir != UP){ // go DOWN
					for(int newI = i; newI < map.length-1; newI++){
						if(map[newI][j] == 'x'){
							for(int newNewI = newI + 1; newNewI < map.length; newNewI++){
								if(map[newNewI][j] == '.'){
									char[][]m = copyArray(map);
									m[newI][j] = '.';
									m[i][j] = '.';
									m[newNewI][j] = 'o';
									ret.push(new State(isKing, turn+1, m, newNewI, j, blackcount-1, DOWN));
								}else if(map[newNewI][j] == 'x'){
									break;
								}
							}
							break;
						}
					}

				}

			}
			return ret;

		}
		boolean isWon() {
			return this.blackcount == 0;
		}	

	}


}

