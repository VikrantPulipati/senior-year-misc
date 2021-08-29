import java.text.DecimalFormat;

public class Player implements Comparable<Player> {
	
	private String name;
	private double pick;
	private String pos;
	private String team;
	private int bye;
	private double overall;
	private double sd;
	private double highRd;
	private double lowRd;
	private int timesDrafted;
	private int draftCons;
	
	public Player (double pick, String name, String pos, String team, int bye, double overall, double sd, double highRd, double lowRd,  int timesDrafted) {
		this.name = name;
		this.pick = pick;
		this.pos = pos;
		this.team = team;
		this.bye = bye;
		this.overall = overall;
		this.sd = sd;
		this.highRd = highRd;
		this.lowRd = lowRd;
		this.timesDrafted = timesDrafted;
		this.draftCons = ((int)(lowRd)*12 + (int)((lowRd%1)*100)) - ((int)(highRd)*12 + (int)((highRd%1)*100));
	}

	@Override
	public int compareTo(Player o) {
		if (this.draftCons == o.draftCons) {
			if (this.overall == o.overall) {
				return 0;
			}
			else if (this.overall > o.overall) {
				return -1;
			} else {
				return 1;
			}
		}
		else if (this.draftCons < o.draftCons) {
			return 1;
		} else {
			return -1;
		}
	}
	
	public String toString () {
		DecimalFormat f = new DecimalFormat("0.00");
		String picksSt = f.format(pick);
		String overallSt = f.format(overall);
		String lowSt = f.format(lowRd);
		String highSt = f.format(highRd);
		return String.format("%-25s%-8s%-8s%-8s%-8s%-13s%-13s%-13s%-13s%-13s", this.name, picksSt, this.pos, this.team, this.bye, overallSt, this.sd, highSt, lowSt, this.timesDrafted);
	}
}
