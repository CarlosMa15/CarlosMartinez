/**********************************************
 * Author: Carlos Martinez
 * Date: Jan 28, 2017
 * Assignment: Inheritance
 *********************************************/
package inheritance;

/**
 * This Class creates a new object of type Rectangle
 * @author Carlos Martinez
 */
public class Rectangle {
	//Fields
	/**
	 * This is the Length of the Rectangle
	 */
	private int length;
	/**
	 * This is the width of the Rectangle
	 */
	private int width;
	
	//Constructor
	/**
	 * This Constructor creates an object of Rectangle.
	 * @param length The length of the Rectangle.
	 * @param width The Width of the Rectangle.
	 */
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}
	
	//Methods
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * This is a toString method that Overrides the method to print
	 * the object in the form of Rectangle(Length X Width).
	 */
	@Override
	public String toString() {
		return "Rectangle(" + getLength() + "x" + getWidth() + ")";
	}
}