package demension;

public class Dimension {
	
	//Fields
	private int foot;
	private int inch;
	
	//Constructor
	public Dimension(int foot, int inches) {
		this.foot = foot;
		this.inch = inches;
	}
    
	//Methods
	@Override
	public String toString() {
		return foot+ "'" + inch + "\"";
	}

	public int getFoot() {
		return foot;
	}

	public int getInch() {
		return inch;
	}
	
	public int asInches(){
		return foot * 12 + inch;
	}
}