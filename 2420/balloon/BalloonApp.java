package balloon;

public class BalloonApp {
	
	/**
	 * creates balloon
	 * @param args
	 */
	public static void main(String[] args) {
		Balloon b1 = new Balloon("air",Size.S);
		Balloon b2 = new Balloon("air",Size.S);
		Balloon b3 = b1;
		
		System.out.println("b1.euals(b2)"  + b1.equals(b2));
		System.out.println("b1.euals(b3)" + b1.equals(b3));
		
	}
}