import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.TreeMap;
import java.util.TreeSet;

public class CensusData {
	public CensusData () {
		ArrayList<Citizen> list = new ArrayList<Citizen>();
		
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\census");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text=input.readLine()) != null) {
				if (text.length() > 0 && text.charAt(0) == '1') {
					Citizen cit = new Citizen(text);
					list.add(cit);
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		Collections.sort(list);
		for (int i = list.size()-1; i >= 0; i--) {
			if (list.get(i).lastName.equals(".") && list.get(i).firstName.equals(".")) {
				list.remove(i);
			}
		}
		
		//streetCitizen(list);
		//birthplaceAge(list);
		//mothertongueName(list);
		//occupationFather(list);
		//genderRemark(list);
		//rentValue(list);
		genderOccupation(list);
	}
	
	public void streetCitizen (ArrayList<Citizen> list) {
		TreeMap<String, TreeSet<Citizen>> map = new TreeMap<String, TreeSet<Citizen>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.street)) {
				map.put(c.street, new TreeSet<Citizen>());
			}
			map.get(c.street).add(c);
		}
		
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			
			String street = it.next();
			System.out.println(street + ":");
			TreeSet<Citizen> temp = map.get(street);
			for (Citizen c : temp) {
				System.out.println("\t" + c);
			}
		}
	}
	
	public void birthplaceAge (ArrayList<Citizen> list) {
		TreeMap<String, PriorityQueue<Double>> map = new TreeMap<String, PriorityQueue<Double>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.birthplace)) {
				map.put(c.birthplace, new PriorityQueue<Double>());
			}
			map.get(c.birthplace).add(c.age);
		}
		
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String birthplace = it.next();
			System.out.println(birthplace + ":");
			PriorityQueue<Double> temp = map.get(birthplace);
			for (Double age : temp) {
				if (birthplace.equals("Pennsylvania")) {
					System.out.println("\t" + temp.size() + " citizens");
					break;
				} else {
					System.out.println("\t" + age);
				}
			}
		}
	}
	
	public void mothertongueName (ArrayList<Citizen> list) {
		TreeMap<String, ArrayList<String>> map = new TreeMap<String, ArrayList<String>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.motherTongue)) {
				map.put(c.motherTongue, new ArrayList<String>());
			}
			map.get(c.motherTongue).add(c.lastName + ", " + c.firstName);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String motherTongue = it.next();
			if (!motherTongue.equals(".")) {
				System.out.println(motherTongue + ":");
				ArrayList<String> temp = map.get(motherTongue);
				for (String name : temp) {
					System.out.println("\t" + name);
				}
			}
		}
	}
	
	public void occupationFather (ArrayList<Citizen> list) {
		TreeMap<String, HashSet<String>> map = new TreeMap<String, HashSet<String>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.occupation)) {
				map.put(c.occupation, new HashSet<String>());
			}
			map.get(c.occupation).add(c.fathersBirthplace);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String occupation = it.next();
			if (!occupation.equals(".")) {
				System.out.println(occupation + ":");
				HashSet<String> temp = map.get(occupation);
				for (String fathersBirthplace : temp) {
					System.out.println("\t" + fathersBirthplace);
				}
			}
		}
	}
	
	public void genderRemark (ArrayList<Citizen> list) {
		TreeMap<String, HashSet<String>> map = new TreeMap<String, HashSet<String>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.gender)) {
				map.put(c.gender, new HashSet<String>());
			}
			map.get(c.gender).add(c.transcriberRemarks);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String gender = it.next();
			if (!gender.equals(".")) {
				System.out.println(gender + ":");
				HashSet<String> temp = map.get(gender);
				for (String remarks : temp) {
					System.out.println("\t" + remarks);
				}
			}
		}
	}
	
	public void rentValue (ArrayList<Citizen> list) {
		TreeMap<String, TreeSet<Double>> map = new TreeMap<String, TreeSet<Double>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.rentOrOwn)) {
				map.put(c.rentOrOwn, new TreeSet<Double>());
			}
			map.get(c.rentOrOwn).add(c.propertyValue);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String rentOrOwn = it.next();
			if (!rentOrOwn.equals(".")) {
				System.out.println(rentOrOwn + ":");
				TreeSet<Double> temp = map.get(rentOrOwn);
				for (Double propVal : temp) {
					System.out.println("\t$" + propVal);
				}
			}
		}
	}
	
	public void genderOccupation (ArrayList<Citizen> list) {
		TreeMap<String, HashSet<String>> map = new TreeMap<String, HashSet<String>>();
		for (Citizen c : list) {
			if (!map.containsKey(c.gender)) {
				map.put(c.gender, new HashSet<String>());
			}
			map.get(c.gender).add(c.occupation);
		}
		Iterator<String> it = map.keySet().iterator();
		while (it.hasNext()) {
			String gender = it.next();
			if (!gender.equals(".")) {
				System.out.println(gender + ":");
				HashSet<String> temp = map.get(gender);
				for (String occupation : temp) {
					if (!occupation.equals(".")) {
						System.out.println("\t" + occupation);
					}
				}
			}
		}
	}
	
	public static void main (String[] args) {
		CensusData app = new CensusData();
	}
	
	
}
