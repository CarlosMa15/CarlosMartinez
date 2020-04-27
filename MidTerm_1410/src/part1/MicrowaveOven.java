/**************************************
 * Author: Carlos Martinez
 * Date: February 14, 2017
 * Assignment: Mid Term Part 1
 *************************************/
package part1;

/**
 * This class creates an object MicrowaveOven
 * This class extends the class Oven
 * @author Carlos Martinez
 *
 */
public class MicrowaveOven extends Oven{
	
	//Fields
	/**
	 * This is the amount of power the MicrowaveOven has
	 */
	private int power;
	
	//Constructor
	/**
	 * This constructor creates an object of MicrowaveOven
	 * @param size The size of the Oven in ft^3
	 * @param power The Power of the MicrowaveOven
	 */
	public MicrowaveOven(double size, int power) {
		super(size);
		this.power = power;
	}
	
	//Methods
	/**
	 * This method defrosts the food
	 */
	public void autoDefrost(){
		System.out.println("defrosting ...");
	}
	
	/**
	 * This Method heat the food using Microwaves
	 */
	@Override
	public void heatFood() {
		super.heatFood();
		System.out.println(" using microwaves");
	}
	
	/**
	 * This Method print the MicrowaveOven the same way the
	 * Oven prints but with the power added as well
	 */
	@Override
	public String toString() {
		return super.toString() + " " + this.power + " W";
	}
}