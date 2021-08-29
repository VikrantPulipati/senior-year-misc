import java.io.*;

public class FileTemplate {
	public FileTemplate () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\example");
		int sum = 0;
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text=input.readLine()) != null) {
				int num = 0;
				try {
					num = Integer.parseInt(text);
				} catch (NumberFormatException nfe) {
					
				}
				sum+=num;
			}
			System.out.println(sum);
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
	public static void main (String[] args) {
		FileTemplate app = new FileTemplate();
	}
}
