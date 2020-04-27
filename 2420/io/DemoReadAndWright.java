package io;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class DemoReadAndWright {

	public static void main(String[] args) {
		try (BufferedReader reader = new BufferedReader(new FileReader("src/io/numbers.txt"));
				PrintWriter writer = new PrintWriter("Invictus2.txt")) {
			String line;
			while((line = reader.readLine()) != null) {
				line = line.trim();
				line = line.replaceAll("\\w+", "XXX");
				writer.println(line);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Done Writing");
	}
}