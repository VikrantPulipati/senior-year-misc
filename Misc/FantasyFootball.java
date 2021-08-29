import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class FantasyFootball {
	public FantasyFootball () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\DraftAverages");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			ArrayList<Player> list = new ArrayList<Player>();
			String text;
			try {
				int x = 0;
				while ((text = input.readLine()) != null) {
					x++;
					if (x>1) {
						String[] data = text.split(";");
						list.add(new Player(Double.parseDouble(data[0]), data[1], data[2], data[3], Integer.parseInt(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]),  Integer.parseInt(data[9])));
					}
				}
			} catch (NumberFormatException nfe) {
				System.out.println("yo");
			}
			Collections.sort(list);
			Collections.reverse(list);
			System.out.println(String.format("%-25s%-8s%-8s%-8s%-8s%-13s%-13s%-13s%-13s%-13s", "Name", "Pick", "Pos.", "Team", "Bye", "Overall", "Std. Dev.", "High Pos.", "Low Pos.", "Times Drafted"));
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i));
			}
		} catch (IOException e) {
			System.out.println("File not found");
		}
	}
	
	public static void main (String[] args) {
		FantasyFootball app = new FantasyFootball();
	}
}
