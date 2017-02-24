import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ZoomIn{
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        final int WIDTH = Integer.parseInt(input.nextLine());
        final int HEIGHT = Integer.parseInt(input.nextLine());
        final int LETTERS = Integer.parseInt(input.nextLine());
        StringBuilder[] print = new StringBuilder[HEIGHT];
        Map<Character, String[]>map = new HashMap<Character, String[]>();
        for(int l = 0; l < LETTERS; l++) {
            char letter = input.nextLine().charAt(0);
            String[] lines = new String[HEIGHT];
            for(int i = 0; i < HEIGHT; i++) {
            	lines[i] = input.nextLine();
            }
            map.put(letter, lines);
        }
        int numStrings = Integer.parseInt(input.nextLine());
        for(int nS = 0; nS < numStrings; nS++) { // loop for each string to print
            for(int i = 0; i < HEIGHT; i++) { // initialize StringBuilders in array
                print[i] = new StringBuilder();
            }
	        for(char c: input.nextLine().toCharArray()) {
	        	String[] lines = map.get(c);
	        	for(int i = 0; i < HEIGHT; i++) {
	        		print[i].append(lines[i]);
	        	}
	        }
	        for(StringBuilder s : print) {
	        	System.out.println(s.toString());
	        }
        }
        
        input.close();
    }
}
