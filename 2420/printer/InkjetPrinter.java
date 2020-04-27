/******************************************
 * Author: Carlos Martinez
 * Date: February 7, 2017
 * Assignment: Lab Printer
 *****************************************/
package printer;

/**
 * This class creates a printer of type 
 * Ink Jet. The class extends abstract 
 * class printer.
 * @author Carlos Martinez
 */
public class InkjetPrinter extends Printer{
	
	/**
	 * This is the amount of remaining cartridge
	 */
	private int remainingCartridge;
	
	/**
	 * This constructor creates an object of type
	 * InkJectPrinter with a serial number and
	 * with a full cartridge
	 * @param serialNumber
	 */
	public InkjetPrinter(int serialNumber) {
		super(serialNumber);
		this.remainingCartridge = 100; 
	}
	
	/**
	 * This method get the amount of cartridge
	 * @return The amount of cartridge
	 */
	public int getRemainingCartridge(){
		return this.remainingCartridge;
	}
	
	/**
	 * This method prints with the printer using up
	 * 10% of the cartridge till its empty
	 */
	@Override
	public void print() {
		if(getRemainingCartridge() > 0){
			this.remainingCartridge -= 10;
			System.out.println("InkjetPrinter is printing. "
					+ "Remaining cartridge: " 
					+ this.remainingCartridge + "%");
		}
		else {
			System.out.println("Cartridge is empty");
		}
	}
}