import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Spiral {
	public Spiral () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\SpiralTxt");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text=input.readLine()) != null) {
				int num = 0;
				try {
					num = Integer.parseInt(text);
					System.out.println(new SpiralObj(num));
				} catch (NumberFormatException nfe) {
					System.out.println("Not a number!");
				}
			}			
		} catch (IOException e) {
			System.out.println("File not found!");
		}
	}
	
	public static void main (String[] args) {
		Spiral app = new Spiral();
	}
}
