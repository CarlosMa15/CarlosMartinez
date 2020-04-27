/******************************************
 * Author: Carlos Martinez
 * Date: February 7, 2017
 * Assignment: Lab Printer
 *****************************************/
package printer;

/**
 * This is an abstract class that creates an
 * object of type printer with a serial 
 * number and an abstract method to print.
 * @author Carlos Martinez
 */
public abstract class Printer {
	
	/**
	 * The Serial Number of the printer
	 */
	private int serialNumber;

	/**
	 * The constructor creates an object of
	 * type printer
	 * @param serialNumber The serial number
	 * of the printer
	 */
	public Printer(int serialNumber) {
		this.serialNumber = serialNumber;
	}
	
	/**
	 * An abstract method of type printer
	 * with no implementation
	 */
	public abstract void print();
	
	/**
	 * The method of toString that prints
	 * the object in the form of: Type of
	 * printer followed by the serial number
	 */
	@Override
	public String toString() {
		return getClass().getSimpleName() 
				+ " #" + this.serialNumber;
	}
}