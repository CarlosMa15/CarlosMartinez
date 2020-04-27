package printer;

public class PrinterApp {

	public static void main(String[] args) {
		InkjetPrinter ink = new InkjetPrinter(1234567);
		System.out.println(ink);
		System.out.println("Remaining Cartridge: " + ink.getRemainingCartridge() + "%");
		System.out.println();
		
		LaserPrinter laser = new LaserPrinter(2345678);
		System.out.println(laser);
		System.out.println("Remaining Toner: " + laser.getRemainingToner() + "%");
		System.out.println();
		
		System.out.println("List of Printer:");
		
		Printer[] printers = {ink,laser};
		
		for (Printer el: printers){
			System.out.println(el);
			for(int i = 0; i < 11; i++){
				el.print();
			}
			System.out.println();
		}
	}
}