import java.io.*;
import java.util.Calendar;
import java.util.Date;

public class TimeTravel {
	public TimeTravel () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\TravelTime");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			String[] travelTime;
			int trip = 1;
			while ((text=input.readLine()) != null) {
				try {
					System.out.println("Trip " + trip + ":");
					travelTime = text.split(" ");
					Calendar c = Calendar.getInstance();
					System.out.println("   Departure Date and Time: " + printTime(c));
					
					c.add(Calendar.DATE, Integer.parseInt(travelTime[0]));
					c.add(Calendar.HOUR, Integer.parseInt(travelTime[1]));
					c.add(Calendar.MINUTE, Integer.parseInt(travelTime[2]));
					
					System.out.println("   Arrival Date and Time: " + printTime(c));
					System.out.println();
				} catch (NumberFormatException nfe) {
					System.out.println("Not a number!");
				}
				
				trip++;
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
	public String printTime (Calendar c) {
		String output = "";
		if (c.get(c.HOUR) == 0) {
			output = output.concat(Integer.toString(12));
		} else {
			output = output.concat(Integer.toString(c.get(c.HOUR)));
		}
		if (c.get(c.MINUTE) < 10) {
			output = output.concat(":0" + c.get(c.MINUTE) + " ");
		} else {
			output = output.concat(":" + c.get(c.MINUTE) + " ");
		}
		if (c.get(c.AM_PM) == Calendar.PM) {
			output = output.concat("PM");
		} else {
			output = output.concat("AM");
		}
		
		output = output.concat(" on " + (c.get(c.MONTH)+1) + "/" + c.get(c.DAY_OF_MONTH) + "/" + c.get(c.YEAR));
		
		return output;
	}
	
	public static void main (String[] args) {
		TimeTravel app = new TimeTravel();
	}
}
