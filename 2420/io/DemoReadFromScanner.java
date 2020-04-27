package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DemoReadFromScanner {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		File file = new File("scr/io/Resources/Numbers.txt");
		
		try(Scanner reader = new Scanner(file)) { 
			while(reader.hasNextLine()) {
				System.out.println(reader.nextLine().replace(' ','.'));
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("Exeption: File Could Not Be Found");
			e.printStackTrace();
		}
	}
}