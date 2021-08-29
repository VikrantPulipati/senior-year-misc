import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class FlightLog {
	public FlightLog () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\PassengerInfo");
		
		Queue<Passenger> passengers = new LinkedList<Passenger>();
		PriorityQueue<Passenger> pqPassengers = new PriorityQueue<Passenger>();
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int counter = 1;
			String ln = "";
			String fn = "";
			String city = "";
			String time = "";
			while ((text=input.readLine()) != null) {
				if (counter == 1) {
					String[] arr = text.split(" ");
					ln = arr[1];
					fn = arr[0];
				}
				else if (counter == 2) {
					city = text;
				}
				else if (counter == 3) {
					time = text;
				}
				counter++;
				if (counter > 3) {
					counter = 1;
					
					passengers.add(new Passenger(ln, fn, city, time));
					pqPassengers.add(new Passenger(ln, fn, city, time));
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
		
		System.out.println("QUEUE:");
		while (!passengers.isEmpty()) {
			System.out.println(passengers.poll());
		}
		System.out.println();
		System.out.println("PRIORITY QUEUE:");
		while (!pqPassengers.isEmpty()) {
			System.out.println(pqPassengers.poll());
		}
	}
	
	public class Passenger implements Comparable<Passenger> {
		private String lastName;
		private String firstName;
		private String city;
		private String time;
		
		public Passenger (String ln, String fn, String city, String time) {
			lastName = ln;
			firstName = fn;
			this.city = city;
			this.time = time;
		}
		
		public String getLastName () {
			return lastName;
		}
		
		public String getFirstName () {
			return firstName;
		}
		
		public String flightCity () {
			return city;
		}
		
		public String flightTime () {
			return time;
		}
		
		public String etdCalc () {
			String t = time.replaceAll(":", " ");
			String[] timeArr = t.split(" ");
			int mins = Integer.parseInt(timeArr[1]);
			if (Integer.parseInt(timeArr[0]) < 12 && timeArr[2].equals("PM")) {
				timeArr[0] = Integer.toString(Integer.parseInt(timeArr[0]) + 12);
			}
			int hours = Integer.parseInt(timeArr[0]);
			
			int departTime = hours*60 + mins;
			int difference = departTime - 543;
			
			return difference/60 + ":" + difference%60;
		}

		@Override
		public int compareTo(Passenger o) {
			String[] thisTime = this.etdCalc().split(":");
			String[] otherTime = o.etdCalc().split(":");
			int hour = Integer.parseInt(thisTime[0]);
			int min = Integer.parseInt(thisTime[1]);
			int otherHour = Integer.parseInt(otherTime[0]);
			int otherMin = Integer.parseInt(otherTime[1]);
			if (!(hour != 0 && otherHour != 0)) {
				if (hour > otherHour) {
					return 1;
				}
				else if (hour < otherHour) {
					return -1;
				} else {
					if (min > otherMin) {
						return 1;
					}
					else if (min == otherMin) {
						return 0;
					} else {
						return -1;
					}
				}
			} else {
				return 0;
			}
		}
		
		public String toString () {
			return lastName + ", " + firstName + " - " + city + " - " + time + " - " + this.etdCalc();
		}
		
	}
	
	public static void main (String[] args) {
		FlightLog app = new FlightLog();
	}
}
