/**********************************************
 * Author: Carlos Martinez
 * Date: Jan 28, 2017
 * Assignment: Inheritance
 *********************************************/
package inheritance;

/**
 * This class creates an object of type
 * IsoscelesRightTriangle
 * @author Carlos Martinez
 *
 */
public class IsoscelesRightTriangle {
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
}