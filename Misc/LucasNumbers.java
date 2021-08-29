import java.io.*;
import java.math.BigInteger;

public class LucasNumbers {
	public LucasNumbers () {
		File filename = new File("D:\\\\Data Structures\\\\Data Structures Lessons\\\\src\\\\LucasNums");
		try {
			BufferedReader input = new BufferedReader(new FileReader(filename));
			String text;
			while ((text=input.readLine()) != null) {
				long x = 0;
				try {
					x = Integer.parseInt(text);
					System.out.println(getLucasNum(x));
				} catch (NumberFormatException nfe) {
					System.out.println("Not a number!");
				}
			}
		} catch (IOException e) {
			System.out.println("File not found!");
		}
	}
	
	public static void main (String[] args) {
		LucasNumbers app = new LucasNumbers();
	}
	
	public BigInteger getLucasNum (long x) {
        BigInteger sum = BigInteger.valueOf(1);
        BigInteger prev = BigInteger.valueOf(2);
        BigInteger current = BigInteger.valueOf(0);
        
        if (x > 0) {
            for (int i = 0; i < x; i++) {
            	current = sum;
            	sum = prev.add(sum);
            	prev = current;
            }
        } else {
        	current = BigInteger.valueOf(2);
        }
        return current;
	}
}
