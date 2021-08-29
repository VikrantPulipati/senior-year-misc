import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class MapTask {
	public MapTask () {
		TreeMap<Integer, PriorityQueue<Bowler>> bowlers = new TreeMap<Integer, PriorityQueue<Bowler>>();
		
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\BowlingData");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text=input.readLine()) != null) {
				String[] arr = text.split(" ");
				if (!bowlers.containsKey(Integer.parseInt(arr[2]))) {
					PriorityQueue<Bowler> pq = new PriorityQueue<Bowler>();
					pq.add(new Bowler(arr[0], arr[1]));
					bowlers.put(Integer.parseInt(arr[2]), pq);
				} else {
					bowlers.get(Integer.parseInt(arr[2])).add(new Bowler(arr[0], arr[1]));
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
		
		Iterator key = bowlers.keySet().iterator();
		System.out.print("{");
		while (key.hasNext()) {
			int n = (int) key.next();
			System.out.print(n + "=");
			printQueue(new PriorityQueue<Bowler>(bowlers.get(n)));
			if (key.hasNext()) {
				System.out.print(", ");
			}
		}
		System.out.println("}");
		
		System.out.println("+++++++++++++++++++++++++++++++++++\r\n" + 
				"+++++++++++++++KEYS++++++++++++++++\r\n" + 
				"+++++++++++++++++++++++++++++++++++");
		Iterator keys = bowlers.keySet().iterator();
		while (keys.hasNext()) {
			System.out.println(keys.next());
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++++\r\n" + 
				"+++++++++++++ENTRY SET+++++++++++++\r\n" + 
				"+++++++++++++++++++++++++++++++++++");
		Iterator entries = bowlers.keySet().iterator();
		while (entries.hasNext()) {
			int n = (int) entries.next();
			System.out.print(n + "=");
			printQueue(new PriorityQueue<Bowler>(bowlers.get(n)));
			System.out.println();
		}
		
		System.out.println("+++++++++++++++++++++++++++++++++++\r\n" + 
				"++++++++++++++VALUES+++++++++++++++\r\n" + 
				"+++++++++++++++++++++++++++++++++++");
		Iterator values = bowlers.values().iterator();
		while (values.hasNext()) {
			printQueue((PriorityQueue<Bowler>)values.next());
			System.out.println();
		}
		
	}
	
	public void printQueue (PriorityQueue<Bowler> q) {
		System.out.print("[");
		PriorityQueue<Bowler> queue = q;
		while(!queue.isEmpty()) {
			System.out.print(queue.poll());
			if (queue.size() > 0) {
				System.out.print(", ");
			}
		}
		System.out.print("]");
	}
	
	public class Bowler implements Comparable {
		private String lastName;
		private String firstName;
		
		public Bowler (String f, String l) {
			lastName = l;
			firstName = f;
		}
		
		public String toString () {
			return firstName + " " + lastName;
		}
		
		public String getLast () {
			return lastName;
		}
		
		public String getFirst () {
			return firstName;
		}

		@Override
		public int compareTo(Object o) {
			if (lastName.compareTo(((Bowler)o).getLast()) > 0) {
				return 1;
			}
			else if (lastName.compareTo(((Bowler)o).getLast()) < 0) {
				return -1;
			} else {
				if (firstName.compareTo(((Bowler)o).getFirst()) > 0) {
					return 1;
				}
				else if (firstName.compareTo(((Bowler)o).getFirst()) < 0) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	
	public static void main (String[] args) {
		MapTask app = new MapTask();
	}
}
