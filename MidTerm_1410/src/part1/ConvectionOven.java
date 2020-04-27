/**************************************
 * Author: Carlos Martinez
 * Date: February 14, 2017
 * Assignment: Mid Term Part 1
 *************************************/

package part1;

/**
 * This class creates an object of ConvectionOven
 * This class extends class Oven
 * @author Carlos Martinez
 */
public class ConvectionOven extends Oven{
	
	//Constructor
	/**
	 * This constructor creates an object of ConvectionOven
	 * @param size The size of the Oven
	 */
	public ConvectionOven(int size) {
		super(size);
	}
	
	//Methods
	/**
	 * This method heats the food by circulating air
	 */
	@Override
	public void heatFood() {
		super.heatFood();
		System.out.println(" by circulating hot air");
	}
	
}