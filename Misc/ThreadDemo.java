import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ThreadDemo {
	
	public static void main (String[] args) {
		
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\doubleThread");
		
		/*try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String letter;
			Thread thread1 = new Thread (" *THREAD1* ") {
				public void run () {
					for (int i = 0; i <= 10; i++) {
						while ((letter = input.readLine()) != null) {
							
						}
					}
				}
			};
					
			Thread thread2 = new Thread(" *THREAD2* ") {
				public void run () {
					for (int i = 0; i <= 10; i++) {
						while ((letter = input.readLine()) != null) {
							
						}
					}
				}
			};
		} catch (IOException e) {
			System.out.println("File not found!");
		}*/
		
		
	}
}
