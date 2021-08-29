import java.util.Stack;

public class ReverseString {
	public ReverseString () {
		String str = "hvuidkmdn bhfuwiwueyrhejkidfujn";
		Stack<Character> reverse = new Stack<Character>();
				
		for (int i = 0; i < str.length(); i++) {
			reverse.push(str.charAt(i));
		}
		
		while (!reverse.isEmpty()) {
			System.out.print(reverse.peek());
			reverse.pop();
		}
	}
	
	public static void main (String[] args) {
		ReverseString app = new ReverseString();
	}
}
