/**************************************
 * Author: Carlos Martinez
 * Date: February 14, 2017
 * Assignment: Mid Term Part 1
 *************************************/

package part1;

/**
 * This class creates an object of Oven
 * @author Carlos Martinez
 */
public class Oven {
	
	//Fields
	/**
	 * This is the size of the Oven in ft^3
	 */
	private double size;
	
	//Constructor
	/**
	 * This Constructor creates an object of Oven
	 * @param size The size of the Oven in ft^3
	 */
	public Oven(double size){
		this.size = size;
	}
	
	//Methods
	/**
	 * This method heats the food
	 */
	public void heatFood() {
		System.out.print("heating food");
	}

	/**
	 * This is a toString method that print the Oven in the form of
	 * Type of Oven followed by the size in ft^3
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() + " " + this.size + " ft\u00B3";
	}	
}
