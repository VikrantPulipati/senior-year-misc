import java.io.*;
import java.util.ArrayList;
import java.util.Stack;

public class StarWarsCharacters {
	
	public StarWarsCharacters () {		
		ArrayList<SWCharacter> arr = new ArrayList<SWCharacter>();
		
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\CharacterList");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			int line = 1;
			String text;
			while ((text = input.readLine()) != null) {
				if (line>1) {
					String[] splitText = text.split(",");
					if (splitText.length == 9) {
						arr.add(new SWCharacter(splitText[0], splitText[5], splitText[6], splitText[7], splitText[8]));
					} else {
						arr.add(new SWCharacter(splitText[0], splitText[6], splitText[7], splitText[8], splitText[9]));
					}
				}
				line++;
			}	
		} catch (IOException e) {
			System.out.println("Error: File not found!");
		}
		
		Stack<SWCharacter> males = new Stack<SWCharacter>();
		Stack<SWCharacter> females = new Stack<SWCharacter>();
		Stack<SWCharacter> droids = new Stack<SWCharacter>();
		Stack<SWCharacter> validYear = new Stack<SWCharacter>();
		
		for (int i = 0; i < arr.size(); i++) {
			if (arr.get(i).getGender().equals("male") || arr.get(i).getGender().equals("hermaphrodite")) {
				males.push(arr.get(i));
			}
			if (arr.get(i).getGender().equals("female") || arr.get(i).getGender().equals("hermaphrodite")) {
				females.push(arr.get(i));
			}
			if (arr.get(i).isDroid()) {
				droids.push(arr.get(i));
			}
			if (arr.get(i).getYear() != null) {
				validYear.push(arr.get(i));
			}
		}
		
		//Print Males
		System.out.println("Male Characters");
		System.out.println(String.format("%-30s%-20s", "Name", "Homeworld"));
		while (!males.isEmpty()) {
			System.out.println(String.format("%-30s%-20s", males.peek().getName(), males.peek().getHome()));
			males.pop();
		}
		
		System.out.println();
		
		//Print Females
		System.out.println("Female Characters");
		System.out.println(String.format("%-30s%-20s", "Name", "Homeworld"));
		while (!females.isEmpty()) {
			System.out.println(String.format("%-30s%-20s", females.peek().getName(), females.peek().getHome()));
			females.pop();
		}
		
		System.out.println();
		
		//Print Droids
		System.out.println("Droids");
		System.out.println(String.format("%-30s%-20s", "Name", "Homeworld"));
		while (!droids.isEmpty()) {
			System.out.println(String.format("%-30s%-20s", droids.peek().getName(), droids.peek().getHome()));
			droids.pop();
		}
		
		System.out.println();
		
		//Print Ages
		System.out.println("Ages");
		System.out.println(String.format("%-30s%-20s%-20s", "Name", "Homeworld", "Birth Year (BBY)"));
		while (!validYear.isEmpty()) {
			System.out.println(String.format("%-30s%-20s%-20s", validYear.peek().getName(), validYear.peek().getHome(), Double.parseDouble(validYear.peek().getYear())));
			validYear.pop();
		}
		
	}
	
	public class SWCharacter {
		
		String name;
		String birthYear;
		String gender;
		String home;
		String species;
		
		public SWCharacter (String n, String bY, String g, String h, String s) {
			name = n;
			birthYear = bY;
			gender = g;
			home = h;
			species = s;
		}
		
		public String getName () {
			return name;
		}
		
		public String getYear () {
			if (birthYear.equals("NA")) {
				return null;
			} else {
				return birthYear.substring(0, birthYear.length()-3);
			}
		}
		
		public String getGender () {
			return gender;
		}
		
		public String getHome () {
			if (home.equals("NA")) {
				return "Unknown";
			} else {
				return home;
			}
		}
		
		public String getSpecies () {
			return species;
		}
		
		public boolean isDroid () {
			return name.matches(".*\\d+.*");
		}
		
		public String toString () {
			return name;
		}
		
	}
	
	public static void main (String[] args) {
		StarWarsCharacters app = new StarWarsCharacters();
	}
}
