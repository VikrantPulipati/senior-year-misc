
public class Citizen implements Comparable<Citizen> {
	
	public String firstName;
	public String lastName;
	
	public String street;
	public int streetNumber;
	
	public String relation;
	
	public String rentOrOwn;
	public double propertyValue;
	
	public String gender;
	public double age;
	public String maritalStatus;
	public int ageAtFirstMarriage;
	
	public boolean attendSchool;
	public boolean canRead;
	
	public String birthplace;
	public String fathersBirthplace;
	public String mothersBirthplace;
	public String motherTongue;
	public int yearImmigrated;
	
	public String occupation;
	public String industry;
	public String transcriberRemarks;
	
	
	public Citizen (String str) {
		this.firstName = str.substring(71, 88).trim();
		this.lastName = str.substring(55, 71).trim();
		
		this.street = str.substring(20, 36).trim();
		try {
			this.streetNumber = Integer.parseInt(str.substring(36, 45).trim());
		} catch (NumberFormatException e) {
			this.streetNumber = -1;
		}
		
		this.relation = str.substring(88, 108).trim();
		
		this.rentOrOwn = str.substring(108, 113).trim();
		String propVal = str.substring(113, 121).trim();
		if (propVal.charAt(0) == '$') {
			propVal = propVal.substring(1);
		}
		try {
			this.propertyValue = Double.parseDouble(propVal);
		} catch (NumberFormatException e) {
			if (propVal.contains("/")) {
				String integer = propVal.substring(0, propVal.indexOf(" "));
				String numer = propVal.substring(propVal.indexOf(" ")+1, propVal.indexOf("/"));
				String denom = propVal.substring(propVal.indexOf("/")+1);
				this.propertyValue = Double.parseDouble(integer) + Double.parseDouble(numer)/Double.parseDouble(denom);
			}
		}
		
		this.gender = str.substring(133, 138).trim();
		
		String a = str.substring(143, 151).trim();
		try {
			this.age = Double.parseDouble(a);
		} catch (NumberFormatException e) {
			if (a.charAt(0) == '.' || a.equals("un")) {
				this.age = -1;
			}
			else if (a.charAt(1) == ' ' && a.contains("/")) {
				String whole = a.substring(0, a.indexOf(" "));
				double dec;
				if (a.substring(a.indexOf(" ")+1, a.indexOf("/")).contains("*")) {
					dec = 0.5;
				} else {
					String numer = a.substring(a.indexOf(" ") + 1, a.indexOf("/"));
					String denom = a.substring(a.indexOf("/")+1);
					dec = Double.parseDouble(numer)/Double.parseDouble(denom);
				}
				this.age = Double.parseDouble(whole)+dec;
			}
			else if (a.contains("*")) {
				this.age = Double.parseDouble(a.substring(0, a.indexOf("*")));
			} else {
				String numer = a.substring(0, a.indexOf("/"));
				String denom = a.substring(a.indexOf("/")+1);
				this.age = Double.parseDouble(numer)/Double.parseDouble(denom);
			}
		}
		this.maritalStatus = str.substring(151, 156).trim();
		try {
			this.ageAtFirstMarriage = Integer.parseInt(str.substring(156, 162).trim());
		} catch (NumberFormatException e) {
			this.ageAtFirstMarriage = -1;
		}
		
		String school = str.substring(162, 167).trim();
		if (school.equals("Yes")) {
			this.attendSchool = true;
		} else {
			this.attendSchool = false;
		}
		
		String read = str.substring(167, 173).trim();
		if (read.equals("Yes")) {
			this.canRead = true;
		} else {
			this.canRead = false;
		}
		
		this.birthplace = str.substring(173, 190).trim();
		this.fathersBirthplace = str.substring(190, 207).trim();
		this.mothersBirthplace = str.substring(207, 224).trim();
		this.motherTongue = str.substring(224, 235).trim();
		try {
			this.yearImmigrated = Integer.parseInt(str.substring(235, 241).trim());
		} catch (NumberFormatException e) {
			this.yearImmigrated = -1;
		}
		
		String job = str.substring(252, 274).trim();
		this.occupation = job.substring(0, 1).toUpperCase() + job.substring(1).toLowerCase();
		this.industry = str.substring(274, 303).trim();
		this.transcriberRemarks = str.substring(342).trim();
	}


	public int compareTo(Citizen o) {
		if (this.firstName.compareTo(o.firstName)<0) {
			return -1;
		}
		else if (this.firstName.compareTo(o.firstName)>0) {
			return 1;
		}
		if (this.lastName.compareTo(o.lastName)<0) {
			return -1;
		}
		else if (this.lastName.compareTo(o.lastName)>0) {
			return 1;
		}
		
		if (this.street.compareTo(o.street)<0) {
			return -1;
		}
		else if (this.street.compareTo(o.street)>0) {
			return 1;
		}
		if (this.streetNumber < o.streetNumber) {
			return -1;
		}
		else if (this.streetNumber > o.streetNumber) {
			return 1;
		}
		if (this.relation.compareTo(o.relation)<0) {
			return -1;
		}
		else if (this.relation.compareTo(o.relation)>0) {
			return 1;
		}
		if (this.rentOrOwn.compareTo(o.rentOrOwn)<0) {
			return -1;
		}
		else if (this.rentOrOwn.compareTo(o.rentOrOwn)>0) {
			return 1;
		}
		if (this.propertyValue < o.propertyValue) {
			return -1;
		}
		else if (this.propertyValue > o.propertyValue) {
			return 1;
		}
		
		if (this.gender.compareTo(o.gender)<0) {
			return -1;
		}
		else if (this.gender.compareTo(o.gender)>0) {
			return 1;
		}
		if (this.age < o.age) {
			return -1;
		}
		else if (this.age > o.age) {
			return 1;
		}
		if (this.maritalStatus.compareTo(o.maritalStatus)<0) {
			return -1;
		}
		else if (this.maritalStatus.compareTo(o.maritalStatus)>0) {
			return 1;
		}
		if (this.ageAtFirstMarriage < o.ageAtFirstMarriage) {
			return -1;
		}
		else if (this.ageAtFirstMarriage > o.ageAtFirstMarriage) {
			return 1;
		}
		
		if (!this.attendSchool && o.attendSchool) {
			return -1;
		}
		else if (this.attendSchool && !o.attendSchool) {
			return 11;
		}
		if (!this.canRead && o.canRead) {
			return -1;
		}
		else if (this.canRead && !o.canRead) {
			return 1;
		}
		
		if (this.birthplace.compareTo(o.birthplace)<0) {
			return -1;
		}
		else if (this.birthplace.compareTo(o.birthplace)>0) {
			return 1;
		}
		if (this.fathersBirthplace.compareTo(o.fathersBirthplace)<0) {
			return -1;
		}
		else if (this.fathersBirthplace.compareTo(o.fathersBirthplace)>0) {
			return 1;
		}
		if (this.mothersBirthplace.compareTo(o.mothersBirthplace)<0) {
			return -1;
		}
		else if (this.mothersBirthplace.compareTo(o.mothersBirthplace)>0) {
			return 1;
		}
		if (this.motherTongue.compareTo(o.motherTongue)<0) {
			return -1;
		}
		else if (this.motherTongue.compareTo(o.motherTongue)>0) {
			return 1;
		}
		if (this.yearImmigrated < o.yearImmigrated) {
			return -1;
		}
		else if (this.yearImmigrated > o.yearImmigrated) {
			return 1;
		}
		
		if (this.occupation.compareTo(o.occupation)<0) {
			return -1;
		}
		else if (this.occupation.compareTo(o.occupation)>0) {
			return 1;
		}
		if (this.industry.compareTo(o.industry)<0) {
			return -1;
		}
		else if (this.industry.compareTo(o.industry)>0) {
			return 1;
		}
		
		if (this.transcriberRemarks.compareTo(o.transcriberRemarks)<0) {
			return -1;
		}
		else if (this.transcriberRemarks.compareTo(o.transcriberRemarks)>0) {
			return 1;
		}
		
		return 0;
	}
	
	public String toString () {
		return String.format("%-25sAge: %s", this.lastName + ", " + this.firstName, this.age);
	}
}
