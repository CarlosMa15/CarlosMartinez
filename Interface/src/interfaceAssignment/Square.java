/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

/**
 * This class creates an object of type Square,
 * the class extends class Rectangle
 * @author Carlos Martinez
 */
public class Square extends Rectangle {
	
	//Constructor
	/**
	 * This constructor creates an object of type Square
	 * @param side The length of the sides of the Square,
	 * all sides of the square are equal length
	 */
	public Square(int side) {
		super(side, side);
	}
	
	//Methods
	public int getSide(){
		return super.getLength();
	}
	
	/**
	 * This is a toString method that overrides the toString to
	 * print the object in the form of Square(Side)
	 */
	@Override
	public String toString() {
		return "Square (" + getSide() + ")";
	}
}