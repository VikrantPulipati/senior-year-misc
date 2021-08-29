import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

public class CarApp {
	public CarApp () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\CarData");
		Queue<Car> q = new LinkedList<Car>();
		Stack<Car> stack = new Stack<Car>();
		PriorityQueue<Car> pq = new PriorityQueue<Car>();
		String[] headings = null;
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int counter = 1;
			while ((text=input.readLine()) != null) {
				if (counter == 1) {
					headings = text.split("\\t");
				}
				else if (counter > 1) {
					ArrayList<Integer> list = new ArrayList<Integer>();
					String[] arr = text.split("\\t");
					for (int i = 0; i < arr.length; i++) {
						if (!(arr[i].equals(""))) {
							list.add(Integer.parseInt(arr[i]));
						}
					}
					q.add(new Car(list.get(0), list.get(1), list.get(2), list.get(3), list.get(4), list.get(5), list.get(6), list.get(7)));
				}
				
				counter++;
			}
		} catch (IOException e) {
			System.out.println("File Not Found!");
		}
		
		System.out.println("QUEUE:");
		printHeaders(headings);
		while (!q.isEmpty()) {
			System.out.println(String.format("%-10s%-20s%-25s%-15s%-20s%-25s%-20s%-25s",
					q.peek().getID(), q.peek().getMPG(), q.peek().getSize(), q.peek().getHP(),
					q.peek().getWgt(), q.peek().getAcc(), q.peek().getCountry(), q.peek().getCyls()));
			stack.push(q.poll());
		}
		
		System.out.println("\nSTACK:");
		printHeaders(headings);
		while (!stack.isEmpty()) {
			System.out.println(String.format("%-10s%-20s%-25s%-15s%-20s%-25s%-20s%-25s",
					stack.peek().getID(), stack.peek().getMPG(), stack.peek().getSize(), stack.peek().getHP(),
					stack.peek().getWgt(), stack.peek().getAcc(), stack.peek().getCountry(), stack.peek().getCyls()));
			pq.add(stack.pop());
		}
		
		System.out.println("\nPRIORITY QUEUE:");
		printHeaders(headings);
		while (!pq.isEmpty()) {
			System.out.println(String.format("%-10s%-20s%-25s%-15s%-20s%-25s%-20s%-25s",
					pq.peek().getID(), pq.peek().getMPG(), pq.peek().getSize(), pq.peek().getHP(),
					pq.peek().getWgt(), pq.peek().getAcc(), pq.peek().getCountry(), pq.peek().getCyls()));
			Car e = pq.poll();
		}
	}
	
	public void printHeaders (String[] headings) {
		System.out.println(String.format("%-10s%-20s%-25s%-15s%-20s%-25s%-20s%-25s",
				headings[0], headings[1], headings[2], headings[3], headings[4], headings[5], headings[6], headings[7]));
	}
	
	public static void main (String[] args) {
		CarApp app = new CarApp();
	}
	
	public class Car implements Comparable<Car> {
		
		private int carID;
		private int mpg;
		private int engSize;
		private int hp;
		private int weight;
		private int acc;
		private int country;
		private int cyls;
		
		public Car (int id, int miles, int size, int power, int wgt, int acceleration, int origin, int cylinders) {
			carID = id;
			mpg = miles;
			engSize = size;
			hp = power;
			weight = wgt;
			acc = acceleration;
			country = origin;
			cyls = cylinders;
		}
		
		public int getID () {
			return carID;
		}
		
		public int getMPG () {
			return mpg;
		}
		
		public int getSize () {
			return engSize;
		}
		
		private int getHP () {
			return hp;
		}
		
		private int getWgt () {
			return weight;
		}
		
		private int getAcc () {
			return acc;
		}
		
		private int getCountry () {
			return country;
		}
		
		private int getCyls () {
			return cyls;
		}

		public int compareTo(Car o) {
			if (acc > o.getAcc()) {
				return -1;
			}
			else if (acc < o.getAcc()) {
				return 1;
			} else {
				if (mpg > o.getMPG()) {
					return -1;
				}
				else if (mpg < o.getMPG()) {
					return 1;
				} else {
					if (hp > o.getHP()) {
						return -1;
					}
					else if (hp < o.getHP()) {
						return 1;
					} else {
						if (engSize > o.getSize()) {
							return -1;
						}
						else if (engSize < o.getSize()) {
							return 1;
						} else {
							if (weight > o.getWgt()) {
								return -1;
							}
							else if (weight < o.getWgt()) {
								return 1;
							} else {
								if (cyls > o.getCyls()) {
									return -1;
								}
								else if (cyls < o.getCyls()) {
									return 1;
								} else {
									if (carID > o.getID()) {
										return 1;
									}
									else if (carID < o.getID()) {
										return -1;
									} else {
										return 0;
									}
								}
							}
						}
					}
				}
			}
		}
	}
}
