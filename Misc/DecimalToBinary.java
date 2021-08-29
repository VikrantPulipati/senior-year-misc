import java.util.Scanner;
import java.util.Stack;

public class DecimalToBinary {
	public DecimalToBinary () {
		int num;
		Scanner reader = new Scanner(System.in);
		System.out.println("Please enter a decimal integer: ");
		num = reader.nextInt();
		int temp = Math.abs(num);
		Stack<Integer> binary = new Stack<Integer>();
		while (temp != 0) {
			binary.push(temp%2);
			temp -= binary.peek();
			temp /= 2;
		}
		
		if (binary.isEmpty()) {
			binary.push(0);
		}
		
		System.out.print("The binary integer is: ");
		if (num < 0) {
			System.out.print("-");
		}
		
		while (!binary.isEmpty()) {
			System.out.print(binary.peek());
			binary.pop();
		}
		
	}
	
	public static void main (String[] args) {
		DecimalToBinary app = new DecimalToBinary();
	}
}
