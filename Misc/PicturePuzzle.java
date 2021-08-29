import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class PicturePuzzle {
	public PicturePuzzle () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\ASCIIPicture");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			String[] arr = new String[0];
			while ((text=input.readLine()) != null) {
				arr = text.split("");
			}
			for (int i = 49; i < 300; i++) {
				int value = i;
				int counter = 0;
				for (String part : arr) {
					System.out.print(part);
					if (counter % (value) == 0) {
						System.out.println();
					}
					counter++;
				}
				System.out.println("\n\n" + "Print Value: " + i + "\n\n");
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	public static void main (String[] args) {
		PicturePuzzle app = new PicturePuzzle();
	}
}
