import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class AlphabetOrder {
	
	public AlphabetOrder () {
		PriorityQueue<Word> pq = new PriorityQueue<Word>();
		Queue<Word> q = new LinkedList<Word>();
		
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\Paragraph");
		String txt = "";
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			while ((text=input.readLine()) != null) {
				txt = text;
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}	
		
		txt = txt.replaceAll("[^a-zA-Z\\d\\s]", "");
		String[] arr = txt.split(" ");
		
		for (int i = 0; i < arr.length; i++) {
			Word word = new Word(arr[i]);
			q.add(word);
			pq.add(word);
		}
		
		while (!q.isEmpty()) {
			System.out.println(String.format("%-25s%-25s", q.poll(), pq.poll()));
		}
	}
	
	class Word implements Comparable<Word> {
		
		private String word;
		
		public Word (String str) {
			word = str;
		}
		
		public String toString () {
			return this.word;
		}
		
		@Override
		public int compareTo (Word o) {
			return -1*(this.word.compareToIgnoreCase(o.toString()));
		}

	}
	
	public static void main (String[] args) {
		AlphabetOrder app = new AlphabetOrder();		
	}
}
