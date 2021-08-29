
public class Candidate {
	
	private String name;
	
	private String party;
	public static String DEMOCRATIC = "Democratic";
	public static String REPUBLICAN = "Republican";
	
	private String office;
	
	public Candidate (String n, String p, String o) {
		this.name = n;
		this.party = p;
		this.office = o;
	}
	
	public String toString () {
		return name + " is running for " + office + " on the " + party + " ticket.";
	}
	
}
