/**************************************
 * Author: Carlos Martinez
 * Date: February 14, 2017
 * Assignment: Mid Term Part 1
 *************************************/
package part1;

public class OvenApp {

	public static void main(String[] args) {
		//Creating Oven Array
		Oven[] ovens = {new MicrowaveOven(2,1000), new ConvectionOven(3),
				new MicrowaveOven(1.5,750)};
		
		//functionality
		for(Oven o : ovens) {
			System.out.println(o);
			if (o instanceof MicrowaveOven) {
				((MicrowaveOven) o).autoDefrost();
			}
			// This was used to test the heatFood() method
			// o.heatFood();
		}
	}
}