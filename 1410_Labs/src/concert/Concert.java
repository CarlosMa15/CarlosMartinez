package concert;

public class Concert {
	
	//Fields
	private String name;
	private Time startTime;
	private Time endTime;
	
	//Constructor
	public Concert(String n, Time start, Time end) {
		this.name = n;
		this.startTime = start;
		this.endTime = end;
	}
	
	//Methods
	public String toString(){
		return "The Concert " + this.name + ", Starts at " + this.startTime + " and ends at "
				+ this.endTime +".";
	}
}
