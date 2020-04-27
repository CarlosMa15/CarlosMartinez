/************************************************
 * Author: Carlos Martinez
 * Date: February 5, 2017
 * Assignment: Interface
 ***********************************************/

package interfaceAssignment;

/**
 * This is an interface that can be used to calculate 
 * the perimeter and area of the given shape that 
 * implements the interface
 * @author Carlos Martinez
 *
 */
public interface Shape {
	
	/**
	 * This method can be used to calculate the 
	 * perimeter of the shape that implements the interface
	 * @return	The perimeter of the shape
	 */
	double perimeter();
	
	/**
	 * This Method can be used to calculate the 
	 * area of the given shape that uses the interface
	 * @return The area of the Shape
	 */
	double area();
	
}
