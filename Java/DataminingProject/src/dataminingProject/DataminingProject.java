package dataminingProject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataminingProject {

	public static void main(String[] args) {
		File file = new File("/Users/carlosmartinez/Desktop/Java/DataminingProject/src/dataminingProject/data.txt");

		String strain = "Acinetobacter | Fluoroquinolones";
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				
				
				String data = bufferReader.readLine().replaceAll("\\s+", " ").trim();
				String[] parts = data.split(" ");
				try {
					System.out.println(strain + " | 2012 | " + parts[0] + "  | " + Double.parseDouble(parts[2]));
				} catch (NumberFormatException e) {
					System.out.println(strain + " | 2012 | " + parts[0] + " | 1");
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				
				
				String data = bufferReader.readLine().replaceAll("\\s+", " ").trim();
				String[] parts = data.split(" ");
				try {
					System.out.println(strain + " | 2013 | " + parts[0] + "  | " + Double.parseDouble(parts[5]));
				} catch (NumberFormatException e) {
					System.out.println(strain + " | 2013 | " + parts[0] + " | 1");
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				
				
				String data = bufferReader.readLine().replaceAll("\\s+", " ").trim();
				String[] parts = data.split(" ");
				try {
					System.out.println(strain + " | 2014 | " + parts[0] + "  | " + Double.parseDouble(parts[8]));
				} catch (NumberFormatException e) {
					System.out.println(strain + " | 2014 | " + parts[0] + " | 1");
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}
		
		try {
			@SuppressWarnings("resource")
			BufferedReader bufferReader = new BufferedReader(new FileReader(file));

			while (bufferReader.ready()) {
				
				
				String data = bufferReader.readLine().replaceAll("\\s+", " ").trim();
				String[] parts = data.split(" ");
				try {
					System.out.println(strain + " | 2015 | " + parts[0] + "  | " + Double.parseDouble(parts[11]));
				} catch (NumberFormatException e) {
					System.out.println(strain + " | 2015 | " + parts[0] + " | 1");
				}
			}

		} catch (FileNotFoundException e) {
			System.err.println("File Not Found Exception!");
			e.printStackTrace();
		} catch (IOException e) {
			System.err.println("IOException!");
			e.printStackTrace();
		}

	}

}
