package io;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class DemoPrintWriter {

	public static void main(String[] args) {
		try(PrintWriter writer = new PrintWriter("Output.txt")) {
			writer.printf("Numbers  Squares%n");
			int number = 2;
			for(int i = 0; i < 7; i++) {
				writer.printf("%7d  %-7d%n",number, (number*number));
				number *= 2;
			}
			
			
		} catch (FileNotFoundException e) {
			System.out.print("==> ");
			e.printStackTrace();
		}
		
		System.out.println("Done Writing");
	}
}