import java.io.*;
import java.util.ArrayList;

public class AmicableNumbers2 {
	
	public AmicableNumbers2 () {
		File fileName = new File("D:\\Data Structures\\Data Structures Lessons\\src\\AmicableNums");
		try {
			BufferedReader input = new BufferedReader(new FileReader(fileName));
			String text;
			int num1 = 0;
			int num2 = 0;
			while ((text=input.readLine()) != null) {
				try {
					String[] strings = text.split(" ");
					int[] nums = new int[2];
					for (int i = 0; i < 2; i++) {
						nums[i] = Integer.parseInt(strings[i]);
					}
					ArrayList<Integer> list1 = factors(nums[0]);
					ArrayList<Integer> list2 = factors(nums[1]);	
					int sum1 = list1.get(list1.size()-1);
					int sum2 = list2.get(list2.size()-1);
					
					if (nums[0]==sum2 && nums[1]==sum1) {
						System.out.println("The numbers " + nums[0] + " and " + nums[1] + " are amicable");
						printFactors(list1);
						printFactors(list2);
						System.out.println();
					} else {
						System.out.println("The numbers " + nums[0] + " and " + nums[1] + " are not amicable");
						printFactors(list1);
						printFactors(list2);
						System.out.println();
					}
				} catch (NumberFormatException nfe) {
					System.out.println("NFE");
				}
			}
		} catch (IOException e) {
			System.out.println("File Not Found");
		}
	}
	
	public static void main (String[] args) {
		AmicableNumbers2 app = new AmicableNumbers2();
	}
	
	public int factorSum (ArrayList<Integer> list) {
		int sum = 0;
		for (int i = 1; i < list.size(); i++) {		
			sum += list.get(i);
		}
		return sum;
	}
	
	public void printFactors (ArrayList<Integer> list) {
		int listSize = list.size() ;
		System.out.print("Factors of " + list.get(0) + " are ");

		for (int i = 1; i < listSize-1; i++) {
			if (i == list.size()-2) {
				System.out.print("and " + list.get(i) + ".");
			} else 
			{
				System.out.print(list.get(i) + ", ");
			}
		}
	
		System.out.println(" Sum is " + list.get(listSize-1) + ".");
		
	}
	public ArrayList<Integer> factors (int num) {
		System.out.print("Factors of " + num + " are ");
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(num);
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				list.add(i);
			}
		}
		int sum = factorSum(list);
		list.add(sum);
		return list;
	}
}
