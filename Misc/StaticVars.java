
public class StaticVars {
	public static void main (String[] args) {
		Candidate joeBiden = new Candidate("Joseph Biden", Candidate.DEMOCRATIC, "President");
		System.out.println(joeBiden);
		
		Candidate.DEMOCRATIC = "Democratic Party";
		Candidate bernieSanders = new Candidate("Bernard Sanders", Candidate.DEMOCRATIC, "President");
		System.out.println(bernieSanders);
	}
}
