/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

/**
 * This Class creates a new object of type Rectangle
 * 
 * @author Carlos Martinez
 */
public class Rectangle implements Shape, Printable {
	// Fields
	/**
	 * This is the Length of the Rectangle
	 */
	private int length;
	/**
	 * This is the width of the Rectangle
	 */
	private int width;

	// Constructor
	/**
	 * This Constructor creates an object of Rectangle.
	 * 
	 * @param length
	 *            The length of the Rectangle.
	 * @param width
	 *            The Width of the Rectangle.
	 */
	public Rectangle(int length, int width) {
		this.length = length;
		this.width = width;
	}

	// Methods
	public int getLength() {
		return length;
	}

	public int getWidth() {
		return width;
	}

	/**
	 * This is a toString method that Overrides the method to print the object
	 * in the form of Rectangle(Length X Width).
	 */
	@Override
	public String toString() {
		return "Rectangle(" + getLength() + "x" + getWidth() + ")";
	}

	/**
	 * This method prints the out line of the Rectangle
	 */
	@Override
	public void print() {
		if (getLength() != 0) {
			if (getWidth() != 0) {
				for (int i = 0; i < getLength(); i++) {
					System.out.print("o ");
				}
				System.out.println();

				if (getLength() != 1) {
					for (int i = 0; i < getWidth() - 2; i++) {
						System.out.print("o ");
						for (int j = 0; j < getLength() - 2; j++) {
							System.out.print("  ");
						}
						System.out.print("o ");
						System.out.println("  ");
					}
				} else {
					System.out.println("o ");
				}

				if (getWidth() != 1) {
					for (int i = 0; i < getLength(); i++) {
						System.out.print("o ");
					}
				}
			}
		}
		System.out.println();
	}

	/**
	 * This method calculates the perimeter of the Rectangle
	 * 
	 * @return The perimeter of Rectangle
	 */
	@Override
	public double perimeter() {
		return getWidth() + getWidth() + getLength() + getLength();
	}

	/**
	 * This method calculates the area of the Rectangle.
	 * 
	 * @return The area of the Rectangle
	 */
	@Override
	public double area() {
		return getLength() * getWidth();
	}
}