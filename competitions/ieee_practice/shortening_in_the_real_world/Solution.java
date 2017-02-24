import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Scanner;

public class Board {
    public static final String ALPHABET = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String baseS = input.nextLine();
        byte[] base = toByte(baseS.toCharArray());
        int num = Integer.parseInt(input.nextLine());
        for(int a = 0; a < num; a++) {
        	byte[] encode = toByte(input.nextLine().toCharArray());
        	for(int i = 0; i < encode.length; i++ ) {
        		encode[i] ^= base[i % base.length];
        	}
        	String s = "";
        	int shift = 7;
        	for(int i = encode.length -8; i < encode.length; i++) {
        		s += fix(Integer.toHexString(new Integer(encode[i])));
        	}
        	BigInteger bI = new BigInteger(s, 16);
        	s = "";
            while(bI.compareTo(new BigInteger("0")) == 1){
                s = ALPHABET.charAt(Integer.parseInt(bI.mod(new BigInteger("62")).toString())) + s;
                bI = bI.divide(new BigInteger("62"));
            }
            System.out.println(baseS + "/" + s);
        }
        input.close();
    }
    
    private static String fix(String s) {
    	if(s.length() == 0) {
    		return "00";
    	}else if(s.length() == 1) {
    		return "0" + s;
    	}
    	return s;
    }
    private static byte[] toByte(char[] line) {
        ByteBuffer bb = Charset.forName("UTF-8").encode(CharBuffer.wrap(line));
        byte[] b = new byte[bb.remaining()];
        bb.get(b);
        return b;
    }
}

