package flinal;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import edu.princeton.cs.algs4.ST;
import edu.princeton.cs.algs4.StdOut;

/**
 * SYMBOL TABLE
 */
public class Exercise1Prep {

	public static void main(String[] args) {
		ST<Integer, Integer> st = new ST<>();

		String fileName = "src/flinal/Number.txt";
		File file = new File(fileName);
		try (Scanner reader = new Scanner(file)) {
			while (reader.hasNextLine()) {
				String[] line = reader.nextLine().split(",");
				st.put(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		//Checking Value of key 35
		System.out.println("Value at 35: " + st.get(35));
		System.out.println();
		
		//Deleting Key 35 and its value from table
		st.delete(35);
		
		//Is There a Key 39 in the table
		System.out.println("Does the ST contain Key 39: " + st.contains(39));
		System.out.println();
		
		//Is ST empty
		System.out.println("Is ST empty: " + st.isEmpty());
		
		//Number of Keys
		System.out.println("Number of Keys: " + st.size());
		System.out.println();
		
		//Smallest and Biggest Key
		System.out.println("Smallest Key: " + st.min());
		System.out.println("Biggest Key: " + st.max());
		System.out.println();
		
		
		System.out.println("Largest Key eaual or less that 30: "  + st.floor(30));
		System.out.println();
		
		System.out.println("smallest Key eaual or greater that 30: "  + st.ceiling(30));
		System.out.println();
		
		int counter = 1;
		for (Integer s : st.keys()) {
			StdOut.println(counter++ + ": " +s + " " + st.get(s));
		}
		
	}
}