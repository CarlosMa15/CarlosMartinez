package demension;

public class DimensionApp {

	public static void main(String[] args) {
		
		Dimension dimension1 = new Dimension(6,4);
		System.out.println("Dimension1 : " + dimension1);
		
		Rug rug1 = new Rug(new Dimension(4,3), new Dimension(2,7));
		System.out.println("rug1: " + rug1);
		
		Rug rug2 = new Rug(new Dimension(1,0), new Dimension(1,9));
		System.out.println("rug1: " + rug2);
		System.out.println("Area: " + rug2.area());
	}
}