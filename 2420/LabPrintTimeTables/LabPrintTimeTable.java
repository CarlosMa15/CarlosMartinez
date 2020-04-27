package LabPrintTimeTables;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class LabPrintTimeTable {

	public static void main(String[] args) {
		try( PrintWriter writer = new PrintWriter("src/LabPrintTimeTables/TimeTables.txt")) {
			
			int odd = 1;
			int even = 2;
			for(int i = 0; i < 5; i++) {
				for(int j = 0; j < 10;j++) {
					writer.printf("%2d x %-2d = %-2d  %2d x %-2d = %-2d%n",j+1,odd,odd*(j+1),j+1,even,even*(j+1));
				}
				writer.println();
				even += 2;
				odd += 2;
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("Done Writing");
	}
}