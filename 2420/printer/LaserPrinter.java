/******************************************
 * Author: Carlos Martinez
 * Date: February 7, 2017
 * Assignment: Lab Printer
 *****************************************/
package printer;

/**
 * This class creates an object of Laser
 * Printer with a full toner
 * @author Carlos Martinez
 */
public class LaserPrinter extends Printer{
	/**
	 * The amount of Toner remaining
	 */
	private int remainingToner;
	
	/**
	 * This is a constructor that creates an
	 * object of LaserPrinter with a full toner
	 * and a serial number.
	 * @param serialNumber
	 */
	public LaserPrinter(int serialNumber) {
		super(serialNumber);
		this.remainingToner = 100;
	}
	
	/**
	 * This method returns the amount of remaining toner
	 * @return The amount of remaining toner
	 */
	public int getRemainingToner(){
		return this.remainingToner;
	}
	
	/**
	 * This method print using the printer using up 10%
	 * of the toner with each print till it is empty
	 */
	@Override
	public void print() {
		if(getRemainingToner() > 0){
			this.remainingToner -= 10;
			System.out.println("LaserPrinter is "
					+ "printing. Remaining toner: " 
					+ getRemainingToner() + "%");
		}
		else System.out.println("Toner is empty.");
	}
}