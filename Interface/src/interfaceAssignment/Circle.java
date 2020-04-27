/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

/**
 * This class creates a new object of type Circle
 * @author Carlos Martinez
 */
public class Circle implements Shape{
	
	//Fields
	/**
	 * The length of the radius of the circle
	 */
	private int radius;
	
	//Constructor
	/**
	 * This constructor creates a new object of type Circle
	 * @param radius The length of the radius of the circle.
	 */
	public Circle(int radius) {
		this.radius = radius;
	}
	
	//Methods
	/**
	 * This method calculates the length of the diameter
	 * @return The length of the diameter
	 */
	public int diameter(){
		return getRadius() + getRadius();
	}
	
	/**
	 * This method calculates the circumference of the circle
	 * with the  help of Math.PI
	 * @return The circumference of the circle.
	 */
	public double circumference(){
		return diameter() * Math.PI;
	}
	
	public int getRadius() {
		return radius;
	}
	
	/**
	 * This is a toString that overrides the method to print the object
	 * in the form of Circle(Radius)
	 */
	@Override
	public String toString() {
		return "Circle(" + getRadius() + ")";
	}
	
	/**
	 * This method calculates the circumference of the circle
	 * @return the circumference of the Circle.
	 */
	@Override
	public double perimeter() {
		return 2 * Math.PI * getRadius();
	}
	
	/**
	 * This method calculates the area of the circle
	 * @return The area of the Circle
	 */
	@Override
	public double area() {
		return Math.PI * getRadius() * getRadius();
	}
}