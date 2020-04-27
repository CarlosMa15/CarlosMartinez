/*************************************
 * Author: Carlos Martinez
 * Date: March 9, 2017
 * Assignment: ListVsSet
 ************************************/
package listVsSet;

import java.awt.Color;

/**
 * This class creates an object of ColoredSquares,
 * @author Carlos Martinez
 */
public class ColoredSquare {
	
	/**
	 * This is the length of the side of the Square
	 */
	private int side;
	
	/**
	 * This is the color of the square
	 */
	private Color color;
	
	/**
	 * This constructor creates an object of Colored Square
	 * @param s the length of the side of the square
	 * @param c the color of the square
	 */
	public ColoredSquare(int s, Color c) {
		this.side = s;
		this.color = c;
	}
	
	/**
	 * This method calculates the area of the square
	 * @return the area of the square
	 */
	public int area() {
		return this.side * this.side;
	}
	
	/**
	 * Override hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((color == null) ? 0 : color.hashCode());
		result = prime * result + side;
		return result;
	}
	
	/**
	 * Override equals method
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColoredSquare other = (ColoredSquare) obj;
		if (color == null) {
			if (other.color != null)
				return false;
		} else if (!color.equals(other.color))
			return false;
		if (side != other.side)
			return false;
		return true;
	}
	
	/**
	 * This is a toString Method that displays the element in the form of side: (side length) (color's hex format)
	 */
	@Override
	public String toString() {
		String hex = String.format("#%02X%02X%02X", this.color.getRed(), this.color.getGreen(), this.color.getBlue());
		return "side:" + this.side + " " + hex;
	}
}