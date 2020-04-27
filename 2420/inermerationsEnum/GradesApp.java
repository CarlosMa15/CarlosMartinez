package inermerationsEnum;

public class GradesApp {

	public static void main(String[] args) {
		Grade myGrade = Grade.A;
		System.out.println("My Grade: " + myGrade + "\n");
		
		for(Grade el: Grade.values()) {
			System.out.printf("%s %2d - %-3d\n",el.toString().charAt(0),el.getLowerBound(),el.getUpperBound());
		}
	}
}