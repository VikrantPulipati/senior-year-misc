import java.io.*;

public class GuitarHero {
	
	public GuitarHero () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\GuitarTabs");
		
		int[][] helper = getHelper();
		String[][] noteHelper = getNoteHelper();
		
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			String[][] notes = new String[5][];
			int x = 0;
			while ((text=input.readLine()) != null) {
				notes[x] = text.split(",");
				x++;
			}
			String[][] output = new String[30][notes[0].length+1];
			for (int i = 0; i < output.length; i++) {
				for (int j = 0; j < output[0].length; j++) {
					if (i == 0) {
						if (j == 0) {
							output[i][j] = "Measure";
						} else {
							output[i][j] = Integer.toString(j);
						}
					}
				}
			}
			
			for (int i = helper.length-1; i >= 0; i--) {
				for (int j = helper[0].length-1; j >= 0; j--) {
					output[helper[i][j]][0] = noteHelper[i][j];
				}
			}
			
			for (int j = 0; j < notes[0].length; j++) {
				for (int i = 0; i < notes.length; i++) {
					for (int k = 0; k < notes[i][j].length(); k++) {
						if ((notes[i][j]).charAt(k) == 'o' || (notes[i][j]).charAt(k) == '*') {
							output[helper[i][k]][j+1] = "O";
						}
						else if (notes[i][j].charAt(k) == '-' || notes[i][j].charAt(k) == 'x') {
							output[helper[i][k]][j+1] = " ";
						}
					}
				}
			}
			
			printMusic(output);
			
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
	}
	
	public int[][] getHelper () {
		int[][] output = new int[][] {
			{29, 24, 19, 14, 10, 5},
			{28, 23, 18, 13, 9, 4},
			{27, 22, 17, 12, 8, 3},
			{26, 21, 16, 11, 7, 2},
			{25, 20, 15, 10, 6, 1}
		};
		
		return output;
	}
	
	public String[][] getNoteHelper () {
		String[][] output = new String[][] {
			{"E", "A", "D", "G", "B", "E"},
			{"F", "A#", "D#", "G#", "C", "F"},
			{"F#", "B", "E", "A", "C#", "F#"},
			{"G", "C", "F", "A#", "D", "G"},
			{"G#", "C#", "F#", "B", "D#", "G#"}
		};
		
		return output;
	}
	
	public void printMusic (String[][] output) {
		for (int i = 0; i < output.length; i++) {
			for (int j = 0; j < output[0].length; j++) {
				System.out.print(output[i][j] + "\t");
				if (j == 0) {
					System.out.print("\t");
				}
			}
			System.out.println();
		}
	}
	
	public static void main (String[] args) {
		GuitarHero app = new GuitarHero();
	}
}
