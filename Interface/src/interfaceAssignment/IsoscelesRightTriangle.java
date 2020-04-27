/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

/**
 * This class creates an object of type
 * IsoscelesRightTriangle
 * @author Carlos Martinez
 *
 */
public class IsoscelesRightTriangle implements Shape,Printable{
	//Field
	/**
	 * this is the length of the two equal
	 * sides of the triangle that meet at a
	 * right angle in one of the corners of the
	 * triangle.
	 */
	private int leg;

	//Constructor
	/**
	 * This creates an object of type IsoscelesRightTriangle
	 * @param leg The length of the two equal
	 * sides of the triangle that meet at a
	 * right angle in one of the corners of the
	 * triangle.
	 */
	public IsoscelesRightTriangle(int leg) {
		this.leg = leg;
	}
	
	/**
	 * This method calculate the length of the hypotenuse side
	 * of the triangle use a math method hypot(double,double) 
	 * from class Math
	 * @return The length of the hypotenuse
	 */
	public double hypotenuse(){
		return Math.hypot(getLeg(), getLeg());
	}
	
	public int getLeg() {
		return leg;
	}
	
	/**
	 * This is a toString that overrides the method to print the object
	 * in the form of IsoscelesRightTriangle(leg)
	 */
	@Override
	public String toString() {
		return "IsoscelesRightTriangle(" + getLeg() + ")";
	}

	@Override
	public void print() {
		if(getLeg() != 0){
			System.out.println("o ");
		}
		
		for (int i = 0; i < getLeg() - 2; i++){ 
			System.out.print("o ");
			
			for (int j = 0; j < i; j++){
				System.out.print("  ");
			}
			
			System.out.println("o ");
		}
		
		if(getLeg() != 1){
			for(int i = 0; i < getLeg(); i++){
				System.out.print("o ");
			}
		}
		
		System.out.println();
	}
	
	/**
	 * This method calculates the perimeter of the IsoscelesRightTriangle
	 * @return The perimeter of the IsoscelesRightTriangle
	 */
	@Override
	public double perimeter() {
		return getLeg() + getLeg() + hypotenuse();
	}
	
	/**
	 * This methods calculates the area of the IsoscelesRightTriangle
	 * @return The area of the IsoscelesRightTriangle
	 */
	@Override
	public double area() {
		int areaOfSquare = getLeg() * getLeg();
		return areaOfSquare / 2;
	}
}