import java.io.*;
import java.util.ArrayList;

public class AmicableNumbers {
	
	public AmicableNumbers () {
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
					if (nums[0]==factorSum(nums[1]) && nums[1]==factorSum(nums[0])) {
						System.out.println("The numbers " + nums[0] + " and " + nums[1] + " are amicable");
						factorList(nums[0]);
						factorList(nums[1]);
						System.out.println();
					} else {
						System.out.println("The numbers " + nums[0] + " and " + nums[1] + " are not amicable");
						factorList(nums[0]);
						factorList(nums[1]);
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
		AmicableNumbers app = new AmicableNumbers();
	}
	
	public int factorSum (int num) {
		int sum = 0;
		if (num == 1) {
			sum = 0;
		} else {
			for (int i = 1; i < num; i++) {
				if (num % i == 0) {
					sum += i;
				}
			}
		}
		return sum;
	}
	public void factorList (int num) {
		System.out.print("Factors of " + num + " are ");
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i < num; i++) {
			if (num % i == 0) {
				list.add(i);
			}
		}
		for (int i = 0; i < list.size(); i++) {
			if (i == list.size()-1 && list.size() > 1) {
				System.out.print("and " + list.get(i) + ".");
			}
			else if (i == list.size()-1 && list.size() == 1) {
				System.out.print(list.get(i) + ".");
			}
			else if (list.size() > 2) {
				System.out.print(list.get(i) + ", ");
			} else {
				System.out.print(list.get(i) + " ");
			}
		}
		System.out.println(" Sum is " + factorSum(num) + ".");
	}
}
